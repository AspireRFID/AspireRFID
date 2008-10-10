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
import org.osgi.util.position.Position;

/**
 * This is the POJO used to send the GPS information through the wire.
 * 
 * @author <a href="maroulix@gmail.com">Perisanidi Maroula</a>
 * @version 08/07/2008 1.0.0
 */
public class GpsRead {

	/**
	 * Altitude value in meters.
	 */
	private Measurement altitude;

	/**
	 * Longitude value in rad.
	 */
	private Measurement longitude;

	/**
	 * Latitude value in rad.
	 */
	private Measurement latitude;

	/**
	 * TODO Javadoc
	 */
	private Measurement speed;

	/**
	 * TODO Javadoc
	 */
	private Measurement track;

	/**
	 * Contains the measurement for the latitude, longitude and altitude.
	 */
	private Position posititon;

	/**
	 * Name used for the report to describe the altitude.
	 */
	private String appNameAlt;

	/**
	 * Name used for the report to describe the latitude.
	 */
	private String appNameLat;

	/**
	 * Name used for the report to describe the longitude.
	 */
	private String appNameLon;

	/**
	 * Name used for the report to describe the sensor, here a GPS.
	 */
	private String sensor;

	/**
	 * @param alt
	 *            Altitude value in meters.
	 * @param lon
	 *            Longitude value in rad.
	 * @param lat
	 *            Latitude value in rad.
	 * @param u1
	 *            Altitude unit (meters).
	 * @param u2
	 *            Longitude unit (rad).
	 * @param u3
	 *            Latitude unit (rad).
	 * @param err
	 *            GPS expected error.
	 * @param sens
	 *            Name used for the report to describe the sensor, here a GPS.
	 * @param altTime
	 *            Time in milliseconds. The timestamp for altitude.
	 * @param lonlatTime
	 *            Time in milliseconds. The timestamp for longitude and
	 *            latitude.
	 */
	public GpsRead(double alt, double lon, double lat, Unit u1, Unit u2,
			Unit u3, double err, String sens, long altTime, long lonlatTime) {
		this.altitude = new Measurement(alt, err, u1, altTime);
		this.longitude = new Measurement(lon, err, u2, lonlatTime);
		this.latitude = new Measurement(lat, err, u3, lonlatTime);
		this.sensor = sens;
		this.appNameLat = "latitude";
		this.appNameLon = "longitude";
		this.appNameAlt = "altitude";
		// TODO correct values of speed and track
		this.speed = new Measurement(0, Unit.m_s);
		this.track = new Measurement(0, Unit.rad);
		this.posititon = new Position(this.latitude, this.longitude,
				this.altitude, this.speed, this.track);
	}

	/**
	 * @return Name used for the report to describe the latitude.
	 */
	public String getAppNameLat() {
		return appNameLat;
	}

	/**
	 * @return Name used for the report to describe the longitude.
	 */
	public String getAppNameLon() {
		return appNameLon;
	}

	/**
	 * @return Name used for the report to describe the altitude.
	 */
	public String getAppNameAlt() {
		return appNameAlt;
	}

	/**
	 * @return Name used for the report to describe the sensor, here a GPS.
	 */
	public String getSensor() {
		return sensor;
	}

	/**
	 * @param sensor
	 *            New sensor name. Used for reporting.
	 */
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	/**
	 * @return The measurements for the latitude, longitude and altitude.
	 */
	public Position getPosititon() {
		return posititon;
	}

	/**
	 * @param posititon
	 *            The measurements for the latitude, longitude and altitude.
	 */
	public void setPosititon(Position posititon) {
		this.posititon = posititon;
	}
}
