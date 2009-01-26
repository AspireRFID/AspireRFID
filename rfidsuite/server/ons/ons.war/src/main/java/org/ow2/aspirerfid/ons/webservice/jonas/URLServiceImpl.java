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

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.ow2.aspirerfid.ons.webservice.beans.ONSServiceBean;
import org.ow2.aspirerfid.ons.webservice.beans.ONSServiceRemote;

/**
 * Implementation of the Service URL.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class URLServiceImpl implements URLService {
	private ONSServiceRemote onsServiceRemote;

	/**
	 * 
	 */
	public URLServiceImpl() {
		try {
			Context ctx = new InitialContext();
			onsServiceRemote = (ONSServiceRemote) ctx
					.lookup(ONSServiceBean.class.getName() + "_"
							+ ONSServiceRemote.class.getName() + "@Remote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ons.webservice.jonas.URLService#getEPCISServiceURL(java.lang.String)
	 */
	public String getEPCISServiceURL(String tag) throws RemoteException {
		return onsServiceRemote.getEPCISServiceURL(tag);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ons.webservice.jonas.URLService#getEPCISServicesHistory(java.lang.String)
	 */
	public String getEPCISServicesHistory(String tag) throws RemoteException {
		return onsServiceRemote.getEPCISServicesHistory(tag);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ons.webservice.jonas.URLService#registerEPCISServiceHistory(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean registerEPCISServiceHistory(String tags, String serviceURL)
			throws RemoteException {
		return onsServiceRemote.registerEPCISServiceHistory(tags, serviceURL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ons.webservice.jonas.URLService#getEPCISDescription(java.lang.String)
	 */
	public String getEPCISDescription(String serviceURL) throws RemoteException {
		return onsServiceRemote.getEPCISDescription(serviceURL);
	}

}