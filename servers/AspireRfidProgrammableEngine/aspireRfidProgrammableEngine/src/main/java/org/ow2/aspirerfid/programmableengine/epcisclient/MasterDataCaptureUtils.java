/*
 * Copyright (C) 2008-2010, Aspire
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

package org.ow2.aspirerfid.programmableengine.epcisclient;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.epcis.model.AttributeType;
import org.ow2.aspirerfid.commons.epcis.model.EPCISMasterDataDocumentType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementListType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyType;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class MasterDataCaptureUtils {

	/** logger. */
	public static final Logger LOG = Logger.getLogger(MasterDataCaptureUtils.class);

	public void saveIndividualAttr(EPCISMasterDataDocumentType epcisMasterDataDocument, MasterDataCaptureClient masterDataCaptureClient) {

		boolean simpleMasterDataCaptureSucceeded = false;

		ArrayList<VocabularyType> vocabularyTypeList = (ArrayList<VocabularyType>) epcisMasterDataDocument.getEPCISBody().getVocabularyList()
				.getVocabulary();

		for (VocabularyType vocabularyType : vocabularyTypeList) {
			VocabularyElementListType vocabularyElementList = vocabularyType.getVocabularyElementList();

			ArrayList<VocabularyElementType> vocabularyElementTypeList = (ArrayList<VocabularyElementType>) vocabularyElementList
					.getVocabularyElement();

			for (VocabularyElementType vocabularyElementType : vocabularyElementTypeList) {

				ArrayList<AttributeType> attributeTypeList = (ArrayList<AttributeType>) vocabularyElementType.getAttribute();

				for (AttributeType attributeType : attributeTypeList) {

					// Save the business_step if it doesn't already exists
					if (attributeType.getId().equals("urn:epcglobal:epcis:mda:business_step")) {
						simpleMasterDataCaptureSucceeded = masterDataCaptureClient.insertVocElem(EpcisConstants.BUSINESS_STEP_ID,
								attributeType.getOtherAttributes().get(new QName("value")));
						if (simpleMasterDataCaptureSucceeded) {
							LOG.debug("Master Data " + attributeType.getOtherAttributes().get(new QName("value")) + " saccesfuly saved!");
							simpleMasterDataCaptureSucceeded = false;
						}
						else {
							LOG.debug("The Master Data " + attributeType.getOtherAttributes().get(new QName("value")) + " could NOT be captured!");
						}
					}
					else
					// Save the business_location if it doesn't already exists
					if (attributeType.getId().equals("urn:epcglobal:epcis:mda:business_location")) {

						simpleMasterDataCaptureSucceeded = masterDataCaptureClient.insertVocElem(EpcisConstants.BUSINESS_LOCATION_ID,
								attributeType.getOtherAttributes().get(new QName("value")));
						if (simpleMasterDataCaptureSucceeded) {
							LOG.debug("Master Data " + attributeType.getOtherAttributes().get(new QName("value")) + " saccesfuly saved!");
							simpleMasterDataCaptureSucceeded = false;
						}
						else {
							LOG.debug("The Master Data " + attributeType.getOtherAttributes().get(new QName("value")) + " could NOT be captured!");
						}
					}
					else
					// Save the disposition if it doesn't already exists
					if (attributeType.getId().equals("urn:epcglobal:epcis:mda:disposition")) {

						simpleMasterDataCaptureSucceeded = masterDataCaptureClient.insertVocElem(EpcisConstants.DISPOSITION_ID,
								attributeType.getOtherAttributes().get(new QName("value")));
						if (simpleMasterDataCaptureSucceeded) {
							LOG.debug("Master Data " + attributeType.getOtherAttributes().get(new QName("value")) + " saccesfuly saved!");
							simpleMasterDataCaptureSucceeded = false;
						}
						else {
							LOG.debug("The Master Data " + attributeType.getOtherAttributes().get(new QName("value")) + " could NOT be captured!");
						}
					}
					else
					// Save the read_point if it doesn't already exists
					if (attributeType.getId().equals("urn:epcglobal:epcis:mda:read_point")) {

						simpleMasterDataCaptureSucceeded = masterDataCaptureClient.insertVocElem(EpcisConstants.READ_POINT_ID, attributeType
								.getOtherAttributes().get(new QName("value")));
						if (simpleMasterDataCaptureSucceeded) {
							LOG.debug("Master Data " + attributeType.getOtherAttributes().get(new QName("value")) + " saccesfuly saved!");
							simpleMasterDataCaptureSucceeded = false;
						}
						else {
							LOG.debug("The Master Data " + attributeType.getOtherAttributes().get(new QName("value")) + " could NOT be captured!");
						}

					}
					else
					// Save the transaction_type if it doesn't already exists
					if (attributeType.getId().equals("urn:epcglobal:epcis:mda:transaction_type")) {

						simpleMasterDataCaptureSucceeded = masterDataCaptureClient.insertVocElem(
								EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID, attributeType.getOtherAttributes().get(new QName("value")));
						if (simpleMasterDataCaptureSucceeded) {
							LOG.debug("Master Data " + attributeType.getOtherAttributes().get(new QName("value")) + " saccesfuly saved!");
							simpleMasterDataCaptureSucceeded = false;
						}
						else {
							LOG.debug("The Master Data " + attributeType.getOtherAttributes().get(new QName("value")) + " could NOT be captured!");
						}
					}
				}
			}
		}

	}

	public void saveTransactionEvent(EPCISMasterDataDocumentType epcisMasterDataDocument, MasterDataCaptureClient masterDataCaptureClient,
			String parentTransaction) {

//		boolean masterDataCaptureSucceeded = false;
		
		String transactionID = "";

		ArrayList<VocabularyType> vocabularyTypeList = (ArrayList<VocabularyType>) epcisMasterDataDocument.getEPCISBody().getVocabularyList()
				.getVocabulary();

		for (VocabularyType vocabularyType : vocabularyTypeList) {
			VocabularyElementListType vocabularyElementList = vocabularyType.getVocabularyElementList();

			ArrayList<VocabularyElementType> vocabularyElementTypeList = (ArrayList<VocabularyElementType>) vocabularyElementList
					.getVocabularyElement();

			for (VocabularyElementType vocabularyElementType : vocabularyElementTypeList) {

				// masterDataCaptureSucceeded =
				// masterDataCaptureClient.simpleMasterDataInsert(EpcisConstants.BUSINESS_STEP_ID,
				// parentTransaction +","+ vocabularyElementType.getId());
				
				transactionID = parentTransaction + "," + vocabularyElementType.getId();
				vocabularyElementType.setId(transactionID);
				
				
//				
//				if (attributeType.getId().equals("urn:epcglobal:epcis:mda:business_step")) {
//				}
//				else if (attributeType.getId().equals("urn:epcglobal:epcis:mda:business_location")) {
//				}
//				else if (attributeType.getId().equals("urn:epcglobal:epcis:mda:disposition")) {
//				}
//				else if (attributeType.getId().equals("urn:epcglobal:epcis:mda:read_point")) {
//				}
//				else if (attributeType.getId().equals("urn:epcglobal:epcis:mda:event_type")) {
//				}
//				else if (attributeType.getId().equals("urn:epcglobal:epcis:mda:event_name")) {
//				}
//				else if (attributeType.getId().equals("urn:epcglobal:epcis:mda:action")) {
//				}
//				else if (attributeType.getId().equals("urn:epcglobal:epcis:mda:transaction_type")) {
//					attributeType.getOtherAttributes().get(new QName("value"));
//				}
				
//				eventType = vocAttributes.get("urn:epcglobal:epcis:mda:event_type");
//				transactionID = vocabularyElementType.getId();
//				
//				if (eventType.equals("ObjectEvent")){				
//					vocAttributes.put("urn:epcglobal:epcis:mda:ecreport_names", "bizTransactionIDs@"+transactionID+",transactionItems@"+transactionID);			
//				}else if(eventType.equals("QuantityEvent")){
//					vocAttributes.put("urn:epcglobal:epcis:mda:ecreport_names", "bizTransactionIDs@"+transactionID+",transactionItems@"+transactionID);						
//				}else if(eventType.equals("AggregationEvent")){
//					vocAttributes.put("urn:epcglobal:epcis:mda:ecreport_names", "bizTransactionIDs@"+transactionID+",transactionItems@"+transactionID+",parentObjects@"+transactionID);	
//				}else if(eventType.equals("TransactionEvent")){
//					vocAttributes.put("urn:epcglobal:epcis:mda:ecreport_names", "bizTransactionParentIDs@"+transactionID+",bizTransactionIDs@"+transactionID+",transactionItems@"+transactionID);
//				}
				
				
			}
			
		}
		
		try {
			masterDataCaptureClient.capture(epcisMasterDataDocument);
		}
		catch (IOException e) {
			LOG.error("Master Data Document for " + transactionID + " could not be saved!");
			e.printStackTrace();
		}
		catch (JAXBException e) {
			LOG.error("Master Data Document for " + transactionID + " could not be saved!");
			e.printStackTrace();
		}
		
		
	}
	
	public void saveMasterDataDocument(EPCISMasterDataDocumentType epcisMasterDataDocument, MasterDataCaptureClient masterDataCaptureClient){
	
		epcisMasterDataDocument.setSchemaVersion(new BigDecimal("1.0"));
		epcisMasterDataDocument.setCreationDate(masterDataCaptureClient.getCurrentTime());
		
		try {
			masterDataCaptureClient.capture(epcisMasterDataDocument);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}

}
