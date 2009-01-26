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
import java.util.Set;

import org.ow2.aspirerfid.epc.ale.report.api.ECReportGroup;
import org.ow2.aspirerfid.epc.ale.report.api.ECReportGroupListMember;

/**
 * TODO Javadoc
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class ECReportGroupImpl implements ECReportGroup {

	private String groupName;
	private final Set groupList;

	/**
	 * @param groupName
	 *            TODO Javadoc
	 */
	public ECReportGroupImpl(final String groupName) {
		this.groupName = groupName;
		this.groupList = new HashSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECReportGroup#addGroupListMember(org.ow2.aspirerfid.epc.ale.report.api.ECReportGroupListMember)
	 */
	public void addGroupListMember(final ECReportGroupListMember groupListMember) {
		this.groupList.add(groupListMember);
	}

	/**
	 * @return the groupCount
	 */
	public int getGroupCount() {
		return this.groupList.size();
	}

	/**
	 * @return the groupList
	 */
	public Set getGroupList() {
		return this.groupList;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return this.groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

}
