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

import java.io.StringReader;

import javax.xml.bind.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.Command;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ErrorType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.Reply;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

/**
 * The <code>XmlMessageParser</code> parses incoming xml-messages and tries to
 * extract the information needed for a correct execution of the associated
 * command.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class XmlMessageParser implements MessageParser {
	// ====================================================================
	// ---------------------------- Fields ------------------------------//
	// ====================================================================

	/** The logger. */
	static private Logger log;

	/** Unmarshaller for the XML binding. */
	private Unmarshaller unmarshaller = null;

	/** The factory for creating error replies if a parser exception happens. */
	private org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ObjectFactory replyFactory;

	// ====================================================================
	// ------------------------- Constructor ----------------------------//
	// ====================================================================
	/**
	 * Constructor for an XmlMessageParser.
	 */
	public XmlMessageParser(JAXBContext jaxbContext) {
		log = Logger.getLogger(getClass().getName());
		try {
			unmarshaller = jaxbContext.createUnmarshaller();
//			unmarshaller.setValidating(true);
			replyFactory = new org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ObjectFactory();
		} catch (JAXBException e) {
			log.error(e);
		}
	}

	// ====================================================================
	// ------------------------- Methods --------------------------------//
	// ====================================================================

	/**
	 * Parses an COMMAND XML message using the JAXB XML bindings.
	 * 
	 * @param message
	 *            the message that has to be parsed.
	 * @throws MessageParsingException
	 */
	public Command parseCommandMessage(final String message) throws MessageParsingException {
		StringReader reader = new StringReader(message);
		Command command = null;
		try {
			command = (Command) unmarshaller.unmarshal(new InputSource(reader));
		} catch (JAXBException e) {
			throw new MessageParsingException(e);
		} catch (Exception e) {
			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
					MessagingConstants.ERROR_UNKNOWN_STR,
					"[XML_PARSING_EXCEPTION] " + e.getMessage());
		}
		return command;
	}
	
	/**
	 * Parses an REPLY XML message using the JAXB XML bindings.
	 * 
	 * @param message
	 *            the message that has to be parsed.
	 * @throws MessageParsingException
	 */
	public Reply parseReplyMessage(final String message) throws MessageParsingException {
		StringReader reader = new StringReader(message);
		Reply reply = null;
		try {
			reply = (Reply) unmarshaller.unmarshal(new InputSource(reader));
		} catch (JAXBException e) {
			throw new MessageParsingException(e);
		} catch (Exception e) {
			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
					MessagingConstants.ERROR_UNKNOWN_STR,
					"[XML_PARSING_EXCEPTION] " + e.getMessage());
		}
		return reply;
	}

	/**
	 * Generates an XML error reply for a parser exception.
	 * 
	 * @param parsingException
	 *            The parsing exception.
	 * @return The <code>Reply</code> consisting of an error reply.
	 */
	public Reply createParserErrorReply(
			final MessageParsingException parsingException) {
		Reply reply = replyFactory.createReply();
		reply.setId("0");	// per default set to zero because I don't know
							// the id
		reply.setResultCode(parsingException.getResultCode());
		ErrorType error = replyFactory.createErrorType();
		error.setName(parsingException.getErrorName());
		error.setDescription(parsingException.getErrorDescription());
		reply.setError(error);
		return reply;
	}

}