/*
 * Copyright (C) 2008-2010 Loic Schmidt - INRIA
 * 
 * This file is part of AspireRFID.
 *
 * AspireRFID is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * AspireRFID is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with AspireRFID. If not, see
 * <http://www.gnu.org/licenses/>.
 */


package org.ow2.aspirerfid.tdt;

import org.ow2.aspirerfid.tdt.iso.*;

import java.util.*;
import java.math.BigInteger;
import org.fosstrak.tdt.TDTException;

/**
 *
 *	<p>This class provides methods for translating ISO tags codes
 *      between six levels of representation:
 *      LEGACY, TAG_ENCODING_URI, PURE_IDENTITY_URI, ONS_HOSTNAME, HEXA and BINARY
 *      
 *      <p>The method convert is static.
 *
 * @version Revision: 0.5 Date: 2009/11/23
 */

public class ISOTDTEngine {

    //-----------/
    //- Methods -/
    //-----------/

    /** 
     *	Translates the input string to a specified output format
     *  (LEGACY, TAG_ENCODING_URI, PURE_IDENTITY_URI, ONS_HOSTNAME, HEXA or BINARY).
	  *  For example, the input
     *  string value may be encoded in HEXA format and
     *	the output format specified in the inputParameters by string
     *  'outputFormat' may be LEGACY, in which case
     *  the return value is a legacy representation expressed as a string.
     * 
     *
     *  @param input input tag code
     *  @param inputParameters a map with any additional properties.
     *  @return output iso tag coding
     */
	public static String convert(String input, Map<String,String> inputParameters) throws TDTException {
		// which ISO standard is it ?
		ISOSerial isoSerial=null;
		if(input.indexOf("15693")!=-1) isoSerial=new ISO15693(input);
		else if(input.indexOf("14443")!=-1) isoSerial=new ISO14443(input);
		else if(input.indexOf("18092")!=-1) isoSerial=new ISO18092(input);
		else if(input.indexOf("mac")!=-1) isoSerial=new ISOMAC(input); 
		else if(input.indexOf("oui")!=-1) isoSerial=new ISOMAC(input); // legacy for mac address
		else if (input.length()<21) {
			// if the input is encoded in hexa
			if(input.indexOf(":")!=-1) isoSerial=new ISOMAC(input);
			else if(input.length()==16) isoSerial=new ISO15693(input);
			else if ((input.length()==8) || (input.length()==14) || (input.length()==20)) {
				isoSerial=new ISO14443(input);
				// or 18092, how make the difference ?
			}
		} else if(input.length()>31) {
			//  if the input is encoded in binary
			if(input.length()==64) isoSerial=new ISO15693(input);
			else if (input.length()==48) isoSerial=new ISOMAC(input);
			else if((input.length()==32) || (input.length()==56) || (input.length()==80)){
				isoSerial=new ISO14443(input);
				// or 18092, how make the difference ?
			}
		} else throw new TDTException("Invalid ISO Format : "+input);
		return isoSerial.convert(inputParameters.get("outputFormat"));
    }

	


}
