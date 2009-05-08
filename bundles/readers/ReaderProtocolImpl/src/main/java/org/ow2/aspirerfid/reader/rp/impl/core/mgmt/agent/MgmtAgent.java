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

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent;

import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmChannel;

/**
 * All management agents must implement this interface.
 */
public interface MgmtAgent {
	
	/**
	 * Adds the given <code>AlarmChannel</code>s.
	 * 
	 * @param alarmChannels
	 *            The <code>AlarmChannel</code>s to add
	 */
	public void addAlarmChannels(AlarmChannel[] alarmChannels);
	
	/**
	 * Removes an <code>AlarmChannel</code>.
	 * 
	 * @param alarmChannel
	 *            The <code>AlarmChannel</code> to remove
	 */
	public void removeAlarmChannel(AlarmChannel alarmChannel);
	
	/**
	 * Checks whether this agent has been initialized already.
	 * 
	 * @return <code>true</code> if this agent has been initialized already,
	 *         <code>false</code> otherwise
	 */
	public boolean isInitialized();

}
