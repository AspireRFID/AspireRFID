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
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.OperationalStatus;

/**
 * <code>IOPortOperStatusAlarm</code> extends the
 * <code>TTOperStatusAlarm</code> class. Its receipt signals a change in the
 * operational status of a Reader's IO Port. The abstract model's
 * <code>IOPort.operStatusAlarmControl</code> data element controls the
 * triggering of alarms of this type.
 */
public class IOPortOperStatusAlarm extends TTOperStatusAlarm {
	
	/**
	 * The name of the IO Port that experienced the alarm-triggering state
	 * transition, i.e., the value of the respective <code>IOPort.name</code>
	 * model element.
	 */
	private String ioPortName;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            The name of the alarm identifying the type of alarm, e.g.,
	 *            "IOPortOperStatusAlarm"
	 * @param alarmLevel
	 *            The severity level of the alarm
	 * @param readerDevice
	 *            The reader device
	 * @param fromState
	 *            The originating <code>OperationalStatus</code> of the IO
	 *            Port before the <code>Alarm</code> is generated
	 * @param toState
	 *            The <code>OperationalStatus</code> at the time the
	 *            <code>Alarm</code> is generated
	 * @param ioPortName
	 *            The name of the IO Port
	 */
	public IOPortOperStatusAlarm(String name, AlarmLevel alarmLevel,
			ReaderDevice readerDevice, OperationalStatus fromState,
			OperationalStatus toState, String ioPortName) {
		super(name, alarmLevel, readerDevice, fromState, toState);
		this.ioPortName = ioPortName;
	}
	
	/**
	 * Returns the name of the IO Port that experienced the alarm-triggering
	 * state transition, i.e., the value of the respective
	 * <code>IOPort.name</code> model element.
	 * 
	 * @return The name of the <code>IOPort</code>
	 */
	public String getIOPortName() {
		return ioPortName;
	}

}
