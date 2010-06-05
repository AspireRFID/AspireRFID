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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.handler;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.CompanyImpl;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class InsertMasterDataEditorGMFIntoEpcisWizardPage extends WizardPage {

	/**
	 * IStructuredSelection
	 */
	private final IStructuredSelection currentSelection;
	
	/**
	 * CompanyEditPart
	 */
	private static CompanyEditPart companyEditPart = null;

	/**
	 * Constructor
	 */
	public InsertMasterDataEditorGMFIntoEpcisWizardPage(String name, IStructuredSelection currentSelection) {
		super(name);
		setTitle("Insert a new MasterDataEditorGMF diagram");
		setDescription("Choose the company name.");
		this.currentSelection = currentSelection;
	}

	/**
	 * Get the currentSelection
	 */
	protected IStructuredSelection getSelection() {
		return currentSelection;
	}

	/**
	 * Create the contents of the page
	 */
	public void createControl(Composite parent) {
		setPageComplete(false);
		Composite plate = new Composite(parent, SWT.NONE);
		
		final List list = new List(plate, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		list.setLayoutData(new FillLayout(SWT.VERTICAL));
		list.setSize(500, 230);
		
		Set<Entry<IWorkbenchPartReference, CompanyEditPart>> set = MasterDataEditParts.getCompanyPartRef().entrySet();
		Iterator<Entry<IWorkbenchPartReference, CompanyEditPart>> itr = set.iterator();

		while (itr.hasNext()) {
			Entry<IWorkbenchPartReference, CompanyEditPart> item = itr.next();
			Company company = (CompanyImpl) ((View) item.getValue().getModel()).getElement();
			list.add(company.getID());
		}
		   
	    list.addSelectionListener(new SelectionListener() {
	        public void widgetSelected(SelectionEvent event) {
	          String[] selectedItems = list.getSelection();
	          String selection = selectedItems[0];
	          System.out.println(selection);
	          
	          if (!(selection == null && selection == "")) {	        	  
	        	Set<Entry<IWorkbenchPartReference, CompanyEditPart>> set1 = MasterDataEditParts.getCompanyPartRef().entrySet();
				Iterator<Entry<IWorkbenchPartReference, CompanyEditPart>> itr1 = set1.iterator();
					
				while (itr1.hasNext()) {
					Entry<IWorkbenchPartReference, CompanyEditPart> item1 = itr1.next();
					Company company = (CompanyImpl) ((View) item1.getValue().getModel()).getElement();
					if (company.getID().equals(selection))
						MasterDataEditParts.setCompanyPart(item1.getValue());
				}
				System.out.println(MasterDataEditParts.getCompanyPart());
				setPageComplete(true);
	          }
	        }
	        
        public void widgetDefaultSelected(SelectionEvent event) {
          }
        });

		setControl(plate);
	}


}
