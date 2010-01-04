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

package org.ow2.aspirerfid.programmableengine.encode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.ow2.aspirerfid.programmableengine.aleclient.AleClientUtil;
import org.ow2.aspirerfid.programmableengine.aleclient.AleLrClientUtil;
import org.ow2.aspirerfid.programmableengine.epcisclient.EpcisConstants;
import org.ow2.aspirerfid.programmableengine.epcisclient.MasterDataCaptureClient;
import org.ow2.aspirerfid.programmableengine.model.ApdlDataField;
import org.ow2.aspirerfid.programmableengine.model.ApdlDataFields;
import org.ow2.aspirerfid.programmableengine.model.AttributeType;
import org.ow2.aspirerfid.programmableengine.model.CLCBProc;
import org.ow2.aspirerfid.programmableengine.model.EBProc;
import org.ow2.aspirerfid.programmableengine.model.ECReportSpec;
import org.ow2.aspirerfid.programmableengine.model.ECSpec;
import org.ow2.aspirerfid.programmableengine.model.EPCISMasterDataDocumentType;
import org.ow2.aspirerfid.programmableengine.model.ExtendedAttribute;
import org.ow2.aspirerfid.programmableengine.model.LRSpec;
import org.ow2.aspirerfid.programmableengine.model.OLCBProc;
import org.ow2.aspirerfid.programmableengine.model.VocabularyElementListType;
import org.ow2.aspirerfid.programmableengine.model.VocabularyElementType;
import org.ow2.aspirerfid.programmableengine.model.VocabularyType;
import org.ow2.aspirerfid.programmableengine.model.ECSpec.ReportSpecs;
import org.w3c.dom.Element;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class EncodeImplementation {

	/** logger. */
	public static final Logger LOG = Logger.getLogger(EncodeImplementation.class);

	/**
	 * if the encode is successful the reply ID will be 400 if not the reply ID
	 * will be 425. The default value is 425
	 */
	private Integer replyID = 425;

	private OLCBProc openLoopCBPro = null;

	private AleClientUtil aleClientUtil = null;

	private String openLoopCBProcID = "";

	public EncodeImplementation(OLCBProc openLoopCBProc) {

		this.openLoopCBPro = openLoopCBProc;
		openLoopCBProcID = openLoopCBProc.getId();

		LOG.debug("Recieved OpenLoopCBProc ID for Encode: " + openLoopCBProc.getId());
		LOG.debug("Recieved OpenLoopCBProc Name for Encode: " + openLoopCBProc.getName());

		encode();

		// if (openLoopCBProc != null) {
		// System.out.println("Recieved OpenLoopCBProc ID for Encode: " +
		// openLoopCBProc.getId());
		// System.out.println("Recieved OpenLoopCBProc Name for Encode: " +
		// openLoopCBProc.getName());
		// replyID = 400;
		// return;
		// }
		// System.out.println("The openLoopCBProc was Null");
		//
		// replyID = 425;
	}

	private void encode() {

		ArrayList<CLCBProc> clCBProcesses = new ArrayList<CLCBProc>();
		clCBProcesses = (ArrayList<CLCBProc>) openLoopCBPro.getCLCBProc();

		for (CLCBProc clCBProc : clCBProcesses) {

			String clCBProcID = clCBProc.getId();

			clCBProc.getDescription();
			ArrayList<EBProc> ebProcesses = new ArrayList<EBProc>();
			ebProcesses = (ArrayList<EBProc>) clCBProc.getEBProc();

			for (EBProc ebProc : ebProcesses) {
				if (!((ebProc.getId()).equals(null))) {
					if ((!(ebProc.getId()).equals("CLCBProcEnd")) && (!(ebProc.getId()).equals("CLCBProcStart"))) {
						ProcessedEBProc processedEBProc = new ProcessedEBProc();
						processedEBProc = processEBProc(ebProc);

						// SetUp LRSpec
						setUpAleLR(processedEBProc.getAleLrClientEndPoint(), processedEBProc.getLrSpecs());

						// SetUpECSpec
						setUpAle(processedEBProc.getAleClientEndPoint(), processedEBProc.getId(), processedEBProc.getDefinedECSpecName(),
								processedEBProc.getEcSpec(), processedEBProc.getEcSpecSubscriptionURI());

						// SetUp MasterData
						setUpEPCIS(processedEBProc.getEpcisClientCaptureEndPoint(), clCBProcID, processedEBProc.getEpcisMasterDataDocument());

						//SetUp BEG
					}
				}
			}

		}

	}

	private void setUpAleLR(String aleLrClientEndPoint, Hashtable<String, LRSpec> lrSpecs) {
		AleLrClientUtil aleLrClientUtil = new AleLrClientUtil();

		aleLrClientUtil.initializeAleLrProxy(aleLrClientEndPoint);

		for (String logicalReaderName : lrSpecs.keySet()) {

			aleLrClientUtil.defineLRSpec(logicalReaderName, lrSpecs.get(logicalReaderName));
		}

		LOG.debug("Logical Reader" + "Was Successfull Set Up!");
	}

	private void setUpAle(String aleClientEndPoint, String ebProcID, String definedECSpecName, ECSpec ecSpec, String ecSpecSubscriptionURI) {
		// Concatenate to every ReportSpecName the @ebProcID for BEG use
		ArrayList<ECReportSpec> reportSpecsList = new ArrayList<ECReportSpec>();
		reportSpecsList = (ArrayList<ECReportSpec>) ecSpec.getReportSpecs().getReportSpec();

		for (ECReportSpec ecReportSpec : reportSpecsList) {
			ecReportSpec.setReportName(ecReportSpec.getReportName() + "@" + ebProcID);
		}

		AleClientUtil aleClientUtil = new AleClientUtil();
		aleClientUtil.initializeAleProxy(aleClientEndPoint);
		aleClientUtil.defineECSpec(definedECSpecName, ecSpec);
		aleClientUtil.subscribeECSpec(definedECSpecName, ecSpecSubscriptionURI);

	}

	private void setUpEPCIS(String epcisClientCaptureEndPoint, String clCBProcID, EPCISMasterDataDocumentType epcisMasterDataDocument) {

		boolean simpleMasterDataCaptureSucceeded = false;

		MasterDataCaptureClient masterDataCaptureClient = new MasterDataCaptureClient(epcisClientCaptureEndPoint);

		// Save openLoopCBProcID
		simpleMasterDataCaptureSucceeded = masterDataCaptureClient.simpleMasterDataInsert(EpcisConstants.BUSINESS_TRANSACTION_ID, openLoopCBProcID);
		if (simpleMasterDataCaptureSucceeded) {
			LOG.debug("Master Data " + openLoopCBProcID + " saccesfuly saved!");
			simpleMasterDataCaptureSucceeded = false;
		}
		else {
			LOG.debug("The Master Data " + openLoopCBProcID + " could NOT be captured!");
		}

		// Save clCBProcID
		simpleMasterDataCaptureSucceeded = masterDataCaptureClient.simpleMasterDataInsert(EpcisConstants.BUSINESS_TRANSACTION_ID, openLoopCBProcID
				+ "," + clCBProcID);
		if (simpleMasterDataCaptureSucceeded) {
			LOG.debug("Master Data " + openLoopCBProcID + "," + clCBProcID + " saccesfuly saved!");
			simpleMasterDataCaptureSucceeded = false;
		}
		else {
			LOG.debug("The Master Data " + openLoopCBProcID + "," + clCBProcID + " could NOT be captured!");
		}

		// ==== Save the Individual Attributes to their relative Vocabularies=====

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
						simpleMasterDataCaptureSucceeded = masterDataCaptureClient.simpleMasterDataInsert(EpcisConstants.BUSINESS_STEP_ID,attributeType.getAny().get(0).getNodeValue());
						if (simpleMasterDataCaptureSucceeded) {
							LOG.debug("Master Data " + attributeType.getAny().get(0).getNodeValue() + " saccesfuly saved!");
							simpleMasterDataCaptureSucceeded = false;
						}
						else {
							LOG.debug("The Master Data " + attributeType.getAny().get(0).getNodeValue() + " could NOT be captured!");
						}
					}else
					// Save the business_location if it doesn't already exists
					if (attributeType.getId().equals("urn:epcglobal:epcis:mda:business_location")) {

						simpleMasterDataCaptureSucceeded = masterDataCaptureClient.simpleMasterDataInsert(EpcisConstants.BUSINESS_LOCATION_ID,attributeType.getAny().get(0).getNodeValue());
						if (simpleMasterDataCaptureSucceeded) {
							LOG.debug("Master Data " + attributeType.getAny().get(0).getNodeValue() + " saccesfuly saved!");
							simpleMasterDataCaptureSucceeded = false;
						}
						else {
							LOG.debug("The Master Data " + attributeType.getAny().get(0).getNodeValue() + " could NOT be captured!");
						}
					}else
					// Save the disposition if it doesn't already exists
					if (attributeType.getId().equals("urn:epcglobal:epcis:mda:disposition")) {

						simpleMasterDataCaptureSucceeded = masterDataCaptureClient.simpleMasterDataInsert(EpcisConstants.DISPOSITION_ID,attributeType.getAny().get(0).getNodeValue());
						if (simpleMasterDataCaptureSucceeded) {
							LOG.debug("Master Data " + attributeType.getAny().get(0).getNodeValue() + " saccesfuly saved!");
							simpleMasterDataCaptureSucceeded = false;
						}
						else {
							LOG.debug("The Master Data " + attributeType.getAny().get(0).getNodeValue() + " could NOT be captured!");
						}
					}else
					// Save the read_point if it doesn't already exists
					if (attributeType.getId().equals("urn:epcglobal:epcis:mda:read_point")) {

						simpleMasterDataCaptureSucceeded = masterDataCaptureClient.simpleMasterDataInsert(EpcisConstants.READ_POINT_ID,attributeType.getAny().get(0).getNodeValue());
						if (simpleMasterDataCaptureSucceeded) {
							LOG.debug("Master Data " + attributeType.getAny().get(0).getNodeValue() + " saccesfuly saved!");
							simpleMasterDataCaptureSucceeded = false;
						}
						else {
							LOG.debug("The Master Data " + attributeType.getAny().get(0).getNodeValue() + " could NOT be captured!");
						}						
						
					}else
					// Save the transaction_type if it doesn't already exists
					if (attributeType.getId().equals("urn:epcglobal:epcis:mda:transaction_type")) {

						simpleMasterDataCaptureSucceeded = masterDataCaptureClient.simpleMasterDataInsert(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID,attributeType.getAny().get(0).getNodeValue());
						if (simpleMasterDataCaptureSucceeded) {
							LOG.debug("Master Data " + attributeType.getAny().get(0).getNodeValue() + " saccesfuly saved!");
							simpleMasterDataCaptureSucceeded = false;
						}
						else {
							LOG.debug("The Master Data " + attributeType.getAny().get(0).getNodeValue() + " could NOT be captured!");
						}						
					}
				}
			}
		}

		// ===========================================================================

		// Save the hole Transaction Event from the given
		// epcisMasterDataDocument
		try {
			masterDataCaptureClient.capture(epcisMasterDataDocument);
		}
		catch (IOException e) {
			LOG.error("Master Data Document for " + clCBProcID + " could not be saved!");
			e.printStackTrace();
		}
		catch (JAXBException e) {
			LOG.error("Master Data Document for " + clCBProcID + " could not be saved!");
			e.printStackTrace();
		}

	}

	private void setUpBEG(VocabularyElementType vocabularyElementType, String repositoryCaptureURL, String begListeningPort) {

	}

	private ProcessedEBProc processEBProc(EBProc ebProc) {

		ProcessedEBProc processedEBProc = new ProcessedEBProc();

		processedEBProc.setId(ebProc.getId());
		processedEBProc.setName(ebProc.getName());

		// Get All the DataFields
		ApdlDataFields apdlDataFields = new ApdlDataFields();
		apdlDataFields = ebProc.getApdlDataFields();
		EPCISMasterDataDocumentType epcisMasterDataDocument = new EPCISMasterDataDocumentType();
		ECSpec ecSpec = new ECSpec();
		Hashtable<String, LRSpec> lrSpecs = new Hashtable<String, LRSpec>();
		for (ApdlDataField apdlDataField : apdlDataFields.getApdlDataField()) {
			if (apdlDataField.getType().equals("EPCISMasterDataDocument")) {
				epcisMasterDataDocument = apdlDataField.getEPCISMasterDataDocument();
				processedEBProc.setEpcisMasterDataDocument(epcisMasterDataDocument);
			}
			else if (apdlDataField.getType().equals("ECSpec")) {
				ecSpec = apdlDataField.getECSpec();
				processedEBProc.setEcSpec(ecSpec);
				processedEBProc.setDefinedECSpecName(apdlDataField.getName());
			}
			else if (apdlDataField.getType().equals("LRSpec")) {
				lrSpecs.put(apdlDataField.getName(), apdlDataField.getLRSpec());
			}
		}
		processedEBProc.setLrSpecs(lrSpecs);

		// Get required extensions
		for (ExtendedAttribute extendedAttribute : ebProc.getExtendedAttributes().getExtendedAttribute()) {

			if (extendedAttribute.getName().equals("ECSpecSubscriptionURI")) {
				processedEBProc.setEcSpecSubscriptionURI(extendedAttribute.getValue());
			}
			else if (extendedAttribute.getName().equals("AleClientEndPoint")) {
				processedEBProc.setAleClientEndPoint(extendedAttribute.getValue());
			}
			else if (extendedAttribute.getName().equals("AleLrClientEndPoint")) {
				processedEBProc.setAleLrClientEndPoint(extendedAttribute.getValue());
			}
			else if (extendedAttribute.getName().equals("EpcisClientCaptureEndPoint")) {
				processedEBProc.setEpcisClientCaptureEndPoint(extendedAttribute.getValue());
			}
			else if (extendedAttribute.getName().equals("EpcisClientQueryEndPoint")) {
				processedEBProc.setEpcisClientQueryEndPoint(extendedAttribute.getValue());
			}

		}
		return processedEBProc;
	}

	public Integer getReplyID() {
		return replyID;
	}

}
