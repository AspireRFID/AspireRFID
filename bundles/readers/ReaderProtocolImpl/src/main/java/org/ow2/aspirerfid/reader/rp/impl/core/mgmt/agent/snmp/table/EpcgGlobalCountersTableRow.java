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

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table;

import org.ow2.aspirerfid.reader.rp.impl.core.AntennaReadPoint;
import org.ow2.aspirerfid.reader.rp.impl.core.ReadPoint;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.Source;
import org.ow2.aspirerfid.reader.rp.impl.core.Trigger;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib;
import org.apache.log4j.Logger;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

/**
 * Forms a row of the epcgGlobalCountersTable.
 */
public class EpcgGlobalCountersTableRow extends SnmpTableRow {
	
	/**
	 * The counter types.
	 */
	public enum CounterType {
		ANTENNA_TAGS_IDENTIFIED,
		ANTENNA_TAGS_NOT_IDENTIFIED,
		ANTENNA_MEMORY_READ_FAILURES,
		ANTENNA_WRITE_OPERATIONS,
		ANTENNA_WRITE_FAILURES,
		ANTENNA_KILL_OPERATIONS,
		ANTENNA_KILL_FAILURES,
		ANTENNA_ERASE_OPERATIONS,
		ANTENNA_LOCK_OPERATIONS,
		ANTENNA_LOCK_FAILURES,
		SOURCE_UNKNOWN_TO_GLIMPSED,
		SOURCE_GLIMPSED_TO_UNKNOWN,
		SOURCE_GLIMPSED_TO_OBSERVED,
		SOURCE_OBSERVED_TO_LOST,
		SOURCE_LOST_TO_GLIMPSED,
		SOURCE_LOST_TO_UNKNOWN,
		TRIGGER_MATCHES,
		ANTENNA_MEMORY_READ_OPERATIONS,
		ANTENNA_ERASE_FAILURES;
		
		/**
		 * Converts this <code>CounterType</code> instance to an
		 * <code>int</code> value.
		 * 
		 * @return <code>int</code> representation of this
		 *         <code>CounterType</code> instance
		 */
		public final int toInt() {
			CounterType[] values = CounterType.values();
			for (int index = 0; index < values.length; index++) {
				if (values[index] == this) return index + 1;
			}
			return -1;
		}
		
		/**
		 * Converts an <code>int</code> value to an <code>CounterType</code>
		 * instance. If the <code>int</code> value is out of range it returns
		 * <code>null</code>.
		 * 
		 * @param i
		 *            The <code>int</code> value
		 * @return The <code>CounterType</code> representation of the
		 *         <code>int</code> value
		 */
		public static final CounterType intToEnum(final int i) {
			try {
				return CounterType.values()[i - 1];
			} catch (ArrayIndexOutOfBoundsException e) {
				return null;
			}
		}
		
	}
	
	private static long refreshTimeInMs = 100; // default value of the refresh time
	
	public static final int numOfColumns = EpcglobalReaderMib.getInstance().getEpcgGlobalCountersEntry().getColumnCount();
	private static Logger log = Logger.getLogger(EpcgGlobalCountersTableRow.class);
	private long[] lastRefreshTimes = new long[EpcgGlobalCountersTableRow.numOfColumns];
	private CounterType counterType;
	private ReaderDevice readerDevice;
	
	/**
	 * Protected Constructor. Use <code>getSnmpTableRow()</code> to
	 * instantiate.
	 * 
	 * @param cont
	 *            Row object container which corresponds to this row
	 * @throws ReaderProtocolException
	 *             Thrown if the <code>ReaderDevice</code> instance cannot be
	 *             obtained.
	 */
	protected EpcgGlobalCountersTableRow(RowObjectContainer cont)
			throws ReaderProtocolException {
		super(EpcgGlobalCountersTableRow.computeIndex(cont), new Variable[numOfColumns], cont);
		counterType = (CounterType)cont.getRowObjects()[0];
		readerDevice = ReaderDevice.getInstance();
	}
	
	/**
	 * Computes the index for this row
	 * 
	 * @param cont
	 *            Row object container of this row
	 * @return Index
	 */
	private static OID computeIndex(RowObjectContainer cont) {
		CounterType counterType = (CounterType)cont.getRowObjects()[0];
		return new OID(Integer.toString(counterType.toInt()));
	}
	
	/**
	 * Gets the value at the specified column index.
	 * 
	 * @param column
	 *            The (zero-based) column index
	 * @return The value at the specified index
	 */
	@Override
	public Variable getValue(int column) {
		boolean refresh = ((System.currentTimeMillis() - lastRefreshTimes[column]) > refreshTimeInMs);
		if (refresh) {
			log.debug("refreshing row " + index.toString());
			lastRefreshTimes[column] = System.currentTimeMillis();
		} else {
			log.debug("no need to refresh row " + index.toString());
		}
		
		switch (column) {
			case EpcglobalReaderMib.idxEpcgGlobalCountersData:
				if (refresh) {
					int value = 0;
					ReadPoint[] readPoints;
					Source[] sources;
					
					switch (counterType) {
					
						case ANTENNA_TAGS_IDENTIFIED:
							readPoints = readerDevice.getAllReadPoints();
							for (int i = 0; i < readPoints.length; i++) {
								if (readPoints[i] instanceof AntennaReadPoint) {
									value += ((AntennaReadPoint)readPoints[i])
											.getIdentificationCount();
								}
							}
							break;
						case ANTENNA_TAGS_NOT_IDENTIFIED:
							readPoints = readerDevice.getAllReadPoints();
							for (int i = 0; i < readPoints.length; i++) {
								if (readPoints[i] instanceof AntennaReadPoint) {
									value += ((AntennaReadPoint) readPoints[i])
											.getFailedIdentificationCount();
								}
							}
							break;
						case ANTENNA_MEMORY_READ_FAILURES:
							readPoints = readerDevice.getAllReadPoints();
							for (int i = 0; i < readPoints.length; i++) {
								if (readPoints[i] instanceof AntennaReadPoint) {
									value += ((AntennaReadPoint) readPoints[i])
											.getFailedMemReadCount();
								}
							}
							break;
						case ANTENNA_WRITE_OPERATIONS:
							// TODO: Remove the comment operators and the code
							// of this case statement after the region commented
							// out (except the break statement).
//							readPoints = readerDevice.getAllReadPoints();
//							for (int i = 0; i < readPoints.length; i++) {
//								if (readPoints[i] instanceof AntennaReadPoint) {
//									value += ((AntennaReadPoint) readPoints[i])
//											.getWriteCount();
//								}
//							}
							sources = readerDevice.getAllSources();
							for (int i = 0; i < sources.length; i++) {
								value += sources[i].getAntennaReadPointWriteCount();
							}
							break;
						case ANTENNA_WRITE_FAILURES:
							readPoints = readerDevice.getAllReadPoints();
							for (int i = 0; i < readPoints.length; i++) {
								if (readPoints[i] instanceof AntennaReadPoint) {
									value += ((AntennaReadPoint) readPoints[i])
											.getFailedWriteCount();
								}
							}
							break;
						case ANTENNA_KILL_OPERATIONS:
							// TODO: Remove the comment operators and the code
							// of this case statement after the region commented
							// out (except the break statement).
//							readPoints = readerDevice.getAllReadPoints();
//							for (int i = 0; i < readPoints.length; i++) {
//								if (readPoints[i] instanceof AntennaReadPoint) {
//									value += ((AntennaReadPoint) readPoints[i])
//											.getKillCount();
//								}
//							}
							sources = readerDevice.getAllSources();
							for (int i = 0; i < sources.length; i++) {
								value += sources[i].getAntennaReadPointKillCount();
							}
							break;
						case ANTENNA_KILL_FAILURES:
							readPoints = readerDevice.getAllReadPoints();
							for (int i = 0; i < readPoints.length; i++) {
								if (readPoints[i] instanceof AntennaReadPoint) {
									value += ((AntennaReadPoint) readPoints[i])
											.getFailedKillCount();
								}
							}
							break;
						case ANTENNA_ERASE_OPERATIONS:
							readPoints = readerDevice.getAllReadPoints();
							for (int i = 0; i < readPoints.length; i++) {
								if (readPoints[i] instanceof AntennaReadPoint) {
									value += ((AntennaReadPoint) readPoints[i])
											.getEraseCount();
								}
							}
							break;
						case ANTENNA_LOCK_OPERATIONS:
							readPoints = readerDevice.getAllReadPoints();
							for (int i = 0; i < readPoints.length; i++) {
								if (readPoints[i] instanceof AntennaReadPoint) {
									value += ((AntennaReadPoint) readPoints[i])
											.getLockCount();
								}
							}
							break;
						case ANTENNA_LOCK_FAILURES:
							readPoints = readerDevice.getAllReadPoints();
							for (int i = 0; i < readPoints.length; i++) {
								if (readPoints[i] instanceof AntennaReadPoint) {
									value += ((AntennaReadPoint) readPoints[i])
											.getFailedLockCount();
								}
							}
							break;
						case SOURCE_UNKNOWN_TO_GLIMPSED:
							sources = readerDevice.getAllSources();
							for (int i = 0; i < sources.length; i++) {
								value += sources[i].getUnknownToGlimpsedCount();
							}
							break;
						case SOURCE_GLIMPSED_TO_UNKNOWN:
							sources = readerDevice.getAllSources();
							for (int i = 0; i < sources.length; i++) {
								value += sources[i].getGlimpsedToUnknownCount();
							}
							break;
						case SOURCE_GLIMPSED_TO_OBSERVED:
							sources = readerDevice.getAllSources();
							for (int i = 0; i < sources.length; i++) {
								value += sources[i].getGlimpsedToObservedCount();
							}
							break;
						case SOURCE_OBSERVED_TO_LOST:
							sources = readerDevice.getAllSources();
							for (int i = 0; i < sources.length; i++) {
								value += sources[i].getObservedToLostCount();
							}
							break;
						case SOURCE_LOST_TO_GLIMPSED:
							sources = readerDevice.getAllSources();
							for (int i = 0; i < sources.length; i++) {
								value += sources[i].getLostToGlimpsedCount();
							}
							break;
						case SOURCE_LOST_TO_UNKNOWN:
							sources = readerDevice.getAllSources();
							for (int i = 0; i < sources.length; i++) {
								value += sources[i].getLostToUnknownCount();
							}
							break;
						case TRIGGER_MATCHES:
							Trigger[] triggers = readerDevice.getAllTriggers();
							for (int i = 0; i < triggers.length; i++) {
								value += triggers[i].getFireCount();
							}
							break;
						case ANTENNA_MEMORY_READ_OPERATIONS:
							// TODO: Remove the comment operators and the code
							// of this case statement after the region commented
							// out (except the break statement).
//							readPoints = readerDevice.getAllReadPoints();
//							for (int i = 0; i < readPoints.length; i++) {
//								if (readPoints[i] instanceof AntennaReadPoint) {
//									value += ((AntennaReadPoint) readPoints[i])
//											.getMemReadCount();
//								}
//							}
							sources = readerDevice.getAllSources();
							for (int i = 0; i < sources.length; i++) {
								value += sources[i].getAntennaReadPointMemReadCount();
							}
							break;
						case ANTENNA_ERASE_FAILURES:
							readPoints = readerDevice.getAllReadPoints();
							for (int i = 0; i < readPoints.length; i++) {
								if (readPoints[i] instanceof AntennaReadPoint) {
									value += ((AntennaReadPoint) readPoints[i])
											.getFailedEraseCount();
								}
							}
							break;
							
					}
					
					setValue(column, new Integer32(value));
				}
				break;
		}
		return super.getValue(column);
	}

	/**
	 * Forces a refresh of all values.
	 */
	public void forceRefresh() {
		for (int i = 0; i < lastRefreshTimes.length; i++) {
			lastRefreshTimes[i] = 0;
		}
	}
	
	/**
	 * Forces a refresh of the value of a given column.
	 * 
	 * @param column
	 *            Column
	 */
	public void forceRefresh(int column) {
		lastRefreshTimes[column] = 0;
	}
	
	/**
	 * Sets the refresh time in ms.
	 * 
	 * @param refreshTimeInMs
	 *            Refresh time in ms
	 */
	public void setRefreshTime(long refreshTimeInMs) {
		EpcgGlobalCountersTableRow.refreshTimeInMs = refreshTimeInMs;
	}

}
