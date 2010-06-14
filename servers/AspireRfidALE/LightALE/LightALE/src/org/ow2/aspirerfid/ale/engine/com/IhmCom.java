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

package org.ow2.aspirerfid.ale.engine.com;

import org.ow2.aspirerfid.ale.codec.Sgln96Codec;
import org.ow2.aspirerfid.ale.codec.Sgtin96Codec;

import fr.icom.decathlon.access.fifo.EventFifo;

/**
 * Interface with the IHM subset. The following features are provided : 
 * <ul>
 * 	<li> log of the startup status with the error context. </li>
 *  <li> aggregation report build on a cycle basis : {@link #flush()} method ends cycle</li>
 * </ul>
 * 
 * @author rdagher
 *
 */
public class IhmCom {
	/**
	 *  fifo for communicating with 
	 */
	private EventFifo anEventFifo;
	
	/**
	 * Aggregation Report
	 */
	private AggregationReport aggregReport;
	
	/**
	 * Wrapper for sgtin96 tag decoding 
	 */
	private Sgtin96Codec sgtin96Codec;
	
	/**
	 * Wrapper for sgln96 tag decoding 
	 */
	private Sgln96Codec sgln96Codec;
	
	/**
	 *  set of sgtin tags
	 */
	private GtinPool gtinSet;
	
	/**
	 * Current GTIN for aggregation
	 */	
	private Gtin currentGtin;
	
//	/**
//	 *  set of sgln tags
//	 */
//	private HashSet sglnSet;
//	
//	
//	/**
//	 *  Count of each sgln tag
//	 */
//	private int [] sglnCount;
	
	
	
	/**
	 * Constructor
	 * @param anEventFifo the fifo for sending status and aggregation info to the IHM.
	 * @param maxTags the maximum number of tags
	 */
	public IhmCom(EventFifo anEventFifo, int maxTags) {
		this.anEventFifo = anEventFifo;
		
		// sgtin set
		this.gtinSet = new GtinPool(maxTags);
		currentGtin = new Gtin();
		sgtin96Codec = new Sgtin96Codec();
		
		init();
	}

	/**
	 * Initialize aggregation process.
	 * Must always be called after a flush, in order to begin a new aggregation cycle.
	 */
	public void init() {
		aggregReport =  new AggregationReport();
		gtinSet.reset();
	}
	
	/**
	 * Sends the start up status to the IHM.
	 * @param status flag : true for OK, false in case of error
	 * @param errContext context of error, or null if none
	 */
	public void logStartupStatus(boolean status, Exception errContext) {
		StartupStatus sts = new StartupStatus();
		
		sts.setStatus(status, errContext);
		anEventFifo.push(sts);		
	}
	
	/**
	 * Adds the sgln tag to the aggregation report, and sends it to the IHM.
	 */
	public void aggregateSGLN96(byte[] tagData) {
		// wrap and decode
		sgln96Codec.decodeTagData(tagData);
		
		// aggregate
		aggregateSGLN96(sgln96Codec.getCompanyPrefix(), sgln96Codec.getLocationReference());
	}
	
	public void aggregateSGLN96(long companyPrefix, int locationReference) {

		// Build Report
		aggregReport.addSGLN(companyPrefix, locationReference, 1);

		// send report
		anEventFifo.push(this.aggregReport);

		// clean up
		aggregReport = null;	
	}
	
	/**
	 * Perfoms the aggregation of gtin tag. <br>
	 * For each different gtin, a counter is updated. 
	 */
	public void aggregateSGTIN96(byte[] id) {
		// wrap and decode
		this.sgtin96Codec.decodeTagData(id);
		
		// agregate 
		aggregateSGTIN96(this.sgtin96Codec.getCompanyPrefix(), this.sgtin96Codec.getItemReference());
		
	}
	
	/**
	 * Perfoms the aggregation of gtin tag. <br>
	 * For each different gtin, a counter is updated. 
	 */
	public void aggregateSGTIN96(long companyPrefix, int itemReference) {
		int idx;
		
		// Robustness
		if (aggregReport == null)
			init();
		
		// update count
		currentGtin.companyPrefix = companyPrefix;
		currentGtin.itemReference = itemReference;
		idx = gtinSet.contains(currentGtin);
		if (idx == -1) {
			// gtin not present, add it
			gtinSet.getGtin().setFields(companyPrefix, itemReference);
		}
		else {
			gtinSet.pool[idx].count++;
		}
	}	
	
	/**
	 * Sends gtin aggregation report on fifo.
	 * @throws Exception 
	 */
	public void flush() {
		Gtin gtin;
		
		// Build report for each gtin tag
		for (int i = 0; i< gtinSet.current; i++ ) {
			gtin = gtinSet.pool[i];
			aggregReport.addSGTIN(gtin.companyPrefix, gtin.itemReference, gtin.count);			
		}
		
		// send report
		anEventFifo.push(this.aggregReport);

		// clean up
		aggregReport = null;		
		gtinSet.reset();
	}	
	
	/*
	 * Test case : debug with step by step to observe data.
	 */
	public static void main(String[] args) throws Exception{
		EventFifo aFifo  = null;
		IhmCom aCom = new IhmCom(aFifo, 10000);
		
		// sgln trigger event
		//aCom.aggregateSGLN96(211298, 70875);
		
		// sgtins
		long companyPrefix = 0;
		int itemReference = 0;
		
		for (int i = 0, j = 0 ; i < 100; i++) {
			if ((i!=0) && (0==i%10))
				j++;
			aCom.aggregateSGTIN96(companyPrefix+10*j, itemReference+j);
		}
		// will raise java.lang.NullPointerException : because fifo is null
		aCom.flush();
	}
}


/**
 * A pool of gtin members.
 * @author rdagher
 *
 */
class GtinPool {
	/**
	 * Current free index
	 */
	protected int current;
	
	/**
	 * Pool of reusable Gtin elements
	 */
	protected Gtin [] pool;
	
	 public GtinPool(int maxCapacity) {
		pool = new Gtin[maxCapacity];
		for (int i=0; i< maxCapacity; i++) {
			pool[i] = new Gtin();
			pool[i].invalidate();
		}
		current = 0;
	}
	
	 /**
	  * get a free gtin from the pool
	  * @return
	  */
	Gtin getGtin() {
		return pool[current++];
	}
	
	/**
	 * Tests if a gtin is present in pool.
	 * @param gtin
	 * @return the index of the gtin in the pool if present, -1 otherwise.
	 */
	int contains (Gtin gtin) {
		int idx = -1;
		
		for (int i = 0; i< current; i++) {
			if (gtin.equals(pool[i])) {
				idx = i;
				break;
			}
		}
		return idx;
	}
	
	/**
	 * Invalidates all pool members.
	 */
	void reset() {		
		for (int i = 0; i<current; i++)
			this.pool[i].invalidate();;
		this.current = 0 ;
	}
}


/**
 * Gtin element.
 * @author rdagher
 *
 */
class Gtin {
	protected long companyPrefix;
	protected int itemReference;	
	
	/* Number of readings */
	protected int count;

	
	/**
	 * Equality.
	 * @param obj
	 * @return
	 */
	public boolean equals(Gtin obj) {
		return (companyPrefix == obj.companyPrefix) && 
			   (itemReference == obj.itemReference);
	}
	
	/**
	 * Invalidates gtin fields
	 */
	public void invalidate() {
		this.companyPrefix = -1 ;
		this.itemReference = -1 ;
		this.count = -1 ;	
	}
	
	/**
	 * sets gtin fields for first time.
	 * 
	 * @param companyPrefix
	 * @param itemReference
	 */
	public void setFields(long companyPrefix, int itemReference) {
		this.companyPrefix = companyPrefix;
		this.itemReference = itemReference;	
		this.count = 1;
	}
}
