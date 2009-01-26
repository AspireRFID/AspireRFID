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

import org.ow2.aspirerfid.epc.ale.report.api.ECReport;
import org.ow2.aspirerfid.epc.ale.report.api.ECReportGroup;

/**
 * This class implements the methods which are common to all event cycle report
 * types.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class ECReportImpl implements ECReport {

	private String reportName;

	private Set reportGroups;

	private String reportSet;

	/**
	 * TODO Javadoc
	 * 
	 * @param reportName
	 * @param reportSet
	 */
	public ECReportImpl(final String reportName, final String reportSet) {
		this.reportName = reportName;
		this.reportSet = reportSet;
		this.reportGroups = new HashSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReport#addGroup(org.ow2.aspirerfid.epc.ale.report.api.ECReportGroup)
	 */
	public void addGroup(final ECReportGroup reportGroup) {
		this.reportGroups.add(reportGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(final Object obj) {
		if (obj instanceof ECReportImpl) {
			final ECReportImpl reportSpec = (ECReportImpl) obj;
			if (reportSpec.getReportName().equals(this.getReportName())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReport#getGroup(java.lang.String)
	 */
	public ECReportGroup getGroup(final String groupName) {
		final Iterator it = this.reportGroups.iterator();
		while (it.hasNext()) {
			final ECReportGroup ecReport = (ECReportGroup) it.next();
			if (ecReport.getGroupName().equals(groupName)) {
				return ecReport;
			}
		}
		return null;
	}

	public Set getGroups() {
		return this.reportGroups;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReport#getReportName()
	 */
	public String getReportName() {
		return this.reportName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReport#getReportSet()
	 */
	public String getReportSet() {
		return this.reportSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReport#removeGroups()
	 */
	public void removeGroups() {
		this.reportGroups = new HashSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReport#setReportName(java.lang.String)
	 */
	public void setReportName(final String reportName) {
		this.reportName = reportName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReport#setReportSet(java.lang.String)
	 */
	public void setReportSet(final String reportSet) {
		this.reportSet = reportSet;
	}

}
