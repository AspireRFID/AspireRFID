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

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.OperationalStatus;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.SnmpAgent;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.IfMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.IpMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.Address;
import org.apache.log4j.Logger;
import org.snmp4j.agent.DefaultMOContextScope;
import org.snmp4j.agent.DefaultMOQuery;
import org.snmp4j.agent.DefaultMOServer;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.agent.mo.snmp.DateAndTime;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;

/**
 * This class provides useful functionality for SNMP.
 */
public class SnmpUtil {
	
	/**
	 * The logger.
	 */
	private static Logger log = Logger.getLogger(SnmpUtil.class);

	/**
	 * Returns the <code>OctetString</code> representation of a
	 * <code>Date</code> object.
	 * 
	 * @param date
	 *            The date to be converted
	 * @return The <code>OctetString</code> representation of the given
	 *         <code>Date</code> object
	 */
	public static final OctetString dateToOctetString(final Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return DateAndTime.makeDateAndTime(calendar);
	}

	/**
	 * Returns the <code>Date</code> object represented by a
	 * <code>OctetString</code>.
	 * 
	 * @param dateOctetString
	 *            The octet string to be converted
	 * @return The <code>Date</code> object represented by the given
	 *         <code>OctetString</code>
	 */
	public static final Date octetStringToDate(final OctetString dateOctetString) {
		return DateAndTime.makeCalendar(dateOctetString).getTime();
	}
	
	/**
	 * Finds an <code>MOScalar</code> by an <code>OID</code>.
	 * 
	 * @param oid
	 *            The <code>OID</code>
	 * @return The <code>MOScalar</code> if it can be found, <code>null</code>
	 *         otherwise
	 */
	public static final MOScalar findMOScalar(OID oid) {
		SnmpAgent snmpAgent = SnmpAgent.getInstance();
		if (snmpAgent != null) {
			DefaultMOServer server = SnmpAgent.getInstance().getServer();
			OctetString[] contexts = server.getContexts();
			for (int i = 0; i < contexts.length; i++) {
				MOScalar scalar = null;
				try {
					scalar = (MOScalar) server.lookup(new DefaultMOQuery(
							new DefaultMOContextScope(contexts[i], oid, true,
									oid.nextPeer(), false)));
				} catch (ClassCastException cce) {
					log.error("Managed object with OID " + oid
							+ " is not a MOScalar");
					return null;
				}
				if (scalar != null) {
					return scalar;
				}
			}
		} else {
			log.error("SNMP agent not found.");
		}
		return null;
	}
	
	/**
	 * Gets a <code>MOTable</code> by a specified <code>TableTypeEnum</code>.
	 * 
	 * @param type
	 *            The <code>TableTypeEnum</code>
	 * @return The <code>MOTable</code> if it is accessible, <code>null</code>
	 *         otherwise
	 */
	public static final MOTable getSnmpTable(TableTypeEnum type) {
		EpcglobalReaderMib epcglobalReaderMib = EpcglobalReaderMib.getInstance();
		IfMib ifMib = IfMib.getInstance();
		IpMib ipMib = IpMib.getInstance();
		switch (type) {
			case EPCG_GLOBAL_COUNTERS_TABLE:
				return epcglobalReaderMib.getEpcgGlobalCountersEntry();
			case EPCG_READER_SERVER_TABLE:
				return epcglobalReaderMib.getEpcgReaderServerEntry();
			case EPCG_READ_POINT_TABLE:
				return epcglobalReaderMib.getEpcgReadPointEntry();
			case EPCG_ANT_READ_POINT_TABLE:
				return epcglobalReaderMib.getEpcgAntReadPointEntry();
			case EPCG_IO_PORT_TABLE:
				return epcglobalReaderMib.getEpcgIoPortEntry();
			case EPCG_SOURCE_TABLE:
				return epcglobalReaderMib.getEpcgSourceEntry();
			case EPCG_NOTIFICATION_CHANNEL_TABLE:
				return epcglobalReaderMib.getEpcgNotificationChannelEntry();
			case EPCG_TRIGGER_TABLE:
				return epcglobalReaderMib.getEpcgTriggerEntry();
			case EPCG_NOTIF_TRIG_TABLE:
				return epcglobalReaderMib.getEpcgNotifTrigEntry();
			case EPCG_READ_TRIG_TABLE:
				return epcglobalReaderMib.getEpcgReadTrigEntry();
			case EPCG_RD_PNT_SRC_TABLE:
				return epcglobalReaderMib.getEpcgRdPntSrcEntry();
			case EPCG_NOTIF_CHAN_SRC_TABLE:
				return epcglobalReaderMib.getEpcgNotifChanSrcEntry();
			case IF_TABLE:
				return ifMib.getIfEntry();
			case IP_ADDR_TABLE:
				return ipMib.getIpAddrEntry();
			case IP_NET_TO_MEDIA_TABLE:
				return ipMib.getIpNetToMediaEntry();
			case SNMP_TARGET_ADDR_TABLE:
				SnmpAgent snmpAgent = SnmpAgent.getInstance();
				if ((snmpAgent != null) && (snmpAgent.isInitialized())) {
					return snmpAgent.getSnmpTargetMIB().getSnmpTargetAddrEntry();
				}
			case SNMP_TARGET_PARAMS_TABLE:
				break;
			case SYS_OR_TABLE:
				break;
		}
		log.error(type + " is not accessible.");
		return null;
	}
	
	/**
	 * Returns the BITS-representation of an <code>OperationalStatus</code>.
	 * 
	 * @param operState
	 *            The <code>OperationalStatus</code>
	 * @return The BITS-representation of the <code>OperationalStatus</code>
	 */
	public static final OctetString operStateToBITS(OperationalStatus operState) {
		Integer value = null;
		switch (operState) {
			case UNKNOWN:
				value = Integer.valueOf("10000000", 2);
				break;
			case OTHER:
				value = Integer.valueOf("01000000", 2);
				break;
			case UP:
				value = Integer.valueOf("00100000", 2);
				break;
			case DOWN:
				value = Integer.valueOf("00010000", 2);
				break;
			case ANY:
				value = Integer.valueOf("11110000", 2);
		}
		return OctetString.fromHexString(Integer.toHexString(value.intValue()));
	}
	
	/**
	 * Returns the <code>OperationalStatus</code> represented by a BITS-<code>OctetString</code>.
	 * 
	 * @param bits
	 *            The BITS-<code>OctetString</code>
	 * @return The <code>OperationalStatus</code> represented by the BITS-<code>OctetString</code>
	 */
	public static final OperationalStatus bitsToOperState(OctetString bits) {
		Integer value = Integer.valueOf(bits.toHexString(), 16);
		if (value.intValue() == Integer.valueOf("10000000", 2).intValue())
			return OperationalStatus.UNKNOWN;
		if (value.intValue() == Integer.valueOf("01000000", 2).intValue())
			return OperationalStatus.OTHER;
		if (value.intValue() == Integer.valueOf("00100000", 2).intValue())
			return OperationalStatus.UP;
		if (value.intValue() == Integer.valueOf("00010000", 2).intValue())
			return OperationalStatus.DOWN;
		if (value.intValue() == Integer.valueOf("11110000", 2).intValue())
			return OperationalStatus.ANY;
		return null;
	}
	
	/**
	 * Converts the <code>OctetString</code> representation of an address to
	 * an <code>Address</code> object.
	 * 
	 * @param address
	 *            The <code>OctetString</code> representation of an address
	 * @param transportType
	 *            The transport type as specified in RFC3419 (currently only the
	 *            <code>transportDomainUdpIpv4</code> and
	 *            <code>transportDomainTcpIpv4</code> are supported)
	 * @return The <code>Address</code> object or <code>null</code> if the
	 *         given transport type is not supported
	 * @throws MalformedURLException
	 * @throws UnknownHostException
	 */
	public static final Address octetStringToAddress(OctetString address, OID transportType)
			throws MalformedURLException, UnknownHostException {
		String addrString = "";
		if (transportType.toString().equals("1.3.6.1.2.1.100.1.1")) {
			UdpAddress udpAddress = new UdpAddress();
			udpAddress.setTransportAddress(address);
			addrString += "udp://" + udpAddress.getInetAddress().getHostAddress() + ":" + udpAddress.getPort();
		} else if (transportType.toString().equals("1.3.6.1.2.1.100.1.5")) {
			TcpAddress tcpAddress = new TcpAddress();
			tcpAddress.setTransportAddress(address);
			addrString += "tcp://" + tcpAddress.getInetAddress().getHostAddress() + ":" + tcpAddress.getPort();
		} else {
			return null;
		}
		
		return new Address(addrString);
	}

// /**
// * Generates a bit string as <code>OctetString</code>. Each bit of that
//	 * bit string represents whether a particular state defined by the
//	 * <code>OperationalStatus</code> is enabled for notifications. The single
//	 * bits reference to the operational states as follows:
//	 * 
//	 * bit 0 -> <code>OperationalStatus.UNKNOWN</code>
//	 * bit 1 -> <code>OperationalStatus.OTHER</code>
//	 * bit 2 -> <code>OperationalStatus.UP</code>
//	 * bit 3 -> <code>OperationalStatus.DOWN</code>
//	 * 
//	 * @param unknownEnabled
//	 *            Defines whether <code>OperationalStatus.UNKNOWN</code> is
//	 *            enabled for notifications
//	 * @param otherEnabled
//	 *            Defines whether <code>OperationalStatus.OTHER</code> is
//	 *            enabled for notifications
//	 * @param upEnabled
//	 *            Defines whether <code>OperationalStatus.UP</code> is enabled
//	 *            for notifications
//	 * @param downEnabled
//	 *            Defines whether <code>OperationalStatus.DOWN</code> is
//	 *            enabled for notifications
//	 * @return Bit string
//	 */
//	public static final OctetString generateEpcgOperationalEnableOctetString(
//			final boolean unknownEnabled, final boolean otherEnabled,
//			final boolean upEnabled, final boolean downEnabled) {
//		return new OctetString((unknownEnabled ? "1" : "0")
//				+ (otherEnabled ? "1" : "0") + (upEnabled ? "1" : "0")
//				+ (downEnabled ? "1" : "0"));
//	}

}
