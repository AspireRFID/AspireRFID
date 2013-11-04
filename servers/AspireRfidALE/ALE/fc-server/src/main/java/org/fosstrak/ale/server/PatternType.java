/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Fosstrak (www.fosstrak.org).
 *
 * Fosstrak is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Fosstrak is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Fosstrak; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.fosstrak.ale.server;

import org.fosstrak.ale.exception.ECSpecValidationException;

/**
 * This enumeration defines the possible pattern types.
 * 
 * @author regli
 * @author benoit.plomion@orange.com
 */
public enum PatternType {

	//ORANGE: add tagFormat EPC for Filtering : sgtin-96, sscc-96, grai-96
	
	GID_96,
	SGTIN_64,
	SSCC_64,
	SGTIN_96,
	SSCC_96,
	GRAI_96,
	
	//added by Rim Driss
	SGTIN_198,
	GSGLN_96,
	GSGLN_195,
	GRAI_170,
	GIAI_96,
	GIAI_202,
	USDOD_96,
	GID,
	SGTIN,
	SSCC,
	GSGLN,
	GRAI,
	GIAI,
	USDOD;

	
	private static final String GID_96_STRING = "gid-96";
	private static final String SGTIN_64_STRING = "sgtin-64";
	private static final String SSCC_64_STRING = "sscc-64";
	private static final String SGTIN_96_STRING = "sgtin-96";
	private static final String SSCC_96_STRING = "sscc-96";
	private static final String GRAI_96_STRING = "grai-96";
	
	//added by Rim Driss
	private static final String SGTIN_198_STRING = "sgtin-198";
	private static final String GSGLN_96_STRING = "gsgln-96";
	private static final String GSGLN_195_STRING = "gsgln-195";
	private static final String GRAI_170_STRING = "grai-170";
	private static final String GIAI_96_STRING = "giai-96";
	private static final String GIAI_202_STRING = "giai-202";
	private static final String USDOD_96_STRING = "usdod-96";
	private static final String GID_STRING = "gid";
	private static final String SGTIN_STRING = "sgtin";
	private static final String SSCC_STRING = "sscc";
	private static final String GSGLN_STRING = "gsgln";
	private static final String GRAI_STRING = "grai";
	private static final String GIAI_STRING = "giai";
	private static final String USDOD_STRING = "usdod";
	//-------------------------------------------
	
	private static final int GID_96_DATAFIELDS = 3;
	private static final int SGTIN_64_DATAFIELDS = 4;
	private static final int SSCC_64_DATAFIELDS = 3;
	private static final int SGTIN_96_DATAFIELDS = 4;
	private static final int SSCC_96_DATAFIELDS = 3;
	private static final int GRAI_96_DATAFIELDS = 3;
	
	//added by Rim Driss
	private static final int GID_DATAFIELDS = 3;
	private static final int SGTIN_DATAFIELDS = 3;
	private static final int SSCC_DATAFIELDS = 2;
	private static final int GSGLN_DATAFIELDS = 3;
	private static final int GRAI_DATAFIELDS = 3;
	private static final int GIAI_DATAFIELDS = 2;
	private static final int USDOD_DATAFIELDS = 2;
	private static final int SGTIN_198_DATAFIELDS = 4;
	private static final int GSGLN_96_DATAFIELDS = 4;
	private static final int GSGLN_195_DATAFIELDS = 4;
	private static final int GRAI_170_DATAFIELDS = 4;
	private static final int GIAI_96_DATAFIELDS = 3;
	private static final int GIAI_202_DATAFIELDS = 3;
	private static final int USDOD_96_DATAFIELDS = 3;
	//-------------------------------------------
	
	
	/**
	 * This method returns the pattern type of a types string represenation.
	 * 
	 * @param type string representation of the type
	 * @return pattern type
	 * @throws ECSpecValidationException if the string representation is invalid
	 */
	public static PatternType getType(String type) throws ECSpecValidationException {

		if (GID_96_STRING.equals(type)) {
			return GID_96;
		} else if (SGTIN_64_STRING.equals(type)) {
			return SGTIN_64;
		} else if (SSCC_64_STRING.equals(type)) {
			return SSCC_64;	
		}else if (SGTIN_96_STRING.equals(type)){
			return SGTIN_96;
		}else if (SSCC_96_STRING.equals(type)){
			return SSCC_96;
		}else if (GRAI_96_STRING.equals(type)){
			return GRAI_96;
		//Added by Rim Driss
		} else if (SGTIN_96_STRING.equals(type)) {
			return SGTIN_96;
		} else if (SGTIN_198_STRING.equals(type)) {
			return SGTIN_198;
		} else if (SSCC_96_STRING.equals(type)) {
			return SSCC_96;
		} else if (GSGLN_96_STRING.equals(type)) {
			return GSGLN_96;
		} else if (GSGLN_195_STRING.equals(type)) {
			return GSGLN_195;
		} else if (GRAI_96_STRING.equals(type)) {
			return GRAI_96;
		} else if (GRAI_170_STRING.equals(type)) {
			return GRAI_170;
		} else if (GIAI_96_STRING.equals(type)) {
			return GIAI_96;
		} else if (GIAI_202_STRING.equals(type)) {
			return GIAI_202;
		} else if (USDOD_96_STRING.equals(type)) {
			return USDOD_96;
		} else if (GID_STRING.equals(type)) {
			return GID;
		} else if (SGTIN_STRING.equals(type)) {
			return SGTIN;
		} else if (SSCC_STRING.equals(type)) {
			return SSCC;
		} else if (GSGLN_STRING.equals(type)) {
			return GSGLN;
		} else if (GRAI_STRING.equals(type)) {
			return GRAI;
		} else if (GIAI_STRING.equals(type)) {
			return GIAI;
		} else if (USDOD_STRING.equals(type)) {
			return USDOD;
		//-------------------------
		}else {
			throw new ECSpecValidationException("Unknown Tag Format '" + type + "'. Known formats are" +
					" '" + GID_96_STRING + "', '" + SGTIN_64_STRING + "', '" + SGTIN_96_STRING + "' and '" + SSCC_64_STRING + "', '" + SSCC_96_STRING + "', '" + GRAI_96_STRING + "'.");
		}
		
	}
	
	/**
	 * This method returns the number of data fields the pattern type has.
	 * 
	 * @return number of data fields
	 */
	public int getNumberOfDatafields() {
		
		if (this == GID_96) {
			return GID_96_DATAFIELDS;
		} else if (this == SGTIN_64) {
			return SGTIN_64_DATAFIELDS;
		} else if (this == SSCC_64) {
			return SSCC_64_DATAFIELDS;
		} else if (this == SGTIN_96) {
			return SGTIN_96_DATAFIELDS;
		}else if (this == SSCC_96) {
			return SSCC_96_DATAFIELDS;
		}else if (this == GRAI_96) {
			return GRAI_96_DATAFIELDS;
		
		//Added by Rim Driss	
		} else if (this == SGTIN_96) {
			return SGTIN_96_DATAFIELDS;
		} else if (this == SGTIN_198) {
			return SGTIN_198_DATAFIELDS;
		} else if (this == SSCC_96) {
			return SSCC_96_DATAFIELDS;
		} else if (this == GSGLN_96) {
			return GSGLN_96_DATAFIELDS;
		} else if (this == GSGLN_195) {
			return GSGLN_195_DATAFIELDS;
		} else if (this == GRAI_96) {
			return GRAI_96_DATAFIELDS;
		} else if (this == GRAI_170) {
			return GRAI_170_DATAFIELDS;
		} else if (this == GIAI_96) {
			return GIAI_96_DATAFIELDS;
		} else if (this == GIAI_202) {
			return GIAI_202_DATAFIELDS;
		} else if (this == USDOD_96) {
			return USDOD_96_DATAFIELDS;
		} else if (this == GID) {
			return GID_DATAFIELDS;
		} else if (this == SGTIN) {
			return SGTIN_DATAFIELDS;
		} else if (this == SSCC) {
			return SSCC_DATAFIELDS;
		} else if (this == GSGLN) {
			return GSGLN_DATAFIELDS;
		} else if (this == GRAI) {
			return GRAI_DATAFIELDS;
		} else if (this == GIAI) {
			return GIAI_DATAFIELDS;
		} else if (this == USDOD) {
			return USDOD_DATAFIELDS;
		//------------------
		}else {
			return -1;
		}
		
	}
	
	/**
	 * This method returns a string representation of the pattern type.
	 * 
	 * @return string representation of the pattern type
	 */
	public String toSring() {
		
		if (this == GID_96) {
			return GID_96_STRING;
		} else if (this == SGTIN_64) {
			return SGTIN_64_STRING;
		} else if (this == SSCC_64) {
			return SSCC_64_STRING;
		} else if (this == SGTIN_96) {
			return SGTIN_96_STRING;
		} else if (this == SSCC_96) {
			return SSCC_96_STRING;
		} else if (this == GRAI_96) {
			return GRAI_96_STRING;
		
		//Added by Rim Driss
		} else if (this == SGTIN_96) {
			return SGTIN_96_STRING;
		} else if (this == SGTIN_198) {
			return SGTIN_198_STRING;
		} else if (this == SSCC_96) {
			return SSCC_96_STRING;
		} else if (this == GSGLN_96) {
			return GSGLN_96_STRING;
		} else if (this == GSGLN_195) {
			return GSGLN_195_STRING;
		} else if (this == GRAI_96) {
			return GRAI_96_STRING;
		} else if (this == GRAI_170) {
			return GRAI_170_STRING;
		} else if (this == GIAI_96) {
			return GIAI_96_STRING;
		} else if (this == GIAI_202) {
			return GIAI_202_STRING;
		} else if (this == USDOD_96) {
			return USDOD_96_STRING;
		} else if (this == GID) {
			return GID_STRING;
		} else if (this == SGTIN) {
			return SGTIN_STRING;
		} else if (this == SSCC) {
			return SSCC_STRING;
		} else if (this == GSGLN) {
			return GSGLN_STRING;
		} else if (this == GRAI) {
			return GRAI_STRING;
		} else if (this == GIAI) {
			return GIAI_STRING;
		} else if (this == USDOD) {
			return USDOD_STRING;
		//--------------------------------
			
		} else {
			return null;
		}
		
	}
	
}