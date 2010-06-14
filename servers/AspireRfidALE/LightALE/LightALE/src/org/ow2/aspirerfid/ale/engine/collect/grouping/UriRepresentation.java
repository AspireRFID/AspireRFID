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

package org.ow2.aspirerfid.ale.engine.collect.grouping;

public class UriRepresentation {
	/* URI identity representation */
	public final static UriRepresentation EPC_PURE = new UriRepresentation("urn:epc:id");
	
	public final static UriRepresentation EPC_TAG = new UriRepresentation("urn:epc:tag");
	
	public final static UriRepresentation EPC_RAW = new UriRepresentation("urn:epc:raw");
	
	/* Grouping Patterns */
		
	public final static UriRepresentation EPC_PAT_PURE = new UriRepresentation("urn:epc:idpat");
	
	public final static UriRepresentation EPC_PAT_TAG = new UriRepresentation("urn:epc:pat");
	
	/**
	 * 
	 * @param header a string representation for the uri header: ex. "urn:epc:pat"
	 * @param isPattern boolean indicating if the header is of pattern or not
	 * @return the Encoding scheme object
	 */
	public static UriRepresentation getUriRepresentation(String header, boolean isPattern) {
		UriRepresentation ret ;
		
		if (isPattern) {
			if (EPC_PAT_PURE.equals(header))
				ret = EPC_PAT_PURE;
			else if (EPC_PAT_TAG.equals(header))
				ret = EPC_PAT_TAG;
			else
				throw new IllegalArgumentException("Unsuppoted header : " + header);
		}
		else {
			if (EPC_PURE.equals(header))
			ret = EPC_PURE;
			else if (EPC_TAG.equals(header))
				ret = EPC_TAG;
			else if (EPC_RAW.equals(header))
				ret = EPC_RAW;
			else
				throw new IllegalArgumentException("Unsuppoted header : " + header);
		}
		
		return ret;
	}
	
	private String header;
		
	/**
	 * Constructor
	 * @param id unique id of object
	 * @param scheme representation
	 */
	protected UriRepresentation(String header) {
		this.header = header;
	}
	
	public String toString() {
		return this.header;
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
			ret = this.header.equals(anObject);
		}
		return ret;
	}
}
