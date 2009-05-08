/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.impl.core.msg;


/**
 * Constant values used in the Reader Protocol.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 *
 */
public interface MessagingConstants {
	/* ------------------ GENERAL CONSTANTS ------------------ */
	/** end of line character(s) */
	public static final String EOL = "\r\n";
	
	/** identifier for the TCP protocol */
	public static final String PROTOCOL_TCP = "tcp";
	
	/** identifier for the HTTP protocol */
	public static final String PROTOCOL_HTTP = "http";

	/* ------------------ TCP CONSTANTS ------------------ */
	/** end of header character(s) */
	public static final String EOH = ":";

	/* ------------------ HTTP CONSTANTS ------------------ */
	/** the HTTP POST method */
	public static final String POST = "POST";
	
	/** the HTTP Version should be used */
	public static final String HTTP_VERSION = "HTTP/1.1";
	
	/** the HTTP content length */
	public static final String HTTP_CONTENT_LENGTH = "Content-Length";
	
	/** the content type, corresponds to sender-format-request */
	public static final String HTTP_CONTENT_TYPE = "Content-Type";
	
	/** the host parameter required by HTTP 1.1 */
	public static final String HTTP_HOST = "Host";
	
	/** the OK status */
	public static final int HTTP_OK = 200;
	
	/* 4XX: client error */
	/** 400 bad request */
	public static final int HTTP_BAD_REQUEST = 400;
	
	/** 401 unauthorized */
	public static final int HTTP_UNAUTHORIZED = 401;
	
	/** 402 paymnet required */
	public static final int HTTP_PAYMENT_REQUIRED = 402;
	
	/** 403 forbidden */
	public static final int HTTP_FORBIDDEN = 403;
	
	/** 404 not found */
	public static final int HTTP_NOT_FOUND = 404;
	
	/** 405 bad method */
	public static final int HTTP_BAD_METHOD = 405;
	
	/** 406 not acceptable */
	public static final int HTTP_NOT_ACCEPTABLE = 406;
	
	/**407 proxy auth */
	public static final int HTTP_PROXY_AUTH = 407;
	
	/** 408 client timeout */
	public static final int HTTP_CLIENT_TIMEOUT = 408;
	
	/** 409 http conflict */
	public static final int HTTP_CONFLICT = 409;
	
	/** 410 gone */
	public static final int HTTP_GONE = 410;
	
	/** 411 length required */
	public static final int HTTP_LENGTH_REQUIRED = 411;
	
	/** 412 precon failed */
	public static final int HTTP_PRECON_FAILED = 412;
	
	/** 413 entity too large */
	public static final int HTTP_ENTITY_TOO_LARGE = 413;
	
	/** 414 request too long */
	public static final int HTTP_REQ_TOO_LONG = 414;
	
	/** 415 unsupported */
	public static final int HTTP_UNSUPPORTED_TYPE = 415;
	
	
	/* Names of the handshaking parameters in the HTTP header */

	
	/** octet-stream type corresponds to the binary message format B1 */
	public static final String HTTP_CONTENT_TYPE_BINARY = "application/octet-stream";
	
	/** text message format */
	public static final String HTTP_CONTENT_TYPE_TEXT = "text/plain; charset=utf-8";
	
	/** xml message format */
	public static final String HTTP_CONTENT_TYPE_XML = "text/xml; charset=utf-8";
		
	/** sender-signature */
	public static final String RP_SENDER_SIGNATURE = "RP-Sender-Signature";
	
	/** spec-version-request */
	public static final String RP_SPEC_VERSION_REQUEST = "RP-Spec-Version";
	
	/** receiver-format-request */
	public static final String RP_RESPONSE_CONTENT_TYPE = "RP-Response-Content-Type";
	
	/** ack-nak-request */
	public static final String RP_RESPONSE_ACKNAK = "RP-Response-ACKNAK";
	
	/** receiver-signature */
	public static final String RP_RECEIVER_SIGNATURE = "RP-Receiver-Signature";
	
	/** spec-version-response */
	public static final String RP_SPEC_VERSION_OK = "RP-Spec-Version-OK";
	
	/** sender-format-response */
	public static final String RP_REQUEST_CONTENT_TYPE_OK = "RP-Request-Content-Type-OK";
	
	/** receiver-format-response */
	public static final String RP_RESPONSE_CONTENT_TYPE_OK = "RP-Response-Content-Type-OK";
	
	/** ack-nak-response */
	public static final String RP_RESPONSE_ACKNAK_OK = "RP-Response-ACKNAK-OK";
	
	/* ------------------ Error Handling Constants  ------------------*/
	/** no error */
	public static final int NO_ERROR = 0;
	/** no error */
	public static final String NO_ERROR_STR = "NO_ERROR";
	
	/** Unspecified error */
	public static final int ERROR_UNKNOWN = 1;
	/** Unspecified error */
	public static final String ERROR_UNKNOWN_STR = "ERROR_UNKNOWN";
	
	/** The command is not supported or unknown to the reader */
	public static final int ERROR_COMMAND_NOT_SUPPORTED = 2;
	/** The command is not supported or unknown to the reader */
	public static final String ERROR_COMMAND_NOT_SUPPORTED_STR = "ERROR_COMMAND_NOT_SUPPORTED";
	
	/** A parameter has the wrong format */
	public static final int ERROR_PARAMETER_INVALID_FORMAT = 3;
	/** A parameter has the wrong format */
	public static final String ERROR_PARAMETER_INVALID_FORMAT_STR = "ERROR_PARAMETER_INVALID_FORMAT";
	
	/** A required parameter is not specified */
	public static final int ERROR_PARAMETER_MISSING = 4;
	/** A required parameter is not specified */
	public static final String ERROR_PARAMETER_MISSING_STR = "ERROR_PARAMETER_MISSING";
	
	/** A parameter has the wrong format, e.g. string instead of an integer */
	public static final int ERROR_PARAMETER_INVALID_DATATYPE = 5;
	/** A parameter has the wrong format, e.g. string instead of an integer */
	public static final String ERROR_PARAMETER_INVALID_DATATYPE_STR = "ERROR_PARAMETER_INVALID_DATATYPE";
	
	/** Using a parameter value that is illegal. Example: Setting the port to 10 in the <code>addReadTrigger</code> command when there is no port 10. */
	public static final int ERROR_PARAMETER_ILLEGAL_VALUE = 6;
	/** Using a parameter value that is illegal. Example: Setting the port to 10 in the <code>addReadTrigger</code> command when there is no port 10. */
	public static final String ERROR_PARAMETER_ILLEGAL_VALUE_STR = "ERROR_PARAMETER_ILLEGAL_VALUE";
	
	/** A parameter is out of the valid range for this parameter, e.g. setting the duty cycle to value not between 0 and 100. */
	public static final int ERROR_PARAMETER_OUT_OF_RANGE = 7;
	/** A parameter is out of the valid range for this parameter, e.g. setting the duty cycle to value not between 0 and 100. */
	public static final String ERROR_PARAMETER_OUT_OF_RANGE_STR = "ERROR_PARAMETER_OUT_OF_RANGE";
	
	/** A parameter is not supported by this reader, e.g. asking a reader that doesnt support any read triggers to add a read trigger with a value other than <code>none</code>.*/
	public static final int ERROR_PARAMETER_NOT_SUPPORTED = 8;
	/** A parameter is not supported by this reader, e.g. asking a reader that doesnt support any read triggers to add a read trigger with a value other than <code>none</code>.*/
	public static final String ERROR_PARAMETER_NOT_SUPPORTED_STR = "ERROR_PARAMETER_NOT_SUPPORTED";
	
	/** The length of the given parameter was too long, e.g. when providing a string value in a setAttribute method that is longer than the maximum length of that attribute. */
	public static final int ERROR_PARAMETER_LENGTH_EXCEEDED = 9;
	/** The length of the given parameter was too long, e.g. when providing a string value in a setAttribute method that is longer than the maximum length of that attribute. */
	public static final String ERROR_PARAMETER_LENGTH_EXCEEDED_STR = "ERROR_PARAMETER_LENGTH_EXCEEDED";
	
	/** Raised when trying to add more than the maximum number of tag selectors. */
	public static final int ERROR_TOO_MANY_TAGSELECTORS = 10;
	/** Raised when trying to add more than the maximum number of tag selectors. */
	public static final String ERROR_TOO_MANY_TAGSELECTORS_STR = "ERROR_TOO_MANY_TAGSELECTORS";
	
	/** Raised when trying to add more than the maximum number of triggers. */
	public static final int ERROR_TOO_MANY_TRIGGERS = 11;
	/** Raised when trying to add more than the maximum number of triggers. */
	public static final String ERROR_TOO_MANY_TRIGGERS_STR = "ERROR_TOO_MANY_TRIGGERS";
	
	/** Raised  when the given tag selector parameter is not known. */
	public static final int ERROR_TAGSELECTOR_NOT_FOUND = 12;
	/** Raised  when the given tag selector parameter is not known. */
	public static final String ERROR_TAGSELECTOR_NOT_FOUND_STR = "ERROR_TAGSELECTOR_NOT_FOUND";
	
	/** Raised  when the given trigger parameter is not known. */
	public static final int ERROR_TRIGGER_NOT_FOUND = 13;
	/** Raised  when the given trigger parameter is not known. */
	public static final String ERROR_TRIGGER_NOT_FOUND_STR = "ERROR_TRIGGER_NOT_FOUND_STR";
	
	/** Raised  when the given read point parameter is not known. */
	public static final int ERROR_READPOINT_NOT_FOUND = 14;
	/** Raised  when the given read point parameter is not known. */
	public static final String ERROR_READPOINT_NOT_FOUND_STR = "ERROR_READPOINT_NOT_FOUND";
	
	/** Raised  when the given channel parameter is not known. */
	public static final int ERROR_CHANNEL_NOT_FOUND = 15;
	/** Raised  when the given channel parameter is not known. */
	public static final String ERROR_CHANNEL_NOT_FOUND_STR = "ERROR_CHANNEL_NOT_FOUND";
	
	/** Raised  when the given source parameter is not known. */
	public static final int ERROR_SOURCE_NOT_FOUND = 16;
	/** Raised  when the given source parameter is not known. */
	public static final String ERROR_SOURCE_NOT_FOUND_STR = "ERROR_SOURCE_NOT_FOUND";
	
	/** Raised  when trying to remove a source that CANNOT be removed, e.g. if it is one of the preconfigured sources. */
	public static final int ERROR_SOURCE_READ_ONLY = 17;
	/** Raised  when trying to remove a source that CANNOT be removed, e.g. if it is one of the preconfigured sources. */
	public static final String ERROR_SOURCE_READ_ONLY_STR = "ERROR_SOURCE_READ_ONLY";
	
	/** Raised  when the given DataSelector parameter is not known. */
	public static final int ERROR_DATASELECTOR_NOT_FOUND = 18;
	/** Raised  when the given DataSelector parameter is not known. */
	public static final String ERROR_DATASELECTOR_NOT_FOUND_STR = "ERROR_DATASELECTOR_NOT_FOUND";
	
	/** Tags were detected by the reader, but CANNOT be read successfully. Note: No tags detected is a valid result, not an error. */
	public static final int ERROR_TAGS_DETECTED_NOTREAD = 19;
	/** Tags were detected by the reader, but CANNOT be read successfully. Note: No tags detected is a valid result, not an error. */
	public static final String ERROR_TAGS_DETECTED_NOTREAD_STR = "ERROR_TAGS_DETECTED_NOTREAD";
	
	/** No tag was in the field of the reader when trying to write or kill. */
	public static final int ERROR_NO_TAG = 20;
	/** No tag was in the field of the reader when trying to write or kill. */
	public static final String ERROR_NO_TAG_STR = "ERROR_NO_TAG";
	
	/** The to be written or killed is locked. */
	public static final int ERROR_TAG_LOCKED = 21;
	/** The to be written or killed is locked. */
	public static final String ERROR_TAG_LOCKED_STR = "ERROR_TAG_LOCKED";
	
	/** The given kill code is wrong. */
	public static final int ERROR_WRONG_PASSWORD = 22;
	/** The given kill code is wrong. */
	public static final String ERROR_WRONG_PASSWORD_STR = "ERROR_WRONG_PASSWORD";
	
	/** Multiple tags were detected in the range of the reader when trying to write or kill. */
	public static final int ERROR_MULTIPLE_TAGS = 23;
	/** Multiple tags were detected in the range of the reader when trying to write or kill. */
	public static final String ERROR_MULTIPLE_TAGS_STR = "ERROR_MULTIPLE_TAGS";
	
	/** The tag was a read-only tag that CANNOT be written. */
	public static final int ERROR_READONLY_TAG = 24;
	/** The tag was a read-only tag that CANNOT be written. */
	public static final String ERROR_READONLY_TAG_STR = "ERROR_READONLY_TAG";
	
	/** Raised when trying to create an object and an object of the same type with the same name exists already.  */
	public static final int ERROR_OBJECT_EXISTS = 25;
	/** Raised when trying to create an object and an object of the same type with the same name exists already.  */
	public static final String ERROR_OBJECT_EXISTS_STR = "ERROR_OBJECT_EXISTS";
	
	/** The reader does not allow ReadPoints shared between Sources and the ReadPoint is already assigned to another Source. */
	public static final int ERROR_READPOINT_IN_USE = 26;
	/** The reader does not allow ReadPoints shared between Sources and the ReadPoint is already assigned to another Source. */
	public static final String ERROR_READPOINT_IN_USE_STR = "ERROR_READPOINT_IN_USE";
	
	/** Raised when the given tag field parameter is not known. */
	public static final int ERROR_TAGFIELD_NOT_FOUND = 27;
	/** Raised when the given tag field parameter is not known. */
	public static final String ERROR_TAGFIELD_NOT_FOUND_STR = "ERROR_TAGFIELD_NOT_FOUND";
	
	/** Raised when the given IOPort parameter is not known. */
	public static final int ERROR_IOPORT_NOT_FOUND = 28;
	/** Raised when the given IOPort parameter is not known. */
	public static final String ERROR_IOPORT_NOT_FOUND_STR = "ERROR_IOPORT_NOT_FOUND";
	
	/** A vendor-specific error. See the Error structures vendor extension section for the (vendor specific) error code. */
	public static final int ERROR_VENDOR_EXTENSION = 65535;
	/** A vendor-specific error. See the Error structures vendor extension section for the (vendor specific) error code. */
	public static final String ERROR_VENDOR_EXTENSION_STR = "ERROR_VENDOR_EXTENSION";
	
//	/** Hashtable holding the error code's String representations. */
//	private static Hashtable errorCause;
//	
//	private static void initializeErrorCause() {
//		errorCause = new Hashtable();
//		errorCause.put(new Integer(NO_ERROR), NO_ERROR_STR);
//		errorCause.put(new Integer(ERROR_UNKNOWN), ERROR_UNKNOWN_STR);
//		errorCause.put(new Integer(ERROR_COMMAND_NOT_SUPPORTED), ERROR_COMMAND_NOT_SUPPORTED_STR);
//		errorCause.put(new Integer(ERROR_PARAMETER_INVALID_FORMAT), ERROR_PARAMETER_INVALID_FORMAT_STR);
//		errorCause.put(new Integer(ERROR_PARAMETER_MISSING), ERROR_PARAMETER_MISSING_STR);
//		errorCause.put(new Integer(ERROR_PARAMETER_INVALID_DATATYPE), ERROR_PARAMETER_INVALID_DATATYPE_STR);
//		errorCause.put(new Integer(ERROR_PARAMETER_ILLEGAL_VALUE), ERROR_PARAMETER_ILLEGAL_VALUE_STR);
//		errorCause.put(new Integer(ERROR_PARAMETER_OUT_OF_RANGE), ERROR_PARAMETER_OUT_OF_RANGE_STR);
//		errorCause.put(new Integer(ERROR_PARAMETER_NOT_SUPPORTED), ERROR_PARAMETER_NOT_SUPPORTED_STR);
//		errorCause.put(new Integer(ERROR_PARAMETER_LENGTH_EXCEEDED), ERROR_PARAMETER_LENGTH_EXCEEDED_STR);
//		errorCause.put(new Integer(ERROR_TOO_MANY_TAGSELECTORS), ERROR_TOO_MANY_TAGSELECTORS_STR);
//		errorCause.put(new Integer(ERROR_TOO_MANY_TRIGGERS), ERROR_TOO_MANY_TRIGGERS_STR);
//		errorCause.put(new Integer(ERROR_TAGSELECTOR_NOT_FOUND), ERROR_TAGSELECTOR_NOT_FOUND_STR);
//		errorCause.put(new Integer(ERROR_TRIGGER_NOT_FOUND), ERROR_TRIGGER_NOT_FOUND_STR);
//		errorCause.put(new Integer(ERROR_READPOINT_NOT_FOUND), ERROR_READPOINT_NOT_FOUND_STR);
//		errorCause.put(new Integer(ERROR_CHANNEL_NOT_FOUND), ERROR_CHANNEL_NOT_FOUND_STR);
//		errorCause.put(new Integer(ERROR_SOURCE_NOT_FOUND), ERROR_SOURCE_NOT_FOUND_STR);
//		errorCause.put(new Integer(ERROR_SOURCE_READ_ONLY), ERROR_SOURCE_READ_ONLY_STR);
//		errorCause.put(new Integer(ERROR_DATASELECTOR_NOT_FOUND), ERROR_DATASELECTOR_NOT_FOUND_STR);
//		errorCause.put(new Integer(ERROR_TAGS_DETECTED_NOTREAD), ERROR_TAGS_DETECTED_NOTREAD_STR);
//		errorCause.put(new Integer(ERROR_NO_TAG), ERROR_NO_TAG_STR);
//		errorCause.put(new Integer(ERROR_TAG_LOCKED), ERROR_TAG_LOCKED_STR);
//		errorCause.put(new Integer(ERROR_WRONG_PASSWORD), ERROR_WRONG_PASSWORD_STR);
//		errorCause.put(new Integer(ERROR_MULTIPLE_TAGS), ERROR_MULTIPLE_TAGS_STR);
//		errorCause.put(new Integer(ERROR_READONLY_TAG), ERROR_READONLY_TAG_STR);
//		errorCause.put(new Integer(ERROR_OBJECT_EXISTS), ERROR_OBJECT_EXISTS_STR);
//		errorCause.put(new Integer(ERROR_READPOINT_IN_USE), ERROR_READPOINT_IN_USE_STR);
//		errorCause.put(new Integer(ERROR_TAGFIELD_NOT_FOUND), ERROR_TAGFIELD_NOT_FOUND_STR);
//		errorCause.put(new Integer(ERROR_VENDOR_EXTENSION), ERROR_VENDOR_EXTENSION_STR);		
//	}
//	
//	/**
//	 * Returns the String representation of a error code.
//	 * @param type The error code as <code>int</code>
//	 * @return The String representation of <code>type</code>
//	 */
//	public static String getErrorCause(int type) {
//		if (errorCause == null) {
//			initializeErrorCause();
//		}
//		return (String)errorCause.get(new Integer(type));
//	}
}
