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

package org.ow2.aspirerfid.ale.engine;


import java.util.Date;

import org.ow2.aspirerfid.ale.codec.EpcCodec;
import org.ow2.aspirerfid.ale.engine.collect.Grouping;
import org.ow2.aspirerfid.ale.engine.collect.grouping.Sgln96;
import org.ow2.aspirerfid.ale.engine.collect.grouping.Sgtin96;
import org.ow2.aspirerfid.ale.engine.collect.grouping.UriRepresentation;
import org.ow2.aspirerfid.ale.engine.com.IhmCom;
import org.ow2.aspirerfid.ale.engine.input.RPChannel;
import org.ow2.aspirerfid.ale.engine.input.Tag;
import org.ow2.aspirerfid.ale.epc.reports.ECReport;
import org.ow2.aspirerfid.ale.epc.reports.ECReports;
import org.ow2.aspirerfid.ale.epc.reports.ECTerminationCondition;
import org.ow2.aspirerfid.ale.epc.spec.ECFieldSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECTrigger;
import org.ow2.aspirerfid.ale.epc.spec.TriggerType;


public class IcomAleEngine{
	/**
	 * Reference to current input spec
	 */
	protected ECSpec inputSpec;
	
	/**
	 * Reference to current output spec
	 */
	protected ECReports outputReports;
			
	/**
	 * ALE worker thread for handling the transactions.
	 */
	private AleWorker aleWorkerThread;
	
	/**
	 * Ale Engine singleton.
	 */
	private static IcomAleEngine aleEngine = null;
	
	/**
	 * 
	 * Constructor
	 */
	protected IcomAleEngine() {
		outputReports = new ECReports();
	}
	
	/**
	 * Access to the singleton instance of the ALE engine.
	 * @return the ALE engine singleton.
	 */
	public static IcomAleEngine getEngine() {
		if (aleEngine == null) {
			aleEngine = new IcomAleEngine();
		}
		return aleEngine;
	}
	
	/**
	 * init stage of the engine.
	 * @param rpLink 
	 */
	public void init(IhmCom ihmLink, RPChannel rpLink) {
		boolean status = true ;
		Exception e = null;
		
		try {
			// Init ALE
					
			// Init RP : spec and params
			//rpLink.init(readerName, null);
			rpLink.initReaderDevice();
		
		}
		catch (Exception ex) {
			e = ex;
			status = false;
		}
		// Send status to IHM
		if (ihmLink!=null)
			ihmLink.logStartupStatus(status, e);
	}
	
	/**
	 * Immediate mode spec handler.
	 * 
	 * @param spec Valid ECSpec component.
	 * @return the reports.
	 * @throws InterruptedException 
	 */
	public ECReports handleECSpec(ECSpec spec, RPChannel RpLink, IhmCom ihmLink) throws InterruptedException {					
		// Submit spec to ALE working thread
		inputSpec = spec;
		
		// start ALE working Thread : give it access to our fields
		aleWorkerThread = new AleWorker(this, RpLink, ihmLink);
		aleWorkerThread.start();
		
		// Wait for working thread to finish : join
		aleWorkerThread.join();
		
		// Later, try without start and join, 
		// but only by calling run directly : i.e execute in the context of the caller thread.
		//aleWorkerThread.run();
		
		// Get output Reports from thread and return it to caller
		return outputReports;
	}
}

/**
 * 
 * @author rdagher
 *
 */
class AleWorker extends Thread{
	
	/**
	 * Ale Engine client for getting results
	 */
	private IcomAleEngine aleEngine;
	
	/**
	 * Communication Channel with a connected RP component.
	 */
	private RPChannel RpLink;
	
	/**
	 * IHM aggregation and communication component.
	 */
	private IhmCom ihmLink;
	
	/**
	 * EPC grouping engine.
	 */
	private Grouping groupingEngine;
	
	/**
	 * Wrapper for decoding and grouping a sgtin-96 tag.
	 */
	private Sgtin96 sgtin96Wrapper = new Sgtin96();
	
	/**
	 * Wrapper for decoding and grouping a sgln-96 tag.
	 */
	private Sgln96 sgln96Wrapper = new Sgln96();
	
	/**
	 * 
	 * Constructor
	 * @param aleEngine
	 * @param RPLink
	 */
	public AleWorker(IcomAleEngine aleEngine,  RPChannel RPLink, IhmCom ihmLink) {
		super();
		this.aleEngine = aleEngine;
		this.RpLink = RPLink;
		this.ihmLink = ihmLink;
		this.groupingEngine = new Grouping();
	}
		
	/**
	 * Runnable method.
	 */
	public void run() {
		long totalTime = System.currentTimeMillis(), time;
		Tag currentTag;
		byte[] currentTagId ;
		byte header;
		ECSpec spec;
		ECReport[] reports;
		UriRepresentation uriRepresentation;
		boolean maxReached; //Threshold of maximum aggregated tags is reached
		int threshold;
		
		
		/* Read input spec from aleEngine Client */
		spec = this.aleEngine.inputSpec; // read input spec
		
		/* output report
		 *  Demonstrator : Only one report is handled : spec -> report
		 */
		reports = new ECReport[1];
		
		/* Transaction init stage */
		// Init ECReport grouping : aggregation conforming to ECSpec
		ECFieldSpec fieldSpec = spec.getReportSpecs()[0].getGroupSpec().getFieldspec();
		
		// Grouping is in epc-pure or epc-tag format (default)
		if (fieldSpec.getFormat() == "epc-pure") 
			uriRepresentation = UriRepresentation.EPC_PAT_PURE;
		else
			uriRepresentation = UriRepresentation.EPC_PAT_TAG;
			
		this.groupingEngine.addGroupPattern(spec.getReportSpecs()[0].getGroupSpec().getPatternList(), uriRepresentation) ;
		
		// TODO set Maximum aggregated tags from input spec
		threshold = 2000;
		
		// Init GTIN grouping : Decathlon aggregation
		if (ihmLink!=null)
			ihmLink.init();
		try {
			//RpLink.init(this.aleEngine.readerName, spec);
			try {
				RpLink.initAcquisition(spec.getBoundarySpec().getStartTriggerList()[0] /* start trigger */, 
						   			   spec.getReportSpecs()[0].getFilterSpec() 	   /* filtering spec*/);
			} catch (Exception e) {
				System.err.println("Error Initializing RpLink.initAcquisition, ignoring filters and start trigger");
				RpLink.initAcquisition(null, null);
			}

			RpLink.start();
			
			/* TODO Iteration 2 : remove the aggregation of the sgln */
			/* Poll for starting condition : first SGLN trigger */
			// Wait for Acquisition report
			currentTag = RpLink.nextTag();
			
			currentTagId = currentTag.getId();
	
			// aggregate and send to ihm upon reception
			if (ihmLink!=null)
				ihmLink.aggregateSGLN96(currentTagId);
			
			// aggregate for EPC
			this.sgln96Wrapper.setTagData(currentTagId);
			maxReached = groupingEngine.aggregate(EpcCodec.SGLN_96_HEADER, currentTagId, currentTag.getTimestamp(), this.sgln96Wrapper);
			
			/* Poll acquisition reports of SGTIN/SGLN incoming from RP */
			// Important : We assume that reports are received in burst mode
			do {
				// event cycle
				do {
					/* Wait for Acquisition report : blocking call. */
					currentTag = RpLink.nextTag();
					if (currentTag == null) 
						break;
					
					/* Aggregate */
					currentTagId = currentTag.getId();					
					try {
						header = EpcCodec.decodeHeader(currentTagId);
						switch(header) {
							/* Sgtin-96 tag */
							case EpcCodec.SGTIN_96_HEADER :				
								/* wrap and decode id */
								this.sgtin96Wrapper.setTagData(currentTagId);
								
								/* Compute internal aggregation information to IHM : sgtin only */				
								// aggregate
								if (ihmLink!=null)
									ihmLink.aggregateSGTIN96(this.sgtin96Wrapper.getCompanyPrefix(), this.sgtin96Wrapper.getItemReference());
								
								/* Compute EPC aggregation */
								maxReached = groupingEngine.aggregate(header, currentTagId, currentTag.getTimestamp(), this.sgtin96Wrapper);
								break;
							
							/* Sgln-96 tag */
							case EpcCodec.SGLN_96_HEADER :
								/* wrap and decode id */
								this.sgln96Wrapper.setTagData(currentTagId);
								
								/* TODO Iteration 2: Compute internal aggregation information to IHM : sgtin only */				
								// aggregate
								//if (ihmLink!=null)
								//	ihmLink.aggregateSGLN96(this.sgln96Wrapper.getCompanyPrefix(), this.sgln96Wrapper.getLocationReference());
								
								/* Compute EPC aggregation */
								maxReached = groupingEngine.aggregate(header, currentTagId, currentTag.getTimestamp(), this.sgln96Wrapper);
								break;
							
							/* Unsupported tag : dropped to default group*/
							default:
								// aggregate an invalid tag
								maxReached = groupingEngine.aggregate(header, currentTagId, currentTag.getTimestamp(), null);
						}
					}
					catch (Exception e) {
						// aggregate an invalid tag
						maxReached = groupingEngine.aggregate((byte)0xFF, currentTagId, currentTag.getTimestamp(), null);
					}
				} while (RpLink.hasTags());
				// Note : in case of whenDataAvailable implem, put it here with flag 
				//        and later on termination condition managment.
				
				// log cycle information to the IHM 
				if (ihmLink!=null)
					ihmLink.flush();
				
				// Test if termination condition is reached		
			} while(!RpLink.isDone() && !maxReached);
		}
		finally {
			RpLink.clean();
		}
		
		// compute total spent time
		time = System.currentTimeMillis();
		totalTime = System.currentTimeMillis() - totalTime;
		
		/* Set ECReports response */
		
		// Set date and time when the event cycle ended.
		aleEngine.outputReports.setDate(new Date(time));
		// set total time
		aleEngine.outputReports.setTotalMilliseconds(totalTime);
		
		// set spec name : implementation dependent
		aleEngine.outputReports.setSpecName("Immediate mode spec");
		
		// include spec if necessary : by reference (ICOM specific)
		if (spec.isIncludeSpecInReports())
			aleEngine.outputReports.setECSpec(spec);
		
		// build report : grouping and statistics 
		reports[0] = groupingEngine.buildReporGroups(spec);
		groupingEngine.cleanUp(); // clean up
		
		// set Initiation Trigger
		aleEngine.outputReports.setInitiationTrigger(RpLink.getInitTrigger());
		// set Initiation condition : ex. start trigger (SGLN trigger) is received.
		aleEngine.outputReports.setInitiationCondition(RpLink.getInitCondition());
		
		if (!maxReached) {
			// set termination condition
			aleEngine.outputReports.setTerminationCondition(RpLink.getTermCondition());
			// set Termination Trigger
			aleEngine.outputReports.setTerminationTrigger(RpLink.getTermTrigger());
		}
		else {
			// set termination condition
			aleEngine.outputReports.setTerminationCondition(ECTerminationCondition.TRIGGER);
			// set Termination Trigger
			aleEngine.outputReports.setTerminationTrigger(new ECTrigger(TriggerType.buildTriggerURI(new StringBuffer(), TriggerType.THRESHOLD_TRIGGER, Integer.toString(threshold))));
		}
		/* Build ECReport blocks : a copy from the temporary list of ECReports */
		aleEngine.outputReports.setReports(reports);
	}	
}
