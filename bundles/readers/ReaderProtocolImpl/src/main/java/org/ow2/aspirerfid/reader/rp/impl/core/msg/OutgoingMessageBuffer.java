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
 *
 */
package org.ow2.aspirerfid.reader.rp.impl.core.msg;

import java.util.Vector;
import org.apache.log4j.Logger;

/**
 * <code>OutgoingMessageBuffer</code> is a buffer consisting of messages that
 * has to be sent to a client (called "host" in the EPC Reader Protocol). Each
 * client has its own buffer. Thus, messages in one buffer are for only one
 * client. It provides methods to put and get a message, to give size
 * information and to synchronize a <code>ServiceDispatcher</code> thread to
 * wait until the buffer is empty.
 * 
 * @author Dijana Micijevic, ETH Zurich Switzerland, Winter 2003/04
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class OutgoingMessageBuffer {
	// --------------------------------fields--------------------------------
	/** The elements of the buffer. */
	private Vector elements;

	/** The logger. */
	private static Logger logger_;

	/**
	 * Constructs a <code>OutgoingMessageBuffer</code> object.
	 */
	public OutgoingMessageBuffer() {
		elements = new Vector();
		logger_ = Logger.getLogger(getClass().getName());
	}

	// ---------------------------methods--------------------------------------
	/**
	 * Returns the size of this buffer.
	 * @return The size of the buffer.
	 */
	public int size() {
		return elements.size();
	}

	/**
	 * 
	 * Blocks <code>ServiceDispatcher</code> until the buffer is empty.
	 * 
	 */
	public synchronized void waitUntilEmpty() {
		while (this.size() != 0) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		notifyAll();
	}

	/**
	 * Adds an element to the end of the buffer.
	 * 
	 * @param element
	 *            the element to be added
	 */
	public synchronized void put(Object element) {
		logger_.debug("Message put into buffer!");
		elements.addElement(element);
		notifyAll();
	}

	/**
	 * Removes the first element from the buffer. If the buffer is empty, the
	 * calling thread will be blocked until another thread adds an element to
	 * the buffer.
	 * 
	 * @return the removed element
	 */
	public synchronized Object get() {
		logger_.debug("Message removed from buffer!");
		while (this.elements.isEmpty()) {
			try {
				logger_
						.info("Outgoing message client is waiting for next message...");
				wait();
			} catch (InterruptedException e) {
				logger_.debug("is woken up through interrupt!");
				return null;
			}
		}
		notifyAll();

		Object element = elements.firstElement();
		elements.removeElement(element);
		notifyAll();
		logger_.info("Read next message...");
		return element;
	}

	/** Cleans this buffer. */
	public synchronized void clean() {
		this.elements.clear();
		notifyAll();
	}
}
