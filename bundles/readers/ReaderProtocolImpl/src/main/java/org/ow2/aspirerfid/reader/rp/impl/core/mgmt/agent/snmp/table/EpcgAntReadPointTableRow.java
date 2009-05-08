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
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmLevel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util.SnmpUtil;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;
import org.apache.log4j.Logger;
import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.Gauge32;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UnsignedInteger32;
import org.snmp4j.smi.Variable;

/**
 * Forms a row of the epcgAntReadPointTable.
 */
public class EpcgAntReadPointTableRow extends SnmpTableRow {
	
	private static long refreshTimeInMs = 100; // default value of the refresh time
	
	public static final int numOfColumns = EpcglobalReaderMib.getInstance().getEpcgAntReadPointEntry().getColumnCount();
	private static Logger log = Logger.getLogger(EpcgAntReadPointTableRow.class);
	private long[] lastRefreshTimes = new long[EpcgAntReadPointTableRow.numOfColumns];
	private AntennaReadPoint antReadPoint;
	
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
	protected EpcgAntReadPointTableRow(RowObjectContainer cont)
			throws ReaderProtocolException {
		super(EpcgAntReadPointTableRow.computeIndex(cont), new Variable[numOfColumns], cont);
		antReadPoint = (AntennaReadPoint)cont.getRowObjects()[0];
	}
	
	/**
	 * Computes the index for this row
	 * 
	 * @param cont
	 *            Row object container of this row
	 * @return Index
	 */
	private static OID computeIndex(RowObjectContainer cont)
			throws ReaderProtocolException {
		AntennaReadPoint antReadPoint = (AntennaReadPoint) cont.getRowObjects()[0];
		SnmpTable table = (SnmpTable) SnmpUtil
				.getSnmpTable(TableTypeEnum.EPCG_READ_POINT_TABLE);
		OID index = table.getTableRowIndexByValue(new OctetString(antReadPoint
				.getName()), EpcglobalReaderMib.idxEpcgReadPointName);
		if (index != null) {
			return index;
		} else {
			throw new ReaderProtocolException("ERROR_READPOINT_NOT_FOUND",
					MessagingConstants.ERROR_READPOINT_NOT_FOUND,
					"Every AntennaReadPoint should be listet in the epcgReadPointTable");
		}
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
			case EpcglobalReaderMib.idxEpcgAntRdPntTagsIdentified:
				if (refresh) setValue(column, new Integer32(antReadPoint.getIdentificationCount()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntTagsNotIdentified:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedIdentificationCount()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntMemoryReadFailures:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedMemReadCount()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntReadFailureNotifEnable:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedMemReadAlarmControl().getEnabled() ? 1 : 2));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntReadFailureNotifLevel:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedMemReadAlarmControl().getLevel().toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntWriteOperations:
				if (refresh) setValue(column, new Integer32(antReadPoint.getWriteCount()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntWriteFailures:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedWriteCount()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntWriteFailuresNotifEnable:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedWriteAlarmControl().getEnabled() ? 1 : 2));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntWriteFailuresNotifLevel:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedWriteAlarmControl().getLevel().toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntKillOperations:
				if (refresh) setValue(column, new Integer32(antReadPoint.getKillCount()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntKillFailures:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedKillCount()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntKillFailuresNotifEnable:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedKillAlarmControl().getEnabled() ? 1 : 2));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntKillFailuresNotifLevel:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedKillAlarmControl().getLevel().toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntEraseOperations:
				if (refresh) setValue(column, new Integer32(antReadPoint.getEraseCount()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntEraseFailures:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedEraseCount()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntEraseFailuresNotifEnable:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedEraseAlarmControl().getEnabled() ? 1 : 2));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntEraseFailuresNotifLevel:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedEraseAlarmControl().getLevel().toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntLockOperations:
				if (refresh) setValue(column, new Integer32(antReadPoint.getLockCount()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntLockFailures:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedLockCount()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntLockFailuresNotifEnable:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedLockAlarmControl().getEnabled() ? 1 : 2));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntLockFailuresNotifLevel:
				if (refresh) setValue(column, new Integer32(antReadPoint.getFailedLockAlarmControl().getLevel().toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntPowerLevel:
				if (refresh) setValue(column, new Integer32(antReadPoint.getPowerLevel()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntNoiseLevel:
				if (refresh) setValue(column, new Integer32(antReadPoint.getNoiseLevel()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntTimeEnergized:
				if (refresh) setValue(column, new Gauge32(antReadPoint.getTimeEnergized()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntMemoryReadOperations:
				if (refresh) setValue(column, new Integer32(antReadPoint.getMemReadCount()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntReadFailureSuppressInterval:
				if (refresh) setValue(column, new UnsignedInteger32(antReadPoint.getFailedMemReadAlarmControl().getSuppressInterval()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntReadFailureSuppressions:
				if (refresh) setValue(column, new Counter32(antReadPoint.getReadFailureSuppressions()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntWriteFailureSuppressInterval:
				if (refresh) setValue(column, new UnsignedInteger32(antReadPoint.getFailedWriteAlarmControl().getSuppressInterval()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntWriteFailureSuppressions:
				if (refresh) setValue(column, new Counter32(antReadPoint.getWriteFailureSuppressions()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntKillFailureSuppressInterval:
				if (refresh) setValue(column, new UnsignedInteger32(antReadPoint.getFailedKillAlarmControl().getSuppressInterval()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntKillFailureSuppressions:
				if (refresh) setValue(column, new Counter32(antReadPoint.getKillFailureSuppressions()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntEraseFailureSuppressInterval:
				if (refresh) setValue(column, new UnsignedInteger32(antReadPoint.getFailedEraseAlarmControl().getSuppressInterval()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntEraseFailureSuppressions:
				if (refresh) setValue(column, new Counter32(antReadPoint.getEraseFailureSuppressions()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntLockFailureSuppressInterval:
				if (refresh) setValue(column, new UnsignedInteger32(antReadPoint.getFailedLockAlarmControl().getSuppressInterval()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntLockFailureSuppressions:
				if (refresh) setValue(column, new Counter32(antReadPoint.getLockFailureSuppressions()));
				break;
		}
		return super.getValue(column);
	}
	
	/**
	 * Sets the value of a column of this row.
	 * 
	 * @param column
	 *            The (zero-based) column index
	 * @param value
	 *            The new value for the specified column
	 */
	@Override
	public void setValue(int column, Variable value) {
		switch (column) {
		
			case EpcglobalReaderMib.idxEpcgAntRdPntReadFailureNotifEnable:
				antReadPoint.getFailedMemReadAlarmControl().setEnabled(
					((Integer32) value).toInt() == 1 ? true : false);
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntReadFailureNotifLevel:
				antReadPoint.getFailedMemReadAlarmControl().setLevel(
					AlarmLevel.intToEnum(((Integer32) value).toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntWriteFailuresNotifEnable:
				antReadPoint.getFailedWriteAlarmControl().setEnabled(
					((Integer32) value).toInt() == 1 ? true : false);
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntWriteFailuresNotifLevel:
				antReadPoint.getFailedWriteAlarmControl().setLevel(
					AlarmLevel.intToEnum(((Integer32) value).toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntKillFailuresNotifEnable:
				antReadPoint.getFailedKillAlarmControl().setEnabled(
					((Integer32) value).toInt() == 1 ? true : false);
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntKillFailuresNotifLevel:
				antReadPoint.getFailedKillAlarmControl().setLevel(
					AlarmLevel.intToEnum(((Integer32) value).toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntEraseFailuresNotifEnable:
				antReadPoint.getFailedEraseAlarmControl().setEnabled(
					((Integer32) value).toInt() == 1 ? true : false);
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntEraseFailuresNotifLevel:
				antReadPoint.getFailedEraseAlarmControl().setLevel(
					AlarmLevel.intToEnum(((Integer32) value).toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntLockFailuresNotifEnable:
				antReadPoint.getFailedLockAlarmControl().setEnabled(
					((Integer32) value).toInt() == 1 ? true : false);
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntLockFailuresNotifLevel:
				antReadPoint.getFailedLockAlarmControl().setLevel(
					AlarmLevel.intToEnum(((Integer32) value).toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntReadFailureSuppressInterval:
				antReadPoint.getFailedMemReadAlarmControl().setSuppressInterval(((UnsignedInteger32) value).toInt());
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntWriteFailureSuppressInterval:
				antReadPoint.getFailedWriteAlarmControl().setSuppressInterval(((UnsignedInteger32) value).toInt());
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntKillFailureSuppressInterval:
				antReadPoint.getFailedKillAlarmControl().setSuppressInterval(((UnsignedInteger32) value).toInt());
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntEraseFailureSuppressInterval:
				antReadPoint.getFailedEraseAlarmControl().setSuppressInterval(((UnsignedInteger32) value).toInt());
				break;
			case EpcglobalReaderMib.idxEpcgAntRdPntLockFailureSuppressInterval:
				antReadPoint.getFailedLockAlarmControl().setSuppressInterval(((UnsignedInteger32) value).toInt());
				break;
				
		}
		super.setValue(column, value);
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
		EpcgAntReadPointTableRow.refreshTimeInMs = refreshTimeInMs;
	}

}
