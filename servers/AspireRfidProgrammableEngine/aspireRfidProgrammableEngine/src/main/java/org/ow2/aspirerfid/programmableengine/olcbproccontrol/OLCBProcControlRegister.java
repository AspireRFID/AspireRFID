package org.ow2.aspirerfid.programmableengine.olcbproccontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReportSpec;
import org.ow2.aspirerfid.commons.ale.model.ale.ECSpec;
import org.ow2.aspirerfid.commons.ale.model.alelr.LRSpec;
import org.ow2.aspirerfid.commons.apdl.model.ApdlDataField;
import org.ow2.aspirerfid.commons.apdl.model.ApdlDataFields;
import org.ow2.aspirerfid.commons.apdl.model.CLCBProc;
import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.ow2.aspirerfid.commons.epcis.model.EPCISMasterDataDocumentType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementListType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyType;
import org.ow2.aspirerfid.commons.pe.exceptions.NotCompletedExecutionException;
import org.ow2.aspirerfid.commons.pe.exceptions.OLCBProcValidationException;
import org.ow2.aspirerfid.commons.xpdl.model.ExtendedAttribute;
import org.ow2.aspirerfid.programmableengine.aleclient.AleClientUtil;
import org.ow2.aspirerfid.programmableengine.aleclient.AleLrClientUtil;
import org.ow2.aspirerfid.programmableengine.begclient.BegEngineClient;
import org.ow2.aspirerfid.programmableengine.encode.EncodeImplementation;
import org.ow2.aspirerfid.programmableengine.epcisclient.EpcisConstants;
import org.ow2.aspirerfid.programmableengine.epcisclient.MasterDataCaptureClient;
import org.ow2.aspirerfid.programmableengine.epcisclient.MasterDataCaptureUtils;
import org.ow2.aspirerfid.programmableengine.objects.ProcessedEBProc;

public class OLCBProcControlRegister {

	/** logger. */
	public static final Logger LOG = Logger.getLogger(EncodeImplementation.class);

	/**
	 * The Object where the various steps success or not feedback will be stored
	 */
	private HashMap<String, String> olcbProcControlRegisterStepsFeedback = null;

	// private Integer replyID = 425;

	private OLCBProc openLoopCBPro = null;

	private AleClientUtil aleClientUtil = null;

	private String openLoopCBProcID = "";

	public OLCBProcControlRegister(OLCBProc openLoopCBProc) throws OLCBProcValidationException,
			NotCompletedExecutionException {
		this.openLoopCBPro = openLoopCBProc;
		openLoopCBProcID = openLoopCBProc.getId();

		LOG.debug("Recieved OpenLoopCBProc ID for Encode: " + openLoopCBProc.getId());
		LOG.debug("Recieved OpenLoopCBProc Name for Encode: " + openLoopCBProc.getName());

		register();
	}

	private void register() {

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

						// SetUp openLoopCBProc (Standar Vocabulary) Epcis Master Data

						// Define LRSpec
						setUpAleLR(processedEBProc.getAleLrClientEndPoint(), processedEBProc.getLrSpecs());

						// DefineECSpec
						defineECSpec(processedEBProc.getAleClientEndPoint(), processedEBProc.getId(), processedEBProc
								.getDefinedECSpecName(), processedEBProc.getEcSpec());

						/**
						 * SetUp EBProc (User Vocabulary) MasterData, closeLoopCBProc
						 * (User Vocabulary) Epcis Master Data and SetUp
						 * openLoopCBProc (Standar Vocabulary) Epcis Master Data
						 */
						setUpEPCIS(processedEBProc.getEpcisClientCaptureEndPoint(), clCBProc, processedEBProc
								.getEpcisMasterDataDocument());

						// SetUp BEG
						setUpBEG(processedEBProc.getEpcisMasterDataDocument(), processedEBProc.getBegEngineEndpoint(),
								processedEBProc.getEpcisClientCaptureEndPoint(), processedEBProc.getEcSpecSubscriptionURI());

						// SubscribeECSpec
						subscribeECSpec(processedEBProc.getAleClientEndPoint(), processedEBProc.getDefinedECSpecName(),
								processedEBProc.getEcSpecSubscriptionURI());

						// replyID = 400;
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

	private void setUpEPCIS(String epcisClientCaptureEndPoint, CLCBProc clCBProc,
			EPCISMasterDataDocumentType epcisMasterDataDocument) {

		EPCISMasterDataDocumentType openLoopCBProcEpcisMasterDataDocument = openLoopCBPro.getEPCISMasterDataDocument();
		EPCISMasterDataDocumentType closeLoopCBProcEpcisMasterDataDocument = clCBProc.getEPCISMasterDataDocument();

		MasterDataCaptureUtils masterDataCaptureUtils = new MasterDataCaptureUtils();
		
		String clCBProcID = clCBProc.getId();
		String clCBProcName = clCBProc.getName();

		boolean simpleMasterDataCaptureSucceeded = false;

		MasterDataCaptureClient masterDataCaptureClient = new MasterDataCaptureClient(epcisClientCaptureEndPoint);
		
		//Set Up openLoopCBProc (Standar Vocabulary) Epcis Master Data
		masterDataCaptureUtils.saveMasterDataDocument(openLoopCBProcEpcisMasterDataDocument, masterDataCaptureClient);
	
		//SetUp closeLoopCBProc (User Vocabulary) Epcis Master Data 
		masterDataCaptureUtils.saveMasterDataDocument(closeLoopCBProcEpcisMasterDataDocument, masterDataCaptureClient);
		 
		 
		// Save openLoopCBProcID to BUSINESS_TRANSACTION_ID
		simpleMasterDataCaptureSucceeded = masterDataCaptureClient.insertOrAlterVocElemAttr(
				EpcisConstants.BUSINESS_TRANSACTION_ID, openLoopCBProcID, "Name", openLoopCBPro.getName());
		if (simpleMasterDataCaptureSucceeded) {
			LOG.debug("Master Data " + openLoopCBProcID + " saccesfuly saved!");
			simpleMasterDataCaptureSucceeded = false;
		} else {
			LOG.debug("The Master Data " + openLoopCBProcID + " could NOT be captured!");
		}

		// Save clCBProcID to BUSINESS_TRANSACTION_ID
		simpleMasterDataCaptureSucceeded = masterDataCaptureClient.insertOrAlterVocElemAttr(
				EpcisConstants.BUSINESS_TRANSACTION_ID, openLoopCBProcID + "," + clCBProcID, "Name", clCBProcName);
		if (simpleMasterDataCaptureSucceeded) {
			LOG.debug("Master Data " + openLoopCBProcID + "," + clCBProcID + " saccesfuly saved!");
			simpleMasterDataCaptureSucceeded = false;
		} else {
			LOG.debug("The Master Data " + openLoopCBProcID + "," + clCBProcID + " could NOT be captured!");
		}

		// ==== Save the Individual Attributes to their relative Vocabularies=====

		masterDataCaptureUtils.saveIndividualAttr(epcisMasterDataDocument, masterDataCaptureClient);

		// ===========================================================================

		// Save the hole Transaction Event from the given
		// epcisMasterDataDocument
		masterDataCaptureUtils.saveTransactionEvent(epcisMasterDataDocument, masterDataCaptureClient, openLoopCBProcID
				+ "," + clCBProcID);

	}

	private void setUpBEG(EPCISMasterDataDocumentType epcisMasterDataDocument, String begEngineEndpoint,
			String repositoryCaptureURL, String ecSpecSubscriptionURI) {

		String[] ecSpecSubscriptionURISplited = ecSpecSubscriptionURI.split(":");
		String begListeningPort = ecSpecSubscriptionURISplited[ecSpecSubscriptionURISplited.length - 1];

		BegEngineClient begEngineClient = new BegEngineClient(begEngineEndpoint);

		ArrayList<VocabularyType> vocabularyTypeList = (ArrayList<VocabularyType>) epcisMasterDataDocument.getEPCISBody()
				.getVocabularyList().getVocabulary();

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
			} else if (apdlDataField.getType().equals("ECSpec")) {
				ecSpec = apdlDataField.getECSpec();
				processedEBProc.setEcSpec(ecSpec);
				processedEBProc.setDefinedECSpecName(apdlDataField.getName());
			} else if (apdlDataField.getType().equals("LRSpec")) {
				lrSpecs.put(apdlDataField.getName(), apdlDataField.getLRSpec());
			}
		}
		processedEBProc.setLrSpecs(lrSpecs);

		// Get required extensions
		for (ExtendedAttribute extendedAttribute : ebProc.getExtendedAttributes().getExtendedAttribute()) {

			if (extendedAttribute.getName().equals("ECSpecSubscriptionURI")) {
				processedEBProc.setEcSpecSubscriptionURI(extendedAttribute.getValue());
			} else if (extendedAttribute.getName().equals("AleClientEndPoint")) {
				processedEBProc.setAleClientEndPoint(extendedAttribute.getValue());
			} else if (extendedAttribute.getName().equals("AleLrClientEndPoint")) {
				processedEBProc.setAleLrClientEndPoint(extendedAttribute.getValue());
			} else if (extendedAttribute.getName().equals("EpcisClientCaptureEndPoint")) {
				processedEBProc.setEpcisClientCaptureEndPoint(extendedAttribute.getValue());
			} else if (extendedAttribute.getName().equals("EpcisClientQueryEndPoint")) {
				processedEBProc.setEpcisClientQueryEndPoint(extendedAttribute.getValue());
			} else if (extendedAttribute.getName().equals("BegEngineEndpoint")) {
				processedEBProc.setBegEngineEndpoint(extendedAttribute.getValue());
			}

		}
		return processedEBProc;
	}

	public HashMap<String, String> getOLCBProcControlRegisterStepsFeedback() {
		return olcbProcControlRegisterStepsFeedback;
	}

}
