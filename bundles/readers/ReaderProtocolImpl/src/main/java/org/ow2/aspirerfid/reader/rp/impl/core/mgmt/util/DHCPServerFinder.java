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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;


/**
 * This class provides functionality to find DHCP servers.
 */
public class DHCPServerFinder {
	
	/**
	 * The logger.
	 */
	private static Logger log = Logger.getLogger(DHCPServerFinder.class);
	
	/**
	 * The MAC address of the network device.
	 */
	private byte[] macAddress;
	
	/**
	 * The UDP socket.
	 */
	private DatagramSocket socket;
	
	/**
	 * The socket timeout.
	 */
	private int socketTimeout = 3000;
	
	/**
	 * Random generator for the XID.
	 */
	private static Random ranXid = new Random();
	
	/**
	 * The constructor.
	 * 
	 * @param macAddress
	 *            The MAC address of the network device which is used
	 * @throws SocketException
	 */
	public DHCPServerFinder(byte[] macAddress) throws SocketException {
		this.macAddress = macAddress;
		
      try {
         socket = new DatagramSocket(68);
      } catch (SocketException se) {
         log.error("SocketException:\n-> A DHCP Server Finder is allready "
            + "running on port 68 (probably an other instance of the reader)."
            + "\n-> Will take an arbitrary port for this reader.\n-> This "
            + "will cause a java.net.BindException and the EPCglobal Reader "
            + "Management will not work.\n-> But the rest of the reader "
            + "should run as usual.");
         socket = new DatagramSocket();
         log.info("Port " + socket.getLocalPort()
            + " chosen for DHCP Server Finder.");
      }
		socket.setSoTimeout(socketTimeout);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		socket.close();
		super.finalize();
	}
	
	/**
	 * Finds the DHCP server.
	 * 
	 * @return The address of the DHCP server which has been found (<code>null</code>
	 *         if no DHCP server can be found).
	 */
	public InetAddress findDHCPServer() {
		int xid;
		try {
			xid = sendDHCPDiscoveryMessage();
		} catch (IOException ioe) {
			return null;
		}
		
		try {
			byte[] data = receiveDHCPMessage(xid);
			if (data != null) {
				byte[] addr = new byte[4];
				for (int i = 0; i < addr.length; i++) {
					addr[i] = data[20 + i];
				}
				try {
					return InetAddress.getByAddress(addr);
				} catch (UnknownHostException ignored) { }
			}
		} catch (IOException ignored) { }
		
		return null;
	}
	
	/**
	 * Sets he MAC address of the network device.
	 * 
	 * @param macAddress
	 *            the MAC address of the network device
	 */
	public void setMacAddress(byte[] macAddress) {
		this.macAddress = macAddress;
	}
	
	/**
	 * Converts the hexadecimal <code>String</code> representation of a MAC
	 * address to a byte array.
	 * 
	 * @param macAddr
	 *            The hexadecimal <code>String</code> representation of a MAC
	 *            address
	 * @param delimiter
	 *            The delimiter used in the <code>String</code> representation
	 * @return The resulting byte array
	 */
	public static byte[] macAddressStringToByteArray(String macAddr, String delimiter) {
		StringTokenizer tokenizer = new StringTokenizer(macAddr, delimiter);
		byte[] result = new byte[tokenizer.countTokens()];
		int index = 0;
		while (tokenizer.hasMoreElements()) {
			String token = tokenizer.nextToken();
			result[index++] = (byte) Integer.parseInt(token, 16);
		}
		return result;
	}
	
	/**
	 * Broadcasts a DHCP discovery message.
	 * 
	 * @return The XID of the discovery message
	 * @throws IOException
	 */
	private int sendDHCPDiscoveryMessage() throws IOException {
		byte[] dhcpDiscoveryMsg = new byte[244];
		
		// set OP
		dhcpDiscoveryMsg[0] = (byte) 0x1;
		
		// set HTYPE
		dhcpDiscoveryMsg[1] = (byte) 0x1;
		
		// set HLEN
		dhcpDiscoveryMsg[2] = (byte) 0x6;
		
		// set XID
		int xid = DHCPServerFinder.ranXid.nextInt();
		byte[] xidB = intToByteArray(xid);
		for (int i = 0; i < xidB.length; i++) {
			dhcpDiscoveryMsg[i + 4] = xidB[i];
		}
		
		// set CHADDR
		for (int i = 0; i < macAddress.length; i++) {
			dhcpDiscoveryMsg[i + 28] = macAddress[i];
		}
		
		// set the "magic cookie"
		dhcpDiscoveryMsg[236] = (byte) 0x63;
		dhcpDiscoveryMsg[237] = (byte) 0x82;
		dhcpDiscoveryMsg[238] = (byte) 0x53;
		dhcpDiscoveryMsg[239] = (byte) 0x63;
		
		// set DHCP Msg Type to "DHCP Discover"
		dhcpDiscoveryMsg[240] = (byte) 0x35;
		dhcpDiscoveryMsg[241] = (byte) 0x1;
		dhcpDiscoveryMsg[242] = (byte) 0x1;
		dhcpDiscoveryMsg[243] = (byte) 0xff;

		InetAddress dest = null;
		try {
			dest = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException ignored) { }
		
		DatagramPacket outgoing = new DatagramPacket(dhcpDiscoveryMsg,
				dhcpDiscoveryMsg.length, dest, 67);
		
		socket.send(outgoing); // send outgoing message
		
		return xid;
	}
	
	/**
	 * Receives a message with the given XID from the DHCP server.
	 * 
	 * @param xid
	 *            The XID
	 * @return The message (<code>null</code> if no message has been
	 *         received).
	 * @throws IOException
	 */
	private byte[] receiveDHCPMessage(int xid) throws IOException {
		byte[] xidB = intToByteArray(xid);
		long timeout = socket.getSoTimeout();
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < timeout) {
			DatagramPacket incoming = new DatagramPacket(new byte[1500], 1500);
			socket.receive(incoming);
			byte[] data = incoming.getData();
			boolean ok = true;
			for (int i = 0; i < xidB.length; i++) {
				if (data[i + 4] != xidB[i]) {
					ok = false;
					break;
				}
			}
			if (ok) {
				return data;
			}
		}
		return null;
	}
	
	/**
	 * Converts an integer to a byte array.
	 * 
	 * @param value
	 *            The integer
	 * @return The resulting byte array
	 */
	private byte[] intToByteArray(int value) {
		ByteArrayOutputStream outBStream = new ByteArrayOutputStream ();
		DataOutputStream outStream = new DataOutputStream (outBStream);

		try {
		    outStream.writeInt(value);
		} catch (IOException ioe) {
			log.error(ioe);
		}
		return outBStream.toByteArray();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String macAddr = "00-07-E9-3C-AD-1B";
		
		try {
			DHCPServerFinder finder = new DHCPServerFinder(DHCPServerFinder
					.macAddressStringToByteArray(macAddr, "-"));
			InetAddress server = finder.findDHCPServer();
			if (server != null) {
				System.out.println(server);
			} else {
				System.out.println("No DHCP server found.");
			}
		} catch (Exception ignored) { }

	}

}
