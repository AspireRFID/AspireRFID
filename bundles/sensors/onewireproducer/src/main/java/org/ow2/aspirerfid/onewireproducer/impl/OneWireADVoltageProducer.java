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
import org.osgi.util.measurement.Measurement;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.container.ADContainer;
import com.dalsemi.onewire.container.ClockContainer;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.container.OneWireSensor;

public class OneWireADVoltageProducer extends OneWireAbstractProducer {
	int channel;

	public OneWireADVoltageProducer( BundleContext context, OneWireContainer onewirecontainer, int pollInterval) {
		super(context,onewirecontainer,pollInterval);
		this.channel=0;
	}

	protected Dictionary getProducerProperties(){
		Dictionary p = super.getProducerProperties();
		ADContainer adcontainer=(ADContainer) onewirecontainer;
		//if(adcontainer.canADMultiChannelRead()){
		//	p.put( org.osgi.service.wireadmin.WireConstants.WIREADMIN_PRODUCER_FLAVORS,
		//		new Class[] { Measurement[].class, String[].class });
		//} else {
		p.put( org.osgi.service.wireadmin.WireConstants.WIREADMIN_PRODUCER_FLAVORS,
				new Class[] { Measurement.class, String.class });
		p.put( "value.unit","V"); // TODO value.unit should a common constant
		p.put( "application.name","voltage"); // TODO application.name should a common constant
		//}
		//p.put( "Range0", adcontainer.getADRange()[0]);			
		//p.put( "Range1", adcontainer.getADRange()[1]);			
		return p;
	}
	
	protected Measurement getCurrentMeasurement() {
		Measurement result=null;
		// one wire command
		DSPortAdapter dsportadapter=onewirecontainer.getAdapter();
		try{
			dsportadapter.beginExclusive(true);

			byte[] state = ((OneWireSensor)onewirecontainer).readDevice();

			ADContainer adc = ( ADContainer ) onewirecontainer;
			double adRes = adc.getADResolution(channel,state);
			double ad = adc.getADVoltage(channel,state); // Unit is Volt

			long current_time;
			if(onewirecontainer instanceof ClockContainer){
				// device time
				ClockContainer cc = ( ClockContainer ) onewirecontainer;
				current_time = cc.getClock(state);
			} else {
				// system time
				current_time = System.currentTimeMillis();
			}

			result= new Measurement(ad,adRes,null,current_time); // TODO fix a Unit
		} catch(OneWireException owe){
			owe.printStackTrace(System.err);
			result=null;
		} finally {
			dsportadapter.endExclusive();
		}
		return result;
	}
}