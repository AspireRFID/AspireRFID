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

package org.ow2.aspirerfid.ale.epc.spec;

/**
 * ECReportSetSpec is an enumerated type (no enum in JDK 1.4) denoting what set 
 * of Tags is to be considered for filtering and output: all Tags read in the 
 * current event cycle, additions from the previous event cycle, or deletions 
 * from the previous event cycle.<br>
 * Note : <br>
 * No enum in Java before JDK1.4. The chosen workaround for implementing an enum-like 
 * class is by creating a class that cannot be instantiated and that exports constants
 * defining enumerated values with a string representation.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECReportSetSpec {
	
	/**
	 * The set of tags considered for filtering and output SHALL be the set
	 * of Tags read during the event cycle.
	 */
	public final static ECReportSetSpec CURRENT = new ECReportSetSpec("CURRENT");
	
	/**
	 * The set of tags considered for filtering and output SHALL be the set of Tags 
	 * read during the event cycle, minus the prior set of Tags; that is, 
	 * the set of Tags that were read during the event cycle and not members 
	 * of the prior set of Tags.
	 * 
	 * @category Not implemented : only ALE immediate mode is implemented.
	 */
	public final static ECReportSetSpec ADDITIONS = new ECReportSetSpec("ADDITIONS");
	
	/**
	 * The set of tags considered for filtering and output SHALL be the prior set 
	 * of Tags, minus the set of Tags read during the event cycle; that is, the set
	 * of Tags that were not read during the event cycle but are members of the 
	 * prior set of Tags.
	 *  
	 *  @category Not implemented : only ALE immediate mode is implemented.
	 */
	public final static ECReportSetSpec DELETIONS = new ECReportSetSpec("DELETIONS");
	
	//
	// <<extension point>>
	//	---
	
	/**
	 * 
	 * Constructor protected. Do not instantiate externally.
	 */
	protected ECReportSetSpec()  {
		toStringValue = "";
	}

	/**
	 * 
	 * Constructor protected. Do not instantiate externally.
	 */
	protected ECReportSetSpec (String toStringValue) {
		this.toStringValue = new String(toStringValue);
	}

	/**
	 * String representation of an enumerated value.
	 */
	private String toStringValue ;


	/**
	 * Conversion to string
	 */
	public String toString() {
		return this.toStringValue;
	}
	
}
