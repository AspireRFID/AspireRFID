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

package org.ow2.aspirerfid.beg.core.capture;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.ow2.aspirerfid.commons.ale.utils.DeserializerUtil;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReport;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReportGroup;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReportGroupListMember;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReports;
import org.ow2.aspirerfid.commons.epcglobal.commons.EPC;

import org.ow2.aspirerfid.commons.epcis.model.ActionType;
import org.ow2.aspirerfid.commons.epcis.model.AggregationEventType;
import org.ow2.aspirerfid.commons.epcis.model.AttributeType;
import org.ow2.aspirerfid.commons.epcis.model.BusinessLocationType;
import org.ow2.aspirerfid.commons.epcis.model.BusinessTransactionListType;
import org.ow2.aspirerfid.commons.epcis.model.BusinessTransactionType;
import org.ow2.aspirerfid.commons.epcis.model.EPCISBodyType;
import org.ow2.aspirerfid.commons.epcis.model.EPCISDocumentType;
import org.ow2.aspirerfid.commons.epcis.model.EPCListType;
import org.ow2.aspirerfid.commons.epcis.model.EventListType;
import org.ow2.aspirerfid.commons.epcis.model.ObjectEventType;
import org.ow2.aspirerfid.commons.epcis.model.QuantityEventType;
import org.ow2.aspirerfid.commons.epcis.model.ReadPointType;
import org.ow2.aspirerfid.commons.epcis.model.TransactionEventType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;

import org.ow2.aspirerfid.beg.core.capture.CaptureClient;
import org.ow2.aspirerfid.beg.core.capture.CaptureReport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.ow2.aspirerfid.commons.beg.model.BusinessCtx;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class CaptureReport extends Thread {

	/**
	 * The logger.
	 */
	private static Log log = LogFactory.getLog(CaptureReport.class);

	private int port;

	private CaptureClient captureClient = null;

	private ServerSocket serverSocket = null;

	private String epcisRepository = "";

	private boolean activated = false;

	private String begID = "";

	private BusinessCtx businessCtx = new BusinessCtx();

	private String eventType = "";

	// defines if the captured report will be handles or not
	boolean handleTheReport = false;

	// the retrieved transactionID from the captured report
	private String bizTransactionCapturedID = "";

	private String oldBizTransactionCapturedID = "";

	private String parentObjectID = "";

	// The MasterData Vocabularys transaction ID (URI)
	private String vocabularyTransactionID = "";

	// private ArrayList<String> ecreportNames = null;

	// used for storing all the epcs for a specific transaction ID
	private ArrayList<String> epcListForTransaction = new ArrayList<String>();

	private List<EPC> epcs = new LinkedList<EPC>();

	// get the current time and set the eventTime
	// XMLGregorianCalendar now = null;

	public CaptureReport(VocabularyElementType vocabularyElementType, String epcisRepository, String port) throws IOException {

		this.port = Integer.parseInt(port);
		this.epcisRepository = epcisRepository;

		this.captureClient = new CaptureClient(this.epcisRepository);
		businessCtx = getBusinessCtx(vocabularyElementType);
		serverSocket = new ServerSocket(this.port);
		begID = vocabularyElementType.getId();
		eventType = businessCtx.getEventType();
		vocabularyTransactionID = businessCtx.getBusinessTransactionID();
		// ecreportNames = businessCtx.getEcReportNames();

		activated = true;
		start(); // Calls run()
	}

	public void run() {
		while (activated) {
			log.debug("Recieved Buffer:");
			try {
				Socket s = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

				String data = in.readLine();
				String buf = "";
				// ignore the http header
				data = in.readLine();
				data = in.readLine();
				data = in.readLine();
				data = in.readLine();

				log.debug("Recieved Data From Port " + port);
				while (data != null) {
					buf += data;
					data = in.readLine();
					log.debug("XML DATA: " + data);
				}

				// create a stream from the buf
				InputStream parseStream = new ByteArrayInputStream(buf.getBytes());

				// parse the string
				ECReports reports = DeserializerUtil.deserializeECReports(parseStream);
				if (reports != null) {
					List<ECReport> theReports = reports.getReports().getReport();
					if (theReports != null) {
						log.debug("Recieved ECRpeport Names:");
						for (ECReport report : theReports) {
							log.debug(report.getReportName());
							if (report.getReportName().split("@")[1].equals(vocabularyTransactionID)) {
								handleTheReport = true;
							}
						}
						if (handleTheReport) {
							log.debug("The report will be handled");

							if (eventType.equals("ObjectEvent")) {
								handleObjectEventReports(theReports);
							}
							else if (eventType.equals("AggregationEvent")) {
								handleAggregationEventReports(theReports);
							}
							else if (eventType.equals("QuantityEvent")) {
								handleQuantityEventReports(theReports);
							}
							else if (eventType.equals("TransactionEvent")) {
								handleTransactionEventReports(theReports);
							}
							handleTheReport = false;
						}
						else {
							log.debug("Transaction ID does not mach, the report will not be handled!");
						}

					}

				}
			}
			catch (Exception e) {

				log.debug("ERROR: " + e.getMessage() + "\n");

				e.printStackTrace();
				// System.exit(2);
			}
		}
		try {
			serverSocket.close();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		log.debug("BEG with ID: " + begID + " Stoped!\n");

	}

	private void handleTransactionEventReports(List<ECReport> theReports) throws IOException, JAXBException {
		// TODO Auto-generated method stub
	}

	private void handleQuantityEventReports(List<ECReport> theReports) throws IOException, JAXBException {
		// TODO Auto-generated method stub

	}

	private void handleAggregationEventReports(List<ECReport> theReports) throws IOException, JAXBException {

		AggregationEventType aggregationEvent = new AggregationEventType();
		XMLGregorianCalendar now = getCurrentTime();

		EPCISDocumentType epcisDoc = new EPCISDocumentType();
		EPCISBodyType epcisBody = new EPCISBodyType();
		EventListType eventList = new EventListType();

		log.debug("****Handling incomming reports****");

		// // collect all the tags
		// List<EPC> epcs = new LinkedList<EPC>();
		for (ECReport report : theReports) {
			// log.debug("Report Count: "+report.getGroup().size());
			log.debug("****Report Name:" + report.getReportName() + "****");
			if (report.getGroup() != null && report.getReportName().startsWith("bizTransactionIDs")) {// &&
				// ecreportNames.contains(report.getReportName())

				for (ECReportGroup group : report.getGroup()) {
					log.debug("Group Count: " + group.getGroupCount().getCount());
					log.debug("Group Name: " + group.getGroupName());
					if (group.getGroupList() != null) {
						for (ECReportGroupListMember member : group.getGroupList().getMember()) {
							if (member.getEpc() != null) {
								log.debug("***Recieved Group Values***");
								log.debug("RawDecimal Value: " + member.getRawDecimal().getValue());
								{
									if (!(member.getEpc() == null))
										// epcs.add(member.getEpc());
										bizTransactionCapturedID = member.getEpc().getValue();

									log.debug("bizTransactionCapturedID EPC Value: " + member.getEpc().getValue());
									if ((member.getEpc() == null))
										log.debug("TransactionID EPC Value: null");
								}
								log.debug("bizTransactionCapturedID RawHex Value: " + member.getRawHex().getValue());
								log.debug("bizTransactionCapturedID Tag Value: " + member.getTag().getValue());
								// log.debug("Group Value:"+member.getExtension().getFieldList().toString());
							}
						}
					}
				}
			}
			if (report.getGroup() != null && report.getReportName().startsWith("parentObjects")) {// &&
				// ecreportNames.contains(report.getReportName())
				for (ECReportGroup group : report.getGroup()) {
					log.debug("Group Count: " + group.getGroupCount().getCount());
					log.debug("Group Name: " + group.getGroupName());
					if (group.getGroupList() != null) {
						for (ECReportGroupListMember member : group.getGroupList().getMember()) {
							if (member.getEpc() != null) {
								log.debug("***Recieved Group Values***");
								log.debug("RawDecimal Value: " + member.getRawDecimal().getValue());
								{
									if (!(member.getEpc() == null))
										// epcs.add(member.getEpc());
										parentObjectID = member.getEpc().getValue();

									log.debug("bizTransactionCapturedID EPC Value: " + member.getEpc().getValue());
									if ((member.getEpc() == null))
										log.debug("TransactionID EPC Value: null");
								}
								log.debug("bizTransactionCapturedID RawHex Value: " + member.getRawHex().getValue());
								log.debug("bizTransactionCapturedID Tag Value: " + member.getTag().getValue());
								// log.debug("Group Value:"+member.getExtension().getFieldList().toString());
							}
						}
					}
				}
			}
			if (report.getGroup() != null && report.getReportName().startsWith("transactionItems")) {// &&
				// ecreportNames.contains(report.getReportName())
				for (ECReportGroup group : report.getGroup()) {
					log.debug("Group Count: " + group.getGroupCount().getCount());
					log.debug("Group Name: " + group.getGroupName());
					if (group.getGroupList() != null) {
						for (ECReportGroupListMember member : group.getGroupList().getMember()) {
							if (member.getEpc() != null) {
								log.debug("***Recieved Group Values***");
								log.debug("RawDecimal Value: " + member.getRawDecimal().getValue());
								{
									if (!(member.getEpc() == null))
										epcs.add(member.getEpc());
									log.debug("Epc Value: " + member.getEpc().getValue());
									if ((member.getEpc() == null))
										log.debug("Epc Value: null");
								}
								log.debug("RawHex Value: " + member.getRawHex().getValue());
								log.debug("Tag Value: " + member.getTag().getValue());
								// log.debug("Group Value:"+member.getExtension().getFieldList().toString());
							}
						}
					}
				}
			}
		}

		if (epcs.size() == 0) {
			log.debug("no epc received - generating no event");
			return;
		}

		aggregationEvent.setEventTime(now);
		aggregationEvent.setEventTimeZoneOffset(EventTimeZoneOffset(now));

		if (!bizTransactionCapturedID.equals("")) {
			BusinessTransactionListType businessTransactionListType = new BusinessTransactionListType();
			BusinessTransactionType businessTransactionType = new BusinessTransactionType();
			if (!businessCtx.getBusinessTransactionTypeID().equals("")) {
				businessTransactionType.setType(businessCtx.getBusinessTransactionTypeID());
			}
			else {
				businessTransactionType.setType("urn:epc:transaction:type:general");
			}
			businessTransactionType.setValue(bizTransactionCapturedID);
			businessTransactionListType.getBizTransaction().add(businessTransactionType);
			aggregationEvent.setBizTransactionList(businessTransactionListType);
		}
		else {
			return;
		}

		EPCListType epcList = new EPCListType();
		// add the epcs
		for (EPC epc : epcs) {
			// org.ow2.aspirerfid.commons.epcis.model.EPC nepc = new
			// org.ow2.aspirerfid.commons.epcis.model.EPC();
			EPC nepc = new EPC();
			nepc.setValue(epc.getValue());
			epcList.getEpc().add(nepc);
		}
		aggregationEvent.setChildEPCs(epcList);
		aggregationEvent.setParentID(parentObjectID);

		// set action
		if (!businessCtx.getAction().equals(null))
			aggregationEvent.setAction(businessCtx.getAction());

		// set bizStep
		if (!businessCtx.getBizStep().equals(null) || !businessCtx.getBizStep().equals(""))
			aggregationEvent.setBizStep(businessCtx.getBizStep());

		// set disposition
		if (!businessCtx.getDisposition().equals(null) || !businessCtx.getDisposition().equals(""))
			aggregationEvent.setDisposition(businessCtx.getDisposition());

		// set readPoint
		if (!businessCtx.getReadPoint().equals(null)) {
			aggregationEvent.setReadPoint(businessCtx.getReadPoint());
		}

		// set bizLocation
		if (!businessCtx.getBizLocation().equals(null)) {
			aggregationEvent.setBizLocation(businessCtx.getBizLocation());
		}

		eventList.getObjectEventOrAggregationEventOrQuantityEvent().add(aggregationEvent);

		epcisBody.setEventList(eventList);
		epcisDoc.setEPCISBody(epcisBody);
		epcisDoc.setSchemaVersion(new BigDecimal("1.0"));
		epcisDoc.setCreationDate(now);

		int httpResponseCode = captureClient.capture(epcisDoc);
		if (httpResponseCode != 200) {
			log.debug("The event could NOT be captured!\n");
		}
		else if (httpResponseCode == 200) {
			epcs.clear();
		}

	}

	private void handleObjectEventReports(List<ECReport> theReports) throws IOException, JAXBException {
		ObjectEventType objEvent = null;
		XMLGregorianCalendar now = getCurrentTime();
		EPCISDocumentType epcisDoc = new EPCISDocumentType();
		EPCISBodyType epcisBody = new EPCISBodyType();
		EventListType eventList = new EventListType();
		EPCListType epcList = new EPCListType();

		log.debug("****Handling incomming reports****");

		// collect the tags transaction ID
		for (ECReport report : theReports) {

			// log.debug("Report Count: "+report.getGroup().size());
			log.debug("****Report Name:" + report.getReportName() + "****");
			if (report.getGroup() != null && report.getReportName().startsWith("bizTransactionIDs")) {// &&
				// ecreportNames.contains(report.getReportName())
				for (ECReportGroup group : report.getGroup()) {
					log.debug("Group Count: " + group.getGroupCount().getCount());
					log.debug("Group Name: " + group.getGroupName());
					if (group.getGroupList() != null) {
						for (ECReportGroupListMember member : group.getGroupList().getMember()) {
							if (member.getEpc() != null) {
								log.debug("***Recieved Group Values***");
								log.debug("RawDecimal Value: " + member.getRawDecimal().getValue());
								{
									if (!(member.getEpc() == null))
										// epcs.add(member.getEpc());
										bizTransactionCapturedID = member.getEpc().getValue();

									log.debug("bizTransactionCapturedID EPC Value: " + member.getEpc().getValue());
									if ((member.getEpc() == null))
										log.debug("TransactionID EPC Value: null");
								}
								log.debug("bizTransactionCapturedID RawHex Value: " + member.getRawHex().getValue());
								log.debug("bizTransactionCapturedID Tag Value: " + member.getTag().getValue());
								// log.debug("Group Value:"+member.getExtension().getFieldList().toString());
							}
						}
					}
				}
			}
			// collect all the tags
			if (report.getGroup() != null && report.getReportName().startsWith("transactionItems")) {// &&
				// ecreportNames.contains(report.getReportName())
				for (ECReportGroup group : report.getGroup()) {
					log.debug("Group Count: " + group.getGroupCount().getCount());
					log.debug("Group Name: " + group.getGroupName());
					if (group.getGroupList() != null) {
						for (ECReportGroupListMember member : group.getGroupList().getMember()) {
							if (member.getEpc() != null) {
								log.debug("***Recieved Group Values***");
								log.debug("RawDecimal Value: " + member.getRawDecimal().getValue());
								{
									if (!(member.getEpc() == null))
										epcs.add(member.getEpc());
									log.debug("Epc Value: " + member.getEpc().getValue());
									if ((member.getEpc() == null))
										log.debug("Epc Value: null");
								}
								log.debug("RawHex Value: " + member.getRawHex().getValue());
								log.debug("Tag Value: " + member.getTag().getValue());
								// log.debug("Group Value:"+member.getExtension().getFieldList().toString());
							}
						}
					}
				}
			}
		}

		if (!oldBizTransactionCapturedID.equals(bizTransactionCapturedID)) {
			epcListForTransaction.clear();
		}

		oldBizTransactionCapturedID = bizTransactionCapturedID;

		if (epcs.size() == 0) {
			log.debug("no epc received - generating no event");
			return;
		}

		// create the ecpis event
		objEvent = new ObjectEventType();
		objEvent.setEventTime(now);
		objEvent.setEventTimeZoneOffset(EventTimeZoneOffset(now));

		if (!bizTransactionCapturedID.equals("")) {
			BusinessTransactionListType businessTransactionListType = new BusinessTransactionListType();
			BusinessTransactionType businessTransactionType = new BusinessTransactionType();
			if (!businessCtx.getBusinessTransactionTypeID().equals("")) {
				businessTransactionType.setType(businessCtx.getBusinessTransactionTypeID());
			}
			else {
				businessTransactionType.setType("urn:epc:transaction:type:general");
			}
			businessTransactionType.setValue(bizTransactionCapturedID);
			businessTransactionListType.getBizTransaction().add(businessTransactionType);
			objEvent.setBizTransactionList(businessTransactionListType);
		}
		else {
			return;
		}

		// add the epcs
		for (EPC epc : epcs) {
			// org.ow2.aspirerfid.commons.epcis.model.EPC nepc = new
			// org.ow2.aspirerfid.commons.epcis.model.EPC();
			EPC nepc = new EPC();
			nepc.setValue(epc.getValue());
			epcList.getEpc().add(nepc);
			epcListForTransaction.add(epc.getValue());
		}

		objEvent.setEpcList(epcList);

		// set action
		if (!businessCtx.getAction().equals(null))
			objEvent.setAction(businessCtx.getAction());

		// set bizStep
		if (!businessCtx.getBizStep().equals(null) || !businessCtx.getBizStep().equals(""))
			objEvent.setBizStep(businessCtx.getBizStep());

		// set disposition
		if (!businessCtx.getDisposition().equals(null) || !businessCtx.getDisposition().equals(""))
			objEvent.setDisposition(businessCtx.getDisposition());

		// set readPoint
		if (!businessCtx.getReadPoint().equals(null)) {
			objEvent.setReadPoint(businessCtx.getReadPoint());
		}

		// set bizLocation
		if (!businessCtx.getBizLocation().equals(null)) {
			objEvent.setBizLocation(businessCtx.getBizLocation());
		}

		eventList.getObjectEventOrAggregationEventOrQuantityEvent().add(objEvent);

		epcisBody.setEventList(eventList);
		epcisDoc.setEPCISBody(epcisBody);
		epcisDoc.setSchemaVersion(new BigDecimal("1.0"));
		epcisDoc.setCreationDate(now);

		int httpResponseCode = captureClient.capture(epcisDoc);
		if (httpResponseCode != 200) {
			log.debug("The event could NOT be captured!\n");
		}
		else if (httpResponseCode == 200) {
			epcs.clear();
		}

	}

	private void handleReports(List<ECReport> theReports) throws IOException, JAXBException {

		ObjectEventType objEvent = null;
		AggregationEventType aggregationEvent = null;
		QuantityEventType quantityEvent = null;
		TransactionEventType transactionEvent = null;

		XMLGregorianCalendar now = getCurrentTime();

		EPCISDocumentType epcisDoc = new EPCISDocumentType();
		EPCISBodyType epcisBody = new EPCISBodyType();
		EventListType eventList = new EventListType();

		log.debug("****Handling incomming reports****");

		// collect all the tags
		List<EPC> epcs = new LinkedList<EPC>();
		for (ECReport report : theReports) {
			// log.debug("Report Count: "+report.getGroup().size());
			log.debug("****Report Name:" + report.getReportName() + "****");
			if (report.getGroup() != null && report.getReportName().startsWith("bizTransactionIDs")) {// &&
				// ecreportNames.contains(report.getReportName())
				for (ECReportGroup group : report.getGroup()) {
					log.debug("Group Count: " + group.getGroupCount().getCount());
					log.debug("Group Name: " + group.getGroupName());
					if (group.getGroupList() != null) {
						for (ECReportGroupListMember member : group.getGroupList().getMember()) {
							if (member.getEpc() != null) {
								log.debug("***Recieved Group Values***");
								log.debug("RawDecimal Value: " + member.getRawDecimal().getValue());
								{
									if (!(member.getEpc() == null))
										// epcs.add(member.getEpc());
										bizTransactionCapturedID = member.getEpc().getValue();

									log.debug("bizTransactionCapturedID EPC Value: " + member.getEpc().getValue());
									if ((member.getEpc() == null))
										log.debug("TransactionID EPC Value: null");
								}
								log.debug("bizTransactionCapturedID RawHex Value: " + member.getRawHex().getValue());
								log.debug("bizTransactionCapturedID Tag Value: " + member.getTag().getValue());
								// log.debug("Group Value:"+member.getExtension().getFieldList().toString());
							}
						}
					}
				}
			}
			if (report.getGroup() != null && report.getReportName().startsWith("transactionItems")) {// &&
				// ecreportNames.contains(report.getReportName())
				for (ECReportGroup group : report.getGroup()) {
					log.debug("Group Count: " + group.getGroupCount().getCount());
					log.debug("Group Name: " + group.getGroupName());
					if (group.getGroupList() != null) {
						for (ECReportGroupListMember member : group.getGroupList().getMember()) {
							if (member.getEpc() != null) {
								log.debug("***Recieved Group Values***");
								log.debug("RawDecimal Value: " + member.getRawDecimal().getValue());
								{
									if (!(member.getEpc() == null))
										epcs.add(member.getEpc());
									log.debug("Epc Value: " + member.getEpc().getValue());
									if ((member.getEpc() == null))
										log.debug("Epc Value: null");
								}
								log.debug("RawHex Value: " + member.getRawHex().getValue());
								log.debug("Tag Value: " + member.getTag().getValue());
								// log.debug("Group Value:"+member.getExtension().getFieldList().toString());
							}
						}
					}
				}
			}
		}

		if (epcs.size() == 0) {
			log.debug("no epc received - generating no event");
			return;
		}

		if (eventType.equals("ObjectEvent")) {
			// create the ecpis event
			objEvent = new ObjectEventType();
			objEvent.setEventTime(now);
			objEvent.setEventTimeZoneOffset(EventTimeZoneOffset(now));

			EPCListType epcList = new EPCListType();
			// add the epcs
			for (EPC epc : epcs) {
				// org.ow2.aspirerfid.commons.epcis.model.EPC nepc = new
				// org.ow2.aspirerfid.commons.epcis.model.EPC();
				EPC nepc = new EPC();
				nepc.setValue(epc.getValue());
				epcList.getEpc().add(nepc);
			}
			objEvent.setEpcList(epcList);

			// set action
			if (!businessCtx.getAction().equals(null))
				objEvent.setAction(businessCtx.getAction());

			// set bizStep
			if (!businessCtx.getBizStep().equals(null) || !businessCtx.getBizStep().equals(""))
				objEvent.setBizStep(businessCtx.getBizStep());

			// set disposition
			if (!businessCtx.getDisposition().equals(null) || !businessCtx.getDisposition().equals(""))
				objEvent.setDisposition(businessCtx.getDisposition());

			// set readPoint
			if (!businessCtx.getReadPoint().equals(null)) {
				objEvent.setReadPoint(businessCtx.getReadPoint());
			}

			// set bizLocation
			if (!businessCtx.getBizLocation().equals(null)) {
				objEvent.setBizLocation(businessCtx.getBizLocation());
			}

			if (!bizTransactionCapturedID.equals("")) {
				BusinessTransactionListType businessTransactionListType = new BusinessTransactionListType();
				BusinessTransactionType businessTransactionType = new BusinessTransactionType();
				if (!businessCtx.getBusinessTransactionTypeID().equals("")) {
					businessTransactionType.setType(businessCtx.getBusinessTransactionTypeID());
				}
				else {
					businessTransactionType.setType("urn:epc:transaction:type:general");
				}
				businessTransactionType.setValue(bizTransactionCapturedID);
				businessTransactionListType.getBizTransaction().add(businessTransactionType);
				objEvent.setBizTransactionList(businessTransactionListType);
			}

			eventList.getObjectEventOrAggregationEventOrQuantityEvent().add(objEvent);
			// =================================================
		}
		else if (eventType.equals("AggregationEvent")) {
			eventList.getObjectEventOrAggregationEventOrQuantityEvent().add(aggregationEvent);

		}
		else if (eventType.equals("QuantityEvent")) {
			eventList.getObjectEventOrAggregationEventOrQuantityEvent().add(quantityEvent);

		}
		else if (eventType.equals("TransactionEvent")) {
			eventList.getObjectEventOrAggregationEventOrQuantityEvent().add(transactionEvent);

		}

		epcisBody.setEventList(eventList);
		epcisDoc.setEPCISBody(epcisBody);
		epcisDoc.setSchemaVersion(new BigDecimal("1.0"));
		epcisDoc.setCreationDate(now);

		int httpResponseCode = captureClient.capture(epcisDoc);
		if (httpResponseCode != 200) {
			log.debug("The event could NOT be captured!\n");
		}

	}

	private String EventTimeZoneOffset(XMLGregorianCalendar now) {
		// get the current time zone and set the eventTimeZoneOffset
		if (now != null) {
			int timezone = now.getTimezone();
			int h = Math.abs(timezone / 60);
			int m = Math.abs(timezone % 60);
			DecimalFormat format = new DecimalFormat("00");
			String sign = (timezone < 0) ? "-" : "+";
			return sign + format.format(h) + ":" + format.format(m);
		}
		return null;
	}

	private XMLGregorianCalendar getCurrentTime() {
		// get the current time and set the eventTime
		XMLGregorianCalendar currentTime = null;
		try {
			DatatypeFactory dataFactory = DatatypeFactory.newInstance();
			currentTime = dataFactory.newXMLGregorianCalendar(new GregorianCalendar());
			log.debug("Event Time:" + currentTime.getHour() + ":" + currentTime.getMinute() + ":" + ":" + currentTime.getSecond() + "\n");
		}
		catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return currentTime;
	}

	private BusinessCtx getBusinessCtx(VocabularyElementType vocabularyElementType) {

		BusinessCtx businessCtx = new BusinessCtx();

		String[] transactionIdPath = vocabularyElementType.getId().split(",");

		businessCtx.setBusinessTransactionID(transactionIdPath[transactionIdPath.length - 1]);

		for (AttributeType vocElementAttribute : vocabularyElementType.getAttribute()) {
			if (vocElementAttribute.getId().endsWith("business_location")) {
				businessCtx.setBizLocation(vocElementAttribute.getOtherAttributes().get(new QName("value")));
			}
			else if (vocElementAttribute.getId().endsWith("business_step")) {
				businessCtx.setBizStep(vocElementAttribute.getOtherAttributes().get(new QName("value")));
			}
			else if (vocElementAttribute.getId().endsWith("disposition")) {
				businessCtx.setDisposition(vocElementAttribute.getOtherAttributes().get(new QName("value")));
			}
			else if (vocElementAttribute.getId().endsWith("read_point")) {
				businessCtx.setReadPoint(vocElementAttribute.getOtherAttributes().get(new QName("value")));
			}
			else if (vocElementAttribute.getId().endsWith("action")) {
				businessCtx.setAction(vocElementAttribute.getOtherAttributes().get(new QName("value")));
			}
			else if (vocElementAttribute.getId().endsWith("event_type")) {
				businessCtx.setEventType(vocElementAttribute.getOtherAttributes().get(new QName("value")));
			}
			// else if (vocElementAttribute.getId().endsWith("ecreport_names"))
			// {
			// businessCtx.setEcReportNames(vocElementAttribute.getOtherAttributes().get(new
			// QName("value")));
			// }
			else if (vocElementAttribute.getId().endsWith("transaction_type")) {
				businessCtx.setBusinessTransactionTypeID(vocElementAttribute.getOtherAttributes().get(new QName("value")));
			}
		}

		return businessCtx;
	}

	/**
	 * @return
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * @param starts
	 *            or stops the event generation
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	/**
	 * @return for which transaction ID is currently generating Events
	 */
	public String getBizTransactionID() {
		return bizTransactionCapturedID;
	}

	/**
	 * @return the port it listens to
	 */
	public int getPort() {
		return port;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public ArrayList<String> getEpcListForTransaction() {
		return epcListForTransaction;
	}

}
