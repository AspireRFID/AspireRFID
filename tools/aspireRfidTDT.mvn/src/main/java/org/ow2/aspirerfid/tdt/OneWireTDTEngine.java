/*
 * Copyright (C) 2008-2010, Aspire
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

package org.ow2.aspirerfid.tdt;

import org.fosstrak.tdt.TDTException;
import java.util.*;
import org.ow2.aspirerfid.tdt.onewire.*;



/**
 * @author Loic Schmidt e-mail: loic.schmidt@lifl.fr
 *
 *	<p>This class provides methods for translating iButton address
 *      between five levels of representation:
 *      LEGACY, TAG_ENCODING_URI,ONS_HOSTNAME, HEXA and BINARY
 *      
 *      <p>The method convert is static.
 *
 * @version Revision: 0.6 Date: 2010/04/26
 */

public class OneWireTDTEngine {

    //-----------/
    //- Methods -/
    //-----------/

    /** 
     *	Translates the input string to a specified output format
     *  (LEGACY, TAG_ENCODING_URI, ONS_HOSTNAME, HEXA or BINARY).
	  *  For example, the input
     *  string value may be encoded in BINARY format and
     *	the output format specified in the inputParameters by string
     *  'outputFormat' may be LEGACY, in which case
     *  the return value is a legacy representation expressed as a string.
     * 
     *
     *  @param input input OneWire ID
     *  @param inputParameters a map with any additional properties.
     *  @return output OneWire ID in output format
     */
	public static String convert(String input, Map<String,String> inputParameters) throws TDTException {
		OneWire num=new OneWire(input);
		
		String outputFormat=inputParameters.get("outputFormat");
		if (outputFormat.equalsIgnoreCase("BINARY")) return num.toBin();
		if (outputFormat.equalsIgnoreCase("HEXA")) return num.toHex();
		if (outputFormat.equalsIgnoreCase("LEGACY")) return num.toLegacy();
		if (outputFormat.equalsIgnoreCase("TAG_ENCODING")) return num.toTagEncodingURI();
		if (outputFormat.equalsIgnoreCase("ONS_HOSTNAME")) return num.toONSHostname();

		//default return LEGACY
		return num.toLegacy();
	}


}

