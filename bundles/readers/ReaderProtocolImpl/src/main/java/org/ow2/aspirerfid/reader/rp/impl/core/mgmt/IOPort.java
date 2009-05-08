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

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt;

import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmLevel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.IOPortOperStatusAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.TTOperationalStatusAlarmControl;
import org.apache.log4j.Logger;

/**
 * An <code>IOPort</code> provides the description of the hardware element
 * that provides external input and output lines to connect to other components
 * outside the reader device.
 */
public class IOPort {
	
	/**
	 * The logger.
	 */
	private static Logger log = Logger.getLogger(IOPort.class);
	
	/**
	 * The name of this IO-port.
	 */
	private String name;
	
	/**
	 * The textual description of the IO-port. It is typically used to denote
	 * the equipment connected via this IO-port.
	 */
	private String description;
	
	/**
	 * The administrative status of a particular IO-port. It represents the
	 * host's desired status for this <code>IOPort</code>.
	 */
	private AdministrativeStatus adminStatus;
	
	/**
	 * The operational status of this particular IO-port.
	 */
	private OperationalStatus operStatus;
	
	/**
	 * Controls the conditions for generating alarms alerting a manager of
	 * changes in an <code>IOPort</code>'s operational status.
	 */
	private TTOperationalStatusAlarmControl operStatusAlarmControl;
	
	/**
	 * The number of IO port state change notifications that have been
	 * suppressed for this <code>IOPort</code> object.
	 */
	private int operStateSuppressions = 0;
	
	/**
	 * The constructor of <code>IOPort</code>.
	 * 
	 * @param name
	 *            The name of the IO-Port
	 */
	public IOPort(String name) {
		this.name = name;
		
		// default values
		description = "";
		adminStatus = AdministrativeStatus.UP;
		operStatus = OperationalStatus.UNKNOWN;
		
		operStatusAlarmControl = new TTOperationalStatusAlarmControl(name
				+ "_OperStatusAlarmControl", false, AlarmLevel.ERROR, 0,
				OperationalStatus.ANY, OperationalStatus.ANY);
	}
	
	/**
	 * Returns the name of this particular IO-port. Since the IO-port names are
	 * set by the manufacturer, there is no <code>setName()</code> command.
	 * 
	 * @return The name of this particular IO-port
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns a textual description of this IO-port. This is typically used to
	 * denote the equipment connected via this IO-port. If no description has
	 * been set it returns a <code>String</code> of length zero (an empty
	 * string).
	 * 
	 * @return The description of the IO-port
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Associates a textual description with an IO-port. This is typically used
	 * to denote the equipment connected via this IO-port.
	 * 
	 * @param description
	 *            The textual description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Returns the operational status of this particular IO-port.
	 * 
	 * @return The operational status of this particular IO-port
	 */
	public OperationalStatus getOperStatus() {
		return operStatus;
	}
	
	/**
	 * Returns the administrative status of a particular IO-port. This
	 * represents the host's desired status for this <code>IOPort</code>.
	 * 
	 * @return The current administrative status of this IO-port
	 */
	public AdministrativeStatus getAdminStatus() {
		return adminStatus;
	}
	
	/**
	 * Sets the administrative status of a particular IO-port. This represents
	 * the host's desired status for this <code>IOPort</code>.
	 * 
	 * @param administrativeStatus
	 *            The new administrative status to be set
	 */
	public void setAdminStatus(AdministrativeStatus administrativeStatus) {
		adminStatus = administrativeStatus;
	}
	
	/**
	 * Returns the <code>operStatusAlarmControl</code> attribute of this
	 * IO-port. This attribute is the object that controls the conditions for
	 * generating alarms alerting a manager of changes in an <code>IOPort</code>'s
	 * operational status.
	 * 
	 * @return The alarm control for monitoring the operational status of this
	 *         <code>IOPort</code>
	 */
	public TTOperationalStatusAlarmControl getOperStatusAlarmControl() {
		return operStatusAlarmControl;
	}
	
	/**
	 * Increases the number of IO port state change notifications that have been
	 * suppressed for this <code>IOPort</code> object by <code>1</code>.
	 */
	public void increaseOperStateSuppressions() {
		operStateSuppressions++;
	}
	
	/**
	 * Returns the number of IO port state change notifications that have been
	 * suppressed for this <code>IOPort</code> object.
	 * 
	 * @return The number of IO port state change notifications that have been
	 *         suppressed for this <code>IOPort</code> object
	 */
	public int getOperStateSuppressions() {
		return operStateSuppressions;
	}
	
	/**
	 * Resets the number of IO port state change notifications that have been
	 * suppressed for this <code>IOPort</code> object to <code>0</code>.
	 */
	public void resetOperStateSuppressions() {
		operStateSuppressions = 0;
	}
	
	/**
	 * Sets the operational status.
	 * 
	 * @param operStatus
	 *            The new operational status
	 */
	private void setOperStatus(OperationalStatus operStatus) {
		if (operStatus != this.operStatus) {
			try {
				ReaderDevice readerDevice = ReaderDevice.getInstance();
				readerDevice.getAlarmManager()
						.fireAlarm(
								new IOPortOperStatusAlarm(
										"IOPortOperStatusAlarm",
										operStatusAlarmControl.getLevel(),
										readerDevice, this.operStatus,
										operStatus, name),
								operStatusAlarmControl);
			} catch (ReaderProtocolException rpe) {
				log.error("Unable to obtain the ReaderDevice instance"
						+ " -> cannot generate operational status alarm.");
			}
			this.operStatus = operStatus;
		}
	}

}
