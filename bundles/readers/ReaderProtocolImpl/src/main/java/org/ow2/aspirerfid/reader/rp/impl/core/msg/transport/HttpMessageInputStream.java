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

import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.HttpSenderHandshakeMessage;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.SenderHandshakeMessage;

/**
 * The <code>HttpMessageInputStream</code> reads a message and the hanshaking
 * from a TcpConnection.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class HttpMessageInputStream extends MessageInputStream {

	/** The handshake parameters. */
	private HttpSenderHandshakeMessage handshakeMsg = new HttpSenderHandshakeMessage();

	/**
	 * Creates a new buffered input stream to read data from the specified
	 * underlying input stream with a default 512-byte buffer size.
	 * 
	 * @param input
	 *            The input that has to be examined.
	 * 
	 */
	public HttpMessageInputStream(InputStream input) {
		super(input);
	}

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
	public SenderHandshakeMessage readHandshake() throws IOException {
		handshakeMsg = HttpMessageHeaderParser.readHandshake(reader);
		return handshakeMsg;
	}
	
	/**
	 * Reads a message from the HTTP stream. HTTP/1.1 requires to specify a
	 * content length in the header. So we read as long we reach the value of
	 * the content length.
	 * 
	 * @throws IOException
	 */
	public void processMessage() throws IOException {
		int contentLength = handshakeMsg.getContentLength();
		int offset = 0;
		char[] buffer = new char[contentLength];

		while (!messageCompleted) {
			int i = reader.read(buffer, offset, contentLength);

			if (i == -1) {
				// permature EOF
				throw new IOException(
						"Permature end of file. Could not read the whole HTTP message");
			} else {
				offset += i;
				if (i >= contentLength) {
					messageCompleted = true;
				}
			}
		}
		this.message = new String(buffer);
	}
}
