package org.ow2.aspirerfid.ide.MasterDataEditorGMF.handler;

import java.util.ArrayList;

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

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class EditorHandler {

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
	public static ArrayList<CompanyEditPart> getOpenEditors() {
		ArrayList<CompanyEditPart> openEditors = new ArrayList<CompanyEditPart>();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		for (int i = 0; i < page.getEditorReferences().length; i++) {
			IEditorPart editorPart = page.getEditorReferences()[i].getEditor(true);
			DiagramEditPart diagramEditPart = ((DiagramEditor)editorPart).getDiagramEditPart();

			if (diagramEditPart instanceof CompanyEditPart) {
				CompanyEditPart editor = (CompanyEditPart) diagramEditPart;
				openEditors.add(editor);
			}
		}

		return openEditors;
	}
	
}
