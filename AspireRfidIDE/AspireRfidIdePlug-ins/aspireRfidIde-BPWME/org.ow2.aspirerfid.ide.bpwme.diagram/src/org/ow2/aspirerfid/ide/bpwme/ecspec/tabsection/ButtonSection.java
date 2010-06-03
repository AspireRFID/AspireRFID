/*
 * Copyright © 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */

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
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.*;

/**
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class ButtonSection extends AbstractPropertySection {
	private Button addProperty;
	private Button delProperty;
	private Spec input;
	private Text text;
	
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		super.setInput(part, selection);
		//System.out.println(part);
		this.input = (Spec)((IStructuredSelection) selection).getFirstElement();
		if(input != null){		
			this.text.setText(input.getName());
		}
	}
	
	
	@Override
	public void createControls(final Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		// TODO Auto-generated method stub
		super.createControls(parent, aTabbedPropertySheetPage);
		
		Composite mainComposite = getWidgetFactory().createFlatFormComposite(parent);
		mainComposite.setLayout(new GridLayout(3, false));
		
		final Label label = getWidgetFactory().createLabel(mainComposite, "Reader Name");
		//label.setLayoutData(new GridData(36, SWT.DEFAULT));
		//label.setText("Reader Name");
		text = getWidgetFactory().createText(mainComposite, "");
		text.setLayoutData(new GridData(100, SWT.DEFAULT));
		Button updateButton = getWidgetFactory().createButton(mainComposite, "Update", SWT.PUSH);
		updateButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				input.setName(text.getText());
			}
		});
//        Composite composite = getWidgetFactory()
//        .createFlatFormComposite(parent);
//        composite.setLayout(new RowLayout(SWT.HORIZONTAL));
//		addProperty = getWidgetFactory().createButton(composite,
//	            "New", SWT.PUSH);
//		addProperty.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mouseDown(MouseEvent e) {
//				// TODO Auto-generated method stub
//				//System.out.println("Click");
//				//input.propertyTable.put("Else", "Something");
//				//System.out.println(
//				//PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("AdvancedTab")
//				//);
//				//IViewPart[] kk = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViews();
//				//HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().
//				//System.out.println(sv.ts.page.handleEntrySelection(selection).getSelection());
//				

//				//input.propertyTable.put(selection, "");
//				sv.refreshSampleView();
//				sv.refreshProperty();
//
//				/*
//				InputDialog id = new InputDialog(parent.getShell());
//				id.setMessage("Input the property name");
//				String pn = id.open();
//				input.propertyTable.put(pn, "");
//				sv.refreshSampleView();
//				sv.refreshProperty();
//				*/
//				
//				//input.name = "test";
//				//input.propertyTable.put("Name", input.name);
//				/*
//				try {
//					IViewPart ppview = PlatformUI.getWorkbench().getActiveWorkbenchWindow().
//					getActivePage().showView("org.eclipse.ui.views.properties.PropertySheet");
//					
//				} catch (PartInitException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}*/
//			}
//		});
//		
//		delProperty = getWidgetFactory().createButton(composite,
//	            "Remove", SWT.PUSH);
////		delProperty.addMouseListener(new MouseAdapter(){
////			//TODO make the loops smaller, may change the extra property data structure.
////			@Override
////			public void mouseDown(MouseEvent e) {
////				String property = sv.ts.page.getSelectProperty();
////				if(input instanceof LLRPSpec) {
////					for(int i = 0; i < sv.extraLLRPProperty.size(); i++) {
////						if(sv.extraLLRPProperty.get(i).toString().equals(property)) {
////							input.propertyTable.remove(sv.ts.page.getSelectProperty());
////							break;
////						}
////					}
////				}else if(input instanceof RPSpec) {
////					for(int i = 0; i < sv.extraRPProperty.size(); i++) {
////						if(sv.extraRPProperty.get(i).toString().equals(property)) {
////							input.propertyTable.remove(sv.ts.page.getSelectProperty());	
////							break;
////						}
////					}
////				}else if(input instanceof HALSpec) {
////					for(int i = 0; i < sv.extraHALProperty.size(); i++) {
////						if(sv.extraHALProperty.get(i).toString().equals(property)) {
////							input.propertyTable.remove(sv.ts.page.getSelectProperty());
////							break;
////						}
////					}
////				}		
////				sv.refreshSampleView();
////				sv.refreshProperty();
////			}			
////			}
////		);
////		sv = LRSpecView.getLRSpecView();
////		/*
////		if(sv != null) {
////			//System.out.println(sv);
////			sv.bindButtonSection(this);
////		}
////		*/
	}

}
