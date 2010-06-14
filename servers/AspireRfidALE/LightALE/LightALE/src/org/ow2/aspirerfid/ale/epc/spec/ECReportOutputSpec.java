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

import org.ow2.aspirerfid.ale.epc.reports.ECReportGroupListMember;

/**
 * <code>ECReportOutputSpec</code> specifies how the final set of EPCs is to be reported.
 * <br> 
 * The parameters of <code>ECReportOutputSpec</code> determine which parameters are present 
 * in each <code>ECReportGroup</code> instance that appears as part of an <code>ECReport</code> 
 * generated from this <code>ECReportSpec</code>. 
 * If any of <code>includeEPC</code>, <code>includeTag</code>, <code>includeRawHex</code>, 
 * or <code>includeRawDecimal</code> are true, or if <code>fieldList</code> is non-empty, 
 * the ALE implementation SHALL set the groupList parameter of each <code>ECReportGroup</code> 
 * instance to an <code>ECReportGroupList</code> instance, which in turn SHALL contain a 
 * list of <code>ECReportGroupListMember<code> instances having parameters set according to 
 * the definition below. Otherwise, the ALE implementation SHALL set the <code>groupList</code> 
 * parameter to <code>null</code>. 
 * <br>
 * <br>
 * If <code>includeCount</code> is true, the ALE implementation SHALL set the 
 * <code>groupCount</code> parameter of each <code>ECReportGroup</code> instance to an 
 * <code>ECReportGroupCount</code> instance, with parameters set according to the defintion
 * below. Otherwise, the ALE implementation SHALL set the <code>groupCount</code> parameter 
 * to <code>null</code>.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECReportOutputSpec {
	
	/**
	 * If true, each generated <code>ECReportGroupListMember</code> instance SHALL include 
	 * an <code>epc</code> parameter containing the value of the <code>epc</code> field of 
	 * the Tag represented in the <code>epc-pure</code> format.
	 */
	private boolean includeEPC ;
	
	/**
	 * If true, each generated <code>ECReportGroupListMember</code> instance SHALL include 
	 * a <code>tag</code> parameter containing the value of the <code>epc</code> field of 
	 * the Tag represented in the <code>epc-tag</code> format.
	 */
	private boolean includeTag;
	
	/**
	 * If true, each generated <code>ECReportGroupListMember</code> instance SHALL include 
	 * a <code>rawHex</code> parameter containing the value of the <code>epc</code> field 
	 * of the Tag represented in the <code>epc-hex</code> format.
	 */
	private boolean includeRawHex;
	
	/**
	 * If true, each generated <code>ECReportGroupListMember</code> instance SHALL include 
	 * a <code>rawDecimal</code> parameter containing the value of the <code>epc</code> field 
	 * of the Tag represented in the <code>epc-decimal</code> format.
	 */
	private boolean includeRawDecimal;
	
	/**
	 * If <code>includeCount</code> is true, the <code>groupCount</code> parameter of each generated 
	 * <code>ECReportGroup</code> instance SHALL be set to an <code>ECReportGroupCount</code> 
	 * instance, giving the number of Tags in the group.
	 */
	private boolean includeCount;
	
	/**
	 * An ordered list of fields to include in the result. If specified and non-empty, each 
	 * generated <code>ECReportGroupListMember</code> instance SHALL include a 
	 * <code>fieldList</code> parameter, with contents as specified in {@link ECReportGroupListMember}.
	 * <br>
	 * If empty or null, each generated <code>ECReportGroupListMember</code> SHALL NOT 
	 * include the fieldList parameter.
	 */
	private ECReportOutputFieldSpec[] fieldList ;

	//
	// <<extension point>>
	//	---	

	/**
	 * Constructor
	 * @param includeEPC
	 * @param includeTag
	 * @param includeRawHex
	 * @param includeRawDecimal
	 * @param includeCount
	 * @param fieldList
	 */
	public ECReportOutputSpec(boolean includeEPC, boolean includeTag,
			boolean includeRawHex, boolean includeRawDecimal,
			boolean includeCount, ECReportOutputFieldSpec[] fieldList) {
		this.includeEPC = includeEPC;
		this.includeTag = includeTag;
		this.includeRawHex = includeRawHex;
		this.includeRawDecimal = includeRawDecimal;
		this.includeCount = includeCount;
		this.fieldList = fieldList;
	}
	
	/**
	 * 
	 * Constructor
	 */
	public ECReportOutputSpec() {
		
	}

	/**
	 * Getter for includeEPC
	 * @return the includeEPC
	 */
	public boolean isIncludeEPC() {
		return includeEPC;
	}

	/**
	 * Getter for includeTag
	 * @return the includeTag
	 */
	public boolean isIncludeTag() {
		return includeTag;
	}

	/**
	 * Getter for includeRawHex
	 * @return the includeRawHex
	 */
	public boolean isIncludeRawHex() {
		return includeRawHex;
	}

	/**
	 * Getter for includeRawDecimal
	 * @return the includeRawDecimal
	 */
	public boolean isIncludeRawDecimal() {
		return includeRawDecimal;
	}

	/**
	 * Getter for includeCount
	 * @return the includeCount
	 */
	public boolean isIncludeCount() {
		return includeCount;
	}

	/**
	 * Getter for fieldList
	 * @return the fieldList
	 */
	public ECReportOutputFieldSpec[] getFieldList() {
		return fieldList;
	}

	/**
	 * Setter for includeEPC
	 * @param includeEPC the value to set
	 */
	public void setIncludeEPC(boolean includeEPC) {
		this.includeEPC = includeEPC;
	}

	/**
	 * Setter for includeTag
	 * @param includeTag the value to set
	 */
	public void setIncludeTag(boolean includeTag) {
		this.includeTag = includeTag;
	}

	/**
	 * Setter for includeRawHex
	 * @param includeRawHex the value to set
	 */
	public void setIncludeRawHex(boolean includeRawHex) {
		this.includeRawHex = includeRawHex;
	}

	/**
	 * Setter for includeRawDecimal
	 * @param includeRawDecimal the value to set
	 */
	public void setIncludeRawDecimal(boolean includeRawDecimal) {
		this.includeRawDecimal = includeRawDecimal;
	}

	/**
	 * Setter for includeCount
	 * @param includeCount the value to set
	 */
	public void setIncludeCount(boolean includeCount) {
		this.includeCount = includeCount;
	}

	/**
	 * Setter for fieldList
	 * @param fieldList the value to set
	 */
	public void setFieldList(ECReportOutputFieldSpec[] fieldList) {
		this.fieldList = fieldList;
	}	
}
