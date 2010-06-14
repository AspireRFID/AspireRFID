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

package org.ow2.aspirerfid.ale.epc.reports;

/**
 * <code> ECInitiationCondition </code> is an enumerated type that describes how an event cycle 
 * was started.<br>
 * Note : <br>
 * No enum in Java before JDK1.4. The chosen workaround for implementing an enum-like 
 * class is by creating a class that cannot be instantiated and that exports constants
 * defining enumerated values with a string representation.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECInitiationCondition {
	
	/**
	 * One of the triggers specified in the <code>startTrigger</code> 
	 * or <code>startTriggerList</code> parameter of <code>ECBoundarySpec</code>
	 * was received.
	 */
	public final static ECInitiationCondition TRIGGER = new ECInitiationCondition("TRIGGER");
	
	/**
	 * The <code>repeatPeriod</code> specified in the <code>ECBoundarySpec</code> 
	 * expired, or the event cycle started immediately after the previous event 
	 * cycle ended because neither a start trigger nor a repeat period was specified.
	 */
	public final static ECInitiationCondition REPEAT_PERIOD = new ECInitiationCondition("REPEAT_PERIOD");

	/**
	 * The <code>ECSpec</code> transitioned from the unrequested state to the 
	 * requested state and <code>startTriggerLis</code>t in <code>ECBoundarySpec</code> 
	 * was empty.
	 */
	public final static ECInitiationCondition REQUESTED = new ECInitiationCondition("REQUESTED");

	/**
	 * Used when an outstanding <code>poll</code> call is terminated due to an <code>undefine</code> call,
	 * while the ECSpec was in the requested state (that is, before any start condition actually occurred).
	 */
	public final static ECInitiationCondition UNDEFINED = new ECInitiationCondition("UNDEFINED");

	//
	// <<extension point>>
	//	---
	
	/**
	 * 
	 * Constructor protected. Do not instantiate externally.
	 */
	protected ECInitiationCondition()  {
		toStringValue = "";
	}

	/**
	 * 
	 * Constructor protected. Do not instantiate externally.
	 */
	protected ECInitiationCondition (String toStringValue) {
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
