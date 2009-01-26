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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventAdmin;
import org.ow2.aspirerfid.common.cron.CronService;
import org.ow2.aspirerfid.util.RFIDConstants;

/**
 * TODO Javadoc
 * @author Unknown
 * @version 2006
 */
public class Tiris6350Driver implements Tiris6350DriverMBean {

	private List readers = new ArrayList();

	private BundleContext context;

	private CronService cron;

	private EventAdmin eventAdmin;

	private String portName, logicalName, flowControlIn, flowControlOut, parity;

	private int period, baudRate, dataBits, stopBits;

	private String coordinates, eventTopic;

	private String readerGUId;

	private int readerNumber = 1;

	/**
	 * @param context TODO Javadoc
	 */
	public Tiris6350Driver(BundleContext context) {
		this.context = context;
		initConfig();
	}

	/**
	 * complete the reader configuration with default values for non specified
	 * parameters
	 */
	private void initConfig() {
		// port name
		portName = "COM1";

		// read period in millisecond
		period = 1000; // default 1000 millisec

		// baud rate to communicate with the reader
		baudRate = 57600; // default value

		// flow control from reader to computer
		flowControlIn = "None"; // no control by default

		// flow control from computer to reader
		flowControlOut = "None"; // no control by default

		// data size for communication between reader and computer
		dataBits = 8; // default 8 bits

		// number of stop bits for communication between reader and computer
		stopBits = 1; // default value

		// parity bit for communication between reader and computer
		parity = "None"; // default value
		
		coordinates = "47.16,2.99";

		eventTopic = RFIDConstants.TOPIC_DETECTION;
		logicalName = "tiris";
	}


	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.RfidDriverMBean#newReader()
	 */
	public void newReader() {
		try {
			newReaderProceed();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void newReaderProceed() {
		Tiris6350Reader reader = new Tiris6350Reader(context, readerNumber);
		readerNumber++;

		reader.setPortName(portName);
		reader.setBaudRate(baudRate);
		reader.setFlowControlIn(flowControlIn);
		reader.setFlowControlOut(flowControlOut);
		reader.setDataBits(dataBits);
		reader.setStopBits(stopBits);
		reader.setParity(parity);
		reader.setRepeatPeriod(new Integer(period).intValue());
		reader.setLogicalName(logicalName);
		reader.setGpsCoordinates(coordinates);
		reader.setRFIDEventTopic(eventTopic);
		reader.bindCronService(cron);
		reader.bindEventAdminService(eventAdmin);

		readers.add(reader);
		reader.startReader();
	}

	/**
	 * stop the driver and all the generated readers
	 */
	public void stop() {
		Iterator iter = readers.iterator();
		while (iter.hasNext()) {
			Tiris6350Reader reader = (Tiris6350Reader) iter.next();
			reader.dispose();
		}
	}

	/**
	 * @return Returns the baudRate.
	 */
	public int getBaudRate() {
		return baudRate;
	}

	/**
	 * @param baudRate The baudRate to set.
	 */
	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
	}

	/**
	 * @return Returns the coordinates.
	 */
	public String getGpsCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates The coordinates to set.
	 */
	public void setGpsCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * @return Returns the dataBits.
	 */
	public int getDataBits() {
		return dataBits;
	}

	/**
	 * @param dataBits The dataBits to set.
	 */
	public void setDataBits(int dataBits) {
		this.dataBits = dataBits;
	}

	/**
	 * @return Returns the eventTopic.
	 */
	public String getEventTopic() {
		return eventTopic;
	}

	/**
	 * @param eventTopic The eventTopic to set.
	 */
	public void setEventTopic(String eventTopic) {
		this.eventTopic = eventTopic;
	}

	/**
	 * @return Returns the flowControlIn.
	 */
	public String getFlowControlIn() {
		return flowControlIn;
	}

	/**
	 * @param flowControlIn The flowControlIn to set.
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
	 * @param flowControlOut The flowControlOut to set.
	 */
	public void setFlowControlOut(String flowControlOut) {
		this.flowControlOut = flowControlOut;
	}

	/**
	 * @return Returns the logicalName.
	 */
	public String getLogicalName() {
		return logicalName;
	}

	/**
	 * @param logicalName The logicalName to set.
	 */
	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}

	/**
	 * @return Returns the parity.
	 */
	public String getParity() {
		return parity;
	}

	/**
	 * @param parity The parity to set.
	 */
	public void setParity(String parity) {
		this.parity = parity;
	}

	/**
	 * @return Returns the period.
	 */
	public int getRepeatPeriod() {
		return period;
	}

	/**
	 * @param period The period to set.
	 */
	public void setRepeatPeriod(int period) {
		this.period = period;
	}

	/**
	 * @return Returns the portName.
	 */
	public String getPortName() {
		return portName;
	}

	/**
	 * @param portName The portName to set.
	 */
	public void setPortName(String portName) {
		this.portName = portName;
	}

	/**
	 * @return Returns the readerId.
	 */
	public String getReaderGUId() {
		return readerGUId;
	}

	/**
	 * @param readerGUId The readerId to set.
	 */
	public void setReaderGUId(String readerGUId) {
		this.readerGUId = readerGUId;
	}

	/**
	 * @return Returns the stopBits.
	 */
	public int getStopBits() {
		return stopBits;
	}

	/**
	 * @param stopBits The stopBits to set.
	 */
	public void setStopBits(int stopBits) {
		this.stopBits = stopBits;
	}

	/**
	 * @param ref TODO Javadoc
	 */
	public void removeCron(CronService ref) {
		Iterator iter = readers.iterator();
		while (iter.hasNext()) {
			Tiris6350Reader reader = (Tiris6350Reader) iter.next();
			reader.unbindCronService();
		}
		cron = null;
	}

	/**
	 * @param ref TODO Javadoc
	 */
	public void addCron(CronService ref) {
		Iterator iter = readers.iterator();
		while (iter.hasNext()) {
			Tiris6350Reader reader = (Tiris6350Reader) iter.next();
			reader.bindCronService(ref);
		}
		cron = ref;
	}

	/**
	 * TODO Javadoc
	 */
	public void removeEventAdmin() {
		Iterator iter = readers.iterator();
		while (iter.hasNext()) {
			Tiris6350Reader reader = (Tiris6350Reader) iter.next();
			reader.unbindEventAdminService();
		}
		eventAdmin = null;
	}

	/**
	 * @param ea TODO Javadoc
	 */
	public void addEventAdmin(EventAdmin ea) {
		Iterator iter = readers.iterator();
		while (iter.hasNext()) {
			Tiris6350Reader reader = (Tiris6350Reader) iter.next();
			reader.bindEventAdminService(ea);
		}
		eventAdmin = ea;
	}
}


