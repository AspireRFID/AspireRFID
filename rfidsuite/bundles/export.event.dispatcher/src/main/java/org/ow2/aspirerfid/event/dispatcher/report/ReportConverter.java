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

import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.ow2.aspirerfid.ale.ECMeasurementList;
import org.ow2.aspirerfid.epc.ale.api.ECReportSetSpec;
import org.ow2.aspirerfid.ale.ECReport;
import org.ow2.aspirerfid.ale.ECReportGroup;
import org.ow2.aspirerfid.ale.ECReportGroupListMember;
import org.ow2.aspirerfid.event.dispatcher.impl.Dispatcher;
import org.ow2.aspirerfid.util.Logger;
import org.ow2.aspirerfid.util.RFIDConstants;

/**
 * This class is used to convert received CURRENT reports into OSGi events.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class ReportConverter {
	/**
	 * Logger
	 */
	private Logger logger;

	/**
	 * Event admin service
	 */
	private EventAdmin eventAdmin;

	/**
	 * Topic to which OSGi events are sent
	 */
	private static final String TOPIC_DETECTION = "org/ow2/aspirerfid/DETECTION";

	/**
	 * Build the report converter
	 * 
	 * @param dispatcher
	 *            the dispatcher
	 * @param logger
	 *            the logger
	 */
	public ReportConverter(Dispatcher dispatcher, Logger logger) {
		this.logger = logger;
		this.eventAdmin = dispatcher.getEventAdmin();
	}

	/**
	 * Publish all members from CURRENT reports contained in the report into
	 * OSGi events
	 * 
	 * @param reports
	 *            the reports to convert
	 */
	public void publishCurrentSetReports(ECReport[] reports) {
		// For each reports
		for (int i = 0; i < reports.length; i++) {
			ECReport report = reports[i];

			// If the report is CURRENT
			if (report.getReportSet().equals(ECReportSetSpec.CURRENT)) {
				ECReportGroup[] reportGroups = report.getGroupArray();

				// For each group
				for (int j = 0; j < reportGroups.length; j++) {
					if (reportGroups[j].getGroupList() != null) {
						ECReportGroupListMember[] reportMembers = reportGroups[j]
								.getGroupList().getMemberArray();

						// For each member
						for (int k = 0; k < reportMembers.length; k++) {
							ECReportGroupListMember reportMember = reportMembers[k];
							Dictionary props = createEventProperties(reportMember);
							Event event = new Event(TOPIC_DETECTION, props);
							eventAdmin.postEvent(event);
						}
					}
				}
			}
		}
	}

	/**
	 * Create properties from a report member for the OSGi even to publish
	 * 
	 * @param reportMember
	 * @return the properties of the OSGi event
	 */
	private Dictionary createEventProperties(
			ECReportGroupListMember reportMember) {
		Dictionary props = new Properties();

		String epc = reportMember.getEpc().getStringValue();
		String gpsCoordinates = reportMember.getExtension().getGpsCoordinates();
		String date = Long.toString(reportMember.getDate().getTimeInMillis());

		if (epc != null) {
			props.put(RFIDConstants.TAGGUID_KEY, epcToTagGuid(epc));
		}

		props.put(RFIDConstants.READERNAME_KEY, reportMember.getExtension()
				.getReaderName());

		if (gpsCoordinates != null) {
			props.put(RFIDConstants.COORDINATES_KEY, reportMember
					.getExtension().getGpsCoordinates());
		}

		props.put(EventConstants.TIMESTAMP, date);

		List measurements = new LinkedList();
		ECMeasurementList measurementList = reportMember.getExtension()
				.getMeasurementList();

		for (int i = 0; i < measurementList.sizeOfMeasurementArray(); i++) {
			String applicationName = measurementList.getMeasurementArray(i)
					.getApplicationName();
			double error = measurementList.getMeasurementArray(i).getError();
			String sensorId = measurementList.getMeasurementArray(i)
					.getSensorId();
			long timestamp = measurementList.getMeasurementArray(i)
					.getTimestamp();
			String unit = measurementList.getMeasurementArray(i).getUnit();
			double value = measurementList.getMeasurementArray(i).getValue();
			measurements.add(value + ":" + error + ":" + unit + ":" + timestamp
					+ ":" + applicationName + ":" + sensorId);
		}

		props.put(RFIDConstants.MEASUREMENT_KEY, measurements);

		return props;
	}

	/**
	 * Converts an epc tag into a tag Guid
	 * 
	 * @param param
	 *            the epc tag
	 * @return a string representing a tag Guid
	 */
	private String epcToTagGuid(String param) {
		param = param.replace(".", "");
		return "35" + param.substring(param.length() - 22, param.length());
	}
}
