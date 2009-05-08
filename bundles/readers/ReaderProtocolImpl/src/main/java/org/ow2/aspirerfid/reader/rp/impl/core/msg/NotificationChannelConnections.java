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

package org.ow2.aspirerfid.reader.rp.impl.core.msg;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.HttpConnection;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.TCPConnection;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.TCPNotificationListenConnection;
import org.apache.log4j.Logger;

/**
 * This class is used as a pool for notification channel connections. Every
 * NotificationChannel needs a connection to send the read reports to the host.
 * This class manages the <code>Connection</code> used for that purpose.
 * Connections are either persistent or non-persistent, which means that they
 * are destroied after the ReadReport is sent to the host system.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class NotificationChannelConnections {
	// ====================================================================
	// ---------------------------- Fields ------------------------------//
	// ====================================================================

	/** The logger. */
	static private Logger log;

	/** The singleton instance of the <code>NotificationChannels</code>. */
	private static NotificationChannelConnections instance;

	/** The clients structure. */
	private Clients clients;

	/** The id sent with notification messages. */
	private static int messageId = 0;

	// ====================================================================
	// ----------------------- Constructors -----------------------------//
	// ====================================================================

	/**
	 * Constructor
	 */
	protected NotificationChannelConnections() {
		log = Logger.getLogger(getClass().getName());
		clients = Clients.getInstance();
	}

	// ====================================================================
	// ------------------------- Methods --------------------------------//
	// ====================================================================

	/**
	 * Returns the single Instance of a <code>NotificationChannels</code>.
	 * 
	 * @return The single instance of <code>NotificationChannels</code>.
	 */
	public static NotificationChannelConnections getInstance() {
		if (instance == null) {
			instance = new NotificationChannelConnections();
		}
		return instance;
	}

	/**
	 * Create a new notification channel and add it to the
	 * <code>NotificationChannels</code>. Also a new <code>Connection</code>
	 * using the <code>format</code> message format for serializing the
	 * messages is instantiated.
	 * 
	 * @param name
	 *            The name of the notification channel
	 * @param address
	 *            The address of the host to send the notifications.
	 * @param senderHandshake
	 *            The handshake of the request.
	 * @param receiverHandshake
	 *            The handshake of the response
	 */
	public void create(final String name, final Address address,
			final SenderHandshakeMessage senderHandshake,
			final ReceiverHandshakeMessage receiverHandshake) {
		try {
			Connection conn = null;
			if (address.getProtocol().equals(MessagingConstants.PROTOCOL_TCP)) {
				// use a TCP connection
				if (address.getMode() != null
						&& address.getMode().equals(Address.MODE_LISTEN)) {
					// listen mode
					ServerSocket serverSocket = new ServerSocket(address
							.getPort());
					conn = new TCPNotificationListenConnection(serverSocket);
				} else {
					// connect mode
					Socket socket = new Socket(address.getHost(), address
							.getPort());
					conn = new TCPConnection(socket);
				}

			} else if (address.getProtocol().equals(
					MessagingConstants.PROTOCOL_HTTP)) {
				// use a HTTP connection
				Socket socket = new Socket(address.getHost(), address.getPort());
				conn = new HttpConnection(socket);
			} else {
				log.error("Wrong transport protocol " + address.getProtocol()
						+ ". Only TCP and HTTP are supported.");
				return;
			}

			conn.setSenderHandshake(senderHandshake);
			conn.setReceiverHandshake(receiverHandshake);
			log.debug("adding notification client " + address.toString()
					+ " to the clients.");
			clients.addNotificationClient(address.toString(), name, conn);

		} catch (ConnectException e) {
			log.error("NotificationChannel refused. Cannot connect with host "
					+ address.toString());
		} catch (IOException e) {
			log.error(e);
		}
	}

	/**
	 * Closes a notification channel and removes it from the
	 * <code>Clients</code>.
	 * 
	 * @param conn
	 *            The connection used to send the notifications.
	 */
	public void close(Connection conn) {
		conn.close();
		clients.removeClient(conn);
	}

	/**
	 * Gets the message format of a notification channel.
	 * 
	 * @param name
	 *            The name of the notification channel.
	 * @return The <code>MessageFormat</code> to use as the format for
	 *         serializing the message.
	 */
	public MessageFormat getChannelFormat(final String name) {
		String clientID = clients.getNotificationChannelClientID(name);
		Connection conn = clients.getConnection(clientID);
		return conn.getReceiverMessageFormat();
	}

	/**
	 * Gets the the ID which are used in the message to identify a specific
	 * message (e.g., in the ACK process). The id is automatically increased.
	 * 
	 * @return the next id to use as a message id.
	 */
	public synchronized static int nextMessageId() {
		messageId++;
		return messageId;
	}

	/**
	 * Resets all NotificationChannelConnections.
	 */
	public void reset() {
		instance = null;
	}

}
