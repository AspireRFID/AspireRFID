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

package org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.policies.WorkflowMapCanonicalEditPolicy;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.policies.WorkflowMapItemSemanticEditPolicy;
import org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl.FileAction;

/**
 * Catch the edit events and do more
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class WorkflowMapEditPart extends DiagramEditPart {

	/**
	 * @generated
	 */
	public final static String MODEL_ID = "Bpwme"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1000;

	/**
	 * @generated
	 */
	public WorkflowMapEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new WorkflowMapItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new WorkflowMapCanonicalEditPolicy());
	}
	
	@Override
	protected void addChild(EditPart child, int index) {
		//Yongming add
		
		MainControl mc = MainControl.getMainControl();
		if(mc.getFileAction() == FileAction.OpenAction) {
			mc.olcbep = (OLCBProcEditPart) child;
		}else if(mc.getFileAction() == FileAction.Restart) {
			mc.rebuild();
			mc.olcbep = (OLCBProcEditPart) child;
			mc.mapModels();
		}else if(mc.getFileAction() == FileAction.OpenFromAction) {
			mc.olcbep = (OLCBProcEditPart) child;
			mc.mapModels();
		}
		
		super.addChild(child, index);
	}
	
	
	@Override
	protected void handleNotificationEvent(Notification event) {
		super.handleNotificationEvent(event);
		if((event.getEventType() == Notification.ADD) 
				&& (event.getNewValue() instanceof OLCBProcImpl)) {		
			OLCBProcImpl opi = (OLCBProcImpl)event.getNewValue();			
			try {
				MainControl mc = MainControl.getMainControl();
				org.ow2.aspirerfid.commons.apdl.model.OLCBProc olcbp = mc.createOLCBProc();
				olcbp.setId(opi.getId());
				mc.addMap(opi.hashCode(), olcbp);
				MasterDataBuilder.getInstance().setOLCBProc(olcbp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if((event.getEventType() == Notification.REMOVE) 
				&& (event.getOldValue() instanceof OLCBProcImpl)) {
			OLCBProcImpl opi = (OLCBProcImpl)event.getOldValue();
			MainControl mc = MainControl.getMainControl();
			mc.delMap(opi.hashCode());
			MasterDataBuilder.getInstance().setOLCBProc(null);
		}
	}
}
