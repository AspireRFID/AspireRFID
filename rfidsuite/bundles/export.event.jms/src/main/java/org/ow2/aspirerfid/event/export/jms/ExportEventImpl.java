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
package org.ow2.aspirerfid.event.export.jms;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.TopicPublisher;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.objectweb.joram.client.jms.ObjectMessage;
import org.objectweb.joram.client.jms.Topic;
import org.objectweb.joram.client.jms.TopicConnection;
import org.objectweb.joram.client.jms.TopicConnectionFactory;
import org.objectweb.joram.client.jms.TopicSession;
import org.objectweb.joram.client.jms.admin.AdminModule;
import org.objectweb.joram.client.jms.tcp.TopicTcpConnectionFactory;
import org.ow2.aspirerfid.event.export.api.ExportEventSpecialized;
import org.ow2.aspirerfid.event.export.uri.BadUriFormatException;
import org.ow2.aspirerfid.event.export.uri.JmsUri;
import org.ow2.aspirerfid.event.export.uri.NotExpectedProtocolException;
import org.ow2.aspirerfid.util.Logger;
import org.ow2.aspirerfid.util.RFIDConstants;

/**
 * Implementation of an Export Event Service. We export OSGi event on JMS (
 * JORAM )
 * 
 * @author mike oullion
 * @author gilles broussillon
 * @author guillaume vaudaux-ruth
 * @version 2007 0.1
 */
public class ExportEventImpl implements ExportEventSpecialized {
	/**
	 * Server timeout
	 */
	private static int SERVER_TIMEOUT_CONNECTION = 30;

	/**
	 * Separators used for topic name in OSGi
	 */
	private static final char SEPARATOR_OSGI = '/';

	/**
	 * Separators used for topic name in JMS
	 */
	private static final char SEPARATOR_JMS = '$';

	private Logger logger;

	/*
	 * @see org.ow2.aspirerfid.event.export.api.ExportEventSpecialized#publish(java.util.Map)
	 */
	public void publish(Map event) {
		this.logger = new Logger("Export JMS", Logger.INFO);

		TopicConnection tc = null;
		TopicSession ts = null;
		TopicPublisher tpub = null;
		Topic jmsTopic = null;
		ObjectMessage msg = null;

		String uri = (String) event.get(RFIDConstants.DEST_URI);
		JmsUri jmsUri = null;
		try {
			jmsUri = new JmsUri(uri);
		} catch (NotExpectedProtocolException e3) {
			logger.log(Logger.ERROR,
					"Publish impossible, URI : Not ExpectedProtocol");
			e3.printStackTrace();
		} catch (BadUriFormatException e3) {
			logger.log(Logger.ERROR,
					"Publish impossible, URI : BadUriFormatException");
			e3.printStackTrace();
		}

		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("java.naming.factory.initial", jmsUri
				.getInitialFactory());
		connectionProperties.setProperty("java.naming.factory.host", jmsUri
				.getFactoryHost());
		connectionProperties.setProperty("java.naming.factory.port", jmsUri
				.getFactoryPort());

		// Get initialContext
		ClassLoader classLoaderCurrentBundle = getClass().getClassLoader();
		Thread.currentThread().setContextClassLoader(classLoaderCurrentBundle);

		Context ictx = null;
		try {
			ictx = new InitialContext(connectionProperties);
		} catch (NamingException e2) {
			logger.log(Logger.ERROR, "Initial context build error.");
			e2.printStackTrace();
		}

		// Get TopicConnectionFactory
		TopicConnectionFactory tcf = null;
		try {
			tcf = (TopicConnectionFactory) ictx.lookup(jmsUri
					.getTopicConnectionFactory());
		} catch (NamingException e1) {
			tcf = (TopicConnectionFactory) TopicTcpConnectionFactory.create(
					jmsUri.getServerHost(), Integer.parseInt(jmsUri
							.getServerPort()));
			try {
				ictx.rebind(jmsUri.getTopicConnectionFactory(), tcf);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		try {
			AdminModule.connect(jmsUri.getServerHost(), Integer.parseInt(jmsUri
					.getServerPort()), jmsUri.getLogin(), jmsUri.getPass(),
					SERVER_TIMEOUT_CONNECTION);

			// Get Topic
			jmsTopic = null;
			try {
				jmsTopic = (Topic) ictx.lookup(jmsUri.getTopic());
			} catch (NamingException e1) {
				jmsTopic = Topic.create(jmsUri.getTopic());
				jmsTopic.setFreeReading();
				jmsTopic.setFreeWriting();
				ictx.bind(jmsUri.getTopic(), jmsTopic);
			}

			AdminModule.disconnect();

			// Create TopicConnection and TopicSession
			tc = (TopicConnection) tcf.createTopicConnection(jmsUri.getLogin(),
					jmsUri.getPass());
			ts = (TopicSession) tc.createTopicSession(true,
					TopicSession.SESSION_TRANSACTED);
			tpub = ts.createPublisher(jmsTopic);
			ictx.close();
		} catch (Exception e) {
			logger.log(Logger.ERROR, "Connection problem.");
			e.printStackTrace();
		}

		try {
			msg = (ObjectMessage) ts.createObjectMessage((Serializable) event);
		} catch (JMSException e) {
			e.printStackTrace();
		}

		try {
			tpub.publish(msg);
		} catch (JMSException e) {
			e.printStackTrace();
			logger.log(Logger.ERROR, "Message published failed");
		}

		logger.log(Logger.DEBUG, "Message published on topic : "
				+ jmsUri.getTopic());

		try {
			ts.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		}

		try {
			tc.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.event.export.api.ExportEventSpecialized#getType()
	 */
	public String getType() {
		return ExportEventSpecialized.TYPE_JMS;
	}

}
