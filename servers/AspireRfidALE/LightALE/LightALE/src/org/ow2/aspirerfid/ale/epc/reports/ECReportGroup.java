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
 * <code>ECReportGroup</code> represents one group within an <code>ECReport</code>.
 *
 * @author pops
 * @author R.DAGHER
 */
public class ECReportGroup {
	
	/**
	 * Group Name. <br>
	 * Null for the <code>default</code> group. For any other group, the group name as determined according to {@link org.ow2.aspirerfid.ale.epc.spec.ECGroupSpec}
	 */
	private String groupName ;
	
	/**
	 * <code>ECReportGroupList</code> instance containing data read from the Tags in this group.<br>
	 * <code>null</code> if the <code>includeEPC</code> , <code>includeTag</code> , 
	 * <code>includeRawHex</code> , and <code>includeRawDecimal</code>  fields of the corresponding 
	 * <code>ECReportOutputSpec</code>  are all false and the <code>fieldList</code>  in the corresponding 
	 * <code>ECReportOutputSpec</code>  is empty (unless <code>ECReportOutputSpec</code>  has vendor extensions 
	 * that cause <code>groupList</code> to be included).
	 */
	private ECReportGroupList groupList ;
	
	/**
	 * The number of Tags in this group.<br>
	 * <code>null</code> if the <code>includeCount</code> field of the corresponding <code>ECReportOutputSpec</code> is false
	 * (unless <code>ECReportOutputSpec</code> has vendor extensions that cause <code>groupCount</code> to be included).
	 */
	private ECReportGroupCount groupCount ;

		
	//
	// <<extension point>>
	//	---

	
	/**
	 * Constructor
	 * @param groupName
	 * @param groupList
	 * @param groupCount
	 */
	public ECReportGroup(String groupName, ECReportGroupList groupList,
			ECReportGroupCount groupCount) {
		this.groupName = groupName;
		this.groupList = groupList;
		this.groupCount = groupCount;
	}
	
	/**
	 * 
	 * Constructor
	 */
	public ECReportGroup() { }
	
	/**
	 * Getter for groupName
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Getter for groupList
	 * @return the groupList
	 */
	public ECReportGroupList getGroupList() {
		return groupList;
	}

	/**
	 * Getter for groupCount
	 * @return the groupCount
	 */
	public ECReportGroupCount getGroupCount() {
		return groupCount;
	}

	/**
	 * Setter for groupName
	 * @param groupName the value to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * Setter for groupList
	 * @param groupList the value to set
	 */
	public void setGroupList(ECReportGroupList groupList) {
		this.groupList = groupList;
	}

	/**
	 * Setter for groupCount
	 * @param groupCount the value to set
	 */
	public void setGroupCount(ECReportGroupCount groupCount) {
		this.groupCount = groupCount;
	}
	
}
