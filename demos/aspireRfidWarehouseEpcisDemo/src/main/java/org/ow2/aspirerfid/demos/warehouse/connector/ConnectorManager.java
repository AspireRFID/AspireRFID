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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.connector.interfaces.ClientEventHandler;
import org.ow2.aspirerfid.commons.connector.model.SubscriptionParameters;
import org.ow2.aspirerfid.connectors.client.ConnectorClientImpl;
import org.ow2.aspirerfid.connectors.client.RegistrationManager;
import org.ow2.aspirerfid.commons.utils.Configurator;

/**
 * This class contains operations to communicate with the connector client
 * module
 * 
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 * 
 */
public class ConnectorManager {

	private static String responseEndpoint, sec, min, hour, week, month;
	private boolean started;
	private ConnectorClientImpl connectorClient;

	private static HashMap<String, SubscriptionParameters> subscriptions;

	private static ConnectorManager manager;
	private static int port;
	public static final String transactionId;
	private static final int DEFAULT_STANDALONE_PORT = 8089;
	private static final Logger logger;

	static {
		transactionId = Configurator.getProperty("transactionId", "urn:epc:id:gid:");
		logger = Logger.getLogger(ConnectorManager.class);
		subscriptions = new HashMap<String, SubscriptionParameters>();
		try {
			port = Integer.parseInt(Configurator.getProperty("standaloneConnectorClientPort"));
		}
		catch (Exception e) {
			port = DEFAULT_STANDALONE_PORT;
		}
		String hostname = Configurator.getProperty("host", "localhost");
		responseEndpoint = "http://" + hostname + ":" + port + "/ConnectorClient/connectorClient";

		sec = Configurator.getProperty("subSec", "1,31");
		min = Configurator.getProperty("subMin", "-1");
		hour = Configurator.getProperty("subHour", "-1");
		week = Configurator.getProperty("subWeek", "-1");
		month = Configurator.getProperty("subMonth", "-1");

	}

	public static ConnectorManager getInstance() {
		if (manager == null) {
			manager = new ConnectorManager();
		}
		return manager;
	}

	private ConnectorManager() {
		started = false;
		connectorClient = new ConnectorClientImpl();

	}

	public void setConnectorEventClient(ClientEventHandler client) {
		connectorClient.setEventHandler(client);
	}

	private boolean startService() {
		if (started)
			return true;
		started = connectorClient.startStandaloneMode(port);
		return started;
	}

	/**
	 * Sends a registration message either to the embedded or the remote
	 * connector client
	 * 
	 * @param invoiceId
	 *            The invoice ID to be registered.
	 * @return True if operation suceeds; false otherwise.
	 */
	public boolean registerForTransaction(String invoiceId) {

		SubscriptionParameters params = new SubscriptionParameters();

		if (subscriptions.containsKey(invoiceId)) {
			return true;
		}
		params.setSubscriptionId(invoiceId);
		params.setTransactionType(Configurator.getProperty("bizTransType", "urn:epcglobal:fmcg:btt:receiving"));
		params.setTransactionId(invoiceId);
		params.setInitialTime((GregorianCalendar) Calendar.getInstance());
		params.setQuerySec(sec);
		params.setQueryDayOfWeek(week);
		params.setQueryDayOfMonth(month);
		params.setQueryHour(hour);
		params.setQueryMin(min);
		params.setReplyEndpoint(responseEndpoint);
		// Start local CXF HTTP transport
		if (!startService())
			return false;
		if (RegistrationManager.register(params)) {
			subscriptions.put(invoiceId, params);
			return true;
		}
		else
			return false;
	}

	/**
	 * Sends an unregister message either to the embedded or the remote
	 * connector client
	 * 
	 * @param invoiceId
	 *            The registered invoice ID
	 * @return True if operation suceeds; false otherwise.
	 */
	public boolean unregisterForTransaction(String invoiceId, boolean isComplete) {

		synchronized (subscriptions) {
			if (!subscriptions.containsKey(invoiceId))
				return false;

			if (RegistrationManager.unregister(subscriptions.get(invoiceId), isComplete)) {
				subscriptions.remove(invoiceId);
				return true;
			}
			else
				return false;
		}
	}

	/**
	 * Used when closing application to unregister all pending subscriptions
	 * without sending a transaction delete signal to the EPCIS repository
	 */
	public void cancelAllSubscriptions() {
		logger.warn("Unsubscribing from all pending subscriptions");
		synchronized (subscriptions) {
			for (Iterator<SubscriptionParameters> i = subscriptions.values().iterator(); i.hasNext();) {
				RegistrationManager.unregister(i.next(), false);
			}
		}
	}

	// private String generateRandomSubscriptionId()
	// {
	// SecureRandom sr;
	// try {
	// sr = SecureRandom.getInstance("SHA1PRNG");
	// } catch (NoSuchAlgorithmException e) {
	// sr = new SecureRandom();
	// }
	// // Get 128 random bits
	// byte[] bytes = new byte[128/8];
	// sr.nextBytes(bytes);
	//        
	// return new String(bytes);
	// }
}
