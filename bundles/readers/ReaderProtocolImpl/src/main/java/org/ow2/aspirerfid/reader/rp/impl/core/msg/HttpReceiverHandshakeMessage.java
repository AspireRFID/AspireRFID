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
 * Class used for handshaking. The handshake parameters are internally
 * handled as <code>String</code>. Thus it's evident to validate the values
 * to have correct lengths and contents using the method <code>isValid()</code>.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 *
 */
public class HttpReceiverHandshakeMessage extends ReceiverHandshakeMessage {

	public static final String RESPONSE_YES = "yes";
	public static final String RESPONSE_NO = "no";
	
	public HttpReceiverHandshakeMessage() {
		super();
		this.init();
	}
	
	/**
	 * @return Returns the receiverFormatResponse.
	 */
	public String getReceiverFormatResponse() {
		if (receiverFormatResponse.equals(SenderHandshakeMessage.RECEIVER_FORMAT_REQUEST_XML)
				|| receiverFormatResponse.equals(SenderHandshakeMessage.RECEIVER_FORMAT_REQUEST_TEXT)) {
			return RESPONSE_YES;
		} else {
			return RESPONSE_NO;
		}
	}
	
	/**
	 * @return Returns the senderFormatResponse.
	 */
	public String getSenderFormatResponse() {
		if (senderFormatResponse.equals(SenderHandshakeMessage.RECEIVER_FORMAT_REQUEST_XML)
				|| senderFormatResponse.equals(SenderHandshakeMessage.RECEIVER_FORMAT_REQUEST_TEXT)) {
			return RESPONSE_YES;
		} else {
			return RESPONSE_NO;
		}
	}
	
	/**
	 * @return Returns the specVersionResponse.
	 */
	public String getSpecVersionResponse() {
		if (this.specVersionResponse.equals(SPEC_VERSION_RESPONSE)) {
			return RESPONSE_YES;
		} else {
			return RESPONSE_NO;
		}
	}
	
	/**
	 * @return Returns the ackNakResponse.
	 */
	public String getAckNakResponse() {
		if (ackNakResponse) {
			return RESPONSE_YES;
		} else {
			return RESPONSE_NO;
		}
	}
	/**
	 * @param ackNakResponse The ackNakResponse to set.
	 */
	public void setAckNakResponse(String ackNakResponse) {
		if (ackNakResponse.toLowerCase().equals(RESPONSE_YES)) {
			this.ackNakResponse = true;
		} else {
			this.ackNakResponse = false;
		}
	}
	

	/**
	 * Initialises the <code>ReceiverHandshakeMessage</code> with the required 
	 * default values. As the default values some conservative values are used
	 * (conservative means that for example all format value are set to NO). 
	 */
	public void init() {
		super.init();
	}
	
	/**
	 * Gets the <code>Content-Type</code> used in the HTTP header. 
	 * @return the MIME type for the message format used in the response.
	 */
	public String getHttpContentType() {
		if (receiverFormatResponse.equals(SenderHandshakeMessage.RECEIVER_FORMAT_REQUEST_XML)) {
			return MessagingConstants.HTTP_CONTENT_TYPE_XML;
		} else if (receiverFormatResponse.equals(SenderHandshakeMessage.RECEIVER_FORMAT_REQUEST_TEXT)) {
			return MessagingConstants.HTTP_CONTENT_TYPE_TEXT;
		} else {
			return null;
		}	
		
	}
	
	/**
	 * Initialises the response handshake using the corresponding values
	 * from the request handshake.
	 * @param handshake The sender handshake to use for the initialisation of
	 * the receiver handshake.
	 */
	public void init(HttpSenderHandshakeMessage handshake) {
		super.init(handshake);
	}
	
	

}
