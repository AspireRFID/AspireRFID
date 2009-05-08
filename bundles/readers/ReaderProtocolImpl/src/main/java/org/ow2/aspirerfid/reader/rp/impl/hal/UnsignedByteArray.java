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

package org.ow2.aspirerfid.reader.rp.impl.hal;

import java.io.UnsupportedEncodingException;

/**
 * UnsignedByteArray represents an array of unsigned bytes of arbitrary length.
 * 
 * Since Java does not support unsigned byte arrays this class can be used to 
 * send or receive bytes from hardware devices such as RFID readers.
 * 
 * In addition, the class provides several static byte array support functions,
 * which allow the usage of UnsignedByteArray without creating objects of the class.
 * 
 * @author 	Matthias Lampe, lampe@acm.org
 */
public class UnsignedByteArray {
	
	//---- Fields -----------------------------------------------------------

	/** 
	 * The internal representation of the UnsignedByteArray. Each byte in the byte
	 * array is interpreted as unsigned during operations on the array. 
	 */
	protected byte[] bytes;


	//---- Constructor(s) ---------------------------------------------------

	/**
	 * creates an UnsignedByteArray from the given Java byte array. The bytes in the given
	 * byte array are interpreted as unsigned bytes.
	 *
	 * @param bytes the byte array, which is interpreted as unsigned bytes
	 */
	public UnsignedByteArray(byte[] bytes) {
		// copy source to internal byte array
		this.bytes = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++)
			this.bytes[i] = bytes[i];
	}

//	/**
//	 * creates an UnsignedByteArray from the given array of shorts.
//	 * Each short is interpreted as an unsiged byte and is only allowed
//	 * to have values between 0 and 255.
//	 *
//	 * @param bytes the array of shorts, which is interpreted as unsigned bytes
//	 */
//	public UnsignedByteArray(short[] bytes) {
//		// copy source to internal byte array
//		this.bytes = new byte[bytes.length];
//		for (int i = 0; i < bytes.length; i++)
//			// TODO: add correct conversion!
//			this.bytes[i] = (byte) bytes[i];
//	}

	/**
	 * creates an UnsignedByteArray from a String representation.
	 *
	 * @param bytes the bytes in hex format as a String
	 */
	public UnsignedByteArray(String bytes) {
		this.bytes = hexStringToByteArray(bytes);
	}


	//---- Methods ----------------------------------------------------------

	/**
	 * returns a reference to the internal byte array.
	 *
	 * @return the internal byte array.
	 */
	protected byte[] getInternalByteArray() {
		return bytes;
	}


	/**
	 * gets the UnsignedByteArray as a Java byte array to be 
	 * interpreted as unsigned bytes.
	 *
	 * @return a byte array representing the UnsignedByteArray.
	 */
	public byte[] toByteArray() {
		// copy source to internal byte array
		byte[] ba = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++)
			ba[i] = bytes[i];
			
		return ba;
	}


	/**
	 * gets the UnsignedByteArray as a hex number String representation.
	 *
	 * @return the UnsignedByteArray as a hex number String representation.
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

		// clone UnsignedByteArray
		UnsignedByteArray bbCopy = new UnsignedByteArray(copy);

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
		if (o instanceof UnsignedByteArray)
			return ((UnsignedByteArray)o).bytes.equals(this.bytes);
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
	 * @param byteArray the array of bytes interpreted as unsigned bytes.
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
	 * converts a String of hex bytes to a byte array. The hex bytes are interpreted
	 * as unsigned bytes.
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
	
	
	// TODO: change example to show a correct Java byte array. i.e. FF is not allowed.
	/**
	 * creates a byte array out of the given long number. The numbering
	 * of the bytes starts from the left to the right in contrary
	 * to usual numbering e.g. 
	 * <code>n=0134A0FF002EC0B1</code> returns the array
	 * <code>{ 01,34,A0,FF,00,2E,C0,B1 }</code>. The bytes in the array 
	 * should be interpreted as unsigned bytes. 
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
	 * creates a byte array out of the given int number. The numbering
	 * of the bytes starts from the left to the right in contrary
	 * to usual numbering. The bytes in the array should be interpreted
	 * as unsigned bytes. 
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
	 * creates a byte array out of the given short number. The numbering
	 * of the bytes starts from the left to the right in contrary
	 * to usual numbering. The bytes in the array should be interpreted
	 * as unsigned bytes.
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
	 * checks if two given UnsignedByteArray regions are equal or not.
	 * 
	 * @param firstArray the first UnsignedByteArray.
	 * @param firstOffset the offset of the first region in the array.
	 * @param secondArray the second UnsignedByteArray.
	 * @param secondOffset the offset of the second region in the array.
	 * @param length the length of the regions in the arrays.
	 * 
	 * @return true, if both UnsignedByteArray regions are equal.
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
	 * checks if two given UnsignedByteArray regions are equal or not.
	 * 
	 * @param firstArray the first UnsignedByteArray.
	 * @param firstOffset the offset of the first region in the array.
	 * @param secondArray the second UnsignedByteArray.
	 * 
	 * @return true, if both UnsignedByteArray regions are equal.
	 */
	public static boolean equalsRegion(
				byte[] firstArray, int firstOffset,
				byte[] secondArray) 
	{
		return equalsRegion(firstArray, firstOffset, secondArray, 0, secondArray.length);
	}


	/**
	 * compares two given UnsignedByteArray regions. 
	 * 
	 * @param firstArray the first UnsignedByteArray.
	 * @param firstOffset the offset of the first region in the array.
	 * @param secondArray the second UnsignedByteArray.
	 * @param secondOffset the offset of the second region in the array.
	 * @param length the length of the regions in the arrays.
	 * 
	 * @return -1, if first region < second region,
	 * 			 0, if first region == second region,
	 * 			 1, if first region > second region.
	 */
	public static int compareRegion(
				byte[] firstArray, int firstOffset,
				byte[] secondArray, int secondOffset, 
				int length) 
	{
		int firstIndex = firstOffset;
		int secondIndex = secondOffset;
		
		//-- compare byte regions in arrays
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
