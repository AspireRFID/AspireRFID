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

import java.util.ArrayList;

import org.ow2.aspirerfid.ale.codec.EpcCodec;
import org.ow2.aspirerfid.ale.codec.Sgln96Codec;
import org.ow2.aspirerfid.ale.engine.input.RPChannel;
import org.ow2.aspirerfid.ale.engine.input.Tag;
import org.ow2.aspirerfid.ale.engine.lr.IcomLRPropSetter;
import org.ow2.aspirerfid.ale.engine.triggers.EventServer;
import org.ow2.aspirerfid.ale.engine.triggers.TriggerEvent;
import org.ow2.aspirerfid.ale.engine.triggers.TriggerEventListener;
import org.ow2.aspirerfid.ale.epc.reports.ECInitiationCondition;
import org.ow2.aspirerfid.ale.epc.reports.ECTerminationCondition;
import org.ow2.aspirerfid.ale.epc.spec.ECFilterSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECTrigger;
import org.ow2.aspirerfid.ale.epc.spec.TriggerType;

import pops.rp.api.DataSelector;
import pops.rp.api.RPException;
import pops.rp.api.ReaderDevice;
import pops.rp.api.Source;
import pops.rp.api.readreport.ReadReport;
import pops.rp.imp.factory.ReaderFactory;


/**
 * Connector for RP compliant reader device interface.
 * 
 * @author rdagher
 *
 */
public class RPConnector extends RPChannel {
	
	/**
	 * My abstract reader device.
	 */
	private ReaderDevice myReader;
	
	/**
	 * Reader's source : Singleton.
	 */
	private Source mySource;
	
	/**
	 *  The name of the current source.
	 */	
	private String mySourceName;

	/**
	 * Reader's Data Selector : default singleton for selecting IDs.
	 */
	private DataSelector myDataSelector;
	
	/**
	 * Read Report due to a read cycle.
	 */
	private ReadReport myReadReport;

	/**
	 * Specified Start Trigger.
	 */
	private SGLN96Trigger sglnSpecTrigger;
	
	/**
	 * Received Start Trigger.
	 */
	private String sglnTrigger;
			
	/**
	 * General purpose string buffer for appending strings
	 */
	private StringBuffer sBuffer;
	
	/**
	 * A codec for decoding sgln96 tag. For start Trigger.
	 */
	private final static Sgln96Codec sgln96Codec = new Sgln96Codec();
	
	/**
	 * Current received tags.
	 */
	private Tag[] rxTags;
	
	
	/**
	 * Temporary list for grouping tags after the filtering stage if activated.
	 */
	private ArrayList tagList;
	
	/**
	 * Next tag index to read.
	 */
	private int next;
	
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
	 * My reader's name.
	 */
	private String myReaderName;


	/**
	 * Reader Channel configuration handle.
	 */
	private IcomLRPropSetter updateConfigHandle;
	
	/**
	 * 
	 * Constructor
	 * @param readerName the name of the reader as {@link ReaderFactory}.
	 */
	public RPConnector(ReaderDevice readerDevice, String readerName) {
		super();        
		// fields
		myReader = readerDevice;
		myReaderName = readerName;
		sBuffer = new StringBuffer();
		rxTags = null;
		tagList = new ArrayList(20);
		next = 0;
		
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
			// Session setup
			public void setSession(int session) {
				try {
					ReaderFactory.configReader(myReader, ReaderFactory.ConfigAttributes.SESSION,  new Integer(session));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (RPException e1) {
					e1.printStackTrace();
				}				
			}
			
			// Power setup
			public void setPower(int power, int[] mapping) {
				try {
					ReaderFactory.configReader(myReader, ReaderFactory.ConfigAttributes.POWER, new ReaderFactory.PowerValue(power, mapping));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (RPException e1) {
					e1.printStackTrace();
				}				
			}
			
			// Initial Q setup
			public void setInitialQ(int initialQ) {
				try {
					ReaderFactory.configReader(myReader, ReaderFactory.ConfigAttributes.INITIAL_Q, new Integer(initialQ));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (RPException e1) {
					e1.printStackTrace();
				}				
			}
		};
	}

	public void clean() {
       
		// Disconnect
        try {
        	System.out.println("Disconnecting");
			myReader.goodbye();
			System.out.println("Disconnected");
		} catch (RPException e) {
			e.printStackTrace();
		}
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
		/* Connect to Reader */
		//ReaderFactory.connectReader(this.myReader, "tcp://127.0.0.1");
		ReaderFactory.connectReader(this.myReader); // default address
		mySource = myReader.getCurrentSource();
		myDataSelector = myReader.getCurrentDataSelector();
		myReader.setName(myReaderName);
		
		/* Configure reader */
		super.readerconfig.reconfigure(this.updateConfigHandle);
		
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
			filtering = true;
			super.filteringEngine.addPAtterns(filterSpec);
		} else {
			filtering = false;
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
//			if (sgln96Uri != null) {
//			
//				tokenizer = new StringTokenizer(sgln96Uri.substring(sgln96Uri.lastIndexOf(':')+1), ".");
//				if (tokenizer.countTokens()!=4)
//					throw new IllegalArgumentException("invalid uri '" + sgln96Uri +"'");
//				
//				this.sglnSpecTrigger = new SGLN96Trigger(Byte.parseByte(tokenizer.nextToken()), 
//														 Long.parseLong(tokenizer.nextToken()), 
//														 Integer.parseInt(tokenizer.nextToken()), 
//														 Long.parseLong(tokenizer.nextToken()));
//			}
//		} else {
//			/* Connect to Reader */
//			try {
//				//ReaderFactory.connectReader(this.myReader, "tcp://127.0.0.1");
//				ReaderFactory.connectReader(this.myReader);
//				mySource = myReader.getCurrentSource();
//				myDataSelector = myReader.getCurrentDataSelector();
//			} catch (RPException e) {
//				e.printStackTrace();
//			}			
//		}
//	}
	
	public String getReaderName() {
		String name;
		
		 try {
			 name = myReader.getName();
		} catch (RPException e) {
			name = "";
		}
		
		return name;
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
				if (filtering) {
					while ((this.rxTags == null) && !isDone())
						this.rxTags = this.inventoryFilt(); // blocking call
				} else {
					while ((this.rxTags == null) && !isDone())
						this.rxTags = this.inventoryNoFilt(); // blocking call
				}
				
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
		
		/* set up RX array */
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
	private Tag[] inventoryFilt() {
		Tag[] tags = null;
		String[] tagIds;
		int numTags = 0;
		byte [] tagId;
				
		// inventory of observed tags (only ids)
		// Inventory
		try {
			myReadReport = mySource.rawReadIDs(myDataSelector);
			tagIds = myReadReport.getTagIds(mySourceName);
			if (tagIds != null) {
				tagList.clear();
				numTags = tagIds.length ;
				if (numTags !=0) {
					for (int i = 0 ; i < numTags; i++) {
						// glue to fit contract and add to list	
						tagId = adaptData(tagIds[i]);
						
						// Filter and add to list of observed tags
						// 	Note : Pooling may be useful : but a study has to be done for the pool
						//         size which depends on the max number of tags read in cycle.
						if ((super.filteringEngine.filter(tagId))) {
							//System.out.println("Filter OK");
							tagList.add(new Tag(tagId, super.timing.getTime()) );
						} else {
							//System.out.println("Filter KO : dropping");
						}
					}
					numTags = tagList.size();
				}
			}
		} catch (RPException e) {
			e.printStackTrace();
		}
		
		if (numTags != 0)
			tags = (Tag[]) tagList.toArray(new Tag[numTags]);
		
		return 	tags;	
	}
	
	/**
	 * Factory inventory command.
	 * @param mySource
	 * @return
	 */
	private Tag[] inventoryNoFilt() {
		Tag[] tags = null;
		String[] tagIds;
		int numTags;
		
		// inventory of observed tags (only ids)
		// Inventory
		try {
			//System.out.println("inventoryNoFilt");
			
			myReadReport = mySource.rawReadIDs(myDataSelector);
			tagIds = myReadReport.getTagIds(mySourceName);
			if (tagIds != null) {
				numTags = tagIds.length ;
				if (numTags !=0) {
					tags = new Tag[numTags];
					for (int i = 0 ; i < numTags; i++) {
						// glue to fit contract and add to list
						// Pooling may be useful : but a study has to be done for the pool
						// size which depends on the max number of tags read in cycle.
						tags[i] = new Tag(); 
						tags[i].setId(adaptData(tagIds[i]));
						tags[i].setTimestamp(super.timing.getTime());
					}
				}
			}
		} catch (RPException e) {
			e.printStackTrace();
		}
				
		return tags;		
	}
	
	
	/**
	 * Blocks until any sgln tag is observed.
	 */
	private Tag[] waitSglnTrigger() {
		Tag[] tags = null;
		String[] tagIds;
		byte[] tagId;
		
		while (this.sglnTrigger == null) {
			// Inventory
			try {
				myReadReport = mySource.rawReadIDs(myDataSelector);
			} catch (RPException e) {
				e.printStackTrace();
			}
			// Check
			if (myReadReport != null) {
				tagIds = myReadReport.getTagIds(mySourceName);
				for (int i = 0 ; i < tagIds.length; i++) {
					// glue to fit contract
					tagId = adaptData(tagIds[i]);
					// check if an sgln is received
					if (EpcCodec.SGLN_96_HEADER == EpcCodec.decodeHeader(tagId)) {
						// decode and match
						sgln96Codec.decodeTagData(tagId);
						//System.out.println("sgln : " + sgln96Codec.getTagUri());
						//System.out.println("Trigger : " + this.sglnSpecTrigger.toString());
						if (this.sglnSpecTrigger.matches(sgln96Codec)) {
							this.sglnTrigger = sgln96Codec.getTagUri();
							break;
						}
					} // End if sgln tag
				} // End for each tag Id
			} // End Check for sgln trigger
		} // End While
		
		return tags;
		
	}

	/**
	 * Glue method. Swaps for endianess, and only retrieves the id
	 * @param hexId in big endian.
	 * @return outId 12 bytes ordered in little endian.
	 */
	 private static byte[] adaptData(String hexId) {
		 byte[] outId;
		
		outId = new byte[12];
		for (int i =  outId.length - 1, j = 0 /* drop the H */; i >= 0; i--, j+=2) {
			outId[i] = Integer.decode("0x"+hexId.substring(j,j+2)).byteValue();
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
