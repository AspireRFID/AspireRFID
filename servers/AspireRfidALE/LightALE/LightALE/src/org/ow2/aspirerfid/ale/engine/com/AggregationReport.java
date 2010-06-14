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

import fr.icom.decathlon.access.fifo.IALEEvent;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pops
 * @author Guche Grégory aka Sceez Melting-Pot.
 */
public class AggregationReport implements IALEEvent {

    public static final int cNoEventBit    = 0;

    public static final int cSGLNEventBit  = 1;
    public static final int cSGTINEventBit = 2;

    public static final int cAllEventsBits = cSGLNEventBit + cSGTINEventBit;

    private Map aggregationReportSGTINs, aggregationReportSGLNs;
    private int aggregationReportEventsBitMask;

    public AggregationReport() { clear(); }

    public void clear() {
        aggregationReportEventsBitMask = 0;
        aggregationReportSGTINs        = null;
        aggregationReportSGLNs         = null;
    }

    public int getEvents() { return aggregationReportEventsBitMask; }

    public Map getSGTINS() { return aggregationReportSGTINs; }
    public Map getSGLNS()  { return aggregationReportSGLNs;  }

    public void addSGTIN(long aCompanyPrefix, int aReference, int  aCount) {
		/*
    	aggregationReportEventsBitMask |= cSGTINEventBit;
		if(aggregationReportSGTINs == null)
            aggregationReportSGTINs = newMap();
        aggregationReportSGTINs.put(new SGTINAggregationKey(aCompanyPrefix, aReference), new Integer(aCount));
		*/
    	
		//Roudy : removed duplicate code
		addSGTIN(new SGTINAggregationKey(aCompanyPrefix, aReference), aCount);	
    }
	
	public void addSGTIN(SGTINAggregationKey aKey, int aCount){
		aggregationReportEventsBitMask |= cSGTINEventBit;
		if(aggregationReportSGTINs == null)
            aggregationReportSGTINs = newMap();
        aggregationReportSGTINs.put(aKey, new Integer(aCount));       		
    }
	
//    public void addSGLN(short []theData, int aCount) throws Exception {
//        aggregationReportEventsBitMask |= cSGLNEventBit;
//        if(aggregationReportSGLNs == null)
//            aggregationReportSGLNs = newMap();
//        aggregationReportSGLNs.put(new OldSGLNAggregationKey(theData), new Integer(aCount));
//    }
    
    public void addSGLN(long aCompanyPrefix, int aReference, int  aCount) {
        aggregationReportEventsBitMask |= cSGLNEventBit;
        if(aggregationReportSGLNs == null)
            aggregationReportSGLNs = newMap();
        aggregationReportSGLNs.put(new SGLNAggregationKey(aCompanyPrefix, aReference), new Integer(aCount));
    }

    protected Map newMap() { return new HashMap(); }
}
