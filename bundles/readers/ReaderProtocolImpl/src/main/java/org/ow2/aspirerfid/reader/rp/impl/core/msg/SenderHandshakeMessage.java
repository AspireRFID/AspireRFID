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
public abstract class SenderHandshakeMessage {

	public static final int LENGTH_SENDER_SIGNATURE = 4;

	public static final int LENGTH_SPEC_VERSION_REQUEST = 2;

	public static final int LENGTH_SENDER_FORMAT_REQUEST = 2;

	public static final int LENGTH_RECEIVER_FORMAT_REQUEST = 2;

	public static final String SENDER_SIGNATURE = "RPS1";

	public static final String SPEC_VERSION_REQUEST = "11";

	public static final String SENDER_FORMAT_REQUEST_TEXT = "T1";

	public static final String SENDER_FORMAT_REQUEST_XML = "X1";

	public static final String RECEIVER_FORMAT_REQUEST_TEXT = "T1";

	public static final String RECEIVER_FORMAT_REQUEST_XML = "X1";

	protected String senderSignature;

	protected String specVersionRequest;

	protected String senderFormatRequest;

	protected String receiverFormatRequest;

	protected boolean ackNakRequest;

	/**
	 * @return Returns the ackNakRequest.
	 */
	public abstract String getAckNakRequest();

	/**
	 * @param ackNakRequest
	 *            The ackNakRequest to set.
	 */
	public abstract void setAckNakRequest(String ackNakRequest);

	/**
	 * Does the host system wants acknowledgements or not?
	 * 
	 * @return <code>true</code> if ACK is desired, otherwise
	 *         <code>false</code>
	 */
	public boolean getAckNakEnabled() {
		return ackNakRequest;
	}

	/**
	 * @return Returns the receiverFormatRequest.
	 */
	public String getReceiverFormatRequest() {

		return receiverFormatRequest;
	}

	/**
	 * @param receiverFormatRequest
	 *            The receiverFormatRequest to set.
	 */
	public void setReceiverFormatRequest(String receiverFormatRequest) {
		this.receiverFormatRequest = receiverFormatRequest;
	}

	/**
	 * @return Returns the senderFormatRequest.
	 */
	public String getSenderFormatRequest() {
		return senderFormatRequest;
	}

	/**
	 * @param senderFormatRequest
	 *            The senderFormatRequest to set.
	 */
	public void setSenderFormatRequest(String senderFormatRequest) {
		this.senderFormatRequest = senderFormatRequest;
	}

	/**
	 * @return Returns the senderSignature.
	 */
	public String getSenderSignature() {
		return senderSignature;
	}

	/**
	 * @param senderSignature
	 *            The senderSignature to set.
	 */
	public void setSenderSignature(String senderSignature) {
		this.senderSignature = senderSignature;
	}

	/**
	 * @return Returns the specVersionRequest.
	 */
	public String getSpecVersionRequest() {
		return specVersionRequest;
	}

	/**
	 * @param specVersionRequest
	 *            The specVersionRequest to set.
	 */
	public void setSpecVersionRequest(String specVersionRequest) {
		this.specVersionRequest = specVersionRequest;
	}

	/**
	 * Validates the SenderHandshakeMessage
	 * 
	 * @return true if message is valid, false otherwise
	 */
	public boolean isValid() {
		/* check the lengths */
		if (senderSignature.length() != LENGTH_SENDER_SIGNATURE) {
			return false;
		}
		if (specVersionRequest.length() != LENGTH_SPEC_VERSION_REQUEST) {
			return false;
		}
		if (senderFormatRequest == null
				|| senderFormatRequest.length() != LENGTH_SENDER_FORMAT_REQUEST) {
			return false;
		}

		if (receiverFormatRequest == null) {
			/* if not set use the message type of the request */
			receiverFormatRequest = senderFormatRequest;
		}

		if (receiverFormatRequest.length() != LENGTH_RECEIVER_FORMAT_REQUEST) {
			return false;
		}

		/* validate contents */
		if (!senderSignature.equals(SENDER_SIGNATURE)) {
			return false;
		}
		if (!specVersionRequest.equals(SPEC_VERSION_REQUEST)) {
			return false;
		}
		if (!senderFormatRequest.equals(SENDER_FORMAT_REQUEST_TEXT)
				&& !senderFormatRequest.equals(SENDER_FORMAT_REQUEST_XML)) {
			return false;
		}
		if (!receiverFormatRequest.equals(RECEIVER_FORMAT_REQUEST_TEXT)
				&& !receiverFormatRequest.equals(RECEIVER_FORMAT_REQUEST_XML)) {
			return false;
		}

		return true;
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
	 * Initialises the handshake with the default values.
	 */
	public void init() {
		senderSignature = SENDER_SIGNATURE;
		specVersionRequest = SPEC_VERSION_REQUEST;
		ackNakRequest = true; /* default is true (see RP spec) */

	}

}
