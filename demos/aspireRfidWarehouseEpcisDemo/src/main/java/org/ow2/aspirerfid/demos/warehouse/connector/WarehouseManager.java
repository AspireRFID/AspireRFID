/**
 * Copyright (c) 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 * 
 */
package org.ow2.aspirerfid.demos.warehouse.connector;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.connector.interfaces.ClientEventHandler;
import org.ow2.aspirerfid.commons.connector.model.Event;
import org.ow2.aspirerfid.demos.warehouse.management.UI.WMS;
import org.ow2.aspirerfid.demos.warehouse.management.tools.DeliveryItem;
import org.ow2.aspirerfid.demos.warehouse.management.tools.Shipment;

/**
 * This class handles the event subscriptions, processes the events, and updates
 * the GUI.
 * 
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 * 
 */
public class WarehouseManager implements ClientEventHandler {

	private HashMap<String, Shipment> shipments;
	private static ConnectorManager connectorManager;
	private static final Logger logger;

	static {
		logger = Logger.getLogger(WarehouseManager.class);
	}

	public WarehouseManager() {
		shipments = new HashMap<String, Shipment>();
		connectorManager = ConnectorManager.getInstance();
		connectorManager.setConnectorEventClient(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ow2.aspirerfid.connectors.api.ConnectorClient#handleEvent(org.ow2
	 * .aspirerfid.connectors.api.Event)
	 */
	public void handleEvent(Event event) {
		BigInteger delivered, expected, remaining;
		DeliveryItem item;
		String invoice = event.getSubscriptionId();
		Shipment s = shipments.get(invoice);
		List<String> epcs = event.getEpcList();
		if (s != null && epcs != null) {
			for (String epc : epcs) {
				item = s.getItemInfo(epc);
				if (item != null) {
					delivered = item.getQuantityDelivered();
					expected = item.getExpectedQuantity();
					delivered = delivered.add(BigInteger.ONE);
					remaining = expected.subtract(delivered);

					item.setQuantityDelivered(delivered);
					item.setQuantityRemain(remaining);

					s.updateItemDeliveredQuantity(epc, delivered);
					WMS.updateDeliveryTableModel(item);
				}
			}
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(event.getEventTime());

			WMS.setInvoiceIDTextField(invoice);

			if (s.isComplete()) {
				finalizeShipment(invoice);
				WMS.showDeliveredInfo(invoice);
			}
		}
	}

	public boolean addShipmentInfo(Shipment shipment) {

		if (connectorManager.registerForTransaction(shipment.getInvoiceId())) {
			logger.info("Registered for invoice " + shipment.getInvoiceId());
			shipments.put(shipment.getInvoiceId(), shipment);
			return true;
		}
		else
			return false;
	}

	private void finalizeShipment(String invoiceId) {
		connectorManager.unregisterForTransaction(invoiceId, true);
		shipments.remove(invoiceId);
	}

}
