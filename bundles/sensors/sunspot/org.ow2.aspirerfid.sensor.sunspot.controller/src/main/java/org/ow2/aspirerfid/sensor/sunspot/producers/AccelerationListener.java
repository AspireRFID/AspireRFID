package org.ow2.aspirerfid.sensor.sunspot.producers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;

import org.osgi.service.wireadmin.Wire;
import org.osgi.util.measurement.Measurement;
import org.osgi.util.measurement.Unit;
import org.ow2.aspirerfid.sensor.sunspot.controller.PacketTypes;

import com.sun.spot.io.j2me.radiogram.RadiogramConnection;

/**
 * An Acceleration listener always listens for accelerations emitted by SPOTs. 
 * @author lionel
 *
 */
public class AccelerationListener implements Runnable {

	private int listenPort = PacketTypes.ACCEL_PROD_PORT;
	
	private boolean end;
	
	private RadiogramConnection radCon;
	
	private Map<String, AccelerationProducer> prods;
	
	private static AccelerationListener instance = null;
	
	private AccelerationListener(){
		prods = new HashMap<String, AccelerationProducer>();
		instance = this;
	}
	
	public static AccelerationListener getSingleton(){
		return instance;
	}
	
	private void start() {
		end = false;
		Thread t = new Thread (this,"SunSPOT-acceleration-listener");
		t.start();
	}
	
	private void stop(){
		end = true;
		// close the opened connection
		try {
			radCon.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("SunSPOT acceleration listener deactivated");		
	}	
	
	public void run() {
		// open a new connection to receive from the SPOT
		try {
			radCon =  (RadiogramConnection) Connector.open("radiogram://"+":"+listenPort);
			Datagram dg = radCon.newDatagram(radCon.getMaximumLength()); // TODO change length

			// wait to receive updated acceleration values
			while (!end) {
				dg.reset();
				radCon.receive(dg);
				String spotAddress = dg.getAddress();
				byte type = dg.readByte();
				if (type == PacketTypes.ACCELERATION) {
					double xAccel = dg.readDouble();
					double yAccel = dg.readDouble();
					double zAccel = dg.readDouble();
					
					// notify the acceleration producer
					if (prods.containsKey(spotAddress)){
						prods.get(spotAddress).accelerationReceived(xAccel, yAccel, zAccel);
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void addProducer(AccelerationProducer prod) {
		prods.put(prod.getSpotAddress(), prod);
	}
	
	public synchronized void removeProducer(AccelerationProducer prod) {
		prods.remove(prod.getSpotAddress());
	}

}
