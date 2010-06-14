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

package org.ow2.aspirerfid.ale.engine.input.connectors;

import org.ow2.aspirerfid.ale.codec.Sgln96Codec;


public class SGLN96Trigger {
	private byte filterValue;
	private long companyPrefix;
	private int locationReference;
	private long extComponent;
	
	/**
	 * 
	 * Constructor
	 */
	public SGLN96Trigger() {
		
	}
	
	/**
	 * Constructor
	 * @param filterValue
	 * @param companyPrefix
	 * @param locationReference
	 * @param extComponent
	 */
	public SGLN96Trigger(byte filterValue, long companyPrefix,
			int locationReference, long extComponent) {
		this.filterValue = filterValue;
		this.companyPrefix = companyPrefix;
		this.locationReference = locationReference;
		this.extComponent = extComponent;
	}

	/**
	 * Getter for filterValue
	 * @return the filterValue
	 */
	public byte getFilterValue() {
		return filterValue;
	}

	/**
	 * Getter for companyPrefix
	 * @return the companyPrefix
	 */
	public long getCompanyPrefix() {
		return companyPrefix;
	}

	/**
	 * Getter for locationReference
	 * @return the locationReference
	 */
	public int getLocationReference() {
		return locationReference;
	}

	/**
	 * Getter for extComponent
	 * @return the extComponent
	 */
	public long getExtComponent() {
		return extComponent;
	}

	/**
	 * Setter for filterValue
	 * @param filterValue the value to set
	 */
	public void setFilterValue(byte filterValue) {
		this.filterValue = filterValue;
	}

	/**
	 * Setter for companyPrefix
	 * @param companyPrefix the value to set
	 */
	public void setCompanyPrefix(long companyPrefix) {
		this.companyPrefix = companyPrefix;
	}

	/**
	 * Setter for locationReference
	 * @param locationReference the value to set
	 */
	public void setLocationReference(int locationReference) {
		this.locationReference = locationReference;
	}

	/**
	 * Setter for extComponent
	 * @param extComponent the value to set
	 */
	public void setExtComponent(long extComponent) {
		this.extComponent = extComponent;
	}

	/**
	 * Test if tag data wrapped by passed codec matches this sgln96.
	 * @param aSgln96Codec
	 * @return
	 */
	public boolean matches(Sgln96Codec aSgln96Codec) {
		boolean ret = false;
		
		if (aSgln96Codec!=null) {
			ret = (this.filterValue == aSgln96Codec.getFilterValue()) &&
		      	  (this.companyPrefix == aSgln96Codec.getCompanyPrefix()) &&
		          (this.locationReference == aSgln96Codec.getLocationReference()) &&
		          (this.extComponent == aSgln96Codec.getExtComponent());
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SGLN96Trigger [companyPrefix=");
		buffer.append(companyPrefix);
		buffer.append(", extComponent=");
		buffer.append(extComponent);
		buffer.append(", filterValue=");
		buffer.append(filterValue);
		buffer.append(", locationReference=");
		buffer.append(locationReference);
		buffer.append("]");
		return buffer.toString();
	}
	
	
	
}
