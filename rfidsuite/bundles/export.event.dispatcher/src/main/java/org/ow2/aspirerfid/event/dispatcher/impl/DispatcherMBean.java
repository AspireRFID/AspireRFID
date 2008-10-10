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

/**
 * Interface to expose the Dispatcher to JMX
 * 
 * @author François Fornaciari
 * @version 2007
 */
public interface DispatcherMBean {

	/**
	 * Returns the gateway name
	 * 
	 * @return the gateway name
	 */
	public String getGatewayName();

	/**
	 * Sets the name of the gateway which sends reports
	 * 
	 * @param gatewayName
	 *            the gateway name
	 */
	public void setGatewayName(String gatewayName);

	/**
	 * Creates a JMS consumer to listen to incoming JMS messages
	 * 
	 * @param topic
	 *            the topic to subscribe
	 * @throws Exception
	 *             Throws if the consumer cannot be created
	 */
	public void createConsumer(String topic) throws Exception;

	/**
	 * Removes a JMS consumer to stop listen to incoming JMS messages
	 * 
	 * @param topic
	 *            the topic to subscribe
	 * @throws Exception
	 *             Throws if the consumer cannot be removed
	 */
	public void removeConsumer(String topic) throws Exception;

	/**
	 * Gets the JMS admin login
	 * 
	 * @return the JMS admin login
	 */
	public String getJmsAdminLogin();

	/**
	 * Sets the JMS admin login to manage Joram
	 * 
	 * @param jmsAdminLogin
	 *            the JMS admin login
	 */
	public void setJmsAdminLogin(String jmsAdminLogin);

	/**
	 * Returns the JMS admin password
	 * 
	 * @return the JMS admin password
	 */
	public String getJmsAdminPass();

	/**
	 * Sets the JMS admin password to manage Joram
	 * 
	 * @param jmsAdminPass
	 *            the JMS admin password
	 */
	public void setJmsAdminPass(String jmsAdminPass);

	/**
	 * Returns the JMS server host
	 * 
	 * @return the JMS server host
	 */
	public String getJmsServerHost();

	/**
	 * Sets the JMS server host
	 * 
	 * @param jmsServerHost
	 *            the JMS server host
	 */
	public void setJmsServerHost(String jmsServerHost);

	/**
	 * Returns the JMS server port
	 * 
	 * @return the JMS server port
	 */
	public int getJmsServerPort();

	/**
	 * Sets the JMS server port
	 * 
	 * @param jmsServerPort
	 *            the JMS server port
	 */
	public void setJmsServerPort(int jmsServerPort);

	/**
	 * Returns the JMS topic connection factory name
	 * 
	 * @return the JMS topic connection factory name
	 */
	public String getJmsTopicConnectionFactoryName();

	/**
	 * Sets the JMS topic connection factory name
	 * 
	 * @param jmsTopicConnectionFactoryName
	 *            the JMS topic connection factory name
	 */
	public void setJmsTopicConnectionFactoryName(
			String jmsTopicConnectionFactoryName);

	/**
	 * Returns Java naming factory host
	 * 
	 * @return the Java naming factory host
	 */
	public String getJavaNamingFactoryHost();

	/**
	 * Sets the Java naming factory host to initialize the JNDI context
	 * 
	 * @param javaNamingFactoryHost
	 *            the Java naming factory host
	 */
	public void setJavaNamingFactoryHost(String javaNamingFactoryHost);

	/**
	 * Returns the Java naming factory initial
	 * 
	 * @return the Java naming factory initial
	 */
	public String getJavaNamingFactoryInitial();

	/**
	 * Sets the Java naming factory initial
	 * 
	 * @param javaNamingFactoryInitial
	 *            the Java naming factory initial to initialize the JNDI context
	 */
	public void setJavaNamingFactoryInitial(String javaNamingFactoryInitial);

	/**
	 * Returns the Java naming factory port to initialize the JNDI context
	 * 
	 * @return the Java naming factory port
	 */
	public int getJavaNamingFactoryPort();

	/**
	 * Sets the Java naming factory port
	 * 
	 * @param javaNamingFactoryPort
	 *            the Java naming factory port
	 */
	public void setJavaNamingFactoryPort(int javaNamingFactoryPort);

	/**
	 * Returns the destination Uri
	 * 
	 * @return the destination Uri
	 */
	public String getDestinationURI();

	/**
	 * Sets the destination Uri to send reports using the specified properties
	 * (protocol, address, parameters)
	 * 
	 * @param destinationURI
	 *            the destination Uri
	 */
	public void setDestinationURI(String destinationURI);
}
