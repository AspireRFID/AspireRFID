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
package org.ow2.aspirerfid.wires;

import org.osgi.util.measurement.Measurement;
import org.osgi.util.measurement.Unit;

/**
 * This is the POJO used to send the temperature information through the wire.
 * 
 * @author <a href="maroulix@gmail.com">Perisanidi Maroula</a>
 * @version 08/07/2008 1.0.0
 */
public class TemperatureRead {

	/**
	 * Name used for the report to describe the application.
	 */
	private String applicationName;

	/**
	 * Name used for the report to describe the sensor, here a thermometer.
	 */
	private String sensor;

	/**
	 * The temperature value, the unit, the expected error and the timestamp.
	 */
	private Measurement temperature;

	/**
	 * @param temp
	 *            Temperature value in Kelvin.
	 * @param u
	 *            The temperature unit.
	 * @param er
	 *            Thermometer expected error.
	 * @param appN
	 *            Name used for the report to describe the application.
	 * @param sens
	 *            Name used for the report to describe the sensor, here a
	 *            thermometer.
	 * @param time
	 *            Time in milliseconds. The last temperature measurement.
	 */
	public TemperatureRead(double temp, Unit u, double er, String appN,
			String sens, long time) {
		this.temperature = new Measurement(temp, er, u, time);
		this.applicationName = appN;
		this.sensor = sens;
	}

	/**
	 * @return Name used for the report to describe the application.
	 */
	public String getAppName() {
		return applicationName;
	}

	/**
	 * @param appName
	 *            New name used for the report to describe the application.
	 */
	public void setAppName(String appName) {
		this.applicationName = appName;
	}

	/**
	 * @return Name used for the report to describe the sensor, here a
	 *         thermometer.
	 */
	public String getSensor() {
		return sensor;
	}

	/**
	 * @param sensor
	 *            New name used for the report to describe the sensor, here a
	 *            thermometer.
	 */
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	/**
	 * @return The temperature value, the unit, the expected error and the
	 *         timestamp.
	 */
	public Measurement getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature
	 *            The temperature value, the unit, the expected error and the
	 *            timestamp.
	 */
	public void setTemperature(Measurement temperature) {
		this.temperature = temperature;
	}
}
