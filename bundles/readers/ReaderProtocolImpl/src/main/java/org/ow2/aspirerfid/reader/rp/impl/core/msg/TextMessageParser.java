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
import javax.xml.bind.JAXBException;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.Command;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.CommandNotSupportedException;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ParameterMissingException;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.ParameterWrongTypeException;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TextCommandParser;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TextCommandParserException;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TextCommandParserHelper;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.TextLexer;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ErrorType;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.Reply;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TextReplyParser;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TextReplyParserHelper;
import org.apache.log4j.Logger;
import antlr.RecognitionException;
import antlr.TokenStreamException;

/**
 * The <code>TextMessageParser</code> parses incoming text-messages and tries
 * to extract the information needed for a correct execution of the associated
 * command.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 * 
 */
public class TextMessageParser implements MessageParser {
	// ====================================================================
	// ---------------------------- Fields ------------------------------//
	// ====================================================================

	/** The logger. */
	static private Logger log;

	/** The factory for creating error replies if a parser exception happens. */
	private org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ObjectFactory replyFactory;

	// ====================================================================
	// ------------------------- Constructor ----------------------------//
	// ====================================================================
	/**
	 * Creates a new TextMessageParser
	 * @param jaxbContext 
	 */
	public TextMessageParser(Context context) {
		log = Logger.getLogger(getClass().getName());
		replyFactory = new org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.ObjectFactory();
	}

	// ====================================================================
	// ------------------------- Methods --------------------------------//
	// ====================================================================

	/**
	 * Parses a COMMAND TEXT message.
	 * 
	 * @param message
	 *            the message that has to be parsed.
	 * @throws MessageParsingException
	 */
	public Command parseCommandMessage(final String message) throws MessageParsingException {
		try {
			StringReader reader = new StringReader(message);
			TextLexer lexer = new TextLexer(reader);
			TextCommandParser parser = new TextCommandParser(lexer);
			TextCommandParserHelper helper = parser.command_line();
			Command command = helper.buildCommandTree();
			return command;
		} catch (TokenStreamException e) {
			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
					MessagingConstants.ERROR_UNKNOWN_STR,
					"[TEXT_PARSING_EXCEPTION] " + e.getMessage());
		} catch (ParameterMissingException e) {
			throw new MessageParsingException(
					MessagingConstants.ERROR_PARAMETER_MISSING,
					MessagingConstants.ERROR_PARAMETER_MISSING_STR, e
							.getMessage());
		} catch (ParameterWrongTypeException e) {
			throw new MessageParsingException(
					MessagingConstants.ERROR_PARAMETER_INVALID_DATATYPE,
					MessagingConstants.ERROR_PARAMETER_INVALID_DATATYPE_STR, e
							.getMessage());
		} catch (CommandNotSupportedException e) {
			throw new MessageParsingException(
					MessagingConstants.ERROR_COMMAND_NOT_SUPPORTED,
					MessagingConstants.ERROR_COMMAND_NOT_SUPPORTED_STR, e
							.getMessage());
		} catch (TextCommandParserException e) {
			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
					MessagingConstants.ERROR_UNKNOWN_STR, e.getMessage());
		} catch (RecognitionException e) {
			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
					MessagingConstants.ERROR_UNKNOWN_STR,
					"[TEXT_PARSING_EXCEPTION] " + e.getMessage());
		} catch (Exception e) {
			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
					MessagingConstants.ERROR_UNKNOWN_STR,
					"[TEXT_PARSING_EXCEPTION] " + e.getMessage());
		}
	}
	
	/**
	 * Parses a REPLY TEXT message.
	 * 
	 * @param message
	 *            the message that has to be parsed.
	 * @throws MessageParsingException
	 */
	public Reply parseReplyMessage(final String message) throws MessageParsingException {
		try {
			StringReader reader = new StringReader(message);
			org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TextLexer lexer = new org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.TextLexer(reader);
			TextReplyParser parser = new TextReplyParser(lexer);
			TextReplyParserHelper helper = parser.reply();
			Reply reply = helper.buildReplyTree();
			return reply;
		} catch (TokenStreamException e) {
			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
					MessagingConstants.ERROR_UNKNOWN_STR,
					"[TEXT_PARSING_EXCEPTION] " + e.getMessage());
		} catch (RecognitionException e) {
			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
					MessagingConstants.ERROR_UNKNOWN_STR,
					"[TEXT_PARSING_EXCEPTION] " + e.getMessage());
		} catch (Exception e) {
			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
					MessagingConstants.ERROR_UNKNOWN_STR,
					"[TEXT_PARSING_EXCEPTION] " + e.getMessage());
		}
	}
	
//
//	/**
//	 * Parses a TEXT message.
//	 * 
//	 * @param message
//	 *            the message that has to be parsed.
//	 * @throws MessageParsingException
//	 */
//	public Element parseMessage(final String message) throws MessageParsingException {
//		try {
//			StringReader reader = new StringReader(message);
//			if (context == Context.COMMAND) {
//				TextLexer lexer = new TextLexer(reader);
//				TextCommandParser parser = new TextCommandParser(lexer);
//				TextCommandParserHelper helper = parser.command_line();
//				Command command = helper.buildCommandTree();
//				return command;
//			} else if (context == Context.REPLY) {
//				org.accada.reader.msg.reply.TextLexer lexer = new org.accada.reader.msg.reply.TextLexer(reader);
//				TextReplyParser parser = new TextReplyParser(lexer);
//				TextReplyParserHelper helper = parser.reply();
//				Reply reply = helper.buildReplyTree();
//				return reply;
//			} else if (context == Context.NOTIFICATION) {
//				// TODO: notification parsing
//			}
//			return null;
//		} catch (TokenStreamException e) {
//			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
//					MessagingConstants.ERROR_UNKNOWN_STR,
//					"[TEXT_PARSING_EXCEPTION] " + e.getMessage());
//		} catch (ParameterMissingException e) {
//			throw new MessageParsingException(
//					MessagingConstants.ERROR_PARAMETER_MISSING,
//					MessagingConstants.ERROR_PARAMETER_MISSING_STR, e
//							.getMessage());
//		} catch (ParameterWrongTypeException e) {
//			throw new MessageParsingException(
//					MessagingConstants.ERROR_PARAMETER_INVALID_DATATYPE,
//					MessagingConstants.ERROR_PARAMETER_INVALID_DATATYPE_STR, e
//							.getMessage());
//		} catch (CommandNotSupportedException e) {
//			throw new MessageParsingException(
//					MessagingConstants.ERROR_COMMAND_NOT_SUPPORTED,
//					MessagingConstants.ERROR_COMMAND_NOT_SUPPORTED_STR, e
//							.getMessage());
//		} catch (TextCommandParserException e) {
//			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
//					MessagingConstants.ERROR_UNKNOWN_STR, e.getMessage());
//		} catch (RecognitionException e) {
//			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
//					MessagingConstants.ERROR_UNKNOWN_STR,
//					"[TEXT_PARSING_EXCEPTION] " + e.getMessage());
//		} catch (Exception e) {
//			throw new MessageParsingException(MessagingConstants.ERROR_UNKNOWN,
//					MessagingConstants.ERROR_UNKNOWN_STR,
//					"[TEXT_PARSING_EXCEPTION] " + e.getMessage());
//		}
//	}
	
	/**
	 * Generates a text error reply for a parser exception.
	 * 
	 * @param parsingException
	 *            The parsing exception.
	 * @return The <code>Reply</code> consisting of an error reply.
	 */
	public Reply createParserErrorReply(final MessageParsingException parsingException) {
		Reply reply = replyFactory.createReply();
		reply.setId("0"); // per default set to zero because I don't know
							// the id
		reply.setResultCode(parsingException.getResultCode());
		ErrorType error = replyFactory.createErrorType();
		error.setName(parsingException.getErrorName());
		error.setDescription(parsingException.getErrorDescription());
		reply.setError(error);
		return reply;
	}
}
