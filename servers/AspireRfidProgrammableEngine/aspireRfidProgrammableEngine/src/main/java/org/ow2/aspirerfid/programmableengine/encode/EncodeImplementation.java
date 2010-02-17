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
import org.ow2.aspirerfid.programmableengine.begclient.BegEngineClient;

import org.ow2.aspirerfid.programmableengine.epcisclient.EpcisConstants;
import org.ow2.aspirerfid.programmableengine.epcisclient.MasterDataCaptureClient;
import org.ow2.aspirerfid.programmableengine.epcisclient.MasterDataCaptureUtils;

import org.ow2.aspirerfid.commons.epcis.model.AttributeType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementListType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyType;
import org.ow2.aspirerfid.commons.epcis.model.EPCISMasterDataDocumentType;

import org.ow2.aspirerfid.commons.apdl.model.ApdlDataField;
import org.ow2.aspirerfid.commons.apdl.model.ApdlDataFields;
import org.ow2.aspirerfid.commons.apdl.model.CLCBProc;
import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.xpdl.model.ExtendedAttribute;

import org.ow2.aspirerfid.commons.ale.model.ale.ECReportSpec;
import org.ow2.aspirerfid.commons.ale.model.ale.ECSpec;
import org.ow2.aspirerfid.commons.ale.model.ale.ECSpec.ReportSpecs;

import org.ow2.aspirerfid.commons.ale.model.alelr.LRSpec;

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



			clCBProc.getDescription();
			ArrayList<EBProc> ebProcesses = new ArrayList<EBProc>();
			ebProcesses = (ArrayList<EBProc>) clCBProc.getEBProc();

			for (EBProc ebProc : ebProcesses) {
				if (!((ebProc.getId()).equals(null))) {
					if ((!(ebProc.getId()).equals("CLCBProcEnd")) && (!(ebProc.getId()).equals("CLCBProcStart"))) {
						ProcessedEBProc processedEBProc = new ProcessedEBProc();
						processedEBProc = processEBProc(ebProc);

						// Define LRSpec
						setUpAleLR(processedEBProc.getAleLrClientEndPoint(), processedEBProc.getLrSpecs());

						// DefineECSpec
						defineECSpec(processedEBProc.getAleClientEndPoint(), processedEBProc.getId(), processedEBProc.getDefinedECSpecName(),processedEBProc.getEcSpec());

						// SetUp MasterData
						setUpEPCIS(processedEBProc.getEpcisClientCaptureEndPoint(), clCBProc, processedEBProc.getEpcisMasterDataDocument());

						//SetUp BEG
						setUpBEG(processedEBProc.getEpcisMasterDataDocument(), processedEBProc.getBegEngineEndpoint(), processedEBProc.getEpcisClientCaptureEndPoint(),processedEBProc.getEcSpecSubscriptionURI());
						
						// SubscribeECSpec
						subscribeECSpec(processedEBProc.getAleClientEndPoint(),processedEBProc.getDefinedECSpecName(),processedEBProc.getEcSpecSubscriptionURI());
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

	private void defineECSpec(String aleClientEndPoint, String ebProcID, String definedECSpecName, ECSpec ecSpec) {
		// Concatenate to every ReportSpecName the @ebProcID for BEG use
		ArrayList<ECReportSpec> reportSpecsList = new ArrayList<ECReportSpec>();
		reportSpecsList = (ArrayList<ECReportSpec>) ecSpec.getReportSpecs().getReportSpec();

		for (ECReportSpec ecReportSpec : reportSpecsList) {
			ecReportSpec.setReportName(ecReportSpec.getReportName() + "@" + ebProcID);
		}

		AleClientUtil aleClientUtil = new AleClientUtil();
		aleClientUtil.initializeAleProxy(aleClientEndPoint);
		aleClientUtil.defineECSpec(definedECSpecName, ecSpec);

	}

	private void subscribeECSpec(String aleClientEndPoint, String definedECSpecName, String ecSpecSubscriptionURI) {
		AleClientUtil aleClientUtil = new AleClientUtil();
		aleClientUtil.initializeAleProxy(aleClientEndPoint);
		aleClientUtil.subscribeECSpec(definedECSpecName, ecSpecSubscriptionURI);
	}

	private void setUpEPCIS(String epcisClientCaptureEndPoint, CLCBProc clCBProc, EPCISMasterDataDocumentType epcisMasterDataDocument) {

		String clCBProcID = clCBProc.getId();
		String clCBProcName = clCBProc.getName();

		boolean simpleMasterDataCaptureSucceeded = false;

		MasterDataCaptureClient masterDataCaptureClient = new MasterDataCaptureClient(epcisClientCaptureEndPoint);

		// Save openLoopCBProcID
		simpleMasterDataCaptureSucceeded = masterDataCaptureClient.simpleMasterDataAndAttributeInsertOrAlter(EpcisConstants.BUSINESS_TRANSACTION_ID,
				openLoopCBProcID, "Name", openLoopCBPro.getName());
		if (simpleMasterDataCaptureSucceeded) {
			LOG.debug("Master Data " + openLoopCBProcID + " saccesfuly saved!");
			simpleMasterDataCaptureSucceeded = false;
		}
		else {
			LOG.debug("The Master Data " + openLoopCBProcID + " could NOT be captured!");
		}

		// Save clCBProcID
		simpleMasterDataCaptureSucceeded = masterDataCaptureClient.simpleMasterDataAndAttributeInsertOrAlter(EpcisConstants.BUSINESS_TRANSACTION_ID,
				openLoopCBProcID + "," + clCBProcID, "Name", clCBProcName);
		if (simpleMasterDataCaptureSucceeded) {
			LOG.debug("Master Data " + openLoopCBProcID + "," + clCBProcID + " saccesfuly saved!");
			simpleMasterDataCaptureSucceeded = false;
		}
		else {
			LOG.debug("The Master Data " + openLoopCBProcID + "," + clCBProcID + " could NOT be captured!");
		}

		// ==== Save the Individual Attributes to their relative
		// Vocabularies=====

		MasterDataCaptureUtils masterDataCaptureUtils = new MasterDataCaptureUtils();

		masterDataCaptureUtils.saveIndividualAttr(epcisMasterDataDocument, masterDataCaptureClient);

		// ===========================================================================

		// Save the hole Transaction Event from the given
		// epcisMasterDataDocument
		masterDataCaptureUtils.saveTransactionEvent(epcisMasterDataDocument, masterDataCaptureClient, openLoopCBProcID + "," + clCBProcID);

	}

	private void setUpBEG(EPCISMasterDataDocumentType epcisMasterDataDocument,String begEngineEndpoint, String repositoryCaptureURL, String ecSpecSubscriptionURI) {

		String[] ecSpecSubscriptionURISplited = ecSpecSubscriptionURI.split(":");
		String begListeningPort = ecSpecSubscriptionURISplited[ecSpecSubscriptionURISplited.length-1];
		
		BegEngineClient begEngineClient = new BegEngineClient(begEngineEndpoint);
		
		ArrayList<VocabularyType> vocabularyTypeList = (ArrayList<VocabularyType>) epcisMasterDataDocument.getEPCISBody().getVocabularyList()
				.getVocabulary();

		for (VocabularyType vocabularyType : vocabularyTypeList) {
			VocabularyElementListType vocabularyElementList = vocabularyType.getVocabularyElementList();

			ArrayList<VocabularyElementType> vocabularyElementTypeList = (ArrayList<VocabularyElementType>) vocabularyElementList
					.getVocabularyElement();

			for (VocabularyElementType vocabularyElementType : vocabularyElementTypeList) {

				begEngineClient.startBegForEvent(vocabularyElementType, repositoryCaptureURL, begListeningPort);
			}

		}

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
			else if (extendedAttribute.getName().equals("BegEngineEndpoint")) {
				processedEBProc.setBegEngineEndpoint(extendedAttribute.getValue());
			}

		}
		return processedEBProc;
	}

	public Integer getReplyID() {
		return replyID;
	}

}
