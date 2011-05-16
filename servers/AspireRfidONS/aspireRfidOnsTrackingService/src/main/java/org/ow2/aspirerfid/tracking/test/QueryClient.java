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

package org.ow2.aspirerfid.tracking.test;

import java.util.List;
import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.tracking.trackerdoc.model.TagEvent;
import org.ow2.aspirerfid.tracking.client.TrackingInterfaceClient;

/**
 * @author Kostas Mourtzoukos (komo) e-mail: komo@ait.edu.gr
 *
 */

public class QueryClient {

	public static final Logger LOG = Logger.getLogger(QueryClient.class);

	/**
	 * Client invoking the query function of the tracking interface
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		TrackingInterfaceClient tclient = new TrackingInterfaceClient("");
		
		List<TagEvent> rList = tclient.queryTag("urn:epc:id:gid:1.3.127");
		for (TagEvent tagevent : rList) {
			System.out.println("TagId: " + tagevent.getTag().getId());
			System.out.println("Date and Time: " + tagevent.getTag().getTime().toString());
			System.out.println("GeoTag: " + tagevent.getTag().getGeoCoords());
			System.out.println("------------");			
		}

	}
	


}
