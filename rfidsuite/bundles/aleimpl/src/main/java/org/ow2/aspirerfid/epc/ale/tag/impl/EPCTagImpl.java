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

import java.math.BigInteger;
import java.util.BitSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import org.ow2.aspirerfid.epc.ale.tag.api.EPCTag;

/**
 * Abstract class to represent a tag format. Defines the functions common to all
 * tag format, and functions to be implemented for each known format
 * 
 * @author Anne Robert
 * @version 2006
 */
public abstract class EPCTagImpl implements EPCTag {

	/**
	 * Prefix of pure identifier URI
	 */
	public static String PURE_IDENT_PREFIX = "urn:epc:id:";

	/**
	 * Prefix of tag URI
	 */
	public static String TAG_URI_PREFIX = "urn:epc:tag:";

	/**
	 * Prefix of raw URI
	 */
	public static String RAW_URI_PREFIX = "urn:epc:raw:";

	/**
	 * transform a BitSet to an equivalent decimal string
	 * 
	 * @param set
	 *            the bit binary representation
	 * @param length
	 *            the logical length of BitSet (badly represented in BitSet
	 *            class)
	 * @return the String representation. Each character (0-9A-F) represent the
	 *         hexadecimal representation of 4 bits of the input BitSet
	 */
	public static String bitSet2DecString(final BitSet set, final int length) {
		final BigInteger val = new BigInteger(EPCTagImpl.BitsToBytes(set,
				length));
		return val.toString();
	}

	/**
	 * translate a bitSet to its integer value
	 * 
	 * @param set
	 *            the bit binary representation
	 * @param length
	 *            the logical length of BitSet (badly represented in BitSet
	 *            class)
	 * @return the integer value of bit sequence
	 */
	public static int bitSet2int(final BitSet set, final int length) {
		// scan the bit sequence. Add 1 if bit is set, and shift
		// the integer value at each step
		int res = 0;
		for (int i = 0; i < length; i++) {
			res *= 2;
			if (set.get(i)) {
				res += 1;
			}
		}
		return res;
	}

	/**
	 * translate a bitSet to its long value
	 * 
	 * @param set
	 *            the bit binary representation
	 * @param length
	 *            the logical length of BitSet (badly represented in BitSet
	 *            class)
	 * @return the integer value of bit sequence
	 */
	public static long bitSet2long(final BitSet set, final int length) {
		// scan the bit sequence. Add 1 if bit is set, and shift
		// the integer value at each step
		long res = 0;
		for (int i = 0; i < length; i++) {
			res *= 2;
			if (set.get(i)) {
				res += 1;
			}
		}
		return res;
	}

	/**
	 * transform a BitSet to an equivalent string
	 * 
	 * @param set
	 *            the bit binary representation
	 * @param length
	 *            the logical length of BitSet (badly represented in BitSet
	 *            class)
	 * @return the String representation. Each character (0-9A-F) represent the
	 *         hexadecimal representation of 4 bits of the input BitSet
	 */
	public static String bitSet2String(final BitSet set, final int length) {
		String res = "";
		BitSet charBitSet;

		// determine the first character. Particular for the first one because
		// it may
		// represent less than 4 bits
		int ind = length % 4;
		if (ind == 0) {
			ind = 4;
		}
		charBitSet = set.get(0, ind);
		res += Integer.toHexString(EPCTagImpl.bitSet2int(charBitSet, ind));
		// then for the following bit, compute them 4 by 4
		while (ind < length) {
			charBitSet = set.get(ind, ind + 4);
			// get the integer value of the 4 current bits
			final int charInt = EPCTagImpl.bitSet2int(charBitSet, 4);
			// and transform it to its hexadecimal representation
			res += Integer.toHexString(charInt);
			ind += 4;
		}
		return res;
	}

	/**
	 * Translate a BitSet to an array of bytes
	 * 
	 * @param bits
	 *            the input BitSet to translate
	 * @param length
	 *            the logical length of BitSet (badly represented in BitSet
	 *            class)
	 * @return the byte array representation of the BitSet. a byte for 8 bits of
	 *         the BitString
	 */
	public static byte[] BitsToBytes(final BitSet bits, final int length) {
		int j = 0;
		// allocate the byte array
		final byte[] bytes = new byte[length / 8 + 1];
		for (int i = (length - 1); i >= 0; i--) {
			// for each bit set, set the corresponding bit in the byte array
			// with "or" operator
			if (bits.get(i)) {
				bytes[bytes.length - j / 8 - 1] |= 1 << (j % 8);
			}
			j++;
		}
		// return the computed array
		return (bytes);
	}

	/**
	 * Function to translate an hexadecimal value to a BitSet
	 * 
	 * @param hexValue
	 *            the hexadecimal representation of the bit sequence
	 * @return the bit sequence
	 */
	public static BitSet hexString2BitSet(final String hexValue) {
		int first = 0;
		int length = hexValue.length();
		if (hexValue.charAt(0) == '"') {
			first = 1;
			length -= 2;
		}
		final int l = length * 4;

		final BitSet res = new BitSet(l);
		// each character must be 0-9 or A-F represents 4 bits
		int iBit = 0;
		for (int iHex = first; iHex < first + length; iHex += 1, iBit += 4) {
			int charVal = Integer.parseInt("0" + hexValue.charAt(iHex), 16);

			// int charVal = new Integer("Ox"+hexValue.charAt(iHex)).intValue()
			// ;

			for (int i = 3; i >= 0; i--) {
				// set the bit corresponding to the input character
				// if ((iBit+i)>l) System.out.println("bound error") ;
				if (charVal % 2 != 0) {
					res.set(iBit + i);
				}
				charVal = charVal / 2;
			}
		}
		return res;

	}

	// the input binaryGuid is successfully parsed and valid
	protected boolean isTagUri;
	// The tag representation of the Guid
	// The tag representation include physical coding information
	protected String tagUri;
	// unique information that identifies a specific object
	// and is independent of the tag coding
	protected String epcUri;

	// Tag format
	protected String tagFormat;

	// Epc format
	protected String epcFormat;

	// bits read by the reader without any interpretation
	protected BitSet binaryGuid;

	protected int binaryGuidLength;

	private String rawDecUri = null;

	private String rawHexUri = null;

	/**
	 * Vector of fields read on the tag used to compare to a pattern The fields
	 * can be either Long if the field can be converted to a number or a String
	 * in other case
	 */
	public Vector epcFields;

	/**
	 * need an empty constructor for derived class. initialize all field to
	 * null.
	 */
	public EPCTagImpl() {
		this.tagUri = null;
		this.epcUri = null;
		this.rawDecUri = null;
		this.rawHexUri = null;
	}

	// Functions used on BitSet Object

	/**
	 * Create a tag with uri already build
	 * 
	 * @param tagUri
	 * @param epcUri
	 * @param rawDecUri
	 * @param rawHexUri
	 */
	public EPCTagImpl(final String tagUri, final String epcUri,
			final String rawDecUri, final String rawHexUri) {
		this.tagUri = tagUri;
		this.epcUri = epcUri;
		this.rawDecUri = rawDecUri;
		this.rawHexUri = rawHexUri;
		String fieldsStr = null;
		if ((epcUri != null)
				&& (epcUri.startsWith(EPCTagImpl.PURE_IDENT_PREFIX))) {
			fieldsStr = epcUri.substring(EPCTagImpl.PURE_IDENT_PREFIX.length());
			final StringTokenizer st = new StringTokenizer(fieldsStr, ".");
			while (st.hasMoreElements()) {
				this.epcFields.add(st.nextElement());
			}

		} else if ((tagUri != null)
				&& (epcUri.startsWith(EPCTagImpl.TAG_URI_PREFIX))) {
			fieldsStr = epcUri.substring(EPCTagImpl.TAG_URI_PREFIX.length());
			final StringTokenizer st = new StringTokenizer(fieldsStr, ".");
			while (st.hasMoreElements()) {
				this.epcFields.add(st.nextElement());
			}
		} else {
			// raw uri, no fields defined
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(final Object obj) {
		if (obj instanceof EPCTag) {
			final EPCTag tag = (EPCTag) obj;
			return tag.getEpcUri().equals(this.getEpcUri());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.epc.ale.tag.api.EPCTag#getEpcFormat()
	 */
	public String getEpcFormat() {
		return this.epcFormat;
	}

	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.epc.ale.tag.api.EPCTag#getEpcUri()
	 */
	public String getEpcUri() {
		return this.epcUri;
	}

	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.epc.ale.tag.api.EPCTag#getFieldIter()
	 */
	public Iterator getFieldIter() {
		return this.epcFields.iterator();
	}

	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.epc.ale.tag.api.EPCTag#getRawDecimalUri()
	 */
	public String getRawDecimalUri() {
		if (this.rawDecUri != null) {
			return this.rawDecUri;
		}
		if (this.binaryGuid != null) {
			String res = EPCTagImpl.RAW_URI_PREFIX;
			res += this.binaryGuidLength
					+ ".x"
					+ EPCTagImpl.bitSet2DecString(this.binaryGuid,
							this.binaryGuidLength);
			return res;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.epc.ale.tag.api.EPCTag#getRawHexUri()
	 */
	public String getRawHexUri() {
		if (this.rawHexUri != null) {
			return this.rawHexUri;
		}
		if (this.binaryGuid != null) {
			String res = EPCTagImpl.RAW_URI_PREFIX;
			res += this.binaryGuidLength
					+ ".x"
					+ EPCTagImpl.bitSet2String(this.binaryGuid,
							this.binaryGuidLength);
			return res;
		}
		return null;
	}

	public String getTagFormat() {
		return this.tagFormat;
	}

	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.epc.ale.tag.api.EPCTag#getTagUri()
	 */
	public String getTagUri() {
		return this.tagUri;
	}

	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.epc.ale.tag.api.EPCTag#isTagUri()
	 */
	public boolean isTagUri() {
		return this.isTagUri;
	}

}
