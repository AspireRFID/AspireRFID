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
 * An <code>ECReportGroupList</code> SHALL be included in an <code>ECReportGroup</code> 
 * when any of the four boolean fields <code>includeEPC</code> , <code>includeTag</code> , 
 * <code>includeRawHex</code> , and <code>includeRawDecimal</code> of the corresponding 
 * <code>ECReportOutputSpec</code> are true.<br>
 * <br>
 * 
 * Each distinct Tag included in this group SHALL have a distinct 
 * <code>ECReportGroupListMember</code> element in the <code>ECReportGroupList</code>, 
 * even if those <code>ECReportGroupListMember</code> elements would be identical due to 
 * the fields and formats selected.<br>
 * <br>
 * For example, it is possible for two different tags to have the same pure identity EPC 
 * representation; e.g., two Tags having SGTIN-96 EPC values that differ only in the filter
 * bits. If both tags are read in the same event cycle, and <code>ECReportOutputSpec</code>
 * specified includeEPC true and all other formats false, then the resulting 
 * <code>ECReportGroupList</code> SHALL have two <code>ECReportGroupListMember</code> 
 * elements, each having the same pure identity URI in the epc field. <br>
 * <br>
 * Similarly, if two Tags have the same values in one or more user defined fields, and 
 * <code>ECReportOutputSpec</code> only specified reading from those fields, the resulting 
 * <code>ECReportGroupList</code> SHALL have two <code>ECReportGroupListMember</code> 
 * elements, each having the same user fields in the fieldList parameter.
 *
 * @author pops
 * @author R.DAGHER
 */
public class ECReportGroupList {
	
	/**
	 * An unordered, possibly empty list of <code>ECReportGroupListMember</code> instances, 
	 * one for each distinct Tag that belongs to this group.
	 */
	private ECReportGroupListMember[] members ;
	
	//
	// <<extension point>>
	//	---

	/**
	 * Constructor
	 * @param members
	 */
	public ECReportGroupList(ECReportGroupListMember[] members) {
		this.members = members;
	}

	/**
	 * 
	 * Constructor
	 */
	public ECReportGroupList() {
		
	}
	
	/**
	 * Getter for members
	 * @return the members
	 */
	public ECReportGroupListMember[] getMembers() {
		return members;
	}

	/**
	 * Setter for members
	 * @param members the value to set
	 */
	public void setMembers(ECReportGroupListMember[] members) {
		this.members = members;
	}
	
	
}
