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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class RoomWarehousesCompartment2CanonicalEditPolicy extends
		CanonicalEditPolicy {

	/**
	 * @generated
	 */
	Set myFeaturesToSynchronize;

	/**
	 * @generated
	 */
	protected List getSemanticChildrenList() {
		View viewObject = (View) getHost().getModel();
		List result = new LinkedList();
		for (Iterator it = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramUpdater
				.getRoomWarehousesCompartment_5013SemanticChildren(viewObject)
				.iterator(); it.hasNext();) {
			result
					.add(((org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFNodeDescriptor) it
							.next()).getModelElement());
		}
		return result;
	}

	/**
	 * @generated
	 */
	protected boolean isOrphaned(Collection semanticChildren, final View view) {
		int visualID = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
				.getVisualID(view);
		switch (visualID) {
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID:
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID:
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID:
		case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID:
			if (!semanticChildren.contains(view.getElement())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

	/**
	 * @generated
	 */
	protected Set getFeaturesToSynchronize() {
		if (myFeaturesToSynchronize == null) {
			myFeaturesToSynchronize = new HashSet();
			myFeaturesToSynchronize
					.add(org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
							.getAbstractWarehouse_Warehouses());
		}
		return myFeaturesToSynchronize;
	}

}
