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

import org.ow2.aspirerfid.reader.rp.impl.core.NotificationChannel;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.Source;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib.EpcgNotifChanSrcRowStatusEnum;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util.SnmpUtil;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;
import org.apache.log4j.Logger;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

/**
 * Forms a row of the epcgNotifChanSrcTable.
 */
public class EpcgNotifChanSrcTableRow extends SnmpTableRow {
	
	public static final int numOfColumns = EpcglobalReaderMib.getInstance()
			.getEpcgNotifChanSrcEntry().getColumnCount();

	private static Logger log = Logger
			.getLogger(EpcgNotifChanSrcTableRow.class);
	
	
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
	protected EpcgNotifChanSrcTableRow(RowObjectContainer cont)
			throws ReaderProtocolException {
		super(EpcgNotifChanSrcTableRow.computeIndex(cont), new Variable[] { new Integer32(EpcgNotifChanSrcRowStatusEnum.active) }, cont);
	}
	
	/**
	 * Protected Constructor. Use <code>getSnmpTableRow()</code> to
	 * instantiate.
	 * 
	 * @param index
	 *            Index
	 * @param values
	 *            The values to be contained in the new row
	 * @throws ReaderProtocolException
	 *             Thrown if the <code>ReaderDevice</code> instance cannot be
	 *             obtained.
	 */
	protected EpcgNotifChanSrcTableRow(OID index, Variable[] values)
			throws ReaderProtocolException {
		super(index, values, new RowObjectContainer(
				TableTypeEnum.EPCG_NOTIF_CHAN_SRC_TABLE, null));
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
		NotificationChannel notifChan = (NotificationChannel)cont.getRowObjects()[0];
		Source source = (Source)cont.getRowObjects()[1];
		SnmpTable epcgNotificationChannelTable = (SnmpTable)SnmpUtil.getSnmpTable(TableTypeEnum.EPCG_NOTIFICATION_CHANNEL_TABLE);
		SnmpTable epcgSourceTable = (SnmpTable)SnmpUtil.getSnmpTable(TableTypeEnum.EPCG_SOURCE_TABLE);
		OID notifChanIndex = epcgNotificationChannelTable.getTableRowIndexByValue(new OctetString(notifChan.getName()), EpcglobalReaderMib.idxEpcgNotifChanName);
		OID sourceIndex = epcgSourceTable.getTableRowIndexByValue(new OctetString(source.getName()), EpcglobalReaderMib.idxEpcgSrcName);
		if (notifChanIndex == null) {
			throw new ReaderProtocolException("ERROR_CHANNEL_NOT_FOUND",
					MessagingConstants.ERROR_CHANNEL_NOT_FOUND,
					"Every NotificationChannel should be listet in the epcgNotificationChannelTable");
		}
		if (sourceIndex == null) {
			throw new ReaderProtocolException("ERROR_SOURCE_NOT_FOUND",
					MessagingConstants.ERROR_SOURCE_NOT_FOUND,
					"Every Source should be listet in the epcgSourceTable");
		}
		
		OID index = new OID(notifChanIndex + "." + sourceIndex);
		return index;
	}

	/**
	 * Forces a refresh of all values.
	 */
	public void forceRefresh() {
		// do nothing
	}
	
	/**
	 * Forces a refresh of the value of a given column.
	 * 
	 * @param column
	 *            Column
	 */
	public void forceRefresh(int column) {
		// do nothing
	}
	
	/**
	 * Sets the refresh time in ms.
	 * 
	 * @param refreshTimeInMs
	 *            Refresh time in ms
	 */
	public void setRefreshTime(long refreshTimeInMs) {
		// do nothing
	}
	
}