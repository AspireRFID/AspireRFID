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
package org.ow2.aspirerfid.common.cron.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.ow2.aspirerfid.common.cron.CronService;
import org.ow2.aspirerfid.common.cron.TimedObject;
import org.ow2.aspirerfid.common.cron.tool.ThreadPool;
import org.ow2.aspirerfid.common.cron.tool.ThreadSleeper;

/**
 * Class CronServiceImpl is the main class of the poll bundle. It is a thread
 * managing the polling operations. It implements the PollController interface.
 * 
 * @author Unknown
 * @version 2006
 */
public class CronServiceImpl extends Thread implements CronService {

	private int POOL = 5;
	private ArrayList schedule_array;
	private boolean terminate = false;
	private ThreadSleeper threadSleeper = new ThreadSleeper();
	ThreadPool tp;

	/**
	 * Method looking for the position the task to be scheduled must have in the
	 * scheduling list
	 * 
	 * @return -1 if last place return the position between 0 to
	 *         poll_array.size() - 1
	 */
	private int getPlaceIndex(long t) {
		// Going through schedule_array
		for (int i = 0; i < schedule_array.size(); i++) {
			// If time t is more recent the variable to be scheduled return the
			// position
			if (((CronServiceObject) schedule_array.get(i)).ttbc > t)
				return i;
		}
		// All other cases return -1
		return -1;
	}

	/**
	 * @see org.ow2.aspirerfid.common.cron.CronService#add(TimedObject,
	 *      Serializable, String)
	 */
	public void add(TimedObject timedObject, Serializable serializable,
			String cron) {
		synchronized (schedule_array) {
			// Create a new CronParse
			CronParse cr = new CronParse(cron);
			// Then create a CronServiceObject
			CronServiceObject pn = new CronServiceObject(timedObject,
					serializable, cr);
			// Time the new variable must be polled
			long ttbp = pn.getNext();
			// Looking for his position in the queue
			int index = getPlaceIndex(ttbp);
			switch (index) {
			// If position = -1 either the schedule_array is empty either
			// the action must be place in last position
			case -1:
				if (schedule_array.size() == 0)
					index = 0;
				else
					index = schedule_array.size();
				// In all other cases place the action in the right position and
				// the sleeping time is the time missing to go to next action
			default:
				schedule_array.add(index, pn);
				break;
			}
		}
		threadSleeper.notifyOfWork();
	}

	/**
	 * Method stopping the thread
	 */
	private void terminate() {
		terminate = true;
		threadSleeper.notifyOfWork();
	}

	/**
	 * The run() method is the one active during the thread life
	 */
	public void run() {
		long time;
		while (true) {
			// If variables need to be scheduled
			if (schedule_array.size() > 0) {
				// Calculate the time before next schedule
				time = ((CronServiceObject) schedule_array.get(0)).ttbc
						- System.currentTimeMillis();
				// If it is time to poll
				if (time <= 0) {
					// do polling
					do_schedule();
				} else {
					// In other cases we wait till next schedule
					threadSleeper.waitForWork(time);
				}
			} else {
				// If there is nothing to schedule wait till a add
				threadSleeper.waitForWork();
			}
			if (terminate)
				return;
		}
	}

	/**
	 * Do a schedule task
	 */
	private synchronized void do_schedule() {
		synchronized (schedule_array) {
			// If nothing in the schedule array just return
			if (schedule_array.size() == 0)
				return;
			// Get the next action to be schedule
			CronServiceObject tbp = (CronServiceObject) schedule_array.get(0);
			// Assign the task to the thread pool
			tp.assign(new CronServiceThread(tbp));
			// Remove the first element
			schedule_array.remove(0);
			// Get the index the task must be inserted
			int index_now = getPlaceIndex(tbp.getNext());
			if (index_now < 0) {
				// If the schedule array is empty put it a the beginning
				if (schedule_array.size() == 0)
					index_now = 0;
				// Otherwise it must be schedule in last position
				else
					index_now = schedule_array.size();
			}
			// Add the schedule action to the list
			schedule_array.add(index_now, tbp);
		}
	}

	/**
	 * @see org.ow2.aspirerfid.common.cron.CronService#remove(org.ow2.aspirerfid.common.cron.TimedObject,
	 *      java.io.Serializable)
	 */
	public void remove(TimedObject timedObject, Serializable serializable) {
		synchronized (schedule_array) {
			// tp.complete();
			Iterator i = schedule_array.iterator();
			int[] tab = new int[schedule_array.size()];
			int tabpos = 0;
			// Look for a specific business object in the schedule array
			while (i.hasNext()) {
				CronServiceObject so = (CronServiceObject) i.next();
				// If it is founded
				if ((so.getTimedObject() == timedObject)
						&& (so.getSerializable() == serializable)) {
					// Add it to the objects to delete
					tab[tabpos] = schedule_array.indexOf(so);
					tabpos++;
				}
			}
			// Remove all stopped objects
			for (int j = tabpos - 1; j >= 0; j--) {
				schedule_array.remove(tab[j]);
			}
			threadSleeper.notifyOfWork();

		}
	}

	/**
	 * initialization
	 */
	public void startCronService() {
		// Create the array of values to be scheduled
		schedule_array = new ArrayList();
		// Create the thread pool
		tp = new ThreadPool(POOL);
		// initialize service properties
		start();
	}

	/**
	 * ending
	 */
	public void endCronService() {
		// Stop all threads
		tp.complete();
		schedule_array = null;
		terminate();
		tp = null;

	}

	/**
	 * scales of the cron fields
	 */
	static int[] scale = { 1000, // millisecond
			60, // second
			60, // minutes
			24, // hours
			7 // week days
	};

	/**
	 * build the string to represent the period in the cron service
	 * 
	 * @param period
	 *            period in millisecond
	 * @return the period specification for cron service
	 */
	public String cronString(long period) {
		// initialize the string
		String Str = "";
		long rest = period;
		int index = 0;
		// for each unit get the rest corresponding to the unit
		while ((rest != 0) && (index < scale.length)) {
			// add this rest in the cron specification
			Str += rest % scale[index];
			// add a separator
			Str += " ";
			// and step to the next unit
			rest = rest / scale[index];
			index += 1;
		}
		// Period is fully specified. now specify to repeat it
		Str += "|";
		// and full the specification to have the 8 fields
		while (index < 8) {
			Str += " *";
			index += 1;
		}
		return Str;
	}

}
