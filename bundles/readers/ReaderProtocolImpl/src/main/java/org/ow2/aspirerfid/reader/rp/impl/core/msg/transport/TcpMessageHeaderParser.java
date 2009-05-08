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

public class TcpMessageHeaderParser {

	/** TCP protocol specific char that indicates a chunk. */
	private final static int NOT_LAST_CHUNK_INDICATOR = (int) '+';

	/** TCP protocol specific char that indicates the end of the message header. */
	private final static int END_OF_HEADER = (int) ':';

	public static TcpMessageHeader readTcpMessageHeader(BufferedReader reader) throws IOException {
		boolean isLastChunk = true;
		StringBuffer buffer = new StringBuffer();
		boolean headerDone = false;

		while (!headerDone) {
			int r = reader.read();
			switch (r) {
			case NOT_LAST_CHUNK_INDICATOR:
				isLastChunk = false;
				break;
			case END_OF_HEADER:
				headerDone = true;
				break;
			case -1:
				throw new ProtocolException(
						"Bad transport format. Premature end of message.");
			default:
				buffer.append((char) r);
			}
		}
		try {
			int bodyLength = Integer.parseInt(buffer.toString());
			return new TcpMessageHeader(bodyLength, isLastChunk);
		} catch (NumberFormatException e) {
			throw new ProtocolException(
					"Bad transport format. Could not properly read the message length in the message header.");
		}
	}
}