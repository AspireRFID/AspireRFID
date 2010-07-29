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
package org.ow2.aspirerfid.sensor.thermometer;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.osgi.service.wireadmin.WireConstants;
import org.osgi.util.measurement.Measurement;
import org.osgi.util.measurement.Unit;

/**
 * TODO Javadoc
 * 
 * @author Perisanidi Maroula
 * @author Thomas Calmant (Transformation into an iPOJO)
 */
public class TemperatureProducer implements Producer, Runnable {

	/**
	 * Service PID
	 */
	private String m_PID;

	/**
	 * Set of wires between this producer and different consumers.
	 */
	private Wire wires[];

	/**
	 * Time we have to wait between two polls.
	 */
	private int pollDelay;

	/**
	 * Change the temperature value automatically.
	 */
	private boolean autoChange = true;

	/** The current temperature in 1/10 kelvin */
	private int currentTemp = 2732;

	/** Producer flavors */
	private static final Class[] m_flavors = { Measurement.class, Double.class,
			Float.class, String.class };

	/** The update thread */
	private Thread m_thread;

	/** Thread control */
	private boolean m_stop;

	/** iPOJO / MBean : last measurement */
	private Map m_lastMeasurementMap;

	/** Bundle context */
	private BundleContext m_bundleContext;

	/** Producer service registration */
	private ServiceRegistration m_serviceRegistration;

	/**
	 * Constructor (called by iPOJO)
	 * 
	 * @param context
	 *            OSGi bundle context
	 */
	public TemperatureProducer(BundleContext context) {
		m_bundleContext = context;
	}
	
	/**
	 * iPOJO activation (starts thread)
	 */
	public void activate() {

		// registration of the service
		Hashtable props = new Hashtable();
		props.put(Constants.SERVICE_PID, m_PID);
		props.put(WireConstants.WIREADMIN_PRODUCER_FLAVORS, m_flavors);
		m_serviceRegistration = m_bundleContext.registerService(
				new String[] { Producer.class.getName() }, this, props);

		m_thread = new Thread(this);
		m_stop = false;
		m_thread.start();
	}

	/**
	 * iPOJO inactivation (stops thread)
	 */
	public void deactivate() {
		m_stop = true;
		m_thread.interrupt();

		m_serviceRegistration.unregister();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public synchronized void run() {
		while (!m_stop)
			try {
				Measurement temp = updateValue();

				for (int i = 0; wires != null && i < wires.length; i++) {
					Wire wire = wires[i];
					// check if wire is valid and connected
					if (!wire.isConnected() || !wire.isValid())
						continue;

					wire.update(temp);
				}

				wait(pollDelay);
			} catch (InterruptedException ie) {
				break;
			}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.service.wireadmin.Producer#consumersConnected(org.osgi.service
	 * .wireadmin.Wire[])
	 */
	public synchronized void consumersConnected(Wire wires[]) {
		this.wires = wires;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.service.wireadmin.Producer#polled(org.osgi.service.wireadmin
	 * .Wire)
	 */
	public Object polled(Wire wire) {
		Measurement lastValue = updateValue();

		Class clazzes[] = wire.getFlavors();
		for (int i = 0; i < clazzes.length; i++) {
			Class clazz = clazzes[i];
			if (clazz.isAssignableFrom(Measurement.class)) {
				return lastValue;
			}
			if (clazz.isAssignableFrom(Double.class)) {
				return new Double(lastValue.getValue());
			}
			if (clazz.isAssignableFrom(Float.class)) {
				return new Float(lastValue.getValue());
			}
			if (clazz.isAssignableFrom(String.class)) {
				return lastValue.toString();
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.sensor.thermometer.TemperatureProducerMBean#
	 * getCurrentTemperature()
	 */
	public double getCurrentTemperature() {
		if (autoChange) {
			double rand = Math.random();
			long randLong = Math.round(rand * 4.0);
			if (randLong == 0) {
				rand = Math.round(Math.random() * 20.0 - 10.0);
				currentTemp += rand;
			}
		}
		return currentTemp / 10.0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.sensor.thermometer.TemperatureProducerMBean#
	 * setCurrentTemperature(double)
	 */
	public void setCurrentTemperature(double newTemp) {
		currentTemp = (int) Math.round(newTemp * 10);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ow2.aspirerfid.sensor.thermometer.TemperatureProducerMBean#getAutoChange
	 * ()
	 */
	public boolean getAutoChange() {
		return autoChange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ow2.aspirerfid.sensor.thermometer.TemperatureProducerMBean#setAutoChange
	 * (boolean)
	 */
	public void setAutoChange(boolean auto) {
		autoChange = auto;
	}

	/**
	 * Save value in memory (iPOJO/JMX) and creates a {@link TemperatureRead}
	 * object
	 * 
	 * @return a {@link TemperatureRead} object
	 */
	private Measurement updateValue() {
		long timestamp = System.currentTimeMillis();
		double temperature = getCurrentTemperature();

		// Code added for iPOJO
		HashMap data = new HashMap();
		data.put("value", new Double(getCurrentTemperature()));
		data.put("unit", Unit.K.toString());
		data.put("error", new Double(Double.NaN));
		data.put("time", new Long(timestamp));

		// iPOJO injection
		m_lastMeasurementMap = data;

		return new Measurement(temperature, 0.0, Unit.K, timestamp);
	}
}
