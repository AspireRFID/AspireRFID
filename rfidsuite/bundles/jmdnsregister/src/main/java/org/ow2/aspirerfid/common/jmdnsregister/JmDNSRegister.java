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
package org.ow2.aspirerfid.common.jmdnsregister;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.ServiceInfo;

import org.apache.felix.framework.cache.BundleCache;
import org.osgi.framework.BundleContext;

import fr.imag.adele.service.jmdns.JmDNS;

/**
 * Registers JmDNS services 
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class JmDNSRegister {

	/**
	 * JmDNS service
	 */
	private JmDNS jmDNS;

	/**
	 * Bundle context
	 */
	private BundleContext bundleContext = null;

	/**
	 * Constructor
	 * 
	 * @param bundleContext
	 */
	public JmDNSRegister(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	/**
	 * TODO Javadoc
	 */
	public void start() {
		registerJmDNSService();
	}

	/**
	 * Unregister all JmDNS services
	 */
	public void stop() {
		jmDNS.unregisterAllServices();
	}

	/**
	 * Register a JmDNS service
	 */
	private void registerJmDNSService() {
		ServiceInfo serviceInfo = null;

		try {
			String profile = bundleContext
					.getProperty(BundleCache.CACHE_PROFILE_PROP);
			if (profile == null) {
				profile = System.getProperty(BundleCache.CACHE_PROFILE_PROP);
			}
			String rmiPort = bundleContext
					.getProperty("mosgi.jmxconsole.rmiport." + profile);
			String ip = InetAddress.getLocalHost().getHostAddress();
			if (rmiPort == null) {
				rmiPort = "1099";
			}
			String jmxServiceUrl = "service:jmx:rmi:///jndi/rmi://" + ip + ":"
					+ rmiPort + "/" + profile;

			serviceInfo = new ServiceInfo("_osgi._location._eclipse.",
					jmxServiceUrl, Integer.valueOf(rmiPort).intValue(),
					"Service to discover gateways");
			jmDNS.registerService(serviceInfo);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
