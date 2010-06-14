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

package org.ow2.aspirerfid.ale.epc.lr;

import org.ow2.aspirerfid.ale.epc.spec.ECSpec;

/**
 * An LRSpec describes the configuration of a Logical Reader.
 * @author rdagher
 *
 */
public class LRSpec {
	
	/**
	 * If true, this Logical Reader is a composite reader that is an alias for the logical 
	 * reader or readers specified in the readers field. If false, this Logical Reader is a base reader. 
	 * Defaults to false if omitted.
	 */
	private boolean isComposite;
	
	/**
	 * (Optional) If isComposite is true, an unordered list of zero or more names of 
	 * logical readers that collectively provide the channel to access Tags represented by 
	 * this Logical Reader. <br>
	 * Specifying the name of this Logical Reader in an {@link ECSpec}or CCSpec is equivalent 
	 * to specifying the names in readers, except that different properties may apply.
	 * Omitted if isComposite is false.
	 */
	private String[] readers ;
	
	/**
	 * An unordered list of properties (key/value pairs) that control how Tags are 
	 * accessed using this Logical Reader.
	 */
	private LRProperty[] properties;
	
	//
	// <<extension point>>
	//	---	
	
	public void updatePropertyByName(String propName, String value) {
		updateProperty(propName, this, value);
	}

	/**
	 * Factory method for updating a property by name
	 * @param propName
	 * @param properties
	 * @param value
	 */
	public static void updateProperty(String propName, LRSpec spec, String value) {
		boolean found = false;
		LRProperty[] props ;
		
		// search property and set it up
		props = spec.getProperties();
		for (int i = 0 ; ((i < props.length) && !found); i++) {
			if (props[i].getName().equals(propName)) {
				found = true;
				props[i].setValue(value);
			}
		}
		
		// moan if not found
		if (!found)
			throw new IllegalArgumentException("Unfound property : " + propName);
		
	}
	
	/**
	 * Constructor
	 * @param isComposite
	 * @param readers
	 * @param properties
	 */
	public LRSpec(boolean isComposite, String[] readers, LRProperty[] properties) {
		this.isComposite = isComposite;
		this.readers = readers;
		this.properties = properties;
	}

	/**
	 * Getter for isComposite
	 * @return the isComposite
	 */
	public boolean isComposite() {
		return isComposite;
	}

	/**
	 * Getter for readers
	 * @return the readers
	 */
	public String[] getReaders() {
		return readers;
	}

	/**
	 * Getter for properties
	 * @return the properties
	 */
	public LRProperty[] getProperties() {
		return properties;
	}	
}
