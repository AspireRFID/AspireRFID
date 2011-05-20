/*
 * Copyright (C) 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.programmableengine.client;

import java.net.URL;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineOLCBProcControlInterface;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineCLCBProcControlInterface;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammEngineEBProcControlInterface;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class ProgrammableEngineClient {

	/**
	 * The Programmable Engine olcbProcControl client
	 */
	private ProgrammEngineOLCBProcControlInterface olcbProcControlClient = null;

	/**
	 * The Programmable Engine clcbProcControl client
	 */
	private ProgrammEngineCLCBProcControlInterface clcbProcControlClient = null;
	
	/**
	 * The Programmable Engine ebProcControl client
	 */
	private ProgrammEngineEBProcControlInterface ebProcControlClient = null;

	
	/** logger. */
	public static final Logger LOG = Logger.getLogger(ProgrammableEngineClient.class);

	/**
	 * The configuration
	 */
	private XMLConfiguration config = null;

	private String peOLCBProcControlEndpoint = null;
	private String peCLCBProcControlEndpoint = null;
	private String peEBProcControlEndpoint = null;

	private String DEFAULT_peEndpoint = "http://localhost:8080/aspireRfidProgrammableEngine";

	ProgrammableEngineClient() {

		String peEndPoint = null;
		
		// read parameters from configuration file
		config = new XMLConfiguration();
		config.setListDelimiter(',');
		URL fileurl = this.getClass().getResource("/PeClientParameters.xml");
		// sets the parameters according to the properties file

		try {
			config.load(fileurl);
		} catch (ConfigurationException e) {
			String message = "Couldn't get WarehouseParameters at: " + fileurl.getFile() + "\n" + e.getMessage();
			LOG.debug(message);
			e.printStackTrace();
		}

		peEndPoint = config.getString("PeEndPoint");

		if (peEndPoint.equals(null) || peEndPoint.equals("") || peEndPoint == null) {
			this.peOLCBProcControlEndpoint = DEFAULT_peEndpoint + "/olcbproccontrol";
			this.peCLCBProcControlEndpoint = DEFAULT_peEndpoint + "/clcbproccontrol";
			this.peEBProcControlEndpoint = DEFAULT_peEndpoint + "/ebproccontrol";

		} else if (!peEndPoint.endsWith("/")) {
			this.peOLCBProcControlEndpoint = peEndPoint + "olcbproccontrol";
			this.peCLCBProcControlEndpoint = peEndPoint + "clcbproccontrol";
			this.peEBProcControlEndpoint = peEndPoint + "ebproccontrol";

		} else if (peEndPoint.endsWith("/")) {
			this.peOLCBProcControlEndpoint = peEndPoint + "/olcbproccontrol";
			this.peCLCBProcControlEndpoint = peEndPoint + "/clcbproccontrol";
			this.peEBProcControlEndpoint = peEndPoint + "/ebproccontrol";
		}

		initializeWS();

	}
	
	ProgrammableEngineClient(String peEndPoint) {
		
		if (peEndPoint.equals(null) || peEndPoint.equals("") || peEndPoint == null) {
			this.peOLCBProcControlEndpoint = DEFAULT_peEndpoint + "/olcbproccontrol";
			this.peCLCBProcControlEndpoint = DEFAULT_peEndpoint + "/clcbproccontrol";
			this.peEBProcControlEndpoint = DEFAULT_peEndpoint + "/ebproccontrol";

		} else if (!peEndPoint.endsWith("/")) {
			this.peOLCBProcControlEndpoint = peEndPoint + "olcbproccontrol";
			this.peCLCBProcControlEndpoint = peEndPoint + "clcbproccontrol";
			this.peEBProcControlEndpoint = peEndPoint + "ebproccontrol";

		} else if (peEndPoint.endsWith("/")) {
			this.peOLCBProcControlEndpoint = peEndPoint + "/olcbproccontrol";
			this.peCLCBProcControlEndpoint = peEndPoint + "/clcbproccontrol";
			this.peEBProcControlEndpoint = peEndPoint + "/ebproccontrol";
		}

		initializeWS();

	}

	/**
	 * This method initializes the Web Service connection using CXF with Spring
	 */
	private void initializeWS() {

		// Start CXF encodeClient Creation
		// Note that it is important to keep the ProxyFactoryBean ID unique e.g.
		// in this case the "encodeClientFactory"
		JaxWsProxyFactoryBean olcbProcControlClientFactory = new JaxWsProxyFactoryBean();
		olcbProcControlClientFactory.setServiceClass(ProgrammEngineOLCBProcControlInterface.class);
		olcbProcControlClientFactory.setAddress(peOLCBProcControlEndpoint);
		LOG.debug("Creating factory with endpoint: " + peOLCBProcControlEndpoint);
		olcbProcControlClient = (ProgrammEngineOLCBProcControlInterface) olcbProcControlClientFactory.create();

		// Start CXF decodeClient Creation
		JaxWsProxyFactoryBean clcbProcControlClientFactory = new JaxWsProxyFactoryBean();
		clcbProcControlClientFactory.setServiceClass(ProgrammEngineCLCBProcControlInterface.class);
		clcbProcControlClientFactory.setAddress(peCLCBProcControlEndpoint);
		LOG.debug("Creating factory with endpoint: " + peCLCBProcControlEndpoint);
		clcbProcControlClient = (ProgrammEngineCLCBProcControlInterface) clcbProcControlClientFactory.create();

		// Start CXF decodeClient Creation
		JaxWsProxyFactoryBean ebProcControlClientFactory = new JaxWsProxyFactoryBean();
		ebProcControlClientFactory.setServiceClass(ProgrammEngineEBProcControlInterface.class);
		ebProcControlClientFactory.setAddress(peEBProcControlEndpoint);
		LOG.debug("Creating factory with endpoint: " + peEBProcControlEndpoint);
		ebProcControlClient = (ProgrammEngineEBProcControlInterface) ebProcControlClientFactory.create();
		
		// Configuring Clients with the use of client-beans.xml
		// ClassPathXmlApplicationContext context = new
		// ClassPathXmlApplicationContext(new String[] { "/client-beans.xml" });
		// // Start CXF encodeClient Creation
		// encodeClient = (ProgrammableEngineEncoderInterface)
		// context.getBean("encodeClient");
		// // Start CXF decodeClient Creation
		// decodeClient = (ProgrammableEngineDecoderInterface)
		// context.getBean("decodeClient");

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @param openLoopCBProc
	 * @return
	 */
	public int encodeAspireRFID(OLCBProc openLoopCBProc) {

		Integer response = encodeClient.encode(openLoopCBProc);

		LOG.debug("Encode Response ID: " + response);

		return response.intValue();
	}

	/**
	 * @param openLoopCBProcID
	 * @return
	 */
	public OLCBProc decodeAspireRFID(String openLoopCBProcID) {

		OLCBProc openLoopCBProc = decodeClient.decode(openLoopCBProcID);

		LOG.debug("Decode Response openLoopCBProc name: " + openLoopCBProc.getName());

		return openLoopCBProc;
	}

}
