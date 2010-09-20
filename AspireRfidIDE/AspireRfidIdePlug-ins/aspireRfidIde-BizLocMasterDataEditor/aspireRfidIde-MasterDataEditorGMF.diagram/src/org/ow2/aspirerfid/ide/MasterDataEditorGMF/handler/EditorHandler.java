package org.ow2.aspirerfid.ide.MasterDataEditorGMF.handler;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditor;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.WorkflowMap;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.WorkflowMapEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.impl.WorkflowMapImpl;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class EditorHandler {
	
	/**
	 * Map the CLCBProc elements with the file they reside
	 */
	private HashMap<CLCBProc, IPath> clcbFileName = new HashMap<CLCBProc, IPath>();

//	/**
//	 * Close all editor
//	 */
//	private static void isEditorOpened(String editorID) {
//		IWorkbenchPage page = PlatformUI.getWorkbench().
//		getActiveWorkbenchWindow().getActivePage();
//		IEditorReference[] refs = page.getEditorReferences();
//		ArrayList<IEditorReference> closedListRefs = new ArrayList<IEditorReference>();
//		for(int i = 0; i < refs.length; i++) {
//			if(refs[i].getId().equals(editorID)) {
//				closedListRefs.add(refs[i]);
//			}
//		}
//		IEditorReference[] closedRefs = new IEditorReference[closedListRefs.size()];
//		for (int i = 0; i < closedListRefs.size(); i++) {
//			closedRefs[i] = closedListRefs.get(i);
//		}
//		page.closeEditors(closedRefs, true);
//	}
//	
//	/**
//	 * Reopen the diagram to refresh the new attributes
//	 */
//	public static void reopenActiveDiagram(IWorkbenchWindow window) {
//		IWorkbenchPage page = window.getActivePage();
//		
//		try {
//			isEditorOpened(MasterDataEditorGMFDiagramEditor.ID);
//			for (int i = 0; i < MasterDataEditParts.getFileURI().size(); i++)
//				page.openEditor(new URIEditorInput(MasterDataEditParts.getFileURI().get(i)), MasterDataEditorGMFDiagramEditor.ID);	
//		} catch (PartInitException e) {
//			e.printStackTrace();
//		}	
//	}
	
	/**
	 * Get a list with all the open MasterDataEditorGMF editors
	 */
	public ArrayList<CompanyEditPart> getOpenEditors() {
		ArrayList<CompanyEditPart> openEditors = new ArrayList<CompanyEditPart>();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		for (int i = 0; i < page.getEditorReferences().length; i++) {
			IEditorPart editorPart = page.getEditorReferences()[i].getEditor(true);
			
			if (editorPart.getSite().getId().equals(MasterDataEditorGMFDiagramEditor.ID)) {
				DiagramEditPart diagramEditPart = ((DiagramEditor)editorPart).getDiagramEditPart();

				if (diagramEditPart instanceof CompanyEditPart) {
					CompanyEditPart editor = (CompanyEditPart) diagramEditPart;
					openEditors.add(editor);
				}
			}
		}

		return openEditors;
	}
	
	/**
	 * Get a list with all the CLCBProc elements that reside in open BPWME editors
	 */
	public ArrayList<CLCBProc> getCLCBProc() {
		ArrayList<OLCBProc> olcbProcs = new ArrayList<OLCBProc>();
		ArrayList<CLCBProc> clcbProcs = new ArrayList<CLCBProc>();
		ArrayList<WorkflowMap> workflowMaps = new ArrayList<WorkflowMap>();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		for (int i = 0; i < page.getEditorReferences().length; i++) {
			IEditorPart editorPart = page.getEditorReferences()[i].getEditor(true);
			
			if (editorPart.getSite().getId().equals(BpwmeDiagramEditor.ID)) {
				DiagramEditPart diagramEditPart = ((DiagramEditor)editorPart).getDiagramEditPart();
		
				if (diagramEditPart instanceof WorkflowMapEditPart) {
					WorkflowMapEditPart workflowMap = (WorkflowMapEditPart) diagramEditPart;
					WorkflowMap  workflowMapDomain = (WorkflowMapImpl) ((View) workflowMap.getModel()).getElement();
					workflowMaps.add(workflowMapDomain);
				}
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
	
	/**
	 * Get a list with all the file names of open BPWME editors
	 */
	public HashMap<CLCBProc, IPath> getBpwmeFileNames() {
		clcbFileName.clear();
		ArrayList<CLCBProc> clcbProcs = getCLCBProc();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		for (int i = 0; i < page.getEditorReferences().length; i++) {
			IEditorPart editorPart = page.getEditorReferences()[i].getEditor(true);
			
			if (editorPart.getSite().getId().equals(BpwmeDiagramEditor.ID)) {
				DiagramEditPart diagramEditPart = ((DiagramEditor)editorPart).getDiagramEditPart();
				
				if (diagramEditPart instanceof WorkflowMapEditPart) {
					WorkflowMapEditPart workflowMap = (WorkflowMapEditPart) diagramEditPart;
					WorkflowMap  workflowMapDomain = (WorkflowMapImpl) ((View) workflowMap.getModel()).getElement();
					
					
					for (int k = 0; k < workflowMapDomain.eContents().size(); k++) {
						for (int j = 0; j < clcbProcs.size(); j++) {
							if (workflowMapDomain.eContents().get(k).eContents().contains(clcbProcs.get(j))) {
								String path = editorPart.getTitleToolTip().replaceFirst("file:/", "");
								path = path.replaceFirst(workflowMap.getDiagramView().getName(), "");
								clcbFileName.put(clcbProcs.get(j), new Path(path));
							}
						}
					}
				}
			}
		}
		
		return clcbFileName;
	}
	
}
