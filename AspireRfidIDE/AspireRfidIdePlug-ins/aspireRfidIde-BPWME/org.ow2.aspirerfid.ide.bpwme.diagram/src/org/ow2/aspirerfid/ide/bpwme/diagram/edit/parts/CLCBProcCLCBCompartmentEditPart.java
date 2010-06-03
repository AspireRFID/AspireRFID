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



import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.commons.epcis.model.EPCISMasterDataDocumentType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.policies.CLCBProcCLCBCompartmentCanonicalEditPolicy;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.policies.CLCBProcCLCBCompartmentItemSemanticEditPolicy;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.Messages;
import org.ow2.aspirerfid.ide.bpwme.dialog.ReportTypeDialog;
import org.ow2.aspirerfid.ide.bpwme.impl.CLCBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.impl.EBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl.EventType;

/**
 * Catch edit event and do more
 * @generated
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class CLCBProcCLCBCompartmentEditPart extends ShapeCompartmentEditPart {

	/**
	 * 
	 */
	public static final int VISUAL_ID = 7002;

	/**
	 * @generated
	 */
	public CLCBProcCLCBCompartmentEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	public String getCompartmentName() {
		return Messages.CLCBProcCLCBCompartmentEditPart_title;
	}

	/**
	 * @generated
	 */
	public IFigure createFigure() {
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super
				.createFigure();
		result.setTitleVisibility(false);
		return result;
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
				new ResizableCompartmentEditPolicy());
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new CLCBProcCLCBCompartmentItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new CLCBProcCLCBCompartmentCanonicalEditPolicy());
	}

	/**
	 * @generated
	 */
	protected void setRatio(Double ratio) {
		if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
			super.setRatio(ratio);
		}
	}
	/*
	 * To synchronize the in-editor model and diagram with the APDL file
	 * Handling add, remove action to the included node (EBProc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart#handleNotificationEvent(org.eclipse.emf.common.notify.Notification)
	 */
	protected void handleNotificationEvent(Notification event) {
		super.handleNotificationEvent(event);
		
		if((event.getOldValue() instanceof EBProcImpl) 
				&& (event.getEventType() == Notification.REMOVE)) {
			
			CLCBProcImpl cpi = (CLCBProcImpl)event.getNotifier();
			EBProcImpl epi = (EBProcImpl)event.getOldValue();
			
			MainControl mc = MainControl.getMainControl();
			org.ow2.aspirerfid.commons.apdl.model.CLCBProc clcb =
				(org.ow2.aspirerfid.commons.apdl.model.CLCBProc)mc.getMapObject(cpi.hashCode());
			org.ow2.aspirerfid.commons.apdl.model.EBProc ebproc = 
				(org.ow2.aspirerfid.commons.apdl.model.EBProc)mc.getMapObject(epi.hashCode());
			clcb.getEBProc().remove(ebproc);
			mc.saveObject();
			mc.delMap(epi.hashCode());
			
		}else if ((event.getNewValue() instanceof EBProcImpl) 
				&&(event.getEventType() == Notification.ADD)) {
			CLCBProcImpl cpi = (CLCBProcImpl)event.getNotifier();
			EBProcImpl epi = (EBProcImpl)event.getNewValue();
			
			ReportTypeDialog rtd = new ReportTypeDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell());
			rtd.setText("Select Event Type");
			EventType result = rtd.open();
			
			
			MainControl mc = MainControl.getMainControl();
			org.ow2.aspirerfid.commons.apdl.model.CLCBProc clcb = 
				(org.ow2.aspirerfid.commons.apdl.model.CLCBProc) mc.getMapObject(cpi.hashCode());
			org.ow2.aspirerfid.commons.apdl.model.EBProc ebproc = mc.createEBProc(clcb);
			
			EPCISMasterDataDocumentType doc = MasterDataUtil.getEPCISMasterDataDocument(ebproc);
			VocabularyElementType vocabularyElement = MasterDataUtil.getEBProcVocabularyElement(doc);
			//mc.setAttribute(vocabularyElement, "urn:epcglobal:epcis:mda:event_name", ebproc.getName());
			MasterDataUtil.setVocabularyElementAttribute(vocabularyElement, "urn:epcglobal:epcis:mda:event_type", result.toString());
			
			ebproc.setId(epi.getId());
			MasterDataUtil.setVocabularyElementID(vocabularyElement, epi.getId());
			
			mc.addMap(epi.hashCode(), ebproc);
					}	
	}
}
