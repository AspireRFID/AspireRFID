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
package org.ow2.aspirerfid.reader.fictive;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.log.LogService;
import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.ow2.aspirerfid.reader.util.config.Configuration;
import org.ow2.aspirerfid.util.Logger;
import org.ow2.aspirerfid.util.RFIDConstants;
import org.ow2.aspirerfid.wires.RFIDTagRead;

/**
 * This class implements a fictive RFID reader. On each step, it choose a random
 * number of RFID Guid in a list of tags defined in a properties file.
 * Configurable parameters are: <br/>- period : the step period in milliseconds
 * <br/>- logicalName : the fictive reader name. <br/>- readerGuid : the fictive
 * reader identifier. The maximum of read tags, and the tag list in which the
 * fictive reader will randomly chose the read one are defined in a properties
 * configFile. The number of read tags is randomly chosen between 0 and the
 * maximum defined in the configuration file.
 * 
 * @author Anne Robert
 * @author Guillaume Surrel
 * @author Perisanidi Maroula
 * @version 2.0.0 05/2008
 */
public class RfidReaderSimulator implements Runnable, RfidReaderSimulatorMBean,
		Producer {

	/**
	 * Time between two polls in milliseconds.
	 */
	private int period = 3000;

	/**
	 * Logical name associated with the reader.
	 */
	private String logicalName = "fictive";

	/**
	 * Guid of the reader periodical task in cron service.
	 */
	private String readerGUId = "fictiveguid";

	/**
	 * Object to build the random sequences.
	 */
	private RfidSimulator reader;

	/**
	 * State of the reader.
	 */
	private boolean running = false;
	
	/**
	 * Default name of the config File where Maximum, and RFID tags list are
	 * read.
	 */
	private String configFile = "/STATE-INF/initialstate.properties";

	/**
	 * Set of wires between this producer and different consumers.
	 */
	private Wire[] wires;

	/**
	 * List of tags read from the properties file.
	 */
	private String[] tl;

	/**
	 * Logger used to record errors, warnings, information and debug messages.
	 */
	private Logger logger;

	/**
	 * BundleContext used for service registrations.
	 */
	private BundleContext context;

	/**
	 * @param bc
	 *            BundleContext used for service registrations.
	 */
	RfidReaderSimulator(BundleContext bc) {
		System.out.println("{RFIDREADERSIMULATOR}");
		this.context = bc;
		Thread reader = new Thread(this,"FictiveReader");
		reader.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public synchronized void run() {
		logger = new Logger("FictiveReader", Logger.INFO);
		// Register a producer
		Hashtable p = new Hashtable();
		p
				.put(
						org.osgi.service.wireadmin.WireConstants.WIREADMIN_PRODUCER_FLAVORS,
						new Class[] { RFIDTagRead.class });
		p.put(org.osgi.framework.Constants.SERVICE_PID,
				"org.ow2.aspirerfid.osgi.util.rfidtagproducer");
		p.put(org.osgi.framework.Constants.SERVICE_DESCRIPTION,
				"a simple Producer of RFID tags");

		context.registerService(Producer.class.getName(), this, p);
		while (bundleIsActive()) {
			try {
				for (int i = 0; wires != null && i < wires.length; i++) {
					Wire wire = wires[i];
					// check if wire is valid and connected
					if (!wire.isConnected() || !wire.isValid()
							|| running == false)
						continue;
					Object obj = polled(wire, getMeasurement());
					if (obj != null)
						wire.update(obj);
				}
				wait(period);
			} catch (InterruptedException ie) {
				/* will recheck quit */
			}
		}
	}

	private boolean bundleIsActive() {
		return context.getBundle().getState() == Bundle.ACTIVE;
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
		return polled(wire, getMeasurement());
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

	private RFIDTagRead getMeasurement() {
		int num = randomNumber(1, tl.length);
		RFIDTagRead tagProp = readThisTag(tl[num - 1]);
		return tagProp;
	}

	/**
	 * Returns a random integer value from the interval [min,max].
	 * 
	 * @param min
	 *            minimum value
	 * @param max
	 *            maximum value
	 * @return
	 */
	private int randomNumber(int min, int max) {
		return min + (int) (Math.random() * (max - min));
	}

	// __________________________________________________
	// MBean Interface : getter and setter on attributes
	// __________________________________________________

	// implementation of MBean interface
	// setter of max attribute
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.fictive.RfidReaderSimulatorMBean#setMaxTagRead(int)
	 */
	public void setMaxTagRead(int max) {
		reader.setMaxTagRead(max);

	}

	// implementation of MBean interface
	// getter of max attribute
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.fictive.RfidReaderSimulatorMBean#getMaxTagRead()
	 */
	public int getMaxTagRead() {
		return reader.getMaxTagRead();
	}

	// implementation of MBean interface
	// setter of tagList attribute
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.fictive.RfidReaderSimulatorMBean#setTagList(java.lang.String[])
	 */
	public void setTagList(String[] tagList) {
		reader.setTagList(tagList);
	}

	// implementation of RFID Reader MBean interface
	// getter of logicalName attribute
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.fictive.RfidReaderSimulatorMBean#getTagList()
	 */
	public String[] getTagList() {
		return reader.getTagList();
	}

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
	 * @see org.ow2.aspirerfid.reader.fictive.RfidReaderSimulatorMBean#setReaderGUId(java.lang.String)
	 */
	public void setReaderGUId(String readerGuid) {
		if (running) {
			// Stop the cron, because it is referenced by the Guid
			stopReader();
			this.readerGUId = readerGuid;
			restart();
		} else {
			this.readerGUId = readerGuid;
		}
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
		// if cron is activated, stop it and reactivate it
		if (running) {
			stopReader();
			restart();
		}
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
	 * initialize the reader. Attributes may be defined by the properties
	 * parameter.
	 * 
	 * @param properties
	 */
	public void init(Properties properties) {
		// initialize attributes
		logger.log(LogService.LOG_INFO, "create the rfid simulator");
		reader = new RfidSimulator(properties);
		logger.log(LogService.LOG_DEBUG, "Max tag read : "
				+ reader.getMaxTagRead());
		tl = reader.getTagList();
		logger.log(LogService.LOG_DEBUG, "Tags : ");
		for (int i = 0; i < tl.length; i++) {
			logger.log(LogService.LOG_DEBUG, "\t - " + tl[i]);
		}

		if (properties != null)
			try {
				// read initial value of attributes in properties
				logger.log(LogService.LOG_INFO, "read properties");
				String prop;
				prop = properties.getProperty("logicalName");
				if (prop != null)
					logicalName = prop;

				prop = properties.getProperty("period");
				if ((prop != null) && (prop.length() > 0)) {
					period = new Integer(prop).intValue();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * Start, or restart the reader
	 */
	private void restart() {
		logger.log(LogService.LOG_INFO, "fictive reader restart");
		running = true;
	}

	/**
	 * Start the fictive reader. It initializes parameters with the properties
	 * parameter, and starts the read task.
	 * 
	 * @param properties
	 */
	public void startReader(Properties properties) {
		// First read its parameters in properties
		init(properties);
		restart();
	}

	/**
	 * Start the fictive reader. It initializes parameters with the
	 * configuration file, and start the periodically read task
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#startReader()
	 */
	public void startReader() {

		Properties properties;
		// Load properties from configLocation file
		InputStream is = getClass().getResourceAsStream(configFile);
		logger.log(LogService.LOG_INFO, configFile);
		// If the file does not exist, no properties are defined
		if (is == null)
			properties = null;
		// analyze the config file to build properties
		else
			properties = Configuration.loadProperties(is);

		// First read its parameters in properties
		init(properties);
		// Then start the periodically read
		restart();
	}

	/**
	 * Stop the reader, and remove the periodical task from the cron service
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#stopReader()
	 */
	public void stopReader() {
		if ((running)) {
			running = false;
		}
		running = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#dispose()
	 */
	public void dispose() {
		System.err
				.println("The fictive reader has no driver, it can't be disposed.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.reader.fictive.RfidReaderSimulatorMBean#readTheseTags(java.lang.String[])
	 */
	public void readTheseTags(String[] tags) {
		System.out.println("Received " + tags.length + "tags");
		for (int i = 0; i < tags.length; i++) {
			readThisTag(tags[i]);
		}
	}

	/*
	 * In the props we add only the information related to the tag. The
	 * temperature and position values are given by separate producers.
	 * 
	 * @see org.ow2.aspirerfid.reader.fictive.RfidReaderSimulatorMBean#readThisTag(java.lang.String)
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
