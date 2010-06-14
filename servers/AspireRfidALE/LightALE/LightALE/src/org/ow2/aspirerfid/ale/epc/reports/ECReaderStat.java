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
 * An <code>ECReaderStat</code> contains information about sightings of a Tag by a 
 * particular Reader. An ALE implementation MAY use a subclass of this type to provide 
 * information about a Reader’s interaction with a Tag that is not specific to a 
 * particular sighting. <br>
 * For example, a subclass of this type might provide timestamps for 
 * the first and last time the Tag was sighted by the Reader, or the total number of 
 * sightings of the Tag by that Reader.
 *
 * @author pops
 * @author R.DAGHER
 */
public class ECReaderStat {

	/**
	 * The name of the logical Reader whose sightings are reported in this <code>ECReaderStat</code>. 
	 * This name may, at the implementer’s discretion, refer to either a logical reader 
	 * name as named in the defining <code>ECSpec</code> or one of the underlying component 
	 * readers that contribute to a named logical reader. 
	 * Implementers SHOULD document for each statistics profile which of the names are 
	 * used (or both).
	 */
	private String readerName ;
	
	/**
	 * An unordered list containing information pertaining to one sighting of the Tag by 
	 * the Reader named in <code>readerName</code>.
	 */
	private ECSightingStat[] sightings ; // Optional

	
	//	---
	
	/**
	 * Constructor
	 * @param readerName
	 * @param sightings
	 */
	public ECReaderStat(String readerName, ECSightingStat[] sightings) {
		this.readerName = readerName;
		this.sightings = sightings;
	}

	/**
	 * Getter for readerName
	 * @return the readerName
	 */
	public String getReaderName() {
		return readerName;
	}

	/**
	 * Getter for sightings
	 * @return the sightings
	 */
	public ECSightingStat[] getSightings() {
		return sightings;
	}

	/**
	 * Setter for readerName
	 * @param readerName the value to set
	 */
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	/**
	 * Setter for sightings
	 * @param sightings the value to set
	 */
	public void setSightings(ECSightingStat[] sightings) {
		this.sightings = sightings;
	}
}
