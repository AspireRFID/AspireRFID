package org.ow2.aspirerfid.ide.bpwme.test;

import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

public class PropertyActionEditMasterData extends Action {
	public PropertyActionEditMasterData() {
		super("Edit Master Data", IAction.AS_PUSH_BUTTON);
		setId("EditMasterDataAction");
		setToolTipText("Edit Master Data");
		setImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART));		
	}
	
	@Override
	public void run() {
		MainUtil.executeCommand("org.ow2.aspirerfid.ide.bpwme.diagram.showMasterDataEditor");
		
		
		
//		MainUtil.activateEditor(BpwmeDiagramEditor.ID);
//		IEditorPart editor = MainUtil.getEditor(BpwmeDiagramEditor.ID);
//		if(editor == null) {
//			return;
//		}
//		ISelection selection = editor.getSite().getSelectionProvider().getSelection();
//		
//		GraphicalEditPart selectedEditPart = null;
//		if (selection instanceof IStructuredSelection) {
//			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
//			if (structuredSelection.size() != 1) {
//				return;
//			}
//			selectedEditPart = (GraphicalEditPart) structuredSelection.getFirstElement();
//		}
//		
//		if(selectedEditPart instanceof CLCBProcEditPart) {
//			System.out.println(selectedEditPart);
//		}else {
//			//do nothing
//		}
//		super.run();
	}

	
	
}
