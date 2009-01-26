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

import java.net.URI;
import java.util.Calendar;
import java.util.List;

/**
 * This interface defines the methods for the ECReportGroupListMember. The
 * ECReportGroupListMember represents one EPC within the ECReportGroup list.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public interface ECReportGroupListMember {
	/**
	 * Returns an EPC represented as a pure identity URI according to the
	 * EPCglobal Tag Data Specification.
	 * 
	 * @return The Pure Identity URI for this EPC if the corresponding
	 *         ECReportSpec.setIncludeEPC(boolean) is set to true, otherwise
	 *         returns null.
	 */
	public URI getEPC();

	/**
	 * TODO Javadoc
	 * 
	 * @param epc
	 */
	public void setEPC(URI epc);

	/**
	 * Returns an EPC represented as a tag URI according to the EPCglobal Tag
	 * Data Specification.
	 * 
	 * @return The Tag URI for this EPC if the corresponding
	 *         ECReportSpec.setIncludeTag(boolean) is set to true, otherwise
	 *         returns null.
	 */
	public URI getTag();

	/**
	 * TODO Javadoc
	 * 
	 * @param tag
	 */
	public void setTag(URI tag);

	/**
	 * Returns a raw tag value represented as a raw hexadecimal URI according to
	 * the EPCglobal Tag Data Specification.
	 * 
	 * @return The raw hexadecimal URI for this EPC if the corresponding
	 *         ECReportSpec.setIncludeRawHex(boolean) is set to true, otherwise
	 *         returns null.
	 */
	public URI getRawHex();

	/**
	 * Sets raw tag value represented as a raw hexadecimal URI according to the
	 * EPCglobal Tag Data Specification.
	 * 
	 * @param rawHex
	 *            The raw hexadecimal URI for this EPC if the corresponding
	 *            ECReportSpec.setIncludeRawHex(boolean) is set to true,
	 *            otherwise returns null.
	 */
	public void setRawHex(URI rawHex);

	/**
	 * Returns a raw tag value represented as a raw decimal URI according to the
	 * EPCglobal Tag Data Specification.
	 * 
	 * @return The raw decimal URI for this EPC if the corresponding
	 *         ECReportSpec.setIncludeRawDecimal(boolean) is set to true,
	 *         otherwise returns null.
	 */
	public URI getRawDecimal();

	/**
	 * Sets the raw tag value represented as a raw decimal URI according to the
	 * EPCglobal Tag Data Specification.
	 * 
	 * @param rawDecimal
	 *            The raw decimal URI for this EPC if the corresponding
	 *            ECReportSpec.setIncludeRawDecimal(boolean) is set to true,
	 *            otherwise returns null.
	 */
	public void setRawDecimal(URI rawDecimal);

	/**
	 * Returns the reader name for this report
	 * 
	 * @return The reader name for this report
	 */
	public String getReaderName();

	/**
	 * Sets the reader name for this report
	 * 
	 * @param readerName
	 *            The reader name for this report
	 */
	public void setReaderName(String readerName);

	/**
	 * Returns the date when tags are read for this report
	 * 
	 * @return The date when tags are read for this report
	 */
	public Calendar getDate();

	/**
	 * Sets the date when tags are read for this report
	 * 
	 * @param date
	 *            The date when tags are read for this report
	 */
	public void setDate(Calendar date);

	/**
	 * Returns the GPS coordinates of the read tag.
	 * 
	 * @return The GPS coordinates of the read tag
	 */
	public String getGpsCoordinates();

	/**
	 * Sets the GPS coordinates of the read tag.
	 * 
	 * @param gpsCoordinates
	 *            The GPS coordinates of the read tag.
	 */
	public void setGpsCoordinates(String gpsCoordinates);

	/**
	 * Returns the list of measurements of the read tag.
	 * 
	 * @return The list of measurements of the read tag.
	 */
	public List getMeasurements();

	/**
	 * Sets the list of measurements of the read tag.
	 * 
	 * @param measurements
	 *            The list of measurements of the read tag.
	 */
	public void setMeasurements(List measurements);
}
