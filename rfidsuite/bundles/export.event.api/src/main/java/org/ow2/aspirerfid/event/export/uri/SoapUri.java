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

import java.net.MalformedURLException;
import java.net.URL;

import org.ow2.aspirerfid.util.RFIDConstants;

/**
 * SoapUri allow to parse internal Http Uri format.<br/>
 * 
 * Example of valid Soap Uri string :<br/>
 * "soap:http://localhost:8191/ReportService?wsdl"
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class SoapUri {
	// ******************************************
	// Private Members
	// ******************************************
	/**
	 * Web service http adress
	 */
	private URL httpAddress;

	// ******************************************
	// Constructor
	// ******************************************
	/**
	 * Constructor, build a Soap from a Uri string. Example of valide Uri string :
	 * "soap:http://localhost:8191/ReportService?wsdl"
	 * 
	 * @param uriToParse
	 *            Uri string to parse
	 * @throws BadUriFormatException
	 * @throws NotExpectedProtocolException
	 * @throws MalformedURLException
	 */
	public SoapUri(String uriToParse) throws NotExpectedProtocolException,
			BadUriFormatException, MalformedURLException {
		parseStringToUrl(uriToParse);
	}

	// ******************************************
	// Getters
	// ******************************************
	/**
	 * Gets http address of web service
	 * 
	 * @return hostname
	 */
	public URL getHttpAddress() {
		return httpAddress;
	}

	// ******************************************
	// Privates Methods
	// ******************************************
	/**
	 * Parse an Uri string to a SoapUri
	 * 
	 * @param UriToParse
	 *            Soap Uri string to parse
	 * @throws BadUriFormatException
	 *             Bad Uri Format
	 * @throws NotExpectedProtocolException
	 *             Not a SOAP Uri
	 * @throws BadUriFormatException
	 * @throws MalformedURLException
	 */
	private void parseStringToUrl(String UriToParse)
			throws NotExpectedProtocolException, BadUriFormatException,
			MalformedURLException {
		// Is a SOAP URI ?
		String protocol = UriParserHelper.getProtocol(UriToParse);
		if (protocol.compareToIgnoreCase(RFIDConstants.SOAP_PROTOCOL_NAME) != 0) {
			throw new NotExpectedProtocolException();
		}

		// remove the protocol field
		String uri = UriParserHelper.removeProtocolField(UriToParse);

		// check if adress is ok
		if (uri == "" || uri == null) {
			throw new BadUriFormatException();
		}
		protocol = UriParserHelper.getProtocol(uri);
		if (protocol.compareToIgnoreCase(RFIDConstants.HTTP_PROTOCOL_NAME) != 0) {
			throw new BadUriFormatException();
		}

		httpAddress = new URL(uri);
	}
}
