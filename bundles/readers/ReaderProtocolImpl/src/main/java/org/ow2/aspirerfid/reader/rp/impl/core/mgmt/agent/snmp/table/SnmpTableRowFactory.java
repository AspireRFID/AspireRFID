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

import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.snmp4j.agent.mo.DefaultMOMutableRow2PCFactory;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

/**
 * The row factory which creates rows of the type <code>SnmpTableRow</code>.
 */
public class SnmpTableRowFactory extends DefaultMOMutableRow2PCFactory {
	
	private TableTypeEnum type;
	
	/**
	 * Constructor.
	 * 
	 * @param type
	 *            Table type
	 */
	public SnmpTableRowFactory(TableTypeEnum type) {
		this.type = type;
	}
	
	/**
	 * Creates a new <code>MOTableRow</code> row instance of the type
	 * <code>SnmpTableRow</code> and returns it.
	 * 
	 * @param index
	 *            the index OID for the new row.
	 * @param values
	 *            the values to be contained in the new row.
	 * @return the created <code>MOTableRow</code>.
	 * @throws UnsupportedOperationException
	 *             if the specified row cannot be created.
	 */
	@Override
	public synchronized MOTableRow createRow(OID index, Variable[] values)
			throws UnsupportedOperationException {
		return SnmpTableRow.getSnmpTableRow(type, index, values);
	}

	/**
	 * Free the row.
	 * 
	 * @param row
	 *            The row to free
	 */
	@Override
	public synchronized void freeRow(MOTableRow row) {
		super.freeRow(row);
	}

}