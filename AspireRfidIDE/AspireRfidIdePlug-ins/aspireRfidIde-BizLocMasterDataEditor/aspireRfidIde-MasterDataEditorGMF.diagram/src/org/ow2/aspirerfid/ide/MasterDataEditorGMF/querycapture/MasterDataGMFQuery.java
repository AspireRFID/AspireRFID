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
//import org.ow2.aspirerfid.ide.masterdata.classes.EpcisConstants;
//import org.ow2.aspirerfid.ide.masterdata.tools.QueryClientGuiHelper;
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
//		else {
//			String[] epcisAttr = null;
//			String[] preferenceAttr = MasterDataEditParts.getNewCompanyAttr();
//			MasterDataEditParts.setNewCompanyAttr(new String[epcisAttr.length + preferenceAttr.length]);
//			//store.getString(MasterDataEditParts.getNewCompanyAttr()[i]
//			for (int i = 0; i < MasterDataEditParts.getNewCompanyAttr().length; i++) {
//				MasterDataEditParts.setNewCompanyAttr(MasterDataEditParts.getNewCompanyAttr());
//			}
//		}
		
		
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
				
				warehouse.setID(item1.getValue());
				if (warehouseData.containsKey("urn:epcglobal:epcis:mda:Name"))
					warehouse.setName(warehouseData.get("urn:epcglobal:epcis:mda:Name"));
				if (warehouseData.containsKey("urn:epcglobal:epcis:mda:Description"))
					warehouse.setDescription(warehouseData.get("urn:epcglobal:epcis:mda:Description"));
				if (warehouseData.containsKey("urn:epcglobal:epcis:mda:Type") && warehouse instanceof BizLoc)
					warehouse.setType(warehouseData.get("urn:epcglobal:epcis:mda:Type"));
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
				warehouseNew.setID(item1.getValue());
				if (warehouseDataChild.containsKey("urn:epcglobal:epcis:mda:Name"))
					warehouseNew.setName(warehouseDataChild.get("urn:epcglobal:epcis:mda:Name"));
				if (warehouseDataChild.containsKey("urn:epcglobal:epcis:mda:Description"))
					warehouseNew.setDescription(warehouseDataChild.get("urn:epcglobal:epcis:mda:Description"));
				if (warehouseDataChild.containsKey("urn:epcglobal:epcis:mda:Type") && warehouseNew instanceof BizLoc)
					warehouseNew.setType(warehouseDataChild.get("urn:epcglobal:epcis:mda:Type"));	
	
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
