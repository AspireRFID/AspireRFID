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

package org.ow2.aspirerfid.ide.bpwme.master.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.jface.viewers.Viewer;
import org.ow2.aspirerfid.commons.apdl.model.*;
import org.ow2.aspirerfid.commons.epcis.model.*;
import org.ow2.aspirerfid.ide.bpwme.master.model.DispositionItem;
import org.ow2.aspirerfid.ide.bpwme.master.views.MasterEditor;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;

/**
 * All set and get for master data go through
 * Master Data Builder.
 * Observer Pattern for handling data update
 * Singleton Pattern for loosely coupling
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */

public class MasterDataBuilder {
	
	private static MasterDataBuilder mdb = new MasterDataBuilder();
	//private EPCISMasterDataDocumentType doc;
	
	private ArrayList<Viewer> listeners;
	
	private MasterEditor masterEditor;
	
	//change with olcb
	private OLCBProc olcbProc;
	private VocabularyType businessStepVocabulary;
	private VocabularyType dispositionVocabulary;
	private VocabularyType businessTransactionVocabulary;
	private ArrayList<DispositionItem> dispositionItemList;
	private ArrayList<DispositionItem> bizItemList;
	private ArrayList<DispositionItem> transactionItemList;
	
	//change with clcb
	private CLCBProc clcbProc;
	private VocabularyType businessLocationVocabulary;
	private VocabularyType readPointVocabulary;
	
	//change with ebproc
	private EBProc ebProc;
	private VocabularyElementType ebProcVocabularyElement;
	
	public MasterDataBuilder() {
		listeners = new ArrayList<Viewer>();
		dispositionItemList = new ArrayList<DispositionItem>();
		bizItemList = new ArrayList<DispositionItem>();
		transactionItemList = new ArrayList<DispositionItem>();
	}
	
//	public MasterDataBuilder(EBProc ebproc) {
//		this.ebProc = ebproc;
//		listeners = new ArrayList<Viewer>();
//		dispositionItemList = new ArrayList<DispositionItem>();
//		EPCISMasterDataDocumentType doc = MasterDataUtil.getEPCISMasterDataDocument(ebproc);
//		ebProcVocabularyElement = MasterDataUtil.getEBProcVocabularyElement(doc);	
//	}
	
	public VocabularyType getReadPointVocabulary() {
		return readPointVocabulary;
	}
	
	public VocabularyType getBusinessStepVocabulary() {
		return businessStepVocabulary;
	}
	
	public VocabularyType getDispositionVocabulary() {
		return dispositionVocabulary;
	}
	
	public VocabularyType getBusinessTransactionVocabulary() {
		return businessTransactionVocabulary;
	}
	
	public void setMasterEditor(MasterEditor masterEditor) {
		this.masterEditor = masterEditor;
	}
	
	public MasterEditor getMasterEditor() {
		return masterEditor;
	}
	
	
	public void setEBProc(EBProc ebProc) {
		this.ebProc = ebProc;
		listeners.clear();
		EPCISMasterDataDocumentType doc = MasterDataUtil.getEPCISMasterDataDocument(ebProc);
		ebProcVocabularyElement = MasterDataUtil.getEBProcVocabularyElement(doc);
		//mean while find the father CLCBProc and set it
		MainControl mc = MainControl.getMainControl();
		setCLCBProc(mc.getParent(ebProc));
	}
	
	public void setCLCBProc(CLCBProc clcbProc) {
		this.clcbProc = clcbProc;
		EPCISMasterDataDocumentType doc = MasterDataUtil.getEPCISMasterDataDocument(clcbProc);
		businessLocationVocabulary = MasterDataUtil.getVocabulary(doc, "urn:epcglobal:epcis:vtype:BusinessLocation");
		readPointVocabulary = MasterDataUtil.getVocabulary(doc, "urn:epcglobal:epcis:vtype:ReadPoint");
	}
	
	//set both business step and read point
	// in clcb level
	public void setBusinessStepReadPoint(
			HashMap<String, HashMap<String, String>> companyMap,
			HashMap<String, HashMap<String, String>> warehouseMap,
			HashMap<String, HashMap<String, String>> readpointMap) {
		
		VocabularyElementListType blist = MasterDataUtil.getVocabularyElementList(businessLocationVocabulary);
		blist.getVocabularyElement().clear();
		
		VocabularyElementType companyElement = createCompanyElement(companyMap);
		blist.getVocabularyElement().add(companyElement);
		
		List<VocabularyElementType> wlist = createWarehouseList(warehouseMap);
		for(VocabularyElementType element : wlist) {
			blist.getVocabularyElement().add(element);
		}
		
		VocabularyElementListType rlist = MasterDataUtil.getVocabularyElementList(readPointVocabulary);
		rlist.getVocabularyElement().clear();
		
		List<VocabularyElementType> rrlist = createReadpointList(warehouseMap, readpointMap);
		for(VocabularyElementType element : rrlist) {
			rlist.getVocabularyElement().add(element);
		}

		if(masterEditor != null) {
			masterEditor.setDirty(true);
		}else {
			MainControl mc = MainControl.getMainControl();
			mc.saveObject();
		}
		notifyListeners();
	}
	
	private VocabularyElementType createCompanyElement(
			HashMap<String, HashMap<String, String>> companyMap) {
		String prefix = "urn:epcglobal:epcis:mda:";
		VocabularyElementType companyElement = new VocabularyElementType();
		if(companyMap.keySet().size() != 1) {
			System.out.println("Error Company Map");
			return null;
		}
		
		for(String key : companyMap.keySet()) {
			MasterDataUtil.setVocabularyElementID(companyElement, key);

			HashMap<String, String> attrs = companyMap.get(key);
			for(String attr : attrs.keySet()) {
				String value = attrs.get(attr);
				if(!attr.equals("WarehouseChildren")) {
					MasterDataUtil.setVocabularyElementAttribute(companyElement, prefix + attr, value);
				}
			}
		}
		return companyElement;
	}
	
	private List<VocabularyElementType> createWarehouseList(
			HashMap<String, HashMap<String, String>> warehouseMap) {
		String prefix = "urn:epcglobal:epcis:mda:";
		ArrayList<VocabularyElementType> warehouseList = new ArrayList<VocabularyElementType>();
		VocabularyElementType warehouseElement;
		for(String key : warehouseMap.keySet()) {
			warehouseElement = new VocabularyElementType();			
			MasterDataUtil.setVocabularyElementID(warehouseElement, key);
			HashMap<String, String> attrs = warehouseMap.get(key);
			for(String attr : attrs.keySet()) {
				String value = attrs.get(attr);
				if(!attr.equals("WarehouseChildren")) {
					MasterDataUtil.setVocabularyElementAttribute(warehouseElement, prefix + attr, value);
				}
			}			
			warehouseList.add(warehouseElement);
		}		
		return warehouseList;
	}
	
	private List<VocabularyElementType> createReadpointList(
			HashMap<String, HashMap<String, String>> warehouseMap,
			HashMap<String, HashMap<String, String>> readpointMap) {
		String prefix = "urn:epcglobal:epcis:mda:";
		ArrayList<VocabularyElementType> readpointList = new ArrayList<VocabularyElementType>();
		VocabularyElementType readpointElement;
		for(String key : warehouseMap.keySet()) {
			HashMap<String, String> attrs = warehouseMap.get(key);
			for(String attr : attrs.keySet()) {
				String value = attrs.get(attr);
				if(attr.equals("ReadPoint")) {
					//split value
					String[] readpointNames = value.split(",");
					//for each value create an read point element
					for(int i = 0; i < readpointNames.length; i++) {
						readpointElement = new VocabularyElementType();
						readpointElement.setId(readpointNames[i]);
						readpointList.add(readpointElement);
					}
				}
			}			
		}
		
		//add attributes to each element
		for(VocabularyElementType element: readpointList) {
			HashMap<String, String> attrs = readpointMap.get(element.getId());
			if(attrs != null) {
				for(String attr : attrs.keySet()) {
					String value = attrs.get(attr);
					MasterDataUtil.setVocabularyElementAttribute(element, prefix + attr, value);
				}
			}
		}
		
		return readpointList;
	}
	
	public void setOLCBProc(OLCBProc olcbProc) {
		this.olcbProc = olcbProc;
		if(olcbProc == null) {
			return;
		}
		EPCISMasterDataDocumentType doc = MasterDataUtil.getEPCISMasterDataDocument(olcbProc);
		businessStepVocabulary = MasterDataUtil.getVocabulary(doc, "urn:epcglobal:epcis:vtype:BusinessStep");
		dispositionVocabulary = MasterDataUtil.getVocabulary(doc, "urn:epcglobal:epcis:vtype:Disposition");
		businessTransactionVocabulary = MasterDataUtil.getVocabulary(doc, "urn:epcglobal:epcis:vtype:BusinessTransactionType");
		buildDispositionItemList();
		buildBusinessItemList();
		buildTransactionItemList();
		//System.out.println(businessStepVocabulary);
	}
	
	public static MasterDataBuilder getInstance() {
		return mdb;
	}
	
	public String getAttribute(String name) {
		AttributeType attr = 
		MasterDataUtil.getVocabularyElementAttribute(ebProcVocabularyElement, name);
		if(attr != null) {
			return attr.getOtherAttributes().get(new QName("value"));
		}else {
			return "";
		}
	}
	
	public String getID() {
		return ebProcVocabularyElement.getId();
	}
	

	
	public void setAttribute(String name, String value) {
		MasterDataUtil.setVocabularyElementAttribute(
				ebProcVocabularyElement, name, value);
		masterEditor.setDirty(true);
	}
	
	//get read points from master data editor
	public String[] getReadPoints() {
		VocabularyElementListType list = MasterDataUtil.getVocabularyElementList(readPointVocabulary);
		String[] readPoints = new String[list.getVocabularyElement().size()];
		for(int i = 0; i < readPoints.length; i++) {
			readPoints[i] = list.getVocabularyElement().get(i).getId();
		}
		return readPoints;
	}
	
	//from read point get the corresponding business location
	public String getBusinessLocation(String readPoint) {		
		VocabularyElementListType vocabularyElementList = 
			MasterDataUtil.getVocabularyElementList(businessLocationVocabulary);
		//for each vocabulary element
		for(VocabularyElementType element: vocabularyElementList.getVocabularyElement()) {
			//get the read point attribute
			AttributeType attr = 
				MasterDataUtil.getVocabularyElementAttribute(element, "urn:epcglobal:epcis:mda:ReadPoint");
			if(attr != null) {
				//get the read point uri
				String value = attr.getOtherAttributes().get(new QName("value"));
				if(value != null) {
					String[] points = value.split(",");
					for(String point : points) {
						if(point.equals(readPoint)) {
							//if the uri match, return this element id (warehouse uri)
							return element.getId();
						}
					}
				}
			}
		}
		System.out.println("No Mapping Business Location");
		return "";
	}
	
	/********************************************************/
	/***********For disposition item below*******************/
	
	//from disposition vocabulary build the list
	private void buildDispositionItemList() {
		dispositionItemList.clear();
		VocabularyElementListType dispositionVocabularyElementList = 
			MasterDataUtil.getVocabularyElementList(dispositionVocabulary);
		for(VocabularyElementType element: dispositionVocabularyElementList.getVocabularyElement()) {
			dispositionItemList.add(new DispositionItem(element));
		}
	}
	
	private void buildBusinessItemList() {
		bizItemList.clear();
		VocabularyElementListType dispositionVocabularyElementList = 
			MasterDataUtil.getVocabularyElementList(businessStepVocabulary);
		for(VocabularyElementType element: dispositionVocabularyElementList.getVocabularyElement()) {
			bizItemList.add(new DispositionItem(element));
		}
	}
	
	private void buildTransactionItemList() {
		transactionItemList.clear();
		VocabularyElementListType dispositionVocabularyElementList = 
			MasterDataUtil.getVocabularyElementList(businessTransactionVocabulary);
		for(VocabularyElementType element: dispositionVocabularyElementList.getVocabularyElement()) {
			transactionItemList.add(new DispositionItem(element));
		}
	}
	
	
	public ArrayList<DispositionItem> getDispositionList() {
		return dispositionItemList;
	}
	
	public ArrayList<DispositionItem> getBizStepList() {
		return getItemList(bizItemList);
	}
	
	public ArrayList<DispositionItem> getTranzList() {
		return getItemList(transactionItemList);
	}
	
	private ArrayList<DispositionItem> getItemList(ArrayList<DispositionItem> list) {
		return list;
	}
	
	public DispositionItem createNewBizStepItem() {
		return createNewItem(businessStepVocabulary);
	}
	
	public DispositionItem createNewTranzItem() {
		return createNewItem(businessTransactionVocabulary);
	}
	
	private DispositionItem createNewItem(VocabularyType vocabulary) {
		VocabularyElementType vocabularyElement = new VocabularyElementType();
		vocabulary.getVocabularyElementList().getVocabularyElement().add(vocabularyElement);
		DispositionItem di = new DispositionItem(vocabularyElement);
		return di;
	}
	
	public String[] getDispositions() {
		String[] dispositions = new String[dispositionItemList.size()];
		for(int i = 0; i < dispositionItemList.size(); i++) {
			dispositions[i] = dispositionItemList.get(i).getLabel();
		}
		return dispositions;
	}
	
	public DispositionItem createNewDispositionItem() {
		VocabularyElementType vocabularyElement = new VocabularyElementType();
		DispositionItem di = new DispositionItem(vocabularyElement);
		return di;
	}
	

	
	private void addItem(DispositionItem di, ArrayList<DispositionItem> list) {
		list.add(di);
		notifyListeners();
	}
	
	public void addBizStepItem(DispositionItem di) {
		VocabularyElementListType list = MasterDataUtil.getVocabularyElementList(businessStepVocabulary);
		list.getVocabularyElement().add(di.getVocabularyElement());
		addItem(di, bizItemList);
	}
	
	public void addDispositionItem(DispositionItem di) {
		VocabularyElementListType list = MasterDataUtil.getVocabularyElementList(dispositionVocabulary);
		list.getVocabularyElement().add(di.getVocabularyElement());
		addItem(di, dispositionItemList);
	}
	
	public void addTranzItem(DispositionItem di) {
		VocabularyElementListType list = MasterDataUtil.getVocabularyElementList(businessTransactionVocabulary);
		list.getVocabularyElement().add(di.getVocabularyElement());
		addItem(di, transactionItemList);
	}
	

	
	private void removeItem(DispositionItem di, ArrayList<DispositionItem> list) {
		list.remove(di);
		notifyListeners();
	}
	
	public void removeTranzItem(DispositionItem di) {
		VocabularyElementListType list = MasterDataUtil.getVocabularyElementList(businessTransactionVocabulary);
		list.getVocabularyElement().remove(di.getVocabularyElement());
		removeItem(di, transactionItemList);
	}
	
	public void removeDispositionItem(DispositionItem di) {
		VocabularyElementListType list = MasterDataUtil.getVocabularyElementList(dispositionVocabulary);
		list.getVocabularyElement().remove(di.getVocabularyElement());
		removeItem(di, dispositionItemList);
	}
	
	public void removeBizStepItem(DispositionItem di) {
		VocabularyElementListType list = MasterDataUtil.getVocabularyElementList(businessStepVocabulary);
		list.getVocabularyElement().remove(di.getVocabularyElement());
		removeItem(di, bizItemList);
	}
	

	
 	public void addListener(Viewer viewer) {
		listeners.add(viewer);
	}
	
	public void notifyListeners() {
		for(Viewer v : listeners) {
			v.refresh();
		}
	}
	/********************************************************/
	/***********For disposition item attribute***************/
	
	public void modifyDispositionItemID(DispositionItem di, String id) {
		if(id != null) {
			if(!id.equals(di.getID())) {
				di.setID(id);
				notifyListeners();
				masterEditor.setDirty(true);
			}
		}
	}
	
	
	public void addItemAttribute(DispositionItem item, String name, String value) {
		item.addAttribute(name, value);
		masterEditor.setDirty(true);
	}
	
	public void modifyAttribute() {}
	
	public void removeItemAttribute(DispositionItem item, String name) {
		item.removeAttribute(name);
		masterEditor.setDirty(true);
	}
	

}
