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
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.microedition.contactless.ndef.NDEFMessage;
import javax.microedition.contactless.ndef.NDEFRecord;
import javax.microedition.contactless.ndef.NDEFRecordType;

/**
 * This utility class signs or checks a NDEF message
 * <p>Signed messages contains 2 records ending the message: one for the signer and one for the signature 
 * <p>TODO provides cryto API available in J2ME and JavaSE (java.security)  
 * @link http://www.nfc-forum.org/specs
 * @author Didier Donsez
 */
public class NDEFMessageSigner {

	public class NDEFSignerRecord extends NDEFRecord {
		
		public static final byte X509_DN_IDENTITY=0x00;
		
		public NDEFSignerRecord(byte typeCode, String identity) {
	
	        super(new NDEFRecordType(NDEFRecordType.NFC_FORUM_RTD, "urn:nfc:wkt:X"), null, null);
		
	        // Append Payload Manually
	        super.appendPayload(new byte[] {typeCode} );
	        super.appendPayload(identity.getBytes());
		}
	}
	
	public class NDEFSignatureRecord extends NDEFRecord {

		public static final byte DSA_ALGO=0x00;
		
		public NDEFSignatureRecord(byte[] bytesToSign, byte algoCode, Signature signature, PrivateKey privateKey) throws InvalidKeyException, SignatureException {
	
	        super(new NDEFRecordType(NDEFRecordType.NFC_FORUM_RTD, "urn:nfc:wkt:S"), null, null);
	
			signature.initSign(privateKey);
			signature.update(bytesToSign, 0, bytesToSign.length);

	        // Append Payload Manually
	        super.appendPayload(new byte[] {algoCode} );
	        super.appendPayload(signature.sign());
		}
	}

	public static void signMessage(NDEFMessage message, String signer, String keystorepw, String keypw) {

		NDEFRecord signerRecord=null;
		NDEFRecord signatureRecord=null;	
	
		message.appendRecord(signerRecord);
		
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		NDEFRecord[] records=message.getRecords();
		for (int i = 0; i < records.length; i++) {
			try {
				baos.write(records[i].toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// TODO compute signatureRecord from baos.toByteArray()
		
		message.appendRecord(signatureRecord);		
	}

	public static boolean checkMessage(NDEFMessage message, String signer, String keystorepw, String keypw) {

		NDEFRecord[] records=message.getRecords();
				
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		for (int i = 0; i < records.length-1; i++) {
			try {
				baos.write(records[i].toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// TODO check baos.toByteArray() with signatureRecord

		NDEFRecord signerRecord=records[records.length-2];
		NDEFRecord signatureRecord=records[records.length-1];
	
		return true;		
	}

	// TODO should be removed and add as a JUnit test or as an utility class
	public static void main(String args[]){
		
		StringBuffer sb=new StringBuffer();
		for (int i = 5; i < args.length; i++) {
			if(i!=0) sb.append(" ");
			sb.append(args[i]);
		}
		
		String signer=args[0];
		String keystorepass=args[1];
		String keypass=args[2];
		
	    NDEFSmartPosterMessage message = new NDEFSmartPosterMessage(
	    		args[3],
	    		new String[] {args[4]},
	    		new String[] {sb.toString()}
	    );
		
		System.out.println(HexUtility.toHexString(message.toByteArray()," "));		
	}
}
