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
 * JmsUri allow to parse internal JMS Uri format.<br/>
 * 
 * Format :<br/>
 * jms:factory.initial/factory.host/factory.port/jms.server.host/jms.server.port/topic.connection.factory/topic/root.login/root.pass<br/>
 * 
 * Example of valide JMS Uri string :<br/>
 * jms:fr.dyade.aaa.jndi2.client.NamingContextFactory/localhost/16400/localhost/16010/JTCF/rfidtopic/root/root<br/>
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class JmsUri {
	// ******************************************
	// Private Members
	// ******************************************

	/**
	 * Initial factory
	 */
	private String initialFactory;

	/**
	 * factory hostname
	 */
	private String factoryHost;

	/**
	 * factory port
	 */
	private String factoryPort;

	/**
	 * topic connection factory
	 */
	private String topicConnectionFactory;

	/**
	 * destination topic
	 */
	private String topic;

	/**
	 * Login of administrator
	 */
	private String login;

	/**
	 * Password of administrator
	 */
	private String pass;

	/**
	 * JMS server hostname
	 */
	private String serverHost;

	/**
	 * JMS server port
	 */
	private String serverPort;

	// ******************************************
	// Constructor
	// ******************************************
	/**
	 * Constructor, build a JmsUri from a Uri string. <br/> Example of valid
	 * Uri string :<br/>
	 * "jms:fr.dyade.aaa.jndi2.client.NamingContextFactory/localhost/16400/localhost/1099/JTCF/rfidtopic/root/root"
	 * 
	 * @param UriToParse
	 *            Uri string to parse
	 * @throws BadUriFormatException
	 * @throws NotExpectedProtocolException
	 */
	public JmsUri(String UriToParse) throws NotExpectedProtocolException,
			BadUriFormatException {
		ParseStringToUri(UriToParse);
	}

	// ******************************************
	// Getters
	// ******************************************

	/**
	 * Gets factory hostname
	 * 
	 * @return factory hostname
	 */
	public String getFactoryHost() {
		return factoryHost;
	}

	/**
	 * Gets factory port
	 * 
	 * @return factory port
	 */
	public String getFactoryPort() {
		return factoryPort;
	}

	/**
	 * Gets root login
	 * 
	 * @return root login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Gets root pass
	 * 
	 * @return root pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Gets JMS server hostname
	 * 
	 * @return JMS server hostname
	 */
	public String getServerHost() {
		return serverHost;
	}

	/**
	 * Gets JMS server port
	 * 
	 * @return JMS server port
	 */
	public String getServerPort() {
		return serverPort;
	}

	/**
	 * Gets topic destination
	 * 
	 * @return topic destination
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * Gets topic connection factory
	 * 
	 * @return topic connection factory
	 */
	public String getTopicConnectionFactory() {
		return topicConnectionFactory;
	}

	/**
	 * Gets initial factory
	 * 
	 * @return initial factory
	 */
	public String getInitialFactory() {
		return initialFactory;
	}

	/**
	 * Gets server url
	 * 
	 * @return TODO Javadoc
	 */
	public String getServerUrl() {
		String s = RFIDConstants.PROVIDER_URL_PROTOCOL + serverHost + ":"
				+ serverPort;
		return s;
	}

	// ******************************************
	// Privates Methods
	// ******************************************
	/**
	 * Parse an Uri string to a JmsUri
	 * 
	 * @param UriToParse
	 *            Uri smtp string to parse
	 * @throws BadUriFormatException
	 *             Bad Uri Format
	 * @throws NotExpectedProtocolException
	 *             Not a JMS Uri
	 * @throws BadUriFormatException
	 */
	private void ParseStringToUri(String UriToParse)
			throws NotExpectedProtocolException, BadUriFormatException {
		// Is a SMTP URI ?
		String protocol = UriParserHelper.getProtocol(UriToParse);
		if (protocol.compareToIgnoreCase(RFIDConstants.JMS_PROTOCOL_NAME) != 0) {
			throw new NotExpectedProtocolException();
		}

		// remove the protocol field
		String uri = UriParserHelper.removeProtocolField(UriToParse);

		// read the information of the uri
		String[] splitted = UriParserHelper.split(uri,
				RFIDConstants.FIELD_SEPARATOR);
		if (splitted.length != 9) {
			throw new BadUriFormatException();
		}

		initialFactory = splitted[0];
		factoryHost = splitted[1];
		factoryPort = splitted[2];
		serverHost = splitted[3];
		serverPort = splitted[4];
		topicConnectionFactory = splitted[5];
		topic = splitted[6];
		login = splitted[7];
		pass = splitted[8];

	}

}
