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

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.connector.interfaces.ConnectorEngine;
import org.ow2.aspirerfid.commons.connector.model.SubscriptionParameters;
import org.ow2.aspirerfid.commons.utils.Configurator;

/**
 * This class is used to handle event registration operations
 * through SOAP web services.
 * 
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */
public class RegistrationManager {

    private static String connectorEngineEndpoint; 
    private static Logger logger;
    static ConnectorEngine client;
    static
    {
	logger = Logger.getLogger(RegistrationManager.class);
	connectorEngineEndpoint = Configurator.getProperty("connectorServer", "http://localhost:8080/aspireRfidConnectorEngine/connector");
	
	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	factory.setServiceClass(ConnectorEngine.class);
	factory.setAddress(connectorEngineEndpoint);
	logger.debug("Creating factory with endpoint: "+connectorEngineEndpoint);
	client = (ConnectorEngine) factory.create();
    }

    public static boolean register(String tid, String transactionType, String sid)
    {
	SubscriptionParameters params = new SubscriptionParameters();
	params.setTransactionId(tid);
	params.setTransactionType(transactionType);
	params.setSubscriptionId(sid);
	params.setQuerySec("1,31");
	params.setReplyEndpoint("http://localhost:8080/ConnectorClient-1.0/connectorClient");
	
	return register(params);
	
    }
    
    public static boolean unregister(String sid)
    {
	SubscriptionParameters params = new SubscriptionParameters();
	params.setSubscriptionId(sid);
	try
	{
	    return client.stopObservingTransaction(params,false);
	    
	}catch(Exception e)
	{
	    logger.error(e);
	    return false;
	}
    }
    
    public static boolean register (SubscriptionParameters parameters)
    {
	try {
	    client.startObservingTransaction(parameters);
	    return true;
	}catch(Exception e)
	{
	    logger.error(e);
	    return false;
	}
    }
    
    public static boolean unregister(SubscriptionParameters parameters, boolean isComplete)
    {	
	try
	{
	    return client.stopObservingTransaction(parameters, isComplete);
	    
	}catch(Exception e)
	{
	    logger.error(e);
	    return false;
	}
    }
}
