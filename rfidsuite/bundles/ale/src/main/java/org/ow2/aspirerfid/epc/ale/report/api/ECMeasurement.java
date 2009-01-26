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

import org.osgi.util.measurement.Measurement;

/**
 * This interface defines the methods which are common to all event cycle
 * measurement types.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public interface ECMeasurement {
	/**
	 * Returns the measurement of an event cycle measurement
	 * 
	 * @return The measurement of an event cycle measurement
	 */
	public Measurement getMeasurement();

	/**
	 * Sets the measurement of an event cycle measurement
	 * 
	 * @param measurements
	 *            The measurement of an event cycle measurement
	 */
	public void setMeasurement(Measurement measurements);

	/**
	 * Returns the application name of an event cycle measurement
	 * 
	 * @return the application name of an event cycle measurement
	 */
	public String getApplicationName();

	/**
	 * Sets the application name of an event cycle measurement
	 * 
	 * @param applicationName
	 *            the application name of an event cycle measurement
	 */
	public void setApplicationName(String applicationName);

	/**
	 * Returns the sensor id of an event cycle measurement
	 * 
	 * @return the sensor id of an event cycle measurement
	 */
	public String getSensorId();

	/**
	 * Sets the sensor id of an event cycle measurement
	 * 
	 * @param sensorId
	 *            the sensor id of an event cycle measurement
	 */
	public void setSensorId(String sensorId);
}
