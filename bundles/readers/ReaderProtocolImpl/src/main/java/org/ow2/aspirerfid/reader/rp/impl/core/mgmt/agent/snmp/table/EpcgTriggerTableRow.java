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
import org.ow2.aspirerfid.reader.rp.impl.core.Trigger;
import org.ow2.aspirerfid.reader.rp.impl.core.TriggerType;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util.SnmpUtil;
import org.apache.log4j.Logger;
import org.snmp4j.smi.Gauge32;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

/**
 * Forms a row of the epcgTriggerTable.
 */
public class EpcgTriggerTableRow extends SnmpTableRow {
	
	private static long refreshTimeInMs = 100; // default value of the refresh time
	
	public static final int numOfColumns = EpcglobalReaderMib.getInstance().getEpcgTriggerEntry().getColumnCount();
	private static Logger log = Logger.getLogger(EpcgTriggerTableRow.class);
	private long[] lastRefreshTimes = new long[EpcgTriggerTableRow.numOfColumns];
	private Trigger trigger;
	private int triggerTypeInt;
	
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
	protected EpcgTriggerTableRow(RowObjectContainer cont)
			throws ReaderProtocolException {
		super(EpcgTriggerTableRow.computeIndex(cont), new Variable[numOfColumns], cont);
		trigger = (Trigger)cont.getRowObjects()[0];
		
		String type = trigger.getType();
		if (type.equals(TriggerType.NONE)) triggerTypeInt = 1;
		else if (type.equals(TriggerType.TIMER)) triggerTypeInt = 2;
		else if (type.equals(TriggerType.CONTINUOUS)) triggerTypeInt = 3;
		else if (type.equals(TriggerType.IO_EDGE)) triggerTypeInt = 4;
		else if (type.equals(TriggerType.VENDOR_EXTENSION)) triggerTypeInt = 5;
		else triggerTypeInt = 0;
	}
	
	/**
	 * Computes the index for this row
	 * 
	 * @param cont
	 *            Row object container of this row
	 * @return Index
	 */
	private static OID computeIndex(RowObjectContainer cont) {
		SnmpTable table = (SnmpTable)SnmpUtil.getSnmpTable(TableTypeEnum.EPCG_TRIGGER_TABLE);
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
			case EpcglobalReaderMib.idxEpcgTrigName:
				if (refresh) setValue(column, new OctetString(trigger.getName()));
				break;
			case EpcglobalReaderMib.idxEpcgTrigType:
				if (refresh) setValue(column, new Integer32(triggerTypeInt));
				break;
			case EpcglobalReaderMib.idxEpcgTrigParameters:
				if (refresh) setValue(column, new OctetString(trigger.getValue()));
				break;
			case EpcglobalReaderMib.idxEpcgTriggerMatches:
				if (refresh) setValue(column, new Gauge32(trigger.getFireCount()));
				break;
			case EpcglobalReaderMib.idxEpcgTrigIoPort:
				// we don't associate triggers with IOPorts
				if (refresh) setValue(column, new OID("0.0"));
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
		EpcgTriggerTableRow.refreshTimeInMs = refreshTimeInMs;
	}

}
