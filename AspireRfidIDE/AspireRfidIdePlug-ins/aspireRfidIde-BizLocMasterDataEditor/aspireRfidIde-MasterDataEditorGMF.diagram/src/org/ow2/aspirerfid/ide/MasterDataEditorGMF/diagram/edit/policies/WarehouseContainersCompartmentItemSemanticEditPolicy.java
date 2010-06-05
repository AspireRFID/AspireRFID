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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class WarehouseContainersCompartmentItemSemanticEditPolicy
		extends
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.policies.MasterDataEditorGMFBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.ReadPoint_2005 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getAbstractWarehouse_Containers());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.ReadPointCreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Container_2006 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getAbstractWarehouse_Containers());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.ContainerCreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Conveyor_2007 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getAbstractWarehouse_Containers());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.ConveyorCreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Shelf_2008 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getAbstractWarehouse_Containers());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.ShelfCreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Gate_2009 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getAbstractWarehouse_Containers());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.GateCreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.PalletTruck_2010 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getAbstractWarehouse_Containers());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.PalletTruckCreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.PushArm_2011 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getAbstractWarehouse_Containers());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.PushArmCreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.HandHeldReader_2012 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getAbstractWarehouse_Containers());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.HandHeldReaderCreateCommand(
					req));
		}
		return super.getCreateCommand(req);
	}

}
