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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.ow2.aspirerfid.reader.rp.hal.impl.sim.BatchSimulatorTokens;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.util.ResourceLocator;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * For batch file format see run method.
 *
 * @author regli
 */
public class BatchSimulatorServer extends Thread implements SimulatorServerEngine {

	/** the logger */
	private static final Log LOG = LogFactory.getLog(GraphicSimulatorServer.class);

	/** the simulator controller */
	private SimulatorServerController controller;
	/** batch file path */
	private String batchFile;
	/** number if iterations ( < 0 means endless) */
	private int iterations;

	/**
	 * Initiziale and start BatchSimulatorServer:
	 * <ul><li>set controller</li>
	 * <li>check and load properties from properties file PROPERTIES_FILE_LOCATION</li>
	 * <li>check the batch file location</li></ul>
	 */
	public void initialize(SimulatorServerController controller, String propFile,
         String defaultPropFile) throws SimulatorServerException {
		this.controller = controller;

      // load properties
		XMLConfiguration props;
      URL fileurl = ResourceLocator.getURL(propFile, defaultPropFile, this.getClass());
      try {
         props = new XMLConfiguration(fileurl);
      } catch (ConfigurationException ce) {
         throw new SimulatorServerException("Could not load the properties file.");
      }
      
		// check properties
		if (!props.containsKey("batchfile")) {
			throw new SimulatorServerException("Property 'batchfile' not found.");
		}
		if (!props.containsKey("iterations")) {
			throw new SimulatorServerException("Property 'iterations' not found.");
		}

		// get properties
		batchFile = props.getString("batchfile");
		try {
			iterations = props.getInt("iterations");
		} catch (NumberFormatException e) {
			throw new SimulatorServerException("Property 'iterations' must be a number");
		}

		// check batch file
		if (!new File(batchFile).exists()) {
			throw new SimulatorServerException("Batch file '" + batchFile + "' not found.");
		}

		start();
	}

	/**
	 * <p>Execute one line after the other from the batch file.</p>
	 * Line Format: <code>SLEEPTIME; EVENT_TYPE; READER_ID; ANTENNA_ID; TAG_ID</code>
	 * <ul><li><code>SLEEPTIME</code>: time in ms which the simulator will sleep before execute the corresponding line</li>
	 * <li><code>EVENT_TYPE</code>: <code>EN</code> or <code>EX</code> for an enter or exit event</li>
	 * <li><code>READER_ID</code>: the id of the reader to which the tag will be added or removed.</li>
	 * <li><code>ANTENNA_ID</code>: the id of the antenna to which the tag will be added or removed.</li>
	 * <li><code>TAG_ID</code>: the id of the tag which will be added or removed</li></ul>
	 */
	public void run() {
		LOG.info("BatchSimulatorServer started");
		try {
			int iteration = 1;
			while (iterations < 1 || iteration <= iterations) {
				LOG.info(iteration + ". iteration");

				// load batch file
				BufferedReader batchFileReader = new BufferedReader(new FileReader(batchFile));

				// read first line
				int lineNbr = 0;
				String line = batchFileReader.readLine();

				// iterate until the end of the file is reached
				while (line != null) {
					lineNbr++;

					// parse line
					try {

						// check if the line has 5 arguments
						String[] fragments = line.split(";");
						if (fragments.length != 5) {
							throw new IllegalArgumentException();
						}

						// sleep for the sleep time (1. argument)
						try {
							long sleepTime = Long.parseLong(fragments[0].trim());
							Thread.sleep(sleepTime);
						} catch (NumberFormatException e) {
							throw new SimulatorServerException("Illegal batch file line (" + lineNbr + "). First argument must be a number.");
						}

						// get event type (2. argument)
						String eventType = fragments[1].trim();

						// get reader id (3. argument)
						String readerId = fragments[2].trim();

						// get antenna id (4. argument)
						String antennaId = fragments[3].trim();

						// get tag id (5. argument)
						String tagId = fragments[4].trim();

						// fire event
						if (BatchSimulatorTokens.ENTER.equals(eventType)) {
							LOG.info("add tag '" + tagId + "' to antenna '" + antennaId + "' of reader '" + readerId + "'");
							controller.add(readerId, antennaId, tagId);
						} else if (BatchSimulatorTokens.EXIT.equals(eventType)) {
							LOG.info("remove tag '" + tagId + "' to antenna '" + antennaId + "' of reader '" + readerId + "'");
							controller.remove(readerId, antennaId, tagId);
						} else {
							throw new SimulatorServerException("Illegal batch file line (" + lineNbr + "). Second argument must be '" + BatchSimulatorTokens.ENTER + "' or '" + BatchSimulatorTokens.EXIT + "'.");
						}
					} catch (SimulatorServerException e) {
						LOG.error("[ERROR] " + e.getMessage());
					}

					// read next line
					line = batchFileReader.readLine();
				}

				// close batch file
				batchFileReader.close();
				iteration++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info("end of batch-file reached.");
		controller.stop();
	}
}

