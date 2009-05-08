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
 * A base class for vocabulary attribute elements.
 * 
 * @author Nikos Kefalakis (nkef)
 */
public abstract class VocabularyAttributeElement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3825662827697962041L;
	
	private String privateID;
	private String value;
	private VocabularyAttrCiD vocabularyAttrCiD;


	public VocabularyAttributeElement() {
		this.privateID = java.util.UUID.randomUUID().toString();
	}

	public int hashCode() {
		return privateID.hashCode();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public VocabularyAttrCiD getVocabularyAttrCiD() {
		return vocabularyAttrCiD;
	}

	public void setVocabularyAttrCiD(VocabularyAttrCiD vocabularyAttrCiD) {
		this.vocabularyAttrCiD = vocabularyAttrCiD;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof VocabularyAttributeElement) {
			VocabularyAttributeElement that = (VocabularyAttributeElement) o;
			return eq(this.value, that.value) && eq(this.vocabularyAttrCiD, that.vocabularyAttrCiD);
		}
		else {
			return false;
		}
	}

	/**
	 * The formal name of the vocabulary to which this element belongs.
	 */
	public abstract String getVocabularyType();

}
