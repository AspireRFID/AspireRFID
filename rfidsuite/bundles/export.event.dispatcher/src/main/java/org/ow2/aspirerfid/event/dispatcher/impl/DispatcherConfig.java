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

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * Load and expose dispatcher configuration
 * 
 * @author Guillaume Vaudaux-Ruth
 * @author François Fornaciari
 * @version 2007
 */
public class DispatcherConfig {

	private String jmsServerHost;

	private int jmsServerPort;

	private String jmsAdminLogin;

	private String jmsAdminPass;

	private String jmsTopicConnectionFactoryName;

	private String javaNamingFactoryInitial;

	private int javaNamingFactoryPort;

	private String javaNamingFactoryHost;

	private String destinationURI;

	private Properties props;

	private String gatewayName;

	/**
	 * Constructor
	 */
	public DispatcherConfig() {
		props = new Properties();

		setGatewayName("Premise");

		InetAddress inetAddress = null;

		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		}

		try {
			props.load(this.getClass().getResourceAsStream(
					"/dispatcher.properties"));

			if (props.getProperty("jms.server.host").equalsIgnoreCase(
					"localhost")
					|| props.getProperty("jms.server.host").equalsIgnoreCase(
							"127.0.0.1")) {
				jmsServerHost = inetAddress.getHostAddress();
			} else {
				jmsServerHost = props.getProperty("jms.server.host");
			}

			jmsServerPort = Integer.parseInt(props
					.getProperty("jms.server.port"));
			jmsAdminLogin = props.getProperty("jms.admin.login");
			jmsAdminPass = props.getProperty("jms.admin.pass");
			jmsTopicConnectionFactoryName = props
					.getProperty("jms.topic.connection.factory.bind.name");

			javaNamingFactoryInitial = props
					.getProperty("java.naming.factory.initial");
			javaNamingFactoryHost = props
					.getProperty("java.naming.factory.host");
			javaNamingFactoryPort = Integer.parseInt(props
					.getProperty("java.naming.factory.port"));

			destinationURI = props.getProperty("destination.uri");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getJavaNamingFactoryHost() {
		return javaNamingFactoryHost;
	}

	/**
	 * @param javaNamingFactoryHost
	 *            TODO Javadoc
	 */
	public void setJavaNamingFactoryHost(String javaNamingFactoryHost) {
		this.javaNamingFactoryHost = javaNamingFactoryHost;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getJavaNamingFactoryInitial() {
		return javaNamingFactoryInitial;
	}

	/**
	 * @param javaNamingFactoryInitial
	 *            TODO Javadoc
	 */
	public void setJavaNamingFactoryInitial(String javaNamingFactoryInitial) {
		this.javaNamingFactoryInitial = javaNamingFactoryInitial;
	}

	/**
	 * @return TODO Javadoc
	 */
	public int getJavaNamingFactoryPort() {
		return javaNamingFactoryPort;
	}

	/**
	 * @param javaNamingFactoryPort
	 *            TODO Javadoc
	 */
	public void setJavaNamingFactoryPort(int javaNamingFactoryPort) {
		this.javaNamingFactoryPort = javaNamingFactoryPort;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getJmsAdminLogin() {
		return jmsAdminLogin;
	}

	/**
	 * @param jmsAdminLogin
	 *            TODO Javadoc
	 */
	public void setJmsAdminLogin(String jmsAdminLogin) {
		this.jmsAdminLogin = jmsAdminLogin;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getJmsAdminPass() {
		return jmsAdminPass;
	}

	/**
	 * @param jmsAdminPass
	 *            TODO Javadoc
	 */
	public void setJmsAdminPass(String jmsAdminPass) {
		this.jmsAdminPass = jmsAdminPass;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getJmsServerHost() {
		return jmsServerHost;
	}

	/**
	 * @param jmsServerHost
	 *            TODO Javadoc
	 */
	public void setJmsServerHost(String jmsServerHost) {
		this.jmsServerHost = jmsServerHost;
	}

	/**
	 * @return TODO Javadoc
	 */
	public int getJmsServerPort() {
		return jmsServerPort;
	}

	/**
	 * @param jmsServerPort
	 *            TODO Javadoc
	 */
	public void setJmsServerPort(int jmsServerPort) {
		this.jmsServerPort = jmsServerPort;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getJmsTopicConnectionFactoryName() {
		return jmsTopicConnectionFactoryName;
	}

	/**
	 * @param jmsTopicConnectionFactoryName
	 *            TODO Javadoc
	 */
	public void setJmsTopicConnectionFactoryName(
			String jmsTopicConnectionFactoryName) {
		this.jmsTopicConnectionFactoryName = jmsTopicConnectionFactoryName;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getDestinationURI() {
		return destinationURI;
	}

	/**
	 * @param destinationURI
	 *            TODO Javadoc
	 */
	public void setDestinationURI(String destinationURI) {
		this.destinationURI = destinationURI;
	}

	/**
	 * @return TODO Javadoc
	 */
	public String getGatewayName() {
		return this.gatewayName;
	}

	/**
	 * @param gatewayName
	 *            TODO Javadoc
	 */
	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

}
