package org.ow2.aspirerfid.ide.bpwme.actions;

import java.util.HashSet;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditor;
import org.ow2.aspirerfid.ide.bpwme.master.views.MasterEditor;
import org.ow2.aspirerfid.ide.bpwme.master.views.MasterEditorInput;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

public class ShowMasterDataEditor extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if(MainUtil.isEditorOpened(MasterEditor.ID)) {
			MessageBox messageBox = new MessageBox(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
			messageBox.setMessage("Editor is already opened.\nPlease close it and try again.");
			messageBox.open();
			return null;
		}
		
		//if bpwme editor is closed, do nothing
		if(!MainUtil.isEditorOpened(BpwmeDiagramEditor.ID)) {
			MessageBox messageBox = new MessageBox(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
			messageBox.setMessage("BPWME Editor is not open.\nPlease open it and try again.");
			messageBox.open();
			return null;
		}
		
		MasterEditorInput mei = new MasterEditorInput();
		MainControl mc = MainControl.getMainControl();

		IWorkbenchPage page = PlatformUI.getWorkbench().
		getActiveWorkbenchWindow().getActivePage();
		try {
			page.openEditor(mei, MasterEditor.ID);
			mc.saveObject();
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		HashSet<String> editorIDs = new HashSet<String>();
		editorIDs.add(ECSpecEditor.ID);
		editorIDs.add(MasterEditor.ID);
		
		MainUtil.splitEditorArea(BpwmeDiagramEditor.ID,editorIDs);
		return null;
	}

}
