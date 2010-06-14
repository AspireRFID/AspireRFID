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

import org.ow2.aspirerfid.ale.codec.EncScheme;
import org.ow2.aspirerfid.ale.codec.Sgtin96Codec;
import org.ow2.aspirerfid.ale.codec.UnsignedBigInt;

public class Sgtin96 extends Epc{
	private Sgtin96Codec aSgtin96Codec;
	
	/**
	 * Constructor
	 */
	public Sgtin96() {
		super();
		aSgtin96Codec = new Sgtin96Codec();
		this.uriRepresentation = UriRepresentation.EPC_TAG;
	}
	
	public void setTagData(byte[] tagData) {
		
		/* set tag data */
		super.setTagData(tagData);
		
		/* decode tag data */
		aSgtin96Codec.decodeTagData(tagData);
		
		/* Set Fields for grouping  */
		// field 0 : filter
		uriFields[0].setData(aSgtin96Codec.getFilterValue());
		
		// field 1 : company prefix 
		uriFields[1].setData(aSgtin96Codec.getCompanyPrefix());
		
		// field 2 : Item reference
		uriFields[2].setData(aSgtin96Codec.getItemReference());
		
		// field 3 : serial number
		uriFields[3].setData(aSgtin96Codec.getSerialNumber());
	}
	
	/**
	 * Sets the current representation for accessing fields. Useful for the 
	 * grouping algorithm.
	 */
	public void setRepresentation(UriRepresentation uriRepresentation) throws IllegalArgumentException {
		
		if (uriRepresentation == UriRepresentation.EPC_PURE) {
			this.uriRepresentation = uriRepresentation;
			this.type = EncScheme.SGTIN;
		}
		else if (uriRepresentation == UriRepresentation.EPC_TAG) {
			this.uriRepresentation = uriRepresentation;
			this.type = EncScheme.SGTIN_96;
		}
		else
			throw new IllegalArgumentException("invalid header for grouping");
		
		
	}
	
	public UnsignedBigInt getField(int index) throws IndexOutOfBoundsException {
		
		// If pure identity, ignore the filter value
		if (uriRepresentation == UriRepresentation.EPC_PURE)
			index++; // stack effect
		
		return uriFields[index];
	}
	
	/**
	 * @return string representation of the EPC object : int the Tag URI
	 */	
	public String toString() {
		return aSgtin96Codec.getTagUri();
	}
	
	public String getPureUri() {
		return aSgtin96Codec.getPureUri();
	}
	
	public String getTagUri() {
		return aSgtin96Codec.getTagUri();
	}
	
	public String getRawDecUri() {
		return aSgtin96Codec.getRawDecUri();
	}
	
	public String getRawHexUri() {
		return aSgtin96Codec.getRawHexUri();
	}
	
	public byte getFilterValue () {
		return aSgtin96Codec.getFilterValue();
	}
	
	public long getCompanyPrefix () {
		return aSgtin96Codec.getCompanyPrefix();
	}
	
	public int getItemReference () {
		return aSgtin96Codec.getItemReference();
	}
	
	public long getSerialNumber() {
		return aSgtin96Codec.getSerialNumber();
	}

	public boolean equals(Epc tag) {
		boolean ret = false;
		
		if ((tag != null) && (tag instanceof Sgtin96)) {
			Sgtin96 sgtin96 = (Sgtin96)tag;
			ret = (aSgtin96Codec.getFilterValue() == sgtin96.aSgtin96Codec.getFilterValue()) &&
			      (aSgtin96Codec.getPartition() == sgtin96.aSgtin96Codec.getPartition()) &&
			      (aSgtin96Codec.getCompanyPrefix() == sgtin96.aSgtin96Codec.getCompanyPrefix()) &&
			      (aSgtin96Codec.getItemReference() == sgtin96.aSgtin96Codec.getItemReference()) &&
			      (aSgtin96Codec.getSerialNumber() == sgtin96.aSgtin96Codec.getSerialNumber()) ;
		}
			
		return ret;
	}
}
