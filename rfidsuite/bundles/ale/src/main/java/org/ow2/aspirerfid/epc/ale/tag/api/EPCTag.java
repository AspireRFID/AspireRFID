/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.epc.ale.tag.api;

import java.util.Iterator;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 * @version 2007
 */
public interface EPCTag {

	/**
	 * format of tag ID representation: General Identifier Format
	 */
	static final public String GENERAL_IDENT_FORMAT = "gid-96";
	/**
	 * format of tag ID representation: Serialized Global Trade Item Number on
	 * 96 bits
	 */
	static final public String SERIALIZED_96_FORMAT = "sgtin-96";
	/**
	 * format of tag ID representation: Serialized Global Trade Item Number on
	 * 198 bits
	 */
	static final public String SERIALIZED_198_FORMAT = "sgtin-198";
	/**
	 * format of tag ID representation: Serialized Shipping Container Code on 96
	 * bits
	 */
	static final public String SERIAL_SHIPPING_FORMAT = "sscc-96";
	/**
	 * format of tag ID representation: Serial Global Location Number on 96 bits
	 */
	static final public String SERIAL_GLOBAL_96_FORMAT = "sgln-96";
	/**
	 * format of tag ID representation: Serial Global Location Number on 195
	 * bits
	 */
	static final public String SERIAL_GLOBAL_195_FORMAT = "sgln-195";
	/**
	 * format of tag ID representation: Global Returnable Asset Identifier on 96
	 * bits
	 */
	static final public String GLOBAL_RETURNABLE_96_FORMAT = "grai-96";
	/**
	 * format of tag ID representation: Global Returnable Asset Identifier on
	 * 170 bits
	 */
	static final public String GLOBAL_RETURNABLE_170_FORMAT = "grai-170";
	/**
	 * format of tag ID representation: Global Individual Asset Identifier on 96
	 * bits
	 */
	static final public String GLOBAL_INDIVIDUAL_96_FORMAT = "giai-96";
	/**
	 * format of tag ID representation: Global Individual Asset Identifier on
	 * 202 bits
	 */
	static final public String GLOBAL_INDIVIDUAL_202_FORMAT = "giai-202";
	/**
	 * format of tag ID representation: Dod format 202 bits
	 */
	static final public String DOD_FORMAT = "dod-96";

	/**
	 * if no known format is recognized, the format is the following
	 */
	static final public String UNKNOWN_FORMAT = "UNKNOWN";

	/**
	 * function to get the EPC-URI (Pure identity)
	 * 
	 * @return the string representation of the EPC-URI returns a null string if
	 *         the EPC do not correspond to a known format
	 */
	public abstract String getEpcUri();

	/**
	 * determine if the EPC is a Tag or Raw URI.
	 * 
	 * @return true if the EPC is a Tag URI.
	 */
	public abstract boolean isTagUri();

	/**
	 * function to get the EPC tag URI
	 * 
	 * @return the string representation of the EPC tag URI returns a null
	 *         string if the EPC does not correspond to a tag format
	 *         (isTagUri()=false)
	 */
	public abstract String getTagUri();

	/**
	 * function to get the EPC raw URI in hexadecimal format
	 * 
	 * @return the string representation of the EPC tag URI
	 */
	public abstract String getRawHexUri();

	/**
	 * function to get the EPC raw URI in decimal format
	 * 
	 * @return the string representation of the EPC tag URI
	 */
	public abstract String getRawDecimalUri();

	/**
	 * comparison function between 2 tag data
	 * 
	 * @param obj
	 *            the object to be compared
	 * @return true if the 2 tags are logically equal
	 */
	public abstract boolean equals(Object obj);

	/**
	 * function to extract part of EPC naming
	 * 
	 * @return the Tag coding format (GID-96, DGTIN-96...)
	 */
	public abstract String getTagFormat();

	/**
	 * function to extract part of EPC naming
	 * 
	 * @return the EPC representation format (GID, DGTIN...)
	 */
	public abstract String getEpcFormat();

	/**
	 * Get the fields of the representation.
	 * 
	 * @return an iterator on the fields of the Epc representation
	 */
	public abstract Iterator getFieldIter();

}
