/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.hal.impl.sim;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import org.ow2.aspirerfid.reader.rp.hal.impl.sim.multi.SimulatorServerException;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.multi.SimulatorServerTokens;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.SimulatorController;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.SimulatorEngine;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.util.ResourceLocator;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author regli
 */
public class SimulatorClient extends Thread implements SimulatorEngine {
	
	/** the logger */
	private static final Log LOG = LogFactory.getLog(SimulatorClient.class);

	private String defaultPropFile = "/props/SimulatorClient_default.xml";
	
	private String host;
	private int port;
	private int timeout;
	private int waittime;
	
	private SimulatorController controller;
   private XMLConfiguration config;
	private OutputStream out;
	private InputStream in;
	private boolean unkownHost;
	private boolean connected;

	public SimulatorClient() {}
	
   /**
    * implements the initialize method of the SimulatorEngine
    * 
    * @param controller 
    *          the SimulatorController
    * @param propFile
    *          the path and name of the configuration file
    * @throws SimulatorServerException
    */
	public void initialize(SimulatorController controller, String propFile
         ) throws SimulatorServerException {
	   // TODO: adjust to xml properties file, move configuration from constructor to initialization.
	   this.controller = controller;

      // load properties
      URL url = ResourceLocator.getURL(propFile, defaultPropFile, this.getClass());
      try {
         config = new XMLConfiguration(url);
      } catch (ConfigurationException ce) {
         throw new SimulatorServerException("SimulatorClient configuration file not found.");
      }

      // check properties
      if (!config.containsKey("host")) {
         throw new SimulatorServerException("Property 'host' not found.");
      }
      if (!config.containsKey("port")) {
         throw new SimulatorServerException("Property 'port' not found.");
      }
      if (!config.containsKey("timeout")) {
         throw new SimulatorServerException("Property 'timeout' not found.");
      }
      if (!config.containsKey("waittime")) {
         throw new SimulatorServerException("Property 'waittime' not found.");
      }
      
      // get properties
      host = config.getString("host");
      try {
         port = config.getInt("port");
         timeout = config.getInt("timeout");
         waittime = config.getInt("waittime");
      } catch (NumberFormatException e) {
         throw new SimulatorServerException("Properties 'port', 'timeout' and 'waittime' must be numbers");
      }
      
      tryToConnect();
	}
	
	public void run() {
		while (connected) {
			try {
				while (true) {
					generateEvent(getInput());
				}
			} catch (IOException e) {
				try {
					tryToConnect();
				} catch (SimulatorServerException e1) {
					LOG.error(e1.getMessage());
					LOG.error("could not connect");
				}
			}
		}
	}

	public boolean contains(String antennaId, String tagId) {
		return controller.contains(antennaId, tagId);
	}

	private void tryToConnect() throws SimulatorServerException {
		LOG.info("SimulatorServer tries to connect to SimulatorServerController.");
		connected = false;
		if (connect()) {
			connected = true;
		} else {
			int i = 1;
			int nbrOfLoops = waittime > 0 ? timeout / waittime : 0;
			while (!connected && !unkownHost && i < nbrOfLoops) {
				try {
					Thread.sleep(waittime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				connected = connect();
				i++;
			}
		}
		
		// register if connection is up
		if (connected) {
			LOG.info("SimulatorServer is connected to SimulatorServerController.");

			// start if register was successful and thread is not already started
			if (register()) {
				if (!isAlive()) {
					start();
				}
			} else {
				throw new SimulatorServerException("Could not register");
			}
		} else {
			throw new SimulatorServerException("Could not connect to the server. (timeout = " + timeout + "ms)");
		}
	}
	
	private boolean connect() {
			Socket socket;
			try {
				socket = new Socket(host, port);
				out = socket.getOutputStream();
				in = socket.getInputStream();
				unkownHost = false;
				return true;
			} catch (UnknownHostException e) {
				unkownHost = true;
				LOG.info("Unknown host.");
				return false;
			} catch (IOException e) {
				LOG.info("Connection refused.");
				return false;
			}
	}
	
	private boolean register() {
		String readerId = controller.getHALName();
		String[] antennaIds = null;
		antennaIds = controller.getReadPointNames();
		try {
			out.write((readerId + SimulatorServerTokens.EOH).getBytes());
			for (int i = 0; i < antennaIds.length; i++) {
				out.write(antennaIds[i].getBytes());
				if (i + 1 < antennaIds.length) {
					out.write(SimulatorServerTokens.DELIMITER.getBytes());
				}
			}
			out.write(SimulatorServerTokens.EOL);
			out.flush();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	private void sendAlive() throws IOException {
		out.write(SimulatorServerTokens.ALIVE.getBytes());
		out.flush();
	}
	
	private String getInput() throws IOException {
		StringBuffer input = new StringBuffer();
		int buf = in.read();
		do {
			input.append((char)buf);
			buf = in.read();
		} while ((char)buf != SimulatorServerTokens.EOL);
		return input.toString();
	}
	
	private void generateEvent(String input) {
		String[] fragments = input.split(SimulatorServerTokens.EOH);
		if (fragments.length == 1) {
			if (SimulatorServerTokens.PING.equals(input)) {
				try {
					sendAlive();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				throw new IllegalArgumentException();
			}
		} else if (fragments.length == 2) {
			String type = fragments[0];
			fragments = fragments[1].split(SimulatorServerTokens.DELIMITER);
			if (fragments.length == 2) {
				String antennaId = fragments[0];
				String tagId = fragments[1];
				if (SimulatorServerTokens.ADD.equals(type)) {
					controller.add(antennaId, tagId);
				} else if (SimulatorServerTokens.REMOVE.equals(type)) {
					controller.remove(antennaId, tagId);
				}
			} else {
				throw new IllegalArgumentException();
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
}

