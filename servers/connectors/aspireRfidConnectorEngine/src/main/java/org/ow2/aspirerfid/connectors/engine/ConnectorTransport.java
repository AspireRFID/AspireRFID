/**
 * Copyright (c) 2008-2010, Aspire 
 * 
 * Aspire is free software; you can redistribute it and/or 
 * modify it under  the terms of the GNU Lesser General Public 
 * License version 2.1 as published by the Free Software Foundation (the 
 * "LGPL"). 
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library in the file COPYING-LGPL-2.1; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA. 
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY 
 * OF ANY KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations. 
 * 
 */

package org.ow2.aspirerfid.connectors.engine;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.ow2.aspirerfid.connectors.api.ConnectorClient;
import org.ow2.aspirerfid.connectors.api.Event;

/**
 * This class handles the operation of sending an event message to the a
 * connector client using XML-RPC
 * 
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 * 
 */
public class ConnectorTransport extends Thread {

    private Event event;
    private String connectorClientEndpoint;
    private static ConnectorClient client;
    private static final Logger logger;

    static {
	logger = Logger.getLogger(ConnectorTransport.class);
    }

    private ConnectorTransport(Event evt, String endpoint) {
	event = evt;
	connectorClientEndpoint = endpoint;
    }

    public void run() {
	this.transmit();
    }

    private void transmit() {
	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	factory.setServiceClass(ConnectorClient.class);
	factory.setAddress(connectorClientEndpoint);
	try {
	    logger.debug("Creating factory with endpoint: " + connectorClientEndpoint);
	    client = (ConnectorClient) factory.create();
	    client.transactionObserved(event);
	    logger.info("Transmition succeded");
	} catch (Exception e) {
	    logger.error("Transmition failed");
	    logger.error(e);
	}

    }

    public static void transmit(Event event, String endpoint) {
	new ConnectorTransport(event, endpoint).start();
    }

}
