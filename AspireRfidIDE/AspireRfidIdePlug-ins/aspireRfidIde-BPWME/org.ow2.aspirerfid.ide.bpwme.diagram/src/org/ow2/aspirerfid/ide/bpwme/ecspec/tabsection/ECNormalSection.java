package org.ow2.aspirerfid.ide.bpwme.ecspec.tabsection;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.ReportSpec;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.Spec;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ECSpecEditor;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

public class ECNormalSection extends AbstractPropertySection {

	private Text text;
	private ReportSpec input;
	
	
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		this.input = (ReportSpec)((IStructuredSelection) selection).getFirstElement();
		if(input != null){		
			this.text.setText(input.getName());
		}
	}
	
	@Override
	public void createControls(final Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		
		Composite mainComposite = getWidgetFactory().createFlatFormComposite(parent);
		mainComposite.setLayout(new GridLayout(3, false));
		
		//nkef (Changed from "Reader Name" to "Report Name")
		final Label label = getWidgetFactory().createLabel(mainComposite, "Report Name");
		//label.setLayoutData(new GridData(36, SWT.DEFAULT));
		//label.setText("Reader Name");
		text = getWidgetFactory().createText(mainComposite, "");
		text.setLayoutData(new GridData(100, SWT.DEFAULT));
		Button updateButton = getWidgetFactory().createButton(mainComposite, "Update", SWT.PUSH);
		updateButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				//input.setName(text.getText());
				ECSpecEditor editor = (ECSpecEditor) MainUtil.getEditor(ECSpecEditor.ID);
				if(editor != null) {
					input.setName(text.getText());
					editor.setDirty(true);
				}
			}
		});
	}
	
	

}
