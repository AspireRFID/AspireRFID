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

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.osgi.util.measurement.Measurement;

import com.dalsemi.onewire.container.OneWireContainer;

public abstract class OneWireAbstractProducer implements Producer, Runnable {

	private Wire wires[];
	private BundleContext context;
	private ServiceRegistration servreg;
	private int pollInterval;
	private boolean quit;

	protected OneWireContainer onewirecontainer;

	public OneWireAbstractProducer( BundleContext context, OneWireContainer onewirecontainer, int pollInterval) {
		this.context = context;
		this.onewirecontainer=onewirecontainer;
		this.pollInterval=pollInterval;
		servreg=null;
		quit=false;
		new Thread(this).start();
	}
		
	final public void end() {
		this.quit=true;
	}

	final public synchronized void run() {

		servreg = context.registerService( Producer.class.getName(),this,getProducerProperties());
		while( ! quit )
			try {
				Measurement currentMeasurement=getCurrentMeasurement();
				for( int i=0; wires!=null && i<wires.length; i++ ) {
					Wire wire=wires[i];
					if(wire.isConnected() && wire.isValid()) {
						wire.update( currentMeasurement );
					}
				}
				wait(pollInterval);
			}
			catch( InterruptedException ie) {
				/* will recheck quit */
			}

		servreg.unregister();
		System.out.println(this.toString()+" completed");
	}

	final public synchronized void consumersConnected(Wire wires[]){
		this.wires = wires;
	}

	final public Object polled(Wire wire) {
		if(wire==null) return null;
//			return getCurrentMeasurement();

		Class clazzes[] = wire.getFlavors();
		for ( int i=0; i<clazzes.length; i++ ) {
			Class clazz = clazzes[i];
			if ( clazz.isAssignableFrom( Measurement.class ) ){
				return getCurrentMeasurement();
			}
			if ( clazz.isAssignableFrom( String.class ) ){
				return getCurrentMeasurement().toString();
			}
		}
		return null;
	}

	protected String getServicePid(String owAddress){
		StringBuffer sb=new StringBuffer();
		sb.append("ONE.")
			.append(owAddress.substring(owAddress.length()-2))
			.append('-')
			.append(owAddress.substring(0,owAddress.length()-2));
		return sb.toString();
	}
	
	protected Dictionary getProducerProperties(){
		Dictionary p = new Hashtable();
		p.put( "onewire.address", onewirecontainer.getAddressAsString());
		p.put( "onewire.alternatenames", onewirecontainer.getAlternateNames());
		p.put( "onewire.description", onewirecontainer.getDescription());
		p.put( Constants.SERVICE_DESCRIPTION, onewirecontainer.getDescription());
		p.put( org.osgi.framework.Constants.SERVICE_PID,
				getServicePid(onewirecontainer.getAddressAsString()));
		return p;
	}

	abstract protected Measurement getCurrentMeasurement();
}