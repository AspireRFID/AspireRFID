package org.ow2.aspirerfid.ide.bpwme.actions;

import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.EBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

public class ImportBusinessLocationAction extends Action{
	
	public ImportBusinessLocationAction() {
		super("Import Business Location", IAction.AS_PUSH_BUTTON);
		setId("ImportBusinessLocation");
		setToolTipText("Import Business Location");
		setImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_IMPORT_WIZ));		
	}
	
	@Override
	public void run() {
		//get the editpart
		IEditorPart editor = MainUtil.getEditor(BpwmeDiagramEditor.ID);
		if(editor == null) {
			return;
		}
		ISelection selection = editor.getSite().getSelectionProvider().getSelection();

		GraphicalEditPart selectedEditPart = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() != 1) {
				return;
			}
			selectedEditPart = (GraphicalEditPart) structuredSelection.getFirstElement();
		}
		String clcbName = null;
		CLCBProcEditPart clcbPart = null;
		if(selectedEditPart instanceof CLCBProcEditPart) {
			clcbPart = ((CLCBProcEditPart)selectedEditPart);
			//if it is ebproc, get the parent	
		}else if(selectedEditPart instanceof EBProcEditPart){
			clcbPart = (CLCBProcEditPart)((EBProcEditPart)selectedEditPart).getParent().getParent();

		}else {
			return;
		}

		if(clcbPart == null) {
			return;
		}
		
		
		super.run();
	}

}
