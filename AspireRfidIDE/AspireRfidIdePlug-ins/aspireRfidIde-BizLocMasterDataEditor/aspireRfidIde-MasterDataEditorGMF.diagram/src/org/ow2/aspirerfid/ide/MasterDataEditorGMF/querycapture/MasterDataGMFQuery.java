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

import java.io.PrintWriter;
import java.io.StringWriter;
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

import org.ow2.aspirerfid.commons.epcis.model.ArrayOfString;
import org.ow2.aspirerfid.commons.epcis.model.QueryParam;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFFactory;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.ReadPoint;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.preferences.DiagramConfiguratorPreferenceConstants;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.tools.EpcisConstants;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.tools.QueryClientGuiHelper;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class MasterDataGMFQuery {
	
	/**
	 * The endpoint URL for the query service.
	 */
	private static String queryUrl = DiagramConfiguratorPreferenceConstants.P_QueryURL;
	
	/**
	 * The QueryClientGuiHelper for the query service.
	 */
	private static QueryClientGuiHelper queryClient = new QueryClientGuiHelper(queryUrl);
	
	/**
	 * The selected company
	 */
	private static String selectedCompany = null;
	
	/**
	 * @return the selectedCompany
	 */
	public static String getSelectedCompany() {
		return selectedCompany;
	}

	/**
	 * @param selectedCompany the selectedCompany to set
	 */
	public static void setSelectedCompany(String selectedCompany) {
		MasterDataGMFQuery.selectedCompany = selectedCompany;
	}

	/**
	 * Retrieve from EPCIS
	 */
	private static HashMap<String, String> retrieveFromEpcis(String URI, String vocabulary) {
		
		HashMap<String, String> dataSorted = new HashMap<String, String>();

		try {
			HashMap<String, String> data = new HashMap<String, String>();
			queryClient.clearParameters();
	
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			queryClient.addParameter(queryParam);
	
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			queryClient.addParameter(param2);
			
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(true);
			queryClient.addParameter(param3);
		
			ArrayOfString vocabularyNames = new ArrayOfString();
			vocabularyNames.getString().add(URI);
			QueryParam param4 = new QueryParam();
			param4.setName("EQ_name");
			param4.setValue(vocabularyNames);
			queryClient.addParameter(param4);
			
			ArrayOfString vocabularyType = new ArrayOfString();
			vocabularyType.getString().add(vocabulary);
			QueryParam param5 = new QueryParam();
			param5.setName("vocabularyName");
			param5.setValue(vocabularyType);
			queryClient.addParameter(param5);
			
			data = queryClient.runMasterDataQueryHashMap();
			dataSorted = sortByKey(data);
			
			if (!data.equals(null))
			System.out.println("Data retrieved from the query:\n" + dataSorted);
		}
		catch (Exception e) {
			String msg = "Unexpected error while invoking EPCIS Query Interface.";
			System.out.print("\n" + msg + "\n");
			System.out.print(e.toString());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			System.out.print(sw.toString());
		}
		return dataSorted;
	}
	
	/**
	 * Get the company names
	 */
	public static String[] getCompanyNames() {
		String[] companyNames = null;

		HashMap<String, String> data = new HashMap<String, String>();
		HashMap<String, String> dataSorted = new HashMap<String, String>();

		try {
			queryClient.clearParameters();
	
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			queryClient.addParameter(queryParam);
	
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			queryClient.addParameter(param2);
			
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(true);
			queryClient.addParameter(param3);
		
			ArrayOfString vocabularyType = new ArrayOfString();
			vocabularyType.getString().add(EpcisConstants.BUSINESS_LOCATION_ID);
			QueryParam param4 = new QueryParam();
			param4.setName("vocabularyName");
			param4.setValue(vocabularyType);
			queryClient.addParameter(param4);
			
			data = queryClient.runMasterDataQueryCompanyNames();
		}
		catch (Exception e) {
			String msg = "Unexpected error while invoking EPCIS Query Interface.";
			System.out.print("\n" + msg + "\n");
			System.out.print(e.toString());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			System.out.print(sw.toString());
		}
		
		dataSorted = sortByValue(data);
		companyNames = new String[data.size()];
		
		Set<Entry<String, String>> set = dataSorted.entrySet();
		Iterator<Entry<String, String>> itr = set.iterator();
		int i = 0;

		while (itr.hasNext()) {
			Entry<String, String> item = itr.next();
			companyNames[i] = item.getKey();
			i++;
		}
		
		return companyNames;
	}

	/**
	 * Create the company from the EPCIS data
	 */
	public static Company createEpcisModel(String companyID) {
		HashMap<String, String> companyData = retrieveFromEpcis(companyID, EpcisConstants.BUSINESS_LOCATION_ID);
		Company company = MasterDataEditorGMFFactory.eINSTANCE.createCompany();

		//add the company info
		company.setID(companyID);
		if (companyData.containsKey("urn:epcglobal:epcis:mda:Address"))
			company.setAddress(companyData.get("urn:epcglobal:epcis:mda:Address"));
		if (companyData.containsKey("urn:epcglobal:epcis:mda:City"))
			company.setCity(companyData.get("urn:epcglobal:epcis:mda:City"));
		if (companyData.containsKey("urn:epcglobal:epcis:mda:Country"))
			company.setCountry(companyData.get("urn:epcglobal:epcis:mda:Country"));
		if (companyData.containsKey("urn:epcglobal:epcis:mda:Name"))
			company.setName(companyData.get("urn:epcglobal:epcis:mda:Name"));
		if (companyData.containsKey("urn:epcglobal:epcis:mda:Description"))
			company.setDescription(companyData.get("urn:epcglobal:epcis:mda:Description"));
		
		//add custom attributes
		Set<Entry<String, String>> set1 = companyData.entrySet();
		Iterator<Entry<String, String>> itr1 = set1.iterator();

		while (itr1.hasNext()) {
			Entry<String, String> item1 = itr1.next();
		
			if (item1.getKey().startsWith("urn:epcglobal:epcis:mda:") && !item1.getKey().equals("urn:epcglobal:epcis:mda:Address") 
					&& !item1.getKey().equals("urn:epcglobal:epcis:mda:City") && !item1.getKey().equals("urn:epcglobal:epcis:mda:Country") 
					&& !item1.getKey().equals("urn:epcglobal:epcis:mda:Name") && !item1.getKey().equals("urn:epcglobal:epcis:mda:Description")) {
				for (int i = 0; i < MasterDataEditParts.getNewCompanyAttr().length; i++) {
					if (!(MasterDataEditParts.getNewCompanyAttr()[i].isEmpty() && MasterDataEditParts.getNewCompanyAttr()[i] == "")) {
					
						switch(i) {
						case 0:company.setAttr1(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 1:company.setAttr2(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 2:company.setAttr3(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 3:company.setAttr4(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 4:company.setAttr5(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 5:company.setAttr6(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 6:company.setAttr7(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 7:company.setAttr8(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 8:company.setAttr9(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 9:company.setAttr10(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 10:company.setAttr11(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 11:company.setAttr12(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 12:company.setAttr13(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 13:company.setAttr14(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 14:company.setAttr15(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 15:company.setAttr16(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 16:company.setAttr17(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 17:company.setAttr18(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 18:company.setAttr19(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;case 19:company.setAttr20(item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+companyData.get(item1.getKey()));break;
						}
					}
				}
			}
		}
		
		Set<Entry<String, String>> set = companyData.entrySet();
		Iterator<Entry<String, String>> itr = set.iterator();
		int i = 0;
		AbstractWarehouse warehouse = null;
		
		//add the warehouses
		while (itr.hasNext()) {
			Entry<String, String> item1 = itr.next();
			String warehousechild = "WarehouseChildren" + i;
			if (item1.getKey().equals(warehousechild)) {
				HashMap<String, String> warehouseData = retrieveFromEpcis(item1.getValue(), EpcisConstants.BUSINESS_LOCATION_ID);
				System.out.println("warehousedata: " + warehouseData);
				
				//check warehouse type
				if (warehouseData.containsKey("urn:epcglobal:epcis:mda:Type")) {
					if (warehouseData.get("urn:epcglobal:epcis:mda:Type").equals("Warehouse"))
						warehouse = MasterDataEditorGMFFactory.eINSTANCE.createWarehouse();
					else if (warehouseData.get("urn:epcglobal:epcis:mda:Type").equals("Room"))
						warehouse = MasterDataEditorGMFFactory.eINSTANCE.createRoom();
					else if (warehouseData.get("urn:epcglobal:epcis:mda:Type").equals("Section"))
						warehouse = MasterDataEditorGMFFactory.eINSTANCE.createSection();
					else
						warehouse = MasterDataEditorGMFFactory.eINSTANCE.createBizLoc();
				}
				else
					warehouse = MasterDataEditorGMFFactory.eINSTANCE.createBizLoc();
					
				company.getCompanyWarehouses().add(warehouse);
				
				String warehID = item1.getValue().replaceAll(companyID + ",", "");
				warehouse.setID(warehID);
				if (warehouseData.containsKey("urn:epcglobal:epcis:mda:Name"))
					warehouse.setName(warehouseData.get("urn:epcglobal:epcis:mda:Name"));
				if (warehouseData.containsKey("urn:epcglobal:epcis:mda:Description"))
					warehouse.setDescription(warehouseData.get("urn:epcglobal:epcis:mda:Description"));
				if (warehouseData.containsKey("urn:epcglobal:epcis:mda:Type") && warehouse instanceof BizLoc)
					warehouse.setType(warehouseData.get("urn:epcglobal:epcis:mda:Type"));
				
				//add custom attributes
				Set<Entry<String, String>> set2 = warehouseData.entrySet();
				Iterator<Entry<String, String>> itr2 = set2.iterator();


				while (itr2.hasNext()) {
					Entry<String, String> item2 = itr2.next();
				
					if (item2.getKey().startsWith("urn:epcglobal:epcis:mda:") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Address") 
							&& !item2.getKey().equals("urn:epcglobal:epcis:mda:City") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Country") 
							&& !item2.getKey().equals("urn:epcglobal:epcis:mda:Name") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Description")) {
						for (int k = 0; k < MasterDataEditParts.getNewWarehouseAttr().length; k++) {
							if (!(MasterDataEditParts.getNewWarehouseAttr()[k].isEmpty() && MasterDataEditParts.getNewWarehouseAttr()[k] == "")) {
							
								switch(k) {
								case 0:warehouse.setAttr1(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 1:warehouse.setAttr2(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 2:warehouse.setAttr3(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 3:warehouse.setAttr4(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 4:warehouse.setAttr5(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 5:warehouse.setAttr6(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 6:warehouse.setAttr7(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 7:warehouse.setAttr8(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 8:warehouse.setAttr9(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 9:warehouse.setAttr10(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 10:warehouse.setAttr11(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 11:warehouse.setAttr12(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 12:warehouse.setAttr13(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 13:warehouse.setAttr14(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 14:warehouse.setAttr15(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 15:warehouse.setAttr16(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 16:warehouse.setAttr17(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 17:warehouse.setAttr18(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 18:warehouse.setAttr19(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 19:warehouse.setAttr20(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;
								}
							}
						}
					}
				}
				
				//add the containers
				if (warehouseData.containsKey("urn:epcglobal:epcis:mda:ReadPoint")) {
					String[] split = splitString(warehouseData.get("urn:epcglobal:epcis:mda:ReadPoint"));
					HashMap<String, String> containerData = new HashMap<String, String>();
					
					for (int k = 0; k < split.length; k++) {
					
						containerData = retrieveFromEpcis(split[k], EpcisConstants.READ_POINT_ID);
						System.out.println("myid: " + split[k]);
						System.out.println("containerdata: " + containerData);
						AbstractContainer container = null;
						
						//check container type
						if (containerData.containsKey("urn:epcglobal:epcis:mda:Type")) {
							if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("Container"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createContainer();
							else if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("Conveyor"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createConveyor();
							else if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("Shelf"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createShelf();
							else if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("Gate"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createGate();
							else if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("PalletTruck"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createPalletTruck();
							else if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("PushArm"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createPushArm();
							else if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("HandHeldReader"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createHandHeldReader();
							else
								container = MasterDataEditorGMFFactory.eINSTANCE.createReadPoint();
						}
						else
							container = MasterDataEditorGMFFactory.eINSTANCE.createReadPoint();
						
						warehouse.getContainers().add(container);
						if (containerData.containsKey("ID"))
							container.setID(containerData.get("ID"));
						if (containerData.containsKey("urn:epcglobal:epcis:mda:Name"))
							container.setName(containerData.get("urn:epcglobal:epcis:mda:Name"));
						if (containerData.containsKey("urn:epcglobal:epcis:mda:Description"))
							container.setDescription(containerData.get("urn:epcglobal:epcis:mda:Description"));
						if (containerData.containsKey("urn:epcglobal:epcis:mda:Type") && container instanceof ReadPoint)
							container.setDescription(containerData.get("urn:epcglobal:epcis:mda:Type"));
						
						//add custom attributes
						Set<Entry<String, String>> set3 = containerData.entrySet();
						Iterator<Entry<String, String>> itr3 = set3.iterator();


						while (itr3.hasNext()) {
							Entry<String, String> item2 = itr3.next();
						
							if (item2.getKey().startsWith("urn:epcglobal:epcis:mda:") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Address") 
									&& !item2.getKey().equals("urn:epcglobal:epcis:mda:City") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Country") 
									&& !item2.getKey().equals("urn:epcglobal:epcis:mda:Name") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Description")) {
								for (int l = 0; l < MasterDataEditParts.getNewReadPointAttr().length; l++) {
									if (!(MasterDataEditParts.getNewReadPointAttr()[l].isEmpty() && MasterDataEditParts.getNewReadPointAttr()[l] == "")) {
									
										switch(l) {
										case 0:container.setAttr1(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 1:container.setAttr2(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 2:container.setAttr3(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 3:container.setAttr4(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 4:container.setAttr5(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 5:container.setAttr6(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 6:container.setAttr7(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 7:container.setAttr8(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 8:container.setAttr9(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 9:container.setAttr10(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 10:container.setAttr11(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 11:container.setAttr12(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 12:container.setAttr13(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 13:container.setAttr14(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 14:container.setAttr15(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 15:container.setAttr16(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 16:container.setAttr17(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 17:container.setAttr18(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 18:container.setAttr19(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 19:container.setAttr20(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;
										}
									}
								}
							}
						}
					}
				}
				
				createWarehouse(warehouse, item1.getValue());
				i++;
			}
		}
		return company;
	}
	
	/**
	 * Create the warehouses from the EPCIS data
	 */
	private static void createWarehouse(AbstractWarehouse warehouse, String ID) {	
		HashMap<String, String> warehouseData = retrieveFromEpcis(ID, EpcisConstants.BUSINESS_LOCATION_ID);
		
		Set<Entry<String, String>> set = warehouseData.entrySet();
		Iterator<Entry<String, String>> itr = set.iterator();
		int i = 0;
		AbstractWarehouse warehouseNew = null;
		
		//add the warehouses
		while (itr.hasNext()) {
			Entry<String, String> item1 = itr.next();
			String warehousechild = "WarehouseChildren" + i;
			if (item1.getKey().equals(warehousechild)) {
				HashMap<String, String> warehouseDataChild = retrieveFromEpcis(item1.getValue(), EpcisConstants.BUSINESS_LOCATION_ID);
				System.out.println("warehousedatachild: " + warehouseDataChild);
				
				//check warehouse type
				if (warehouseDataChild.containsKey("urn:epcglobal:epcis:mda:Type")) {
					if (warehouseDataChild.get("urn:epcglobal:epcis:mda:Type").equals("Warehouse"))
						warehouseNew = MasterDataEditorGMFFactory.eINSTANCE.createWarehouse();
					else if (warehouseDataChild.get("urn:epcglobal:epcis:mda:Type").equals("Room"))
						warehouseNew = MasterDataEditorGMFFactory.eINSTANCE.createRoom();
					else if (warehouseDataChild.get("urn:epcglobal:epcis:mda:Type").equals("Section"))
						warehouseNew = MasterDataEditorGMFFactory.eINSTANCE.createSection();
					else
						warehouseNew = MasterDataEditorGMFFactory.eINSTANCE.createBizLoc();
				}
				else
					warehouseNew = MasterDataEditorGMFFactory.eINSTANCE.createBizLoc();
					
				warehouse.getWarehouses().add(warehouseNew);
				System.out.println(warehouseNew + " added in " + warehouse);
				System.out.println("value:" + item1.getValue());
				
				String warehID = item1.getValue().replaceAll(ID + ",", "");
				warehouseNew.setID(warehID);
				if (warehouseDataChild.containsKey("urn:epcglobal:epcis:mda:Name"))
					warehouseNew.setName(warehouseDataChild.get("urn:epcglobal:epcis:mda:Name"));
				if (warehouseDataChild.containsKey("urn:epcglobal:epcis:mda:Description"))
					warehouseNew.setDescription(warehouseDataChild.get("urn:epcglobal:epcis:mda:Description"));
				if (warehouseDataChild.containsKey("urn:epcglobal:epcis:mda:Type") && warehouseNew instanceof BizLoc)
					warehouseNew.setType(warehouseDataChild.get("urn:epcglobal:epcis:mda:Type"));	
				
				//add custom attributes
				Set<Entry<String, String>> set2 = warehouseData.entrySet();
				Iterator<Entry<String, String>> itr2 = set2.iterator();


				while (itr2.hasNext()) {
					Entry<String, String> item2 = itr2.next();
				
					if (item2.getKey().startsWith("urn:epcglobal:epcis:mda:") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Address") 
							&& !item2.getKey().equals("urn:epcglobal:epcis:mda:City") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Country") 
							&& !item2.getKey().equals("urn:epcglobal:epcis:mda:Name") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Description")) {
						for (int k = 0; k < MasterDataEditParts.getNewWarehouseAttr().length; k++) {
							if (!(MasterDataEditParts.getNewWarehouseAttr()[k].isEmpty() && MasterDataEditParts.getNewWarehouseAttr()[k] == "")) {
							
								switch(k) {
								case 0:warehouse.setAttr1(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 1:warehouse.setAttr2(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 2:warehouse.setAttr3(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 3:warehouse.setAttr4(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 4:warehouse.setAttr5(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 5:warehouse.setAttr6(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 6:warehouse.setAttr7(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 7:warehouse.setAttr8(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 8:warehouse.setAttr9(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 9:warehouse.setAttr10(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 10:warehouse.setAttr11(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 11:warehouse.setAttr12(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 12:warehouse.setAttr13(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 13:warehouse.setAttr14(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 14:warehouse.setAttr15(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 15:warehouse.setAttr16(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 16:warehouse.setAttr17(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 17:warehouse.setAttr18(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 18:warehouse.setAttr19(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;case 19:warehouse.setAttr20(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+warehouseData.get(item2.getKey()));break;
								}
							}
						}
					}
				}
	
				//add the containers
				if (warehouseDataChild.containsKey("urn:epcglobal:epcis:mda:ReadPoint")) {
					String[] split = splitString(warehouseDataChild.get("urn:epcglobal:epcis:mda:ReadPoint"));
					HashMap<String, String> containerData = new HashMap<String, String>();
					
					for (int k = 0; k < split.length; k++) {
					
						containerData = retrieveFromEpcis(split[k], EpcisConstants.READ_POINT_ID);
						System.out.println("myid: " + split[k]);
						System.out.println("containerdata: " + containerData);
						AbstractContainer container = null;
						
						//check container type
						if (containerData.containsKey("urn:epcglobal:epcis:mda:Type")) {
							if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("Container"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createContainer();
							else if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("Conveyor"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createConveyor();
							else if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("Shelf"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createShelf();
							else if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("Gate"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createGate();
							else if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("PalletTruck"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createPalletTruck();
							else if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("PushArm"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createPushArm();
							else if (containerData.get("urn:epcglobal:epcis:mda:Type").equals("HandHeldReader"))
								container = MasterDataEditorGMFFactory.eINSTANCE.createHandHeldReader();
							else
								container = MasterDataEditorGMFFactory.eINSTANCE.createReadPoint();
						}
						else
							container = MasterDataEditorGMFFactory.eINSTANCE.createReadPoint();
						
						warehouse.getContainers().add(container);
						if (containerData.containsKey("ID"))
							container.setID(containerData.get("ID"));
						if (containerData.containsKey("urn:epcglobal:epcis:mda:Name"))
							container.setName(containerData.get("urn:epcglobal:epcis:mda:Name"));
						if (containerData.containsKey("urn:epcglobal:epcis:mda:Description"))
							container.setDescription(containerData.get("urn:epcglobal:epcis:mda:Description"));
						if (containerData.containsKey("urn:epcglobal:epcis:mda:Type") && container instanceof ReadPoint)
							container.setDescription(containerData.get("urn:epcglobal:epcis:mda:Type"));
						
						//add custom attributes
						Set<Entry<String, String>> set3 = containerData.entrySet();
						Iterator<Entry<String, String>> itr3 = set3.iterator();


						while (itr3.hasNext()) {
							Entry<String, String> item2 = itr3.next();
						
							if (item2.getKey().startsWith("urn:epcglobal:epcis:mda:") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Address") 
									&& !item2.getKey().equals("urn:epcglobal:epcis:mda:City") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Country") 
									&& !item2.getKey().equals("urn:epcglobal:epcis:mda:Name") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Description")) {
								for (int l = 0; l < MasterDataEditParts.getNewReadPointAttr().length; l++) {
									if (!(MasterDataEditParts.getNewReadPointAttr()[l].isEmpty() && MasterDataEditParts.getNewReadPointAttr()[l] == "")) {
									
										switch(l) {
										case 0:container.setAttr1(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 1:container.setAttr2(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 2:container.setAttr3(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 3:container.setAttr4(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 4:container.setAttr5(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 5:container.setAttr6(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 6:container.setAttr7(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 7:container.setAttr8(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 8:container.setAttr9(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 9:container.setAttr10(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 10:container.setAttr11(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 11:container.setAttr12(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 12:container.setAttr13(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 13:container.setAttr14(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 14:container.setAttr15(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 15:container.setAttr16(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 16:container.setAttr17(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 17:container.setAttr18(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 18:container.setAttr19(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;case 19:container.setAttr20(item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "")+"_"+containerData.get(item2.getKey()));break;
										}
									}
								}
							}
						}
					}
				}
				
				createWarehouse(warehouseNew, item1.getValue());
				i++;
			}
		}
	}
	
	/**
	 * Split the ReadPoint value of a Warehouse
	 */
	private static String[] splitString(String s) {
		String[] temp = null;
		temp = s.split(",");
		
		return temp;		
	}
	
	/**
	 * Sort the HashMap based on its values
	 */
	private static HashMap<String, String> sortByValue(HashMap<String, String> map) {
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
	private static HashMap<String, String> sortByKey(HashMap<String, String> map) {
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
