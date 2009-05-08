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

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;


/**
 * This class provides functionality to find NTP servers.
 */
public class NTPServerFinder {
	
	/**
	 * The UDP socket.
	 */
	private DatagramSocket socket;
	
	/**
	 * The socket timeout.
	 */
	private int socketTimeout = 3000;
	
	/**
	 * The constructor.
	 * 
	 * @throws SocketException
	 */
	public NTPServerFinder() throws SocketException {
		socket = new DatagramSocket();
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
	 * Finds the NTP servers that are reachable.
	 * 
	 * @param timeout
	 *            The time duration in which it should be searched for NTP
	 *            servers
	 * 
	 * @return The addresses of the NTP servers that have been found.
	 */
	public Vector<InetAddress> findNTPServers(int timeout) {
		Vector<InetAddress> result = new Vector<InetAddress>();
		long startTime = System.currentTimeMillis();
		try {
			sendNTPDiscoveryMessage();
		} catch (IOException ioe) {
			return result;
		}
		
		int oldTimeout = -1;
		try {
			if (timeout < socket.getSoTimeout()) {
				oldTimeout = socket.getSoTimeout();
				socket.setSoTimeout(timeout);
			}
		} catch (SocketException ignored) { }
		
		while (System.currentTimeMillis() - startTime < timeout) {
			try {
				result.add(receiveNTPMessage().getAddress());
			} catch (IOException ignored) { }
		}
		
		if (oldTimeout != -1) {
			try {
				socket.setSoTimeout(oldTimeout);
			} catch (SocketException ignored) { }
		}
		
		return result;
	}
	
	/**
	 * Broadcasts a NTP discovery message.
	 * 
	 * @throws IOException
	 */
	private void sendNTPDiscoveryMessage() throws IOException {
		byte[] ntpDiscoveryMsg = new byte[48];
		
		// set LI = 0, VN = 1 and Mode = 3
		ntpDiscoveryMsg[0] = (byte) Integer.parseInt("00001011", 2); // 00|001|011
		
		// set Transmit Timestamp
		byte[] timestamp = computeNTPTimestamp(new Date());
		for (int i = 0; i < timestamp.length; i++) {
			ntpDiscoveryMsg[i + 40] = timestamp[i];
		}
		

		InetAddress dest = null;
		try {
			dest = InetAddress.getByName("224.0.0.1");
		} catch (UnknownHostException ignored) { }
		
		DatagramPacket outgoing = new DatagramPacket(ntpDiscoveryMsg,
				ntpDiscoveryMsg.length, dest, 123);
		
		socket.send(outgoing); // send outgoing message
	}
	
	/**
	 * Receives a message from the NTP server.
	 * 
	 * @return The message
	 * @throws IOException
	 */
	private DatagramPacket receiveNTPMessage() throws IOException {
		DatagramPacket incoming = new DatagramPacket(new byte[72], 72);
		socket.receive(incoming);
		return incoming;
	}
	
	/**
	 * Computes a NTP conform timestamp from a <code>Date</code> object.
	 * 
	 * @param date
	 *            The <code>Date</code> object
	 * @return The resulting NTP conform timestamp
	 */
	private byte[] computeNTPTimestamp(Date date) {
		byte[] timestamp = new byte[8];
		long milliseconds = date.getTime();
		long JAN_1970 = 2208988800L;
		long seconds = milliseconds/1000 + JAN_1970;
		long secondsFraction = (milliseconds - (milliseconds/1000) * 1000) * 4294967; // 0xffffffff/1000;;
		
		// seconds
		timestamp[0] = (byte) ((seconds >> 24) & 0xff);
		timestamp[1] = (byte) ((seconds >> 16) & 0xff);
		timestamp[2] = (byte) ((seconds >>  8) & 0xff);
		timestamp[3] = (byte) (seconds & 0xff);
		
		// seconds fraction
		timestamp[4] = (byte) ((secondsFraction >> 24) & 0xff);
		timestamp[5] = (byte) ((secondsFraction >> 16) & 0xff);
		timestamp[6] = (byte) ((secondsFraction >>  8) & 0xff);
		timestamp[7] = (byte) (secondsFraction & 0xff);
		
		return timestamp;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NTPServerFinder finder = new NTPServerFinder();
			Vector<InetAddress> servers = finder.findNTPServers(1000);
			if (servers.size() > 0) {
				Enumeration<InetAddress> iter = servers.elements();
				while (iter.hasMoreElements()) {
					System.out.println(iter.nextElement());
				}
			} else {
				System.out.println("No NTP servers found.");
			}
		} catch (Exception ignored) { }

	}

}
