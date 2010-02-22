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
package org.ow2.aspirerfid.connectors.client;

import javax.jws.WebService;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.connector.interfaces.ClientEventHandler;
import org.ow2.aspirerfid.commons.connector.interfaces.ConnectorClient;
import org.ow2.aspirerfid.commons.connector.model.Event;

/**
 * Implements the client part of the Connector component
 * 
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 * 
 */

@WebService(endpointInterface = "org.ow2.aspirerfid.commons.connector.interfaces.ConnectorClient")
public class ConnectorClientImpl implements ConnectorClient {

    private static ClientEventHandler client;

    private static Logger logger;
    private static boolean servletStarted;

    static {
	logger = Logger.getLogger(ConnectorClientImpl.class);
	servletStarted = false;
    }

    static {
	client = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.ow2.aspirerfid.commons.connector.interfaces.ConnectorClient#transactionObserved
     * (byte [])
     */
    public void transactionObserved(Event event) {
	logger.debug(event.toString());

	if (client != null)
	    client.handleEvent(event);
    }

    /**
     * This method defines if the connector client will be started within the
     * ERP application of within an application server
     * 
     * @param port The port that the connector client will be listening when
     *            started within the ERP application
     * @return True is it starts sucessfully; false otherwise
     */
    public boolean startStandaloneMode(int port) {

	if (!servletStarted) {
	    logger.info("Starting Server");
	    try {
		String address = "http://localhost:" + port + "/ConnectorClient/connectorClient";
		JaxWsServerFactoryBean sf = new JaxWsServerFactoryBean();
		sf.setAddress(address);
		sf.setServiceBean(this);
		sf.create();
		
		logger.info("Server started at " + address);
		servletStarted = true;
		return true;
	    } catch (Exception e) {
		logger.error(e);
		e.printStackTrace();
		return false;
	    }
	} else
	    return true;
    }

    /**
     * This defines the handler application of the event
     * 
     * @param handler
     */
    public void setEventHandler(ClientEventHandler handler) {
	client = handler;
    }
}
