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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.CompanyEditPart;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class MasterDataContentsProvider {
	
	private static CompanyEditPart companyEditPart = null;
	private ArrayList<EObject> allParts = new ArrayList<EObject>();
	private Company company;
	
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
	
	/**
	 * Set all the parts
	 */
	private void setAllParts() {
		allParts.clear();
		
		//add the warehouse children of the company
		for (int i = 0; i < company.eContents().size(); i++) {
			allParts.add((EObject) company.eContents().get(i));
		}
		
		//add all the other children
		for (int i = 0; i < allParts.size(); i++) {
			for (int j = 0; j < allParts.get(i).eContents().size(); j++) {
				allParts.add((EObject) allParts.get(i).eContents().get(j));
			}
		}
	}
	
	/**
	 * Set the Company model
	 */
	public void setCompany(String fileName) {		
		if(fileName != null) {
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource locationDiagram = resourceSet.createResource(URI.createFileURI(fileName));
			
			try {
				locationDiagram.load(null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for(EObject eobject:locationDiagram.getContents()) {
				if (eobject instanceof Company) {
					company = (Company) eobject;
					break;
				}
			}
		}
		//set all the parts
		setAllParts();
	}
	
	/**
	 * Get the Company model URI with attributes and values
	 */
	public HashMap<String, HashMap<String, String>> getCompanyModelUriAttributesValues() {
		HashMap<String, HashMap<String, String>> companyUriAttributesValuesBpwme = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();

		if (!getCompanyAttributes().isEmpty())
			map = getCompanyAttributes();
		
		companyUriAttributesValuesBpwme.put(company.getID(), MasterDataEditParts.sortByKey(map));
		
		return (MasterDataEditParts.sortByKey(companyUriAttributesValuesBpwme));
	}
	
	/**
	 * Get the Warehouse model URIs with attributes and values
	 */
	public HashMap<String, HashMap<String, String>> getWarehouseModelUriAttributesValuesBpwme() {
		HashMap<String, HashMap<String, String>> warehouseUriAttributesValuesBpwme = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		
		for (int i = 0; i < allParts.size(); i++) {
			map.clear();
			
			if (allParts.get(i) instanceof AbstractWarehouse) {
				map = getWarehouseAttributes((AbstractWarehouse) allParts.get(i));
				AbstractWarehouse currentWarehouse = (AbstractWarehouse) allParts.get(i);				
				warehouseUriAttributesValuesBpwme.put(currentWarehouse.getID(), MasterDataEditParts.sortByKey(map));
			}
		}
		
		return (MasterDataEditParts.sortByKey(warehouseUriAttributesValuesBpwme));
	}
	
	/**
	 * Get the ReadPoint model URIs with attributes and values
	 */
	public HashMap<String, HashMap<String, String>> getReadPointModelUriAttributesValuesBpwme() {
		HashMap<String, HashMap<String, String>> readPointUriAttributesValuesBpwme = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		
		for (int i = 0; i < allParts.size(); i++) {
			map.clear();
			
			if (allParts.get(i) instanceof AbstractContainer) {
				map = getReadPointAttributes((AbstractContainer) allParts.get(i));
				AbstractContainer currentReadPoint = (AbstractContainer) allParts.get(i);				
				readPointUriAttributesValuesBpwme.put(currentReadPoint.getID(), MasterDataEditParts.sortByKey(map));
			}
		}
		
		return (MasterDataEditParts.sortByKey(readPointUriAttributesValuesBpwme));
	}
	
	
	/**
	 * Get the Company attributes
	 */
	public HashMap<String, String> getCompanyAttributes() {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.clear();
		
		if (!(company.getName().isEmpty() && company.getName() == ""))
			hm.put("Name", company.getName());
		if (!(company.getAddress().isEmpty() && company.getAddress() == ""))
			hm.put("Address", company.getAddress());
		if (!(company.getCity().isEmpty() && company.getCity() == ""))
			hm.put("City", company.getCity());
		if (!(company.getCountry().isEmpty() && company.getCountry() == ""))
			hm.put("Country", company.getCountry());
		if (!(company.getDescription().isEmpty() && company.getDescription() == ""))
			hm.put("Description", company.getDescription());
		
		String[] companyCustomAttr = {company.getAttr1(),company.getAttr2(),company.getAttr3(),company.getAttr4(),company.getAttr5(),company.getAttr6(),company.getAttr7(),company.getAttr8(),company.getAttr9(),company.getAttr10(),company.getAttr11(),company.getAttr12(),company.getAttr13(),company.getAttr14(),company.getAttr15(),company.getAttr16(),company.getAttr17(),company.getAttr18(),company.getAttr19(),company.getAttr20()};
		
		for (int i = 0; i < companyCustomAttr.length; i++) {
			if (!(companyCustomAttr[i].isEmpty() && companyCustomAttr[i] == ""))
				hm.put(companyCustomAttr[i].substring(0, companyCustomAttr[i].indexOf("_")), companyCustomAttr[i].substring(companyCustomAttr[i].indexOf("_")+1));
		}
		
		String warehouseValue = "";
		int x = 0;
		
		if (!company.getCompanyWarehouses().isEmpty()) {
			//for the first warehouse
			for (int i = 0; i < company.getCompanyWarehouses().size(); i++) {
				if (!(company.getCompanyWarehouses().get(i).getID().isEmpty() && company.getCompanyWarehouses().get(i).getID() == "")) {
					warehouseValue = company.getCompanyWarehouses().get(i).getID();
					x = i;
					break;
				}
			}
		
			//for the rest warehouses
			for (int i = x+1; i < company.getCompanyWarehouses().size(); i++) {
				if (!(company.getCompanyWarehouses().get(i).getID().isEmpty() && company.getCompanyWarehouses().get(i).getID() == ""))
					warehouseValue += "," + company.getCompanyWarehouses().get(i).getID();
			}
		}
		
		if (!(warehouseValue.isEmpty() && warehouseValue == ""))
			hm.put("WarehouseChildren", warehouseValue);
		
		return hm;
	}
	
	/**
	 * Get the Warehouse attributes
	 */
	private HashMap<String, String> getWarehouseAttributes(AbstractWarehouse part) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.clear();
	
		AbstractWarehouse warehouse = (AbstractWarehouse) part; 

		if (!(warehouse.getName().isEmpty() && warehouse.getName() == ""))
			hm.put("Name", warehouse.getName());
		if (!(warehouse.getDescription().isEmpty() && warehouse.getDescription() == ""))
			hm.put("Description", warehouse.getDescription());
		if (!(warehouse.getType().isEmpty() && warehouse.getType() == ""))
			hm.put("Type", warehouse.getType());
		
		String[] warehouseCustomAttr = {warehouse.getAttr1(),warehouse.getAttr2(),warehouse.getAttr3(),warehouse.getAttr4(),warehouse.getAttr5(),warehouse.getAttr6(),warehouse.getAttr7(),warehouse.getAttr8(),warehouse.getAttr9(),warehouse.getAttr10(),warehouse.getAttr11(),warehouse.getAttr12(),warehouse.getAttr13(),warehouse.getAttr14(),warehouse.getAttr15(),warehouse.getAttr16(),warehouse.getAttr17(),warehouse.getAttr18(),warehouse.getAttr19(),warehouse.getAttr20()};
		
		for (int i = 0; i < warehouseCustomAttr.length; i++) {
			if (!(warehouseCustomAttr[i].isEmpty() && warehouseCustomAttr[i] == ""))
				hm.put(warehouseCustomAttr[i].substring(0, warehouseCustomAttr[i].indexOf("_")), warehouseCustomAttr[i].substring(warehouseCustomAttr[i].indexOf("_")+1));
		}
		
		//add the warehouse children
		String warehouseValue = "";
		int x = 0;
		
		//include only the read point containers
		if (!warehouse.getWarehouses().isEmpty()) {
			//for the first read point container
			for (int i = 0; i < warehouse.getWarehouses().size(); i++) {
				if (!(warehouse.getID().isEmpty() && warehouse.getID() == "")) {
					warehouseValue = warehouse.getWarehouses().get(i).getID();
					x = i;
					break;
				}
			}		
			
			//for the rest read point container
			for (int i = x+1; i < warehouse.getWarehouses().size(); i++) {
				if (!(warehouse.getID().isEmpty() && warehouse.getID() == ""))
					warehouseValue += "," + warehouse.getWarehouses().get(i).getID();
			}
		}
		
		if (!(warehouseValue.isEmpty() && warehouseValue == ""))
			hm.put("WarehouseChildren", warehouseValue);
	
		//add the read point children
		String readPointValue = "";
		int y = 0;
		
		//include only the read point containers
		if (!warehouse.getContainers().isEmpty()) {
			//for the first read point container
			for (int i = 0; i < warehouse.getContainers().size(); i++) {
				if (warehouse.getContainers().get(i).isReadPoint()) {
					if (!(warehouse.getID().isEmpty() && warehouse.getID() == "")) {
						readPointValue = warehouse.getContainers().get(i).getID();
						y = i;
						break;
					}
				}
			}		
			
			//for the rest read point container
			for (int i = y+1; i < warehouse.getContainers().size(); i++) {
				if (warehouse.getContainers().get(i).isReadPoint()) {
					if (!(warehouse.getID().isEmpty() && warehouse.getID() == ""))
						readPointValue += "," + warehouse.getContainers().get(i).getID();
				}
			}
		}
		
		if (!(readPointValue.isEmpty() && readPointValue == ""))
			hm.put("ReadPoint", readPointValue);
		
		return hm;
	}
	
	/**
	 * Get the ReadPoint attributes
	 */
	private HashMap<String, String> getReadPointAttributes(AbstractContainer part) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.clear();
	
		AbstractContainer container = (AbstractContainer) part;

		if (!(container.getName().isEmpty() && container.getName() == ""))
			hm.put("Name", container.getName());
		if (!(container.getDescription().isEmpty() && container.getDescription() == ""))
			hm.put("Description", container.getDescription());
		if (!(container.getType().isEmpty() && container.getType() == ""))
			hm.put("Type", container.getType());
	
		String[] readPointCustomAttr = {container.getAttr1(),container.getAttr2(),container.getAttr3(),container.getAttr4(),container.getAttr5(),container.getAttr6(),container.getAttr7(),container.getAttr8(),container.getAttr9(),container.getAttr10(),container.getAttr11(),container.getAttr12(),container.getAttr13(),container.getAttr14(),container.getAttr15(),container.getAttr16(),container.getAttr17(),container.getAttr18(),container.getAttr19(),container.getAttr20()};
		
		for (int i = 0; i < readPointCustomAttr.length; i++) {
			if (!(readPointCustomAttr[i].isEmpty() && readPointCustomAttr[i] == ""))
				hm.put(readPointCustomAttr[i].substring(0, readPointCustomAttr[i].indexOf("_")), readPointCustomAttr[i].substring(readPointCustomAttr[i].indexOf("_")+1));
		}
		
		return hm;
	}

}
