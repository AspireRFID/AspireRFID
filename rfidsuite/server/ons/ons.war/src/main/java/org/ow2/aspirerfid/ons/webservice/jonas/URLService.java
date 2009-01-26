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
package org.ow2.aspirerfid.ons.webservice.jonas;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Provides a Web Service to allow EPC IS to request service URL.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public interface URLService extends Remote {
	/**
	 * Method called by an EPC IS to retrieve the EPC IS provider service URL
	 * and description for a specific tag. This method is an ONS functionality.
	 * 
	 * @param tag
	 *            The specific tag
	 * @return The service URL
	 * @throws RemoteException
	 */
	public String getEPCISServiceURL(String tag) throws RemoteException;

	/**
	 * Get the list of EPC IS services URLs for the specified tag. This method
	 * is a Discovery Service functionality. Due to JBoss problems with string
	 * array return type, a simple string is returned.
	 * 
	 * @param tag
	 *            The specified tag
	 * @return The list of service URLs for the specified tag
	 * @throws RemoteException
	 */
	public String getEPCISServicesHistory(String tag) throws RemoteException;

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
	 * @throws RemoteException
	 */
	public boolean registerEPCISServiceHistory(String tags, String serviceURL)
			throws RemoteException;

	/**
	 * Get the EPC IS description name depending on the EPC IS service URL.
	 * 
	 * @param serviceURL
	 *            The specified tag
	 * @return The EPC IS description name
	 * @throws RemoteException
	 */
	public String getEPCISDescription(String serviceURL) throws RemoteException;
}
