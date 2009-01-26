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

package org.ow2.aspirerfid.admin;

import java.util.Dictionary;

import javax.management.openmbean.TabularData;

import org.osgi.framework.BundleException;

/**
 * The MBean interface provides a service to manage the gateway
 * 
 * @author Didier Donsez (didier.donsez@ieee.org)
 * @version 2007
 */
public interface AdminMBean {

	// TODO getBundleProperties();

	/**
	 * TODO Javadoc
	 * 
	 * @param key
	 * @return TODO Javadoc
	 */
	public String getFrameworkProperty(String key);

	/**
	 * TODO Javadoc
	 * 
	 * @return TODO Javadoc
	 */
	public String[] getFrameworkPropertyKeys();

	/**
	 * TODO Javadoc
	 * 
	 * @return TODO Javadoc
	 */
	public TabularData getFrameworkProperties();

	/**
	 * TODO Javadoc
	 * 
	 * @return TODO Javadoc
	 */
	public long[] getBundleIds();

	/**
	 * TODO Javadoc
	 * 
	 * @return TODO Javadoc
	 */
	public String[] getBundleLocations();

	/**
	 * TODO Javadoc
	 * 
	 * @return TODO Javadoc
	 */
	public long[] getServiceIds();

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleId
	 * @return TODO Javadoc
	 * @see org.osgi.framework.Bundle#getState()
	 */
	public int getBundleState(long bundleId) /*
												 * throws
												 * IllegalArgumentException
												 */;

	/**
	 * TODO Javadoc
	 * 
	 * @param url
	 * @return TODO Javadoc
	 */
	public int getBundleState(String url) /* throws IllegalArgumentException */;

	/**
	 * TODO Javadoc
	 * 
	 * @param url
	 * @throws BundleException
	 * @see org.osgi.framework.BundleContext#installBundle(String url)
	 */
	public void installBundle(String url) throws BundleException;

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleId
	 * @throws BundleException
	 * @see org.osgi.framework.Bundle#start()
	 */
	public void startBundle(long bundleId)
			throws /* IllegalArgumentException, *//* IllegalArgumentException, */BundleException;

	/**
	 * TODO Javadoc
	 * 
	 * @param location
	 * @throws BundleException
	 */
	public void startBundle(String location)
			throws /* IllegalArgumentException, *//* IllegalArgumentException, */BundleException;

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleId
	 * @throws BundleException
	 * @see org.osgi.framework.Bundle#stop(long bundleId)
	 */
	public void stopBundle(long bundleId)
			throws /* IllegalArgumentException, */BundleException;

	/**
	 * TODO Javadoc
	 * 
	 * @param location
	 * @throws BundleException
	 */
	public void stopBundle(String location)
			throws /* IllegalArgumentException, */BundleException;

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleId
	 * @throws BundleException
	 * @see org.osgi.framework.Bundle#update(long bundleId)
	 */
	public void updateBundle(long bundleId)
			throws /* IllegalArgumentException, */BundleException;

	/**
	 * TODO Javadoc
	 * 
	 * @param location
	 * @throws BundleException
	 */
	public void updateBundle(String location)
			throws /* IllegalArgumentException, */BundleException;

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleId
	 * @param url
	 * @throws BundleException
	 * @see org.osgi.framework.Bundle#update(java.io.InputStream)
	 */
	public void updateBundle(long bundleId, String url)
			throws /* IllegalArgumentException, */BundleException;

	/**
	 * TODO Javadoc
	 * 
	 * @param location
	 * @param url
	 * @throws BundleException
	 */
	public void updateBundle(String location, String url)
			throws /* IllegalArgumentException, */BundleException;

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleId
	 * @throws BundleException
	 * @see org.osgi.framework.Bundle#uninstall(long bundleId)
	 */
	public void uninstallBundle(long bundleId)
			throws /* IllegalArgumentException, */BundleException;

	/**
	 * TODO Javadoc
	 * 
	 * @param location
	 * @throws BundleException
	 */
	public void uninstallBundle(String location)
			throws /* IllegalArgumentException, */BundleException;

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleId
	 * @return TODO Javadoc
	 * @see org.osgi.framework.Bundle#getHeaders(long bundleId)
	 */
	public Dictionary getBundleHeaders(long bundleId) /*
														 * throws
														 * IllegalArgumentException
														 */;

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleId
	 * @return TODO Javadoc
	 * @see org.osgi.framework.Bundle#getLocation(long bundleId)
	 */
	public String getBundleLocation(long bundleId) /*
													 * throws
													 * IllegalArgumentException
													 */;

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleId
	 * @return TODO Javadoc
	 * @see org.osgi.framework.Bundle#getRegisteredServices(long bundleId)
	 */
	public long[] getBundleRegisteredServices(long bundleId) /*
																 * throws
																 * IllegalArgumentException
																 */;

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleId
	 * @return TODO Javadoc
	 * @see org.osgi.framework.Bundle#getServicesInUse(long bundleId)
	 */
	public long[] getBundleServicesInUse(long bundleId) /*
														 * throws
														 * IllegalArgumentException
														 */;

	/**
	 * TODO Javadoc
	 * 
	 * @param serviceId
	 * @return TODO Javadoc
	 * @see org.osgi.framework.ServiceReference#getProperty(java.lang.String)
	 */
	public TabularData getServiceProperties(long serviceId);

	/**
	 * @return TODO Javadoc
	 */
	public TabularData getBundlesInfos();

	/**
	 * TODO Javadoc
	 * 
	 * @param serviceId
	 * @param key
	 * @return TODO Javadoc
	 * @see org.osgi.framework.ServiceReference#getProperty(java.lang.String)
	 */
	public Object getServiceProperty(long serviceId, String key) /*
																	 * throws
																	 * IllegalArgumentException
																	 */;

	/**
	 * TODO Javadoc
	 * 
	 * @param serviceId
	 * @return TODO Javadoc
	 * @see org.osgi.framework.ServiceReference#getPropertyKeys()
	 */
	public String[] getServicePropertyKeys(long serviceId) /*
															 * throws
															 * IllegalArgumentException
															 */;
}
