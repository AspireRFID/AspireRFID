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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPartReference;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.edit.parts.*;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractContainerImpl;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.CompanyImpl;


/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class MasterDataEditParts {

	/**
	 * Root company, warehouse, container parts
	 */
	private static CompanyEditPart companyPart = null;
	private static ArrayList<AbstractEditPart> warehouseParts = new ArrayList<AbstractEditPart>();
	private static ArrayList<AbstractEditPart> containerParts = new ArrayList<AbstractEditPart>();
	private static ArrayList<AbstractEditPart> allParts = new ArrayList<AbstractEditPart>();
	
	/**
	 * Root company, warehouse, container parts
	 */
	private static HashMap<IWorkbenchPartReference, CompanyEditPart> companyPartRef = new HashMap<IWorkbenchPartReference, CompanyEditPart>();
	
	/**
	 * Map parts with Epcis ID
	 */
	private static HashMap<AbstractEditPart, String> warehouseInitialEpcisID = new HashMap<AbstractEditPart, String>();
	private static HashMap<AbstractEditPart, String> warehouseEndEpcisID = new HashMap<AbstractEditPart, String>();
	private static HashMap<AbstractEditPart, String> containerInitialEpcisID = new HashMap<AbstractEditPart, String>();
	private static HashMap<AbstractEditPart, String> containerEndEpcisID = new HashMap<AbstractEditPart, String>();
	
	/**
	 * BizLoc URIs
	 */
	private static ArrayList<String> insertedBizLocURIs = new ArrayList<String>();
	private static HashMap<String, String> alteredBizLocURIs = new HashMap<String, String>();
	private static ArrayList<String> deletedBizLocURIs = new ArrayList<String>();
	
	/**
	 * BizLoc URIs with attributes and values
	 */
	private static HashMap<String, HashMap<String, String>> bizLocUriAttributesValues = new HashMap<String, HashMap<String, String>>();
	
	/**
	 * ReadPoint URIs
	 */
	private static ArrayList<String> insertedReadPointURIs = new ArrayList<String>();
	private static HashMap<String, String> alteredReadPointURIs = new HashMap<String, String>();
	private static ArrayList<String> deletedReadPointURIs = new ArrayList<String>();
	
	/**
	 * ReadPoint URIs with attributes and values
	 */
	private static HashMap<String, HashMap<String, String>> readPointUriAttributesValues = new HashMap<String, HashMap<String, String>>();
	
	/**
	 * Company URI with attributes and values
	 */
	private static HashMap<String, HashMap<String, String>> companyUriAttributesValuesBpwme = new HashMap<String, HashMap<String, String>>();
	
	/**
	 * Warehouse URIs with attributes and values
	 */
	private static HashMap<String, HashMap<String, String>> warehouseUriAttributesValuesBpwme = new HashMap<String, HashMap<String, String>>();
	
	/**
	 * ReadPoint URIs with attributes and values
	 */
	private static HashMap<String, HashMap<String, String>> readPointUriAttributesValuesBpwme = new HashMap<String, HashMap<String, String>>();
	
	/**
	 * Inserted or altered BizLoc
	 */
	private static HashMap<CompanyEditPart, Boolean> isInserted = new HashMap<CompanyEditPart, Boolean>();
	
	/**
	 * New Company attributes
	 */
	private static String[] newCompanyAttr = null;
	
	/**
	 * New Warehouse attributes
	 */
	private static String[] newWarehouseAttr = null;
	
	/**
	 * New ReadPoint attributes
	 */
	private static String[] newReadPointAttr = null;

	/**
	 * Preference store for the new attributes
	 */
	private static IPreferenceStore store = MasterDataEditorGMFDiagramEditorPlugin.getInstance().getPreferenceStore();

	/**
	 * @return the companyPartRef
	 */
	public static HashMap<IWorkbenchPartReference, CompanyEditPart> getCompanyPartRef() {
		return companyPartRef;
	}

	/**
	 * @param companyPartRef the companyPartRef to set
	 */
	public static void setCompanyPartRef(
			HashMap<IWorkbenchPartReference, CompanyEditPart> companyPartRef) {
		MasterDataEditParts.companyPartRef = companyPartRef;
	}

	/**
	 * Successful insertion into EPCIS
	 */
	private static boolean isInsertedIntoEpcis = false;
	
	/**
	 * @return the isInsertedIntoEpcis
	 */
	public static boolean isInsertedIntoEpcis() {
		return isInsertedIntoEpcis;
	}

	/**
	 * @param isInsertedIntoEpcis the isInsertedIntoEpcis to set
	 */
	public static void setInsertedIntoEpcis(boolean isInsertedIntoEpcis) {
		MasterDataEditParts.isInsertedIntoEpcis = isInsertedIntoEpcis;
	}

	/**
	 * @return the insertedBizLocURIs
	 */
	public static ArrayList<String> getInsertedBizLocURIs() {
		return insertedBizLocURIs;
	}

	/**
	 * @param insertedBizLocURIs the insertedBizLocURIs to set
	 */
	public static void setInsertedBizLocURIs(ArrayList<String> insertedBizLocURIs) {
		MasterDataEditParts.insertedBizLocURIs = insertedBizLocURIs;
	}

	/**
	 * @return the alteredBizLocURIs
	 */
	public static HashMap<String, String> getAlteredBizLocURIs() {
		return alteredBizLocURIs;
	}

	/**
	 * @param alteredBizLocURIs the alteredBizLocURIs to set
	 */
	public static void setAlteredBizLocURIs(
			HashMap<String, String> alteredBizLocURIs) {
		MasterDataEditParts.alteredBizLocURIs = alteredBizLocURIs;
	}

	/**
	 * @return the deletedBizLocURIs
	 */
	public static ArrayList<String> getDeletedBizLocURIs() {
		return deletedBizLocURIs;
	}

	/**
	 * @param deletedBizLocURIs the deletedBizLocURIs to set
	 */
	public static void setDeletedBizLocURIs(ArrayList<String> deletedBizLocURIs) {
		MasterDataEditParts.deletedBizLocURIs = deletedBizLocURIs;
	}

	/**
	 * @return the insertedReadPointURIs
	 */
	public static ArrayList<String> getInsertedReadPointURIs() {
		return insertedReadPointURIs;
	}

	/**
	 * @param insertedReadPointURIs the insertedReadPointURIs to set
	 */
	public static void setInsertedReadPointURIs(
			ArrayList<String> insertedReadPointURIs) {
		MasterDataEditParts.insertedReadPointURIs = insertedReadPointURIs;
	}

	/**
	 * @return the alteredReadPointURIs
	 */
	public static HashMap<String, String> getAlteredReadPointURIs() {
		return alteredReadPointURIs;
	}

	/**
	 * @param alteredReadPointURIs the alteredReadPointURIs to set
	 */
	public static void setAlteredReadPointURIs(
			HashMap<String, String> alteredReadPointURIs) {
		MasterDataEditParts.alteredReadPointURIs = alteredReadPointURIs;
	}

	/**
	 * @return the deletedReadPointURIs
	 */
	public static ArrayList<String> getDeletedReadPointURIs() {
		return deletedReadPointURIs;
	}

	/**
	 * @param deletedReadPointURIs the deletedReadPointURIs to set
	 */
	public static void setDeletedReadPointURIs(
			ArrayList<String> deletedReadPointURIs) {
		MasterDataEditParts.deletedReadPointURIs = deletedReadPointURIs;
	}

	/**
	 * @return the store
	 */
	public static IPreferenceStore getStore() {
		return store;
	}
	
	/**
	 * @return the newCompanyAttr
	 */
	public static String[] getNewCompanyAttr() {
		return newCompanyAttr;
	}

	/**
	 * @param newCompanyAttr the newCompanyAttr to set
	 */
	public static void setNewCompanyAttr(String[] newCompanyAttr) {
		MasterDataEditParts.newCompanyAttr = newCompanyAttr;
	}

	/**
	 * @return the newWarehouseAttr
	 */
	public static String[] getNewWarehouseAttr() {
		return newWarehouseAttr;
	}

	/**
	 * @param newWarehouseAttr the newWarehouseAttr to set
	 */
	public static void setNewWarehouseAttr(String[] newWarehouseAttr) {
		MasterDataEditParts.newWarehouseAttr = newWarehouseAttr;
	}

	/**
	 * @return the newReadPointAttr
	 */
	public static String[] getNewReadPointAttr() {
		return newReadPointAttr;
	}

	/**
	 * @param newReadPointAttr the newReadPointAttr to set
	 */
	public static void setNewReadPointAttr(String[] newReadPointAttr) {
		MasterDataEditParts.newReadPointAttr = newReadPointAttr;
	}

	/**
	 * @return the companyPart
	 */
	public static CompanyEditPart getCompanyPart() {
		return companyPart;
	}

	/**
	 * @param companyPart the companyPart to set
	 */
	public static void setCompanyPart(CompanyEditPart companyPart) {
		MasterDataEditParts.companyPart = companyPart;
		createInitialParts();
	}

	/**
	 * @return the isInserted
	 */
	public static boolean isInserted(CompanyEditPart companyEditPart) {
		return isInserted.get(companyEditPart);
	}

	/**
	 * @param isInserted the isInserted to set
	 */
	public static void setIsInserted(CompanyEditPart companyEditPart, Boolean isInserted) {
		MasterDataEditParts.isInserted.put(companyEditPart, isInserted);
	}

	/**
	 * @return the bizLocUriAttributesValues
	 */
	public static HashMap<String, HashMap<String, String>> getBizLocUriAttributesValues() {
		return bizLocUriAttributesValues;
	}

	/**
	 * @return the readPointUriAttributesValues
	 */
	public static HashMap<String, HashMap<String, String>> getReadPointUriAttributesValues() {
		return readPointUriAttributesValues;
	}

	/**
	 * Set all the parts
	 */
	private static void setAllParts() {
		allParts.clear();
		
		//add the warehouse children of the company
		for (int i = 0; i < companyPart.getChildren().size(); i++) {
			allParts.add((AbstractEditPart) companyPart.getChildren().get(i));
		}
		
		//add all the other children
		for (int i = 0; i < allParts.size(); i++) {
			for (int j = 0; j < allParts.get(i).getChildren().size(); j++) {
				allParts.add((AbstractEditPart) allParts.get(i).getChildren().get(j));
			}
		}
	}
	
	/**
	 * Set all the warehouse parts
	 */
	private static void setWarehouseParts() {
		warehouseParts.clear();
		
		for (int i = 0; i < allParts.size(); i++) {
			if (isWarehouse(allParts.get(i)))
				warehouseParts.add(allParts.get(i));
		}
	}
	
	/**
	 * Check if part is warehouse
	 */
	public static boolean isWarehouse(AbstractEditPart warehousePart) {
		if (warehousePart instanceof WarehouseEditPart || warehousePart instanceof Warehouse2EditPart ||
				warehousePart instanceof BizLocEditPart || warehousePart instanceof BizLoc2EditPart || 
				warehousePart instanceof RoomEditPart || warehousePart instanceof Room2EditPart || 
				warehousePart instanceof SectionEditPart ||	warehousePart instanceof Section2EditPart)
			return true;
		
		return false;
	}
	
	/**
	 * Set all the container parts
	 */
	private static void setContainerParts() {
		containerParts.clear();
		
		for (int i = 0; i < allParts.size(); i++) {
			if (isContainer(allParts.get(i))) {
				containerParts.add(allParts.get(i));
			}
			
		}
	}
	
	/**
	 * Check if part is container
	 */
	public static boolean isContainer(AbstractEditPart containerPart) {
		if (containerPart instanceof ReadPointEditPart || containerPart instanceof ContainerEditPart ||
				containerPart instanceof ConveyorEditPart || containerPart instanceof ShelfEditPart || 
				containerPart instanceof GateEditPart || containerPart instanceof PalletTruckEditPart || 
				containerPart instanceof PushArmEditPart ||	containerPart instanceof HandHeldReaderEditPart)
			return true;
		
		return false;
	}
	
	/**
	 * Create initial parts
	 */
	private static void createInitialParts() {
		setAllParts();
		setWarehouseParts();
		setContainerParts();
	}
	
	/**
	 * Map the company, warehouses, non read point containers with their EPCIS IDs
	 */
	private static HashMap<AbstractEditPart, String> getWarehouseEpcisID() {
		HashMap<AbstractEditPart, String> hm = new HashMap<AbstractEditPart, String>();
		hm.clear();
		Company company = (CompanyImpl) ((View) companyPart.getModel()).getElement();

		//add the company
		hm.put(companyPart, company.getID());
		
		//add the warehouses
		for (int i = 0; i < warehouseParts.size(); i++) {
			AbstractWarehouse warehouse = (AbstractWarehouseImpl) ((View) warehouseParts.get(i).getModel()).getElement();

			if (warehouseParts.get(i).getParent() instanceof CompanyEditPart)
				hm.put(warehouseParts.get(i), company.getID() + "," + warehouse.getID());
			else if (hm.containsKey(warehouseParts.get(i).getParent().getParent()))
					hm.put(warehouseParts.get(i), hm.get(warehouseParts.get(i).getParent().getParent()) + "," + warehouse.getID());
		}
		
		//add the non read point containers
		for (int i = 0; i < containerParts.size(); i++) {
			AbstractContainer container = (AbstractContainerImpl) ((View) containerParts.get(i).getModel()).getElement();
			
			if (container.isReadPoint() == false)
				hm.put(containerParts.get(i), hm.get(containerParts.get(i).getParent().getParent()) + "," + container.getID());
		}
		
		HashMap<AbstractEditPart, String> hmSorted = sortByValue(hm);
		return hmSorted;
	}
	
	/**
	 * Map the read point containers with their EPCIS IDs
	 */
	private static HashMap<AbstractEditPart, String> getContainerEpcisID() {
		HashMap<AbstractEditPart, String> hm = new HashMap<AbstractEditPart, String>();
		hm.clear();

		for (int i = 0; i < containerParts.size(); i++) {
			AbstractContainer container = (AbstractContainerImpl) ((View) containerParts.get(i).getModel()).getElement();
			//add the read point containers
			if (container.isReadPoint() == true)
				hm.put(containerParts.get(i), container.getID());
		}
		
		HashMap<AbstractEditPart, String> hmSorted = sortByValue(hm);
		return hmSorted;
	}
	
	/**
	 * Set the inserted BizLoc URIs
	 */
	public static void setInsertedBizLocURIs() {
		insertedBizLocURIs.clear();
		
		//add warehouses and non read point containers IDs
		Collection<String> c = warehouseInitialEpcisID.values();
		Iterator<String> itr = c.iterator();
		
		while (itr.hasNext())
			insertedBizLocURIs.add(itr.next());
	}
	
	/**
	 * Set the altered BizLoc URIs
	 */
	public static void setAlteredBizLocURIs() {
		alteredBizLocURIs.clear();

		Set<Entry<AbstractEditPart, String>> set = warehouseEndEpcisID.entrySet();
		Iterator<Entry<AbstractEditPart, String>> itr = set.iterator();

		while (itr.hasNext()) {
			Entry<AbstractEditPart, String> item = itr.next();
			if (warehouseInitialEpcisID.containsKey(item.getKey())) {
				if (!(item.getValue().equals(warehouseInitialEpcisID.get(item.getKey())))) {
					alteredBizLocURIs.put(warehouseInitialEpcisID.get(item.getKey()), item.getValue());
				}
			}
		}
	}
	
	/**
	 * Set the deleted BizLoc URIs
	 */
	public static void setDeletedBizLocURIs() {
		deletedBizLocURIs.clear();
		
		Set<Entry<AbstractEditPart, String>> set = warehouseInitialEpcisID.entrySet();
		Iterator<Entry<AbstractEditPart, String>> itr = set.iterator();

		while (itr.hasNext()) {
			Entry<AbstractEditPart, String> item = itr.next();
			if (!(warehouseEndEpcisID.containsKey(item.getKey()))) {
				deletedBizLocURIs.add(item.getValue());
			}
		}
	}
	
	/**
	 * Set the BizLoc URIs with attributes and values
	 */
	public static void setBizLocUriAttributesValues() {
		bizLocUriAttributesValues.clear();
		
		Set<Entry<AbstractEditPart, String>> set = warehouseInitialEpcisID.entrySet();
		Iterator<Entry<AbstractEditPart, String>> itr = set.iterator();
		
		while (itr.hasNext()){
			Entry<AbstractEditPart, String> item = itr.next();
			if (item.getKey() instanceof CompanyEditPart) {
				if (!getCompanyAttributes().isEmpty())
					bizLocUriAttributesValues.put(item.getValue(), getCompanyAttributes());				
			}
			if (isWarehouse(item.getKey())) {
				if (!getWarehouseAttributes(item.getKey()).isEmpty())
					bizLocUriAttributesValues.put(item.getValue(), getWarehouseAttributes(item.getKey()));
			}
			if (isContainer(item.getKey())) {
				if (!getContainerAttributes(item.getKey()).isEmpty())
					bizLocUriAttributesValues.put(item.getValue(), getContainerAttributes(item.getKey()));
			}
		}
		bizLocUriAttributesValues = sortByKey(bizLocUriAttributesValues);
	}
	
	/**
	 * Get the company attributes
	 */
	private static HashMap<String, String> getCompanyAttributes() {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.clear();
		
		Company company = (CompanyImpl) ((View) companyPart.getModel()).getElement();
		
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
	
		for (int i = 0; i < newCompanyAttr.length; i++) {
			if (!(store.getString(newCompanyAttr[i]).isEmpty() && store.getString(newCompanyAttr[i]) != null))
				hm.put(newCompanyAttr[i], store.getString(newCompanyAttr[i]));
		}
		
		return hm;
	}
	
	/**
	 * Get the warehouse attributes
	 */
	private static HashMap<String, String> getWarehouseAttributes(AbstractEditPart part) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.clear();
	
		AbstractWarehouse warehouse = (AbstractWarehouseImpl) ((View) part.getModel()).getElement();

		if (!(warehouse.getName().isEmpty() && warehouse.getName() == ""))
			hm.put("Name", warehouse.getName());
		if (!(warehouse.getDescription().isEmpty() && warehouse.getDescription() == ""))
			hm.put("Description", warehouse.getDescription());
		if (!(warehouse.getType().isEmpty() && warehouse.getType() == ""))
			hm.put("Type", warehouse.getType());
	
		String readPointValue = "";
		int x = 0;
		
		//include only the read point containers
		if (!warehouse.getContainers().isEmpty()) {
			//for the first read point container
			for (int i = 0; i < warehouse.getContainers().size(); i++) {
				if (warehouse.getContainers().get(i).isReadPoint()) {
					if (!(warehouse.getID().isEmpty() && warehouse.getID() == "")) {
						readPointValue = warehouse.getContainers().get(i).getID();
						x = i;
						break;
					}
				}
			}		
			
			//for the rest read point container
			for (int i = x+1; i < warehouse.getContainers().size(); i++) {
				if (warehouse.getContainers().get(i).isReadPoint()) {
					if (!(warehouse.getID().isEmpty() && warehouse.getID() == ""))
						readPointValue += "," + warehouse.getContainers().get(i).getID();
				}
			}
		}
		
		if (!(readPointValue.isEmpty() && readPointValue == ""))
			hm.put("ReadPoint", readPointValue);
		
		for (int i = 0; i < newWarehouseAttr.length; i++) {
			if (!(store.getString(newWarehouseAttr[i]).equals("") && store.getString(newWarehouseAttr[i]) != null))
				hm.put(newWarehouseAttr[i], store.getString(newWarehouseAttr[i]));
		}
		
		return hm;
	}
	
	/**
	 * Get the container attributes
	 */
	private static HashMap<String, String> getContainerAttributes(AbstractEditPart part) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.clear();
	
		AbstractContainer container = (AbstractContainerImpl) ((View) part.getModel()).getElement();

		if (!(container.getName().isEmpty() && container.getName() == ""))
			hm.put("Name", container.getName());
		if (!(container.getDescription().isEmpty() && container.getDescription() == ""))
			hm.put("Description", container.getDescription());
		if (!(container.getType().isEmpty() && container.getType() == ""))
			hm.put("Type", container.getType());
	
		for (int i = 0; i < newReadPointAttr.length; i++) {
			if (!(store.getString(newReadPointAttr[i]).equals("") && store.getString(newReadPointAttr[i]) != null))
				hm.put(newReadPointAttr[i], store.getString(newReadPointAttr[i]));
		}
		
		return hm;
	}
	
	/**
	 * Set the ReadPoint URIs with attributes and values
	 */
	public static void setReadPointUriAttributesValues() {
		readPointUriAttributesValues.clear();
		
		Set<Entry<AbstractEditPart, String>> set = containerInitialEpcisID.entrySet();
		Iterator<Entry<AbstractEditPart, String>> itr = set.iterator();
		
		while (itr.hasNext()){
			Entry<AbstractEditPart, String> item = itr.next();
			if (!getContainerAttributes(item.getKey()).isEmpty())
				readPointUriAttributesValues.put(item.getValue(), getContainerAttributes(item.getKey()));
		}
		readPointUriAttributesValues = sortByKey(readPointUriAttributesValues);
	}

	/**
	 * Set the inserted ReadPoint URIs
	 */
	public static void setInsertedReadPointURIs() {
		insertedReadPointURIs.clear();
		
		//add read point containers IDs
		Collection<String> c = containerInitialEpcisID.values();
		Iterator<String> itr = c.iterator();
		
		while (itr.hasNext())
			insertedReadPointURIs.add(itr.next());
	}
	
	/**
	 * Set the altered ReadPoint URIs
	 */
	public static void setAlteredReadPointURIs() {
		alteredReadPointURIs.clear();

		Set<Entry<AbstractEditPart, String>> set = containerEndEpcisID.entrySet();
		Iterator<Entry<AbstractEditPart, String>> itr = set.iterator();

		while (itr.hasNext()) {
			Entry<AbstractEditPart, String> item = itr.next();
			if (containerInitialEpcisID.containsKey(item.getKey())) {
				if (!(item.getValue().equals(containerInitialEpcisID.get(item.getKey())))) {
					alteredReadPointURIs.put(containerInitialEpcisID.get(item.getKey()), item.getValue());
				}
			}
		}
	}
	
	/**
	 * Set the deleted ReadPoint URIs
	 */
	public static void setDeletedReadPointURIs() {
		deletedReadPointURIs.clear();
		
		Set<Entry<AbstractEditPart, String>> set = containerInitialEpcisID.entrySet();
		Iterator<Entry<AbstractEditPart, String>> itr = set.iterator();

		while (itr.hasNext()) {
			Entry<AbstractEditPart, String> item = itr.next();
			if (!(containerEndEpcisID.containsKey(item.getKey()))) {
				deletedReadPointURIs.add(item.getValue());
			}
		}
	}
	
	/**
	 * Set initial parts at creation time
	 */
	public static void setInitialParts() {
		setInitialWarehouseEpcisID();
		setInsertedBizLocURIs();
		
		setInitialContainerEpcisID();
		setInsertedReadPointURIs();
		setBizLocUriAttributesValues();
		setReadPointUriAttributesValues();
		/*
		System.out.println("Initial:" + warehouseInitialEpcisID);
		System.out.println("End:    " + warehouseEndEpcisID);
		System.out.println("Initial:" + containerInitialEpcisID);
		System.out.println("End:    " + containerEndEpcisID);
		*/
	}
	
	/**
	 * Set end parts at creation time
	 */
	public static void setEndParts() {
		setEndWarehouseEpcisID();
		setAlteredBizLocURIs();
		setDeletedBizLocURIs();
		
		setEndContainerEpcisID();
		setAlteredReadPointURIs();
		setDeletedReadPointURIs();
		/*
		System.out.println("Initial:" + warehouseInitialEpcisID);
		System.out.println("End:    " + warehouseEndEpcisID);
		System.out.println("Initial:" + containerInitialEpcisID);
		System.out.println("End:    " + containerEndEpcisID);
		*/
		setInitialParts();
	}
	
	/**
	 * Set initial warehouse map Epcis ID - at creation time
	 */
	private static void setInitialWarehouseEpcisID() {
		warehouseInitialEpcisID = getWarehouseEpcisID();
	}

	/**
	 * Set end warehouse map Epcis ID - at save time
	 */
	private static void setEndWarehouseEpcisID() {
		warehouseEndEpcisID = getWarehouseEpcisID();
	}
	
	/**
	 * Set initial container map Epcis ID - at creation time
	 */
	private static void setInitialContainerEpcisID() {
		containerInitialEpcisID = getContainerEpcisID();
	}

	/**
	 * Set end container map Epcis ID - at save time
	 */
	private static void setEndContainerEpcisID() {
		containerEndEpcisID = getContainerEpcisID();
	}
	
	/**
	 * Get the Company URI with attributes and values for Bpwme
	 */
	public static HashMap<String, HashMap<String, String>> getCompanyUriAttributesValuesBpwme() {
		companyUriAttributesValuesBpwme.clear();
		HashMap<String, String> map = new HashMap<String, String>(); 
		Company company = (CompanyImpl) ((View) companyPart.getModel()).getElement();
		
		if (!getCompanyAttributes().isEmpty())
			map = getCompanyAttributes();
		
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
		}		
		
		//for the rest warehouses
		for (int i = x+1; i < company.getCompanyWarehouses().size(); i++) {
			if (!(company.getCompanyWarehouses().get(i).getID().isEmpty() && company.getCompanyWarehouses().get(i).getID() == ""))
				warehouseValue += "," + company.getCompanyWarehouses().get(i).getID();
		}
		
		if (!(warehouseValue.isEmpty() && warehouseValue == ""))
			map.put("WarehouseChildren", warehouseValue);
		
		companyUriAttributesValuesBpwme.put(company.getID(), sortByKey(map));
		
		return (sortByKey(companyUriAttributesValuesBpwme));
	}
	
	/**
	 * Get the Warehouse URIs with attributes and values for Bpwme
	 */
	public static HashMap<String, HashMap<String, String>> getWarehouseUriAttributesValuesBpwme() {
		warehouseUriAttributesValuesBpwme.clear();
		HashMap<String, String> map = new HashMap<String, String>();
		
		Set<Entry<AbstractEditPart, String>> set = warehouseInitialEpcisID.entrySet();
		Iterator<Entry<AbstractEditPart, String>> itr = set.iterator();
		
		while (itr.hasNext()){
			map.clear();
			Entry<AbstractEditPart, String> item = itr.next();

			if (isWarehouse(item.getKey())) {
				if (!getWarehouseAttributes(item.getKey()).isEmpty()) 
					map = getWarehouseAttributes(item.getKey());
				
				String warehouseValue = "";
				int x = 0;
				
				if (!(item.getValue().isEmpty() && item.getValue() == "")) {
					
					for (int i = 0; i < item.getKey().getChildren().size(); i++) {
						
						if (isWarehouseCompartment((AbstractEditPart) item.getKey().getChildren().get(i))) {
							AbstractEditPart warehousePart = (AbstractEditPart) item.getKey().getChildren().get(i);

							for (int j = 0; j < warehousePart.getChildren().size(); j++) {
								//for the first warehouse
								if (isWarehouse((AbstractEditPart) warehousePart.getChildren().get(j))) {
									AbstractWarehouse warehouse = (AbstractWarehouseImpl) ((View) ((AbstractEditPart) warehousePart.getChildren().get(j)).getModel()).getElement();
									
									if (!(warehouse.getID().isEmpty() && warehouse.getID() == "")) {
										warehouseValue = warehouse.getID();
										x = j;
										break;
									}
								}
							}
					
							//for the rest warehouses
							for (int j = x+1; j < warehousePart.getChildren().size(); j++) {
								if (isWarehouse((AbstractEditPart) warehousePart.getChildren().get(j))) {
									AbstractWarehouse warehouse = (AbstractWarehouseImpl) ((View) ((AbstractEditPart) warehousePart.getChildren().get(j)).getModel()).getElement();
			
									if (!(warehouse.getID().isEmpty() && warehouse.getID() == ""))
										warehouseValue += "," + warehouse.getID();
								}
							}
						}

						if (isContainerCompartment((AbstractEditPart) item.getKey().getChildren().get(i))) {
							AbstractEditPart warehousePart = (AbstractEditPart) item.getKey().getChildren().get(i);

							//if warehouseValue is empty
							if (warehouseValue.isEmpty() || warehouseValue == "") {

								for (int j = 0; j < warehousePart.getChildren().size(); j++) {						
									//for first container
									if (isContainer((AbstractEditPart) warehousePart.getChildren().get(j))) {
										AbstractContainer container = (AbstractContainerImpl) ((View) ((AbstractEditPart) warehousePart.getChildren().get(j)).getModel()).getElement();
										
										if (!(container.getID().isEmpty() && container.getID() == "")) {
											if (!container.isReadPoint()) {
												warehouseValue = container.getID();
												x = j;
												break;
											}
										}
									}
								}
							
								//for the rest containers
								for (int j = x+1; j < warehousePart.getChildren().size(); j++) {
									if (isContainer((AbstractEditPart) warehousePart.getChildren().get(j))) {
										AbstractContainer container = (AbstractContainerImpl) ((View) ((AbstractEditPart) warehousePart.getChildren().get(j)).getModel()).getElement();
				
										if (!(container.getID().isEmpty() && container.getID() == "")) {
											if (!container.isReadPoint()) {
												warehouseValue += "," + container.getID();
											}
										}
									}
								}
							}
							else {
								for (int j = 0; j < warehousePart.getChildren().size(); j++) {						
									if (isContainer((AbstractEditPart) warehousePart.getChildren().get(j))) {
										AbstractContainer container = (AbstractContainerImpl) ((View) ((AbstractEditPart) warehousePart.getChildren().get(j)).getModel()).getElement();
										
										if (!(container.getID().isEmpty() && container.getID() == "")) {
											if (!container.isReadPoint()) {
												warehouseValue += "," + container.getID();
											}
										}
									}
								}
							}

						if (!(warehouseValue.isEmpty() && warehouseValue == ""))
							map.put("WarehouseChildren", warehouseValue);
						}
					}
				}
				warehouseUriAttributesValuesBpwme.put(item.getValue(), sortByKey(map));
			}
			
			if (isContainer(item.getKey())) {
				if (!getContainerAttributes(item.getKey()).isEmpty())
					warehouseUriAttributesValuesBpwme.put(item.getValue(), getContainerAttributes(item.getKey()));
			}
		}
		return (sortByKey(warehouseUriAttributesValuesBpwme));
	}
	
	/**
	 * Get the ReadPoint URIs with attributes and values for Bpwme
	 */
	public static HashMap<String, HashMap<String, String>> getReadPointUriAttributesValuesBpwme() {
		readPointUriAttributesValuesBpwme.clear();
		
		Set<Entry<AbstractEditPart, String>> set = containerInitialEpcisID.entrySet();
		Iterator<Entry<AbstractEditPart, String>> itr = set.iterator();
		
		while (itr.hasNext()){
			Entry<AbstractEditPart, String> item = itr.next();
			if (!getContainerAttributes(item.getKey()).isEmpty())
				readPointUriAttributesValuesBpwme.put(item.getValue(), getContainerAttributes(item.getKey()));
		}
		return (sortByKey(readPointUriAttributesValuesBpwme));
	}
	
	/**
	 * Check if part is warehouse compartment
	 */
	public static boolean isWarehouseCompartment(AbstractEditPart warehousePart) {
		if (warehousePart instanceof WarehouseWarehousesCompartmentEditPart || warehousePart instanceof WarehouseWarehousesCompartment2EditPart ||
				warehousePart instanceof BizLocWarehousesCompartmentEditPart || warehousePart instanceof BizLocWarehousesCompartment2EditPart || 
				warehousePart instanceof RoomWarehousesCompartmentEditPart || warehousePart instanceof RoomWarehousesCompartment2EditPart || 
				warehousePart instanceof SectionWarehousesCompartmentEditPart ||	warehousePart instanceof SectionWarehousesCompartment2EditPart)
			return true;
		
		return false;
	}
	
	/**
	 * Check if part is container compartment
	 */
	public static boolean isContainerCompartment(AbstractEditPart containerPart) {
		if (containerPart instanceof WarehouseContainersCompartmentEditPart || containerPart instanceof WarehouseContainersCompartment2EditPart ||
				containerPart instanceof BizLocContainersCompartmentEditPart || containerPart instanceof BizLocContainersCompartment2EditPart || 
				containerPart instanceof RoomContainersCompartmentEditPart || containerPart instanceof RoomContainersCompartment2EditPart || 
				containerPart instanceof SectionContainersCompartmentEditPart ||	containerPart instanceof SectionContainersCompartment2EditPart)
			return true;
		
		return false;
	}
	
	/**
	 * Sort the HashMap based on its values
	 */
	private static HashMap sortByValue(HashMap map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		HashMap result = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	/**
	 * Sort the HashMap based on its keys
	 */
	private static HashMap sortByKey(HashMap map) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getKey())
						.compareTo(((Map.Entry) (o2)).getKey());
			}
		});
		HashMap result = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
		
}
