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

import javax.xml.namespace.QName;

import org.ow2.aspirerfid.commons.apdl.model.*;
import org.ow2.aspirerfid.commons.epcis.model.*;

/**
 * Utils for handling master data elements
 * All are static methods
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class MasterDataUtil {
	
	/**
	 * Get master data document from a ebproc
	 * create a new one if it's not exist
	 * @param ebproc
	 * @return
	 */
	public static EPCISMasterDataDocumentType getEPCISMasterDataDocument(EBProc ebproc) {
		EPCISMasterDataDocumentType doc;
		boolean newDoc = true;
		for(ApdlDataField adf: ebproc.getApdlDataFields().getApdlDataField()) {
			if((doc = adf.getEPCISMasterDataDocument()) != null) {
				newDoc = false;
				return doc;
			}
		}
		if(newDoc) {
			ApdlDataField adf = new ApdlDataField();
			adf.setType("EPCISMasterDataDocument");
			adf.setName("RecievingMasterData");
			doc = new EPCISMasterDataDocumentType();
			adf.setEPCISMasterDataDocument(doc);
			ebproc.getApdlDataFields().getApdlDataField().add(adf);
			return doc;			
		}
		return null;
	}
	
	/**
	 * Get master data document from a olcbproc
	 * create a new one if it's not exist
	 * @param olcbProc
	 * @return
	 */
	public static EPCISMasterDataDocumentType getEPCISMasterDataDocument(OLCBProc olcbProc) {
		EPCISMasterDataDocumentType doc = olcbProc.getEPCISMasterDataDocument();
		if(doc == null) {
			doc = new EPCISMasterDataDocumentType();
			olcbProc.setEPCISMasterDataDocument(doc);
		}
		return doc;
	}
	
	/**
	 * Get master data document from a clcbproc
	 * create a new one if it's not exist
	 * @param clcbProc
	 * @return
	 */
	public static EPCISMasterDataDocumentType getEPCISMasterDataDocument(CLCBProc clcbProc) {
		EPCISMasterDataDocumentType doc = clcbProc.getEPCISMasterDataDocument();
		if(doc == null) {
			doc = new EPCISMasterDataDocumentType();
			clcbProc.setEPCISMasterDataDocument(doc);
		}
		return doc;
	}
	
	/**
	 * Get the attribute name list from a vocabulary element
	 * The attribute names are showed in property view
	 * @param vocabularyElement
	 * @return
	 */
	public static ArrayList<String> getAttributeNameList(VocabularyElementType vocabularyElement) {
		ArrayList<String> list = new ArrayList<String>();
		for(AttributeType attr : vocabularyElement.getAttribute()) {
			list.add(attr.getId());
		}
		return list;
	}
	
	
	/**
	 * Set the value for a specific attribute of element
	 * If the attribute does not exist, create a new one
	 * @param vocabularyElement
	 * @param name
	 * @param value
	 */
	public static void setVocabularyElementAttribute(VocabularyElementType vocabularyElement, 
			String name, String value) {
		//if the attribute exists, update it
		AttributeType attr = getVocabularyElementAttribute(vocabularyElement, name);
		//else add a new one
		if(attr == null) {
			attr = new AttributeType();
			attr.setId(name);
			vocabularyElement.getAttribute().add(attr);
		}
		attr.getOtherAttributes().put(new QName("value"), value);
	}
	
	/**
	 * Get the specific attribute from the element
	 * Return null if it does not exist
	 * @param vocabularyElement
	 * @param name
	 * @return
	 */
	public static AttributeType getVocabularyElementAttribute(VocabularyElementType vocabularyElement, String name) {
		for(AttributeType attr : vocabularyElement.getAttribute()) {
			if(attr.getId().equals(name)) {
				return attr;
			}
		}
		return null;
	}
	
	/**
	 * Remove the given attribute from element
	 * @param vocabularyElement
	 * @param name
	 */
	public static void removeVocabularyElementAttribute(VocabularyElementType vocabularyElement, String name) {
		for(AttributeType attr : vocabularyElement.getAttribute()) {
			if(attr.getId().equals(name)) {
				vocabularyElement.getAttribute().remove(attr);
				return;
			}
		}
	}
	
	/**
	 * Set element id
	 * @param vocabularyElement
	 * @param id
	 */
	public static void setVocabularyElementID(VocabularyElementType vocabularyElement, String id) {
		vocabularyElement.setId(id);
	}
	
	/**
	 * from document get specific vocabulary
	 * if it's not exist, create a new one
	 * the type has to be validate first (in case there is a spelling mistake)
	 * @param doc
	 * @param type
	 * @return
	 */

	public static VocabularyType getVocabulary(EPCISMasterDataDocumentType doc, String type) {
		if(validateVocabularyType(type) == false) {
			return null;
		}
		if(doc == null) {
			System.err.println("NULL DocumentType");
			return null;
		}
		
		EPCISMasterDataBodyType body;
		VocabularyListType vocabularyList;
		VocabularyType vocabulary;
		
		if((body = doc.getEPCISBody()) == null) {
			body = new EPCISMasterDataBodyType();
			doc.setEPCISBody(body);		
		}
		
		if((vocabularyList = body.getVocabularyList()) == null) {
			vocabularyList = new VocabularyListType();
			body.setVocabularyList(vocabularyList);
		}
		
		for(VocabularyType temp: vocabularyList.getVocabulary()) {
			if(temp.getType().equals(type)) {
				vocabulary = temp;
				return vocabulary;
			}
		}
		
		vocabulary = new VocabularyType();
		vocabulary.setType(type);
		vocabularyList.getVocabulary().add(vocabulary);
		return vocabulary;
	}	
	
	/**
	 * Vocabulary can only be the following
	 * @param type
	 * @return
	 */
	private static boolean validateVocabularyType(String type) {
		if(type.equals("urn:epcglobal:epcis:vtype:BusinessStep") ||
				type.equals("urn:epcglobal:epcis:vtype:Disposition") ||
				type.equals("urn:epcglobal:epcis:vtype:BusinessTransactionType") ||
				type.equals("urn:epcglobal:epcis:vtype:BusinessLocation") ||
				type.equals("urn:epcglobal:epcis:vtype:ReadPoint") ||
				type.equals("urn:epcglobal:epcis:vtype:BusinessTransaction")
				) {
			return true;
		}else {
			System.err.println("Wrong Type in Vocabulary Definition");
			return false;
		}
	}
	
	/**
	 * Get the vocabulary element list of the given vocabulary
	 * Create a new one if it's not exist
	 * @param vocabulary
	 * @return
	 */
	public static VocabularyElementListType getVocabularyElementList(VocabularyType vocabulary) {
		if(vocabulary == null) {
			return null;
		}
		VocabularyElementListType vocabularyElementList = vocabulary.getVocabularyElementList();
		if(vocabularyElementList == null) {
			vocabularyElementList = new VocabularyElementListType();
			vocabulary.setVocabularyElementList(vocabularyElementList);	
		}
		return vocabularyElementList;
	}
	
	
	/**
	 * Get VocabularyElement in EBProc Level.
	 * By definition, right now only one VocabularyElement can exist in this level.
	 * @param doc
	 * @return null if anything goes wrong
	 */
	public static VocabularyElementType getEBProcVocabularyElement(EPCISMasterDataDocumentType doc) {
		if(doc == null) {
			return null;
		}
		
		VocabularyType vocabulary = getVocabulary(doc, "urn:epcglobal:epcis:vtype:BusinessTransaction");
		
		if(vocabulary == null) {
			return null;
		}
		
		VocabularyElementListType vocabularyElementList = getVocabularyElementList(vocabulary);
		
		if(vocabularyElementList == null) {
			return null;
		}
		
		VocabularyElementType vocabularyElement;
		if(vocabularyElementList.getVocabularyElement().size() == 0) {
			vocabularyElement = new VocabularyElementType();			
			vocabularyElementList.getVocabularyElement().add(vocabularyElement);
		}else {
			vocabularyElement = vocabularyElementList.getVocabularyElement().get(0);
		}
		return vocabularyElement;
	}

}
