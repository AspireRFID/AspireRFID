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

package org.ow2.aspirerfid.tdt.onewire;

import java.math.BigInteger;
import org.fosstrak.tdt.TDTException;
import com.dalsemi.onewire.utils.CRC8;
import com.dalsemi.onewire.utils.Convert;
import com.dalsemi.onewire.utils.Convert.ConvertException;




/**
 * @author Didier Donsez and Loic Schmidt



 *	<p>This class represents a OneWire UID
 *
 
http://www.maxim-ic.com/products/ibutton/ibuttons/
The Device Address (ie GUID)  is 64 bits long : CRC (8bits) + unique Device Id (48bits) + Family ID (8bits)
Family determines the services provided by the device (0x21 for temperature loggers)


For instance
urn:ow:id:5D0000091A7C2101
urn:signage/ibutton#5D0000091A7C2101



Acoording to Maxim "
Who Is Using the iButton? With over 175 million iButtons currently in circulation, the list of users is very long. You can read about many of the applications here."

*
 */

public class OneWire  {
	private String crc;
	private String devID;
	private String famID;

	public OneWire(String input) {
		// no matter the input format, we remove all non-numeric characters
		String decInput;
		if (input.length()>50) { //binary in input
			decInput=(new BigInteger(input,2)).toString(16);
			for (int i=(decInput.length());i<16;i++) decInput="0"+decInput;

		} else { // change for hexa
			if(input.startsWith("urn:")) { // TAG_ENCODING_URI
				// removing begining and dots
				decInput=input.substring(input.lastIndexOf(":")+1);
				decInput=decInput.replace(".","");
				decInput=decInput.replace("-","");

			} else if(input.endsWith(".com")) { // ONS_HOSTNAME
				// removing end and dots
				decInput=input.substring(0,15);
				decInput=decInput.replace(".","");

			} else if(input.startsWith("crc=")) { // LEGACY
				decInput=input.substring(4);
				decInput=decInput.replace(";deviceID=","");
				decInput=decInput.replace(";familyID=","");

			} else { // HEXA
				decInput=input.replace(" ","");
			}
		}

		//checking the CRC8
		try {
			String temp2=new String();				
			for (int i=decInput.length();i>=2;i-=2) temp2+=decInput.substring(i-2,i);

			if (!this.isValid(Convert.toByteArray(decInput))) {
				// may be bad endian
				if(!this.isValid(Convert.toByteArray(temp2))) throw new TDTException("Invalid CRC in iButton address: "+input);
			} else {
				decInput=temp2;
			}
		} catch (ConvertException e) {
			throw new TDTException("Invalid CRC in iButton address: "+input);
		}

		this.crc=decInput.substring(0,2);
		this.devID=decInput.substring(2,14);
		this.famID=decInput.substring(14);
	}

	public String toLegacy() {
		return "crc="+this.crc+";deviceID="+this.devID+";familyID="+this.famID;
	}

	public String toTagEncodingURI() {
		return "urn:onewire:ibutton:"+this.crc+"-"+this.devID+"-"+this.famID;
	}

	public String toHex() {
		return this.crc+this.devID+this.famID;
	}

	public String toBin() {
		String bin=(new BigInteger(this.toHex(),16)).toString(2);
		for (int i=(bin.length());i<64;i++) bin="0"+bin;
		return bin;
	}

	public String toONSHostname() {
		return this.famID+"."+this.devID+".ibutton.onsonewire.com";
	}

   private boolean isValid (byte[] address)
   {
      if ((address [0] != 0) && (CRC8.compute(address) == 0))
         return true;
      else if ((address[0]&0x7F) == 0x1C) // DS28E04
      {
         // The DS28E04 has a pin selectable ROM ID input.  However,
         // the CRC8 for the ROM ID assumes that the selecatable bits
         // are always 1.
         return 0 ==
            CRC8.compute(address, 2, 6,
               CRC8.compute(0x7F,
                  CRC8.compute(address[0], 0)));
      }
      else
         return false;
   }

}
