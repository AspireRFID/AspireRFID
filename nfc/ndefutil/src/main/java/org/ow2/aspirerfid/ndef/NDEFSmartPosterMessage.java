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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.microedition.contactless.ndef.NDEFMessage;

/**
 * This class represents a NDEF message containing a smart poster
 * @link http://www.nfc-forum.org/specs
 * @author Didier Donsez
 */
public class NDEFSmartPosterMessage extends NDEFMessage{

	public class NDEFRecommendedActionRecord //extends NDEFRecord
	{
		
	    /*
	    Recommended Action Record
	    0 : Do the action (send the SMS, launch the browser, make the telephone call)
	    1 : Save for later (store the SMS in INBOX, put the URI in a bookmark, save the telephone number in contacts)
	    2 : Open for editing (open an SMS in the SMS editor, open the URI in an URI editor, open the telephone number for editing).
	    */

//		public NDEFRecommendedActionRecord(byte recommendedAction) {
//			super();
//			// TODO Auto-generated constructor stub
//		}
		public final static byte DOACTION=0x00;
		public final static byte SAVEFORLATER=0x01;
		public final static byte OPENFOREDITING=0x02;
		
	}
	
		
	/**
	 * Constructor of a NDEF Message containing a smartposter
     * @param lang ISO/IANA language code. Examples: "fi", "en-US", "fr-CA", "jp".
     * @param text  The encoding in US-ASCII
	 */
	public NDEFSmartPosterMessage(String urlstr, /*icon,recommendedAction,*/ String[] langs, String[] texts) {
    		
			super();
			
			// URI
    	    this.appendRecord(new NDEFURIRecord(urlstr));
			
    	    // TODO Recommended Action record
    	    
    	    // TODO Icon record
    	    
    	    // Titles records
    	    for (int i = 0; i < texts.length; i++) {
    	        this.appendRecord(new NDEFTextRecord(langs[i], texts[i]));		
    		}
	}
	
	// TODO should be removed and add as a JUnit test or as an utility class
	public static void main(String args[]) throws IOException{

		NDEFSmartPosterMessage message;
		
		if(args.length==1) {
			Properties properties=new Properties();
			properties.load(new FileInputStream(args[0]));
		
			String urlstr=(String) properties.get("url");
			
			List langs=new ArrayList();
			List titles=new ArrayList();
			
			StringBuffer sb=new StringBuffer();
			for (int i = 0;; i++) {
				String title=(String) properties.get("title-"+i);
				if(title==null) break;
				String lang=(String) properties.get("lang-"+i);
				if(lang==null) lang="en";
				
				langs.add(lang);
				titles.add(title);	
			}
			
		    message = new NDEFSmartPosterMessage(
		    		args[0],
		    		(String[])langs.toArray(new String[langs.size()]),
				    (String[])titles.toArray(new String[titles.size()])
		    );
			
		} else {
		
			StringBuffer sb=new StringBuffer();
			for (int i = 2; i < args.length; i++) {
				if(i!=0) sb.append(" ");
				sb.append(args[i]);
			}
			
		    message = new NDEFSmartPosterMessage(
		    		args[0],
		    		new String[] {args[1]},
		    		new String[] {sb.toString()}
		    );
		}
		
		System.out.println(HexUtility.toHexString(message.toByteArray()," "));		
	}
}
