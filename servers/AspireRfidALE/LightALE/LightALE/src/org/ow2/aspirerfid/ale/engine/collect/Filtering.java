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

package org.ow2.aspirerfid.ale.engine.collect;

import org.ow2.aspirerfid.ale.codec.EpcCodec;
import org.ow2.aspirerfid.ale.engine.collect.grouping.Epc;
import org.ow2.aspirerfid.ale.engine.collect.grouping.FilterOperator;
import org.ow2.aspirerfid.ale.engine.collect.grouping.Sgln96;
import org.ow2.aspirerfid.ale.engine.collect.grouping.Sgtin96;
import org.ow2.aspirerfid.ale.epc.spec.ECFilterListMember;
import org.ow2.aspirerfid.ale.epc.spec.ECFilterSpec;


/**
 * This class implements an EPC filtering engine based on tag ids (EPCID field).
 * @author rdagher
 *
 */
public class Filtering {
	
	/**
	 * Wrappers for filtering tag ids.
	 */
	private Epc sgtin96, sgln96;
	
	/**
	 * Filtering operator.
	 */
	private FilterOperator fitlerOp;
		
	
	/**
	 * Constructor
	 */
	public Filtering() {
		// Filtering operator
		fitlerOp = new FilterOperator();
		
		// Recyclable wrappers
		sgtin96 = new Sgtin96();
		sgln96 = new Sgln96();		
	}

	/**
	 * Adds filtering patterns from an {@link ECFilterSpec} object.
	 * @param filterSpec filter spec object.
	 */
	public void addPAtterns(ECFilterSpec filterSpec) {
		ECFilterListMember[] filterList;
		
		// TODO validate filter Members
		if (filterSpec!= null) {
			filterList = filterSpec.getFilterList();
			if (filterList != null) {
				// for each member : take into account its list of patterns
				for (int i = 0 ; i < filterList.length; i++) {
					String[] patList = filterList[i].getPatList();
					if (patList != null)
						this.fitlerOp.addPatterns(patList,filterList[i].isIncludeExclude());
				}
			}
		}
	}
	
	/**
	 * 
	 * @param epc
	 * @return
	 */
	public boolean filter(Epc epc) {
		return this.fitlerOp.filter(epc);
	}
	
	/**
	 * 
	 * @param TagId an array of bytes containing the tag id.
	 * @return
	 * @see {@link Epc} {@link EpcCodec}
	 */
	public boolean filter(byte[] TagId) {
		byte header;
		boolean filt;
		
		// decode and wrap for filtering
		header = EpcCodec.decodeHeader(TagId);
		switch (header) {
			// sgtin-96
			case EpcCodec.SGTIN_96_HEADER:
				this.sgtin96.setTagData(TagId);
				filt = filter(this.sgtin96);
				break;
				
			//sgln-96 aggregation
			case EpcCodec.SGLN_96_HEADER:
				this.sgln96.setTagData(TagId);
				filt = filter(this.sgln96);
				break;
		
			// Unsupported tag, keep it however
			default : 
				filt = true;
		}
		
		return filt;
	}
	
	/**
	 * Clean up method for reusing the filtering engine instance.
	 */
	public void cleanUp() {
		fitlerOp.cleanUp();
	}
	
}
