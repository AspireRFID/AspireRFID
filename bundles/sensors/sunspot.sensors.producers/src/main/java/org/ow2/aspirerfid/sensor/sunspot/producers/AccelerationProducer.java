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
package org.ow2.aspirerfid.sensor.sunspot.producers;

import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.osgi.service.wireadmin.WireConstants;
import org.osgi.util.measurement.Measurement;
import org.osgi.util.measurement.Unit;
import org.ow2.aspirerfid.sensor.sunspot.host.HostServer;

public class AccelerationProducer implements Producer, Runnable {
	
	private boolean m_end;
	
	private Wire[] m_wires;
	
	private BundleContext m_bc;
	
	private ServiceRegistration m_sr;
	
	private final String PID="org.ow2.aspirerfid.sensor.sunspot.producers.acceleration";
	
	private long UPDATE_DELAY = 200;
	
	private HostServer m_host;
	
	/**
	 * IEEE Address of the corresponding SPOT sensor
	 */
	private String m_address;
	
	private long m_spotUpdateDelay = -1000; // just to wait for the sending of measurements
	private long m_lastMeasurementTime = 0;
	
	public AccelerationProducer(BundleContext bc, String address){
		m_bc = bc;
		m_address = address;		
	}
	
	
	// iPOJO life-cycle callbacks
	
	public void start(){
		System.out.println("SunSPOT acceleration producer activated");
		
		Class[] flavors = new Class[]{Measurement[].class};
		
		// publish the producer service
		Hashtable<String,Object> props = new Hashtable<String, Object>();
		String specificPID = PID+"@{spot:"+m_address+"}";		
		props.put(Constants.SERVICE_PID, specificPID);
		props.put(WireConstants.WIREADMIN_PRODUCER_FLAVORS, flavors);
		props.put(PropertyConstants.DATA_TYPE, PropertyConstants.ACCELERATION_TYPE);		
		m_sr = m_bc.registerService(Producer.class.getName(), this, props);
		
		m_host = HostServer.getSingleton();
		
		m_end = false;
		Thread t = new Thread (this,"SunSPOT-acceleration-producer");
		t.start();		
	}
	
	public void stop(){
		m_end = true;		
		m_sr.unregister();
		System.out.println("SunSPOT acceleration producer deactivated");		
	}
	
	public void consumersConnected(Wire[] wires) {
		m_wires = wires;
	}

	public Object polled(Wire wire) {
		Measurement[] accelMeasurements = new Measurement[3];
		double[] acceleration = m_host.getAcceleration(m_address);
		for (int i = 0; i < 2; i++){
			accelMeasurements[i] = new Measurement(acceleration[i],Unit.m_s2);
		}
		return accelMeasurements;
	}

	public void run() {
		Measurement[] accelMeasurements = new Measurement[3];
		dataFlow:
		while (!m_end){
			// Verify if the temperature value has been updated
			long time = m_host.getLastMeasurementTime(m_address);
			
			if (time > m_lastMeasurementTime) { // everything is fine
				m_lastMeasurementTime = time;
			} else { // no new sensor value has been received from the SPOT
				// maybe the producer wants to update more often than the sensor actually can
				try {
					Thread.sleep(UPDATE_DELAY - m_spotUpdateDelay);
					time = m_host.getLastMeasurementTime(m_address);
				} catch (InterruptedException e) {
					System.err.println("Acceleration Producer has been interrupted");;
				}
				if (time > m_lastMeasurementTime) { // Now everything is fine
					m_lastMeasurementTime = time;
				} else {
					// producer must be stopped
					m_host.stopProducer(m_address);
					break dataFlow;
					
				}
			}			
			
			double[] acceleration = m_host.getAcceleration(m_address);
//			if (acceleration != null)System.out.println("Acceleration updated : "
//					+"aX="+acceleration[0]
//					+"\taY="+acceleration[1]
//					+"\taZ="+acceleration[2]);			
			if (m_wires != null && acceleration != null){
				for (int i = 0; i < acceleration.length; i++){
					accelMeasurements[i] = new Measurement(acceleration[i],Unit.m_s2);
				}
				for (Wire wire : m_wires){
					wire.update(accelMeasurements);
				}
			}
			
			// wait for "UPDATE_DELAY" milliseconds
			try {
				Thread.sleep(UPDATE_DELAY);
			} catch (InterruptedException e) {
				System.err.println("Accelerometer Producer has been interrupted");;
			}
		}
	}

}
