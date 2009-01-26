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
public class ThreadSleeper {
	/**
	 * The thread that may need to sleep.
	 */
	Thread t;
	volatile boolean doSleep = false;
	/**
	 * This is a running counter that gets incremented when work becomes
	 * available. The precise value of this variable is irrelevant. What is
	 * important is whether the number is bigger than the last time we checked.
	 * The last value when checked is stored in oldWakeupTicker. wakeupTicker is
	 * always >= oldWakeupTicker.
	 */
	volatile long wakeupTicker = 0;
	volatile long oldWakeupTicker = 0;

	/**
	 * Create a ThreadSleeper
	 */
	public ThreadSleeper() {
	}

	/**
	 * Same as waitForWork(long)
	 * 
	 * see waitForWork(long) except we potentially sleep forever.
	 */
	public void waitForWork() {
		waitForWork(Long.MAX_VALUE);
	}

	/**
	 * Possibly sleep until new work is available. Don't sleep if work is
	 * available. waitForWork is guaranteed not to sleep when there is work to
	 * do, but it is possible it might occasionally not sleep when there is no
	 * work to do. Caller must handle the case of nothing to do. The current
	 * thread at the time of calling this function is stored internally, and it
	 * will be the thread that wakes up when notifyOfWork is eventually called.
	 * 
	 * @param time
	 *            the time during when the thread will sleep
	 */
	public void waitForWork(long time) {
		if (t != null) {
			throw new RuntimeException(
					"More than one thread trying to waitForWork in ThreadSleeper");
		}
		try {
			t = Thread.currentThread();
			doSleep = true;
			if (wakeupTicker == oldWakeupTicker) {
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					// Keep Going
				}
			}
		} finally {
			// Using a finally { } clause is probably overkill, because I can't
			// imagine anything throwing an exception. However, who really
			// knows what APIs throw RuntimeExceptions?
			oldWakeupTicker = wakeupTicker;
			doSleep = false;
			t = null;
		}
	}

	/**
	 * Notify the thread that work is available. Any thread can call this.
	 */
	public void notifyOfWork() {
		// We need to loop around until we are sure that the thread is awake
		// i.e. not between the doSleep = true / doSleep = false barriers.
		// First we increment the ticker which says that there's more work.
		// The loop is because this method could run in between the if()
		// and the sleep() in waitForWork. In that case we have to keep
		// interrupting until we are sure we have entered and exited the
		// sleep() call. The yield() is because once we have interrupt()ed,
		// CPU time is best devoted to making sure the other thread wakes
		// up instead of a fruitless busy loop of re-interrupting.
		long tick = ++wakeupTicker;
		while (doSleep && oldWakeupTicker < tick) {
			t.interrupt();
			Thread.yield();
		}
	}
}
