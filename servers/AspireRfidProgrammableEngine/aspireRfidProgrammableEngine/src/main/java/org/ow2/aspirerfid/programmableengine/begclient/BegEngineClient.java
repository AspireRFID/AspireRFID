/*
 * Copyright (C) 2008-2010, Aspire
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
 */

package org.ow2.aspirerfid.programmableengine.begclient;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.beg.interfaces.BegInterface;
import org.ow2.aspirerfid.commons.beg.model.EventStatus;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class BegEngineClient {

	/**
	 * The BEG Engine's client
	 */
	private BegInterface begEngineClient = null;

	/** logger. */
	public static final Logger LOG = Logger.getLogger(BegEngineClient.class);

	private String begEngineEndpoint = null;

	private String DEFAULT_BegEngineEndpoint = "http://localhost:8080/aspireRfidBEG/begengine";

	public BegEngineClient(String begEndPoint) {

		if (begEndPoint.equals(null) || begEndPoint.equals("") || begEndPoint == null) {
			this.begEngineEndpoint = DEFAULT_BegEngineEndpoint;
		}
		else {

			this.begEngineEndpoint = begEndPoint;
		}

		initializeWS();

	}

	/**
	 * This method initializes the Web Service connection using CXF with Spring
	 */
	private void initializeWS() {

		// Start CXF encodeClient Creation
		// Note that it is important to keep the ProxyFactoryBean ID unique e.g.
		// in this case the "encodeClientFactory"
		JaxWsProxyFactoryBean begClientFactory = new JaxWsProxyFactoryBean();
		begClientFactory.setServiceClass(BegInterface.class);
		begClientFactory.setAddress(begEngineEndpoint);
		LOG.debug("Creating factory with endpoint: " + begEngineEndpoint);
		begEngineClient = (BegInterface) begClientFactory.create();

	}

	public EventStatus getEpcListForEvent(String eventID) {
		return begEngineClient.getEpcListForEvent(eventID);

	}

	public boolean stopBegForEven(String eventID) {
		return begEngineClient.stopBegForEven(eventID);

	}

	public List<String> getStartedEvents() {
		return begEngineClient.getStartedEvents();
	}

	public boolean startBegForEvent(VocabularyElementType vocabularyElementType, String repositoryCaptureURL, String begListeningPort) {
		return begEngineClient.startBegForEvent(vocabularyElementType, repositoryCaptureURL, begListeningPort);

	}

	public List<VocabularyElementType> getEventList(String repositoryQueryURL) {
		return begEngineClient.getEventList(repositoryQueryURL);
	}

}
