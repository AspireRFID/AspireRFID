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
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.MgmtAgent;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.Address;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;
import org.apache.log4j.Logger;

/**
 * The <code>AlarmChannel</code> carries messages issued asynchronously by the
 * Reader to the Host. Messages on an <code>AlarmChannel</code> only flow in
 * this direction.
 */
public class AlarmChannel {
	
	/**
	 * The logger.
	 */
	private static Logger log = Logger.getLogger(AlarmChannel.class);
	
	/**
	 * The name of this alarm channel.
	 */
	private String name;
	
	/**
	 * The (host) address to which the reader will send alarms.
	 */
	private Address address;
	
	/**
	 * Create a <code>AlarmChannel</code> object with a given name. If an
	 * <code>AlarmChannel</code> object with the same name exists already, an
	 * error is returned. This is a static method. The <code>AlarmChannel</code>
	 * will implicitly be added to the list of all <code>AlarmChannels</code>
	 * kept by the <code>ReaderDevice</code> object.
	 * 
	 * @param name
	 *            The name of the <code>AlarmChannel</code> to be created.
	 * @param addr
	 *            The (host) address to which the reader will send alarms.
	 * @param readerDevice
	 *            The reader device
	 * @return The instance of the new <code>AlarmChannel</code>
	 * @throws ReaderProtocolException
	 *             The ReaderProtocolException "ERROR_OBJECT_EXISTS" is thrown
	 */
	public static AlarmChannel create(final String name, final Address addr, ReaderDevice readerDevice)
			throws ReaderProtocolException {
		// check if AlarmChannel with the same name exists
		try {
			readerDevice.getAlarmChannel(name);
		} catch (ReaderProtocolException e) {
			// create new AlarmChannel
			AlarmChannel newAlarmChannel = new AlarmChannel(name, addr);
			readerDevice.getAlarmChannels().put(name, newAlarmChannel);
			
			// register at the management agent
			MgmtAgent mgmtAgent = readerDevice.getManagementAgent();
			if (mgmtAgent.isInitialized()) {
				mgmtAgent.addAlarmChannels(new AlarmChannel[] { newAlarmChannel });
			} else {
				log
						.debug("Management agent not yet initialized: Cannot register at the management agent.");
			}
			
			return newAlarmChannel;
		}
		throw new ReaderProtocolException("ERROR_OBJECT_EXISTS",
				MessagingConstants.ERROR_OBJECT_EXISTS);
	}
	
	/**
	 * The private constructor of the <code>AlarmChannel</code>.
	 * 
	 * @param name
	 *            The name of the channel
	 * @param addr
	 *            The address of the host
	 */
	private AlarmChannel(final String name, final Address addr) {
		this.name = name;
		address = addr;
	}
	
	/**
	 * Returns the name of the <code>AlarmChannel</code> object.
	 * 
	 * @return The name of the <code>AlarmChannel</code> object
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the (host) address to which this <code>AlarmChannel</code>
	 * object sends its alarms.
	 * 
	 * @return The (host) address to which this <code>AlarmChannel</code>
	 *         object sends its alarms
	 */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * Sets the (host) address to which this <code>AlarmChannel</code> object
	 * sends its alarms.
	 * 
	 * @param addr
	 *            The reporting address for this <code>AlarmChannel</code>
	 */
	public void setAddress(final Address addr) {
		address = addr;
	}

}