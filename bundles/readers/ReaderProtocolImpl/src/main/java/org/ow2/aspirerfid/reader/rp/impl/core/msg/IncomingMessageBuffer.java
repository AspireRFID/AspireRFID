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

import java.util.Vector;

import org.apache.log4j.Logger;


/**
 * <code>IncomingMessageBuffer</code> is a buffer that stores <code>IncomingMessage</code>
 * It is added to each client connection as a listener, so that it can be notified
 * if there is a new message.
 * 
 * @author Dijana Micijevic, ETH Zurich Switzerland, Winter 2003/04
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 */

public class IncomingMessageBuffer implements IncomingMessageListener {

	//============================= fields ==========================================
	
	/** the logger */
	 private static Logger log ;
	 /** instance of incoming message buffer */
	 private static IncomingMessageBuffer messageBuffer;
	/** The elements of the buffer. */
	private Vector elements;


 
	//================================= constructors =====================================
	
	/**
	 * Constructs an IncomingMessageBuffer object.
	 */
	private IncomingMessageBuffer()
	{
		elements = new Vector();
		log = Logger.getLogger(getClass().getName());
	}

	/**
	 * Creates the single Instance of a <code>IncomingMessageBuffer</code>.
	 */
	public static IncomingMessageBuffer getInstance(){
		if (messageBuffer==null){
			messageBuffer = new IncomingMessageBuffer();
		}
		return messageBuffer;
	}

	//=================================== methods =======================================
	
	/**
	 * Gets the size of this buffer.
	 * @return the size of this buffer.
	 */
	public int size()
	{
		return elements.size();
	}

	/**
	 * Adds an element to the end of this buffer. 
	 * 
	 * @param element the element to be added
	 */
	public synchronized void put( Object element ){	
		log.debug("Message put into buffer!");
		elements.addElement( element );
		notifyAll();
	}

	/**
	 * Removes the first element from the buffer. 
	 * If the buffer is empty, the
	 * calling thread will be blocked until another thread adds an element to
	 * the buffer.
	 * @return the first element in the buffer
	 */
	public synchronized Object get(){
		log.debug("Message removed from buffer!");
		while (this.elements.isEmpty()) {
		   try {
		   		log.info("Dispatcher is waiting for next message...");
			   	wait();
		   } 
		   catch (InterruptedException e) { }
	   	}
	   	notifyAll();
	   	
		Object element = elements.firstElement();
		elements.removeElement( element );
		notifyAll();
		log.info("Read next instructions...");
		return element;
	}
	
	/** Cleans the buffer */
	public synchronized void clean(){
		this.elements.clear();
	}

	/* (non-Javadoc)
	 * @see lm.messaging.IncomingMessageListener#messageReceived(lm.messaging.IncomingMessage)
	 */
	public void messageReceived(IncomingMessage msg) {
		put(msg);
	}
}
