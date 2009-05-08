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

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmChannel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util.SnmpUtil;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.Address;
import org.apache.log4j.Logger;
import org.snmp4j.agent.mo.DefaultMOMutableRow2PC;
import org.snmp4j.agent.mo.snmp.RowStatus;
import org.snmp4j.agent.mo.snmp.RowStatusEvent;
import org.snmp4j.agent.mo.snmp.RowStatusListener;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;

/**
 * Row status listener to be used with the <code>snmpTargetAddrRowStatus</code>
 * column.
 */
public class SnmpTargetAddrRowStatusListener implements RowStatusListener {
	
	/**
	 * The logger.
	 */
	private static Logger log = Logger.getLogger(SnmpTargetAddrRowStatusListener.class);
	
	/**
	 * The reader device.
	 */
	private ReaderDevice readerDevice;
	
	/**
	 * The last <code>RowStatusEvent</code> object received.
	 */
	private RowStatusEvent lastEvent;
	
	/**
	 * The constructor.
	 * 
	 * @param readerDevice
	 *            The reader device
	 */
	public SnmpTargetAddrRowStatusListener(ReaderDevice readerDevice) {
		this.readerDevice = readerDevice;
	}

	/**
	 * Called whenever the row status changed.
	 * 
	 * @param event
	 *            Row status event
	 */
	public void rowStatusChanged(RowStatusEvent event) {
		if (!areRowStatusEventsEqual(event, lastEvent)) {
			if ((event.getNewStatus() == RowStatus.active) || (event.getNewStatus() == RowStatus.createAndGo) || (event.getNewStatus() == RowStatus.destroy)) {
				DefaultMOMutableRow2PC row = (DefaultMOMutableRow2PC)event.getRow();
//				row.setValue(4, new OctetString("notify"));
				row.setValue(5, new OctetString("v2c"));
				String name;
				try {
					name = new String(row.getIndex().toByteArray(), "UTF8");
				} catch (UnsupportedEncodingException uee) {
					name = row.getIndex().toString();
				}
				OID domain  = (OID)row.getValue(0);
				OctetString address = (OctetString)row.getValue(1);
				if ((event.getNewStatus() == RowStatus.active) || (event.getNewStatus() == RowStatus.createAndGo)) {
					try {
						Address addr = SnmpUtil.octetStringToAddress(address, domain);
						if (addr != null) {
							AlarmChannel.create(name, addr, readerDevice);
						} else {
							log.error("Transport type not supported: Alarm channel cannot be created.");
						}
					} catch (MalformedURLException mue) {
						log.error("Invalid address: Alarm channel cannot be created.");
					} catch (UnknownHostException uhe) {
						log.error("Unknown host: Alarm channel cannot be created.");
					} catch (ReaderProtocolException rpe) {
						log.error("Alarm channel with name <" + name + "> already exists.");
					}
				}
				else if (event.getNewStatus() == RowStatus.destroy) {
					try {
						AlarmChannel alarmChannel = readerDevice.getAlarmChannel(name.toString());
						readerDevice.getAlarmChannels().remove(alarmChannel.getName());
					} catch (ReaderProtocolException rpe) {
						log.error("Alarm channel with name <" + name + "> does not exist.");
					}
				}
			}
		} else {
			log.debug("Duplicated event ignored");
		}
		lastEvent = event;
	}
	
	/**
	 * Compares to <code>RowStatusEvent</code> objects for equality.
	 * 
	 * @param event1
	 *            First <code>RowStatusEvent</code> object
	 * @param event2
	 *            Second <code>RowStatusEvent</code> object
	 * @return <code>true</code> if the events are equal, <code>false</code>
	 *         otherwise
	 */
	private boolean areRowStatusEventsEqual(RowStatusEvent event1, RowStatusEvent event2) {
		if ((event1 == null) || (event2 == null)) return false;
		return (event1.getSource().equals(event2.getSource()))
				&& (event1.getRow().getIndex().toString().equals(event2
						.getRow().getIndex().toString()))
				&& (event1.getNewStatus() == event2.getNewStatus());
	}

}
