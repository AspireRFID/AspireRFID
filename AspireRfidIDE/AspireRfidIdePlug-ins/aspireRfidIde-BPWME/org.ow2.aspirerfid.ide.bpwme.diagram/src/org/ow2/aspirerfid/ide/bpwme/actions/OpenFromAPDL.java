package org.ow2.aspirerfid.ide.bpwme.actions;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.BpwmeFactory;
import org.ow2.aspirerfid.ide.bpwme.WorkflowMap;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.WorkflowMapEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorUtil;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeNewDiagramFileWizard;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.Messages;
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
		fileDialog.open();
		String fileName = fileDialog.getFileName();
		if (fileName == null || fileName.length() == 0) {
			return null;
		}
		if (fileDialog.getFilterPath() != null) {
			fileName = fileDialog.getFilterPath() + File.separator + fileName;
		}
		//read the file into memory, get the model
		MainControl mc = MainControl.getMainControl();
		mc.setAPDLURI(fileName);
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
		
		return null;
		
	}

}
