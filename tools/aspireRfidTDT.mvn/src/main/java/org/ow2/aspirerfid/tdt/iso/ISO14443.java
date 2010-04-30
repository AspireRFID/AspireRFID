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

import java.math.BigInteger;
import org.fosstrak.tdt.TDTException;

/**
 * @author Loic Schmidt e-mail: loic.schmidt@lifl.fr
 *
 *	<p>This class represents a ISO 14443 UID 
 *      
 * @version Revision: 0.5 Date: 2009/11/23
 */

public class ISO14443 implements ISOSerial {

    //--------------------------/
    //- Class/Member Variables -/
    //--------------------------/

    /** 
     * String iso represents the size of the number
     * that rules the encoded UID.
     * In 14443, there are three kind of UID : single, double or triple size UID
     * Permitted values are "single", "double" and "triple".
     */
	private String iso;

    /** 
     * int uid0 represents the manufacturer ID according to ISO/IEC 7816-6/AM1 in decimal radix
     * for iso 14443 with double or triple UID (not for single UID)
     */
	private int uid0;
    /** 
     * BigInteger isoSerial represents the serial number of the UID without the manufacturer ID
     * The value of isoSerial is encoded in the decimal radix
     */
	private BigInteger isoSerial;
    /** 
     * int serialSize is the size in bytes of the serial number of the UID without the manufacturer ID
     */
	private int serialSize;

    //---------------/
    //- Constructor -/
    //---------------/

    /**
     *	ISO14443 - constructor for a new ISO 14443 serial
     *	 
     *	@param input represents the UID in inbound representation
     *	
     */
	public ISO14443(String input) throws TDTException {

		boolean isLegacy=input.startsWith("iso");
		boolean isTagEncoding=input.startsWith("urn:iso:tag");
		boolean isPureIdentity=input.startsWith("urn:iso:id");
		boolean isONSHostname=input.endsWith("onsiso.com");

		if(isLegacy) {
			// if the input is encoded in legacy format (numbers are decimal)
			this.iso=input.split("[;]")[0].substring(8);
			this.uid0=Integer.parseInt(input.split("[;]")[1].split("[=]")[1]);
			this.isoSerial=new BigInteger(input.split("[;]")[2].split("[=]")[1]);

		} else if (isTagEncoding || isPureIdentity) {
			// on split avec ":"
			String[] infos=input.split(":");
			// on split le dernier element avec ".", et on obtient mfgCode et serial
			this.uid0=Integer.parseInt(infos[infos.length-1].split("[.]")[0]);
			this.isoSerial=new BigInteger(infos[infos.length-1].split("[.]")[1]);
			// on split l'avant dernier element avec "-" et on obtient iso
			this.iso=infos[infos.length-2].split("-")[0].substring(5);
		} else if (isONSHostname) {
			String[] infos=input.split("[.]");
			this.uid0=Integer.parseInt(infos[1]);
			this.isoSerial=new BigInteger(infos[0]);
			this.iso=infos[2].substring(5);
		} else if (input.length()<21) {
			// if the input is encoded in hexa
			if(input.length()==8) iso="single";
			else if(input.length()==14) iso="double";
			else if(input.length()==20) iso="triple";
			
			this.uid0=Integer.parseInt(input.substring(0,2),16);
			this.isoSerial=new BigInteger(input.substring(2),16);
		} else {
			//  if the input is encoded in binary
			int index=0;
			if(input.length()==32) iso="single";
			else if(input.length()==56) iso="double";
			else if(input.length()==80) iso="triple";

			this.uid0=Integer.parseInt(input.substring(0,8),2);
			this.isoSerial=new BigInteger(input.substring(8),2);
		}

		if(this.iso.equalsIgnoreCase("double")) this.serialSize=7;
		else if(this.iso.equalsIgnoreCase("single")) this.serialSize=4;
		else if(this.iso.equalsIgnoreCase("triple"))  this.serialSize=10;

		// here we check that the serial can be encoded with the appropriate number of bytes
		if(this.isoSerial.bitLength()>((this.serialSize-1)*8)) throw new TDTException("UID higher than maximum authorized value");
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
		String outMfgCode=Integer.toHexString(this.uid0);
		String outSerial=this.isoSerial.toString(16);
		// adding leading zeros
		for (int i=outMfgCode.length();i<2;i++) outMfgCode="0"+outMfgCode;
		for (int i=outSerial.length();i<((this.serialSize-1)*2);i++) outSerial="0"+outSerial;
		return outMfgCode+outSerial;
	}

    /** 
     *	Translates the isoSerial in its binary representation
     * 
     *  @return output ISO tag code in binary
     */
	private String toBinaryString() {
		String outMfgCode=Integer.toBinaryString(this.uid0);
		String outSerial=this.isoSerial.toString(2);
		// adding leading zeros
		for (int i=outMfgCode.length();i<8;i++) outMfgCode="0"+outMfgCode;
		for (int i=outSerial.length();i<((this.serialSize-1)*8);i++) outSerial="0"+outSerial;
		return outMfgCode+outSerial;
    	}

    /** 
     *	Translates the isoSerial in its legacy representation
     * 
     *  @return output ISO tag code in legacy
     */
	private String toLegacyString() {
		String outMfgCode=";serial="+this.isoSerial.toString();
		if(this.serialSize==4) outMfgCode=";uid0="+this.uid0+";";
		return "iso14443"+this.iso+";mfgcode="+this.uid0+outMfgCode;
	}

    /** 
     *	Translates the isoSerial in its TAG_ENCODING URI representation
     * 
     *  @return output ISO tag code in tag_encoding URI
     */
	private String toTagEncodingURI() {
		return "urn:iso:tag:14443"+this.iso+"-"+this.serialSize*8+":"+this.uid0+"."+this.isoSerial.toString();
	}

    /** 
     *	Translates the isoSerial in its PURE_IDENTITY URI representation
     * 
     *  @return output ISO tag code in pure identity URI
     */
	private String toPureIdentityURI() {
		return "urn:iso:id:14443"+this.iso+":"+this.uid0+"."+this.isoSerial.toString();
	}

    /** 
     *	Translates the isoSerial in its ONS HOSTNAME representation
     * 
     *  @return output ISO tag code in ONS HOSTNAME
     */
	private String toONSHostname() {
		return this.isoSerial.toString()+"."+this.uid0+".14443"+this.iso+".onsiso.com";
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
