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
package org.ow2.aspirerfid.event.export.uri;

import java.util.regex.Pattern;

import org.ow2.aspirerfid.util.RFIDConstants;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class UriParserHelper {

	/**
	 * Gets Uri protocol
	 * 
	 * @param Uri
	 *            Uri string
	 * @return Uri protocol
	 * @throws BadUriFormatException
	 *             Bad Uri format, missing the protocol header
	 */
	public static String getProtocol(String Uri) throws BadUriFormatException {
		String[] splitted = split(Uri, RFIDConstants.PROTOCOL_SEPARATOR);
		if (splitted == null || splitted.length == 0) {
			throw new BadUriFormatException();
		}
		return splitted[0];
	}

	/**
	 * Split a String
	 * 
	 * @param stringToSplit
	 *            string to split
	 * @param separator
	 *            string separator
	 * @return array of splitted string
	 */
	public static String[] split(String stringToSplit, String separator) {
		Pattern pattern = Pattern.compile(separator);
		return pattern.split(stringToSplit);
	}

	/**
	 * Get a new string without the protocol field of the uri.
	 * 
	 * @param uri
	 *            uri
	 * @return uri without the protocol field
	 */
	public static String removeProtocolField(String uri) {
		int i = uri.indexOf(RFIDConstants.PROTOCOL_SEPARATOR);
		return uri.substring(i + 1);
	}
}
