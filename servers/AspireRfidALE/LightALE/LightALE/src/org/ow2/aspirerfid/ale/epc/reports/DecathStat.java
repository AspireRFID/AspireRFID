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

import org.ow2.aspirerfid.ale.epc.spec.ECStatProfileName;

/**
 * Decathlon statistics consisting of the following information for each captured tag:
 *  
 *  <table border="1">
 *  <tr>
 *  <td> firstSightingTime </td>
 *  <td> First Time the tag was captured in reading cycle </td>
 *  </tr>
 *  <td> lastSightingTime </td>
 *  <td> Last Time the tag was captured in event cycle</td>	
 *  </tr>
 *  <td> number of readings </td>
 *  <td> Number of sightings of the tag in [firstSightingTime, lastSightingTime] </td>	
 *  </table>
 *  
 * @author pops
 * @author R.DAGHER
 */ 
public class DecathStat extends ECTagStat{

	
	/**
	 * First Time the tag was captured in event cycle.
	 */
	private String firstSightingTime;
	
	/**
	 * Last Time the tag was captured in event cycle.
	 */
	private String lastSightingTime;
	
	/**
	 *  Number of sightings of the tag in [<code>firstSightingTime, lastSightingTime</code>] 
	 */
	private long numReadings;
	
	
	//	---
	
	/**
	 * Constructor
	 */
	public DecathStat() {
		super(ECStatProfileName.DecathlonStats);
	}

	/**
	 * Getter for firstSightingTime
	 * @return the firstSightingTime
	 */
	public String getFirstSightingTime() {
		return firstSightingTime;
	}

	/**
	 * Getter for lastSightingTime
	 * @return the lastSightingTime
	 */
	public String getLastSightingTime() {
		return lastSightingTime;
	}

	/**
	 * Getter for numReadings
	 * @return the numReadings
	 */
	public long getNumReadings() {
		return numReadings;
	}

	/**
	 * Setter for firstSightingTime
	 * @param firstSightingTime the value to set
	 */
	public void setFirstSightingTime(String firstSightingTime) {
		this.firstSightingTime = firstSightingTime;
	}

	/**
	 * Setter for lastSightingTime
	 * @param lastSightingTime the value to set
	 */
	public void setLastSightingTime(String lastSightingTime) {
		this.lastSightingTime = lastSightingTime;
	}

	/**
	 * Setter for numReadings
	 * @param numReadings the value to set
	 */
	public void setNumReadings(long numReadings) {
		this.numReadings = numReadings;
	}
	
}
