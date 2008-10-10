/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.james.mailet.jms.util;

import java.util.Hashtable;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 * @version 2007
 */
public class HashtableParser {

	/**
	 * Parse String to Hastable<String,String>
	 * 
	 * CONTRAINTS : on the key : - key can't begin with space character
	 * 
	 * on the value : - can't contain close brace "}" or comma ","
	 * 
	 * example of good string to parse : {key3=v3, key2=v2, key1=v1}
	 * 
	 * 
	 * @param stringToParse
	 *            string to parse
	 * @return parsed string to a hashtable
	 * @throws Exception
	 *             Error in parsing, bad entry format
	 */
	public static Hashtable parseStringStringHashtable(String stringToParse)
			throws Exception {
		Hashtable hash = new Hashtable();

		// remove "{"
		int iOpenBrace = stringToParse.indexOf("{");
		if (iOpenBrace == -1) {
			throw new Exception("[Parsing error] '{' not found");
		}
		stringToParse = stringToParse.substring(iOpenBrace + 1);

		// for each tuples key/value
		boolean stop = false;
		String key = "";
		int iEndOfKey;
		String value = "";
		int iEndOfValue;
		while (!stop) {
			// ===========================================
			// get key
			// ===========================================
			// delete space before key
			while (stringToParse.startsWith(" ")) {
				stringToParse = stringToParse.substring(1);
			}

			iEndOfKey = stringToParse.indexOf("=");
			if (iEndOfKey > 0) {
				key = stringToParse.substring(0, iEndOfKey);
			} else {
				throw new Exception("[Parsing error] A key is empty");
			}

			// ===========================================
			// get value
			// ===========================================
			iEndOfValue = stringToParse.indexOf(",");
			// last element ?
			if (iEndOfValue == -1) {
				stop = true;
				iEndOfValue = stringToParse.indexOf("}");
			}
			if (iEndOfValue == -1) {
				throw new Exception(
						"[Parsing error] Bad ending, no close brace found");
			}

			value = stringToParse.substring(iEndOfKey + 1, iEndOfValue);

			// ===========================================
			// update string to parse
			// ===========================================
			if (iEndOfValue + 1 > stringToParse.length()) {
				throw new Exception("[Parsing error] End too early");
			} else {
				stringToParse = stringToParse.substring(iEndOfValue + 1);
			}

			// ===========================================
			// add in hashtable
			// ===========================================
			hash.put(key, value);
		}
		return hash;
	}
}
