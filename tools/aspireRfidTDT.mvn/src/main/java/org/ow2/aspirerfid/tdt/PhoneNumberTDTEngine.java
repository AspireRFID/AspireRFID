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

import java.util.*;
import org.fosstrak.tdt.TDTException;

/**
 *
 *	<p>This class provides methods for translating Phone Numbers
 *      between five levels of representation:
 *      LEGACY, TAG_ENCODING_URI, PURE_IDENTITY_URI, ONS_HOSTNAME and BINARY
 *      
 *      <p>The method convert is static.
 *
 * @version Revision: 0.5 Date: 2009/11/23
 */

public class PhoneNumberTDTEngine {

    //-----------/
    //- Methods -/
    //-----------/

    /** 
     *	Translates the input string to a specified output format
     *  (LEGACY, TAG_ENCODING_URI, PURE_IDENTITY_URI, ONS_HOSTNAME or BINARY).
	  *  For example, the input
     *  string value may be encoded in BINARY format and
     *	the output format specified in the inputParameters by string
     *  'outputFormat' may be LEGACY, in which case
     *  the return value is a legacy representation expressed as a string.
     * 
     *
     *  @param input input phone number
     *  @param inputParameters a map with any additional properties.
     *  @return output phone number representation
     */
	public static String convert(String input, Map<String,String> inputParameters) throws TDTException {
		// what representation in input
		boolean isLegacy=input.startsWith("country");
		boolean isTagEncoding=input.startsWith("urn:phonenumber:tag");
		boolean isPureIdentity=input.startsWith("urn:phonenumber:id");
		boolean isONSHostname=input.endsWith("onsphonenumber.com");
		boolean isDec=input.startsWith("+");
		boolean isBin=input.startsWith("00101011");

		// cleaning if isDec (remove space and dot)
		if(isDec) {
			input=input.replace(".","");
			input=input.replace(" ","");
		}

		// check inputParameters presences
		if (!isTagEncoding && !inputParameters.containsKey("countryprefixlength"))
			throw new TDTException("No length available for phone number country prefix in parameters");
		byte countryprefixlength;
		if(isTagEncoding)	countryprefixlength=Byte.parseByte(input.substring(20,21));
		else countryprefixlength=Byte.parseByte(inputParameters.get("countryprefixlength"));

		short countryPrefix=0,countryPrefixMax=0;
		long localNumber;
		String outputFormat=inputParameters.get("outputFormat");

		
		if(Byte.valueOf(countryprefixlength)==1)	countryPrefixMax=9;
		else if(Byte.valueOf(countryprefixlength)==2)	countryPrefixMax=99;
		else if(Byte.valueOf(countryprefixlength)==3)	countryPrefixMax=999;



		if(isDec) { // input in decimal representation aka "+XXXXXXXXX"
			if (input.length()>16)  throw new TDTException("Invalid phone number (length)");
			countryPrefix=Short.parseShort(input.substring(1,countryprefixlength+1));
			localNumber=Long.parseLong(input.substring(countryprefixlength+1));
		} else if(isLegacy) {
			countryPrefix=Short.parseShort(input.split("[;]")[0].split("[=]")[1]);
			localNumber=Long.parseLong(input.split("[;]")[1].split("[=]")[1]);
		} else if(isTagEncoding || isPureIdentity) {
			// on split le dernier element avec "."
			String[] infos=input.substring(input.lastIndexOf(":")+1).split("[.]");
			// le prefix est l'avant dernier et le number est le dernier
			countryPrefix=Short.parseShort(infos[infos.length-2]);
			localNumber=Long.parseLong(infos[infos.length-1]);
		} else if(isONSHostname) {
			countryPrefix=Short.parseShort(input.split(".")[1]);
			localNumber=Long.parseLong(input.split(".")[0]);
		} else if(isBin) {
			// on a encode chaque digits du country prefix sur 4 bits
			String cp=input.substring(8,8+(countryprefixlength*4));
			for (int i=0; i<countryprefixlength; i++) {
				countryPrefix*=10;
				countryPrefix+=Short.parseShort(cp.substring(i*4,(i+1)*4),2);
			}
			// le reste est le numero
			localNumber=Long.parseLong(input.substring(8+(countryprefixlength*4)),2);
		} else {
			throw new TDTException("Invalid input representation");	
		}

		if (countryPrefix>countryPrefixMax)  throw new TDTException("Invalid phone number (country prefix)");		
	

		// return in output representation
		if (outputFormat.equalsIgnoreCase("BINARY")) {
			// encoding country prefix digits on four bits
			String cp2="";
			String cp3="";
			for (int i=0; i<countryprefixlength; i++) {
				// retrieve each number composing country prefix
				cp2=Integer.toBinaryString(Integer.parseInt(String.valueOf(String.valueOf(countryPrefix).charAt(i))));
				// adding leading zeros to fill 4 bits
				int len=cp2.length();
				for (int j=0;j<(4-len);j++) cp2="0"+cp2;
				// concat strings
				cp3+=cp2;
			}
			return "00101011"+cp3+Long.toBinaryString(localNumber);
		} else if (outputFormat.equalsIgnoreCase("DECIMAL")) return "+"+Short.toString(countryPrefix)+Long.toString(localNumber);
		else if (outputFormat.equalsIgnoreCase("LEGACY")) return "countryprefix="+Short.toString(countryPrefix)+";number="+Long.toString(localNumber);
		else if (outputFormat.equalsIgnoreCase("TAG_ENCODING")) return "urn:phonenumber:tag:"+countryprefixlength+"."+Short.toString(countryPrefix)+"."+Long.toString(localNumber);
		else if (outputFormat.equalsIgnoreCase("PURE_IDENTITY")) return "urn:phonenumber:id:"+Short.toString(countryPrefix)+"."+Long.toString(localNumber);
		else if (outputFormat.equalsIgnoreCase("ONS_HOSTNAME")) return Long.toString(localNumber)+"."+Short.toString(countryPrefix)+".onsphonenumber.com";
		return null;


	}
}
