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

/**
 * Remote interface of the ONS Service Bean.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public interface ONSServiceRemote {
	/**
	 * Method called by an EPC IS to retrieve the EPC IS provider service URL
	 * and description for a specific tag. This method is an ONS functionality.
	 * 
	 * @param tag
	 *            The specific tag
	 * @return The service URL
	 */
	public String getEPCISServiceURL(String tag);

	/**
	 * Add an EPC IS service URL. This method is an ONS functionality.
	 * 
	 * @param epcManagerNumber
	 * @param serviceUrl
	 * @param description
	 */
	public void addEPCISService(int epcManagerNumber, String serviceUrl,
			String description);

	/**
	 * Remove an EPC IS service URL. This method is an ONS functionality.
	 * 
	 * @param epcManagerNumber
	 */
	public void removeEPCISService(int epcManagerNumber);

	/**
	 * Remove all EPC IS services URLs. This method is an ONS functionality.
	 */
	public void removeAllEPCISServices();

	/**
	 * Register a new EPC IS service in order to update the tag history. This
	 * method is a Discovery Service functionality. Returns true if the update
	 * has succeed.
	 * 
	 * @param tags
	 *            The specified tags
	 * @param serviceURL
	 *            Service URL of the EPC IS which declares the tag
	 * @return True if the update has succeed.
	 */
	public boolean registerEPCISServiceHistory(String tags, String serviceURL);

	/**
	 * Get the list of EPC IS services URLs for the specified tag. This method
	 * is a Discovery Service functionality.
	 * 
	 * @param tag
	 *            The specified tag
	 * @return The list of service URLs for the specified tag
	 */
	public String getEPCISServicesHistory(String tag);

	/**
	 * Remove all EPC IS services Histories. This method is an Discovery Service
	 * functionality.
	 */
	public void removeAllEPCISServicesHistories();

	/**
	 * Remove all EPC IS service history for the specified tag. This method is
	 * an Discovery Service functionality.
	 * 
	 * @param tag
	 *            The specified tag
	 */
	public void removeEPCISServicesHistory(String tag);

	/**
	 * Get the EPC IS description name depending on the EPC IS service URL.
	 * 
	 * @param serviceURL
	 *            The specified tag
	 * @return The EPC IS description name
	 */
	public String getEPCISDescription(String serviceURL);
}
