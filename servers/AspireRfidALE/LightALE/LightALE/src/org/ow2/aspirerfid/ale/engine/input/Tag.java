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

package org.ow2.aspirerfid.ale.engine.input;

/**
 * A tag wrapper.
 * 
 * @author rdagher
 *
 */
public class Tag {
	
	/**
	 * Tag id as an array of byte
	 */
	private byte[] id;

	/**
	 * timestamp when the tag was read.
	 */
	private long timestamp;
	
	

	/**
	 * Constructor
	 * @param id
	 * @param timestamp
	 */
	public Tag(byte[] id, long timestamp) {
		this.id = id;
		this.timestamp = timestamp;
	}

	/**
	 * Constructor
	 */
	public Tag() {
		
	}

	/**
	 * Getter for id
	 * @return the id
	 */
	public byte[] getId() {
		return id;
	}

	/**
	 * Getter for timestamp
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Setter for id
	 * @param id the value to set
	 */
	public void setId(byte[] id) {
		this.id = id;
	}

	/**
	 * Setter for timestamp
	 * @param timestamp the value to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
