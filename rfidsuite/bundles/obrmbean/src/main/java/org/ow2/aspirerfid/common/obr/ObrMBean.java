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
package org.ow2.aspirerfid.common.obr;

import org.osgi.framework.BundleException;

/**
 * The MBean interface provides a service to manage the OBR
 * 
 * @author François Fornaciari
 * @version 2007
 */
public interface ObrMBean {

	/**
	 * Add a new Repository
	 * 
	 * @param repositoryUrl
	 *            the URL Repository
	 */
	public void obrAddRepository(String repositoryUrl);

	/**
	 * Deploy a Bundle from OBR
	 * 
	 * @param name
	 *            the Bundle Name
	 * @param version
	 *            the Bundle Version
	 * @throws BundleException 
	 */
	public void obrDeployBundle(String name, String version)
			throws BundleException;

	/**
	 * Start a Bundle from OBR
	 * 
	 * @param name
	 *            the Bundle Name
	 * @param version
	 *            the Bundle Version
	 * @throws BundleException 
	 */
	public void obrStartBundle(String name, String version)
			throws BundleException;
}
