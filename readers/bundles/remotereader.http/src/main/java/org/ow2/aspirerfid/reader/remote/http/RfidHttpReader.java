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
package org.ow2.aspirerfid.reader.remote.http;

import java.util.Hashtable;
import java.util.Properties;

import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.log.LogService;
import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.ow2.aspirerfid.util.Logger;
import org.ow2.aspirerfid.util.RFIDConstants;
import org.ow2.aspirerfid.wires.RFIDTagRead;

/**
 * Propagates tag reading events
 */
public class RfidHttpReader implements  RfidHttpReaderMBean,
		Producer {
	/**
	 * Time between two polls in milliseconds.
	 */
	private int period = 3000;

	/**
	 * Logical name associated with the reader.
	 */
	private String logicalName = "http";

	/**
	 * Guid of the reader periodical task in cron service.
	 */
	private String readerGUId = "httpguid";


	/**
	 * Set of wires between this producer and different consumers.
	 */
	private Wire[] wires;

	/**
	 * Logger used to record errors, warnings, information and debug messages.
	 */
	private Logger logger;

	/**
	 * BundleContext used for service registrations.
	 */
	private BundleContext context;
	
	private String gpsCoordinates;
	
	private static RfidHttpReader instance;
	
	private RFIDTagRead lastValue;

	/**
	 * @param bc
	 *            BundleContext used for service registrations.
	 */
	RfidHttpReader(BundleContext bc) {
		//TODO Do adequate OSGi code
		this.context = bc;
		logger = new Logger("HTTPReader", Logger.INFO);
		// Register a producer
		Hashtable<String,Object> p = new Hashtable<String,Object>();
		p.put(org.osgi.service.wireadmin.WireConstants.WIREADMIN_PRODUCER_FLAVORS,
			new Class[] { RFIDTagRead.class });
		p.put(org.osgi.framework.Constants.SERVICE_PID,
				"org.ow2.aspirerfid.osgi.util.rfidtagproducer");
		p.put(org.osgi.framework.Constants.SERVICE_DESCRIPTION,
				"an HTTP interface that forwards RFID tag readings");

		context.registerService(Producer.class.getName(), this, p);
		System.out.println("Starting RFID HTTP reader");
		instance = this;
	}
	
	static RfidHttpReader getInstance() {
		return instance;
	}
	
	public String getGpsCoordinates() {
		return gpsCoordinates;
	}
	
	public void setGpsCoordinates(String coordinates) {
		this.gpsCoordinates = coordinates;
	}
	
	void sendEvent(RFIDTagRead tagRead) {
		tagRead.put(RFIDConstants.READERNAME_KEY, this.getLogicalName());
		if (this.gpsCoordinates != null) {
			tagRead.put(RFIDConstants.COORDINATES_KEY,this.gpsCoordinates);
		}
		lastValue = tagRead;
		
		for (int i = 0; wires != null && i < wires.length; i++) {
			Wire wire = wires[i];
			// check if wire is valid and connected
			if (!wire.isConnected() || !wire.isValid())
				continue;
			Object obj = polled(wire, tagRead);
			if (obj != null)
				wire.update(obj);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.wireadmin.Producer#consumersConnected(org.osgi.service.wireadmin.Wire[])
	 */
	public synchronized void consumersConnected(Wire[] wires) {
		this.wires = wires;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.wireadmin.Producer#polled(org.osgi.service.wireadmin.Wire)
	 */
	public Object polled(Wire wire) {
		return polled(wire, lastValue);
	}

	/**
	 * Analyzes the wire's flavors and returns the appropriate object.
	 * 
	 * @param wire
	 *            A connected and valid wire.
	 * @param prop
	 *            The object to be sent through the wire.
	 * @return null if none of its ancestors or implemented interfaces is the
	 *         same as one the wire's flavors
	 */
	private Object polled(Wire wire, RFIDTagRead prop) {
		Class[] clazzes = wire.getFlavors();
		for (int i = 0; i < clazzes.length; i++) {
			Class clazz = clazzes[i];
			if (clazz.isAssignableFrom(RFIDTagRead.class))
				return prop;
		}
		return null;
	}


	// __________________________________________________
	// MBean Interface : getter and setter on attributes
	// __________________________________________________

		/**
	 * implementation of RFID Reader MBean interface setter of logicalName
	 * attribute
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#setLogicalName(java.lang.String)
	 */
	public void setLogicalName(String logName) {
		this.logicalName = logName;
	}

	/**
	 * implementation of RFID Reader MBean interface getter of logicalName
	 * attribute
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#getLogicalName()
	 */
	public String getLogicalName() {
		return logicalName;
	}

	/**
	 * implementation of RFID Reader MBean interface setter of logicalName
	 * attribute
	 * 
	 * @see org.ow2.aspirerfid.reader.remote.http.RfidHttpReaderMBean#setReaderGUId(java.lang.String)
	 */
	public void setReaderGUId(String readerGuid) {
		this.readerGUId = readerGuid;
	}

	/*
	 * implementation of RFID Reader MBean interface getter of logicalName
	 * attribute
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#getReaderGUId()
	 */
	public String getReaderGUId() {
		return readerGUId;
	}

	/**
	 * implementation of RFID Reader MBean interface setter of period attribute
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#setRepeatPeriod(int)
	 */
	public void setRepeatPeriod(int period) {
		this.period = period;
		stopReader();
		restart();
	}

	/**
	 * implementation of RFID Reader MBean interface getter of period attribute
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#getRepeatPeriod()
	 */
	public int getRepeatPeriod() {
		return period;
	}


	/**
	 * Start, or restart the reader
	 */
	private void restart() {
		logger.log(LogService.LOG_INFO, "HTTP reader restart");
	}

	/**
	 * Start the HTTP reader. It initializes parameters with the properties
	 * parameter, and starts the read task.
	 * 
	 * @param properties
	 */
	public void startReader(Properties properties) {
		// First read its parameters in properties
		restart();
	}

	/**
	 * Start the HTTP reader. It initializes parameters with the
	 * configuration file, and start the periodically read task
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#startReader()
	 */
	public void startReader() {
		restart();
	}

	/**
	 * Stop the reader, and remove the periodical task from the cron service
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#stopReader()
	 */
	public void stopReader() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#dispose()
	 */
	public void dispose() {
		System.err
				.println("The HTTP reader has no driver, it can't be disposed.");
	}

	/*
	 * In the props we add only the information related to the tag. The
	 * temperature and position values are given by separate producers.
	 * 
	 */
	public RFIDTagRead readThisTag(String tag) {
		RFIDTagRead tagProp = new RFIDTagRead();
		String elmts = getReaderGUId();
		if (elmts != null)
			tagProp.put(RFIDConstants.READERGUID_KEY, elmts);
		elmts = getLogicalName();
		if (elmts != null)
			tagProp.put(RFIDConstants.READERNAME_KEY, elmts);
		elmts = Long.toString(System.currentTimeMillis());
		if (elmts != null)
			tagProp.put(EventConstants.TIMESTAMP, elmts);
		tagProp.put(RFIDConstants.TAGGUID_KEY, tag);

		return tagProp;
	}
}
