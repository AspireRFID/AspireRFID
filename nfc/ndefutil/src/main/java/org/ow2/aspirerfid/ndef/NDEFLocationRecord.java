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
 * This class represents NDEF records containing a location (ie
 * latitude,longitude,altitude,bearing)
 * <p>
 * All fields in the payload are represented in the IEEE 754 floating-point
 * "single format" bit layout
 * 
 * @see java.lang.Float#floatToIntBits(float)
 * @author Didier Donsez
 */
public class NDEFLocationRecord extends NDEFRecord {
	
	/**
	 * Constructor of a NDEF Record containing a location
	 * 
	 * @param latitude
	 *            the latitude of the location in radian (Systeme
	 *            International)
	 * @param longitude
	 *            the latitude of the location in radian (Systeme
	 *            International)
	 */
	public NDEFLocationRecord(float latitude, float longitude) {
        super(new NDEFRecordType(NDEFRecordType.NFC_FORUM_RTD, "urn:nfc:wkt:L"), null, null);
        // Append Payload Manually
        this.appendPayload(convertFloatToByteArray(latitude));
        this.appendPayload(convertFloatToByteArray(longitude));
	}

	/**
	 * Constructor of a NDEF Record containing a location
	 * 
	 * @param latitude
	 *            the latitude of the location in radian (Systeme
	 *            International)
	 * @param longitude
	 *            the latitude of the location in radian (Systeme
	 *            International)
	 * @param altitude
	 *            the altitude of the location in meter (Systeme International)
	 *            TODO the altitude could be short to save 2 bytes in the record
	 */
	public NDEFLocationRecord(float latitude, float longitude, float altitude) {
        super(new NDEFRecordType(NDEFRecordType.NFC_FORUM_RTD, "urn:nfc:wkt:L"), null, null);
        // Append Payload Manually
        this.appendPayload(convertFloatToByteArray(latitude));
        this.appendPayload(convertFloatToByteArray(longitude));
        this.appendPayload(convertFloatToByteArray(altitude));
	}

	/**
	 * Constructor of a NDEF Record containing a location
	 * 
	 * @param latitude
	 *            the latitude of the location in radian (Systeme
	 *            International)
	 * @param longitude
	 *            the latitude of the location in radian (Systeme
	 *            International)
	 * @param altitude
	 *            the altitude of the location in meter (Systeme International)
	 * @param bearing
	 *            the bearing at this location in radian (Systeme International)
	 *            TODO the altitude could be short in order to save 2 bytes in
	 *            the record TODO the bearing could be degree (0.. 360)
	 *            represented as a short in order to save 2 bytes in the record
	 */
	public NDEFLocationRecord(float latitude, float longitude, float altitude, float bearing) {
        super(new NDEFRecordType(NDEFRecordType.NFC_FORUM_RTD, "urn:nfc:wkt:L"), null, null);
        // Append Payload Manually
        this.appendPayload(convertFloatToByteArray(latitude));
        this.appendPayload(convertFloatToByteArray(longitude));
        this.appendPayload(convertFloatToByteArray(altitude));
        this.appendPayload(convertFloatToByteArray(bearing));
	}

	public float getLattitude() {
		return Float.NaN;
	}

	public float getLongitude() {
		return Float.NaN;
	}

	public float getAltitude() {
		return Float.NaN;
	}

	public float getBearing() {
		return Float.NaN;
	}

	/**
	 * represents a float in a big endian 4-byte array
	 * @link http://en.wikipedia.org/wiki/Endianness
	 */
    static byte[] convertFloatToByteArray(float value){
    	int in=Float.floatToIntBits(value);
    	byte ba[]=new byte[4];
    	for (int i=3; i>=0; i--){
    		ba[i] = new Integer (in>>(3-i)*8).byteValue();
    	}
    	return ba;
    }

    // TODO should be removed and add as a JUnit test or an utility class
	public static void main(String args[]){
				
	    NDEFMessage message = new NDEFMessage();
	    switch(args.length){
	    case 2:
	    	message.appendRecord(new NDEFLocationRecord(Float.parseFloat(args[0]),Float.parseFloat(args[1])));
	    	break;
	    case 3:
	    	message.appendRecord(new NDEFLocationRecord(Float.parseFloat(args[0]),Float.parseFloat(args[1]),Float.parseFloat(args[2])));
	    	break;
		case 4:
			message.appendRecord(new NDEFLocationRecord(Float.parseFloat(args[0]),Float.parseFloat(args[1]),Float.parseFloat(args[2]),Float.parseFloat(args[3])));
			break;
		default: System.out.println("incorrect parameter numbers"); return;		
	    }
		System.out.println(HexUtility.toHexString(message.toByteArray()," "));		
}
