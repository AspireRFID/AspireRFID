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

package org.ow2.aspirerfid.reader.rp.impl.hal.util;

import java.io.UnsupportedEncodingException;

/**
 * ByteBlock represents a block of bytes of arbitrary length.
 * 
 * The bytes are stored internally in a byte array, howerver
 * the bytes are numbered from left to right in contrary to 
 * numbers in hexadecimal format. The bytes in the array are
 * also considered unsigned in contrast to Java. This allows
 * the usage of ByteBlock to store output of hardware devices.
 * 
 * The following is an example of the String representation of 
 * a byte block of length 12:
 * <code>10FF024E6A0000A1611D6400</code>.
 * 
 * In addition the class provides several static byte array support
 * functions, which allow the usage of ByteBlock without creating
 * objects of the class.
 * 
 * @version	1.0, 03/2003
 * @author 	Matthias Lampe, lampe@acm.org
 */
public class ByteBlock {
	//---- Fields -----------------------------------------------------------

	/** the byte array to store the byte block */
	protected byte[] bytes;


	//---- Constructor(s) ---------------------------------------------------

	/**
	 * creates a byte block from the given byte array.
	 *
	 * @param bytes the byte array, which is copied into the
	 * 					internal representation.
	 */
	public ByteBlock(byte[] bytes) {
		// copy source to internal byte array
		this.bytes = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++)
			this.bytes[i] = bytes[i];
	}


	/**
	 * creates a byte block from the given bytes as a String.
	 *
	 * @param bytes the bytes as a String.
	 */
	public ByteBlock(String bytes) {
		this.bytes = hexStringToByteArray(bytes);
	}


	//---- Methods ----------------------------------------------------------

	/**
	 * returns a reference to the internal byte array.
	 *
	 * @return the internal byte array.
	 */
	public byte[] getInternalByteArray() {
		return bytes;
	}


	/**
	 * gets the byte block as a byte array.
	 *
	 */
	public byte[] toByteArray() {
		// copy source to internal byte array
		byte[] ba = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++)
			ba[i] = bytes[i];
			
		return ba;
	}


	/**
	 * gets the byte block as a String.
	 *
	 * @return the unsigned hex number as a String.
	 */
	public String toString() {
		return byteArrayToHexString(bytes);
	}


	/**
	 * clones the byte block.
	 *
	 * @return the deep copy of the byte block.
	 */
	public Object clone() {
		// clone byte array
		byte[] copy = toByteArray();

		// clone ByteBlock
		ByteBlock bbCopy = new ByteBlock(copy);

		return bbCopy;
	}


	
	/**
	 * checks if two byte blocks are equal or not.
	 * 
	 * @return true, if both byte blocks are equal.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof ByteBlock)
			return ((ByteBlock)o).bytes.equals(this.bytes);
		else
			return false;
	}


	/**
	 * calculates a hashcode for the byte block.
	 * 
	 * @return the hashcode of the byte block.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		long v = 0;
		for (int i = bytes.length-1; i >= 0; i--) {
			v = 256 * v + ((int)bytes[i] & 0xFF);
		}
		return (int)v;
	}


	/**
	 * returns a string with the byte in hexadecimal representation.
	 * The byte is interpreted as unsigned value.
	 *
	 * @param b the byte.
	 *
	 * @return a string with the byte in hexadecimal representation.
	 */
	static public String byteToHexString(byte b) {
		int i = b;
		String h = "0123456789ABCDEF";
		return "" + h.charAt((i & 0xF0) >> 4) + h.charAt(i & 0x0F);
	}


	/**
	 * returns a string with the bytes of the input array in hexadecimal
	 * representation.
	 *
	 * @param byteArray the array of bytes.
	 *
	 * @return a string with the bytes of the input array in hexadecimal
	 *          representation.
	 */
	static public String byteArrayToHexString(byte[] byteArray)
	{
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++)
			sb.append(byteToHexString(byteArray[i]));

		return sb.toString();
	}

	/**
	 * returns a string with the byte of the input array in hexadecimal
	 * representation trimed/padded to a specific length.
	 * 
	 * If length is less than the length of the input array, the most
	 * significant bits (low array indices) are cut.
	 * 
	 * If length is greater than the length of the input array, "00"
	 * bytes are added in front of the resulting string.
	 * 
	 * @param byteArray
	 * @param length
	 * @return a string with the bytes of the input array in hexadecimal
	 *          representation.
	 */
	static public String byteArrayToHexString(byte[] byteArray, int length) {
		byte[] data = new byte[length];
		int pad = length - byteArray.length;
		if (pad > 0) {
			System.arraycopy(byteArray, 0, data, pad, byteArray.length);
		} else {
			System.arraycopy(byteArray, -pad, data, 0, length);
		}
		return byteArrayToHexString(data);
	}

	/**
	 * converts a String of hex bytes to a byte array.
	 *
	 * @param hexString the byte array as a String.
	 */
	static public byte[] hexStringToByteArray(String hexString)
	{
		byte[] byteArray;

		int len = hexString.length();
		byteArray = new byte[len/2];
		if (len % 2 != 0) throw new IllegalArgumentException();
		for (int i = 0; i < len/2; i++){
			//String byteString = "0x"+hexString.substring(i*2, i*2+2);
			//Integer intValue = Integer.decode(byteString);
			String byteString = hexString.substring(i*2, i*2+2);
			Integer intValue = new Integer(Integer.parseInt(byteString, 16));
			byte byteValue = intValue.byteValue();
			byteArray[i] = byteValue;
		}

		return byteArray;
	}
	
	/**
	 * converts a String of hex byte to a byte array of a specific length.
	 * 
	 * If length is less than the converted String, the most significant
	 * bits (low array indices) are cut.
	 * 
	 * If length is greater than the converted String, the resulting array
	 * is padded with 0 bytes at the beginning (low array indices).
	 * 
	 * @param hexString
	 * @param length
	 * @return a byte array derived from the hexadecimal representation given
	 */
	static public byte[] hexStringToByteArray(String hexString, int length) {
		byte[] data = new byte[length];
		
		int pad = length - hexString.length()/2;
		byte[] byteArray = hexStringToByteArray(hexString);
		if (pad > 0) {
			System.arraycopy(byteArray, 0, data, pad, byteArray.length);
		} else {
			System.arraycopy(byteArray, -pad, data, 0, length);
		}
		
		return data;
	}
	
	/**
	 * converts an unsigned byte value to a (signed) integer.
	 * 
	 * @param b
	 *            Unsigned byte value
	 * @return Signed integer
	 */
	public static int byteToNumber(byte b) {
		return (int) b & 0xFF;
	}
	
	/**
	 * converts an unsigned byte array (MSB first, max first four bytes) to a
    * (signed) integer.
	 * 
	 * @param b
	 * @return the value representated by the input byte array
	 */
	public static int bytesToNumber(byte[] b) {
		int val = 0;
      int len = b.length;
      if (len > 4) {
         len = 4;
      }
		for (int i = 0; i < len; i++) {
			val |= byteToNumber(b[i]) << (8 * (len - i - 1));
		}
		return val;
	}
	
	/**
	 * creates a byte array out of the given number. The numbering
	 * of the bytes starts from the left to the right in contrary
	 * to usual numbering e.g. 
	 * <code>n=0134A0FF002EC0B1</code> returns the array
	 * <code>{ 01,34,A0,FF,00,2E,C0,B1 }</code>
	 * 
	 * @param n the number
	 * 
	 * @return the byte array representing the number.
	 */
	public static byte[] numberToByteArray(long n) 
	{
		byte[] ba = new byte[8];
		byte b;

		// go through all bytes and filter them out
		for (int s = 0; s < 8; s++) {
			b = (byte) ((n >> ((8 - 1 - s) * 8)) & 0xFF);
			ba[s] = b;
		}
		
		return ba;
	}

	
	/**
	 * creates a byte array out of the given number. The numbering
	 * of the bytes starts from the left to the right in contrary
	 * to usual numbering.
	 * 
	 * @param n the number
	 * 
	 * @return the byte array representing the number.
	 */
	public static byte[] numberToByteArray(int n) 
	{
		byte[] ba = new byte[4];
		byte b;

		// go through all bytes and filter them out
		for (int s = 0; s < 4; s++) {
			b = (byte) ((n >> ((4 - 1 - s) * 8)) & 0xFF);
			ba[s] = b;
		}
		
		return ba;
	}

	
	/**
	 * creates a byte array out of the given number. The numbering
	 * of the bytes starts from the left to the right in contrary
	 * to usual numbering.
	 * 
	 * @param n the number
	 * 
	 * @return the byte array representing the number.
	 */
	public static byte[] numberToByteArray(short n) {
		byte[] ba = new byte[2];
		byte b;

		// go through all bytes and filter them out
		for (int s = 0; s < 2; s++) {
			b = (byte) ((n >> ((2 - 1 - s) * 8)) & 0xFF);
			ba[s] = b;
		}
		
		return ba;
	}


	/**
	 * checks if two given byte block regions are equal or not.
	 * 
	 * @param firstArray the first byte array.
	 * @param firstOffset the offset of the first block in the array.
	 * @param secondArray the second byte array.
	 * @param secondOffset the offset of the second block in the array.
	 * @param length the length of the blocks in the arrays.
	 * 
	 * @return true, if both byte block regions are equal.
	 */
	public static boolean equalsRegion(
				byte[] firstArray, int firstOffset,
				byte[] secondArray, int secondOffset, 
				int length) 
	{
		int firstIndex = firstOffset;
		int secondIndex = secondOffset;
		
		//-- compare byte blocks in arrays
		while (firstIndex < firstArray.length
				&& secondIndex < secondArray.length
				&& firstIndex < firstOffset + length
				&& secondIndex < secondOffset + length
				&& firstArray[firstIndex] == secondArray[secondIndex]) {
			firstIndex++;
			secondIndex++;
		}

		//-- check if end reached, i.e. blocks are equal
		if (firstIndex == firstOffset + length
			&& secondIndex == secondOffset + length) 
			return true;
		else
			return false;
	}

	/**
	 * checks if two given byte regions are equal or not.
	 * 
	 * @param firstArray the first byte array.
	 * @param firstOffset the offset of the first block in the array.
	 * @param secondArray the second byte array.
	 * 
	 * @return true, if both byte block regions are equal.
	 */
	public static boolean equalsRegion(
				byte[] firstArray, int firstOffset,
				byte[] secondArray) 
	{
		return equalsRegion(firstArray, firstOffset, secondArray, 0, secondArray.length);
	}


	/**
	 * compares two given byte block regions. 
	 * 
	 * @param firstArray the first byte array.
	 * @param firstOffset the offset of the first block in the array.
	 * @param secondArray the second byte array.
	 * @param secondOffset the offset of the second block in the array.
	 * @param length the length of the blocks in the arrays.
	 * 
	 * @return -1, if first block < second block,
	 * 			 0, if first block == second block,
	 * 			 1, if first block > second block.
	 */
	public static int compareRegion(
				byte[] firstArray, int firstOffset,
				byte[] secondArray, int secondOffset, 
				int length) 
	{
		int firstIndex = firstOffset;
		int secondIndex = secondOffset;
		
		//-- compare byte blocks in arrays
		while (firstIndex < firstArray.length
				&& secondIndex < secondArray.length
				&& firstIndex < firstOffset + length
				&& secondIndex < secondOffset + length) {
			int res = compare(firstArray[firstIndex], secondArray[secondIndex]);
			if (res != 0)
				return res;
			
			firstIndex++;
			secondIndex++;
		}
		
		// end of blocks reached => blocks equal
		return 0;
	}


	/**
	 * compares two bytes which are interpreted as unsigned bytes.
	 * 
	 * @param first the first byte.
	 * @param second the second byte.
	 * @return -1, if first < second,
	 * 			 0, if first == second,
	 * 			 1, if first > second 
	 */
	public static int compare(byte first, byte second)
	{
		int f = first & 0x000000FF;
		int s = second & 0x000000FF;
		
		if (f < s) 
			return -1;
		else if (f == s)
			return 0;
		else
			return 1;
	}
	

	/**
	 * returns a byte array containing the region of the source byte array. 
	 * 
	 * @param array the byte array.
	 * @param offset the offset of the region in the byte array.
	 * @param length the length of the region in the byte array.
	 * 
	 * @return a byte array containing the region of the source byte array.
	 */
	public static byte[] getRegion(byte[] array, int offset, int length) 
	{
		byte[] region = new byte[length];
		
		// copy region into new byte array
		for (int i = 0; i < length; i++)
			region[i] = array[i + offset];

		return region;
	}

	/**
	 * replaces the region of the first byte array by the 
	 * bytes of the second byte array. 
	 * 
	 * @param firstArray the first byte array.
	 * @param offset the offset of the region in the first array.
	 * @param secondArray the second byte array.
	 */
	public static void replaceRegion(byte[] firstArray, int offset, 
								byte[] secondArray) 
	{
		// copy region into byte array
		for (int i = 0; i < secondArray.length; i++)
			firstArray[i + offset] = secondArray[i];
	}


	/**
	 * replaces the region of a first byte array by the region 
	 * of the second byte array. 
	 * 
	 * @param firstArray the first byte array.
	 * @param firstOffset the offset of the region in the first array.
	 * @param secondArray the second byte array.
	 * @param secondOffset the offset of the region in the second array.
	 * @param length the length of the region in the arrays.
	 */
	public static void replaceRegion(
				byte[] firstArray, int firstOffset,
				byte[] secondArray, int secondOffset, int length)
	{
		// copy region into byte array
		for (int i = 0; i < length; i++)
			firstArray[firstOffset + i] = secondArray[secondOffset + i];
	}
	
	/**
	 * transforms any String into a byte array using UTF8 encoding.
	 * 
	 * @param string the string that has to be converted.
	 * 
	 * @return the converted string as a byte array.
	 */
	public static byte[] stringConverter(String string){
		byte[]defaultBytes=null;
		byte[]utf8Bytes=null;
		
		System.out.println(System.getProperty("file.encoding"));
		String original = new String(string);
   
		System.out.println("original = " + original);
		System.out.println();
   
		try {
			utf8Bytes = original.getBytes("UTF8");
			defaultBytes = original.getBytes();
 
			String roundTrip = new String(utf8Bytes, "UTF8");
			System.out.println("roundTrip = " + roundTrip);
 
			System.out.println();
			
			System.out.println();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return defaultBytes;
	}
}
