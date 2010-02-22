/**
 * Copyright © 2008-2010, Aspire 
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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.ow2.aspirerfid.connectors.epcis.capture.CaptureClient;
import org.ow2.aspirerfid.commons.epcis.model.ActionType;
import org.ow2.aspirerfid.commons.epcis.model.BusinessTransactionListType;
import org.ow2.aspirerfid.commons.epcis.model.BusinessTransactionType;
import org.ow2.aspirerfid.commons.epcis.model.EPCISBodyType;
import org.ow2.aspirerfid.commons.epcis.model.EPCISDocumentType;
import org.ow2.aspirerfid.commons.epcis.model.EventListType;
import org.ow2.aspirerfid.commons.epcis.model.TransactionEventType;
import org.ow2.aspirerfid.commons.connector.model.SubscriptionParameters;
import org.ow2.aspirerfid.commons.utils.Configurator;

/**
 * Sends a Transaction event to the EPCIS repository with action DELETE for a selected 
 * transaction
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */
public class TransactionDeleter extends Thread{

    private static String endpoint;
    private static String timezone;
    private static Logger logger;
    
    private SubscriptionParameters parameters;
    
    static
    {
	endpoint = Configurator.getProperty("epcisCaptureIfceUrl", "http://localhost:8080/epcis/query");
	timezone = Configurator.getProperty("timeDifferenceFromUTC", null);
	logger = Logger.getLogger(TransactionDeleter.class);
    }
    
    private TransactionDeleter(SubscriptionParameters parameters)
    {
	this.parameters = parameters;
    }
    
    public void run()
    {
	try {
	    CaptureClient client = new CaptureClient(endpoint);
	    int httpResponseCode = client.capture(prepareEvent(parameters));
	    if (httpResponseCode != 200) 
	        logger.error("The DELETE event could NOT be captured!");
	    else
		logger.info("Transaction deleted");
	} catch (Exception e) {
	    logger.error(e);
	    e.printStackTrace();
	}
    }
    
    /**
     * Starts a new thread to send the transaction event
     * @param parameters The object that contains the necessary information to delete a specific transaction
     */
    public static void signalTransactionDelete(SubscriptionParameters parameters)
    {
	logger.info("Deleting transaction id: "+parameters.getTransactionId());
	new TransactionDeleter(parameters).start();
    }
    
    private EPCISDocumentType prepareEvent(SubscriptionParameters parameters)
    {
	TransactionEventType event = new TransactionEventType();
	event.setAction(ActionType.DELETE);
	
	// get the current time and set the eventTime
	XMLGregorianCalendar now = null;
	try {
	    now = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
	    event.setEventTime(now);
	} catch (DatatypeConfigurationException e) {
	    logger.error(e);
	    e.printStackTrace();
	}

	// get the current time zone and set the eventTimeZoneOffset
	if(timezone == null)
	{
        	if (now != null) {
        	    int timezone = now.getTimezone();
        	    int h = Math.abs(timezone / 60);
        	    int m = Math.abs(timezone % 60);
        	    DecimalFormat format = new DecimalFormat("00");
        	    String sign = (timezone < 0) ? "-" : "+";
        	    event.setEventTimeZoneOffset(sign + format.format(h) + ":" + format.format(m));
        	}
        	else
        	    event.setEventTimeZoneOffset("+00:00");
	}
	else
	    event.setEventTimeZoneOffset(timezone);
	
	BusinessTransactionListType list = new BusinessTransactionListType();
	BusinessTransactionType transaction = new BusinessTransactionType();
	
	if(!parameters.getTransactionType().equals("-1"))
	    transaction.setType(parameters.getTransactionType());
	
	transaction.setValue(parameters.getTransactionId());
	
	list.getBizTransaction().add(transaction);
	event.setBizTransactionList(list);
	
	// create the EPCISDocument containing a single ObjectEvent
	EPCISDocumentType epcisDoc = new EPCISDocumentType();
	EPCISBodyType epcisBody = new EPCISBodyType();
	EventListType eventList = new EventListType();
	eventList.getObjectEventOrAggregationEventOrQuantityEvent().add(event);
	epcisBody.setEventList(eventList);
	epcisDoc.setEPCISBody(epcisBody);
	epcisDoc.setSchemaVersion(new BigDecimal("1.0"));
	epcisDoc.setCreationDate(now);
	
	return epcisDoc;

    }
}
