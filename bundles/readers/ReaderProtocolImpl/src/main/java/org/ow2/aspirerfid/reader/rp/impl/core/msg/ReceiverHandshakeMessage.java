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
public abstract class ReceiverHandshakeMessage {

	public static final int LENGTH_RECEIVER_SIGNATURE = 4;

	public static final int LENGTH_RESPONSE = 2;

	public static final int LENGTH_SPEC_VERSION_RESPONSE = 2;

	public static final int LENGTH_SENDER_FORMAT_RESPONSE = 2;

	public static final int LENGTH_RECEIVER_FORMAT_RESPONSE = 2;

	public static final String RECEIVER_SIGNATURE = "RPR1";

	public static final String SPEC_VERSION_RESPONSE = "11";

	public static final String RESPONSE_NO = "NO";
   
   public static final String RESPONSE_OK = "OK";

	protected String receiverSignature;

	protected String response;

	protected String specVersionResponse;

	protected String senderFormatResponse;

	protected String receiverFormatResponse;

	protected boolean ackNakResponse;

	public ReceiverHandshakeMessage() {

	}

	/**
	 * @return Returns the ackNakResponse.
	 */
	public abstract String getAckNakResponse();

	/**
	 * @param ackNakResponse
	 *            The ackNakResponse to set.
	 */
	public abstract void setAckNakResponse(String ackNakResponse);

	/**
	 * Sets the flag wheter to acknowledge the acknowledgements are sent.
	 * 
	 * @param ackEnabled
	 */
	public void setAckNakResponse(boolean ackEnabled) {
		this.ackNakResponse = ackEnabled;
	}

	/**
	 * @return Returns the receiverFormatResponse.
	 */
	public String getReceiverFormatResponse() {
		return receiverFormatResponse;
	}

	/**
	 * @param receiverFormatResponse
	 *            The receiverFormatResponse to set.
	 */
	public void setReceiverFormatResponse(String receiverFormatResponse) {
		this.receiverFormatResponse = receiverFormatResponse;
	}

	/**
	 * @return Returns the receiverSignature.
	 */
	public String getReceiverSignature() {
		return receiverSignature;
	}

	/**
	 * @param receiverSignature
	 *            The receiverSignature to set.
	 */
	public void setReceiverSignature(String receiverSignature) {
		this.receiverSignature = receiverSignature;
	}

	/**
	 * @return Returns the response.
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            The response to set.
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * @return Returns the senderFormatResponse.
	 */
	public String getSenderFormatResponse() {
		return senderFormatResponse;
	}

	/**
	 * @param senderFormatResponse
	 *            The senderFormatResponse to set.
	 */
	public void setSenderFormatResponse(String senderFormatResponse) {
		this.senderFormatResponse = senderFormatResponse;
	}

	/**
	 * @return Returns the specVersionResponse.
	 */
	public String getSpecVersionResponse() {
		return specVersionResponse;
	}

	/**
	 * @param specVersionResponse
	 *            The specVersionResponse to set.
	 */
	public void setSpecVersionResponse(String specVersionResponse) {
		this.specVersionResponse = specVersionResponse;
	}

	/**
	 * Validates the ReceiverHandshakeMessage
	 * 
	 * @return true if message is valid, false otherwise
	 */
	public boolean isValid() {
		/* check the lengths */
		if (receiverSignature.length() != LENGTH_RECEIVER_SIGNATURE) {
			return false;
		}
		if (response.length() != LENGTH_RESPONSE) {
			return false;
		}
		if (specVersionResponse.length() != LENGTH_SPEC_VERSION_RESPONSE) {
			return false;
		}
		if (senderFormatResponse.length() != LENGTH_SENDER_FORMAT_RESPONSE) {
			return false;
		}
		if (receiverFormatResponse.length() != LENGTH_RECEIVER_FORMAT_RESPONSE) {
			return false;
		}

		/* validate the contents */
		if (!receiverSignature.equals(RECEIVER_SIGNATURE)) {
			return false;
		}
		if (!specVersionResponse.equals(SPEC_VERSION_RESPONSE)) {
			return false;
		}

		return true;
	}

	/**
	 * Initialises the <code>ReceiverHandshakeMessage</code> with the required
	 * default values. As the default values some conservative values are used
	 * (conservative means that for example all format value are set to NO).
	 */
	public void init() {
		setReceiverSignature(RECEIVER_SIGNATURE);
		setResponse(RESPONSE_NO);
		setSpecVersionResponse(SPEC_VERSION_RESPONSE);
		setSenderFormatResponse(RESPONSE_NO);
		setReceiverFormatResponse(RESPONSE_NO);
		setAckNakResponse(RESPONSE_NO);
	}

	/**
	 * Initialises the response handshake using the corresponding values from
	 * the request handshake.
	 * 
	 * @param handshake
	 *            The sender handshake to use for the initialisation of the
	 *            receiver handshake.
	 */
	public void init(SenderHandshakeMessage senderHandshake) {
		this.setAckNakResponse(senderHandshake.getAckNakEnabled());
		this.setReceiverFormatResponse(senderHandshake
				.getReceiverFormatRequest());
		this.setSenderFormatResponse(senderHandshake.getSenderFormatRequest());

		/*
		 * TODO: Hanshake noch validieren
		 */

		// this.setSpecVersionRespone(...);
	}

}
