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

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.epcis.model.ArrayOfString;
import org.ow2.aspirerfid.commons.epcis.model.Poll;
import org.ow2.aspirerfid.commons.epcis.model.QueryParam;
import org.ow2.aspirerfid.commons.epcis.model.QueryParams;
import org.ow2.aspirerfid.commons.epcis.model.QueryResults;
import org.ow2.aspirerfid.commons.epcis.model.QuerySchedule;
import org.ow2.aspirerfid.commons.epcis.model.Subscribe;
import org.ow2.aspirerfid.commons.epcis.model.SubscriptionControls;
import org.ow2.aspirerfid.connectors.epcis.query.Query;
import org.ow2.aspirerfid.connectors.epcis.query.QueryControlClient;
import org.ow2.aspirerfid.commons.connector.interfaces.ConnectorEngine;
import org.ow2.aspirerfid.commons.connector.model.SubscriptionParameters;
import org.ow2.aspirerfid.commons.utils.Configurator;

/**
 * This is the entry point of the Connector service. This class implements the
 * Connector Engine API and exposes the functionality that implements though
 * XML-RPC.
 * 
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 * 
 */
@WebService(endpointInterface = "org.ow2.aspirerfid.commons.connector.interfaces.ConnectorEngine")
public class ConnectorEngineImpl implements ConnectorEngine {

    private static Logger logger;
    private static boolean loaded = false;
    private String destinationUrl;

    private static String queryName;
    private QueryControlClient client;
    private QueryResultsProcessor processor;

    static {
	logger = Logger.getLogger(ConnectorEngineImpl.class);
    }

    public ConnectorEngineImpl() {
	loadProperties();
	initialize(Configurator.getProperty("epcisQueryIfceUrl", "http://localhost:8080/epcis/query"));
    }

    public ConnectorEngineImpl(String queryUrl) {
	loadProperties();
	initialize(queryUrl);
    }

    private void loadProperties() {
	if (!loaded) {
	    try {
		Configurator.loadProperties("application.properties", this.getClass());
	    } catch (Exception e) {
		logger.error(e);
	    }
	    loaded = true;
	}
    }

    private void initialize(String queryUrl) {
	queryName = Configurator.getProperty("queryName", "SimpleEventQuery");
	destinationUrl = Configurator.getProperty("callbackDestinationUrl", "http://localhost:8899");
	configureClient(queryUrl);

	try {
	    processor = QueryResultsProcessor.getInstance();
	    QueryCallbackListener.getInstance().setResultsProcessor(processor);
	} catch (IOException e) {
	    logger.error(e);
	}
    }

    private void configureClient(String queryUrl) {
	logger.info("Setting epci url: " + queryUrl);
	client = new QueryControlClient(queryUrl);
    }

    public boolean startObservingTransaction(SubscriptionParameters parameters) {

	QueryParams params = prepareQuery(parameters);
	if (parameters.isDoPoll()) {
	    logger.debug("Polling for tid:" + parameters.getTransactionId());
	    QueryResults results = poll(params);
	    return QueryResultsProcessor.getInstance().parse(results, parameters.getReplyEndpoint());
	} else {
	    logger.debug("Registering for tid:" + parameters.getTransactionId() + " with sid:" + parameters.getSubscriptionId());
	    SubscriptionManager.add(parameters.getSubscriptionId(), parameters.getReplyEndpoint());
	    Subscribe s = prepareSubscription(parameters, params);
	    return subscribe(s);
	}
    }

    public boolean stopObservingTransaction(SubscriptionParameters parameters, boolean isComplete) {

	boolean result = false;

	try {
	    if (parameters.getSubscriptionId() != null) {
		client.unsubscribe(parameters.getSubscriptionId());
		result = true;		
		SubscriptionManager.remove(parameters.getSubscriptionId());
		
		if(isComplete)
		    TransactionDeleter.signalTransactionDelete(parameters);
		    
		if (client.getSubscriptionIds(queryName).size() == 0)
		    QueryCallbackListener.getInstance().stopRunning();
	    }
	} catch (Exception e) {
	    logger.error(e);
	}
	return result;
    }

    private QueryParams prepareQuery(SubscriptionParameters parameters) {
	Query query = new Query();
	ArrayOfString parameterArray = new ArrayOfString();
	QueryParam param = new QueryParam();
	QueryParams queryParams = new QueryParams();

	query.setReturnAggregationEvents(true);
	query.setReturnObjectEvents(true);
	query.setReturnQuantityEvents(true);
	query.setReturnTransactionEvents(true);

	if (!parameters.getTransactionType().equals("-1"))
	    param.setName("EQ_bizTransaction_" + parameters.getTransactionType());
	else
	    param.setName("EQ_bizTransaction");

	logger.debug("bizTransactionType: " + param.getName());
	logger.debug("bizTransactionId: " + parameters.getTransactionId());
	parameterArray.getString().add(parameters.getTransactionId());
	param.setValue(parameterArray);
	query.getQueryParameters().add(param);
	queryParams.getParam().add(param);

	return queryParams;
    }

    private Subscribe prepareSubscription(SubscriptionParameters parameters, QueryParams queryParams) {
	final String nullTime = "-1";
	DatatypeFactory factory;
	QuerySchedule sched = new QuerySchedule();
	Subscribe sub = new Subscribe();
	SubscriptionControls controls = new SubscriptionControls();
	
	sub.setDest(destinationUrl);
	sub.setQueryName(queryName);
	sub.setSubscriptionID(parameters.getSubscriptionId());
	sub.setParams(queryParams);
	

	if (!parameters.getQueryDayOfMonth().equals(nullTime))
	    sched.setDayOfMonth(parameters.getQueryDayOfMonth());
	if (!parameters.getQueryDayOfWeek().equals(nullTime))
	    sched.setDayOfWeek(parameters.getQueryDayOfWeek());
	if (!parameters.getQueryHour().equals(nullTime))
	    sched.setHour(parameters.getQueryHour());
	if (!parameters.getQueryMin().equals(nullTime))
	    sched.setMinute(parameters.getQueryMin());
	if (!parameters.getQuerySec().equals(nullTime))
	    sched.setSecond(parameters.getQuerySec());
	if (!parameters.getQueryMonth().equals(nullTime))
	    sched.setMonth(parameters.getQueryMonth());

	controls.setSchedule(sched);

	try {
	    factory = DatatypeFactory.newInstance();
	    
	    if (parameters.getInitialTime() != null)
		controls.setInitialRecordTime(factory.newXMLGregorianCalendar(parameters.getInitialTime()));
	    else
		controls.setInitialRecordTime(factory.newXMLGregorianCalendar(new GregorianCalendar()));
	} catch (DatatypeConfigurationException e) {
	    logger.error(e);
	}

	controls.setReportIfEmpty(parameters.isReportIfEmpty());
	sub.setControls(controls);

	return sub;
    }

    private QueryResults poll(final QueryParams params) {
	try {
	    Poll poll = new Poll();
	    poll.setQueryName("SimpleEventQuery");
	    poll.setParams(params);
	    return client.poll(poll);
	} catch (Exception e) {
	    logger.error(e);
	    return null;
	}
    }

    private boolean subscribe(final Subscribe sub) {
	try {
	    client.subscribe(sub.getQueryName(), sub.getParams(), sub.getDest(), sub.getControls(), sub.getSubscriptionID());

	    if (!QueryCallbackListener.getInstance().isRunning())
		QueryCallbackListener.getInstance().start();

	    return true;

	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error(e);
	}
	return false;
    }

}
