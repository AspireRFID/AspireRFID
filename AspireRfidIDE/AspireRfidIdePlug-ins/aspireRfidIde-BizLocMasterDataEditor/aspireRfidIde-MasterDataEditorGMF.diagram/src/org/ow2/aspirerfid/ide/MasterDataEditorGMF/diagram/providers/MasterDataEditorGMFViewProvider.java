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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class MasterDataEditorGMFViewProvider extends AbstractViewProvider {

	/**
	 * @generated
	 */
	protected Class getDiagramViewClass(IAdaptable semanticAdapter,
			String diagramKind) {
		EObject semanticElement = getSemanticElement(semanticAdapter);
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.MODEL_ID
				.equals(diagramKind)
				&& org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
						.getDiagramVisualID(semanticElement) != -1) {
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.CompanyViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		if (containerView == null) {
			return null;
		}
		IElementType elementType = getSemanticElementType(semanticAdapter);
		EObject domainElement = getSemanticElement(semanticAdapter);
		int visualID;
		if (semanticHint == null) {
			// Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
			// In this situation there should be NO elementType, visualID will be determined
			// by VisualIDRegistry.getNodeVisualID() for domainElement.
			if (elementType != null || domainElement == null) {
				return null;
			}
			visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getNodeVisualID(containerView, domainElement);
		} else {
			visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getVisualID(semanticHint);
			if (elementType != null) {
				// Semantic hint is specified together with element type.
				// Both parameters should describe exactly the same diagram element.
				// In addition we check that visualID returned by VisualIDRegistry.getNodeVisualID() for
				// domainElement (if specified) is the same as in element type.
				if (!org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
						.isKnownElementType(elementType)
						|| (!(elementType instanceof IHintedType))) {
					return null; // foreign element type
				}
				String elementTypeHint = ((IHintedType) elementType)
						.getSemanticHint();
				if (!semanticHint.equals(elementTypeHint)) {
					return null; // if semantic hint is specified it should be the same as in element type
				}
				if (domainElement != null
						&& visualID != org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
								.getNodeVisualID(containerView, domainElement)) {
					return null; // visual id for node EClass should match visual id from element type
				}
			} else {
				// Element type is not specified. Domain element should be present (except pure design elements).
				// This method is called with EObjectAdapter as parameter from:
				//   - ViewService.createNode(View container, EObject eObject, String type, PreferencesHint preferencesHint) 
				//   - generated ViewFactory.decorateView() for parent element
				if (!org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart.MODEL_ID
						.equals(org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
								.getModelID(containerView))) {
					return null; // foreign diagram
				}
				switch (visualID) {
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID:
					if (domainElement == null
							|| visualID != org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
									.getNodeVisualID(containerView,
											domainElement)) {
						return null; // visual id in semantic hint should match visual id for domain element
					}
					break;
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocNameEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartmentEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartmentEditPart.VISUAL_ID:
					if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocEditPart.VISUAL_ID != org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseNameEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartment2EditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartment2EditPart.VISUAL_ID:
					if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseEditPart.VISUAL_ID != org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomNameEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartment2EditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartment2EditPart.VISUAL_ID:
					if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomEditPart.VISUAL_ID != org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionNameEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartment2EditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartment2EditPart.VISUAL_ID:
					if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionEditPart.VISUAL_ID != org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocName2EditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartment2EditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartment2EditPart.VISUAL_ID:
					if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID != org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseName2EditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartmentEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartmentEditPart.VISUAL_ID:
					if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID != org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomName2EditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartmentEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartmentEditPart.VISUAL_ID:
					if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID != org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionName2EditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartmentEditPart.VISUAL_ID:
				case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartmentEditPart.VISUAL_ID:
					if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID != org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				default:
					return null;
				}
			}
		}
		return getNodeViewClass(containerView, visualID);
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(View containerView, int visualID) {
		if (containerView == null
				|| !org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
						.canCreateNode(containerView, visualID)) {
			return null;
		}
		switch (visualID) {
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.BizLocViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocNameEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.BizLocNameViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.WarehouseViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseNameEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.WarehouseNameViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.RoomViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomNameEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.RoomNameViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.SectionViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionNameEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.SectionNameViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.BizLoc2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocName2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.BizLocName2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.Warehouse2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseName2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.WarehouseName2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.Room2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomName2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.RoomName2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.Section2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionName2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.SectionName2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.ReadPointViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.ContainerViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.ConveyorViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.ShelfViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.GateViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.PalletTruckViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.PushArmViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.HandHeldReaderViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartmentEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.BizLocWarehousesCompartmentViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartmentEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.BizLocContainersCompartmentViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocWarehousesCompartment2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.BizLocWarehousesCompartment2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLocContainersCompartment2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.BizLocContainersCompartment2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartmentEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.WarehouseWarehousesCompartmentViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartmentEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.WarehouseContainersCompartmentViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartmentEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.RoomWarehousesCompartmentViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartmentEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.RoomContainersCompartmentViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartmentEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.SectionWarehousesCompartmentViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartmentEditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.SectionContainersCompartmentViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseWarehousesCompartment2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.WarehouseWarehousesCompartment2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.WarehouseContainersCompartment2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.WarehouseContainersCompartment2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomWarehousesCompartment2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.RoomWarehousesCompartment2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.RoomContainersCompartment2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.RoomContainersCompartment2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartment2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.SectionWarehousesCompartment2ViewFactory.class;
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartment2EditPart.VISUAL_ID:
			return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.view.factories.SectionContainersCompartment2ViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		IElementType elementType = getSemanticElementType(semanticAdapter);
		if (!org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes
				.isKnownElementType(elementType)
				|| (!(elementType instanceof IHintedType))) {
			return null; // foreign element type
		}
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		if (elementTypeHint == null) {
			return null; // our hint is visual id and must be specified
		}
		if (semanticHint != null && !semanticHint.equals(elementTypeHint)) {
			return null; // if semantic hint is specified it should be the same as in element type
		}
		int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
				.getVisualID(elementTypeHint);
		EObject domainElement = getSemanticElement(semanticAdapter);
		if (domainElement != null
				&& visualID != org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
						.getLinkWithClassVisualID(domainElement)) {
			return null; // visual id for link EClass should match visual id from element type
		}
		return getEdgeViewClass(visualID);
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(int visualID) {
		switch (visualID) {
		}
		return null;
	}

	/**
	 * @generated
	 */
	private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
		if (semanticAdapter == null) {
			return null;
		}
		return (IElementType) semanticAdapter.getAdapter(IElementType.class);
	}
}
