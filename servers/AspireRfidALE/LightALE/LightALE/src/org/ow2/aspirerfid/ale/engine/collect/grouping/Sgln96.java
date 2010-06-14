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
import org.ow2.aspirerfid.ale.codec.Sgln96Codec;
import org.ow2.aspirerfid.ale.codec.UnsignedBigInt;

public class Sgln96 extends Epc{
	private Sgln96Codec aSgln96Codec;
	
	/**
	 * Constructor
	 */
	public Sgln96() {
		super();
		aSgln96Codec = new Sgln96Codec();
		this.uriRepresentation = UriRepresentation.EPC_TAG;
	}
	
	public void setTagData(byte[] tagData) {
		
		/* set tag data */
		super.setTagData(tagData);
		
		/* decode tag data */
		aSgln96Codec.decodeTagData(tagData);
		
		/* Set Fields for grouping  */
		// field 0 : filter
		uriFields[0].setData(aSgln96Codec.getFilterValue());
		
		// field 1 : company prefix 
		uriFields[1].setData(aSgln96Codec.getCompanyPrefix());
		
		// field 2 : Location Reference
		uriFields[2].setData(aSgln96Codec.getLocationReference());
		
		// field 3 : Extension component
		uriFields[3].setData(aSgln96Codec.getExtComponent());
	}
	
	/**
	 * Sets the current representation for accessing fields. Useful for the 
	 * grouping algorithm.
	 */
	public void setRepresentation(UriRepresentation uriRepresentation) throws IllegalArgumentException {
		
		if (uriRepresentation == UriRepresentation.EPC_PURE) {
			this.uriRepresentation = uriRepresentation;
			this.type = EncScheme.SGLN;
		}
		else if (uriRepresentation == UriRepresentation.EPC_TAG) {
			this.uriRepresentation = uriRepresentation;
			this.type = EncScheme.SGLN_96;
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
		return aSgln96Codec.getTagUri();
	}
	
	public String getPureUri() {
		return aSgln96Codec.getPureUri();
	}
	
	public String getTagUri() {
		return aSgln96Codec.getTagUri();
	}
	
	public String getRawDecUri() {
		return aSgln96Codec.getRawDecUri();
	}
	
	public String getRawHexUri() {
		return aSgln96Codec.getRawHexUri();
	}
	
	public byte getFilterValue () {
		return aSgln96Codec.getFilterValue();
	}
	
	public long getCompanyPrefix () {
		return aSgln96Codec.getCompanyPrefix();
	}
	
	public int getLocationReference () {
		return aSgln96Codec.getLocationReference();
	}
	
	public long getExtComponent() {
		return aSgln96Codec.getExtComponent();
	}
	
	public boolean equals(Epc tag) {
		boolean ret = false;
		
		if ((tag != null) && (tag instanceof Sgln96)) {
			Sgln96 sgln96 = (Sgln96)tag;
			
			ret = (aSgln96Codec.getFilterValue() == sgln96.aSgln96Codec.getFilterValue()) &&
			      (aSgln96Codec.getPartition() == sgln96.aSgln96Codec.getPartition()) &&
			      (aSgln96Codec.getCompanyPrefix() == sgln96.aSgln96Codec.getCompanyPrefix()) &&
			      (aSgln96Codec.getLocationReference() == sgln96.aSgln96Codec.getLocationReference()) &&
			      (aSgln96Codec.getExtComponent() == sgln96.aSgln96Codec.getExtComponent());
		}
			
		return ret;
	}
}
