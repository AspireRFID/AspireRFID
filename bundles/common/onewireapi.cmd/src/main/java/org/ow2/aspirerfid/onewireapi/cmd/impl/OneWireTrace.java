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
package org.ow2.aspirerfid.onewireapi.cmd.impl;

import java.io.PrintStream;

import org.osgi.framework.BundleContext;

import com.dalsemi.onewire.application.monitor.DeviceMonitorEvent;
import com.dalsemi.onewire.application.monitor.DeviceMonitorEventListener;
import com.dalsemi.onewire.application.monitor.DeviceMonitorException;

/**
 * @version 1.0 22/03/2003
 * @version 1.1 15/06/2010
 * @author Didier Donsez (Didier.Donsez@ieee.org)
 */

public class OneWireTrace implements DeviceMonitorEventListener {

	private PrintStream traceout;
	private BundleContext context = null;

	public OneWireTrace(BundleContext context, PrintStream traceout) {
		this.context = context;
		this.traceout = traceout;
	}

	/**
	 * Arrival event as a NetworkMonitorEventListener
	 * 
	 * @param event
	 *            NetworkMonitorEvent add
	 */
	public void deviceArrival(DeviceMonitorEvent event) {
		int count = event.getDeviceCount();
		for (int i = 0; i < count; i++) {
			String address = event.getAddressAsStringAt(i);
			traceln("OneWire Device Event: ADD " + address);

		}
	}

	
	public void deviceDeparture(DeviceMonitorEvent event) {
		int count = event.getDeviceCount();
		for (int i = 0; i < count; i++) {
			String address = event.getAddressAsStringAt(i);
			traceln("OneWire Device Event: REMOVE " + address);

		}
	}

	public void networkException(DeviceMonitorException ex) {
		traceln(ex.toString());
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
