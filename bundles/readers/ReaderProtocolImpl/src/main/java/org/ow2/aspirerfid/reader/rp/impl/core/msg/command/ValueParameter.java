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
 * parsing the simple value parameters  are parsed into such a 
 * <code>ValueParameter</code> object.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 * @see org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TextCommandParserHelper
 *
 */

public class ValueParameter extends Parameter {
	

	
	/** the parameter value */
	private Object value;
	
	/** the parameter type */
	private Class type;

	public ValueParameter(String val) {
		setValue(val);
	}
	
	public ValueParameter(int val) {
		setValue(val);
	}
	
	public ValueParameter(boolean val) {
		setValue(val);
	}

	/**
	 * Sets the String value of a parameter
	 * @param val The parameter value
	 */
	public void setValue(String val) {
		this.value = val;
		this.type = String.class;
	}
	
	/**
	 * Sets the int value of a parameter
	 * @param val The parameter value
	 */
	public void setValue(int val) {
		this.value = new Integer(val);
		this.type = Integer.class;
	}
	
	/**
	 * Sets the boolean value of a parameter
	 * @param val The parameter value
	 */
	public void setValue(boolean val) {
		this.value = new Boolean(val);
		this.type = Boolean.class;
	}
	

	/**
	 * Gets the string value of a parameter
	 * @return the parameter value
	 * @throws TextCommandParserException if the internal parameter value is not of type String
	 */
	public String getStringValue() throws TextCommandParserException {
		if (value instanceof String) {
			return (String)value;
		}
		throw new TextCommandParserException("Wrong parameter type. The value is of type " + type + " and not a String.");
	}
	
	/**
	 * Gets the int value of a parameter
	 * @return the parameter value
	 * @throws TextCommandParserException if the internal parameter value is not of type int
	 */
	public int getIntValue() throws TextCommandParserException {
		if (value instanceof Integer) {
			return ((Integer)value).intValue();
		}
		throw new TextCommandParserException("Wrong parameter type. The value is of type " + type + " and not an int.");
	}
	
	/**
	 * Gets the boolean value of a parameter
	 * @return the parameter value
	 * @throws TextCommandParserException if the internal parameter value is not of type boolean
	 */
	public boolean getBooleanValue() throws TextCommandParserException {
		if (value instanceof Boolean) {
			return ((Boolean)value).booleanValue();
		}
		throw new TextCommandParserException("Wrong parameter type. The value is of type " + type + " and not a boolean.");
	}
	
}
