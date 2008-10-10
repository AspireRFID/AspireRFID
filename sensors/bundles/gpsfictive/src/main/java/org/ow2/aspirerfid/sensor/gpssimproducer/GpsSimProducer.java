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
package org.ow2.aspirerfid.sensor.gpssimproducer;

import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.osgi.util.measurement.Unit;
import org.ow2.aspirerfid.wires.GpsRead;

/**
 * TODO Javadoc
 * 
 * @author Maroula Perisanidi
 * @version 1.0.0 2007/05/01
 */
public class GpsSimProducer implements Producer, Runnable {

	/**
	 * Set of wires between this producer and different consumers.
	 */
	private Wire wires[];

	/**
	 * Time we have to wait between two polls.
	 */
	private int pollDelay;

	/**
	 * Constructor
	 */
	public GpsSimProducer() {
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
				double altitude = getAltitude();
				double longitude = getLongitude();
				double latitude = getLatitude();
				GpsRead gps = new GpsRead(altitude, longitude, latitude,
						Unit.m, Unit.rad, Unit.rad, 0, "fictiveGPS", System
								.currentTimeMillis(), System
								.currentTimeMillis());
				for (int i = 0; wires != null && i < wires.length; i++) {
					Wire wire = wires[i];
					// check if wire is valid and connected
					if (!wire.isConnected() || !wire.isValid())
						continue;
					Object obj = polled(wire, gps);
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
		double altitude = getAltitude();
		double longitude = getLongitude();
		double latitude = getLatitude();
		GpsRead gps = new GpsRead(altitude, longitude, latitude, Unit.m,
				Unit.rad, Unit.rad, 0, "fictiveGPS",
				System.currentTimeMillis(), System.currentTimeMillis());
		return polled(wire, gps);
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
			if (clazz.isAssignableFrom(GpsRead.class))
				return object;
		}
		return null;
	}

	/**
	 * @return Random value of altitude.
	 */
	public double getAltitude() {
		double lower_alt = 10;
		double higher_alt = 234;
		double rand_alt = 0;
		rand_alt = (double) (Math.random() * (higher_alt + 1 - lower_alt))
				+ lower_alt;
		return rand_alt;
	}

	/**
	 * @return Random value of latitude.
	 */
	public double getLatitude() {
		double lower_lat = 4;
		double higher_lat = 5;
		double rand_lat = 0;
		rand_lat = (double) (Math.random() * (higher_lat + 1 - lower_lat))
				+ lower_lat;
		return rand_lat;
	}

	/**
	 * @return Random value of longitude.
	 */
	public double getLongitude() {
		double lower_lon = 45;
		double higher_lon = 46;
		double rand_lon = 0;
		rand_lon = (double) (Math.random() * (higher_lon + 1 - lower_lon))
				+ lower_lon;
		return rand_lon;
	}
}
