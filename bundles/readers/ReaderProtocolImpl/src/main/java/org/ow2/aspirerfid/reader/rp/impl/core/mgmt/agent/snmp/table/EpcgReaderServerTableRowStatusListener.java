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

import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.apache.log4j.Logger;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.agent.mo.snmp.RowStatus;
import org.snmp4j.agent.mo.snmp.RowStatusEvent;
import org.snmp4j.agent.mo.snmp.RowStatusListener;
import org.snmp4j.smi.OID;

/**
 * Row status listener to be used with the
 * <code>epcgReaderServerRowStatus</code> column.
 */
public class EpcgReaderServerTableRowStatusListener implements RowStatusListener {
	
	/**
	 * The logger.
	 */
	private static Logger log = Logger.getLogger(EpcgReaderServerTableRowStatusListener.class);
	
	/**
	 * The reader device.
	 */
	private ReaderDevice readerDevice;
	
	/**
	 * The constructor.
	 * 
	 * @param readerDevice
	 *            The reader device
	 */
	public EpcgReaderServerTableRowStatusListener(ReaderDevice readerDevice) {
		this.readerDevice = readerDevice;
	}

	/**
	 * Called whenever the row status changed.
	 * 
	 * @param event
	 *            Row status event
	 */
	public void rowStatusChanged(RowStatusEvent event) {
		if (event.getNewStatus() == RowStatus.destroy) {
			MOTableRow row = event.getRow();
			OID rowIndex = row.getIndex();
			// TODO: remove the server
		}
	}

}
