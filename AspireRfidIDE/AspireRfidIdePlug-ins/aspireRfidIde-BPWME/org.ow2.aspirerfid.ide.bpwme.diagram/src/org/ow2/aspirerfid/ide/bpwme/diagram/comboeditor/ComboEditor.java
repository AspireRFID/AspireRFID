package org.ow2.aspirerfid.ide.bpwme.diagram.comboeditor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.diagram.xmleditor.XMLEditor;


/**
 * Combo editor is a multipage editor that combines BPWME editor and XML editor
 * 
 * @author yluo
 *
 */

public class ComboEditor extends MultiPageEditorPart{
	
	public static final String ID = "bpwme.diagram.comboeditor.ComboEditor";

	private XMLEditor xmlEditor;
	private BpwmeDiagramEditor bpwmeEditor;
	private PageChangeListener listener;
	
	private void createXMLPage() {
		xmlEditor = new XMLEditor();
		try {
			int index = addPage(xmlEditor, ((ComboInput)getEditorInput()).getPei());
			setPageText(index, "XML Editor");			
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	private void createBPWMEPage() {
		bpwmeEditor = new BpwmeDiagramEditor();
		try {
			int index = addPage(bpwmeEditor, ((ComboInput)getEditorInput()).getUei());
			setPageText(index, "BPWME Editor");
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	public BpwmeDiagramEditor getBpwmeEditor() {
		return bpwmeEditor;
	}
	
	@Override
	protected void createPages() {
		createBPWMEPage();
		createXMLPage();
		addListener();
		
	}

	private void addListener() {
		listener = new PageChangeListener();
		addPageChangedListener(listener);
	}
	
	@Override
	public void dispose() {
		removePageChangedListener(listener);
		super.dispose();
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		xmlEditor.doSave(monitor);
		bpwmeEditor.doSave(monitor);
	}
	
	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	class PageChangeListener implements IPageChangedListener{

		@Override
		public void pageChanged(PageChangedEvent event) {
			if(event.getSelectedPage() instanceof XMLEditor) {
				((XMLEditor)event.getSelectedPage()).refresh();
			}
		}
		
	}
}
