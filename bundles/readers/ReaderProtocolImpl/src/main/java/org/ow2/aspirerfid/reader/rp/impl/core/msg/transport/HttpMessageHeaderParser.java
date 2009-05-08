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

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.HttpSenderHandshakeMessage;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.SenderHandshakeMessage;

public class HttpMessageHeaderParser {

	/**
	 * Reads the handshake from a HTTP connection.
	 * @param reader 
	 * 
	 * @return the <code>SenderHandshakeMessage</code> if handshake could be
	 *         sucessfully read from the connection and is valid,
	 *         <code>null</code> otherwise. A handshake in the context of a
	 *         HTTP connection correspons to the HTTP header.
	 * @throws IOException	if handshake couldn't be read from the
	 *             input stream.
	 */
	public static HttpSenderHandshakeMessage readHandshake(BufferedReader reader) throws IOException {
		try {
			/* Read first line and parse it */
			String firstLine = reader.readLine();
			HttpSenderHandshakeMessage handshakeMsg = new HttpSenderHandshakeMessage();
			if (firstLine.startsWith(MessagingConstants.POST)) {
				handshakeMsg.setMethod(MessagingConstants.POST);
			} else {
				handshakeMsg.setMethod(null);
			}

			int httpIndex = firstLine.indexOf("HTTP/");
			if (httpIndex != -1) {
				handshakeMsg.setHttpVersion(firstLine.substring(httpIndex));
			} else {
				throw new ProtocolException("Bad message format.");
			}

			/* Parse the HTTP header */
			String line = reader.readLine();
			while (!line.equals("")) {
				parseHeaderLine(line, handshakeMsg);
				line = reader.readLine();
			}

			if (!handshakeMsg.isValid()) {
				return null;
			} else {
				return handshakeMsg;
			}

		} catch (IOException e) {
			throw new ProtocolException("Bad message format.");
		}

	}
	
	/**
	 * Parse a header line and adds the parameter name and the parameter value
	 * into the <code>SenderHandshakeMessage</code> object.
	 * 
	 * @param headerLine
	 * @param handshakeMsg 
	 * @throws ProtocolException
	 */
	private static void parseHeaderLine(String headerLine, HttpSenderHandshakeMessage handshakeMsg) throws ProtocolException {
		try {
			StringTokenizer tokens = new StringTokenizer(headerLine, ":");
			String key = tokens.nextToken();
			String value = tokens.nextToken().trim();

			if (key.equals(MessagingConstants.RP_SENDER_SIGNATURE)) {
				handshakeMsg.setSenderSignature(value);
			} else if (key.equals(MessagingConstants.RP_SPEC_VERSION_REQUEST)) {
				handshakeMsg.setSpecVersionRequest(value);
			} else if (key.equals(MessagingConstants.RP_RESPONSE_CONTENT_TYPE)) {
				handshakeMsg.setReceiverFormatRequest(value);
			} else if (key.equals(MessagingConstants.RP_RESPONSE_ACKNAK)) {
				handshakeMsg.setAckNakRequest(value);
			} else if (key.equals(MessagingConstants.HTTP_CONTENT_TYPE)) {
				if (value.equals(MessagingConstants.HTTP_CONTENT_TYPE_TEXT)) {
					handshakeMsg
							.setSenderFormatRequest(SenderHandshakeMessage.SENDER_FORMAT_REQUEST_TEXT);
				} else if (value
						.equals(MessagingConstants.HTTP_CONTENT_TYPE_XML)) {
					handshakeMsg
							.setSenderFormatRequest(SenderHandshakeMessage.SENDER_FORMAT_REQUEST_XML);
				} else {
					throw new ProtocolException("Message format not supported.");
				}
			} else if (key.equals(MessagingConstants.HTTP_CONTENT_LENGTH)) {
				handshakeMsg.setContentLength(value);
			} else if (key.equals(MessagingConstants.HTTP_HOST)) {
				handshakeMsg.setHost(value);
			} else {
				/*
				 * add additional parameters or vendor-specific parameters to a
				 * parameter map
				 */
				if (key != null && !key.equals("")) {
					handshakeMsg.addHeaderField(key, value);
				}
			}
		} catch (NoSuchElementException e) {
			throw new ProtocolException("Wrong parameter format.");
		}
	}
}
