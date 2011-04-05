package org.ow2.aspirerfid.commons.tracking.query;

/**
 * @author Kostas Mourtzoukos (komo) e-mail: komo@ait.edu.gr
 *
 */

public class GeoTagReadPoint {
	private String id;
	private String latitude;
	private String longtitude;
	
	
	public GeoTagReadPoint(String id, String latitude, String longtitude) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	
	

}
