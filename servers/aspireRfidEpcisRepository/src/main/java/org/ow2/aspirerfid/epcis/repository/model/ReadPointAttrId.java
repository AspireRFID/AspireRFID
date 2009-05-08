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

import org.ow2.aspirerfid.epcis.repository.EpcisConstants;

/**
 * A vocabulary type for representing read point identifiers Attributes.
 * 
 * @author Nikos Kefalakis (nkef)
 */
public class ReadPointAttrId extends VocabularyAttributeElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4475114909759563464L;


	@Override
    public String getVocabularyType() {
        return EpcisConstants.READ_POINT_ID;
    }

}
