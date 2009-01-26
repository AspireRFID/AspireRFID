/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.app.epcis.client.widget.gmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT;

import com.mapitz.gwt.googleMaps.client.GLatLng;
import com.mapitz.gwt.googleMaps.client.GLatLngBounds;
import com.mapitz.gwt.googleMaps.client.GMap2;
import com.mapitz.gwt.googleMaps.client.GMarker;
import com.mapitz.gwt.googleMaps.client.GMarkerEventManager;
import com.mapitz.gwt.googleMaps.client.GPolyline;

/**
 * Google map functions helper.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class GMapHelper {

	// /**
	// * Add overlay to the google map
	// *
	// * @param gmap
	// * google map
	// * @param report
	// * List<ReportGroupListMemberGWT>
	// * @param track
	// * tag tracking
	// * @param infoWindow
	// * info window
	// */
	// public static void addCustomOverlay(GMap2 gmap, List reports, boolean
	// track, boolean infoWindow) {
	// GMarkerEventManager markerEventManager =
	// GMarkerEventManager.getInstance();
	// // List already sorted by date
	// List customReports = (List) reports; // =
	// // sortByDateReportGroupListMember(reports);
	// // delete reports without GPS coordinates
	// customReports = reportWithGPSCoordinates(customReports);
	// // regroup by GPS Coordinates
	// Map regrouped = regroupByGPSCoordinates(customReports);
	// Set keySet = regrouped.keySet();
	// GLatLng[] latlng = new GLatLng[keySet.size()];
	//
	// // Display a Marker with InfoWindow associated for each GPS Coordinates
	// int i = 0;
	// int size = keySet.size();
	// for (Iterator iter = keySet.iterator(); iter.hasNext();) {
	// String GPSCoord = (String) iter.next();
	//
	// GMarker gmarker = null;
	// double lat = 0;
	// double lng = 0;
	// String[] splitted = GPSCoord.split(",");
	// lat = Double.parseDouble(splitted[0]);
	// lng = Double.parseDouble(splitted[1]);
	// latlng[i] = new GLatLng(lat, lng);
	//
	// if (infoWindow) {
	// gmarker = new GMarker(latlng[i]);
	// gmap.addOverlay(gmarker);
	// markerEventManager.addOnClickListener(gmarker, new
	// MarkerClickListener(new InfoWindowTab(
	// (List) regrouped.get(GPSCoord))));
	//
	// if (i == 0) {
	// gmarker.setImage("img/marker_green.png");
	// }
	//
	// if (i + 1 == size) {
	// gmarker.setImage("img/marker-end.png");
	//
	// }
	// }
	// i++;
	// }
	//
	// if (track) {
	// GPolyline lines = null;
	// lines = new GPolyline(latlng);
	// gmap.addOverlay(lines);
	// }
	// }

	/**
	 * TODO Javadoc
	 * 
	 * @param gmap
	 * @param reports
	 * @param track
	 * @param infoWindow
	 * @param partner
	 */
	public static void addCustomOverlay(GMap2 gmap, List reports,
			boolean track, boolean infoWindow, boolean partner) {
		GMarkerEventManager markerEventManager = GMarkerEventManager
				.getInstance();
		// List already sorted by date
		List customReports = reports;

		// delete reports without GPS coordinates
		customReports = reportWithGPSCoordinates(customReports);

		// regroup by GPS Coordinates
		Map regrouped = regroupByGPSCoordinates(customReports);

		// clear gmap
		gmap.clearOverlays();

		String gpsBegin = ((ReportGroupListMemberGWT) customReports.get(0))
				.getGpsCoordinate();
		String gpsEnd = ((ReportGroupListMemberGWT) customReports
				.get(customReports.size() - 1)).getGpsCoordinate();

		int size;
		// Display a Marker with InfoWindow associated for each GPS Coordinates
		if (infoWindow) {
			Set keySet = regrouped.keySet();
			size = keySet.size();
			for (Iterator iter = keySet.iterator(); iter.hasNext();) {
				String GPSCoord = (String) iter.next();
				GMarker gmarker = null;

				double lat = 0;
				double lng = 0;
				String[] splitted = GPSCoord.split(",");
				lat = Double.parseDouble(splitted[0]);
				lng = Double.parseDouble(splitted[1]);
				GLatLng pos = new GLatLng(lat, lng);

				gmarker = new GMarker(pos);
				gmap.addOverlay(gmarker);
				markerEventManager.addOnClickListener(gmarker,
						new MarkerClickListener(new InfoWindowTab(
								(List) regrouped.get(GPSCoord))));

				if (GPSCoord.equals(gpsBegin)) {
					gmarker.setImage("img/marker-end.png");
				}

				if (GPSCoord.equals(gpsEnd)) {
					gmarker.setImage("img/marker_green.png");
				}
			}
		}

		// Track
		if (track) {
			size = customReports.size();
			GLatLng[] latlng = new GLatLng[2];
			double lat;
			double lng;
			String GPSCoord;
			String[] splitted;
			ReportGroupListMemberGWT beginRGLM;
			if (size > 1) {
				beginRGLM = (ReportGroupListMemberGWT) customReports.get(0);
				GPSCoord = beginRGLM.getGpsCoordinate();
				lat = 0;
				lng = 0;
				splitted = GPSCoord.split(",");
				lat = Double.parseDouble(splitted[0]);
				lng = Double.parseDouble(splitted[1]);
				latlng[0] = new GLatLng(lat, lng);

				for (int i = 1; i < size; i++) {
					ReportGroupListMemberGWT endPoint = (ReportGroupListMemberGWT) customReports
							.get(i);
					GPSCoord = endPoint.getGpsCoordinate();

					lat = 0;
					lng = 0;
					splitted = GPSCoord.split(",");
					lat = Double.parseDouble(splitted[0]);
					lng = Double.parseDouble(splitted[1]);
					latlng[1] = new GLatLng(lat, lng);

					GPolyline lines = null;
					if (partner) {
						lines = new GPolyline(latlng,
								getColor(endPoint.getId()));
					} else {
						lines = new GPolyline(latlng);
					}
					gmap.addOverlay(lines);

					latlng[0] = latlng[1];
					beginRGLM = endPoint;
				}
			}

		}
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param i
	 * @return
	 */
	private static String getColor(int i) {
		switch (i) {
		case 0:
			return "#0000FF";
		case 1:
			return "#00FF00";
		case 2:
			return "#FF0000";
		case 3:
			return "#FF9000";
		case 4:
			return "#FF8CE6";
		default:
			int r = Math.min(255, i * 10 + 16);
			int g = Math.min(255, i * 10 + 16);
			int b = Math.min(255, i * 30 + 16);
			return "#" + Integer.toHexString(r) + Integer.toHexString(g)
					+ Integer.toHexString(b);
		}

	}

	/**
	 * TODO Javadoc
	 * 
	 * @param reports
	 * @return
	 */
	private static List reportWithGPSCoordinates(List reports) {
		int size = reports.size();
		List result = new ArrayList();

		for (int i = 0; i < size; i++) {
			ReportGroupListMemberGWT e = (ReportGroupListMemberGWT) reports
					.get(i);
			if (e.getGpsCoordinate() != null) {
				result.add(e);
			}
		}
		return result;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param reports
	 * @return
	 */
	private static Map regroupByGPSCoordinates(List reports) {
		int size = reports.size();
		Map result = new HashMap();

		for (int i = 0; i < size; i++) {
			ReportGroupListMemberGWT r = (ReportGroupListMemberGWT) reports
					.get(i);
			if (result.containsKey(r.getGpsCoordinate())) {
				((List) result.get(r.getGpsCoordinate())).add(r);
			} else {
				List list = new ArrayList();
				list.add(r);
				result.put(r.getGpsCoordinate(), list);
			}
		}

		return result;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param gmap
	 * @param markers
	 */
	public static void focusMarker(GMap2 gmap, List markers) {
		// compute barycenter
		double min_lat, min_lng, max_lat, max_lng;

		if (markers.size() > 0) {
			// init value
			ReportGroupListMemberGWT init = (ReportGroupListMemberGWT) markers
					.get(0);
			String s = init.getGpsCoordinate();
			String[] splitted = s.split(",");
			double lat = Double.parseDouble(splitted[0]);
			double lng = Double.parseDouble(splitted[1]);
			min_lat = lat;
			max_lat = lat;
			min_lng = lng;
			max_lng = lng;
			// find min max
			for (Iterator iter = markers.iterator(); iter.hasNext();) {
				ReportGroupListMemberGWT e = (ReportGroupListMemberGWT) iter
						.next();
				s = e.getGpsCoordinate();
				splitted = s.split(",");
				lat = Double.parseDouble(splitted[0]);
				lng = Double.parseDouble(splitted[1]);
				if (min_lat > lat) {
					min_lat = lat;
				}
				if (min_lng > lng) {
					min_lng = lng;
				}
				if (max_lat < lat) {
					max_lat = lat;
				}
				if (max_lng < lng) {
					max_lng = lng;
				}
			}

			double avg_lat, avg_lng;
			avg_lat = min_lat + ((max_lat - min_lat) / 2);
			avg_lng = min_lng + ((max_lng - min_lng) / 2);

			int zoom = gmap.getBoundsZoomLevel(new GLatLngBounds(new GLatLng(
					min_lat, min_lng), new GLatLng(max_lat, max_lng)));
			gmap.enableContinuousZoom();
			gmap.setZoom(zoom);
			gmap.panTo(new GLatLng(avg_lat, avg_lng));

		}
	}

	// /**
	// * Sort list by date (early first)
	// *
	// * @param resultGWT
	// * list
	// * @return list sorted by date (early first)
	// */
	// private static List sortByDateReportGroupListMember(List resultGWT) {
	// int i = 0;
	// int j = 0;
	//
	// List resultSorted = new ArrayList();
	// ReportGroupListMemberGWT min;
	// Date minDate;
	// ReportGroupListMemberGWT r = null;
	//
	// while (resultGWT.size() > 0) {
	// min = (ReportGroupListMemberGWT) resultGWT.get(0);
	// minDate = min.getMemberDate();
	//
	// while (i < resultGWT.size()) {
	// r = (ReportGroupListMemberGWT) resultGWT.get(i);
	// if (minDate.after(r.getMemberDate())) {
	// minDate = r.getMemberDate();
	// min = r;
	// }
	// i++;
	// }
	// resultGWT.remove(r);
	// resultSorted.add(j, r);
	// i = 0;
	// j++;
	// }
	//
	// return resultSorted;
	// }

}