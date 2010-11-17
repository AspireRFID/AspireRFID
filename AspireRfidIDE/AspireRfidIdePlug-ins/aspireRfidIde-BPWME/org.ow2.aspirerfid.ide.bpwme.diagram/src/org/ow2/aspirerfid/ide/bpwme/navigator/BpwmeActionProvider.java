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
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditor;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.CompanyImpl;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.EBProc;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.WorkflowMap;
import org.ow2.aspirerfid.ide.bpwme.diagram.comboeditor.ComboEditor;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.WorkflowMapEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.impl.WorkflowMapImpl;

/**
 * Action provider for the navigator
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class BpwmeActionProvider extends CommonActionProvider
{

    private OpenChildAction openAction;
    private HashMap<String, IWorkbenchPart> mapUriPart = new HashMap<String, IWorkbenchPart>();
    private HashMap<EObject, IWorkbenchPart> mapBpwmeElementWithFileName = new HashMap<EObject, IWorkbenchPart>();
    private HashMap<EObject, IWorkbenchPart> mapMasterDataElementWithFileName = new HashMap<EObject, IWorkbenchPart>();


    public BpwmeActionProvider()
    {

    }

    @Override
    public void init(ICommonActionExtensionSite site)
    {
        ICommonViewerSite viewSite = site.getViewSite();
        if (viewSite instanceof ICommonViewerWorkbenchSite) 
        {
            ICommonViewerWorkbenchSite workbenchSite = (ICommonViewerWorkbenchSite) viewSite;
            openAction = new OpenChildAction(workbenchSite.getPage(), workbenchSite.getSelectionProvider());
        }
    }
    
    
    

    @Override
    public void restoreState(IMemento memento)
    {
        super.restoreState(memento);
    }

    @Override
    public void saveState(IMemento memento)
    {
        super.saveState(memento);
    }

    @Override
    public void fillActionBars(IActionBars actionBars)
    {
        if (openAction.isEnabled())
        {
            actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openAction);
        }
    }
    
    @Override
    public void fillContextMenu(IMenuManager menu)
    {
        if (openAction.isEnabled())
        {
            menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, openAction);
        }
    }

    
    /**
     * 
     * 
     * 
     */
    class OpenChildAction extends Action
    {
        private ISelectionProvider provider;
        private Object data;

        public OpenChildAction(IWorkbenchPage workbenchPage, ISelectionProvider selectionProvider)
        {
            super("Show");
            provider = selectionProvider;
        }
        

        @Override
        public void run() {
            
            if (data != null) {
            	IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            	
            	if (data instanceof URI) {
            		URI uri = (URI) data;
            		page.activate(getUriPart().get(uri.toString()));
            	}
            	if (data instanceof CLCBProc || data instanceof OLCBProc || data instanceof EBProc)
            		page.activate(getEditorForBpwme().get(data));
            	else if (data instanceof Company || data instanceof AbstractWarehouse || data instanceof AbstractContainer)
            		page.activate(getEditorForMasterData().get(data));
            		
            } 
            super.run();
        }

        @Override
        public boolean isEnabled()
        {
            ISelection selection = provider.getSelection();
            if (!selection.isEmpty())
            {
                IStructuredSelection sSelection = (IStructuredSelection) selection;
                if (sSelection.size() == 1 && sSelection.getFirstElement() instanceof Object) 
                {
                    data = (Object)sSelection.getFirstElement();
                    return true;
                }
            }
            return false;
        }

           
    }
    
	/**
	 * Get the Bpwme file URIs
	 */
	public HashMap<EObject, IWorkbenchPart> getEditorForBpwme() {
		mapBpwmeElementWithFileName.clear();
		
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		for (int i = 0; i < page.getEditorReferences().length; i++) {
			IEditorPart editorPart = page.getEditorReferences()[i].getEditor(true);
			
			if (editorPart.getSite().getId().equals(BpwmeDiagramEditor.ID)) {
				DiagramEditPart diagramEditPart = ((DiagramEditor)editorPart).getDiagramEditPart();
				IWorkbenchPart part = page.getEditorReferences()[i].getPart(true);

				if (diagramEditPart instanceof WorkflowMapEditPart) {
					WorkflowMapEditPart workflowMap = (WorkflowMapEditPart) diagramEditPart;
					WorkflowMap  workflowProc = (WorkflowMapImpl) ((View) workflowMap.getModel()).getElement();
					
					for (int j = 0; j < workflowProc.eContents().size(); j++) {
						if (workflowProc.eContents().get(j) instanceof OLCBProc) {
							OLCBProc olcbProc = (OLCBProc) workflowProc.eContents().get(j);
							mapBpwmeElementWithFileName.put(olcbProc, part);
							
							for (int k = 0; k < olcbProc.eContents().size(); k++) {
								if (olcbProc.eContents().get(k) instanceof CLCBProc) {
									CLCBProc clcbProc = (CLCBProc) olcbProc.eContents().get(k);
									mapBpwmeElementWithFileName.put(clcbProc, part);
									
									for (int l = 0; l < clcbProc.eContents().size(); l++) {
										if (clcbProc.eContents().get(l) instanceof EBProc) {
											EBProc ebProc = (EBProc) clcbProc.eContents().get(l);
											mapBpwmeElementWithFileName.put(ebProc, part);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return mapBpwmeElementWithFileName;
	}
	
	/**
	 * Get the MasterData file URIs
	 */
	public HashMap<EObject, IWorkbenchPart> getEditorForMasterData() {
		mapMasterDataElementWithFileName.clear();
		ArrayList<AbstractEditPart> allParts = new ArrayList<AbstractEditPart>(); 
		
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		for (int i = 0; i < page.getEditorReferences().length; i++) {
			allParts.clear();
			
			IEditorPart editorPart = page.getEditorReferences()[i].getEditor(true);
			
			if (editorPart.getSite().getId().equals(MasterDataEditorGMFDiagramEditor.ID)) {
				DiagramEditPart diagramEditPart = ((DiagramEditor)editorPart).getDiagramEditPart();
				IWorkbenchPart part = page.getEditorReferences()[i].getPart(true);

				if (diagramEditPart instanceof CompanyEditPart) {
					CompanyEditPart companyPart = (CompanyEditPart) diagramEditPart;
					Company company = (CompanyImpl) ((View) companyPart.getModel()).getElement();
					mapMasterDataElementWithFileName.put(company, part);
					
					//add the warehouse children of the company
					for (int j = 0; j < companyPart.getChildren().size(); j++) {
						allParts.add((AbstractEditPart) companyPart.getChildren().get(j));
						
						if (MasterDataEditParts.isWarehouse((AbstractEditPart) companyPart.getChildren().get(j))) {
							EObject element = (EObjectImpl) ((View) ((AbstractEditPart) companyPart.getChildren().get(j)).getModel()).getElement();
							mapMasterDataElementWithFileName.put(element, part);
						}
					}
					
					//add all the other children
					for (int k = 0; k < allParts.size(); k++) {
						for (int l = 0; l < allParts.get(k).getChildren().size(); l++) {
							allParts.add((AbstractEditPart) allParts.get(k).getChildren().get(l));
							
							if (MasterDataEditParts.isWarehouse((AbstractEditPart) allParts.get(k).getChildren().get(l))) {
								EObject element = (EObjectImpl) ((View) ((AbstractEditPart) allParts.get(k).getChildren().get(l)).getModel()).getElement();
								mapMasterDataElementWithFileName.put(element, part);
							}
							else if (MasterDataEditParts.isContainer((AbstractEditPart) allParts.get(k).getChildren().get(l))) {
								EObject element = (EObjectImpl) ((View) ((AbstractEditPart) allParts.get(k).getChildren().get(l)).getModel()).getElement();
								mapMasterDataElementWithFileName.put(element, part);
							}
						}
					}
				}
			}
			else if (editorPart.getSite().getId().equals(ComboEditor.ID)) {
				BpwmeDiagramEditor bpwmeDiagramEditor = ((ComboEditor)editorPart).getBpwmeEditor();
				DiagramEditPart diagramEditPart = bpwmeDiagramEditor.getDiagramEditPart();
				IWorkbenchPart part = page.getEditorReferences()[i].getPart(true);

				if (diagramEditPart instanceof CompanyEditPart) {
					CompanyEditPart companyPart = (CompanyEditPart) diagramEditPart;
					Company company = (CompanyImpl) ((View) companyPart.getModel()).getElement();
					mapMasterDataElementWithFileName.put(company, part);
					
					//add the warehouse children of the company
					for (int j = 0; j < companyPart.getChildren().size(); j++) {
						allParts.add((AbstractEditPart) companyPart.getChildren().get(j));
						
						if (MasterDataEditParts.isWarehouse((AbstractEditPart) companyPart.getChildren().get(j))) {
							EObject element = (EObjectImpl) ((View) ((AbstractEditPart) companyPart.getChildren().get(j)).getModel()).getElement();
							mapMasterDataElementWithFileName.put(element, part);
						}
					}
					
					//add all the other children
					for (int k = 0; k < allParts.size(); k++) {
						for (int l = 0; l < allParts.get(k).getChildren().size(); l++) {
							allParts.add((AbstractEditPart) allParts.get(k).getChildren().get(l));
							
							if (MasterDataEditParts.isWarehouse((AbstractEditPart) allParts.get(k).getChildren().get(l))) {
								EObject element = (EObjectImpl) ((View) ((AbstractEditPart) allParts.get(k).getChildren().get(l)).getModel()).getElement();
								mapMasterDataElementWithFileName.put(element, part);
							}
							else if (MasterDataEditParts.isContainer((AbstractEditPart) allParts.get(k).getChildren().get(l))) {
								EObject element = (EObjectImpl) ((View) ((AbstractEditPart) allParts.get(k).getChildren().get(l)).getModel()).getElement();
								mapMasterDataElementWithFileName.put(element, part);
							}
						}
					}
				}
			}
		}
				
		return mapMasterDataElementWithFileName;
	}
	
	/**
	 * Get the file URIs
	 */
	public HashMap<String, IWorkbenchPart> getUriPart() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		for (int i = 0; i < page.getEditorReferences().length; i++) {
			IEditorPart editorPart = page.getEditorReferences()[i].getEditor(true);
			URIEditorInput uri = (URIEditorInput)editorPart.getEditorInput();
			
			if (editorPart.getSite().getId().equals(BpwmeDiagramEditor.ID)) {
				IWorkbenchPart part = page.getEditorReferences()[i].getPart(true);
				File file = new File(uri.getURI().toFileString());
				mapUriPart.put(file.getName().replaceFirst(".bpwme_diagram", ""), part);
			}
			else if (editorPart.getSite().getId().equals(ComboEditor.ID)) {
				IWorkbenchPart part = page.getEditorReferences()[i].getPart(true);
				File file = new File(uri.getURI().toFileString());
				mapUriPart.put(file.getName().replaceFirst(".bpwme_diagram", ""), part);
			}
		}
		
		return mapUriPart;
	}

}
