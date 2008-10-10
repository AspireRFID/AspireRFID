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

import java.util.BitSet;
import java.util.Vector;

import org.ow2.aspirerfid.epc.ale.tag.api.EPCTag;

/**
 * Class to represent a SGTIN format of tag
 * 
 * @author Anne Robert
 * @version 2006
 */
public class EpcSGTINTag extends EPCTagImpl {

	// filter field not used to identify, but to help filters
	private BitSet filterValue;
	int lfv; // and its length

	private BitSet companyPrefix;
	int lcp;
	private BitSet itemReference;
	int lir;
	private BitSet serialNumber;
	int lsn;

	/**
	 * Constructor: parse the input guid to build the SGTIN fields
	 * 
	 * @param binaryGuid
	 *            the input guid in BitSet format
	 * @param size
	 *            physical format size
	 * @throws EPCParseError
	 *             if format is not correct
	 */
	public EpcSGTINTag(final BitSet binaryGuid, final int size)
			throws EPCParseError {
		// 5 fields :
		// 3 bits Filters
		// 3 bits partition
		// 20-40 bits Compagny Prefix
		// 24-4 bits Item Reference
		// 38/140 Serial Number
		this.binaryGuid = binaryGuid;
		this.binaryGuidLength = size;

		if (size == 96) {
			this.getSerializedFields();
			// 38 last bits for SerialNumber
			this.serialNumber = binaryGuid.get(59, 73);
			;
			this.tagFormat = EPCTag.SERIALIZED_96_FORMAT;

		} else if (size == 198) {
			this.getSerializedFields();
			// 140 last bits for SerialNumber
			this.serialNumber = binaryGuid.get(58, 198);
			this.tagFormat = EPCTag.SERIALIZED_198_FORMAT;
		} else {
			this.isTagUri = false;
			final EPCParseError e = new EPCParseError();
			throw e;
		}

		this.tagUri = EPCTagImpl.PURE_IDENT_PREFIX + "sgtin:"
				+ EPCTagImpl.bitSet2String(this.companyPrefix, this.lcp) + "."
				+ EPCTagImpl.bitSet2String(this.itemReference, this.lir) + "."
				+ EPCTagImpl.bitSet2String(this.serialNumber, this.lsn);

		this.epcFormat = "sgtin";
		this.epcFields = new Vector();
		this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.filterValue,
				this.lfv)));
		this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.companyPrefix,
				this.lcp)));
		this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.itemReference,
				this.lir)));
		this.epcFields.add(EPCTagImpl
				.bitSet2String(this.serialNumber, this.lsn));

	}

	/**
	 * get the fields common on both serialized global trade item format and
	 * variable with partition value (for length 96 and 198)
	 * 
	 * @throws EPCParseError
	 *             is the format does not correspond to SGTIN format
	 */
	private void getSerializedFields() throws EPCParseError {
		this.filterValue = this.binaryGuid.get(8, 11);
		this.lfv = 3;
		final int partition = EPCTagImpl.bitSet2int(
				this.binaryGuid.get(11, 14), 3);
		this.companyPrefix = null;
		this.itemReference = null;
		// SGTIN Partition (bit number) :
		// Part Company Prefix Item ref
		// 0 40 4
		// 1 37 7
		// 2 34 10
		// 3 30 14
		// 4 27 17
		// 5 24 20
		// 6 20 24
		switch (partition) {
		case 0: // 40 bits for Company Prefix, 4 for Item reference
			this.companyPrefix = this.binaryGuid.get(14, 54);
			this.lcp = 40;
			this.itemReference = this.binaryGuid.get(54, 58);
			this.lir = 4;
			break;
		case 1: // 37 bits for Company Prefix, 7 for Item reference
			this.companyPrefix = this.binaryGuid.get(14, 51);
			this.lcp = 37;
			this.itemReference = this.binaryGuid.get(51, 58);
			this.lir = 7;
			break;
		case 2: // 34 bits for Company Prefix, 10 for Item reference
			this.companyPrefix = this.binaryGuid.get(14, 48);
			this.lcp = 34;
			this.itemReference = this.binaryGuid.get(48, 58);
			this.lir = 10;
			break;
		case 3: // 30 bits for Company Prefix, 14 for Item reference
			this.companyPrefix = this.binaryGuid.get(14, 44);
			this.lcp = 30;
			this.itemReference = this.binaryGuid.get(44, 58);
			this.lir = 14;
			break;
		case 4: // 27 bits for Company Prefix, 17 for Item reference
			this.companyPrefix = this.binaryGuid.get(14, 41);
			this.lcp = 27;
			this.itemReference = this.binaryGuid.get(41, 58);
			this.lir = 17;
			break;
		case 5: // 24 bits for Company Prefix, 20 for Item reference
			this.companyPrefix = this.binaryGuid.get(14, 38);
			this.lcp = 24;
			this.itemReference = this.binaryGuid.get(38, 58);
			this.lir = 20;
			break;
		case 6: // 20 bits for Company Prefix, 24 for Item reference
			this.companyPrefix = this.binaryGuid.get(14, 34);
			this.lcp = 20;
			this.itemReference = this.binaryGuid.get(34, 58);
			this.lir = 24;
			break;

		default:
			this.isTagUri = false;
			final EPCParseError e = new EPCParseError();
			throw e;
		}
	}

}
