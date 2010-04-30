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

package org.ow2.aspirerfid.tdt.gsma;

import java.math.BigInteger;
import org.fosstrak.tdt.TDTException;




/**
 * @author Didier Donsez and Loic Schmidt

International Mobile Equipment Identity (IMEI)
----------------------------------------------
For mobile phones identification
Proposer : Didier Donsez
http://en.wikipedia.org/wiki/International_Mobile_Equipment_Identity


see http://tools.ietf.org/html/draft-montemurro-gsma-imei-urn-04

For instance
urn:gsma:imei:90420156-025763-0
urn:gsma:imeisv:90420156-025763-42

*
 */

public class IMEI  {
	private String tac;
	private String snr;
	private String third; // check digit for IMEI or sw version number for IMEISV

	public IMEI(String input) {
		// no matter the input format, we remove all non-numeric characters
		String decInput;
		if (input.length()>50) { //binary in input
			decInput=(new BigInteger(input,2)).toString(16);
		} else { // no matter the format
			decInput=input.replaceAll("[^0-9]","");
		}

		//checking the length of the input for a valid IMEI or IMEISV
		if (decInput.length()<15 || decInput.length()>16) throw new TDTException("Invalid IMEI number: "+input);

		this.tac=decInput.substring(0,8);
		this.snr=decInput.substring(8,14);
		this.third=decInput.substring(14);

		//checking the Check Digit in case of IMEI
		if (this.third.length()==1) {
			int cd=0;
			for (int i=0;i<14;i++) {
				int digit=Integer.parseInt(decInput.substring(i,i+1));
				if (i%2!=0) digit*=2;
				if (digit>9) {
					cd+=digit%10;
					cd+=digit/10;
				} else {
					cd+=digit;
				}
			}
			if (Integer.parseInt(this.third)!=(10-(cd%10))) throw new TDTException("Invalid IMEI check digit: "+input+" - "+(10-(cd%10)));
		}
	}

	public String toLegacy() {
		String title=";cd=";
		if (this.third.length()>1) title=";svn=";
		return "tac="+this.tac+";snr="+this.snr+title+this.third;
	}

	public String toTagEncodingURI() {
		String title="urn:gsma:imei:";
		if (this.third.length()>1) title="urn:gsma:imeisv:";
		return title+this.tac+"-"+this.snr+"-"+this.third;
	}

	public String toDec() {
		return this.tac+this.snr+this.third;
	}

	public String toBin() {
		String bin=(new BigInteger(this.tac+this.snr+this.third,16)).toString(2);
		for (int i=(bin.length());i<64;i++) bin="0"+bin;
		return bin;
	}

	public String toONSHostname() {
		String title=".imei.onsgsma.com";
		if (this.third.length()>1) title=".imeisv.onsgsma.com";
		return this.third+"."+this.snr+"."+this.tac+title;
	}

}
