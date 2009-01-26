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
package org.ow2.aspirerfid.epc.ale.report.api;

import java.util.Set;

/**
 * This interface defines the methods for the ECReportGroup. The ECReportGroup
 * represents one group within the ECReport. The interface includes accessors
 * for the groupName, groupList, and groupCount. The groupName is null for the
 * default group. GroupList and groupCount are null if the includeList and
 * includeCount fields, respectively, or the corresponding ECReportOutputSpec
 * are false.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public interface ECReportGroup {
	/**
	 * Returns the group name, null if this is the default group.
	 * 
	 * @return Name of the ECReportGroup
	 */
	public String getGroupName();

	/**
	 * Sets the group name, null if this is the default group.
	 * 
	 * @param groupName
	 *            Name of the ECReportGroup
	 */
	public void setGroupName(String groupName);

	/**
	 * Returns the count of EPCS in the group. Returns 0 if the includeCount
	 * field of the corresponding ECReportOutputSpec is false.
	 * 
	 * @return Count of EPCs in this group
	 */
	public int getGroupCount();

	/**
	 * Return the list of EPCs in the group. Returns null if the includeList
	 * field of the corresponding ECReportOutputSpec is false.
	 * 
	 * @return Set of ECReportGroupListMember contained by this this group
	 */
	public Set getGroupList();

	/**
	 * Add a ECReportGroupListMember to this group.
	 * 
	 * @param groupListMember
	 *            ECReportGroupListMember to add to this group
	 */
	public void addGroupListMember(ECReportGroupListMember groupListMember);
}
