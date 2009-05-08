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

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm;

import java.util.Hashtable;

import org.ow2.aspirerfid.reader.rp.impl.core.NotificationChannel;
import org.ow2.aspirerfid.reader.rp.impl.core.ReadPoint;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.Source;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.IOPort;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.OperationalStatus;
import org.apache.log4j.Logger;

/**
 * This class manages the alarm generation. On the one hand it is a thread which
 * polls some managed objects for some information which may lead to the
 * generation of alarms, on the other hand it provides a method to raise
 * alarms.
 */
public class AlarmManager extends Thread {
	
	/**
	 * The polling interval in milliseconds.
	 */
	private static final long POLL_INTERVAL = 3000;
	
	/**
	 * The logger.
	 */
	private static Logger log = Logger.getLogger(AlarmManager.class);
	
	/**
	 * The alarm processor whose <code>process()</code> method will be called
	 * in order to raise an alarm.
	 */
	private AlarmProcessor alarmProcessor;
	
	/**
	 * The reader device.
	 */
	private ReaderDevice readerDevice;
	
	/**
	 * The table which contains the times of the last fired alarms.
	 */
	private Hashtable<AlarmControl, Long> lastFiredTimes;
	
	/**
	 * The free memory alarm.
	 */
	private FreeMemoryAlarm freeMemoryAlarm;
	
	/**
	 * Constructor.
	 * 
	 * @param alarmProcessor
	 *            The alarm processor whose <code>process()</code> method will
	 *            be called in order to raise an alarm
	 * @param readerDevice
	 *            The reader device
	 */
	public AlarmManager(AlarmProcessor alarmProcessor, ReaderDevice readerDevice) {
		this.alarmProcessor = alarmProcessor;
		this.readerDevice = readerDevice;
		lastFiredTimes = new Hashtable<AlarmControl, Long>();
		freeMemoryAlarm = null;
	}

	/**
	 * The main loop of the polling thread.
	 */
	@Override
	public void run() {
		while (true) {

			// free memory alarm
			EdgeTriggeredAlarmControl freeMemAlarmControl = readerDevice
					.getFreeMemoryAlarmControl();
			if (isEdgeTriggeredAlarmReadyForFire(readerDevice.getFreeMemory(),
					freeMemAlarmControl)) {
				if (freeMemoryAlarm == null)
					freeMemoryAlarm = new FreeMemoryAlarm("FreeMemoryAlarm",
							freeMemAlarmControl.getLevel(), readerDevice);
				if (!alarmIsSuppressed(freeMemAlarmControl)) {
					fireAlarm_(freeMemoryAlarm, freeMemAlarmControl);
					freeMemoryAlarm = null;
				} else {
					freeMemoryAlarm.suppress();
					log.debug(freeMemoryAlarm.getName() + " suppressed");
				}
			}

			// other alarms...
			
			// operational status alarms
			readerDevice.getOperStatus();
			ReadPoint[] readPoints = readerDevice.getAllReadPoints();
			for (int i = 0; i < readPoints.length; i++) {
				readPoints[i].getOperStatus();
			}
			IOPort[] ioPorts = readerDevice.getAllIOPorts();
			for (int i = 0; i < ioPorts.length; i++) {
				ioPorts[i].getOperStatus();
			}
			NotificationChannel[] notifChans = readerDevice.getAllNotificationChannels();
			for (int i = 0; i < notifChans.length; i++) {
				notifChans[i].getOperStatus();
			}
			Source[] sources = readerDevice.getAllSources();
			for (int i = 0; i < sources.length; i++) {
				sources[i].getOperStatus();
			}

			try {
				Thread.sleep(AlarmManager.POLL_INTERVAL);
			} catch (InterruptedException ignore) { }

		}
	}
	
	/**
	 * Checks whether an edge-triggered alarm can be fired (does not care about
	 * the suppress interval - use the <code>isSuppressed</code> method).
	 * 
	 * @param value
	 *            The current value of the corresponding managed object field
	 * @param alarmControl
	 *            The alarm control
	 * @return <code>true</code> if it can be fired, <code>false</code>
	 *         otherwise
	 */
	private boolean isEdgeTriggeredAlarmReadyForFire(long value,
			EdgeTriggeredAlarmControl alarmControl) {
		if (alarmControl.getEnabled()) {
			boolean fallingEdge = alarmControl.getDirection() == EdgeTriggeredAlarmDirection.FALLING;
			if (alarmControl.getStatus() == EdgeTriggeredAlarmStatus.FIRED) {
				if (fallingEdge) {
					if (value > alarmControl.getRearmThreshold())
						alarmControl.setStatus(EdgeTriggeredAlarmStatus.ARMED);
				} else {
					if (value < alarmControl.getRearmThreshold())
						alarmControl.setStatus(EdgeTriggeredAlarmStatus.ARMED);
				}
			}
			if (alarmControl.getStatus() == EdgeTriggeredAlarmStatus.ARMED) {
				if (fallingEdge) {
					if (value < alarmControl.getAlarmThreshold()) {
						return true;
					}
				} else {
					if (value > alarmControl.getAlarmThreshold()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks whether an alarm is suppressed using its <code>AlarmControl</code>
	 * object.
	 * 
	 * @param alarmControl
	 *            The alarm control
	 * @return <code>true</code> if it is suppressed, <code>false</code>
	 *         otherwise
	 */
	private boolean alarmIsSuppressed(AlarmControl alarmControl) {
		Long lastFiredTime = lastFiredTimes.get(alarmControl);
		return !((lastFiredTime == null) || (System.currentTimeMillis()
				- lastFiredTime > alarmControl.getSuppressInterval() * 1000));
	}
	
	/**
	 * Fires an alarm.
	 * 
	 * @param alarm
	 *            The alarm
	 * @param alarmControl
	 *            The alarm control
	 */
	private void fireAlarm_(Alarm alarm, AlarmControl alarmControl) {
		alarmProcessor.process(alarm);
		lastFiredTimes.remove(alarmControl);
		lastFiredTimes.put(alarmControl, System.currentTimeMillis());
		if (alarmControl instanceof EdgeTriggeredAlarmControl) {
			((EdgeTriggeredAlarmControl) alarmControl)
					.setStatus(EdgeTriggeredAlarmStatus.FIRED);
		}
		log.debug(alarm.getName() + " fired");
	}
	
	/**
	 * Fires an alarm iff all relevant conditions are fulfilled.
	 * 
	 * @param alarm
	 *            The alarm
	 * @param alarmControl
	 *            The alarm control
	 */
	public void fireAlarm(Alarm alarm, AlarmControl alarmControl) {
		if (alarmControl.getEnabled()) {
			try {
				if (alarm instanceof TTOperStatusAlarm) {
					TTOperStatusAlarm operStatusAlarm = (TTOperStatusAlarm)alarm;
					TTOperationalStatusAlarmControl operStatusAlarmControl = (TTOperationalStatusAlarmControl)alarmControl;
					if ((operStatusAlarmControl.getTriggerFromState() == OperationalStatus.ANY)
							|| (operStatusAlarmControl.getTriggerToState() == OperationalStatus.ANY)
							|| (operStatusAlarm.getFromState() == operStatusAlarmControl
									.getTriggerFromState())
							|| (operStatusAlarm.getToState() == operStatusAlarmControl
									.getTriggerToState())) {
						if (!alarmIsSuppressed(operStatusAlarmControl)) {
							fireAlarm_(operStatusAlarm, operStatusAlarmControl);
						} else {
							operStatusAlarm.suppress();
							log
									.debug(operStatusAlarm.getName()
											+ " suppressed");
						}
					}
				} else if (alarmControl instanceof EdgeTriggeredAlarmControl) {
					EdgeTriggeredAlarmControl edgeTriggeredAlarmControl = (EdgeTriggeredAlarmControl)alarmControl;
					if (edgeTriggeredAlarmControl.getStatus() == EdgeTriggeredAlarmStatus.ARMED) {
						if (!alarmIsSuppressed(edgeTriggeredAlarmControl)) {
							fireAlarm_(alarm, edgeTriggeredAlarmControl);
						} else {
							alarm.suppress();
							log.debug(alarm.getName() + " suppressed");
						}
					}
				} else {
					if (!alarmIsSuppressed(alarmControl)) {
						fireAlarm_(alarm, alarmControl);
					} else {
						alarm.suppress();
						log.debug(alarm.getName() + " suppressed");
					}
				}
			} catch (ClassCastException cce) {
				log.error("Alarm and AlarmControl don't match.");
			}
		}
	}

}
