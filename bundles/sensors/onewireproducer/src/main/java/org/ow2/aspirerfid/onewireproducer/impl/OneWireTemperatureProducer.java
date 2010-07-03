/*
 * OneWire Bundle that register org.osgi.service.wireadmin.Producer interfaces when 1-Wire sensors appear on the 1-Wire bus
 *
 * Copyright (C) 2003  Didier Donsez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Contact: Didier Donsez (Didier.Donsez@ieee.org)
 * Contributor(s):
 *
**/
package org.ow2.aspirerfid.onewireproducer.impl;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.util.measurement.Measurement;
import org.osgi.util.measurement.Unit;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.container.ClockContainer;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.container.OneWireSensor;
import com.dalsemi.onewire.container.TemperatureContainer;

public class OneWireTemperatureProducer extends OneWireAbstractProducer {

	final static double ZERODEGRRECELCIUS_IN_K=273.15;
	
	public OneWireTemperatureProducer( BundleContext context, OneWireContainer onewirecontainer, int pollInterval) {
		super(context,onewirecontainer,pollInterval);
	}

	protected Dictionary getProducerProperties(){
		Dictionary p = super.getProducerProperties();
		p.put( org.osgi.service.wireadmin.WireConstants.WIREADMIN_PRODUCER_FLAVORS,
			new Class[] { Measurement.class, String.class });
		p.put( "value.unit","K"); // TODO value.unit should a common constant
		p.put( "application.name","temperature"); // TODO application.name should a common constant
		return p;
	}
	
	protected Measurement getCurrentMeasurement() {
		Measurement result=null;
			// one wire command
			DSPortAdapter dsportadapter=onewirecontainer.getAdapter();
		try{
			dsportadapter.beginExclusive(true);

			byte[] state = ((OneWireSensor)onewirecontainer).readDevice();

			TemperatureContainer tc = ( TemperatureContainer ) onewirecontainer;
			double tempres = tc.getTemperatureResolution(state);
			double temperatureInC = tc.getTemperature(state);

			long current_time;
			if(onewirecontainer instanceof ClockContainer){
				// device time
				ClockContainer cc = ( ClockContainer ) onewirecontainer;
				current_time = cc.getClock(state);
			} else {
				// system time
				current_time = System.currentTimeMillis();
			}

			

			result= new Measurement(convertCtoK(temperatureInC),tempres,Unit.K,current_time);
		} catch(OneWireException owe){
			owe.printStackTrace(System.err);
			result=null;
		} finally {
			dsportadapter.endExclusive();
		}
		return result;
	}

	private double convertCtoK(double temperatureInC){
		return 	temperatureInC+ZERODEGRRECELCIUS_IN_K;
	}
}