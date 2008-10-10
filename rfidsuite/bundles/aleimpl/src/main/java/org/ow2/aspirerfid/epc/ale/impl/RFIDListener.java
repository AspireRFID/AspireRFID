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
package org.ow2.aspirerfid.epc.ale.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.log.LogService;
import org.osgi.service.wireadmin.Consumer;
import org.osgi.service.wireadmin.Wire;
import org.osgi.service.wireadmin.WireConstants;
import org.osgi.util.measurement.Measurement;
import org.osgi.util.position.Position;

import org.ow2.aspirerfid.ale.ECReports;
import org.ow2.aspirerfid.ale.ECReportsDocument;
import org.ow2.aspirerfid.event.eabridge.EABridge;
import org.ow2.aspirerfid.epc.ale.api.ECSpec;
import org.ow2.aspirerfid.epc.ale.api.ECSpecValidationException;
import org.ow2.aspirerfid.epc.ale.report.impl.ECMeasurementImpl;
import org.ow2.aspirerfid.epc.ale.tag.impl.EPCTagFactory;
import org.ow2.aspirerfid.epc.ale.report.api.ECMeasurement;
import org.ow2.aspirerfid.epc.ale.tag.api.EPCTag;
import org.ow2.aspirerfid.util.Logger;
import org.ow2.aspirerfid.util.RFIDConstants;
import org.ow2.aspirerfid.wires.GpsRead;
import org.ow2.aspirerfid.wires.RFIDTagRead;
import org.ow2.aspirerfid.wires.TemperatureRead;
import org.ow2.aspirerfid.common.cron.CronService;
import org.ow2.aspirerfid.common.cron.TimedObject;

/**
 * This class consumes RFID tags and measurements sent by readers and publishes
 * periodically reports to subscribers.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class RFIDListener implements Consumer, RFIDListenerMBean, TimedObject,
		ServiceListener {

	private static final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ System.getProperty("line.separator");

	private final ALE ale;

	private final String ecSpecName;

	private final ECSpec ecSpec;

	private final Logger logger;

	private CronService cronService;

	private EventAdmin eventAdmin;

	private final String cronName = "Report";

	private final BundleContext bundleContext;

	private ServiceRegistration listenerServiceRegistration;

	private boolean started;

	private final EPCBuffer epcBuffer;

	private EABridge eaBridge;

	private final String reportTopic = "rfidtopic";

	private boolean cronServiceStarted = false;

	private List polls;

	private final Map mapPolls;

	private long messageIdCounter;

	private Wire[] wires = null;

	/**
	 * TODO Javadoc
	 * 
	 * @param bundleContext
	 * @param logger
	 * @param ale
	 * @param specName
	 * @param ecSpec
	 * @throws ECSpecValidationException
	 */
	public RFIDListener(final BundleContext bundleContext, final Logger logger,
			final ALE ale, final String specName, final ECSpec ecSpec)
			throws ECSpecValidationException {
		this.polls = new Vector();
		this.mapPolls = new HashMap();
		this.bundleContext = bundleContext;
		this.logger = logger;
		this.ale = ale;
		this.ecSpecName = specName;
		this.ecSpec = ecSpec;
		this.epcBuffer = new EPCBuffer(ecSpec, specName, logger);
		this.messageIdCounter = 1;

		// If the ECSpec is correctly constructed
		final String problem = ecSpec.getValidationMessage();
		if (problem == null) {
			logger.log(LogService.LOG_INFO, "ECSpec validation succeeded");

			final Dictionary props = new Hashtable();
			props.put("jmxagent.objectname", "rfid:type=listener,SymbolicName="
					+ specName);
			// register a service implementation
			this.listenerServiceRegistration = bundleContext.registerService(
					RFIDListenerMBean.class.getName(), this, props);
		} else {
			throw new ECSpecValidationException(problem);
		}

		// Get the Cron Service reference to send reports periodically
		final ServiceReference cronServiceReference = bundleContext
				.getServiceReference(CronService.class.getName());
		if (cronServiceReference != null) {
			this.cronService = (CronService) bundleContext
					.getService(cronServiceReference);
			this.cronServiceStarted = true;
		}

		// Get the Event Admin Service reference
		final ServiceReference eventAdminServiceReference = bundleContext
				.getServiceReference(EventAdmin.class.getName());
		if (eventAdminServiceReference != null) {
			this.eventAdmin = (EventAdmin) bundleContext
					.getService(eventAdminServiceReference);
		}

		// Get the EABridge Service reference to export reports
		final ServiceReference eaBridgeServiceReference = bundleContext
				.getServiceReference(EABridge.class.getName());
		if (eaBridgeServiceReference != null) {
			this.eaBridge = (EABridge) bundleContext
					.getService(eaBridgeServiceReference);
			final String[] topics = new String[] { this.reportTopic };
			this.eaBridge.exportTopics(topics);
		}

		// Listen to service events
		bundleContext.addServiceListener(this);
	}

	/**
	 * TODO Javadoc
	 * 
	 * @param result
	 */
	public void addPoll(final PollResult result) {
		this.polls.add(result);
	}

	/**
	 * Method called for each timer cycle.
	 * 
	 * @param arg0
	 *            TODO Javadoc
	 */
	public synchronized void doReact(final Serializable arg0) {
		String reportToSend = null;

		if (this.eaBridge != null) {
			final ECReports reports = this.epcBuffer.buildXMLECReports();
			final ECReportsDocument reportDoc = ECReportsDocument.Factory
					.newInstance();
			reportDoc.setECReports(reports);
			reportToSend = RFIDListener.HEADER + reportDoc.toString();

			this.logger.log(LogService.LOG_INFO, reportToSend);

			final Dictionary dict = new Hashtable();
			dict.put(RFIDConstants.REPORT_ENTRY, reportToSend);

			try {
				final List subscribers = this.ale
						.getSubscribers(this.ecSpecName);
				this.logger.log(LogService.LOG_INFO, "Send report "
						+ this.messageIdCounter + " to  " + subscribers);

				dict.put(RFIDConstants.MESSAGE_ID, Long
						.toString((this.messageIdCounter)));
				dict.put(RFIDConstants.GATEWAY_NAME, this.ecSpec
						.getGatewayName());

				for (int i = 0; i < subscribers.size(); i++) {
					final String uri = (String) subscribers.get(i);
					dict.put(RFIDConstants.DEST_URI, uri);
					final Event event = new Event(this.reportTopic, dict);
					this.eventAdmin.postEvent(event);
				}

				this.messageIdCounter++;
				if (this.messageIdCounter > Long.MAX_VALUE) {
					this.messageIdCounter = 1;
				}

			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < this.polls.size(); i++) {
			final PollResult p = (PollResult) this.polls.get(i);
			this.mapPolls.put(p, reportToSend);
			synchronized (p) {
				p.notify();
			}
		}
		this.polls = new Vector();
	}

	/**
	 * @param result
	 * @return TODO Javadoc
	 */
	public String getPollResult(final PollResult result) {
		final String r = (String) this.mapPolls.get(result);
		this.mapPolls.remove(r);
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.wireadmin.Consumer#producersConnected(org.osgi.service.wireadmin.Wire[])
	 */
	public void producersConnected(final Wire[] wires) {
		final Wire[] oldWires = this.wires;
		this.wires = wires;
		if (wires != null) {
			for (int i = 0; (wires != null) && (i < wires.length); i++) {
				final Wire w = wires[i];
				if (w.isValid() && w.isConnected()) {
					int o = 0;
					for (; (oldWires != null) && (o < oldWires.length); o++) {
						if (w.equals(oldWires[o])) {
							break;
						}
					}
					if (o == oldWires.length) { // the consumer polls the
						// producer because the Wire has
						// been just created !
						this.updated(w, w.poll());
					}
				}
			}
		}

	}

	private void register(final ServiceReference serviceReference) {
		final String[] property = (String[]) serviceReference
				.getProperty(Constants.OBJECTCLASS);
		if (property[0].equals(CronService.class.getName())) {
			this.cronService = (CronService) this.bundleContext
					.getService(serviceReference);
			final long duration = this.ecSpec.getDuration();
			if (this.started) {
				this.cronService.add(this, this.cronName, this.cronService
						.cronString(duration));
				this.cronServiceStarted = true;
			}
		} else if (property[0].equals(EABridge.class.getName())) {
			this.eaBridge = (EABridge) this.bundleContext
					.getService(serviceReference);
			final String[] topics = new String[] { this.reportTopic };
			this.eaBridge.exportTopics(topics);
		}
	}

	/**
	 * TODO Javadoc
	 */
	public void remove() {
		this.stop();
		if (this.listenerServiceRegistration != null) {
			// Unregisters RFID Listener Service (MBean)
			this.listenerServiceRegistration.unregister();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.ServiceListener#serviceChanged(org.osgi.framework.ServiceEvent)
	 */
	public void serviceChanged(final ServiceEvent serviceEvent) {
		final ServiceReference serviceReference = serviceEvent
				.getServiceReference();

		if (serviceEvent.getType() == ServiceEvent.REGISTERED) {
			this.register(serviceReference);
		} else if (serviceEvent.getType() == ServiceEvent.UNREGISTERING) {
			this.unregister(serviceReference);
		} else if (serviceEvent.getType() == ServiceEvent.MODIFIED) {
			this.unregister(serviceReference);
			this.register(serviceReference);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.RFIDListenerMBean#start()
	 */
	public void start() {
		if (!this.started) {
			// Register a consumer service
			final Dictionary registrationProperties = new Hashtable();
			registrationProperties.put(Constants.SERVICE_PID,
					"org.ow2.aspirerfid.service.aleconsumer");
			registrationProperties.put(
					WireConstants.WIREADMIN_CONSUMER_FLAVORS, new Class[] {
							RFIDTagRead.class, TemperatureRead.class,
							GpsRead.class });
			(this.bundleContext).registerService(Consumer.class.getName(),
					this, registrationProperties);

			// Don't send anything if there is no cron service
			if (this.cronService != null) {
				long cronRepeatPeriod;
				if (this.ecSpec.getRepeatPeriod() < this.ecSpec.getDuration()) {
					cronRepeatPeriod = this.ecSpec.getDuration();
				} else {
					cronRepeatPeriod = this.ecSpec.getRepeatPeriod();
				}

				this.cronService.add(this, this.cronName, this.cronService
						.cronString(cronRepeatPeriod));

			} else {
				this.logger.log(LogService.LOG_WARNING,
						"Cron Service not found");
			}
			this.started = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.impl.RFIDListenerMBean#stop()
	 */
	public void stop() {
		if (this.started) {
			if (this.cronServiceStarted) {
				// Stops sending reports
				this.cronService.remove(this, this.cronName);
			}
			this.started = false;
		}
	}

	private void unregister(final ServiceReference serviceReference) {
		final String[] property = (String[]) serviceReference
				.getProperty(Constants.OBJECTCLASS);
		if (property[0].equals(CronService.class.getName())) {
			this.cronServiceStarted = false;
			this.cronService.remove(this, this.cronName);
		} else if (property[0].equals(EABridge.class.getName())) {
			this.eaBridge = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.service.wireadmin.Consumer#updated(org.osgi.service.wireadmin.Wire,
	 *      java.lang.Object)
	 */
	public void updated(final Wire wire, final Object object) {
		if (object != null) {
			if (object instanceof RFIDTagRead) {
				this.logger.log(LogService.LOG_INFO, this.getClass().getName()
						+ ": updated RFIDTagRead object \"" + object
						+ "\" on wire " + wire.toString());
				// extraction of the necessary information from the Hash
				final RFIDTagRead prop = (RFIDTagRead) object;
				final String guid = (String) prop
						.get(RFIDConstants.TAGGUID_KEY);
				final EPCTag epc = EPCTagFactory.getInstance(guid);
				final String reader = (String) prop
						.get(RFIDConstants.READERGUID_KEY);
				final String timeStamp = (String) prop
						.get(EventConstants.TIMESTAMP);
				final String logicalName = (String) prop
						.get(RFIDConstants.READERNAME_KEY);
				final String coordinates = (String) prop
						.get(RFIDConstants.COORDINATES_KEY);

				final List measurements = new ArrayList();
				// Get the most recent value of the wires
				for (int i = 0; (this.wires != null) && (i < this.wires.length); i++) {
					final Wire w = this.wires[i];
					// if it is a measurement
					final Object obj = w.getLastValue();
					if (obj instanceof TemperatureRead) {
						final TemperatureRead temp = (TemperatureRead) obj;
						logger.log(LogService.LOG_INFO,
								"Object instance of TemperatureRead");
						final Measurement m = new Measurement(temp
								.getTemperature().getValue(), temp
								.getTemperature().getError(), temp
								.getTemperature().getUnit(), temp
								.getTemperature().getTime());
						final ECMeasurement measurement = new ECMeasurementImpl(
								m, temp.getAppName(), temp.getSensor());
						measurements.add(measurement);
					} else if (obj instanceof GpsRead) {
						final GpsRead gps = (GpsRead) obj;
						logger.log(LogService.LOG_INFO,
								"Object instance of GpsRead");
						Position position = gps.getPosititon();

						final Measurement alt = new Measurement(position
								.getAltitude().getValue(), position
								.getAltitude().getError(), position
								.getAltitude().getUnit(), position
								.getAltitude().getTime());

						final Measurement lon = new Measurement(position
								.getLongitude().getValue(), position
								.getLongitude().getError(), position
								.getLongitude().getUnit(), position
								.getLongitude().getTime());

						final Measurement lat = new Measurement(position
								.getLatitude().getValue(), position
								.getLatitude().getError(), position
								.getLatitude().getUnit(), position
								.getLatitude().getTime());

						final ECMeasurement altitude = new ECMeasurementImpl(
								alt, gps.getAppNameAlt(), gps.getSensor());
						final ECMeasurement longitude = new ECMeasurementImpl(
								lon, gps.getAppNameLon(), gps.getSensor());
						final ECMeasurement latitude = new ECMeasurementImpl(
								lat, gps.getAppNameLat(), gps.getSensor());

						measurements.add(altitude);
						measurements.add(longitude);
						measurements.add(latitude);
					}
				}
				// to get coord and measurements the producers have been polled
				final ALEEvent aleEvent = new ALEEvent(epc, reader, timeStamp,
						logicalName, coordinates, measurements);

				this.epcBuffer.handleALEEvent(aleEvent);
			}
		} else {
			this.logger.log(LogService.LOG_ERROR, this.getClass().getName()
					+ ": updated null object " + " on wire " + wire.toString());
		}
	}
}
