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

/**
 * Class to represent a SGLN format of tag
 * 
 * @author Anne Robert
 * @version 2006
 */
public class EpcSGLNTag extends EPCTagImpl {

	// filter field not used to identify, but to help filters
	private BitSet filterValue;
	int lfv; // and its length

	// Field to represent the product company
	private BitSet companyPrefix;
	int lcp; // and its length

	// Location reference of the product
	private BitSet locationReference;
	int llr; // and its length

	// Extension of the product
	private BitSet extensionComponent;
	int lec; // and its length

	/**
	 * Constructor: parse the input guid to build the SGLN fields
	 * 
	 * @param binaryGuid
	 *            the input guid in BitSet format
	 * @param size
	 *            physical format size
	 * @throws EPCParseError
	 *             if format is not correct
	 */
	public EpcSGLNTag(final BitSet binaryGuid, final int size)
			throws EPCParseError {
		// 5 fields :
		// 3 bits Filters
		// 3 bits partition
		// 20-40 bits Compagny Prefix
		// 24-4 bits Item Reference
		// 38/140 Serial Number
		this.binaryGuid = binaryGuid;
		this.binaryGuidLength = size;

		this.getSerializedGlobalFields();
		if (size == 96) {
			this.extensionComponent = binaryGuid.get(55, 96);
			this.lcp = 41;
		} else if (size == 195) {
			this.extensionComponent = binaryGuid.get(55, 195);
			this.lcp = 140;
		} else {
			this.isTagUri = false;
			final EPCParseError e = new EPCParseError();
			throw e;
		}

		this.tagUri = EPCTagImpl.PURE_IDENT_PREFIX + "sscc:"
				+ EPCTagImpl.bitSet2String(this.companyPrefix, this.lcp) + "."
				+ EPCTagImpl.bitSet2String(this.locationReference, this.llr)
				+ "."
				+ EPCTagImpl.bitSet2String(this.extensionComponent, this.lec);

		this.epcFormat = "sscc";
		this.epcFields = new Vector();
		this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.filterValue,
				this.lfv)));
		this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.companyPrefix,
				this.lcp)));
		this.epcFields.add(new Long(EPCTagImpl.bitSet2long(
				this.extensionComponent, this.lec)));
	}

	/**
	 * get the fields common on both serialized global Location number format
	 * and variable with partition value (for length 96 and 195)
	 * 
	 * @throws EPCParseError
	 *             if format is not correct
	 */
	private void getSerializedGlobalFields() throws EPCParseError {
		// GGLN Partition (bit number) :
		// Part Company Prefix Location reference
		// 0 40 1
		// 1 37 4
		// 2 34 7
		// 3 30 11
		// 4 27 14
		// 5 24 21
		// 6 20 21
		final int partition = EPCTagImpl.bitSet2int(
				this.binaryGuid.get(11, 14), 3);
		switch (partition) {
		case 0: // 40 bits for Company Prefix, 1 for Location reference
			this.companyPrefix = this.binaryGuid.get(14, 54);
			this.lcp = 40;
			this.locationReference = this.binaryGuid.get(54, 55);
			this.llr = 1;
			break;
		case 1: // 37 bits for Company Prefix, 4 for Location reference
			this.companyPrefix = this.binaryGuid.get(14, 51);
			this.lcp = 37;
			this.locationReference = this.binaryGuid.get(51, 55);
			this.llr = 4;
			break;
		case 2: // 34 bits for Company Prefix, 7 for Location reference
			this.companyPrefix = this.binaryGuid.get(14, 48);
			this.lcp = 34;
			this.locationReference = this.binaryGuid.get(48, 55);
			this.llr = 7;
			break;
		case 3: // 30 bits for Company Prefix, 11 for Location reference
			this.companyPrefix = this.binaryGuid.get(14, 44);
			this.lcp = 30;
			this.locationReference = this.binaryGuid.get(44, 55);
			this.llr = 11;
			break;
		case 4: // 27 bits for Company Prefix, 14 for Location reference
			this.companyPrefix = this.binaryGuid.get(14, 41);
			this.locationReference = this.binaryGuid.get(41, 55);
			break;
		case 5: // 24 bits for Company Prefix, 17 for Location reference
			this.companyPrefix = this.binaryGuid.get(14, 38);
			this.lcp = 24;
			this.locationReference = this.binaryGuid.get(38, 55);
			this.llr = 17;
			break;
		case 6: // 20 bits for Company Prefix, 21 for Location reference
			this.companyPrefix = this.binaryGuid.get(14, 34);
			this.lcp = 20;
			this.locationReference = this.binaryGuid.get(34, 55);
			this.llr = 21;
			break;

		default:
			this.isTagUri = false;
			final EPCParseError e = new EPCParseError();
			throw e;
		}
	}

}
