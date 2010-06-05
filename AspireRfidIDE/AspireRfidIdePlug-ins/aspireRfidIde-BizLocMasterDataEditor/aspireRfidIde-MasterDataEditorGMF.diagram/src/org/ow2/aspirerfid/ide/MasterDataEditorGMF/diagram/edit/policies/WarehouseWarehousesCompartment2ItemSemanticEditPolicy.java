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
public class WarehouseWarehousesCompartment2ItemSemanticEditPolicy
		extends
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.policies.MasterDataEditorGMFBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.BizLoc_2001 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getAbstractWarehouse_Warehouses());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.BizLoc2CreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Warehouse_2002 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getAbstractWarehouse_Warehouses());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.Warehouse2CreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Room_2003 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getAbstractWarehouse_Warehouses());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.Room2CreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Section_2004 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getAbstractWarehouse_Warehouses());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.Section2CreateCommand(
					req));
		}
		return super.getCreateCommand(req);
	}

}
