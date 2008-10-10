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
package org.ow2.aspirerfid.event.dispatcher.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.felix.ipojo.Nullable;
import org.apache.xmlbeans.XmlException;
import org.objectweb.joram.client.jms.Topic;
import org.objectweb.joram.client.jms.admin.AdminModule;
import org.objectweb.joram.client.jms.tcp.TopicTcpConnectionFactory;
import org.osgi.service.event.EventAdmin;
import org.ow2.aspirerfid.common.cron.CronService;
import org.ow2.aspirerfid.common.cron.TimedObject;
import org.ow2.aspirerfid.event.dispatcher.report.ReportId;
import org.ow2.aspirerfid.event.dispatcher.report.ReportSender;
import org.ow2.aspirerfid.event.export.api.ExportEventDispatcher;
import org.ow2.aspirerfid.event.export.api.ExportEventSpecialized;
import org.ow2.aspirerfid.event.export.uri.BadUriFormatException;
import org.ow2.aspirerfid.event.export.uri.UriParserHelper;
import org.ow2.aspirerfid.util.Logger;
import org.ow2.aspirerfid.util.RFIDConstants;

/**
 * Dispatcher receives messages from EABridge then sends these messages through
 * the protocol specified in the message. For each protocol, a bundle must
 * implement ExportEvent interface to send messages.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @author François Fornaciari
 * @version 2007
 */
public class Dispatcher extends DispatcherConfig implements
		ExportEventDispatcher, MessageListener, TimedObject, DispatcherMBean {

	/**
	 * Service for sending reports by JMS
	 */
	private ExportEventSpecialized exportEventJMS;

	/**
	 * Service for sending reports by SMTP
	 */
	private ExportEventSpecialized exportEventSMTP;

	/**
	 * Service for sending reports by WS
	 */
	private ExportEventSpecialized exportEventWS;

	/**
	 * Joram server connection timeout (in seconds)
	 */
	private static final int SERVER_TIMEOUT_CONNECTION = 10; // in seconds

	/**
	 * Timer cycle duration : every 120 seconds
	 */
	private static final int CRON_DURATION = 120000;

	/**
	 * This list saves received reports to avoid saving redundant reports.
	 */
	private List receivedReports;

	/**
	 * Module for treating received reports
	 */
	private ReportSender reportSender;

	/**
	 * Cron service
	 */
	private CronService cronService;

	/**
	 * Cron name
	 */
	private Serializable cronName;

	private Logger logger;

	/**
	 * List of active JMS consumers
	 */
	private Map consumerMap;

	/**
	 * Event admin service
	 */
	private EventAdmin eventAdmin;

	/**
	 * Returns the event admin service
	 * 
	 * @return The event admin service
	 */
	public EventAdmin getEventAdmin() {
		return eventAdmin;
	}

	/**
	 * Method called when all required services are resolved.
	 */
	public void start() {
		this.receivedReports = new LinkedList();
		this.logger = new Logger("Dispatcher", Logger.DEBUG);
		this.consumerMap = new Properties();
		this.reportSender = new ReportSender(this, logger);

		if (cronService != null) {
			// Starts cron timer
			cronService.add(this, cronName, cronService
					.cronString(CRON_DURATION));
		}

		logger.log(Logger.INFO, "Dispatcher started");
	}

	/**
	 * Method called before all required services are released.
	 */
	public void stop() {
		if (cronService != null) {
			// Stops cron timer
			cronService.remove(this, cronName);
		}

		try {
			// Unregisters all JMS messages listeners
			Iterator it = consumerMap.values().iterator();
			while (it.hasNext()) {
				TopicConnection topicConnection = (TopicConnection) it.next();
				topicConnection.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.log(Logger.INFO, "Dispatcher stopped");
	}

	/**
	 * @param ref
	 *            TODO Javadoc
	 */
	public void removeCron(CronService ref) {
		cronService = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.event.export.api.ExportEventDispatcher#publish(java.util.Map)
	 */
	public void publish(Map report) {
		String uri = (String) report.get(RFIDConstants.DEST_URI);
		String protocol;

		// get protocol
		try {
			protocol = UriParserHelper.getProtocol(uri);
		} catch (BadUriFormatException e) {
			protocol = "";
			logger.log(Logger.ERROR, "Uri : BadUriFormatException");
		}

		// Send report using the correct protocol
		if (protocol.compareToIgnoreCase(RFIDConstants.JMS_PROTOCOL_NAME) == 0) {
			if (!(exportEventJMS instanceof Nullable)) {
				logger.log(Logger.DEBUG, "Message JMS publish");
				exportEventJMS.publish(report);
			} else {
				logger
						.log(Logger.WARNING,
								"Message cannot be sent by JMS: JMS ExportEvent unavailable");
			}
		}

		if (protocol.compareToIgnoreCase(RFIDConstants.SMTP_PROTOCOL_NAME) == 0) {
			if (!(exportEventSMTP instanceof Nullable)) {
				logger.log(Logger.DEBUG, "Message SMTP publish");
				exportEventSMTP.publish(report);
			} else {
				logger
						.log(Logger.WARNING,
								"Message cannot be sent by SMTP: SMTP ExportEvent unavailable");
			}
		}

		if (protocol.compareToIgnoreCase(RFIDConstants.SOAP_PROTOCOL_NAME) == 0) {
			if (!(exportEventWS instanceof Nullable)) {
				logger.log(Logger.DEBUG, "Message WS publish");
				exportEventWS.publish(report);
			} else {
				logger
						.log(Logger.WARNING,
								"Message cannot be sent by WS: WS ExportEvent unavailable");
			}
		}
	}

	/**
	 * @see org.ow2.aspirerfid.event.export.api.ExportEventDispatcher#receive(java.util.Map)
	 */
	public void receive(Map report) {
		String xmlReport = "XMLReport";
		String message = (String) report.get(xmlReport);
		if (message == null) {
			logger.log(Logger.DEBUG, "Report is empty");
		} else {
			// Create the report identifier
			String gatewayName = (String) report.get("gateway.name");
			String messageId = (String) report.get("message.id");
			ReportId reportId = new ReportId(gatewayName, messageId, System
					.currentTimeMillis());

			synchronized (receivedReports) {
				// Treat the received report if it hasn't been already treated
				if (!receivedReports.contains(reportId)) {
					try {
						// Parse the received report
						reportSender.parseReport(message);
						receivedReports.add(reportId);
					} catch (XmlException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/*
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message msg) {
		logger.log(Logger.DEBUG, "JMS message received");

		if (msg instanceof ObjectMessage) {
			// Receive a Map from the sender
			Map report = null;
			try {
				report = (Map) ((ObjectMessage) msg).getObject();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			if (report == null) {
				logger.log(Logger.WARNING, "Map is empty ");
			} else {
				// Treat the JMS message
				receive(report);
			}
		}
	}

	/**
	 * Gets dispatcher logger
	 * 
	 * @return dispatcher logger
	 */
	public Logger getLogger() {
		return logger;
	}

	/*
	 * @see org.ow2.aspirerfid.common.cron.TimedObject#doReact(java.io.Serializable)
	 */
	public void doReact(Serializable serializable) {
		List reportIdList = new LinkedList();

		// Remove old report identifiers from the received report list.
		synchronized (receivedReports) {
			for (int i = 0; i < receivedReports.size(); i++) {
				ReportId reportId = (ReportId) receivedReports.get(i);
				if (reportId.getDate() + CRON_DURATION > System
						.currentTimeMillis()) {
					reportIdList.add(reportId);
				}
			}

			receivedReports = reportIdList;
			logger.log(Logger.DEBUG, "Refreshed list : " + reportIdList);
		}
	}

	/**
	 * @see org.ow2.aspirerfid.event.dispatcher.impl.DispatcherMBean#createConsumer(java.lang.String)
	 */
	public void createConsumer(String topic) throws Exception {
		// If a subscription already exists
		if (consumerMap.containsKey(topic)) {
			logger.log(Logger.WARNING, "Topic already exists");
			throw new Exception("Topic already exists");
		} else {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			Thread.currentThread().setContextClassLoader(
					this.getClass().getClassLoader());

			MessageConsumer subscriber = null;

			try {
				// Get initialContext
				Properties props = new Properties();
				props.put(Context.INITIAL_CONTEXT_FACTORY,
						getJavaNamingFactoryInitial());
				props.put("java.naming.factory.port", new Integer(
						getJavaNamingFactoryPort()).toString());
				props.put("java.naming.factory.host",
						getJavaNamingFactoryHost());
				Context initialContext = new InitialContext(props);

				// Get TopicConnectionFactory
				TopicConnectionFactory tcf;
				try {
					tcf = (TopicConnectionFactory) initialContext
							.lookup(getJmsTopicConnectionFactoryName());

				} catch (NamingException e1) {
					tcf = TopicTcpConnectionFactory.create(getJmsServerHost(),
							getJmsServerPort());
					initialContext.rebind(getJmsTopicConnectionFactoryName(),
							tcf);
				}

				TopicConnection topicConnection = (TopicConnection) tcf
						.createTopicConnection(getJmsAdminLogin(),
								getJmsAdminPass());
				TopicSession session = (TopicSession) topicConnection
						.createTopicSession(false,
								javax.jms.Session.AUTO_ACKNOWLEDGE);

				AdminModule.connect(getJmsServerHost(), getJmsServerPort(),
						getJmsAdminLogin(), getJmsAdminPass(),
						SERVER_TIMEOUT_CONNECTION);

				// Rebind topic
				Topic jmsTopic = Topic.create(topic);
				jmsTopic.setFreeReading();
				jmsTopic.setFreeWriting();
				initialContext.rebind(topic, jmsTopic);

				AdminModule.disconnect();

				subscriber = session.createConsumer(jmsTopic);
				subscriber.setMessageListener(this);
				topicConnection.start();

				Thread.currentThread().setContextClassLoader(classLoader);

				consumerMap.put(topic, topicConnection);

				logger.log(Logger.INFO, "Topic subscription started on "
						+ topic);
			} catch (Exception e) {
				logger
						.log(Logger.INFO,
								"Only for premise configuration : could not connect to Joram server");
			}
		}
	}

	/**
	 * @see org.ow2.aspirerfid.event.dispatcher.impl.DispatcherMBean#removeConsumer(java.lang.String)
	 */
	public void removeConsumer(String topic) throws Exception {
		if (consumerMap.containsKey(topic)) {
			try {
				TopicConnection topicConnection = (TopicConnection) consumerMap
						.get(topic);
				topicConnection.close();
				consumerMap.remove(topic);
				logger.log(Logger.INFO, "Topic subscription removed on "
						+ topic);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else {
			logger.log(Logger.WARNING, "Topic doesn't exist");
			throw new Exception("Topic doesn't exist");
		}
	}
}
