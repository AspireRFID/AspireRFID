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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Singleton factory class which creates the concrete instances of a
 * <code>MessageParser</code> and a <code>MessageSerializer</code>. Because
 * the whole ReaderProtocol infrastructure can use the same parsers and the same
 * serializers we can use a singleton instance of the parser and serialiser.
 * Hence there is a singelton instance for each message binding.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 */
public class MessageFactory {
	/** The singleton instance of the MessageFactory. */
	private static MessageFactory instance;

	/** The singleton instance of the XML parser. */
	private static HashMap xmlParsers = new HashMap(); //XmlMessageParser

	/** The singleton instance of the XML serializer. */
	private static HashMap xmlSerializers = new HashMap(); //XmlMessageSerializer

	/** The singleton instance of the TEXT parser. */
	private static HashMap textParsers = new HashMap(); //TextMessageParser

	/** The singleton instance of the TEXT serializer. */
	private static HashMap textSerializers = new HashMap(); //TextMessageSerializer

	/**
	 * Returns the singleton instance of a <code>MessageBindingFactory</code>.
	 * 
	 * @return The <code>MessageBindingFactory</code>.
	 */
	public static MessageFactory getInstance() {
		if (instance == null) {
			instance = new MessageFactory();
		}
		return instance;
	}

	/**
	 * Gets the singleton instance of a <code>XmlMessageParser</code>.
	 * 
	 * @return a parser
	 */
	public MessageParser getXmlMessageParser(JAXBContext jaxbContext) {
		XmlMessageParser xmlParser = (XmlMessageParser)xmlParsers.get(jaxbContext);
		if (xmlParser == null) {
			xmlParser = new XmlMessageParser(jaxbContext);
			xmlParsers.put(jaxbContext, xmlParser);
		}
		return xmlParser;
	}

	/**
	 * Gets the singleton instance of a <code>XmlMessageSerializer</code>.
	 * 
	 * @return a serializer
	 */
	public MessageSerializer getXmlMessageSerializer(JAXBContext jaxbContext) {
		XmlMessageSerializer xmlSerializer = (XmlMessageSerializer)xmlSerializers.get(jaxbContext);
		if (xmlSerializer == null) {
			xmlSerializer = new XmlMessageSerializer();
			xmlSerializers.put(jaxbContext, xmlSerializer);
		}
		return xmlSerializer;
	}

	/**
	 * Gets the singleton instance of a <code>TextMessageParser</code>.
	 * @param jaxbContext 
	 * 
	 * @return a parser
	 */
	public MessageParser getTextMessageParser(Context context) {
		TextMessageParser textParser = (TextMessageParser)textParsers.get(context);
		if (textParser == null) {
			textParser = new TextMessageParser(context);
			textParsers.put(context, textParser);
		}
		return textParser;
	}

	/**
	 * Gets the singleton instance of a <code>TextMessageSerializer</code>.
	 * 
	 * @return a serializer
	 */
	public MessageSerializer getTextMessageSerializer(Context context) {
		TextMessageSerializer textSerializer = (TextMessageSerializer)textSerializers.get(context);
		if (textSerializer == null) {
			textSerializer = new TextMessageSerializer();
			textSerializers.put(context, textSerializer);
		}
		return textSerializer;
	}

	/**
	 * Gets an instance of the parser for the corresponding message format.
	 * 
	 * @param msgFormat
	 *            the format of the message
	 * @param jaxbContext 
	 * @return the corresponding <code>MessageParser</code>
	 * @throws MessageParsingException
	 *             If the message format is unknown.
	 */
	public MessageParser createParser(final MessageFormat msgFormat, Context context)
			throws MessageParsingException {
		if (msgFormat == MessageFormat.XML) {
			try {
				JAXBContext jaxbContext = context.getJAXBContext();
				return getXmlMessageParser(jaxbContext);
			} catch(JAXBException e) {
				e.printStackTrace();
				throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
						MessagingConstants.ERROR_UNKNOWN_STR,
						"Illegal message format.");
			}
		} else if (msgFormat == MessageFormat.TEXT) {
			return getTextMessageParser(context);
		} else {
			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
					MessagingConstants.ERROR_UNKNOWN_STR,
					"Illegal message format.");
		}
	}

	/**
	 * Gets an instance of the serializer for the corresponding message format.
	 * 
	 * @param msgFormat
	 *            the format of the message
	 * @return the corresponding <code>MessageSerializer</code>
	 * @throws MessageSerializingException
	 *             If the message format is unknown.
	 */
	public MessageSerializer createSerializer(final MessageFormat msgFormat, Context context)
			throws MessageSerializingException {
		if (msgFormat == MessageFormat.XML) {
			try {
				return getXmlMessageSerializer(context.getJAXBContext());
			} catch (JAXBException e) {
				throw new MessageSerializingException("Illegal message format.");
			}
		} else if (msgFormat == MessageFormat.TEXT) {
			return getTextMessageSerializer(context);
		} else {
			throw new MessageSerializingException("Illegal message format.");
		}
	}

}