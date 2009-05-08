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
 * <code>AlarmControl</code> is the base class for all classes responsible for
 * controlling the generation of Alarm messages, and consists of four main
 * attributes.
 */
public class AlarmControl {
	
	/**
	 * A unique identifier of an <code>AlarmControl</code> object.
	 */
	private String name;
	
	/**
	 * Controls whether the Alarm is enabled. If set to <code>false</code>,
	 * Alarm generation will be inhibited for the specific Alarm this object
	 * controls.
	 */
	private boolean enabled;
	
	/**
	 * Specifies the Alarm level (defined by the <code>AlarmLevel</code>
	 * enumeration) assigned to alarms whose generation this object controls.
	 */
	private AlarmLevel level;
	
	/**
	 * Specifies the minimum number of milliseconds that shall elapse before the
	 * next Alarm of the same type is generated. Each object can only generate
	 * one of these Alarms per SupressInterval. Setting the
	 * <code>suppressInterval</code> to <code>0</code> results in an Alarm
	 * generated for every Alarm condition encountered. Setting
	 * <code>suppressInterval</code> to a value greater than zero reduces the
	 * number of Alarms raised, minimizing the network traffic and load on the
	 * health monitoring applications. The ideal value will depend on the
	 * monitored environment and how frequently the health monitoring
	 * application needs to be interrupted to process Alarm conditions. Every
	 * Alarm contains a SuppressCount indicating the number of times the Alarm
	 * generation has been suppressed during SuppressInterval. This count is
	 * reset to <code>0</code> after the successful generation of the Alarm.
	 */
	private int suppressInterval;
	
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
	 */
	public AlarmControl(String name, boolean enabled, AlarmLevel level, int suppressInterval) {
		this.name = name;
		this.enabled = enabled;
		this.level = level;
		this.suppressInterval = suppressInterval;
	}
	
	/**
	 * Returns the <code>AlarmControl</code>'s unique name.
	 * 
	 * @return The <code>AlarmControl</code>'s unique name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the value which indicates whether the <code>AlarmControl</code>
	 * is enabled.
	 * 
	 * @return The value which indicates whether the <code>AlarmControl</code>
	 *         is enabled
	 */
	public boolean getEnabled() {
		return enabled;
	}
	
	/**
	 * Enables or disables alarm generation.
	 * 
	 * @param enable
	 *            A boolean that enables or disables the
	 *            <code>AlarmControl</code>
	 */
	public void setEnabled(boolean enable) {
		enabled = enable;
	}
	
	/**
	 * Returns the <code>level</code> attribute.
	 * 
	 * @return The <code>level</code> attribute
	 */
	public AlarmLevel getLevel() {
		return level;
	}
	
	/**
	 * Sets the <code>level</code> attribute.
	 * 
	 * @param alarmLevel
	 *            The desired Alarm Level value
	 */
	public void setLevel(AlarmLevel alarmLevel) {
		level = alarmLevel;
	}
	
	/**
	 * Returns the value of the <code>AlarmControl</code>'s
	 * <code>suppressInterval</code> value in msec.
	 * 
	 * @return The value of the <code>AlarmControl</code>'s
	 *         <code>suppressInterval</code> value in msec
	 */
	public int getSuppressInterval() {
		return suppressInterval;
	}
	
	/**
	 * Sets the <code>suppressInterval</code> attribute.
	 * 
	 * @param suppressInterval
	 *            The desired Alarm SuppressInterval value in msec
	 */
	public void setSuppressInterval(int suppressInterval) {
		this.suppressInterval = suppressInterval;
	}
	
}
