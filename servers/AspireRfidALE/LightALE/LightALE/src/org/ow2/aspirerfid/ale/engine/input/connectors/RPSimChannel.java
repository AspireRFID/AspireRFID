/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package org.ow2.aspirerfid.ale.engine.input.connectors;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ow2.aspirerfid.ale.codec.Sgln96Codec;
import org.ow2.aspirerfid.ale.engine.input.RPChannel;
import org.ow2.aspirerfid.ale.engine.input.Tag;
import org.ow2.aspirerfid.ale.engine.lr.IcomLRFactory;
import org.ow2.aspirerfid.ale.engine.lr.IcomLRPropSetter;
import org.ow2.aspirerfid.ale.engine.triggers.EventServer;
import org.ow2.aspirerfid.ale.engine.triggers.TriggerEvent;
import org.ow2.aspirerfid.ale.engine.triggers.TriggerEventListener;
import org.ow2.aspirerfid.ale.epc.reports.ECInitiationCondition;
import org.ow2.aspirerfid.ale.epc.reports.ECTerminationCondition;
import org.ow2.aspirerfid.ale.epc.spec.ECFilterSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECTrigger;
import org.ow2.aspirerfid.ale.epc.spec.TriggerType;


public class RPSimChannel extends RPChannel {
	
	/**
	 * Bench flag. If set, the channel will be in DONE state after all the tags of 
	 * the HashMap are read.
	 * If true, the duration time of the immediate call will be the totalMilliseconds 
	 * field of the header.
	 */
	private boolean bench = false;
	
	/**
	 * Start Trigger
	 */
	private String sglnTrigger = "urn:epc:tag:sgln-96:2.0012345.00003" ; 
	
	/**
	 * A map of received tags.
	 */
	private List tags;
	
	/**
	 * Iterator on tag Map.
	 */
	private Iterator iter = null;
	
	/**
	 * Reusable Tag wrapper for communicating tag to ALE.
	 */
	private Tag aTag;
		
	/**
	 * General purpose string buffer for appending strings
	 */
	private StringBuffer sBuffer = new StringBuffer();
	
	/**
	 * Start trigger Condition semaphore.
	 */
	private Semaphore startTrigger;
	
	/**
	 * Stop trigger Condition semaphore.
	 */
	private Semaphore stopTrigger;
	
	/**
	 * Stop pause Condition semaphore.
	 */
	private Semaphore pauseTrigger;
	
	/**
	 * The duration of pause time.
	 * The time between pause fire and pause release events.
	 */
	private long pauseTime;
	
	/**
	 * Reader's Name.
	 */
	private String readerName;
	
	/**
	 * Reader Channel configuration handle.
	 */
	private IcomLRPropSetter upateConfigHandle;
	
	/**
	 * 
	 * Constructor
	 * @param bench
	 */
	public RPSimChannel(boolean bench) {
		this();
		this.bench = bench ;
	}
	/**
	 * 
	 * Constructor
	 */
	public RPSimChannel() {
		super();
		
		// current tag
		aTag = new Tag();
				
		// init tags
        tags = new ArrayList();
        
        this.readerName = IcomLRFactory.READER_NAMES[2];

        // init start trigger semaphore
        startTrigger = new Semaphore();
        startTrigger.setFlag(false);
        EventServer.getInstance().addEventListner(
       		 EventServer.START_TRIGGER, 
       		 new TriggerEventListener() {
					// set flag upon notification
					public void triggerNotify(TriggerEvent evt) {
						startTrigger.signal(true);
					}
				});
        
		// init stop trigger semaphore
        stopTrigger = new Semaphore();
        stopTrigger.setFlag(false);
		EventServer.getInstance().addEventListner(
			 EventServer.END_TRIGGER, 
			 new TriggerEventListener() {
				// set flag upon notification
				public void triggerNotify(TriggerEvent evt) {
					stopTrigger.signal(true);						
				}
			});        
		
		 // init pause trigger semaphore
		 pauseTrigger = new Semaphore();
		 pauseTrigger.setFlag(false);
		 EventServer.getInstance().addEventListner(
				 EventServer.PAUSE_TRIGGER, 
				 new TriggerEventListener() {
					 // set flag upon notification
					 public void triggerNotify(TriggerEvent evt) {
						 Boolean evtData = (Boolean)evt.getEvtData();
						 pauseTrigger.signal((evtData == Boolean.TRUE));						
				}
			});
		 
		 // set up reader channel configuration handler :
		 upateConfigHandle = new IcomLRPropSetter() {
			// Session setup
			public void setSession(int session) {
				System.out.println("Setting session to " + session);			
			}
			
			// Power setup
			public void setPower(int power, int[] mapping) {
				System.out.println("Setting Initial power to " + power);
				System.out.println("Power Mapping :");
				if (mapping == null)
					System.out.println("null");
				else {
					for(int i=0 ; i < mapping.length; i++) {
						System.out.println("mapping[" + i + "] = " + mapping[i]);
					}
				}
			}
			
			// Initial Q setup
			public void setInitialQ(int initialQ) {
				System.out.println("Setting Initial Q to " + initialQ);			
			}
		};
	}
		
	/**
	 * 
	 * Constructor
	 * @param sgln
	 */
	public RPSimChannel(String sgln) {
		this();
		this.sglnTrigger = sgln;
	}
	
	/**
	 * 
	 * Constructor
	 * @param sgln
	 */
	public RPSimChannel(String sgln, boolean bench) {
		this(bench);
		this.sglnTrigger = sgln;
	}
	
	private final static Sgln96Codec sgln96Codec = new Sgln96Codec();
	/**
	 * Init a Hash Map with tags.
	 * @param tags
	 * @param fileName
	 * @return the sgln of the raw. First sgln in the stream.
	 */
	private static String initTags(List tags, String fileName) {
		BufferedReader input = null;
		String sgln = null;
		
        // try reading simu tags from file
        try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		} catch (Exception e) {
			input = null;
		}
		
		if (input == null) {
			// first tag always Sgln
	        tags.add(new Item("Sgln Rayon",  	"3238CE588229B6000000ABCD"));
	        sgln96Codec.decodeTagData(stringToArrayOfBytes("3238CE588229B6000000ABCD"));
	        sgln = sgln96Codec.getTagUri();
	        
	        tags.add(new Item("FITNESS POCKET",  "30395DFA80E587C000000001"));
	        tags.add(new Item("BOIS EXIA GRAPH", "30395DFA80BD284000000003"));
	        tags.add(new Item("FER UNITE EXIA",  "30395DFA80BD1F8000000001"));
	        tags.add(new Item("TS EQUAREA",      "30395DFA80B3894000000001"));
	        tags.add(new Item("ARTENGO 710 P",   "30395DFA80A49E8000000001"));
	        tags.add(new Item("GEL SHAMPOING/D", "30395DFA8080828000000001"));
	        tags.add(new Item("TS FORCLAZ 50 R", "30395DFA80E1060000000001"));
	        tags.add(new Item("POL FORCLAZ 500", "30395DFA80E2BEC000000001"));
	        tags.add(new Item("ARTENGO 100W PO", "30395DFA80D6698000000001"));
	        tags.add(new Item("POL FORCLAZ 50",  "30395DFA80CBCE8000000001"));
	        tags.add(new Item("ARTENGO 920 X 4", "30395DFA80CD1B0000000001"));
	        tags.add(new Item("R300 LOW NOIR V", "30395DFA8096E8C000000001"));
	        tags.add(new Item("ARTENGO  451 LA", "30395DFA80C23E0000000001"));
	        tags.add(new Item("6 BARRES CEREAL", "30395DFA8067B50000000001"));
	        tags.add(new Item("ARC STARTECH AD", "30395DFA808AF98000000001"));
	        tags.add(new Item("ARTENGO 100W SH", "30395DFA808AF98000000001"));
	        tags.add(new Item("FLECHE CARBONE",  "30395DFA806C90C000000001"));
	        tags.add(new Item("SYNKRON NOIRE",   "30395DFA80A69DC000000001"));
	        tags.add(new Item("BALLES EXIA",     "30395DFA809B0EC000000001"));
	        tags.add(new Item("LOT 3 ANTIVOLS",  "30395DFA8037C84000000001"));
	        tags.add(new Item("BALLON  R300",    "30395DFA804042C000000001"));
	        tags.add(new Item("Mini Spray IP30", "30395DFA80B389400000000B"));
	       
	        //tags.put("Invalide", "30FF5DFA80B389400000000B");
		} else {
			String line;
			boolean sglnFound = false;
			int i = 0;
			
			// init map accordingly
			try {
				while ((line = input.readLine()) != null) {
					// ignore comments
					if (line.startsWith("#")) continue;
					// ignore empty lines
					if (line.length() == 0) continue;
					
					String key;
					// make sure that at least one sgln-96 is detected for special key 
					if (line.startsWith("sgln-96") && !sglnFound) {
						key = "Sgln Rayon"; // special key
						sglnFound = true;
				        sgln96Codec.decodeTagData(stringToArrayOfBytes(line.substring(line.indexOf(':')+1)));
				        sgln = sgln96Codec.getTagUri();
					}
					else
						key = Integer.toString(i++);
					
					tags.add(new Item(key, line.substring(line.indexOf(':')+1)));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// default sgln in case the user did not mention any sgln-96
			if (!sglnFound) {
				tags.add(new Item("Sgln Rayon",  	"3238CE588229B6000000ABCD")); // default sgln
		        sgln96Codec.decodeTagData(stringToArrayOfBytes("3238CE588229B6000000ABCD"));
		        sgln = sgln96Codec.getTagUri();
			}
		}
		// Invalid tag test
		//tags.add(new Item("Invalide", "30FF5DFA80B389400000000B"));
		
		return sgln;
	}
	
	public static byte[] stringToArrayOfBytes (String data) {
		byte[] bytes;
		
		// add a leading zero if necessary
		if ((data.length()%2)!=0)
			data = "0"+data;
		
		bytes = new byte[data.length()/2];
		for (int i =  bytes.length - 1, j = 0; i >= 0; i--, j+=2) {
			bytes[i] = Integer.decode("0x"+data.substring(j,j+2)).byteValue();
		}
		
		return bytes;
	}
	
	public static void dumpByteArray(byte[] bytes) {
		
		System.out.print("[");
		for (int i = 0; i < bytes.length; i++) {
			System.out.print("0x"+Integer.toHexString(bytes[i]&0x00FF) + ((i < (bytes.length-1)) ? "," : "]\n"));
		}
	}
	
	/*
	 * Simulated methods
	 */
	
	public ECInitiationCondition getInitCondition() {
		return ECInitiationCondition.TRIGGER;
	}

	public ECTrigger getInitTrigger() {
		sBuffer.setLength(0);
		return new ECTrigger(TriggerType.buildTriggerURI(sBuffer, TriggerType.TAG_TRIGGER, this.sglnTrigger));
	}

	public ECTerminationCondition getTermCondition() {
		sBuffer.setLength(0);
		return ECTerminationCondition.TRIGGER;
	}

	public ECTrigger getTermTrigger() {
		return new ECTrigger(TriggerType.buildTriggerURI(sBuffer, TriggerType.END_TRIGGER, null));
	}

	/**
	 * Initialise the reader device (Low Level) communication.
	 * 
	 * @param address Communication address with the reader.
	 */
	public void initReaderDevice() {
		// No device
	}
	
	/**
	 * Set up the acquisition layer.
	 * @param sglnStartTrigger The sgln start trigger for starting tag reporting.
	 * @param filterSpec filtering spec. May be null if no filtering.
	 */
	public void initAcquisition(ECTrigger sglnStartTrigger, ECFilterSpec filterSpec) {
		
		// reader init : set up tags
		if (tags != null)
			tags.clear();
		// set sgln trigger from stream. 
		// TODO set it up from spec.
		this.sglnTrigger = initTags(tags,  System.getProperty("user.dir") + "tags.txt");
		iter = tags.iterator();
	
		// Take into account filtering patterns
		if (filterSpec != null) {
			super.filteringEngine.addPAtterns(filterSpec);
		}
	}
	
//	public void init(String readerName, ECSpec spec) {
//		
//		iter = tags.iterator();
//		// reader init : set up tags
//		if (spec != null) {
//			if (tags != null)
//				tags.clear();
//			// set sgln trigger from stream. 
//			this.sglnTrigger = initTags(tags,  System.getProperty("user.dir") + "tags.txt");
//			
//			// get filtering patterns from spec
//			super.filteringEngine.addPAtterns(spec.getReportSpecs()[0].getFilterSpec());
//		}
//		if (readerName != null)
//			this.readerName = IcomLRFactory.READER_NAMES[0];
//	}
	
	public String getReaderName() {
		return this.readerName;
	}

	public boolean isDone() {
		boolean ret = true;
		
		if (bench) {
			// one iteration
			ret = !iter.hasNext();
		}
		else if (!this.stopTrigger.getFlag()) {
			ret = false;
			if (!iter.hasNext()) {
				iter = tags.iterator(); // re-init
				// simulate delay for event cycle
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}
	
	public boolean hasTags() {
		return iter.hasNext();
	}
	
	public Tag nextTag() {
		Item key ;
		boolean keep = false;
		byte[] tagId;
		
		// wait for start if necessary : while (false)
		try {
			this.startTrigger.lock(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
        // wait for pause if necessary : while (true)
		if (this.pauseTrigger.getFlag()) {
			// pause event fired, time stamp the beginning of the pause time.
			this.pauseTime = System.currentTimeMillis();
			
			// sync until pause event is released.
			try {
				this.pauseTrigger.lock(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// compute pause duration
			this.pauseTime = System.currentTimeMillis()-this.pauseTime;
			
			// Accumulate the pause time
			super.timing.shiftOrigin(this.pauseTime);
			
			//System.out.println("Pause Time = " + pauseTime);
		}
		
		if (!this.stopTrigger.getFlag()) {
			//System.err.println("\tpause Passed");
			// read and filter tag
			keep = false;
			do {
				key = (Item)iter.next();
				tagId = stringToArrayOfBytes(key.value);
				keep = super.filteringEngine.filter(tagId);
			} while (!keep && !isDone() && iter.hasNext());
			//
			if (keep) {
				aTag.setId(tagId);
				//aTag.setTimestamp(System.currentTimeMillis());
				aTag.setTimestamp(super.timing.getTime());
				
				// simulate delay : sleep 100 ms
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				aTag = null;
			}
		} else {
			aTag = null;
		}
		
		return aTag;
	}
	
	public void start() {
		// Emulate device Reconfiguration
		super.readerconfig.reconfigure(this.upateConfigHandle);
		
		// no action
		iter = tags.iterator(); // restart iterator
		
		// activate sgln trigger
		aTag.setId(null);
		
		// launch stop trigger
		if (isDefaultTriggerLauncherInstalled)
			super.startDefaultTriggerLauncher();
	}
	
	public void clean() {
		// no action
		iter = null;
		
        // Kill trigger Launcher
		if (isDefaultTriggerLauncherInstalled)
			super.killDefaultTriggerLauncher();
		
		// Reset filtering Engine
		super.filteringEngine.cleanUp();
	}
	
	//////////////////////////////////////////////////////
	// IcomLRPropSetter Implementation : Device rebound //
	/////////////////////////////////////////////////////
	public void setInitialQ(int initialQ) {
		super.readerconfig.setInitialQ(initialQ);
	}

	public void setPower(int power, int[] mapping) {
		super.readerconfig.setPower(power);
		super.readerconfig.setReaderPowerMapping(mapping);
	}

	public void setSession(int session) {
		super.readerconfig.setSession(session);
	}
	
}

class Item {
	
	/**
	 * Constructor
	 * @param key
	 * @param value
	 */
	public Item(String key, String value) {
		this.key = key;
		this.value = value;
	}
	String key;
	String value;
}