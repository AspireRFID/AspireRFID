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
import java.util.Vector;

import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib.EpcgRdPntSrcRowStatusEnum;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util.SnmpUtil;
import org.apache.log4j.Logger;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.Variable;

/**
 * Forms a row of the epcgReaderServerTable.
 */
public class EpcgReaderServerTableRow extends SnmpTableRow {
	
	public static final int numOfColumns = EpcglobalReaderMib.getInstance()
			.getEpcgReaderServerEntry().getColumnCount();
	
	/**
	 * Server type enum.
	 */
	public enum ServerTypeEnum {
		DHCP,
		DNS,
		TIME
	}

	private static Logger log = Logger.getLogger(EpcgReaderServerTableRow.class);
	
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
	protected EpcgReaderServerTableRow(RowObjectContainer cont)
			throws ReaderProtocolException {
		super(EpcgReaderServerTableRow.computeIndex(cont), new Variable[] {
				new Integer32(1),
				new OctetString(new UdpAddress((String) cont.getRowObjects()[1]).getValue()),
				new Integer32(EpcgRdPntSrcRowStatusEnum.active) }, cont);
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
	protected EpcgReaderServerTableRow(OID index, Variable[] values)
			throws ReaderProtocolException {
		super(index, values, new RowObjectContainer(
				TableTypeEnum.EPCG_READER_SERVER_TABLE, null));
		// TODO: create the server, if all required values are given
		cont = new RowObjectContainer(TableTypeEnum.EPCG_READER_SERVER_TABLE, new Object[] { });
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
		ServerTypeEnum type = (ServerTypeEnum)cont.getRowObjects()[0];
		int serverType = 0;
		switch (type) {
			case DHCP:
				serverType = 1;
				break;
			case DNS:
				serverType = 2;
				break;
			case TIME:
				serverType = 3;
				break;
		}
		
		SnmpTable table = (SnmpTable)SnmpUtil.getSnmpTable(TableTypeEnum.EPCG_READER_SERVER_TABLE);
		Vector<OID> indices = table.getSortedIndices();
		Collections.sort(indices, new Comparator<OID>() {public int compare(OID index1, OID index2) {return new Integer(index1.last()).compareTo(new Integer(index2.last()));}});
		
		int serverNumber = 1;
		if (!indices.isEmpty() && (indices.firstElement().last() == 1)) {
			Enumeration<OID> elements = indices.elements();
			while (elements.hasMoreElements()) {
				serverNumber = elements.nextElement().last() + 1;
				Enumeration<OID> iter = indices.elements();
				boolean ok = true;
				while (iter.hasMoreElements()) {
					if (iter.nextElement().last() == serverNumber) ok = false;
				}
				if (ok) break;
			}
		}
		
		return new OID(serverType + "." + serverNumber);
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
			case EpcglobalReaderMib.idxEpcgReaderServerAddressType:
				// TODO: do something
				break;
			case EpcglobalReaderMib.idxEpcgReaderServerAddress:
				// TODO: do something
				break;
		}
		super.setValue(column, value);
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

