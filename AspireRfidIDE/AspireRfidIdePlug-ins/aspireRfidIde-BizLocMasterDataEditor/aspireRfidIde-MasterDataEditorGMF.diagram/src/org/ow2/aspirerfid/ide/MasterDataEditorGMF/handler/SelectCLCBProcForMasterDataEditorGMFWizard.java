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

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class SelectCLCBProcForMasterDataEditorGMFWizard extends Wizard implements
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
	 * The name of the MasterDataEditorGMF diagram file according to all the CLCBProc elements
	 */
	protected SelectCLCBProcForMasterDataEditorGMFWizardPage diagramModelFileNamePage;

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
	 * Get diagramModelFileNamePage
	 */
	public SelectCLCBProcForMasterDataEditorGMFWizardPage getDiagramModelFileNamePage() {
		return diagramModelFileNamePage;
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
		diagramModelFileNamePage = new SelectCLCBProcForMasterDataEditorGMFWizardPage(
				"DiagramModel", getSelection()); //$NON-NLS-1$ //$NON-NLS-2$
		diagramModelFileNamePage
		.setTitle("Select a CLCBProc for the MasterDataEditorGMF");
		diagramModelFileNamePage
				.setDescription("Select the CLCBProc that the MasterDataEditorGMF will reside in\nor press cancel to not correlate the MasterDataEditorGMF with any CLCBProc.");
		addPage(diagramModelFileNamePage);
	}

	/**
	 * @generated
	 */
	public boolean performFinish() {
		setClcbProc(diagramModelFileNamePage.getClcbProc());
		return true;
	}
}
