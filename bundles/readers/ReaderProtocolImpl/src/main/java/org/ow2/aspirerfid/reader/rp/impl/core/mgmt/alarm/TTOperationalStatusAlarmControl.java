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

import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.OperationalStatus;

/**
 * This class extends <code>AlarmControl</code> to control alarms generated
 * when a monitored model element of type <code>OperationalStatus</code>
 * transitions to a new value. This type of alarm is called "transition
 * triggered" (abbreviated "TT").
 */
public class TTOperationalStatusAlarmControl extends AlarmControl {
	
	/**
	 * Indicates the value of the monitored, <code>OperationalStatus</code>-valued,
	 * model element prior to the state transition which triggers the alarm.
	 */
	private OperationalStatus triggerFromState;
	
	/**
	 * Indicates the value of the monitored, <code>OperationalStatus</code>-valued,
	 * model element immediately after the state transition which triggers the
	 * alarm.
	 */
	private OperationalStatus triggerToState;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            A unique identifier
	 * @param enabled
	 *            Defines whether the Alarm is enabled
	 * @param level
	 *            The Alarm level
	 * @param suppressInterval
	 *            The minimum number of seconds that shall elapse before the
	 *            next Alarm of the same type is generated
	 * @param triggerFromState
	 *            The value of the monitored, <code>OperationalStatus</code>-valued,
	 *            model element prior to the state transition which triggers the
	 *            alarm
	 * @param triggerToState
	 *            The value of the monitored, <code>OperationalStatus</code>-valued,
	 *            model element immediately after the state transition which
	 *            triggers the alarm
	 */
	public TTOperationalStatusAlarmControl(String name, boolean enabled,
			AlarmLevel level, int suppressInterval,
			OperationalStatus triggerFromState, OperationalStatus triggerToState) {
		super(name, enabled, level, suppressInterval);
		this.triggerFromState = triggerFromState;
		this.triggerToState = triggerToState;
	}
	
	/**
	 * Returns the value of the <code>triggerFromState</code> attribute.
	 * 
	 * @return The value of the <code>triggerFromState</code> attribute
	 */
	public OperationalStatus getTriggerFromState() {
		return triggerFromState;
	}
	
	/**
	 * Sets the value of the <code>triggerFromState</code> attribute.
	 * 
	 * @param triggerFromState
	 *            The desired value of <code>triggerFromState</code>
	 */
	public void setTriggerFromState(OperationalStatus triggerFromState) {
		this.triggerFromState = triggerFromState;
	}
	
	/**
	 * Returns the value of the <code>triggerToState</code> attribute.
	 * 
	 * @return The value of the <code>triggerToState</code> attribute
	 */
	public OperationalStatus getTriggerToState() {
		return triggerToState;
	}
	
	/**
	 * Sets the value of the <code>triggerToState</code> attribute.
	 * 
	 * @param triggerToState
	 *            The desired value of <code>triggerToState</code>
	 */
	public void setTriggerToState(OperationalStatus triggerToState) {
		this.triggerToState = triggerToState;
	}

}
