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

import org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.Connection;
import org.apache.log4j.Logger;

/**>code>OutgoingMessageClient</code> is a thread responsible to get a message
 * from the <code>OutgoingMessageBuffer</code> and send it to the client using
 * a corresponding * <code>Connection</code>.<br>
 * There is one <code>OutgoingMessageClient</code> per client. It waits if
 * there are no messages in the buffer. If there is a message available the
 * thread is woken by the <code>OutgoingMessageBuffer</code> and gets the
 * message. Calling the <code>send()</code> method of the
 * <code>Connection</code> implementation the message is sent to the client.
 * 
 * @author Dijana Micijevic, ETH Zurich Switzerland, Winter 2003/04
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class OutgoingMessageClient implements Runnable {
	
	/** The connection. */
	private Connection connection_;

	/** The outgoing buffer. */
	private OutgoingMessageBuffer buffer_;

	/** The dispatcher thread. */
	private Thread outgoingMsgClient_;

	/** indicates if the thread shall be suspended. */
	private boolean suspendThread = true;

	/** The logger. */
	private static Logger logger_;

	/** indicates if the thread is running. */
	private boolean isRunning = false;

	// --------------------------constructors-------------------------------------
	/**
	 * Constructs a new instance of <code>OutgoingMessageClient</code>.
	 */
	public OutgoingMessageClient(Connection connection,
			OutgoingMessageBuffer buffer) {
		super();
		connection_ = connection;
		logger_ = Logger.getLogger(getClass().getName());
		this.buffer_ = buffer;
	}

	// ----------------------------------methods--------------------------------------
	/**
	 * Gets messages and sends them to the client.
	 * 
	 */
	public void run() {
		while (isRunning) {
			synchronized (this) {
				while (suspendThread) {
					logger_.info("Dispatching suspended...");
					try {
						wait();
					} catch (Exception e) {
						logger_.error(e);
					}
				}
			}
			String msg = (String) buffer_.get();
			if (msg != null) {
				connection_.send(msg);
				logger_.info("Sending message to client.");
				logger_.debug("message:\n" + msg);
			}
		}
		// ServiceDispatcher.getInstance().isShutdown(this);
		logger_.info("OutgoingMessageClient is shut down...");
	}

	/**
	 * Starts an instance of OutgoingMessageClient thread.
	 * 
	 */
	public void start() {
		this.outgoingMsgClient_ = new Thread(this);
		logger_.debug("Starting OutgoingMessageClient...");
		this.isRunning = true;
		this.outgoingMsgClient_.start();
	}

	/**
	 * Suspends the OutgoingMessageClient thread.
	 * 
	 */
	public synchronized void suspendDispatching() {
		this.suspendThread = true;
	}

	/**
	 * Resumes dispatching of the outgoing messages.
	 */
	public synchronized void resumeDispatching() {
		this.suspendThread = false;
		logger_.info("Dispatching resumed...");
		notify();
	}

	/**
	 * Stops the OutgoingMessageClient thread.
	 * 
	 */
	public synchronized void stopDispatching() {
		logger_.debug("stop dispatching requested...");
		this.isRunning = false;
		this.outgoingMsgClient_.interrupt();
	}
}
