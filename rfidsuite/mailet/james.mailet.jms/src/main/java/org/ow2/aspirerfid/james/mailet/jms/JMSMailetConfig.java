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
import java.util.Properties;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 * @version 2007
 */
public class JMSMailetConfig {

	private String jmsServerHost;
	private int jmsServerPort;
	private String jmsAdminLogin;
	private String jmsAdminPass;
	private String jmsTopicDestination;
	private String jmsTopicConnectionFatcoryName;

	private Properties props;

	/**
	 * TODO Javadoc
	 * 
	 * @throws IOException
	 */
	public JMSMailetConfig() throws IOException {
		props = new Properties();
		props.load(this.getClass().getResourceAsStream("/jms.properties"));

		jmsServerHost = props.getProperty("jms.server.host");
		jmsServerPort = Integer.parseInt(props.getProperty("jms.server.port"));
		jmsAdminLogin = props.getProperty("jms.admin.login");
		jmsAdminPass = props.getProperty("jms.admin.pass");
		jmsTopicDestination = props.getProperty("jms.topic.destination");
		jmsTopicConnectionFatcoryName = props
				.getProperty("jms.topic.connection.fatcory.bind.name");
	}

	/**
	 * @return TODO Javadoc
	 */
	public Properties getPropertyFile() {
		return props;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getJmsAdminLogin() {
		return jmsAdminLogin;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getJmsAdminPass() {
		return jmsAdminPass;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getJmsServerHost() {
		return jmsServerHost;
	}

	/**
	 * @return TODO Javadoc
	 */
	public int getJmsServerPort() {
		return jmsServerPort;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getJmsTopicDestination() {
		return jmsTopicDestination;
	}

	/**
	 * @return TODO Javadoc
	 */
	public Properties getProps() {
		return props;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getJmsTopicConnectionFatcoryName() {
		return jmsTopicConnectionFatcoryName;
	}
}
