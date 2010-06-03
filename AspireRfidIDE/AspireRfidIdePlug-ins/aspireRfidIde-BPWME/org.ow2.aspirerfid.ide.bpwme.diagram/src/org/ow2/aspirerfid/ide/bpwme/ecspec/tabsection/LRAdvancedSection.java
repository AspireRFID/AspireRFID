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


import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.ow2.aspirerfid.ide.bpwme.dialog.InputDialog;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.ExtraProperty;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.ExtraPropertyListViewer;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.LLRPExtraView;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
/**
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class LRAdvancedSection extends AbstractPropertySection {
	
	private CTabFolder tabFolder;
	private CTabItem ctiLLRP;
	private CTabItem ctiRP;
	private CTabItem ctiHAL;
	//private Button newB;
	//private Button changeB;
	//private Button removeB;
	private LLRPExtraView llrpe;
	//private ExtraPropertyListViewer eListViewer;
	
	
	public LRAdvancedSection() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void createControls(final Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		// TODO Auto-generated method stub
		super.createControls(parent, aTabbedPropertySheetPage);
		//Composite tab = new Composite(parent, SWT.NONE);
		//tab.setLayout(new GridLayout(1,false));
		//parent.setLayout(new GridLayout());
		tabFolder = new CTabFolder(parent, SWT.TOP);
		tabFolder.setMaximized(true);
		tabFolder.setBorderVisible(false);
		//tabFolder.setLayout(new FillLayout());
		//tabFolder.setLayout(new GridLayout(1, false));
		//tabFolder.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		ctiLLRP = new CTabItem(tabFolder, 0);
		ctiLLRP.setText("LLRP");
		ctiRP = new CTabItem(tabFolder, 0);
		ctiRP.setText("RP");
		ctiHAL = new CTabItem(tabFolder, 0);
		ctiHAL.setText("HAL");
		
		Composite compositeLLRP = new Composite(tabFolder, SWT.NONE);
		compositeLLRP.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Composite compositeRP = new Composite(tabFolder, SWT.NONE);
		compositeRP.setLayout(new RowLayout(SWT.HORIZONTAL));


		Composite compositeHAL = new Composite(tabFolder, SWT.NONE);
		compositeHAL.setLayout(new RowLayout(SWT.HORIZONTAL));

		//compositeLLRP.setLayout(new GridLayout(1, false));
		
		ctiLLRP.setControl(compositeLLRP);
		ctiRP.setControl(compositeRP);
		ctiHAL.setControl(compositeHAL);

		createEditPart(compositeLLRP, ExtraProperty.LLRP_TYPE);
		createEditPart(compositeRP, ExtraProperty.RP_TYPE);
		createEditPart(compositeHAL, ExtraProperty.HAL_TYPE);
		
	}

	
	
	private void createEditPart(final Composite parent, int type) {
		final ExtraPropertyListViewer eListViewer;
		
		Group grp2 = new Group(parent, SWT.NONE);
		grp2.setLayout(new RowLayout(SWT.HORIZONTAL));
		grp2.setText("Candidate Attribute");
		
		Group grp1 = new Group(parent, SWT.NONE);
		//grp1.setLayout(new RowLayout(SWT.VERTICAL));
		GridLayout g1Layout = new GridLayout(1, false);
		grp1.setLayout(g1Layout);
		//grp1.setLayout(new FillLayout(SWT.VERTICAL));
		grp1.setText("Edit");
		//grp1.setLayoutData(new RowData(300, 30));
		
		//grp2.setLayout(new TableColumnLayout());
		//grp2.setLayoutData(new RowData(300, 300));
		GridData data = new GridData(GridData.FILL_BOTH);

		Button newB = getWidgetFactory().createButton(grp1, "New", SWT.PUSH);
		newB.setLayoutData(data);
		
		data = new GridData(GridData.FILL_BOTH);
		Button changeB = getWidgetFactory().createButton(grp1, "Change", SWT.PUSH);
		changeB.setLayoutData(data);
		
		data = new GridData(GridData.FILL_BOTH);
		Button removeB = getWidgetFactory().createButton(grp1, "Remove", SWT.PUSH);
		removeB.setLayoutData(data);
		MainControl mc = MainControl.getMainControl();
		switch(type){
		case ExtraProperty.LLRP_TYPE:
			eListViewer = new ExtraPropertyListViewer(grp2, 
					mc.extraLLRPProperty,
					ExtraProperty.LLRP_TYPE);
			break;
		case ExtraProperty.RP_TYPE:
			eListViewer = new ExtraPropertyListViewer(grp2, 
					mc.extraRPProperty,
					ExtraProperty.RP_TYPE);

			break;
		case ExtraProperty.HAL_TYPE:
			eListViewer = new ExtraPropertyListViewer(grp2, 
					mc.extraHALProperty,
					ExtraProperty.HAL_TYPE);
			break;
		default:
			System.out.println("Illegal Type");
			return;
		}
		
		
		//llrpe = new LLRPExtraView(grp2, SampleView.getSampleView().LLRPPropertyTable);
		//llrpe.setSize(260, 250);
		
		newB.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				InputDialog id = new InputDialog(parent.getShell());
				String input = id.open();
				if((input == null) | (input.equals("")))
					return;
				eListViewer.add(input);
				eListViewer.refresh();
				
				//llrpe.toNewRow();
			}
		});
		
		changeB.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDown(e);
				ExtraProperty ep = (ExtraProperty)((IStructuredSelection)eListViewer.getSelection()).getFirstElement();
				if(ep != null) {
					InputDialog id = new InputDialog(parent.getShell());
					id.setInput(ep.toString());
					String input = id.open();
					if((input != null) & (!input.equals(""))) {
						eListViewer.change(ep, input);
						eListViewer.refresh();
					}
				}

				//llrpe.toNewRow();
			}
		});
		
		
		removeB.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDown(e);
				ExtraProperty ep = (ExtraProperty)((IStructuredSelection)eListViewer.getSelection()).getFirstElement();
				if(ep != null) {
					eListViewer.remove(ep);
					eListViewer.refresh();
				}
				//llrpe.removeSelectRow();
			}
		});
	}
	
}
