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
package org.ow2.aspirerfid.sensor.sunspot.host;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;

import org.osgi.framework.BundleContext;
import org.osgi.service.wireadmin.Producer;
import org.ow2.aspirerfid.sensor.sunspot.host.config.Configuration;
import org.ow2.aspirerfid.sensor.sunspot.producers.AccelerationProducer;
import org.ow2.aspirerfid.sensor.sunspot.producers.LuminosityProducer;
import org.ow2.aspirerfid.sensor.sunspot.producers.TemperatureProducer;

import com.sun.spot.io.j2me.radiogram.RadiogramConnection;

/**
 * @author Lionel Touseau
 * 
 * Listens to Sun SPOTS connections.
 * Reads luminosity and temperature value from a SPOT and accelerometer values from another SPOT.
 * 
 * TODO detect end of communication with a SPOT to stop the corresponding producer
 * 
 */
public class HostServer implements Runnable {
	
	private static HostServer singleton;
	
    // Broadcast port on which we listen for sensor samples
//    private int HOST_PORT = 67;
    private int m_hostPort;
    
//	private String STATION_SERIAL_PORT = "COM5";
    private String m_serialPort;
	
	private AccelerometerListener m_listener; // TODO transform into a HashMap
	
    private RadiogramConnection m_rCon;	
	
    private boolean m_end;
    
	/**
	 * Map<IEEEAdress, 3-axis Accelerations>
	 */
	private Hashtable<String, double[]> m_accelerations;
	
	/**
	 * Map<IEEEAdress, Temperature>
	 */
	private Hashtable<String, Double> m_temperatures;
	
	/**
	 * Map<IEEEAdress, Luminosity>
	 */
	private Hashtable<String, Integer> m_luminosities;
	
	/**
	 * Map<IEEEAdress, Time>
	 */
	private Hashtable<String, Long> m_lastMeasurementTimes;
	
	/**
	 * Map<IEEEAdress, {TemperatureProducer, LuminosityProducer}>
	 * or Map<IEEEAdress, {AccelerationProducer}>
	 */
	private Hashtable<String, Producer[]> m_producers;
	
	private BundleContext m_bundleContext;
	
	/**
	 * Default configuration file location
	 */
	private static final String m_CONFIGFILE = "/config.properties";	
	
	private HostServer(BundleContext bc){
//		if (singleton == null){
			singleton = this;
			m_bundleContext = bc;
			configurePorts();
			System.setProperty("SERIAL_PORT", m_serialPort /*STATION_SERIAL_PORT*/);
			m_listener = new AccelerometerListener();
		
			m_producers = new Hashtable<String, Producer[]>();
			m_temperatures = new Hashtable<String, Double>();
			m_luminosities = new Hashtable<String, Integer>();
			m_accelerations = new Hashtable<String, double[]>();
			m_lastMeasurementTimes = new Hashtable<String, Long>();
//		}
		
	}
	
	private void configurePorts() {

		String configLocation = m_CONFIGFILE;
		
		// Load properties from configLocation file
		InputStream is = getClass().getResourceAsStream(configLocation);
		Properties configurationProperties = Configuration.loadProperties(is);

		String hostPortProperty = (String)configurationProperties.get(Configuration.HOST_PORT);
		m_hostPort = 67; // default host port
		if (hostPortProperty != null) {
			m_hostPort = Integer.parseInt(hostPortProperty);
		}
		
		String serialPortProperty = (String)configurationProperties.get(Configuration.STATION_SERIAL_PORT);
		m_serialPort = "COM8"; // default
		if (serialPortProperty != null) {
			m_serialPort = serialPortProperty;
		}
		
	}	
	
	// iPOJO callbacks
	
	public void start(){
		// starts the accelerometer listener.		
		m_listener.start();
		m_end = false;
		Thread t = new Thread(this);
		t.start();
	}
	
	public void stop(){
		// stops receiving data from the SunSPOT
		m_end = true;
		try {
			m_rCon.close();
			System.out.println("Connection closed on port "+m_hostPort);	
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		// switch off the accelerometer listener
		m_listener.doBlink();
		m_listener.doSendData(false);
		m_listener.doQuit();
		m_listener = null;
		
		stopAllProducers();
		
	}
	

	public void run() {
		
        m_rCon = null;
        Datagram dg = null;
        DateFormat fmt = DateFormat.getTimeInstance();
         
        try {
            // Open up a server-side broadcast radiogram connection
            // to listen for sensor readings being sent by different SPOTs
        	m_rCon = (RadiogramConnection) Connector.open("radiogram://:" + m_hostPort);
            dg = m_rCon.newDatagram(m_rCon.getMaximumLength());
        } catch (Exception e) {
             System.err.println("setUp caught " + e.getMessage());
//             throw e;
        }


        // Main data collection loop
        while (!m_end) {
            try {
                // Read sensor sample received over the radio
            	if (m_rCon==null || dg == null){
            		m_end = true;
            		break;
            	}
            	else {
	            	m_rCon.receive(dg);
	                String addr = dg.getAddress();  // read sender's Id
	                
	                if (!m_producers.containsKey(addr)){ // if the address is unknown, create new producers
	                	TemperatureProducer temp = new TemperatureProducer(m_bundleContext, addr);
	                	LuminosityProducer lum = new LuminosityProducer(m_bundleContext, addr);
	                	m_producers.put(addr, new Producer[]{temp, lum});
	                	temp.start();
	                	lum.start();
	                }
	                
	                long time = dg.readLong();      // read time of the reading
	                int lumVal = dg.readInt();         // read the luminosity sensor value
	                double tempVal = dg.readDouble();	// read the temperature sensor value
	                m_lastMeasurementTimes.put(addr, time); // memorize the timestamp
	                updateLuminosity(lumVal, addr);
	                updateTemperature(tempVal, addr);
	//                System.out.println(fmt.format(new Date(time)) + "  from: "+ addr
	//                		+ "   luminosity value = " + lumVal
	//                		+ "   temperature value = " + tempVal);
            	}
            	
            } catch (Exception e) {
                System.err.println("Caught " + e +  " while reading sensor samples.");
                
            }
        }	
	}	
	
	public static HostServer getSingleton(){
		return singleton;
	}
	
	public void startAccelerationProducer(String address){
		AccelerationProducer accelProd = new AccelerationProducer(m_bundleContext, address);
		m_producers.put(address, new Producer[]{accelProd});
		accelProd.start();
	}
	
	
	/**
	 * Callback method called by the AccelerationListener
	 * @param accel
	 * @param timeStamp 
	 * @param address 
	 */
	public void updateAcceleration(double[] accel, String address, long timeStamp){
		m_accelerations.put(address, accel);
		m_lastMeasurementTimes.put(address, timeStamp);
	}
	
	public void updateLuminosity(int luminosity, String address){
		m_luminosities.put(address, luminosity);
	}

	public void updateTemperature(double temperature, String address){
		m_temperatures.put(address, temperature);
	}
	
	/**
	 * @param address, the IEEE address of the SPOT
	 * @return the 3-axis accelerations of the corresponding SPOT sensor
	 */
	public double[] getAcceleration(String address) {
		return (m_accelerations.containsKey(address)) ? m_accelerations.get(address) : new double[]{0,0,0};
	}
	
	/**
	 * @param address, the IEEE address of the SPOT
	 * @return the luminosity for the corresponding SPOT sensor
	 */
	public int getLuminosity(String address) {
		return (m_luminosities.containsKey(address)) ? m_luminosities.get(address) : 0;
	}
	
	/**
	 * @param address, the IEEE address of the SPOT
	 * @return the temperature for the corresponding SPOT sensor
	 */
	public double getTemperature(String address) {
		return (m_temperatures.containsKey(address)) ? m_temperatures.get(address) : 0.0d;
	}

	/**
	 * @param address, the IEEE address of the SPOT
	 * @return the last time the corresponding SPOT sensor has sent a value
	 */	
	public long getLastMeasurementTime(String address) {
		return (m_lastMeasurementTimes.containsKey(address)) ? m_lastMeasurementTimes.get(address) : 0;
	}
	
	public synchronized void stopProducer (String address){
		Producer[] prods = m_producers.remove(address);
		if (prods != null){
			switch (prods.length) {
			case 1:
				((AccelerationProducer) prods[0]).stop();
				if (m_listener != null)
					m_listener.reconnect();
				break;
			case 2:
				((TemperatureProducer) prods[0]).stop();
				((LuminosityProducer) prods[1]).stop();
				break;	
			default:
				break;
			}
		}
	}
	
	private void stopAllProducers(){
		// stop all producers
		String[] addresses = new String[m_producers.size()];
		int i = 0;
		for (String spotAddress : m_producers.keySet()){
			addresses[i] = spotAddress;
			i++;
		}
		for (String spotAddress : addresses){
			stopProducer(spotAddress);
		}
//		m_producers.clear();
	}
	
}
