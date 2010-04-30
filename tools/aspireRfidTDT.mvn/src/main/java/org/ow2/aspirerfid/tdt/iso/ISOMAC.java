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

package org.ow2.aspirerfid.tdt.iso;

import org.fosstrak.tdt.TDTException;

/**
 * @author Loic Schmidt e-mail: loic.schmidt@lifl.fr
 *
 *	<p>This class represents a MAC ADDRESS
 *      
 * @version Revision: 0.5 Date: 2009/11/23
 */

public class ISOMAC implements ISOSerial {

    //--------------------------/
    //- Class/Member Variables -/
    //--------------------------/

    /** 
     * int oui represents the Organizationally Unique Identifier according to ISO/IEC 10032 in decimal radix
     */
	private int oui;
    /** 
	  * int address represents the last 24 bits of the MAC ADDRESS
     * The value of address is encoded in the decimal radix
     */
	private int address;

    //---------------/
    //- Constructor -/
    //---------------/

    /**
     *	ISOMAC - constructor for a new ISO MAC ADDRESS serial
     *	 
     *	@param input represents the UID in inbound representation
     *
     */
	public ISOMAC(String input) throws TDTException {

		boolean isLegacy=input.startsWith("oui");
		boolean isTagEncoding=input.startsWith("urn:iso:tag");
		boolean isPureIdentity=input.startsWith("urn:iso:id");
		boolean isONSHostname=input.endsWith("onsiso.com");

		if(isLegacy) {
			// if the input is encoded in legacy format (numbers are decimal)
			this.oui=Integer.parseInt(input.split("[;]")[0].split("[=]")[1]);
			this.address=Integer.parseInt(input.split("[;]")[1].split("[=]")[1]);
		} else if (isTagEncoding || isPureIdentity) {
			// on split avec ":"
			String[] infos=input.split("[:]");
			// on split le dernier element avec ".", et on obtient mfgCode et serial
			this.oui=Integer.parseInt(infos[infos.length-1].split("[.]")[0]);
			this.address=Integer.parseInt(infos[infos.length-1].split("[.]")[1]);
		} else if (isONSHostname) {
			this.oui=Integer.parseInt(input.split("[.]")[1]);
			this.address=Integer.parseInt(input.split("[.]")[0]);
		} else if(input.length()==48) {
			//  if the input is encoded in binary
			this.oui=Integer.parseInt(input.substring(0,24),2);
			this.address=Integer.parseInt(input.substring(24),2);
		} else {
			// if the input is encoded in hexa
			// remove ":"
			String input2=input.replace(":","");
			this.oui=Integer.parseInt(input2.substring(0,6),16);
			this.address=Integer.parseInt(input2.substring(6),16);
		}
		// here we check that the mac address can be encoded with the appropriate number of bytes
		if(this.oui>16777215) throw new TDTException("MAC ADDRESS higher than maximum authorized value");
		if(this.address>16777215) throw new TDTException("MAC ADDRESS higher than maximum authorized value");
	}

    //-----------/
    //- Methods -/
    //-----------/

    /** 
     * Translates the isoSerial in its Hexadecimal representation
     *
     *  @return output ISO tag code in hexadecimal
     */

	private String toHexString() {
		String outOui=Integer.toHexString(this.oui);
		String outSerial=Integer.toHexString(this.address);
		// adding leading zeros
		for (int i=outOui.length();i<6;i++) outOui="0"+outOui;
		for (int i=outSerial.length();i<6;i++) outSerial="0"+outSerial;
		//return outOui+outSerial;
		// needs of the ":" ?
		String code=outOui+outSerial;
		String r = ""+code.charAt(0);
	   for (int i = 1; i < code.length(); i ++) {
	      if (i%2==0) r+=":";
			r += code.charAt(i);
      }
	   return r;
	}

    /** 
     *	Translates the isoSerial in its binary representation
     * 
     *  @return output ISO tag code in binary
     */
	private String toBinaryString() {
		String outOui=Integer.toBinaryString(this.oui);
		String outSerial=Integer.toBinaryString(this.address);
		// adding leading zeros
		for (int i=outOui.length();i<24;i++) outOui="0"+outOui;
		for (int i=outSerial.length();i<24;i++) outSerial="0"+outSerial;
		return outOui+outSerial;
    	}

    /** 
     *	Translates the isoSerial in its legacy representation
     * 
     *  @return output ISO tag code in legacy
     */
	private String toLegacyString() {
		return "oui="+this.oui+";address="+this.address;
	}

    /** 
     *	Translates the isoSerial in its TAG_ENCODING URI representation
     * 
     *  @return output ISO tag code in tag_encoding URI
     */
	private String toTagEncodingURI() {
		return "urn:iso:tag:mac:"+this.oui+"."+this.address;
	}

    /** 
     *	Translates the isoSerial in its PURE_IDENTITY URI representation
     * 
     *  @return output ISO tag code in pure identity URI
     */
	private String toPureIdentityURI() {
		return "urn:iso:id:mac:"+this.oui+"."+this.address;
	}

    /** 
     *	Translates the isoSerial in its ONS HOSTNAME representation
     * 
     *  @return output ISO tag code in ONS HOSTNAME
     */
	private String toONSHostname() {
		return this.address+"."+this.oui+".mac.onsiso.com";
	}



    /** 
     *	Convert the isoSerial in other representation
     * 
     *  @param outputFormat format representation for output
     *  @return output ISO tag code in the new representation
     */
	public String convert(String outputFormat) {
		if (outputFormat.equalsIgnoreCase("BINARY")) return this.toBinaryString();
		if (outputFormat.equalsIgnoreCase("HEXA")) return this.toHexString();
		if (outputFormat.equalsIgnoreCase("LEGACY")) return this.toLegacyString();
		if (outputFormat.equalsIgnoreCase("TAG_ENCODING")) return this.toTagEncodingURI();
		if (outputFormat.equalsIgnoreCase("PURE_IDENTITY")) return this.toPureIdentityURI();
		if (outputFormat.equalsIgnoreCase("ONS_HOSTNAME")) return this.toONSHostname();
		return null;
	}






}
