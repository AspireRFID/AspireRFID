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

package org.ow2.aspirerdfid.tracking.demo.client;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Mourtzoukos Konstantinos {email: komo@ait.edu.gr}
 *
 */

public class TagEventSerialObject implements Serializable {
	

	private static final long serialVersionUID = 1723917662396107507L;
	private String tag;
	private Date timeDate;
	private String geoTag;
	private String name;
	private String description;
	private String address;
	private String country;
	private String region;
	private String email;
	private String tel;
	private String fax;
	private String epcClassProperties;
	
	public TagEventSerialObject() {
		super();
	}

	public TagEventSerialObject(String tag, Date timeDate, String geoTag) {
		super();
		this.tag = tag;
		this.timeDate = timeDate;
		this.geoTag = geoTag;
	}
	
	

	public TagEventSerialObject(String tag, Date timeDate, String geoTag,
			String name, String description, String address, String country,
			String region, String email, String tel, String fax) {
		super();
		this.tag = tag;
		this.timeDate = timeDate;
		this.geoTag = geoTag;
		this.name = name;
		this.description = description;
		this.address = address;
		this.country = country;
		this.region = region;
		this.email = email;
		this.tel = tel;
		this.fax = fax;
	}
	
	

	public TagEventSerialObject(String tag, Date timeDate, String geoTag,
			String name, String description, String address, String country,
			String region, String email, String tel, String fax,
			String epcClassProperties) {
		super();
		this.tag = tag;
		this.timeDate = timeDate;
		this.geoTag = geoTag;
		this.name = name;
		this.description = description;
		this.address = address;
		this.country = country;
		this.region = region;
		this.email = email;
		this.tel = tel;
		this.fax = fax;
		this.epcClassProperties = epcClassProperties;
	}
	
	

	public String getEpcClassProperties() {
		return epcClassProperties;
	}

	public void setEpcClassProperties(String epcClassProperties) {
		this.epcClassProperties = epcClassProperties;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getTimeDate() {
		return timeDate;
	}

	public void setTimeDate(Date timeDate) {
		this.timeDate = timeDate;
	}

	public String getGeoTag() {
		return geoTag;
	}

	public void setGeoTag(String geoTag) {
		this.geoTag = geoTag;
	}
	
	

}
