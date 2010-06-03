package org.ow2.aspirerfid.ide.bpwme.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.ow2.aspirerfid.ide.bpwme.BpwmePackage;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.Connection;
import org.ow2.aspirerfid.ide.bpwme.EBProc;
import org.ow2.aspirerfid.ide.bpwme.Node;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.WorkflowMap;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcCLCBCompartmentEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.ConnectionEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.EBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.OLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.OLCBProcOLCBCompartmentEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.WorkflowMapEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.providers.BpwmeElementTypes;


/**
 * @generated
 */
public class BpwmeDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (BpwmeVisualIDRegistry.getVisualID(view)) {
		case OLCBProcOLCBCompartmentEditPart.VISUAL_ID:
			return getOLCBProcOLCBCompartment_7001SemanticChildren(view);
		case CLCBProcCLCBCompartmentEditPart.VISUAL_ID:
			return getCLCBProcCLCBCompartment_7002SemanticChildren(view);
		case WorkflowMapEditPart.VISUAL_ID:
			return getWorkflowMap_1000SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOLCBProcOLCBCompartment_7001SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		OLCBProc modelElement = (OLCBProc) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getCLCBProc().iterator(); it.hasNext();) {
			CLCBProc childElement = (CLCBProc) it.next();
			int visualID = BpwmeVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == CLCBProcEditPart.VISUAL_ID) {
				result.add(new BpwmeNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getCLCBProcCLCBCompartment_7002SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		CLCBProc modelElement = (CLCBProc) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getEBProc().iterator(); it.hasNext();) {
			EBProc childElement = (EBProc) it.next();
			int visualID = BpwmeVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == EBProcEditPart.VISUAL_ID) {
				result.add(new BpwmeNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getWorkflowMap_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		WorkflowMap modelElement = (WorkflowMap) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getNodes().iterator(); it.hasNext();) {
			Node childElement = (Node) it.next();
			int visualID = BpwmeVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == OLCBProcEditPart.VISUAL_ID) {
				result.add(new BpwmeNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (BpwmeVisualIDRegistry.getVisualID(view)) {
		case WorkflowMapEditPart.VISUAL_ID:
			return getWorkflowMap_1000ContainedLinks(view);
		case OLCBProcEditPart.VISUAL_ID:
			return getOLCBProc_2001ContainedLinks(view);
		case CLCBProcEditPart.VISUAL_ID:
			return getCLCBProc_3001ContainedLinks(view);
		case EBProcEditPart.VISUAL_ID:
			return getEBProc_3002ContainedLinks(view);
		case ConnectionEditPart.VISUAL_ID:
			return getConnection_4001ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (BpwmeVisualIDRegistry.getVisualID(view)) {
		case OLCBProcEditPart.VISUAL_ID:
			return getOLCBProc_2001IncomingLinks(view);
		case CLCBProcEditPart.VISUAL_ID:
			return getCLCBProc_3001IncomingLinks(view);
		case EBProcEditPart.VISUAL_ID:
			return getEBProc_3002IncomingLinks(view);
		case ConnectionEditPart.VISUAL_ID:
			return getConnection_4001IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (BpwmeVisualIDRegistry.getVisualID(view)) {
		case OLCBProcEditPart.VISUAL_ID:
			return getOLCBProc_2001OutgoingLinks(view);
		case CLCBProcEditPart.VISUAL_ID:
			return getCLCBProc_3001OutgoingLinks(view);
		case EBProcEditPart.VISUAL_ID:
			return getEBProc_3002OutgoingLinks(view);
		case ConnectionEditPart.VISUAL_ID:
			return getConnection_4001OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getWorkflowMap_1000ContainedLinks(View view) {
		WorkflowMap modelElement = (WorkflowMap) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Connection_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getOLCBProc_2001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getCLCBProc_3001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getEBProc_3002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getConnection_4001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOLCBProc_2001IncomingLinks(View view) {
		OLCBProc modelElement = (OLCBProc) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Connection_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getCLCBProc_3001IncomingLinks(View view) {
		CLCBProc modelElement = (CLCBProc) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Connection_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEBProc_3002IncomingLinks(View view) {
		EBProc modelElement = (EBProc) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Connection_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getConnection_4001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOLCBProc_2001OutgoingLinks(View view) {
		OLCBProc modelElement = (OLCBProc) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_Connection_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getCLCBProc_3001OutgoingLinks(View view) {
		CLCBProc modelElement = (CLCBProc) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_Connection_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEBProc_3002OutgoingLinks(View view) {
		EBProc modelElement = (EBProc) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_Connection_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getConnection_4001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Connection_4001(
			WorkflowMap container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Connection) {
				continue;
			}
			Connection link = (Connection) linkObject;
			if (ConnectionEditPart.VISUAL_ID != BpwmeVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getTargetNode();
			Node src = link.getSourceNode();
			result.add(new BpwmeLinkDescriptor(src, dst, link,
					BpwmeElementTypes.Connection_4001,
					ConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Connection_4001(
			Node target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != BpwmePackage.eINSTANCE
					.getConnection_TargetNode()
					|| false == setting.getEObject() instanceof Connection) {
				continue;
			}
			Connection link = (Connection) setting.getEObject();
			if (ConnectionEditPart.VISUAL_ID != BpwmeVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getSourceNode();
			result.add(new BpwmeLinkDescriptor(src, target, link,
					BpwmeElementTypes.Connection_4001,
					ConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Connection_4001(
			Node source) {
		WorkflowMap container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof WorkflowMap) {
				container = (WorkflowMap) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Connection) {
				continue;
			}
			Connection link = (Connection) linkObject;
			if (ConnectionEditPart.VISUAL_ID != BpwmeVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getTargetNode();
			Node src = link.getSourceNode();
			if (src != source) {
				continue;
			}
			result.add(new BpwmeLinkDescriptor(src, dst, link,
					BpwmeElementTypes.Connection_4001,
					ConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

}
