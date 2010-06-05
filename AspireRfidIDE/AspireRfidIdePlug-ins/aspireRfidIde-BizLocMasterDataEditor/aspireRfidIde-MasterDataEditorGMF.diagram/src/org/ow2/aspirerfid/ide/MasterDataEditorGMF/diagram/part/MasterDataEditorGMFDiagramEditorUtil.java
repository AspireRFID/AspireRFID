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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFFactory;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataGMFQuery;

/**
 * @generated
 */
public class MasterDataEditorGMFDiagramEditorUtil {

	/**
	 * @generated
	 */
	public static Map getSaveOptions() {
		Map saveOptions = new HashMap();
		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
				Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		return saveOptions;
	}

	/**
	 * @generated
	 */
	public static boolean openDiagram(Resource diagram)
			throws PartInitException {
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		page
				.openEditor(
						new URIEditorInput(diagram.getURI()),
						org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditor.ID);
		return true;
	}

	/**
	 * @generated
	 */
	public static String getUniqueFileName(IPath containerFullPath,
			String fileName, String extension) {
		if (containerFullPath == null) {
			containerFullPath = new Path(""); //$NON-NLS-1$
		}
		if (fileName == null || fileName.trim().length() == 0) {
			fileName = "default"; //$NON-NLS-1$
		}
		IPath filePath = containerFullPath.append(fileName);
		if (extension != null && !extension.equals(filePath.getFileExtension())) {
			filePath = filePath.addFileExtension(extension);
		}
		extension = filePath.getFileExtension();
		fileName = filePath.removeFileExtension().lastSegment();
		int i = 1;
		while (filePath.toFile().exists()) {
			i++;
			filePath = containerFullPath.append(fileName + i);
			if (extension != null) {
				filePath = filePath.addFileExtension(extension);
			}
		}
		return filePath.lastSegment();
	}

	/**
	 * Allows user to select file and loads it as a model.
	 * 
	 * @generated
	 */
	public static Resource openModel(Shell shell, String description,
			TransactionalEditingDomain editingDomain) {
		FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
		if (description != null) {
			fileDialog.setText(description);
		}
		fileDialog.open();
		String fileName = fileDialog.getFileName();
		if (fileName == null || fileName.length() == 0) {
			return null;
		}
		if (fileDialog.getFilterPath() != null) {
			fileName = fileDialog.getFilterPath() + File.separator + fileName;
		}
		URI uri = URI.createFileURI(fileName);
		Resource resource = null;
		try {
			resource = editingDomain.getResourceSet().getResource(uri, true);
		} catch (WrappedException we) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin
					.getInstance().logError(
							"Unable to load resource: " + uri, we); //$NON-NLS-1$
			MessageDialog
					.openError(
							shell,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFDiagramEditorUtil_OpenModelResourceErrorDialogTitle,
							NLS
									.bind(
											org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFDiagramEditorUtil_OpenModelResourceErrorDialogMessage,
											fileName));
		}
		return resource;
	}

	/**
	 * Runs the wizard in a dialog.
	 * 
	 * @generated
	 */
	public static void runWizard(Shell shell, Wizard wizard, String settingsKey) {
		IDialogSettings pluginDialogSettings = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin
				.getInstance().getDialogSettings();
		IDialogSettings wizardDialogSettings = pluginDialogSettings
				.getSection(settingsKey);
		if (wizardDialogSettings == null) {
			wizardDialogSettings = pluginDialogSettings
					.addNewSection(settingsKey);
		}
		wizard.setDialogSettings(wizardDialogSettings);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.create();
		dialog.getShell().setSize(Math.max(500, dialog.getShell().getSize().x),
				500);
		dialog.open();
	}

	/**
	 * @generated
	 */
	public static Resource createDiagram(URI diagramURI,
			IProgressMonitor progressMonitor) {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
				.createEditingDomain();
		progressMonitor
				.beginTask(
						org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFDiagramEditorUtil_CreateDiagramProgressTask,
						3);
		final Resource diagramResource = editingDomain.getResourceSet()
				.createResource(diagramURI);
		final String diagramName = diagramURI.lastSegment();
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(
				editingDomain,
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.Messages.MasterDataEditorGMFDiagramEditorUtil_CreateDiagramCommandLabel,
				Collections.EMPTY_LIST) {
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company model = createInitialModel();
				attachModelToResource(model, diagramResource);

				Diagram diagram = ViewService
						.createDiagram(
								model,
								org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.MODEL_ID,
								org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				if (diagram != null) {
					diagramResource.getContents().add(diagram);
					diagram.setName(diagramName);
					diagram.setElement(model);
				}

				try {

					diagramResource
							.save(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
									.getSaveOptions());
				} catch (IOException e) {

					org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin
							.getInstance()
							.logError(
									"Unable to store model and diagram resources", e); //$NON-NLS-1$
				}
				return CommandResult.newOKCommandResult();
			}
		};
		try {
			OperationHistoryFactory.getOperationHistory().execute(command,
					new SubProgressMonitor(progressMonitor, 1), null);
		} catch (ExecutionException e) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin
					.getInstance().logError(
							"Unable to create model and diagram", e); //$NON-NLS-1$
		}
		MasterDataGMFQuery.setFromEPCIS(false);
		return diagramResource;
	}

	/**
	 * Create a new instance of domain element associated with canvas.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	private static org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company createInitialModel() {
		
		if (MasterDataGMFQuery.isFromEPCIS())
			return MasterDataGMFQuery.createEpcisModel(MasterDataGMFQuery.getSelectedCompany());
		else
			return MasterDataEditorGMFFactory.eINSTANCE.createCompany();
	}

	/**
	 * Store model element in the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static void attachModelToResource(
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company model,
			Resource resource) {
		resource.getContents().add(model);
	}

	/**
	 * @generated
	 */
	public static void selectElementsInDiagram(
			IDiagramWorkbenchPart diagramPart, List/*EditPart*/editParts) {
		diagramPart.getDiagramGraphicalViewer().deselectAll();

		EditPart firstPrimary = null;
		for (Iterator it = editParts.iterator(); it.hasNext();) {
			EditPart nextPart = (EditPart) it.next();
			diagramPart.getDiagramGraphicalViewer().appendSelection(nextPart);
			if (firstPrimary == null && nextPart instanceof IPrimaryEditPart) {
				firstPrimary = nextPart;
			}
		}

		if (!editParts.isEmpty()) {
			diagramPart.getDiagramGraphicalViewer().reveal(
					firstPrimary != null ? firstPrimary : (EditPart) editParts
							.get(0));
		}
	}

	/**
	 * @generated
	 */
	private static int findElementsInDiagramByID(DiagramEditPart diagramPart,
			EObject element, List editPartCollector) {
		IDiagramGraphicalViewer viewer = (IDiagramGraphicalViewer) diagramPart
				.getViewer();
		final int intialNumOfEditParts = editPartCollector.size();

		if (element instanceof View) { // support notation element lookup
			EditPart editPart = (EditPart) viewer.getEditPartRegistry().get(
					element);
			if (editPart != null) {
				editPartCollector.add(editPart);
				return 1;
			}
		}

		String elementID = EMFCoreUtil.getProxyID(element);
		List associatedParts = viewer.findEditPartsForElement(elementID,
				IGraphicalEditPart.class);
		// perform the possible hierarchy disjoint -> take the top-most parts only
		for (Iterator editPartIt = associatedParts.iterator(); editPartIt
				.hasNext();) {
			EditPart nextPart = (EditPart) editPartIt.next();
			EditPart parentPart = nextPart.getParent();
			while (parentPart != null && !associatedParts.contains(parentPart)) {
				parentPart = parentPart.getParent();
			}
			if (parentPart == null) {
				editPartCollector.add(nextPart);
			}
		}

		if (intialNumOfEditParts == editPartCollector.size()) {
			if (!associatedParts.isEmpty()) {
				editPartCollector.add(associatedParts.iterator().next());
			} else {
				if (element.eContainer() != null) {
					return findElementsInDiagramByID(diagramPart, element
							.eContainer(), editPartCollector);
				}
			}
		}
		return editPartCollector.size() - intialNumOfEditParts;
	}

	/**
	 * @generated
	 */
	public static View findView(DiagramEditPart diagramEditPart,
			EObject targetElement, LazyElement2ViewMap lazyElement2ViewMap) {
		boolean hasStructuralURI = false;
		if (targetElement.eResource() instanceof XMLResource) {
			hasStructuralURI = ((XMLResource) targetElement.eResource())
					.getID(targetElement) == null;
		}

		View view = null;
		if (hasStructuralURI
				&& !lazyElement2ViewMap.getElement2ViewMap().isEmpty()) {
			view = (View) lazyElement2ViewMap.getElement2ViewMap().get(
					targetElement);
		} else if (findElementsInDiagramByID(diagramEditPart, targetElement,
				lazyElement2ViewMap.editPartTmpHolder) > 0) {
			EditPart editPart = (EditPart) lazyElement2ViewMap.editPartTmpHolder
					.get(0);
			lazyElement2ViewMap.editPartTmpHolder.clear();
			view = editPart.getModel() instanceof View ? (View) editPart
					.getModel() : null;
		}

		return (view == null) ? diagramEditPart.getDiagramView() : view;
	}

	/**
	 * @generated
	 */
	public static class LazyElement2ViewMap {
		/**
		 * @generated
		 */
		private Map element2ViewMap;

		/**
		 * @generated
		 */
		private View scope;

		/**
		 * @generated
		 */
		private Set elementSet;

		/**
		 * @generated
		 */
		public final List editPartTmpHolder = new ArrayList();

		/**
		 * @generated
		 */
		public LazyElement2ViewMap(View scope, Set elements) {
			this.scope = scope;
			this.elementSet = elements;
		}

		/**
		 * @generated
		 */
		public final Map getElement2ViewMap() {
			if (element2ViewMap == null) {
				element2ViewMap = new HashMap();
				// map possible notation elements to itself as these can't be found by view.getElement()
				for (Iterator it = elementSet.iterator(); it.hasNext();) {
					EObject element = (EObject) it.next();
					if (element instanceof View) {
						View view = (View) element;
						if (view.getDiagram() == scope.getDiagram()) {
							element2ViewMap.put(element, element); // take only those that part of our diagram
						}
					}
				}

				buildElement2ViewMap(scope, element2ViewMap, elementSet);
			}
			return element2ViewMap;
		}

		/**
		 * @generated
		 */
		static Map buildElement2ViewMap(View parentView, Map element2ViewMap,
				Set elements) {
			if (elements.size() == element2ViewMap.size())
				return element2ViewMap;

			if (parentView.isSetElement()
					&& !element2ViewMap.containsKey(parentView.getElement())
					&& elements.contains(parentView.getElement())) {
				element2ViewMap.put(parentView.getElement(), parentView);
				if (elements.size() == element2ViewMap.size())
					return element2ViewMap;
			}

			for (Iterator it = parentView.getChildren().iterator(); it
					.hasNext();) {
				buildElement2ViewMap((View) it.next(), element2ViewMap,
						elements);
				if (elements.size() == element2ViewMap.size())
					return element2ViewMap;
			}
			for (Iterator it = parentView.getSourceEdges().iterator(); it
					.hasNext();) {
				buildElement2ViewMap((View) it.next(), element2ViewMap,
						elements);
				if (elements.size() == element2ViewMap.size())
					return element2ViewMap;
			}
			for (Iterator it = parentView.getSourceEdges().iterator(); it
					.hasNext();) {
				buildElement2ViewMap((View) it.next(), element2ViewMap,
						elements);
				if (elements.size() == element2ViewMap.size())
					return element2ViewMap;
			}
			return element2ViewMap;
		}
	} //LazyElement2ViewMap	

}
