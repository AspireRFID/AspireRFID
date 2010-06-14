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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	 * The logger.
	 */
	private static Log log = LogFactory.getLog(MasterDataGMFQuery.class);
	
	/**
	 * If the diagram will be created from the EPCIS data
	 */
	private static boolean isFromEPCIS = false;
	
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
	 * @return the isFromEPCIS
	 */
	public static boolean isFromEPCIS() {
		return isFromEPCIS;
	}

	/**
	 * @param isFromEPCIS the isFromEPCIS to set
	 */
	public static void setFromEPCIS(boolean isFromEPCIS) {
		MasterDataGMFQuery.isFromEPCIS = isFromEPCIS;
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
	 * Create a new diagram from the EPCIS data
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
		
		Set<Entry<String, String>> set1 = companyData.entrySet();
		Iterator<Entry<String, String>> itr1 = set1.iterator();

		while (itr1.hasNext()) {
			Entry<String, String> item1 = itr1.next();
		
			if (item1.getKey().startsWith("urn:epcglobal:epcis:mda:") && !item1.getKey().equals("urn:epcglobal:epcis:mda:Address") 
					&& !item1.getKey().equals("urn:epcglobal:epcis:mda:City") && !item1.getKey().equals("urn:epcglobal:epcis:mda:Country") 
					&& !item1.getKey().equals("urn:epcglobal:epcis:mda:Name") && !item1.getKey().equals("urn:epcglobal:epcis:mda:Description")) {
				for (int i = 0; i < MasterDataEditParts.getNewCompanyAttr().length; i++) {
					if ((item1.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "").equals(MasterDataEditParts.getNewCompanyAttr()[i]))) {
						switch(i) {
						case 0:company.setAttr1(companyData.get(item1.getKey()));break;case 1:company.setAttr2(companyData.get(item1.getKey()));break;case 2:company.setAttr3(companyData.get(item1.getKey()));break;case 3:company.setAttr4(companyData.get(item1.getKey()));break;case 4:company.setAttr5(companyData.get(item1.getKey()));break;case 5:company.setAttr6(companyData.get(item1.getKey()));break;case 6:company.setAttr7(companyData.get(item1.getKey()));break;case 7:company.setAttr8(companyData.get(item1.getKey()));break;case 8:company.setAttr9(companyData.get(item1.getKey()));break;case 9:company.setAttr10(companyData.get(item1.getKey()));break;case 10:company.setAtt11(companyData.get(item1.getKey()));break;case 11:company.setAttr12(companyData.get(item1.getKey()));break;case 12:company.setAttr13(companyData.get(item1.getKey()));break;case 13:company.setAttr14(companyData.get(item1.getKey()));break;case 14:company.setAttr15(companyData.get(item1.getKey()));break;case 15:company.setAttr16(companyData.get(item1.getKey()));break;case 16:company.setAttr17(companyData.get(item1.getKey()));break;case 17:company.setAttr18(companyData.get(item1.getKey()));break;case 18:company.setAttr19(companyData.get(item1.getKey()));break;case 19:company.setAttr20(companyData.get(item1.getKey()));break;case 20:company.setAttr21(companyData.get(item1.getKey()));break;case 21:company.setAttr22(companyData.get(item1.getKey()));break;case 22:company.setAttr23(companyData.get(item1.getKey()));break;case 23:company.setAttr24(companyData.get(item1.getKey()));break;case 24:company.setAttr25(companyData.get(item1.getKey()));break;case 25:company.setAttr26(companyData.get(item1.getKey()));break;case 26:company.setAttr27(companyData.get(item1.getKey()));break;case 27:company.setAttr28(companyData.get(item1.getKey()));break;case 28:company.setAttr29(companyData.get(item1.getKey()));break;case 29:company.setAttr30(companyData.get(item1.getKey()));break;case 30:company.setAttr31(companyData.get(item1.getKey()));break;case 31:company.setAttr32(companyData.get(item1.getKey()));break;case 32:company.setAttr33(companyData.get(item1.getKey()));break;case 33:company.setAttr34(companyData.get(item1.getKey()));break;case 34:company.setAttr35(companyData.get(item1.getKey()));break;case 35:company.setAttr36(companyData.get(item1.getKey()));break;case 36:company.setAttr37(companyData.get(item1.getKey()));break;case 37:company.setAttr38(companyData.get(item1.getKey()));break;case 38:company.setAttr39(companyData.get(item1.getKey()));break;case 39:company.setAttr40(companyData.get(item1.getKey()));break;case 40:company.setAttr41(companyData.get(item1.getKey()));break;case 41:company.setAttr42(companyData.get(item1.getKey()));break;case 42:company.setAttr43(companyData.get(item1.getKey()));break;case 43:company.setAttr44(companyData.get(item1.getKey()));break;case 44:company.setAttr45(companyData.get(item1.getKey()));break;case 45:company.setAttr46(companyData.get(item1.getKey()));break;case 46:company.setAttr47(companyData.get(item1.getKey()));break;case 47:company.setAttr48(companyData.get(item1.getKey()));break;case 48:company.setAttr49(companyData.get(item1.getKey()));break;case 49:company.setAttr50(companyData.get(item1.getKey()));
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
				
				Set<Entry<String, String>> set2 = warehouseData.entrySet();
				Iterator<Entry<String, String>> itr2 = set2.iterator();

				while (itr2.hasNext()) {
					Entry<String, String> item2 = itr2.next();
				
					if (item2.getKey().startsWith("urn:epcglobal:epcis:mda:") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Type")
							&& !item2.getKey().equals("urn:epcglobal:epcis:mda:Name") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Description")
							&& !item2.getKey().equals("urn:epcglobal:epcis:mda:ReadPoint")) {
						for (int j = 0; j < MasterDataEditParts.getNewWarehouseAttr().length; j++) {
							if ((item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "").equals(MasterDataEditParts.getNewWarehouseAttr()[j]))) {
								switch(j) {
								case 0:warehouse.setAttr1(warehouseData.get(item2.getKey()));break;case 1:warehouse.setAttr2(warehouseData.get(item2.getKey()));break;case 2:warehouse.setAttr3(warehouseData.get(item2.getKey()));break;case 3:warehouse.setAttr4(warehouseData.get(item2.getKey()));break;case 4:warehouse.setAttr5(warehouseData.get(item2.getKey()));break;case 5:warehouse.setAttr6(warehouseData.get(item2.getKey()));break;case 6:warehouse.setAttr7(warehouseData.get(item2.getKey()));break;case 7:warehouse.setAttr8(warehouseData.get(item2.getKey()));break;case 8:warehouse.setAttr9(warehouseData.get(item2.getKey()));break;case 9:warehouse.setAttr10(warehouseData.get(item2.getKey()));break;case 10:warehouse.setAtt11(warehouseData.get(item2.getKey()));break;case 11:warehouse.setAttr12(warehouseData.get(item2.getKey()));break;case 12:warehouse.setAttr13(warehouseData.get(item2.getKey()));break;case 13:warehouse.setAttr14(warehouseData.get(item2.getKey()));break;case 14:warehouse.setAttr15(warehouseData.get(item2.getKey()));break;case 15:warehouse.setAttr16(warehouseData.get(item2.getKey()));break;case 16:warehouse.setAttr17(warehouseData.get(item2.getKey()));break;case 17:warehouse.setAttr18(warehouseData.get(item2.getKey()));break;case 18:warehouse.setAttr19(warehouseData.get(item2.getKey()));break;case 19:warehouse.setAttr20(warehouseData.get(item2.getKey()));break;case 20:warehouse.setAttr21(warehouseData.get(item2.getKey()));break;case 21:warehouse.setAttr22(warehouseData.get(item2.getKey()));break;case 22:warehouse.setAttr23(warehouseData.get(item2.getKey()));break;case 23:warehouse.setAttr24(warehouseData.get(item2.getKey()));break;case 24:warehouse.setAttr25(warehouseData.get(item2.getKey()));break;case 25:warehouse.setAttr26(warehouseData.get(item2.getKey()));break;case 26:warehouse.setAttr27(warehouseData.get(item2.getKey()));break;case 27:warehouse.setAttr28(warehouseData.get(item2.getKey()));break;case 28:warehouse.setAttr29(warehouseData.get(item2.getKey()));break;case 29:warehouse.setAttr30(warehouseData.get(item2.getKey()));break;case 30:warehouse.setAttr31(warehouseData.get(item2.getKey()));break;case 31:warehouse.setAttr32(warehouseData.get(item2.getKey()));break;case 32:warehouse.setAttr33(warehouseData.get(item2.getKey()));break;case 33:warehouse.setAttr34(warehouseData.get(item2.getKey()));break;case 34:warehouse.setAttr35(warehouseData.get(item2.getKey()));break;case 35:warehouse.setAttr36(warehouseData.get(item2.getKey()));break;case 36:warehouse.setAttr37(warehouseData.get(item2.getKey()));break;case 37:warehouse.setAttr38(warehouseData.get(item2.getKey()));break;case 38:warehouse.setAttr39(warehouseData.get(item2.getKey()));break;case 39:warehouse.setAttr40(warehouseData.get(item2.getKey()));break;case 40:warehouse.setAttr41(warehouseData.get(item2.getKey()));break;case 41:warehouse.setAttr42(warehouseData.get(item2.getKey()));break;case 42:warehouse.setAttr43(warehouseData.get(item2.getKey()));break;case 43:warehouse.setAttr44(warehouseData.get(item2.getKey()));break;case 44:warehouse.setAttr45(warehouseData.get(item2.getKey()));break;case 45:warehouse.setAttr46(warehouseData.get(item2.getKey()));break;case 46:warehouse.setAttr47(warehouseData.get(item2.getKey()));break;case 47:warehouse.setAttr48(warehouseData.get(item2.getKey()));break;case 48:warehouse.setAttr49(warehouseData.get(item2.getKey()));break;case 49:warehouse.setAttr50(warehouseData.get(item2.getKey()));
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
						
						Set<Entry<String, String>> set3 = containerData.entrySet();
						Iterator<Entry<String, String>> itr3 = set3.iterator();

						while (itr3.hasNext()) {
							Entry<String, String> item2 = itr3.next();
						
							if (item2.getKey().startsWith("urn:epcglobal:epcis:mda:") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Type")
									&& !item2.getKey().equals("urn:epcglobal:epcis:mda:Name") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Description")) {
								for (int j = 0; j < MasterDataEditParts.getNewReadPointAttr().length; j++) {
									if ((item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "").equals(MasterDataEditParts.getNewReadPointAttr()[j]))) {
										switch(j) {
										case 0:container.setAttr1(containerData.get(item2.getKey()));break;case 1:container.setAttr2(containerData.get(item2.getKey()));break;case 2:container.setAttr3(containerData.get(item2.getKey()));break;case 3:container.setAttr4(containerData.get(item2.getKey()));break;case 4:container.setAttr5(containerData.get(item2.getKey()));break;case 5:container.setAttr6(containerData.get(item2.getKey()));break;case 6:container.setAttr7(containerData.get(item2.getKey()));break;case 7:container.setAttr8(containerData.get(item2.getKey()));break;case 8:container.setAttr9(containerData.get(item2.getKey()));break;case 9:container.setAttr10(containerData.get(item2.getKey()));break;case 10:container.setAtt11(containerData.get(item2.getKey()));break;case 11:container.setAttr12(containerData.get(item2.getKey()));break;case 12:container.setAttr13(containerData.get(item2.getKey()));break;case 13:container.setAttr14(containerData.get(item2.getKey()));break;case 14:container.setAttr15(containerData.get(item2.getKey()));break;case 15:container.setAttr16(containerData.get(item2.getKey()));break;case 16:container.setAttr17(containerData.get(item2.getKey()));break;case 17:container.setAttr18(containerData.get(item2.getKey()));break;case 18:container.setAttr19(containerData.get(item2.getKey()));break;case 19:container.setAttr20(containerData.get(item2.getKey()));break;case 20:container.setAttr21(containerData.get(item2.getKey()));break;case 21:container.setAttr22(containerData.get(item2.getKey()));break;case 22:container.setAttr23(containerData.get(item2.getKey()));break;case 23:container.setAttr24(containerData.get(item2.getKey()));break;case 24:container.setAttr25(containerData.get(item2.getKey()));break;case 25:container.setAttr26(containerData.get(item2.getKey()));break;case 26:container.setAttr27(containerData.get(item2.getKey()));break;case 27:container.setAttr28(containerData.get(item2.getKey()));break;case 28:container.setAttr29(containerData.get(item2.getKey()));break;case 29:container.setAttr30(containerData.get(item2.getKey()));break;case 30:container.setAttr31(containerData.get(item2.getKey()));break;case 31:container.setAttr32(containerData.get(item2.getKey()));break;case 32:container.setAttr33(containerData.get(item2.getKey()));break;case 33:container.setAttr34(containerData.get(item2.getKey()));break;case 34:container.setAttr35(containerData.get(item2.getKey()));break;case 35:container.setAttr36(containerData.get(item2.getKey()));break;case 36:container.setAttr37(containerData.get(item2.getKey()));break;case 37:container.setAttr38(containerData.get(item2.getKey()));break;case 38:container.setAttr39(containerData.get(item2.getKey()));break;case 39:container.setAttr40(containerData.get(item2.getKey()));break;case 40:container.setAttr41(containerData.get(item2.getKey()));break;case 41:container.setAttr42(containerData.get(item2.getKey()));break;case 42:container.setAttr43(containerData.get(item2.getKey()));break;case 43:container.setAttr44(containerData.get(item2.getKey()));break;case 44:container.setAttr45(containerData.get(item2.getKey()));break;case 45:container.setAttr46(containerData.get(item2.getKey()));break;case 46:container.setAttr47(containerData.get(item2.getKey()));break;case 47:container.setAttr48(containerData.get(item2.getKey()));break;case 48:container.setAttr49(containerData.get(item2.getKey()));break;case 49:container.setAttr50(containerData.get(item2.getKey()));
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
	 * Create the Warehouses
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
				
				Set<Entry<String, String>> set2 = warehouseDataChild.entrySet();
				Iterator<Entry<String, String>> itr2 = set2.iterator();

				while (itr2.hasNext()) {
					Entry<String, String> item2 = itr2.next();
				
					if (item2.getKey().startsWith("urn:epcglobal:epcis:mda:") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Type")
							&& !item2.getKey().equals("urn:epcglobal:epcis:mda:Name") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Description")
						&& !item2.getKey().equals("urn:epcglobal:epcis:mda:ReadPoint")) {
						for (int j = 0; j < MasterDataEditParts.getNewWarehouseAttr().length; j++) {
							if ((item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "").equals(MasterDataEditParts.getNewWarehouseAttr()[j]))) {
								switch(j) {
								case 0:warehouse.setAttr1(warehouseDataChild.get(item2.getKey()));break;case 1:warehouse.setAttr2(warehouseDataChild.get(item2.getKey()));break;case 2:warehouse.setAttr3(warehouseDataChild.get(item2.getKey()));break;case 3:warehouse.setAttr4(warehouseDataChild.get(item2.getKey()));break;case 4:warehouse.setAttr5(warehouseDataChild.get(item2.getKey()));break;case 5:warehouse.setAttr6(warehouseDataChild.get(item2.getKey()));break;case 6:warehouse.setAttr7(warehouseDataChild.get(item2.getKey()));break;case 7:warehouse.setAttr8(warehouseDataChild.get(item2.getKey()));break;case 8:warehouse.setAttr9(warehouseDataChild.get(item2.getKey()));break;case 9:warehouse.setAttr10(warehouseDataChild.get(item2.getKey()));break;case 10:warehouse.setAtt11(warehouseDataChild.get(item2.getKey()));break;case 11:warehouse.setAttr12(warehouseDataChild.get(item2.getKey()));break;case 12:warehouse.setAttr13(warehouseDataChild.get(item2.getKey()));break;case 13:warehouse.setAttr14(warehouseDataChild.get(item2.getKey()));break;case 14:warehouse.setAttr15(warehouseDataChild.get(item2.getKey()));break;case 15:warehouse.setAttr16(warehouseDataChild.get(item2.getKey()));break;case 16:warehouse.setAttr17(warehouseDataChild.get(item2.getKey()));break;case 17:warehouse.setAttr18(warehouseDataChild.get(item2.getKey()));break;case 18:warehouse.setAttr19(warehouseDataChild.get(item2.getKey()));break;case 19:warehouse.setAttr20(warehouseDataChild.get(item2.getKey()));break;case 20:warehouse.setAttr21(warehouseDataChild.get(item2.getKey()));break;case 21:warehouse.setAttr22(warehouseDataChild.get(item2.getKey()));break;case 22:warehouse.setAttr23(warehouseDataChild.get(item2.getKey()));break;case 23:warehouse.setAttr24(warehouseDataChild.get(item2.getKey()));break;case 24:warehouse.setAttr25(warehouseDataChild.get(item2.getKey()));break;case 25:warehouse.setAttr26(warehouseDataChild.get(item2.getKey()));break;case 26:warehouse.setAttr27(warehouseDataChild.get(item2.getKey()));break;case 27:warehouse.setAttr28(warehouseDataChild.get(item2.getKey()));break;case 28:warehouse.setAttr29(warehouseDataChild.get(item2.getKey()));break;case 29:warehouse.setAttr30(warehouseDataChild.get(item2.getKey()));break;case 30:warehouse.setAttr31(warehouseDataChild.get(item2.getKey()));break;case 31:warehouse.setAttr32(warehouseDataChild.get(item2.getKey()));break;case 32:warehouse.setAttr33(warehouseDataChild.get(item2.getKey()));break;case 33:warehouse.setAttr34(warehouseDataChild.get(item2.getKey()));break;case 34:warehouse.setAttr35(warehouseDataChild.get(item2.getKey()));break;case 35:warehouse.setAttr36(warehouseDataChild.get(item2.getKey()));break;case 36:warehouse.setAttr37(warehouseDataChild.get(item2.getKey()));break;case 37:warehouse.setAttr38(warehouseDataChild.get(item2.getKey()));break;case 38:warehouse.setAttr39(warehouseDataChild.get(item2.getKey()));break;case 39:warehouse.setAttr40(warehouseDataChild.get(item2.getKey()));break;case 40:warehouse.setAttr41(warehouseDataChild.get(item2.getKey()));break;case 41:warehouse.setAttr42(warehouseDataChild.get(item2.getKey()));break;case 42:warehouse.setAttr43(warehouseDataChild.get(item2.getKey()));break;case 43:warehouse.setAttr44(warehouseDataChild.get(item2.getKey()));break;case 44:warehouse.setAttr45(warehouseDataChild.get(item2.getKey()));break;case 45:warehouse.setAttr46(warehouseDataChild.get(item2.getKey()));break;case 46:warehouse.setAttr47(warehouseDataChild.get(item2.getKey()));break;case 47:warehouse.setAttr48(warehouseDataChild.get(item2.getKey()));break;case 48:warehouse.setAttr49(warehouseDataChild.get(item2.getKey()));break;case 49:warehouse.setAttr50(warehouseDataChild.get(item2.getKey()));
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
						
						Set<Entry<String, String>> set3 = containerData.entrySet();
						Iterator<Entry<String, String>> itr3 = set3.iterator();

						while (itr3.hasNext()) {
							Entry<String, String> item2 = itr3.next();
						
							if (item2.getKey().startsWith("urn:epcglobal:epcis:mda:") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Type")
									&& !item2.getKey().equals("urn:epcglobal:epcis:mda:Name") && !item2.getKey().equals("urn:epcglobal:epcis:mda:Description")) {
								for (int j = 0; j < MasterDataEditParts.getNewReadPointAttr().length; j++) {
									if ((item2.getKey().replaceFirst("urn:epcglobal:epcis:mda:", "").equals(MasterDataEditParts.getNewReadPointAttr()[j]))) {
										switch(j) {
										case 0:container.setAttr1(containerData.get(item2.getKey()));break;case 1:container.setAttr2(containerData.get(item2.getKey()));break;case 2:container.setAttr3(containerData.get(item2.getKey()));break;case 3:container.setAttr4(containerData.get(item2.getKey()));break;case 4:container.setAttr5(containerData.get(item2.getKey()));break;case 5:container.setAttr6(containerData.get(item2.getKey()));break;case 6:container.setAttr7(containerData.get(item2.getKey()));break;case 7:container.setAttr8(containerData.get(item2.getKey()));break;case 8:container.setAttr9(containerData.get(item2.getKey()));break;case 9:container.setAttr10(containerData.get(item2.getKey()));break;case 10:container.setAtt11(containerData.get(item2.getKey()));break;case 11:container.setAttr12(containerData.get(item2.getKey()));break;case 12:container.setAttr13(containerData.get(item2.getKey()));break;case 13:container.setAttr14(containerData.get(item2.getKey()));break;case 14:container.setAttr15(containerData.get(item2.getKey()));break;case 15:container.setAttr16(containerData.get(item2.getKey()));break;case 16:container.setAttr17(containerData.get(item2.getKey()));break;case 17:container.setAttr18(containerData.get(item2.getKey()));break;case 18:container.setAttr19(containerData.get(item2.getKey()));break;case 19:container.setAttr20(containerData.get(item2.getKey()));break;case 20:container.setAttr21(containerData.get(item2.getKey()));break;case 21:container.setAttr22(containerData.get(item2.getKey()));break;case 22:container.setAttr23(containerData.get(item2.getKey()));break;case 23:container.setAttr24(containerData.get(item2.getKey()));break;case 24:container.setAttr25(containerData.get(item2.getKey()));break;case 25:container.setAttr26(containerData.get(item2.getKey()));break;case 26:container.setAttr27(containerData.get(item2.getKey()));break;case 27:container.setAttr28(containerData.get(item2.getKey()));break;case 28:container.setAttr29(containerData.get(item2.getKey()));break;case 29:container.setAttr30(containerData.get(item2.getKey()));break;case 30:container.setAttr31(containerData.get(item2.getKey()));break;case 31:container.setAttr32(containerData.get(item2.getKey()));break;case 32:container.setAttr33(containerData.get(item2.getKey()));break;case 33:container.setAttr34(containerData.get(item2.getKey()));break;case 34:container.setAttr35(containerData.get(item2.getKey()));break;case 35:container.setAttr36(containerData.get(item2.getKey()));break;case 36:container.setAttr37(containerData.get(item2.getKey()));break;case 37:container.setAttr38(containerData.get(item2.getKey()));break;case 38:container.setAttr39(containerData.get(item2.getKey()));break;case 39:container.setAttr40(containerData.get(item2.getKey()));break;case 40:container.setAttr41(containerData.get(item2.getKey()));break;case 41:container.setAttr42(containerData.get(item2.getKey()));break;case 42:container.setAttr43(containerData.get(item2.getKey()));break;case 43:container.setAttr44(containerData.get(item2.getKey()));break;case 44:container.setAttr45(containerData.get(item2.getKey()));break;case 45:container.setAttr46(containerData.get(item2.getKey()));break;case 46:container.setAttr47(containerData.get(item2.getKey()));break;case 47:container.setAttr48(containerData.get(item2.getKey()));break;case 48:container.setAttr49(containerData.get(item2.getKey()));break;case 49:container.setAttr50(containerData.get(item2.getKey()));
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
