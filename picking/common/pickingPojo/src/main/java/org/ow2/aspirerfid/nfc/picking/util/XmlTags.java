/*
 *  Copyright (C) Aspire
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.ow2.aspirerfid.nfc.picking.util;

/**
 * TODO Javadoc
 * 
 * @author Maroula Perisanidi
 * @version 1.0.0 01/05/2008
 */
public interface XmlTags {
	/**
	 * Constant containing the String used in the XML to describe the maximum
	 * range tag.
	 */
	String FINAL = "final";
	/**
	 * Constant containing the String used in the XML to describe minimum range
	 * tag.
	 */
	String INIT = "init";
	/**
	 * Constant containing the String used in the XML to describe the product
	 * tag.
	 */
	String PROD = "prod";
	/**
	 * Constant containing the String used in the XML to describe the list of
	 * products tag.
	 */
	String PROD_LIST = "prodList";
	/**
	 * Constant containing the String used in the XML to describe the name tag.
	 */
	String PROD_NAME = "name";
	/**
	 * Constant containing the String used in the XML to describe the quantity
	 * tag.
	 */
	String QUANTITY = "quantity";
	/**
	 * Constant containing the String used in the XML to describe the range tag.
	 */
	String RANGE = "range";
	/**
	 * Constant containing the String used in the XML to describe the ranges
	 * tag.
	 */
	String RANGES = "ranges";
	/**
	 * Constant containing the String used in the XML to describe the command
	 * that sends the list of products to be collected.
	 */
	String SEND_LIST = "receive.list";

	/**
	 * TODO Javadoc
	 */
	void getString();
}
