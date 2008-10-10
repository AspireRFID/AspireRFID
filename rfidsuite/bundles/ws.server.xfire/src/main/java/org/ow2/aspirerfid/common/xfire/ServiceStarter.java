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
package org.ow2.aspirerfid.common.xfire;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.server.http.XFireHttpServer;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.service.invoker.BeanInvoker;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.ow2.aspirerfid.util.Logger;

/**
 * This class creates a Service from the ServiceFactory and also starts up an
 * instance of the XFireHttpServer
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class ServiceStarter implements ServiceListener {
	/**
	 * The XFire http server
	 */
	private XFireHttpServer server = null;

	/**
	 * The Bundle context
	 */
	private BundleContext context = null;

	/**
	 * List of registered services
	 */
	private Map serviceReferences = null;

	private Logger logger;

	/**
	 * TODO Javadoc
	 */
	public ServiceStarter() {
		serviceReferences = new HashMap();
	}

	/**
	 * Start the server and register existing services (to be accessed as web
	 * services)
	 * 
	 * @param context
	 *            the bundle context
	 * @throws Exception
	 */
	public void start(BundleContext context) throws Exception {
		this.context = context;
		this.logger = new Logger("XFire", Logger.INFO);

		Thread.currentThread().setContextClassLoader(
				this.getClass().getClassLoader());

		// Start the HTTP server
		server = new XFireHttpServer();
		server.setPort(8191);
		server.start();

		// Register existing Services
		registerExistingServices();

		context.addServiceListener(this);
	}

	/**
	 * Unregister existing services
	 * 
	 * @throws Exception
	 */
	public void stop() throws Exception {
		// Unregister existing Services
		unregisterExistingServices();

		if (server != null) {
			server.stop();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.ServiceListener#serviceChanged(org.osgi.framework.ServiceEvent)
	 */
	public void serviceChanged(ServiceEvent serviceEvent) {
		ServiceReference serviceReference = serviceEvent.getServiceReference();

		if (isWebService(serviceReference)) {
			if (serviceEvent.getType() == ServiceEvent.REGISTERED) {
				register(serviceReference);
			} else if (serviceEvent.getType() == ServiceEvent.UNREGISTERING) {
				unregister(serviceReference);
			} else if (serviceEvent.getType() == ServiceEvent.MODIFIED) {
				unregister(serviceReference);
				register(serviceReference);
			}
		}
	}

	/**
	 * List OSGi services and register those which have to be registered as web
	 * services
	 */
	private void registerExistingServices() {
		ServiceReference[] serviceReferences = null;
		try {
			serviceReferences = context.getServiceReferences(null, null);
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}

		if (serviceReferences == null)
			return;

		for (int i = 0; i < serviceReferences.length; i++) {
			if (isWebService(serviceReferences[i]))
				register(serviceReferences[i]);
		}
	}

	/**
	 * List OSGi services and unregister those which have been registered as web
	 * services
	 */
	private void unregisterExistingServices() {
		ServiceReference[] serviceReferences = null;
		try {
			serviceReferences = context.getServiceReferences(null, null);
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}

		if (serviceReferences != null) {
			for (int i = 0; i < serviceReferences.length; i++) {
				if (isWebService(serviceReferences[i]))
					unregister(serviceReferences[i]);
			}
		}
	}

	/**
	 * Register a service
	 * 
	 * @param serviceReference
	 */
	private void register(ServiceReference serviceReference) {
		logger.log(Logger.INFO, "Register service : "
				+ serviceReference.getProperty(Constants.SERVICE_DESCRIPTION));
		Object serviceImpl = context.getService(serviceReference);

		// Create an XFire Service
		ObjectServiceFactory serviceFactory = new ObjectServiceFactory();
		Service service = serviceFactory.create(serviceImpl.getClass());
		service.setInvoker(new BeanInvoker(serviceImpl));

		// Register the service in the ServiceRegistry
		XFire xfire = XFireFactory.newInstance().getXFire();
		xfire.getServiceRegistry().register(service);

		serviceReferences.put(serviceReference
				.getProperty(Constants.SERVICE_ID), service);
	}

	/**
	 * Unregister a service
	 * 
	 * @param serviceReference
	 */
	private void unregister(ServiceReference serviceReference) {
		logger.log(Logger.INFO, "Unregister service : "
				+ serviceReference.getProperty(Constants.SERVICE_DESCRIPTION));

		Long serviceId = (Long) serviceReference
				.getProperty(Constants.SERVICE_ID);

		Service service = (Service) serviceReferences.get(serviceId);

		// Unregister the service in the ServiceRegistry
		XFire xfire = XFireFactory.newInstance().getXFire();
		xfire.getServiceRegistry().unregister(service);

		serviceReferences.remove(serviceReference
				.getProperty(Constants.SERVICE_ID));
	}

	/**
	 * Return the type of an OSGi service
	 * 
	 * @param serviceReference
	 * @return
	 */
	private boolean isWebService(ServiceReference serviceReference) {
		String type = (String) serviceReference.getProperty("service.type");
		if (type != null && type.compareToIgnoreCase("WS") == 0) {
			return true;
		}
		return false;
	}
}
