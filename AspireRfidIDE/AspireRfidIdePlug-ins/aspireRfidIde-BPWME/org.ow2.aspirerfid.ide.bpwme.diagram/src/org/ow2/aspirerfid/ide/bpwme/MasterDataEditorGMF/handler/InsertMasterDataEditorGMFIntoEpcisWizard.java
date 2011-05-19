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

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFCreationWizardPage;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataGMFCapture;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class InsertMasterDataEditorGMFIntoEpcisWizard extends Wizard implements
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
	protected MasterDataEditorGMFCreationWizardPage diagramModelFilePage;
	
	/**
	 * @generated
	 */
	protected InsertMasterDataEditorGMFIntoEpcisWizardPage diagramModelPage;

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
		setWindowTitle("Insert MasterDataEditorGMF Diagram");
		ImageDescriptor descriptor = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin.
			imageDescriptorFromPlugin("org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram", "icons/wizban/NewMasterDataEditorGMFWizard.gif");
		setDefaultPageImageDescriptor(descriptor); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}

	/**
	 * @generated
	 */
	public void addPages() {
		diagramModelPage = new InsertMasterDataEditorGMFIntoEpcisWizardPage (
				"DiagramModel", getSelection()); //$NON-NLS-1$ //$NON-NLS-2$
		diagramModelPage
				.setTitle("Insert MasterDataEditorGMF Diagram Into Epcis");
		diagramModelPage
				.setDescription("Select the Company that will be inserted into EPCIS.");
		addPage(diagramModelPage);
	}

	/**
	 * @generated
	 */
	public boolean performFinish() {
		if (!MasterDataEditParts.isInserted(MasterDataEditParts.getCompanyPart())) {
			MasterDataEditParts.setInitialParts();
			MasterDataEditParts.setIsInserted(MasterDataEditParts.getCompanyPart(), true);
		}
		else
			MasterDataEditParts.setEndParts();

		MasterDataGMFCapture.insertInEPCIS();
		
		if (MasterDataEditParts.isInsertedIntoEpcis()) {
			MessageDialog.openInformation(getShell(), "Successful Insertion", 
			"The MasterData have been successfully inserted into EPCIS.");
		}
		else {
			MessageDialog.openError(getShell(), "Error", 
			"The MasterData have not been inserted into EPCIS.\nCheck if a connection to the EPCIS repository exists.");
		}
		
		return true;
	}
}
