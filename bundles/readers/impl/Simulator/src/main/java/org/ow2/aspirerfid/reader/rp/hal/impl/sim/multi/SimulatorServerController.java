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

package org.ow2.aspirerfid.reader.rp.hal.impl.sim.multi;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.Set;
import java.util.TreeMap;

import org.ow2.aspirerfid.reader.rp.hal.impl.sim.util.ResourceLocator;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author regli
 */
public class SimulatorServerController {

	/** the logger */
	private static Log LOG = LogFactory.getLog(SimulatorServerController.class);

	private String simTypeDefaultPropFile = "/props/GraphicSimulator_default.xml";

	private final ServerSocket serverSocket;
	private final TreeMap readerSimulators = new TreeMap();

	private String simType;
	private XMLConfiguration propsConfig;
	private int port;
	private RegisterSocket registerSocket;

	public SimulatorServerController(String propFile, String defaultPropFile)
         throws SimulatorServerException {
      // load properties
      try {
         URL fileurl = ResourceLocator.getURL(propFile, defaultPropFile, this.getClass());
         propsConfig = new XMLConfiguration(fileurl);
      } catch (ConfigurationException ce) {
         throw new SimulatorServerException("Could not load property file.");
      }
      
		// get properties
		simType = propsConfig.getString("simType");
		String simTypePropFile = propsConfig.getString("simTypePropFile");
		port = propsConfig.getInt("port");

		// try to open server socket
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			throw new SimulatorServerException("Could not open the server socket.");
		}

		// start register socket
		registerSocket = new RegisterSocket();
		registerSocket.start();

		// try to initialize the MutliSimulatorEngine (GraphicSimulatorServer or BatchSimulatorServer)
		LOG.info("SimulatorServerEngine: " + simType);
		try {
			Class simClass = Class.forName(simType);
			SimulatorServerEngine simulator = (SimulatorServerEngine)simClass.getConstructor(new Class[0]).newInstance(new Object[0]);
			simulator.initialize(this, simTypePropFile, simTypeDefaultPropFile);
		} catch (ClassNotFoundException e) {
			throw new SimulatorServerException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new SimulatorServerException(e.getMessage());
		} catch (SecurityException e) {
			throw new SimulatorServerException(e.getMessage());
		} catch (InstantiationException e) {
			throw new SimulatorServerException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new SimulatorServerException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new SimulatorServerException(e.getMessage());
		} catch (NoSuchMethodException e) {
			throw new SimulatorServerException(e.getMessage());
		} catch (SimulatorServerException e) {
			throw new SimulatorServerException(e.getMessage());
		}
	}

	public Set getReaderIds() {
		checkReaderSimulators();
		return readerSimulators.keySet();
	}

	public Set getAntennaIds(String readerId) {
		return ((SimulatorClientStub)readerSimulators.get(readerId)).getAntennaIds();
	}

	public void add(String readerId, String antennaId, String epc) throws SimulatorServerException {
		try {
			SimulatorClientStub readerSimulatorStub = (SimulatorClientStub)readerSimulators.get(readerId);
			if (readerSimulatorStub == null) {
				throw new SimulatorServerException("No such reader '" + readerId + "' available");
			}
			readerSimulatorStub.add(antennaId, epc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void remove(String readerId, String antennaId, String epc) throws SimulatorServerException {
		try {
			SimulatorClientStub readerSimulatorStub = (SimulatorClientStub)readerSimulators.get(readerId);
			if (readerSimulatorStub == null) {
				throw new SimulatorServerException("No such reader '" + readerId + "' available");
			}
			readerSimulatorStub.remove(antennaId, epc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}

	public void stop() {
		registerSocket.exitLoop();
	}

	private void register(Socket socket) throws IOException {
		InputStream in = socket.getInputStream();

		// get arguments
		StringBuffer definition = new StringBuffer();
		int buf = in.read();
		do {
			definition.append((char)buf);
			buf = in.read();
		} while ((char)buf != SimulatorServerTokens.EOL);

		SimulatorClientStub readerSimulator = new SimulatorClientStub(socket.getOutputStream(), definition.toString());
		readerSimulators.put(readerSimulator.getReaderId(), readerSimulator);
	}

	private void checkReaderSimulators() {
		SimulatorClientStub[] simulators = (SimulatorClientStub[])readerSimulators.values().toArray(new SimulatorClientStub[0]);
		for (int i = 0; i < simulators.length; i++) {
			if (simulators[i].checkConnection() == false) {
				readerSimulators.remove(simulators[i].getReaderId());
			}
		}
	}

	private class RegisterSocket extends Thread {
		private boolean stop = false;

		public void run() {
			super.run();
			while (!stop) {
				try {
					// wait for connection
					Socket socket = serverSocket.accept();
					register(socket);
				} catch (IOException e) {
					if (!stop) e.printStackTrace();
				}
			}
		}

		public void exitLoop() {
			try {
				stop = true;
				serverSocket.close();
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new SimulatorServerController("/props/SimulatorServerController.xml",
         "/props/SimulatorServerController_default.xml");
	}
}
