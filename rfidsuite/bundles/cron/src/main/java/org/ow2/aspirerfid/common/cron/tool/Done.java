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

/**
 * TODO Javadoc
 * 
 * @author Unknown
 * @version 2006
 */
public class Done {

	/**
	 * The number of Worker object threads that are currently working on
	 * something.
	 */
	private int _activeThreads = 0;

	/**
	 * This boolean keeps track of if the very first thread has started or not.
	 * This prevents this object from falsely reporting that the ThreadPool is
	 * done, just because the first thread has not yet started.
	 */
	private boolean _started = false;

	/**
	 * This method can be called to block the current thread until the
	 * ThreadPool is done.
	 */

	synchronized public void waitDone() {
		try {
			while (_activeThreads > 0) {
				wait();
			}
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Called to wait for the first thread to start. Once this method returns
	 * the process has begun.
	 */

	synchronized public void waitBegin() {
		try {
			while (!_started) {
				wait();
			}
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Called by a Worker object to indicate that it has begun working on a
	 * workload.
	 */
	synchronized public void workerBegin() {
		_activeThreads++;
		_started = true;
		notify();
	}

	/**
	 * Called by a Worker object to indicate that it has completed a workload.
	 */
	synchronized public void workerEnd() {
		_activeThreads--;
		notify();
	}

	/**
	 * Called to reset this object to its initial state.
	 */
	synchronized public void reset() {
		_activeThreads = 0;
	}

}
