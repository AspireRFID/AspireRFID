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

package org.ow2.aspirerfid.tracking.client;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.TagEvent;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.Trackerdocument;
import org.ow2.aspirerfid.tracking.TrackingInterface;


/**
 * @author Mourtzoukos Konstantinos {email: komo@ait.edu.gr}
 *
 */
public class TrackingInterfaceClient {


	private TrackingInterface trackingClient = null;
	
	/** logger. */
	public static final Logger LOG = Logger.getLogger(TrackingInterfaceClient.class);
	
	private String triEndpoint = null;
	private String DEFAULT_triEndpoint = "http://localhost:8080/aspireRfidTracking/tracking";

	public TrackingInterfaceClient(String triEndpoint) {
		if(triEndpoint.equals("")){
			this.triEndpoint = DEFAULT_triEndpoint;
		}
		else this.triEndpoint = triEndpoint;
		
		initializeWS();
		
	}
	
	private void initializeWS() {
		// Start CXF encodeClient Creation
		// Note that it is important to keep the ProxyFactoryBean ID unique e.g.
		// in this case the "encodeClientFactory"
		JaxWsProxyFactoryBean trackingClientFactory = new JaxWsProxyFactoryBean();
		trackingClientFactory.setServiceClass(TrackingInterface.class);
		trackingClientFactory.setAddress(triEndpoint);
		LOG.debug("Creating factory with endpoint: " + triEndpoint);
		trackingClient = (TrackingInterface) trackingClientFactory.create();
	}
	
	public boolean reportTagList(Trackerdocument tdoc){
		
		Boolean response = trackingClient.report(tdoc);
		
		LOG.debug("Encode Response ID: " + response);
		
		return response;	
	}
	
	public List<TagEvent> queryTag(String epcId){
		List<TagEvent> response = trackingClient.query(epcId);
		
		LOG.debug("Querying for tag:" + epcId);
		
		return response;
	}

}
