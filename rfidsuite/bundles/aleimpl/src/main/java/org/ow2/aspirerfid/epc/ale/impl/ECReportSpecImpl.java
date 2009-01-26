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
package org.ow2.aspirerfid.epc.ale.impl;

import java.util.ArrayList;
import java.util.List;

import org.ow2.aspirerfid.epc.ale.api.ECReportSetSpec;
import org.ow2.aspirerfid.epc.ale.api.ECReportSpec;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel
 * @version 2006
 */
public class ECReportSpecImpl implements ECReportSpec {

	private String name;

	private String reportSetSpec;

	private boolean includeCount;

	private boolean includeRawDecimal;

	private boolean includeRawHex;

	private boolean includeTag;

	private boolean includeEPC;

	private boolean reportIfEmpty;

	private boolean reportOnlyOnChange;

	private boolean includeGPS;

	private boolean includeMeasurement;

	private boolean includeReaderName;

	private List excludePatterns;

	private List includePatterns;

	private List groupSpec;

	/**
	 * TODO javadoc
	 */
	public ECReportSpecImpl() {
		this.excludePatterns = new ArrayList();
		this.includePatterns = new ArrayList();
		this.groupSpec = new ArrayList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#addExcludePattern(java.lang.String)
	 */
	public void addExcludePattern(final String name) {
		this.excludePatterns.add(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#addExcludePatterns(java.util.List)
	 */
	public void addExcludePatterns(final List names) {
		this.excludePatterns.addAll(names);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#addGroupSpec(java.lang.String)
	 */
	public void addGroupSpec(final String group) {
		this.groupSpec.add(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#addIncludePattern(java.lang.String)
	 */
	public void addIncludePattern(final String name) {
		this.includePatterns.add(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#addIncludePatterns(java.util.List)
	 */
	public void addIncludePatterns(final List names) {
		this.includePatterns.addAll(names);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(final Object obj) {
		if (obj instanceof ECReportSpecImpl) {
			final ECReportSpecImpl reportSpec = (ECReportSpecImpl) obj;
			if (reportSpec.getName().equals(this.getName())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getExcludePatterns()
	 */
	public List getExcludePatterns() {
		return this.excludePatterns;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getGroupSpec()
	 */
	public List getGroupSpec() {
		return this.groupSpec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getIncludeCount()
	 */
	public boolean getIncludeCount() {
		return this.includeCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getIncludeEPC()
	 */
	public boolean getIncludeEPC() {
		return this.includeEPC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getIncludeGPS()
	 */
	public boolean getIncludeGPS() {
		return this.includeGPS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getIncludeMeasurement()
	 */
	public boolean getIncludeMeasurement() {
		return this.includeMeasurement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getIncludePatterns()
	 */
	public List getIncludePatterns() {
		return this.includePatterns;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getIncludeRawDecimal()
	 */
	public boolean getIncludeRawDecimal() {
		return this.includeRawDecimal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getIncludeRawHex()
	 */
	public boolean getIncludeRawHex() {
		return this.includeRawHex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getIncludeReaderName()
	 */
	public boolean getIncludeReaderName() {
		return this.includeReaderName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getIncludeTag()
	 */
	public boolean getIncludeTag() {
		return this.includeTag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getName()
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getReportIfEmpty()
	 */
	public boolean getReportIfEmpty() {
		return this.reportIfEmpty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#getReportSetSpec()
	 */
	public String getReportSetSpec() {
		return this.reportSetSpec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#reportOnlyOnChange()
	 */
	public boolean reportOnlyOnChange() {
		return this.reportOnlyOnChange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setExcludePatterns(java.util.List)
	 */
	public void setExcludePatterns(final List names) {
		this.excludePatterns = names;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setGroupSpec(java.util.List)
	 */
	public void setGroupSpec(final List listOfGroupSpecs) {
		this.groupSpec = listOfGroupSpecs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setIncludeCount(boolean)
	 */
	public void setIncludeCount(final boolean includeCount) {
		this.includeCount = includeCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setIncludeEPC(boolean)
	 */
	public void setIncludeEPC(final boolean includeEPC) {
		this.includeEPC = includeEPC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setIncludeGPS(boolean)
	 */
	public void setIncludeGPS(final boolean includeGPS) {
		this.includeGPS = includeGPS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setIncludeMeasurement(boolean)
	 */
	public void setIncludeMeasurement(final boolean includeMeasurement) {
		this.includeMeasurement = includeMeasurement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setIncludePatterns(java.util.List)
	 */
	public void setIncludePatterns(final List names) {
		this.includePatterns = names;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setIncludeRawDecimal(boolean)
	 */
	public void setIncludeRawDecimal(final boolean includeRawDecimal) {
		this.includeRawDecimal = includeRawDecimal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setIncludeRawHex(boolean)
	 */
	public void setIncludeRawHex(final boolean includeRawHex) {
		this.includeRawHex = includeRawHex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setIncludeReaderName(boolean)
	 */
	public void setIncludeReaderName(final boolean includeReaderName) {
		this.includeReaderName = includeReaderName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setIncludeTag(boolean)
	 */
	public void setIncludeTag(final boolean includeTag) {
		this.includeTag = includeTag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setName(java.lang.String)
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setReportIfEmpty(boolean)
	 */
	public void setReportIfEmpty(final boolean reportIfEmpty) {
		this.reportIfEmpty = reportIfEmpty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setReportOnlyOnChange(boolean)
	 */
	public void setReportOnlyOnChange(final boolean reportOnlyOnChange) {
		this.reportOnlyOnChange = reportOnlyOnChange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECReportSpec#setReportSetSpec(java.lang.String)
	 */
	public void setReportSetSpec(String rSetSpec) {
		if (!(rSetSpec.equals(ECReportSetSpec.ADDITIONS) || rSetSpec
				.equals(ECReportSetSpec.DELETIONS))) {
			rSetSpec = ECReportSetSpec.CURRENT;
		}
		this.reportSetSpec = rSetSpec;
	}

}
