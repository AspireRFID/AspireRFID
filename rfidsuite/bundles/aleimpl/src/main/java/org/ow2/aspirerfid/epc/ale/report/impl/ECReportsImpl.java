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
package org.ow2.aspirerfid.epc.ale.report.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.ow2.aspirerfid.epc.ale.api.ECSpec;
import org.ow2.aspirerfid.epc.ale.report.api.ECReport;
import org.ow2.aspirerfid.epc.ale.report.api.ECReports;

/**
 * TODO Javadoc
 * 
 * @author unknown
 * @version 2007
 */
public class ECReportsImpl implements ECReports {

	private final Set reports;

	private long date;

	private String ALEID;

	private ECSpec spec;

	private String specName;

	private String terminationCondition;

	private long totalMilliseconds;

	/**
	 * TODO Javadoc
	 * 
	 * @param ALEID
	 * @param spec
	 * @param specName
	 * @param terminationCondition
	 */
	public ECReportsImpl(final String ALEID, final ECSpec spec,
			final String specName, final String terminationCondition) {
		this.ALEID = ALEID;
		this.spec = spec;
		this.specName = specName;
		this.terminationCondition = terminationCondition;
		this.reports = new HashSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#addReport(org.ow2.aspirerfid.epc.ale.report.api.ECReport)
	 */
	public void addReport(final ECReport report) {
		this.reports.add(report);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#getALEID()
	 */
	public String getALEID() {
		return this.ALEID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#getDate()
	 */
	public long getDate() {
		return this.date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#getECSpec()
	 */
	public ECSpec getECSpec() {
		return this.spec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#getReport(java.lang.String)
	 */
	public ECReport getReport(final String reportName) {
		final Iterator it = this.reports.iterator();
		while (it.hasNext()) {
			final ECReport ecReport = (ECReport) it.next();
			if (ecReport.getReportName().equals(reportName)) {
				return ecReport;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#getReports()
	 */
	public Set getReports() {
		return this.reports;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#getSpecName()
	 */
	public String getSpecName() {
		return this.specName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#getTerminationCondition()
	 */
	public String getTerminationCondition() {
		return this.terminationCondition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#getTotalMilliseconds()
	 */
	public long getTotalMilliseconds() {
		return this.totalMilliseconds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#setALEID(java.lang.String)
	 */
	public void setALEID(final String ALEID) {
		this.ALEID = ALEID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#setDate(long)
	 */
	public void setDate(final long date) {
		this.date = date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#setECSpec(org.ow2.aspirerfid.epc.ale.api.ECSpec)
	 */
	public void setECSpec(final ECSpec spec) {
		this.spec = spec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#setSpecName(java.lang.String)
	 */
	public void setSpecName(final String specName) {
		this.specName = specName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#setTerminationCondition(java.lang.String)
	 */
	public void setTerminationCondition(final String terminationCondition) {
		this.terminationCondition = terminationCondition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReports#setTotalMilliseconds(long)
	 */
	public void setTotalMilliseconds(final long totalMilliseconds) {
		this.totalMilliseconds = totalMilliseconds;
	}

}
