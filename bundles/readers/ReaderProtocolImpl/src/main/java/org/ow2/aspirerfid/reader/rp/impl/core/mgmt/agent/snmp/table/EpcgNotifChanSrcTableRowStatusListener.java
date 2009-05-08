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
import org.ow2.aspirerfid.reader.rp.impl.core.Source;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util.SnmpUtil;
import org.apache.log4j.Logger;
import org.snmp4j.agent.mo.snmp.RowStatus;
import org.snmp4j.agent.mo.snmp.RowStatusEvent;
import org.snmp4j.agent.mo.snmp.RowStatusListener;
import org.snmp4j.smi.OID;

/**
 * Row status listener to be used with the
 * <code>epcgNotifChanSrcRowStatus</code> column.
 */
public class EpcgNotifChanSrcTableRowStatusListener implements RowStatusListener {
	
	/**
	 * The logger.
	 */
	private static Logger log = Logger.getLogger(EpcgNotifChanSrcTableRowStatusListener.class);
	
	/**
	 * The last <code>RowStatusEvent</code> object received.
	 */
	private RowStatusEvent lastEvent;

	/**
	 * Called whenever the row status changed.
	 * 
	 * @param event
	 *            Row status event
	 */
	public void rowStatusChanged(RowStatusEvent event) {
		if (!areRowStatusEventsEqual(event, lastEvent)) {
			lastEvent = event;
			SnmpTableRow row = (SnmpTableRow) event.getRow();
			OID rowIndex = row.getIndex();
			OID notifChanIndex = new OID(new int[] { rowIndex.get(0) });
			OID sourceIndex = new OID(new int[] { rowIndex.get(1) });
			SnmpTable epcgNotificationChannelTable = (SnmpTable)SnmpUtil.getSnmpTable(TableTypeEnum.EPCG_NOTIFICATION_CHANNEL_TABLE);
			SnmpTable epcgSourceTable = (SnmpTable)SnmpUtil.getSnmpTable(TableTypeEnum.EPCG_SOURCE_TABLE);
			NotificationChannel notifChan;
			Source source;
			try {
				notifChan = (NotificationChannel) epcgNotificationChannelTable.getRowObjContOfRow(notifChanIndex).getRowObjects()[0];
			} catch (NullPointerException npe) {
				log.warn("There is no notification channel with index " + notifChanIndex);
				return;
			}
			try {
				source = (Source) epcgSourceTable.getRowObjContOfRow(sourceIndex).getRowObjects()[0];
			} catch (NullPointerException npe) {
				log.warn("There is no source with index " + sourceIndex);
				return;
			}
			row.cont = new RowObjectContainer(TableTypeEnum.EPCG_NOTIF_CHAN_SRC_TABLE, new Object[] { notifChan, source });
			
			if ((event.getNewStatus() == RowStatus.active) || (event.getNewStatus() == RowStatus.createAndGo)) {
				source.addNotificationChannel(notifChan);
			}
			else if (event.getNewStatus() == RowStatus.destroy) {
				source.removeNotificationChannel(notifChan);
			}
			else if ((event.getNewStatus() == RowStatus.notInService) && (event.getOldStatus() == RowStatus.active)) {
				source.removeNotificationChannel(notifChan);
			}
		} else {
			lastEvent = event;
		}
	}
	
	/**
	 * Compares to <code>RowStatusEvent</code> objects for equality.
	 * 
	 * @param event1
	 *            First <code>RowStatusEvent</code> object
	 * @param event2
	 *            Second <code>RowStatusEvent</code> object
	 * @return <code>true</code> if the events are equal, <code>false</code>
	 *         otherwise
	 */
	private boolean areRowStatusEventsEqual(RowStatusEvent event1, RowStatusEvent event2) {
		if ((event1 == null) || (event2 == null)) return false;
		return (event1.getSource().equals(event2.getSource()))
				&& (event1.getRow().getIndex().toString().equals(event2
						.getRow().getIndex().toString()))
				&& (event1.getNewStatus() == event2.getNewStatus());
	}

}
