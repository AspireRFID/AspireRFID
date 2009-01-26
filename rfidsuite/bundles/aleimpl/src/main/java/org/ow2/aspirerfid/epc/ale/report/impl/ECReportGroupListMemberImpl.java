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

import java.net.URI;
import java.util.Calendar;
import java.util.List;

import org.ow2.aspirerfid.epc.ale.report.api.ECReportGroupListMember;

/**
 * TODO Javadoc
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class ECReportGroupListMemberImpl implements ECReportGroupListMember {

	private Calendar date;

	private URI epc;

	private URI tag;

	private URI rawDecimal;

	private URI rawHex;

	private String readerName;

	private String gpsCoordinates;

	private List measurements;

	/**
	 * TODO Javadoc
	 * 
	 * @param date
	 * @param epc
	 * @param tag
	 * @param rawDecimal
	 * @param rawHex
	 * @param readerName
	 * @param gpsCoordinates
	 * @param measurements
	 */
	public ECReportGroupListMemberImpl(final Calendar date, final URI epc,
			final URI tag, final URI rawDecimal, final URI rawHex,
			final String readerName, final String gpsCoordinates,
			final List measurements) {
		this.date = date;
		this.epc = epc;
		this.tag = tag;
		this.rawDecimal = rawDecimal;
		this.rawHex = rawHex;
		this.readerName = readerName;
		this.gpsCoordinates = gpsCoordinates;
		this.measurements = measurements;
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return this.date;
	}

	/**
	 * @return the epc
	 */
	public URI getEPC() {
		return this.epc;
	}

	/**
	 * @return the gpsCoordinates
	 */
	public String getGpsCoordinates() {
		return this.gpsCoordinates;
	}

	/**
	 * @return the measurements
	 */
	public List getMeasurements() {
		return this.measurements;
	}

	/**
	 * @return the rawDecimal
	 */
	public URI getRawDecimal() {
		return this.rawDecimal;
	}

	/**
	 * @return the rawHex
	 */
	public URI getRawHex() {
		return this.rawHex;
	}

	/**
	 * @return the readerName
	 */
	public String getReaderName() {
		return this.readerName;
	}

	/**
	 * @return the tag
	 */
	public URI getTag() {
		return this.tag;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(final Calendar date) {
		this.date = date;
	}

	/**
	 * @param epc
	 *            the epc to set
	 */
	public void setEPC(final URI epc) {
		this.epc = epc;
	}

	/**
	 * @param gpsCoordinates
	 *            the gpsCoordinates to set
	 */
	public void setGpsCoordinates(final String gpsCoordinates) {
		this.gpsCoordinates = gpsCoordinates;
	}

	/**
	 * @param measurements
	 *            the measurements to set
	 */
	public void setMeasurements(final List measurements) {
		this.measurements = measurements;
	}

	/**
	 * @param rawDecimal
	 *            the rawDecimal to set
	 */
	public void setRawDecimal(final URI rawDecimal) {
		this.rawDecimal = rawDecimal;
	}

	/**
	 * @param rawHex
	 *            the rawHex to set
	 */
	public void setRawHex(final URI rawHex) {
		this.rawHex = rawHex;
	}

	/**
	 * @param readerName
	 *            the gatewayName to set
	 */
	public void setReaderName(final String readerName) {
		this.readerName = readerName;
	}

	/**
	 * @param tag
	 *            the tag to set
	 */
	public void setTag(final URI tag) {
		this.tag = tag;
	}

}
