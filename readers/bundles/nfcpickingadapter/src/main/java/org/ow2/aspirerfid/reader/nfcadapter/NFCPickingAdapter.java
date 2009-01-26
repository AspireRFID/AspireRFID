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
package org.ow2.aspirerfid.reader.nfcadapter;

import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.LogService;
import org.osgi.service.wireadmin.Producer;
import org.osgi.service.wireadmin.Wire;
import org.ow2.aspirerfid.nfc.picking.server.PickingEventAdapter;
import org.ow2.aspirerfid.util.Logger;
import org.ow2.aspirerfid.util.RFIDConstants;
import org.ow2.aspirerfid.wires.GpsRead;
import org.ow2.aspirerfid.wires.RFIDTagRead;

/**
 * Adapter
 */
// TODO Create AbstractReaderMBean implementing basic stuff
public class NFCPickingAdapter implements NFCPickingAdapterMBean, Producer {
	private int repeatPeriod;
	/**
	 * Logical name associated with the reader.
	 */
	private String logicalName = "nfcadapter";

	/**
	 * Guid of the reader periodical task in cron service.
	 */
	private String readerGUId = "nfcadapterguid";

	/**
	 * State of the reader.
	 */
	private boolean running = false;

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
	
	private RFIDTagRead lastObject;
	private String gpsCoordinates;

	/**
	 * @param bc
	 *            BundleContext used for service registrations.
	 */
	NFCPickingAdapter(BundleContext bc) {
		this.context = bc;
		logger = new Logger("NFCPickingAdapter", Logger.INFO);
		registerEventHandlers();
	}

	
	//starting in a separate thread as a workaround to problems in component startup
	private void registerEventHandlers() {
		new Thread("NFCRegister") {
			public void run() {
				regWire();
				logger.log(Logger.INFO ,"Registered Wires");
				regPickingConsumer();
				logger.log(Logger.INFO, "Registered EventAdmin consumer");
			}
		}.start();
	}

	private void regPickingConsumer() {
		//event admin consumer
		String[] topics = new String[] { PickingEventAdapter.TOPIC};
		Hashtable ht = new Hashtable();
		ht.put(EventConstants.EVENT_TOPIC, topics);
		context.registerService(EventHandler.class.getName(), new NFCReadingForwarder(), ht);
	}
	
	private void regWire() {
		// Register a producer
		Hashtable p = new Hashtable();
		p.put(org.osgi.service.wireadmin.WireConstants.WIREADMIN_PRODUCER_FLAVORS,
			  new Class[] { RFIDTagRead.class });
		p.put(org.osgi.framework.Constants.SERVICE_PID,
				"org.ow2.aspirerfid.osgi.util.nfcadapter");
		p.put(org.osgi.framework.Constants.SERVICE_DESCRIPTION,
				"Adaptation of an NFC picking server into Aspire");
		context.registerService(Producer.class.getName(), this, p);
	}

	private void sendEvents(RFIDTagRead[] tags) {
		//stores the last object to be used in case of calls to poll
		lastObject = tags[tags.length - 1];
		
		for (int i = 0; wires != null && i < wires.length; i++) {
			Wire wire = wires[i];
			// check if wire is valid and connected
			if (!wire.isConnected() || !wire.isValid() || running == false)
				continue;
			for (int j = 0; j < tags.length; j++) {
				Object obj = polled(wire, tags[j]);
				if (obj != null) {
					wire.update(obj);
				}
			}
		}
	}

	/**
	 * @see org.osgi.service.wireadmin.Producer#consumersConnected(org.osgi.service.wireadmin.Wire[])
	 */
	public synchronized void consumersConnected(Wire[] wires) {
		this.wires = wires;
	}

	/**
	 * @see org.osgi.service.wireadmin.Producer#polled(org.osgi.service.wireadmin.Wire)
	 */
	public Object polled(Wire wire) {
		return polled(wire, lastObject);
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

	// implementation of MBean interface

	// implementation of RFID Reader MBean interface
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
	
	public String getGpsCoordinates() {
		return gpsCoordinates;
	}
	
	public void setGpsCoordinates(String coordinates) {
		this.gpsCoordinates = coordinates;
	}

	/**
	 * implementation of RFID Reader MBean interface setter of logicalName
	 * attribute
	 * 
	 * @see org.ow2.aspirerfid.reader.nfcadapter.NFCPickingAdapterMBean#setReaderGUId(java.lang.String)
	 */
	public void setReaderGUId(String readerGuid) {
		if (running) {
			stopReader();
			this.readerGUId = readerGuid;
			restart();
		} else {
			this.readerGUId = readerGuid;
		}
	}

	/**
	 * implementation of RFID Reader MBean interface getter of logicalName
	 * attribute
	 * 
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#getReaderGUId()
	 */
	public String getReaderGUId() {
		return readerGUId;
	}

	/**
	 * Start, or restart the reader
	 */
	private void restart() {
		logger.log(LogService.LOG_INFO, "NFC Adapter restart");
		running = true;
	}

	/**
	 * Start the reader.
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
		if (running) {
			running = false;
		}
	}

	/**
	 * @see org.ow2.aspirerfid.reader.RfidReaderMBean#dispose()
	 */
	public void dispose() {
		System.err
				.println("The fictive reader has no driver, it can't be disposed.");
	}

	public int getRepeatPeriod() {
		return repeatPeriod;
	}

	public void setRepeatPeriod(int period) {
		repeatPeriod = period;
	}

	// handles events produced by the NFC picking server wraps them
	// in an format acceptable by the RFID suite
	private class NFCReadingForwarder implements EventHandler {
		private static final int TAG_LENGTH = 24;
		private static final String GID_TAG_PREFIX = "35";
		
		public void handleEvent(Event event) {
			System.out.println("receiving event!");
			PickingEventAdapter adapter = new PickingEventAdapter(event);
			List readTags = adapter.getTags();
			RFIDTagRead[] translatedTags = translateTags(readTags);
			//forwards the event to the RFID suite
			System.out.println("sending to suite");
			sendEvents(translatedTags);
		}

		private RFIDTagRead[] translateTags(List readTags) {
			RFIDTagRead[] translatedTags = new RFIDTagRead[readTags.size()];
			for (int i = 0; i < readTags.size(); i++) {
				RFIDTagRead tagProp = new RFIDTagRead();
				String tagID = adaptIDToEPC((String)readTags.get(i));
				String data = getReaderGUId();
				if (data != null)
					tagProp.put(RFIDConstants.READERGUID_KEY, data);
				data = getLogicalName();
				if (data != null)
					tagProp.put(RFIDConstants.READERNAME_KEY, data);
				if (gpsCoordinates != null)
					tagProp.put(RFIDConstants.COORDINATES_KEY, gpsCoordinates);
				
				tagProp.put(EventConstants.TIMESTAMP, Long.toString(System.currentTimeMillis()));
				tagProp.put(RFIDConstants.TAGGUID_KEY, tagID);
				translatedTags[i] = tagProp;
			}
			return translatedTags;
		}
		//Adapting the read NFC tag to a format compliant with aspire's tag factory
		private String adaptIDToEPC(String tagID) {
			StringBuffer sb = new StringBuffer(tagID);
			sb.insert(0,GID_TAG_PREFIX);
			
			while (sb.length() < TAG_LENGTH) {
				sb.insert(2, "0");
			}
			return sb.toString();
		}
	}
}