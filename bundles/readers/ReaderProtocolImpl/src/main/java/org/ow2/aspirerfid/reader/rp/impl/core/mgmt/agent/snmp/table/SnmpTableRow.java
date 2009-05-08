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

import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.apache.log4j.Logger;
import org.snmp4j.agent.mo.DefaultMOMutableRow2PC;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.agent.request.SubRequest;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

/**
 * Forms a SNMP table row used in the reader management implementation.
 */
public abstract class SnmpTableRow extends DefaultMOMutableRow2PC {
	
//	protected int row; // index of this row
	protected RowObjectContainer cont;
	
	private static Logger log = Logger.getLogger(SnmpTableRow.class);
	
	/**
	 * Protected Constructor. Use <code>getSnmpTableRow()</code> to instantiate.
	 * @param index Index
	 * @param values Row values
	 * @param cont Row object container which corresponds to this row
	 * @throws ReaderProtocolException Thrown if the <code>ReaderDevice</code> instance cannot be obtained.
	 */
	protected SnmpTableRow(OID index, Variable[] values, RowObjectContainer cont)
			throws ReaderProtocolException {
		super(index, values);
		this.cont = cont;
	}
	
	/**
	 * Commits this row.
	 * 
	 * @param subRequest
	 *            The sub request
	 * @param changeSet
	 *            The change set
	 * @param column
	 *            The (zero-based) column index
	 */
	@Override
	public void commit(SubRequest subRequest, MOTableRow changeSet, int column) {
		setValue(column, subRequest.getVariableBinding().getVariable());
		super.commit(subRequest, changeSet, column);
	}
	
	/**
	 * Forces a refresh of all values.
	 */
	public abstract void forceRefresh();
	
	/**
	 * Forces a refresh of the value of a given column.
	 * @param column Column
	 */
	public abstract void forceRefresh(int column);
	
	/**
	 * Sets the refresh time in ms.
	 * @param refreshTimeInMs Refresh time in ms
	 */
	public abstract void setRefreshTime(long refreshTimeInMs);
	
	/**
	 * Gets the row object container corresponding to this row.
	 * @return Managed object
	 */
	public RowObjectContainer getRowObjectContainer() {
		return cont;
	}
	
	/**
	 * Creates a <code>SnmpTableRow</code> instance according to the given
	 * <code>RowObjectContainer</code> object. The index will be chosen
	 * automatically.
	 * @param cont
	 *            Row object container
	 * @return Table row
	 */
	public static SnmpTableRow getSnmpTableRow(RowObjectContainer cont) {
		SnmpTableRow row = null;
		switch (cont.getTableType()) {
		
			case EPCG_GLOBAL_COUNTERS_TABLE:
				try {
					row = new EpcgGlobalCountersTableRow(cont);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create global counters table row");
				}
				break;
			case EPCG_READER_SERVER_TABLE:
				try {
					row = new EpcgReaderServerTableRow(cont);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create reader server table row");
				}
				break;
			case EPCG_READ_POINT_TABLE:
				try {
					row = new EpcgReadPointTableRow(cont);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create read point table row");
				}
				break;
			case EPCG_ANT_READ_POINT_TABLE:
				try {
					row = new EpcgAntReadPointTableRow(cont);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create antenna read point table row");
				}
				break;
			case EPCG_IO_PORT_TABLE:
				try {
					row = new EpcgIoPortTableRow(cont);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create io-port table row");
				}
				break;
			case EPCG_SOURCE_TABLE:
				try {
					row = new EpcgSourceTableRow(cont);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create source table row");
				}
				break;
			case EPCG_NOTIFICATION_CHANNEL_TABLE:
				try {
					row = new EpcgNotificationChannelTableRow(cont);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create notification channel table row");
				}
				break;
			case EPCG_TRIGGER_TABLE:
				try {
					row = new EpcgTriggerTableRow(cont);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create trigger table row");
				}
				break;
			case EPCG_NOTIF_TRIG_TABLE:
				try {
					row = new EpcgNotifTrigTableRow(cont);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create notif trig table row");
				}
				break;
			case EPCG_READ_TRIG_TABLE:
				try {
					row = new EpcgReadTrigTableRow(cont);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create read trig table row");
				}
				break;
			case EPCG_RD_PNT_SRC_TABLE:
				try {
					row = new EpcgRdPntSrcTableRow(cont);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create read point source table row");
				}
				break;
			case EPCG_NOTIF_CHAN_SRC_TABLE:
				try {
					row = new EpcgNotifChanSrcTableRow(cont);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create notif chan source table row");
				}
				break;
			case IF_TABLE:
				break;
			case IP_ADDR_TABLE:
				break;
			case IP_NET_TO_MEDIA_TABLE:
				break;
			case SNMP_TARGET_ADDR_TABLE:
				break;
			case SNMP_TARGET_PARAMS_TABLE:
				break;
			case SYS_OR_TABLE:
				break;
			
			// TODO: implement all cases
		}
		return row;
	}
	
	/**
	 * Creates a <code>SnmpTableRow</code> instance according to the given
	 * <code>RowObjectContainer</code> object and index.
	 * @param type
	 *            Table type
	 * @param index
	 *            Index
	 * @param values
	 *            The values to be contained in the new row
	 * @return Table row
	 */
	public static SnmpTableRow getSnmpTableRow(TableTypeEnum type, OID index, Variable[] values) {
		SnmpTableRow row = null;
		switch (type) {
		
			case EPCG_GLOBAL_COUNTERS_TABLE:
				log.error("The EpcgGlobalCountersTableRow class cannot be instantiated in this way.");
				break;
			case EPCG_READER_SERVER_TABLE:
				try {
					row = new EpcgReaderServerTableRow(index, values);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create reader server table row");
				}
				break;
			case EPCG_READ_POINT_TABLE:
				log.error("The EpcgReadPointTableRow class cannot be instantiated in this way.");
				break;
			case EPCG_ANT_READ_POINT_TABLE:
				log.error("The EpcgAntReadPointTableRow class cannot be instantiated in this way.");
				break;
			case EPCG_IO_PORT_TABLE:
				log.error("The EpcgIOPortTableRow class cannot be instantiated in this way.");
				break;
			case EPCG_SOURCE_TABLE:
				log.error("The EpcgSourceTableRow class cannot be instantiated in this way.");
				break;
			case EPCG_NOTIFICATION_CHANNEL_TABLE:
				log.error("The EpcgNotificationChannelTableRow class cannot be instantiated in this way.");
				break;
			case EPCG_TRIGGER_TABLE:
				log.error("The EpcgTriggerTableRow class cannot be instantiated in this way.");
				break;
			case EPCG_NOTIF_TRIG_TABLE:
				try {
					row = new EpcgNotifTrigTableRow(index, values);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create notif trig table row");
				}
				break;
			case EPCG_READ_TRIG_TABLE:
				try {
					row = new EpcgReadTrigTableRow(index, values);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create read trig table row");
				}
				break;
			case EPCG_RD_PNT_SRC_TABLE:
				try {
					row = new EpcgRdPntSrcTableRow(index, values);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create readpoint source table row");
				}
				break;
			case EPCG_NOTIF_CHAN_SRC_TABLE:
				try {
					row = new EpcgNotifChanSrcTableRow(index, values);
				} catch (ReaderProtocolException rpe) {
					log.error("Unable to create notif chan source table row");
				}
				break;
			case IF_TABLE:
				break;
			case IP_ADDR_TABLE:
				break;
			case IP_NET_TO_MEDIA_TABLE:
				break;
			case SNMP_TARGET_ADDR_TABLE:
				break;
			case SNMP_TARGET_PARAMS_TABLE:
				break;
			case SYS_OR_TABLE:
				break;
				
			// TODO: implement all cases
		}
		return row;
	}

}
