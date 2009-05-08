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

// $ANTLR : "command.g" -> "TextCommandParser.java"$
 
package org.ow2.aspirerfid.reader.rp.impl.core.msg.command;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.command.Command;

public class TextCommandParser extends antlr.LLkParser       implements TextCommandParserTokenTypes
 {

	TextCommandParserHelper helper = new TextCommandParserHelper();

protected TextCommandParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public TextCommandParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected TextCommandParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public TextCommandParser(TokenStream lexer) {
  this(lexer,1);
}

public TextCommandParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final TextCommandParserHelper  command_line() throws Exception {
		TextCommandParserHelper parserHelper;
		
		
				parserHelper = helper;
				String id = null;
			
		
		{
		if ((LA(1)==EXCLAMATION)) {
			id=command_id();
		}
		else if ((_tokenSet_0.member(LA(1)))) {
		}
		else {
			throw new Exception(getFilename());
		}
		
		}
		{
		if (((LA(1) >= READERDEVICE && LA(1) <= TF))) {
			object_type_name();
			{
			switch ( LA(1)) {
			case SHARP:
			{
				match(SHARP);
				target_name();
				break;
			}
			case DOT:
			{
				break;
			}
			default:
			{
				throw new Exception(getFilename());
			}
			}
			}
			match(DOT);
		}
		else if ((_tokenSet_1.member(LA(1)))) {
		}
		else {
			throw new Exception(getFilename());
		}
		
		}
		command_name();
		{
		if ((_tokenSet_2.member(LA(1)))) {
			parameter_list();
		}
		else if ((LA(1)==LF)) {
		}
		else {
			throw new Exception(getFilename());
		}
		
		}
		match(LF);
		
					parserHelper.setId(id);
				
		return parserHelper;
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
	
	public final void object_type_name() throws Exception {
		
		
		{
		switch ( LA(1)) {
		case READERDEVICE:
		{
			match(READERDEVICE);
			helper.setObject(READERDEVICE);
			break;
		}
		case RD:
		{
			match(RD);
			helper.setObject(READERDEVICE);
			break;
		}
		case SOURCE:
		{
			match(SOURCE);
			helper.setObject(SOURCE);
			break;
		}
		case SRC:
		{
			match(SRC);
			helper.setObject(SOURCE);
			break;
		}
		case READPOINT:
		{
			match(READPOINT);
			helper.setObject(READPOINT);
			break;
		}
		case RP:
		{
			match(RP);
			helper.setObject(READPOINT);
			break;
		}
		case TAGSELECTOR:
		{
			match(TAGSELECTOR);
			helper.setObject(TAGSELECTOR);
			break;
		}
		case TS:
		{
			match(TS);
			helper.setObject(TAGSELECTOR);
			break;
		}
		case DATASELECTOR:
		{
			match(DATASELECTOR);
			helper.setObject(DATASELECTOR);
			break;
		}
		case DS:
		{
			match(DS);
			helper.setObject(DATASELECTOR);
			break;
		}
		case NOTIFICATIONCHANNEL:
		{
			match(NOTIFICATIONCHANNEL);
			helper.setObject(NOTIFICATIONCHANNEL);
			break;
		}
		case NC:
		{
			match(NC);
			helper.setObject(NOTIFICATIONCHANNEL);
			break;
		}
		case TRIGGER:
		{
			match(TRIGGER);
			helper.setObject(TRIGGER);
			break;
		}
		case TRG:
		{
			match(TRG);
			helper.setObject(TRIGGER);
			break;
		}
		case COMMANDCHANNEL:
		{
			match(COMMANDCHANNEL);
			helper.setObject(COMMANDCHANNEL);
			break;
		}
		case CC:
		{
			match(CC);
			helper.setObject(COMMANDCHANNEL);
			break;
		}
		case EVENTTYPE:
		{
			match(EVENTTYPE);
			helper.setObject(EVENTTYPE);
			break;
		}
		case ET:
		{
			match(ET);
			helper.setObject(EVENTTYPE);
			break;
		}
		case TRIGGERTYPE:
		{
			match(TRIGGERTYPE);
			helper.setObject(TRIGGERTYPE);
			break;
		}
		case TT:
		{
			match(TT);
			helper.setObject(TRIGGERTYPE);
			break;
		}
		case FIELDNAME:
		{
			match(FIELDNAME);
			helper.setObject(FIELDNAME);
			break;
		}
		case FN:
		{
			match(FN);
			helper.setObject(FIELDNAME);
			break;
		}
		case IOPORTS:
		{
			match(IOPORTS);
			helper.setObject(IOPORTS);
			break;
		}
		case IOP:
		{
			match(IOP);
			helper.setObject(IOPORTS);
			break;
		}
		case TAGFIELD:
		{
			match(TAGFIELD);
			helper.setObject(TAGFIELD);
			break;
		}
		case TF:
		{
			match(TF);
			helper.setObject(TF);
			break;
		}
		default:
		{
			throw new Exception(getFilename());
		}
		}
		}
	}
	
	public final void target_name() throws RecognitionException, TokenStreamException {
		
		Token  t = null;
		
		t = LT(1);
		match(IDENT);
		
					helper.setTargetName(t.getText());
				
	}
	
	public final void command_name() throws RecognitionException, TokenStreamException {
		
		
		{
		switch ( LA(1)) {
		case CMD_CREATE:
		{
			match(CMD_CREATE);
			helper.setCommand(CMD_CREATE);
			break;
		}
		case CMD_C:
		{
			match(CMD_C);
			helper.setCommand(CMD_CREATE);
			break;
		}
		case CMD_GETEPC:
		{
			match(CMD_GETEPC);
			helper.setCommand(CMD_GETEPC);
			break;
		}
		case CMD_GE:
		{
			match(CMD_GE);
			helper.setCommand(CMD_GETEPC);
			break;
		}
		case CMD_GETMANUFACTURER:
		{
			match(CMD_GETMANUFACTURER);
			helper.setCommand(CMD_GETMANUFACTURER);
			break;
		}
		case CMD_GMAN:
		{
			match(CMD_GMAN);
			helper.setCommand(CMD_GETMANUFACTURER);
			break;
		}
		case CMD_GETMODEL:
		{
			match(CMD_GETMODEL);
			helper.setCommand(CMD_GETMODEL);
			break;
		}
		case CMD_GMOD:
		{
			match(CMD_GMOD);
			helper.setCommand(CMD_GETMODEL);
			break;
		}
		case CMD_GETHANDLE:
		{
			match(CMD_GETHANDLE);
			helper.setCommand(CMD_GETHANDLE);
			break;
		}
		case CMD_GH:
		{
			match(CMD_GH);
			helper.setCommand(CMD_GETHANDLE);
			break;
		}
		case CMD_SETHANDLE:
		{
			match(CMD_SETHANDLE);
			helper.setCommand(CMD_SETHANDLE);
			break;
		}
		case CMD_SH:
		{
			match(CMD_SH);
			helper.setCommand(CMD_SETHANDLE);
			break;
		}
		case CMD_GETNAME:
		{
			match(CMD_GETNAME);
			helper.setCommand(CMD_GETNAME);
			break;
		}
		case CMD_GN:
		{
			match(CMD_GN);
			helper.setCommand(CMD_GETNAME);
			break;
		}
		case CMD_SETNAME:
		{
			match(CMD_SETNAME);
			helper.setCommand(CMD_SETNAME);
			break;
		}
		case CMD_SN:
		{
			match(CMD_SN);
			helper.setCommand(CMD_SETNAME);
			break;
		}
		case CMD_GETROLE:
		{
			match(CMD_GETROLE);
			helper.setCommand(CMD_GETROLE);
			break;
		}
		case CMD_GR:
		{
			match(CMD_GR);
			helper.setCommand(CMD_GETROLE);
			break;
		}
		case CMD_SETROLE:
		{
			match(CMD_SETROLE);
			helper.setCommand(CMD_SETROLE);
			break;
		}
		case CMD_SR:
		{
			match(CMD_SR);
			helper.setCommand(CMD_SETROLE);
			break;
		}
		case CMD_GET_TIME_TICKS:
		{
			match(CMD_GET_TIME_TICKS);
			helper.setCommand(CMD_GET_TIME_TICKS);
			break;
		}
		case CMD_GTIC:
		{
			match(CMD_GTIC);
			helper.setCommand(CMD_GET_TIME_TICKS);
			break;
		}
		case CMD_GET_TIME_UTC:
		{
			match(CMD_GET_TIME_UTC);
			helper.setCommand(CMD_GET_TIME_UTC);
			break;
		}
		case CMD_GUTC:
		{
			match(CMD_GUTC);
			helper.setCommand(CMD_GET_TIME_UTC);
			break;
		}
		case CMD_SET_TIME_UTC:
		{
			match(CMD_SET_TIME_UTC);
			helper.setCommand(CMD_SET_TIME_UTC);
			break;
		}
		case CMD_SUTC:
		{
			match(CMD_SUTC);
			helper.setCommand(CMD_SET_TIME_UTC);
			break;
		}
		case CMD_GET_MANUFACTURER_DESCRIPTION:
		{
			match(CMD_GET_MANUFACTURER_DESCRIPTION);
			helper.setCommand(CMD_GET_MANUFACTURER_DESCRIPTION);
			break;
		}
		case CMD_GMD:
		{
			match(CMD_GMD);
			helper.setCommand(CMD_GET_MANUFACTURER_DESCRIPTION);
			break;
		}
		case CMD_GET_CURRENT_SOURCE:
		{
			match(CMD_GET_CURRENT_SOURCE);
			helper.setCommand(CMD_GET_CURRENT_SOURCE);
			break;
		}
		case CMD_GCS:
		{
			match(CMD_GCS);
			helper.setCommand(CMD_GET_CURRENT_SOURCE);
			break;
		}
		case CMD_SET_CURRENT_SOURCE:
		{
			match(CMD_SET_CURRENT_SOURCE);
			helper.setCommand(CMD_SET_CURRENT_SOURCE);
			break;
		}
		case CMD_SCS:
		{
			match(CMD_SCS);
			helper.setCommand(CMD_SET_CURRENT_SOURCE);
			break;
		}
		case CMD_GET_CURRENT_DATA_SELECTOR:
		{
			match(CMD_GET_CURRENT_DATA_SELECTOR);
			helper.setCommand(CMD_GET_CURRENT_DATA_SELECTOR);
			break;
		}
		case CMD_GCDS:
		{
			match(CMD_GCDS);
			helper.setCommand(CMD_GET_CURRENT_DATA_SELECTOR);
			break;
		}
		case CMD_SET_CURRENT_DATA_SELECTOR:
		{
			match(CMD_SET_CURRENT_DATA_SELECTOR);
			helper.setCommand(CMD_SET_CURRENT_DATA_SELECTOR);
			break;
		}
		case CMD_SCDS:
		{
			match(CMD_SCDS);
			helper.setCommand(CMD_SET_CURRENT_DATA_SELECTOR);
			break;
		}
		case CMD_REMOVE_SOURCES:
		{
			match(CMD_REMOVE_SOURCES);
			helper.setCommand(CMD_REMOVE_SOURCES);
			break;
		}
		case CMD_RSRC:
		{
			match(CMD_RSRC);
			helper.setCommand(CMD_REMOVE_SOURCES);
			break;
		}
		case CMD_REMOVE_ALL_SOURCES:
		{
			match(CMD_REMOVE_ALL_SOURCES);
			helper.setCommand(CMD_REMOVE_ALL_SOURCES);
			break;
		}
		case CMD_RASRC:
		{
			match(CMD_RASRC);
			helper.setCommand(CMD_REMOVE_ALL_SOURCES);
			break;
		}
		case CMD_GET_SOURCE:
		{
			match(CMD_GET_SOURCE);
			helper.setCommand(CMD_GET_SOURCE);
			break;
		}
		case CMD_GSRC:
		{
			match(CMD_GSRC);
			helper.setCommand(CMD_GET_SOURCE);
			break;
		}
		case CMD_GET_ALL_SOURCES:
		{
			match(CMD_GET_ALL_SOURCES);
			helper.setCommand(CMD_GET_ALL_SOURCES);
			break;
		}
		case CMD_GASRC:
		{
			match(CMD_GASRC);
			helper.setCommand(CMD_GET_ALL_SOURCES);
			break;
		}
		case CMD_REMOVE_DATA_SELECTORS:
		{
			match(CMD_REMOVE_DATA_SELECTORS);
			helper.setCommand(CMD_REMOVE_DATA_SELECTORS);
			break;
		}
		case CMD_RDS:
		{
			match(CMD_RDS);
			helper.setCommand(CMD_REMOVE_DATA_SELECTORS);
			break;
		}
		case CMD_REMOVE_ALL_DATA_SELECTORS:
		{
			match(CMD_REMOVE_ALL_DATA_SELECTORS);
			helper.setCommand(CMD_REMOVE_ALL_DATA_SELECTORS);
			break;
		}
		case CMD_RADS:
		{
			match(CMD_RADS);
			helper.setCommand(CMD_REMOVE_ALL_DATA_SELECTORS);
			break;
		}
		case CMD_GET_DATA_SELECTOR:
		{
			match(CMD_GET_DATA_SELECTOR);
			helper.setCommand(CMD_GET_DATA_SELECTOR);
			break;
		}
		case CMD_GDS:
		{
			match(CMD_GDS);
			helper.setCommand(CMD_DATA_SELECTOR);
			break;
		}
		case CMD_GET_ALL_DATA_SELECTORS:
		{
			match(CMD_GET_ALL_DATA_SELECTORS);
			helper.setCommand(CMD_GET_ALL_DATA_SELECTORS);
			break;
		}
		case CMD_GADS:
		{
			match(CMD_GADS);
			helper.setCommand(CMD_GET_ALL_DATA_SELECTORS);
			break;
		}
		case CMD_REMOVE_NOTIFICATION_CHANNELS:
		{
			match(CMD_REMOVE_NOTIFICATION_CHANNELS);
			helper.setCommand(CMD_REMOVE_NOTIFICATION_CHANNELS);
			break;
		}
		case CMD_RNC:
		{
			match(CMD_RNC);
			helper.setCommand(CMD_REMOVE_NOTIFICATION_CHANNELS);
			break;
		}
		case CMD_REMOVE_ALL_NOTIFICATION_CHANNELS:
		{
			match(CMD_REMOVE_ALL_NOTIFICATION_CHANNELS);
			helper.setCommand(CMD_REMOVE_ALL_NOTIFICATION_CHANNELS);
			break;
		}
		case CMD_RANC:
		{
			match(CMD_RANC);
			helper.setCommand(CMD_REMOVE_ALL_NOTIFICATION_CHANNELS);
			break;
		}
		case CMD_GET_NOTIFICATION_CHANNEL:
		{
			match(CMD_GET_NOTIFICATION_CHANNEL);
			helper.setCommand(CMD_GET_NOTIFICATION_CHANNEL);
			break;
		}
		case CMD_GNC:
		{
			match(CMD_GNC);
			helper.setCommand(CMD_GET_NOTIFICATION_CHANNEL);
			break;
		}
		case CMD_GET_ALL_NOTIFICATION_CHANNEL:
		{
			match(CMD_GET_ALL_NOTIFICATION_CHANNEL);
			helper.setCommand(CMD_GET_ALL_NOTIFICATION_CHANNEL);
			break;
		}
		case CMD_GANC:
		{
			match(CMD_GANC);
			helper.setCommand(CMD_GET_ALL_NOTIFICATION_CHANNEL);
			break;
		}
		case CMD_REMOVE_TRIGGERS:
		{
			match(CMD_REMOVE_TRIGGERS);
			helper.setCommand(CMD_REMOVE_TRIGGERS);
			break;
		}
		case CMD_RTRG:
		{
			match(CMD_RTRG);
			helper.setCommand(CMD_REMOVE_TRIGGERS);
			break;
		}
		case CMD_REMOVE_ALL_TRIGGERS:
		{
			match(CMD_REMOVE_ALL_TRIGGERS);
			helper.setCommand(CMD_REMOVE_ALL_TRIGGERS);
			break;
		}
		case CMD_RATRG:
		{
			match(CMD_RATRG);
			helper.setCommand(CMD_REMOVE_ALL_TRIGGERS);
			break;
		}
		case CMD_GET_TRIGGER:
		{
			match(CMD_GET_TRIGGER);
			helper.setCommand(CMD_GET_TRIGGER);
			break;
		}
		case CMD_GTRG:
		{
			match(CMD_GTRG);
			helper.setCommand(CMD_GET_TRIGGER);
			break;
		}
		case CMD_GET_ALL_TRIGGERS:
		{
			match(CMD_GET_ALL_TRIGGERS);
			helper.setCommand(CMD_GET_ALL_TRIGGERS);
			break;
		}
		case CMD_GATRG:
		{
			match(CMD_GATRG);
			helper.setCommand(CMD_GET_ALL_TRIGGERS);
			break;
		}
		case CMD_REMOVE_TAG_SELECTORS:
		{
			match(CMD_REMOVE_TAG_SELECTORS);
			helper.setCommand(CMD_REMOVE_TAG_SELECTORS);
			break;
		}
		case CMD_RTS:
		{
			match(CMD_RTS);
			helper.setCommand(CMD_REMOVE_TAG_SELECTORS);
			break;
		}
		case CMD_REMOVE_ALL_TAG_SELECTORS:
		{
			match(CMD_REMOVE_ALL_TAG_SELECTORS);
			helper.setCommand(CMD_REMOVE_ALL_TAG_SELECTORS);
			break;
		}
		case CMD_RATS:
		{
			match(CMD_RATS);
			helper.setCommand(CMD_REMOVE_ALL_TAG_SELECTORS);
			break;
		}
		case CMD_GET_TAG_SELECTOR:
		{
			match(CMD_GET_TAG_SELECTOR);
			helper.setCommand(CMD_GET_TAG_SELECTOR);
			break;
		}
		case CMD_GTS:
		{
			match(CMD_GTS);
			helper.setCommand(CMD_GET_TAG_SELECTOR);
			break;
		}
		case CMD_GET_ALL_TAG_SELECTORS:
		{
			match(CMD_GET_ALL_TAG_SELECTORS);
			helper.setCommand(CMD_GET_ALL_TAG_SELECTORS);
			break;
		}
		case CMD_GATS:
		{
			match(CMD_GATS);
			helper.setCommand(CMD_GET_ALL_TAG_SELECTORS);
			break;
		}
		case CMD_REMOVE_TAG_FIELDS:
		{
			match(CMD_REMOVE_TAG_FIELDS);
			helper.setCommand(CMD_REMOVE_TAG_FIELDS);
			break;
		}
		case CMD_RTF:
		{
			match(CMD_RTF);
			helper.setCommand(CMD_REMOVE_TAG_FIELDS);
			break;
		}
		case CMD_REMOVE_ALL_TAG_FIELDS:
		{
			match(CMD_REMOVE_ALL_TAG_FIELDS);
			helper.setCommand(CMD_REMOVE_ALL_TAG_FIELDS);
			break;
		}
		case CMD_RATF:
		{
			match(CMD_RATF);
			helper.setCommand(CMD_REMOVE_ALL_TAG_FIELDS);
			break;
		}
		case CMD_GET_TAG_FIELD:
		{
			match(CMD_GET_TAG_FIELD);
			helper.setCommand(CMD_GET_TAG_FIELD);
			break;
		}
		case CMD_GTF:
		{
			match(CMD_GTF);
			helper.setCommand(CMD_GET_TAG_FIELD);
			break;
		}
		case CMD_GET_ALL_TAG_FIELDS:
		{
			match(CMD_GET_ALL_TAG_FIELDS);
			helper.setCommand(CMD_GET_ALL_TAG_FIELDS);
			break;
		}
		case CMD_RESET_TO_DEFAULT_SETTINGS:
		{
			match(CMD_RESET_TO_DEFAULT_SETTINGS);
			helper.setCommand(CMD_RESET_TO_DEFAULT_SETTINGS);
			break;
		}
		case CMD_RESET:
		{
			match(CMD_RESET);
			helper.setCommand(CMD_RESET_TO_DEFAULT_SETTINGS);
			break;
		}
		case CMD_REBOOT:
		{
			match(CMD_REBOOT);
			helper.setCommand(CMD_REBOOT);
			break;
		}
		case CMD_BOOT:
		{
			match(CMD_BOOT);
			helper.setCommand(CMD_REBOOT);
			break;
		}
		case CMD_GOODBYE:
		{
			match(CMD_GOODBYE);
			helper.setCommand(CMD_GOODBYE);
			break;
		}
		case CMD_BYE:
		{
			match(CMD_BYE);
			helper.setCommand(CMD_GOODBYE);
			break;
		}
		case CMD_GET_READ_POINT:
		{
			match(CMD_GET_READ_POINT);
			helper.setCommand(CMD_GET_READ_POINT);
			break;
		}
		case CMD_GRP:
		{
			match(CMD_GRP);
			helper.setCommand(CMD_GET_READ_POINT);
			break;
		}
		case CMD_GET_ALL_READ_POINTS:
		{
			match(CMD_GET_ALL_READ_POINTS);
			helper.setCommand(CMD_GET_ALL_READ_POINTS);
			break;
		}
		case CMD_GARP:
		{
			match(CMD_GARP);
			helper.setCommand(CMD_GET_ALL_READ_POINTS);
			break;
		}
		case CMD_IS_FIXED:
		{
			match(CMD_IS_FIXED);
			helper.setCommand(CMD_IS_FIXED);
			break;
		}
		case CMD_ISFX:
		{
			match(CMD_ISFX);
			helper.setCommand(CMD_IS_FIXED);
			break;
		}
		case CMD_ADD_READ_POINTS:
		{
			match(CMD_ADD_READ_POINTS);
			helper.setCommand(CMD_ADD_READ_POINTS);
			break;
		}
		case CMD_ARP:
		{
			match(CMD_ARP);
			helper.setCommand(CMD_ADD_READ_POINTS);
			break;
		}
		case CMD_REMOVE_READ_POINTS:
		{
			match(CMD_REMOVE_READ_POINTS);
			helper.setCommand(CMD_REMOVE_READ_POINTS);
			break;
		}
		case CMD_RRP:
		{
			match(CMD_RRP);
			helper.setCommand(CMD_REMOVE_READ_POINTS);
			break;
		}
		case CMD_REMOVE_ALL_READ_POINTS:
		{
			match(CMD_REMOVE_ALL_READ_POINTS);
			helper.setCommand(CMD_REMOVE_ALL_READ_POINTS);
			break;
		}
		case CMD_RARP:
		{
			match(CMD_RARP);
			helper.setCommand(CMD_REMOVE_ALL_READ_POINTS);
			break;
		}
		case CMD_ADD_READ_TRIGGERS:
		{
			match(CMD_ADD_READ_TRIGGERS);
			helper.setCommand(CMD_ADD_READ_TRIGGERS);
			break;
		}
		case CMD_ART:
		{
			match(CMD_ART);
			helper.setCommand(CMD_ADD_READ_TRIGGERS);
			break;
		}
		case CMD_REMOVE_READ_TRIGGERS:
		{
			match(CMD_REMOVE_READ_TRIGGERS);
			helper.setCommand(CMD_REMOVE_READ_TRIGGERS);
			break;
		}
		case CMD_RRT:
		{
			match(CMD_RRT);
			helper.setCommand(CMD_REMOVE_READ_TRIGGERS);
			break;
		}
		case CMD_REMOVE_ALL_READ_TRIGGERS:
		{
			match(CMD_REMOVE_ALL_READ_TRIGGERS);
			helper.setCommand(CMD_REMOVE_ALL_READ_TRIGGERS);
			break;
		}
		case CMD_RART:
		{
			match(CMD_RART);
			helper.setCommand(CMD_REMOVE_ALL_READ_TRIGGERS);
			break;
		}
		case CMD_GET_READ_TRIGGER:
		{
			match(CMD_GET_READ_TRIGGER);
			helper.setCommand(CMD_GET_READ_TRIGGER);
			break;
		}
		case CMD_GRT:
		{
			match(CMD_GRT);
			helper.setCommand(CMD_GET_READ_TRIGGER);
			break;
		}
		case CMD_GET_ALL_READ_TRIGGERS:
		{
			match(CMD_GET_ALL_READ_TRIGGERS);
			helper.setCommand(CMD_GET_ALL_READ_TRIGGERS);
			break;
		}
		case CMD_GART:
		{
			match(CMD_GART);
			helper.setCommand(CMD_GET_ALL_READ_TRIGGERS);
			break;
		}
		case CMD_ADD_TAG_SELECTORS:
		{
			match(CMD_ADD_TAG_SELECTORS);
			helper.setCommand(CMD_ADD_TAG_SELECTORS);
			break;
		}
		case CMD_ATS:
		{
			match(CMD_ATS);
			helper.setCommand(CMD_ADD_TAG_SELECTORS);
			break;
		}
		case CMD_GET_GLIMPSED_TIMEOUT:
		{
			match(CMD_GET_GLIMPSED_TIMEOUT);
			helper.setCommand(CMD_GET_GLIMPSED_TIMEOUT);
			break;
		}
		case CMD_GGTO:
		{
			match(CMD_GGTO);
			helper.setCommand(CMD_GET_GLIMPSED_TIMEOUT);
			break;
		}
		case CMD_SET_GLIMPSED_TIMEOUT:
		{
			match(CMD_SET_GLIMPSED_TIMEOUT);
			helper.setCommand(CMD_SET_GLIMPSED_TIMEOUT);
			break;
		}
		case CMD_SGTO:
		{
			match(CMD_SGTO);
			helper.setCommand(CMD_SET_GLIMPSED_TIMEOUT);
			break;
		}
		case CMD_GET_OBSERVED_THRESHOLD:
		{
			match(CMD_GET_OBSERVED_THRESHOLD);
			helper.setCommand(CMD_GET_OBSERVED_THRESHOLD);
			break;
		}
		case CMD_GOTH:
		{
			match(CMD_GOTH);
			helper.setCommand(CMD_GET_OBSERVED_THRESHOLD);
			break;
		}
		case CMD_SET_OBSERVED_THRESHOLD:
		{
			match(CMD_SET_OBSERVED_THRESHOLD);
			helper.setCommand(CMD_SET_OBSERVED_THRESHOLD);
			break;
		}
		case CMD_SOTH:
		{
			match(CMD_SOTH);
			helper.setCommand(CMD_SET_OBSERVED_THRESHOLD);
			break;
		}
		case CMD_GET_OBSERVED_TIMEOUT:
		{
			match(CMD_GET_OBSERVED_TIMEOUT);
			helper.setCommand(CMD_GET_OBSERVED_TIMEOUT);
			break;
		}
		case CMD_GOTO:
		{
			match(CMD_GOTO);
			helper.setCommand(CMD_GET_OBSERVED_TIMEOUT);
			break;
		}
		case CMD_SET_OBSERVED_TIMEOUT:
		{
			match(CMD_SET_OBSERVED_TIMEOUT);
			helper.setCommand(CMD_SET_OBSERVED_TIMEOUT);
			break;
		}
		case CMD_SOTO:
		{
			match(CMD_SOTO);
			helper.setCommand(CMD_SET_OBSERVED_TIMEOUT);
			break;
		}
		case CMD_GET_LOST_TIMEOUT:
		{
			match(CMD_GET_LOST_TIMEOUT);
			helper.setCommand(CMD_GET_LOST_TIMEOUT);
			break;
		}
		case CMD_GLTO:
		{
			match(CMD_GLTO);
			helper.setCommand(CMD_GET_LOST_TIMEOUT);
			break;
		}
		case CMD_SET_LOST_TIMEOUT:
		{
			match(CMD_SET_LOST_TIMEOUT);
			helper.setCommand(CMD_SET_LOST_TIMEOUT);
			break;
		}
		case CMD_SLTO:
		{
			match(CMD_SLTO);
			helper.setCommand(CMD_SET_LOST_TIMEOUT);
			break;
		}
		case CMD_RAW_READ_IDS:
		{
			match(CMD_RAW_READ_IDS);
			helper.setCommand(CMD_RAW_READ_IDS);
			break;
		}
		case CMD_RRID:
		{
			match(CMD_RRID);
			helper.setCommand(CMD_RAW_READ_IDS);
			break;
		}
		case CMD_READ_IDS:
		{
			match(CMD_READ_IDS);
			helper.setCommand(CMD_READ_IDS);
			break;
		}
		case CMD_RID:
		{
			match(CMD_RID);
			helper.setCommand(CMD_READ_IDS);
			break;
		}
		case CMD_READ:
		{
			match(CMD_READ);
			helper.setCommand(CMD_READ);
			break;
		}
		case CMD_R:
		{
			match(CMD_R);
			helper.setCommand(CMD_READ);
			break;
		}
		case CMD_WRITE_ID:
		{
			match(CMD_WRITE_ID);
			helper.setCommand(CMD_WRITE_ID);
			break;
		}
		case CMD_WID:
		{
			match(CMD_WID);
			helper.setCommand(CMD_WRITE_ID);
			break;
		}
		case CMD_WRITE:
		{
			match(CMD_WRITE);
			helper.setCommand(CMD_WRITE);
			break;
		}
		case CMD_W:
		{
			match(CMD_W);
			helper.setCommand(CMD_WRITE);
			break;
		}
		case CMD_KILL:
		{
			match(CMD_KILL);
			helper.setCommand(CMD_KILL);
			break;
		}
		case CMD_K:
		{
			match(CMD_K);
			helper.setCommand(CMD_KILL);
			break;
		}
		case CMD_GET_READ_CYCLES_PER_TRIGGER:
		{
			match(CMD_GET_READ_CYCLES_PER_TRIGGER);
			helper.setCommand(CMD_GET_READ_CYCLES_PER_TRIGGER);
			break;
		}
		case CMD_GRCPT:
		{
			match(CMD_GRCPT);
			helper.setCommand(CMD_GET_READ_CYCLES_PER_TRIGGER);
			break;
		}
		case CMD_SET_READ_CYCLES_PER_TRIGGER:
		{
			match(CMD_SET_READ_CYCLES_PER_TRIGGER);
			helper.setCommand(CMD_SET_READ_CYCLES_PER_TRIGGER);
			break;
		}
		case CMD_SRCPT:
		{
			match(CMD_SRCPT);
			helper.setCommand(CMD_SET_READ_CYCLES_PER_TRIGGER);
			break;
		}
		case CMD_GET_MAX_READ_DUTY_CYCLE:
		{
			match(CMD_GET_MAX_READ_DUTY_CYCLE);
			helper.setCommand(CMD_GET_MAX_READ_DUTY_CYCLE);
			break;
		}
		case CMD_GMRDC:
		{
			match(CMD_GMRDC);
			helper.setCommand(CMD_GET_MAX_READ_DUTY_CYCLE);
			break;
		}
		case CMD_SET_MAX_READ_DUTY_CYCLE:
		{
			match(CMD_SET_MAX_READ_DUTY_CYCLE);
			helper.setCommand(CMD_SET_MAX_READ_DUTY_CYCLE);
			break;
		}
		case CMD_SMRDC:
		{
			match(CMD_SMRDC);
			helper.setCommand(CMD_SET_MAX_READ_DUTY_CYCLE);
			break;
		}
		case CMD_GET_READ_TIMEOUT:
		{
			match(CMD_GET_READ_TIMEOUT);
			helper.setCommand(CMD_GET_READ_TIMEOUT);
			break;
		}
		case CMD_GRTO:
		{
			match(CMD_GRTO);
			helper.setCommand(CMD_GET_READ_TIMEOUT);
			break;
		}
		case CMD_SET_READ_TIMEOUT:
		{
			match(CMD_SET_READ_TIMEOUT);
			helper.setCommand(CMD_SET_READ_TIMEOUT);
			break;
		}
		case CMD_SRTO:
		{
			match(CMD_SRTO);
			helper.setCommand(CMD_SET_READ_TIMEOUT);
			break;
		}
		case CMD_GET_SESSION:
		{
			match(CMD_GET_SESSION);
			helper.setCommand(CMD_GET_SESSION);
			break;
		}
		case CMD_GSS:
		{
			match(CMD_GSS);
			helper.setCommand(CMD_GET_SESSION);
			break;
		}
		case CMD_SET_SESSION:
		{
			match(CMD_SET_SESSION);
			helper.setCommand(CMD_SET_SESSION);
			break;
		}
		case CMD_SSS:
		{
			match(CMD_SSS);
			helper.setCommand(CMD_SET_SESSION);
			break;
		}
		case CMD_GET_MAX_NUMBER_SUPPORTED:
		{
			match(CMD_GET_MAX_NUMBER_SUPPORTED);
			helper.setCommand(CMD_GET_MAX_NUMBER_SUPPORTED);
			break;
		}
		case CMD_GMAX:
		{
			match(CMD_GMAX);
			helper.setCommand(CMD_GET_MAX_NUMBER_SUPPORTED);
			break;
		}
		case CMD_GMX:
		{
			match(CMD_GMX);
			helper.setCommand(CMD_GET_MAX_NUMBER_SUPPORTED);
			break;
		}
		case CMD_GET_TYPE:
		{
			match(CMD_GET_TYPE);
			helper.setCommand(CMD_GET_TYPE);
			break;
		}
		case CMD_GT:
		{
			match(CMD_GT);
			helper.setCommand(CMD_GET_TYPE);
			break;
		}
		case CMD_GET_VALUE:
		{
			match(CMD_GET_VALUE);
			helper.setCommand(CMD_GET_VALUE);
			break;
		}
		case CMD_GV:
		{
			match(CMD_GV);
			helper.setCommand(CMD_GET_VALUE);
			break;
		}
		case CMD_FIRE:
		{
			match(CMD_FIRE);
			helper.setCommand(CMD_FIRE);
			break;
		}
		case CMD_F:
		{
			match(CMD_F);
			helper.setCommand(CMD_FIRE);
			break;
		}
		case CMD_GET_MASK:
		{
			match(CMD_GET_MASK);
			helper.setCommand(CMD_GET_MASK);
			break;
		}
		case CMD_GM:
		{
			match(CMD_GM);
			helper.setCommand(CMD_GET_MASK);
			break;
		}
		case CMD_GET_INCLUSIVE_FLAG:
		{
			match(CMD_GET_INCLUSIVE_FLAG);
			helper.setCommand(CMD_GET_INCLUSIVE_FLAG);
			break;
		}
		case CMD_GIF:
		{
			match(CMD_GIF);
			helper.setCommand(CMD_GET_INCLUSIVE_FLAG);
			break;
		}
		case CMD_GET_ADDRESS:
		{
			match(CMD_GET_ADDRESS);
			helper.setCommand(CMD_GET_ADDRESS);
			break;
		}
		case CMD_GADR:
		{
			match(CMD_GADR);
			helper.setCommand(CMD_GET_ADDRESS);
			break;
		}
		case CMD_GET_EFFECTIVE_ADDRESS:
		{
			match(CMD_GET_EFFECTIVE_ADDRESS);
			helper.setCommand(CMD_GET_EFFECTIVE_ADDRESS);
			break;
		}
		case CMD_GEADR:
		{
			match(CMD_GEADR);
			helper.setCommand(CMD_GET_EFFECTIVE_ADDRESS);
			break;
		}
		case CMD_SET_ADDRESS:
		{
			match(CMD_SET_ADDRESS);
			helper.setCommand(CMD_SET_ADDRESS);
			break;
		}
		case CMD_SADR:
		{
			match(CMD_SADR);
			helper.setCommand(CMD_SET_ADDRESS);
			break;
		}
		case CMD_SET_DATA_SELECTOR:
		{
			match(CMD_SET_DATA_SELECTOR);
			helper.setCommand(CMD_SET_DATA_SELECTOR);
			break;
		}
		case CMD_SDS:
		{
			match(CMD_SDS);
			helper.setCommand(CMD_SET_DATA_SELECTOR);
			break;
		}
		case CMD_ADD_SOURCES:
		{
			match(CMD_ADD_SOURCES);
			helper.setCommand(CMD_ADD_SOURCES);
			break;
		}
		case CMD_ASRC:
		{
			match(CMD_ASRC);
			helper.setCommand(CMD_ADD_SOURCES);
			break;
		}
		case CMD_ADD_NOTIFICATIOIN_TRIGGERS:
		{
			match(CMD_ADD_NOTIFICATIOIN_TRIGGERS);
			helper.setCommand(CMD_ADD_NOTIFICATION_TRIGGERS);
			break;
		}
		case CMD_ANT:
		{
			match(CMD_ANT);
			helper.setCommand(CMD_ADD_NOTIFICATION_TRIGGERS);
			break;
		}
		case CMD_REMOVE_NOTIFICATION_TRIGGERS:
		{
			match(CMD_REMOVE_NOTIFICATION_TRIGGERS);
			helper.setCommand(CMD_REMOVE_NOTIFICATION_TRIGGERS);
			break;
		}
		case CMD_RNT:
		{
			match(CMD_RNT);
			helper.setCommand(CMD_REMOVE_NOTIFICATION_TRIGGERS);
			break;
		}
		case CMD_REMOVE_ALL_NOTIFICATION_TRIGGERS:
		{
			match(CMD_REMOVE_ALL_NOTIFICATION_TRIGGERS);
			helper.setCommand(CMD_REMOVE_ALL_NOTIFICATION_TRIGGERS);
			break;
		}
		case CMD_RANT:
		{
			match(CMD_RANT);
			helper.setCommand(CMD_REMOVE_ALL_NOTIFICATION_TRIGGERS);
			break;
		}
		case CMD_GET_NOTIFICATION_TRIGGER:
		{
			match(CMD_GET_NOTIFICATION_TRIGGER);
			helper.setCommand(CMD_GET_NOTIFICATION_TRIGGER);
			break;
		}
		case CMD_GNT:
		{
			match(CMD_GNT);
			helper.setCommand(CMD_GET_NOTIFICATION_TRIGGER);
			break;
		}
		case CMD_GET_ALL_NOTIFICATION_TRIGGERS:
		{
			match(CMD_GET_ALL_NOTIFICATION_TRIGGERS);
			helper.setCommand(CMD_GET_ALL_NOTIFICATION_TRIGGERS);
			break;
		}
		case CMD_GANT:
		{
			match(CMD_GANT);
			helper.setCommand(CMD_GET_ALL_NOTIFICATION_TRIGGERS);
			break;
		}
		case CMD_READ_QUEUED_DATA:
		{
			match(CMD_READ_QUEUED_DATA);
			helper.setCommand(CMD_READ_QUEUED_DATA);
			break;
		}
		case CMD_RQD:
		{
			match(CMD_RQD);
			helper.setCommand(CMD_READ_QUEUED_DATA);
			break;
		}
		case CMD_ADD_FIELD_NAMES:
		{
			match(CMD_ADD_FIELD_NAMES);
			helper.setCommand(CMD_ADD_FIELD_NAMES);
			break;
		}
		case CMD_AFN:
		{
			match(CMD_AFN);
			helper.setCommand(CMD_ADD_FIELD_NAMES);
			break;
		}
		case CMD_REMOVE_FIELD_NAMES:
		{
			match(CMD_REMOVE_FIELD_NAMES);
			helper.setCommand(CMD_REMOVE_FIELD_NAMES);
			break;
		}
		case CMD_RFN:
		{
			match(CMD_RFN);
			helper.setCommand(CMD_REMOVE_FIELD_NAMES);
			break;
		}
		case CMD_REMOVE_ALL_FIELD_NAMES:
		{
			match(CMD_REMOVE_ALL_FIELD_NAMES);
			helper.setCommand(CMD_REMOVE_ALL_FIELD_NAMES);
			break;
		}
		case CMD_RAFN:
		{
			match(CMD_RAFN);
			helper.setCommand(CMD_REMOVE_ALL_FIELD_NAMES);
			break;
		}
		case CMD_GET_ALL_FIELD_NAMES:
		{
			match(CMD_GET_ALL_FIELD_NAMES);
			helper.setCommand(CMD_GET_ALL_FIELD_NAMES);
			break;
		}
		case CMD_GAFN:
		{
			match(CMD_GAFN);
			helper.setCommand(CMD_GET_ALL_FIELD_NAMES);
			break;
		}
		case CMD_ADD_EVENT_FILTERS:
		{
			match(CMD_ADD_EVENT_FILTERS);
			helper.setCommand(CMD_ADD_EVENT_FILTERS);
			break;
		}
		case CMD_AEF:
		{
			match(CMD_AEF);
			helper.setCommand(CMD_ADD_EVENT_FILTERS);
			break;
		}
		case CMD_REMOVE_EVENT_FILTERS:
		{
			match(CMD_REMOVE_EVENT_FILTERS);
			helper.setCommand(CMD_REMOVE_EVENT_FILTERS);
			break;
		}
		case CMD_REF:
		{
			match(CMD_REF);
			helper.setCommand(CMD_REMOVE_EVENT_FILTERS);
			break;
		}
		case CMD_REMOVE_ALL_EVENT_FILTERS:
		{
			match(CMD_REMOVE_ALL_EVENT_FILTERS);
			helper.setCommand(CMD_REMOVE_ALL_EVENT_FILTERS);
			break;
		}
		case CMD_RAEF:
		{
			match(CMD_RAEF);
			helper.setCommand(CMD_REMOVE_ALL_EVENT_FILTERS);
			break;
		}
		case CMD_GET_ALL_EVENT_FILTERS:
		{
			match(CMD_GET_ALL_EVENT_FILTERS);
			helper.setCommand(CMD_GET_ALL_EVENT_FILTERS);
			break;
		}
		case CMD_GAEF:
		{
			match(CMD_GAEF);
			helper.setCommand(CMD_GET_ALL_EVENT_FILTERS);
			break;
		}
		case CMD_ADD_TAG_FIELD_NAMES:
		{
			match(CMD_ADD_TAG_FIELD_NAMES);
			helper.setCommand(CMD_ADD_TAG_FIELD_NAMES);
			break;
		}
		case CMD_ATFN:
		{
			match(CMD_ATFN);
			helper.setCommand(CMD_ADD_TAG_FIELD_NAMES);
			break;
		}
		case CMD_REMOVE_TAG_FIELD_NAMES:
		{
			match(CMD_REMOVE_TAG_FIELD_NAMES);
			helper.setCommand(CMD_REMOVE_TAG_FIELD_NAMES);
			break;
		}
		case CMD_RTFN:
		{
			match(CMD_RTFN);
			helper.setCommand(CMD_REMOVE_TAG_FIELD_NAMES);
			break;
		}
		case CMD_REMOVE_ALL_TAG_FIELD_NAMES:
		{
			match(CMD_REMOVE_ALL_TAG_FIELD_NAMES);
			helper.setCommand(CMD_REMOVE_ALL_TAG_FIELD_NAMES);
			break;
		}
		case CMD_RATFN:
		{
			match(CMD_RATFN);
			helper.setCommand(CMD_REMOVE_ALL_TAG_FIELD_NAMES);
			break;
		}
		case CMD_GET_ALL_TAG_FIELD_NAMES:
		{
			match(CMD_GET_ALL_TAG_FIELD_NAMES);
			helper.setCommand(CMD_GET_ALL_TAG_FIELD_NAMES);
			break;
		}
		case CMD_GATFN:
		{
			match(CMD_GATFN);
			helper.setCommand(CMD_GET_ALL_TAG_FIELD_NAMES);
			break;
		}
		case CMD_GET_SUPPORTED_TYPES:
		{
			match(CMD_GET_SUPPORTED_TYPES);
			helper.setCommand(CMD_GET_SUPPORTED_TYPES);
			break;
		}
		case CMD_GST:
		{
			match(CMD_GST);
			helper.setCommand(CMD_GET_SUPPORTED_TYPES);
			break;
		}
		case CMD_GET_SUPPORTED_NAMES:
		{
			match(CMD_GET_SUPPORTED_NAMES);
			helper.setCommand(CMD_GET_SUPPORTED_NAMES);
			break;
		}
		case CMD_GSN:
		{
			match(CMD_GSN);
			helper.setCommand(CMD_GET_SUPPORTED_NAMES);
			break;
		}
		case CMD_GET_TAG_FIELD_NAME:
		{
			match(CMD_GET_TAG_FIELD_NAME);
			helper.setCommand(CMD_GET_TAG_FIELD_NAME);
			break;
		}
		case CMD_GTFN:
		{
			match(CMD_GTFN);
			helper.setCommand(CMD_GET_TAG_FIELD_NAME);
			break;
		}
		case CMD_SET_TAG_FIELD_NAME:
		{
			match(CMD_SET_TAG_FIELD_NAME);
			helper.setCommand(CMD_SET_TAG_FIELD_NAME);
			break;
		}
		case CMD_STFN:
		{
			match(CMD_STFN);
			helper.setCommand(CMD_SET_TAG_FIELD_NAME);
			break;
		}
		case CMD_GET_MEMORY_BANK:
		{
			match(CMD_GET_MEMORY_BANK);
			helper.setCommand(CMD_GET_MEMORY_BANK);
			break;
		}
		case CMD_GMB:
		{
			match(CMD_GMB);
			helper.setCommand(CMD_GET_MEMORY_BANK);
			break;
		}
		case CMD_SET_MEMORY_BANK:
		{
			match(CMD_SET_MEMORY_BANK);
			helper.setCommand(CMD_SET_MEMORY_BANK);
			break;
		}
		case CMD_SMB:
		{
			match(CMD_SMB);
			helper.setCommand(CMD_SET_MEMORY_BANK);
			break;
		}
		case CMD_GET_OFFSET:
		{
			match(CMD_GET_OFFSET);
			helper.setCommand(CMD_GET_OFFSET);
			break;
		}
		case CMD_GOFF:
		{
			match(CMD_GOFF);
			helper.setCommand(CMD_GET_OFFSET);
			break;
		}
		case CMD_SET_OFFSET:
		{
			match(CMD_SET_OFFSET);
			helper.setCommand(CMD_SET_OFFSET);
			break;
		}
		case CMD_SOFF:
		{
			match(CMD_SOFF);
			helper.setCommand(CMD_SET_OFFSET);
			break;
		}
		case CMD_GET_LENGTH:
		{
			match(CMD_GET_LENGTH);
			helper.setCommand(CMD_GET_LENGTH);
			break;
		}
		case CMD_GLEN:
		{
			match(CMD_GLEN);
			helper.setCommand(CMD_GET_LENGTH);
			break;
		}
		case CMD_SET_LENGTH:
		{
			match(CMD_SET_LENGTH);
			helper.setCommand(CMD_SET_LENGTH);
			break;
		}
		case CMD_SLEN:
		{
			match(CMD_SLEN);
			helper.setCommand(CMD_SET_LENGTH);
			break;
		}
		
		}
		}
	}
	
	public final void parameter_list() throws RecognitionException, TokenStreamException {
		
		
				Parameter p = null;
			
		
		try {
			p=parameter();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
					helper.writeParameter(p);
				
		{
		_loop927:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				try {
					p=parameter();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
					 		helper.writeParameter(p);
					 	
			}
			else {
				break _loop927;
			}
			
		} while (true);
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
	
	public final Parameter  parameter() throws Exception {
		Parameter param;
		
		Token  i = null;
		Token  s = null;
		
					Parameter valParam = null;
					ListParameter listParam = null;
					ListParameter workingList = null;
					PairParameter pairParam = null;
					param = null;
					String hex = null;
					
				
		
		switch ( LA(1)) {
		case IDENT:
		case INT:
		{
			{
			switch ( LA(1)) {
			case INT:
			{
				i = LT(1);
				match(INT);
				
							int r = Integer.parseInt(i.getText());
							valParam = new ValueParameter(r);
						
				break;
			}
			case IDENT:
			{
				s = LT(1);
				match(IDENT);
				{
				switch ( LA(1)) {
				case ASSIGN:
				{
					match(ASSIGN);
					hex=hex_val();
					break;
				}
				case LF:
				case COMMA:
				case RBRACKET:
				{
					break;
				}
				default:
				{
					throw new Exception(getFilename());
				}
				}
				}
				
							if (s != null && hex != null) {
								//it's a field-value-pair
								valParam = new PairParameter(s.getText(), hex);
							} else {
								//it's a normal ValueParameter
								valParam = new ValueParameter(s.getText());
							}
						
				break;
			}
			default:
			{
				throw new Exception(getFilename());
			}
			}
			}
			
						if (helper.topState() == TextCommandParserHelper.STATE_LIST_PARAMETER) {
							//Add a parameter to the current working list
							try {
								workingList = helper.popList();
								workingList.addParameter(valParam);
								helper.pushList(workingList);
								param = workingList;
							} catch (TextCommandParserException e) {/* nothing to handle */}
							//TODO: Evtl. recognition exception werfen
						} else {
							param = valParam;
						}
					
			break;
		}
		case LBRACKET:
		{
			listParam=list_val();
			
						if (helper.topState() == TextCommandParserHelper.STATE_LIST_PARAMETER) {
							//We have a nested list, add the current list to the (parent) working list
							try {
								workingList = helper.popList();
								workingList.addParameter(listParam);
								param = workingList;
							} catch (TextCommandParserException e) {/* nothing to handle */}
							//TODO: Evtl. recognition exception werfen
						} else {
							//Normal case, push list to the parameter stack
							param = listParam;
						}		
					
			break;
		}
		default:
			if ((_tokenSet_3.member(LA(1)))) {
				valParam=keyword2ident();
				
							/* because a keyword has higher priority than an identifier, command_name is 
							   matched here mistakenly. Just handle it as a normal identifier. */
							if (helper.topState() == TextCommandParserHelper.STATE_LIST_PARAMETER) {
								//Add a parameter to the current working list
								try {
									workingList = helper.popList();
									workingList.addParameter(valParam);
									helper.pushList(workingList);
									param = workingList;
								} catch (TextCommandParserException e) {/* nothing to handle */}
								//TODO: Evtl. recognition exception werfen
							} else {
								param = valParam;
							}  
							   
						
			}
		else {
			throw new Exception(getFilename());
		}
		}
		return param;
	}
	
	public final String  hex_val() throws Exception {
		String hex;
		
		Token  s = null;
		Token  i = null;
		
				hex = null;
			
		
		switch ( LA(1)) {
		case IDENT:
		{
			s = LT(1);
			match(IDENT);
			
						hex = s.getText();
					
			break;
		}
		case INT:
		{
			i = LT(1);
			match(INT);
			
						hex = i.getText();
					
			break;
		}
		default:
		{
			throw new Exception(getFilename());
		}
		}
		return hex;
	}
	
	public final ListParameter  list_val() throws Exception {
		ListParameter listParam;
		
		
				Parameter p = null;
				listParam = null;
				ListParameter workingList = null;
			
		
		match(LBRACKET);
		
					//Create a new list and push it to the list stack to use it as the working list
					workingList = new ListParameter();
					//workingList.addParameter(p);
					helper.pushList(workingList);
					helper.pushState(TextCommandParserHelper.STATE_LIST_PARAMETER);
				
		{
		if ((_tokenSet_2.member(LA(1)))) {
			p=parameter();
			{
			_loop936:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					parameter();
				}
				else {
					break _loop936;
				}
				
			} while (true);
			}
		}
		else if ((LA(1)==RBRACKET)) {
		}
		else {
			throw new Exception(getFilename());
		}
		
		}
		match(RBRACKET);
		
					//Remove the working list and return it
					try {
						workingList = helper.popList();
						helper.popState();
						listParam = workingList;
					} catch (TextCommandParserException e) { /* nothing to handle here */ }
					//TODO: Evtl. RecognitionException werfen	
				
				
		return listParam;
	}
	
	public final ValueParameter  keyword2ident() throws Exception {
		ValueParameter p;
		
			p = null;	
		
		
					Token s = LT(1);
					p = new ValueParameter(s.getText());
				
		{
		switch ( LA(1)) {
		case READERDEVICE:
		{
			match(READERDEVICE);
			break;
		}
		case RD:
		{
			match(RD);
			break;
		}
		case SOURCE:
		{
			match(SOURCE);
			break;
		}
		case SRC:
		{
			match(SRC);
			break;
		}
		case READPOINT:
		{
			match(READPOINT);
			break;
		}
		case RP:
		{
			match(RP);
			break;
		}
		case TAGSELECTOR:
		{
			match(TAGSELECTOR);
			break;
		}
		case TS:
		{
			match(TS);
			break;
		}
		case DATASELECTOR:
		{
			match(DATASELECTOR);
			break;
		}
		case DS:
		{
			match(DS);
			break;
		}
		case NOTIFICATIONCHANNEL:
		{
			match(NOTIFICATIONCHANNEL);
			break;
		}
		case NC:
		{
			match(NC);
			break;
		}
		case TRIGGER:
		{
			match(TRIGGER);
			break;
		}
		case TRG:
		{
			match(TRG);
			break;
		}
		case COMMANDCHANNEL:
		{
			match(COMMANDCHANNEL);
			break;
		}
		case CC:
		{
			match(CC);
			break;
		}
		case EVENTTYPE:
		{
			match(EVENTTYPE);
			break;
		}
		case ET:
		{
			match(ET);
			break;
		}
		case TRIGGERTYPE:
		{
			match(TRIGGERTYPE);
			break;
		}
		case TT:
		{
			match(TT);
			break;
		}
		case FIELDNAME:
		{
			match(FIELDNAME);
			break;
		}
		case FN:
		{
			match(FN);
			break;
		}
		case IOPORTS:
		{
			match(IOPORTS);
			break;
		}
		case IOP:
		{
			match(IOP);
			break;
		}
		case TAGFIELD:
		{
			match(TAGFIELD);
			break;
		}
		case TF:
		{
			match(TF);
			break;
		}
		case CMD_CREATE:
		{
			match(CMD_CREATE);
			break;
		}
		case CMD_C:
		{
			match(CMD_C);
			break;
		}
		case CMD_GETEPC:
		{
			match(CMD_GETEPC);
			break;
		}
		case CMD_GE:
		{
			match(CMD_GE);
			break;
		}
		case CMD_GETMANUFACTURER:
		{
			match(CMD_GETMANUFACTURER);
			break;
		}
		case CMD_GMAN:
		{
			match(CMD_GMAN);
			break;
		}
		case CMD_GETMODEL:
		{
			match(CMD_GETMODEL);
			break;
		}
		case CMD_GMOD:
		{
			match(CMD_GMOD);
			break;
		}
		case CMD_GETHANDLE:
		{
			match(CMD_GETHANDLE);
			break;
		}
		case CMD_GH:
		{
			match(CMD_GH);
			break;
		}
		case CMD_SETHANDLE:
		{
			match(CMD_SETHANDLE);
			break;
		}
		case CMD_SH:
		{
			match(CMD_SH);
			break;
		}
		case CMD_GETNAME:
		{
			match(CMD_GETNAME);
			break;
		}
		case CMD_GN:
		{
			match(CMD_GN);
			break;
		}
		case CMD_SETNAME:
		{
			match(CMD_SETNAME);
			break;
		}
		case CMD_SN:
		{
			match(CMD_SN);
			break;
		}
		case CMD_GETROLE:
		{
			match(CMD_GETROLE);
			break;
		}
		case CMD_GR:
		{
			match(CMD_GR);
			break;
		}
		case CMD_SETROLE:
		{
			match(CMD_SETROLE);
			break;
		}
		case CMD_SR:
		{
			match(CMD_SR);
			break;
		}
		case CMD_GET_TIME_TICKS:
		{
			match(CMD_GET_TIME_TICKS);
			break;
		}
		case CMD_GTIC:
		{
			match(CMD_GTIC);
			break;
		}
		case CMD_GET_TIME_UTC:
		{
			match(CMD_GET_TIME_UTC);
			break;
		}
		case CMD_GUTC:
		{
			match(CMD_GUTC);
			break;
		}
		case CMD_SET_TIME_UTC:
		{
			match(CMD_SET_TIME_UTC);
			break;
		}
		case CMD_SUTC:
		{
			match(CMD_SUTC);
			break;
		}
		case CMD_GET_MANUFACTURER_DESCRIPTION:
		{
			match(CMD_GET_MANUFACTURER_DESCRIPTION);
			break;
		}
		case CMD_GMD:
		{
			match(CMD_GMD);
			break;
		}
		case CMD_GET_CURRENT_SOURCE:
		{
			match(CMD_GET_CURRENT_SOURCE);
			break;
		}
		case CMD_GCS:
		{
			match(CMD_GCS);
			break;
		}
		case CMD_SET_CURRENT_SOURCE:
		{
			match(CMD_SET_CURRENT_SOURCE);
			break;
		}
		case CMD_SCS:
		{
			match(CMD_SCS);
			break;
		}
		case CMD_GET_CURRENT_DATA_SELECTOR:
		{
			match(CMD_GET_CURRENT_DATA_SELECTOR);
			break;
		}
		case CMD_GCDS:
		{
			match(CMD_GCDS);
			break;
		}
		case CMD_SET_CURRENT_DATA_SELECTOR:
		{
			match(CMD_SET_CURRENT_DATA_SELECTOR);
			break;
		}
		case CMD_SCDS:
		{
			match(CMD_SCDS);
			break;
		}
		case CMD_REMOVE_SOURCES:
		{
			match(CMD_REMOVE_SOURCES);
			break;
		}
		case CMD_RSRC:
		{
			match(CMD_RSRC);
			break;
		}
		case CMD_REMOVE_ALL_SOURCES:
		{
			match(CMD_REMOVE_ALL_SOURCES);
			break;
		}
		case CMD_RASRC:
		{
			match(CMD_RASRC);
			break;
		}
		case CMD_GET_SOURCE:
		{
			match(CMD_GET_SOURCE);
			break;
		}
		case CMD_GSRC:
		{
			match(CMD_GSRC);
			break;
		}
		case CMD_GET_ALL_SOURCES:
		{
			match(CMD_GET_ALL_SOURCES);
			break;
		}
		case CMD_GASRC:
		{
			match(CMD_GASRC);
			break;
		}
		case CMD_REMOVE_DATA_SELECTORS:
		{
			match(CMD_REMOVE_DATA_SELECTORS);
			break;
		}
		case CMD_RDS:
		{
			match(CMD_RDS);
			break;
		}
		case CMD_REMOVE_ALL_DATA_SELECTORS:
		{
			match(CMD_REMOVE_ALL_DATA_SELECTORS);
			break;
		}
		case CMD_RADS:
		{
			match(CMD_RADS);
			break;
		}
		case CMD_DATA_SELECTOR:
		{
			match(CMD_DATA_SELECTOR);
			break;
		}
		case CMD_GDS:
		{
			match(CMD_GDS);
			break;
		}
		case CMD_GET_ALL_DATA_SELECTORS:
		{
			match(CMD_GET_ALL_DATA_SELECTORS);
			break;
		}
		case CMD_GADS:
		{
			match(CMD_GADS);
			break;
		}
		case CMD_REMOVE_NOTIFICATION_CHANNELS:
		{
			match(CMD_REMOVE_NOTIFICATION_CHANNELS);
			break;
		}
		case CMD_RNC:
		{
			match(CMD_RNC);
			break;
		}
		case CMD_REMOVE_ALL_NOTIFICATION_CHANNELS:
		{
			match(CMD_REMOVE_ALL_NOTIFICATION_CHANNELS);
			break;
		}
		case CMD_RANC:
		{
			match(CMD_RANC);
			break;
		}
		case CMD_GET_NOTIFICATION_CHANNEL:
		{
			match(CMD_GET_NOTIFICATION_CHANNEL);
			break;
		}
		case CMD_GNC:
		{
			match(CMD_GNC);
			break;
		}
		case CMD_GET_ALL_NOTIFICATION_CHANNEL:
		{
			match(CMD_GET_ALL_NOTIFICATION_CHANNEL);
			break;
		}
		case CMD_GANC:
		{
			match(CMD_GANC);
			break;
		}
		case CMD_REMOVE_TRIGGERS:
		{
			match(CMD_REMOVE_TRIGGERS);
			break;
		}
		case CMD_RTRG:
		{
			match(CMD_RTRG);
			break;
		}
		case CMD_REMOVE_ALL_TRIGGERS:
		{
			match(CMD_REMOVE_ALL_TRIGGERS);
			break;
		}
		case CMD_RATRG:
		{
			match(CMD_RATRG);
			break;
		}
		case CMD_GET_TRIGGER:
		{
			match(CMD_GET_TRIGGER);
			break;
		}
		case CMD_GTRG:
		{
			match(CMD_GTRG);
			break;
		}
		case CMD_GET_ALL_TRIGGERS:
		{
			match(CMD_GET_ALL_TRIGGERS);
			break;
		}
		case CMD_GATRG:
		{
			match(CMD_GATRG);
			break;
		}
		case CMD_REMOVE_TAG_SELECTORS:
		{
			match(CMD_REMOVE_TAG_SELECTORS);
			break;
		}
		case CMD_RTS:
		{
			match(CMD_RTS);
			break;
		}
		case CMD_REMOVE_ALL_TAG_SELECTORS:
		{
			match(CMD_REMOVE_ALL_TAG_SELECTORS);
			break;
		}
		case CMD_RATS:
		{
			match(CMD_RATS);
			break;
		}
		case CMD_GET_TAG_SELECTOR:
		{
			match(CMD_GET_TAG_SELECTOR);
			break;
		}
		case CMD_GTS:
		{
			match(CMD_GTS);
			break;
		}
		case CMD_GET_ALL_TAG_SELECTORS:
		{
			match(CMD_GET_ALL_TAG_SELECTORS);
			break;
		}
		case CMD_GATS:
		{
			match(CMD_GATS);
			break;
		}
		case CMD_REMOVE_TAG_FIELDS:
		{
			match(CMD_REMOVE_TAG_FIELDS);
			break;
		}
		case CMD_RTF:
		{
			match(CMD_RTF);
			break;
		}
		case CMD_REMOVE_ALL_TAG_FIELDS:
		{
			match(CMD_REMOVE_ALL_TAG_FIELDS);
			break;
		}
		case CMD_RATF:
		{
			match(CMD_RATF);
			break;
		}
		case CMD_GET_TAG_FIELD:
		{
			match(CMD_GET_TAG_FIELD);
			break;
		}
		case CMD_GTF:
		{
			match(CMD_GTF);
			break;
		}
		case CMD_GET_ALL_TAG_FIELDS:
		{
			match(CMD_GET_ALL_TAG_FIELDS);
			break;
		}
		case CMD_RESET_TO_DEFAULT_SETTINGS:
		{
			match(CMD_RESET_TO_DEFAULT_SETTINGS);
			break;
		}
		case CMD_RESET:
		{
			match(CMD_RESET);
			break;
		}
		case CMD_REBOOT:
		{
			match(CMD_REBOOT);
			break;
		}
		case CMD_BOOT:
		{
			match(CMD_BOOT);
			break;
		}
		case CMD_GOODBYE:
		{
			match(CMD_GOODBYE);
			break;
		}
		case CMD_BYE:
		{
			match(CMD_BYE);
			break;
		}
		case CMD_GET_READ_POINT:
		{
			match(CMD_GET_READ_POINT);
			break;
		}
		case CMD_GRP:
		{
			match(CMD_GRP);
			break;
		}
		case CMD_GET_ALL_READ_POINTS:
		{
			match(CMD_GET_ALL_READ_POINTS);
			break;
		}
		case CMD_GARP:
		{
			match(CMD_GARP);
			break;
		}
		case CMD_IS_FIXED:
		{
			match(CMD_IS_FIXED);
			break;
		}
		case CMD_ISFX:
		{
			match(CMD_ISFX);
			break;
		}
		case CMD_ADD_READ_POINTS:
		{
			match(CMD_ADD_READ_POINTS);
			break;
		}
		case CMD_ARP:
		{
			match(CMD_ARP);
			break;
		}
		case CMD_REMOVE_READ_POINTS:
		{
			match(CMD_REMOVE_READ_POINTS);
			break;
		}
		case CMD_RRP:
		{
			match(CMD_RRP);
			break;
		}
		case CMD_REMOVE_ALL_READ_POINTS:
		{
			match(CMD_REMOVE_ALL_READ_POINTS);
			break;
		}
		case CMD_RARP:
		{
			match(CMD_RARP);
			break;
		}
		case CMD_ADD_READ_TRIGGERS:
		{
			match(CMD_ADD_READ_TRIGGERS);
			break;
		}
		case CMD_ART:
		{
			match(CMD_ART);
			break;
		}
		case CMD_REMOVE_READ_TRIGGERS:
		{
			match(CMD_REMOVE_READ_TRIGGERS);
			break;
		}
		case CMD_RRT:
		{
			match(CMD_RRT);
			break;
		}
		case CMD_REMOVE_ALL_READ_TRIGGERS:
		{
			match(CMD_REMOVE_ALL_READ_TRIGGERS);
			break;
		}
		case CMD_RART:
		{
			match(CMD_RART);
			break;
		}
		case CMD_GET_READ_TRIGGER:
		{
			match(CMD_GET_READ_TRIGGER);
			break;
		}
		case CMD_GRT:
		{
			match(CMD_GRT);
			break;
		}
		case CMD_GET_ALL_READ_TRIGGERS:
		{
			match(CMD_GET_ALL_READ_TRIGGERS);
			break;
		}
		case CMD_GART:
		{
			match(CMD_GART);
			break;
		}
		case CMD_ADD_TAG_SELECTORS:
		{
			match(CMD_ADD_TAG_SELECTORS);
			break;
		}
		case CMD_ATS:
		{
			match(CMD_ATS);
			break;
		}
		case CMD_GET_GLIMPSED_TIMEOUT:
		{
			match(CMD_GET_GLIMPSED_TIMEOUT);
			break;
		}
		case CMD_GGTO:
		{
			match(CMD_GGTO);
			break;
		}
		case CMD_SET_GLIMPSED_TIMEOUT:
		{
			match(CMD_SET_GLIMPSED_TIMEOUT);
			break;
		}
		case CMD_SGTO:
		{
			match(CMD_SGTO);
			break;
		}
		case CMD_GET_OBSERVED_THRESHOLD:
		{
			match(CMD_GET_OBSERVED_THRESHOLD);
			break;
		}
		case CMD_GOTH:
		{
			match(CMD_GOTH);
			break;
		}
		case CMD_SET_OBSERVED_THRESHOLD:
		{
			match(CMD_SET_OBSERVED_THRESHOLD);
			break;
		}
		case CMD_SOTH:
		{
			match(CMD_SOTH);
			break;
		}
		case CMD_GET_OBSERVED_TIMEOUT:
		{
			match(CMD_GET_OBSERVED_TIMEOUT);
			break;
		}
		case CMD_GOTO:
		{
			match(CMD_GOTO);
			break;
		}
		case CMD_SET_OBSERVED_TIMEOUT:
		{
			match(CMD_SET_OBSERVED_TIMEOUT);
			break;
		}
		case CMD_SOTO:
		{
			match(CMD_SOTO);
			break;
		}
		case CMD_GET_LOST_TIMEOUT:
		{
			match(CMD_GET_LOST_TIMEOUT);
			break;
		}
		case CMD_GLTO:
		{
			match(CMD_GLTO);
			break;
		}
		case CMD_SET_LOST_TIMEOUT:
		{
			match(CMD_SET_LOST_TIMEOUT);
			break;
		}
		case CMD_SLTO:
		{
			match(CMD_SLTO);
			break;
		}
		case CMD_RAW_READ_IDS:
		{
			match(CMD_RAW_READ_IDS);
			break;
		}
		case CMD_RRID:
		{
			match(CMD_RRID);
			break;
		}
		case CMD_READ_IDS:
		{
			match(CMD_READ_IDS);
			break;
		}
		case CMD_RID:
		{
			match(CMD_RID);
			break;
		}
		case CMD_READ:
		{
			match(CMD_READ);
			break;
		}
		case CMD_R:
		{
			match(CMD_R);
			break;
		}
		case CMD_WRITE_ID:
		{
			match(CMD_WRITE_ID);
			break;
		}
		case CMD_WID:
		{
			match(CMD_WID);
			break;
		}
		case CMD_WRITE:
		{
			match(CMD_WRITE);
			break;
		}
		case CMD_W:
		{
			match(CMD_W);
			break;
		}
		case CMD_KILL:
		{
			match(CMD_KILL);
			break;
		}
		case CMD_K:
		{
			match(CMD_K);
			break;
		}
		case CMD_GET_READ_CYCLES_PER_TRIGGER:
		{
			match(CMD_GET_READ_CYCLES_PER_TRIGGER);
			break;
		}
		case CMD_GRCPT:
		{
			match(CMD_GRCPT);
			break;
		}
		case CMD_SET_READ_CYCLES_PER_TRIGGER:
		{
			match(CMD_SET_READ_CYCLES_PER_TRIGGER);
			break;
		}
		case CMD_SRCPT:
		{
			match(CMD_SRCPT);
			break;
		}
		case CMD_GET_MAX_READ_DUTY_CYCLE:
		{
			match(CMD_GET_MAX_READ_DUTY_CYCLE);
			break;
		}
		case CMD_GMRDC:
		{
			match(CMD_GMRDC);
			break;
		}
		case CMD_SET_MAX_READ_DUTY_CYCLE:
		{
			match(CMD_SET_MAX_READ_DUTY_CYCLE);
			break;
		}
		case CMD_SMRDC:
		{
			match(CMD_SMRDC);
			break;
		}
		case CMD_GET_READ_TIMEOUT:
		{
			match(CMD_GET_READ_TIMEOUT);
			break;
		}
		case CMD_GRTO:
		{
			match(CMD_GRTO);
			break;
		}
		case CMD_SET_READ_TIMEOUT:
		{
			match(CMD_SET_READ_TIMEOUT);
			break;
		}
		case CMD_SRTO:
		{
			match(CMD_SRTO);
			break;
		}
		case CMD_GET_SESSION:
		{
			match(CMD_GET_SESSION);
			break;
		}
		case CMD_GSS:
		{
			match(CMD_GSS);
			break;
		}
		case CMD_SET_SESSION:
		{
			match(CMD_SET_SESSION);
			break;
		}
		case CMD_SSS:
		{
			match(CMD_SSS);
			break;
		}
		case CMD_GET_MAX_NUMBER_SUPPORTED:
		{
			match(CMD_GET_MAX_NUMBER_SUPPORTED);
			break;
		}
		case CMD_GMAX:
		{
			match(CMD_GMAX);
			break;
		}
		case CMD_GET_TYPE:
		{
			match(CMD_GET_TYPE);
			break;
		}
		case CMD_GT:
		{
			match(CMD_GT);
			break;
		}
		case CMD_GET_VALUE:
		{
			match(CMD_GET_VALUE);
			break;
		}
		case CMD_GV:
		{
			match(CMD_GV);
			break;
		}
		case CMD_FIRE:
		{
			match(CMD_FIRE);
			break;
		}
		case CMD_F:
		{
			match(CMD_F);
			break;
		}
		case CMD_GET_MASK:
		{
			match(CMD_GET_MASK);
			break;
		}
		case CMD_GM:
		{
			match(CMD_GM);
			break;
		}
		case CMD_GET_INCLUSIVE_FLAG:
		{
			match(CMD_GET_INCLUSIVE_FLAG);
			break;
		}
		case CMD_GIF:
		{
			match(CMD_GIF);
			break;
		}
		case CMD_GET_ADDRESS:
		{
			match(CMD_GET_ADDRESS);
			break;
		}
		case CMD_GADR:
		{
			match(CMD_GADR);
			break;
		}
		case CMD_GET_EFFECTIVE_ADDRESS:
		{
			match(CMD_GET_EFFECTIVE_ADDRESS);
			break;
		}
		case CMD_GEADR:
		{
			match(CMD_GEADR);
			break;
		}
		case CMD_SET_ADDRESS:
		{
			match(CMD_SET_ADDRESS);
			break;
		}
		case CMD_SADR:
		{
			match(CMD_SADR);
			break;
		}
		case CMD_SET_DATA_SELECTOR:
		{
			match(CMD_SET_DATA_SELECTOR);
			break;
		}
		case CMD_SDS:
		{
			match(CMD_SDS);
			break;
		}
		case CMD_ADD_SOURCES:
		{
			match(CMD_ADD_SOURCES);
			break;
		}
		case CMD_ASRC:
		{
			match(CMD_ASRC);
			break;
		}
		case CMD_ADD_NOTIFICATIOIN_TRIGGERS:
		{
			match(CMD_ADD_NOTIFICATIOIN_TRIGGERS);
			break;
		}
		case CMD_ANT:
		{
			match(CMD_ANT);
			break;
		}
		case CMD_REMOVE_NOTIFICATION_TRIGGERS:
		{
			match(CMD_REMOVE_NOTIFICATION_TRIGGERS);
			break;
		}
		case CMD_RNT:
		{
			match(CMD_RNT);
			break;
		}
		case CMD_REMOVE_ALL_NOTIFICATION_TRIGGERS:
		{
			match(CMD_REMOVE_ALL_NOTIFICATION_TRIGGERS);
			break;
		}
		case CMD_RANT:
		{
			match(CMD_RANT);
			break;
		}
		case CMD_GET_NOTIFICATION_TRIGGER:
		{
			match(CMD_GET_NOTIFICATION_TRIGGER);
			break;
		}
		case CMD_GNT:
		{
			match(CMD_GNT);
			break;
		}
		case CMD_GET_ALL_NOTIFICATION_TRIGGERS:
		{
			match(CMD_GET_ALL_NOTIFICATION_TRIGGERS);
			break;
		}
		case CMD_GANT:
		{
			match(CMD_GANT);
			break;
		}
		case CMD_READ_QUEUED_DATA:
		{
			match(CMD_READ_QUEUED_DATA);
			break;
		}
		case CMD_RQD:
		{
			match(CMD_RQD);
			break;
		}
		case CMD_ADD_FIELD_NAMES:
		{
			match(CMD_ADD_FIELD_NAMES);
			break;
		}
		case CMD_AFN:
		{
			match(CMD_AFN);
			break;
		}
		case CMD_REMOVE_FIELD_NAMES:
		{
			match(CMD_REMOVE_FIELD_NAMES);
			break;
		}
		case CMD_RFN:
		{
			match(CMD_RFN);
			break;
		}
		case CMD_REMOVE_ALL_FIELD_NAMES:
		{
			match(CMD_REMOVE_ALL_FIELD_NAMES);
			break;
		}
		case CMD_RAFN:
		{
			match(CMD_RAFN);
			break;
		}
		case CMD_GET_ALL_FIELD_NAMES:
		{
			match(CMD_GET_ALL_FIELD_NAMES);
			break;
		}
		case CMD_GAFN:
		{
			match(CMD_GAFN);
			break;
		}
		case CMD_ADD_EVENT_FILTERS:
		{
			match(CMD_ADD_EVENT_FILTERS);
			break;
		}
		case CMD_AEF:
		{
			match(CMD_AEF);
			break;
		}
		case CMD_REMOVE_EVENT_FILTERS:
		{
			match(CMD_REMOVE_EVENT_FILTERS);
			break;
		}
		case CMD_REF:
		{
			match(CMD_REF);
			break;
		}
		case CMD_REMOVE_ALL_EVENT_FILTERS:
		{
			match(CMD_REMOVE_ALL_EVENT_FILTERS);
			break;
		}
		case CMD_RAEF:
		{
			match(CMD_RAEF);
			break;
		}
		case CMD_GET_ALL_EVENT_FILTERS:
		{
			match(CMD_GET_ALL_EVENT_FILTERS);
			break;
		}
		case CMD_GAEF:
		{
			match(CMD_GAEF);
			break;
		}
		case CMD_ADD_TAG_FIELD_NAMES:
		{
			match(CMD_ADD_TAG_FIELD_NAMES);
			break;
		}
		case CMD_ATFN:
		{
			match(CMD_ATFN);
			break;
		}
		case CMD_REMOVE_TAG_FIELD_NAMES:
		{
			match(CMD_REMOVE_TAG_FIELD_NAMES);
			break;
		}
		case CMD_RTFN:
		{
			match(CMD_RTFN);
			break;
		}
		case CMD_REMOVE_ALL_TAG_FIELD_NAMES:
		{
			match(CMD_REMOVE_ALL_TAG_FIELD_NAMES);
			break;
		}
		case CMD_RATFN:
		{
			match(CMD_RATFN);
			break;
		}
		case CMD_GET_ALL_TAG_FIELD_NAMES:
		{
			match(CMD_GET_ALL_TAG_FIELD_NAMES);
			break;
		}
		case CMD_GATFN:
		{
			match(CMD_GATFN);
			break;
		}
		case CMD_GET_SUPPORTED_TYPES:
		{
			match(CMD_GET_SUPPORTED_TYPES);
			break;
		}
		case CMD_GST:
		{
			match(CMD_GST);
			break;
		}
		case CMD_GET_SUPPORTED_NAMES:
		{
			match(CMD_GET_SUPPORTED_NAMES);
			break;
		}
		case CMD_GSN:
		{
			match(CMD_GSN);
			break;
		}
		case CMD_GET_TAG_FIELD_NAME:
		{
			match(CMD_GET_TAG_FIELD_NAME);
			break;
		}
		case CMD_GTFN:
		{
			match(CMD_GTFN);
			break;
		}
		case CMD_SET_TAG_FIELD_NAME:
		{
			match(CMD_SET_TAG_FIELD_NAME);
			break;
		}
		case CMD_STFN:
		{
			match(CMD_STFN);
			break;
		}
		case CMD_GET_MEMORY_BANK:
		{
			match(CMD_GET_MEMORY_BANK);
			break;
		}
		case CMD_GMB:
		{
			match(CMD_GMB);
			break;
		}
		case CMD_SET_MEMORY_BANK:
		{
			match(CMD_SET_MEMORY_BANK);
			break;
		}
		case CMD_SMB:
		{
			match(CMD_SMB);
			break;
		}
		case CMD_GET_OFFSET:
		{
			match(CMD_GET_OFFSET);
			break;
		}
		case CMD_GOFF:
		{
			match(CMD_GOFF);
			break;
		}
		case CMD_SET_OFFSET:
		{
			match(CMD_SET_OFFSET);
			break;
		}
		case CMD_SOFF:
		{
			match(CMD_SOFF);
			break;
		}
		case CMD_GET_LENGTH:
		{
			match(CMD_GET_LENGTH);
			break;
		}
		case CMD_GLEN:
		{
			match(CMD_GLEN);
			break;
		}
		case CMD_SET_LENGTH:
		{
			match(CMD_SET_LENGTH);
			break;
		}
		case CMD_SLEN:
		{
			match(CMD_SLEN);
			break;
		}
		default:
		{
			throw new Exception(getFilename());
		}
		}
		}
		return p;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"ReaderDevice\"",
		"\"RD\"",
		"\"Source\"",
		"\"SRC\"",
		"\"ReadPoint\"",
		"\"RP\"",
		"\"TagSelector\"",
		"\"TS\"",
		"\"DataSelector\"",
		"\"DS\"",
		"\"NotificationChannel\"",
		"\"NC\"",
		"\"Trigger\"",
		"\"TRG\"",
		"\"CommandChannel\"",
		"\"CC\"",
		"\"EventType\"",
		"\"ET\"",
		"\"TriggerType\"",
		"\"TT\"",
		"\"FieldName\"",
		"\"FN\"",
		"\"IOPorts\"",
		"\"IOP\"",
		"\"TagField\"",
		"\"TF\"",
		"\"getEPC\"",
		"\"gE\"",
		"\"getManufacturer\"",
		"\"gMan\"",
		"\"getModel\"",
		"\"gMod\"",
		"\"getHandle\"",
		"\"gH\"",
		"\"setHandle\"",
		"\"sH\"",
		"\"getName\"",
		"\"gN\"",
		"\"setName\"",
		"\"sN\"",
		"\"getRole\"",
		"\"gR\"",
		"\"setRole\"",
		"\"sR\"",
		"\"getTimeTicks\"",
		"\"gTic\"",
		"\"getTimeUTC\"",
		"\"gUTC\"",
		"\"setTimeUTC\"",
		"\"sUTC\"",
		"\"getManufacturerDescription\"",
		"\"gMD\"",
		"\"getCurrentSource\"",
		"\"gCS\"",
		"\"setCurrentSource\"",
		"\"sCS\"",
		"\"getCurrentDataSelector\"",
		"\"gCDS\"",
		"\"setCurrentDataSelector\"",
		"\"sCDS\"",
		"\"removeSources\"",
		"\"rSRC\"",
		"\"removeAllSources\"",
		"\"raSRC\"",
		"\"getSource\"",
		"\"gSRC\"",
		"\"getAllSources\"",
		"\"gaSRC\"",
		"\"removeDataSelectors\"",
		"\"rDS\"",
		"\"removeAllDataSelectors\"",
		"\"raDS\"",
		"\"getDataSelector\"",
		"\"gDS\"",
		"\"getAllDataSelectors\"",
		"\"gaDS\"",
		"\"removeNotificationChannels\"",
		"\"rNC\"",
		"\"removeAllNotificationChannels\"",
		"\"raNC\"",
		"\"getNotificationChannel\"",
		"\"gNC\"",
		"\"getAllNotificationChannels\"",
		"\"gaNC\"",
		"\"removeTriggers\"",
		"\"rTRG\"",
		"\"removeAllTriggers\"",
		"\"raTRG\"",
		"\"getTrigger\"",
		"\"gTRG\"",
		"\"getAllTriggers\"",
		"\"gaTRG\"",
		"\"removeTagSelectors\"",
		"\"rTS\"",
		"\"removeAllTagSelectors\"",
		"\"raTS\"",
		"\"getTagSelector\"",
		"\"gTS\"",
		"\"getAllTagSelectors\"",
		"\"gaTS\"",
		"\"removeTagFields\"",
		"\"rTF\"",
		"\"removeAllTagFields\"",
		"\"raTF\"",
		"\"getTagField\"",
		"\"gTF\"",
		"\"getAllTagFields\"",
		"\"gaTF\"",
		"\"resetToDefaultSettings\"",
		"\"reset\"",
		"\"reboot\"",
		"\"boot\"",
		"\"goodbye\"",
		"\"bye\"",
		"\"getReadPoint\"",
		"\"gRP\"",
		"\"getAllReadPoints\"",
		"\"gaRP\"",
		"\"create\"",
		"\"c\"",
		"\"isFixed\"",
		"\"isFX\"",
		"\"addReadPoints\"",
		"\"aRP\"",
		"\"removeReadPoints\"",
		"\"rRP\"",
		"\"removeAllReadPoints\"",
		"\"raRP\"",
		"\"addReadTriggers\"",
		"\"aRT\"",
		"\"removeReadTriggers\"",
		"\"rRT\"",
		"\"removeAllReadTriggers\"",
		"\"raRT\"",
		"\"getReadTrigger\"",
		"\"gRT\"",
		"\"getAllReadTriggers\"",
		"\"gaRT\"",
		"\"addTagSelectors\"",
		"\"aTS\"",
		"\"getGlimpsedTimeout\"",
		"\"gGTO\"",
		"\"setGlimpsedTimeout\"",
		"\"sGTO\"",
		"\"getObservedThreshold\"",
		"\"gOTH\"",
		"\"setObservedThreshold\"",
		"\"sOTH\"",
		"\"getObservedTimeout\"",
		"\"gOTO\"",
		"\"setObservedTimeout\"",
		"\"sOTO\"",
		"\"getLostTimeout\"",
		"\"gLTO\"",
		"\"setLostTimeout\"",
		"\"sLTO\"",
		"\"rawReadIDs\"",
		"\"rrid\"",
		"\"readIDs\"",
		"\"rid\"",
		"\"read\"",
		"\"r\"",
		"\"writeID\"",
		"\"wid\"",
		"\"write\"",
		"\"w\"",
		"\"kill\"",
		"\"k\"",
		"\"getReadCyclesPerTrigger\"",
		"\"gRCPT\"",
		"\"setReadCyclesPerTrigger\"",
		"\"sRCPT\"",
		"\"getMaxReadDutyCycles\"",
		"\"gMRDC\"",
		"\"setMaxReadDutyCycles\"",
		"\"sMRDC\"",
		"\"getReadTimeout\"",
		"\"gRTO\"",
		"\"setReadTimeout\"",
		"\"sRTO\"",
		"\"setSession\"",
		"\"sSS\"",
		"\"getSession\"",
		"\"gSS\"",
		"\"getMaxNumberSupported\"",
		"\"gMax\"",
		"\"gMx\"",
		"\"getType\"",
		"\"gT\"",
		"\"getValue\"",
		"\"gV\"",
		"\"fire\"",
		"\"f\"",
		"\"getMask\"",
		"\"gM\"",
		"\"getInclusiveFlag\"",
		"\"gIF\"",
		"\"getAddress\"",
		"\"gAdr\"",
		"\"getEffectiveAddress\"",
		"\"gEAdr\"",
		"\"setAddress\"",
		"\"sAdr\"",
		"\"setDataSelector\"",
		"\"sDS\"",
		"\"addSources\"",
		"\"aSRC\"",
		"\"addNotificationTriggers\"",
		"\"aNT\"",
		"\"removeNotificatonTriggers\"",
		"\"rNT\"",
		"\"removeAllNotificationTriggers\"",
		"\"raNT\"",
		"\"getNotificationTrigger\"",
		"\"gNT\"",
		"\"getAllNotificationTriggers\"",
		"\"gaNT\"",
		"\"readQueuedData\"",
		"\"rqd\"",
		"\"addFieldNames\"",
		"\"aFN\"",
		"\"removeFieldNames\"",
		"\"rFN\"",
		"\"removeAllFieldNames\"",
		"\"raFN\"",
		"\"getAllFieldNames\"",
		"\"gaFN\"",
		"\"addEventFilters\"",
		"\"aEF\"",
		"\"removeEventFilters\"",
		"\"rEF\"",
		"\"removeAllEventFilters\"",
		"\"raEF\"",
		"\"getAllEventFilters\"",
		"\"gaEF\"",
		"\"addTagFieldNames\"",
		"\"aTFN\"",
		"\"removeTagFieldNames\"",
		"\"rTFN\"",
		"\"removeAllTagFieldNames\"",
		"\"raTFN\"",
		"\"getAllTagFieldNames\"",
		"\"gaTFN\"",
		"\"getSupportedTypes\"",
		"\"gST\"",
		"\"getSupportedNames\"",
		"\"gSN\"",
		"\"getTagFieldName\"",
		"\"gTFN\"",
		"\"setTagFieldName\"",
		"\"sTFN\"",
		"\"getMemoryBank\"",
		"\"gMB\"",
		"\"setMemoryBank\"",
		"\"sMB\"",
		"\"getOffset\"",
		"\"gOFF\"",
		"\"setOffset\"",
		"\"sOFF\"",
		"\"getLength\"",
		"\"gLEN\"",
		"\"setLength\"",
		"\"sLEN\"",
		"SHARP",
		"DOT",
		"LF",
		"EXCLAMATION",
		"IDENT",
		"CMD_GET_ALL_NOTIFICATION_CHANNEL",
		"CMD_ADD_NOTIFICATIOIN_TRIGGERS",
		"COMMA",
		"INT",
		"ASSIGN",
		"CMD_DATA_SELECTOR",
		"LBRACKET",
		"RBRACKET",
		"WS",
		"ESCAPE",
		"DIGIT"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[10];
		data[0]=-16L;
		data[1]=-140737492549633L;
		data[2]=-1L;
		data[3]=-524289L;
		data[4]=198655L;
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[10];
		data[0]=-1073741824L;
		data[1]=-140737492549633L;
		data[2]=-1L;
		data[3]=-524289L;
		data[4]=198655L;
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = new long[10];
		data[0]=-16L;
		data[1]=-140737492553729L;
		data[2]=-4611686018427387905L;
		data[3]=-524289L;
		data[4]=7047167L;
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = new long[10];
		data[0]=-16L;
		data[1]=-140737492553729L;
		data[2]=-4611686018427387905L;
		data[3]=-524289L;
		data[4]=2295807L;
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	
	}
