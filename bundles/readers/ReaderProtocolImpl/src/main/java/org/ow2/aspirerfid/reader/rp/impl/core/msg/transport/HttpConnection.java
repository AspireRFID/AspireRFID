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

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Socket;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.Clients;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.HttpReceiverHandshakeMessage;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.HttpSenderHandshakeMessage;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.IncomingMessage;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.ReceiverHandshakeMessage;
import org.apache.log4j.Logger;

/**
 * 
 * A <code>HttpConnection</code> establishes socket-based communication link.
 * However, multiple client requests can come into the same port and,
 * consequently, into the same ServerSocket. Client connection requests are
 * queued at the port, so the server must accept the connections sequentially.
 * However, the server can service them simultaneously through the use of
 * threads - one thread per each client connection. HTTP connections are only
 * persitent until the HTTP Response is transmitted or the connection is lost
 * due to Reader, Host or network failure.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 */

public class HttpConnection extends Connection implements Runnable {

	// -------------------fields-----------------------------------------------

	/** The logger. */
	private static Logger log;

	/** Flag that indicates if the connection is still open. */
	private static boolean isOpen;

	/** Flag that indicates if there is still a client stream. */
	private boolean hasClient;

	/** The thread pool. */
	private ConnectionThreadPool threadPool = null;

	/** The clientSocket. */
	private Socket clientSocket = null;

	/** The input stream. */
	private MessageInputStream stream;

	// -------------------constructor-----------------------------------------

	/**
	 * Creates a new instance of <code>HttpConnection</code>. It creates and
	 * starts a thread for a client.
	 */
	public HttpConnection(final Socket clientSocket) {
		this.clientSocket = clientSocket;
		hasClient = true;
		isOpen = true;
		log = Logger.getLogger(getClass().getName());
		threadPool = ConnectionThreadPool.getInstance();
	}

	// -------------------methods-----------------------------------------------

	/**
	 * Handles a client by using a separate thread which processes the messages.
	 */
	public void handleClient() {
		try {
			threadPool.execute(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// clientThread = new Thread(this);
		// clientThread.start();
	}

	/**
	 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection#close()
	 */
	public void close() {
		log.debug("Closing the HTTP connection.");
		hasClient = false;
		isOpen = false;
		try {
			this.clientSocket.close();
			Clients.getInstance().removeClient(this);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Sends a message without the HTTP Header! Only use this method for HTTP
	 * status responses (e.g., 200 OK, 404 NOT FOUND etc.)
	 * 
	 * @param outMessage
	 *            The message to send to the client
	 */
	public void sendRaw(final String outMessage) {
		try {
			DataOutputStream out = new DataOutputStream(clientSocket
					.getOutputStream());
			out.writeUTF(outMessage);
			out.writeUTF(MessagingConstants.EOL);
			out.flush();
			log.debug("Response Message sent: " + outMessage);
			if (this.closeRequest) {
				close();
			}
		} catch (IOException e) {
			log.warn(e.getMessage());
			this.close();
		}

	}

	/**
	 * Sends a HTTP message. The handshaking parameters are added to the HTTP
	 * header.
	 * 
	 * @param outMessage
	 *            The message content to send.
	 */
	public void send(final String outMessage) {
		try {
			HttpReceiverHandshakeMessage httpHandshake = (HttpReceiverHandshakeMessage) receiverHandshake;
			DataOutputStream out = new DataOutputStream(clientSocket
					.getOutputStream());
			out.writeUTF(MessagingConstants.HTTP_VERSION + " "
					+ MessagingConstants.HTTP_OK + " OK"
					+ MessagingConstants.EOL);
			out.writeUTF(MessagingConstants.RP_RECEIVER_SIGNATURE + ": "
					+ httpHandshake.getReceiverSignature()
					+ MessagingConstants.EOL);
			out.writeUTF(MessagingConstants.RP_SPEC_VERSION_OK + ": "
					+ httpHandshake.getSpecVersionResponse()
					+ MessagingConstants.EOL);
			out.writeUTF(MessagingConstants.RP_REQUEST_CONTENT_TYPE_OK + ": "
					+ httpHandshake.getSenderFormatResponse()
					+ MessagingConstants.EOL);
			out.writeUTF(MessagingConstants.RP_RESPONSE_CONTENT_TYPE_OK + ": "
					+ httpHandshake.getReceiverFormatResponse()
					+ MessagingConstants.EOL);
			out.writeUTF(MessagingConstants.RP_RESPONSE_ACKNAK_OK + ": "
					+ httpHandshake.getAckNakResponse()
					+ MessagingConstants.EOL);
			out.writeUTF(MessagingConstants.HTTP_CONTENT_TYPE + ": "
					+ httpHandshake.getHttpContentType()
					+ MessagingConstants.EOL);
			out.writeUTF(MessagingConstants.HTTP_CONTENT_LENGTH + ": "
					+ outMessage.getBytes().length + MessagingConstants.EOL);
			out.writeUTF(MessagingConstants.EOL);
			out.writeUTF(outMessage);
			out.flush();
			log.debug("Response Message sent: " + outMessage);
			if (this.closeRequest) {
				close();
			}
		} catch (IOException e) {
			log.warn(e.getMessage());
			this.close();
		}
	}

	/**
	 * Sends a HTTP message 400 Bad Request to the client.
	 */
	private void send400() {
		this.sendRaw(MessagingConstants.HTTP_VERSION + " "
				+ MessagingConstants.HTTP_BAD_REQUEST + " Bad Request"
				+ MessagingConstants.EOL);
	}

	/**
	 * Sends a HTTP message 405 Bad Method to the client.
	 */
	private void send405() {
		this.sendRaw(MessagingConstants.HTTP_VERSION + " "
				+ MessagingConstants.HTTP_BAD_METHOD
				+ " unsupported method type." + MessagingConstants.EOL);
	}

	/**
	 * Returns the Socket this HttpConnection uses.
	 */
	public Socket getSocket() {
		return clientSocket;
	}
	
	/**
	 * Reads new messages from stream. It creates new incoming message and
	 * notifies all added listeners.
	 */
	public void run() {
		String message = null;
		boolean isNewConnection = true;
		log.debug("New HTTP connection thread running!");
		Clients client = Clients.getInstance();

		try {
			stream = new HttpMessageInputStream(clientSocket.getInputStream());

			while (isOpen && hasClient) {
				/* Read the handshake */
				log
						.debug("Trying to read header/handshake from the connection.");
				senderHandshake = (HttpSenderHandshakeMessage) stream
						.readHandshake();
				if (senderHandshake == null) {
					log
							.debug("Sender handshake not valid. Send HTTP error 400 BAD METHOD back.");
					receiverHandshake = new HttpReceiverHandshakeMessage();
					receiverHandshake
							.setResponse(ReceiverHandshakeMessage.RESPONSE_NO);
					send400();
					close();
				} else {
					// validate the HTTP request
					if (!validate((HttpSenderHandshakeMessage) senderHandshake)) {
						// request not valid, abort.
						send400();
						close();
						return;
					}
					receiverHandshake = new HttpReceiverHandshakeMessage();
					receiverHandshake.init(senderHandshake);

					if (!((HttpSenderHandshakeMessage) senderHandshake)
							.isPersistent()) {
						/* Connection: close in HTTP header */
						this.requestClose();
					}

					if (isNewConnection) {
						client.addClient(clientSocket.getRemoteSocketAddress()
								.toString(), this);
						log.debug("adding client "
								+ clientSocket.getRemoteSocketAddress()
										.toString() + " to the clients.");
						isNewConnection = false;
					}

				}

				/* Read the messages */
				log.debug("trying to read message from stream!");
				message = stream.readMessage();
				if (message != null) {
					log.debug("read from stream, message = " + message);
					// put the message into the MessageBuffer
					IncomingMessage im = new IncomingMessage(this, message);
					notifyListener(im);
				} else {
					log.debug("message is null");
					IncomingMessage im = new IncomingMessage(this, message);
					notifyListener(im);
					log.warn("Client connection closed.");
					hasClient = false;
				}
			}
			clientSocket.close();
		} catch (ProtocolException e) {
			send400();
		} catch (IOException e) {
			log.debug("Cannot read from HTTP Connection. Reason: "
					+ e.getMessage());
		} catch (Exception e) {
			log.error(e);
		} finally {
			// Close all streams
			stream.close();
			// Remove all clients which use this connection
			Clients clients = Clients.getInstance();
			clients.removeClient(this);
		}
	}

	/**
	 * Validates a HTTP request and sends back the corresponding HTTP error code
	 * if the handshake was not correct.
	 * 
	 * @param handshakeMsg
	 *            The handshake message to validate.
	 * @return flag indicating if the request was valid or not.
	 */
	private boolean validate(final HttpSenderHandshakeMessage handshakeMsg) {
		/* Only POST is supported */
		if (handshakeMsg.getMethod() == null
				|| !handshakeMsg.getMethod().equals(MessagingConstants.POST)) {
			// send405();
			return false;
		}

		/* Only HTTP/1.1 Request are allowed */
		if (handshakeMsg.getHttpVersion() == null
				|| !handshakeMsg.getHttpVersion().equals(
						MessagingConstants.HTTP_VERSION)
				|| handshakeMsg.getHost() == null
				|| handshakeMsg.getHost().equals("")) {
			// send405();
			return false;
		}

		return true;
	}

	/**
	 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection#setReceiverHandshake(ReceiverHandshakeMessage)
	 */
	public void setReceiverHandshake(
			HttpReceiverHandshakeMessage receiverHandshake) {
		this.receiverHandshake = receiverHandshake;
	}

	/**
	 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection#setSenderHandshake(SenderHandshakeMessage)
	 */
	public void setSenderHandshake(HttpSenderHandshakeMessage senderHandshake) {
		this.senderHandshake = senderHandshake;
	}

}
