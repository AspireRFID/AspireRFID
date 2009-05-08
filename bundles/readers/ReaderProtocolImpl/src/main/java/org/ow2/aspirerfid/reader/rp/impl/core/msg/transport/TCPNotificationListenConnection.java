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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessageLayer;
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
 * 
 * @author Dijana Micijevic, ETH Zurich Switzerland, Winter 2003/04
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 */

public class TCPNotificationListenConnection extends Connection implements Runnable{
	
	//-------------------fields-----------------------------------------------
	
	
	/** The logger. */
	private static Logger log;
	/** Flag that indicates if the connection is still open. */
	private static boolean isOpen;
	/** Flag that indicates if there is still a client stream. */
	private boolean hasClient;
	
	//private Thread clientThread = null;
	/** The <code>ServerSocket</code> */
	private ServerSocket serverSocket = null;
	
	/** The client socket - the socket to the client (host) */
	private Socket clientSocket;
	
	private MessageInputStream stream;
	
	/** the thread pool for all connections */
	private ConnectionThreadPool threadPool = null;
	
	/** the output stream to the host */
	private DataOutputStream out = null;
	

	//-------------------constructor-----------------------------------------
	
	/**
	 * Creates a new instance of <code>TCPConnection</code>.
	 * It creates and starts a thread for a client.
	 */
	public TCPNotificationListenConnection(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
		hasClient = true;
		log = Logger.getLogger(getClass().getName()); 
		threadPool = ConnectionThreadPool.getInstance();
	}
	
	
	//-------------------methods-----------------------------------------------
	

	/**
	 * Handles a client by using a separate thread which processes the messages.
	 */
	public void handleClient() {
		try {
			threadPool.execute(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see lm.messaging.Connection#close()
	 */
	public void close() {
		log.debug("Closing the TCP connection.");
		hasClient=false;
		isOpen=false;
		try{
			this.serverSocket.close();			
		}
		catch (IOException e){
			log.error(e.getMessage());
		}
	}


	/* (non-Javadoc)
	 * @see lm.messaging.Connection#send()
	 */
	public void send(String outMessage){
		try{
			/* Because we are in LISTEN mode we have to wait
			 * until the host opens the connection for the first time. As soon the
			 * connection is opened we begin the sending process.
			 */
			if (!isOpen) {
				log.debug("TCP Notification connection is waiting in listen mode.");
				serverSocket.setSoTimeout(MessageLayer.getNotificationListenTimeout());
				clientSocket = serverSocket.accept(); /* blocking! */
				out = new DataOutputStream(clientSocket.getOutputStream());
				isOpen = true;
				//TODO: Was f�r ein Handshake soll gesendet werden? Sender oder Receiver Handshake??
				sendHandshake((TcpReceiverHandshakeMessage)receiverHandshake);
			}
			out.writeUTF(outMessage);
			out.flush();
			if (this.closeRequest) {
				close();
			}
		}
		catch (SocketTimeoutException e) {
			log.warn("Could not deliver the notification message because host did not open it. Throws the notification message away...bye bye!");
			//this.close();
		}
		catch (IOException e){
			log.warn(e.getMessage());
			this.close();
		}
	}
	
	/**
	 * Sends a receiver handshake back.
	 * @param handshake
	 */
	public void sendHandshake(TcpReceiverHandshakeMessage handshake)  {
		try {
			
			if (handshake.isValid()) {
				out.writeUTF(handshake.getReceiverSignature());
				out.writeUTF(handshake.getResponse());
				out.writeUTF(handshake.getSpecVersionResponse());
				out.writeUTF(handshake.getSenderFormatResponse());
				out.writeUTF(handshake.getReceiverFormatResponse());
				out.writeUTF(handshake.getAckNakResponse());
				out.writeUTF(handshake.getReceiverReserved());
				out.writeUTF(handshake.getTrailer());
				out.flush();
				log.debug("Receiver handshake sent.");
			} else {
				log.error("Could not send the receiver handshake. The handshake message is invalid.");
			}
		} catch (IOException e) {
			log.warn(e.getMessage());
			this.close();
		}
	}
	
	public void run() {
		/* nothing to do in this thread, the OutgoingMessageClient does the sending job */
	}
	
	public void setReceiverHandshake(TcpReceiverHandshakeMessage receiverHandshake) {
		this.receiverHandshake = receiverHandshake;
	}
	
	public void setSenderHandshake(TcpSenderHandshakeMessage senderHandshake) {
		this.senderHandshake = senderHandshake;
	}

}
