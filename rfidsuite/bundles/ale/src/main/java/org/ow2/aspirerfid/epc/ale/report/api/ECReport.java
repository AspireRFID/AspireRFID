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
 * This interface defines the methods which are common to all event cycle report
 * types.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public interface ECReport {
	/**
	 * Gets the name of this report
	 * 
	 * @return Name of this report
	 */
	public String getReportName();

	/**
	 * Sets the name of this report
	 * 
	 * @param reportName
	 *            Name of this report
	 */
	public void setReportName(String reportName);

	/**
	 * Gets the type of this report (ADDITIONS, DELETIONS or CURRENT)
	 * 
	 * @return Type of this report
	 */
	public String getReportSet();

	/**
	 * Sets the type of this report
	 * 
	 * @param reportSet
	 *            Type of this report
	 */
	public void setReportSet(String reportSet);

	/**
	 * Returns the set of ECReportGroup instances
	 * 
	 * @return Set of ECReportGroup instances
	 */
	public Set getGroups();

	/**
	 * Adds an ECReportGroup
	 * 
	 * @param reportGroup
	 *            ECReportGroup to add
	 */
	public void addGroup(ECReportGroup reportGroup);

	/**
	 * Gets the ECReportGroup of the specified name
	 * 
	 * @param string
	 *            the name of the group
	 * @return the ECReportGroupGroup, null if it doesn't exist
	 */
	public ECReportGroup getGroup(String string);

	/**
	 * Removes all the groups
	 */
	public void removeGroups();
}
