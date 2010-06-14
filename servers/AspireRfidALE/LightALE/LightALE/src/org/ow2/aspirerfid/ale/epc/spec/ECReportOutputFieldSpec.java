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

/**
 * An <code>ECReportOutputFieldSpec</code> specifies a Tag field to be included in an 
 * event cycle report.
 *
 * @author pops
 * @author R.DAGHER
 */
public class ECReportOutputFieldSpec {
	
	/**
	 * Specifies which field of the Tag is to be included in the report. The <code>fieldspec</code> 
	 * may contain a "pattern" fieldname, in which case zero or more fields matching the 
	 * pattern are read and included in the report.
	 */
	private ECFieldSpec fieldspec ;
	
	/**
	 * (Optional) Specifies a name that is included in the corresponding 
	 * <code>ECReportGroupListMember</code> instance.
	 * If empty or null, the <code>fieldname</code> parameter of the specified 
	 * <code>fieldspec</code> SHALL be used as the name.
	 */
	private String name ; // optional
	
	/**
	 * (Optional) If true, the corresponding <code>ECReportGroupListMember</code> instance 
	 * SHALL include a copy of the specified <code>fieldspec</code>.
	 */
	private boolean includeFieldSpecInReport ; // optional

	/**
	 * Constructor
	 * @param fieldspec
	 * @param name
	 * @param includeFieldSpecInReport
	 */
	public ECReportOutputFieldSpec(ECFieldSpec fieldspec, String name,
			boolean includeFieldSpecInReport) {
		this.fieldspec = fieldspec;
		this.name = name;
		this.includeFieldSpecInReport = includeFieldSpecInReport;
	}
	
	/**
	 * 
	 * Constructor
	 */
	public ECReportOutputFieldSpec() {
		
	}

	/**
	 * Getter for fieldspec
	 * @return the fieldspec
	 */
	public ECFieldSpec getFieldspec() {
		return fieldspec;
	}

	/**
	 * Getter for name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for includeFieldSpecInReport
	 * @return the includeFieldSpecInReport
	 */
	public boolean isIncludeFieldSpecInReport() {
		return includeFieldSpecInReport;
	}

	/**
	 * Setter for fieldspec
	 * @param fieldspec the value to set
	 */
	public void setFieldspec(ECFieldSpec fieldspec) {
		this.fieldspec = fieldspec;
	}

	/**
	 * Setter for name
	 * @param name the value to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for includeFieldSpecInReport
	 * @param includeFieldSpecInReport the value to set
	 */
	public void setIncludeFieldSpecInReport(boolean includeFieldSpecInReport) {
		this.includeFieldSpecInReport = includeFieldSpecInReport;
	}
	
}
