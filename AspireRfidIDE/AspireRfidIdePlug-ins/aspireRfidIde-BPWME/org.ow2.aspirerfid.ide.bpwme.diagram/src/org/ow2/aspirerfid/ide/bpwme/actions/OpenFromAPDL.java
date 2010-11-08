package org.ow2.aspirerfid.ide.bpwme.actions;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.handler.OpenMasterDataEditorGMFFromBpwmeApdlWizard;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataGMFCreateFromFile;
import org.ow2.aspirerfid.ide.bpwme.BpwmeFactory;
import org.ow2.aspirerfid.ide.bpwme.WorkflowMap;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.WorkflowMapEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorUtil;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeNewDiagramFileWizard;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.Messages;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

public class OpenFromAPDL extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
		.createEditingDomain();
		
		//select the apdl file
		FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();		
		String filterPath = store.getString(PreferenceConstants.P_APDL_DIR);
		String[] extensions = new String[]{"*.xml"};
		fileDialog.setFilterExtensions(extensions);
		fileDialog.setFilterPath(filterPath);		
		fileDialog.open();
		
		String fileName = fileDialog.getFileName();
		if (fileName == null || fileName.length() == 0) {
			return null;
		}
		if (fileDialog.getFilterPath() != null) {
			fileName = fileDialog.getFilterPath() + File.separator + fileName;
		}
		Path path1 = new Path(fileDialog.getFilterPath());
		Path path2 = new Path(filterPath);
		
		String projectNames[] = fileDialog.getFileName().split("\\.");
		String projectName;
		if(projectNames.length == 0) {
			projectName = fileDialog.getFileName();
		}else {
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < projectNames.length - 1; i++) {
				sb.append(projectNames[i]);
			}
			projectName = sb.toString();
		}
		
		//create a new, unique apdl file anyway
		String newApdlName = BpwmeDiagramEditorUtil.getUniqueFileName(path1, projectName, "xml");
		MainUtil.copyFile(fileName, path2.append(newApdlName).toOSString());
		String newApdlFile = path2.append(newApdlName).toOSString();
		//read the file into memory, get the model
		MainControl mc = MainControl.getMainControl();
		mc.setAPDLURI(newApdlFile);
		mc.rebuild();
		OLCBProc olcb = mc.getOLCBProc();
		
		//transfer the model to a GMF model
		WorkflowMap workflowMap = BpwmeFactory.eINSTANCE.createWorkflowMap();
		MainUtil.copyToDiagramModel(olcb, workflowMap);
		
		//use the gmf model to get the whole diagram
		BpwmeNewDiagramFileWizard wizard = new BpwmeNewDiagramFileWizard(mc.getApdlURI(),
				(EObject)workflowMap, editingDomain);
		wizard.setWindowTitle(NLS.bind(Messages.InitDiagramFile_WizardTitle,
				WorkflowMapEditPart.MODEL_ID));
		BpwmeDiagramEditorUtil.runWizard(shell, wizard, "InitDiagramFile"); //$NON-NLS-1$
		
		//Modified by elka
		boolean result = MessageDialog.openQuestion(shell, "Question", 
				"Do you want to create also the MasterData from the Apdl file?");
		
		if (result) {
			URI masterDataURI = URI.createURI(fileDialog.getFilterPath() + File.separator + fileDialog.getFileName());
			//open APDL file and set the CLCBProc names
			MasterDataGMFCreateFromFile.openApdlFile(masterDataURI);
			MasterDataGMFCreateFromFile.setClcbProcNamesWithMasterDataFromApdl();
			
			if (MasterDataGMFCreateFromFile.getClcProcNames().size() == 0) {
				MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", 
						"No MasterData found in the Apdl file.");
				return null;
			}
	
			IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
			//create the MasterDataEditorGMF
			OpenMasterDataEditorGMFFromBpwmeApdlWizard wizard1 = new OpenMasterDataEditorGMFFromBpwmeApdlWizard();
			//set the file
			wizard1.setFileURI(wizard.getBpwmeURI());
			wizard1.init(window.getWorkbench(), StructuredSelection.EMPTY);
			WizardDialog wizardDialog = new WizardDialog(
					window.getShell(), wizard1);
			wizardDialog.open();
		}
		
		return null;
		
	}

}
