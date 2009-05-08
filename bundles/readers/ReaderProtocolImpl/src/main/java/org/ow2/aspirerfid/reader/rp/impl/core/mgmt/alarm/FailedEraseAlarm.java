/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm;

import org.ow2.aspirerfid.reader.rp.impl.core.AntennaReadPoint;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;

/**
 * <code>FailedEraseAlarm</code> extends the <code>Alarm</code> class. Its
 * receipt signals a tag erase or tag block erase failure. The abstract model's
 * <code>AntennaReadPoint.failedEraseAlarmControl</code> data element controls
 * the triggering of alarms of this type.
 */
public class FailedEraseAlarm extends Alarm {
	
	/**
	 * The name of the read point (an <code>AntennaReadPoint</code>) over
	 * which the erase failure occurred.
	 */
	private String readPointName;

	/**
	 * The value of <code>AntennaReadPoint.failedEraseCount</code> element
	 * after the erase failure occurred.
	 */
	private int failedEraseCount;

	/**
	 * The value of the <code>AntennaReadPoint.noiseLevel</code> element when
	 * the erase failure occurred.
	 */
	private int noiseLevel;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            The name of the alarm identifying the type of alarm, e.g.,
	 *            "FailedEraseAlarm"
	 * @param alarmLevel
	 *            The severity level of the alarm
	 * @param readerDevice
	 *            The reader device
	 * @param readPoint
	 *            The <code>AntennaReadPoint</code> over which the erase
	 *            failure occured
	 */
	public FailedEraseAlarm(String name, AlarmLevel alarmLevel,
			ReaderDevice readerDevice, AntennaReadPoint readPoint) {
		super(name, alarmLevel, readerDevice);
		readPointName = readPoint.getName();
		failedEraseCount = readPoint.getFailedEraseCount();
		noiseLevel = readPoint.getNoiseLevel();
	}
	
	/**
	 * Returns the name of the read point (an <code>AntennaReadPoint</code>)
	 * over which the erase failure occurred.
	 * 
	 * @return The name of the <code>ReadPoint</code>
	 */
	public String getReadPointName() {
		return readPointName;
	}

	/**
	 * Returns the value of <code>AntennaReadPoint.failedEraseCount</code>
	 * element after the erase failure occurred.
	 * 
	 * @return The <code>failedEraseCount</code> for the
	 *         <code>ReadPoint</code>
	 */
	public int getFailedEraseCount() {
		return failedEraseCount;
	}

	/**
	 * Returns the value of the <code>AntennaReadPoint.noiseLevel</code>
	 * element when the erase failure occurred.
	 * 
	 * @return The <code>noiseLevel</code> for the <code>ReadPoint</code>
	 */
	public int getNoiseLevel() {
		return noiseLevel;
	}

}
