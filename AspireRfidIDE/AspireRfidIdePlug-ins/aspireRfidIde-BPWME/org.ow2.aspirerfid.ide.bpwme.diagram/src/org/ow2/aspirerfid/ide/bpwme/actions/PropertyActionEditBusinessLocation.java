package org.ow2.aspirerfid.ide.bpwme.actions;

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.EBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.MasterDataFileUtil;
import org.eclipse.gmf.runtime.notation.View;


public class PropertyActionEditBusinessLocation extends Action{

	public PropertyActionEditBusinessLocation() {
		super("Edit Business Location", IAction.AS_PUSH_BUTTON);
		setId("EditBusinessLocation");
		setToolTipText("Edit Business Location");
		setImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EDITOR_TRIMPART));		
	}

	@Override
	public void run() {
		//get the editpart
		IEditorPart editor = MainUtil.getBPWMEEditor();
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

		CLCBProc clcb = (CLCBProc)((View)clcbPart.getModel()).getElement();
		clcbName = clcb.getName();

		if(clcbName == null) {
			return;
		}
		
		MainControl mc = MainControl.getMainControl();
		URI uri = mc.getApdlURI();
		
		String projectName = MainUtil.getProjectName(uri.toFileString());

		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();
		String dir = store.getString(PreferenceConstants.P_BPWME_DIR);
				
		String newDir = dir + File.separator + 
			projectName + File.separator + 
			clcbName + File.separator;
		//check if the corresponding file exists
		
		String lFileName = getLocationFile(newDir);
		
		//System.out.println(lFileName);
		//if it is, open the file
		if(lFileName != null) {
			MasterDataFileUtil.openMasterDataFile(lFileName);
		}else {//else create a new one			
			MainUtil.executeCommand("org.ow2.aspirerfid.ide.MasterDataEditorGMF.newMasterDataEditorGMFBpwme.command");
		}
		super.run();
	}

	//get the diagram file name from the directory
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
}
