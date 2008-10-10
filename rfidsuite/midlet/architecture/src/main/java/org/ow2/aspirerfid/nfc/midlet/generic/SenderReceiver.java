/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.nfc.midlet.generic;

import java.util.Vector;

import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.BusinessToCommInterface;

/**
 * Class that defines the structure of a Sender / Receiver of messages. The
 * implementation of this class can be using the state pattern to describe each
 * state of the connection.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public abstract class SenderReceiver {

	/**
	 * Class that uses the sender receiver (caller.)
	 */
	private BusinessToCommInterface m_caller = null;

	/**
	 * List of tags that have been read.
	 */
	public Vector m_taglist = new Vector();

	/**
	 * Constructor that associates the caller class with this sender receiver.
	 * 
	 * @param caller
	 *            Caller class.
	 */
	protected SenderReceiver(BusinessToCommInterface caller) {
		this.m_caller = caller;
	}

	/**
	 * Returns the caller class.
	 * 
	 * @return Caller class
	 */
	protected BusinessToCommInterface getCallerClass() {
		return this.m_caller;
	}

	/**
	 * Receives a message.
	 * 
	 * @return Message received.
	 */
	public abstract String receiveMessage();

	/**
	 * Sends a message.
	 * 
	 * @param message
	 *            Message to be sent.
	 */
	public abstract void sendMessage(String message);

	/**
	 * Starts the sender. Defines all the parameters and elements necessaries to
	 * starts the application.
	 */
	public abstract void startSender();

	/**
	 * Stops the sender. Finishes the connections, close the streams, etc.
	 */
	public abstract void stopSender();
}
