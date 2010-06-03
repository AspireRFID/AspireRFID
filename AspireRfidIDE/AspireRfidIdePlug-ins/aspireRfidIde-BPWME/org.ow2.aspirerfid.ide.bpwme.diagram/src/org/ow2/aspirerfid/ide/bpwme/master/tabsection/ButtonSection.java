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
import org.ow2.aspirerfid.ide.bpwme.master.model.DispositionItem;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;

/**
 * Button section in properties view.
 * Update id in this section.
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class ButtonSection extends AbstractPropertySection {
//	private Button addProperty;
//	private Button delProperty;
	//private Spec input;
	private DispositionItem input;
	private Text textURI;
//	private Text textName;
	
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		
		super.setInput(part, selection);
		this.input = (DispositionItem)((IStructuredSelection) selection).getFirstElement();
		if(input != null) {
			textURI.setText(input.getID());
			//textName.setText(input.getName());
		}
	}
	
	
	@Override
	public void createControls(final Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		
		Composite mainComposite = getWidgetFactory().createFlatFormComposite(parent);
		mainComposite.setLayout(new GridLayout(5, false));
		final MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		
		getWidgetFactory().createLabel(mainComposite, "ID");
		//label.setLayoutData(new GridData(36, SWT.DEFAULT));
		//label.setText("Reader Name");
		textURI = getWidgetFactory().createText(mainComposite, "");
		textURI.setLayoutData(new GridData(150, SWT.DEFAULT));
		getWidgetFactory().createLabel(mainComposite, "Name");
//		textName = getWidgetFactory().createText(mainComposite, "");
//		textName.setLayoutData(new GridData(100, SWT.DEFAULT));
		
		Button updateButton = getWidgetFactory().createButton(mainComposite, "Update", SWT.PUSH);
		updateButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				super.mouseDown(e);
				mdb.modifyDispositionItemID(input, textURI.getText());
			}
		});
	}

}
