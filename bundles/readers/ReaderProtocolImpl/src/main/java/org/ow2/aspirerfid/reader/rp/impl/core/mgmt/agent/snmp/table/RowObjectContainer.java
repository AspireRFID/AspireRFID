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

import java.util.Arrays;

import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;


/**
 * Stores the objects that belong to a table row.
 */
public class RowObjectContainer {
	
	private TableTypeEnum type;
	private Object[] rowObjects;
	
	/**
	 * Constructor.
	 * @param type Table type
	 * @param rowObjects Stored objects
	 */
	public RowObjectContainer(TableTypeEnum type, Object[] rowObjects) {
		this.type = type;
		this.rowObjects = rowObjects;
	}
	
	/**
	 * Gets the type of the table this <code>RowObjectContainer</code> belongs to.
	 * @return Table type
	 */
	public TableTypeEnum getTableType() {
		return type;
	}
	
	/**
	 * Gets the row objects.
	 * @return Row objects
	 */
	public Object[] getRowObjects() {
		return rowObjects;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		RowObjectContainer cont = null;
		try {
			cont = (RowObjectContainer)obj;
		} catch (ClassCastException cce) {
			return false;
		}
		if (
				(type == cont.getTableType()) &&
				(Arrays.equals(rowObjects, cont.getRowObjects()))
			) {
			return true;
		}
		return false;
	}
	
}
