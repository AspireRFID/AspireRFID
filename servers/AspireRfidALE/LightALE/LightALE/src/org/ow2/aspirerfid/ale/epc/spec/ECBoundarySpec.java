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

import java.util.Properties;

/**
 * An ECBoundarySpec specifies how the beginning and end of event cycles are 
 * to be determined.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECBoundarySpec extends Spec {

	/**
	 * (Optional) An unordered list that specifies zero or more triggers that may start 
	 * a new event cycle for this ECSpec.
	 */
	private ECTrigger[] startTriggerList;
	
	/**
	 * (Optional) Specifies an interval of time for starting a new event cycle for this 
	 * ECSpec, relative to the start of the previous event cycle.
	 * 
	 * @category Not implemented.
	 */
	private ECTime repeatPeriod ;
	
	/**
	 * (Optional) An unordered list that specifies zero or more triggers that may stop 
	 * an event cycle for this ECSpec.
	 */
	private ECTrigger[] stopTriggerList ;
	
	/**
	 * (Optional) Specifies an interval of time for stopping an event cycle for this ECSpec,
	 * relative to the start of the event cycle.
	 * If omitted or equal to zero, has no effect on the stopping of the event cycle.
	 * 
	 * @category Not implemented.
	 */
	protected ECTime duration ;
	
	/**
	 * (Optional) Specifies that an event cycle may be stopped if no new tags are read within
	 * the specified interval.
	 * If omitted or equal to zero, has no effect on the stopping of the event cycle.
	 */
	private ECTime stableSetInterval ;
	
	/**
	 * (Optional) If true, specifies that an event cycle may be stopped when any Tag is read
	 * that matches the filter conditions of at least one ECReportSpec within this ECSpec.
	 * If omitted or false, has no effect on the stopping of the event cycle.
	 * 
	 * @protected Not implemented.
	 */
	private boolean whenDataAvailable ;

	//
	// <<extension point>>
	//	---	

	/**
	 * Constructor for building spec using a Java property File
	 * 
	 * @param loadFromProp Java property object containing specification data
	 * @throws TBD
	 */	
	public ECBoundarySpec (Properties loadFromProp) {
		loadFromProp (loadFromProp);		
	}

	/**
	 * Constructor
	 * @param startTriggerList
	 * @param repeatPeriod
	 * @param stopTriggerList
	 * @param duration
	 * @param stableSetInterval
	 * @param whenDataAvailable
	 */
	public ECBoundarySpec(ECTrigger[] startTriggerList, ECTime repeatPeriod,
			ECTrigger[] stopTriggerList, ECTime duration,
			ECTime stableSetInterval, boolean whenDataAvailable) {
		this.startTriggerList = startTriggerList;
		this.repeatPeriod = repeatPeriod;
		this.stopTriggerList = stopTriggerList;
		this.duration = duration;
		this.stableSetInterval = stableSetInterval;
		this.whenDataAvailable = whenDataAvailable;
	}
	
	/**
	 * Set up fields from a Java property object
	 * 
	 * @param spec_property Java property object containing specification data
	 * @throws TBD
	 */
	public void loadFromProp (Properties spec_property) {
		// TODO
		
	}
	
	/**
	 * Getter for startTriggerList
	 * @return the startTriggerList
	 */
	public ECTrigger[] getStartTriggerList() {
		return startTriggerList;
	}
	/**
	 * Getter for repeatPeriod
	 * @return the repeatPeriod
	 */
	public ECTime getRepeatPeriod() {
		return repeatPeriod;
	}
	/**
	 * Getter for stopTriggerList
	 * @return the stopTriggerList
	 */
	public ECTrigger[] getStopTriggerList() {
		return stopTriggerList;
	}
	/**
	 * Getter for duration
	 * @return the duration
	 */
	public ECTime getDuration() {
		return duration;
	}
	/**
	 * Getter for stableSetInterval
	 * @return the stableSetInterval
	 */
	public ECTime getStableSetInterval() {
		return stableSetInterval;
	}
	/**
	 * Getter for whenDataAvailable
	 * @return the whenDataAvailable
	 */
	public boolean isWhenDataAvailable() {
		return whenDataAvailable;
	}

}
