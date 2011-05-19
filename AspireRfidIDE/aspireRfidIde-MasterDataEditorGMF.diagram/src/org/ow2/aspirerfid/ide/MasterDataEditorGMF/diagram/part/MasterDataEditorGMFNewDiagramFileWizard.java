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

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PartInitException;

/**
 * @generated
 */
public class MasterDataEditorGMFNewDiagramFileWizard extends Wizard {

	/**
	 * @generated
	 */
	private org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.application.WizardNewFileCreationPage myFileCreationPage;

	/**
	 * @generated
	 */
	private org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.ModelElementSelectionPage diagramRootElementSelectionPage;

	/**
	 * @generated
	 */
	private TransactionalEditingDomain myEditingDomain;

	/**
	 * @generated
	 */
	public MasterDataEditorGMFNewDiagramFileWizard(URI domainModelURI,
			EObject diagramRoot, TransactionalEditingDomain editingDomain) {
		assert domainModelURI != null : "Domain model uri must be specified"; //$NON-NLS-1$
		assert diagramRoot != null : "Diagram root element must be specified"; //$NON-NLS-1$
		assert editingDomain != null : "Editing domain must be specified"; //$NON-NLS-1$

		myFileCreationPage = new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.application.WizardNewFileCreationPage(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFNewDiagramFileWizard_CreationPageName,
				StructuredSelection.EMPTY);
		myFileCreationPage
				.setTitle(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFNewDiagramFileWizard_CreationPageTitle);
		myFileCreationPage
				.setDescription(NLS
						.bind(
								org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFNewDiagramFileWizard_CreationPageDescription,
								org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.MODEL_ID));
		IPath filePath;
		String fileName = domainModelURI.trimFileExtension().lastSegment();
		if (domainModelURI.isPlatformResource()) {
			filePath = new Path(domainModelURI.trimSegments(1)
					.toPlatformString(true));
		} else if (domainModelURI.isFile()) {
			filePath = new Path(domainModelURI.trimSegments(1).toFileString());
		} else {
			// TODO : use some default path
			throw new IllegalArgumentException(
					"Unsupported URI: " + domainModelURI); //$NON-NLS-1$
		}
		myFileCreationPage.setContainerFullPath(filePath);
		myFileCreationPage
				.setFileName(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
						.getUniqueFileName(filePath, fileName,
								"masterdataeditorgmf_diagram")); //$NON-NLS-1$

		diagramRootElementSelectionPage = new DiagramRootElementSelectionPage(
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFNewDiagramFileWizard_RootSelectionPageName);
		diagramRootElementSelectionPage
				.setTitle(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFNewDiagramFileWizard_RootSelectionPageTitle);
		diagramRootElementSelectionPage
				.setDescription(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFNewDiagramFileWizard_RootSelectionPageDescription);
		diagramRootElementSelectionPage.setModelElement(diagramRoot);

		myEditingDomain = editingDomain;
	}

	/**
	 * @generated
	 */
	public void addPages() {
		addPage(myFileCreationPage);
		addPage(diagramRootElementSelectionPage);
	}

	/**
	 * @generated
	 */
	public boolean performFinish() {
		List affectedFiles = new LinkedList();
		IPath diagramModelPath = myFileCreationPage.getContainerFullPath()
				.append(myFileCreationPage.getFileName());
		URI diagramModelURI = URI.createFileURI(diagramModelPath.toString());
		ResourceSet resourceSet = myEditingDomain.getResourceSet();
		final Resource diagramResource = resourceSet
				.createResource(diagramModelURI);
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(
				myEditingDomain,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFNewDiagramFileWizard_InitDiagramCommand,
				affectedFiles) {

			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				int diagramVID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
						.getDiagramVisualID(diagramRootElementSelectionPage
								.getModelElement());
				if (diagramVID != org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.VISUAL_ID) {
					return CommandResult
							.newErrorCommandResult(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFNewDiagramFileWizard_IncorrectRootError);
				}
				Diagram diagram = ViewService
						.createDiagram(
								diagramRootElementSelectionPage
										.getModelElement(),
								org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.MODEL_ID,
								org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				diagramResource.getContents().add(diagram);
				return CommandResult.newOKCommandResult();
			}
		};
		try {
			OperationHistoryFactory.getOperationHistory().execute(command,
					new NullProgressMonitor(), null);
			diagramResource
					.save(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
							.getSaveOptions());
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
					.openDiagram(diagramResource);
		} catch (ExecutionException e) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin
					.getInstance().logError(
							"Unable to create model and diagram", e); //$NON-NLS-1$
		} catch (IOException ex) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin
					.getInstance()
					.logError(
							"Save operation failed for: " + diagramModelURI, ex); //$NON-NLS-1$
		} catch (PartInitException ex) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin
					.getInstance().logError("Unable to open editor", ex); //$NON-NLS-1$
		}
		return true;
	}

	/**
	 * @generated
	 */
	private static class DiagramRootElementSelectionPage extends
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.ModelElementSelectionPage {

		/**
		 * @generated
		 */
		protected DiagramRootElementSelectionPage(String pageName) {
			super(pageName);
		}

		/**
		 * @generated
		 */
		protected String getSelectionTitle() {
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFNewDiagramFileWizard_RootSelectionPageSelectionTitle;
		}

		/**
		 * @generated
		 */
		protected boolean validatePage() {
			if (selectedModelElement == null) {
				setErrorMessage(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFNewDiagramFileWizard_RootSelectionPageNoSelectionMessage);
				return false;
			}
			boolean result = ViewService
					.getInstance()
					.provides(
							new CreateDiagramViewOperation(
									new EObjectAdapter(selectedModelElement),
									org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.MODEL_ID,
									org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT));
			setErrorMessage(result ? null
					: org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFNewDiagramFileWizard_RootSelectionPageInvalidSelectionMessage);
			return result;
		}
	}
}
