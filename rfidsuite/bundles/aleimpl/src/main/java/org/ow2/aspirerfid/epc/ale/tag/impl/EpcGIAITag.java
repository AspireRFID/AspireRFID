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
 * Class to represent a GIAI format of tag
 * 
 * @author Anne Robert
 * @version 2006
 */
public class EpcGIAITag extends EPCTagImpl {

	// filter field not used to identify, but to help filters
	private BitSet filterValue;
	int lfv; // and its length

	// Field to represent the product company
	private BitSet companyPrefix;
	int lcp; // and its length

	// Field to represent the asset Type
	private BitSet assetType;
	int lat; // and its length

	/**
	 * Constructor: parse the input guid to build the GIAI fields
	 * 
	 * @param binaryGuid
	 *            the input guid in BitSet format
	 * @param size
	 *            physical format size
	 * @throws EPCParseError
	 *             if format is not correct
	 */
	public EpcGIAITag(final BitSet binaryGuid, final int size)
			throws EPCParseError {
		// 5 fields :
		// 3 bits Filters
		// 3 bits partition
		// 20-40 bits Company Prefix
		// 24-4 bits Item Reference
		// 38/140 Serial Number
		this.binaryGuid = binaryGuid;
		this.binaryGuidLength = size;

		if (size == 96) {
			this.tagFormat = EPCTag.GLOBAL_INDIVIDUAL_96_FORMAT;
			this.decodeGlobalIndividual96Fileds();
		} else if (size == 202) {
			this.tagFormat = EPCTag.GLOBAL_INDIVIDUAL_202_FORMAT;
			this.decodeGlobalIndividual202Fileds();
		} else {
			this.isTagUri = false;
			final EPCParseError e = new EPCParseError();
			throw e;
		}

		this.tagUri = EPCTagImpl.PURE_IDENT_PREFIX + "giai:"
				+ EPCTagImpl.bitSet2String(this.companyPrefix, this.lcp) + "."
				+ EPCTagImpl.bitSet2String(this.assetType, this.lat);
		this.epcUri = EPCTagImpl.TAG_URI_PREFIX + this.tagFormat
				+ EPCTagImpl.bitSet2String(this.companyPrefix, this.lcp) + "."
				+ EPCTagImpl.bitSet2String(this.assetType, this.lat);

		this.epcFormat = "grai";
		this.epcFields = new Vector();
		this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.filterValue,
				this.lfv)));
		this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.companyPrefix,
				this.lcp)));
		this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.assetType,
				this.lat)));

	}

	/**
	 * get the fields common on both global individual asset identification
	 * format and variable with partition value (for length 96 and 202)
	 * 
	 * @throws EPCParseError
	 *             if the format is not correct
	 */
	private void decodeGlobalIndividual202Fileds() throws EPCParseError {
		// GRAI Partition (bit number) :
		// Part Company Prefix Individual Asset Reference
		// 0 40 126
		// 1 37 133
		// 2 34 140
		// 3 30 147
		// 4 27 154
		// 5 24 161
		// 6 20 168
		final int partition = EPCTagImpl.bitSet2int(
				this.binaryGuid.get(11, 14), 3);
		switch (partition) {
		case 0: // 40 bits for Company Prefix, 126 for asset type
			this.lcp = 40;
			this.lat = 126;
			break;
		case 1: // 37 bits for Company Prefix, 133 for asset type
			this.lcp = 37;
			this.lat = 133;
			break;
		case 2: // 34 bits for Company Prefix, 140 for asset type
			this.lcp = 34;
			this.lat = 140;
			break;
		case 3: // 30 bits for Company Prefix, 147 for asset type
			this.lcp = 30;
			this.lat = 147;
			break;
		case 4: // 27 bits for Company Prefix, 154 for asset type
			this.lcp = 27;
			this.lat = 154;
			break;
		case 5: // 24 bits for Company Prefix, 161 for asset type
			this.lcp = 24;
			this.lat = 161;
			break;
		case 6: // 20 bits for Company Prefix, 168 for asset type
			this.lcp = 24;
			this.lat = 168;
			break;

		default:
			this.isTagUri = false;
			final EPCParseError e = new EPCParseError();
			throw e;
		}
		this.companyPrefix = this.binaryGuid.get(14, 14 + this.lcp);
		this.assetType = this.binaryGuid.get(14 + this.lcp, 14 + this.lcp
				+ this.lat);

	}

	/**
	 * get the fields common on both global individual asset identification
	 * format and variable with partition value (for length 96 and 202)
	 * 
	 * @throws EPCParseError
	 *             if the format is not correct
	 */
	private void decodeGlobalIndividual96Fileds() throws EPCParseError {
		// GRAI Partition (bit number) :
		// Part Company Prefix Individual Asset Reference
		// 0 40 42
		// 1 37 45
		// 2 34 48
		// 3 30 52
		// 4 27 55
		// 5 24 58
		// 6 20 62
		final int partition = EPCTagImpl.bitSet2int(
				this.binaryGuid.get(11, 14), 3);
		switch (partition) {
		case 0: // 40 bits for Company Prefix, 42 for asset type
			this.lcp = 40;
			this.lat = 42;
			break;
		case 1: // 37 bits for Company Prefix, 45 for asset type
			this.lcp = 37;
			this.lat = 45;
			break;
		case 2: // 34 bits for Company Prefix, 48 for asset type
			this.lcp = 34;
			this.lat = 48;
			break;
		case 3: // 30 bits for Company Prefix, 52 for asset type
			this.lcp = 30;
			this.lat = 52;
			break;
		case 4: // 27 bits for Company Prefix, 55 for asset type
			this.lcp = 27;
			this.lat = 55;
			break;
		case 5: // 24 bits for Company Prefix, 58 for asset type
			this.lcp = 24;
			this.lat = 58;
			break;
		case 6: // 20 bits for Company Prefix, 62 for asset type
			this.lcp = 24;
			this.lat = 62;
			break;

		default:
			this.isTagUri = false;
			final EPCParseError e = new EPCParseError();
			throw e;
		}
		this.companyPrefix = this.binaryGuid.get(14, 14 + this.lcp);
		this.assetType = this.binaryGuid.get(14 + this.lcp, 14 + this.lcp
				+ this.lat);

	}

}
