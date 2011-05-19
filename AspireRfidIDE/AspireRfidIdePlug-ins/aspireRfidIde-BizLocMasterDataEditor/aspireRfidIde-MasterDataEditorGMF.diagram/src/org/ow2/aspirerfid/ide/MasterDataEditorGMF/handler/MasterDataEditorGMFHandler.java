package org.ow2.aspirerfid.ide.MasterDataEditorGMF.handler;

import java.util.ArrayList;
import java.util.HashMap;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditor;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class MasterDataEditorGMFHandler {
	 
	/**
	 * Get a list with all the open MasterDataEditorGMF editors
	 */
	public ArrayList<CompanyEditPart> getOpenMasterDataEditorGMFEditors() {
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
	 * Get a list with all the EditorParts of all the open MasterDataEditorGMF editors
	 */
	public ArrayList<IEditorPart> getOpenMasterDataEditorGMFEditorParts() {
		ArrayList<IEditorPart> openEditors = new ArrayList<IEditorPart>();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		for (int i = 0; i < page.getEditorReferences().length; i++) {
			IEditorPart editorPart = page.getEditorReferences()[i].getEditor(true);
			
			if (editorPart.getSite().getId().equals(MasterDataEditorGMFDiagramEditor.ID)) {
				DiagramEditPart diagramEditPart = ((DiagramEditor)editorPart).getDiagramEditPart();

				if (diagramEditPart instanceof CompanyEditPart) {
					openEditors.add(editorPart);
				}
			}
		}

		return openEditors;
	}
	
	/**
	 * Get a list with all the open MasterDataEditorGMF editors
	 */
	public HashMap<IEditorPart, CompanyEditPart> getOpenMasterDataEditorGMFForEditorPart() {
		HashMap<IEditorPart, CompanyEditPart> openEditors = new HashMap<IEditorPart, CompanyEditPart>();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		for (int i = 0; i < page.getEditorReferences().length; i++) {
			IEditorPart editorPart = page.getEditorReferences()[i].getEditor(true);
			
			if (editorPart.getSite().getId().equals(MasterDataEditorGMFDiagramEditor.ID)) {
				DiagramEditPart diagramEditPart = ((DiagramEditor)editorPart).getDiagramEditPart();

				if (diagramEditPart instanceof CompanyEditPart) {
					CompanyEditPart editor = (CompanyEditPart) diagramEditPart;
					openEditors.put(editorPart, editor);
				}
			}
		}

		return openEditors;
	}
		
}
