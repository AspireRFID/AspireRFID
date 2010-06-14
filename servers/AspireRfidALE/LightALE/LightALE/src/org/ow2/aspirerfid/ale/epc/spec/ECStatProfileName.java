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
 * Each valid value of <code>ECStatProfileName</code> names a statistics profile that can 
 * be included in an <code>ECReports</code>.
 * <br>
 * Note :<br>
 * No enum in Java before JDK1.4. The chosen workaround for implementing an enum-like 
 * class is by creating a class that cannot be instantiated and that exports constants
 * defining enumerated values with a string representation.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECStatProfileName {
	/**
	 * The EPC Global specification defines one statistics profile named 
	 * <code>TagTimestamps</code> which vendors MAY implement; vendors MAY also implement 
	 * their own proprietary profiles.
	 */
	public final static ECStatProfileName TagTimestamps = new ECStatProfileName("TagTimestamps");

	//
	// <<extension point>>
	//	---
	
	/**
	 * Decathlon statistics consisting of the following information for each captured tag:
	 *  
	 *  <table border="1">
	 *  <tr>
	 *  <td> firstSightingTime </td>
	 *  <td> First Time the tag was captured in event cycle </td>
	 *  </tr>
	 *  <td> lastSightingTime </td>
	 *  <td> Last Time the tag was captured in event cycle</td>	
	 *  </tr>
	 *  <td> number of readings </td>
	 *  <td> Number of sightings of the tag in [firstSightingTime, lastSightingTime] </td>	
	 *  </table>
	 */
	public final static ECStatProfileName DecathlonStats = new ECStatProfileName("DecathlonStats");

	/**
	 * 
	 * Constructor protected. Do not instantiate externally.
	 */
	protected ECStatProfileName()  {
		toStringValue = "";
	}

	/**
	 * 
	 * Constructor protected. Do not instantiate externally.
	 */
	protected ECStatProfileName (String toStringValue) {
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
