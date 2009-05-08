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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;
import org.fosstrak.epcis.model.AggregationEventType;
import org.fosstrak.epcis.model.BusinessTransactionType;
import org.fosstrak.epcis.model.EPC;
import org.fosstrak.epcis.model.EPCISEventType;
import org.fosstrak.epcis.model.ObjectEventType;
import org.fosstrak.epcis.model.QuantityEventType;
import org.fosstrak.epcis.model.QueryResults;
import org.fosstrak.epcis.model.TransactionEventType;
import org.fosstrak.epcis.utils.QueryResultsParser;
import org.ow2.aspirerfid.connectors.api.Event;
import org.ow2.aspirerfid.connectors.api.QueryResultsProcessorIfce;

/**
 * This class receives new events from the repository and encapsulates them
 * into Event objects. Then forwards them for transmition to the connector client.
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 * 
 */
public class QueryResultsProcessor extends Thread implements QueryResultsProcessorIfce {

    private static QueryResultsProcessor instance;
    private static Logger logger;
    private String result;

    static {
	logger = Logger.getLogger(QueryResultsProcessor.class);
    }

    private QueryResultsProcessor() {
    }

    private QueryResultsProcessor(String xmlQueryResult) {
	this.result = xmlQueryResult;
    }

    public static QueryResultsProcessor getInstance() {
	if (instance == null)
	    instance = new QueryResultsProcessor();

	return instance;
    }

    public void run() {
	parse(result);
    }
    
    public boolean parse(QueryResults results, String clientEndpoint)
    {
	EPCISEventType e;
	Event event = new Event();
	ArrayList<String> list;
	event.setSubscriptionId(results.getSubscriptionID());

	    List<Object> eventList = results.getResultsBody().getEventList().getObjectEventOrAggregationEventOrQuantityEvent();

	    for (Object o : eventList) {

		if (o instanceof JAXBElement<?>) {
		    o = ((JAXBElement<?>) o).getValue();
		}
		e = (EPCISEventType) o;

		if (e instanceof AggregationEventType) {
		    AggregationEventType evt = (AggregationEventType) e;
		    event.setAction(evt.getAction().toString());
		    event.setParentId(evt.getParentID());
		    list = new ArrayList<String>();
		    for(EPC epc : evt.getChildEPCs().getEpc())
		    {
			list.add(epc.getValue());
		    }
		    event.setChildEpcs(list);
		    event.setBizStepId(evt.getBizStep());
		    event.setDispositionId(evt.getDisposition());
		    event.setReadPointId(evt.getReadPoint().getId());
		    event.setBizLocationId(evt.getBizLocation().getId());
		    list = new ArrayList<String>();
		    for (BusinessTransactionType bt : evt.getBizTransactionList().getBizTransaction()) {
			list.add(bt.getValue());
		    }
		    event.setBizTransactionList(list);
		    event.setEventTime(evt.getEventTime().toGregorianCalendar().getTimeInMillis());
		}
		if (e instanceof ObjectEventType) {
		    ObjectEventType evt = (ObjectEventType) e;
		    event.setAction(evt.getAction().toString());
		    for (EPC epc : evt.getEpcList().getEpc()) {
			event.addToEpcList(epc.getValue());
		    }
		    event.setAction(evt.getAction().toString());
		    event.setBizStepId(evt.getBizStep());
		    event.setDispositionId(evt.getDisposition());
		    event.setReadPointId(evt.getReadPoint().getId());
		    event.setBizLocationId(evt.getBizLocation().getId());
		    list = new ArrayList<String>();
		    for (BusinessTransactionType bt : evt.getBizTransactionList().getBizTransaction()) {
			list.add(bt.getValue());
		    }
		    event.setBizTransactionList(list);
		    event.setEventTime(evt.getEventTime().toGregorianCalendar().getTimeInMillis());
		}
		if (e instanceof QuantityEventType) {
		    QuantityEventType evt = (QuantityEventType) e;
		    event.setEventTime(evt.getEventTime().toGregorianCalendar().getTimeInMillis());
		    event.setEpcClass(evt.getEpcClass());
		    event.setQuantity(evt.getQuantity());
		    event.setBizStepId(evt.getBizStep());
		    event.setDispositionId(evt.getDisposition());
		    event.setReadPointId(evt.getReadPoint().getId());
		    event.setBizLocationId(evt.getBizLocation().getId());
		    list = new ArrayList<String>();
		    for (BusinessTransactionType bt : evt.getBizTransactionList().getBizTransaction()) {
			list.add(bt.getValue());
		    }
		    event.setBizTransactionList(list);
		    event.setEventTime(evt.getEventTime().toGregorianCalendar().getTimeInMillis());
		}
		if (e instanceof TransactionEventType) {
		    TransactionEventType evt = (TransactionEventType) e;
		    event.setAction(evt.getAction().toString());
		    event.setParentId(evt.getParentID());
		    for (EPC epc : evt.getEpcList().getEpc()) {
			event.addToEpcList(epc.getValue());
		    }
		    event.setBizStepId(evt.getBizStep());
		    event.setDispositionId(evt.getDisposition());
		    event.setReadPointId(evt.getReadPoint().getId());
		    event.setBizLocationId(evt.getBizLocation().getId());
		    list = new ArrayList<String>();
		    for (BusinessTransactionType bt : evt.getBizTransactionList().getBizTransaction()) {
			list.add(bt.getValue());
		    }
		    event.setBizTransactionList(list);
		    event.setEventTime(evt.getEventTime().toGregorianCalendar().getTimeInMillis());
		}
		logger.info("Transmitting event");
		logger.debug("Event: "+event.toString());
		// Send the report to the client
		ConnectorTransport.transmit(event, clientEndpoint);
	    }
	    return true;
    }

    /**
     * Parses a given element from the EPCIS repository which is in XML String format, and encapsulates it in an Event object.
     * @param result The event in XML format
     */
    public void parse(String result) {
	String sid,replyEndpoint;
	try {
	    QueryResults qr = QueryResultsParser.parseQueryDocResults(new StringReader(result));
	    sid = qr.getSubscriptionID();
	    replyEndpoint = SubscriptionManager.get(sid);
	    if(replyEndpoint == null)
	    {
		logger.error("No valid reply endpoint");
		return;
	    }
	    parse(qr, replyEndpoint);
	} catch (Exception ex) {
	    logger.error(ex);
	    ex.printStackTrace();
	}

    }

    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.connectors.api.QueryResultsProcessorIfce#fetchQueryResult(java.lang.String)
     */
    public void fetchQueryResult(String result) {

	new QueryResultsProcessor(result).start();

    }
}
