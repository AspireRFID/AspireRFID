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

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class MasterDataEditorGMFDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
				.getVisualID(view)) {
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartmentEditPart.VISUAL_ID:
			return getBizLocWarehousesCompartment_5001SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartmentEditPart.VISUAL_ID:
			return getBizLocContainersCompartment_5002SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartment2EditPart.VISUAL_ID:
			return getBizLocWarehousesCompartment_5003SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartment2EditPart.VISUAL_ID:
			return getBizLocContainersCompartment_5004SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartmentEditPart.VISUAL_ID:
			return getWarehouseWarehousesCompartment_5005SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartmentEditPart.VISUAL_ID:
			return getWarehouseContainersCompartment_5006SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartmentEditPart.VISUAL_ID:
			return getRoomWarehousesCompartment_5007SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartmentEditPart.VISUAL_ID:
			return getRoomContainersCompartment_5008SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartmentEditPart.VISUAL_ID:
			return getSectionWarehousesCompartment_5009SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartmentEditPart.VISUAL_ID:
			return getSectionContainersCompartment_5010SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartment2EditPart.VISUAL_ID:
			return getWarehouseWarehousesCompartment_5011SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartment2EditPart.VISUAL_ID:
			return getWarehouseContainersCompartment_5012SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartment2EditPart.VISUAL_ID:
			return getRoomWarehousesCompartment_5013SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartment2EditPart.VISUAL_ID:
			return getRoomContainersCompartment_5014SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartment2EditPart.VISUAL_ID:
			return getSectionWarehousesCompartment_5015SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartment2EditPart.VISUAL_ID:
			return getSectionContainersCompartment_5016SemanticChildren(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.VISUAL_ID:
			return getCompany_79SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBizLocWarehousesCompartment_5001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getWarehouses().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBizLocContainersCompartment_5002SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getContainers().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBizLocWarehousesCompartment_5003SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getWarehouses().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBizLocContainersCompartment_5004SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getContainers().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getWarehouseWarehousesCompartment_5005SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getWarehouses().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getWarehouseContainersCompartment_5006SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getContainers().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRoomWarehousesCompartment_5007SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getWarehouses().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRoomContainersCompartment_5008SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getContainers().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSectionWarehousesCompartment_5009SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getWarehouses().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSectionContainersCompartment_5010SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getContainers().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getWarehouseWarehousesCompartment_5011SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getWarehouses().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getWarehouseContainersCompartment_5012SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getContainers().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRoomWarehousesCompartment_5013SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getWarehouses().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRoomContainersCompartment_5014SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getContainers().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSectionWarehousesCompartment_5015SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getWarehouses().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSectionContainersCompartment_5016SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getContainers().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getCompany_79SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company modelElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company) view
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getCompanyWarehouses().iterator(); it
				.hasNext();) {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse childElement = (org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse) it
					.next();
			int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(view, childElement);
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
			if (visualID == org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionEditPart.VISUAL_ID) {
				result
						.add(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor(
								childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
				.getVisualID(view)) {
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.VISUAL_ID:
			return getCompany_79ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocEditPart.VISUAL_ID:
			return getBizLoc_1001ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseEditPart.VISUAL_ID:
			return getWarehouse_1002ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomEditPart.VISUAL_ID:
			return getRoom_1003ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionEditPart.VISUAL_ID:
			return getSection_1004ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID:
			return getBizLoc_2001ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID:
			return getWarehouse_2002ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID:
			return getRoom_2003ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID:
			return getSection_2004ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID:
			return getReadPoint_2005ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID:
			return getContainer_2006ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID:
			return getConveyor_2007ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID:
			return getShelf_2008ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID:
			return getGate_2009ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID:
			return getPalletTruck_2010ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID:
			return getPushArm_2011ContainedLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID:
			return getHandHeldReader_2012ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
				.getVisualID(view)) {
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocEditPart.VISUAL_ID:
			return getBizLoc_1001IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseEditPart.VISUAL_ID:
			return getWarehouse_1002IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomEditPart.VISUAL_ID:
			return getRoom_1003IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionEditPart.VISUAL_ID:
			return getSection_1004IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID:
			return getBizLoc_2001IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID:
			return getWarehouse_2002IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID:
			return getRoom_2003IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID:
			return getSection_2004IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID:
			return getReadPoint_2005IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID:
			return getContainer_2006IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID:
			return getConveyor_2007IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID:
			return getShelf_2008IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID:
			return getGate_2009IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID:
			return getPalletTruck_2010IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID:
			return getPushArm_2011IncomingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID:
			return getHandHeldReader_2012IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
				.getVisualID(view)) {
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocEditPart.VISUAL_ID:
			return getBizLoc_1001OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseEditPart.VISUAL_ID:
			return getWarehouse_1002OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomEditPart.VISUAL_ID:
			return getRoom_1003OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionEditPart.VISUAL_ID:
			return getSection_1004OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID:
			return getBizLoc_2001OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID:
			return getWarehouse_2002OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID:
			return getRoom_2003OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID:
			return getSection_2004OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID:
			return getReadPoint_2005OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID:
			return getContainer_2006OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID:
			return getConveyor_2007OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID:
			return getShelf_2008OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID:
			return getGate_2009OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID:
			return getPalletTruck_2010OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID:
			return getPushArm_2011OutgoingLinks(view);
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID:
			return getHandHeldReader_2012OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getCompany_79ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBizLoc_1001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getWarehouse_1002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRoom_1003ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSection_1004ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBizLoc_2001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getWarehouse_2002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRoom_2003ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSection_2004ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getReadPoint_2005ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getContainer_2006ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getConveyor_2007ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getShelf_2008ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGate_2009ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPalletTruck_2010ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPushArm_2011ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getHandHeldReader_2012ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBizLoc_1001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getWarehouse_1002IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRoom_1003IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSection_1004IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBizLoc_2001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getWarehouse_2002IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRoom_2003IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSection_2004IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getReadPoint_2005IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getContainer_2006IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getConveyor_2007IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getShelf_2008IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGate_2009IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPalletTruck_2010IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPushArm_2011IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getHandHeldReader_2012IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBizLoc_1001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getWarehouse_1002OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRoom_1003OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSection_1004OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBizLoc_2001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getWarehouse_2002OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRoom_2003OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSection_2004OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getReadPoint_2005OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getContainer_2006OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getConveyor_2007OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getShelf_2008OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGate_2009OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPalletTruck_2010OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPushArm_2011OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getHandHeldReader_2012OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

}
