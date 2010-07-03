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

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.osgi.framework.BundleContext;

import com.dalsemi.onewire.application.monitor.DeviceMonitorEvent;
import com.dalsemi.onewire.application.monitor.DeviceMonitorEventListener;
import com.dalsemi.onewire.application.monitor.DeviceMonitorException;
import com.dalsemi.onewire.container.ADContainer;
import com.dalsemi.onewire.container.HumidityContainer;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.container.SwitchContainer;
import com.dalsemi.onewire.container.TemperatureContainer;

/**
 * @version 1.0 22 Mar. 2003
 * @version 1.1 2 Jan. 2004
 * @version 1.2 4 Dec. 2004
 * @version 1.3 15Jun. 2010
 * @author Didier Donsez (Didier.Donsez@ieee.org)
 */

public class OneWireProducerRegister implements DeviceMonitorEventListener {

	private PrintStream traceout;
	private Map registeredProducers;
	private BundleContext context = null;
	private int pollInterval;

	public OneWireProducerRegister(BundleContext context, int pollInterval,
			PrintStream traceout) {
		this.registeredProducers = new HashMap();
		this.context = context;
		this.pollInterval = pollInterval;
		this.traceout = traceout;
	}

	/**
	 * stop all producers
	 */
	public void stop() {
		Iterator iter = registeredProducers.values().iterator();
		while (iter.hasNext()) {
			((OneWireAbstractProducer) iter.next()).end();
		}
	}

	/**
	 * Arrival event as a NetworkMonitorEventListener
	 * 
	 * @param nme
	 *            NetworkMonitorEvent add
	 */
	public void deviceArrival(DeviceMonitorEvent event) {

		int count = event.getDeviceCount();
		for (int i = 0; i < count; i++) {

			String address = event.getAddressAsStringAt(i);

			// Search all the implemented Container interfaces
			OneWireContainer owc = event.getContainerAt(i);

			if (owc instanceof ADContainer) {
				registeredProducers.put(address, new OneWireADVoltageProducer(
						context, owc, pollInterval));
			}
			if (owc instanceof HumidityContainer) {
				registeredProducers.put(address, new OneWireHumidityProducer(
						context, owc, pollInterval));
			}
			if (owc instanceof SwitchContainer) {
				// TODO org.osgi.util.measurement.State
				// one producer per channel ???
				// ((SwitchContainer)owc).getNumberChannels()
				/*
				 * TODO registeredProducers.put( nme.getAddressAsString(), new
				 * OneWireStateProducer(context,owc) );
				 */
			}
			if (owc instanceof TemperatureContainer) {
				registeredProducers.put(address,
						new OneWireTemperatureProducer(context, owc,
								pollInterval));
			}

		}

	}

	/**
	 * Depart event as a NetworkMonitorEventListener
	 * 
	 * @param nme
	 *            NetworkMonitorEvent depart
	 */
	public void deviceDeparture(DeviceMonitorEvent event) {
		
		int count = event.getDeviceCount();
		for (int i = 0; i < count; i++) {
			String address = event.getAddressAsStringAt(i);
			OneWireAbstractProducer owp = (OneWireAbstractProducer) registeredProducers.remove(address);
			owp.end();
		}
	}

	/**
	 * Exception event as a NetworkMonitorEventListener
	 * 
	 * @param ex
	 *            Exception
	 */
	public void networkException(DeviceMonitorException arg0) {

	}

	/**
	 * print a trace
	 * 
	 * @param message
	 *            message to trace
	 */
	private void traceln(String message) {
		if (traceout != null) {
			trace(message);
			trace("\n");
		}
	}

	/**
	 * print a trace
	 * 
	 * @param message
	 *            message to trace
	 */
	private void trace(String message) {
		if (traceout != null) {
			traceout.print(message);
		}
	}

}
