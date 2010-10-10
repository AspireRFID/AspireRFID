package org.ow2.aspirerfid.sensor.sunspot.service.impl;

import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;

import org.ow2.aspirerfid.sensor.sunspot.controller.LifecycleController;
import org.ow2.aspirerfid.sensor.sunspot.controller.PacketTypes;
import org.ow2.aspirerfid.sensor.sunspot.service.SunSpotService;

import com.sun.spot.io.j2me.radiogram.RadiogramConnection;

public class SunSpotServiceImpl implements SunSpotService, LifecycleController {
	
	
	private boolean spotConnected;
	private String spotAddress; // injected by iPOJO
	private int replyPort; // injected by iPOJO
	
	private RadiogramConnection radCon;
	
	public SunSpotServiceImpl() {
		spotConnected = true;
	}
	
	/**
	 * iPOJO Validate callback
	 */
	public void start() {		
		
		System.out.println("CONTROL SERVICE STARTED");
		
		// open a connection to reply to the SPOT
		try {
			radCon = (RadiogramConnection) Connector.open("radiogram://"+spotAddress+":"+replyPort);
			Datagram dg = radCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
			dg.writeByte(PacketTypes.ADMIN_SVC_CONNECTION);
			dg.writeInt(PacketTypes.ADMIN_SVC_PORT);
			
			// send a command to open a new channel
			radCon.send(dg);
			
			dg.reset();
			radCon.close();
			// open a new connection to communicate with the SPOT
			radCon =  (RadiogramConnection) Connector.open("radiogram://"+spotAddress+":"+PacketTypes.ADMIN_SVC_PORT);
			
//			// TODO erase test
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			dg = radCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
//			dg.writeByte(PacketTypes.BLINK_LEDS_REQ);
//			radCon.send(dg);
//			
//			System.out.println("request for LEDs sent");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * iPOJO Invalidate callback
	 */
	public void stop() {		
		// close the opened connection
		try {
			radCon.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setState(boolean valid) {
		spotConnected = valid;
		System.out.println("setState Changed ! VALID="+valid);
	}
	
	
	
	
	public void setAccelerometerSampleDelay (long delay) {
		try {
			Datagram dg = radCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
			dg.writeByte(PacketTypes.SET_ACCEL_SAMPLE_DELAY);
			dg.writeLong(delay);
			radCon.send(dg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void startAccelerometer() {
		// TODO Auto-generated method stub
		
	}

	public void startLuminosity() {
		// TODO Auto-generated method stub
		
	}

	public void startTemperature() {
		// TODO Auto-generated method stub
		
	}

	public void stopAccelerometer() {
		// TODO Auto-generated method stub
		
	}

	public void stopLuminosity() {
		// TODO Auto-generated method stub
		
	}

	public void stopTemperature() {
		// TODO Auto-generated method stub
		
	}

	public String getAddress() {
		return spotAddress;
	}

	public int getBatteryLevel() {
		int batteryLevel = 0;
		try {
			Datagram dg = radCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
			dg.writeByte(PacketTypes.GET_BATTERY_REQ);
			radCon.send(dg);
			Datagram reply = radCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
			radCon.receive(reply);
			if (reply.readByte() == PacketTypes.GET_BATTERY_REQ){
				batteryLevel = reply.readInt();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return batteryLevel;
	}

	public void blinkLEDs(int times) {
		Datagram dg;
		try {
			dg = radCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
			dg.writeByte(PacketTypes.BLINK_LEDS_REQ);
			dg.writeInt(times);
			radCon.send(dg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ledsOff() {
		Datagram dg;
		try {
			dg = radCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
			dg.writeByte(PacketTypes.LEDS_OFF);
			radCon.send(dg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ledsOn() {
		// TODO Auto-generated method stub
		
	}

	public void setLED(int ledNumber, LEDColor color) {
		setLED(ledNumber, color, -1);
	}

	public void setLED(int ledNumber, LEDColor color, long duration) {
		Datagram dg;
		try {
			dg = radCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
			dg.writeByte(PacketTypes.SET_LED_DURATION_REQ);
			dg.writeInt(ledNumber);
			dg.writeUTF(color.name());
			dg.writeLong(duration);
			radCon.send(dg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setLEDs(int[] ledNumbers, LEDColor color) {
		setLEDs(ledNumbers, color, -1);
		
	}

	public void setLEDs(int[] ledNumbers, LEDColor color, long duration) {
		Datagram dg;		
		try {
			dg = radCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
			dg.writeByte(PacketTypes.SET_LEDS_DURATION_REQ);
			dg.writeInt(ledNumbers.length);
			for (int i=0; i < ledNumbers.length; i++){
                dg.writeInt(ledNumbers[i]);
            } 
			dg.writeUTF(color.name());
			dg.writeLong(duration);
			radCon.send(dg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
