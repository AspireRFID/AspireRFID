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
package org.ow2.aspirerfid.reader.tiris6350;

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
import java.io.Serializable;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.TooManyListenersException;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.ow2.aspirerfid.common.cron.CronService;
import org.ow2.aspirerfid.common.cron.TimedObject;
import org.ow2.aspirerfid.util.Logger;
import org.ow2.aspirerfid.util.RFIDConstants;
import org.ow2.aspirerfid.reader.tiris6350.tagit.ReadIsoRequestFrame;
import org.ow2.aspirerfid.reader.tiris6350.tagit.ReadIsoResponseFrame;
import org.ow2.aspirerfid.reader.util.text.*;

import rxtx.sample.SerialParameters;

/**
 * TODO Javadoc
 * 
 * @author Didier Donsez
 * @version 2005
 */
public class Tiris6350Reader implements Tiris6350ReaderMBean, TimedObject,
		SerialPortEventListener {

	/**
	 * communication parameters of the reader
	 */
	private String portName, flowControlIn, flowControlOut, parity;

	private int period, baudRate, dataBits, stopBits;

	private String detectionTopic = RFIDConstants.TOPIC_DETECTION;

	/**
	 * the serial port, open to communicate with the reader
	 */
	private SerialPort serialPort = null;

	/**
	 * data flow for sending commands to the reader
	 */
	private OutputStream outputStream = null;

	/**
	 * data flow for receiving response from the reader
	 */
	private InputStream inputStream = null;

	/**
	 * cron service to periodically send a read command. Automatically binded by
	 * iPOJO
	 */
	private CronService cron;

	/**
	 * request frame to interrogate the reader (read an ISO 15693 tag)
	 */
	private ReadIsoRequestFrame readIsoRequestFrame = new ReadIsoRequestFrame();

	/**
	 * save the last event to not duplicate it on successive read.
	 */
	Event2 lastEvent;

	private EventAdmin ea;

	/**
	 * reader identifier. Set by configuration. It is possible to read it with
	 * reader command?
	 */
	private String readerGUId;

	/**
	 * reader logical name.
	 */
	private String logicalName;

	/**
	 * reader coordinates. For this reader, the geographical coordinates are fix
	 * by configuration
	 */
	private String coordinates;

	/**
	 * true between call of startReader and stopReader functions.
	 */
	private boolean actif = false;

	// TODO resizable buffer
	private static final int BUFFER_SIZE = 256;

	// buffer for read response from reader
	private byte[] readBuffer = new byte[BUFFER_SIZE];

	// current position in this buffer
	private int currentPos = 0;

	private Logger logger = new Logger("TIRIS READER ", Logger.DEBUG);

	private ServiceRegistration serviceRegistration;

	/**
	 * TODO Javadoc
	 * 
	 * @param context
	 * @param readerNumber
	 */
	public Tiris6350Reader(BundleContext context, int readerNumber) {
		readerGUId = "Tiris " + readerNumber;

		Dictionary props = new Hashtable();
		props.put("jmxagent.objectname",
				"rfid:type=reader,SymbolicName=Tiris6350Reader" + readerNumber);

		serviceRegistration = context.registerService(
				"org.ow2.aspirerfid.reader.tiris6350.Tiris6350ReaderMBean",
				this, props);
	}

	/**
	 * open the serial port
	 */
	private void openPort() {
		// Create a serial parameters object with communication parameters
		SerialParameters serialParameters = new SerialParameters();
		// port name
		serialParameters.setPortName(portName);
		// communication speed
		serialParameters.setBaudRate(baudRate);
		// input flow control
		serialParameters.setFlowControlIn(flowControlIn);
		// output flow control
		serialParameters.setFlowControlOut(flowControlOut);
		// number of bits by data word
		serialParameters.setDatabits(dataBits);
		// number of stop bit
		serialParameters.setStopbits(stopBits);
		// parity bit
		serialParameters.setParity(parity);

		// initialize the port identifier
		CommPortIdentifier portId = null;
		try {
			// try to find a port with the precedent parameters
			portId = CommPortIdentifier.getPortIdentifier(serialParameters
					.getPortName());
		} catch (NoSuchPortException e1) {
			// this port does not exist
			System.err
					.println("No Such Port:" + serialParameters.getPortName());
			return;
		}
		// Check if it is a serial port
		if (portId.getPortType() != CommPortIdentifier.PORT_SERIAL) {
			System.err.println(serialParameters.getPortName()
					+ " is not a serial port");
			return;
		}
		// open the found port
		try {
			// use the port name to differentiate the id if
			// there is more than one reader
			serialPort = (SerialPort) portId.open("tagitproducer"
					+ serialParameters.getPortName(), 2000);
		} catch (PortInUseException e) {
			// the port is already open
			System.err
					.println(serialParameters.getPortName() + " already used");
			return;
		}

		try {
			// open the output flow to send commands
			outputStream = serialPort.getOutputStream();
		} catch (IOException e) {
			System.err.println(e);
			return;
		}

		try {
			// open the input flow to receive response
			inputStream = serialPort.getInputStream();
		} catch (IOException e) {
			System.err.println(e);
			return;
		}

		try {
			// add this object as listener on event of this port (callback :
			// serialEvent)
			serialPort.addEventListener(this);
			// enable notification
			serialPort.notifyOnDataAvailable(true);
		} catch (TooManyListenersException e) {
			System.err.println(e);
			return;
		}

		try {
			// set the communication parameters on this port
			serialPort.setSerialPortParams(serialParameters.getBaudRate(),
					serialParameters.getDatabits(), serialParameters
							.getStopbits(), serialParameters.getParity());
		} catch (UnsupportedCommOperationException e) {
			// the parameters are not supported
			System.err.println(e);
			return;
		}
	}

	/**
	 * close the serial port
	 */
	private void closePort() {

		// check it was really opened
		if (serialPort == null) {
			System.err.println("No opened port");
			return;
		}

		// in output stream is open, close it
		if (outputStream != null) {
			try {
				// open, close it
				outputStream.close();
			} catch (IOException e) {
				System.err.println(e);
				return;
			}
			// and reset it to be sure to not reuse it
			outputStream = null;
		}

		// if input stream is open, close it
		if (inputStream != null) {
			try {
				// open, close it
				inputStream.close();
			} catch (IOException e) {
				System.err.println(e);
				return;
			}
			// and reset it to be sure to not reuse it
			inputStream = null;
		}

		if (serialPort != null) {
			// unregister the listener
			serialPort.removeEventListener();
			// and close the port
			serialPort.close();
			// reset it to null to be sure to have no more
			// access on the close port
			serialPort = null;
		}
	}

	/**
	 * send a read ISO 15693 command
	 */
	private void sendCommand() {
		try {
			// send the command on the output stream open on the port
			outputStream.write(readIsoRequestFrame.getBytes());
			// trace the sent command
			logger.log(Logger.DEBUG, "send :"
					+ HexString.hexify(readIsoRequestFrame.getBytes(), 0, 0,
							16, " "));
			// be sure the command is immediately sent
			outputStream.flush();
		} catch (IOException e) {
			// error will writing on the port
			System.err.println(e);
			return;
		}

	}

	private int intervalBetweenTwoDetectionsWithSameID;

	private String cronName = "tiris";

	/**
	 * compute the command response to publish an event
	 * 
	 * @param reader
	 * @param address
	 */
	public void process(String reader, String address) {
		// create the sensor event
		Event2 event = new Event2("uuid:" + reader, // "uuid:tagit:" +
				address, Event2.DETECTION, System.currentTimeMillis());
		// traceln(this.getClass().getName()+":"+event.toString());
		// check if the new event is not redundant with the last send event
		if (lastEvent != null
				&& event.getReaderGUId().equals(lastEvent.getReaderGUId())
				&& event.getTagGUId().equals(lastEvent.getTagGUId())
				&& event.getTimestamp() < (lastEvent.getTimestamp() + intervalBetweenTwoDetectionsWithSameID)) {
			return;
		}

		// store the new event as the last one
		lastEvent = event;

		// create a dictionary for the event properties
		Hashtable props = new Hashtable();
		// props.put(EventConstants.BUNDLE_SYMBOLICNAME,
		// context.getBundle().getHeaders());
		// add the event information in this dictionary
		props.put(RFIDConstants.READERGUID_KEY, event.getReaderGUId());
		if (logicalName != null)
			props.put(RFIDConstants.READERNAME_KEY, logicalName);
		if (coordinates != null)
			props.put(RFIDConstants.COORDINATES_KEY, coordinates);
		props.put(RFIDConstants.TAGGUID_KEY, event.getTagGUId());
		props.put(EventConstants.TIMESTAMP, new Long(event.getTimestamp())
				.toString());

		// find the publish topic depending on the sensor event type
		String topic;
		switch (event.getType()) {
		case Event2.DETECTION:
			// new tag detection
			topic = detectionTopic;
			break;
		default:
			topic = null; // should throws a exception
			break;
		}

		// build the event
		Event e = new Event(topic, props);
		// use the publish object is not null (replace eventadmin service)
		if (ea != null)
			ea.postEvent(e);
	}

	/**
	 * event handler to read the "Read TRP Detail" response command
	 * 
	 * @param event
	 * @see javax.comm.SerialPortEventListener#serialEvent(javax.comm.SerialPortEvent)
	 */
	public void serialEvent(SerialPortEvent event) {

		ReadIsoResponseFrame readIsoResponseFrame;

		String tagId;

		logger.log(Logger.DEBUG, "SerialEvent " + event.getEventType());

		switch (event.getEventType()) {
		// all this serial events are ignored
		case SerialPortEvent.BI: // break interrupt
		case SerialPortEvent.OE: // overrun error
		case SerialPortEvent.FE: // Framing error
		case SerialPortEvent.PE: // Parity error
		case SerialPortEvent.CD: // carrier detect
		case SerialPortEvent.CTS: // clear to send
		case SerialPortEvent.DSR: // Data set ready
		case SerialPortEvent.RI: // Ring indicator
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;

		case SerialPortEvent.DATA_AVAILABLE:
			// the event signal that data has been received on the port

			try {
				// Initialize the number of received bytes
				int numBytes = 0;
				synchronized (this) {
					logger.log(Logger.DEBUG, "received:");
					// while there is more available bytes
					while ((numBytes = inputStream.available()) > 0) {
						// add the available bytes
						numBytes = inputStream.read(readBuffer, currentPos,
								numBytes);
						// switch the current position
						currentPos = currentPos + numBytes;
						// trace the received bytes
						logger.log(Logger.DEBUG, HexString.hexify(readBuffer,
								0, currentPos, 16, " "));
						int len;
						// the response must contain at least the length
						if (currentPos > 2
								&& currentPos >= (len = readBuffer[1])) {
							// copy the response defined with the length in the
							// read buffer
							byte[] b = new byte[len];
							System.arraycopy(readBuffer, 0, b, 0, len);
							currentPos = 0;
							// build the response frame with the read data
							readIsoResponseFrame = new ReadIsoResponseFrame(b);

							// check if the response is not an error response
							if (readIsoResponseFrame.noErrorReturned()) {
								// extract the tag id from the response
								// FIXME
								tagId = "35111111111111"
										+ HexString.hexify(readIsoResponseFrame
												.getTagId(), 0, 5, 40, "");
								// and transmit it by event
								process(readerGUId, tagId);
							}
						}
					}
					logger.log(Logger.DEBUG, "end of receive");
				}
			} catch (IOException e) {
				// IO error
				System.err.print(readerGUId + ":" + e);
			}
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#startReader()
	 */
	public void startReader() {
		logger.log(Logger.INFO, "Start reader");
		if (!actif) {
			actif = true;
			try {
				// open the port of the reader
				openPort();
				// if the port is successfully opened (input and output stream
				// open in last)
				if ((outputStream != null) && (inputStream != null)) {
					bindCronService(cron);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#stopReader()
	 */
	public void stopReader() {
		logger.log(Logger.INFO, "stop reader");
		if (actif) {
			// inactive next command
			actif = false;
			// remove the periodical task
			if (cron != null) {
				cron.remove(this, cronName);
			} else {
				System.err.println("cron null");
			}
			// close the port
			closePort();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.common.cron.TimedObject#doReact(java.io.Serializable)
	 */
	public void doReact(Serializable arg0) {
		// read periodically the tags
		if (actif) {
			sendCommand();
		}
	}

	/**
	 * bind the cron service. Used only in non iPOJO version
	 * 
	 * @param cron2
	 *            the reference to cron service
	 */
	public void bindCronService(CronService cron2) {
		cron = cron2;
		if (actif) {
			// and add the periodical task
			cron.add(this, cronName, cron.cronString(period));
		}
	}

	/**
	 * unbind the cron service: dereference the service. Used only in non iPOJO
	 * version
	 */
	public void unbindCronService() {
		cron.remove(this, cronName);
		cron = null;
	}

	/**
	 * bind the eventadmin service. Used only in non iPOJO version
	 * 
	 * @param ea
	 *            the reference to event admin service
	 */
	public void bindEventAdminService(EventAdmin ea) {
		this.ea = ea;
	}

	/**
	 * unbind the event admin service: dereference the service. Used only in non
	 * iPOJO version
	 */
	public void unbindEventAdminService() {
		this.ea = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#setLogicalName(java.lang.String)
	 */
	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#getLogicalName()
	 */
	public String getLogicalName() {
		return logicalName;
	}

	/**
	 * @param coordinates
	 *            TODO Javadoc
	 */
	public void setGpsCoordinates(String coordinates) {
		this.coordinates = coordinates;

	}

	/**
	 * @return TODO Javadoc
	 */
	public String getGpsCoordinates() {
		return coordinates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#setRepeatPeriod(int)
	 */
	public void setRepeatPeriod(int period) {
		this.period = period;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#getRepeatPeriod()
	 */
	public int getRepeatPeriod() {
		return period;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#getReaderGUId()
	 */
	public String getReaderGUId() {
		return readerGUId;
	}

	/**
	 * @param topic
	 *            TODO Javadoc
	 */
	public void setRFIDEventTopic(String topic) {
		if (topic != null)
			detectionTopic = topic;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getRFIDEventTopic() {
		return detectionTopic;
	}

	/**
	 * @return Returns the baudRate.
	 */
	public int getBaudRate() {
		return baudRate;
	}

	/**
	 * @param baudRate
	 *            The baudRate to set.
	 */
	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
	}

	/**
	 * @return Returns the dataBits.
	 */
	public int getDataBits() {
		return dataBits;
	}

	/**
	 * @param dataBits
	 *            The dataBits to set.
	 */
	public void setDataBits(int dataBits) {
		this.dataBits = dataBits;
	}

	/**
	 * @return Returns the flowControlIn.
	 */
	public String getFlowControlIn() {
		return flowControlIn;
	}

	/**
	 * @param flowControlIn
	 *            The flowControlIn to set.
	 */
	public void setFlowControlIn(String flowControlIn) {
		this.flowControlIn = flowControlIn;
	}

	/**
	 * @return Returns the flowControlOut.
	 */
	public String getFlowControlOut() {
		return flowControlOut;
	}

	/**
	 * @param flowControlOut
	 *            The flowControlOut to set.
	 */
	public void setFlowControlOut(String flowControlOut) {
		this.flowControlOut = flowControlOut;
	}

	/**
	 * @return Returns the parity.
	 */
	public String getParity() {
		return parity;
	}

	/**
	 * @param parity
	 *            The parity to set.
	 */
	public void setParity(String parity) {
		this.parity = parity;
	}

	/**
	 * @return Returns the portName.
	 */
	public String getPortName() {
		return portName;
	}

	/**
	 * @param portName
	 *            The portName to set.
	 */
	public void setPortName(String portName) {
		this.portName = portName;
	}

	/**
	 * @return Returns the stopBits.
	 */
	public int getStopBits() {
		return stopBits;
	}

	/**
	 * @param stopBits
	 *            The stopBits to set.
	 */
	public void setStopBits(int stopBits) {
		this.stopBits = stopBits;
	}

	public void dispose() {
		stopReader();
		serviceRegistration.unregister();
	}
}
