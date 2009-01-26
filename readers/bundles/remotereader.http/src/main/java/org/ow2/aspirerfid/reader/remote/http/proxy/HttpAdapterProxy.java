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

package org.ow2.aspirerfid.reader.remote.http.proxy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

/**
 * Utility class to send a RFID event by HTTP to the Aspire HTTP adapter
 * @author Didier Donsez
 */
public class HttpAdapterProxy {

	/**
	 * Utility method to send a RFID event by HTTP to the Aspire HTTP adapter
	 * @param urlPrefix the prefix of the servlet processing the 
	 * @param tagId the tag id
	 * @param readerId the reader id
	 * @return true if the response code is not 4xx or 5xx
	 * @throws IOException
	 * @TODO take into account 3xx response codes 
	 * @TODO take into account HttpsURLConnection 
	 */
	public static boolean sendTag(String urlPrefix,String tagId, String readerId) throws IOException {
	  MessageFormat urlTemplate=null;
	  if(readerId==null) {
	    urlTemplate=new MessageFormat("{0}?id={1}&timestamp={2}");
	  } else {
	    urlTemplate=new MessageFormat("{0}?id={1}&timestamp={2}&readerid={3}");
	  }

	  Object[] args = {urlPrefix, tagId, new Long(System.currentTimeMillis()).toString(), readerId};

	  URL url = new URL(urlTemplate.format(args));
	  URLConnection conn = url.openConnection();
	  HttpURLConnection c = (HttpURLConnection) conn;
	  int responseCode=c.getResponseCode();
	  System.out.println("Response Code for : " + url.toString());
	  if(responseCode >=300) {
		  return false;
	  } else {
		  return true;		  
	  }
	}
	  
	/**
	 * Utility method to send a RFID event by HTTP to the Aspire HTTP adapter without be blocking
	 * @param urlPrefix the prefix of the servlet processing the 
	 * @param tagId the tag id
	 * @param readerId the reader id
	 */
	public static void sendAsyncTag(String urlPrefix,String tagId, String readerId) {
		  System.err.println("Not implemented");	
	}
}
