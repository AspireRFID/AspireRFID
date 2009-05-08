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

/*
 * Created on 12.02.2004
 *
 */
package org.ow2.aspirerfid.reader.rp.impl.core.msg.transport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.IncomingMessage;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.IncomingMessageListener;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessageFormat;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.ReceiverHandshakeMessage;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.SenderHandshakeMessage;

/**
 * <code>Connection</code> represents a connection to the client.
 * 
 * @author Dijana Micijevic, ETH Zurich Switzerland, Winter 2003/04
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 */

public abstract class Connection {

	/** A list of listeners that listen to new messages. */
	protected List listeners_;

	/** Flag which states if there is a pending close request on the connection. */
	protected boolean closeRequest = false;

	/** The sender handshake used by this connection. */
	protected SenderHandshakeMessage senderHandshake = null;

	/** The receiver handshake used by this connection. */
	protected ReceiverHandshakeMessage receiverHandshake = null;

	// ================================= methods
	// =======================================

	/**
	 * Adds a listener of incoming messages for this connection.
	 */
	public void addIncomingMessageListener(IncomingMessageListener listener) {
		if (listener != null) {
			if (listeners_ == null) {
				listeners_ = new ArrayList();
			}
			listeners_.add(listener);
		}
	}

	/**
	 * Removes a listener of incoming messages for this connection.
	 */
	protected void removeIncomingMessageListener(
			IncomingMessageListener listener) {
		if (listener != null) {
			listeners_.remove(listener);
		}
	}

	/**
	 * Notifies all added listeners that there is a new message.
	 * 
	 * @param msg
	 *            a new message received
	 */
	protected void notifyListener(IncomingMessage msg) {
		Iterator iter = listeners_.iterator();
		while (iter.hasNext()) {
			IncomingMessageListener element = (IncomingMessageListener) iter
					.next();
			synchronized (element) {
				element.messageReceived(msg);
			}
		}
	}

	/**
	 * Closes the connection
	 * 
	 */
	abstract public void close();

	/**
	 * Sends a message to the specified receiver.
	 * 
	 * @param outMessage
	 *            the message to send.
	 */
	abstract public void send(String outMessage);

	/**
	 * Requests for closing of the connection. It means that the connection has
	 * to close itself after having sent the next message (which should then be
	 * the "last" message, e.g. an ACK of a goodbye command).
	 */
	public void requestClose() {
		closeRequest = true;
	}

	/**
	 * @return Returns the receiverHandshake.
	 */
	public ReceiverHandshakeMessage getReceiverHandshake() {
		return receiverHandshake;
	}

	/**
	 * @param receiverHandshake
	 *            The receiverHandshake to set.
	 */
	public void setReceiverHandshake(ReceiverHandshakeMessage receiverHandshake) {
		this.receiverHandshake = receiverHandshake;
	}

	/**
	 * @return Returns the senderHandshake.
	 */
	public SenderHandshakeMessage getSenderHandshake() {
		return senderHandshake;
	}

	/**
	 * @param senderHandshake
	 *            The senderHandshake to set.
	 */
	public void setSenderHandshake(SenderHandshakeMessage senderHandshake) {
		this.senderHandshake = senderHandshake;
	}

	/**
	 * Returns format of the sender message
	 * 
	 * @return format of the message which arrived or <code>null</code> if not
	 *         defined.
	 */
	public MessageFormat getSenderMessageFormat() {
		if (senderHandshake != null) {
			return senderHandshake.getSenderMessageFormat();
		} else {
			return null;
		}
	}

	/**
	 * Gets the format of message sent back to the receiver
	 * 
	 * @return format of the message which the receiver expects or
	 *         <code>null</code> if not defined.
	 */
	public MessageFormat getReceiverMessageFormat() {
		if (senderHandshake != null) {
			return senderHandshake.getReceiverMessageFormat();
		} else {
			return null;
		}
	}

	/**
	 * @return Returns the ackNakEnabled or <code>false</code> if handshake is
	 *         not defined.
	 */
	public boolean isAckNakEnabled() {
		if (senderHandshake != null) {
			return senderHandshake.getAckNakEnabled();
		} else {
			return false;
		}
	}

	/**
	 * Handles a client on that connection.
	 */
	public abstract void handleClient();

}
