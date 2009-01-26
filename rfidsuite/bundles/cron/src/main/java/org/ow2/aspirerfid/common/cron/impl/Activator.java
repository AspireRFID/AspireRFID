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
package org.ow2.aspirerfid.common.cron.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.ow2.aspirerfid.common.cron.CronService;

/**
 * This class runs the cron service in OSGi context
 * 
 * @author Anne Robert
 * @version 2006
 */
public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	/**
	 * TODO Javadoc
	 */
	CronServiceImpl cronService;

	/**
	 * @param context
	 * @throws BundleException
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws BundleException {
		// Create the cron service object
		cronService = new CronServiceImpl();
		// Start it
		cronService.startCronService();
		Dictionary props = new Hashtable();
		// inform bundle description.
		props.put(Constants.BUNDLE_DESCRIPTION, "schedules jobs periodically");
		// register a service implementation
		serviceRegistration = context.registerService(CronService.class
				.getName(), cronService, props);
	}

	/**
	 * @param context
	 * @throws BundleException
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws BundleException {
		// Stop all threads
		cronService.endCronService();
		// unregister the service
		if (serviceRegistration != null)
			serviceRegistration.unregister();
	}
}
