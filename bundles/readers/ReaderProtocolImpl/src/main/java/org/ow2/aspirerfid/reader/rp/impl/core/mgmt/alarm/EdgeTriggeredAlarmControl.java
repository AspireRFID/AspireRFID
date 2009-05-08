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

/**
 * This class extends <code>AlarmControl</code> to control alarms generated
 * when a monitored, integer-valued, model element first crosses a threshold
 * value (the <code>alarmThreshold</code>). This type of alarm is called
 * "edge triggered". The monitored value must cross a potentially different
 * threshold (the <code>rearmThreshold</code>) before it can be triggered
 * again.
 */
public class EdgeTriggeredAlarmControl extends AlarmControl {
	
	/**
	 * Threshold that must be crossed to trigger an alarm ready to fire.
	 */
	private int alarmThreshold;
	
	/**
	 * Threshold that must be crossed before a fired alarm can be triggered
	 * again.
	 */
	private int rearmThreshold;
	
	/**
	 * Indicates whether the alarm will be triggered on an
	 * <code>EdgeTriggeredAlarmDirection.RISING</code> or
	 * <code>EdgeTriggeredAlarmDirection.FALLING</code> passage through the
	 * <code>alarmThreshold</code>.
	 */
	private EdgeTriggeredAlarmDirection direction;
	
	/**
	 * Identifies the current state of the alarm, i.e.,
	 * <code>EdgeTriggeredAlarmStatus.ARMED</code> or
	 * <code>EdgeTriggeredAlarmStatus.FIRED</code>.
	 */
	private EdgeTriggeredAlarmStatus status;

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
	 * @param alarmThreshold
	 *            Threshold that must be crossed to trigger an alarm ready to
	 *            fire
	 * @param rearmThreshold
	 *            Threshold that must be crossed before a fired alarm can be
	 *            triggered again
	 * @param direction
	 *            Defines whether the alarm will be triggered on a rising or a
	 *            falling edge
	 */
	public EdgeTriggeredAlarmControl(String name, boolean enabled,
			AlarmLevel level, int suppressInterval, int alarmThreshold,
			int rearmThreshold, EdgeTriggeredAlarmDirection direction) {
		super(name, enabled, level, suppressInterval);
		this.alarmThreshold = alarmThreshold;
		this.rearmThreshold = rearmThreshold;
		this.direction = direction;
		
		// initial values
		status = EdgeTriggeredAlarmStatus.ARMED;
	}
	
	/**
	 * Returns the value of the <code>alarmThreshold</code> attribute.
	 * 
	 * @return The value of the <code>alarmThreshold</code> attribute
	 */
	public int getAlarmThreshold() {
		return alarmThreshold;
	}
	
	/**
	 * Sets the value of the <code>alarmThreshold</code> attribute.
	 * 
	 * @param alarmThreshold
	 *            The desired value of <code>AlarmThreshold</code>
	 */
	public void setAlarmThreshold(int alarmThreshold) {
		this.alarmThreshold = alarmThreshold;
	}
	
	/**
	 * Returns the value of the <code>rearmThreshold</code> attribute.
	 * 
	 * @return The value of the <code>rearmThreshold</code> attribute
	 */
	public int getRearmThreshold() {
		return rearmThreshold;
	}
	
	/**
	 * Sets the value of the <code>rearmThreshold</code> attribute.
	 * 
	 * @param alarmThreshold
	 *            The desired value of <code>rearmThreshold</code>
	 */
	public void setRearmThreshold(int alarmThreshold) {
		rearmThreshold = alarmThreshold;
	}
	
	/**
	 * Returns the value of the <code>direction</code> attribute.
	 * 
	 * @return The value of the <code>direction</code> attribute
	 */
	public EdgeTriggeredAlarmDirection getDirection() {
		return direction;
	}
	
	/**
	 * Sets the value of the <code>direction</code> attribute.
	 * 
	 * @param direction
	 *            The desired value of <code>direction</code>
	 */
	public void setDirection(EdgeTriggeredAlarmDirection direction) {
		this.direction = direction;
	}
	
	/**
	 * Returns the value of the <code>status</code> attribute.
	 * 
	 * @return The value of the <code>status</code> attribute
	 */
	public EdgeTriggeredAlarmStatus getStatus() {
		return status;
	}
	
	/**
	 * Sets the value of the <code>status</code> attribute.
	 * 
	 * @param status
	 *            The new value of the <code>status</code> attribute
	 */
	public void setStatus(EdgeTriggeredAlarmStatus status) {
		this.status = status;
	}

}
