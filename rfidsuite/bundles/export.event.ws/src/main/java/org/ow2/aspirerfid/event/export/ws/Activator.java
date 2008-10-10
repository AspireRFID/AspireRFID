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
package org.ow2.aspirerfid.event.export.ws;

import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.ow2.aspirerfid.event.export.api.ExportEventSpecialized;

/**
 * This class registers a service which invokes a Web Service.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class Activator implements BundleActivator {

	/**
	 * The export event web service service
	 */
	private ExportEventWSImpl exportEventWS;

	/**
	 * Web service registration
	 */
	private ServiceRegistration serviceRegistration;

	/**
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		exportEventWS = new ExportEventWSImpl(context);
		Properties props = new Properties();
		props.put(Constants.SERVICE_DESCRIPTION, "WS ExportEvent");
		props.put("SERVICE_TYPE", "ExportEventSpecialized");
		props.put("SERVICE_SUBTYPE", ExportEventSpecialized.TYPE_WS);
		serviceRegistration = context.registerService(
				ExportEventSpecialized.class.getName(), exportEventWS, props);
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		serviceRegistration.unregister();
		exportEventWS = null;
		serviceRegistration = null;
	}

}
