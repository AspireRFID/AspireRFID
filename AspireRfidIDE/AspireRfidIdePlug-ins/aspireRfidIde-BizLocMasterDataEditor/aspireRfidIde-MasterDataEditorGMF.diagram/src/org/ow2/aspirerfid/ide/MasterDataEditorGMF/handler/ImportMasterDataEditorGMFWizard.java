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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.handler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class ImportMasterDataEditorGMFWizard extends Wizard implements
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
	 * The existing MasterDataEditorGMF diagram file 
	 */
	protected CorrelateMasterDataEditorGMFWithClcbWizardPage existingModelFileNamePage;

	/**
	 * @generated
	 */
	protected Resource diagram;
	
	/**
	 * CLCBProc
	 */
	private CLCBProc clcbProc;
	
	/**
	 * @return the clcbProc
	 */
	public CLCBProc getClcbProc() {
		return clcbProc;
	}

	/**
	 * @param clcbProc the clcbProc to set
	 */
	public void setClcbProc(CLCBProc clcbProc) {
		this.clcbProc = clcbProc;
	}

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
		setWindowTitle("Select CLCBProc");
		ImageDescriptor descriptor = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin.
			imageDescriptorFromPlugin("org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram", "icons/wizban/NewMasterDataEditorGMFWizard.gif");
		setDefaultPageImageDescriptor(descriptor); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}

	/**
	 * @generated
	 */
	public void addPages() {
		existingModelFileNamePage = new CorrelateMasterDataEditorGMFWithClcbWizardPage(
				"DiagramModel", getSelection()); //$NON-NLS-1$ //$NON-NLS-2$
		existingModelFileNamePage
		.setTitle("Select a MasterDataEditorGMF Diagram");
		existingModelFileNamePage
				.setDescription("Select the MasterDataEditorGMF that will reside in the selected CLCB.");
		addPage(existingModelFileNamePage);
	}

	/**
	 * @generated
	 */
	public boolean performFinish() {
		String fileSeparator = System.getProperty("file.separator");				
		String bpwmePath = editorHandler.getBpwmeFileNames().get(clcbProc).toString();
		String masterDataPath = fileSeparator + clcbProc.getName() + fileSeparator;
		
		//create the directory under which the MasterDataEditorGMF file will reside
		File directory = new File(bpwmePath + masterDataPath);
		if(!directory.exists())
			directory.mkdirs();

		IPath path = new Path(bpwmePath + masterDataPath + clcbProc.getName());
		createDiagram(URI.createFileURI(path.toString() + ".masterdataeditorgmf_diagram"));
	
		return true;
	}
	
	/**
	 * Create the diagram
	 */
	private void createDiagram(final URI fileURI) {
		//prompt the user to replace the file
		File file = new File(fileURI.toFileString());

		if (file.exists()) {
			boolean result = MessageDialog.openConfirm(getShell(), "Warning", 
					file.getName() + " already exists.\nDo you want to replace it?");
			if (!result)
				return;
		}
		
		IRunnableWithProgress op = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {				
				diagram = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
						.createDiagram(fileURI, monitor);
				if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
					try {
						org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
								.openDiagram(diagram);
					} catch (PartInitException e) {
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
			return;
		} catch (InvocationTargetException e) {
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
			return;
		}
	}
	
}
