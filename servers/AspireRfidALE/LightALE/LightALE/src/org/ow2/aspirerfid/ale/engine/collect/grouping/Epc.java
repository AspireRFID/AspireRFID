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
import org.ow2.aspirerfid.ale.codec.EpcCodec;
import org.ow2.aspirerfid.ale.codec.UnsignedBigInt;

public abstract class Epc {
	public final static byte MAX_URI_FIELDS = 4;
	
	protected UriRepresentation uriRepresentation;
	
	protected EncScheme type;
	
	protected byte[] tagData;
	
	/**
	 * This array holds wrappers for manipulating uri fields as numbers on 
	 * more than 64 bits if necessary.
	 * These wrappers are created and provided to children.
	 */
	protected final UnsignedBigInt[] uriFields = new UnsignedBigInt[MAX_URI_FIELDS];
	
	/**
	 * 
	 * Constructor
	 */
	public Epc() {
		// initialize fields
		for (int i = 0; i< MAX_URI_FIELDS; i++)
			uriFields[i] = new UnsignedBigInt();
	}
	
	/**
	 * Sets tag Data, by reference.
	 * To be overloaded by children to set up fields
	 * @param tagData
	 */
	public void setTagData(byte[] tagData) {
		this.tagData = tagData;
		this.type = EpcCodec.getEncodingScheme(tagData);		
	}
		
	public EncScheme getType() {
		return this.type;
	}
	
	public int getNumFields() {
		return type.getNumFields();
	}
	
	/**
	 * sets the Epc wrapper representation for grouping
	 * 
	 * @param header must be {@link UriRepresentation#EPC_PURE} or {@link UriRepresentation#EPC_TAG}
	 * 
	 * @throws IllegalArgumentException if header is none of {@link UriRepresentation#EPC_PURE} or {@link UriRepresentation#EPC_TAG}
	 */
	public abstract void setRepresentation(UriRepresentation header) throws IllegalArgumentException;
	
	/**
	 * To be implemented by children. Depends on the encoding scheme.
	 * This method is intended to return the field as an UnsignedBigInt.
	 * the returned value may be a Byte, Integer, Long, or even BigInteger...
	 * @param index the field index [0..NumFields[
	 * @return field output field value
	 * @throws IndexOutOfBoundsException if given index out of bounds
	 */
	public abstract UnsignedBigInt getField(int index) throws IndexOutOfBoundsException;    
		
	/**
	 * Conversion Utilities: to be implemented by children.
	 * Using {@link EpcCodec} and its children.
	 */
	public abstract String getPureUri();	
	public abstract String getTagUri();	
	public abstract String getRawDecUri();	
	public abstract String getRawHexUri();
	
	/**
	 * Comparison utility : to be implemented by children.
	 */
	public abstract boolean equals( Epc tag);
}
