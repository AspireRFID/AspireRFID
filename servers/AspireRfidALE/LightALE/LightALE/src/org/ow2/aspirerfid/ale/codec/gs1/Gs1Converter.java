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

package org.ow2.aspirerfid.ale.codec.gs1;

import java.util.StringTokenizer;

import org.ow2.aspirerfid.ale.codec.EncScheme;
import org.ow2.aspirerfid.ale.codec.EpcCodec;
import org.ow2.aspirerfid.ale.codec.PartitionTable;
import org.ow2.aspirerfid.ale.codec.PartitionTables;


/**
 * This class exports conversion utilities for gs1 number.
 *
 */
public final class Gs1Converter {
	
	/**
	 * Powers of ten from 10^0 to 10^18 (Look up table).
	 */
	private final static long[] tenPowers = {1, 10, 100, 1000, 10000, 100000, 1000000, 
											 10000000, 100000000, 1000000000, 10000000000L, 
											 100000000000L, 1000000000000L, 10000000000000L, 
											 100000000000000L, 1000000000000000L, 10000000000000000L,
											 100000000000000000L, 1000000000000000000L};
	/**
	 * Digits Table.
	 */
	private final static char [] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	/**
	 * String buffer for building string representation.
	 */
	private static StringBuffer sBuffer = new StringBuffer(14);
	
	/**
	 * Reference to sgtin  partition table singleton.
	 */
	private static PartitionTable sgtinPartition = PartitionTables.getPartitionTable(PartitionTables.SGTIN_TABLE);
	
	/**
	 * Reference to sgtin  partition table singleton.
	 */
	private static PartitionTable sglnPartition = PartitionTables.getPartitionTable(PartitionTables.SGLN_TABLE);
	
	/**
	 * 
	 * @param header see {@link EpcCodec#SGTIN_96_HEADER} and {@link EpcCodec#SGLN_96_HEADER}
	 * @param companyPrefix
	 * @param reference
	 * @param partition
	 * @return 14-digit gs1 number.
	 */
	public final static long computeGS1(byte header, long companyPrefix, int reference, byte partition) {
		int cpDigits, refDigits;
		long gs1 = -1 ;
		
		switch (header) {
			case EpcCodec.SGTIN_96_HEADER :
				cpDigits = sgtinPartition.getDigits(partition, PartitionTables.SGTIN_COMPANY_PREFIX);
				refDigits = sgtinPartition.getDigits(partition, PartitionTables.SGTIN_INDIC_DIGIT_ITEM_REFERENCE);
				//gs1 = computeGS1(14, companyPrefix, reference, cpDigits, refDigits);
				gs1 = computeGtin(companyPrefix, reference, cpDigits, refDigits);
				break;
				
			case EpcCodec.SGLN_96_HEADER :
				cpDigits = sglnPartition.getDigits(partition, PartitionTables.SGLN_COMPANY_PREFIX);
				refDigits = sglnPartition.getDigits(partition, PartitionTables.SGLN_LOCATION_REFERENCE);
				//gs1 = computeGS1(13, companyPrefix, reference, cpDigits, refDigits);
				gs1 = computeGln(companyPrefix, reference, cpDigits, refDigits);
				break;
			
			default :
				; // no action
		}
		
		return gs1;
	}
	
	/**
	 * Computes a GS1 number from an uri representation in epc-tag format.
	 * @param uri sgtin-96 or sgln-96 uri representation in epc-tag format.
	 *  Examples : "urn:epc:tag:sgtin-96:1.358378.0142310.1", "urn:epc:tag:sgln-96:1.211298.070875.43981".
	 * @return gs1 number
	 */
	public final static long computeGS1(String tagUri) {
		int firstSeparator = tagUri.indexOf("tag") + 4;
		int lastSeparator = tagUri.lastIndexOf(":");
		String type = tagUri.substring(firstSeparator, lastSeparator);	
		StringTokenizer tokenizer = new StringTokenizer(tagUri.substring(lastSeparator+1),".");
			
		// decode representation
		if (true)
			tokenizer.nextToken();
		
		// decode type
		byte header;
		if (type.equals(EncScheme.SGTIN_96.toString())) 
			header = EpcCodec.SGTIN_96_HEADER;
		else if (type.equals(EncScheme.SGLN_96.toString())) 
			header = EpcCodec.SGLN_96_HEADER;
		else
			throw new IllegalArgumentException("unsupported type" + type);
		
		
		// get company prefix and reference
		String cPrefix, ref;
		cPrefix = tokenizer.nextToken();
		ref = tokenizer.nextToken();
		
		// compute gs1 number 
		// TODO compute partition
		return computeGS1(header, Long.parseLong(cPrefix), Integer.parseInt(ref), (byte) 6 /* partition*/);
	}
	
	/**
	 * Computes a GS1 number from an uri representation in epc-tag format.
	 * @param uri sgtin-96 or sgln-96 uri representation in epc-pure or epc-tag format.
	 *  Examples : "urn:epc:tag:sgtin-96:1.358378.0142310.1", "urn:epc:tag:sgln-96:1.211298.070875.43981".
	 * @return gs1 number
	 */
	public final static Gs1Number decodeTagUri(String tagUri) {
		int firstSeparator = tagUri.indexOf("tag") + 4;
		int lastSeparator = tagUri.lastIndexOf(":");
		String type = tagUri.substring(firstSeparator, lastSeparator);	
		StringTokenizer tokenizer = new StringTokenizer(tagUri.substring(lastSeparator+1),".");
			
		// decode representation
		if (true)
			tokenizer.nextToken();
		
		// decode type
		if (!type.equals(EncScheme.SGTIN_96.toString()) && 
			!type.equals(EncScheme.SGLN_96.toString()))
			throw new IllegalArgumentException("unsupported type" + type);
		
		// get company prefix and reference
		String cPrefix, ref;
		cPrefix = tokenizer.nextToken();
		ref = tokenizer.nextToken();
		// return it 
		return new Gs1Number(Long.parseLong(cPrefix), Integer.parseInt(ref));
	}
	
	private final static long computeGtin(long companyPrefix, int reference, int cpDigits, int refDigits) {
		int i,j ; // loop indexes
		long gtin = 0 ; // d_1|d_2...d_L+1|d_L+2..d13d14
				
		// d1
		gtin = tenPowers[13]*getDigits(reference, refDigits, 1, 1);
		
		// d_2...d_L+1 = p1p2...pL
		for (i = 2, j = 1 ; i <=(cpDigits+1); i++, j++)
			gtin += tenPowers[14-i]*getDigits(companyPrefix, cpDigits, j, j);
		
		// d_L+2..d13 = i2i3...i13-L
		for (j = 2; i <14; i++, j++)
			gtin += tenPowers[14-i]*getDigits(reference, refDigits, j, j);
		
		// d14 : check digit
		gtin += computeCheckDigit(gtin/10, 13) ;
			
		return gtin;
	}
	
	private final static long computeGln(long companyPrefix, int reference, int cpDigits, int refDigits) {
		int i,j ; // loop indexes
		long gln = 0 ; // d_1|d_2...d_L+1|d_L+2..d13
		
		// d_1...d_L = p1p2...pL
		for (i = 1, j = 1 ; i <=cpDigits; i++, j++)
			gln += tenPowers[13-i]*getDigits(companyPrefix, cpDigits, j, j);
		
		if (cpDigits <12) {
			// d_L+1..d12 = i1i3...i12-L
			for (j = 1; i <13; i++, j++)
				gln += tenPowers[13-i]*getDigits(reference, refDigits, j, j);
		}
		// d13 : check digit
		gln += computeCheckDigit(gln/10,13) ;
			
		return gln;
	}
	
	/**
	 * Converts a given gs1 number to a 14 digits string with leading zeros if necessary.
	 * @param gs1 14 digits max gs1 number.
	 * @return a string representation of the gs1 number.
	 */
	public final static String toString(int length, long gs1) {
		String ret ;

		try {
			// append digits
			for (int i = 1 ; i <= length ; i++)
				sBuffer.append(digit[(int) getDigits(gs1, length, i, i)]);
			
			// convert to string
			ret = sBuffer.toString();
		}
		finally {
			// clean up
			sBuffer.setLength(0);			
		}
		
		return ret;
	}
	
	public final static int GTIN_LENGTH = 14;
	public final static int GLN_LENGTH = 13;
	
	
	/**
	 * Returns the check digit for a gs1 number.
	 * @param gs1 13 digits "d_1d_2...d13".
	 * @return the check digit.
	 */
	public final static byte computeCheckDigit(long gs1, int length) {
		long check = 0;//,d1, d2;
		int i;
		int stop = length;
		boolean even = true;
		
				
		if ((length %2) != 0) {
			even = false;
			stop = length-1;
		}
		// digits d1 to d12
		for (i = 1; i <= stop; i+=2) {
			//System.out.println("d1 = " + getDigits(gs1, length, i, i) + ", d2 = " + getDigits(gs1, length, i+1, i+1));
			check += (3*getDigits(gs1, length, i, i) + getDigits(gs1, length, i+1, i+1));  
		}
		// last digit if not even
		if (!even) {
			//System.out.println("dlast = " + getDigits(gs1, length, i, i));
			check += (3*getDigits(gs1, length, i, i));
		}
		
		// modulo
		check = (byte) (10 - (check %10));
		
		return (byte)check;
	}

	
	/**
	 * Returns a sub number of a number.
	 * @param n the input number.
	 * @param digits number of digits of the number.
	 * @param start first digit to extract/
	 * @param stop last digit to extract.
	 * @return a sub number of the number
	 */
	private final static long getDigits(long n, int digits, int start, int stop ) {
		return  (n/tenPowers[digits-stop] - ((n/tenPowers[digits-start+1])*tenPowers[stop-start+1]));
	}

	
	/* Test Case */
	public static void main (String[] args) {
		
		long gtin = 6291041500213L;
		//long gtin2 = 66291041500215L;
		long gtin2 = 96291041500216L;
		
		long gln = 2112980708758L;
		long gln2 = 72112980708757L;
				
//		for (int i = 1 ; i <= GLN_LENGTH; i++)
//			System.out.println("digit " + i + " : " + getDigits(gln/10, GLN_LENGTH, i, i));
//		System.exit(0);
		System.out.println("gtin check digit = " + computeCheckDigit(gtin/10, 13));
		System.out.println("gtin2 check digit = " + computeCheckDigit(gtin2/10, 13));
		System.out.println("gln check digit = " + computeCheckDigit(gln/10, 13));
		System.out.println("gln2 check digit = " + computeCheckDigit(gln2/10, 13));
		
		System.out.println("digits 1 to 4 = " + getDigits(gtin, 13, 1,4));
		System.out.println("check digit = " + computeCheckDigit(gtin/10, 13));
		
		System.out.println("check digit = " + computeCheckDigit(21129870875L, 12));
		System.out.println("gln = " + toString(GLN_LENGTH, computeGS1("urn:epc:tag:sgln-96:1.211298.070875.43981")));
		System.out.println("gtin = " + toString(GTIN_LENGTH, computeGS1("urn:epc:tag:sgtin-96:1.358378.0183845.1")));
		
		Gs1Number gs1Number = decodeTagUri("urn:epc:tag:sgtin-96:1.358378.0183845.1");
		System.out.println("gtin = " + gs1Number.companyPrefix + ',' + gs1Number.reference);
	}
}
