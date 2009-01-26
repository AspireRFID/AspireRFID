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
package org.ow2.aspirerfid.epc.ale.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import java.net.URI;
import java.net.URISyntaxException;

import javax.management.openmbean.ArrayType;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
import javax.management.openmbean.TabularData;
import javax.management.openmbean.TabularDataSupport;
import javax.management.openmbean.TabularType;

import org.ow2.aspirerfid.epc.ale.api.ECReportSetSpec;
import org.ow2.aspirerfid.epc.ale.api.ECReportSpec;
import org.ow2.aspirerfid.epc.ale.api.ECSpec;
import org.ow2.aspirerfid.util.Logger;
import org.ow2.aspirerfid.util.RFIDConstants;

/**
 * A class to create ECSpec through JMX
 * 
 * @author Guillaume Surrel
 * @author François Fornaciari
 * @version 2007
 */
public class ECSpecFactory implements ECSpecFactoryMBean {

	private Set logicalReaderNames;

	private String startTrigger;

	private String stopTrigger;

	private long repeatPeriod;

	private long duration;

	private long stableSetInterval;

	private TabularData tdECReportSpec;

	private CompositeType rowType;

	private String eventTopic;

	private String gatewayName;

	private static String[] reportSpecItemNames = { "-Report name",
			"Include EPC", "Include Tag", "Include RawHex",
			"Include RawDecimal", "Include Count", "ReportSetSpec",
			"Report if empty", "Report only on change", "Filters (including)",
			"Filters (excluding)", "Grouping patterns",
			"Include GPS coordinates", "Include Temperature",
			"Include reader name" };

	private ALE ale;

	private boolean includeSpecInReports = false;

	private Logger logger;

	/**
	 * TODO Javadoc
	 * 
	 * @param ale
	 * @param logger
	 */
	public ECSpecFactory(ALE ale, Logger logger) {
		this.ale = ale;
		this.logger = logger;
		init();
	}

	private void init() {
		logicalReaderNames = new HashSet();
		startTrigger = "";
		stopTrigger = "";
		gatewayName = "Gateway name";
		eventTopic = RFIDConstants.TOPIC_DETECTION;
		duration = RFIDConstants.DEFAULT_EC_DURATION;

		try {
			ArrayType filterArrayType = new ArrayType(1, SimpleType.STRING);

			OpenType[] reportSpecItemTypes = { SimpleType.STRING,
					SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN,
					SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.STRING,
					SimpleType.BOOLEAN, SimpleType.BOOLEAN, filterArrayType,
					filterArrayType, filterArrayType, SimpleType.BOOLEAN,
					SimpleType.BOOLEAN, SimpleType.BOOLEAN };
			String[] indexName = { "-Report name" };

			rowType = new CompositeType("ECReportSpec", "description",
					reportSpecItemNames, reportSpecItemNames,
					reportSpecItemTypes);
			TabularType tabularType = new TabularType("ECReportSpec",
					"ECReportSpec desc", rowType, indexName);
			tdECReportSpec = new TabularDataSupport(tabularType);

		} catch (OpenDataException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#getDuration()
	 */
	public long getDuration() {
		return duration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#setDuration(long)
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#getRepeatPeriod()
	 */
	public long getRepeatPeriod() {
		return repeatPeriod;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#setRepeatPeriod(long)
	 */
	public void setRepeatPeriod(long repeatPeriod) {
		this.repeatPeriod = repeatPeriod;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#getStableSetInterval()
	 */
	public long getStableSetInterval() {
		return stableSetInterval;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#setStableSetInterval(long)
	 */
	public void setStableSetInterval(long stableSetInterval) {
		this.stableSetInterval = stableSetInterval;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#getStartTrigger()
	 */
	public String getStartTrigger() {
		return startTrigger.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#setStartTrigger(java.lang.String)
	 */
	public void setStartTrigger(String startTrigger) {
		this.startTrigger = startTrigger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#getStopTrigger()
	 */
	public String getStopTrigger() {
		return stopTrigger.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#setStopTrigger(java.lang.String)
	 */
	public void setStopTrigger(String stopTrigger) {
		this.stopTrigger = stopTrigger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#addLogicalReaderName(java.lang.String)
	 */
	public void addLogicalReaderName(String logicalName) {
		logicalReaderNames.add(logicalName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#getLogicalReaderNames()
	 */
	public Set getLogicalReaderNames() {
		return logicalReaderNames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#setLogicalReaderNames(java.util.Set)
	 */
	public void setLogicalReaderNames(Set logicalReaderNames) {
		this.logicalReaderNames = logicalReaderNames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#getEventTopic()
	 */
	public String getEventTopic() {
		return eventTopic;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#setEventTopic(java.lang.String)
	 */
	public void setEventTopic(String eventTopic) {
		this.eventTopic = eventTopic;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#getGatewayName()
	 */
	public String getGatewayName() {
		return gatewayName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#setGatewayName(java.lang.String)
	 */
	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#addECReportSpec(java.lang.String,
	 *      boolean, boolean, boolean, boolean, boolean, java.lang.String,
	 *      boolean, boolean, boolean, boolean, boolean)
	 */
	public void addECReportSpec(String reportName, boolean includeEPC,
			boolean includeTag, boolean includeRawHex,
			boolean includeRawDecimal, boolean includeCount,
			String reportSetSpec, boolean reportIfEmpty,
			boolean reportOnlyOnChange, boolean includeGPS,
			boolean includeTemperature, boolean includeReaderName) {

		if (!(reportSetSpec.equals(ECReportSetSpec.ADDITIONS) || reportSetSpec
				.equals(ECReportSetSpec.DELETIONS))) {
			reportSetSpec = ECReportSetSpec.CURRENT;
		}

		Object[] reportSpecItemValues = new Object[reportSpecItemNames.length];
		reportSpecItemValues[0] = reportName;
		reportSpecItemValues[1] = new Boolean(includeEPC);
		reportSpecItemValues[2] = new Boolean(includeTag);
		reportSpecItemValues[3] = new Boolean(includeRawHex);
		reportSpecItemValues[4] = new Boolean(includeRawDecimal);
		reportSpecItemValues[5] = new Boolean(includeCount);
		reportSpecItemValues[6] = reportSetSpec;
		reportSpecItemValues[7] = new Boolean(reportIfEmpty);
		reportSpecItemValues[8] = new Boolean(reportOnlyOnChange);
		reportSpecItemValues[9] = new String[0];
		reportSpecItemValues[10] = new String[0];
		reportSpecItemValues[11] = new String[0];
		reportSpecItemValues[12] = new Boolean(includeGPS);
		reportSpecItemValues[13] = new Boolean(includeTemperature);
		reportSpecItemValues[14] = new Boolean(includeReaderName);

		try {
			CompositeData compositeData = new CompositeDataSupport(rowType,
					reportSpecItemNames, reportSpecItemValues);
			tdECReportSpec.put(compositeData);
		} catch (OpenDataException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#addECFilterSpec(java.lang.String,
	 *      java.lang.String, boolean)
	 */
	public void addECFilterSpec(String reportName, String epcPattern,
			boolean isIncludingFilter) {
		String[] s = { reportName };
		CompositeData compositeData = tdECReportSpec.get(s);

		String[] filters;
		if (isIncludingFilter) {
			filters = (String[]) compositeData.get(reportSpecItemNames[9]);
		} else {
			filters = (String[]) compositeData.get(reportSpecItemNames[10]);
		}
		String[] newFilters = new String[filters.length + 1];
		int i = 0;
		while (i < filters.length) {
			newFilters[i] = filters[i];
			i++;
		}
		newFilters[i] = epcPattern;
		Object[] reportSpecItemValues = compositeData
				.getAll(reportSpecItemNames);

		if (isIncludingFilter) {
			reportSpecItemValues[9] = newFilters;
		} else {
			reportSpecItemValues[10] = newFilters;
		}
		CompositeData newCompositeData;
		try {
			newCompositeData = new CompositeDataSupport(rowType,
					reportSpecItemNames, reportSpecItemValues);
			tdECReportSpec.remove(s);
			tdECReportSpec.put(newCompositeData);
		} catch (OpenDataException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#addECGroupSpec(java.lang.String,
	 *      java.lang.String)
	 */
	public void addECGroupSpec(String reportName, String epcPattern) {
		String[] s = { reportName };
		CompositeData compositeData = tdECReportSpec.get(s);

		String[] groups;
		groups = (String[]) compositeData.get(reportSpecItemNames[11]);
		String[] newGroups = new String[groups.length + 1];
		int i = 0;
		while (i < groups.length) {
			newGroups[i] = groups[i];
			i++;
		}
		newGroups[i] = epcPattern;
		Object[] reportSpecItemValues = compositeData
				.getAll(reportSpecItemNames);

		reportSpecItemValues[11] = newGroups;
		CompositeData newCompositeData;

		try {
			newCompositeData = new CompositeDataSupport(rowType,
					reportSpecItemNames, reportSpecItemValues);
			tdECReportSpec.remove(s);
			tdECReportSpec.put(newCompositeData);
		} catch (OpenDataException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#getECReportSpecList()
	 */
	public TabularData getECReportSpecList() {
		return tdECReportSpec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#createECSpec(java.lang.String)
	 */
	public void createECSpec(String specName) {
		try {
			ECSpec ecSpec = createECSpec();
			init();
			ale.define(specName, ecSpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ECSpec createECSpec() throws URISyntaxException {
		ECSpec ecSpec = new ECSpecImpl();
		ecSpec.setLogicalReaderNames(logicalReaderNames);
		ecSpec.setDuration(duration);
		ecSpec.setRepeatPeriod(repeatPeriod);
		ecSpec.setStableSetInterval(stableSetInterval);
		ecSpec.setStartTrigger(new URI(startTrigger));
		ecSpec.setStopTrigger(new URI(stopTrigger));
		ecSpec.setEventTopic(eventTopic);
		ecSpec.setGatewayName(gatewayName);
		ecSpec.setIncludeSpecInReports(includeSpecInReports);

		Iterator it = tdECReportSpec.keySet().iterator();

		while (it.hasNext()) {
			ECReportSpec ecrs = new ECReportSpecImpl();
			Object[] key = ((List) it.next()).toArray();
			CompositeData compositeData = tdECReportSpec.get(key);
			Object[] reportSpecItemValues = compositeData
					.getAll(reportSpecItemNames);

			ecrs.setName((String) reportSpecItemValues[0]);
			ecrs.setIncludeEPC(((Boolean) reportSpecItemValues[1])
					.booleanValue());
			ecrs.setIncludeTag(((Boolean) reportSpecItemValues[2])
					.booleanValue());
			ecrs.setIncludeRawHex(((Boolean) reportSpecItemValues[3])
					.booleanValue());
			ecrs.setIncludeRawDecimal(((Boolean) reportSpecItemValues[4])
					.booleanValue());
			ecrs.setIncludeCount(((Boolean) reportSpecItemValues[5])
					.booleanValue());
			ecrs.setReportSetSpec((String) reportSpecItemValues[6]);
			ecrs.setReportIfEmpty(((Boolean) reportSpecItemValues[7])
					.booleanValue());
			ecrs.setReportOnlyOnChange(((Boolean) reportSpecItemValues[8])
					.booleanValue());
			ecrs.setIncludePatterns(Arrays
					.asList((Object[]) reportSpecItemValues[9]));
			ecrs.setExcludePatterns(Arrays
					.asList((Object[]) reportSpecItemValues[10]));
			ecrs.setGroupSpec(Arrays
					.asList((Object[]) reportSpecItemValues[11]));
			ecrs.setIncludeGPS(((Boolean) reportSpecItemValues[12])
					.booleanValue());
			ecrs.setIncludeMeasurement(((Boolean) reportSpecItemValues[13])
					.booleanValue());
			ecrs.setIncludeReaderName(((Boolean) reportSpecItemValues[14])
					.booleanValue());

			ecSpec.addReportSpec(ecrs);
		}
		return ecSpec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.ECSpecFactoryMBean#getIncludeSpecInReports()
	 */
	public boolean getIncludeSpecInReports() {
		return includeSpecInReports;
	}

	public void setIncludeSpecInReports(boolean includeSpecInReports) {
		this.includeSpecInReports = includeSpecInReports;
	}

	public String immediate() {
		try {
			return ale.immediate(createECSpec());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
