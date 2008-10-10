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
package org.ow2.aspirerfid.james.mailet.jms;

import java.io.IOException;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.mail.MessagingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.mailet.Mail;
import org.apache.mailet.Mailet;
import org.apache.mailet.MailetConfig;
import org.objectweb.joram.client.jms.Topic;
import org.objectweb.joram.client.jms.admin.AdminModule;
import org.objectweb.joram.client.jms.tcp.TopicTcpConnectionFactory;
import org.ow2.aspirerfid.james.mailet.jms.util.HashtableParser;

/**
 * JAMES Mailet : convert mail to JMS message
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class JMSMailet implements Mailet {

	private static final int SERVER_TIMEOUT_CONNECTION = 60; // in seconds

	private TopicPublisher publisher;

	private TopicSession session;

	private TopicConnection cnn;

	private MailetConfig mailetConf;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.mailet.Mailet#init(org.apache.mailet.MailetConfig)
	 */
	public void init(MailetConfig config) throws MessagingException {

		mailetConf = config;
		Context initialContext;

		JMSMailetConfig conf;
		try {
			conf = new JMSMailetConfig();
		} catch (IOException e2) {
			throw new MessagingException("[ERROR] Load config failed");
		}

		try {
			// Get initialContext
			initialContext = new InitialContext(conf.getPropertyFile());

			// Get TopicConnectionFactory
			TopicConnectionFactory tcf;
			try {
				tcf = (TopicConnectionFactory) initialContext.lookup(conf
						.getJmsTopicConnectionFatcoryName());
			} catch (NamingException e1) {
				tcf = (TopicConnectionFactory) TopicTcpConnectionFactory
						.create(conf.getJmsServerHost(), conf
								.getJmsServerPort());
				initialContext.rebind(conf.getJmsTopicConnectionFatcoryName(),
						tcf);
			}

			AdminModule.connect(conf.getJmsServerHost(), conf
					.getJmsServerPort(), conf.getJmsAdminLogin(), conf
					.getJmsAdminPass(), SERVER_TIMEOUT_CONNECTION);

			// Get Topic
			Topic topic = null;
			try {
				topic = (Topic) initialContext.lookup(conf
						.getJmsTopicDestination());
				topic.setFreeReading();
				topic.setFreeWriting();
			} catch (NamingException e1) {
				topic = Topic.create(conf.getJmsTopicDestination());
				topic.setFreeReading();
				topic.setFreeWriting();
				initialContext.bind(conf.getJmsTopicDestination(), topic);
			}
			AdminModule.disconnect();

			// Create TopicConnection et TopicSession
			cnn = tcf.createTopicConnection(conf.getJmsAdminLogin(), conf
					.getJmsAdminPass());
			session = cnn.createTopicSession(true,
					TopicSession.SESSION_TRANSACTED);
			publisher = session.createPublisher(topic);
			initialContext.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new MessagingException(
					"[ERROR] Mailet init failed, maybe connection problem with Joram server");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.mailet.Mailet#destroy()
	 */
	public void destroy() {
		System.out.println("[james.mailet.jms] destroy()");
		try {
			cnn.close();
			session.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.mailet.Mailet#getMailetInfo()
	 */
	public String getMailetInfo() {
		return "JMS Mailet";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.mailet.Mailet#getMailetConfig()
	 */
	public MailetConfig getMailetConfig() {
		return mailetConf;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.mailet.Mailet#service(org.apache.mailet.Mail)
	 */
	public void service(Mail mail) throws MessagingException {
		System.out.println("[james.mailet.jms] service() v3]");
		Message msg;
		String strContent = "";

		// Get mail content
		Hashtable hashMessage;
		try {
			strContent = mail.getMessage().getContent().toString();
			System.out.println("[INFO-DEBUG] strContent : " + strContent);
		} catch (IOException e1) {
			System.out.println("[ERROR] Message content unreadable");
			e1.printStackTrace();
			throw new MessagingException();
		}

		// Convert mail content to hashtable
		try {
			hashMessage = HashtableParser
					.parseStringStringHashtable(strContent);
		} catch (Exception e) {
			System.out.println("[ERROR] Message parsing failed");
			e.printStackTrace();
			throw new MessagingException();
		}

		// Create JMS Message then send
		try {
			msg = session.createObjectMessage(hashMessage);
			publisher.publish(msg);
			session.commit();
		} catch (JMSException e) {
			System.out.println("[ERROR] MessagingException()");
			throw new MessagingException();
		}
	}
}