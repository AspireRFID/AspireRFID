/*
   Copyright 2005-2008, OW2 Aspire RFID project 
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.

   Contact: OW2 Aspire RFID project <X AT Y DOT org> (with X=aspirerfid and Y=ow2)

   LGPL version 2.1 full text http://www.gnu.org/licenses/lgpl-2.1.txt    
*/

package org.ow2.aspirerfid.rxtx.cmd.impl;

import java.util.StringTokenizer;

/**
 * This class provides utility methods to format and parse byte array in base 16
 * @author Didier Donsez
 */
public class HexString {

	protected final static String[] hexChars = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	/**
	 * Parse a Hex string
	 * 
	 * @param hexString a sequence of 2-symbol hexadecimal numbers
	 * @return a byte array
	 * @exception java.lang.NumberFormatException if a number is not a hexadecimal number
	 * @note : java.lang.Integer.parseInt(String s, int radix) do not verify if
	 *       symbol is correct !!
	 * @note : opencard.core.util.HexString.parseHexString(String s) requires
	 *       fixed space in string
	 */
	public static byte[] parseHexString(String hexString)
			throws java.lang.NumberFormatException {

		StringTokenizer st = new StringTokenizer(hexString);
		byte[] result = new byte[st.countTokens()];
		for (int i = 0; st.hasMoreTokens(); i++) {
			//result[i]=(byte)Integer.parseInt(st.nextToken(),16);
			char[] ca = (st.nextToken()).toCharArray();
			if (ca.length != 2) {
				throw new java.lang.NumberFormatException();
			}
			result[i] = (byte) (parseHexChar(ca[0]) * 16 + parseHexChar(ca[1]));
		}
		return result;
	}

	/**
	 * Parse a radix 16 symbol
	 * 
	 * @param c a symbol
	 * @return a byte
	 * @exception java.lang.NumberFormatException if c is not a hexadecimal symbol 
	 * @note : java.lang.Integer.parseInt(String s, int radix) do not verify if
	 *       symbol is correct !!
	 */
	public static byte parseHexChar(char c)
			throws java.lang.NumberFormatException {
		if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f')
				|| (c >= 'A' && c <= 'F')) {
			return (byte) (Character.digit(c, 16));
		}
		throw new java.lang.NumberFormatException();
	}

	/**
	 * Hexify (ie represents in hexadecimal) a byte array
	 * 
	 * @param data the byte array to hexify
	 * @param offset the offset in the byte array
	 * @param length the number of bytes to represent
	 * @param linesize the number of byte per line in the string
	 * @return a String with Hexadecimal representation of bytes
	 */
	public static String hexify(byte[] data, int offset, int length, int linesize) {
		return hexify(data,offset,length,linesize," ");
		
	}
	
	public static String hexify(byte[] data, int offset, int length, int linesize, String separator) {
		if (data == null)
			return "null";

		StringBuffer out = new StringBuffer(256);
		int n = 0;
		int c = 0;

		for (int i = offset; i < data.length && c < length; i++, c++) {
			if (n > 0)
				out.append(separator);

			out.append(hexChars[(data[i] >> 4) & 0x0f]);
			out.append(hexChars[data[i] & 0x0f]);

			if (++n == linesize) {
				out.append('\n');
				n = 0;
			}
		}

		return out.toString();
	}

}