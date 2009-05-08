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

package org.ow2.aspirerfid.reader.rp.impl.core.msg.util;

import org.apache.log4j.Logger;

/**
 * Utilities used for hexadecimal to byte[] and vice versa conversion and for
 * bitarray manipulation.
 * 
 * @author Jonas Haller
 */
public class HexUtil {

	private static final Logger LOG = Logger.getLogger(HexUtil.class);

	/**
	 * Converts a hexadecimal value represented as String into an array of
	 * bytes.
	 * 
	 * @param hexString
	 *            the hexadecimal string to convert
	 * @return the representing byte array
	 */
	public static byte[] hexToByteArray(String hexString) {
		try {
			if (hexString.startsWith("H")) {
				hexString = hexString.substring(1);
			}
			LOG.debug("Convert HexString: " + hexString);

			byte[] byteArray;
			if (hexString.length() % 2 != 0) {
				hexString = new String("0" + hexString);
			}
			int len = hexString.length();
			byteArray = new byte[len / 2];
			for (int i = 0; i < len / 2; i++) {
				String byteString = hexString.substring(i * 2, i * 2 + 2);
				Integer intValue = new Integer(Integer.parseInt(byteString, 16));
				byte byteValue = intValue.byteValue();
				byteArray[i] = byteValue;
			}

			return byteArray;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Converts a byte array into a hexadecimal string.
	 * 
	 * @param byteArray
	 *            the byte array to convert
	 * @return the representing hexadecimal string
	 */
	public static String byteArrayToHexString(byte[] byteArray) {

		StringBuffer sb = new StringBuffer();
		String h = "0123456789ABCDEF";
		int byteint;
		String bytestring;

		for (int i = 0; i < byteArray.length; i++) {
			byteint = ((int) byteArray[i]) & 0xFF;
			bytestring = "" + h.charAt((byteint & 0xF0) >> 4) + h.charAt(byteint & 0x0F);
			sb.append(bytestring);
		}

		return sb.toString();
	}

	/**
	 * Shifts data bits left and fills insignificant bits between data bits and
	 * next byte boundaries with data from given first and last byte. This
	 * method is intended to substitute a burst of bits in a bit string.
	 * 
	 * Example: (in bitstring 0x555555, replace bits 6 - 19 with ones) data:
	 * 0x1FFF = 00011111 11111111, length: 13, shift: 5 (last bit (#19) is 5
	 * bits from next byte boundary (#24), first: 01010101, last: 01010101
	 * (original bytes at start and end of shifted data bit string). - shift
	 * data 5 bits left: 00000011 11111111 11100000 - replace insignificant bits
	 * (the zeros in this example) with bits of original bytes: 010101|11
	 * 11111111 111|10101 (| marks boundary between original bits and data bits)
	 * 
	 * Example preparation code to get shift value and if necessary first and
	 * last byte from a byte array with given bit offset and bit length: int
	 * byteoffset = offset / 8; int bytelength = ((offset % 8) + length + 7) /
	 * 8; int shift = (8 - ((offset + length) % 8)) % 8; byte first; if ((offset %
	 * 8) == 0) { first = 0x00; } else { first = reader.readBytes(readPointName,
	 * id, memoryBank, byteoffset, 1, passwords) .toByteArray()[0]; } byte last;
	 * if (shift == 0) { last = 0x00; } else if (bytelength == 1) { last =
	 * first; } else { last = reader.readBytes(readPointName, id, memoryBank,
	 * (byteoffset + bytelength - 1), 1, passwords).toByteArray()[0]; }
	 * 
	 * @param data
	 *            the bit array as byte array (padded with zero-bits before the
	 *            most significant bit)
	 * @param length
	 *            the number of significant bits in the data
	 * @param shift
	 *            the amount of bits the data needs to be shifted left (0 - 7)
	 * @param first
	 *            the first original byte to fill insignificant data bits
	 * @param last
	 *            the last original byte to fill insignificant data bits
	 * @return the shifted and filled data as byte[]
	 */
	public static byte[] bitarrayShiftAndFill(byte[] data, int length, int shift, byte first, byte last) {

		byte[] result;

		if (shift > ((data.length * 8) - length)) {
			// padding one more byte if shift greater than insignificant bits
			result = new byte[data.length + 1];
			result[0] = (byte) 0x00;
			System.arraycopy(data, 0, result, 1, data.length);
		} else {
			result = new byte[data.length];
			System.arraycopy(data, 0, result, 0, data.length);
		}

		// get databytes and shift significant bits
		int leftmask = (0xFF << shift) & 0xFF;
		int rightmask = leftmask ^ 0xFF;
		for (int i = 0; i < (result.length - 1); i++) {
			result[i] = (byte) ((((((int) result[i]) << shift) & leftmask) | ((((int) result[i + 1]) >> (8 - shift)) & rightmask)) & 0xFF);
		}
		result[result.length - 1] = (byte) ((((int) result[result.length - 1]) << shift) & leftmask);

		// fill insignificant bits with original data
		int leftbits = (8 - ((shift + length) % 8)) % 8;
		int firstleftmask = (0xFF << (8 - leftbits)) & 0xFF; // new left mask
		int inverseleftmask = firstleftmask ^ 0xFF;
		result[0] = (byte) (((int) result[0] & inverseleftmask) | ((int) first & firstleftmask));
		result[result.length - 1] = (byte) (((int) result[result.length - 1] & leftmask) | ((int) last & rightmask));

		return result;
	}
}
