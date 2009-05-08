/*
 * Copyright (c) 2008-2010, Aspire 
 * 
 * This file contains the source code of the Accada library by ETH Zurich (www.accada.org), 
 * licensed under the terms of the GNU Lesser General Public License version 2.1 in 2007 and 
 * modified for the needs of the Aspire project.
 * 
 * Aspire is free software; you can redistribute it and/or 
 * modify it under  the terms of the GNU Lesser General Public 
 * License version 2.1 as published by the Free Software Foundation (the 
 * "LGPL"). 
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library in the file COPYING-LGPL-2.1; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA. 
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY 
 * OF ANY KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 *  
 */

package org.ow2.aspirerfid.reader.rp.impl.core.msg;

import java.net.URL;

import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.util.ResourceLocator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;


/**
 * Loads the configuration properties for the MessageLayer from the property
 * file.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 * 
 */
public final class MessageLayerConfiguration {

	/** The logger. */
	private static Logger log = Logger
			.getLogger(MessageLayerConfiguration.class);

	/** The path of the property file. */
	private static final String msgLayerPropFile = ReaderDevice.PROPERTIES_FILE;
   private static final String msgLayerDefaultPropFile = ReaderDevice.DEFAULT_PROPERTIES_FILE;

	/** Key for the thread pool size property. */
	private static final String THREAD_POOL_SIZE = "threadPoolSize";

	/** Default value for the thread pool size. */
	public static final String THREAD_POOL_SIZE_DEFAULT = "16";

	/** Key for the TCP server connection property. */
	private static final String TCP_SERVER_CONNECTION = "tcpServerConnection";

	/** Key for the TCP port property. */
	private static final String TCP_PORT = "tcpPort";

	/** Key for the HTTP server connection property. */
	private static final String HTTP_SERVER_CONNECTION = "httpServerConnection";

	/** Key for the HTTP port property. */
	private static final String HTTP_PORT = "httpPort";

	/** Key for the notification timeout property. */
	private static final String NOTIFICATION_LISTEN_TIMEOUT = "notificationListenTimeout";

	/** Default value for the notification timeout. */
	public static final String NOTIFICATION_LISTEN_TIMEOUT_DEFAULT = "0";

	/** The properties. */
	private static XMLConfiguration configuration;

	/** The singleton configuration instance. */
	private static MessageLayerConfiguration instance;

	/**
	 * Private constructor to construct the hide the default constructor.
	 */
	private MessageLayerConfiguration() {
		configuration = getProperties();
	}

	/**
	 * Gets the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static MessageLayerConfiguration getInstance() {
		if (instance == null) {
			instance = new MessageLayerConfiguration();
		}
		return instance;
	}

   /**
    * Singleton implementation of properties file accessor.
    * 
    * @return properties instance
    */
   private static XMLConfiguration getProperties() {
      return getProperties(msgLayerPropFile, msgLayerDefaultPropFile);
   }

	/**
	 * Singleton implementation of properties file accessor.
	 * 
	 * @return properties instance
	 */
	private static XMLConfiguration getProperties(final String propFile,
         final String defaultPropFile) {
		URL fileurl;
		if (configuration == null) {
	      // properties
		   configuration = new XMLConfiguration();
	      try {
            fileurl = ResourceLocator.getURL(propFile, defaultPropFile);
	         configuration.load(fileurl);
	      } catch (ConfigurationException e) {
	         log.error("Could not find properties file: " + propFile);
	      }
	   }
		return configuration;
	}

	/**
	 * Gets the number of workers for the thread pool.
	 * 
	 * @return The number of workers or <code>THREAD_POOL_SIZE_DEFAULT</code>
	 *         if the property isn't specified in the property file.
	 * @throws NumberFormatException
	 *             If the pool size couldn't be converted into an
	 *             <code>int</code>.
	 */
	public int getThreadPoolSize() throws NumberFormatException {
      return Integer.parseInt(configuration.getString(
            THREAD_POOL_SIZE, THREAD_POOL_SIZE_DEFAULT));
	}

	/**
	 * Should a TCP server be openend?
	 * 
	 * @return the value of <code>tcpServerConnection</code> or
	 *         <code>false</code> if the value is not specified. Also if the
	 *         TCP port is not specified it returns <code>false</code>.
	 */
	public boolean hasTcpServerConnection() {
		String tcpConn = configuration.getString(TCP_SERVER_CONNECTION);
		if (tcpConn != null && tcpConn.equalsIgnoreCase("true")
				&& getTcpPort() != -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the port on which the TCP server should listen.
	 * 
	 * @return the TCP port or <code>-1</code> if there is no TCP port
	 *         specified.
	 * @throws NumberFormatException
	 *             If the value couldn't be converted into an <code>int</code>.
	 */
	public int getTcpPort() throws NumberFormatException {
		String tcpPort = configuration.getString(TCP_PORT);
		if (tcpPort != null) {
			return Integer.parseInt(tcpPort);
		} else {
			return -1;
		}
	}

	/**
	 * Should a HTTP server be openend?
	 * 
	 * @return the value of <code>httpServerConnection</code> or
	 *         <code>false</code> if the value is not specified. Also if the
	 *         HTTP port is not specified it returns <code>false</code>.
	 */
	public boolean hasHttpServerConnection() {
		String httpConn = configuration.getString(HTTP_SERVER_CONNECTION);
		if (httpConn != null && httpConn.equalsIgnoreCase("true")
				&& getHttpPort() != -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the port on which the HTTP server should listen.
	 * 
	 * @return the HTTP port or <code>-1</code> if there is no HTTP port
	 *         specified.
	 * @throws NumberFormatException
	 *             If the value couldn't be converted into an <code>int</code>.
	 */
	public int getHttpPort() throws NumberFormatException {
		String httpPort = configuration.getString(HTTP_PORT);
		if (httpPort != null) {
			return Integer.parseInt(httpPort);
		} else {
			return -1;
		}
	}

	/**
	 * Returns the timeout in ms which a NotificationChannel in listen mode is
	 * waiting for a connection from the client. A value of 0 indicates an
	 * infinite waiting time.
	 * 
	 * @return the timeout time or 0 if the property isn't specified.
	 * @throws NumberFormatException
	 *             If the value couldn't be converted into an <code>int</code>.
	 */
	public int getNotificationListenTimeout() throws NumberFormatException {
		return Integer.parseInt(configuration.getString(
				NOTIFICATION_LISTEN_TIMEOUT,
				NOTIFICATION_LISTEN_TIMEOUT_DEFAULT));
	}

}
