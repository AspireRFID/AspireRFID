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

import org.ow2.aspirerfid.reader.rp.impl.core.msg.SenderHandshakeMessage;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.TcpSenderHandshakeMessage;

/**
 * The <code>TcpMessageInputStream</code> reads a message and the hanshaking
 * from a TcpConnection.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class TcpMessageInputStream extends MessageInputStream {

	/**
	 * Creates a new buffered input stream to read data from the specified
	 * underlying input stream with a default 512-byte buffer size.
	 * 
	 * @param input
	 *            The input that has to be examined.
	 * 
	 */
	public TcpMessageInputStream(InputStream input) {
		super(input);
	}

	/**
	 * Reads the handshake from a TCP connection.
	 * 
	 * @return the <code>SenderHandshakeMessage</code> if handshake could be
	 *         sucessfully read from the connection and is valid,
	 *         <code>null</code> otherwise.
	 * @throws IOException	if handshake couldn't be read from the
	 *             input stream.
	 */
	public SenderHandshakeMessage readHandshake() throws IOException {
		boolean handshakeComplete = false;
		int offset = 0;
		char[] buffer = new char[TcpSenderHandshakeMessage.LENGTH];
		while (!handshakeComplete) {
			int r = reader.read(buffer, offset,
					TcpSenderHandshakeMessage.LENGTH - offset);
			offset += r;
			if (r == -1 && offset != TcpSenderHandshakeMessage.LENGTH) {
				return null;
			} else if (r != -1 && offset == TcpSenderHandshakeMessage.LENGTH) {
				handshakeComplete = true;
			}
		}

		// read the handshake message
		TcpSenderHandshakeMessage handshakeMsg = new TcpSenderHandshakeMessage();
		handshakeMsg.setSenderSignature(new String(buffer, 0, 4));
		handshakeMsg.setSpecVersionRequest(new String(buffer, 4, 2));
		handshakeMsg.setSenderFormatRequest(new String(buffer, 6, 2));
		handshakeMsg.setReceiverFormatRequest(new String(buffer, 8, 2));
		handshakeMsg.setAckNakRequest(new String(buffer, 10, 2));
		handshakeMsg.setSenderReserved(new String(buffer, 12, 4));
		handshakeMsg.setTrailer(new String(buffer, 16, 4));
		if (!handshakeMsg.isValid()) {
			return null;
		} else {
			return handshakeMsg;
		}

	}

	/**
	 * Reads a message from the TCP stream. The TCP protocol specified in Reader
	 * Protocol Proposal from March, 10 2006 is required to properly read the
	 * message payloads. A message can be splitted into several chunks.
	 * 
	 * @throws IOException
	 */
	public void processMessage() throws IOException {
		StringBuffer msgBuffer = new StringBuffer();
		while (!messageCompleted) {
			TcpMessageHeader header = TcpMessageHeaderParser.readTcpMessageHeader(reader);
			msgBuffer.append(this.read(header.getLength()));
			if (header.getIsLastChunk()) {
				// it's the last chunk, message is complete
				messageCompleted = true;
			}
		}

		this.message = msgBuffer.toString();
	}

	/**
	 * Reads <code>length</code> bytes from the TCP stream.
	 * 
	 * @param length
	 *            The number of bytes to read from the stream.
	 * @return the message chunk as a string.
	 * @throws IOException
	 *             if message is shorter than the indicated length.
	 */
	private String read(int length) throws IOException {

		boolean chunkCompleted = false;
		int offset = 0;
		char[] buffer = new char[length];

		while (!chunkCompleted) {
			int i = reader.read(buffer, offset, length);

			if (i == -1) {
				// permature EOF
				throw new IOException(
						"Permature end of file. Could not read the whole TCP message");
			} else {
				offset += i;
				if (i >= length) {
					chunkCompleted = true;
				}
			}
		}
		return new String(buffer);
	}

}
