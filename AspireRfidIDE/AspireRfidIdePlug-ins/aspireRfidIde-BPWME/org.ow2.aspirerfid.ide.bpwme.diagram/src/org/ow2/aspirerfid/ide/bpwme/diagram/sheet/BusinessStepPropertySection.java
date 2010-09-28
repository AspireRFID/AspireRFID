package org.ow2.aspirerfid.ide.bpwme.diagram.sheet;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class BusinessStepPropertySection extends AbstractPropertySection{
	private ListViewer listViewerMain;
	private Group group1;
	private Button button3;
	private Button button2;
	private Button button1;
	private Group groupButtons;
	private TableViewer tableViewer;
	private Button buttonRemove;
	private Button buttonEdit;
	private Button buttonNew;

	public BusinessStepPropertySection() {
		
	}
	
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
	}
	
	
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		FormLayout compositeLayout = new FormLayout();
		composite.setLayout(compositeLayout);
		composite.setSize(596, 180);
		{
			group1 = new Group(composite, SWT.NONE);
			GridLayout group1Layout = new GridLayout();
			group1Layout.makeColumnsEqualWidth = true;
			group1.setLayout(group1Layout);
			FormData group1LData = new FormData();
			group1LData.left =  new FormAttachment(0, 1000, 170);
			group1LData.top =  new FormAttachment(0, 1000, 12);
			group1LData.width = 65;
			group1LData.height = 140;
			group1.setLayoutData(group1LData);
			group1.setText("");
			{
				buttonEdit = new Button(group1, SWT.PUSH | SWT.CENTER);
				GridData buttonEditLData = new GridData();
				buttonEdit.setLayoutData(buttonEditLData);
				buttonEdit.setText("Edit");
			}
			{
				buttonNew = new Button(group1, SWT.PUSH | SWT.CENTER);
				GridData buttonNewLData = new GridData();
				buttonNew.setLayoutData(buttonNewLData);
				buttonNew.setText("New");
			}
			{
				buttonRemove = new Button(group1, SWT.PUSH | SWT.CENTER);
				GridData buttonRemoveLData = new GridData();
				buttonRemove.setLayoutData(buttonRemoveLData);
				buttonRemove.setText("Remove");
			}
		}
		{
			
//			groupButtons = getWidgetFactory().createGroup(composite, "Button Group");
			groupButtons = new Group(composite, SWT.NONE);
			GridLayout groupButtonsLayout = new GridLayout();
			groupButtonsLayout.makeColumnsEqualWidth = true;
			groupButtons.setLayout(groupButtonsLayout);
			FormData groupButtonsLData = new FormData();
			groupButtonsLData.left =  new FormAttachment(0, 1000, 497);
			groupButtonsLData.top =  new FormAttachment(0, 1000, 12);
			groupButtonsLData.width = 81;
			groupButtonsLData.height = 140;
			groupButtons.setLayoutData(groupButtonsLData);
			//groupButtons.setText("Action");
			//groupButtons.setBackground(null);
			{
				button1 = new Button(groupButtons, SWT.PUSH | SWT.CENTER);
				GridData button1LData = new GridData();
				button1.setLayoutData(button1LData);
				button1.setText("button1");
			}
			{
				button2 = new Button(groupButtons, SWT.PUSH | SWT.CENTER);
				GridData button2LData = new GridData();
				button2.setLayoutData(button2LData);
				button2.setText("button2");
			}
			{
				button3 = new Button(groupButtons, SWT.PUSH | SWT.CENTER);
				GridData button3LData = new GridData();
				button3.setLayoutData(button3LData);
				button3.setText("button3");
			}
		}
		{
			FormData tableViewerLData = new FormData();
			tableViewerLData.left =  new FormAttachment(0, 1000, 253);
			tableViewerLData.top =  new FormAttachment(0, 1000, 12);
			tableViewerLData.width = 218;
			tableViewerLData.height = 139;
			tableViewer = new TableViewer(composite, SWT.BORDER);
			tableViewer.getControl().setLayoutData(tableViewerLData);
		}
		{
			FormData listViewerMainLData = new FormData();
			listViewerMainLData.left =  new FormAttachment(0, 1000, 12);
			listViewerMainLData.top =  new FormAttachment(0, 1000, 12);
			listViewerMainLData.width = 149;
			listViewerMainLData.height = 156;
			listViewerMain = new ListViewer(composite, SWT.BORDER);
			listViewerMain.getControl().setLayoutData(listViewerMainLData);
		}

		
	}

//	protected Object transformSelection(Object selected) {
//
//		if (selected instanceof EditPart) {
//			Object model = ((EditPart) selected).getModel();
//			return model instanceof View ? ((View) model).getElement() : null;
//		}
//		if (selected instanceof View) {
//			return ((View) selected).getElement();
//		}
//		if (selected instanceof IAdaptable) {
//			View view = (View) ((IAdaptable) selected).getAdapter(View.class);
//			if (view != null) {
//				return view.getElement();
//			}
//		}
//		return selected;
//	}

//	public void setInput(IWorkbenchPart part, ISelection selection) {
//		if (selection.isEmpty()
//				|| false == selection instanceof StructuredSelection) {
//			super.setInput(part, selection);
//			return;
//		}
//		final StructuredSelection structuredSelection = ((StructuredSelection) selection);
//		ArrayList transformedSelection = new ArrayList(structuredSelection
//				.size());
//		for (Iterator it = structuredSelection.iterator(); it.hasNext();) {
//			Object r = transformSelection(it.next());
//			if (r != null) {
//				transformedSelection.add(r);
//			}
//		}
//		super.setInput(part, new StructuredSelection(transformedSelection));
//	}


}
