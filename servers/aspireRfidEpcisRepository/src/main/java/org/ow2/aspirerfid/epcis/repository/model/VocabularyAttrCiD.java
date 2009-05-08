/*
 * Copyright © 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.epcis.repository.model;

import static org.ow2.aspirerfid.epcis.repository.Utils.eq;

import java.io.Serializable;

/**
 * A vocabulary type for representing business step identifiers Attributes
 * 
 * @author Nikos Kefalakis (nkef)
 */
public class VocabularyAttrCiD implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5025973585282842235L;
	
	private String privateID;
	private Long id;
	private String attribute;
	
	public VocabularyAttrCiD() {
		this.privateID = java.util.UUID.randomUUID().toString();
	}

	public int hashCode() {
		return privateID.hashCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof VocabularyAttrCiD) {
			VocabularyAttrCiD that = (VocabularyAttrCiD) o;
			return eq(this.id, that.id) && eq(this.attribute, that.attribute);
		}
		else {
			return false;
		}

	}

}
