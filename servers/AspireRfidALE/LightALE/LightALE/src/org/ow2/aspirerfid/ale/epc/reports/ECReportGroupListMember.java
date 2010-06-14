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
 * 
 * This class represents information related to reading a single tag.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECReportGroupListMember {
	
	/**
	 * The value of the <code>epc</code> field of the Tag, in the <code>epc-pure</code> 
	 * format.<br>
	 * Null, if the <code>includeEPC</code> field of the corresponding 
	 * <code>ECReportOutputSpec</code> instance is false, or if accessing the <code>epc</code> 
	 * field of the Tag results in a "field not found" or "operation not possible" 
	 * condition.
	 */
	private String epc ;
	
	/**
	 * The value of the <code>epc</code> field of the Tag, in the <code>epc-ptag</code> 
	 * format.<br>
	 * Null, if the <code>includeTag</code> field of the corresponding 
	 * <code>ECReportOutputSpec</code> instance is false, or if accessing the <code>epc</code>
	 * field of the Tag results in a "field not found" or "operation not possible" 
	 * condition.
	 */
	private String tag;
	
	/**
	 * The value of the <code>epc</code> field of the Tag, in the <code>epc-hex</code> 
	 * format.<br>
	 * Null, if the <code>includeRawHex</code> field of the corresponding 
	 * <code>ECReportOutputSpec</code> instance is false, or if accessing the <code>epc</code>
	 * field of the Tag results in a "field not found" or "operation not possible" 
	 * condition.
	 */
	private String rawHex;
	
	/**
	 * The value of the <code>epc</code> field of the Tag, in the <code>epc-decimal</code> 
	 * format.<br>
	 * Null, if the <code>includeRawDecimal</code> field of the corresponding 
	 * <code>ECReportOutputSpec</code> instance is false, or if accessing the <code>epc</code>
	 * field of the Tag results in a "field not found" or "operation not possible" 
	 * condition.
	 */
	private String rawDecimal;
	

	/**
	 * Contains zero or more <code>ECReportMemberField</code> instances for each <code>fieldspec</code> 
	 * listed in the fieldList parameter of the corresponding <code>ECReportOutputSpec</code>, 
	 * in the corresponding order. <br>
	 * If a <code>fieldspec</code> specified a pattern <code>fieldname</code>, then zero or more 
	 * <code>ECReportMemberField</code> instances may be present. Otherwise, exactly one 
	 * <code>ECReportMemberField</code> instance is present. <br>
	 * Null, if the <code>fieldList</code> parameter of the corresponding <code>ECReportOutputSpec</code>
	 * is empty, omitted, or null.
	 */
	private ECReportMemberField fieldList;
	
	/**
	 * Contains an <code>ECTagStat</code> for each statistics profile named in the 
	 * <code>statProfileNames</code> parameter of the corresponding <code>ECReportSpec</code>, 
	 * in the corresponding order. Null, if the <code>statProfileNames</code> parameter of 
	 * the corresponding <code>ECReportSpec</code> is empty, omitted, or null.
	 */
	private ECTagStat stats ;

	
	//
	// <<extension point>>
	//	---
	
	/**
	 * Tag Id as an array of bytes. This format is used to optimize size for transmission.
	 * Later On decoding is done offline.
	 */
	private byte[] tagId;
	
	/**
	 * Constructor
	 * @param epc
	 * @param tag
	 * @param rawHex
	 * @param rawDecimal
	 * @param fieldList
	 * @param stats
	 * @param tagId
	 */
	public ECReportGroupListMember(String epc, String tag, String rawHex,
			String rawDecimal, ECReportMemberField fieldList, ECTagStat stats, byte[] tagId) {
		this.epc = epc;
		this.tag = tag;
		this.rawHex = rawHex;
		this.rawDecimal = rawDecimal;
		this.fieldList = fieldList;
		this.stats = stats;
		this.tagId = tagId;
	}
	
	/**
	 * 
	 * Constructor
	 */
	public ECReportGroupListMember() {
		
	}
	

	/**
	 * Getter for epc
	 * @return the epc
	 */
	public String getEpc() {
		return epc;
	}

	/**
	 * Getter for tag
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Getter for rawHex
	 * @return the rawHex
	 */
	public String getRawHex() {
		return rawHex;
	}

	/**
	 * Getter for rawDecimal
	 * @return the rawDecimal
	 */
	public String getRawDecimal() {
		return rawDecimal;
	}

	/**
	 * Getter for fieldList
	 * @return the fieldList
	 */
	public ECReportMemberField getFieldList() {
		return fieldList;
	}

	/**
	 * Getter for stats
	 * @return the stats
	 */
	public ECTagStat getStats() {
		return stats;
	}

	/**
	 * Setter for epc
	 * @param epc the value to set
	 */
	public void setEpc(String epc) {
		this.epc = epc;
	}

	/**
	 * Setter for tag
	 * @param tag the value to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Setter for rawHex
	 * @param rawHex the value to set
	 */
	public void setRawHex(String rawHex) {
		this.rawHex = rawHex;
	}

	/**
	 * Setter for rawDecimal
	 * @param rawDecimal the value to set
	 */
	public void setRawDecimal(String rawDecimal) {
		this.rawDecimal = rawDecimal;
	}

	/**
	 * Setter for fieldList
	 * @param fieldList the value to set
	 */
	public void setFieldList(ECReportMemberField fieldList) {
		this.fieldList = fieldList;
	}

	/**
	 * Setter for stats
	 * @param stats the value to set
	 */
	public void setStats(ECTagStat stats) {
		this.stats = stats;
	}

	/**
	 * Getter for tagId
	 * @return the tagId
	 */
	public byte[] getTagId() {
		return tagId;
	}

	/**
	 * Setter for tagId
	 * @param tagId the value to set
	 */
	public void setTagId(byte[] tagId) {
		this.tagId = tagId;
	}
		
}
