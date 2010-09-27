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

package org.ow2.aspirerfid.ide.bpwme.navigator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.CompanyImpl;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.handler.EditorHandler;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.EBProc;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.WorkflowMap;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.WorkflowMapEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.impl.WorkflowMapImpl;

/**
 * Content provider for the navigator
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class BpwmeContentProvider implements ITreeContentProvider {

    private static final Object[] EMPTY_ARRAY = new Object[0];
    private ArrayList<WorkflowMapEditPart> bpwmeList = new ArrayList<WorkflowMapEditPart>();
    private HashMap<WorkflowMapEditPart, IWorkbenchPart> mapBpwmePart = new HashMap<WorkflowMapEditPart, IWorkbenchPart>();
    private URI[] fileURIs;
    private HashMap<URI, OLCBProc> uriBpwmeMap = new HashMap<URI, OLCBProc>();
    public static TreeViewer viewer;
    private EditorHandler editorHandler = new EditorHandler();
    private Action refreshViewer;

	/**
	 * Returns the children of the navigator
	 */
    public Object[] getChildren(Object parentElement) {  
    	setBpwmeEditors();
    	setFileURIs();

    	if (parentElement instanceof Root)
    		return fileURIs;
    	else if (parentElement instanceof URI) {
    		OLCBProc[] bpwmes = new OLCBProc[1];
    		bpwmes[0] = uriBpwmeMap.get(parentElement);
    		return bpwmes;
    	}
    	else if (parentElement instanceof OLCBProc)   		
    		return getElementChildren(parentElement);
    	else if (parentElement instanceof CLCBProc) {
    		//the children of CLCBProc are both MasterData and EBProc elements
    		CLCBProc element = (CLCBProc) parentElement;    		
    		String fileName = getLocationFile(editorHandler.getBpwmeFileNames().get(element).toString() + element.getName() + File.separator);
    		//if filename does not exist
    		if (fileName == null || fileName.isEmpty())
    			return getElementChildren(parentElement);
    		
    		//if filename exists but it is not open
    		if (getOpenMasterDataEditorGMF(fileName) == null)
    			return getElementChildren(parentElement);
		
    		Company company = (CompanyImpl) ((View) getOpenMasterDataEditorGMF(fileName).getModel()).getElement();;
    		Company companies[] = new Company[1];
    		companies[0] = company;
    		Object[] obj = new Object[companies.length + getElementChildren(parentElement).length];
    		obj[0] = companies[0];

    		for (int i = 0; i < getElementChildren(parentElement).length; i++)
    			obj[i+1] = getElementChildren(parentElement)[i];

    		return obj;
    	}
    	else if (parentElement instanceof EBProc)
    		return getElementChildren(parentElement);
    	else if (parentElement instanceof Company)
    		return getElementChildren(parentElement);
   		else if (parentElement instanceof AbstractWarehouse)
   			return getElementChildren(parentElement);
   		else if (parentElement instanceof AbstractContainer)
   			return EMPTY_ARRAY;
   		else
   			return EMPTY_ARRAY;
    }

	/**
	 * Returns the children of each element of the navigator
	 */
    public Object getParent(Object element) {
    	if (element instanceof URI)
    		return ((Parent) element).getRoot();
    	else if (element instanceof OLCBProc)
    		return ((OLCBProc) element).eContainer();
    	else if (element instanceof CLCBProc)
    		return ((CLCBProc) element).eContainer();
    	else if (element instanceof EBProc)
    		return ((EBProc) element).eContainer();
    	else if (element instanceof AbstractContainer)
            return ((AbstractContainer) element).eContainer();
        else if (element instanceof AbstractWarehouse)
            return ((AbstractWarehouse) element).eContainer();
        else if (element instanceof Company)
            return ((Parent) element).getRoot();
        return null;
    }

	/**
	 * Checks if an element has children
	 */
    public boolean hasChildren(Object element) {
        return (element instanceof Root || element instanceof URI || element instanceof OLCBProc || 
        		element instanceof CLCBProc || element instanceof Company || element instanceof AbstractWarehouse);
    }

	/**
	 * Returns the elements of the navigator
	 */
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

	/**
	 * Notifies when input is changed
	 */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
       	BpwmeContentProvider.viewer = (TreeViewer) viewer;
    	//hookListeners();
    	
    	IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    	
		page.addPartListener(new IPartListener2(){
	        public void partActivated( IWorkbenchPartReference partRef ) {
	        	if (partRef.getId().equals("org.ow2.aspirerfid.ide.bpwme.navigator.view")) {
	        		BpwmeContentProvider.viewer.refresh();
	        	}
	        }
	
	        public void partBroughtToTop( IWorkbenchPartReference partRef ) {}
	
	        public void partClosed( IWorkbenchPartReference partRef ) {}
	
	        public void partDeactivated( IWorkbenchPartReference partRef ) {}
	
	        public void partOpened( IWorkbenchPartReference partRef ) {}
	        
	        public void partHidden( IWorkbenchPartReference partRef ) {}
	
	        public void partVisible( IWorkbenchPartReference partRef ) {}
	
	        public void partInputChanged( IWorkbenchPartReference partRef ) {}
	    });
    }

	/**
	 * Disposes the viewer
	 */
	public void dispose() {
	}
	
	/**
	 * Create actions
	 */
	protected void createActions() {
		refreshViewer = new Action("Refresh") {
			public void run() {
				viewer.refresh();
				System.out.println("refresh");;
			}			
		};
		
	}
	
	/**
	 * Add listeners to the viewer
	 */
	protected void hookListeners() {
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			AbstractEditPart selectedPart = null;
			
			public void selectionChanged(SelectionChangedEvent event) {
				if(event.getSelection().isEmpty()) {
					System.out.println("no selection");
					return;
				}
			
				if(event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					
					EObjectImpl selectedObject = (EObjectImpl) selection.getFirstElement();
					System.out.println("selected: " + selectedObject);
					System.out.println(getEditorForPart(selectedObject));
					
					//page.activate(getEditorForPart(selectedObject));
					
					//remove the selected edit part
					if (selectedPart != null)
						selectedPart.setSelected(0);
					
//					for (int i = 0; i < MasterDataEditParts.getAllParts().size(); i++) {
//						EObject partsObject = (EObjectImpl) ((View) MasterDataEditParts.getAllParts().get(i).getModel()).getElement();
//						if (partsObject.equals(selectedObject)) {
//							MasterDataEditParts.getAllParts().get(i).setSelected(1);
//							selectedPart = MasterDataEditParts.getAllParts().get(i);
//							return;
//						}
//					}
//					
//					for (int i = 0; i < getAllBpwmeParts().size(); i++) {
//						EObject partsObject = (EObjectImpl) ((View) getAllBpwmeParts().get(i).getModel()).getElement();
//						if (partsObject.equals(selectedObject)) {
//							getAllBpwmeParts().get(i).setSelected(1);
//							selectedPart = getAllBpwmeParts().get(i);
//							return;
//						}
//					}
				}
				

			}
		});
//		viewer.addDoubleClickListener(new IDoubleClickListener() {
//			AbstractEditPart selectedPart = null;
//			public void doubleClick(DoubleClickEvent event) {
//				System.out.println("double clicked");				
//				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
//				EObjectImpl selectedObject = (EObjectImpl) selection.getFirstElement();
//				
//				//remove the selected edit part
//				if (selectedPart != null)
//					selectedPart.setSelected(0);
//				
//				for (int i = 0; i < MasterDataEditParts.getAllParts().size(); i++) {
//					EObject partsObject = (EObjectImpl) ((View) MasterDataEditParts.getAllParts().get(i).getModel()).getElement();
//					if (partsObject.equals(selectedObject)) {
//						MasterDataEditParts.getAllParts().get(i).setSelected(1);
//						selectedPart = MasterDataEditParts.getAllParts().get(i);
//						break;
//					}
//				}
//			}
//		});
	}
	
	/**
	 * Get the children of each element	
	 */
	private Object[] getElementChildren(Object parentElement) {
		EObject[] children = null;
		
		if (((EObject) parentElement).eContents().size() > 0) {
			children = new EObject[((EObject) parentElement).eContents().size()];
			
			for (int i = 0; i < ((EObject) parentElement).eContents().size(); i++)
				children[i] = ((EObject) parentElement).eContents().get(i);
		}
		return children;
	}
	
	/**
	 * Set the Bpwme editors of the workbench	
	 */
	private void setBpwmeEditors() {
		bpwmeList.clear();
		mapBpwmePart.clear();
		
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		WorkflowMap[] bpwmeEditors;
		
		for (int i = 0; i < page.getEditorReferences().length; i++) {
			IEditorPart editorPart = page.getEditorReferences()[i].getEditor(true);
			IWorkbenchPart workbenchPart = page.getEditorReferences()[i].getEditor(true);
			
//			System.out.println(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPages().length);
//			for (int n = 0; n < PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences().length; n++) {
//				System.out.println(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences()[n].getId());
//				//org.ow2.aspirerfid.ide.bpwme.navigator.view
//			}
			
			if (editorPart.getSite().getId().equals(BpwmeDiagramEditor.ID)) { 
				DiagramEditPart diagramEditPart = ((DiagramEditor)editorPart).getDiagramEditPart();
	
				if (diagramEditPart instanceof WorkflowMapEditPart) {
					WorkflowMapEditPart bpwmeEditPart = (WorkflowMapEditPart) diagramEditPart;
					bpwmeList.add(bpwmeEditPart);
					mapBpwmePart.put(bpwmeEditPart, workbenchPart);
				}
			}
		}
		
		bpwmeEditors = new WorkflowMap[bpwmeList.size()];
		
		for (int i = 0; i < bpwmeList.size(); i++) {
			WorkflowMap bpwmes = (WorkflowMapImpl) ((View) bpwmeList.get(i).getModel()).getElement();
			bpwmeEditors[i] = bpwmes;
		}
	}
	
	/**
	 * Set the file URIs	
	 */
	private void setFileURIs() {
		uriBpwmeMap.clear();
		fileURIs = new URI[bpwmeList.size()];
		
		for (int i = 0; i < bpwmeList.size(); i++) {
			fileURIs[i] = URI.createFileURI(bpwmeList.get(i).getDiagramView().getName().replaceFirst(".bpwme_diagram", ""));
			WorkflowMap bpwme = (WorkflowMapImpl) ((View) bpwmeList.get(i).getModel()).getElement();

			for (int j = 0; j < bpwme.eContents().size(); j++) { 
				if (bpwme.eContents().get(j) instanceof OLCBProc) {
					OLCBProc olcbProc = (OLCBProc) bpwme.eContents().get(j);
					uriBpwmeMap.put(fileURIs[i], olcbProc);
				}
			}
		}
	}
	
	/**
	 * Get the MasterDataEditorGMF file name from the directory
	 */
	private String getLocationFile(String locationFileDirectory) {
		File dir = new File(locationFileDirectory);
		if(!dir.exists()) {
			return null;
		}else {//if the directory does not include any diagram file, return false
			String[] children = dir.list();
			for(String child : children) {
				if(child.endsWith(".masterdataeditorgmf_diagram")) {
					return dir.getAbsolutePath() + File.separator + child;
				}
			}
		}
		return null;
	}
	
	/**
	 * Get the company of the open MasterDataEditorGMF
	 */
	private CompanyEditPart getOpenMasterDataEditorGMF(String filePath) {
		File file = new File(filePath);
		ArrayList<IEditorPart> openEditors = editorHandler.getOpenMasterDataEditorGMFEditorParts();
		
		for (int i = 0; i < openEditors.size(); i++) {
			URIEditorInput uri = (URIEditorInput)openEditors.get(i).getEditorInput();
			File fileEditorPart = new File(uri.getURI().toFileString());
		
			if (file.equals(fileEditorPart))
				return editorHandler.getOpenMasterDataEditorGMFForEditorPart().get(openEditors.get(i));
		}
		
		return null;
	}
	
	/**
	 * Get the editor of an element
	 */
	private IWorkbenchPart getEditorForPart(EObject element) {
		ArrayList<AbstractEditPart> allParts = new ArrayList<AbstractEditPart>();
		EObject bpwme = null;
	
		for (int k = 0; k < bpwmeList.size(); k++) {

			//add the olcb children of the bpwme
			for (int i = 0; i < bpwmeList.get(k).getChildren().size(); i++) {
				bpwme = (EObjectImpl) ((View) ((AbstractEditPart) bpwmeList.get(k).getChildren().get(i)).getModel()).getElement();
				if (bpwme.equals(element)) {
					return mapBpwmePart.get(bpwmeList.get(k));
				}
				allParts.add((AbstractEditPart) bpwmeList.get(k).getChildren().get(i));
			}
			
			//add all the other children
			for (int i = 0; i < allParts.size(); i++) {
				for (int j = 0; j < allParts.get(i).getChildren().size(); j++) {
					bpwme = (EObjectImpl) ((View) ((AbstractEditPart) allParts.get(i).getChildren().get(j)).getModel()).getElement();
					if (bpwme.equals(element)) {
						return mapBpwmePart.get(bpwmeList.get(k));
					}
					allParts.add((AbstractEditPart) allParts.get(i).getChildren().get(j));
				}
			}
		}
		
		return null;
	}
	
	
	/**
	 * Get all Bpwme parts
	 */
	private ArrayList<AbstractEditPart> getAllBpwmeParts() {
		ArrayList<AbstractEditPart> allParts = new ArrayList<AbstractEditPart>();
	
		for (int i = 0; i < bpwmeList.size(); i++) {
			for (int j = 0; j < bpwmeList.get(i).getChildren().size(); j++) {
				allParts.add((AbstractEditPart) bpwmeList.get(i).getChildren().get(j));
			}
		}
		
		for (int i = 0; i < allParts.size(); i++) {
			for (int j = 0; j < allParts.get(i).getChildren().size(); j++) {
				allParts.add((AbstractEditPart) allParts.get(i).getChildren().get(j));
			}
		}
		return allParts;
	}
	
//	/**
//	 * Get all Bpwme parts
//	 */
//	private IEditorPart setAllBpwmeParts(EObject element) {
//		ArrayList<AbstractEditPart> allParts = new ArrayList<AbstractEditPart>();
//	
//		for (int i = 0; i < bpwmeList.size(); i++) {
//			System.out.println(bpwmeList.get(i));
//			for (int j = 0; j < bpwmeList.get(i).getChildren().size(); j++) {
//				EObject bpwme = (EObjectImpl) ((View) ((AbstractEditPart) bpwmeList.get(i).getChildren().get(j)).getModel()).getElement();
//				if (bpwme.equals(element)) {
//					return mapBpwmePart.get(bpwmeList.get(i));
//				}
//				System.out.println(bpwmeList.get(i).getChildren().get(j));
//				allParts.add((AbstractEditPart) bpwmeList.get(i).getChildren().get(j));
//			}
//		}
//		
//		for (int i = 0; i < allParts.size(); i++) {
//			for (int j = 0; j < allParts.get(i).getChildren().size(); j++) {
//				EObject bpwme = (EObjectImpl) ((View) ((AbstractEditPart) allParts.get(i).getChildren().get(j)).getModel()).getElement();
//				if (bpwme.equals(element)) {
//					return mapBpwmePart.get(bpwmeList.get(k));
//				}
//				System.out.println(allParts.get(i).getChildren().get(j));
//				allParts.add((AbstractEditPart) allParts.get(i).getChildren().get(j));
//			}
//		}
//		return null;
//	}
	
	
	
	
	

}
