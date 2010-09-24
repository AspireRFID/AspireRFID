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
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataGMFCreateFromFile;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class OpenMasterDataEditorGMFFromApdlWizard extends Wizard implements
		INewWizard {

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
	protected NewMasterDataEditorGMFWizardPage[] diagramModelFilePage;

	/**
	 * @generated
	 */
	protected Resource[] diagram;

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
	 * fileURI
	 */
	private URI fileURI;
	
	/**
	 * Counter for created Apdl files
	 */
	private static int count;

	/**
	 * @return the count
	 */
	public static int getCount() {
		return count;
	}

	/**
	 * @return the fileURI
	 */
	public URI getFileURI() {
		return fileURI;
	}

	/**
	 * @param fileURI the fileURI to set
	 */
	public void setFileURI(URI fileURI) {
		this.fileURI = fileURI;
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
	public final Resource[] getDiagram() {
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
		diagramModelFilePage = new NewMasterDataEditorGMFWizardPage[MasterDataGMFCreateFromFile.getClcProcNames().size()];
		
		for (int i = 0; i < diagramModelFilePage.length; i++) {
			diagramModelFilePage[i] = new NewMasterDataEditorGMFWizardPage(
					"DiagramModelFile", getSelection(), "masterdataeditorgmf_diagram"); //$NON-NLS-1$ //$NON-NLS-2$
			diagramModelFilePage[i]
					.setTitle("Open MasterDataEditorGMF Diagram From Apdl File");
			diagramModelFilePage[i]
					.setDescription("Select file that will contain diagram model for the MasterData"
							+ "\nthat reside in the CLCB element named \"" + MasterDataGMFCreateFromFile.getClcProcNames().get(i) + "\".");
			
			String fileSeparator =	System.getProperty("file.separator");
			String home = System.getProperty("user.home");
			String defaultPath = home + fileSeparator + "AspireRFID" + fileSeparator + "IDE" + fileSeparator + "BPWME" + fileSeparator +
			MasterDataGMFCreateFromFile.getClcProcNames().get(i) + fileSeparator;
			File directory = new File(defaultPath);
			if(!directory.exists())
				directory.mkdirs();
			diagramModelFilePage[i].setFileName(MasterDataGMFCreateFromFile.getClcProcNames().get(i));
			IPath path = new Path(defaultPath);
			diagramModelFilePage[i].setContainerFullPath(path);
			
			addPage(diagramModelFilePage[i]);
		}
	}

	/**
	 * @generated
	 */
	public boolean performFinish() {
		diagram = new Resource[diagramModelFilePage.length];
		count = 0;
		
		for (int i = 0; i < diagram.length; i++) {
			count = i;
			createDiagrams(i);
		}
		
		return true;
	}
	
	/**
	 * Create the diagrams
	 */
	public boolean createDiagrams(final int count) {
		//prompt the user to replace the file
		File file = new File(diagramModelFilePage[count].getURI().toFileString());

		if (file.exists()) {
			boolean result = MessageDialog.openConfirm(getShell(), "Warning", 
					diagramModelFilePage[count].getFileName() + ".masterdataeditorgmf_diagram already exists.\nDo you want to replace it?");
			if (!result)
				return true;
		}
		
		IRunnableWithProgress op = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				MasterDataGMFCreateFromFile.setFromApdl(true);

				diagram[count] = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
						.createDiagram(diagramModelFilePage[count].getURI(), monitor);
				if (isOpenNewlyCreatedDiagramEditor() && diagram[count] != null) {
					try {
						org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
								.openDiagram(diagram[count]);
					} catch (PartInitException e) {
						MasterDataGMFCreateFromFile.setFromApdl(false);
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
			MasterDataGMFCreateFromFile.setFromApdl(false);
			return false;
		} catch (InvocationTargetException e) {
			MasterDataGMFCreateFromFile.setFromApdl(false);
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
