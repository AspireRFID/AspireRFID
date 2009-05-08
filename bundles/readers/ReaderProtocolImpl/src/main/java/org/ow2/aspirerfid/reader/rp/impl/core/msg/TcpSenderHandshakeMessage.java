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

/**
 * Class used for handshaking. The handshake parameters are internally handled
 * as <code>String</code>. Thus it's evident to validate the values to have
 * correct lengths and contents using the method <code>isValid()</code>.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class TcpSenderHandshakeMessage extends SenderHandshakeMessage {

	/** the total length of the handshake */
	public static final int LENGTH = 20;

	public static final int LENGTH_SPEC_VERSION_REQUEST = 2;

	public static final int LENGTH_ACK_NAK_REQUEST = 2;

	public static final int LENGTH_SENDER_RESERVED = 4;

	public static final int LENGTH_TRAILER = 4;

	public static final String SPEC_VERSION_REQUEST = "11";

	public static final String ACK_REQUEST = "AR";

	public static final String NAK_REQUEST = "NA";

	public static final String SENDER_RESERVED = "0000";

	public static final String TRAILER = "END1";

	private String trailer;

	private String senderReserved;

	/**
	 * The default constructor which initialises the handshake message.
	 */
	public TcpSenderHandshakeMessage() {
		this.init();
	}

	/**
	 * @return Returns the ackNakRequest.
	 */
	public String getAckNakRequest() {
		if (ackNakRequest) {
			return ACK_REQUEST;
		} else {
			return NAK_REQUEST;
		}
	}

	/**
	 * @param ackNakRequest
	 *            The ackNakRequest to set.
	 */
	public void setAckNakRequest(String ackNakRequest) {
		if (ackNakRequest.equals(ACK_REQUEST)) {
			this.ackNakRequest = true;
		} else {
			this.ackNakRequest = false;
		}
	}

	/**
	 * @return Returns the senderReserved.
	 */
	public String getSenderReserved() {
		return senderReserved;
	}

	/**
	 * @param senderReserved
	 *            The senderReserved to set.
	 */
	public void setSenderReserved(String senderReserved) {
		this.senderReserved = senderReserved;
	}

	/**
	 * @return Returns the trailer.
	 */
	public String getTrailer() {
		return trailer;
	}

	/**
	 * @param trailer
	 *            The trailer to set.
	 */
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	/**
	 * Validates the SenderHandshakeMessage
	 * 
	 * @return true if message is valid, false otherwise
	 */
	public boolean isValid() {
		boolean generalValid = super.isValid();

		/* check the lengths */
		if (senderReserved.length() != LENGTH_SENDER_RESERVED) {
			return false;
		}
		if (trailer.length() != LENGTH_TRAILER) {
			return false;
		}

		if (!trailer.equals(TRAILER)) {
			return false;
		}

		/*
		 * if we reach this point all the tcp specific validation is correct and
		 * we can return the result of the valid from the super class
		 */
		return generalValid;
	}

	/**
	 * Gets the format of incoming messages from the sender.
	 * 
	 * @return the <code>MessageFormat</code> or <code>null</code> if the
	 *         format is unknown.
	 */
	public MessageFormat getSenderMessageFormat() {
		if (getSenderFormatRequest().equals(SENDER_FORMAT_REQUEST_XML)) {
			return MessageFormat.XML;
		} else if (getSenderFormatRequest().equals(SENDER_FORMAT_REQUEST_TEXT)) {
			return MessageFormat.TEXT;
		} else {
			return null;
		}
	}

	/**
	 * Gets the format of outgoing messages to the receiver.
	 * 
	 * @return the <code>MessageFormat</code> or <code>null</code> if the
	 *         format is unknown.
	 */
	public MessageFormat getReceiverMessageFormat() {
		if (getReceiverFormatRequest().equals(RECEIVER_FORMAT_REQUEST_XML)) {
			return MessageFormat.XML;
		} else if (getReceiverFormatRequest().equals(
				RECEIVER_FORMAT_REQUEST_TEXT)) {
			return MessageFormat.TEXT;
		} else {
			return null;
		}
	}

	/**
	 * Does the host system wants acknowledgements or not?
	 * 
	 * @return <code>true</code> if ACK is desired, otherwise
	 *         <code>false</code>
	 */
	public boolean getAckEnabled() {
		return ackNakRequest;
	}

}
