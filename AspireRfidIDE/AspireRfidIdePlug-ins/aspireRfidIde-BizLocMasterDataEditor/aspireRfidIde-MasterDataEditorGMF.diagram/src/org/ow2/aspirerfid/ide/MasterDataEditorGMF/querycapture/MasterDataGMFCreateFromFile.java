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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.emf.common.util.URI;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFFactory;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.ReadPoint;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class MasterDataGMFCreateFromFile {
	
	/**
	 * The APDL xml file
	 */
	private static Document doc = null;
	
	/**
	 * If the diagram will be created from the APDL file
	 */
	private static boolean isFromApdl = false;
	
	/**
	 * If the diagram will be created from the BPWME APDL file
	 */
	private static boolean isFromBpwmeApdl = false;
	
	/**
	 * All the CLCBProc names of the APDL file
	 */
	private static ArrayList<String> clcProcNames;
	
	/**
	 * @return the clcProcNames
	 */
	public static ArrayList<String> getClcProcNames() {
		return clcProcNames;
	}

	/**
	 * @param clcProcNames the clcProcNames to set
	 */
	public static void setClcProcNames(ArrayList<String> clcProcNames) {
		MasterDataGMFCreateFromFile.clcProcNames = clcProcNames;
	}

	/**
	 * @return the isFromApdl
	 */
	public static boolean isFromApdl() {
		return isFromApdl;
	}

	/**
	 * @param isFromApdl the isFromApdl to set
	 */
	public static void setFromApdl(boolean isFromApdl) {
		MasterDataGMFCreateFromFile.isFromApdl = isFromApdl;
	}

	/**
	 * @return the isFromBpwmeApdl
	 */
	public static boolean isFromBpwmeApdl() {
		return isFromBpwmeApdl;
	}

	/**
	 * @param isFromBpwmeApdl the isFromBpwmeApdl to set
	 */
	public static void setFromBpwmeApdl(boolean isFromBpwmeApdl) {
		MasterDataGMFCreateFromFile.isFromBpwmeApdl = isFromBpwmeApdl;
	}

	/**
	 * Open the APDL xml file
	 */
	public static void openApdlFile(URI fileURI) {
		try {
			String URI = fileURI.toString();
			File file = new File(URI);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(file);
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get all the CLCBProc names if they have MasterData from the APDL file
	 */
	public static void setClcbProcNamesWithMasterDataFromApdl() {
		clcProcNames = new ArrayList<String>();
		
		try {
			NodeList olcbProcs = doc.getElementsByTagName("ns7:OLCBProc");
			
			for (int i = 0; i < olcbProcs.getLength(); i++) {
				Element olcbProc = (Element) olcbProcs.item(i);
				NodeList clcbProcs = olcbProc.getElementsByTagName("ns7:CLCBProc");
				String clcbName = "";
				
				for (int j = 0; j < clcbProcs.getLength(); j++) {
					Element clcbProc = (Element) clcbProcs.item(j);
					NamedNodeMap nnm1 = clcbProc.getAttributes();

					for (int t = 0; t < nnm1.getLength(); t++) {
						Attr attr1 = (Attr) nnm1.item(t);
						if (attr1.getNodeName().equals("name")) {
							clcbName = attr1.getNodeValue(); 
						}
					}
					
					NodeList vocabularies = clcbProc.getElementsByTagName("Vocabulary");
					
					for (int k = 0; k < vocabularies.getLength(); k++) {
						Element vocabulary = (Element) vocabularies.item(k);
						
						if (vocabulary.getAttribute("type").equals("urn:epcglobal:epcis:vtype:BusinessLocation")) {			
							NodeList vocabularyElementLists = vocabulary.getElementsByTagName("VocabularyElementList");
						
							Element vocabularyElementList = (Element) vocabularyElementLists.item(0);
							NodeList vocabularyElements = vocabularyElementList.getElementsByTagName("VocabularyElement");
							
							Element vocabularyElement = (Element) vocabularyElements.item(0);
							NamedNodeMap nnm2 = vocabularyElement.getAttributes();
							
							for (int n = 0; n < nnm2.getLength(); n++) {
								Attr attr2 = (Attr) nnm2.item(n);
								
								if (attr2.getNodeName().equals("id")) {
									if (!(attr2.getNodeValue().isEmpty() && attr2.getNodeValue() == "")) {
										System.out.println(attr2.getNodeValue());
										clcProcNames.add(clcbName);
										break;
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < clcProcNames.size(); i++)
			System.out.println("clcb: " + clcProcNames.get(i));
	}
	
	/**
	 * Get a HashMap with the company data from the APDL file
	 */
	private static HashMap<String, String> getCompanyDataFromApdl(String clcbName) {
		HashMap<String, String> companyData = new HashMap<String, String>();
		String companyID = "";
		
		try {
			boolean found = false;
			
			NodeList olcbProcs = doc.getElementsByTagName("ns7:OLCBProc");
			
			for (int o = 0; o < olcbProcs.getLength(); o++) {
				Element olcbProc = (Element) olcbProcs.item(o);
				NodeList clcbProcs = olcbProc.getElementsByTagName("ns7:CLCBProc");

				for (int c = 0; c < clcbProcs.getLength(); c++) {
					Element clcbProc = (Element) clcbProcs.item(c);
					NamedNodeMap nnm2 = clcbProc.getAttributes();

					for (int t = 0; t < nnm2.getLength(); t++) {
						Attr attr2 = (Attr) nnm2.item(t);
						if (attr2.getNodeName().equals("name")) {
							if (attr2.getValue().equals(clcbName)) {
								found = true;
								System.out.println("value: " + attr2.getValue());
							}
						}
					}
					
					if (found) {
						found = false;
						NodeList vocabularies = clcbProc.getElementsByTagName("Vocabulary");
			
						for (int i = 0; i < vocabularies.getLength(); i++) {
							Element vocabulary = (Element) vocabularies.item(i);
			
							if (vocabulary.getAttribute("type").equals("urn:epcglobal:epcis:vtype:BusinessLocation")) {
								NodeList vocabularyElementLists = vocabulary.getElementsByTagName("VocabularyElementList");
			
								for (int j = 0; j < vocabularyElementLists.getLength(); j++) {
									Element vocabularyElementList = (Element) vocabularyElementLists.item(j);
									NodeList vocabularyElements = vocabularyElementList.getElementsByTagName("VocabularyElement");
			
									//insert the company with its attributes
									for (int k = 0; k < 1; k++) {
										Element vocabularyElement = (Element) vocabularyElements.item(k);
										NamedNodeMap nnm = vocabularyElement.getAttributes();
			
										for (int l = 0; l < nnm.getLength(); l++) {
											Attr attr = (Attr) nnm.item(l);
			
											companyData.put("ID", attr.getNodeValue());
											companyID = attr.getNodeValue();
										}
			
										NodeList attributes = vocabularyElement.getElementsByTagName("attribute");
			
										for (int m = 0; m < attributes.getLength(); m++) {
											Element attribute = (Element) attributes.item(m);
											NamedNodeMap nnm1 = attribute.getAttributes();
			
											String name = "";
											String value = "";
											boolean isName = false;
											boolean isValue = false;
											boolean isData = false;
			
											for (int n = 0; n < nnm1.getLength(); n++) {
												Attr attr1 = (Attr) nnm1.item(n);
												
												if (isData) {
													isName = false;
													isValue = false;
													isData = false;
												}
																					
												if (attr1.getNodeName().equals("id")) {
													name = attr1.getNodeValue();
													isName = true;
												}
												else if (attr1.getNodeName().equals("value")) {
													value = attr1.getNodeValue();
													isValue = true;
												}
												
												if (isName && isValue) {
													companyData.put(name, value);
													isData = true;
												}
											}
										}
									}
									
									//insert the warehouse children IDs of the company
									for (int k = 1; k < vocabularyElements.getLength(); k++) {
										Element vocabularyElement = (Element) vocabularyElements.item(k);
										NamedNodeMap nnm = vocabularyElement.getAttributes();
			
										//count the warehouse children
										int warehouseCount = 0;
										
										for (int l = 0; l < nnm.getLength(); l++) {
											Attr attr = (Attr) nnm.item(l);
									
											if (attr.getNodeName().startsWith(companyID) && attr.getNodeName().contains(",")) {
												companyData.put("WarehouseChildren" + warehouseCount, attr.getNodeValue());
												warehouseCount++;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			insertWarehouseChildren(companyID, companyData, clcbName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return companyData;
	}
	
	/**
	 * Insert the warehouse children from the APDL file
	 */
	private static void insertWarehouseChildren(String ID, HashMap<String, String> data, String clcbName) {
		//count the warehouse children
		int warehouseCount = 0;
		
		try {
			boolean found = false;
			
			NodeList olcbProcs = doc.getElementsByTagName("ns7:OLCBProc");
			
			for (int o = 0; o < olcbProcs.getLength(); o++) {
				Element olcbProc = (Element) olcbProcs.item(o);
				NodeList clcbProcs = olcbProc.getElementsByTagName("ns7:CLCBProc");

				for (int c = 0; c < clcbProcs.getLength(); c++) {
					Element clcbProc = (Element) clcbProcs.item(c);
					NamedNodeMap nnm2 = clcbProc.getAttributes();

					for (int t = 0; t < nnm2.getLength(); t++) {
						Attr attr2 = (Attr) nnm2.item(t);
						if (attr2.getNodeName().equals("name")) {
							if (attr2.getValue().equals(clcbName)) {
								found = true;
							}
						}
					}
					
					if (found) {
						found = false;
						NodeList vocabularies = clcbProc.getElementsByTagName("Vocabulary");
	
						for (int i = 0; i < vocabularies.getLength(); i++) {
							Element vocabulary = (Element) vocabularies.item(i);
			
							if (vocabulary.getAttribute("type").equals("urn:epcglobal:epcis:vtype:BusinessLocation")) {
								NodeList vocabularyElementLists = vocabulary.getElementsByTagName("VocabularyElementList");
			
								for (int j = 0; j < vocabularyElementLists.getLength(); j++) {
									Element vocabularyElementList = (Element) vocabularyElementLists.item(j);
									NodeList vocabularyElements = vocabularyElementList.getElementsByTagName("VocabularyElement");
			
									for (int k = 0; k < vocabularyElements.getLength(); k++) {
										Element vocabularyElement = (Element) vocabularyElements.item(k);
										NamedNodeMap nnm = vocabularyElement.getAttributes();
			
										for (int l = 0; l < nnm.getLength(); l++) {
											Attr attr = (Attr) nnm.item(l);
			
											String warehID = attr.getNodeValue().replaceAll(ID + ",", "");
					
											//insert only the warehouse children - not this ID
											if (attr.getNodeValue().startsWith(ID) && !warehID.contains(",") && !attr.getNodeValue().equals(ID)) {
												data.put("WarehouseChildren" + warehouseCount, attr.getNodeValue());
												warehouseCount++;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Get a HashMap with the warehouse data from the APDL file
	 */
	private static HashMap<String, String> getWarehouseDataFromApdl(String warehouseID, String clcbName) {
		HashMap<String, String> warehouseData = new HashMap<String, String>();
		//if warehouseID is found
		boolean warehouseFound = false;

		try {
			boolean found = false;
			
			NodeList olcbProcs = doc.getElementsByTagName("ns7:OLCBProc");
			
			for (int o = 0; o < olcbProcs.getLength(); o++) {
				Element olcbProc = (Element) olcbProcs.item(o);
				NodeList clcbProcs = olcbProc.getElementsByTagName("ns7:CLCBProc");

				for (int c = 0; c < clcbProcs.getLength(); c++) {
					Element clcbProc = (Element) clcbProcs.item(c);
					NamedNodeMap nnm2 = clcbProc.getAttributes();

					for (int t = 0; t < nnm2.getLength(); t++) {
						Attr attr2 = (Attr) nnm2.item(t);
						if (attr2.getNodeName().equals("name")) {
							if (attr2.getValue().equals(clcbName)) {
								found = true;
							}
						}
					}
					
					if (found) {
						found = false;
						NodeList vocabularies = clcbProc.getElementsByTagName("Vocabulary");
						
						for (int i = 0; i < vocabularies.getLength(); i++) {
							Element vocabulary = (Element) vocabularies.item(i);
			
							if (vocabulary.getAttribute("type").equals("urn:epcglobal:epcis:vtype:BusinessLocation")) {
								NodeList vocabularyElementLists = vocabulary.getElementsByTagName("VocabularyElementList");
			
								for (int j = 0; j < vocabularyElementLists.getLength(); j++) {
									Element vocabularyElementList = (Element) vocabularyElementLists.item(j);
									NodeList vocabularyElements = vocabularyElementList.getElementsByTagName("VocabularyElement");
			
									for (int k = 0; k < vocabularyElements.getLength(); k++) {
										Element vocabularyElement = (Element) vocabularyElements.item(k);
										NamedNodeMap nnm = vocabularyElement.getAttributes();
			
										for (int l = 0; l < nnm.getLength(); l++) {
											Attr attr = (Attr) nnm.item(l);
											warehouseFound = false;
											
											if (attr.getNodeValue().equals(warehouseID)) {
												warehouseData.put("ID", warehouseID);
												warehouseFound = true;
											}
										}
			
										if (warehouseFound) {
											NodeList attributes = vocabularyElement.getElementsByTagName("attribute");
				
											for (int m = 0; m < attributes.getLength(); m++) {
												Element attribute = (Element) attributes.item(m);
												NamedNodeMap nnm1 = attribute.getAttributes();
			
												String name = "";
												String value = "";
												boolean isName = false;
												boolean isValue = false;
												boolean isData = false;
			
												for (int n = 0; n < nnm1.getLength(); n++) {
													Attr attr1 = (Attr) nnm1.item(n);
													
													if (isData) {
														isName = false;
														isValue = false;
														isData = false;
													}
																						
													if (attr1.getNodeName().equals("id")) {
														name = attr1.getNodeValue();
														isName = true;
													}
													else if (attr1.getNodeName().equals("value")) {
														value = attr1.getNodeValue();
														isValue = true;
													}
													
													if (isName && isValue) {
														warehouseData.put(name, value);
														isData = true;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			insertWarehouseChildren(warehouseID, warehouseData, clcbName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return warehouseData;
	}
	
	/**
	 * Get a HashMap with the read point data from the APDL file
	 */
	private static HashMap<String, String> getReadPointDataFromApdl(String readPointID, String clcbName) {
		HashMap<String, String> readPointData = new HashMap<String, String>();
		//if readPointID is found
		boolean readPointFound = false;

		try {
			boolean found = false;
			
			NodeList olcbProcs = doc.getElementsByTagName("ns7:OLCBProc");
			
			for (int o = 0; o < olcbProcs.getLength(); o++) {
				Element olcbProc = (Element) olcbProcs.item(o);
				NodeList clcbProcs = olcbProc.getElementsByTagName("ns7:CLCBProc");

				for (int c = 0; c < clcbProcs.getLength(); c++) {
					Element clcbProc = (Element) clcbProcs.item(c);
					NamedNodeMap nnm2 = clcbProc.getAttributes();

					for (int t = 0; t < nnm2.getLength(); t++) {
						Attr attr2 = (Attr) nnm2.item(t);
						if (attr2.getNodeName().equals("name")) {
							if (attr2.getValue().equals(clcbName)) {
								found = true;
							}
						}
					}
					
					if (found) {
						found = false;
						NodeList vocabularies = clcbProc.getElementsByTagName("Vocabulary");
								
			
						for (int i = 0; i < vocabularies.getLength(); i++) {
							Element vocabulary = (Element) vocabularies.item(i);
			
							if (vocabulary.getAttribute("type").equals("urn:epcglobal:epcis:vtype:ReadPoint")) {
								NodeList vocabularyElementLists = vocabulary.getElementsByTagName("VocabularyElementList");
			
								for (int j = 0; j < vocabularyElementLists.getLength(); j++) {
									Element vocabularyElementList = (Element) vocabularyElementLists.item(j);
									NodeList vocabularyElements = vocabularyElementList.getElementsByTagName("VocabularyElement");
			
									for (int k = 0; k < vocabularyElements.getLength(); k++) {
										Element vocabularyElement = (Element) vocabularyElements.item(k);
										NamedNodeMap nnm = vocabularyElement.getAttributes();
			
										for (int l = 0; l < nnm.getLength(); l++) {
											Attr attr = (Attr) nnm.item(l);
											readPointFound = false;
											
											if (attr.getNodeValue().equals(readPointID)) {
												readPointData.put("ID", readPointID);
												readPointFound = true;
											}
										}
			
										if (readPointFound) {
											NodeList attributes = vocabularyElement.getElementsByTagName("attribute");
				
											for (int m = 0; m < attributes.getLength(); m++) {
												Element attribute = (Element) attributes.item(m);
												NamedNodeMap nnm1 = attribute.getAttributes();
			
												String name = "";
												String value = "";
												boolean isName = false;
												boolean isValue = false;
												boolean isData = false;
			
												for (int n = 0; n < nnm1.getLength(); n++) {
													Attr attr1 = (Attr) nnm1.item(n);
													
													if (isData) {
														isName = false;
														isValue = false;
														isData = false;
													}
																						
													if (attr1.getNodeName().equals("id")) {
														name = attr1.getNodeValue();
														isName = true;
													}
													else if (attr1.getNodeName().equals("value")) {
														value = attr1.getNodeValue();
														isValue = true;
													}
													
													if (isName && isValue) {
														readPointData.put(name, value);
														isData = true;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readPointData;
	}
	
	/**
	 * Create the company from the APDL
	 */
	public static Company createApdlModel(String clcbName) {
		HashMap<String, String> companyData = getCompanyDataFromApdl(clcbName);
		Company company = MasterDataEditorGMFFactory.eINSTANCE.createCompany();

		//add the company info
		company.setID(companyData.get("ID"));
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
				HashMap<String, String> warehouseData = getWarehouseDataFromApdl(item1.getValue(), clcbName);
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
				
				String warehID = item1.getValue().replaceAll(companyData.get("ID") + ",", "");
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
					
						containerData = getReadPointDataFromApdl(split[k], clcbName);
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
				
				createWarehouse(warehouse, item1.getValue(), clcbName);
				i++;
			}
		}
		return company;
	}
	
	/**
	 * Create the warehouses from the EPCIS data
	 */
	private static void createWarehouse(AbstractWarehouse warehouse, String ID, String clcbName) {	
		HashMap<String, String> warehouseData = getWarehouseDataFromApdl(ID, clcbName);
		
		Set<Entry<String, String>> set = warehouseData.entrySet();
		Iterator<Entry<String, String>> itr = set.iterator();
		int i = 0;
		AbstractWarehouse warehouseNew = null;
		
		//add the warehouses
		while (itr.hasNext()) {
			Entry<String, String> item1 = itr.next();
			String warehousechild = "WarehouseChildren" + i;
			if (item1.getKey().equals(warehousechild)) {
				HashMap<String, String> warehouseDataChild = getWarehouseDataFromApdl(item1.getValue(), clcbName);
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
					
						containerData = getReadPointDataFromApdl(split[k], clcbName);
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
				
				createWarehouse(warehouseNew, item1.getValue(), clcbName);
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
	

}
