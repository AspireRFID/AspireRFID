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

package org.ow2.aspirerfid.ide.bpwme.MasterDataEditorGMF.handler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class OpenMasterDataEditorGMFFromEpcisWizard extends Wizard implements
		INewWizard {
	
	/**
	 * Editor handler
	 */
	 EditorHandler editorHandler = new EditorHandler();

	/**
	 * @generated
	 */
	private IWorkbench workbench;

	/**
	 * @generated
	 */
	protected IStructuredSelection selection;

	/**
	 * @generated
	 */
	protected NewMasterDataEditorGMFWizardPage diagramModelFilePage;
	
	/**
	 * @generated
	 */
	protected OpenMasterDataEditorGMFFromEpcisWizardPage diagramModelPage;

	/**
	 * @generated
	 */
	protected Resource diagram;

	/**
	 * @generated
	 */
	private boolean openNewlyCreatedDiagramEditor = true;

	/**
	 * @generated
	 */
	public IWorkbench getWorkbench() {
		return workbench;
	}

	/**
	 * @generated
	 */
	public IStructuredSelection getSelection() {
		return selection;
	}

	/**
	 * @generated
	 */
	public final Resource getDiagram() {
		return diagram;
	}

	/**
	 * @generated
	 */
	public final boolean isOpenNewlyCreatedDiagramEditor() {
		return openNewlyCreatedDiagramEditor;
	}

	/**
	 * @generated
	 */
	public void setOpenNewlyCreatedDiagramEditor(
			boolean openNewlyCreatedDiagramEditor) {
		this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
	}

	/**
	 * @generated
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		setWindowTitle("Open MasterDataEditorGMF Diagram");
		ImageDescriptor descriptor = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin.
			imageDescriptorFromPlugin("org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram", "icons/wizban/NewMasterDataEditorGMFWizard.gif");
		setDefaultPageImageDescriptor(descriptor); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}

	/**
	 * @generated
	 */
	public void addPages() {
		diagramModelPage = new OpenMasterDataEditorGMFFromEpcisWizardPage (
				"DiagramModel", getSelection()); //$NON-NLS-1$ //$NON-NLS-2$
		diagramModelPage
				.setTitle("Open MasterDataEditorGMF Diagram From Epcis");
		diagramModelPage
				.setDescription("Select the Company from Epcis that will create the diagram model.");
		addPage(diagramModelPage);
		
		diagramModelFilePage = new NewMasterDataEditorGMFWizardPage(
				"DiagramModelFile", getSelection(), "masterdataeditorgmf_diagram"); //$NON-NLS-1$ //$NON-NLS-2$
		diagramModelFilePage
				.setTitle("Open MasterDataEditorGMF Diagram From Epcis");
		diagramModelFilePage
				.setDescription(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFCreationWizard_DiagramModelFilePageDescription);
		
		//set the default filename and path of the MasterDataEditorGMF file
		IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();
		String defaultPath = store.getString(org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants.P_BPWME_DIR);
		
		File directory = new File(defaultPath);
		if(!directory.exists())
			directory.mkdirs();
		diagramModelFilePage.setFileName("default");
		IPath path = new Path(defaultPath);
		diagramModelFilePage.setContainerFullPath(path);
		
		addPage(diagramModelFilePage);
	}

	/**
	 * @generated
	 */
	public boolean performFinish() {
		//prompt the user to replace the file
		File file = new File(diagramModelFilePage.getURI().toFileString());

		if (file.exists()) {
			boolean result = MessageDialog.openConfirm(getShell(), "Warning", 
					"The file already exists.\nDo you want to replace it?");
			if (!result)
				return true;
		}
		
		IRunnableWithProgress op = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				MasterDataEditParts.setFromEPCIS(true);
				MasterDataEditParts.setEpcisFileURI(diagramModelFilePage.getURI());
				
				diagram = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
						.createDiagram(diagramModelFilePage.getURI(), monitor);
				if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
					try {
						org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
								.openDiagram(diagram);
					} catch (PartInitException e) {
						MasterDataEditParts.setFromEPCIS(false);
						ErrorDialog
								.openError(
										getContainer().getShell(),
										org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFCreationWizardOpenEditorError,
										null, e.getStatus());
					}
				}
			}
		};
		try {
			getContainer().run(false, true, op);
		} catch (InterruptedException e) {
			MasterDataEditParts.setFromEPCIS(false);
			return false;
		} catch (InvocationTargetException e) {
			MasterDataEditParts.setFromEPCIS(false);
			if (e.getTargetException() instanceof CoreException) {
				ErrorDialog
						.openError(
								getContainer().getShell(),
								org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFCreationWizardCreationError,
								null, ((CoreException) e.getTargetException())
										.getStatus());
			} else {
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin
						.getInstance()
						.logError(
								"Error creating diagram", e.getTargetException()); //$NON-NLS-1$
			}
			return false;
		}
		return diagram != null;
	}
}
