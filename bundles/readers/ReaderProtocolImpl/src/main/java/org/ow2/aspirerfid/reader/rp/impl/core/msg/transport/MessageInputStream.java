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

/*
 * Created on 02.12.2005
 *
 */
package org.ow2.aspirerfid.reader.rp.impl.core.msg.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessageFormat;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.SenderHandshakeMessage;
import org.apache.log4j.Logger;

/**
 * An input Stream that streams messages.
 * 
 * The <code>MessageInputStream</code> is based on a simple InputStream which
 * it processes in order to extract messages. The processing of the InputStream
 * is done in the abstrace method <code>processMessage()</code> which should
 * be implemented by each concrete instance of this abstract class.
 * 
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 */
public abstract class MessageInputStream {

	/** The logger. */
	static protected Logger log;

	/** The bufferedReader. */
	protected BufferedReader reader = null;

	/** Message buffer. */
	protected StringBuffer messageBuffer = null;

	/** The message. */
	protected String message = null;

	/** Flag that indicates, whether a message has been read or not. */
	protected boolean messageCompleted;

	/**
	 * Creates a new buffered input stream to read data from the specified
	 * underlying input stream with a default 512-byte buffer size.
	 * 
	 * @param input
	 *            The input that has to be examined.
	 * 
	 */
	public MessageInputStream(InputStream input) {
		log = Logger.getLogger(getClass().getName());
		log.debug("MessageInputStream created");
		reader = new BufferedReader(new InputStreamReader(input));
	}

	// ---------------------------methods------------------------------------------

	/**
	 * Parses the handshake from the reader
	 */
	public abstract SenderHandshakeMessage readHandshake() throws IOException;

	/**
	 * Reads a plain message from the internal buffer.
	 * 
	 * @return The message.
	 */
	public String readMessage() throws IOException {
		this.message = null;
		this.messageCompleted = false;
		processMessage();
		return message;
	}

	/**
	 * Processes the incomming stream. The method reads from the stream and
	 * extracts the message
	 * 
	 */
	public abstract void processMessage() throws IOException;

	/**
	 * Close the associated stream.
	 */
	public void close() {
		try {
			this.reader.close();
		} catch (IOException e) {
			log.error(e);

		}
	}
}
