/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package org.ow2.aspirerfid.ale.epc.reports;

/**
 * <code>ECReport</code> represents a single report within an event cycle.
 *
 * @author pops
 * @author R.DAGHER
 */
public class ECReport {
	
	/**
	 * A copy of the reportName field from the corresponding <code>ECReportSpec<code> within the 
	 * <code>ECSpec</code> that controlled this event cycle.
	 */
	private String reportName ;
	
	/**
	 * An unordered list containing one element for each group in the report as controlled by 
	 * the group field of the corresponding <code>ECReportSpec</code>. When no grouping is 
	 * specified, the groups list just consists of the single default group.
	 */
	private ECReportGroup[] groups ;
	
	//
	// <<extension point>>
	//	---

	/**
	 * Constructor
	 * @param reportName
	 * @param groups
	 */
	public ECReport(String reportName, ECReportGroup[] groups) {
		this.reportName = reportName;
		this.groups = groups;
	}
	
	public ECReport() { }

	/**
	 * Getter for reportName
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}

	/**
	 * Getter for groups
	 * @return the groups
	 */
	public ECReportGroup[] getGroups() {
		return groups;
	}

	/**
	 * Setter for reportName
	 * @param reportName the value to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	/**
	 * Setter for groups
	 * @param groups the value to set
	 */
	public void setGroups(ECReportGroup[] groups) {
		this.groups = groups;
	}
	
	
}
