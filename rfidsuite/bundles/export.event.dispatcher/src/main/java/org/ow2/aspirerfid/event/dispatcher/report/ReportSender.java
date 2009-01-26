/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.event.dispatcher.report;

import java.util.Map;
import java.util.Properties;

import org.apache.xmlbeans.XmlException;
import org.ow2.aspirerfid.epc.ale.api.ECReportSetSpec;
import org.ow2.aspirerfid.ale.ECReport;
import org.ow2.aspirerfid.ale.ECReportList;
import org.ow2.aspirerfid.ale.ECReports;
import org.ow2.aspirerfid.ale.ECReportsDocument;
import org.ow2.aspirerfid.event.dispatcher.impl.Dispatcher;
import org.ow2.aspirerfid.util.Logger;
import org.ow2.aspirerfid.util.RFIDConstants;

/**
 * This class makes three operations :
 * <ul>
 * <li>It transmits directly to a server (or another premise) reports which
 * contains only ADDITIONS or DELETIONS sets </li>
 * <li>It transmits reports ADDITIONS or DELETIONS sets by removing CURRENT
 * reports from the received report</li>
 * <li>It converts CURRENT reports into OSGi events to be treat by ALE</li>
 * </ul>
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class ReportSender {

	/**
	 * The dispatcher
	 */
	private Dispatcher dispatcher;

	/**
	 * Logger
	 */
	private Logger logger;

	/**
	 * Counter used to identify a sent report
	 */
	private long messageIdCounter = 1;

	/**
	 * Object used to convert CURRENT reports into OSGi events
	 */
	private ReportConverter reportConvertor;

	/**
	 * XML report header
	 */
	private static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ System.getProperty("line.separator");

	/**
	 * Build a Report Sender
	 * 
	 * @param dispatcher
	 *            the dispatcher
	 * @param logger
	 *            the logger
	 */
	public ReportSender(Dispatcher dispatcher, Logger logger) {
		this.dispatcher = dispatcher;
		this.logger = logger;
		this.reportConvertor = new ReportConverter(dispatcher, logger);
	}

	/**
	 * This method parses the reports and send new reports depending on the
	 * report sets
	 * 
	 * @param data
	 *            the reports to parse
	 * @throws XmlException
	 *             Thrown when the reports cannot be parsed
	 */
	public void parseReport(String data) throws XmlException {
		ECReportsDocument reportDoc = ECReportsDocument.Factory.parse(data);
		ECReports ecReports = reportDoc.getECReports();
		String gatewayName = ecReports.getExtension().getGatewayName();
		if (ecReports != null) {
			ECReport[] reports = ecReports.getReports().getReportArray();

			// Publish ADDITIONS and DELETIONS reports
			// The report doesn't contain CURRENT reports
			if (!containCurrentReports(reports) && reports.length > 0) {
				logger.log(Logger.DEBUG,
						"ADDITIONS or DELETIONS reports have been transfered.");
				publishReport(data, gatewayName);
			} else {
				// Treat extracted CURRENT reports
				reportConvertor.publishCurrentSetReports(reports);
				logger
						.log(Logger.DEBUG,
								"CURRENT reports have been published.");

				// Publish extracted ADDITIONS and DELETIONS reports
				String newNonCurrentReports = extractNonCurrentReports(ecReports);
				if (newNonCurrentReports != null) {
					logger
							.log(Logger.DEBUG,
									"ADDITIONS or DELETIONS reports have been transfered.");
					publishReport(newNonCurrentReports, gatewayName);
				}
			}
		}
	}

	/**
	 * Returns true if at least a CURRENT report is found
	 * 
	 * @param reports
	 *            the reports to parse
	 * @return true if at least a CURRENT report is found
	 */
	private boolean containCurrentReports(ECReport[] reports) {
		for (int i = 0; i < reports.length; i++) {
			ECReport report = reports[i];
			String reportSet = report.getReportSet();
			if (reportSet.equals(ECReportSetSpec.CURRENT)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Publish a report
	 * 
	 * @param data
	 *            the report to send
	 */
	private void publishReport(String data, String gatewayName) {
		Map map = new Properties();
		map.put(RFIDConstants.MESSAGE_ID, Long.toString((messageIdCounter)));
		map.put(RFIDConstants.GATEWAY_NAME, gatewayName);
		map.put(RFIDConstants.REPORT_ENTRY, data);
		map.put(RFIDConstants.DEST_URI, dispatcher.getDestinationURI());

		dispatcher.publish(map);

		messageIdCounter++;
		if (messageIdCounter > Long.MAX_VALUE) {
			messageIdCounter = 1;
		}
	}

	/**
	 * Returns new reports without CURRENT reports
	 * 
	 * @param ecReports
	 *            the Event Cycle reports to modify
	 * @return New reports without CURRENT reports
	 */
	private String extractNonCurrentReports(ECReports ecReports) {
		ECReportsDocument reportDoc = ECReportsDocument.Factory.newInstance();

		if (ecReports.isSetECSpec()) {
			// TODO : modify the ecspec if it is defined
			ecReports.setECSpec(null);
		}

		// Remove CURRENT reports
		ECReportList reports = ecReports.getReports();
		for (int i = reports.sizeOfReportArray() - 1; i >= 0; i--) {
			ECReport report = reports.getReportArray(i);
			String reportSet = report.getReportSet();
			if (reportSet.equals(ECReportSetSpec.CURRENT)) {
				reports.removeReport(i);
			}
		}

		// If there is no report
		if (reports.sizeOfReportArray() == 0) {
			return null;
		} else {
			ecReports.setReports(reports);
		}

		reportDoc.setECReports(ecReports);
		String reportToSend = HEADER + reportDoc.toString();
		return reportToSend;
	}

}
