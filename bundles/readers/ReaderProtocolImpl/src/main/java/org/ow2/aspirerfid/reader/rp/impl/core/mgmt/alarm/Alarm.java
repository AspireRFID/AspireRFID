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

import java.util.Date;

import org.ow2.aspirerfid.reader.rp.impl.core.AntennaReadPoint;
import org.ow2.aspirerfid.reader.rp.impl.core.NotificationChannel;
import org.ow2.aspirerfid.reader.rp.impl.core.ReadPoint;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.Source;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.IOPort;
import org.apache.log4j.Logger;

/**
 * <code>Alarm</code> is the base of all the classes within the object model
 * that define the contents of alarm messages.
 */
public class Alarm {
	
	/**
	 * The logger.
	 */
	private static Logger log = Logger.getLogger(Alarm.class);
	
	/**
	 * The <code>epc</code> attribute of the reader that generated the alarm.
	 */
	protected String readerDeviceEPC;
	
	/**
	 * The name of the reader that generated the alarm.
	 */
	protected String readerDeviceName;
	
	/**
	 * The <code>handle</code> attribute of the reader that generated the
	 * alarm.
	 */
	protected int readerDeviceHandle;
	
	/**
	 * The <code>role</code> attribute of the reader that generated the alarm.
	 */
	protected String readerDeviceRole;
	
	/**
	 * The return value of the <code>ReaderDevice</code>'s
	 * <code>getTimeTicks()</code> method at the time the <code>Alarm</code>
	 * was generated.
	 */
	protected long timeTicks;
	
	/**
	 * The return value of the <code>ReaderDevice</code>'s
	 * <code>getTimeUTC()</code> method at the time the <code>Alarm</code>
	 * was generated.
	 */
	protected Date timeUTC;
	
	/**
	 * The name of the <code>Alarm</code> object identifying the type of
	 * alarm, e.g., "FreeMemoryAlarm", "TagListFullAlarm",
	 * "ReadPointOperStatusAlarm".
	 */
	protected String name;
	
	/**
	 * Indicates the severity level assigned to this alarm.
	 */
	protected AlarmLevel alarmLevel;
	
	/**
	 * Indicates the number of times the generation of this alarm has been
	 * suppressed. It is reset to <code>0</code> after the alarm is generated.
	 */
	protected int suppressCount;
	
	/**
	 * The reader device.
	 */
	private ReaderDevice readerDevice;
	
	/**
	 * Protected constructor.
	 * 
	 * @param name
	 *            The name of the alarm identifying the type of alarm, e.g.,
	 *            "FreeMemoryAlarm", "TagListFullAlarm",
	 *            "ReadPointOperStatusAlarm"
	 * @param alarmLevel
	 *            The severity level of the alarm
	 * @param readerDevice
	 *            The reader device
	 */
	protected Alarm(String name, AlarmLevel alarmLevel, ReaderDevice readerDevice) {
		this.name = name;
		this.alarmLevel = alarmLevel;
		this.readerDeviceEPC = readerDevice.getEPC();
		this.readerDeviceName = readerDevice.getName();
		this.readerDeviceHandle = readerDevice.getHandle();
		this.readerDeviceRole = readerDevice.getRole();
		this.timeTicks = readerDevice.getTimeTicks();
		this.timeUTC = readerDevice.getTimeUTC();
		this.suppressCount = 0;
		this.readerDevice = readerDevice;
	}
	
	/**
	 * Returns the <code>epc</code> attribute of the reader that generated the
	 * alarm.
	 * 
	 * @return The <code>epc</code> attribute of the reader that generated the
	 *         alarm
	 */
	public String getReaderDeviceEPC() {
		return readerDeviceEPC;
	}
	
	/**
	 * Returns the name of the reader that generated the alarm.
	 * 
	 * @return The name of the reader that generated the alarm
	 */
	public String getReaderDeviceName() {
		return readerDeviceName;
	}
	
	/**
	 * Returns the <code>handle</code> attribute of the reader that generated
	 * the alarm.
	 * 
	 * @return The <code>handle</code> attribute of the reader that generated
	 *         the alarm
	 */
	public int getReaderDeviceHandle() {
		return readerDeviceHandle;
	}
	
	/**
	 * Returns the <code>role</code> attribute of the reader that generated
	 * the alarm.
	 * 
	 * @return The <code>role</code> attribute of the reader that generated
	 *         the alarm
	 */
	public String getReaderDeviceRole() {
		return readerDeviceRole;
	}
	
	/**
	 * Returns the return value of the <code>ReaderDevice</code>'s
	 * <code>getTimeTicks()</code> method at the time the <code>Alarm</code>
	 * was generated.
	 * 
	 * @return The return value of the <code>ReaderDevice</code>'s
	 *         <code>getTimeTicks()</code> method at the time the
	 *         <code>Alarm</code> was generated
	 */
	public long getTimeTicks() {
		return timeTicks;
	}
	
	/**
	 * Returns the return value of the <code>ReaderDevice</code>'s
	 * <code>getTimeUTC()</code> method at the time the <code>Alarm</code>
	 * was generated.
	 * 
	 * @return The return value of the <code>ReaderDevice</code>'s
	 *         <code>getTimeUTC()</code> method at the time the
	 *         <code>Alarm</code> was generated
	 */
	public Date getTimeUTC() {
		return timeUTC;
	}
	
	/**
	 * Returns the name of the <code>Alarm</code> object identifying the type
	 * of alarm, e.g., "FreeMemoryAlarm", "TagListFullAlarm",
	 * "ReadPointOperStatusAlarm".
	 * 
	 * @return The name of the <code>Alarm</code> object
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the severity level assigned to this alarm.
	 * 
	 * @return The severity level assigned to this alarm
	 */
	public AlarmLevel getAlarmLevel() {
		return alarmLevel;
	}
	
	/**
	 * Returns the number of times the generation of this alarm has been
	 * suppressed.
	 * 
	 * @return The number of times the generation of this alarm has been
	 *         suppressed
	 */
	public int getSuppressCount() {
		return suppressCount;
	}
	
	/**
	 * Increases the number of times the generation of this alarm has been
	 * suppressed by <code>1</code>.
	 */
	public void suppress() {
		suppressCount++;
		
		if (this instanceof ReaderDeviceOperStatusAlarm) {
			ReaderDevice.increaseOperStateSuppressions();
		} else if (this instanceof FreeMemoryAlarm) {
			ReaderDevice.increaseMemStateSuppressions();
		} else if (this instanceof ReadPointOperStatusAlarm) {
			try {
				ReadPoint readPoint = readerDevice.getReadPoint(((ReadPointOperStatusAlarm) this).getReadPointName());
				readPoint.increaseOperStateSuppressions();
			} catch (ReaderProtocolException rpe) {
				log.error("ReadPoint not found");
			}
		} else if (this instanceof FailedMemReadAlarm) {
			try {
				ReadPoint readPoint = readerDevice.getReadPoint(((FailedMemReadAlarm) this).getReadPointName());
				if (readPoint instanceof AntennaReadPoint) {
					((AntennaReadPoint) readPoint).increaseReadFailureSuppressions();
				}
			} catch (ReaderProtocolException rpe) {
				log.error("ReadPoint not found");
			}
		} else if (this instanceof FailedWriteAlarm) {
			try {
				ReadPoint readPoint = readerDevice.getReadPoint(((FailedWriteAlarm) this).getReadPointName());
				if (readPoint instanceof AntennaReadPoint) {
					((AntennaReadPoint) readPoint).increaseWriteFailureSuppressions();
				}
			} catch (ReaderProtocolException rpe) {
				log.error("ReadPoint not found");
			}
		} else if (this instanceof FailedKillAlarm) {
			try {
				ReadPoint readPoint = readerDevice.getReadPoint(((FailedKillAlarm) this).getReadPointName());
				if (readPoint instanceof AntennaReadPoint) {
					((AntennaReadPoint) readPoint).increaseKillFailureSuppressions();
				}
			} catch (ReaderProtocolException rpe) {
				log.error("ReadPoint not found");
			}
		} else if (this instanceof FailedEraseAlarm) {
			try {
				ReadPoint readPoint = readerDevice.getReadPoint(((FailedEraseAlarm) this).getReadPointName());
				if (readPoint instanceof AntennaReadPoint) {
					((AntennaReadPoint) readPoint).increaseEraseFailureSuppressions();
				}
			} catch (ReaderProtocolException rpe) {
				log.error("ReadPoint not found");
			}
		} else if (this instanceof FailedLockAlarm) {
			try {
				ReadPoint readPoint = readerDevice.getReadPoint(((FailedLockAlarm) this).getReadPointName());
				if (readPoint instanceof AntennaReadPoint) {
					((AntennaReadPoint) readPoint).increaseLockFailureSuppressions();
				}
			} catch (ReaderProtocolException rpe) {
				log.error("ReadPoint not found");
			}
		} else if (this instanceof IOPortOperStatusAlarm) {
			try {
				IOPort ioPort = readerDevice.getIOPort(((IOPortOperStatusAlarm) this).getIOPortName());
				ioPort.increaseOperStateSuppressions();
			} catch (ReaderProtocolException rpe) {
				log.error("IOPort not found");
			}
		} else if (this instanceof SourceOperStatusAlarm) {
			try {
				Source source = readerDevice.getSource(((SourceOperStatusAlarm) this).getSourceName());
				source.increaseOperStateSuppressions();
			} catch (ReaderProtocolException rpe) {
				log.error("Source not found");
			}
		} else if (this instanceof NotificationChannelOperStatusAlarm) {
			try {
				NotificationChannel notifChan = readerDevice.getNotificationChannel(((NotificationChannelOperStatusAlarm) this).getNotificationChannelName());
				notifChan.increaseOperStateSuppressions();
			} catch (ReaderProtocolException rpe) {
				log.error("NotificationChannel not found");
			}
		}
	}

}
