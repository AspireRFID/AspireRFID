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

import java.util.Enumeration;
import java.util.Vector;

import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.Source;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.AdministrativeStatus;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmLevel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util.SnmpUtil;
import org.apache.log4j.Logger;
import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.Gauge32;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UnsignedInteger32;
import org.snmp4j.smi.Variable;

/**
 * Forms a row of the epcgSourceTable.
 */
public class EpcgSourceTableRow extends SnmpTableRow {
	
	private static long refreshTimeInMs = 100; // default value of the refresh time
	
	public static final int numOfColumns = EpcglobalReaderMib.getInstance().getEpcgSourceEntry().getColumnCount();
	private static Logger log = Logger.getLogger(EpcgSourceTableRow.class);
	private long[] lastRefreshTimes = new long[EpcgSourceTableRow.numOfColumns];
	private Source source;
	
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
	protected EpcgSourceTableRow(RowObjectContainer cont)
			throws ReaderProtocolException {
		super(EpcgSourceTableRow.computeIndex(cont), new Variable[numOfColumns], cont);
		source = (Source)cont.getRowObjects()[0];
	}
	
	/**
	 * Computes the index for this row
	 * 
	 * @param cont
	 *            Row object container of this row
	 * @return Index
	 */
	private static OID computeIndex(RowObjectContainer cont) {
		SnmpTable table = (SnmpTable)SnmpUtil.getSnmpTable(TableTypeEnum.EPCG_SOURCE_TABLE);
		Vector<OID> indices = table.getSortedIndices();
		
		Enumeration<OID> elements = indices.elements();
		while (elements.hasMoreElements()) {
			OID tempIndex = elements.nextElement().nextPeer();
			if (!indices.contains(tempIndex)) return tempIndex;
		}
		return new OID("1");
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
			case EpcglobalReaderMib.idxEpcgSrcName:
				if (refresh) setValue(column, new OctetString(source.getName()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcReadCyclesPerTrigger:
				if (refresh) setValue(column, new UnsignedInteger32(source.getReadCyclesPerTrigger()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcReadDutyCycle:
				if (refresh) setValue(column, new Gauge32(source.getMaxReadDutyCycles()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcReadTimeout:
				if (refresh) setValue(column, new UnsignedInteger32(source.getReadTimeout()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcGlimpsedTimeout:
				if (refresh) setValue(column, new UnsignedInteger32(source.getGlimpsedTimeout()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcObservedThreshold:
				if (refresh) setValue(column, new UnsignedInteger32(source.getObservedThreshold()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcObservedTimeout:
				if (refresh) setValue(column, new UnsignedInteger32(source.getObservedTimeout()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcLostTimeout:
				if (refresh) setValue(column, new UnsignedInteger32(source.getLostTimeout()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcUnknownToGlimpsedTrans:
				if (refresh) setValue(column, new Gauge32(source.getUnknownToGlimpsedCount()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcGlimpsedToUnknownTrans:
				if (refresh) setValue(column, new Gauge32(source.getGlimpsedToUnknownCount()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcGlimpsedToObservedTrans:
				if (refresh) setValue(column, new Gauge32(source.getGlimpsedToObservedCount()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcObservedToLostTrans:
				if (refresh) setValue(column, new Gauge32(source.getObservedToLostCount()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcLostToGlimpsedTrans:
				if (refresh) setValue(column, new Gauge32(source.getLostToGlimpsedCount()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcLostToUnknownTrans:
				if (refresh) setValue(column, new Gauge32(source.getLostToUnknownCount()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcAdminStatus:
				if (refresh) setValue(column, new Integer32(source.getAdminStatus().toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcOperStatus:
				if (refresh) setValue(column, new Integer32(source.getOperStatus().toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcOperStatusNotifEnable:
				if (refresh) setValue(column, new Integer32(source.getOperStatusAlarmControl().getEnabled() ? 1 : 2));
				break;
			case EpcglobalReaderMib.idxEpcgSrcOperStatusNotifFromState:
				if (refresh) setValue(column, SnmpUtil.operStateToBITS(source.getOperStatusAlarmControl().getTriggerFromState()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcOperStatusNotifToState:
				if (refresh) setValue(column, SnmpUtil.operStateToBITS(source.getOperStatusAlarmControl().getTriggerToState()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcOperStatusNotifyLevel:
				if (refresh) setValue(column, new Integer32(source.getOperStatusAlarmControl().getLevel().toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcSupportsWriteOperations:
				try {
					if (refresh) setValue(column, new Integer32(source.supportsWriteOperations() ? 1 : 2));
				} catch (ReaderProtocolException rpe) {
					log.error(rpe.getMessage());
				}
				break;
			case EpcglobalReaderMib.idxEpcgSrcOperStateSuppressInterval:
				if (refresh) setValue(column, new UnsignedInteger32(source.getOperStatusAlarmControl().getSuppressInterval()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcOperStateSuppressions:
				if (refresh) setValue(column, new Counter32(source.getOperStateSuppressions()));
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
			case EpcglobalReaderMib.idxEpcgSrcReadCyclesPerTrigger:
				source.setReadCyclesPerTrigger(((UnsignedInteger32) value).toInt());
				break;
			case EpcglobalReaderMib.idxEpcgSrcReadDutyCycle:
				source.setMaxReadDutyCycles(((Gauge32) value).toInt());
				break;
			case EpcglobalReaderMib.idxEpcgSrcReadTimeout:
				source.setReadTimeout(((UnsignedInteger32) value).toInt());
				break;
			case EpcglobalReaderMib.idxEpcgSrcGlimpsedTimeout:
				try {
					source.setGlimpsedTimeout(((UnsignedInteger32) value).toInt());
				} catch (ReaderProtocolException rpe) {
					log.error("Cannot write GlimpsedTimeout");
				}
				break;
			case EpcglobalReaderMib.idxEpcgSrcObservedThreshold:
				try {
					source.setObservedThreshold(((UnsignedInteger32) value).toInt());
				} catch (ReaderProtocolException rpe) {
					log.error("Cannot write ObservedThreshold");
				}
				break;
			case EpcglobalReaderMib.idxEpcgSrcObservedTimeout:
				try {
					source.setObservedTimeout(((UnsignedInteger32) value).toInt());
				} catch (ReaderProtocolException rpe) {
					log.error("Cannot write ObservedTimeout");
				}
				break;
			case EpcglobalReaderMib.idxEpcgSrcLostTimeout:
				try {
					source.setLostTimeout(((UnsignedInteger32) value).toInt());
				} catch (ReaderProtocolException rpe) {
					log.error("Cannot write LostTimeout");
				}
				break;	
			case EpcglobalReaderMib.idxEpcgSrcAdminStatus:
				source.setAdminStatus(AdministrativeStatus
					.intToEnum(((Integer32) value).toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcOperStatusNotifEnable:
				source.getOperStatusAlarmControl().setEnabled(
					((Integer32) value).toInt() == 1 ? true : false);
				break;
			case EpcglobalReaderMib.idxEpcgSrcOperStatusNotifFromState:
				source.getOperStatusAlarmControl().setTriggerFromState(SnmpUtil.bitsToOperState((OctetString)value));
				break;
			case EpcglobalReaderMib.idxEpcgSrcOperStatusNotifToState:
				source.getOperStatusAlarmControl().setTriggerToState(SnmpUtil.bitsToOperState((OctetString)value));
				break;
			case EpcglobalReaderMib.idxEpcgSrcOperStatusNotifyLevel:
				source.getOperStatusAlarmControl().setLevel(
					AlarmLevel.intToEnum(((Integer32) value).toInt()));
				break;
			case EpcglobalReaderMib.idxEpcgSrcOperStateSuppressInterval:
				source.getOperStatusAlarmControl().setSuppressInterval(((UnsignedInteger32) value).toInt());
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
		EpcgSourceTableRow.refreshTimeInMs = refreshTimeInMs;
	}

}
