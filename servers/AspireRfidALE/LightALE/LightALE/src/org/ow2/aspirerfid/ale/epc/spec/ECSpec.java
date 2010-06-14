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

/* Java imports */
import java.util.Properties;

/**
 * An ECSpec describes an event cycle and one or more reports that are to be generated 
 * from it. It contains a list of logical Readers whose data are to be included in the
 * event cycle, a specification of how the boundaries of event cycles are to be 
 * determined, and a list of specifications each of which describes a report to be 
 * generated from this event cycle.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECSpec extends Spec{
	
	/**
	 * An unordered list that specifies one or more logical readers that are used 
	 * to acquire tags.
	 */
	private String[] logicalReaders ;
	
	/**
	 * Specifies the starting and stopping conditions for event cycles.
	 */
	private ECBoundarySpec boundarySpec ;
	
	/**
	 * An ordered list that specifies one or more reports to be included 
	 * in the output from each event cycle.
	 */
	private ECReportSpec[] reportSpecs ;
	
	/**
	 * If true, specifies that each ECReports instance generated from this ECSpec 
	 * 	SHALL include a copy of the ECSpec. 
	 * If false, each ECReports instance SHALL NOT include a copy of the ECSpec.
	 */
	private boolean includeSpecInReports ;
	
	/**
	 * (Optional) An ordered list that specifies a set of fields which together constitute 
	 * the "primary key" for determining Tag uniqueness, as described below. Each element 
	 * of the list is a fieldname. 
	 * If omitted, the ALE implementation SHALL use only the epc field to determine Tag
	 * uniqueness. This gives back-compatibility with ALE 1.0.
	 * 
	 * @category Not implemented.
	 */
	protected String[]primaryKeyFields ; 
	
	//
	// <<extension point>>
	//	---	

	/**
	 * Set up fields from a Java property object
	 * @param spec_property Java property object containing specification data
	 */
	public void loadFromProp (Properties spec_property) {
		// TODO
		
	}

	/**
	 * @param logicalReaders
	 * @param boundarySpec
	 * @param reportSpecs
	 * @param includeSpecInReports
	 */
	public ECSpec(String[] logicalReaders, ECBoundarySpec boundarySpec,
			ECReportSpec[] reportSpecs, boolean includeSpecInReports) {
		
		this.logicalReaders = logicalReaders;
		this.boundarySpec = boundarySpec;
		this.reportSpecs = reportSpecs;
		this.includeSpecInReports = includeSpecInReports;
	}

	/**
	 * 
	 * Constructor
	 */
	public ECSpec() {
		
	}
	
	/**
	 * Constructor for building spec using a Java property File
	 * 
	 * @param spec_property Java property object containing specification data
	 * @throws TBD
	 */	
	public ECSpec (Properties spec_property) {
		loadFromProp (spec_property);		
	}

	/**
	 * Getter for logicalReaders
	 * @return the logicalReaders
	 */
	public String[] getLogicalReaders() {
		return logicalReaders;
	}
	
	/**
	 * Getter for boundarySpec
	 * @return the boundarySpec
	 */
	public ECBoundarySpec getBoundarySpec() {
		return boundarySpec;
	}

	/**
	 * Getter for reportSpecs
	 * @return the reportSpecs
	 */
	public ECReportSpec[] getReportSpecs() {
		return reportSpecs;
	}

	/**
	 * Getter for includeSpecInReports
	 * @return the includeSpecInReports
	 */
	public boolean isIncludeSpecInReports() {
		return includeSpecInReports;
	}

}
