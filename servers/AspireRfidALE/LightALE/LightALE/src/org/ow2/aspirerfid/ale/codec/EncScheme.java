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

package org.ow2.aspirerfid.ale.codec;

public final class EncScheme {
	
	public final static EncScheme SGTIN = new EncScheme("sgtin",3);
	
	public final static EncScheme SGLN = new EncScheme("sgln",3);
	
	public final static EncScheme SGTIN_96 = new EncScheme("sgtin-96",4);
	
	public final static EncScheme SGLN_96 = new EncScheme("sgln-96",4);
	
	/**
	 * 
	 * @param scheme a string representation for the encoding scheme : ex. "sgtin-96"
	 * @return the Encoding scheme object
	 */
	public static EncScheme getScheme(String scheme) {
		EncScheme ret ;
		
		if (SGTIN.equals(scheme))
			ret = SGTIN;
		else if (SGLN.equals(scheme))
			ret = SGLN;
		else if (SGTIN_96.equals(scheme))
			ret = SGTIN_96;
		else if (SGLN_96.equals(scheme))
			ret = SGLN_96;
		else
			throw new IllegalArgumentException("Unsuppoted scheme : " + scheme);
		return ret;
	}
	
	private String scheme;
	
	protected int numFields;
	
	/**
	 * Constructor
	 * @param scheme representation
	 * @param numFields number of uri fields
	 */
	protected EncScheme(String scheme, int numFields) {
		this.scheme = scheme;
		this.numFields = numFields;
	}
	
	public String toString() {
		return this.scheme;
	}
	
	public int getNumFields() {
		return this.numFields;
	}
	
	/**
	 * equals implementation. For testing equality against a string containing 
	 * the scheme (ex. sgtin-96).
	 */
	public boolean equals(Object anObject) {
		boolean ret = false;
		
		if (this == anObject) {
			ret = true ;
		}
		else if (anObject instanceof String) {
			ret = this.scheme.equals(anObject);
		}
		return ret;
	}
	
	/**
	 * equals implementation. For testing equality against a string containing 
	 * the scheme (ex. sgtin-96).
	 */
	public boolean equals(EncScheme aPatternEncScheme, boolean isPattern) {
		boolean ret;
		
		if (isPattern)
			ret = this.scheme.startsWith(aPatternEncScheme.scheme);
		else
			ret = this.scheme.equals(aPatternEncScheme.scheme);
	
		return ret;
	}

}
