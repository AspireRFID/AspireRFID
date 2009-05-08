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

import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.Command;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.reply.Reply;

/**
 * A <code>MessageParser</code> parses a messages into the intermediate representation (IR)
 * used by the <code>CommandDispatcher</code>. As input into the parser
 * serves a String, the output is a <code>Command</code>.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 *
 */

public interface MessageParser {

	/**
	 * Parses the command message.
	 * 
	 * @param message the message that has to be parsed.
	 * @throws MessageParsingException
	 */
	public Command parseCommandMessage(String message) throws MessageParsingException;
	
	/**
	 * Parses the reply message.
	 * 
	 * @param message the message that has to be parsed.
	 * @throws MessageParsingException
	 */
	public Reply parseReplyMessage(String message) throws MessageParsingException;

	/**
	 * Generates an error reply for a parser exception.
	 * @param parsingException The parsing exception.
	 * @return The <code>Reply</code> consisting of an error reply.
	 */
	public Reply createParserErrorReply(MessageParsingException parsingException);

}
