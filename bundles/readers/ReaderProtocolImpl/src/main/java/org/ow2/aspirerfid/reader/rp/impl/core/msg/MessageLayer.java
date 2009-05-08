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


import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.List;

import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.Source;
import org.ow2.aspirerfid.reader.rp.impl.core.Trigger;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.SnmpAgent;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmChannel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.simulator.MgmtSimulator;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.ConnectionThreadPool;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.ServerConnection;
import org.ow2.aspirerfid.reader.rp.impl.core.util.ResourceLocator;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;
import org.snmp4j.agent.io.ImportModes;

/**
 * MessageLayer is the main class. It instantiates the whole infrastructure.
 * After that, class <code>MessageDispatcher</code> and
 * <code>CommandDispatcher</code> are ready to execute commands on the reader.
 *
 * @author Patrice Oehen (poehen@student.ethz.ch)
 * @author Anna Wojtas, Marcel Bihr, Lukas Blunschi
 * @author Andreas F�rer, ETH Zurich, anfuerer@student.ethz.ch
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */
public class MessageLayer {

	// ====================================================================
	// ---------------------------- Fields ------------------------------//
	// ====================================================================

	/** The logger. */
	private static Logger log = Logger.getLogger(MessageLayer.class);

	/** All connected <code>clients</code>. */
	private Clients clients = null;


	/**
	 * The time in ms that the reader waits for a notification connection in
	 * listen mode.
	 */
	private static int notificationListenTimeout;

	/** The single instance of the dispatcher. */
	private MessageDispatcher sDispatcher;

	/** The <code>IncomingMessageBuffer</code>. */
	private IncomingMessageBuffer mbuffer;

	/** The outgoing message dispatcher. */
	private OutgoingMessageDispatcher outDispatcher;

	/** The SNMP agent. */
	private SnmpAgent snmpAgent;

	/** The management agent's address */
	private String mgmtAgentAddress;

	/** The management agent's port */
	private int mgmtAgentPort;

	/** The management agent type (SNMP per default) */
	public static AgentType mgmtAgentType = AgentType.SNMP;

	/** Specifies whether the management simulator will be started */
	private boolean mgmtSimulatorStart;

	/** The management agent's properties file */
	private static final String mgmtAgentPropFile = ReaderDevice.PROPERTIES_FILE;
	private static final String mgmtAgentDefaultPropFile = ReaderDevice.DEFAULT_PROPERTIES_FILE;

	private ReaderDevice readerDevice;
   
	private boolean isInitialized = false;
	
	/** The agent type enum */
	public enum AgentType {
		SNMP
	}

	// ====================================================================
	// ------------------------- Constructor ----------------------------//
	// ====================================================================

	/**
	 * Creates a new Message Layer
	 */
	public MessageLayer() {
		this.initialize();
	}

	public boolean isInitialized()
	{
	    return isInitialized;
	}
	/**
	 * Initializes the messaging layer and starts the reader device.
	 *
	 * @param reader reference to the ReaderDevice
	 */
	private void initialize() {
	    
	    if(isInitialized)
		return;
	    
		log.debug("**************************************");
		log.debug("* MessageLayer is beeing initialized *");
		log.debug("**************************************");

//		readMgmtAgentProperties(MessageLayer.mgmtAgentPropFile,
//         MessageLayer.mgmtAgentDefaultPropFile);

//		switch (MessageLayer.mgmtAgentType) {
//
//			case SNMP:
//
//				/*************************
//				 *   create SNMP agent   *
//				 *************************/
//
//				String bootCounterFileName = "SnmpAgentBC.cfg";
//				String configFileName = "SnmpAgentConfig.cfg";
//				File bootCounterFile = new File(bootCounterFileName);
//				File configFile = new File(configFileName);
//
//				// delete the old files
//				bootCounterFile.delete();
//				configFile.delete();
//
//				// create agent
//				snmpAgent = SnmpAgent.create(bootCounterFile, configFile,
//						mgmtAgentAddress + "/" + mgmtAgentPort);
//				break;
//
//			// case ...:
//
//		}


		/*************************
		 *     MessageLayer      *
		 *************************/

		String connType;

		// sets the parameters according to the properties file
		try {
			Map serverConns = new HashMap();
			MessageLayerConfiguration conf = MessageLayerConfiguration.getInstance();

			try {
				ConnectionThreadPool.create(conf.getThreadPoolSize());

			} catch (NumberFormatException e) {
				log.error("Could not interpret the threadPoolSize from the property file. Please ensure to use a correct integer format.");
			}

			if (conf.hasTcpServerConnection()) {
				connType = ServerConnection.TCP_SERVER;
				try {
					int tcpPort = conf.getTcpPort();
					serverConns.put(connType, new Integer(tcpPort));
				} catch (NumberFormatException e) {
					log.error("Could not interpret the tcpPort from the property file. Please ensure to use a correct integer format.");
				}
			}

			if (conf.hasHttpServerConnection()) {
				connType = ServerConnection.HTTP_SERVER;
				try {
					int httpPort = conf.getHttpPort();
					serverConns.put(connType, new Integer(httpPort));
				} catch (NumberFormatException e) {
					log.error("Could not interpret the httpPort from the property file. Please ensure to use a correct integer format.");
				}
			}

			try {
				notificationListenTimeout = conf.getNotificationListenTimeout();
			} catch (NumberFormatException e) {
				log.error("Could not interpret the notificationListenTimeout from the property file. Please ensure to use a correct integer format.");
			}

			// create the message buffer
			log.debug("creating an IncomingMessageBuffer");
			mbuffer = IncomingMessageBuffer.getInstance();

			// create clients
			log.debug("creating Clients");
			clients = Clients.getInstance();

			// create the outgoing message dispatcher
			log.debug("creating an OutgoingMessageDispatcher");
			outDispatcher = OutgoingMessageDispatcher.getInstance();
			outDispatcher.initialize(clients);

			// create and init the service dispatcher
			log.debug("creating a ServiceDispatcher");
			sDispatcher = MessageDispatcher.getInstance(this);
			sDispatcher.initialize(mbuffer, clients, outDispatcher);
			sDispatcher.start();

			log.debug("creating ServiceConnection");
			Iterator iter = serverConns.keySet().iterator();

			while (iter.hasNext()) {
				String cType = (String) iter.next();
				ServerConnection.createServerConnection(cType,
						((Integer) serverConns.get(cType)).intValue(), mbuffer);
			}

			readerDevice = ReaderDevice.getInstance();

//			switch (MessageLayer.mgmtAgentType) {
//
//				case SNMP:
//
//					/*************************
//					 * initialize SNMP agent *
//					 *************************/
//
//					snmpAgent.init();
//					snmpAgent.loadConfig(ImportModes.UPDATE_CREATE);
//
//					// We need to add the alarm channels again because at their
//					// creation the SNMP agent was not initialized yet.
//					Enumeration<AlarmChannel> alarmChanIter = readerDevice.getAlarmChannels().elements();
//					while (alarmChanIter.hasMoreElements()) {
//						snmpAgent.addAlarmChannels(new AlarmChannel[] { alarmChanIter.nextElement() });
//					}
//
//					snmpAgent.run();
//					break;
//
//				// case ...:
//
//			}

//			if (mgmtSimulatorStart) {
//				MgmtSimulator inst = new MgmtSimulator(readerDevice);
//				inst.setVisible(true);
//			}
			
			isInitialized = true;

		} catch (Exception e) {
			e.printStackTrace();
			isInitialized = false;
		}
	}

	/**
	 * Resets the MessageLayer to the default values and restarts dispatching.
	 *
	 */
	public void reset() {
		stop();
		initialize();
	}
	
	public void stop()
	{
		if(!isInitialized)
		    return;
		
		
//		log.info("killing worker threads");
//		ConnectionThreadPool.getInstance().stopRequestAllWorkers();
		Source source = readerDevice.getCurrentSource();
		readerDevice.stopReaders();
		Hashtable triggers = source.getReadTriggers();
		if(triggers.size() > 0)
		{
			Trigger [] triggerArr = new Trigger[triggers.size()];
			Enumeration iterator = triggers.elements();
			for(int i=0; i<triggerArr.length; i++)
			{
				triggerArr[i] = (Trigger) iterator.nextElement();
			}
			source.removeReadTriggers(triggerArr);
		}
//		// Close all server connections
		List servers = ServerConnection.getServerConnections();
		for (Iterator it = servers.iterator(); it.hasNext();) {
			ServerConnection server = (ServerConnection) it.next();
			try {
			    server.close();
			}catch(Exception e)
			{}
		}
//		
//		// Reset all clients
//		Clients clients = Clients.getInstance();
//		clients.reset();
//
//		// Reset all buffers and dispatchers
//		sDispatcher = null;
		MessageDispatcher.stopDispatcher();
		sDispatcher.stop();
//
//		for(ListIterator i = ServerConnection.getServerConnections().listIterator(); i.hasNext();)
//		{
//			log.info("Closing a connection");
//			ServerConnection sc = (ServerConnection)i.next();
//			sc.close();
//		}
//		mbuffer.clean();

		isInitialized = false;
//		snmpAgent.stop();
	}

	/**
	 * The time in ms a server waits for a host to connect a notification
	 * connection in listen mode.
	 *
	 * @return The time in milliseconds.
	 */
	public static int getNotificationListenTimeout() {
		return notificationListenTimeout;
	}

	/**
	 * Gets the name of this class (e.g.,
	 * org.accada.reader.msg.MessageLayer).
	 *
	 * @return The class name of this class
	 */
	public static String getClassname() {
		Class clazz = MessageLayer.class;
		return clazz.getName();
	}

	/**
	 * Gets the current CLASSPATH
	 *
	 * @return The CLASSPATH
	 */
	public static String getClasspath() {
		return System.getProperty("java.class.path");
	}

//	/**
//	 * Reads the management agent properties from a file.
//	 *
//	 * @param propFile
//	 *            The properties file
//	 * @throws ReaderProtocolException
//	 */
//	private void readMgmtAgentProperties(String propFile, String defaultPropFile) {
//		XMLConfiguration conf;
//      URL fileurl = ResourceLocator.getURL(propFile, defaultPropFile);
//		try {
//			conf = new XMLConfiguration(fileurl);
//			MessageLayer.mgmtAgentType = AgentType.valueOf(conf.getString(
//					"mgmtAgentType").toUpperCase());
//			mgmtAgentAddress = conf.getString("mgmtAgentAddress");
//			mgmtAgentPort = conf.getInt("mgmtAgentPort");
//			mgmtSimulatorStart = conf.getBoolean("mgmtSimulatorStart");
//		} catch (ConfigurationException e) {
//			log.error("Failed to read the management agent information from "
//					+ propFile + "\n -> Start default SNMP agent.");
//			MessageLayer.mgmtAgentType = AgentType.SNMP;
//		}
//	}

}
