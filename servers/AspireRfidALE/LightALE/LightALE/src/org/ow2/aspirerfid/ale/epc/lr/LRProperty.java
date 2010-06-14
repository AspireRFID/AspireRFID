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

/**
 * A logical reader property is a name-value pair. Values are generically represented as 
 * strings in the Logical Reader API. <br>The ALE implementation is responsible for any 
 * data type conversions that may be necessary.
 * @author rdagher
 *
 */
public class LRProperty {
	
	/**
	 * The name of the property. 
	 * The recognised names for properties are implementation-defined.
	 */
	private String name;
	
	/**
	 * The value of the property (Optional).
	 */
	private String value;
	
	//	---	

	
	/**
	 * Constructor
	 */
	public LRProperty() {
		name = "";
		value = "";
	}


	/**
	 * Constructor
	 * @param name
	 * @param value
	 */
	public LRProperty(String name, String value) {
		this.name = name;
		this.value = value;
	}


	/**
	 * Getter for name
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Getter for value
	 * @return the value
	 */
	public String getValue() {
		return value;
	}


	/**
	 * Setter for name
	 * @param name the value to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Setter for value
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}	
}
