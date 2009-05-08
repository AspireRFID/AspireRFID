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

package org.ow2.aspirerfid.reader.rp.impl.core.msg.transport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.IncomingMessageListener;
import org.apache.log4j.Logger;
/**<code>TCPServerConnection</code> represents a tcp server connection. It
 * listens at a port specified in the property file. If there is a new client, a
 * new <code>TCPConnection</code> is created for this client, which runs in a
 * separate thread. Thus, it is possible to have several clients communicating
 * with the server.
 * 
 * @author Dijana Micijevic, ETH Zurich Switzerland, Winter 2003/04
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 * 
 */
public class TCPServerConnection extends ServerConnection implements Runnable {

	/** The logger. */
	static private Logger log;

	/** Flag that indicates if the connection is still open. */
	private static boolean isOpen;

	/** The ServerSocket. */
	ServerSocket serverSocket = null;

	/** The connections. */
	private static Vector connections = null;

	/** Listener for incomin messages. */
	private IncomingMessageListener listener = null;

	/** The thread in which the server is running. */
	private Thread serverThread = null;

	/** The port number on which the server is listening. */
	private int port;

	/**
	 * Creates a new instance of <code>TCPServerConnection</code>.
	 * @param listener
	 */
	public TCPServerConnection(final IncomingMessageListener listener) {
		this.listener = listener;
		connections = new Vector();
		log = Logger.getLogger(getClass().getName());
	}

	/** Creates a separate client thread which processes client messages. */
	protected void createServerThread() {
		serverThread = new Thread(this);
		serverThread.start();
	}

	/**
	 * 
	 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection#open()
	 */
	public void open(int port) {
		isOpen = true;
		try {
			log.debug("Trying to open port " + port);
			serverSocket = new ServerSocket(port);
			this.port = port;
		} catch (IOException e) {
			log.error("Could not listen on port: " + port + ".");
			log.error(e.getMessage());
		}
		createServerThread();
	}

	public void run() {
		while (isOpen) {
			log.debug("Listening to port " + port);
			try {
				//
				// The accept method waits until a client starts up and
				// requests a connection on the host and port of this server.
				// When a connection is requested and successfully established,
				// the accept method returns a new Socket object which is bound
				// to a new port.
				//
				Socket clientSocket = serverSocket.accept();
				Connection clientConnection = new TCPConnection(clientSocket);
				clientConnection.addIncomingMessageListener(listener);
				connections.add(clientConnection);
				log.debug("Connection requested by client!");
				clientConnection.handleClient();

			} catch (IOException e) {
				log.warn("Accept failed: " + e.getMessage()
						+ e.getStackTrace());
				this.reset();
			}
		}
	}

	/**
	 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection#close()
	 */
	public void close() {
		isOpen = false;
		for (int i = 0; i < connections.size(); i++) {
			((Connection) connections.get(i)).close();
		}try {
			serverSocket.close();
		} catch (IOException e) {
		}
	}

	/**
	 * Resets the current connection.
	 * 
	 */
	protected void reset() {
		log.debug("the connection is resetting!");
		this.close();
	}
}
