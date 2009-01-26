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
package org.ow2.aspirerfid.epc.ale.tag.impl;

import java.io.PrintStream;
import java.util.BitSet;

import org.ow2.aspirerfid.epc.ale.tag.api.EPCTag;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 * @version 2006
 */
public class EPCTagFactory {

	private static PrintStream traceout = null;

	/**
	 * @param guid
	 * @return TODO Javadoc
	 */
	public static EPCTag getInstance(final String guid) {
		EPCTag epc;
		int size = 0;
		int f = 0;

		// First check the guid exist and is not empty
		if ((guid == null) || (guid.length() == 0)) {
			return null;
		}
		// build e bit set representation to be able to extract
		// any group of bits
		final BitSet binaryGuid = EPCTagImpl.hexString2BitSet(guid);

		EPCTagFactory.traceln("binaryGuid: " + binaryGuid);

		// try to interpret the guid
		size = (guid.length()) * 4;
		if (guid.charAt(0) == '"') {
			size -= 8;
		}

		EPCTagFactory.traceln("Size: " + size + "");

		// first get the header tag.
		if (binaryGuid.length() < 8) {
			// the guid must contains at least the 8 bits header
			epc = new EPCUnknownTag(binaryGuid, size);
			return epc;
		}

		// extract the header, and continue interpretation
		// depending on this header value
		try {
			f = EPCTagImpl.bitSet2int(binaryGuid.get(0, 8), 8);

			switch (f) {
			case 0x30: // 
				if (size == 96) {
					size = 96;
					epc = new EpcSGTINTag(binaryGuid, 96);
				} else {
					epc = new EPCUnknownTag(binaryGuid, size);
				}
				break;
			case 0x31:
				if (size == 96) {
					size = 96;
					epc = new EpcSSCCTag(binaryGuid, 96);
				} else {
					epc = new EPCUnknownTag(binaryGuid, size);
				}
				break;
			case 0x32:
				if (size == 96) {
					size = 96;
					epc = new EpcSGLNTag(binaryGuid, 96);
				} else {
					epc = new EPCUnknownTag(binaryGuid, size);
				}
				break;
			case 0x33:
				if (size == 96) {
					size = 96;
					epc = new EpcGRAITag(binaryGuid, 96);
				} else {
					epc = new EPCUnknownTag(binaryGuid, size);
				}
				break;
			case 0x34:
				if (size == 96) {
					size = 96;
					epc = new EpcGIAITag(binaryGuid, 96);
				} else {
					epc = new EPCUnknownTag(binaryGuid, size);
				}
				break;
			case 0x35:
				if (size == 96) {
					size = 96;

					epc = new EpcGIDTag(binaryGuid);
					EPCTagFactory.traceln("EpcUri: " + epc.getEpcUri());
					EPCTagFactory.traceln("TagUri: " + epc.getTagUri());
					EPCTagFactory.traceln("");
				} else {
					epc = new EPCUnknownTag(binaryGuid, size);
				}
				break;
			case 0x36:
				if (size == 200) {
					size = 198;
					epc = new EpcSGTINTag(binaryGuid.get(2, 200), 198);
				} else {
					epc = new EPCUnknownTag(binaryGuid, size);
				}
				break;
			case 0x37:
				if (size == 172) {
					size = 170;
					epc = new EpcGRAITag(binaryGuid.get(2, 172), 170);
				} else {
					epc = new EPCUnknownTag(binaryGuid, size);
				}
				break;
			case 0x38:
				if (size == 204) {
					size = 202;
					epc = new EpcGIAITag(binaryGuid.get(2, 204), 202);
				} else {
					epc = new EPCUnknownTag(binaryGuid, size);
				}
				break;
			case 0x39:
				if (size == 196) {
					size = 196;
					epc = new EpcSGLNTag(binaryGuid, 195);
				} else {
					epc = new EPCUnknownTag(binaryGuid, size);
				}
				break;

			case 0: // Unprogrammed Tag
			default:
				epc = new EPCUnknownTag(binaryGuid, size);
			}
		} catch (final EPCParseError e) {
			// an error during decode format. The format is incorrect
			// read the tag as an unknown format
			epc = new EPCUnknownTag(binaryGuid, size);
		}
		return epc;
	}

	private static void traceln(final String message) {
		if (EPCTagFactory.traceout != null) {
			EPCTagFactory.traceout.print(message);
		}
	}

}
