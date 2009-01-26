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
package org.ow2.aspirerfid.util;

/**
 * Declare constants used in RFID project, by more than one service.
 * 
 * @author Anne Robert
 * @version 2006
 */
public class RFIDConstants {

	// ******************************************//
	// Constants use for Uri parsing //
	// ******************************************//
	/**
	 * Character which following the protocol
	 */
	public static final String PROTOCOL_SEPARATOR = ":";

	/**
	 * Uri field separator
	 */
	public static final String FIELD_SEPARATOR = "/";

	/**
	 * Define a SMTP protocol in Uri string format
	 */
	public static final String SMTP_PROTOCOL_NAME = "smtp";

	/**
	 * Define a SOAP protocol in Uri string format
	 */
	public static final String SOAP_PROTOCOL_NAME = "soap";

	/**
	 * Define a HTTP protocol in Uri string format
	 */
	public static final String HTTP_PROTOCOL_NAME = "http";

	/**
	 * Define a JMS protocol in Uri string format
	 */
	public static final String JMS_PROTOCOL_NAME = "jms";

	/**
	 * Define a WS protocol in Uri string format
	 */
	public static final String WS_PROTOCOL_NAME = "ws";

	/**
	 * Define protocol to connect to JMS server
	 */
	public static final String PROVIDER_URL_PROTOCOL = "rmi://";

	// ******************************************//
	// Miscellaneous //
	// ******************************************//
	/**
	 * the key associated with the text of the XML report
	 */
	public static final String REPORT_ENTRY = "XMLReport";

	/**
	 * topic used to publish RFID presence detection event from an RFID reader
	 */
	public static final String TOPIC_DETECTION = "org/ow2/aspirerfi/rfid/DETECTION";

	/**
	 * key associated with the reader guid in a reader event
	 */
	static public final String READERGUID_KEY = "rfid.readerguid";

	/**
	 * key associated with the tag guid in a reader event
	 */
	static public final String TAGGUID_KEY = "rfid.tagguid";

	/**
	 * key associated with the reader logical name reader event
	 */
	static public final String READERNAME_KEY = "rfid.readername";

	/**
	 * key associated with the reader GPS coordinates reader event
	 */
	static public final String COORDINATES_KEY = "rfid.readercoordinates";

	/**
	 * key associated with the temperature of the reader event
	 */
	public static final String MEASUREMENT_KEY = "rfid.measurement";

	/**
	 * The default duration of an event cycle in milliseconds
	 */
	static public final long DEFAULT_EC_DURATION = 10000;

	/**
	 * The destination uri to send reports
	 */
	static public final String DEST_URI = "rfid.destination.uri";

	/**
	 * The destination uri to send reports
	 */
	public static final String MESSAGE_ID = "message.id";

	/**
	 * The destination uri to send reports
	 */
	public static final String GATEWAY_NAME = "gateway.name";

}
