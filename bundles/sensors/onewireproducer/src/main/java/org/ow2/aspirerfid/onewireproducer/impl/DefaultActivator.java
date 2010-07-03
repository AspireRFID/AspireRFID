/*
   Copyright 2005-2008, Aspire
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.
**/
package org.ow2.aspirerfid.onewireproducer.impl;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.ow2.aspirerfid.util.config.Configuration;

import com.dalsemi.onewire.OneWireAccessProvider;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.application.monitor.DeviceMonitor;


/**
 * This activator read configuration properties from a file
 * and initialize a OneWireProducerRegister service
 *
 * @version 	1.0 22/03/2003 
 * @version 	1.1 2/ * @author 	Didier Donsez (Didier.Donsez@ieee.org)
 */
public class DefaultActivator implements BundleActivator, ManagedService {

	private DeviceMonitor dm;
	private EventProducer eventProducer;
	private OneWireProducerRegister oneWireProducerRegister;
	private DSPortAdapter adapter;

	
	private int pollInterval=5000;
	
	protected BundleContext context = null;

	private PrintStream traceout=System.out;

	/**
	 * Called upon starting of the bundle. This method invokes initialize() which
	 * load the configuration file, creates the dependency managers and registers the
	 * eventual services.
	 *
	 * @param   context  The bundle context passed by the framework
	 * @exception   E
	 */
	public void start(BundleContext context) throws Exception {
		this.context = context;
		initialize();
	}

	/**
	 * Load the configuration properties and register e new service
	 */
	private void initialize() throws Exception {

		if (context == null)
			return;
		
		//DSPortAdapter adapter=OneWireAccessProvider.getDefaultAdapter();
		DSPortAdapter adapter=OneWireAccessProvider.getAdapter("{DS9490}", "USB1");

		
		
		traceln(
			"Adapter: "
				+ adapter.getAdapterName()
				+ " Port: "
				+ adapter.getPortName());

		// clear any previous search restrictions
		adapter.setSearchAllDevices();
		adapter.targetAllFamilies();
		adapter.setSpeed(DSPortAdapter.SPEED_REGULAR);
		// SPEED_OVERDRIVE, SPEED_HYPERDRIVE, SPEED_FLEX

		// create a network monitor
		dm = new DeviceMonitor(adapter);
		
		eventProducer=new EventProducer(context,traceout);
		oneWireProducerRegister=new OneWireProducerRegister(context, pollInterval, traceout);

		// add this to the event listers
		dm.addDeviceMonitorEventListener(eventProducer);
		dm.addDeviceMonitorEventListener(oneWireProducerRegister);

		// start the monitor
		// start the monitor
		Thread thread=new Thread(dm);
		thread.setName(DeviceMonitor.class.getName());
		thread.start();
	}

	public void stop(BundleContext context) throws Exception {

		oneWireProducerRegister.stop();
		eventProducer.stop();

		// kill the monitor
		dm.killMonitor();
		// free port used by adapter
		adapter.freePort();
	}
	/**
	 * print a trace 
	 * @param message message to trace
	 */
	private void traceln(String message) {
		if (traceout != null) {
			trace(message);
			trace("\n");
		}
	}

	/**
	 * print a trace 
	 * @param message message to trace
	 */
	private void trace(String message) {
		if (traceout != null) {
			traceout.print(message);
		}
	}

	public void updated(Dictionary properties) throws ConfigurationException {
//		adapterName=DS9097U
//		portName=COM1
//		pollInterval=5000
	}
}
