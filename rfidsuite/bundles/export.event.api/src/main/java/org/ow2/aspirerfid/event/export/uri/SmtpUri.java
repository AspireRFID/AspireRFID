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

import org.ow2.aspirerfid.util.RFIDConstants;

/**
 * SmtpUri allow to parse internal Smtp Uri format.<br/>
 * 
 * Example of valide SMTP Uri string : <br/>
 * "smtp:localhost/25/from@localhost/to@localhost"
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class SmtpUri {
	// ******************************************
	// Private Members
	// ******************************************
	/**
	 * SMTP server hostname
	 */
	private String host;

	/**
	 * SMTP server port
	 */
	private String port;

	/**
	 * mail from
	 */
	private String mailFrom;

	/**
	 * mail to
	 */
	private String mailTo;

	// ******************************************
	// Constructor
	// ******************************************
	/**
	 * Constructor, build a SmtpUri from a Uri string. <br/>Example of valide
	 * Uri string : <br/>"smtp:localhost/25/from@localhost/to@localhost"
	 * 
	 * @param UriToParse
	 *            Uri string to parse
	 * @throws BadUriFormatException
	 * @throws NotExpectedProtocolException
	 */
	public SmtpUri(String UriToParse) throws NotExpectedProtocolException,
			BadUriFormatException {
		ParseStringToUri(UriToParse);
	}

	// ******************************************
	// Getters
	// ******************************************
	/**
	 * Gets hostname of SMTP server
	 * 
	 * @return hostname
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Gets mail from
	 * 
	 * @return mail from
	 */
	public String getMailFrom() {
		return mailFrom;
	}

	/**
	 * Gets mail to
	 * 
	 * @return mail to
	 */
	public String getMailTo() {
		return mailTo;
	}

	/**
	 * Gets SMTP server port
	 * 
	 * @return SMTP server port
	 */
	public String getPort() {
		return port;
	}

	// ******************************************
	// Privates Methods
	// ******************************************
	/**
	 * Parse an Uri string to a SmtpUri
	 * 
	 * @param UriToParse
	 *            Uri smtp string to parse
	 * @throws BadUriFormatException
	 *             Bad Uri Format
	 * @throws NotExpectedProtocolException
	 *             Not a SMTP Uri
	 * @throws BadUriFormatException
	 */
	private void ParseStringToUri(String UriToParse)
			throws NotExpectedProtocolException, BadUriFormatException {
		// Is a SMTP URI ?
		String protocol = UriParserHelper.getProtocol(UriToParse);
		if (protocol.compareToIgnoreCase(RFIDConstants.SMTP_PROTOCOL_NAME) != 0) {
			throw new NotExpectedProtocolException();
		}

		// remove the protocol field
		String uri = UriParserHelper.removeProtocolField(UriToParse);

		// read the information of the uri
		String[] splitted = UriParserHelper.split(uri,
				RFIDConstants.FIELD_SEPARATOR);
		if (splitted.length != 4) {
			throw new BadUriFormatException();
		}

		host = splitted[0];
		port = splitted[1];
		mailFrom = splitted[2];
		mailTo = splitted[3];
	}
}
