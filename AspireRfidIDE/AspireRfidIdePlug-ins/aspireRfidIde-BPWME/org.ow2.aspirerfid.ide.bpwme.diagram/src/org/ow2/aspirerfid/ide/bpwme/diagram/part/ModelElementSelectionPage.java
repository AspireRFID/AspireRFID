package org.ow2.aspirerfid.ide.bpwme.diagram.part;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.WorkflowMap;

/**
 * Wizard page that allows to select element from model.
 * @generated
 */
public class ModelElementSelectionPage extends WizardPage {
	/**
	 * @generated
	 */
	protected EObject selectedModelElement;

	/**
	 * @generated
	 */
	private TreeViewer modelViewer;

	/**
	 * @generated
	 */
	public ModelElementSelectionPage(String pageName) {
		super(pageName);
	}

	/**
	 * @generated
	 */
	public EObject getModelElement() {
		return selectedModelElement;
	}

	/**
	 * Modified by yluo
	 */
	public void setModelElement(EObject modelElement) {
		selectedModelElement = modelElement;
		if (modelViewer != null) {
			if (selectedModelElement != null) {
				modelViewer.setInput(selectedModelElement);
				modelViewer.setSelection(new StructuredSelection(
						selectedModelElement));
			} else {
				modelViewer.setInput(null);
			}
			setPageComplete(validatePage());
		}
	}

	/**
	 * Modified by yluo
	 */
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite plate = new Composite(parent, SWT.NONE);
		plate.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		plate.setLayout(layout);
		setControl(plate);

		Label label = new Label(plate, SWT.NONE);
		label.setText(getSelectionTitle());
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		modelViewer = new TreeViewer(plate, SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		layoutData.heightHint = 300;
		layoutData.widthHint = 300;
		modelViewer.getTree().setLayoutData(layoutData);
		modelViewer.setContentProvider(new ModelContentProvider());
		modelViewer.setLabelProvider(new ModelLabelProvider());
		if (selectedModelElement != null) {
			modelViewer.setInput(selectedModelElement);
			modelViewer.setSelection(new StructuredSelection(
					selectedModelElement));
		}
		modelViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						ModelElementSelectionPage.this
								.updateSelection((IStructuredSelection) event
										.getSelection());
					}
				});

		setPageComplete(validatePage());
	}

	/**
	 * Override to provide custom model element description.
	 * @generated
	 */
	protected String getSelectionTitle() {
		return Messages.ModelElementSelectionPageMessage;
	}

	/**
	 * @generated
	 */
	protected void updateSelection(IStructuredSelection selection) {
		selectedModelElement = null;
		if (selection.size() == 1) {
			Object selectedElement = selection.getFirstElement();
			if (selectedElement instanceof IWrapperItemProvider) {
				selectedElement = ((IWrapperItemProvider) selectedElement)
						.getValue();
			}
			if (selectedElement instanceof FeatureMap.Entry) {
				selectedElement = ((FeatureMap.Entry) selectedElement)
						.getValue();
			}
			if (selectedElement instanceof EObject) {
				selectedModelElement = (EObject) selectedElement;
			}
		}
		setPageComplete(validatePage());
	}

	/**
	 * Override to provide specific validation of the selected model element.
	 * @generated
	 */
	protected boolean validatePage() {
		return true;
	}

	private class ModelContentProvider implements ITreeContentProvider{

		@Override
		public Object[] getChildren(Object parentElement) {
			if(parentElement instanceof WorkflowMap) {
				WorkflowMap workflow = (WorkflowMap)parentElement;
				return workflow.getNodes().toArray();
			}else {
				return null;
			}
		}

		@Override
		public Object getParent(Object element) {
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if(element instanceof WorkflowMap) {
				WorkflowMap workflow = (WorkflowMap)element;
				if (workflow.getNodes().size() > 0) {
					return true;
				}
			}
			return false;
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return new Object[] { inputElement };
		}

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {			
		}
		
	}
	
	private class ModelLabelProvider implements ILabelProvider {

		private java.util.List listeners;

		public ModelLabelProvider() {
			listeners = new java.util.ArrayList();
		}
		
		@Override
		public Image getImage(Object element) {
			return null;
		}

		@Override
		public String getText(Object element) {
			if(element instanceof WorkflowMap) {
				return "Workflow Map";
			}else if(element instanceof OLCBProc) {
				return ((OLCBProc)element).getName();
			}
			return null;
		}

		@Override
		public void addListener(ILabelProviderListener listener) {
			listeners.add(listener);
		}

		@Override
		public void dispose() {			
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
			listeners.remove(listener);
		}
		
	}
	
}
