/*
 * Copyright (c) 2008-2010, Aspire
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

package org.ow2.aspirerfid.demos.warehouse.management.beg;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.ow2.aspirerfid.commons.ale.utils.DeserializerUtil;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReport;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReportGroup;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReportGroupListMember;
import org.ow2.aspirerfid.commons.ale.model.ale.ECReports;
import org.ow2.aspirerfid.commons.epcglobal.commons.EPC;
//import org.accada.epcis.model.ActionType;
//import org.accada.epcis.model.BusinessLocationType;
//import org.accada.epcis.model.EPCISBodyType;
//import org.accada.epcis.model.EPCISDocumentType;
//import org.accada.epcis.model.EPCListType;
//import org.accada.epcis.model.EventListType;
//import org.accada.epcis.model.ObjectEventType;
//import org.accada.epcis.model.ReadPointType;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

import org.ow2.aspirerfid.demos.warehouse.management.UI.WarehouseManagement;
import org.ow2.aspirerfid.demos.warehouse.management.tools.DeliveredItem;
//import org.ow2.aspirerfid.demos.warehouse.management.tools.CaptureClient;

import java.math.BigInteger;

/**
 * @author nkef (Nikos Kefalakis)
 * 
 */
public class CaptureReport extends Thread {

	/**
	 * The logger.
	 */
	private static Log log = LogFactory.getLog(CaptureReport.class);

	/**
	 * The configuration
	 */
	private XMLConfiguration config = null;

	private static boolean activated = true;

	private int port;

	// private String epcisRepository;

//	private CaptureClient client = null;

	private ServerSocket ss;

	private DeliveredItem deliveredItem;

	// Data
	private String zoneID = "";
	private String warehouseID = "";
	private String warehousemenGroupName = "";
	private String invoiceGroupName = "";

	
	private String[] packetsGroupName;
	private String[] packetsCompany;
	private String[] packetsDescription;
	private String[] packetsMeasurementID;
	private String[] packetsQuantity;
	private String[] packetsExpectedQuantity;
	
	private int nOFmerchandise = 0;
	

	public CaptureReport(int port, String epcisRepository) {

		// read parameters from configuration file
		config = new XMLConfiguration();
		config.setListDelimiter(',');
		URL fileurl = this.getClass().getResource("/WarehouseParameters.xml");

		// sets the parameters according to the properties file
		try {
			config.load(fileurl);
		} catch (Exception e) {
			String message = "Couldn't get WarehouseParameters at: " + fileurl.getFile() + "\n" + e.getMessage();
			log.debug(message);
			e.printStackTrace();
		}

		nOFmerchandise =  config.getMaxIndex("merchandise.packetsContent.packetsGroupName") + 1;

		
		zoneID = config.getString("zoneID");
		warehouseID = config.getString("warehouseID");
		warehousemenGroupName = config.getString("warehousemenGroupName");
		invoiceGroupName = config.getString("invoiceGroupName");

		//readPointNames = new String[nOFmerchandise];
		packetsGroupName = new String[nOFmerchandise];
		packetsCompany = new String[nOFmerchandise];
		packetsDescription = new String[nOFmerchandise];
		packetsMeasurementID = new String[nOFmerchandise];
		packetsQuantity = new String[nOFmerchandise];
		packetsExpectedQuantity = new String[nOFmerchandise];
		
		for (int i = 0; i < nOFmerchandise; i++) {
			
			packetsGroupName[i] = config.getString("merchandise.packetsContent("+ i +").packetsGroupName");
			packetsCompany[i] = config.getString("merchandise.packetsContent("+ i +").company");
			packetsDescription[i] = config.getString("merchandise.packetsContent("+ i +").description");
			packetsMeasurementID[i] = config.getString("merchandise.packetsContent("+ i +").measurementID");
			packetsQuantity[i] = config.getString("merchandise.packetsContent("+ i +").quantity");
			packetsExpectedQuantity[i] = config.getString("merchandise.packetsContent("+ i +").expectedQuantity");

		}


		this.port = port;
		// this.epcisRepository = epcisRepository;
//		client = new CaptureClient(epcisRepository);
		ss = null;
		try {
			ss = new ServerSocket(port);
		} catch (IOException e1) {
			help();
			log.debug(e1.getMessage());
		}

	}

	private static void help() {
		System.out.println("You need to specify the port where to listen and the url of the epcis repository");
		System.out.println("Example: 9999 http://localhost:8080/aspire0.3.0EpcisRepository/capture");
	}

	/**
	 * @param activated
	 *            the activated to set
	 */
	public static void setActivated(boolean activated) {
		CaptureReport.activated = activated;
	}

	private void handleReports(ECReports reports) throws IOException, JAXBException {
		log.debug("**********************Handling incomming reports****************************");

		// get the current time and set the eventTime
		XMLGregorianCalendar now = null;
		try {
			DatatypeFactory dataFactory = DatatypeFactory.newInstance();
			now = dataFactory.newXMLGregorianCalendar(new GregorianCalendar());

			log.debug("Event Time:" + now.getHour() + ":" + now.getMinute() + ":" + ":" + now.getSecond() + "\n");

		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}

		List<ECReport> theReports = reports.getReports().getReport();
		// collect all the tags
		List<EPC> epcs = new LinkedList<EPC>();
		if (theReports != null) {
			for (ECReport report : theReports) {
				// log.debug("Report Count: "+report.getGroup().size());
				log.debug("***************Report Name:" + report.getReportName() + "**************");
				if (report.getGroup() != null) {
					for (ECReportGroup group : report.getGroup()) {

						if (WarehouseManagement.getEntryDateTextField().equals("")) {
							WarehouseManagement.setEntryDateTextField(now.getDay() + "/" + now.getMonth() + "/" + now.getYear());
						}
						if (WarehouseManagement.getEntryHourTextField().equals("")) {
							WarehouseManagement.setEntryHourTextField(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
						}

						WarehouseManagement.setZoneIDTextField(zoneID);
						WarehouseManagement.setWarehouseIDTextField(warehouseID);

						log.debug("Group Count: " + group.getGroupCount().getCount());
						log.debug("Group Name: " + group.getGroupName());
						if (group.getGroupList() != null) {
							deliveredItem = null;

	
							// warehousemen
							if (group.getGroupName().equals(warehousemenGroupName)) {
								for (ECReportGroupListMember member : group.getGroupList().getMember()) {
									if (member.getEpc() != null) {
										WarehouseManagement.setUserIDTextField(member.getTag().getValue().split(":")[4]);
									}
								}
							}

							// Invoice
							if (group.getGroupName().equals(invoiceGroupName)) {
								for (ECReportGroupListMember member : group.getGroupList().getMember()) {
									if (member.getEpc() != null) {
										WarehouseManagement.setInvoiceIDTextField(member.getTag().getValue().split(":")[4]);
										WarehouseManagement.setOfferingDateTextField("22/05/08");
										WarehouseManagement.setOfferingHourTextField("10:53:22");
									}
								}
							}
//							// Small Packets
//							if (group.getGroupName().equals("urn:epc:pat:gid-96:145.56.*")) {
//								for (ECReportGroupListMember member : group.getGroupList().getMember()) {
//									if (member.getEpc() != null) {
//										// WarehouseManagement.setInvoiceIDTextField(member.getTag().getValue().split(":")[4]);
//									}
//								}
//							}
//							// Medium Packets
//							if (group.getGroupName().equals("urn:epc:pat:gid-96:145.87.*")) {
//								for (ECReportGroupListMember member : group.getGroupList().getMember()) {
//									if (member.getEpc() != null) {
//										// WarehouseManagement.setInvoiceIDTextField(member.getTag().getValue().split(":")[4]);
//									}
//								}
//							}
							
							
							for (int i = 0; i < nOFmerchandise; i++) {

								if (group.getGroupName().equals(packetsGroupName[i])) {
									BigInteger quantity = new BigInteger(packetsQuantity[i]);
									BigInteger expectedQuantity = new BigInteger(packetsExpectedQuantity[i]);
									BigInteger quantityDelivered = new BigInteger((group.getGroupCount().getCount()) + "");
									BigInteger quantityRemain = quantity.add(quantityDelivered.negate());

									for (ECReportGroupListMember member : group.getGroupList().getMember()) {
										if (member.getEpc() != null) {
											deliveredItem = new DeliveredItem();
											deliveredItem.setCompany(packetsCompany[i]);
											deliveredItem.setDeliveryDate(now.getDay() + "/" + now.getMonth() + "/" + now.getYear());
											deliveredItem.setDescription(packetsDescription[i]);
											deliveredItem.setExpectedQuantity(expectedQuantity);
											deliveredItem.setMeasurementID(packetsMeasurementID[i]);
											deliveredItem.setQuantity(quantity);
											deliveredItem.setQuantityDelivered(quantityDelivered);
											deliveredItem.setQuantityRemain(quantityRemain);
											deliveredItem.setItemCode(member.getTag().getValue().split(":")[4]);
											WarehouseManagement.updateDeliveryTableModel(deliveredItem);
											deliveredItem = null;
										}
									}
								}
							}


							// Print All
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
									// log.debug("Group
									// Value:"+member.getExtension().getFieldList().toString());

								}
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
	}

	public void run() {
		while (activated) {
			log.debug("Recieved Buffer:");
			try {
				Socket s = ss.accept();
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
				// log.debug("Recieved Buffer:"+buf);

				// create a stream from the buf
				InputStream parseStream = new ByteArrayInputStream(buf.getBytes());

				// parse the string
				ECReports reports = DeserializerUtil.deserializeECReports(parseStream);
				if (reports != null) {
					handleReports(reports);
				}
			} catch (Exception e) {
				log.debug("ERROR: " + e.getMessage() + "\n");
			}
		}
		log.debug("*********Door Deactivated***********\n");
	}
}