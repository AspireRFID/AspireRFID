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

package org.ow2.aspirerfid.rp.imp.caen;

import org.ow2.aspirerfid.rp.api.DataSelector;
import org.ow2.aspirerfid.rp.api.RPException;
import org.ow2.aspirerfid.rp.api.enumerations.EventType;
import org.ow2.aspirerfid.rp.api.enumerations.FieldName;
import org.ow2.aspirerfid.rp.api.enumerations.PredefinedTagFieldName;


public class CAENDataSelector implements DataSelector, EventType, FieldName {
	/**
	 * Data Selector object name.
	 */
	private String name;
	
	/**
	 * @see FieldName
	 */
	private String[] fieldNames;
	
	/**
	 * @see EventType
	 */
	private String[] eventFilters;
	
	/**
	 * Tag fields : example ID, Kill password, etc.
	 * @see PredefinedTagFieldName.
	 */
	private String[] tagFieldNames;	

	/**
	 * Constructor
	 * @param name
	 * @param fieldNames
	 * @param eventFilters
	 * @param tagFieldNames
	 */
	protected CAENDataSelector(String name, String[] fieldNames,
			String[] eventFilters, String[] tagFieldNames) {
		this.name = name;
		this.fieldNames = fieldNames;
		this.eventFilters = eventFilters;
		this.tagFieldNames = tagFieldNames;
	}
	
	/**
	 * 
	 * Default constructor Constructor
	 */
	protected CAENDataSelector(String name) {
		this.name = name;
		this.fieldNames = FieldName.MANDATORY_FIELDS;
		this.eventFilters = new String[0]; 	// empty
		this.tagFieldNames = new String[1];
		this.tagFieldNames[0] = PredefinedTagFieldName.tagID;
	}

	public void addEventFilters(String[] events) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;
	}

	public void addFieldNames(String[] fields) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void addTagFieldNames(String[] fieldNames) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public String[] getAllEventFilters() throws RPException {
		return this.eventFilters;
	}

	public String[] getAllFieldNames() throws RPException {
		return this.fieldNames;
	}

	public String[] getAllTagFieldNames() throws RPException {
		return this.tagFieldNames;
	}

	public String getName() throws RPException {
		return this.name;
	}

	public void removeAllEventFilters() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void removeAllFieldNames() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void removeAllTagFieldNames() throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void removeEventFilters(String[] events) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void removeFieldNames(String[] fields) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}

	public void removeTagFieldNames(String[] fieldNames) throws RPException {
		throw RPException.NOT_SUPPORTED_OPERATION;

	}
	
	/////////////////////////////////
	// EventType Implementation    //
	/////////////////////////////////
	/**
	 * No events support.
	 */
	public String[] getSupportedTypes() {
		return null;
	}

	/////////////////////////////////
	// FieldName Implementation    //
	/////////////////////////////////
	/**
	 * Only mandatory fields are supported.
	 */
	public String[] getSupportedNames() {
		return FieldName.MANDATORY_FIELDS;
	}

}
