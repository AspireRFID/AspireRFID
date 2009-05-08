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
 * A pool of threads.
 * <p>
 * Avoids the expense of thread creation by pooling threads. The threads are recycled 
 * after the job is completed.
 * If the maximum pool size is reached, jobs wait for a free thread.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 *
 */

public class ThreadPool extends Object {
	private BlockingQueue idleWorkers;
	
	private ThreadPoolWorker[] workerList;
	
	public ThreadPool(int numberOfThreads) {
		// make sure that its at least one
		numberOfThreads = Math.max(1, numberOfThreads);
		
		idleWorkers = new BlockingQueue(numberOfThreads);
		workerList = new ThreadPoolWorker[numberOfThreads];
		
		for (int i = 0; i < workerList.length; i++) {
			workerList[i] = new ThreadPoolWorker(idleWorkers);
		}
	}
	
	public void execute(Runnable target) throws InterruptedException {
		// block (forever) until a worker is available
		ThreadPoolWorker worker = (ThreadPoolWorker) idleWorkers.pop();
		worker.process(target);
	}
	
	public void stopRequestIdleWorkers() {
		try {
			int size = idleWorkers.size();
			for (int i = 0; i < size; i++) {
				((ThreadPoolWorker) idleWorkers.pop()).stopRequest();
			}
		} catch (InterruptedException x) {
			Thread.currentThread().interrupt(); // re-assert
		}
	}
	
	public void stopRequestAllWorkers() {
		// Stop the idle ones first
		// productive.
		stopRequestIdleWorkers();
		
		// give the idle workers a quick chance to die
		try {
			Thread.sleep(250);
		} catch (InterruptedException x) {
		}
		
		// Step through the list of ALL workers.
		for (int i = 0; i < workerList.length; i++) {
			if (workerList[i].isAlive()) {
				workerList[i].stopRequest();
			}
		}
	}
}

