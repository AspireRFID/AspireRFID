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

// $ANTLR : "reply_new.g" -> "TextReplyParser.java"$
 
	package org.ow2.aspirerfid.reader.rp.impl.core.msg.reply;

public interface TextReplyParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int OK = 4;
	int ERR = 5;
	int COMMAND_REPLY_TERMINATOR = 6;
	int LF = 7;
	int EXCLAMATION = 8;
	int COMMA = 9;
	int String_val = 10;
	int INT = 11;
	int STRING = 12;
	int WS = 13;
	int SHARP = 14;
	int DOT = 15;
	int LBRACKET = 16;
	int RBRACKET = 17;
	int ASSIGN = 18;
	int ESCAPE = 19;
	int DIGIT = 20;
	int STRINGCHAR1 = 21;
	int STRINGCHAR2 = 22;
	int IDENT = 23;
}
