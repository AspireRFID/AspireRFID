/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.impl.core.msg.command;

/**
 * This class is used as a helper class in the text parser. During
 * parsing the field value pair parameters  are parsed into such a 
 * <code>PairParameter</code> object.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TextCommandParserHelper
 *
 */

public class PairParameter extends Parameter {

	/** the TagField name */
	private String field;
	
	/** the value */
	private String value;
	
	/**
	 * Constructor for a new <code>PairParameter</code>, a pair of
	 * a field name and its value.
	 * @param tagField The field name
	 * @param value The value
	 */
	public PairParameter(String tagField, String value) {
		setField(tagField);
		setValue(value);
	}

	/**
	 * @return Returns the tag field.
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field The tag field to set.
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
