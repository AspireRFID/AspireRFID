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

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class SectionItemSemanticEditPolicy
		extends
		org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.policies.MasterDataEditorGMFBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand cc = getDestroyEdgesCommand();
		addDestroyChildNodesCommand(cc);
		addDestroyShortcutsCommand(cc);
		View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			req.setElementToDestroy(view);
		}
		cc.add(getGEFWrapper(new DestroyElementCommand(req)));
		return cc.unwrap();
	}

	/**
	 * @generated
	 */
	protected void addDestroyChildNodesCommand(CompoundCommand cmd) {
		View view = (View) getHost().getModel();
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation != null) {
			return;
		}
		for (Iterator it = view.getChildren().iterator(); it.hasNext();) {
			Node node = (Node) it.next();
			switch (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
					.getVisualID(node)) {
			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionWarehousesCompartment2EditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
							.getVisualID(cnode)) {
					case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.BizLoc2EditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Warehouse2EditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Room2EditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.Section2EditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					}
				}
				break;
			case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.SectionContainersCompartment2EditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry
							.getVisualID(cnode)) {
					case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ReadPointEditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ContainerEditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ConveyorEditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.ShelfEditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.GateEditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PalletTruckEditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.PushArmEditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					case org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.HandHeldReaderEditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					}
				}
				break;
			}
		}
	}

}
