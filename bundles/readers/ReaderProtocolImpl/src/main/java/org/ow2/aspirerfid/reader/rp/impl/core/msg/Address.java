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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Implementation of addresses used in the EPC Message/Transport Bindings (MTB).
 * Similar to <code>java.net.URL</code> an EPC address has a protocol, a host,
 * a port and a query.
 * <p>
 * Format: address = protocol "://" (host (":" port)+ )+ ( "?" (query)* )
 * 
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class Address {
	/* ------------------ URL CONSTANTS ------------------ */
	/** the delimiter used in URL (protocol://host:port/file?query1&query2).*/
	public static final String QUERY_DELIMITER = "&";
	
	/** the delimiter used in URL (protocol://host:port/file?key1=val1). */
	public static final String KEY_VALUE_DELIMITER = "=";
	
	/** the constant for the mode used in addresses of NotificationChannel's. */
	public static final String MODE = "mode";
	
	/**
	 * specifies to use the connect mode (used in a NotificationChannel
	 * address).
	 */
	public static final String MODE_CONNECT = "connect";

	/** specifies to use the listen mode (used in a NotificationChannel 
	 * address). */
	public static final String MODE_LISTEN = "listen";
	
	/* ------------------ FIELDS ------------------ */

	/** The transport protocol. */
	private String protocol;
	
	/** The host. */
	private String host;
	
	/** The port. */
	private int port = -1;
	
	/** The query parameters. */
	private HashMap urlParameters;
	
	/* ------------------ CONSTRUCTORS ------------------ */
	/**
	 * Creates a new Address object.
	 * 
	 * @param address
	 *            The address
	 * @throws MalformedURLException
	 *            If the URI couldn't be built out of the address parameter.
	 */
	public Address(final String address) throws MalformedURLException {
		try {
			URI uri = new URI(address);
			urlParameters = new HashMap();
			protocol = uri.getScheme();
			host = uri.getHost();
			port = uri.getPort();
			parseQuery(uri.getQuery());
			
		} catch (URISyntaxException e) {
			throw new MalformedURLException("Malformed address.");
		}
		
	}
	
	/* ------------------ METHODS ------------------ */

	/**
	 * @return Returns the host.
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            The host to set.
	 */
	public void setHost(final String host) {
		this.host = host;
	}

	/**
	 * @return Returns the port.
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            The port to set.
	 */
	public void setPort(final int port) {
		this.port = port;
	}

	/**
	 * @return Returns the protocol.
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol
	 *            The protocol to set.
	 */
	public void setProtocol(final String protocol) {
		this.protocol = protocol;
	}

	/**
	 * Gets the mode of a address (listen|connect).
	 * 
	 * @return The mode used in NotificationChannel addresses or
	 *         <code>null</code> if the mode is not specified.
	 */
	public String getMode() {
		return (String) urlParameters.get(MODE);
	}

	/**
	 * Represents an address as a string.
	 * @return The address as a string.
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(protocol);
		buffer.append("://");
		if (host != null) {
			buffer.append(host);
		}
		if (port != -1) {
			buffer.append(":");
			buffer.append(port);
		}

		return buffer.toString();
	}

	/**
	 * Parses the query from an URL (part after the "?") and stores the
	 * key-value-pairs in the <code>HashMap</code> urlParameters.
	 * 
	 * @param query
	 *            The query from the URL.
	 */
	private void parseQuery(final String query) {
		if (query != null && query != "") {
			StringTokenizer tokenizer = new StringTokenizer(query,
					QUERY_DELIMITER);
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				String[] pair = token.split(KEY_VALUE_DELIMITER);
				urlParameters.put(pair[0], pair[1]);

			}
		}
	}
	
}
