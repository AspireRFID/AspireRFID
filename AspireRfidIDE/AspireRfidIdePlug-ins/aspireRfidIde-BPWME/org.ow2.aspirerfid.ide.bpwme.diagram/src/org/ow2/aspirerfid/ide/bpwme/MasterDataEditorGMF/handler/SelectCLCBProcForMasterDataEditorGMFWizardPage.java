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

package org.ow2.aspirerfid.ide.bpwme.MasterDataEditorGMF.handler;


import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class SelectCLCBProcForMasterDataEditorGMFWizardPage extends WizardPage {
	
	/**
	 * Editor handler
	 */
	 EditorHandler editorHandler = new EditorHandler();

	/**
	 * IStructuredSelection
	 */
	private final IStructuredSelection currentSelection;
	
	/**
	 * CLCBProc
	 */
	private CLCBProc clcbProc;
	
	/**
	 * Constructor
	 */
	public SelectCLCBProcForMasterDataEditorGMFWizardPage(String name, IStructuredSelection currentSelection) {
		super(name);
		this.currentSelection = currentSelection;
	}

	/**
	 * Get the currentSelection
	 */
	protected IStructuredSelection getSelection() {
		return currentSelection;
	}
	
	/**
	 * Get the clcbProc
	 */
	protected CLCBProc getClcbProc() {
		return clcbProc;
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
		
		for (int i = 0; i < editorHandler.getCLCBProc().size(); i++) {
			if (!(editorHandler.getCLCBProc().get(i).getName().isEmpty() && editorHandler.getCLCBProc().get(i).getName() == ""))
				list.add(editorHandler.getCLCBProc().get(i).getName());
		}
		   
	    list.addSelectionListener(new SelectionListener() {
	        public void widgetSelected(SelectionEvent event) {
	          String[] selectedItems = list.getSelection();
	          String selection = selectedItems[0];
	          
	          if (!(selection == null && selection == "")) {	        	  
	      		for (int i = 0; i < editorHandler.getCLCBProc().size(); i++) {
	    			if (editorHandler.getCLCBProc().get(i).getName().equals(selection)) {
	    				clcbProc = editorHandler.getCLCBProc().get(i);
	    				break;
	    			}
	      		}
	      		
				setPageComplete(true);
	          }
	        }
	        
	        public void widgetDefaultSelected(SelectionEvent event) {
	        }
        });

		setControl(plate);
	}
	
}
