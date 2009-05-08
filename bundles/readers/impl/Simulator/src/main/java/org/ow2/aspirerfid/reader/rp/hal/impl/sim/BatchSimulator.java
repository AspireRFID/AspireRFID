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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.StringTokenizer;

import org.ow2.aspirerfid.reader.rp.hal.impl.sim.util.ResourceLocator;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class implements a simple event simulator in batch mode.
 *
 * <p>
 * The simulator reads the input from a file and generates RFID events at
 * a certain time stated in the file. Lines starting with '#' are ignored.
 * The next lines contain the events where only one event definition per
 * line is allowed in the following format:</p>
 * 
 * <p><code>reltime;eventtype;antenna;serial;data</code><br>
 * <ul><li><code>reltime</code> is the time the event should happen relative to
 * the event that happend before and for the first event relative to the
 * start of the event simulator in milliseconds.</li>
 * <li><code>eventtype</code> is either <code>EN</code> for a enter event or
 * <code>EX</code> for an exit event.</li>
 * <li><code>antenna</code> is the id of the antenna.</li>
 * <li><code>serial</code> is the serial number of the RFID tag where each
 * byte is separated by a space.</li>
 * <li><code>data</code> the data on the tag or <code>null</code> if the tag
 * contains no data.</li></ul>
 * Spaces before and after an entry are trimmed.</p>
 *
 * <p>The following is an example for a simple event file:<br>
 * <code>
 * 1000; EN; channel1; 28 97 0B 01 00 00 00 01; null<br>
 *  500; EN; channel1; 3A 90 C0 01 00 00 00 01; null<br>
 * 1000; EX; channel1; 28 97 0B 01 00 00 00 01; null<br>
 *  100; EN; channel1; 20 01 D0 01 00 00 00 01; null<br>
 *  200; EN; channel1; 1B 65 0C 01 00 00 00 01; null<br>
 * 1500; EX; channel1; 1B 65 0C 01 00 00 00 01; null<br>
 *  500; EX; channel1; 20 01 D0 01 00 00 00 01; null<br>
 * </code></p>
 *
 * <p>
 * Which generates the following events:<br>
 * 1 sec after the simulator starts: <code>TAG_ENTERED(28970B0100000001)</code><br>
 * 0.5 sec later: <code>TAG_ENTERED(3A90C00100000001)</code><br>
 * 1 sec later: <code>TAG_EXITED(28970B0100000001)</code><br>
 * 0.1 sec later: <code>TAG_ENTERED(2001D00100000001)</code><br>
 * 0.2 sec later: <code>TAG_ENTERED(1B650C0100000001)</code><br>
 * 1.5 sec later: <code>TAG_EXITED(1B650C0100000001)</code><br>
 * 0.5 sec later: <code>TAG_EXITED(2001D00100000001)</code><br>
 *
 * @version	1.1, April 2005
 * @author Matthias Lampe, lampe@acm.org
 * @author Anna Wojtas, Marcel Bihr, Lukas Blunschi, Remo Egli
 */
public class BatchSimulator  implements SimulatorEngine, Runnable {


	//	-------- Fields -----------------------------------------------------

	/** config file */
	private String configFile = null;
   private String defaultConfigFile = "/props/BatchSimulator_default.xml";
	
    /** RFID tag entered antenna range event. */
    public static final int TAG_ENTERED = 1;

    /** RFID tag exited antenna range event. */
    public static final int TAG_EXITED = 2;
    
	/** the logger */
	private static Log log = LogFactory.getLog(BatchSimulator.class);
	
    /** the event input file */
    protected File eventFile;

    /** states if the event file is open */
    protected boolean isOpen = false;

    /** number of repetitions of event file. 0 for endless */
    protected long cycles = 0;

    /** input reader for event file */
    protected BufferedReader in = null;

    /** main thread */
    protected Thread rfidThread;

    /** states if the thread should stop */
    protected boolean stopRequested;

    /** states if the thread is running */
    protected boolean threadRunning;

    /** SimulatorController objtect */
    private SimulatorController controller;

	//-------- Constructor(s) ---------------------------------------------

 
    /*
     * default constructor used for reflection
     */
    public BatchSimulator() 
    {}

    /**
     * implements the initialize method of the SimulatorEngine
     * 
     * @param controller 
     * @throws RFIDException
     */
    public void initialize(SimulatorController controller, String configFile){
		this.controller = controller;
		this.configFile = configFile;
		try {
			initSimulator();
		} catch (SimulatorException e) {
			log.warn("Not able to initialize the simulator: " + e.getMessage());
			e.printStackTrace();
		}
    }

    /**
     * initializes the fields and starts the simulator.
     *
     * @throws SimulatorException if the event input file could not be opened.
     */
     private void initSimulator() throws SimulatorException {
        // load properties from config file
        XMLConfiguration config = new XMLConfiguration();
        URL fileurl = ResourceLocator.getURL(configFile, defaultConfigFile,
           this.getClass());
        try {
           config.load(fileurl);
        } catch (ConfigurationException ce) {
           throw new SimulatorException("Can not load config file '" + configFile + "'.");
        }
		
		// get properties
      String file = config.getString("batchfile");
      cycles = config.getLong("iterations");
		
      // find batchfile
      URL batchfileurl = ResourceLocator.getURL(file, file, this.getClass());

		try {
         eventFile = new File(batchfileurl.toURI());
      } catch (URISyntaxException e1) {
         throw new SimulatorException("Can not creat URI of batchfile '" + file + "'.");
      }
		rfidThread = null;
		threadRunning = false;
		stopRequested = false;

        try {
            // tries to open file
            FileReader in = new FileReader(eventFile);
            in.close();

            // start simulator thread
            start();
        } catch (IOException e) {
            throw new SimulatorException("Cannot open event file '"+file+"': "+e);
        }
     }

 	//-------- Public methods ---------------------------------------------



 	//-------- Thread Methods ---------------------------------------------

     /**
      * starts the RFID thread.
      *
      * @throws SimulatorException if a RFID hardware exception occurs.
      */

     public synchronized void start() throws SimulatorException {
         if (!threadRunning) {
             threadRunning = true;
             rfidThread = new Thread(this);
             stopRequested = false;

             // Start scanning
             rfidThread.start();
         }
     }

     /**
      * requests the RFID thread to stop.
      */
     public synchronized void stop() {
     	stopRequested = true;
     }


     /**
      * thread main method that reads from the input file and generates the
      * events. If the thread is stopped and started again the input file
      * is read from the beginning again. If the file ends the file is read
      * from the beginning again.
      */
     public void run() {
         try {
             String line;
             String sourceID, timeString, eventTypeName, serial, data;
             long time, timeNotifyStart, timeNotifyEnd;
             Tag tag;
             int eventType = 0;
             long cycle = 0;

             timeNotifyStart = System.currentTimeMillis();

             log.info("Simulator started.");
             while (!stopRequested) {
                 if (cycles != 0 && cycle >= cycles) {
                     stopRequested = true;
                     continue;
                 }
                 int lineNbr = 1;

                 //-- check if file needs to be opened
                 if (!isOpen) {
                     openFile();
                 }

                 //-- read event from file

                 while ( ((line = in.readLine()) != null) && line.charAt(0) == '#') {
                	 lineNbr++;
                 }
                 if (line == null) {
                     closeFile();
                     cycle++;
                     continue;
                 }

                 //-- parse line
                 StringTokenizer st = new StringTokenizer(line, ";");
                 timeString = st.nextToken().trim();
                 eventTypeName = st.nextToken().trim();
                 sourceID = st.nextToken().trim();
                 time = Long.parseLong(timeString);
                 serial = st.nextToken().trim();
                 data = st.nextToken().trim();

                 //-- create RFID tag
                 if (data.equals("null")) {
                    tag = new Tag(serial);
                 } else {
                    tag = new Tag(serial, data.getBytes());
                 }

                 //-- get event type
                 if (BatchSimulatorTokens.ENTER.equals(eventTypeName)) {
                	 eventType = TAG_ENTERED;
                 } else if (BatchSimulatorTokens.EXIT.equals((eventTypeName))) {
                	 eventType = TAG_EXITED;
                 } else {
                	 log.error("Error on line " + lineNbr + ". Second argument must be '" + BatchSimulatorTokens.ENTER + "' or '" + BatchSimulatorTokens.EXIT + "'.");
                	 continue;
                 }

                 timeNotifyEnd = System.currentTimeMillis();
                 long timeActual = time - (timeNotifyEnd-timeNotifyStart);
                 if (timeActual < 0)
                     timeActual = 0;
                 log.info("Simulator event wait time="+time+" ("+timeActual+")");
                 
                 //-- wait for event to happen
                 try {
                     Thread.sleep(timeActual);
                 } catch (InterruptedException e) { }

                 //-- notify controller
                 String type = (eventType==TAG_ENTERED) ? "enter" : "exit";
                 log.info("Simulator: Generate "+type+" event: "+tag.getTagID());

                 timeNotifyStart = System.currentTimeMillis();
                 notify(sourceID, tag, eventType);
             } // end of main loop

             log.info("Simulator stopped.");

             //---- close input readers
             closeFile();

             threadRunning = false;
         } catch (Exception e) {
             log.error("BatchSimulator.run(): Exception e="+e);
             e.printStackTrace();
         }

         return;
     }

    /**
     * notifies the controller about a RFIDEvent.
     *
     * @param source the source that generated the event.
     * @param tag the tag that triggered the event.
     * @param id the RFIDEvent.
     */
     public void notify(String source, Tag tag, int id) {
     	switch(id) {
     	case TAG_ENTERED:
     		if (controller.add(source, tag.getTagID())){
				log.info("Controller notified about new tag entry event");
			}
			else{
				log.warn("Notification about new tag entry failed.");
			}
     	break;
     	case TAG_EXITED:
     		if (controller.remove(source, tag.getTagID())){
     			log.info("Controller notified about new tag exit event");
			}
			else{
				log.warn("Notification about new tag exit failed.");
			}
     	break;
     	}
     }	
    
     protected void openFile() throws IOException {
         if (!isOpen) {
             in = new BufferedReader(new FileReader(eventFile));

//             String line;
//
//             while ( ((line = in.readLine()) != null) && line.charAt(0) == '#');
//             if (line == null)
//                 throw new IOException("File empty.");
//             cycles = Long.parseLong(line);

             isOpen = true;
         }
     }

     protected void closeFile() throws IOException {
         if (isOpen) {
             isOpen = false;
             in.close();
         }
     }

}
