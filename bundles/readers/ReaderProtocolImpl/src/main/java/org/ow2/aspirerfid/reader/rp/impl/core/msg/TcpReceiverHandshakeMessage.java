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
public class TcpReceiverHandshakeMessage extends ReceiverHandshakeMessage {

	/** the total length of the handshake */
	public static final int LENGTH = 20;

	public static final int LENGTH_ACK_NAK_RESPONSE = 2;

	public static final int LENGTH_RECEIVER_RESERVED = 2;

	public static final int LENGTH_TRAILER = 4;

	public static final String RESPONSE_OK = "OK";

	public static final String RESPONSE_NO = "NO";

	public static final String RECEIVER_RESERVED = "00";

	public static final String TRAILER = "END1";

	private String receiverReserved;

	private String trailer;

	public TcpReceiverHandshakeMessage() {
		this.init();
	}

	/**
	 * @return Returns the ackNakResponse.
	 */
	public String getAckNakResponse() {
		if (ackNakResponse) {
			return RESPONSE_OK;
		} else {
			return RESPONSE_NO;
		}
	}

	/**
	 * @param ackNakResponse
	 *            The ackNakResponse to set.
	 */
	public void setAckNakResponse(String ackNakResponse) {
		if (ackNakResponse.equals(RESPONSE_OK)) {
			this.ackNakResponse = true;
		} else {
			this.ackNakResponse = false;
		}
	}

	/**
	 * @return Returns the receiverReserved.
	 */
	public String getReceiverReserved() {
		return receiverReserved;
	}

	/**
	 * @param receiverReserved
	 *            The receiverReserved to set.
	 */
	public void setReceiverReserved(String receiverReserved) {
		this.receiverReserved = receiverReserved;
	}

	/**
	 * @return Returns the receiverSignature.
	 */

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
	 * Validates the ReceiverHandshakeMessage
	 * 
	 * @return true if message is valid, false otherwise
	 */
	public boolean isValid() {
		boolean generalValid = super.isValid();

		/* check the lengths */
		if (receiverReserved.length() != LENGTH_RECEIVER_RESERVED) {
			return false;
		}
		if (trailer.length() != LENGTH_TRAILER) {
			return false;
		}

		/* validate the contents */
		if (!response.equals(RESPONSE_OK) && !response.equals(RESPONSE_NO)) {
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
	 * Initialises the <code>ReceiverHandshakeMessage</code> with the required
	 * default values. As the default values some conservative values are used
	 * (conservative means that for example all format value are set to NO).
	 */
	public void init() {
		super.init();
		setReceiverReserved(RECEIVER_RESERVED);
		setTrailer(TRAILER);
	}

	/**
	 * Initialises the response handshake using the corresponding values from
	 * the request handshake.
	 * 
	 * @param handshake
	 *            The sender handshake to use for the initialisation of the
	 *            receiver handshake.
	 */
	public void init(TcpSenderHandshakeMessage handshake) {
		super.init(handshake);
	}

}
