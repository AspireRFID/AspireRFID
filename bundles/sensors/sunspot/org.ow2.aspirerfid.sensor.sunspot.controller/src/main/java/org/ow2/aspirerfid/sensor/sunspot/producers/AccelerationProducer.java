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

import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;

import org.osgi.framework.BundleContext;
import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.osgi.util.measurement.Measurement;
import org.osgi.util.measurement.Unit;
import org.ow2.aspirerfid.sensor.sunspot.controller.LifecycleController;
import org.ow2.aspirerfid.sensor.sunspot.controller.PacketTypes;

import com.sun.spot.io.j2me.radiogram.RadiogramConnection;

/**
 * @author Lionel Touseau
 * Acceleration Producer that reads measurements sent to the base station by SPOTs
 * 
 */
public class AccelerationProducer implements Producer, LifecycleController {

	private static final long DEFAULT_SAMPLE_DELAY = 200;
	
//	private boolean end;
	
	private Wire[] wires;
	
	/**
	 * IEEE Address of the corresponding SPOT sensor
	 * injected by iPOJO (instance configuration)
	 */
	private String spotAddress;
	
	private int replyPort;
	
	private Class[] flavors;
	
	/**
	 * iPOJO Lifecycle controller
	 */
	private boolean spotConnected;
	
	private RadiogramConnection radCon;
	
	// initializes an array for 3-axis acceleration measurements
	private Measurement[] accelerationMeasurements = new Measurement[3];
	
	public AccelerationProducer(BundleContext ctx){
		spotConnected = true;
	}
	
	// iPOJO life-cycle callbacks
	
	public void start(){
		System.out.println("SunSPOT acceleration producer activated");
		
		try {
			radCon = (RadiogramConnection) Connector.open("radiogram://"+spotAddress+":"+replyPort);
			Datagram dg = radCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
			dg.writeByte(PacketTypes.ACCEL_PROD_CONNECTION);
			dg.writeInt(PacketTypes.ACCEL_PROD_PORT);
			dg.writeLong(DEFAULT_SAMPLE_DELAY);
			// send the command to open a new channel
			radCon.send(dg);
			radCon.close();
//			// open a new connection to receive from the SPOT
//			radCon =  (RadiogramConnection) Connector.open("radiogram://"+":"+PacketTypes.ACCEL_PROD_PORT);		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		end = false;
//		Thread t = new Thread (this,"SunSPOT-acceleration-producer@"+spotAddress);
//		t.start();
	}
	
	public void stop(){
//		end = true;
//		// close the opened connection
//		try {
//			radCon.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		System.out.println("SunSPOT acceleration producer deactivated");		
	}	

	public String getSpotAddress() {
		return spotAddress;
	}
	
	public void accelerationReceived(double xAccel, double yAccel, double zAccel) {
		if (wires != null && wires.length > 0){
			accelerationMeasurements[0] = new Measurement(xAccel, 0.01, Unit.m_s2, System.currentTimeMillis());
			accelerationMeasurements[1] = new Measurement(yAccel, 0.01, Unit.m_s2, System.currentTimeMillis());
			accelerationMeasurements[2] = new Measurement(zAccel, 0.01, Unit.m_s2, System.currentTimeMillis());
			
			for (Wire wire : wires){
				wire.update(accelerationMeasurements);
			}
		}		
	}
	
//	public void run() {
//		
//		// open a connection to reply to the SPOT
//		try {
//			Datagram dg = radCon.newDatagram(radCon.getMaximumLength());
//
//			// wait to receive updated acceleration values
//			while (!end) {
//				dg.reset();
//				radCon.receive(dg);
//				byte type = dg.readByte();
//				if (type == PacketTypes.ACCELERATION) {
//					double xAccel = dg.readDouble();
//					double yAccel = dg.readDouble();
//					double zAccel = dg.readDouble();
//					if (wires != null && wires.length > 0){
//						accelerationMeasurements[0] = new Measurement(xAccel, 0.01, Unit.m_s2, System.currentTimeMillis());
//						accelerationMeasurements[1] = new Measurement(yAccel, 0.01, Unit.m_s2, System.currentTimeMillis());
//						accelerationMeasurements[2] = new Measurement(zAccel, 0.01, Unit.m_s2, System.currentTimeMillis());
//						
//						for (Wire wire : wires){
//							wire.update(accelerationMeasurements);
//						}
//					}
//				}
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
	
	public void consumersConnected(Wire[] wires) {
		this.wires = wires;
	}

	public Object polled(Wire wire) {
		return accelerationMeasurements;
		// TODO ask a more recent value to the SPOT
	}

	public void setState(boolean valid) {
		spotConnected = valid;
	}

}
