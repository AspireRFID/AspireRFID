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

package org.ow2.aspirerfid.reader.rp.impl.core.msg.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;
/**
 * Implementation of a blocking FIFO queue. Blocking means that all threads that try
 * to read on an empty queue or the a queue that reached its capacity are blocked.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 *
 */

public class BlockingQueue {
	
	/** The internal collection used in the queue implementation */
	private final Vector queue;
	
	/** The maximum number of elements in the queue */
	private int capacity;

	/**
	 * Creates a new <code>BlockingQueue</code>.
	 * @param initCapacity The initial capacity of the queue.
	 */
	public BlockingQueue(int initCapacity) {
		queue = new Vector(initCapacity);
	}
	
	/**
	 * Pushes an object onto the end of the queue, and then notifies
	 * one of the waiting threads.
	 * @param o The object to push to the FIFO queue.
	 */
	public void push(Object o)  {
		synchronized (queue) {
			queue.add(o);
			queue.notify();
		}
	}

	/**
	 * Pops an object from the top of the FIFO queue. The pop operation 
	 * blocks until either an object is returned or the thread
	 * is interrupted, in which case it throws an InterruptedException.
	 * @return object from the FIFO queue.
	 * @throws InterruptedException If the thread is interrupted.
	 */
	public Object pop() throws InterruptedException {
		synchronized (queue) {
			while (queue.isEmpty()) {
				queue.wait();
			}
			return queue.remove(0);
		}
	}

	/**
	 * Gets the size of the queue.
	 * @return Size of the queue.
	 */
	public int size() {
		return queue.size();
	}
	
	
}
