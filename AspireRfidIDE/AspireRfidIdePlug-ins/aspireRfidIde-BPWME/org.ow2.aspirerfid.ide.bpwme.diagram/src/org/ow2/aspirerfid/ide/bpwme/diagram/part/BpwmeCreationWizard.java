package org.ow2.aspirerfid.ide.bpwme.diagram.part;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredCreateConnectionViewAndElementCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.ow2.aspirerfid.ide.bpwme.BpwmeFactory;
import org.ow2.aspirerfid.ide.bpwme.BpwmePackage;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.WorkflowMap;
import org.ow2.aspirerfid.ide.bpwme.diagram.application.WizardNewProjectCreationPage;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.OLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.WorkflowMapEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.providers.BpwmeElementTypes;
import org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.impl.WorkflowMapImpl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

/**
 * @generated
 */
public class BpwmeCreationWizard extends Wizard implements INewWizard {

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
//	protected BpwmeCreationWizardPage diagramModelFilePage;

	protected WizardNewProjectCreationPage newProjectPage;
	
	/**
	 * @generated
	 */
	//protected BpwmeCreationWizardPage domainModelFilePage;

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
		setWindowTitle(Messages.BpwmeCreationWizardTitle);
		setDefaultPageImageDescriptor(BpwmeDiagramEditorPlugin
				.getBundledImageDescriptor("icons/wizban/NewBpwmeWizard.gif")); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}

	/**
	 * @generated
	 */
	public void addPages() {
		newProjectPage = new WizardNewProjectCreationPage("DiagramModelFile");
		newProjectPage.setTitle("Create new project");
		newProjectPage.setDescription("Create a new bpwme project");
		addPage(newProjectPage);		
		
//		diagramModelFilePage = new BpwmeCreationWizardPage(
//				"DiagramModelFile", getSelection(), "bpwme_diagram"); //$NON-NLS-1$ //$NON-NLS-2$
//		diagramModelFilePage
//				.setTitle(Messages.BpwmeCreationWizard_DiagramModelFilePageTitle);
//		diagramModelFilePage
//				.setDescription(Messages.BpwmeCreationWizard_DiagramModelFilePageDescription);
//		addPage(diagramModelFilePage);
	}

	public boolean performFinish() {
		IRunnableWithProgress op = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				diagram = BpwmeDiagramEditorUtil.createDiagram(newProjectPage.getURI(),monitor);
				
//				diagram = BpwmeDiagramEditorUtil.createDiagram(
//						diagramModelFilePage.getURI(),monitor);
				
				if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
					try {
						//BpwmeDiagramEditorUtil.openDiagram(diagram);
						BpwmeDiagramEditorUtil.openMultipageDiagram(diagram);
						
						BpwmeDiagramEditor beditor = MainUtil.getBPWMEEditor();
						if(beditor != null) {
							//mainly from http://wiki.eclipse.org/index.php/GMF_Tips
							//notice modifications are done via commands
							
							WorkflowMapEditPart wPart = (WorkflowMapEditPart)beditor.getDiagramEditPart();
							WorkflowMapImpl wmp = (WorkflowMapImpl)((View)wPart.getModel()).getElement();
							//prepare the command
							IElementType type = BpwmeElementTypes.OLCBProc_2001;
							CreateViewAndElementRequest req = 
								(CreateViewAndElementRequest)CreateViewRequestFactory.getCreateShapeRequest(type, wPart.getDiagramPreferencesHint());
							
							req.setLocation(new Point(50,50));
							CompoundCommand cmd = new CompoundCommand("Create New OLCB Proc");
							cmd.add(wPart.getCommand(req));
							//execute the command
							wPart.getDiagramEditDomain().getDiagramCommandStack().execute(cmd);
							final EditPartViewer wViewer = wPart.getViewer();
							
							if(wPart.getChildren().size() > 0) {
								final OLCBProcEditPart olcbPart = (OLCBProcEditPart)wPart.getChildren().get(0);
								final OLCBProcImpl olcbi = (OLCBProcImpl)((View)olcbPart.getModel()).getElement();
								SetRequest reqSet = new SetRequest(olcbPart.getEditingDomain(),
										olcbi, BpwmePackage.eINSTANCE.getOLCBProc_Name(),
										newProjectPage.getProjectName());
								SetValueCommand operation = new SetValueCommand(reqSet);
								olcbPart.getDiagramEditDomain().getDiagramCommandStack().execute(new 
										ICommandProxy(operation));
							}
						}
						
//						GMFResource gr = (GMFResource)diagram;
//						EList<EObject> objects = gr.getContents();
//						
//						for(EObject object : objects) {
//							//if(object instanceof WorkflowMapEditPart) {
//								System.out.println(object);								
//							//}
//						}

						
					} catch (PartInitException e) {
						ErrorDialog.openError(getContainer().getShell(),
								Messages.BpwmeCreationWizardOpenEditorError,
								null, e.getStatus());
					}
				}
			}
		};
		try {
			getContainer().run(false, true, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			if (e.getTargetException() instanceof CoreException) {
				ErrorDialog.openError(getContainer().getShell(),
						Messages.BpwmeCreationWizardCreationError, null,
						((CoreException) e.getTargetException()).getStatus());
			} else {
				BpwmeDiagramEditorPlugin.getInstance().logError(
						"Error creating diagram", e.getTargetException()); //$NON-NLS-1$
			}
			return false;
		}
		return diagram != null;
	}
}
