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

import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.osgi.util.measurement.Unit;
import org.ow2.aspirerfid.wires.TemperatureRead;

/**
 * TODO Javadoc
 * 
 * @author Perisanidi Maroula
 */
public class TemperatureProducer implements Producer, Runnable,
		TemperatureProducerMBean {

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

	/**
	 * The current temperature in 1/10 kelvin
	 */
	private int currentTemp = 2732;

	/**
	 * Constructor.
	 */
	public TemperatureProducer() {

		this.pollDelay = 10000;
		new Thread(this).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public synchronized void run() {
		while (true)
			try {
				double temperature = getCurrentTemperature();
				TemperatureRead temp = new TemperatureRead(temperature, Unit.K,
						0, "temperature", "fictiveTemperature", System
								.currentTimeMillis());
				for (int i = 0; wires != null && i < wires.length; i++) {
					Wire wire = wires[i];
					// check if wire is valid and connected
					if (!wire.isConnected() || !wire.isValid())
						continue;
					Object obj = polled(wire, temp);
					if (obj != null)
						wire.update(obj);
				}
				wait(pollDelay);
			} catch (InterruptedException ie) {
				/* will recheck quit */
			}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.wireadmin.Producer#consumersConnected(org.osgi.service.wireadmin.Wire[])
	 */
	public synchronized void consumersConnected(Wire wires[]) {
		this.wires = wires;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.wireadmin.Producer#polled(org.osgi.service.wireadmin.Wire)
	 */
	public Object polled(Wire wire) {
		double temperature = getCurrentTemperature();
		TemperatureRead temp = new TemperatureRead(temperature, Unit.K, 0,
				"temperature", "fictiveTemperature", System.currentTimeMillis());
		return polled(wire, temp);
	}

	/**
	 * @param wire
	 *            A connected and valid wire.
	 * @param object
	 *            The object to be sent through the wire.
	 * @return null if none of its ancestors or implemented interfaces is the
	 *         same as one the wire's flavors
	 */
	private Object polled(Wire wire, Object object) {
		Class clazzes[] = wire.getFlavors();
		for (int i = 0; i < clazzes.length; i++) {
			Class clazz = clazzes[i];
			if (clazz.isAssignableFrom(TemperatureRead.class))
				return object;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.sensor.thermometer.TemperatureProducerMBean#getCurrentTemperature()
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
	 * @see org.ow2.aspirerfid.sensor.thermometer.TemperatureProducerMBean#setCurrentTemperature(double)
	 */
	public void setCurrentTemperature(double newTemp) {
		currentTemp = (int) Math.round(newTemp * 10);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.sensor.thermometer.TemperatureProducerMBean#getAutoChange()
	 */
	public boolean getAutoChange() {
		return autoChange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.sensor.thermometer.TemperatureProducerMBean#setAutoChange(boolean)
	 */
	public void setAutoChange(boolean auto) {
		autoChange = auto;
	}
}
