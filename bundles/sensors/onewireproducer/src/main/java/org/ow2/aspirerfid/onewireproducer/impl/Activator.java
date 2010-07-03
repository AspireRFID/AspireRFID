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

public class Activator implements BundleActivator {

	private static final String CONFIGFILE = "/initialstate.properties";

	private DeviceMonitor dm;
	private EventProducer eventProducer;
	private OneWireProducerRegister oneWireProducerRegister;
	private DSPortAdapter adapter;

	protected BundleContext context = null;

	private PrintStream traceout;

	/**
	 * Called upon starting of the bundle. This method invokes initialize() which
	 * load the configuration file, creates the dependency managers and registers the
	 * eventual services.
	 *
	 * @param   context  The bundle context passed by the framework
	 * @exception   Exception
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

		// Get the Config-Location value from the manifest

		String configLocation = null;
		Dictionary dict = context.getBundle().getHeaders();
		Enumeration enum1 = dict.keys();
		while (enum1.hasMoreElements()) {
			Object nextKey = enum1.nextElement();
			Object nextElem = dict.get(nextKey);
			if (nextKey.equals("Config-Location")) {
				configLocation = nextElem.toString();
				break;
			}
		}
		if (configLocation == null) {
			configLocation = CONFIGFILE;
		}

		// Load properties from configLocation file
		InputStream is = getClass().getResourceAsStream(configLocation);
		Properties props = Configuration.loadProperties(is);

		String ptraceout = props.getProperty("traceout");
		PrintStream traceout = null;
		if (ptraceout != null) {
			if (ptraceout.equals("System.out")) {
				traceout = System.out;
			} else if (ptraceout.equals("System.err")) {
				traceout = System.err;
			} else {
				// TODO instanciante the class with the name	
				System.err.println("custom trace is not implemented !");
			}
		} else {
			traceout = null;
		}

		// Get the properties
		String adapter_name = props.getProperty("adapterName");
		String port_name = props.getProperty("portName");
		traceln("Configured Adapter:" + adapter_name
			+ " on port:" + port_name);

		String poll_interval = props.getProperty("pollInterval");
		int pollInterval = 1000; // default 1000 millisec
		if (poll_interval != null) {
			pollInterval = Integer.parseInt(poll_interval);
		}

		try {
			if (adapter_name == null || port_name == null) {
				// get the default adapter
				adapter = OneWireAccessProvider.getDefaultAdapter();
			} else {
				adapter =
					OneWireAccessProvider.getAdapter(adapter_name, port_name);
			}
			
			traceln("Found adapter: " + adapter.getAdapterName()
				+ " on port: " + adapter.getPortName());
			
		} catch (Exception e) {
			traceln("That is not a valid adapter/port combination.");

			Enumeration en = OneWireAccessProvider.enumerateAllAdapters();
			while (en.hasMoreElements()) {

				DSPortAdapter temp = (DSPortAdapter) en.nextElement();

				traceln("Adapter: " + temp.getAdapterName());

				Enumeration f = temp.getPortNames();

				while (f.hasMoreElements()) {
					traceln("   Port name : " + ((String) f.nextElement()));
				}
			}

			return;
		}

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
}
