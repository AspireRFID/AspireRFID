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

package org.ow2.aspirerfid.sensor.temperproducer;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.util.measurement.Measurement;
import org.osgi.util.measurement.Unit;
import org.ow2.aspirerfid.sensor.producer.MeasurementProducer;

/**
 * The class provides a temperature producer for a TEMPer USB thermometer
 * (http://www.dealextreme.com/details.dx/sku.7003)
 * TODO should unregister Producer service when temper is null
 */
public class TEMPerProducer extends MeasurementProducer {

	/** Delay between device polls (in ms) */
	private int m_pollDelay;

	/** Service PID */
	private String m_PID;

	/** Device COM port */
	private String m_portName;

	/** Device communication */
	private TEMPerDevice temper;

	/** Field readable through JMX */
	private Map m_lastMeasurementMap;

	/** Fixed data values */
	public static final Double MEASUREMENT_ERROR = new Double(0.5);
	public static final Unit MEASUREMENT_UNIT = Unit.K;
	
	/** Bundle context */
	private BundleContext m_bundleContext;
	
	public TEMPerProducer(BundleContext bundleContext) {
		m_bundleContext = bundleContext;
	}
	
	/**
	 * iPOJO activation (starts thread)
	 * @throws Exception 
	 */
	public void activate() throws Exception {
		this.pollDelay = m_pollDelay;
		temper = new TEMPerDevice(m_portName);
		
		super.start(m_bundleContext);
	}

	/**
	 * iPOJO inactivation (stops thread)
	 * @throws Exception 
	 */
	public void deactivate() throws Exception {
		super.stop(m_bundleContext);
	}

	/**
	 * Retrieve current temperature
	 */
	protected Measurement getMeasurement() {
		if (temper == null) {
			return null;
		}

		double temperature = temper.getTemperature();
		long timestamp = System.currentTimeMillis();

		// Tests if temperature is under 500°K (~227 °C), because of some control values
		// visible under Linux (~ 255°C -> ~ 529°K)
		if (temperature != Double.NaN && temperature < 500) {
			// Remark: TEMPer accuracy is ~ 0.5°C @ 25°C according to
			// http://www.dealextreme.com/details.dx/sku.7003
			// and the temperature testing is between -40°C and +120°C

			HashMap data = new HashMap();
			data.put("value", new Double(temperature));
			data.put("unit", MEASUREMENT_UNIT.toString());
			data.put("error", MEASUREMENT_ERROR);
			data.put("time", new Long(timestamp));

			// iPOJO injection
			m_lastMeasurementMap = data;

			return new Measurement(temperature,
					MEASUREMENT_ERROR.doubleValue(), MEASUREMENT_UNIT,
					timestamp);
		}

		return null;
	}

	protected Hashtable getRegistrationProperties() {
		Hashtable registrationProperties = super.getRegistrationProperties();
		registrationProperties.put(Unit.class.getName(), MEASUREMENT_UNIT.toString());
		// could be outdoor temperature, indoor temperature, ...
		registrationProperties.put("application", "temperature"); 
		registrationProperties
				.put(org.osgi.framework.Constants.SERVICE_DESCRIPTION,
						"a Producer that poll Measurement objects containing temperatures measuring on a TEMPer USB thermometer");
		return registrationProperties;
	}
}
