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

/*
 * Created on 2005
 */
package org.ow2.aspirerfid.reader.rp.hal.impl.sim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;

import org.ow2.aspirerfid.reader.rp.impl.hal.HardwareException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a simple imlementation of a SimulatorEngine.
 * 
 * With a command line based dialog tags can wirtually moved and removed at runtime.
 * The CmdLineSim supports all command provided by the SimulatorController
 * 
 * @author Roland Schneider
 */

public class CmdLineSim implements SimulatorEngine, Runnable {
	
	/** the logger */
	private static Log log = LogFactory.getLog(CmdLineSim.class);
	
	/** the input stream */
	private BufferedReader cmdLineIn;

	/**
	 * @uml.property name="controller"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private SimulatorController controller;
	
	/** the properties file */
	private String propFile;
   private String defaultPropFile = "/props/CmdLineSim_default.xml";
	
	private Thread consoleThread;

	/*
	 * default constructor
	 */
	public CmdLineSim()
	{}

	/**
     * implements the initialize method of the SimulatorEngine
     * 
     * @param controller
     * @param propFile (not used)
     */
	public void initialize(SimulatorController controller, String propFile) {
		this.controller = controller;
		this.propFile = propFile;
		
		consoleThread= new Thread(this);
		consoleThread.start();
	}
	
	public void run(){
		waitCommandLine();
	}
	
	private void waitCommandLine(){
		try {
			cmdLineIn = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				getInputString("> ");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void getInputString ( String prompt ) throws IOException {
		System.out.print( prompt );
		String cmd = cmdLineIn.readLine();
		
		if (cmd.equalsIgnoreCase("add")){
			System.out.print("Source ID> ");
			String sourceId = cmdLineIn.readLine();
			System.out.print("Tag ID> ");
			String tagId = cmdLineIn.readLine();
			if (controller.add(sourceId, tagId)){
				System.out.println("OK.");
			} else{
				System.out.println("Failed.");
			}
		}
		else if (cmd.equalsIgnoreCase("sourceids")){
			String[] sourceIds = null;
			sourceIds = controller.getReadPointNames();
			for (int i = 0 ; i<sourceIds.length; i++){
				System.out.println(sourceIds[i]);
			}
		}
		else if (cmd.equalsIgnoreCase("remove")){
			System.out.print("Source ID> ");
			String sourceId = cmdLineIn.readLine();
			System.out.print("Tag ID> ");
			String tagId = cmdLineIn.readLine();
			if (controller.remove(sourceId, tagId)){
				System.out.println("OK.");
			}
			else{
				System.out.println("Failed.");
			}
		}
		else if (cmd.equalsIgnoreCase("contains")){
			System.out.print("Source ID> ");
			String sourceId = cmdLineIn.readLine();
			System.out.print("Tag ID> ");
			String tagId = cmdLineIn.readLine();
			if (controller.contains(sourceId, tagId)){
				System.out.println("Yes.");
			}
			else{
				System.out.println("No.");
			}
		}
		else if (cmd.equalsIgnoreCase("getall")){
			System.out.print("Source ID> ");
			String sourceId = cmdLineIn.readLine();
			
			HashSet s = (HashSet)controller.getTagsFromReadPoint(sourceId);
			Iterator it = s.iterator();
			while(it.hasNext()){
				System.out.println(((Tag)it.next()).getTagID());
			}
		}
		else if (cmd.equalsIgnoreCase("exit")){
			System.exit(0);
		}
		else {
			log.error("Unknown Command");
			log.info("Usage: Available commmands:");
			log.info(" sourceids, add, remove, contains, getall");
		}
	}
}
