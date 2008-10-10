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
package org.ow2.aspirerfid.ons.webservice.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The EPC History Bean.
 * 
 * @author François Fornaciari
 * @version 2007
 */
@Entity
@Table(name = "TAG_HISTORY")
@NamedQuery(name = "allEPCISServicesHistories", query = "select o from EPCISServiceHistoryBean o")
public class EPCISServiceHistoryBean implements Serializable {

	private static final long serialVersionUID = -7188309613371715612L;

	/**
	 * Id
	 */
	private int id;

	private String serviceURL;

	private String tag;

	/**
	 * Constructor by default
	 */
	public EPCISServiceHistoryBean() {
	}

	/**
	 * @param serviceURL
	 * @param tag
	 */
	public EPCISServiceHistoryBean(String serviceURL, String tag) {
		this.serviceURL = serviceURL;
		this.tag = tag;
	}

	/**
	 * Get the primary key
	 * 
	 * @return The primary key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	/**
	 * Set the primary key
	 * 
	 * @param id
	 *            The primary key
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getServiceURL() {
		return serviceURL;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param serviceURL
	 */
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

}
