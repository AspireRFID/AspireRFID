/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

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


package org.ow2.aspirerfid.ide.bpwme.master.tabsection;


import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.AdvancedPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ow2.aspirerfid.commons.ale.model.alelr.LRProperty;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceUtil;
import org.ow2.aspirerfid.ide.bpwme.dialog.ComboDialog;
import org.ow2.aspirerfid.ide.bpwme.dialog.InputDialog;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.*;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.TableSheetPage;
import org.ow2.aspirerfid.ide.bpwme.master.model.DispositionItem;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;

/**
 * Modified from eclipse table section.
 * Can edit attributes, add and remove attributes.
 * 
 * @since 3.2
 * @author Yongming Luo <yluo@ait.edu.gr>
 */
public class TableSection extends AbstractPropertySection {

	/**
	 * The Property Sheet Page.
	 */
	protected TableSheetPage page;
	protected DispositionItem input;
	private Button newProperty;
	private Button removeProperty;
	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			final TabbedPropertySheetPage atabbedPropertySheetPage) {
		super.createControls(parent, atabbedPropertySheetPage);
		Composite mainComposite = getWidgetFactory().createFlatFormComposite(parent);
		mainComposite.setLayout(new GridLayout(2, false));
		
		final Composite buttonComposite = new Composite(mainComposite, 0);
		buttonComposite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true));
		//buttonComposite.setLayoutData(new GridData(50, 100));
		buttonComposite.setLayout(new GridLayout());
		
		Composite tableComposite = getWidgetFactory().createFlatFormComposite(mainComposite);
		tableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		//newProperty = new Button(buttonComposite, SWT.NONE);
		//newProperty.setText("Test");
		//newProperty.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		
		newProperty = getWidgetFactory().createButton(buttonComposite,
	            "New", SWT.PUSH);
		newProperty.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
				
		removeProperty = getWidgetFactory().createButton(buttonComposite,
	            "Remove", SWT.PUSH);
		removeProperty.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		
		newProperty.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				ComboDialog cd = new ComboDialog(buttonComposite.getShell());
				cd.setText("Choose Dialog");
				String id = input.getID();
				if(id.startsWith("urn:epcglobal:fmcg:bizstep:")) {
					cd.setOption(PreferenceUtil.getAttributes(PreferenceConstants.P_BS));
				}else if(id.startsWith("urn:epcglobal:fmcg:disp:")) {
					cd.setOption(PreferenceUtil.getAttributes(PreferenceConstants.P_DI));
				}else if(id.startsWith("urn:epcglobal:fmcg:btt:")) {
					cd.setOption(PreferenceUtil.getAttributes(PreferenceConstants.P_BT));
				}
				String selection = cd.open();
				//add a new attribute pair
				MasterDataBuilder mdb = MasterDataBuilder.getInstance();
				mdb.addItemAttribute(input, selection, "");				
				page.refresh();

//				InputDialog id = new InputDialog(buttonComposite.getShell());
//				id.setMessage("Input Attribute Name");
//				String attributeName = id.open();
			}
		});
		
		removeProperty.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				String property = page.getSelectProperty();
				if(property == null || property.equals("")) {
					return;
				}
				MasterDataBuilder mdb = MasterDataBuilder.getInstance();
				mdb.removeItemAttribute(input, property);
				
				page.refresh();
			}
		});
		
		page = new TableSheetPage();
		page.createControl(tableComposite);
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);
		page.getControl().setLayoutData(data);
		
		page.getControl().addControlListener(new ControlAdapter() {

			public void controlResized(ControlEvent e) {
				atabbedPropertySheetPage.resizeScrolledComposite();
			}
		});
		
		//LRSpecView.getLRSpecView().bindTableSection(this);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		page.selectionChanged(part, selection);
		this.input = (DispositionItem)((IStructuredSelection) selection).getFirstElement();
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#dispose()
	 */
	public void dispose() {
		super.dispose();

		if (page != null) {
			page.dispose();
			page = null;
		}
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
	 */
	public void refresh() {
		page.refresh();
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#shouldUseExtraSpace()
	 */
	public boolean shouldUseExtraSpace() {
		return true;
	}
}