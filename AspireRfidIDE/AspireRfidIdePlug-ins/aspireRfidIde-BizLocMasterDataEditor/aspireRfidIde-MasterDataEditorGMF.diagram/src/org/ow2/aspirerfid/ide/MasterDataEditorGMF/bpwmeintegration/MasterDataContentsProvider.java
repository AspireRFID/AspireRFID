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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.bpwmeintegration;

import java.util.HashMap;

import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class MasterDataContentsProvider {
	
	private static CompanyEditPart companyEditPart = null;
	
	/**
	 * Set the company of the active window
	 */
	private static void setCompany() {
		GraphicalEditPart selectedEditPart = null;
		
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() != 1) {
				return;
			}
			selectedEditPart = (GraphicalEditPart) structuredSelection.getFirstElement();
		}
		
		while (!(selectedEditPart instanceof CompanyEditPart)) {
			selectedEditPart = (GraphicalEditPart) selectedEditPart.getParent();			
		}
		companyEditPart = (CompanyEditPart) selectedEditPart;
		
		MasterDataEditParts.setCompanyPart(companyEditPart);
		MasterDataEditParts.setInitialParts();
	}
	
	/**
	 * Get the Company URI with attributes and values
	 */
	public static HashMap<String, HashMap<String, String>> getCompanyUriAttributesValues() {
		setCompany();
		return MasterDataEditParts.getCompanyUriAttributesValuesBpwme();
	}
	
	/**
	 * Get the Warehouse URIs with attributes and values
	 */
	public static HashMap<String, HashMap<String, String>> getWarehouseUriAttributesValues() {
		setCompany();
		return MasterDataEditParts.getWarehouseUriAttributesValuesBpwme();
	}
	
	/**
	 * Get the ReadPoint URI with attributes and values
	 */
	public static HashMap<String, HashMap<String, String>> getReadPointUriAttributesValues() {
		setCompany();
		return MasterDataEditParts.getReadPointUriAttributesValuesBpwme();
	}
	
	

}
