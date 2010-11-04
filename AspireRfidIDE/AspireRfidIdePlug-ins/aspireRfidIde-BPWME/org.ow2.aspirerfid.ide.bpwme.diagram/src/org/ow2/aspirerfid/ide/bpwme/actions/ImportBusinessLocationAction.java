package org.ow2.aspirerfid.ide.bpwme.actions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.ow2.aspirerfid.commons.apdl.model.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.EBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditor;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.impl.CLCBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.MasterDataFileUtil;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.bpwmeintegration.MasterDataContentsProvider;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.handler.*;

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
			System.out.println("Cannot get reference for " + BpwmeDiagramEditor.ID);
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
		
		CLCBProcEditPart clcbPart = null;
		String clcbName;
		
		if(selectedEditPart instanceof CLCBProcEditPart) {
			clcbPart = ((CLCBProcEditPart)selectedEditPart);
			//if it is ebproc, get the parent	
		}else if(selectedEditPart instanceof EBProcEditPart){
			clcbPart = (CLCBProcEditPart)((EBProcEditPart)selectedEditPart).getParent().getParent();
		}else {
			return;
		}
		
		if(clcbPart == null) {
			System.out.println("CLCBProcPart is null");
			return;
		}
		
		MainControl mc = MainControl.getMainControl();
		
		CLCBProcImpl clcbi = (CLCBProcImpl)((View)clcbPart.getModel()).getElement();
		CLCBProc clcb = (CLCBProc) mc.getMapObject(clcbi.hashCode());

		clcbName = clcb.getName();

		URI uri = mc.getApdlURI();
		
		String projectName = MainUtil.getProjectName(uri.toFileString());

		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();
		String dir = store.getString(PreferenceConstants.P_BPWME_DIR);
				
		String newDir = dir +  
			projectName + File.separator + 
			clcbName + File.separator;
		//check if the corresponding file exists
		
		
		String lFileName = getLocationFile(newDir);
		//if it is, open the file
		if(lFileName != null) {
			MasterDataBuilder mdb = MasterDataBuilder.getInstance();			
			mdb.setCLCBProc(clcb);
			
			MasterDataContentsProvider mcp = new MasterDataContentsProvider();
			mcp.setCompany(lFileName);
			
			
			HashMap<String, HashMap<String, String>> companyMap = 
				mcp.getCompanyModelUriAttributesValues();
			HashMap<String, HashMap<String, String>> warehouseMap = 
				mcp.getWarehouseModelUriAttributesValuesBpwme();
			HashMap<String, HashMap<String, String>> readpointMap = 
				mcp.getReadPointModelUriAttributesValuesBpwme();			
			
			mdb.setBusinessStepReadPoint(companyMap, warehouseMap, readpointMap);
			
		}else {//else create a new one			
			MainUtil.executeCommand("org.ow2.aspirerfid.ide.MasterDataEditorGMF.newMasterDataEditorGMF.command");
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
