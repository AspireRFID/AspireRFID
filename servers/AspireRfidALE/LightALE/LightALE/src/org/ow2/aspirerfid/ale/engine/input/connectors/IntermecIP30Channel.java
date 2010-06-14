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


import java.util.StringTokenizer;

import org.ow2.aspirerfid.ale.codec.EpcCodec;
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


import com.intermec.datacollection.rfid.BasicBRIReader;
import com.intermec.datacollection.rfid.BasicReaderException;

public class IntermecIP30Channel extends RPChannel{
	
	/**
	 * Specified Start Trigger
	 */
	private SGLN96Trigger sglnSpecTrigger;
	
	/**
	 * Received Start Trigger
	 */
	private String sglnTrigger = null;
			
	/**
	 * General purpose string buffer for appending strings
	 */
	private StringBuffer sBuffer;
	
	/**
	 * A codec for decoding sgln96 tag. For start Trigger.
	 */
	private final static Sgln96Codec sgln96Codec = new Sgln96Codec();
	
	/**
	 * Current received tags
	 */
	private Tag[] rxTags = null;
	
	/**
	 * Next tag index to read.
	 */
	private int next = 0;
		
	/**
	 * Basic Bri Reader instance.
	 */
	private BasicBRIReader myReader;
	
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
	private IcomLRPropSetter updateConfigHandle;
	
	/**
	 * Constructor
	 */
	public IntermecIP30Channel() {
		super();
        
		// fields
		this.sBuffer = new StringBuffer();
		this.myReader = new BasicBRIReader();
		this.readerName = IcomLRFactory.READER_NAMES[1];
		
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
		 updateConfigHandle = new IcomLRPropSetter() {
				// initialQ setup
				public void setInitialQ(int initialQ) {
					if ((initialQ >= INITIALQ_MIN_VALUE) && (initialQ <= INITIALQ_MAX_VALUE)) {
						try {
							myReader.execute("ATTRIB INITIALQ=" + initialQ);
						} catch (BasicReaderException e) {
							e.printStackTrace();
						}
					}
					
				}

				// Power setup
				public void setPower(int power, int[] mapping) {
					
					if (power >= POWER_MIN_VALUE && power <= POWER_MAX_VALUE) {
						try {
							myReader.execute("ATTRIB FIELDSTRENGTH=" + mapping[power]);
						} catch (BasicReaderException e) {
							e.printStackTrace();
						}
					}
					
				}

				// Session setup
				public void setSession(int session) {
					
					if (session >= SESSION_MIN_VALUE && session <= SESSION_MAX_VALUE) {
						try {
							myReader.execute("ATTRIB SESSION=" + session);
						} catch (BasicReaderException e) {
							e.printStackTrace();
						}
					}
				}
		};
		
	}

	public void clean() {
        System.out.println("Disconnecting");
        try {
			myReader.close();
		} catch (BasicReaderException e) {
			e.printStackTrace();
		}
        System.out.println("Disconnected");
        
		// Reset flag
        connected = false;
        
        // Kill trigger Launcher
        if (isDefaultTriggerLauncherInstalled)
        	super.killDefaultTriggerLauncher();
		
	}

	public ECInitiationCondition getInitCondition() {
		return ECInitiationCondition.TRIGGER;
	}

	public ECTrigger getInitTrigger() {
		sBuffer.setLength(0);
		return new ECTrigger(TriggerType.buildTriggerURI(sBuffer, TriggerType.TAG_TRIGGER, this.sglnTrigger));
	}

	public ECTerminationCondition getTermCondition() {
		return ECTerminationCondition.TRIGGER;
	}

	public ECTrigger getTermTrigger() {
		sBuffer.setLength(0);
		return new ECTrigger(TriggerType.buildTriggerURI(sBuffer, TriggerType.END_TRIGGER, null));
	}

	public boolean hasTags() {
		return (this.rxTags != null); // we have tags if inventory have been done and all tags were read
	}

	/**
	 * Initialise the reader device (Low Level) communication.
	 * 
	 * @param address Communication address with the reader.
	 */
	public void initReaderDevice() throws Exception{
		/* Init connection with Reader */
		myReader.open();
		
		/* Set connected flag */
		connected = true;
	}
	
	/**
	 * Set up the acquisition layer.
	 * @param sglnStartTrigger The sgln start trigger for starting tag reporting.
	 * @param filterSpec filtering spec. May be null if no filtering.
	 */
	public void initAcquisition(ECTrigger sglnStartTrigger, ECFilterSpec filterSpec) {
		
		// decode start trigger		
		if (sglnStartTrigger != null) {
			this.sglnSpecTrigger = super.decodeStartTrigger(sglnStartTrigger);
			if (this.sglnSpecTrigger == null)
				throw new IllegalArgumentException("Invalid sglnStartTrigger object : " + sglnStartTrigger);
		}

		// Take into account filtering patterns
		if (filterSpec != null) {
			super.filteringEngine.addPAtterns(filterSpec);
		}
	}
	
//	public void init(String readerName, ECSpec spec) {
//		String sgln96Uri;
//		StringTokenizer tokenizer;
//		
//		if (spec!= null) {
//			/* Set up sgln trigger from spec */
//			//sgln96Uri = "urn:epc:tag:sgln-96:1.211298.070875.43981";
//			sgln96Uri = spec.getBoundarySpec().getStartTriggerList()[0].getTriggerData();
//			
//			tokenizer = new StringTokenizer(sgln96Uri.substring(sgln96Uri.lastIndexOf(':')+1), ".");
//			if (tokenizer.countTokens()!=4)
//				throw new IllegalArgumentException("invalid uri '" + sgln96Uri +"'");
//			
//			this.sglnSpecTrigger = new SGLN96Trigger(Byte.parseByte(tokenizer.nextToken()), 
//													 Long.parseLong(tokenizer.nextToken()), 
//													 Integer.parseInt(tokenizer.nextToken()), 
//													 Long.parseLong(tokenizer.nextToken()));	
//		} else {			
//			// connect and config
//			try {
//				myReader.open();
//			} catch (BasicReaderException e) {
//				e.printStackTrace();
//			}
//		}	
//	}
	
	public String getReaderName() {
		return this.readerName;
	}

	public boolean isDone() {
		return this.stopTrigger.getFlag();
	}

	public Tag nextTag() {
		Tag ret;
		
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
		
		if (this.sglnSpecTrigger!= null) {
	        // poll sgln trigger : this is done by the real RP too
			if (this.sglnTrigger == null) // only once
				this.rxTags = waitSglnTrigger();
		}
		
		if (!this.stopTrigger.getFlag()) {
			if (this.rxTags == null) {
				// poll for tags
				while ((this.rxTags == null) && !isDone())
					this.rxTags = this.inventory(); // blocking call
				
				if (this.rxTags == null) { 
					// polling aborted due to stop trigger and no tags were received
					return null;
				} else {
					// return first one
					ret = this.rxTags[0];
					next = 1;
				}
			}
			else {
				ret = this.rxTags[next];
				next++;
			}
			// update index one inventory cycle is complete
			if (next >= this.rxTags.length) {
				// all tags were delivered
				this.rxTags = null;
			}
			// dump
			//dumpTag(ret);
		} else {
			ret = null;
		}
		
		return ret;
	}

	public void start() {
		
		/* Set up reader connection if necessary */
		if (!connected) {
			try {
				initReaderDevice();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/* Configure reader if necessary */
		super.readerconfig.reconfigure(this.updateConfigHandle);
		
		// Init RX buffer
		this.rxTags = null;
		
		// launch stop trigger 
		if (isDefaultTriggerLauncherInstalled)
			super.startDefaultTriggerLauncher();		
	}
	
	/**
	 * Factory inventory command.
	 * @param mySource
	 * @return
	 */
	private Tag[] inventory() {
		String sResponse = null;
		Tag[] tags = null;
		
		// inventory of observed tags (only ids)
		try {			
			sResponse = this.myReader.execute("RD WHERE HEX(1:4,1)=H30 OR HEX(1:4,1)=H32");
			//System.out.println("inventory response : \n" + sResponse);
		} catch (BasicReaderException e) {
			e.printStackTrace();
		}
		if ((sResponse != null) && !sResponse.startsWith("NOTAG")) {
			tags = decodeResponse(sResponse);
		}
		else {
			tags = null;
		}
		
		return tags;		
	}
	
	/**
	 * Blocks until any sgln tag is observed.
	 */
	private Tag[] waitSglnTrigger() {
		String sResponse; // received tags
		Tag[] tags = null;
		
		while (this.sglnTrigger == null) {
			try {
				sResponse = myReader.execute("RD WHERE HEX(1:4,1)=H32");
				
				if ((sResponse == null) || sResponse.startsWith("NOTAG"))
					continue;
				System.out.println("waitSglnTrigger response : \n" + sResponse);
				tags = decodeResponse(sResponse);
				
				// glue to fit contract
				for (int i = 0 ; i < tags.length; i++) {
					// check if an sgln is received
					if (EpcCodec.SGLN_96_HEADER == EpcCodec.decodeHeader(tags[i].getId())) {
						// decode and match
						sgln96Codec.decodeTagData(tags[i].getId());
						//System.out.println("sgln : " + sgln96Codec.getTagUri());
						//System.out.println("Trigger : " + this.sglnSpecTrigger.toString());
						if (this.sglnSpecTrigger.matches(sgln96Codec)) {
							this.sglnTrigger = sgln96Codec.getTagUri();
							break;
						}
					}
				}			
			} catch (BasicReaderException e) {
				System.out.println("waitSglnTrigger exception : " + e);
				e.printStackTrace();
				//break;
			}
		}
		
		return tags;
		
	}
	
	private Tag[] decodeResponse(String sResponse) {
		StringTokenizer tokenizer = new StringTokenizer(sResponse, "\r\n");
		Tag[] tags = null;
		int numTags ;
		
		// if not Error or no tags, there is one id per line
		numTags = tokenizer.countTokens() -1 ;
		if (numTags > 0) {
			tags = new Tag[numTags];
			for (int i = 0 ; i < numTags ; i++) {
				tags[i] = new Tag();
				tags[i].setId(adaptData(tokenizer.nextToken()));
				//tags[i].setTimestamp(System.currentTimeMillis());
				tags[i].setTimestamp(super.timing.getTime());
			}
		}
		
		return tags;
	}

	/**
	 * Glue method. Swaps for endianess, and only retrieves the id
	 * @param inData
	 * @return outId 12 bytes ordered in little endian.
	 */
	 private static byte[] adaptData(String line) {
		 byte[] outId;
		
		outId = new byte[12];
		for (int i =  outId.length - 1, j = 1 /* drop the H */; i >= 0; i--, j+=2) {
			outId[i] = Integer.decode("0x"+line.substring(j,j+2)).byteValue();
		}
				 
		return outId;
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
