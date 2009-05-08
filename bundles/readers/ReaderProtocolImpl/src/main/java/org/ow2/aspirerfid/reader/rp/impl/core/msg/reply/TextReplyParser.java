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

import antlr.NoViableAltException;
import antlr.ParserSharedInputState;
import antlr.RecognitionException;
import antlr.Token;
import antlr.TokenBuffer;
import antlr.TokenStream;
import antlr.TokenStreamException;

public class TextReplyParser extends antlr.LLkParser       implements TextReplyParserTokenTypes
 {

	TextReplyParserHelper helper = new TextReplyParserHelper();

protected TextReplyParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public TextReplyParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected TextReplyParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public TextReplyParser(TokenStream lexer) {
  this(lexer,1);
}

public TextReplyParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final TextReplyParserHelper  reply() throws RecognitionException, TokenStreamException, Exception {
		TextReplyParserHelper parserHelper;
		
		
				parserHelper = helper;
			
		
		{
		switch ( LA(1)) {
		case OK:
		case ERR:
		case EXCLAMATION:
		{
			reply_header();
			break;
		}
		case COMMAND_REPLY_TERMINATOR:
		case INT:
		case STRING:
		{
			break;
		}
		default:
		{
			throw new Exception(getFilename());
		}
		}
		}
		{
		if ((LA(1)==COMMAND_REPLY_TERMINATOR||LA(1)==INT||LA(1)==STRING)) {
			command_specific_reply();
		}
		else if ((LA(1)==COMMAND_REPLY_TERMINATOR)) {
		}
		else {
			throw new Exception(getFilename());
		}
		
		}
		match(COMMAND_REPLY_TERMINATOR);
		return parserHelper;
	}
	
	public final void reply_header() throws RecognitionException, TokenStreamException, Exception {
		
		
				String id = null;
			
		
		{
		switch ( LA(1)) {
		case EXCLAMATION:
		{
			id=command_id();
			
					helper.setId(id);
				
			break;
		}
		case OK:
		case ERR:
		{
			break;
		}
		default:
		{
			throw new Exception(getFilename());
		}
		}
		}
		reply_status();
		match(LF);
	}
	
	public final void command_specific_reply() throws RecognitionException, TokenStreamException {
		
		
		{
		_loop841:
		do {
			if ((LA(1)==INT||LA(1)==STRING)) {
				reply_line();
			}
			else {
				break _loop841;
			}
			
		} while (true);
		}
	}
	
	public final String  command_id() throws RecognitionException, TokenStreamException {
		String s;
		
		
				int r = 0;
				s = null;
			
		
		match(EXCLAMATION);
		r=dec_val();
		
				s = Integer.toString(r);
			
		return s;
	}
	
	public final void reply_status() throws RecognitionException, TokenStreamException, Exception {
		
		
		{
		switch ( LA(1)) {
		case OK:
		{
			reply_success();
			break;
		}
		case ERR:
		{
			reply_error();
			break;
		}
		default:
		{
			throw new Exception(getFilename());
		}
		}
		}
	}
	
	public final int  dec_val() throws RecognitionException, TokenStreamException {
		int r;
		
		Token  i = null;
		
				r = 0;
			
		
		i = LT(1);
		match(INT);
		
				r = Integer.parseInt(i.getText());
			
		return r;
	}
	
	public final void reply_success() throws RecognitionException, TokenStreamException, Exception {
		
		
				int statusCode = 0;
				int statusInfo = 0;
			
		
		match(OK);
		{
		switch ( LA(1)) {
		case COMMA:
		{
			match(COMMA);
			statusCode=status_code();
			
					helper.setStatusCode(statusCode);
				
			{
			switch ( LA(1)) {
			case COMMA:
			{
				match(COMMA);
				status_info();
				break;
			}
			case LF:
			{
				break;
			}
			default:
			{
				throw new Exception(getFilename());
			}
			}
			}
			break;
		}
		case LF:
		{
			break;
		}
		default:
		{
			throw new Exception(getFilename());
		}
		}
		}
	}
	
	public final void reply_error() throws RecognitionException, TokenStreamException, Exception {
		
		
				int statusCode = 0;
				int statusInfo = 0;
			
		
		match(ERR);
		match(COMMA);
		statusCode=status_code();
		
				helper.setStatusCode(statusCode);
			
		{
		switch ( LA(1)) {
		case COMMA:
		{
			match(COMMA);
			status_info();
			break;
		}
		case LF:
		{
			break;
		}
		default:
		{
			throw new Exception(getFilename());
		}
		}
		}
	}
	
	public final int  status_code() throws RecognitionException, TokenStreamException {
		int code;
		
		
				code = 0;
			
		
		code=dec_val();
		return code;
	}
	
	public final void status_info() throws RecognitionException, TokenStreamException, Exception {
		
		
				ErrorType errorType = new ErrorType();
				String resultName = null;
				String resultCause = null;
				String resultDescription = null;
			
		
		{
		switch ( LA(1)) {
		case STRING:
		{
			resultName=result_name();
			break;
		}
		case LF:
		case COMMA:
		{
			break;
		}
		default:
		{
			throw new Exception(getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case COMMA:
		{
			match(COMMA);
			{
			switch ( LA(1)) {
			case STRING:
			{
				resultCause=result_cause();
				break;
			}
			case LF:
			case COMMA:
			{
				break;
			}
			default:
			{
				throw new Exception(getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case COMMA:
			{
				match(COMMA);
				resultDescription=result_description();
				break;
			}
			case LF:
			{
				break;
			}
			default:
			{
				throw new Exception(getFilename());
			}
			}
			}
			break;
		}
		case LF:
		{
			break;
		}
		default:
		{
			throw new Exception(getFilename());
		}
		}
		}
		
				errorType.setName(resultName);
				errorType.setCause(resultCause);
				errorType.setDescription(resultDescription);
				helper.setErrorType(errorType);
			
	}
	
	public final String  result_name() throws RecognitionException, TokenStreamException {
		String name;
		
		
				name = null;
			
		
		name=string_val();
		return name;
	}
	
	public final String  result_cause() throws RecognitionException, TokenStreamException {
		String cause;
		
		
				cause = null;
			
		
		cause=string_val();
		return cause;
	}
	
	public final String  result_description() throws RecognitionException, TokenStreamException {
		String description;
		
		Token  resultDescription = null;
		
				description = null;
			
		
		resultDescription = LT(1);
		match(String_val);
		
				description = resultDescription.getText();
			
		return description;
	}
	
	public final String  string_val() throws RecognitionException, TokenStreamException {
		String r;
		
		Token  s = null;
		
				r = null;
			
		
		s = LT(1);
		match(STRING);
		
				r = s.getText();
			
		return r;
	}
	
	public final void reply_line() throws RecognitionException, TokenStreamException {
		
		
				String replyValue = null;
			
		
		replyValue=reply_value();
		
				helper.addValue(replyValue.trim());
			
		{
		_loop844:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				replyValue=reply_value();
				
						helper.addValue(replyValue.trim());
					
			}
			else {
				break _loop844;
			}
			
		} while (true);
		}
		match(LF);
	}
	
	public final String  reply_value() throws RecognitionException, TokenStreamException {
		String value;
		
		
				value = null;
				int dec_value = 0;
			
		
		switch ( LA(1)) {
		case STRING:
		{
			value=string_val();
			break;
		}
		case INT:
		{
			dec_value=dec_val();
			
					value = Integer.toString(dec_value);
				
			break;
		}
		default:
		{
			return null;
		}
		}
		return value;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"OK\"",
		"\"ERR\"",
		"COMMAND_REPLY_TERMINATOR",
		"LF",
		"EXCLAMATION",
		"COMMA",
		"String_val",
		"INT",
		"STRING",
		"WS",
		"SHARP",
		"DOT",
		"LBRACKET",
		"RBRACKET",
		"ASSIGN",
		"ESCAPE",
		"DIGIT",
		"STRINGCHAR1",
		"STRINGCHAR2",
		"IDENT"
	};
	
	
	}
