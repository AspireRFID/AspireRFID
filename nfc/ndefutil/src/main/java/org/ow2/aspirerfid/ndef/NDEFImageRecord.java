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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.microedition.contactless.ndef.NDEFMessage;
import javax.microedition.contactless.ndef.NDEFRecord;
import javax.microedition.contactless.ndef.NDEFRecordType;

/**
 * This class provides utility methods to generate NDEF records and messages
 * @author Didier Donsez
 */
public class NDEFImageRecord extends NDEFRecord {

	/**
	 * Constructor of a NDEF Record containing a image
	 * @param urlstr
	 */
	public NDEFImageRecord(String mimetype, byte[] imageValue) {
        super(
        		new NDEFRecordType(NDEFRecordType.MIME, mimetype),
    			null,
    			imageValue
        );
	}
	
	// TODO should be removed and add as a JUnit test or as an utility class			
	public static void main(String args[]) throws IOException {
		InputStream is=(new URL(args[1])).openStream();
		
		//Read image from phone to ByteArrayOutputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int c;
        while ((c = is.read()) != -1) {
            baos.write(c);
        }
        is.close();
		
	    NDEFMessage message = new NDEFMessage();
	    message.appendRecord(new NDEFImageRecord(args[0],baos.toByteArray()));
		
		System.out.println(HexUtility.toHexString(message.toByteArray()," "));		
	}
}
