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

import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import org.ow2.aspirerfid.reader.rp.impl.core.AntennaReadPoint;
import org.ow2.aspirerfid.reader.rp.impl.core.NotificationChannel;
import org.ow2.aspirerfid.reader.rp.impl.core.ReadPoint;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.Source;
import org.ow2.aspirerfid.reader.rp.impl.core.Trigger;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.IOPort;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.EpcgReaderServerTableRow.ServerTypeEnum;
import org.apache.log4j.Logger;
import org.snmp4j.agent.MOScope;
import org.snmp4j.agent.mo.DefaultMOTable;
import org.snmp4j.agent.mo.MOColumn;
import org.snmp4j.agent.mo.MOTableIndex;
import org.snmp4j.agent.mo.MOTableModel;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.agent.mo.snmp.RowStatus;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

/**
 * Forms a SNMP table used in the reader management implementation.
 */
public class SnmpTable extends DefaultMOTable {
	
	/**
	 * Denotes the table types.
	 */
	public enum TableTypeEnum {
		EPCG_GLOBAL_COUNTERS_TABLE,
		EPCG_READER_SERVER_TABLE,
		EPCG_READ_POINT_TABLE,
		EPCG_ANT_READ_POINT_TABLE,
		EPCG_IO_PORT_TABLE,
		EPCG_SOURCE_TABLE,
		EPCG_NOTIFICATION_CHANNEL_TABLE,
		EPCG_TRIGGER_TABLE,
		EPCG_NOTIF_TRIG_TABLE,
		EPCG_READ_TRIG_TABLE,
		EPCG_RD_PNT_SRC_TABLE,
		EPCG_NOTIF_CHAN_SRC_TABLE,
		IF_TABLE,
		IP_ADDR_TABLE,
		IP_NET_TO_MEDIA_TABLE,
		SNMP_TARGET_ADDR_TABLE,
		SNMP_TARGET_PARAMS_TABLE,
		SYS_OR_TABLE
	}
	
	private TableTypeEnum type;
	
	private static Logger log = Logger.getLogger(SnmpTable.class);
	
	/**
	 * Constructor.
	 * 
	 * @param type
	 *            Table type
	 * @param oid
	 *            OID
	 * @param indexDef
	 *            Index definition
	 * @param columns
	 *            Columns
	 */
	public SnmpTable(TableTypeEnum type, OID oid, MOTableIndex indexDef, MOColumn[] columns) {
		super(oid, indexDef, columns);
		this.type = type;
	}

	/**
	 * Constructor.
	 * 
	 * @param type
	 *            Table type
	 * @param oid
	 *            OID
	 * @param indexDef
	 *            Index definition
	 * @param columns
	 *            Columns
	 * @param model
	 *            Table model
	 */
	public SnmpTable(TableTypeEnum type, OID oid, MOTableIndex indexDef, MOColumn[] columns, MOTableModel model) {
		super(oid, indexDef, columns, model);
		this.type = type;
	}
	
	/**
	 * Gets the value of the cell instance in the specified column and row.
	 * 
	 * @param index
	 *            the row index of the cell.
	 * @param col
	 *            the column index of the cell.
	 * @return the value of the cell or <code>null</code> if such a cell does
	 *         not exist.
	 */
	@Override
	public Variable getValue(OID index, int col) {
		update();
		return super.getValue(index, col);
	}

	/**
	 * Gets the value of the cell instance with the specified instance OID.
	 * 
	 * @param cellOID
	 *            the instance OID of the requested cell.
	 * @return the value of the cell or <code>null</code> if such a cell does
	 *         not exist.
	 */
	@Override
	public Variable getValue(OID cellOID) {
		update();
		return super.getValue(cellOID);
	}

	/**
	 * Gets the indices of all rows contained in this table as a sorted
	 * <code>Vector</code>.
	 * 
	 * @return Sorted indices
	 */
	public Vector<OID> getSortedIndices() {
		Vector<OID> indices = new Vector<OID>();
		Iterator iter = getModel().iterator();
		while (iter.hasNext()) {
			indices.add(((MOTableRow)iter.next()).getIndex());
		}
		Collections.sort(indices, new Comparator<OID>() {public int compare(OID index1, OID index2) {return index1.compareTo(index2);}});
		return indices;
	}
	
	/**
	 * Gets the table type.
	 * 
	 * @return Table type
	 */
	public TableTypeEnum getType() {
		return type;
	}
	
	/**
	 * Finds the first object ID (<code>OID</code>) in the specified search
	 * range.
	 * 
	 * @param range
	 *            The <code>MOScope</code> for the search
	 * @return The <code>OID</code> that is included in the search range and
	 *         <code>null</code> if no such instances could be found
	 */
	@Override
	public OID find(MOScope range) {
		update();
		return super.find(range);
	}

	/**
	 * Updates the table.
	 */
	public void update() {
		
		Vector<RowObjectContainer> conts;
		Iterator iter;
		ReadPoint[] readPoints;
		Source[] sources;
		NotificationChannel[] notifChans;
		
		ReaderDevice readerDevice = null;
		try {
			readerDevice = ReaderDevice.getInstance();
		} catch (ReaderProtocolException rpe) {
			log.warn("Unable to obtain the ReaderDevice instance");
		}
		
		switch (type) {
			case EPCG_GLOBAL_COUNTERS_TABLE:
				// do nothing
				break;
			case EPCG_READER_SERVER_TABLE:
				if (readerDevice != null) {
					String dhcp = readerDevice.getDHCPServer();
					String[] ntp = readerDevice.getNTPservers();
					conts = new Vector<RowObjectContainer>((dhcp == null ? 0 : 1) + ntp.length);
					if (dhcp != null) {
						conts.add(new RowObjectContainer(TableTypeEnum.EPCG_READER_SERVER_TABLE, new Object[] { ServerTypeEnum.DHCP, dhcp }));
					}
					for (int i = 0; i < ntp.length; i++) {
						conts.add(new RowObjectContainer(TableTypeEnum.EPCG_READER_SERVER_TABLE, new Object[] { ServerTypeEnum.TIME, ntp[i] }));
					}
					iter = getModel().iterator();
					while (iter.hasNext()) {
						SnmpTableRow row = (SnmpTableRow) iter.next();
						if (((Integer32) row.getValue(EpcglobalReaderMib.idxEpcgReaderServerRowStatus)).toInt() == RowStatus.notInService) {
							conts.add(row.getRowObjectContainer());
						}
					}
					updateStoredRows(conts);
				}else {
					log.error(type + ": Unable to update.");
				}
				break;
			case EPCG_READ_POINT_TABLE:
				if (readerDevice != null) {
					readPoints = readerDevice.getAllReadPoints();
					conts = new Vector<RowObjectContainer>(readPoints.length);
					for (int i = 0; i < readPoints.length; i++) {
						conts.add(new RowObjectContainer(TableTypeEnum.EPCG_READ_POINT_TABLE, new Object[] {readPoints[i]}));
					}
					updateStoredRows(conts);
				} else {
					log.error(type + ": Unable to update.");
				}
				break;
			case EPCG_ANT_READ_POINT_TABLE:
				if (readerDevice != null) {
					readPoints = readerDevice.getAllReadPoints();
					conts = new Vector<RowObjectContainer>();
					for (int i = 0; i < readPoints.length; i++) {
						if (readPoints[i] instanceof AntennaReadPoint) {
							conts.add(new RowObjectContainer(TableTypeEnum.EPCG_ANT_READ_POINT_TABLE, new Object[] {readPoints[i]}));
						}
					}
					updateStoredRows(conts);
				} else {
					log.error(type + ": Unable to update.");
				}
				break;
			case EPCG_IO_PORT_TABLE:
				if (readerDevice != null) {
					IOPort[] ioPorts = readerDevice.getAllIOPorts();
					conts = new Vector<RowObjectContainer>(ioPorts.length);
					for (int i = 0; i < ioPorts.length; i++) {
						conts.add(new RowObjectContainer(TableTypeEnum.EPCG_IO_PORT_TABLE, new Object[] {ioPorts[i]}));
					}
					updateStoredRows(conts);
				} else {
					log.error(type + ": Unable to update.");
				}
				break;
			case EPCG_SOURCE_TABLE:
				if (readerDevice != null) {
					sources = readerDevice.getAllSources();
					conts = new Vector<RowObjectContainer>(sources.length);
					for (int i = 0; i < sources.length; i++) {
						conts.add(new RowObjectContainer(TableTypeEnum.EPCG_SOURCE_TABLE, new Object[] {sources[i]}));
					}
				updateStoredRows(conts);
				} else {
					log.error(type + ": Unable to update.");
				}
				break;
			case EPCG_NOTIFICATION_CHANNEL_TABLE:
				if (readerDevice != null) {
					notifChans = readerDevice.getAllNotificationChannels();
					conts = new Vector<RowObjectContainer>(notifChans.length);
					for (int i = 0; i < notifChans.length; i++) {
						conts.add(new RowObjectContainer(TableTypeEnum.EPCG_NOTIFICATION_CHANNEL_TABLE, new Object[] {notifChans[i]}));
					}
					updateStoredRows(conts);
				} else {
					log.error(type + ": Unable to update.");
				}
				break;
			case EPCG_TRIGGER_TABLE:
				if (readerDevice != null) {
					Trigger[] triggers = readerDevice.getAllTriggers();
					conts = new Vector<RowObjectContainer>(triggers.length);
					for (int i = 0; i < triggers.length; i++) {
						conts.add(new RowObjectContainer(TableTypeEnum.EPCG_TRIGGER_TABLE, new Object[] {triggers[i]}));
					}
					updateStoredRows(conts);
				} else {
					log.error(type + ": Unable to update.");
				}
				break;
			case EPCG_NOTIF_TRIG_TABLE:
				if (readerDevice != null) {
					notifChans = readerDevice.getAllNotificationChannels();
					conts = new Vector<RowObjectContainer>();
					for (int i = 0; i < notifChans.length; i++) {
						Trigger[] triggers = notifChans[i].getAllNotificationTriggers();
						for (int j = 0; j < triggers.length; j++) {
							conts.add(new RowObjectContainer(TableTypeEnum.EPCG_NOTIF_TRIG_TABLE, new Object[] { notifChans[i], triggers[j] }));
						}
					}
					iter = getModel().iterator();
					while (iter.hasNext()) {
						SnmpTableRow row = (SnmpTableRow) iter.next();
						if (((Integer32) row.getValue(EpcglobalReaderMib.idxEpcgNotifTrigRowStatus)).toInt() == RowStatus.notInService) {
							conts.add(row.getRowObjectContainer());
						}
					}
					updateStoredRows(conts);
				} else {
					log.error(type + ": Unable to update.");
				}
				break;
			case EPCG_READ_TRIG_TABLE:
				if (readerDevice != null) {
					sources = readerDevice.getAllSources();
					conts = new Vector<RowObjectContainer>();
					for (int i = 0; i < sources.length; i++) {
						Trigger[] triggers = sources[i].getAllReadTriggers();
						for (int j = 0; j < triggers.length; j++) {
							conts.add(new RowObjectContainer(TableTypeEnum.EPCG_READ_TRIG_TABLE, new Object[] { sources[i], triggers[j] }));
						}
					}
					iter = getModel().iterator();
					while (iter.hasNext()) {
						SnmpTableRow row = (SnmpTableRow) iter.next();
						if (((Integer32) row.getValue(EpcglobalReaderMib.idxEpcgReadTrigRowStatus)).toInt() == RowStatus.notInService) {
							conts.add(row.getRowObjectContainer());
						}
					}
					updateStoredRows(conts);
				} else {
					log.error(type + ": Unable to update.");
				}
				break;
			case EPCG_RD_PNT_SRC_TABLE:
				if (readerDevice != null) {
					sources = readerDevice.getAllSources();
					conts = new Vector<RowObjectContainer>();
					for (int i = 0; i < sources.length; i++) {
						readPoints = sources[i].getAllReadPoints();
						for (int j = 0; j < readPoints.length; j++) {
							conts.add(new RowObjectContainer(TableTypeEnum.EPCG_RD_PNT_SRC_TABLE, new Object[] { sources[i], readPoints[j] }));
						}
					}
					iter = getModel().iterator();
					while (iter.hasNext()) {
						SnmpTableRow row = (SnmpTableRow) iter.next();
						if (((Integer32) row.getValue(EpcglobalReaderMib.idxEpcgRdPntSrcRowStatus)).toInt() == RowStatus.notInService) {
							conts.add(row.getRowObjectContainer());
						}
					}
					updateStoredRows(conts);
				} else {
					log.error(type + ": Unable to update.");
				}
				break;
			case EPCG_NOTIF_CHAN_SRC_TABLE:
				if (readerDevice != null) {
					sources = readerDevice.getAllSources();
					conts = new Vector<RowObjectContainer>();
					for (int i = 0; i < sources.length; i++) {
						Enumeration notifChansIter = sources[i].getAllNotificationChannels().elements();
						while (notifChansIter.hasMoreElements()) {
							NotificationChannel curNotifChan = (NotificationChannel)notifChansIter.nextElement();
							conts.add(new RowObjectContainer(TableTypeEnum.EPCG_NOTIF_CHAN_SRC_TABLE, new Object[] { curNotifChan, sources[i] }));
						}
					}
					iter = getModel().iterator();
					while (iter.hasNext()) {
						SnmpTableRow row = (SnmpTableRow) iter.next();
						if (((Integer32) row.getValue(EpcglobalReaderMib.idxEpcgNotifChanSrcRowStatus)).toInt() == RowStatus.notInService) {
							conts.add(row.getRowObjectContainer());
						}
					}
					updateStoredRows(conts);
				} else {
					log.error(type + ": Unable to update.");
				}
				break;
			case IF_TABLE:
				// TODO: implement this
				break;
			case IP_ADDR_TABLE:
				// TODO: implement this
				break;
			case IP_NET_TO_MEDIA_TABLE:
				// TODO: implement this
				break;
			case SNMP_TARGET_ADDR_TABLE:
				// TODO: implement this
				break;
			case SNMP_TARGET_PARAMS_TABLE:
				// TODO: implement this
				break;
			case SYS_OR_TABLE:
				// TODO: implement this
				break;
		}
	}
	
	/**
	 * Updates the table in a way that there exists for every given
	 * <code>RowObjectContainer</code> object one table row.
	 * 
	 * @param conts
	 *            <code>RowObjectContainer</code> objects
	 */
	public void updateStoredRows(Vector<RowObjectContainer> conts) {
		Vector<RowObjectContainer> oldConts = new Vector<RowObjectContainer>(); // row object containers that are already listet in the table
		Vector<RowObjectContainer> newConts = new Vector<RowObjectContainer>(); // new row object containers that should be added to the table
		Vector<RowObjectContainer> delConts = new Vector<RowObjectContainer>(); // row object containers that should be remove from the table
		
		Vector<RowObjectContainer> storedConts = new Vector<RowObjectContainer>();
		Iterator iter = getModel().iterator();
		while (iter.hasNext()) {
			storedConts.add(((SnmpTableRow)iter.next()).getRowObjectContainer());
		}
		
		Enumeration<RowObjectContainer> elements;
		
		// find already existing and new row object containers in the table
		elements = conts.elements();
		while (elements.hasMoreElements()) {
			RowObjectContainer curCont = elements.nextElement();
			if (storedConts.contains(curCont)) {
				oldConts.add(curCont);
			} else {
				newConts.add(curCont);
			}
		}
		
		// find row object containers that should be deleted from the table
		elements = storedConts.elements();
		while (elements.hasMoreElements()) {
			RowObjectContainer curCont = elements.nextElement();
			if (!conts.contains(curCont)) {
				delConts.add(curCont);
			}
		}
		
		// find rows that should be deleted
		Vector<SnmpTableRow> delRows = new Vector<SnmpTableRow>(); // the rows that should be deleted will be stored here
		Iterator rowIterator = getModel().iterator();
		while (rowIterator.hasNext()) {
			SnmpTableRow row = (SnmpTableRow)(rowIterator.next());
			if ((row.getRowObjectContainer().getRowObjects() == null) || (delConts.contains(row.getRowObjectContainer()))) {
				delRows.add(row);
			}
		}
		
		// delete rows
		Enumeration<SnmpTableRow> delElements = delRows.elements();
		while (delElements.hasMoreElements()) {
			SnmpTableRow row = delElements.nextElement();
			removeRow(row.getIndex());
			log.debug("Row with index " + row.getIndex() + " removed");
		}
		
		// add new rows
		elements = newConts.elements();
		while (elements.hasMoreElements()) {
			RowObjectContainer curCont = elements.nextElement();
			SnmpTableRow row = SnmpTableRow.getSnmpTableRow(curCont);
			addRow(row);
			log.debug("Row with index " + row.getIndex() + " added");
		}
	}
	
	/**
	 * Gets the index of the first row whose value at the specified column
	 * matches a given value.
	 * 
	 * @param value
	 *            The value that has to match
	 * @param column
	 *            The column
	 * @return The index of the first row whose value at the specified column
	 *         matches the given value
	 */
	public OID getTableRowIndexByValue(Variable value, int column) {
		update();
		Vector<OID> indices = getSortedIndices();
		Enumeration<OID> iter = indices.elements();
		while (iter.hasMoreElements()) {
			OID curIndex = iter.nextElement();
			if (getValue(curIndex, column).equals(value))
				return curIndex;
		}
		return null;
	}
	
	/**
	 * Gets the <code>RowObjectContainer</code> of the specified row.
	 * 
	 * @param index
	 *            The row index
	 * @return The <code>RowObjectContainer</code> of the specified row
	 */
	public RowObjectContainer getRowObjContOfRow(OID index) {
		update();
		MOTableRow row = getModel().getRow(index);
		if (row != null) {
			return ((SnmpTableRow)row).getRowObjectContainer();
		}
		return null;
	}
	
//	/**
//	 * Fires a lookup event in order to update this table.
//	 */
//	private void fireLookupEvent() {
//		SnmpAgent snmpAgent = SnmpAgent.getInstance();
//		if (snmpAgent != null) {
//			DefaultMOServer server = snmpAgent.getServer();
//			OctetString[] contexts = server.getContexts();
//			for (int i = 0; i < contexts.length; i++) {
//				server.lookup(new DefaultMOQuery(
//						new DefaultMOContextScope(contexts[i], getOID(), true,
//								getOID().nextPeer(), false)));
//			}
//		}
//	}
}
