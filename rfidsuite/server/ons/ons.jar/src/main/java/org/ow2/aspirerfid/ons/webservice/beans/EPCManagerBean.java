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
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The EPC Manager Bean.
 * 
 * @author François Fornaciari
 * @version 2007
 */
@Entity
@Table(name = "EPC_MANAGERS")
@NamedQuery(name = "allEPCManagers", query = "select o from EPCManagerBean o")
public class EPCManagerBean implements Serializable {

	private static final long serialVersionUID = 8483560214715038370L;

	/**
	 * Id
	 */
	private int epcManagerNumber;

	private String serviceURL;

	private String description;

	/**
	 * Constructor by default
	 */
	public EPCManagerBean() {
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param serviceURL
	 * @param epcManagerNumber
	 * @param description
	 */
	public EPCManagerBean(String serviceURL, int epcManagerNumber,
			String description) {
		this.serviceURL = serviceURL;
		this.epcManagerNumber = epcManagerNumber;
		this.description = description;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return TODO Javadoc
	 */
	@Id
	public int getEpcManagerNumber() {
		return epcManagerNumber;
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param epcManagerNumber
	 */
	public void setEpcManagerNumber(int epcManagerNumber) {
		this.epcManagerNumber = epcManagerNumber;
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

}
