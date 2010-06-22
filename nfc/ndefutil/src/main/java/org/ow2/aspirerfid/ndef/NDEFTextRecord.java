/*
   Copyright 2005-2008, OW2 Aspire RFID project 
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.

   Contact: OW2 Aspire RFID project <X AT Y DOT org> (with X=aspirerfid and Y=ow2)

   LGPL version 2.1 full text http://www.gnu.org/licenses/lgpl-2.1.txt    
*/
package org.ow2.aspirerfid.ndef;

import javax.microedition.contactless.ndef.NDEFMessage;
import javax.microedition.contactless.ndef.NDEFRecord;
import javax.microedition.contactless.ndef.NDEFRecordType;

/**
 * This class represents a NDEF record containing a text
 * <p>TODO check the convertion of UTF8 instead of UNICODE (java.lang.String)
 * @link http://www.nfc-forum.org/specs
 * @author Didier Donsez
 */
public class NDEFTextRecord extends NDEFRecord {

	/**
	 * Constructor of a NDEF Record containing a text
     * @param lang ISO/IANA language code. Examples: "fi", "en-US", "fr-CA", "jp".
     * @param text  The encoding in US-ASCII
	 */
	public NDEFTextRecord(String lang, String text) {

        super(new NDEFRecordType(NDEFRecordType.NFC_FORUM_RTD, "urn:nfc:wkt:T"), null, null);

            // Append Payload Manually
        this.appendPayload(new byte[]{(byte)(lang.length())});
        this.appendPayload(lang.getBytes());
        this.appendPayload(text.getBytes());
	}
	// TODO should be removed and add as a JUnit test			
	public static void main(String args[]){
		
		StringBuffer sb=new StringBuffer();
		for (int i = 1; i < args.length; i++) {
			if(i!=0) sb.append(" ");
			sb.append(args[i]);
		}
		
	    NDEFMessage message = new NDEFMessage();
	    message.appendRecord(new NDEFTextRecord(args[0],sb.toString()));
		
		System.out.println(HexUtility.toHexString(message.toByteArray()," "));		
	}
}
