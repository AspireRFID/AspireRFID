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

// $ANTLR : "command.g" -> "TextLexer.java"$
 
package org.ow2.aspirerfid.reader.rp.impl.core.msg.command;

import java.io.InputStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.SemanticException;

public class TextLexer extends antlr.CharScanner implements TextCommandParserTokenTypes, TokenStream
 {
public TextLexer(InputStream in) {
	this(new ByteBuffer(in));
}
public TextLexer(Reader in) {
	this(new CharBuffer(in));
}
public TextLexer(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public TextLexer(LexerSharedInputState state) {
	super(state);
	caseSensitiveLiterals = false;
	setCaseSensitive(true);
	literals = new Hashtable();
	literals.put(new ANTLRHashString("IOP", this), new Integer(27));
	literals.put(new ANTLRHashString("gRP", this), new Integer(119));
	literals.put(new ANTLRHashString("removeTagFieldNames", this), new Integer(241));
	literals.put(new ANTLRHashString("getTagFieldName", this), new Integer(251));
	literals.put(new ANTLRHashString("k", this), new Integer(171));
	literals.put(new ANTLRHashString("sGTO", this), new Integer(147));
	literals.put(new ANTLRHashString("gOFF", this), new Integer(260));
	literals.put(new ANTLRHashString("setCurrentSource", this), new Integer(58));
	literals.put(new ANTLRHashString("getSession", this), new Integer(186));
	literals.put(new ANTLRHashString("getAllFieldNames", this), new Integer(229));
	literals.put(new ANTLRHashString("getCurrentDataSelector", this), new Integer(60));
	literals.put(new ANTLRHashString("aRP", this), new Integer(127));
	literals.put(new ANTLRHashString("removeAllTriggers", this), new Integer(90));
	literals.put(new ANTLRHashString("setName", this), new Integer(42));
	literals.put(new ANTLRHashString("raTS", this), new Integer(99));
	literals.put(new ANTLRHashString("setLength", this), new Integer(265));
	literals.put(new ANTLRHashString("getAllNotificationChannels", this), new Integer(86));
	literals.put(new ANTLRHashString("TagField", this), new Integer(28));
	literals.put(new ANTLRHashString("removeNotificationChannels", this), new Integer(80));
	literals.put(new ANTLRHashString("getAllTagSelectors", this), new Integer(102));
	literals.put(new ANTLRHashString("addSources", this), new Integer(209));
	literals.put(new ANTLRHashString("gCDS", this), new Integer(61));
	literals.put(new ANTLRHashString("setOffset", this), new Integer(261));
	literals.put(new ANTLRHashString("setMemoryBank", this), new Integer(257));
	literals.put(new ANTLRHashString("rqd", this), new Integer(222));
	literals.put(new ANTLRHashString("setMaxReadDutyCycles", this), new Integer(178));
	literals.put(new ANTLRHashString("gaTRG", this), new Integer(95));
	literals.put(new ANTLRHashString("gaSRC", this), new Integer(71));
	literals.put(new ANTLRHashString("raDS", this), new Integer(75));
	literals.put(new ANTLRHashString("sN", this), new Integer(43));
	literals.put(new ANTLRHashString("removeAllNotificationChannels", this), new Integer(82));
	literals.put(new ANTLRHashString("gOTO", this), new Integer(153));
	literals.put(new ANTLRHashString("gMod", this), new Integer(35));
	literals.put(new ANTLRHashString("rTS", this), new Integer(97));
	literals.put(new ANTLRHashString("rid", this), new Integer(163));
	literals.put(new ANTLRHashString("removeSources", this), new Integer(64));
	literals.put(new ANTLRHashString("getHandle", this), new Integer(36));
	literals.put(new ANTLRHashString("gaEF", this), new Integer(238));
	literals.put(new ANTLRHashString("gN", this), new Integer(41));
	literals.put(new ANTLRHashString("DataSelector", this), new Integer(12));
	literals.put(new ANTLRHashString("ReadPoint", this), new Integer(8));
	literals.put(new ANTLRHashString("aSRC", this), new Integer(210));
	literals.put(new ANTLRHashString("aFN", this), new Integer(224));
	literals.put(new ANTLRHashString("sMRDC", this), new Integer(179));
	literals.put(new ANTLRHashString("rawReadIDs", this), new Integer(160));
	literals.put(new ANTLRHashString("Trigger", this), new Integer(16));
	literals.put(new ANTLRHashString("rDS", this), new Integer(73));
	literals.put(new ANTLRHashString("getAddress", this), new Integer(201));
	literals.put(new ANTLRHashString("write", this), new Integer(168));
	literals.put(new ANTLRHashString("gM", this), new Integer(198));
	literals.put(new ANTLRHashString("getGlimpsedTimeout", this), new Integer(144));
	literals.put(new ANTLRHashString("removeAllReadPoints", this), new Integer(130));
	literals.put(new ANTLRHashString("sOFF", this), new Integer(262));
	literals.put(new ANTLRHashString("removeTagSelectors", this), new Integer(96));
	literals.put(new ANTLRHashString("removeAllEventFilters", this), new Integer(235));
	literals.put(new ANTLRHashString("addTagSelectors", this), new Integer(142));
	literals.put(new ANTLRHashString("getSource", this), new Integer(68));
	literals.put(new ANTLRHashString("f", this), new Integer(196));
	literals.put(new ANTLRHashString("raNC", this), new Integer(83));
	literals.put(new ANTLRHashString("boot", this), new Integer(115));
	literals.put(new ANTLRHashString("removeReadPoints", this), new Integer(128));
	literals.put(new ANTLRHashString("removeAllFieldNames", this), new Integer(227));
	literals.put(new ANTLRHashString("getReadTrigger", this), new Integer(138));
	literals.put(new ANTLRHashString("getManufacturer", this), new Integer(32));
	literals.put(new ANTLRHashString("getReadPoint", this), new Integer(118));
	literals.put(new ANTLRHashString("gMan", this), new Integer(33));
	literals.put(new ANTLRHashString("setRole", this), new Integer(46));
	literals.put(new ANTLRHashString("sCDS", this), new Integer(63));
	literals.put(new ANTLRHashString("isFixed", this), new Integer(124));
	literals.put(new ANTLRHashString("TF", this), new Integer(29));
	literals.put(new ANTLRHashString("removeFieldNames", this), new Integer(225));
	literals.put(new ANTLRHashString("getMaxNumberSupported", this), new Integer(188));
	literals.put(new ANTLRHashString("setTimeUTC", this), new Integer(52));
	literals.put(new ANTLRHashString("gaRT", this), new Integer(141));
	literals.put(new ANTLRHashString("rNC", this), new Integer(81));
	literals.put(new ANTLRHashString("sOTO", this), new Integer(155));
	literals.put(new ANTLRHashString("raTFN", this), new Integer(244));
	literals.put(new ANTLRHashString("removeDataSelectors", this), new Integer(72));
	literals.put(new ANTLRHashString("getAllReadPoints", this), new Integer(120));
	literals.put(new ANTLRHashString("removeAllTagFieldNames", this), new Integer(243));
	literals.put(new ANTLRHashString("gaNT", this), new Integer(220));
	literals.put(new ANTLRHashString("getName", this), new Integer(40));
	literals.put(new ANTLRHashString("setTagFieldName", this), new Integer(253));
	literals.put(new ANTLRHashString("setCurrentDataSelector", this), new Integer(62));
	literals.put(new ANTLRHashString("sDS", this), new Integer(208));
	literals.put(new ANTLRHashString("c", this), new Integer(123));
	literals.put(new ANTLRHashString("TRG", this), new Integer(17));
	literals.put(new ANTLRHashString("gaTF", this), new Integer(111));
	literals.put(new ANTLRHashString("SRC", this), new Integer(7));
	literals.put(new ANTLRHashString("sH", this), new Integer(39));
	literals.put(new ANTLRHashString("IOPorts", this), new Integer(26));
	literals.put(new ANTLRHashString("getEPC", this), new Integer(30));
	literals.put(new ANTLRHashString("getMaxReadDutyCycles", this), new Integer(176));
	literals.put(new ANTLRHashString("getInclusiveFlag", this), new Integer(199));
	literals.put(new ANTLRHashString("CommandChannel", this), new Integer(18));
	literals.put(new ANTLRHashString("gTS", this), new Integer(101));
	literals.put(new ANTLRHashString("RP", this), new Integer(9));
	literals.put(new ANTLRHashString("getReadCyclesPerTrigger", this), new Integer(172));
	literals.put(new ANTLRHashString("raEF", this), new Integer(236));
	literals.put(new ANTLRHashString("gH", this), new Integer(37));
	literals.put(new ANTLRHashString("gOTH", this), new Integer(149));
	literals.put(new ANTLRHashString("getTimeTicks", this), new Integer(48));
	literals.put(new ANTLRHashString("getTagField", this), new Integer(108));
	literals.put(new ANTLRHashString("gRCPT", this), new Integer(173));
	literals.put(new ANTLRHashString("aTS", this), new Integer(143));
	literals.put(new ANTLRHashString("gDS", this), new Integer(77));
	literals.put(new ANTLRHashString("gAdr", this), new Integer(202));
	literals.put(new ANTLRHashString("rEF", this), new Integer(234));
	literals.put(new ANTLRHashString("gaRP", this), new Integer(121));
	literals.put(new ANTLRHashString("writeID", this), new Integer(166));
	literals.put(new ANTLRHashString("removeAllReadTriggers", this), new Integer(136));
	literals.put(new ANTLRHashString("getMemoryBank", this), new Integer(255));
	literals.put(new ANTLRHashString("getAllEventFilters", this), new Integer(237));
	literals.put(new ANTLRHashString("FieldName", this), new Integer(24));
	literals.put(new ANTLRHashString("gLEN", this), new Integer(264));
	literals.put(new ANTLRHashString("getEffectiveAddress", this), new Integer(203));
	literals.put(new ANTLRHashString("FN", this), new Integer(25));
	literals.put(new ANTLRHashString("bye", this), new Integer(117));
	literals.put(new ANTLRHashString("getNotificationTrigger", this), new Integer(217));
	literals.put(new ANTLRHashString("gTFN", this), new Integer(252));
	literals.put(new ANTLRHashString("ET", this), new Integer(21));
	literals.put(new ANTLRHashString("gE", this), new Integer(31));
	literals.put(new ANTLRHashString("reboot", this), new Integer(114));
	literals.put(new ANTLRHashString("addNotificationTriggers", this), new Integer(211));
	literals.put(new ANTLRHashString("getRole", this), new Integer(44));
	literals.put(new ANTLRHashString("removeTagFields", this), new Integer(104));
	literals.put(new ANTLRHashString("raRT", this), new Integer(137));
	literals.put(new ANTLRHashString("w", this), new Integer(169));
	literals.put(new ANTLRHashString("setObservedTimeout", this), new Integer(154));
	literals.put(new ANTLRHashString("Source", this), new Integer(6));
	literals.put(new ANTLRHashString("sSS", this), new Integer(185));
	literals.put(new ANTLRHashString("raNT", this), new Integer(216));
	literals.put(new ANTLRHashString("getLostTimeout", this), new Integer(156));
	literals.put(new ANTLRHashString("gNC", this), new Integer(85));
	literals.put(new ANTLRHashString("raTRG", this), new Integer(91));
	literals.put(new ANTLRHashString("raSRC", this), new Integer(67));
	literals.put(new ANTLRHashString("addReadPoints", this), new Integer(126));
	literals.put(new ANTLRHashString("gUTC", this), new Integer(51));
	literals.put(new ANTLRHashString("readIDs", this), new Integer(162));
	literals.put(new ANTLRHashString("getModel", this), new Integer(34));
	literals.put(new ANTLRHashString("gaFN", this), new Integer(230));
	literals.put(new ANTLRHashString("reset", this), new Integer(113));
	literals.put(new ANTLRHashString("getSupportedTypes", this), new Integer(247));
	literals.put(new ANTLRHashString("sOTH", this), new Integer(151));
	literals.put(new ANTLRHashString("removeEventFilters", this), new Integer(233));
	literals.put(new ANTLRHashString("sCS", this), new Integer(59));
	literals.put(new ANTLRHashString("getTimeUTC", this), new Integer(50));
	literals.put(new ANTLRHashString("addEventFilters", this), new Integer(231));
	literals.put(new ANTLRHashString("raTF", this), new Integer(107));
	literals.put(new ANTLRHashString("gST", this), new Integer(248));
	literals.put(new ANTLRHashString("addFieldNames", this), new Integer(223));
	literals.put(new ANTLRHashString("rRT", this), new Integer(135));
	literals.put(new ANTLRHashString("setObservedThreshold", this), new Integer(150));
	literals.put(new ANTLRHashString("getOffset", this), new Integer(259));
	literals.put(new ANTLRHashString("gRTO", this), new Integer(181));
	literals.put(new ANTLRHashString("rNT", this), new Integer(214));
	literals.put(new ANTLRHashString("sAdr", this), new Integer(206));
	literals.put(new ANTLRHashString("getSupportedNames", this), new Integer(249));
	literals.put(new ANTLRHashString("getAllTagFieldNames", this), new Integer(245));
	literals.put(new ANTLRHashString("getMask", this), new Integer(197));
	literals.put(new ANTLRHashString("gSS", this), new Integer(187));
	literals.put(new ANTLRHashString("setHandle", this), new Integer(38));
	literals.put(new ANTLRHashString("gLTO", this), new Integer(157));
	literals.put(new ANTLRHashString("sLEN", this), new Integer(266));
	literals.put(new ANTLRHashString("gMx", this), new Integer(190));
	literals.put(new ANTLRHashString("rTF", this), new Integer(105));
	literals.put(new ANTLRHashString("getAllTagFields", this), new Integer(110));
	literals.put(new ANTLRHashString("sTFN", this), new Integer(254));
	literals.put(new ANTLRHashString("removeAllTagFields", this), new Integer(106));
	literals.put(new ANTLRHashString("gCS", this), new Integer(57));
	literals.put(new ANTLRHashString("gIF", this), new Integer(200));
	literals.put(new ANTLRHashString("NotificationChannel", this), new Integer(14));
	literals.put(new ANTLRHashString("raRP", this), new Integer(131));
	literals.put(new ANTLRHashString("addTagFieldNames", this), new Integer(239));
	literals.put(new ANTLRHashString("getReadTimeout", this), new Integer(180));
	literals.put(new ANTLRHashString("CC", this), new Integer(19));
	literals.put(new ANTLRHashString("TriggerType", this), new Integer(22));
	literals.put(new ANTLRHashString("TT", this), new Integer(23));
	literals.put(new ANTLRHashString("sRCPT", this), new Integer(175));
	literals.put(new ANTLRHashString("read", this), new Integer(164));
	literals.put(new ANTLRHashString("gEAdr", this), new Integer(204));
	literals.put(new ANTLRHashString("resetToDefaultSettings", this), new Integer(112));
	literals.put(new ANTLRHashString("setSession", this), new Integer(184));
	literals.put(new ANTLRHashString("getAllReadTriggers", this), new Integer(140));
	literals.put(new ANTLRHashString("sUTC", this), new Integer(53));
	literals.put(new ANTLRHashString("setLostTimeout", this), new Integer(158));
	literals.put(new ANTLRHashString("r", this), new Integer(165));
	literals.put(new ANTLRHashString("rTFN", this), new Integer(242));
	literals.put(new ANTLRHashString("kill", this), new Integer(170));
	literals.put(new ANTLRHashString("aEF", this), new Integer(232));
	literals.put(new ANTLRHashString("sMB", this), new Integer(258));
	literals.put(new ANTLRHashString("gTRG", this), new Integer(93));
	literals.put(new ANTLRHashString("gSRC", this), new Integer(69));
	literals.put(new ANTLRHashString("getAllTriggers", this), new Integer(94));
	literals.put(new ANTLRHashString("removeAllSources", this), new Integer(66));
	literals.put(new ANTLRHashString("gMD", this), new Integer(55));
	literals.put(new ANTLRHashString("rRP", this), new Integer(129));
	literals.put(new ANTLRHashString("getValue", this), new Integer(193));
	literals.put(new ANTLRHashString("rrid", this), new Integer(161));
	literals.put(new ANTLRHashString("sRTO", this), new Integer(183));
	literals.put(new ANTLRHashString("TS", this), new Integer(11));
	literals.put(new ANTLRHashString("goodbye", this), new Integer(116));
	literals.put(new ANTLRHashString("getAllNotificationTriggers", this), new Integer(219));
	literals.put(new ANTLRHashString("getNotificationChannel", this), new Integer(84));
	literals.put(new ANTLRHashString("gTic", this), new Integer(49));
	literals.put(new ANTLRHashString("DS", this), new Integer(13));
	literals.put(new ANTLRHashString("raFN", this), new Integer(228));
	literals.put(new ANTLRHashString("sLTO", this), new Integer(159));
	literals.put(new ANTLRHashString("getAllSources", this), new Integer(70));
	literals.put(new ANTLRHashString("removeAllTagSelectors", this), new Integer(98));
	literals.put(new ANTLRHashString("gaTS", this), new Integer(103));
	literals.put(new ANTLRHashString("gV", this), new Integer(194));
	literals.put(new ANTLRHashString("removeAllNotificationTriggers", this), new Integer(215));
	literals.put(new ANTLRHashString("gSN", this), new Integer(250));
	literals.put(new ANTLRHashString("setDataSelector", this), new Integer(207));
	literals.put(new ANTLRHashString("gMB", this), new Integer(256));
	literals.put(new ANTLRHashString("getDataSelector", this), new Integer(76));
	literals.put(new ANTLRHashString("getObservedTimeout", this), new Integer(152));
	literals.put(new ANTLRHashString("removeReadTriggers", this), new Integer(134));
	literals.put(new ANTLRHashString("create", this), new Integer(122));
	literals.put(new ANTLRHashString("gRT", this), new Integer(139));
	literals.put(new ANTLRHashString("addReadTriggers", this), new Integer(132));
	literals.put(new ANTLRHashString("fire", this), new Integer(195));
	literals.put(new ANTLRHashString("gGTO", this), new Integer(145));
	literals.put(new ANTLRHashString("EventType", this), new Integer(20));
	literals.put(new ANTLRHashString("gNT", this), new Integer(218));
	literals.put(new ANTLRHashString("gaTFN", this), new Integer(246));
	literals.put(new ANTLRHashString("gMax", this), new Integer(189));
	literals.put(new ANTLRHashString("getObservedThreshold", this), new Integer(148));
	literals.put(new ANTLRHashString("getTagSelector", this), new Integer(100));
	literals.put(new ANTLRHashString("RD", this), new Integer(5));
	literals.put(new ANTLRHashString("removeTriggers", this), new Integer(88));
	literals.put(new ANTLRHashString("setAddress", this), new Integer(205));
	literals.put(new ANTLRHashString("rFN", this), new Integer(226));
	literals.put(new ANTLRHashString("gaDS", this), new Integer(79));
	literals.put(new ANTLRHashString("gMRDC", this), new Integer(177));
	literals.put(new ANTLRHashString("getLength", this), new Integer(263));
	literals.put(new ANTLRHashString("isFX", this), new Integer(125));
	literals.put(new ANTLRHashString("aRT", this), new Integer(133));
	literals.put(new ANTLRHashString("setGlimpsedTimeout", this), new Integer(146));
	literals.put(new ANTLRHashString("getManufacturerDescription", this), new Integer(54));
	literals.put(new ANTLRHashString("setReadTimeout", this), new Integer(182));
	literals.put(new ANTLRHashString("removeAllDataSelectors", this), new Integer(74));
	literals.put(new ANTLRHashString("aNT", this), new Integer(212));
	literals.put(new ANTLRHashString("getAllDataSelectors", this), new Integer(78));
	literals.put(new ANTLRHashString("gTF", this), new Integer(109));
	literals.put(new ANTLRHashString("gT", this), new Integer(192));
	literals.put(new ANTLRHashString("ReaderDevice", this), new Integer(4));
	literals.put(new ANTLRHashString("aTFN", this), new Integer(240));
	literals.put(new ANTLRHashString("NC", this), new Integer(15));
	literals.put(new ANTLRHashString("removeNotificatonTriggers", this), new Integer(213));
	literals.put(new ANTLRHashString("wid", this), new Integer(167));
	literals.put(new ANTLRHashString("readQueuedData", this), new Integer(221));
	literals.put(new ANTLRHashString("setReadCyclesPerTrigger", this), new Integer(174));
	literals.put(new ANTLRHashString("getTrigger", this), new Integer(92));
	literals.put(new ANTLRHashString("sR", this), new Integer(47));
	literals.put(new ANTLRHashString("getType", this), new Integer(191));
	literals.put(new ANTLRHashString("TagSelector", this), new Integer(10));
	literals.put(new ANTLRHashString("getCurrentSource", this), new Integer(56));
	literals.put(new ANTLRHashString("rTRG", this), new Integer(89));
	literals.put(new ANTLRHashString("gR", this), new Integer(45));
	literals.put(new ANTLRHashString("gaNC", this), new Integer(87));
	literals.put(new ANTLRHashString("rSRC", this), new Integer(65));
}

public Token nextToken() throws TokenStreamException {
	Token theRetToken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		resetText();
		try {   // for char stream error handling
			try {   // for lexical error handling
				switch ( LA(1)) {
				case '\t':  case ' ':
				{
					mWS(true);
					theRetToken=_returnToken;
					break;
				}
				case '\n':  case '\r':
				{
					mLF(true);
					theRetToken=_returnToken;
					break;
				}
				case '#':
				{
					mSHARP(true);
					theRetToken=_returnToken;
					break;
				}
				case '.':
				{
					mDOT(true);
					theRetToken=_returnToken;
					break;
				}
				case '!':
				{
					mEXCLAMATION(true);
					theRetToken=_returnToken;
					break;
				}
				case ',':
				{
					mCOMMA(true);
					theRetToken=_returnToken;
					break;
				}
				case '{':
				{
					mLBRACKET(true);
					theRetToken=_returnToken;
					break;
				}
				case '}':
				{
					mRBRACKET(true);
					theRetToken=_returnToken;
					break;
				}
				case '=':
				{
					mASSIGN(true);
					theRetToken=_returnToken;
					break;
				}
				case '\\':
				{
					mESCAPE(true);
					theRetToken=_returnToken;
					break;
				}
				case '1':  case '2':  case '3':  case '4':
				case '5':  case '6':  case '7':  case '8':
				case '9':
				{
					mINT(true);
					theRetToken=_returnToken;
					break;
				}
				case 'A':  case 'B':  case 'C':  case 'D':
				case 'E':  case 'F':  case 'G':  case 'H':
				case 'I':  case 'J':  case 'K':  case 'L':
				case 'M':  case 'N':  case 'O':  case 'P':
				case 'Q':  case 'R':  case 'S':  case 'T':
				case 'U':  case 'V':  case 'W':  case 'X':
				case 'Y':  case 'Z':  case 'a':  case 'b':
				case 'c':  case 'd':  case 'e':  case 'f':
				case 'g':  case 'h':  case 'i':  case 'j':
				case 'k':  case 'l':  case 'm':  case 'n':
				case 'o':  case 'p':  case 'q':  case 'r':
				case 's':  case 't':  case 'u':  case 'v':
				case 'w':  case 'x':  case 'y':  case 'z':
				{
					mIDENT(true);
					theRetToken=_returnToken;
					break;
				}
				default:
				{
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
				else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				}
				if ( _returnToken==null ) continue tryAgain; // found SKIP token
				_ttype = _returnToken.getType();
				_ttype = testLiteralsTable(_ttype);
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (RecognitionException e) {
				throw new TokenStreamRecognitionException(e);
			}
		}
		catch (CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
			}
			else {
				throw new TokenStreamException(cse.getMessage());
			}
		}
	}
}

	public final void mWS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = WS;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case ' ':
		{
			match(' ');
			break;
		}
		case '\t':
		{
			match('\t');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		_ttype = Token.SKIP;
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLF(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LF;
		int _saveIndex;
		
		{
		if ((LA(1)=='\r') && (LA(2)=='\n')) {
			match("\r\n");
		}
		else if ((LA(1)=='\r') && (true)) {
			match('\r');
		}
		else if ((LA(1)=='\n')) {
			match('\n');
		}
		else {
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		
		}
		newline();
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSHARP(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SHARP;
		int _saveIndex;
		
		match("#");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDOT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DOT;
		int _saveIndex;
		
		match(".");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mEXCLAMATION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = EXCLAMATION;
		int _saveIndex;
		
		match("!");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCOMMA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COMMA;
		int _saveIndex;
		
		match(',');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLBRACKET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LBRACKET;
		int _saveIndex;
		
		match('{');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRBRACKET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = RBRACKET;
		int _saveIndex;
		
		match('}');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ASSIGN;
		int _saveIndex;
		
		match('=');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mESCAPE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ESCAPE;
		int _saveIndex;
		
		match("\\");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mDIGIT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIGIT;
		int _saveIndex;
		
		matchRange('0','9');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mINT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = INT;
		int _saveIndex;
		
		{
		matchRange('1','9');
		}
		{
		_loop955:
		do {
			if (((LA(1) >= '0' && LA(1) <= '9'))) {
				matchRange('0','9');
			}
			else {
				break _loop955;
			}
			
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mIDENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = IDENT;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case 'A':  case 'B':  case 'C':  case 'D':
		case 'E':  case 'F':  case 'G':  case 'H':
		case 'I':  case 'J':  case 'K':  case 'L':
		case 'M':  case 'N':  case 'O':  case 'P':
		case 'Q':  case 'R':  case 'S':  case 'T':
		case 'U':  case 'V':  case 'W':  case 'X':
		case 'Y':  case 'Z':
		{
			matchRange('A','Z');
			break;
		}
		case 'a':  case 'b':  case 'c':  case 'd':
		case 'e':  case 'f':  case 'g':  case 'h':
		case 'i':  case 'j':  case 'k':  case 'l':
		case 'm':  case 'n':  case 'o':  case 'p':
		case 'q':  case 'r':  case 's':  case 't':
		case 'u':  case 'v':  case 'w':  case 'x':
		case 'y':  case 'z':
		{
			matchRange('a','z');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		{
		_loop959:
		do {
			switch ( LA(1)) {
			case 'A':  case 'B':  case 'C':  case 'D':
			case 'E':  case 'F':  case 'G':  case 'H':
			case 'I':  case 'J':  case 'K':  case 'L':
			case 'M':  case 'N':  case 'O':  case 'P':
			case 'Q':  case 'R':  case 'S':  case 'T':
			case 'U':  case 'V':  case 'W':  case 'X':
			case 'Y':  case 'Z':
			{
				matchRange('A','Z');
				break;
			}
			case 'a':  case 'b':  case 'c':  case 'd':
			case 'e':  case 'f':  case 'g':  case 'h':
			case 'i':  case 'j':  case 'k':  case 'l':
			case 'm':  case 'n':  case 'o':  case 'p':
			case 'q':  case 'r':  case 's':  case 't':
			case 'u':  case 'v':  case 'w':  case 'x':
			case 'y':  case 'z':
			{
				matchRange('a','z');
				break;
			}
			case '0':  case '1':  case '2':  case '3':
			case '4':  case '5':  case '6':  case '7':
			case '8':  case '9':
			{
				matchRange('0','9');
				break;
			}
			default:
			{
				break _loop959;
			}
			}
		} while (true);
		}
		_ttype = testLiteralsTable(_ttype);
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	
	
	}
