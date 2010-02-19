/*
 * Copyright © 2008-2010, Aspire
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
 * @author Loïc Schmidt e-mail: loic.schmidt@lifl.fr
 *
 *	<p>This class represents a ISO 15693 UID
 *      
 * @version Revision: 0.5 Date: 2009/11/23
 */

public class ISO15693 implements ISOSerial {

    //--------------------------/
    //- Class/Member Variables -/
    //--------------------------/

    /** 
     * int mfgCode represents the manufacturer ID according to ISO/IEC 7816-6/AM1 in decimal radix
     */
	private int mfgCode;
    /** 
     * BigInteger isoSerial represents the serial number of the UID without the manufacturer ID
     * The value of isoSerial is encoded in the decimal radix
     */
	private BigInteger isoSerial;

    //---------------/
    //- Constructor -/
    //---------------/

    /**
     *	ISO15693 - constructor for a new ISO 15693 serial
     *	 
     *	@param input represents the UID in inbound representation
     *
     */
	public ISO15693(String input) throws TDTException {

		boolean isLegacy=input.startsWith("iso");
		boolean isTagEncoding=input.startsWith("urn:iso:tag");
		boolean isPureIdentity=input.startsWith("urn:iso:id");
		boolean isONSHostname=input.endsWith("onsiso.com");

		if(isLegacy) {
			// if the input is encoded in legacy format (numbers are decimal)
			this.mfgCode=Integer.parseInt(input.split("[;]")[1].split("[=]")[1]);
			this.isoSerial=new BigInteger(input.split("[;]")[2].split("[=]")[1]);
		} else if (isTagEncoding || isPureIdentity) {
			// on split avec ":"
			String[] infos=input.split(":");
			// on split le dernier element avec ".", et on obtient mfgCode et serial
			this.mfgCode=Integer.parseInt(infos[infos.length-1].split("[.]")[0]);
			this.isoSerial=new BigInteger(infos[infos.length-1].split("[.]")[1]);
		} else if (isONSHostname) {
			this.mfgCode=Integer.parseInt(input.split("[.]")[1]);
			this.isoSerial=new BigInteger(input.split("[.]")[0]);
		} else if (input.length()==16) {
			// if the input is encoded in hexa
			this.mfgCode=Integer.parseInt(input.substring(2,4),16);
		//	this.isoSerial=new BigInteger(new BigInteger(input.substring(4),16).toString(10));
			this.isoSerial=new BigInteger(input.substring(4),16);

		} else if(input.length()==64) {
			//  if the input is encoded in binary
			this.mfgCode=Integer.parseInt(input.substring(8,16),2);
			this.isoSerial=new BigInteger(input.substring(16),2);
		}
		// here we check that the serial can be encoded with the appropriate number of bytes
		if(this.isoSerial.bitLength()>48) throw new TDTException("UID higher than maximum authorized value");
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
		String outMfgCode=Integer.toHexString(this.mfgCode);
		String outSerial=this.isoSerial.toString(16);
		// adding leading zeros
		for (int i=outMfgCode.length();i<2;i++) outMfgCode="0"+outMfgCode;
		for (int i=outSerial.length();i<12;i++) outSerial="0"+outSerial;
		// adding header
		return "E0"+outMfgCode+outSerial;
	}

    /** 
     *	Translates the isoSerial in its binary representation
     * 
     *  @return output ISO tag code in binary
     */
	private String toBinaryString() {
		String outMfgCode=Integer.toBinaryString(this.mfgCode);
		String outSerial=this.isoSerial.toString(2);
		// adding leading zeros
		for (int i=outMfgCode.length();i<8;i++) outMfgCode="0"+outMfgCode;
		for (int i=outSerial.length();i<48;i++) outSerial="0"+outSerial;
		// adding header for iso 15693
		return "11100000"+outMfgCode+outSerial;
    	}

    /** 
     *	Translates the isoSerial in its legacy representation
     * 
     *  @return output ISO tag code in legacy
     */
	private String toLegacyString() {
		return "iso15693;mfgcode="+this.mfgCode+";serial="+this.isoSerial.toString();
	}

    /** 
     *	Translates the isoSerial in its TAG_ENCODING URI representation
     * 
     *  @return output ISO tag code in tag_encoding URI
     */
	private String toTagEncodingURI() {
		return "urn:iso:tag:15693-64:"+this.mfgCode+"."+this.isoSerial.toString();
	}

    /** 
     *	Translates the isoSerial in its PURE_IDENTITY URI representation
     * 
     *  @return output ISO tag code in pure identity URI
     */
	private String toPureIdentityURI() {
		return "urn:iso:id:15693:"+this.mfgCode+"."+this.isoSerial.toString();
	}

    /** 
     *	Translates the isoSerial in its ONS HOSTNAME representation
     * 
     *  @return output ISO tag code in ONS HOSTNAME
     */
	private String toONSHostname() {
		return this.isoSerial.toString()+"."+this.mfgCode+".15693.onsiso.com";
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
