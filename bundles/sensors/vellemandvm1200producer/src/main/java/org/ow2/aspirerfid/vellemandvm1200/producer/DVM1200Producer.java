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
package org.ow2.aspirerfid.vellemandvm1200.producer;

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
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.TooManyListenersException;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.log.LogService;
import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.osgi.service.wireadmin.WireConstants;
import org.osgi.util.measurement.Measurement;
import org.ow2.aspirerfid.vellemandvm1200.data.DVM1200MultimeterAcquisition;

import rxtx.sample.SerialParameters;

public class DVM1200Producer implements Producer, SerialPortEventListener {

	private String m_PID;

	private String m_portName;

	private Wire m_wires[];

	private LogService m_logService;
	
	private Measurement m_lastMeasurement;

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

	private boolean m_end;

	private Thread m_thread;

	public DVM1200Producer(BundleContext bc) {
		m_bundleContext = bc;
	}

	public void activate() {

		// open the port and register a listener
		open();
		
		// registration of the Consumer service
		Hashtable props = new Hashtable();
		props.put(Constants.SERVICE_PID, m_PID);
		props.put(WireConstants.WIREADMIN_PRODUCER_FLAVORS, m_flavors);
		m_serviceRegistration = m_bundleContext.registerService(
				new String[] { Producer.class.getName() }, this, props);
	}

	public void deactivate() {
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

	private void open() {
		SerialParameters serialParameters = new SerialParameters();
		serialParameters.setPortName(m_portName);
		serialParameters.setBaudRate(2400);
		serialParameters.setFlowControlIn("None");
		serialParameters.setFlowControlOut("None");
		serialParameters.setDatabits(8);
		serialParameters.setStopbits(1);
		serialParameters.setParity("None");

		CommPortIdentifier portId = null;
		try {
			portId = CommPortIdentifier.getPortIdentifier(serialParameters
					.getPortName());
		} catch (NoSuchPortException e1) {
			System.err
					.println("No Such Port:" + serialParameters.getPortName());
			return;
		}
		if (portId.getPortType() != CommPortIdentifier.PORT_SERIAL) {
			System.err.println(serialParameters.getPortName()
					+ " is not a serial port");
			return;
		}
		try {
			serialPort = (SerialPort) portId.open("DVM1200", 2000);
		} catch (PortInUseException e) {
			System.err
					.println(serialParameters.getPortName() + " already used");
			return;
		}

		try {
			outputStream = serialPort.getOutputStream();
		} catch (IOException e) {
			System.err.println(e);
			return;
		}

		try {
			inputStream = serialPort.getInputStream();
		} catch (IOException e) {
			System.err.println(e);
			return;
		}

		try {
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (TooManyListenersException e) {
			System.err.println(e);
			return;
		}

		try {
			serialPort.setSerialPortParams(serialParameters.getBaudRate(),
					serialParameters.getDatabits(), serialParameters
							.getStopbits(), serialParameters.getParity());
		} catch (UnsupportedCommOperationException e) {
			System.err.println(e);
			return;
		}

	}

	private void close() {
		if (serialPort == null) {
			System.err.println("No opened port");
			return;
		}
		close(null, System.out, System.err);

	}

	private void close(String commandLine, PrintStream out,PrintStream err) {
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				System.err.println(e);
				return;
			}
			outputStream = null;
		}

		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.err.println(e);
				return;
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

						try {
							DVM1200MultimeterAcquisition acquisition = new DVM1200MultimeterAcquisition(readBuffer);
							String unitStr=acquisition.getUnit();
							Measurement m = new Measurement(UnitUtil.convertValue(acquisition.getValue(),unitStr), UnitUtil.convertError(acquisition.getError(),unitStr) , UnitUtil.getUnit(unitStr),System.currentTimeMillis());
							m_lastMeasurement = m;

							// System.out.println(acquisition.toString()+"-->"+m.toString());
							
							synchronized (this) {
								for (int i = 0; m_wires != null
										&& i < m_wires.length; i++) {
									Wire wire = m_wires[i];
									if (wire.isConnected() && wire.isValid()) {
										wire.update(polled(wire));
									}
								}
							}

						} catch (ParseException e) {
							System.err.println(e);
							return;
						}
					}
				}
			} catch (IOException e) {
				System.err.print("DVM1200" + ":" + e);
			}
			break;
		}
	}

}
