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

import org.osgi.framework.BundleContext;
import org.osgi.util.measurement.Unit;
import org.osgi.util.measurement.Measurement;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.container.ClockContainer;
import com.dalsemi.onewire.container.HumidityContainer;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.container.OneWireSensor;

public class OneWireHumidityProducer extends OneWireAbstractProducer {
	OneWireContainer onewirecontainer;

	public OneWireHumidityProducer( BundleContext context, OneWireContainer onewirecontainer, int pollInterval) {
		super(context,onewirecontainer,pollInterval);
	}

	protected Dictionary getProducerProperties(){
		Dictionary p = super.getProducerProperties();
		p.put( org.osgi.service.wireadmin.WireConstants.WIREADMIN_PRODUCER_FLAVORS,
			new Class[] { Measurement.class, String.class });
		p.put( "application.name","hygrometry"); // TODO application.name should a common constant
		return p;
	}
	
	protected Measurement getCurrentMeasurement() {
		Measurement result=null;
			// one wire command
			DSPortAdapter dsportadapter=onewirecontainer.getAdapter();
		try{
			dsportadapter.beginExclusive(true);

			byte[] state = ((OneWireSensor)onewirecontainer).readDevice();

			HumidityContainer hc = ( HumidityContainer ) onewirecontainer;
			double humidityRes = hc.getHumidityResolution(state);
			double humidity = hc.getHumidity(state)/100.0d; // get percent value (0.0 to 100.0) of humidity

			long current_time;
			if(onewirecontainer instanceof ClockContainer){
				// device time
				ClockContainer cc = ( ClockContainer ) onewirecontainer;
				current_time = cc.getClock(state);
			} else {
				// system time
				current_time = System.currentTimeMillis();
			}
			// result= new Measurement(humidity,humidityRes,Unit.UNITY,current_time); // TODO fix a Unit
			result= new Measurement(humidity,humidityRes,null,current_time); // TODO fix a Unit
		} catch(OneWireException owe){
			owe.printStackTrace(System.err);
			result=null;
		} finally {
			dsportadapter.endExclusive();
		}
		return result;
	}
}