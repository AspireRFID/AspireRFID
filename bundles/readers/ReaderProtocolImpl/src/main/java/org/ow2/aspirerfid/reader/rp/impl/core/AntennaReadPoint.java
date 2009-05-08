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

package org.ow2.aspirerfid.reader.rp.impl.core;

import java.util.Date;

import org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction;
import org.ow2.aspirerfid.reader.rp.impl.hal.HardwareException;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmControl;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmLevel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.FailedEraseAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.FailedKillAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.FailedLockAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.FailedMemReadAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.FailedWriteAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;

/**
 * <code>AntennaReadPoint</code> extends <code>ReadPoint</code>. It is used
 * to track details about the Radio Frequency characteristics and RFID
 * statistics for the reader device.
 */
public class AntennaReadPoint extends ReadPoint {

	/**
	 * The number of the successful tags that have been identified across this
	 * <code>AntennaReadPoint</code>. In other words, the number of tags
	 * whose unique identifier has been successfully detected by the
	 * <code>AntennaReadPoint</code>. This count is automatically reset to
	 * <code>0</code> whenever the reader is restarted, and may be manually
	 * reset via a call to <code>ReaderDevice.resetStatistics</code>. Note
	 * that if a single tag's identifier is read multiple times, then the read
	 * count will increment on every successful read. It is the number of times
	 * any tag's identifier is read, not the number of unique tags which have
	 * been read.
	 */
	private int identificationCount;

	/**
	 * The number of the failed tag identification attempts at the
	 * <code>AntennaReadPoint</code>. This count only includes failures where
	 * the tag's identifier could not be obtained, either due to a bad handle or
	 * any other reason. This count is automatically reset to <code>0</code>
	 * whenever the reader is restarted, and may be manually reset via a call to
	 * <code>ReaderDevice.resetStatistics</code>.
	 */
	private int failedIdentificationCount;

	/**
	 * The number of tag memory reads at the <code>AntennaReadPoint</code>.
	 * This count is automatically reset to <code>0</code> whenever the reader
	 * is restarted, and may be manually reset via a call to
	 * <code>ReaderDevice.resetStatistics</code>.
	 */
	private int memReadCount;

	/**
	 * The number of the failed tag memory reads at the
	 * <code>AntennaReadPoint</code>. This count only includes failures where
	 * it attempts to read tag memory but does not complete successfully. This
	 * count is automatically reset to <code>0</code> whenever the reader is
	 * restarted, and may be manually reset via a call to
	 * <code>ReaderDevice.resetStatistics</code>.
	 */
	private int failedMemReadCount;

	/**
	 * Controls the generation of alarms triggered by failed read operations of
	 * memory banks across the <code>AntennaReadPoint</code>.
	 */
	private AlarmControl failedMemReadAlarmControl;

	/**
	 * The number of successful tag writes at the <code>AntennaReadPoint</code>,
	 * including writes to any of the memory banks including the tag identifier.
	 * This count is automatically reset to <code>0</code> whenever the reader
	 * is restarted, and may be manually reset via a call to
	 * <code>ReaderDevice.resetStatistics</code>. Note that if a single tag
	 * is written multiple times, then the count will increment on every
	 * successful write. It is the number of times any tag has been written, not
	 * the number of unique tags which have been written.
	 */
	private int writeCount;

	/**
	 * The number of the failed attempts to write tags at the
	 * <code>AntennaReadPoint</code>, including failed writes to any of the
	 * memory banks including the tag identifier. This is the number of times
	 * the reader attempted to write a tag, but the operation failed to complete
	 * successfully. This count is automatically reset to <code>0</code>
	 * whenever the reader is restarted, and may be manually reset via a call to
	 * <code>ReaderDevice.resetStatistics</code>.
	 */
	private int failedWriteCount;

	/**
	 * Controls the generation of alarms triggered by failed write operations
	 * across the <code>AntennaReadPoint</code>, including failed writes to
	 * any of the memory banks including the tag identifier.
	 */
	private AlarmControl failedWriteAlarmControl;

	/**
	 * The number of tags successfully killed at the
	 * <code>AntennaReadPoint</code>. This count is automatically reset to
	 * <code>0</code> whenever the reader is restarted, and may be manually
	 * reset via a call to <code>ReaderDevice.resetStatistics</code>.
	 */
	private int killCount;

	/**
	 * The number of the failed tag kills at the <count>AntennaReadPoint</count>.
	 * This count only includes failures where it attempts to kill a tag but the
	 * kill does not complete successfully. This count is automatically reset to
	 * <code>0</code> whenever the reader is restarted, and may be manually
	 * reset via a call to <code>ReaderDevice.resetStatistics</code>.
	 */
	private int failedKillCount;

	/**
	 * Controls the generation of alarms triggered by failed kill operations
	 * across the <code>AntennaReadPoint</code>.
	 */
	private AlarmControl failedKillAlarmControl;

	/**
	 * The number of tags successfully erased at the
	 * <code>AntennaReadPoint</code>. This count is automatically reset to
	 * <code>0</code> whenever the reader is restarted, and may be manually
	 * reset via a call to <code>ReaderDevice.resetStatistics</code>.
	 */
	private int eraseCount;

	/**
	 * The number of the failed tag erasures at the
	 * <code>AntennaReadPoint</code>. This count only includes failures where
	 * it attempts to erase a tag but the erasure does not complete
	 * successfully. This count is automatically reset to <code>0</code>
	 * whenever the reader is restarted, and may be manually reset via a call to
	 * <code>ReaderDevice.resetStatistics</code>.
	 */
	private int failedEraseCount;

	/**
	 * Controls the generation of alarms triggered by failed erase operations
	 * across the <code>AntennaReadPoint</code>.
	 */
	private AlarmControl failedEraseAlarmControl;

	/**
	 * The number of tags successfully locked at the
	 * <code>AntennaReadPoint</code>. This count is automatically reset to
	 * <code>0</code> whenever the reader is restarted, and may be manually
	 * reset via a call to <code>ReaderDevice.resetStatistics</code>.
	 */
	private int lockCount;

	/**
	 * The number of the failed tag locks at the <code>AntennaReadPoint</code>.
	 * This count only includes failures where it attempts to lock a tag but the
	 * lock does not complete successfully. This count is automatically reset to
	 * <code>0</code> whenever the reader is restarted, and may be manually
	 * reset via a call to <code>ReaderDevice.resetStatistics</code>.
	 */
	private int failedLockCount;

	/**
	 * Controls the generation of alarms triggered by failed lock operations
	 * across the <code>AntennaReadPoint</code>.
	 */
	private AlarmControl failedLockAlarmControl;

	/**
	 * The number of milliseconds the <code>AntennaReadPoint</code> has been
	 * energized in order to communicate with tags. This count is automatically
	 * reset to <code>0</code> whenever the reader is restarted, and may be
	 * manually reset via a call to <code>ReaderDevice.resetStatistics</code>.
	 */
	private long timeEnergized;
	
	/**
	 * The time stamp when the <code>AntennaReaPoint</code> has been started
	 * to be energized.
	 */
	private Date energizedTimeStamp;

	/**
	 * The current transmit power level of the <code>AntennaReadPoint</code>.
	 */
	private int powerLevel;

	/**
	 * The current noise level observed at the <code>AntennaReadPoint</code>.
	 */
	private int noiseLevel;
	
	/**
	 * The number of <code>FailedMemReadAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object.
	 */
	private int readFailureSuppressions = 0;
	
	/**
	 * The number of <code>FailedWriteAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object.
	 */
	private int writeFailureSuppressions = 0;
	
	/**
	 * The number of <code>FailedKillAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object.
	 */
	private int killFailureSuppressions = 0;
	
	/**
	 * The number of <code>FailedEraseAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object.
	 */
	private int eraseFailureSuppressions = 0;
	
	/**
	 * The number of <code>FailedLockAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object.
	 */
	private int lockFailureSuppressions = 0;
	
	/**
	 * The static method to create an instance of a
	 * <code>AntennaReadPoint</code>. If this method terminates successfully,
	 * the <code>AntennaReadPoint</code> is added to the list of read points
	 * in the reader device. If a read point with the same name exists, an
	 * exception ("ERROR_OBJECT_EXISTS") is thrown.
	 * 
	 * @param name
	 *            The name of the <code>AntennaReadPoint</code>
	 * @param readerDevice
	 *            The reader device the source belongs to
	 * @param reader
	 *            The <code>HardwareAbstraction</code> which contains the read
	 *            point
	 * @return The instance of the created readpoint
	 * @throws ReaderProtocolException
	 *             "ERROR_OBJECT_EXISTS"
	 */
	public static AntennaReadPoint create(final String name,
			final ReaderDevice readerDevice, final HardwareAbstraction reader)
			throws ReaderProtocolException {
		// check if ReadPoint with the same name exists
		try {
			readerDevice.getReadPoint(name);
		} catch (ReaderProtocolException e) {
			// create new ReadPoint
			AntennaReadPoint newReadPoint = new AntennaReadPoint(name, readerDevice, reader);
			readerDevice.getReadPoints().put(name, newReadPoint);
			return newReadPoint;
		}
		throw new ReaderProtocolException("ERROR_OBJECT_EXISTS",
				MessagingConstants.ERROR_OBJECT_EXISTS);
	}

	/**
	 * The private constructor of a AntennaReadPoint.
	 * 
	 * @param name
	 *            The name of the readpoint
	 * @param readerDevice
	 *            The reader device the source belongs to
	 * @param reader
	 *            The HardwareAbstraction which contains the read point
	 */
	private AntennaReadPoint(String name, ReaderDevice readerDevice,
			HardwareAbstraction reader) {
		super(name, readerDevice, reader);
		
		failedMemReadAlarmControl = new AlarmControl(name
				+ "_FailedMemReadAlarmControl", false, AlarmLevel.ERROR, 0);

		failedWriteAlarmControl = new AlarmControl(name
				+ "_FailedWriteAlarmControl", false, AlarmLevel.NOTICE, 0);

		failedKillAlarmControl = new AlarmControl(name
				+ "_FailedKillAlarmControl", false, AlarmLevel.NOTICE, 0);

		failedEraseAlarmControl = new AlarmControl(name
				+ "_FailedEraseAlarmControl", false, AlarmLevel.NOTICE, 0);

		failedLockAlarmControl = new AlarmControl(name
				+ "_FailedLockAlarmControl", false, AlarmLevel.NOTICE, 0);
		
		
		resetCounters();
	}

	/**
	 * Returns the number of the successful tags that have been identified
	 * across this <code>AntennaReadPoint</code>. In other words, the number
	 * of tags whose unique identifier has been successfully detected by the
	 * <code>AntennaReadPoint</code>. Note that if a single tag's identifier
	 * is read multiple times, then the read count will increment on every
	 * successful read. It is the number of times any tag's identifier is read,
	 * not the number of unique tags which have been read.
	 * 
	 * @return The count of the successful tag identifiers read at this
	 *         <code>AntennaReadPoint</code>
	 */
	public int getIdentificationCount() {
		return identificationCount;
	}

	/**
	 * Returns the number of the failed tag identification attempts at the
	 * <code>AntennaReadPoint</code>. This count only includes failures where
	 * the tag's identifier could not be obtained, either due to a bad handle or
	 * any other reason.
	 * 
	 * @return The count of the failed attempts at reading the identifier for a
	 *         tag at this <code>AntennaReadPoint</code>
	 */
	public int getFailedIdentificationCount() {
		return failedIdentificationCount;
	}

	/**
	 * Returns the number of tag memory reads at the
	 * <code>AntennaReadPoint</code>.
	 * 
	 * @return The count of the successful tag memory reads at this
	 *         <code>AntennaReadPoint</code>
	 */
	public int getMemReadCount() {
		return memReadCount;
	}

	/**
	 * Returns the number of the failed tag memory reads at the
	 * <code>AntennaReadPoint</code>. This count only includes failures where
	 * it attempts to read tag memory but does not complete successfully.
	 * 
	 * @return The count of the failed tag memory reads at this
	 *         <code>AntennaReadPoint</code>
	 */
	public int getFailedMemReadCount() {
		return failedMemReadCount;
	}

	/**
	 * Returns the alarm control object which controls the generation of alarms
	 * triggered by failed read operations of memory banks across the
	 * <code>AntennaReadPoint</code>.
	 * 
	 * @return An alarm control for monitoring tag memory read failures
	 */
	public AlarmControl getFailedMemReadAlarmControl() {
		return failedMemReadAlarmControl;
	}

	/**
	 * Returns the number of successful tag writes at the
	 * <code>AntennaReadPoint</code>, including writes to any of the memory
	 * banks including the tag identifier. If Write functionality is not
	 * supported by the Reader it returns <code>0</code> every time. Note that
	 * if a single tag is written multiple times, then the count will increment
	 * on every successful write. It is the number of times any tag has been
	 * written, not the number of unique tags which have been written.
	 * 
	 * @return The count of the successful writes at this
	 *         <code>AntennaReadPoint</code>
	 */
	public int getWriteCount() {
		return writeCount;
	}

	/**
	 * Returns the number of the failed attempts to write tags at the
	 * <code>AntennaReadPoint</code>, including failed writes to any of the
	 * memory banks including the tag identifier. This is the number of times
	 * the reader attempted to write a tag, but the operation failed to complete
	 * successfully.
	 * 
	 * @return The count of the failed writes at this
	 *         <code>AntennaReadPoint</code>
	 */
	public int getFailedWriteCount() {
		return failedWriteCount;
	}

	/**
	 * Returns the alarm control object which controls the generation of alarms
	 * triggered by failed write operations across the
	 * <code>AntennaReadPoint</code>, including failed writes to any of the
	 * memory banks including the tag identifier.
	 * 
	 * @return An alarm control for monitoring the number of failed writes
	 */
	public AlarmControl getFailedWriteAlarmControl() {
		return failedWriteAlarmControl;
	}

	/**
	 * Returns the number of tags successfully killed at the
	 * <code>AntennaReadPoint</code>.
	 * 
	 * @return The count of the successful tag kills at this
	 *         <code>AntennaReadPoint</code>
	 */
	public int getKillCount() {
		return killCount;
	}

	/**
	 * Returns the number of the failed tag kills at the <count>AntennaReadPoint</count>.
	 * This count only includes failures where it attempts to kill a tag but the
	 * kill does not complete successfully.
	 * 
	 * @return The count of the failed tag kills at this
	 *         <code>AntennaReadPoint</code>
	 */
	public int getFailedKillCount() {
		return failedKillCount;
	}

	/**
	 * Returns the alarm control object which controls the generation of alarms
	 * triggered by failed kill operations across the
	 * <code>AntennaReadPoint</code>.
	 * 
	 * @return An alarm control for monitoring tag kill failures
	 */
	public AlarmControl getFailedKillAlarmControl() {
		return failedKillAlarmControl;
	}

	/**
	 * Returns the number of tags successfully erased at the
	 * <code>AntennaReadPoint</code>.
	 * 
	 * @return The count of the successful tag erasures at this
	 *         <code>AntennaReadPoint</code>
	 */
	public int getEraseCount() {
		return eraseCount;
	}

	/**
	 * Returns the number of the failed tag erasures at the
	 * <code>AntennaReadPoint</code>. This count only includes failures where
	 * it attempts to erase a tag but the erasure does not complete
	 * successfully.
	 * 
	 * @return The count of the failed tag erasures at this
	 *         <code>AntennaReadPoint</code>
	 */
	public int getFailedEraseCount() {
		return failedEraseCount;
	}

	/**
	 * Returns the alarm control object which controls the generation of alarms
	 * triggered by failed erase operations across the
	 * <code>AntennaReadPoint</code>.
	 * 
	 * @return An alarm control for monitoring tag erasure failures
	 */
	public AlarmControl getFailedEraseAlarmControl() {
		return failedEraseAlarmControl;
	}

	/**
	 * Returns the number of tags successfully locked at the
	 * <code>AntennaReadPoint</code>.
	 * 
	 * @return The count of tags successfully locked at this
	 *         <code>AntennaReadPoint</code>
	 */
	public int getLockCount() {
		return lockCount;
	}

	/**
	 * Returns the number of the failed tag locks at the
	 * <code>AntennaReadPoint</code>. This count only includes failures where
	 * it attempts to lock a tag but the lock does not complete successfully.
	 * 
	 * @return The count of the failed tag locks at this
	 *         <code>AntennaReadPoint</code>
	 */
	public int getFailedLockCount() {
		return failedLockCount;
	}

	/**
	 * Returns the alarm control object which controls the generation of alarms
	 * triggered by failed lock operations across the
	 * <code>AntennaReadPoint</code>.
	 * 
	 * @return An alarm control for monitoring tag lock failures
	 */
	public AlarmControl getFailedLockAlarmControl() {
		return failedLockAlarmControl;
	}

	/**
	 * Returns the number of milliseconds the <code>AntennaReadPoint</code>
	 * has been energized in order to communicate with tags.
	 * 
	 * @return The number of milliseconds the <code>AntennaReadPoint</code>
	 *         has been energized attempting communication with tags
	 */
	public long getTimeEnergized() {
		timeEnergized = System.currentTimeMillis() - energizedTimeStamp.getTime();
		return timeEnergized;
	}

	/**
	 * Returns the current transmit power level of the
	 * <code>AntennaReadPoint</code>.
	 * 
	 * @return The current transmit power level of the
	 *         <code>AntennaReadPoint</code>
	 */
	public int getPowerLevel() {
		try {
			powerLevel = reader.getReadPointPowerLevel(name, true);
		} catch (HardwareException e) {
			// return last power level
		}
		return powerLevel;
	}

	/**
	 * Returns the current noise level observed at the
	 * <code>AntennaReadPoint</code>.
	 * 
	 * @return The current noise level observed at the
	 *         <code>AntennaReadPoint</code>
	 */
	public int getNoiseLevel() {
		try {
			noiseLevel = reader.getReadPointNoiseLevel(name, true);
		} catch (HardwareException e) {
			// return last noise level
		}
		return noiseLevel;
	}
	
	/**
	 * Resets all counters.
	 */
	public void resetCounters() {
		identificationCount = 0;
		failedIdentificationCount = 0;
		memReadCount = 0;
		failedMemReadCount = 0;
		writeCount = 0;
		failedWriteCount = 0;
		killCount = 0;
		failedKillCount = 0;
		eraseCount = 0;
		failedEraseCount = 0;
		lockCount = 0;
		failedLockCount = 0;
		energizedTimeStamp = new Date();
	}
	
	/**
	 * This method should be called whenever a memory read failure occurred.
	 */
	public void memReadFailureOccurred() {
		failedMemReadCount++;
		FailedMemReadAlarm alarm = new FailedMemReadAlarm(name
				+ "_FailedMemReadAlarm", failedMemReadAlarmControl.getLevel(),
				readerDevice, this);
		readerDevice.getAlarmManager().fireAlarm(alarm,
				failedMemReadAlarmControl);
	}
	
	/**
	 * This method should be called whenever a write failure occurred.
	 */
	public void writeFailureOccurred() {
		failedWriteCount++;
		FailedWriteAlarm alarm = new FailedWriteAlarm(name
				+ "_FailedWriteAlarm", failedWriteAlarmControl.getLevel(),
				readerDevice, this);
		readerDevice.getAlarmManager()
				.fireAlarm(alarm, failedWriteAlarmControl);
	}
	
	/**
	 * This method should be called whenever a kill failure occurred.
	 */
	public void killFailureOccurred() {
		failedKillCount++;
		FailedKillAlarm alarm = new FailedKillAlarm(name + "_FailedKillAlarm",
				failedKillAlarmControl.getLevel(), readerDevice, this);
		readerDevice.getAlarmManager().fireAlarm(alarm, failedKillAlarmControl);
	}
	
	/**
	 * This method should be called whenever a erase failure occurred.
	 */
	public void eraseFailureOccurred() {
		failedEraseCount++;
		FailedEraseAlarm alarm = new FailedEraseAlarm(name
				+ "_FailedEraseAlarm", failedEraseAlarmControl.getLevel(),
				readerDevice, this);
		readerDevice.getAlarmManager()
				.fireAlarm(alarm, failedEraseAlarmControl);
	}
	
	/**
	 * This method should be called whenever a lock failure occurred.
	 */
	public void lockFailureOccurred() {
		failedLockCount++;
		FailedLockAlarm alarm = new FailedLockAlarm(name + "_FailedLockAlarm",
				failedLockAlarmControl.getLevel(), readerDevice, this);
		readerDevice.getAlarmManager().fireAlarm(alarm, failedLockAlarmControl);
	}
	
	/**
	 * Increases the <code>identificationCount</code> attribute by
	 * <code>1</code>.
	 */
	public void increaseIdentificationCount() {
		identificationCount++;
	}
	
	/**
	 * Increases the <code>failedIdentificationCount</code> attribute by
	 * <code>1</code>.
	 */
	public void increaseFailedIdentificationCount() {
		failedIdentificationCount++;
	}
	
	/**
	 * Increases the <code>memReadCount</code> attribute by <code>1</code>.
	 */
	public void increaseMemReadCount() {
		memReadCount++;
	}
	
	/**
	 * Increases the <code>writeCount</code> attribute by <code>1</code>.
	 */
	public void increaseWriteCount() {
		writeCount++;
	}
	
	/**
	 * Increases the <code>killCount</code> attribute by <code>1</code>.
	 */
	public void increaseKillCount() {
		killCount++;
	}
	
	/**
	 * Increases the <code>eraseCount</code> attribute by <code>1</code>.
	 */
	public void increaseEraseCount() {
		eraseCount++;
	}
	
	/**
	 * Increases the <code>lockCount</code> attribute by <code>1</code>.
	 */
	public void increaseLockCount() {
		lockCount++;
	}
	
	/**
	 * Increases the number of <code>FailedMemReadAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object by <code>1</code>.
	 */
	public void increaseReadFailureSuppressions() {
		readFailureSuppressions++;
	}
	
	/**
	 * Returns the number of <code>FailedMemReadAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object.
	 * 
	 * @return The number of <code>FailedMemReadAlarm</code>s that have been
	 *         suppressed for this <code>AntennaReadPoint</code> object
	 */
	public int getReadFailureSuppressions() {
		return readFailureSuppressions;
	}
	
	/**
	 * Resets the number of <code>FailedMemReadAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object to <code>0</code>.
	 */
	public void resetReadFailureSuppressions() {
		readFailureSuppressions = 0;
	}
	
	/**
	 * Increases the number of <code>FailedWriteAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object by <code>1</code>.
	 */
	public void increaseWriteFailureSuppressions() {
		writeFailureSuppressions++;
	}
	
	/**
	 * Returns the number of <code>FailedWriteAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object.
	 * 
	 * @return The number of <code>FailedWriteAlarm</code>s that have been
	 *         suppressed for this <code>AntennaReadPoint</code> object
	 */
	public int getWriteFailureSuppressions() {
		return writeFailureSuppressions;
	}
	
	/**
	 * Resets the number of <code>FailedWriteAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object to <code>0</code>.
	 */
	public void resetWriteFailureSuppressions() {
		writeFailureSuppressions = 0;
	}
	
	/**
	 * Increases the number of <code>FailedKillAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object by <code>1</code>.
	 */
	public void increaseKillFailureSuppressions() {
		killFailureSuppressions++;
	}
	
	/**
	 * Returns the number of <code>FailedKillAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object.
	 * 
	 * @return The number of <code>FailedKillAlarm</code>s that have been
	 *         suppressed for this <code>AntennaReadPoint</code> object
	 */
	public int getKillFailureSuppressions() {
		return killFailureSuppressions;
	}
	
	/**
	 * Resets the number of <code>FailedKillAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object to <code>0</code>.
	 */
	public void resetKillFailureSuppressions() {
		killFailureSuppressions = 0;
	}
	
	/**
	 * Increases the number of <code>FailedEraseAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object by <code>1</code>.
	 */
	public void increaseEraseFailureSuppressions() {
		eraseFailureSuppressions++;
	}
	
	/**
	 * Returns the number of <code>FailedEraseAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object.
	 * 
	 * @return The number of <code>FailedEraseAlarm</code>s that have been
	 *         suppressed for this <code>AntennaReadPoint</code> object
	 */
	public int getEraseFailureSuppressions() {
		return eraseFailureSuppressions;
	}
	
	/**
	 * Resets the number of <code>FailedEraseAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object to <code>0</code>.
	 */
	public void resetEraseFailureSuppressions() {
		eraseFailureSuppressions = 0;
	}
	
	/**
	 * Increases the number of <code>FailedLockAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object by <code>1</code>.
	 */
	public void increaseLockFailureSuppressions() {
		lockFailureSuppressions++;
	}
	
	/**
	 * Returns the number of <code>FailedLockAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object.
	 * 
	 * @return The number of <code>FailedLockAlarm</code>s that have been
	 *         suppressed for this <code>AntennaReadPoint</code> object
	 */
	public int getLockFailureSuppressions() {
		return lockFailureSuppressions;
	}
	
	/**
	 * Resets the number of <code>FailedLockAlarm</code>s that have been
	 * suppressed for this <code>AntennaReadPoint</code> object to <code>0</code>.
	 */
	public void resetLockFailureSuppressions() {
		lockFailureSuppressions = 0;
	}

}
