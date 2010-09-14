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

import java.util.ArrayList;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.WorkflowMap;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.WorkflowMapEditPart;
import org.ow2.aspirerfid.ide.bpwme.impl.WorkflowMapImpl;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class SelectCLCBProcForMasterDataEditorGMFWizardPage extends WizardPage {

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
		
		for (int i = 0; i < getCLCBProc().size(); i++) {
			if (!(getCLCBProc().get(i).getName().isEmpty() && getCLCBProc().get(i).getName() == ""))
				list.add(getCLCBProc().get(i).getName());
		}
		   
	    list.addSelectionListener(new SelectionListener() {
	        public void widgetSelected(SelectionEvent event) {
	          String[] selectedItems = list.getSelection();
	          String selection = selectedItems[0];
	          
	          if (!(selection == null && selection == "")) {	        	  
	      		for (int i = 0; i < getCLCBProc().size(); i++) {
	    			if (getCLCBProc().get(i).getName().equals(selection)) {
	    				clcbProc = getCLCBProc().get(i);
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
	
	/**
	 * Get a list with all the CLCBProc elements that reside in open BPWME editors
	 */
	private ArrayList<CLCBProc> getCLCBProc() {
		ArrayList<OLCBProc> olcbProcs = new ArrayList<OLCBProc>();
		ArrayList<CLCBProc> clcbProcs = new ArrayList<CLCBProc>();
		ArrayList<WorkflowMap> workflowMaps = new ArrayList<WorkflowMap>();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		for (int i = 0; i < page.getEditorReferences().length; i++) {
			IEditorPart editorPart = page.getEditorReferences()[i].getEditor(true);
			DiagramEditPart diagramEditPart = ((DiagramEditor)editorPart).getDiagramEditPart();
			
			if (diagramEditPart instanceof WorkflowMapEditPart) {
				WorkflowMapEditPart workflowMap = (WorkflowMapEditPart) diagramEditPart;
				WorkflowMap  workflowMapDomain = (WorkflowMapImpl) ((View) workflowMap.getModel()).getElement();
				workflowMaps.add(workflowMapDomain);
			}
		}
		
		for (int i = 0; i < workflowMaps.size(); i++) {
			for (int j = 0; j < workflowMaps.get(i).eContents().size(); j++) {
				olcbProcs.add((OLCBProc) workflowMaps.get(i).eContents().get(j));
			}
		}
		
		for (int i = 0; i < olcbProcs.size(); i++) {
			for (int j = 0; j < olcbProcs.get(i).eContents().size(); j++) {
				clcbProcs.add((CLCBProc) olcbProcs.get(i).eContents().get(j));
			}
		}

		return clcbProcs;
	}


}
