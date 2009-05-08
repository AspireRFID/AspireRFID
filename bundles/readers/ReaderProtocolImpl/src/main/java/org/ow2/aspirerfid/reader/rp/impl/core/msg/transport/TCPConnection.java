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

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.Clients;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.IncomingMessage;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.ReceiverHandshakeMessage;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.TcpReceiverHandshakeMessage;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.TcpSenderHandshakeMessage;
import org.apache.log4j.Logger;

/**
 * 
 * A <code>TCPConnection</code> establishes socket-based communication link.
 * However, multiple client requests can come into the same port and,
 * consequently, into the same ServerSocket. Client connection requests are
 * queued at the port, so the server must accept the connections sequentially.
 * However, the server can service them simultaneously through the use of
 * threads - one thread per each client connection.
 * 
 * @author Dijana Micijevic, ETH Zurich Switzerland, Winter 2003/04
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 */

public class TCPConnection extends Connection implements Runnable {

	// -------------------fields-----------------------------------------------

	/** The logger. */
	private static Logger log;

	/** Flag that indicates if the connection is still open. */
	private static boolean isOpen;

	/** Flag that indicates if there is still a client stream. */
	private boolean hasClient;

	/** The clientSocket. */
	Socket clientSocket = null;

	/** The input stream. */
	private MessageInputStream stream;

	/** The thread pool for all connections. */
	private ConnectionThreadPool threadPool = null;

	// -------------------constructor-----------------------------------------

	/**
	 * Creates a new instance of <code>TCPConnection</code>. It creates and
	 * starts a thread for a client.
	 * @param clientSocket The socket for client handling
	 */
	public TCPConnection(final Socket clientSocket) {
		this.clientSocket = clientSocket;
		hasClient = true;
		isOpen = true;
		log = Logger.getLogger(getClass().getName());
		threadPool = ConnectionThreadPool.getInstance();
	}

	// -------------------methods-----------------------------------------------

	/**
	 * Handles a client by using a separate thread which processes the messages.
	 * @see @see org.accada.reader.msg.transport.Connection#handleClient()
	 */
	public void handleClient() {
		try {
			threadPool.execute(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection#close()
	 */
	public void close() {
		log.debug("Closing the TCP connection.");
		hasClient = false;
		isOpen = false;
		try {
			this.clientSocket.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection#send(String)
	 */
	public void send(String outMessage) {
		try {
			DataOutputStream out = new DataOutputStream(clientSocket
					.getOutputStream());
			//send message header
			out.writeUTF(outMessage.length() + MessagingConstants.EOH);
			//send message
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
	 * Sends a receiver handshake back.
	 * 
	 * @param handshake
	 */
	public void sendHandshake(TcpReceiverHandshakeMessage handshake) {
		try {
			if (handshake.isValid()) {
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(clientSocket.getOutputStream()));
            String message = handshake.getReceiverSignature() + handshake.getResponse()
               + handshake.getSpecVersionResponse() + handshake.getSenderFormatResponse()
               + handshake.getReceiverFormatResponse() + handshake.getAckNakResponse()
               + handshake.getReceiverReserved() + handshake.getTrailer();
				writer.write(message);
				writer.flush();
				//writer.close(); // do not close the connection after handshake!
				log.debug("Receiver handshake sent: " + message);
			} else {
				log.error("Could not send the receiver handshake. The handshake message is invalid.");
			}
		} catch (IOException e) {
			log.warn(e.getMessage());
			this.close();
		}
	}

	/**
	 * Reads new messages from stream. It creates new incoming message and
	 * notifies all added listeners.
	 */

	public void run() {
		String message = null;

		try {
			log.debug("New TCP connection thread running!");
			stream = new TcpMessageInputStream(clientSocket.getInputStream());

			/* Connection establishing. Read the handshake */
			log.debug("Tries to read handshaking from the new connection.");
			senderHandshake = (TcpSenderHandshakeMessage) stream
					.readHandshake();
			if (senderHandshake == null) {
				log.debug("Sender handshake not valid. Send \"NO\" back.");
				TcpReceiverHandshakeMessage receiverHandshake = new TcpReceiverHandshakeMessage();
				receiverHandshake.init(senderHandshake);
				receiverHandshake
						.setResponse(ReceiverHandshakeMessage.RESPONSE_NO);
				sendHandshake(receiverHandshake);
				close();
			} else {
				Clients client = Clients.getInstance();
				client.addClient(clientSocket.getRemoteSocketAddress()
						.toString(), this);
				log.debug("adding client "
						+ clientSocket.getRemoteSocketAddress().toString()
						+ " to the clients.");

            // Send back receiver handshake
            TcpReceiverHandshakeMessage receiverHandshake = new TcpReceiverHandshakeMessage();
            receiverHandshake.init(senderHandshake);
            receiverHandshake.setResponse(ReceiverHandshakeMessage.RESPONSE_OK);
            receiverHandshake.setAckNakResponse(!senderHandshake.getAckNakEnabled());
            sendHandshake(receiverHandshake);
			}

			/* Read the messages */
			while (isOpen && hasClient) {
				log.debug("trying to read from stream!");
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
		} catch (IOException e) {
			log.debug("Cannot read from TCP Connection. Reason: "
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
	 * 
	 * @param receiverHandshake
	 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection#setReceiverHandshake(ReceiverHandshakeMessage)
	 */
	public void setReceiverHandshake(
			TcpReceiverHandshakeMessage receiverHandshake) {
		this.receiverHandshake = receiverHandshake;
	}

	/**
	 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection#setSenderHandshake(SenderHandshakeMessage)
	 * @param senderHandshake
	 */
	public void setSenderHandshake(TcpSenderHandshakeMessage senderHandshake) {
		this.senderHandshake = senderHandshake;
	}

}
