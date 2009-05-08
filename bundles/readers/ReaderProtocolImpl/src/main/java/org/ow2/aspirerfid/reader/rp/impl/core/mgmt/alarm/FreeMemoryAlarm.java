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

import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;

/**
 * <code>FreeMemoryAlarm</code> extends the <code>Alarm</code> class. Its
 * receipt signals the movement of a reader device's free memory (represented in
 * the abstract model by <code>ReaderDevice.freeMemory</code>) below a
 * specified threshold value. The abstract model's
 * <code>ReaderDevice.freeMemoryAlarmControl</code> object controls the
 * triggering of alarms of this type.
 */
public class FreeMemoryAlarm extends Alarm {
	
	/**
	 * The value of <code>ReaderDevice.freeMemory</code> when the alarm was
	 * triggered.
	 */
	private long freeMemory;
	
	/**
	 * Constructor.
	 * 
	 * @param name
	 *            The name of the alarm identifying the type of alarm, e.g.,
	 *            "FreeMemoryAlarm"
	 * @param alarmLevel
	 *            The severity level of the alarm
	 * @param readerDevice
	 *            The reader device
	 */
	public FreeMemoryAlarm(String name, AlarmLevel alarmLevel,
			ReaderDevice readerDevice) {
		super(name, alarmLevel, readerDevice);
		freeMemory = readerDevice.getFreeMemory();
	}
	
	/**
	 * Returns the value of <code>ReaderDevice.freeMemory</code> when the
	 * alarm was triggered.
	 * 
	 * @return The value of <code>ReaderDevice.freeMemory</code> when the
	 *         alarm was triggered
	 */
	public long getFreeMemory() {
		return freeMemory;
	}
	
}
