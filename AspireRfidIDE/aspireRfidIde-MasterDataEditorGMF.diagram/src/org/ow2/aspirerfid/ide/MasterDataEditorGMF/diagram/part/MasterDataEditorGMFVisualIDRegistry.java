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

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class MasterDataEditorGMFVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "aspireRfidIde-MasterDataEditorGMF.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.MODEL_ID
					.equals(view.getType())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
				.getVisualID(view.getType());
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
				org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin
						.getInstance().logError(
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
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
				.getCompany().isSuperTypeOf(domainElement.eClass())
				&& isDiagram((org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company) domainElement)) {
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.VISUAL_ID;
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
		String containerModelID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
				.getModelID(containerView);
		if (!org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.MODEL_ID
				.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.MODEL_ID
				.equals(containerModelID)) {
			containerVisualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getBizLoc().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getWarehouse().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getRoom().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getSection().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getReadPoint().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getContainer().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getConveyor().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getShelf().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getGate().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPalletTruck().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPushArm().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getHandHeldReader().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getBizLoc().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getWarehouse().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getRoom().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getSection().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getReadPoint().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getContainer().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getConveyor().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getShelf().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getGate().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPalletTruck().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPushArm().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getHandHeldReader().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getBizLoc().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getWarehouse().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getRoom().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getSection().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getReadPoint().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getContainer().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getConveyor().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getShelf().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getGate().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPalletTruck().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPushArm().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getHandHeldReader().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getBizLoc().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getWarehouse().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getRoom().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getSection().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getReadPoint().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getContainer().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getConveyor().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getShelf().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getGate().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPalletTruck().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPushArm().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getHandHeldReader().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getBizLoc().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getWarehouse().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getRoom().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getSection().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getReadPoint().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getContainer().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getConveyor().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getShelf().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getGate().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPalletTruck().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPushArm().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getHandHeldReader().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getBizLoc().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getWarehouse().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getRoom().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getSection().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getReadPoint().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getContainer().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getConveyor().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getShelf().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getGate().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPalletTruck().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPushArm().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getHandHeldReader().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getBizLoc().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getWarehouse().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getRoom().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getSection().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getReadPoint().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getContainer().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getConveyor().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getShelf().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getGate().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPalletTruck().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPushArm().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getHandHeldReader().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getBizLoc().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getWarehouse().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getRoom().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getSection().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getReadPoint().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getContainer().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getConveyor().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getShelf().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getGate().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPalletTruck().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getPushArm().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getHandHeldReader().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getBizLoc().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getWarehouse().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getRoom().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomEditPart.VISUAL_ID;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
					.getSection().isSuperTypeOf(domainElement.eClass())) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
				.getModelID(containerView);
		if (!org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.MODEL_ID
				.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.MODEL_ID
				.equals(containerModelID)) {
			containerVisualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartmentEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartment2EditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.VISUAL_ID:
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionEditPart.VISUAL_ID == nodeVisualID) {
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
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company element) {
		return true;
	}

}
