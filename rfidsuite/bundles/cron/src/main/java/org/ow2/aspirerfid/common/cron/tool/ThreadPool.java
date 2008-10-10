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
package org.ow2.aspirerfid.common.cron.tool;

import java.util.ArrayList;
import java.util.Collection;

/**
 * TODO Javadoc
 * 
 * @author Unknown.
 * @version 2006
 */
public class ThreadPool {
	/**
	 * TODO Javadoc
	 */
	boolean used = false;
	/**
	 * The threads in the pool.
	 */
	protected Thread threads[] = null;
	/**
	 * The backlog of assignments, which are waiting for the thread pool.
	 */
	Collection assignments = new ArrayList(3);
	/**
	 * A Done object that is used to track when the thread pool is done, that is
	 * has no more work to perform.
	 */
	protected Done done = new Done();

	/**
	 * The constructor.
	 * 
	 * @param size
	 *            How many threads in the thread pool.
	 */
	public ThreadPool(int size) {
		threads = new WorkerThread[size];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new WorkerThread(this);
			threads[i].start();
		}
	}

	/**
	 * Add a task to the thread pool. Any class which implements the Runnable
	 * interface may be assienged. When this task runs, its run method will be
	 * called.
	 * 
	 * @param r
	 *            An object that implements the Runnable interface
	 */
	public synchronized void assign(Runnable r) {
		used = true;
		done.workerBegin();
		assignments.add(r);
		notify();
	}

	/**
	 * Get a new work assignment.
	 * 
	 * @return A new assignment
	 */
	public synchronized Runnable getAssignment() {
		try {
			while (!assignments.iterator().hasNext())
				wait();

			Runnable r = (Runnable) assignments.iterator().next();
			assignments.remove(r);
			return r;
		} catch (InterruptedException e) {
			done.workerEnd();
			return null;
		}
	}

	/**
	 * Called to block the current thread until the thread pool has no more
	 * work.
	 */
	public void complete() {
		if (!used)
			return;
		done.waitBegin();
		done.waitDone();
	}

	/*
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() {
		done.reset();
		for (int i = 0; i < threads.length; i++) {
			threads[i].interrupt();
			done.workerBegin();
			threads[i].destroy();
			threads[i] = null;
		}
		done.waitDone();
	}
}

/**
 * The worker threads that make up the thread pool.
 * 
 * @author Jeff Heaton
 * @version 1.0
 */
class WorkerThread extends Thread {
	/**
	 * True if this thread is currently processing.
	 */
	public boolean busy;
	/**
	 * The thread pool that this object belongs to.
	 */
	public ThreadPool owner;

	/**
	 * The constructor.
	 * 
	 * @param o
	 *            the thread pool
	 */
	WorkerThread(ThreadPool o) {
		owner = o;
	}

	/**
	 * Scan for and execute tasks.
	 */
	public void run() {
		Runnable target = null;

		do {
			target = owner.getAssignment();
			if (target != null) {
				target.run();
				owner.done.workerEnd();
			}
		} while (target != null);
	}
}
