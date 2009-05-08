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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection;

/**
 * <code>Clients</code> manages the inforamtion about registered clients. Each
 * client becomes a <code>Connection</code>,
 * <code>OutgoingMessageBuffer</code> and <code>OutgoingMessageClient</code>.
 * Clients stores this information in three maps.
 * 
 * @author Dijana Micijevic, ETH Zurich Switzerland, Winter 2003/04
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 */
public class Clients {

	private static Clients clients_ = null;

	private Map clientConnection;

	private Map clientMsgClient;

	private Map clientBuffer;

	private static Map clientNotifyClient;

	/**
	 * Constructor
	 * 
	 */
	private Clients() {
		clientBuffer = new HashMap();
		clientConnection = new HashMap();
		clientMsgClient = new HashMap();
		clientNotifyClient = new HashMap();
	}

	/**
	 * Gets the instance of this class.
	 * 
	 * @return instance of Clients
	 */
	public static Clients getInstance() {
		if (clients_ == null) {
			clients_ = new Clients();
		}
		return clients_;
	}

	/**
	 * Adds a new client with a given connection. An OutgoingMessageBuffer and
	 * an <code>OutgoingMessageClient</code> are created and added into the
	 * corresponding maps. The <code>OutgoingMessageClient</code> thread is
	 * started here.
	 * 
	 * @param clientID
	 *            id of the client
	 * @param connection
	 *            connection assigned to the client
	 */
	public void addClient(String clientID, Connection connection) {
		OutgoingMessageBuffer outBuffer = new OutgoingMessageBuffer();
		OutgoingMessageClient outClient = new OutgoingMessageClient(connection,
				outBuffer);
		clientConnection.put(clientID, connection);
		clientMsgClient.put(clientID, outClient);
		clientBuffer.put(clientID, outBuffer);
		outClient.start();
		outClient.resumeDispatching();
	}

	/**
	 * Adds a new notification channel client with a given connection. An
	 * OutgoingMessageBuffer and an <code>OutgoingMessageClient</code> are
	 * created and added into the corresponding maps. The
	 * <code>OutgoingMessageClient</code> thread is started.
	 * 
	 * @param clientID
	 *            id of the client
	 * @param channelName
	 *            The name of the notification channel.
	 * @param connection
	 *            connection assigned to the client
	 */
	public void addNotificationClient(final String clientID,
			final String channelName, Connection connection) {
		clientNotifyClient.put(channelName, clientID);
		addClient(clientID, connection);
	}

	/**
	 * Gets the client id of the notification channel associated with a channel
	 * name.
	 * 
	 * @param channelName
	 *            The channel name of the notification channel.
	 * @return The client id of the associated host.
	 */
	public String getNotificationChannelClientID(final String channelName) {
		return (String) clientNotifyClient.get(channelName);
	}

	/**
	 * Gets the channel name of the notification channel associated with the
	 * host with id clientID.
	 * 
	 * @param clientID
	 *            The clientID of a client associated with a notification
	 *            channel.
	 * @return The associated notification client name.
	 */
	private String getNotificationChannelName(final String clientID) {
		Iterator iter = clientNotifyClient.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String id = (String) clientNotifyClient.get(key);
			if (id.equals(clientID)) {
				return key;
			}
		}
		return null;
	}

	/**
	 * Checks if client with given clientID is registered.
	 * 
	 * @param clientID
	 *            id of client
	 * @return true if the client is registered
	 */
	protected boolean isRegistered(final String clientID) {
		return clientConnection.containsKey(clientID);
	}

	/**
	 * Checks if there is a client registered with this connection.
	 * 
	 * @param connection
	 *            for which we search for a client
	 * @return true if the client is registered with this connection
	 */
	protected boolean isRegistered(Connection connection) {
		return clientConnection.containsValue(connection);
	}

	/**
	 * Gets a connection assigned to the client with given id.
	 * 
	 * @param clientID
	 *            id of the client
	 * @return connection assigned to this client
	 */
	protected Connection getConnection(final String clientID) {
		return (Connection) clientConnection.get(clientID);
	}

	/**
	 * Gats the outgoing message buffer assigned to the client with given id.
	 * 
	 * @param clientID
	 *            id of the client
	 * @return outgoing message buffer assigned to the client
	 */
	protected OutgoingMessageBuffer getOutMsgBuffer(final String clientID) {
		return (OutgoingMessageBuffer) clientBuffer.get(clientID);
	}

	/**
	 * Gets the id of the client to whome the given connection is associated.
	 * 
	 * @param connection
	 *            associated to the client
	 * @return id of the client
	 */
	protected String getClientID(Connection connection) {
		Iterator iter = clientConnection.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Connection conn = (Connection) clientConnection.get(key);
			if (conn == connection) {
				return key;
			}
		}
		return null;
	}

	/**
	 * Removes a client.
	 * 
	 * @param connection
	 *            connection associated to the client
	 */
	public void removeClient(Connection connection) {
		String channelName;
		String clientID = getClientID(connection);
		OutgoingMessageClient omc = (OutgoingMessageClient) clientMsgClient
				.get(clientID);
		if (omc != null)
			omc.stopDispatching();
		clientMsgClient.remove(clientID);
		clientBuffer.remove(clientID);
		clientConnection.remove(clientID);
		channelName = getNotificationChannelName(clientID);
		if (channelName != null) {
			clientNotifyClient.remove(channelName);
		}
	}

	/**
	 * Gets all outgoing message clients.
	 * 
	 * @return all outgoing message clients
	 */
	protected Collection getOutgoingMessageClients() {
		return clientMsgClient.values();
	}

	/**
	 * Gets all client connections.
	 * 
	 * @return all client connections
	 */
	protected Collection getConnections() {
		return clientConnection.values();
	}

	/**
	 * Gets all outgoing message buffers.
	 * 
	 * @return all outgoing message buffers
	 */
	protected Collection getOutgoingMessageBuffers() {
		return clientBuffer.values();
	}

	/**
	 * Resets all connections.
	 */
	public synchronized void reset() {
		Iterator iter = clientConnection.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Connection conn = (Connection) clientConnection.get(key);
			removeClient(conn);
			conn.close();
		}
		clients_ = null;
	}
}
