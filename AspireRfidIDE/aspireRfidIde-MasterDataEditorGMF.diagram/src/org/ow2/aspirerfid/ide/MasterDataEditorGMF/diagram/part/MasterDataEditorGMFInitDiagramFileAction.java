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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * @generated
 */
public class MasterDataEditorGMFInitDiagramFileAction implements
		IWorkbenchWindowActionDelegate {

	/**
	 * @generated
	 */
	private IWorkbenchWindow window;

	/**
	 * @generated
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	/**
	 * @generated
	 */
	public void dispose() {
		window = null;
	}

	/**
	 * @generated
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * @generated
	 */
	private Shell getShell() {
		return window.getShell();
	}

	/**
	 * @generated
	 */
	public void run(IAction action) {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
				.createEditingDomain();
		Resource resource = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
				.openModel(
						getShell(),
						org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFInitDiagramFileAction_OpenModelFileDialogTitle,
						editingDomain);
		if (resource == null || resource.getContents().isEmpty()) {
			return;
		}
		EObject diagramRoot = (EObject) resource.getContents().get(0);
		Wizard wizard = new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNewDiagramFileWizard(
				resource.getURI(), diagramRoot, editingDomain);
		wizard
				.setWindowTitle(NLS
						.bind(
								org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFInitDiagramFileAction_InitDiagramFileWizardTitle,
								org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.MODEL_ID));
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
				.runWizard(getShell(), wizard, "InitDiagramFile"); //$NON-NLS-1$
	}
}
