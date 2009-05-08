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



/**
 * Generic worker used in the <code>ThreadPool</code> class.
 *  
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * @author Paul Hyde
 *
 */

public class ThreadPoolWorker {

	private static int nextWorkerID = 0;
	
	private BlockingQueue idleWorkers;
	private int workerID;
	
	private Thread internalThread;
	private volatile boolean noStopRequested;
	
	private BlockingQueue handoffBox;
	
	public ThreadPoolWorker(BlockingQueue idleWorkers) {
		super();
		this.idleWorkers = idleWorkers;
		workerID = getNextWorkerID();
		
		handoffBox = new BlockingQueue(1);
		
		// just before returning, the thread should be created.
		noStopRequested = true;
		
		Runnable r = new Runnable() {
			public void run() {
				try {
					runWork();
				} catch ( Exception x ) {
					// in case ANY exception slips through
					x.printStackTrace();
				}
			}
		};
		
		internalThread = new Thread(r);
		internalThread.start();
	}
	
	/**
	 * Gets a unique id for the worker.
	 * @return
	 */
	public static synchronized int getNextWorkerID() {
		int id = nextWorkerID;
		nextWorkerID++;
		return id;
	}
	
	/**
	 * Processes a <code>Runnable</code> target.
	 * @param target The <code>Runnable</code> to execute.
	 * @throws InterruptedException
	 */
	public void process(Runnable target) throws InterruptedException {
		handoffBox.push(target);
	}
	
	private void runWork() {
		while ( noStopRequested ) {
			try {
				idleWorkers.push(this);
				Runnable r = (Runnable) handoffBox.pop();
				runIt(r);
			 } catch ( InterruptedException x ) {
				Thread.currentThread().interrupt(); // re-assert
			}
		}
	}
	
	private void runIt(Runnable r) {
		try {
			r.run();
		} catch ( Exception runex ) {
			// catch any and all exceptions
			System.err.println("Uncaught exception fell through from run()");
			runex.printStackTrace();
		} finally {
			// Clear the interrupted flag (in case it comes back
			// set) so that if the loop goes again, the
			// handoffBox.remove() does not mistakenly
			// throw an InterruptedException.
			Thread.interrupted();
		}
	}
	
	/**
	 * Request for a stop. Requet means that the worker is stopped after
	 * the currently running thread completes its job.
	 */
	public void stopRequest() {
		noStopRequested = false;
		internalThread.interrupt();
	}
	
	/**
	 * Gets the liveness status of a worker.
	 * @return <code>true</code> if the worker is alive, otherwise <code>false</code>.
	 */
	public boolean isAlive() {
		return internalThread.isAlive();
	}
	
	
	public String toString() {
		return "[ThreadPoolWorker #" + workerID + "]";
	}
}
