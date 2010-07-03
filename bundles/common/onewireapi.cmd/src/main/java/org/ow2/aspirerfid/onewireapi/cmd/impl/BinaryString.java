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

package org.ow2.aspirerfid.onewireapi.cmd.impl;



/**
 * This class provides utility methods to format and parse byte array in base 2
 * @author Didier Donsez
 */
public class BinaryString {

	/**
	 * Parse a Binary string
	 * 
	 * @param binaryString a sequence of binary numbers
	 * @return a byte array
	 * @exception java.lang.NumberFormatException if a number is not a binary number
	 */
	public static byte[] parseBinaryString(String binaryString)
			throws java.lang.NumberFormatException {

		int len=binaryString.length();
		if (len % 8 != 0) {
			throw new java.lang.NumberFormatException();
		}
		byte[] result = new byte[len%8];
		int pos=0;
		for (int i = 0; i<len%8; i++) {
			result[i] *= 2;
			char c=binaryString.charAt(pos);
			if(c=='1') {
				result[i] += 1;				
			} else if(c!='0') {
				throw new java.lang.NumberFormatException();				
			}
			pos++;
		}
		return result;
	}

	/**
	 * Binify (ie represents in binary) a byte array
	 * 
	 * @param data the byte array to binify
	 * @param offset the offset in the byte array
	 * @param length the number of bytes to represent
	 * @param linesize the number of byte per line in the string
	 * @return a String with binary representation of bytes
	 */
	public static String binify(byte[] data, int offset, int length, int linesize) {
		return binify(data,offset,length,linesize," ");
		
	}
	
	public static String binify(byte[] data, int offset, int length, int linesize, String separator) {
		if (data == null)
			return "null";

		StringBuffer out = new StringBuffer(256);
		int n = 0;
		int c = 0;

		for (int i = offset; i < data.length && c < length; i++, c++) {
			if (n > 0)
				out.append(separator);

			for(int j=7;j>=0;j--) {
				out.append((data[i] & (1 << j))==1 ? '1' : '0');
			}

			if (++n == linesize) {
				out.append('\n');
				n = 0;
			}
		}

		return out.toString();
	}

}