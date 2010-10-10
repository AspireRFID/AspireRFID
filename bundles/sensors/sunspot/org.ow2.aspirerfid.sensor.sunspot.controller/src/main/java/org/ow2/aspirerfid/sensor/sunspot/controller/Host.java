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
package org.ow2.aspirerfid.sensor.sunspot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;

import org.apache.felix.ipojo.ComponentInstance;
import org.apache.felix.ipojo.ConfigurationException;
import org.apache.felix.ipojo.Factory;
import org.apache.felix.ipojo.InstanceManager;
import org.apache.felix.ipojo.MissingHandlerException;
import org.apache.felix.ipojo.UnacceptableConfiguration;
import org.osgi.framework.Constants;
import org.osgi.util.measurement.Measurement;
import org.ow2.aspirerfid.sensor.sunspot.event.buttons.SwitchButtonEventPublisher;
import org.ow2.aspirerfid.sensor.sunspot.event.buttons.EventConstants.SwitchButton;
import org.ow2.aspirerfid.sensor.sunspot.producers.AccelerationListener;
import org.ow2.aspirerfid.sensor.sunspot.producers.AccelerationProducer;
import org.ow2.aspirerfid.sensor.sunspot.producers.TemperatureListener;
import org.ow2.aspirerfid.sensor.sunspot.producers.TemperatureProducer;

import com.sun.spot.io.j2me.radiogram.RadiogramConnection;

/**
 * @author Lionel Touseau
 * 
 * Listens to Sun SPOTS connections.
 * 
 * TODO complete description
 * 
 */
public class Host implements Runnable {
	
    private static final String SPOT_ADDRESS = "spot.address";
    private static final String REPLY_PORT = "reply.port";
	private static final String FLAVORS_PROPERTY = "flavors";

	/**
     * Broadcast port on which we listen for SPOT connections
     */
    private String hostPort;
    
    private String serialPort;
	
    private RadiogramConnection rCon;	
	
    private Factory spotServiceFactory;
    private Factory spotEventFactory;
    private Factory accelProducerFactory;
    private Factory tempProducerFactory;
    private Factory lumProducerFactory;
    private Factory batteryProducerFactory;
    
    private ComponentInstance sbepInstance;
    private SwitchButtonEventPublisher buttonEventPublisher; 
    
    private boolean end;
    
    private long heartBeatTimeout = 4000;
    
    private Timer timer;
    
	/**
	 * Map<IEEEAdress, List<componentInstances>>
	 */
	private Map<String, List<ComponentInstance>> components;
    
	/**
	 * Collection<IEEEAdress>
	 */
	private Set<String> connectedSpots;
	
	/**
	 * Map<IEEEAdress, timestamp>
	 */
	private Map<String, Long> lastHbReceived;
	
	public Host() {
		components = new HashMap<String, List<ComponentInstance>>();
		connectedSpots = new HashSet<String>();
		lastHbReceived = new HashMap<String, Long>();
	}
		
	// iPOJO callbacks
	
	public void start(){
		System.setProperty("SERIAL_PORT", serialPort);
		
		// create an instance of the publisher
		Properties config = new Properties();
		config.setProperty("instance.name", "spot-event-pub-inst");
		try {
			sbepInstance = spotEventFactory.createComponentInstance(config);
			buttonEventPublisher = (SwitchButtonEventPublisher) ((InstanceManager)sbepInstance).getPojoObject();			
        } catch(Exception e) {
            e.printStackTrace();
         }
		
		// Start listening to SPOT connection broadcasted messages
		end = false;
		Thread t = new Thread(this);
		t.start();
		timer = new Timer();
		System.out.println("HOST STARTED");
	}
	
	public void stop(){
		
		// TODO check for connected SPOTs and send them a termination message		
		
		// stops receiving data from the SunSPOT
		end = true;
		
		// stop the clock
		timer.cancel();
		
		try {
			rCon.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		// stop all components
		for (List<ComponentInstance> componentList : components.values()){
			for (ComponentInstance inst : componentList) {
	            
	            // unregisters the producer so it stops receiving updated measurements
	            Object pojo = ((InstanceManager)inst).getPojoObject();
				if (pojo instanceof AccelerationProducer) {
					AccelerationListener accelListener = AccelerationListener.getSingleton();
					if (accelListener != null) {
						accelListener.removeProducer((AccelerationProducer) pojo);
					}
				} else if (pojo instanceof TemperatureProducer) {
					TemperatureListener tempListener = TemperatureListener.getSingleton();
					if (tempListener != null){
						tempListener.removeProducer((TemperatureProducer) pojo);
					}
				}
				inst.dispose();
			}
		}
		components.clear();
		connectedSpots.clear();
		
		// including the event Publisher
		sbepInstance.dispose();
	}
	


	public void run() {
		
        rCon = null;
        Datagram dg = null;
         

        // Open up a server-side broadcast radiogram connection
        // to listen for spot incoming connections being sent by different SPOTs
    	try {
			rCon = (RadiogramConnection) Connector.open("radiogram://:" + hostPort);
//			rCon.setTimeout(heartBeatTimeout); TODO can be done with timeout ?
			dg = rCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
		} catch (IOException e) {
			e.printStackTrace();
//				throw e;
		}
		
        // Main loop
        while (!end) {
        	if (rCon==null || dg == null){
        		end = true;
        		break;
        	}
        	else {
            	try {
					rCon.receive(dg);
	            	// read sender's Id
	                String addr = dg.getAddress();
	                byte type = dg.readByte();
	                
	                if (type == PacketTypes.HEARTBEAT) {
		                
	                    lastHbReceived.put(addr, System.currentTimeMillis());
	                	
		            	// if the address was unknown, call the callback	                
		                if (!components.containsKey(addr)){
		                	int replyPort = dg.readInt();
		                	spotDiscovered(addr, replyPort);
		                	
		                } else {
		                	// consider this as a heartbeat message
		                	if (!connectedSpots.contains(addr)) {
		                		// the SPOT was previously detected disconnected
		                		spotReturned(addr);
		                	}
		                }
	                }
	                else if (type == PacketTypes.BUTTON) {
	                	
	                	// which button ?
	                	byte button = dg.readByte();
	                	SwitchButton switchButton = null;
	                	switch (button) {
	                		case PacketTypes.BUTTON_SW1:
	                			switchButton = SwitchButton.SW1;
	                			break;
	                		case PacketTypes.BUTTON_SW2:
	                			switchButton = SwitchButton.SW2;
	                			break;
	                		default :
	                			break;
	                	}
	                	
	                	// which event ?
	                	byte event = dg.readByte();
	                	switch (event) {
	                		case PacketTypes.BUTTON_PRESSED:
	                			buttonEventPublisher.fireButtonPressedEvent(switchButton, addr);
	                			break;
	                		case PacketTypes.BUTTON_RELEASED:
	                			buttonEventPublisher.fireButtonReleasedEvent(switchButton, addr);
	                			break;
	                		default : break;
	                	}
	                	
	                }
	                // else other commands ?
		            
            	} catch (IOException e) {
					e.printStackTrace();
				}
	                
	        } 				
        	
        }
	}

	private synchronized void spotDiscovered(String spotAddress, int replyPort) {
		
		connectedSpots.add(spotAddress);
		
		List<ComponentInstance> componentList = new ArrayList<ComponentInstance>(1);
		Hashtable<String, Object> configuration = new Hashtable<String, Object>();
		
		// create a SunSpotService component instance
        configuration.put("instance.name","sunspot-svc-inst-"+spotAddress);
        configuration.put(Constants.SERVICE_PID, spotServiceFactory.getName()+"@"+spotAddress);
        configuration.put(SPOT_ADDRESS, spotAddress);
        configuration.put(REPLY_PORT, Integer.toString(replyPort));
        try {
            ComponentInstance instance = spotServiceFactory.createComponentInstance(configuration);
            componentList.add(instance);
        } catch(Exception e) {
           e.printStackTrace();
        }
        
        
        // create a TemperatureProducer component instance
		configuration.clear();
        configuration.put("instance.name","sunspot-temp-prod-"+spotAddress);
        configuration.put(Constants.SERVICE_PID, tempProducerFactory.getName()+"@"+spotAddress);
        configuration.put(SPOT_ADDRESS, spotAddress);
        configuration.put(REPLY_PORT, replyPort);
//        configuration.put(FLAVORS_PROPERTY, new String[]{Measurement.class.getName()});
        configuration.put("wireadmin.producer.flavors", new Class[]{Measurement.class});
        try {
            ComponentInstance instance = tempProducerFactory.createComponentInstance(configuration);
            
            // registers the producer so it can receive updated measurements
            Object pojo = ((InstanceManager)instance).getPojoObject();
			if (pojo instanceof TemperatureProducer) {
				TemperatureListener tempListener = TemperatureListener.getSingleton();
				if (tempListener != null) {
					tempListener.addProducer((TemperatureProducer) pojo);
				}
			}
			
			componentList.add(instance);
        } catch(Exception e) {
           e.printStackTrace();
        }
        
        // create an AccelerationProducer component instance
		configuration.clear();
        configuration.put("instance.name","sunspot-accel-prod-"+spotAddress);
        configuration.put(Constants.SERVICE_PID, accelProducerFactory.getName()+"@"+spotAddress);
        configuration.put(SPOT_ADDRESS, spotAddress);
        configuration.put(REPLY_PORT, replyPort);
//        configuration.put(FLAVORS_PROPERTY, new String[]{Measurement.class.getName()});
        configuration.put("wireadmin.producer.flavors", new Class[]{Measurement[].class});
        try {
            ComponentInstance instance = accelProducerFactory.createComponentInstance(configuration);
            
            // registers the producer so it can receive updated measurements
            Object pojo = ((InstanceManager)instance).getPojoObject();
			if (pojo instanceof AccelerationProducer) {
				AccelerationListener accelListener = AccelerationListener.getSingleton();
				if (accelListener != null){
					accelListener.addProducer((AccelerationProducer) pojo);
				}
			}

            componentList.add(instance);
        } catch(Exception e) {
           e.printStackTrace();
        }
        
        // TODO do the same with other producers
        
//        // open a connection to reply to the SPOT
//        try {
//			RadiogramConnection radCon = (RadiogramConnection) Connector.open("radiogram://"+spotAddress+":"+replyPort);
//			Datagram dg = radCon.newDatagram(PacketTypes.DEFAULT_DATAGRAM_SIZE);
//			dg.writeByte(PacketTypes.ADMIN_SVC_CONNECTION);
//			dg.writeInt(PacketTypes.ADMIN_SVC_PORT);
//			// send the command to open a channel
//			radCon.send(dg);
//			
//			// the same for temperature producer
//			dg.reset();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
        
        components.put(spotAddress, componentList);
        
        
        // Schedule a timer tasks that will listen to heartbeats
        final String address = spotAddress;
    	TimerTask tt = new TimerTask() {
    		public void run() {
    			if (connectedSpots.contains(address)
    				&& System.currentTimeMillis() - lastHbReceived.get(address) > heartBeatTimeout) {
    				onTimeout(address);
    			}
    		}
    	};
    	timer.schedule(tt, heartBeatTimeout, heartBeatTimeout);
        
	}	
	
	private synchronized void spotReturned(String spotAddress) {
		connectedSpots.add(spotAddress);
		// revalidate all components
		setValidity(spotAddress, true);
		
	}

	
	/**
	 * On a heartbeat timeout, notify all components of the sunspot disconnection
	 * @see org.ow2.aspirerfid.sensor.sunspot.controller.TimerCallback#onTimeout(java.lang.String)
	 */
	public void onTimeout(String spotAddress) {
		System.out.println("onTIMEOUT called");
		
		connectedSpots.remove(spotAddress);
		
		setValidity(spotAddress, false);
		
	}

	private void setValidity(String spotAddress, boolean validity) {
		List<ComponentInstance> comps = components.get(spotAddress);
		for (ComponentInstance comp : comps) {
			Object pojo = ((InstanceManager)comp).getPojoObject();
			if (pojo instanceof LifecycleController) {
				((LifecycleController) pojo).setState(validity);
			}
		}
	}
	
}
