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

package org.ow2.aspirerfid.ale.epc.spec;

import java.util.Properties;

/** 
 * An ECReportSpec specifies one report to be included in the list of reports that 
 * results from executing an event cycle.
 *
 * @author pops
 * @author R.DAGHER
 */
public class ECReportSpec extends Spec{
	/**
	 * Specifies a name for reports generated from this ECReportSpec. The ALE
	 * implementation SHALL copy this name into the ECReport instance generated 
	 * from this ECReportSpec.
	 */
	private String reportName ;
	
	/**
	 * Specifies what set of Tags are considered for reporting: 
	 * CURRENT, ADDITIONS, or DELETIONS .
	 * 
	 * @category partially implemented : only CURRENT is implemented
	 */
	private ECReportSetSpec reportSet ;
	
	/**
	 * Specifies how Tags are filtered before inclusion in the report.
	 */
	private ECFilterSpec filterSpec ;
	
	/**
	 * Specifies how filtered Tags are grouped together for reporting.
	 */
	private ECGroupSpec groupSpec ;
	
	/**
	 * Specifies which fields to report from each Tag or a count, or both.
	 */
	private ECReportOutputSpec output ;
	
	/**
	 * Specifies whether to omit the ECReport instance if the final set of 
	 * Tags is empty
	 */
	private boolean reportIfEmpty ;
	
	/**
	 * Specifies whether to omit the ECReport instance if the set of filtered Tags 
	 * is unchanged from the previous event cycle.
	 * 
	 * @category not implemented : only the immediate mode is implemented in the ALE 
	 * interface
	 */
	private boolean reportOnlyOnChange;
	
	/**
	 * An ordered list that specifies zero or more statistics profiles that 
	 * govern what statistics are to be included in the report.
	 */
	private ECStatProfileName[] statProfileNames ;

	//
	// <<extension point>>
	//	---	
	
	/**
	 * Set up fields from a Java property object
	 * 
	 * @param spec_property Java property object containing specification data
	 * @throws TBD
	 */
	public void loadFromProp (Properties spec_property) {
		// TODO
		
	}
	
	/**
	 * 
	 * Constructor from a Java property file 
	 * @param spec_property Java property object containing specification data
	 */
	public ECReportSpec(Properties spec_property) {
		this.loadFromProp(spec_property);
	}
	
	/**
	 * Constructor
	 * 
	 * @param reportName
	 * @param reportSet
	 * @param filterSpec
	 * @param groupSpec
	 * @param output
	 * @param reportIfEmpty
	 * @param reportOnlyOnChange
	 * @param statProfileNames
	 */
	public ECReportSpec(String reportName, ECReportSetSpec reportSet,
			ECFilterSpec filterSpec, ECGroupSpec groupSpec,
			ECReportOutputSpec output, boolean reportIfEmpty,
			boolean reportOnlyOnChange, ECStatProfileName[] statProfileNames) {
		this.reportName = reportName;
		this.reportSet = reportSet;
		this.filterSpec = filterSpec;
		this.groupSpec = groupSpec;
		this.output = output;
		this.reportIfEmpty = reportIfEmpty;
		this.reportOnlyOnChange = reportOnlyOnChange;
		this.statProfileNames = statProfileNames;
	}
	
	/**
	 * Getter for reportName
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}

	/**
	 * Getter for reportSet
	 * @return the reportSet
	 */
	public ECReportSetSpec getReportSet() {
		return reportSet;
	}

	/**
	 * Getter for filterSpec
	 * @return the filterSpec
	 */
	public ECFilterSpec getFilterSpec() {
		return filterSpec;
	}

	/**
	 * Getter for groupSpec
	 * @return the groupSpec
	 */
	public ECGroupSpec getGroupSpec() {
		return groupSpec;
	}

	/**
	 * Getter for output
	 * @return the output
	 */
	public ECReportOutputSpec getOutput() {
		return output;
	}

	/**
	 * Getter for reportIfEmpty
	 * @return the reportIfEmpty
	 */
	public boolean getReportIfEmpty() {
		return reportIfEmpty;
	}

	/**
	 * Getter for reportOnlyOnChange
	 * @return the reportOnlyOnChange
	 */
	public boolean getReportOnlyOnChange() {
		return reportOnlyOnChange;
	}

	/**
	 * Getter for statProfileNames
	 * @return the statProfileNames
	 */
	public ECStatProfileName[] getStatProfileNames() {
		return statProfileNames;
	}
}
