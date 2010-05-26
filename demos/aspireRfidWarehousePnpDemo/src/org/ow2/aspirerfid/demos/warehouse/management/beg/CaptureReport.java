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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.ow2.aspirerfid.demos.warehouse.management.UI.WarehouseManagement;
import org.ow2.aspirerfid.demos.warehouse.management.tools.DeliveredItem;

import java.math.BigInteger;

/**
 * @author nkef (Nikos Kefalakis)
 * @author nkons (Nikolaos Konstantinou)
 * 
 */
public class CaptureReport extends Thread {

	private static Log log = LogFactory.getLog(CaptureReport.class);

	private XMLConfiguration config = null;

	private static boolean activated = true;

	private int port;

	private ServerSocket ss;

//	private DeliveredItem deliveredItem;

	// Data
	private String invoiceGroupName = "";

	
	private ArrayList<String> packetsGroupName;
	private ArrayList<String> packetsCompany;
	private ArrayList<String> packetsDescription;
	private ArrayList<String> packetsMeasurementId;
	//private ArrayList<String> packetsQuantity;
	private ArrayList<String> packetsExpectedQuantity;
	
	private ArrayList<String> knownTagFamilies;
	private int itemCount = 0;
	private boolean completeInvoice = false;

	public CaptureReport(int port, String epcisRepository) {

		// read parameters from configuration file
		config = new XMLConfiguration();
		config.setListDelimiter(',');
		URL fileurl = this.getClass().getResource("/pnpWarehouseParameters.xml");

		// sets the parameters according to the properties file
		try {
			config.load(fileurl);
		} catch (Exception e) {
			String message = "Couldn't get WarehouseParameters at: " + fileurl.getFile() + "\n" + e.getMessage();
			log.debug(message);
			e.printStackTrace();
		}

		itemCount =  config.getMaxIndex("invoice.merchandise.packetsContent.packetsGroupName") + 1;

		
		invoiceGroupName = config.getString("invoice.invoiceGroupName");

		//readPointNames = new String[nOFmerchandise];
		packetsGroupName = new ArrayList<String>(); //new String[nOFmerchandise];
		packetsCompany = new ArrayList<String>(); //new String[nOFmerchandise];
		packetsDescription = new ArrayList<String>(); //new String[nOFmerchandise];
		packetsMeasurementId = new ArrayList<String>(); //new String[nOFmerchandise];
		//packetsQuantity = new ArrayList<String>(); //new String[nOFmerchandise];
		packetsExpectedQuantity = new ArrayList<String>(); //new String[nOFmerchandise];
		
		knownTagFamilies = new ArrayList<String>();
		
		for (int i = 0; i < itemCount; i++) {
			packetsGroupName.add(config.getString("invoice.merchandise.packetsContent("+ i +").packetsGroupName"));
			packetsCompany.add(config.getString("invoice.merchandise.packetsContent("+ i +").company"));
			packetsDescription.add(config.getString("invoice.merchandise.packetsContent("+ i +").description"));
			packetsMeasurementId.add(config.getString("invoice.merchandise.packetsContent("+ i +").measurementID"));
			//packetsQuantity.add(config.getString("invoice.merchandise.packetsContent("+ i +").quantity"));
			packetsExpectedQuantity.add(config.getString("invoice.merchandise.packetsContent("+ i +").expectedQuantity"));
			
			knownTagFamilies.add(tagFamily(config.getString("invoice.merchandise.packetsContent("+ i +").packetsGroupName")));
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

	private void help() {
		System.out.println("You need to specify the port where to listen and the url of the epcis repository");
		System.out.println("Example: 9999 http://localhost:8080/aspire0.3.0EpcisRepository/capture");
	}

	/**
	 * @param activated
	 */
	public void setActivated(boolean activated) {
		CaptureReport.activated = activated;
	}

	private void handleReports(ECReports reports) throws IOException, JAXBException {
		log.debug("**********************Handling incoming reports****************************");

		//List<ECReport> theReports = reports.getReports().getReport();
		// collect all the tags
		//List<EPC> epcs = new LinkedList<EPC>();
		
		ArrayList<DeliveredItem> deliveredItems = extractItemsFromReports(reports.getReports().getReport());
		
		//Verify that we received ALL the items we needed and ONLY them
		int neededItems = 0;
		int notNeededItems = 0;
		for (DeliveredItem deliveredItem : deliveredItems) {
			if (deliveredItem.isNeeded()) {
				neededItems++;
			} else {
				notNeededItems++;
			}
		}
		
		completeInvoice = ((itemCount + 1) == neededItems && notNeededItems == 0);
		log.debug("setting completeInvoice to " + completeInvoice + " because (itemCount + 1) is " + (itemCount + 1) + ", needed is " + neededItems + " and not needed is " + notNeededItems);
		
		for (DeliveredItem deliveredItem : deliveredItems) {
			WarehouseManagement.updateDeliveryTableModel(deliveredItem, completeInvoice);			
		}

		if (deliveredItems.size() == 0) {
			log.debug("no items received - generating no event");
			return;
		}
	}
	
	public ArrayList<DeliveredItem> extractItemsFromReports(List<ECReport> reports) {
		ArrayList<DeliveredItem> ret = new ArrayList<DeliveredItem>();
		
		// get the current time and set the eventTime
		XMLGregorianCalendar now = null;
		try {
			DatatypeFactory dataFactory = DatatypeFactory.newInstance();
			now = dataFactory.newXMLGregorianCalendar(new GregorianCalendar());

			log.debug("Event Time:" + now.getHour() + ":" + now.getMinute() + ":" + ":" + now.getSecond() + "\n");

		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		if (reports != null) {
			for (ECReport report : reports) {
				// log.debug("Report Count: "+report.getGroup().size());
				log.debug("***************Report Name: " + report.getReportName() + "**************");
				if (report.getGroup() != null) {

					for (ECReportGroup group : report.getGroup()) {

						if (WarehouseManagement.getEntryDateTextField().equals("")) {
							WarehouseManagement.setEntryDateTextField(now.getDay() + "/" + now.getMonth() + "/" + now.getYear());
						}
						if (WarehouseManagement.getEntryHourTextField().equals("")) {
							WarehouseManagement.setEntryHourTextField(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
						}


						log.debug("Group Count: " + group.getGroupCount().getCount());
						log.debug("Group Name: " + group.getGroupName());

						if (group.getGroupList() != null) {
							//deliveredItem = null;

							log.debug("group name " + group.getGroupName());
							log.debug("group name   family " + tagFamily(group.getGroupName()));
							log.debug("invoicegroup family " + tagFamily(invoiceGroupName));
							// Invoice

							if (tagFamily(group.getGroupName()).equals(tagFamily(invoiceGroupName))) {
								log.debug("found invoice");
								for (ECReportGroupListMember member : group.getGroupList().getMember()) {
									if (member.getEpc() != null) {
										WarehouseManagement.setInvoiceIdTextField(member.getTag().getValue().split(":")[4]);
										WarehouseManagement.setOfferingDateTextField("22/05/08");
										WarehouseManagement.setOfferingHourTextField("10:53:22");
									}
								}
							}
							
							for (int i = 0; i < itemCount; i++) {
								log.debug("packetsGroupName[i]  family " + tagFamily(packetsGroupName.get(i)));
								log.debug("group.getGroupName() family " + tagFamily(group.getGroupName()));

								//if (group.getGroupName().equals(packetsGroupName[i])) {
								//if (tagFamily(packetsGroupName[i]).equals(tagFamily(group.getGroupName()))) {
								if (!tagFamily(group.getGroupName()).equals(tagFamily(invoiceGroupName))) {
									log.debug("checking items");
									//BigInteger expectedQuantity = new BigInteger(packetsExpectedQuantity.get(i));
									BigInteger expectedQuantity = new BigInteger(findExpectedQuantityByTagFamily(tagFamily(group.getGroupName())));
									BigInteger quantityDelivered = new BigInteger((group.getGroupCount().getCount()) + "");
									BigInteger quantityRemain = expectedQuantity.subtract(quantityDelivered);//quantity.add(quantityDelivered.negate());

									int counter = 0;
									for (ECReportGroupListMember member : group.getGroupList().getMember()) {
										if (member.getEpc() != null) {
											DeliveredItem deliveredItem = new DeliveredItem();
											
											deliveredItem = new DeliveredItem();
											
											if (knownTagFamilies.contains(tagFamily(member.getTag().getValue()))) {
												//deliveredItem.setCompany(packetsCompany.get(i));
												deliveredItem.setCompany(findCompanyByTagFamily(tagFamily(member.getTag().getValue())));
												//deliveredItem.setDescription(packetsDescription.get(i));
												deliveredItem.setDescription(findDescriptionByTagFamily(tagFamily(member.getTag().getValue())));
												deliveredItem.setExpectedQuantity(expectedQuantity);
												deliveredItem.setQuantityRemain(quantityRemain);
											} else {
												deliveredItem.setCompany("Unknown");
												deliveredItem.setDescription("Unknown tag");
												deliveredItem.setExpectedQuantity(BigInteger.valueOf(0));
												deliveredItem.setQuantityRemain(BigInteger.valueOf(0));
											}
											
											deliveredItem.setDeliveryDate(now.getDay() + "/" + now.getMonth() + "/" + now.getYear());

											deliveredItem.setMeasurementId(packetsMeasurementId.get(i));
											deliveredItem.setCounter(BigInteger.valueOf(counter++));
											deliveredItem.setQuantityDelivered(quantityDelivered);
											deliveredItem.setItemCode(member.getTag().getValue().split(":")[4]);

											deliveredItem.setNeeded(tagFamily(group.getGroupName()).equals(tagFamily(packetsGroupName.get(i)))
													&& (deliveredItem.getCounter().intValue() < deliveredItem.getExpectedQuantity().intValue()));
													//&& invoiceFound);

											log.debug("deliveredItem.getExpectedQuantity().intValue() " + deliveredItem.getExpectedQuantity().intValue());
											log.debug("deliveredItem.getCounter().intValue() " + deliveredItem.getCounter().intValue());
											log.debug("set needeed " + deliveredItem.isNeeded());
											
											//check if the deliveredItem we are about to add exists in the list
											boolean deliveredItemExists = false;
											for (DeliveredItem di : ret) {
												if (di.getItemCode().equals(deliveredItem.getItemCode())) {
												//if (tagFamily(di.getItemCode()).equals(tagFamily(deliveredItem.getItemCode()))) {
													
													deliveredItemExists = true;
													
													if (!di.isNeeded() && deliveredItem.isNeeded())
														di.setNeeded(true);
													
												}
											}
											
											if (!deliveredItemExists) {
												
												log.debug("adding item with company " + deliveredItem.getCompany() +
														" description " + deliveredItem.getDescription() +
														//" quantity " + deliveredItem.getQuantity() +
														" item code " + deliveredItem.getItemCode() +
														" is needed " + deliveredItem.isNeeded());
												
												ret.add(deliveredItem);
											}
											//WarehouseManagement.updateDeliveryTableModel(deliveredItem);
										}
									}
								}
							}
						}
					}
				}
			} //collected the reports
		}
		
		log.debug("returning " + ret.size() + " delivered items");
		return ret;
	}

	public void run() {
		while (activated) {
			log.debug("Received Buffer:");
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

				log.debug("Received Data From Port " + port);
				while (data != null) {
					buf += data;
					data = in.readLine();
					log.debug("XML DATA: " + data);
				}
				// log.debug("Received Buffer:"+buf);

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
	
	/**
	 * @param tag
	 * @return e.g. out of a urn:epc:pat:gid-96:145.233.* will return a 145.233 
	 */
	public String tagFamily(String tag) {
		return tag.substring(tag.lastIndexOf(':') + 1, tag.lastIndexOf('.'));
	}
	
	public String findCompanyByTagFamily(String tagFamily) {
		for (int i = 0; i < itemCount; i++) {
			if (tagFamily.equals(tagFamily(packetsGroupName.get(i)))) {
				return packetsCompany.get(i);
			}
		}
		return "Unknown";
	}
	
	public String findDescriptionByTagFamily(String tagFamily) {
		for (int i = 0; i < itemCount; i++) {
			if (tagFamily.equals(tagFamily(packetsGroupName.get(i)))) {
				return packetsDescription.get(i);
			}
		}
		return "Unknown tag";
	}
	
	public String findExpectedQuantityByTagFamily(String tagFamily) {
		for (int i = 0; i < itemCount; i++) {
			if (tagFamily.equals(tagFamily(packetsGroupName.get(i)))) {
				return packetsExpectedQuantity.get(i);
			}
		}
		return "0";
	}
}