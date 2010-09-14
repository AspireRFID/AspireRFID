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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditor;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFVisualIDRegistry;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;

/**
 * @generated
 */
public class CompanyEditPart extends DiagramEditPart {

	/**
	 * @generated
	 */
	public final static String MODEL_ID = "MasterDataEditorGMF"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 79;

	/**
	 * @generated NOT
	 */
	public CompanyEditPart(View view) {
		super(view);
		MasterDataEditParts.setIsInserted(this, false);
		MasterDataEditParts.setCompanyPart(this);
//		System.out.println("here");
//		//add listener
//		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//		System.out.println(page.getActivePart()); //org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditor@14b8cdc
//		System.out.println(page.getActivePartReference().getPartName()); //file name
//		System.out.println(page.getActivePartReference().getId()); //MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorID
//		
//		for (int i = 0; i < page.getEditorReferences().length; i++)
//			System.out.println(page.getEditorReferences()[i].getPartName());
//		
//		System.out.println("****");
//		
//		page.addPartListener(new IPartListener2(){
//	        public void partActivated( IWorkbenchPartReference partRef ) {
////	        	System.out.println("partRef:" + partRef);
////	        	System.out.println("activated");
//	        }
//	
//	        public void partBroughtToTop( IWorkbenchPartReference partRef ) {
////	        	System.out.println("partRef:" + partRef);
////	        	System.out.println("broughtToTop");
//	        }
//	
//	        public void partClosed( IWorkbenchPartReference partRef ) {
//	        	if (partRef.getId().equals(MasterDataEditorGMFDiagramEditor.ID)) {
//	        		MasterDataEditParts.getCompanyPartRef().remove(partRef);
////		        	System.out.println("partRef:" + partRef);
////		        	System.out.println("closed");
//	        	}
//	        }
//
//	        public void partDeactivated( IWorkbenchPartReference partRef ) {
////	        	System.out.println("partRef:" + partRef);
////	        	System.out.println("deactivated");
//	        }
//	
//	        public void partOpened( IWorkbenchPartReference partRef ) {
//	        	if (partRef.getId().equals(MasterDataEditorGMFDiagramEditor.ID)) {
//	        		MasterDataEditParts.getCompanyPartRef().put(partRef, CompanyEditPart.this);
////		        	System.out.println("partRef:" + partRef);
////		        	System.out.println("opened");
//	        	}
//	        }
//	
//	        public void partHidden( IWorkbenchPartReference partRef ) {
////	        	System.out.println("partRef:" + partRef);
////	        	System.out.println("hidden");
//	        }
//	
//	
//	        public void partVisible( IWorkbenchPartReference partRef ) {
////	        	System.out.println("partRef:" + partRef);
////	        	System.out.println("visible");
//	        }
//	
//	        public void partInputChanged( IWorkbenchPartReference partRef ) {
////	        	System.out.println("partRef:" + partRef);
////	        	System.out.println("inputChanged");
//	        }
//	    });
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.policies.CompanyItemSemanticEditPolicy());
		installEditPolicy(
				EditPolicyRoles.CANONICAL_ROLE,
				new org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.policies.CompanyCanonicalEditPolicy());
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
	}

}
