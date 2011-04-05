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
