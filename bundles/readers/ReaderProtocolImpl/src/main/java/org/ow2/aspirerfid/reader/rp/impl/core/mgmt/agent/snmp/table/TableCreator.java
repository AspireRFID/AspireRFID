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

/**
 * This class provides functionality to create SNMP tables that can be used in
 * the reader management implementation.
 */
package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table;

import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.EpcgGlobalCountersTableRow.CounterType;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.apache.log4j.Logger;
import org.snmp4j.agent.DefaultMOServer;

public class TableCreator {
	
	private DefaultMOServer server;
	private ReaderDevice readerDevice;
	
	private static Logger log = Logger.getLogger(TableCreator.class);
	
	/**
	 * Constructor
	 * @param server Managed object server
	 * @param readerDevice Reader device
	 */
	public TableCreator(DefaultMOServer server, ReaderDevice readerDevice) {
		this.server = server;
		this.readerDevice = readerDevice;
	}
	
	/**
	 * Creates a SNMP table.
	 * @param type Table type
	 * @return The SNMP table
	 */
	public SnmpTable createTable(TableTypeEnum type) {
		switch (type) {
		
			case EPCG_GLOBAL_COUNTERS_TABLE:
				SnmpTable epcgGlobalCountersTable = (SnmpTable) EpcglobalReaderMib
					.getInstance().getEpcgGlobalCountersEntry();
				
				// add all rows
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.ANTENNA_TAGS_IDENTIFIED })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.ANTENNA_TAGS_NOT_IDENTIFIED })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.ANTENNA_MEMORY_READ_FAILURES })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.ANTENNA_WRITE_OPERATIONS })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.ANTENNA_WRITE_FAILURES })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.ANTENNA_KILL_OPERATIONS })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.ANTENNA_KILL_FAILURES })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.ANTENNA_ERASE_OPERATIONS })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.ANTENNA_LOCK_OPERATIONS })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.ANTENNA_LOCK_FAILURES })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.SOURCE_UNKNOWN_TO_GLIMPSED })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.SOURCE_GLIMPSED_TO_UNKNOWN })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.SOURCE_GLIMPSED_TO_OBSERVED })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.SOURCE_OBSERVED_TO_LOST })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.SOURCE_LOST_TO_GLIMPSED })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.SOURCE_LOST_TO_UNKNOWN })));
				epcgGlobalCountersTable.addRow(SnmpTableRow
					.getSnmpTableRow(new RowObjectContainer(
							TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
							new Object[] { CounterType.TRIGGER_MATCHES })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.ANTENNA_MEMORY_READ_OPERATIONS })));
				epcgGlobalCountersTable
					.addRow(SnmpTableRow
							.getSnmpTableRow(new RowObjectContainer(
									TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
									new Object[] { CounterType.ANTENNA_ERASE_FAILURES })));
				
				return epcgGlobalCountersTable;
				
			case EPCG_READER_SERVER_TABLE:
				SnmpTable epcgReaderServerTable = (SnmpTable)EpcglobalReaderMib.getInstance().getEpcgReaderServerEntry();
				server.addLookupListener(new SnmpTableLookupListener(epcgReaderServerTable), epcgReaderServerTable);
				return epcgReaderServerTable;
				
			case EPCG_READ_POINT_TABLE:
				SnmpTable epcgReadPointTable = (SnmpTable)EpcglobalReaderMib.getInstance().getEpcgReadPointEntry();
				server.addLookupListener(new SnmpTableLookupListener(epcgReadPointTable), epcgReadPointTable);
				return epcgReadPointTable;
				
			case EPCG_ANT_READ_POINT_TABLE:
				SnmpTable epcgAntReadPointTable = (SnmpTable)EpcglobalReaderMib.getInstance().getEpcgAntReadPointEntry();
				server.addLookupListener(new SnmpTableLookupListener(epcgAntReadPointTable), epcgAntReadPointTable);
				return epcgAntReadPointTable;
				
			case EPCG_IO_PORT_TABLE:
				SnmpTable epcgIoPortTable = (SnmpTable)EpcglobalReaderMib.getInstance().getEpcgIoPortEntry();
				server.addLookupListener(new SnmpTableLookupListener(epcgIoPortTable), epcgIoPortTable);
				return epcgIoPortTable;
				
			case EPCG_SOURCE_TABLE:
				SnmpTable epcgSourceTable = (SnmpTable)EpcglobalReaderMib.getInstance().getEpcgSourceEntry();
				server.addLookupListener(new SnmpTableLookupListener(epcgSourceTable), epcgSourceTable);
				return epcgSourceTable;
				
			case EPCG_NOTIFICATION_CHANNEL_TABLE:
				SnmpTable epcgNotificationChannelTable = (SnmpTable)EpcglobalReaderMib.getInstance().getEpcgNotificationChannelEntry();
				server.addLookupListener(new SnmpTableLookupListener(epcgNotificationChannelTable), epcgNotificationChannelTable);
				return epcgNotificationChannelTable;
				
			case EPCG_TRIGGER_TABLE:
				SnmpTable epcgTriggerTable = (SnmpTable)EpcglobalReaderMib.getInstance().getEpcgTriggerEntry();
				server.addLookupListener(new SnmpTableLookupListener(epcgTriggerTable), epcgTriggerTable);
				return epcgTriggerTable;
				
			case EPCG_NOTIF_TRIG_TABLE:
				SnmpTable epcgNotifTrigTable = (SnmpTable)EpcglobalReaderMib.getInstance().getEpcgNotifTrigEntry();
				return epcgNotifTrigTable;
				
			case EPCG_READ_TRIG_TABLE:
				SnmpTable epcgReadTrigTable = (SnmpTable)EpcglobalReaderMib.getInstance().getEpcgReadTrigEntry();
				return epcgReadTrigTable;
				
			case EPCG_RD_PNT_SRC_TABLE:
				SnmpTable epcgRdPntSrcTable = (SnmpTable)EpcglobalReaderMib.getInstance().getEpcgRdPntSrcEntry();
				return epcgRdPntSrcTable;
				
			case EPCG_NOTIF_CHAN_SRC_TABLE:
				SnmpTable epcgNotifChanSrcTable = (SnmpTable)EpcglobalReaderMib.getInstance().getEpcgNotifChanSrcEntry();
				return epcgNotifChanSrcTable;
				
		}
		return null;
	}

}
