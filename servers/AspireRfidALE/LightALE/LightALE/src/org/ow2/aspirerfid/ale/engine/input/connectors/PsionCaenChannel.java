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

import gnu.javax.comm.wce.WCECommDriver;

import com.caen.RFIDLibrary.CAENRFIDException;
import com.caen.RFIDLibrary.CAENRFIDLogicalSource;
import com.caen.RFIDLibrary.CAENRFIDPort;
import com.caen.RFIDLibrary.CAENRFIDProtocol;
import com.caen.RFIDLibrary.CAENRFIDReader;
import com.caen.RFIDLibrary.CAENRFIDTag;

/**
 * Psion CAEN connector.
 * 
 * @author rdagher
 *
 */
public class PsionCaenChannel extends RPChannel{
	
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
	 * CAEN RFID reader instance.
	 */
	private CAENRFIDReader myReader;
	
	/**
	 * Source for reception.
	 */
	private CAENRFIDLogicalSource mySource;
	
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
	 * Bench flag. If set, the channel will be in DONE state after a complete inventory
	 * cycle is done.
	 * If true, the duration time of the immediate call will be the totalMilliseconds 
	 * field of the header.
	 */
	private boolean bench = false;
	
	public PsionCaenChannel(boolean bench) {
		this();
		this.bench = bench ;
	}
	
	/**
	 * Constructor
	 */
	public PsionCaenChannel() {
		
		super();
		sBuffer = new StringBuffer();
		this.readerName = IcomLRFactory.READER_NAMES[0];
        
		// Load com library
		System.loadLibrary("javaxcomm");
		
		// fields
		this.myReader = new CAENRFIDReader();
		this.mySource = myReader.GetSources()[0];
		System.out.println("Using source : " + mySource.GetName());
		
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
		 
		 updateConfigHandle = new IcomLRPropSetter() {
				// Session setup
				public void setSession(int session) {
					System.err.println(this.getClass() + "\n setSession operation not supported.");	
					// TODO
					// No action : SetSession_EPC_C1G2 and CAENRFIDLogicalSourceConstants missing in CAENRFIDLIb.jar.
					return;
					/*
					if (session >= SESSION_MIN_VALUE && session <= SESSION_MAX_VALUE) {
						try {
							switch (session) {
								case 0 : 
									mySource.SetSession_EPC_C1G2(CAENRFIDLogicalSourceConstants.EPC_C1G2_SESSION_S0);
									break;
								case 1:
									mySource.SetSession_EPC_C1G2(CAENRFIDLogicalSourceConstants.EPC_C1G2_SESSION_S1);
									break;
								case 2 :
									mySource.SetSession_EPC_C1G2(CAENRFIDLogicalSourceConstants.EPC_C1G2_SESSION_S2);
									break;
								case 3 :
									mySource.SetSession_EPC_C1G2(CAENRFIDLogicalSourceConstants.EPC_C1G2_SESSION_S3);
									break;
								default : 
										; // no action
							}
						} catch (CAENRFIDException e) {
							e.printStackTrace();
						}
					}
					*/			
				}
				
				// Power setup
				public void setPower(int power, int[] mapping) {
					if (power >= POWER_MIN_VALUE && power <= POWER_MAX_VALUE) {
						try {
							myReader.SetPower(mapping[power]);
							System.out.println("Expected Power = " + mapping[power]);
							System.out.println("Power = " + myReader.GetPower());
						} catch (CAENRFIDException e) {
							e.printStackTrace();
						}
					}			
				}
				
				// Initial Q setup
				public void setInitialQ(int initialQ) {
					// TODO
					// No action : SetQ_EPC_C1G2 missing in CAENRFIDLIb.jar.
					System.err.println(this.getClass() + "\n setSession operation not supported.");	
					return;	
					/*
					if ((initialQ >= INITIALQ_MIN_VALUE) && (initialQ <= INITIALQ_MAX_VALUE)) {
						try {
							this.mySource.SetQ_EPC_C1G2(initialQ);
						} catch (CAENRFIDException e) {
							e.printStackTrace();
						}
					}
					*/
				}
			};
		

	}

	public void clean() {
        System.out.println("Disconnecting");
        try {
			myReader.Disconnect();
		} catch (CAENRFIDException e) {
			e.printStackTrace();
		} 
		finally {
			// power down CAEN reader
			WCECommDriver.powerDownCaenReader();
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
	public void initReaderDevice() throws Exception {
		/* Set up Reader */
		// power up CAEN reader
		WCECommDriver.powerUpCaenReader();
		try {
			Thread.sleep(300); // sleep for reader to power up
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		// connect and config
        /* Connect to reader on COM1 */
        System.out.println("Connecting to reader : " + "RS232 : COM1");
        myReader.Connect(CAENRFIDPort.CAENRFID_RS232, "COM1:");
        System.out.println("Connection Success");
        
        /* configure Reader */
        // set protocol  : default is CAENRFID_EPC_C1G2
        myReader.SetProtocol(CAENRFIDProtocol.CAENRFID_EPC_C1G2);
        // set bitRate
        //System.out.println("Setting BitRate");
        //myReader.SetBitRate(CAENRFIDBitRate.TX10RX40);
        //myReader.SetBitRate(CAENRFIDBitRate.TX40RX40);
        //myReader.SetBitRate(CAENRFIDBitRate.TX40RX160);
        //System.out.println("SetBitRate OK");
        // set RF regulation
        //myReader.SetRFRegulation(CAENRFIDRFRegulations.ETSI_302208);
        myReader.Disconnect();
        myReader.Connect(CAENRFIDPort.CAENRFID_RS232, "COM1:");
        
		// Reset flag
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
//			/* Set up Reader */
//			// power up CAEN reader
//			WCECommDriver.powerUpCaenReader();
//			try {
//				Thread.sleep(300); // sleep for reader to power up
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
//			
//			// connect and config
//			try {
//		        /* Connect to reader on COM1 */
//		        System.out.println("Connecting to reader : " + "RS232 : COM1");
//		        myReader.Connect(CAENRFIDPort.CAENRFID_RS232, "COM1:");
//		        System.out.println("Connection Success");
//		        
//		        /* configure Reader */
//		        // set protocol  : default is CAENRFID_EPC_C1G2
//		        myReader.SetProtocol(CAENRFIDProtocol.CAENRFID_EPC_C1G2);
//		        // set bitRate
//		        //System.out.println("Setting BitRate");
//		        //myReader.SetBitRate(CAENRFIDBitRate.TX10RX40);
//		        //myReader.SetBitRate(CAENRFIDBitRate.TX40RX40);
//		        //myReader.SetBitRate(CAENRFIDBitRate.TX40RX160);
//		        //System.out.println("SetBitRate OK");
//		        // set RF regulation
//		        //myReader.SetRFRegulation(CAENRFIDRFRegulations.ETSI_302208);
//		        myReader.Disconnect();
//		        myReader.Connect(CAENRFIDPort.CAENRFID_RS232, "COM1:");
//				
//			} catch (CAENRFIDException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		if (readerName != null)
//			this.readerName = readerName;
//	}

	public String getReaderName() {
		return this.readerName;
	}
	
	public boolean isDone() {
		boolean ret;
		
		//return !hasTags(); // if no more tags, quit
		if (bench) {
			// one read cycle
			ret = !hasTags();
		} 
		else {
			ret = this.stopTrigger.getFlag();
		}
		return ret;
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
					this.rxTags = this.inventory(this.mySource); // blocking call
				
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
	private Tag[] inventory(CAENRFIDLogicalSource mySource) {
		CAENRFIDTag[] myRFIDTags = null;
		Tag[] tags = null;
		int numTags;
				
		// inventory of observed tags (only ids)
		try {			
			myRFIDTags = mySource.InventoryTag();
		} catch (CAENRFIDException e) {
			e.printStackTrace();
		}
		if (myRFIDTags != null) {
		
			// glue to fit contract
			numTags = myRFIDTags.length;
			tags = new Tag[numTags];
			for (int i = 0 ; i < numTags; i++) {
				// create Tag
				tags[i] = new Tag();
				// Adapt data
				tags[i].setId(adaptData(myRFIDTags[i].GetId()));
				// Time stamp : when seen by ALE not by CAEN reader, for that a channel is necessary
				//tags[i].setTimestamp(System.currentTimeMillis());
				tags[i].setTimestamp(super.timing.getTime());
			}
		} else {
			tags = null;
		}
		return tags;		
	}
	
	/**
	 * Blocks until any sgln tag is observed.
	 */
	private Tag[] waitSglnTrigger() {
		CAENRFIDTag[] myRFIDTags; // received tags
		byte[] tagId;
		Tag[] tags = null;
		
		if (!bench)
			System.out.println("Waiting for start trigger : ");
		//System.out.println(this.sglnSpecTrigger);
		
		while (this.sglnTrigger == null) {
			try {
				myRFIDTags = mySource.InventoryTag(); // blocking call
				
				if (myRFIDTags == null)
					continue;
				
				// glue to fit contract
				for (int i = 0 ; i < myRFIDTags.length; i++) {
					// Adapt data, 
					tagId = adaptData(myRFIDTags[i].GetId());
					// check if an sgln is received
					if (EpcCodec.SGLN_96_HEADER == EpcCodec.decodeHeader(tagId)) {
						// decode and match
						sgln96Codec.decodeTagData(tagId);
						System.out.println("sgln : " + sgln96Codec.getTagUri());
						System.out.println("Trigger : " + this.sglnSpecTrigger.toString());
						if (this.sglnSpecTrigger.matches(sgln96Codec)) {
							this.sglnTrigger = sgln96Codec.getTagUri();
							// copy tags starting from sgln trigger included
							int numTags = myRFIDTags.length-i;
							tags = new Tag[numTags];
							for (int j = 0 ; j < numTags; j++) {
								// create Tag
								tags[j] = new Tag();
								// Adapt data
								tags[j].setId(adaptData(myRFIDTags[j+i].GetId()));
								// Time stamp : when seen by ALE not by CAEN reader, for that a channel is necessary
								//tags[i].setTimestamp(System.currentTimeMillis());
								tags[i].setTimestamp(super.timing.getTime());
							}
							break;
						}
					}
				}			
			} catch (CAENRFIDException e) {
				System.out.println("waitSglnTrigger error ");
				e.printStackTrace();
				break;
			}
		}
		if (!bench) {
			System.out.println("Received Sgln : \n" + this.sglnTrigger);
			System.out.println("raw : " + sgln96Codec.getRawHexUri());
			for (int j = 0 ; j < tags.length; j++) {
				EpcCodec.convertToRawHexUri(tags[j].getId());
			}
		}
		return tags;
		
	}
	
	/**
	 * Glue method. Swaps for endianess, and only retrieves the id
	 * @param inData
	 * @return outId 12 bytes ordered in little endian.
	 */
	 private static byte[] adaptData(byte[] inData) {
		 byte[] outId;
		 
		 outId = new byte[12];
		 for (int i = 0 ; i < 12 ; i++) {
			 outId[i] = inData[11-i];
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
