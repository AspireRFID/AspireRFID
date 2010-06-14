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


package org.ow2.aspirerfid.rp.imp.caen;

import java.util.ArrayList;
import java.util.List;

import org.ow2.aspirerfid.rp.api.readreport.ReadReport;


/**
 * A Caen Read Report.
 * Two singleton are decared :
 * <ul>
 * 	<li> EMPTY_CAEN_READ_REPORT : for empty reports</li>
 * 	<li> REUSABLE_REPORT : singleton for single shot mode </li>
 * </ul>
 * 
 * @author rdagher
 *
 */
public class CAENReadReport implements ReadReport {

	protected final static CAENReadReport EMPTY_CAEN_READ_REPORT = new CAENReadReport();
	
	protected static CAENReadReport REUSABLE_REPORT = new CAENReadReport();
	
	/**
	 * List of tag Ids associated with the unique default source.
	 */
	private List tagIds;	
	
	
	/**
	 * Constructor
	 */
	protected CAENReadReport() {
		tagIds = new ArrayList() ;
	}

	protected synchronized void addTag(String hexId) {
		tagIds.add(hexId);
	}
	
	/**
	 * Returns a copy of the report. This operation pops the contents of the 
	 * report for reusing the same object..
	 */
	public synchronized String[] getTagIds(String sourceName) {
		String [] tags;
		
		tags = (String[]) tagIds.toArray(new String[tagIds.size()]);
		tagIds.clear();
		
		return tags;
	}

}
