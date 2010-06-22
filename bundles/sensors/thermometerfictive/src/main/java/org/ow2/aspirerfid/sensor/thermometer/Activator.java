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
package org.ow2.aspirerfid.sensor.thermometer;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.wireadmin.Producer;
import org.ow2.aspirerfid.wires.TemperatureRead;

/**
 * The bundle activator which registers a producer service.
 * 
 * @author Guillaume Surrel
 * @author Perisanidi Maroula
 */
public class Activator implements BundleActivator {

	/**
	 * Used for the registration of the MBean.
	 */
	private ServiceRegistration mBeanRegistration;

	/**
	 * Used for the registration of a producer service.
	 */
	private ServiceRegistration serviceRegistration;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		Dictionary props = new Hashtable();
		TemperatureProducerMBean therm = new TemperatureProducer();
		props.put("jmxagent.objectname",
				"rfid:type=sensor,SymbolicName=Thermometer");
		// register a service implementation
		mBeanRegistration = context.registerService(
				TemperatureProducerMBean.class.getName(), therm, props);

		// producer registration
		Hashtable prop = new Hashtable();
		prop
				.put(
						org.osgi.service.wireadmin.WireConstants.WIREADMIN_PRODUCER_FLAVORS,
						new Class[] { TemperatureRead.class });
		prop.put(org.osgi.framework.Constants.SERVICE_PID,
				"org.ow2.aspirerfid.osgi.util.fictiveTemperature");
		prop.put(org.osgi.framework.Constants.SERVICE_DESCRIPTION,
				"a simple Producer that polls Temperature values");

		serviceRegistration = context.registerService(Producer.class.getName(),
				therm, prop);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		mBeanRegistration.unregister();
		serviceRegistration.unregister();
	}

}
