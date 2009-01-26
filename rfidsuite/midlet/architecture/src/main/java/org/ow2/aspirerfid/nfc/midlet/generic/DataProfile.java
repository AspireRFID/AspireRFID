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

package org.ow2.aspirerfid.nfc.midlet.generic;

import java.util.Calendar;
import java.util.Date;

/**
 * This class gets the information about the telephone.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class DataProfile {

	/**
	 * Returns the information about the platform of the telephone. This is
	 * called the IMEI.
	 * 
	 * @return IMEI information.
	 */
	public static String getImei() {
		String imei;
		try {
			// Nokia
			imei = "microedition.platform:"
					+ System.getProperty("microedition.platform");
			// imei += "phone.imei:" + System.getProperty("phone.imei");
			// imei += "com.nokia.IMEI" +
			// System.getProperty("com.nokia.IMEI");
			// Sony
			// imei += "com.sonyericsson.imei"
			// + System.getProperty("com.sonyericsson.imei");
			// Motorola
			// imei += "IMEI" + System.getProperty("IMEI");
			// imei += "com.motorola.IMEI" +
			// System.getProperty("com.motorola.IMEI");
			// Samsung
			// imei += "com.samsung.imei" +
			// System.getProperty("com.samsung.imei");
			// Siemens
			// imei += "com.siemens.imei" +
			// System.getProperty("com.siemens.imei");

		} catch (Exception e) {
			throw new RuntimeException("Error DataProfile1 imei: "
					+ e.getMessage());
		}

		return imei;
	}

	/**
	 * Returns the language of the telephone. The MIDP system property
	 * "microedition.locale" tells the current language used in the device.
	 * 
	 * @param midlet
	 *            Caller midlet.
	 * @return Current phone's language.
	 */
	public static String getLanguage(GenericMidlet midlet) {
		return midlet.getAppProperty("microedition.locale");
	}

	/**
	 * Returns the version of the midlet.
	 * 
	 * @param midlet
	 *            Caller midlet.
	 * @return Version of the current midlet.
	 */
	public static String getMidletVersion(GenericMidlet midlet) {
		return midlet.getAppProperty("MIDlet-Version");
	}

	/**
	 * Returns the phone's profile. Composite Capability/Preference Profiles
	 * (CC/PP.)
	 * 
	 * @param m_midlet
	 *            Caller midlet.
	 * @return The phone's profile.
	 */
	public static String getProfile(GenericMidlet m_midlet) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the dimensions of the phone's display (Resolution in pixels.)
	 * 
	 * @param m_midlet
	 *            Midlet that executes.
	 * @return Size of the display.
	 */
	public static String getScreenSize(GenericMidlet m_midlet) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns information about the SIM card.
	 * 
	 * @return Information about the SIM card.
	 */
	public static String getSIMInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the name of the caller midlet. This is a symbolic name of the
	 * application.
	 * 
	 * @param midlet
	 *            Midlet to analyze.
	 * @return Name of the midlet.
	 */
	public static String getSymbolicName(GenericMidlet midlet) {
		return midlet.getClass().getName();
	}

	/**
	 * Returns the date and time in a timestamp format.
	 * 
	 * @return Current timestamp
	 */
	public static String getTimestamp() {
		String value = "";
		try {
			Calendar date = Calendar.getInstance();
			date.setTime(new Date());

			// Year.
			value += date.get(Calendar.YEAR);
			// Month
			int month = date.get(Calendar.MONTH) + 1;
			value += (month < 10) ? "0" + month : "" + month;
			// Day
			int day = date.get(Calendar.DAY_OF_MONTH);
			value += (day < 10) ? "0" + day : "" + day;
			// Hour
			value += date.get(Calendar.HOUR_OF_DAY);
			// Minute
			value += date.get(Calendar.MINUTE);
			// Second
			value += date.get(Calendar.SECOND);
		} catch (Exception e) {
			throw new RuntimeException("Error DataProfile2 timestamp: "
					+ e.getMessage());
		}
		return value;
	}

	/**
	 * Returns a unique identifier of the midlet that is generated at deployment
	 * time.
	 * 
	 * @param midlet
	 *            Caller midlet.
	 * @return UUID of the midlet.
	 */
	public static String getUUID(GenericMidlet midlet) {
		return midlet.getAppProperty("MIDlet-UUID");
	}

	/**
	 * Returns the coodinates of the phone's position. The coordinates are in
	 * format WSG 84.
	 * 
	 * @return Coordinates of the phone's position.
	 */
	public static String getWGS84Coordinates() {
		// TODO Auto-generated method stub
		return null;
	}
}
