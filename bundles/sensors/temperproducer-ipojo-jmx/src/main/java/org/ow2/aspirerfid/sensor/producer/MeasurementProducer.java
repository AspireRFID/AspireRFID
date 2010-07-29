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

package org.ow2.aspirerfid.sensor.producer;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.osgi.util.measurement.Measurement;

/**
 * The class provides an abstract producer of measurements 
 * @author Didier Donsez
 * @author Thomas Calmant - iPOJO transformation
 */
public abstract class MeasurementProducer implements Producer, Runnable {

	private Wire wires[];
	private ServiceRegistration serviceRegistration;
	
	private boolean end;

	protected int pollDelay;
	
	/**
	 * @see java.lang.Runnable#run()
	 */
	public final void run() {
		while (!end){
			try {
				// Update values (even if there is no wires activated)
				Measurement measurement = getMeasurement();
				if(measurement!=null) {
					synchronized (this) {
						if(wires != null && wires.length > 0) {
							for (int i = 0; wires != null && i < wires.length; i++) {
								Wire wire = wires[i];
								// check if wire is valid and connected ?
								if (!wire.isConnected() || !wire.isValid())
									continue;
								Object obj=adapt(wire,measurement);
								// TODO : respect the control flow specified by the consumer
								if(obj!=null) wire.update(obj);
							}
						}
					}
				}
				if(!end) Thread.sleep(pollDelay);
			} catch (InterruptedException ie) {
				/* will recheck end */
			}
		}
	}

	/**
	 * @see org.osgi.service.wireadmin.Producer#consumersConnected(org.osgi.service.wireadmin.Wire[])
	 */
	public final synchronized void consumersConnected(Wire wires[]) {
		this.wires = wires;
	}

	/**
	 * @see org.osgi.service.wireadmin.Producer#polled(org.osgi.service.wireadmin.Wire)
	 */
	public final Object polled(Wire wire) {
		return adapt(wire,getMeasurement());
	}

	private Object adapt(Wire wire, Measurement measurement) {
		Class clazzes[] = wire.getFlavors();
		for (int i = 0; i < clazzes.length; i++) {
			Class clazz = clazzes[i];
			if (clazz.isAssignableFrom(Measurement.class))
				return measurement;
			if (clazz.isAssignableFrom(Double.class))
				return new Double(measurement.getValue());
			if (clazz.isAssignableFrom(String.class))
				return measurement.toString();
		}
		return null;
	}

	/**
	 * get the Measurement
	 * @return
	 */
	protected abstract Measurement getMeasurement();

	protected Hashtable getRegistrationProperties() {
		Hashtable registrationProperties = new Hashtable();
		registrationProperties.put(
			org.osgi.service.wireadmin.WireConstants.WIREADMIN_PRODUCER_FLAVORS,
			new Class[] { Measurement.class, Double.class, String.class });
		registrationProperties.put(
				org.osgi.framework.Constants.SERVICE_PID,
				this.getClass().getPackage().getName());
		registrationProperties.put(
				org.osgi.framework.Constants.SERVICE_DESCRIPTION,
				"a simple Producer that poll Measurement objects");
		return registrationProperties;
	}
	
	/**
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		serviceRegistration=bundleContext.registerService(Producer.class.getName(), this, getRegistrationProperties());

		end = false;
		Thread thread=new Thread(this);
		String threadName=(String)bundleContext.getBundle().getHeaders().get(Constants.BUNDLE_NAME);
		if(threadName==null) threadName=bundleContext.getBundle().getSymbolicName();
		if(threadName==null) threadName=getClass().getPackage().getName();
		thread.setName(threadName);
		thread.start();
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		this.end = true;
		serviceRegistration.unregister();
	}
}