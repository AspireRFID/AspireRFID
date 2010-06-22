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
package org.ow2.aspirerfid.sensor.gpssimproducer;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.wireadmin.Producer;
import org.ow2.aspirerfid.wires.GpsRead;

/**
 * The bundle activator which registers a producer service.
 * 
 * @author Perisanidi Maroula
 * @version 1.0.0 2007/05/01
 */
public class Activator implements BundleActivator {

	/**
	 * Used for the registration of a producer service.
	 */
	private ServiceRegistration serviceRegistration;

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		GpsSimProducer gps = new GpsSimProducer();
		Hashtable prop = new Hashtable();
		prop
				.put(
						org.osgi.service.wireadmin.WireConstants.WIREADMIN_PRODUCER_FLAVORS,
						new Class[] { GpsRead.class });
		prop.put(org.osgi.framework.Constants.SERVICE_PID,
				"org.ow2.aspirerfid.osgi.util.fictiveGPS");
		prop.put(org.osgi.framework.Constants.SERVICE_DESCRIPTION,
				"a simple Producer that polls GPS values");

		serviceRegistration = bundleContext.registerService(Producer.class.getName(),
				gps, prop);
	}

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		serviceRegistration.unregister();
	}
}