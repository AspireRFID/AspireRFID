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
 * An <code>ECTagStat</code> provides additional, implementation-defined information about 
 * each "sighting" of a Tag, that is, each time a Tag is acquired by one of the Readers 
 * participating in the event cycle.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECTagStat {
	
	/**
	 * The name of the statistics profile that governed the generation of this 
	 * <code>ECTagStat</code> instance.
	 */
	private ECStatProfileName profile ;
	
	/**
	 * An unordered list containing an <code>ECReaderStat</code> instance for each Reader 
	 * that sighted this Tag.
	 */
	private ECReaderStat[] statBlocks;

	/**
	 * Constructor
	 * @param profile
	 * @param statBlocks
	 */
	public ECTagStat() {
	}
	
	/**
	 * 
	 * Constructor
	 */
	public ECTagStat(ECStatProfileName profile) {
		this.profile = profile;
	}
	
	/**
	 * Getter for profile
	 * @return the profile
	 */
	public ECStatProfileName getProfile() {
		return profile;
	}
	
	/**
	 * Getter for statBlocks
	 * @return the statBlocks
	 */
	public ECReaderStat[] getStatBlocks() {
		return statBlocks;
	}
	
	/**
	 * Setter for statBlocks
	 * @param statBlocks the value to set
	 */
	public void setStatBlocks(ECReaderStat[] statBlocks) {
		this.statBlocks = statBlocks;
	}	
	
}
