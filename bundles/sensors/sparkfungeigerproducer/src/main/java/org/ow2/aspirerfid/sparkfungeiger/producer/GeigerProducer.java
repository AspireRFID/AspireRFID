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
package org.ow2.aspirerfid.sparkfungeiger.producer;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.TooManyListenersException;

/*import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
*/
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.log.LogService;
import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.osgi.service.wireadmin.WireConstants;
import org.osgi.util.measurement.Measurement;
import org.osgi.util.measurement.Unit;


/**
 * provides a WireAdmin producer for a connected Geiger counter (http://www.sparkfun.com/products/9298)
 * @author Didier Donsez
 *
 */
public class GeigerProducer implements Producer, Runnable, SerialPortEventListener /*,MBeanRegistration*/ {

	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600; // default data rate for the Sparkfun geiger counter http://www.sparkfun.com/products/9298

	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;

	private String m_PID;

	private String m_portName;

	private long m_period;
	
	private Wire m_wires[];

	private LogService m_logService;

	private Measurement m_lastMeasurement;

	
	private Thread myThread;
	private boolean end;
	private int detectedParticleCounter=0;
	private int totalDetectedParticleCounter=0;
	
	
	// for JMX MBean
	private Map m_lastMeasurementMap;
	private double m_lastMeasurementValue = Double.NaN;
	private double m_lastMeasurementError = Double.NaN;
	private long m_lastMeasurementTime = 0;
	private String m_lastMeasurementUnit = null;
//	private CompositeData m_lastMeasurementComposite;

//	private CompositeType m_lastMeasurementCompositeType;

	private static final String[] m_itemsNames = { "time", "unit", "value", "error" };

	private static final String[] m_itemsDescr = { "T", "U", "V", "E" };

//	private static final OpenType[] m_itemsTypes = { SimpleType.LONG, SimpleType.STRING, SimpleType.DOUBLE, SimpleType.DOUBLE };

	private SerialPort serialPort = null;

	private OutputStream outputStream = null;

	private InputStream inputStream = null;

	// TODO resizable buffer
	byte[] readBuffer = new byte[256];

	private static final Class[] m_flavors = new Class[] { Measurement.class,
			Double.class, Float.class, String.class };

	private BundleContext m_bundleContext;

	/**
	 * registration of the Producer service
	 */
	private ServiceRegistration m_serviceRegistration;

	public GeigerProducer(BundleContext bc) /*throws OpenDataException*/ {
		m_bundleContext = bc;

/*		m_lastMeasurementCompositeType = new CompositeType(
				"SparkfunGeiger", "Measurements from SparkfunGeiger",
				m_itemsNames, m_itemsDescr, m_itemsTypes);
	*/}

	public void activate() {

		// open the port and register a listener
		open();

		// registration of the Consumer service
		Hashtable props = new Hashtable();
		props.put(Constants.SERVICE_PID, m_PID);
		props.put(WireConstants.WIREADMIN_PRODUCER_FLAVORS, m_flavors);
		m_serviceRegistration = m_bundleContext.registerService(
				new String[] { Producer.class.getName() }, this, props);
		
		myThread=new Thread(this);
		end=false;
		myThread.setName(m_PID);
		myThread.start();
	}

	public void deactivate() {
		end=true;
		myThread.interrupt();
		
		// unregistration of the producer service
		m_serviceRegistration.unregister();

		// close the serial port
		close();
	}

	public void consumersConnected(Wire[] wires) {
		synchronized (this) {
			m_wires = wires;
		}
	}

	public Object polled(Wire wire) {

		if (wire == null)
			return null;

		Class clazzes[] = wire.getFlavors();
		for (int i = 0; i < clazzes.length; i++) {
			Class clazz = clazzes[i];
			if (clazz.isAssignableFrom(Measurement.class)) {
				return m_lastMeasurement;
			}
			if (clazz.isAssignableFrom(Double.class)) {
				return new Double(m_lastMeasurement.getValue());
			}
			if (clazz.isAssignableFrom(Float.class)) {
				return new Float(m_lastMeasurement.getValue());
			}
			if (clazz.isAssignableFrom(String.class)) {
				return m_lastMeasurement.toString();
			}
		}
		return null;
	}

	private void log(int level, String message) {
		if (m_logService != null) {
			m_logService.log(level, message);
		} else print(level,message,null);
	}

	private void log(int level, String message, Throwable throwable) {
		if (m_logService != null) {
			m_logService.log(level, message, throwable);
		} else print(level,message,throwable);
	}

	private void print(int level, String message, Throwable throwable) {
		String levelstr="UNKNOWN LEVEL";
		switch(level) {
			case LogService.LOG_DEBUG: levelstr="DEBUG"; break;
			case LogService.LOG_WARNING: levelstr="WARNING"; break;
			case LogService.LOG_ERROR: levelstr="ERROR"; break;
			case LogService.LOG_INFO: levelstr="INFO"; break;
		}
		System.err.print(levelstr+": "+message);
		if(throwable!=null) throwable.printStackTrace(System.err);
		System.err.println();
	}

	private void open() {

		CommPortIdentifier portId = null;
		try {
			portId = CommPortIdentifier.getPortIdentifier(m_portName);
		} catch (NoSuchPortException e) {
			log(LogService.LOG_ERROR, "No Such Port:" + m_portName, e);
			return;
		}
		if (portId.getPortType() != CommPortIdentifier.PORT_SERIAL) {
			log(LogService.LOG_ERROR, m_portName + " is not a serial port");
			return;
		}
		try {
			serialPort = (SerialPort) portId.open("SparfunGeiger", TIME_OUT);
		} catch (PortInUseException e) {
			log(LogService.LOG_ERROR, m_portName + " already used", e);
			return;
		}

		try {
			outputStream = serialPort.getOutputStream();
		} catch (IOException e) {
			log(LogService.LOG_ERROR, e.getMessage(), e);
			return;
		}

		try {
			inputStream = serialPort.getInputStream();
		} catch (IOException e) {
			log(LogService.LOG_ERROR, e.getMessage(), e);
			return;
		}

		try {
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (TooManyListenersException e) {
			log(LogService.LOG_ERROR, e.getMessage(), e);
			return;
		}

		try {
			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
			log(LogService.LOG_ERROR, e.getMessage(), e);
			return;
		}

	}

	private void close() {
		if (serialPort == null) {
			log(LogService.LOG_ERROR, "No opened port");
			return;
		}
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				log(LogService.LOG_ERROR, e.getMessage(), e);
			}
			outputStream = null;
		}

		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				log(LogService.LOG_ERROR, e.getMessage(), e);
			}
			inputStream = null;
		}

		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
			serialPort = null;
		}
	}

	/**
	 * @see gnu.io.SerialPortEventListener#serialEvent(gnu.io.SerialPortEvent)
	 */
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:

			try {
				int numBytes = 0;
				int numBytesAvailables = 0;
				synchronized (this) {

					while ((numBytesAvailables = inputStream.available()) > 0) {
						numBytes = inputStream.read(readBuffer);

						synchronized (this) {
							detectedParticleCounter+=numBytes;
							totalDetectedParticleCounter+=detectedParticleCounter;
						}						

						/*
						 * // set the last value for the JMX attribute Map map =
						 * new HashMap(); map.put("value", new
						 * Double(m_lastMeasurement.getValue()));
						 * map.put("error", new
						 * Double(m_lastMeasurement.getError()));
						 * map.put("unit", UnitUtil.toString(m_lastMeasurement
						 * .getUnit())); map.put("time", new
						 * Long(m_lastMeasurement.getTime()));
						 * m_lastMeasurementMap = map; // injected by iPOJO
						 * 
						 * try { CompositeData tempData = new
						 * CompositeDataSupport( m_lastMeasurementCompositeType,
						 * map); m_lastMeasurementComposite = tempData; } catch
						 * (OpenDataException e) { // TODO Auto-generated catch
						 * block e.printStackTrace(); }
						 * 
						 * if (m_lastMeasurement.getValue() !=
						 * m_lastMeasurementValue) { m_lastMeasurementValue =
						 * m_lastMeasurement .getValue(); }
						 * 
						 * if (m_lastMeasurement.getError() !=
						 * m_lastMeasurementError) { m_lastMeasurementError =
						 * m_lastMeasurement .getError(); }
						 * 
						 * String curUnit; if (!(curUnit =
						 * UnitUtil.toString(m_lastMeasurement
						 * .getUnit())).equals(m_lastMeasurementUnit)) {
						 * m_lastMeasurementUnit = curUnit; }
						 * 
						 * if (m_lastMeasurement.getTime() !=
						 * m_lastMeasurementTime) { m_lastMeasurementTime =
						 * m_lastMeasurement .getTime(); }
						 */
					}
				}
			} catch (IOException e) {
				log(LogService.LOG_ERROR, e.getMessage(), e);
			}
			break;
		}
	}

	
	public void run() {
		while(!end){
			try {
				Thread.sleep(m_period);

				log(LogService.LOG_INFO,"particles=" + detectedParticleCounter + "(total="+totalDetectedParticleCounter+")");
				System.err.println("particles=" + detectedParticleCounter + "(total="+totalDetectedParticleCounter+")");
				
				synchronized (this) {
					// do not push measurement if no particle was detected during the period
					if(detectedParticleCounter==0) continue; 
					Measurement m = new Measurement((double) detectedParticleCounter, 0.0,Unit.unity, System.currentTimeMillis());
					detectedParticleCounter=0;
					// set the last value for the wires
					m_lastMeasurement = m;
				}
				
				synchronized (this) {
					for (int i = 0; m_wires != null
							&& i < m_wires.length; i++) {
						Wire wire = m_wires[i];
						if (wire.isConnected() && wire.isValid()) {
							wire.update(polled(wire));
						}
					}
				}
			} catch (InterruptedException e) {
				
			}			
		}
	}

	
	/*
	 * @see
	 * javax.management.MBeanRegistration#preRegister(javax.management.MBeanServer
	 * , javax.management.ObjectName)
	 */
/*	public ObjectName preRegister(MBeanServer server, ObjectName name)
			throws Exception {
		// Add the device port to the MBean name
		ObjectName newName = new ObjectName(name.getCanonicalName() + ",port="
				+ m_portName);
		return newName;
	}
*/
	/*
	 * @see javax.management.MBeanRegistration#postDeregister()
	 */
	public void postDeregister() {
	}

	/*
	 * @see javax.management.MBeanRegistration#postRegister(java.lang.Boolean)
	 */
	public void postRegister(Boolean arg0) {
	}

	/*
	 * @see javax.management.MBeanRegistration#preDeregister()
	 */
	public void preDeregister() throws Exception {
	}

}
