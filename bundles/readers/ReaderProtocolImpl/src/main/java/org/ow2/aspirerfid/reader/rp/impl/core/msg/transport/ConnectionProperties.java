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

import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessageFormat;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.ReceiverHandshakeMessage;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.SenderHandshakeMessage;

/**
 * Wrapper class for properties related with a connection. The connection
 * properties are determined during a handshaking process. Properties can be:
 * <ul>
 * <li>Message format of the incoming sender message</li>
 * <li>Message format of the outgoint receiver message</li>
 * <li>Does the host system want acknowledgements?</li>
 * <li>etc.</li>
 * </ul>
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class ConnectionProperties {
	/** The format of incoming messages. */
	private MessageFormat senderMsgFormat_;

	/** The format of outgoing messages. */
	private MessageFormat receiverMsgFormat_;

	/** Should commands be acknowledged? */
	private boolean ackNakEnabled = false;

	/** The handshake associated with the connection. */
	private ReceiverHandshakeMessage receiverHandshake;

	/** The handshake associated with the connection. */
	private SenderHandshakeMessage senderHandshake;

	/**
	 * Gets the handshake of the sender.
	 * 
	 * @return The handshake for requests.
	 */
	public SenderHandshakeMessage getSenderHandshake() {
		return senderHandshake;
	}

	/**
	 * Sets the handshake of the sender.
	 * 
	 * @param senderHandshake
	 *            The handshake for requests
	 */
	public void setSenderHandshake(final SenderHandshakeMessage senderHandshake) {
		this.senderHandshake = senderHandshake;
	}

	/**
	 * Returnes format of the sender message.
	 * 
	 * @return format of the message which arrived
	 */
	public MessageFormat getSenderMessageFormat() {
		return senderMsgFormat_;
	}

	/**
	 * Gets the format of message sent back to the receiver.
	 * 
	 * @return format of the message which the receiver expects.
	 */
	public MessageFormat getReceiverMessageFormat() {
		return receiverMsgFormat_;
	}

	/**
	 * Sets format of incoming messages.
	 * 
	 * @param mf
	 *            format of incoming sender messages
	 */
	public void setSenderMessageFormat(final MessageFormat mf) {
		senderMsgFormat_ = mf;
	}

	/**
	 * Sets format of outgoing messages.
	 * 
	 * @param mf
	 *            format of outgoing messages
	 */
	public void setReceiverMessageFormat(final MessageFormat mf) {
		receiverMsgFormat_ = mf;
	}

	/**
	 * @return Returns the ackNakEnabled.
	 */
	public boolean isAckNakEnabled() {
		return ackNakEnabled;
	}

	/**
	 * @param ackNakEnabled
	 *            The ackNakEnabled to set.
	 */
	public void setAckNakEnabled(final boolean ackNakEnabled) {
		this.ackNakEnabled = ackNakEnabled;
	}

	/**
	 * Gets the receiver handshake.
	 * 
	 * @return The handshake used for replies.
	 */
	public ReceiverHandshakeMessage getReceiverHandshake() {
		return receiverHandshake;
	}

	/**
	 * Sets the receiver handshake.
	 * 
	 * @param receiverHandshake
	 *            The handshake used for replies.
	 */
	public void setReceiverHandshake(final ReceiverHandshakeMessage receiverHandshake) {
		this.receiverHandshake = receiverHandshake;
	}

}
