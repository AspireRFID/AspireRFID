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
 * Created on 12.02.2004
 */
package org.ow2.aspirerfid.reader.rp.impl.core.msg;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection;

/**
 * <code>IncomingMessage</code> holds connection and a message received through this connection.
 * The connection has to be stored in the incoming message so that the <code>ServiceDispatcher</code>
 * can send a response to the client if he is not registered. Thus, the first message that has to be
 * sent by a client is registerClient message with clientID as a parameter.
 * 
 * 
 * @author Dijana Micijevic, ETH Zurich Switzerland, Winter 2003/04
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 */
public class IncomingMessage  {

	private Connection conn_;
	private String msg_;
		

	/**
	 * Constructor.
	 */
    public IncomingMessage(Connection connection, String msg) {
		conn_ = connection;
        msg_ = msg;
    }
	
	
	/**
	 * Gets a client connection through which this message arrived.
	 * 
	 * @return connection through which a message arrived
	 */
	protected Connection getConnection() {
		return conn_;
	}

	/**
	 * Returnes a message which arrived.
	 * 
	 * @return message which arrived 
	 */
	protected String getMessage() {
		return msg_;
	}

	/**
	 * Sets a client connection
	 * @param connection client connection to set
	 */
	protected void setConnection(Connection connection) {
		conn_ = connection;
	}

	/**
	 * Sets a new arrived message.
	 * @param message message that arrived
	 */
	protected void setMessage(String message) {
		msg_ = message;
	}
	
	
	
	

}
