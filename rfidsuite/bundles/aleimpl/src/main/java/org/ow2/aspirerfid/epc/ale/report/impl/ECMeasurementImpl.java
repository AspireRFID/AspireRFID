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

import org.osgi.util.measurement.Measurement;
import org.ow2.aspirerfid.epc.ale.report.api.ECMeasurement;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 * @version 2007
 */
public class ECMeasurementImpl implements ECMeasurement {

	private String applicationName;

	private String sensorId;

	private Measurement measurement;

	/**
	 * Build an event cycle measurement.
	 * 
	 * @param applicationName
	 *            the application name
	 * @param sensorId
	 *            the sensor id
	 * @param measurement
	 *            the measurement
	 */
	public ECMeasurementImpl(final Measurement measurement,
			final String applicationName, final String sensorId) {
		this.applicationName = applicationName;
		this.sensorId = sensorId;
		this.measurement = measurement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECMeasurement#getApplicationName()
	 */
	public String getApplicationName() {
		return this.applicationName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECMeasurement#getMeasurement()
	 */
	public Measurement getMeasurement() {
		return this.measurement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECMeasurement#getSensorId()
	 */
	public String getSensorId() {
		return this.sensorId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECMeasurement#setApplicationName(java.lang.String)
	 */
	public void setApplicationName(final String applicationName) {
		this.applicationName = applicationName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECMeasurement#setMeasurement(org.osgi.util.measurement.Measurement)
	 */
	public void setMeasurement(final Measurement measurement) {
		this.measurement = measurement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.report.api.ECMeasurement#setSensorId(java.lang.String)
	 */
	public void setSensorId(final String sensorId) {
		this.sensorId = sensorId;
	}

}
