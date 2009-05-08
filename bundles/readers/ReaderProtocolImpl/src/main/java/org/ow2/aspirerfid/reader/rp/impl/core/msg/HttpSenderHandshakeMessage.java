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

import java.util.HashMap;
import java.util.Map;

/**
 * Class used for handshaking. The handshake parameters are internally
 * handled as <code>String</code>. Thus it's evident to validate the values
 * to have correct lengths and contents using the method <code>isValid()</code>.
 * The <code>HttpSenderHandshakeMessage</code> can handle the HTTP specific
 * additons to the <code>SenderHandshakeMessage</code>, e.g.:
 * <ul>
 * <li>access method (POST, GET, ...)</li>
 * <li>vedor-specific parameters in the HTTP header</li>
 * <li>content-length of the message</li>
 * </ul>
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 *
 */
public class HttpSenderHandshakeMessage extends SenderHandshakeMessage {
	
	public static final String ACK_REQUEST = "yes";
	public static final String NAK_REQUEST = "no";
	
	/** the HTTP method (POST, GET, ...) */
	private String method;
	
	/** the HTTP version */
	private String httpVersion;
	
	/** the HTTP host parameter (required in HTTP 1.1) */
	private String host;
	
	/** the content length (required in HTTP 1.1) */
	private int contentLength;
	
	/** if the connection is persistent leave it open, otherwise close it. */
	private boolean isPersistent;
	
	/** additional header parameters */
    private Map headerFields = new HashMap();

    /**
     * The default constructor which initialises
     * the handshake message.
     */
    public HttpSenderHandshakeMessage() {
    	this.init();
    }
    
	/**
	 * Initialises the handshake with the default values.
	 */
	public void init() {
		super.init();
		isPersistent = true; /* default is keep-alive in HTTP/1.1 */		
	}
    
	/**
	 * @return Returns the ackNakRequest.
	 */
	public String getAckNakRequest() {
		if (ackNakRequest) {
			return ACK_REQUEST;
		} else {
			return NAK_REQUEST;
		}
	}
	
	/**
	 * @param ackNakRequest The ackNakRequest to set.
	 */
	public void setAckNakRequest(String ackNakRequest) {
		if (ackNakRequest.toLowerCase().equals(ACK_REQUEST)) {
			this.ackNakRequest = true;
		} else {
			this.ackNakRequest = false;
		}
	}
	
	/**
	 * @return Returns the contentLength.
	 */
	public int getContentLength() {
		return contentLength;
	}

	/**
	 * @param contentLength The contentLength to set.
	 */
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}
	
	/**
	 * Sets the Content-length. If the String could not be parsed into an int
	 * the contentLength is set to zero.
	 * @param contentLength The contentLength to set.
	 */
	public void setContentLength(String contentLength) {
		try {
			this.contentLength = Integer.parseInt(contentLength);
		} catch (NumberFormatException e) {
			// Could not read the content-length. Set it to zero.
			this.contentLength = 0;
		}
	}

	/**
	 * @return Returns the httpVersion.
	 */
	public String getHttpVersion() {
		return httpVersion;
	}

	/**
	 * @param httpVersion The httpVersion to set.
	 */
	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}

	/**
	 * @return Returns the method.
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method The method to set.
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	
	/**
	 * @return Returns the host.
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host The host to set.
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	/**
     * Returns the value to which the specified name is mapped in the HTTP header.
     * @param name A name of a parameter in the header
     * @return the value to which the name is mapped; <code>null</code> if the parameter doesn't exist.
     */
    public String getHeaderField(String name) {
        return (String)headerFields.get(name);
    }
    
    /**
     * Adds a new value of a HTTP header field into the header map. Use this
     * map for additional or vendor-specific parameters in the HTTP header.
     * @param key The name of the HTTP parameter
     * @param value The value of the HTTP parameter
     */
    public void addHeaderField(String key, String value) {
    	headerFields.put(key, value);
    }

	/**
	 * @return Returns the isPersistent.
	 */
	public boolean isPersistent() {
		return isPersistent;
	}

	/**
	 * @param isPersistent The isPersistent to set.
	 */
	public void setPersistent(boolean isPersistent) {
		this.isPersistent = isPersistent;
	}

	
    

	
}
