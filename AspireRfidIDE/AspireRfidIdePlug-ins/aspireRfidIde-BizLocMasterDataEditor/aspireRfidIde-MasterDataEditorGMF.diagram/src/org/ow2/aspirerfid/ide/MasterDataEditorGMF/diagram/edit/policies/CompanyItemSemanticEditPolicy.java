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

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

/**
 * @generated
 */
public class CompanyItemSemanticEditPolicy
		extends
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.policies.MasterDataEditorGMFBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.BizLoc_1001 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getCompany_CompanyWarehouses());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.BizLocCreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Warehouse_1002 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getCompany_CompanyWarehouses());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.WarehouseCreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Room_1003 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getCompany_CompanyWarehouses());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.RoomCreateCommand(
					req));
		}
		if (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers.MasterDataEditorGMFElementTypes.Section_1004 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req
						.setContainmentFeature(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
								.getCompany_CompanyWarehouses());
			}
			return getGEFWrapper(new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.commands.SectionCreateCommand(
					req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}

	}

}
