/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package org.ow2.aspirerfid.ale.codec;

public abstract class EpcCodec {
	/*---------------------------------------------------------*/
	/**
	 * Header values of an SGTIN_96.
	 */
	public final static byte SGTIN_96_HEADER = 0x30; //binary 0011.0000
	
	/**
	 * Header of an SGTLN96
	 */
	public final static byte SGLN_96_HEADER = 0x32; // 0011.0010
	
	/*---------------------------------------------------------*/
	
	/*
	 * Constants
	 */
	protected final static boolean MSB_FIRST = true;
	protected final static boolean MSB_LAST = false;
	
	protected final static String COMMON_HEADER = "urn:epc:";

	/*---------------------------------------------------------*/
	/**
	 * This look-up table gives the mask to use for getting from 1 up to 7 Most 
	 * significant bits of a byte
	 */	
	protected final static byte[] boundaryLeftMasks = { 
		(byte) 0x80 /* 0b1000.0000 */,
		(byte) 0xC0 /* 0b1100.0000 */,
		(byte) 0xE0 /* 0b1110.0000 */,
		(byte) 0xF0 /* 0b1111.0000 */,
		(byte) 0xF8 /* 0b1111.1000 */,
		(byte) 0xFC /* 0b1111.1100 */,
		(byte) 0xFE /* 0b1111.1110 */
		};
	
	/**
	 * This look-up table gives the mask to use for getting from 1 up to 7 Least 
	 * significant bits of a byte
	 */	
	protected final static byte[] boundaryRightMasks = { 
		(byte) 0x01 /* 0b0000.0001 */,
		(byte) 0x03 /* 0b0000.0011 */,
		(byte) 0x07 /* 0b0000.0111 */,
		(byte) 0x0F /* 0b0000.1111 */,
		(byte) 0x1F /* 0b0001.1111 */,
		(byte) 0x3F /* 0b0011.1111 */,
		(byte) 0x7F /* 0b0111.1111 */
		};
	
	/**
	 * This look-up table gives the shift to use for getting 1 to 7 Most significant
	 * bits of a bytes
	 */
	protected final static byte[] boundaryShifts = { 
		7 /* 0bX000.0000 */,
		6 /* 0bXX00.0000 */,
		5 /* 0bXXX0.0000 */,
		4 /* 0bXXXX.0000 */,
		3 /* 0bXXXX.X000 */,
		2 /* 0bXXXX.XX00 */,
		1 /* 0bXXXX.XXX0 */
		};
	
	/*---------------------------------------------------------*/
	
	/**
	 * Validates and decodes header.
	 * @param tagData tag data bytes.
	 * @return the header byte or 0xFF if unknown header
	 */
	public static byte decodeHeader (byte[] tagData) {
		byte header = tagData[tagData.length-1];
			
		switch (header) {
			case SGTIN_96_HEADER : break;
			case SGLN_96_HEADER : break;
			default :
				// invalid header
				header = (byte)0xFF;
		}
		
		return header;
	}
	
	/**
	 * Validates and decodes header.
	 * @param tagData tag data bytes.
	 * @return the header byte
	 */
	public static EncScheme getEncodingScheme (byte[] tagData) {
		EncScheme ret ;
		byte header = tagData[tagData.length-1];
			
		switch (header) {
			case SGTIN_96_HEADER : 
				ret = EncScheme.SGTIN_96;
				break;
			case SGLN_96_HEADER : 
				ret = EncScheme.SGLN_96;
				break;
			default :
				throw new IllegalArgumentException("invalid header " + header);
		}
		
		return ret;
	}
	
	/**
	 * Decode filter Value. The 3 bits following the header(Last Byte).
	 * @param tagData tag data bytes.
	 * @return the filter value
	 */
	protected static byte getFilterValue (byte[] tagData) {
		return (byte) ((tagData[tagData.length-2] & 0xF0)>>>5);
	}
	
	/**
	 * Decode Partition Number. The 3 bits following the filter Value
	 * @param tagData tag data bytes.
	 * @return the filter value
	 */
	protected static byte getPartition (byte[] tagData) {
		return (byte) ((tagData[tagData.length-2] & 0x1F)>>>2);
	}
	
	/**
	 * Utility for decoding company prefix.
	 * @param tagData tag data bytes.
	 * @param M Bits of company prefix.
	 * @param L Digits of company prefix. 
	 * @return the company prefix
	 */
	protected static long getCompanyPrefix (byte[] tagData, 
										 byte M,
										 byte L) 
	{	
		int from, to;
		byte leadingBits, trailingBits;
			
		// Compute trailing and leading bits
		leadingBits = 2 ;
		trailingBits = (byte) ((M - leadingBits)%8);
		// Start at the first two bits of the Byte after the header 
		to = tagData.length-2;
		from = to - (int)Math.ceil(M/8.0);
		
		return extractLongField (tagData, from, trailingBits, to, leadingBits);
	}
	
	/**
	 * Utility for decoding company an item reference.
	 * @param tagData tag data bytes.
	 * @param M Bits of company prefix.
	 * @param N Bits of item reference
	 * @return the item reference.
	 */
	protected static int getItemReference (byte[] tagData, 
										byte M,
										byte N)
	{
		
		int from, to;
		byte leadingBits, trailingBits;
			
		// Compute trailing and leading bits
		leadingBits = (byte)(8 - ((M - 2) % 8));
		trailingBits = (byte) ( (M-2 + N) % 8);
		
		// Start at the first two bits of the Byte after the header 
		to = tagData.length-2 - (int)Math.ceil((M/8.0));
		from = to - ((N<=8) ? 1 : (int)Math.floor(N/8.0));
		
		return (int)extractLongField (tagData, from, trailingBits, to, leadingBits);
	}
	
	/**
	 * Utility for decoding company a Serial number.
	 * @param tagData tag data bytes.
	 * @param M Bits of company prefix.
	 * @param N Bits of item reference
	 * @return the serial number.
	 */
	protected static long getSerialNumber (byte[] tagData, 
										byte M,
										byte N)
	{
		int from, to;
		byte leadingBits, trailingBits, S /* serial's number of bit */;
		
		// Compute leading bits
		S = (byte) (8*tagData.length -(8+3+3 + M + N));
		to = (int) Math.floor(S/8.0);
		leadingBits = (byte)( 8 - ((M + N - 2) % 8));
		
		// Compute trailing bits
		trailingBits = 8; // all trailing bits of first byte i.e whole first byte
		from = 0; // start at first byte
		
		return extractLongField (tagData, from, trailingBits, to, leadingBits);
	}
	
	/*---------------------------------------------------------*/
	
	/**
	 * Extracts a long (64-bit max) value from an array of bytes. The latter is arranged 
	 * in LSB first.
	 * 
	 * @param bytes array of bytes containing data
	 * @param from index of the 'first' <code>byte</code> to use
	 * @param trailingBits number of most significant bits to take. Range : 1..8.
	 * @param to index of the 'last' <code>byte</code> to use
	 * @param leadingBits number of least significant bits to take in 'last' byte. Range : 1..8.
	 * @return the long number.
	 */
	protected static long extractLongField (byte[] bytes,
									   int from,
									   byte trailingBits, /* in {1,8} */
									   int to,
									   byte leadingBits /* in {1,8} */) 
	{
		long data = 0;		/* return value */
		byte completeBytes 	/* number of complete bytes */;
		byte[] temp;		/* temporary buffer */
		byte shift = 0;		/* shift value for conversion */
		long mask;			/* mask value for conversion */
	
		// TODO Validate parameters
				
		// compute trailing bits(s) : if the first byte is not completely selected
		if (trailingBits != 8) {
			// compute mask and shift
			mask = byteToLong(boundaryLeftMasks[trailingBits -1]);
			shift = boundaryShifts[trailingBits -1];
			// update data
			data |= ((byteToLong(bytes[from]) & mask)>>> shift);
			// go further : drop first byte and shift trailing bits
			from++; // (on stack effect)
			shift = trailingBits;
		}		
	
		// compute leading bit(s) : if the last byte is not completely selected
		if (leadingBits != 8) {
			// compute mask and shift : shift the eventual trailing bits and middle bytes
			mask = byteToLong(boundaryRightMasks[leadingBits -1]);
			shift += (byte) 8*(to - from); 
			// update data
			data |= ((byteToLong(bytes[to]) & mask)<< shift);
			// go further : drop last byte with trailing bits
			to--;  // (on stack effect)
		}

		// compute middle bytes : complete bytes are copied to a temporary buffer
		// then converted to long
		completeBytes = (byte) ((to - from)  + 1);
		if(completeBytes != 0) {
			temp = new byte[completeBytes];
			for (int i = 0, j= from ; i < completeBytes ; i++, j++)
				temp[i] = bytes[j];
			
			// Convert to long and update data
			shift = (trailingBits != 8) ? trailingBits : 0 ;
			data |= (ByteArrayToLong(temp, MSB_LAST)<<shift);
		}
		
		return data;
	}
	
	/**
	 * General purpose utility for converting an array of 4 bytes to an integer.
	 * Note : the MSB is first byte.
	 */
	protected static int ByteArrayToInt (byte[] bytes, boolean msbFirst) {
		int ret;
		
		if (msbFirst) {
			// MSB is first
			ret = byteToInt(bytes[bytes.length-1]); //LSB                                 
			for (int i = bytes.length-2, shift = 8; (i>0)&&(i >=(bytes.length-4)) ; i--, shift+=8) {
				ret |= (byteToInt(bytes[i])<< shift);
			}
		} else {
			// MSB is last
			ret =  byteToInt(bytes[0]); //LSB                                                  
			for (int i = 1, shift = 8; (i < bytes.length)&&(i<8) ; i++, shift+=8) 
				ret |= (byteToInt(bytes[i])<< shift);
		}
		return ret;                                                        
	}
	
	/**
	 * General purpose utility for converting an array of 8 bytes to a long.
	 * Note : the MSB is first byte.
	 */
	protected static long ByteArrayToLong (byte[] bytes, boolean msbFirst) {
		long ret ;
		
		if (msbFirst) {
			// MSB is first
			ret = byteToLong(bytes[bytes.length-1]); //LSB                                 
			for (int i = bytes.length-2, shift = 8; (i>0)&&(i >=(bytes.length-8))  ; i--, shift+=8) 
				ret |= (byteToLong(bytes[i])<< shift);
		} else {
			// MSB is last
			ret = byteToLong(bytes[0]); //LSB                                                 
			for (int i = 1, shift = 8; (i < bytes.length)&&(i<8) ; i++, shift+=8)
				ret |= (byteToLong(bytes[i])<< shift);
		}
		                                                                   
		return ret;     
	}

	/**
	 * Safe byte to long conversion : without bit sign problem ;).
	 */
	protected static long byteToLong(byte data) {
		return (long) ( ((long)data) & 0x000000FF ); // force leading bytes to 0
	}
	
	/**
	 * Safe byte to int conversion :  without bit sign problem ;).
	 */
	protected static int byteToInt(byte data) {
		return (int) ( ((int)data) & 0x00FF );  // force leading bytes to 0
	}
	
	/**
	 * Utility for facilitating the construction of an URI. 
	 * It appends a data represented on a given number of digits to a string buffer, 
	 * by adding leading zeros if necessary.
	 * @param uri String buffer to complete
	 * @param data data to append
	 * @param digits maximum digits for data
	 * @throws IllegalArgumentException if data is greater than 10^digits
	 */
	protected static void appendUri(StringBuffer uri, long data, byte digits) {
		int zeros;
		double max = Math.pow(10, digits);
		
		if (data > max)
			throw new IllegalArgumentException("data = " + data + ", is greater than " + max);
			
		zeros = digits - Long.toString(data).length();
		for (int i = 0 ; i < zeros ; i++)
			uri.append('0');
		uri.append(data);
	}
	
	protected static String convertToRawDecUri(byte[] tagData) 
	{
		StringBuffer uri ;
		int size, bitLength;
		
		/* build header */
		uri = new StringBuffer(COMMON_HEADER);
		uri.append("raw:");
		
		/* build payload */
		// Bit Length
		size = tagData.length;
		bitLength = size*8; 
		uri.append(bitLength);
		uri.append(".");
		// Data : little endian, notice : tagData big endian
		for (int i = size-1 ; i >=0; i--)
			uri.append(tagData[i]&0x00FF);
		
		return uri.toString();
	}

	public static String convertToRawHexUri(byte[] tagData) {
		StringBuffer uri ;
		int size, bitLength;
		
		/* build header */
		uri = new StringBuffer(COMMON_HEADER);
		uri.append("raw:");
		
		/* build payload */
		// Bit Length
		size = tagData.length;
		bitLength = size*8; 
		uri.append(bitLength);
		uri.append(".x");
		// Data : little endian, notice : tagData big endian
		for (int i = size-1 ; i >=0; i--) {
			//uri.append(Integer.toHexString(tagData[i]&0x00FF).toUpperCase());
			short aByte = (short) (tagData[i]&0xFF); // convert to short for bit sign workaround   			
			uri.append( ((aByte < 16) ? "0":"")).append(Integer.toHexString(aByte).toUpperCase());
		}
		
		
		return uri.toString();
	}
	
	/*---------------------------------------------------------*/
	// Abstract methods
	public abstract String getPureUri() ;
	public abstract String getTagUri() ;
	
	/*---------------------------------------------------------*/
	/*
	 * Test Case
	 */
	public static void main(String[] args) {
		byte[] data = {(byte) 0xFF, 0x50, 0x00, 0x18, (byte) 0xFF, 0x70, (byte) 0xF1, 0x45};
		
		System.out.println("Data (dec) : " + ByteArrayToInt(data, true));
		System.out.println("Data (hex) : " + Integer.toHexString(ByteArrayToInt(data, true)));
		
		System.out.println("\nData (dec) : " + ByteArrayToLong(data, true));
		System.out.println("Data (hex) : " + Long.toHexString(ByteArrayToLong(data, true)));
		
		System.out.println("\nData (dec) : " + ByteArrayToInt(data, false));
		System.out.println("Data (hex) : " + Integer.toHexString(ByteArrayToInt(data, false)));
		
		System.out.println("\nData (dec) : " + ByteArrayToLong(data, false));
		System.out.println("Data (hex) : " + Long.toHexString(ByteArrayToLong(data, false)));
		
		System.out.println(convertToRawHexUri(data));
	}
}
