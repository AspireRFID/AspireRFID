package org.ow2.aspirerfid.ide.bpwme.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.ow2.aspirerfid.ide.bpwme.BpwmePackage;
import org.ow2.aspirerfid.ide.bpwme.WorkflowMap;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcCLCBCompartmentEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcDescriptionEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.ConnectionEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.EBProcDescriptionEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.EBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.OLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.OLCBProcNameEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.OLCBProcOLCBCompartmentEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.WorkflowMapEditPart;


/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class BpwmeVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.ow2.aspirerfid.ide.bpwme.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (WorkflowMapEditPart.MODEL_ID.equals(view.getType())) {
				return WorkflowMapEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeVisualIDRegistry.getVisualID(view
				.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				BpwmeDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (BpwmePackage.eINSTANCE.getWorkflowMap().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((WorkflowMap) domainElement)) {
			return WorkflowMapEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeVisualIDRegistry
				.getModelID(containerView);
		if (!WorkflowMapEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (WorkflowMapEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = WorkflowMapEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case OLCBProcOLCBCompartmentEditPart.VISUAL_ID:
			if (BpwmePackage.eINSTANCE.getCLCBProc().isSuperTypeOf(
					domainElement.eClass())) {
				return CLCBProcEditPart.VISUAL_ID;
			}
			break;
		case CLCBProcCLCBCompartmentEditPart.VISUAL_ID:
			if (BpwmePackage.eINSTANCE.getEBProc().isSuperTypeOf(
					domainElement.eClass())) {
				return EBProcEditPart.VISUAL_ID;
			}
			break;
		case WorkflowMapEditPart.VISUAL_ID:
			if (BpwmePackage.eINSTANCE.getOLCBProc().isSuperTypeOf(
					domainElement.eClass())) {
				return OLCBProcEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeVisualIDRegistry
				.getModelID(containerView);
		if (!WorkflowMapEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (WorkflowMapEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = WorkflowMapEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case OLCBProcEditPart.VISUAL_ID:
			if (OLCBProcNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OLCBProcOLCBCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CLCBProcEditPart.VISUAL_ID:
			if (CLCBProcDescriptionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CLCBProcCLCBCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EBProcEditPart.VISUAL_ID:
			if (EBProcDescriptionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case OLCBProcOLCBCompartmentEditPart.VISUAL_ID:
			if (CLCBProcEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CLCBProcCLCBCompartmentEditPart.VISUAL_ID:
			if (EBProcEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case WorkflowMapEditPart.VISUAL_ID:
			if (OLCBProcEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (BpwmePackage.eINSTANCE.getConnection().isSuperTypeOf(
				domainElement.eClass())) {
			return ConnectionEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(WorkflowMap element) {
		return true;
	}

}
