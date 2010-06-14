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
 * <code>ECTerminationCondition</code> is an enumerated type that describes how an event cycle 
 * was ended.<br>
 * Note : <br>
 * No enum in Java before JDK1.4. The chosen workaround for implementing an enum-like 
 * class is by creating a class that cannot be instantiated and that exports constants
 * defining enumerated values with a string representation.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECTerminationCondition {
	
	/**
	 * One of the triggers specified in <code>stopTriggerList</code> of <code>ECBoundarySpec</code>
	 * was received.
	 */
	public final static ECTerminationCondition TRIGGER = new ECTerminationCondition("TRIGGER");
	
	/**
	 * The duration specified in the <code>ECBoundarySpec</code> expired.
	 */
	public final static ECTerminationCondition DURATION = new ECTerminationCondition("DURATION");
	
	/**
	 * No new Tags were read within the <code>stableSetInterval</code> specified in the 
	 * <code>ECBoundarySpec</code>.
	 */
	public final static ECTerminationCondition STABLE_SET = new ECTerminationCondition("STABLE_SET");
	
	/**
	 * The <code>whenDataAvailable</code> parameter of the <code>ECSpec</code> was true and 
	 * a Tag was read.
	 */
	public final static ECTerminationCondition DATA_AVAILABLE = new ECTerminationCondition("DATA_AVAILABLE");
	
	/**
	 * The <code>ECSpec</code> transitioned to the unrequested state. By definition, this value 
	 * cannot actually appear in an <code>ECReports</code> instance sent to any client.
	 */
	public final static ECTerminationCondition UNREQUEST = new ECTerminationCondition("UNREQUEST");
	
	/**
	 * The <code>ECSpec</code> was removed by an <code>undefine</code> call while in the 
	 * requested or active state.
	 */
	public final static ECTerminationCondition UNDEFINE = new ECTerminationCondition("UNDEFINE");

	//
	// <<extension point>>
	//	---
	
	/**
	 * 
	 * Constructor protected. Do not instantiate externally.
	 */
	protected ECTerminationCondition()  {
		toStringValue = "";
	}

	/**
	 * 
	 * Constructor protected. Do not instantiate externally.
	 */
	protected ECTerminationCondition (String toStringValue) {
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
