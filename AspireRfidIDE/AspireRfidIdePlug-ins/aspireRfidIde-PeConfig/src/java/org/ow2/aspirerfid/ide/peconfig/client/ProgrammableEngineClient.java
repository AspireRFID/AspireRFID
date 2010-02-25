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

package org.ow2.aspirerfid.ide.peconfig.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammableEngineDecoderInterface;
import org.ow2.aspirerfid.commons.pe.interfaces.ProgrammableEngineEncoderInterface;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class ProgrammableEngineClient {

	/**
	 * The Programmable Engine Encoder client
	 */
	private ProgrammableEngineEncoderInterface encodeClient = null;

	/**
	 * The Programmable Engine Decoder client
	 */
	private ProgrammableEngineDecoderInterface decodeClient = null;

	/** logger. */
	public static final Logger LOG = Logger.getLogger(ProgrammableEngineClient.class);

	private String peEncodeEndpoint = null;
	private String peDecodeEndpoint = null;

	private String DEFAULT_peEncodeEndpoint = "http://localhost:8080/aspireRfidProgrammableEngine/encoder";
	private String DEFAULT_peDecodeEndpoint = "http://localhost:8080/aspireRfidProgrammableEngine/decoder";

	public ProgrammableEngineClient(String peEndPoint) {

		if (peEndPoint.equals(null) || peEndPoint.equals("") || peEndPoint == null) {
			this.peDecodeEndpoint = DEFAULT_peDecodeEndpoint;
			this.peEncodeEndpoint = DEFAULT_peEncodeEndpoint;

		}
		else if (!peEndPoint.endsWith("/")) {
			this.peDecodeEndpoint = peEndPoint + "decoder";
			this.peEncodeEndpoint = peEndPoint + "encoder";

		}
		else if (peEndPoint.endsWith("/")) {
			this.peDecodeEndpoint = peEndPoint + "/decoder";
			this.peEncodeEndpoint = peEndPoint + "/encoder";
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
		JaxWsProxyFactoryBean encodeClientFactory = new JaxWsProxyFactoryBean();
		encodeClientFactory.setServiceClass(ProgrammableEngineEncoderInterface.class);
		encodeClientFactory.setAddress(peEncodeEndpoint);
		LOG.debug("Creating factory with endpoint: " + peEncodeEndpoint);
		encodeClient = (ProgrammableEngineEncoderInterface) encodeClientFactory.create();

		// Start CXF decodeClient Creation
		JaxWsProxyFactoryBean decodeClientFactory = new JaxWsProxyFactoryBean();
		decodeClientFactory.setServiceClass(ProgrammableEngineDecoderInterface.class);
		decodeClientFactory.setAddress(peDecodeEndpoint);
		LOG.debug("Creating factory with endpoint: " + peDecodeEndpoint);
		decodeClient = (ProgrammableEngineDecoderInterface) decodeClientFactory.create();

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
