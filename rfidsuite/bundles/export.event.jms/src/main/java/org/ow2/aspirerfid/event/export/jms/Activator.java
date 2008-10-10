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
package org.ow2.aspirerfid.event.export.jms;

import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.ow2.aspirerfid.event.export.api.ExportEventSpecialized;

/**
 * TODO Javadoc
 * 
 * @author François Fornaciari
 * @author Guillaume Vaudaux-Ruth
 */

public class Activator implements BundleActivator {

	/**
	 * The bundle context
	 */
	private static BundleContext bcontext = null;

	/**
	 * A instance of ExportEvent
	 */
	private ExportEventSpecialized exportEvent;

	/**
	 * The associated serviceRegistration of ExportEvent
	 */
	private ServiceRegistration serviceRegistrationExportEvent;

	/**
	 * Uses to get the Bundle context of this bundle
	 * 
	 * @return BundleContext
	 */
	public static BundleContext getBundleContext() {
		return bcontext;
	}

	/**
	 * Called after the bundle is started.
	 * 
	 * @param context
	 *            The bundle context
	 * @throws BundleException
	 */
	public void start(BundleContext context) throws BundleException {
		Properties props = new Properties();
		exportEvent = new ExportEventImpl();
		props.put(Constants.SERVICE_DESCRIPTION, "JMS ExportEvent");
		props.put("SERVICE_TYPE", "ExportEventSpecialized");
		props.put("SERVICE_SUBTYPE", ExportEventSpecialized.TYPE_JMS);
		serviceRegistrationExportEvent = context.registerService(
				(ExportEventSpecialized.class).getName(), exportEvent, props);
	}

	/**
	 * The method is called before stopping the bundle
	 * 
	 * @param context
	 * @throws BundleException
	 */
	public void stop(BundleContext context) throws BundleException {
		serviceRegistrationExportEvent.unregister();
	}

}
