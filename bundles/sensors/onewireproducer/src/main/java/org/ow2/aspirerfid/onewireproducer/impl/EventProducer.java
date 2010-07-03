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
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.ow2.aspirerfid.onewireproducer.Event;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.application.monitor.DeviceMonitorEvent;
import com.dalsemi.onewire.application.monitor.DeviceMonitorEventListener;
import com.dalsemi.onewire.application.monitor.DeviceMonitorException;


/**
 * @version 	1.0 3 Dec. 2004 
 * @author 	Didier Donsez (Didier.Donsez@ieee.org)
 */

public class EventProducer implements DeviceMonitorEventListener, Producer {

	private PrintStream traceout;
	private BundleContext context = null;

	public EventProducer(
		BundleContext context,
		PrintStream traceout) {
		this.context = context;
		this.traceout = traceout;
		registerProducer();
	}

	/**
     * stop the producer
     */
    public void stop() {
        unregisterProducer();
    }

    /**
	 * Arrival event as a DeviceMonitorEventListener
	 *
	 * @param nme DeviceMonitorEvent add
	 */
	public void deviceArrival(DeviceMonitorEvent event) {
		try {
			int count = event.getDeviceCount();
			for (int i = 0; i < count; i++) {
	            produceEvent(
	            		event.getAdapter().getAdapterAddress(),
	            		event.getAddressAsStringAt(i),
	                    Event.ACTIVATION
	             );
			}
        } catch (OneWireException e) {
            if(traceout!=null)
                e.printStackTrace(traceout);
        }
	}

	/**
	 * Depart event as a DeviceMonitorEventListener
	 *
	 * @param nme DeviceMonitorEvent depart
	 */
	public void deviceDeparture(DeviceMonitorEvent event) {
		try {
			int count = event.getDeviceCount();
			for (int i = 0; i < count; i++) {
	            produceEvent(
	            		event.getAdapter().getAdapterAddress(),
	            		event.getAddressAsStringAt(i),
	                    Event.DESACTIVATION
	             );
			}
        } catch (OneWireException e) {
            if(traceout!=null)
                e.printStackTrace(traceout);
        }

	}

	/**
	 * Exception event as a DeviceMonitorEventListener
	 *
	 * @param ex Exception
	 */
	public void networkException(DeviceMonitorException dme) {

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

	// Producer section

	private ServiceRegistration serviceRegistration;

	private Wire[] wires=null;

	private Event lastEvent=null;
	
	private void registerProducer(){
		Hashtable properties = new Hashtable();
		properties.put(
			org.osgi.service.wireadmin.WireConstants.WIREADMIN_PRODUCER_FLAVORS,
			new Class[] { Event.class, String.class });
		properties.put(
				org.osgi.framework.Constants.SERVICE_PID,
				"org.ow2.aspirerfid.onewireproducer.event");
		properties.put(
				org.osgi.framework.Constants.SERVICE_DESCRIPTION,
				"a simple Producer that poll Event objects");

		serviceRegistration=context.registerService(Producer.class.getName(), this, properties);
	}
	
	private void unregisterProducer(){
	    serviceRegistration.unregister();
	}

	
	private void produceEvent(String owpath, String address, int type){
	    Event event=new Event("ow:"+owpath,"ow:"+address,type,System.currentTimeMillis());
	    lastEvent=event;

		for (int i = 0; wires != null && i < wires.length; i++) {
			Wire wire = wires[i];
			// check if wire is valid and connected ?
			if (!wire.isConnected() || !wire.isValid())
				continue;
			Object obj=polled(wire,event);
			if(obj!=null) wire.update(obj);
		}	    
	}
	
    private Object polled(Wire wire,Event event) {
		if(lastEvent!=null) {
		    Class clazzes[] = wire.getFlavors();
			for (int i = 0; i < clazzes.length; i++) {
				Class clazz = clazzes[i];
				if (clazz.isAssignableFrom(Event.class))
					return lastEvent;
				if (clazz.isAssignableFrom(String.class))
					return lastEvent.toString();
			}
		}
		return null;
    }
	
    /**
     * @see org.osgi.service.wireadmin.Producer#polled(org.osgi.service.wireadmin.Wire)
     */
    public Object polled(Wire wire) {
        return polled(wire,lastEvent);
    }

    /**
     * @see org.osgi.service.wireadmin.Producer#consumersConnected(org.osgi.service.wireadmin.Wire[])
     */
    public synchronized void consumersConnected(Wire[] wires) {
        this.wires=wires;
    }

}
