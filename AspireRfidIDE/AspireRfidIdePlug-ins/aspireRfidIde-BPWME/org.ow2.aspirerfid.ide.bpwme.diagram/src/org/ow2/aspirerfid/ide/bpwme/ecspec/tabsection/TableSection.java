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



package org.ow2.aspirerfid.ide.bpwme.ecspec.tabsection;


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
import org.ow2.aspirerfid.ide.bpwme.dialog.ComboDialog;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.*;
import org.ow2.aspirerfid.ide.bpwme.ecspec.views.TableSheetPage;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;

/**
 * Modified from eclipse table section.
 * @since 3.2
 *
 */
public class TableSection extends AbstractPropertySection {

	/**
	 * The Property Sheet Page.
	 */
	protected TableSheetPage page;
	protected Spec input;
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
				MainControl mc = MainControl.getMainControl();
				if(input instanceof LLRPSpec) {
					cd.setMessage("Choose LLRP Property");
					cd.setOption(mc.extraLLRPProperty);
				}else if(input instanceof RPSpec) {
					cd.setMessage("Choose RP Property");
					cd.setOption(mc.extraRPProperty);
				}else if(input instanceof HALSpec) {
					cd.setMessage("Choose HAL Property");
					cd.setOption(mc.extraHALProperty);
				}
				
				String selection = cd.open();
				if(selection.equals("")) {
					return;
				}
				LRProperty lrp = new LRProperty();
				lrp.setName(selection);
				lrp.setValue("");
				input.addProperty(lrp);
				page.refresh();
			}
		});
		
		removeProperty.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				String property = page.getSelectProperty();
				if(property == null || property.equals(""))
					return;
				input.removeProperty(property);
				page.refresh();
			}
		});
		
//		FormData mainData = new FormData();
//		mainData.left = new FormAttachment(0, 0);
//		mainData.right = new FormAttachment(100, 0);
//		mainData.top = new FormAttachment(0, 0);
//		mainData.bottom = new FormAttachment(100, 0);
		//mainComposite.setLayoutData(new FillData());

		
//		Composite buttonComposite = getWidgetFactory().createFlatFormComposite(mainComposite);
//		FormData buttonData = new FormData();
//		buttonData.left = new FormAttachment(0, 10);
//		buttonData.right = new FormAttachment(0, 50);
//		buttonData.top = new FormAttachment(0, 10);
//		buttonData.bottom = new FormAttachment(0, 60);
//		buttonComposite.setLayoutData(buttonData);
//		
//		Composite tableComposite = getWidgetFactory().createFlatFormComposite(mainComposite);
//		FormData tableData = new FormData();
//		tableData.left = new FormAttachment(0, 60);
//		tableData.right = new FormAttachment(100, 0);
//		tableData.top = new FormAttachment(0, 10);
//		tableData.bottom = new FormAttachment(100, 0);
//		tableComposite.setLayoutData(tableData);
//		
		
		
//		Composite composite = getWidgetFactory()
//			.createFlatFormComposite(tableComposite);
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
		this.input = (Spec)((IStructuredSelection) selection).getFirstElement();
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