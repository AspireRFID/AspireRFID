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

package org.ow2.aspirerfid.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.StringTokenizer;

import pops.ale.codec.EpcCodec;
import pops.ale.codec.Sgln96Codec;
import pops.ale.codec.Sgtin96Codec;

public class Parser {
	
	/**
	 * Parser Input.
	 */
	private BufferedReader input;
	
	/**
	 * Parser Output.
	 */
	private BufferedWriter output;
	
	/**
	 * Constructor
	 * @param input
	 * @param output
	 */
	public Parser(InputStream input, OutputStream output) throws Exception {
		
		// open streams
		this.input = new BufferedReader(new InputStreamReader(input));
		try {
			this.output = new BufferedWriter(new OutputStreamWriter(output));
		}
		catch (Exception e) {
			// close open streams
			this.input.close();
			throw e;
		}		
	}
	
	
	/**
	 * Generates the tags ids according on output to specification on input.
	 * @throws Exception 
	 */
	public void generatePatterns() throws Exception {
		String line;
		String type;
		int times;
		int start, stop ;
		
		// parse input and generate output
		while ((line = input.readLine()) != null) {
			// ignore comments
			if (line.startsWith("#")) continue;
			// ignore empty lines
			if (line.length() == 0) continue;
			
			// decode line : if not comment
			start = line.indexOf(':');
			stop = line.indexOf('/');
			type = line.substring(0, start);
			times = Integer.parseInt(line.substring(stop+1));

				
			// generate output
			output.write("#spec:" + line + "\n");
			if (type.equals("sgtin-96"))
				genSgtin96(line.substring(start+1, stop), times, this.output, new String[4]);
			else if (type.equals("sgln-96"))
				genSgln96(line.substring(start+1, stop), times, this.output, new String[4]);
			else 
				throw new IllegalArgumentException("Unsupported type " + type);
			
			output.flush();
		}	
	}
	
	//  -- Factory Methods -- //

	/**
	 * 
	 * @param tagUri
	 * @param output
	 * @param tokens
	 * @throws Exception 
	 */
	private static void genSgtin96(String tagUri, int times, Writer output, String [] tokens) throws Exception {		
		byte filterValue;
		long companyPrefix;
		int itemReference;
		long serial;
		Long[] range = null, test = null;
		int idx = 0 ;		
		
		/* decode pattern */
		chop(tagUri, '.', tokens);
		
		// handle filter value
		filterValue = Byte.parseByte(tokens[0]);
		
		// handle Company Prefix 
		if ((test = isRange(tokens[1])) == null)
			companyPrefix = Long.parseLong(tokens[1]);
		else {
			idx = 1;
			range = test;
			companyPrefix = range[0].longValue(); // min
		}
		
		// handle Item Reference 
		if ((test = isRange(tokens[2])) == null)
			itemReference = Integer.parseInt(tokens[2]);
		else if (idx != 0)
			throw new IllegalArgumentException("Error on item reference, only one range by pattern is allowed");
		else {
			idx = 2;
			range = test;
			itemReference = range[0].intValue(); // min
		}
		
		// handle Serial Number
		if ((test = isRange(tokens[3])) == null)
			serial = Long.parseLong(tokens[3]);
		else if (idx != 0)
			throw new IllegalArgumentException("Error on Serial Number, only one range by pattern is allowed");
		else {
			idx = 3;
			range = test;
			serial = range[0].longValue(); // min
		}
		
		/* Generate tag ids */
		boolean includeEPC = true;
		for (int i = 0 ; i< times ; i++) {
			switch(idx) {
				// fixed sgtin
				case 0 :
					encodeAppendSgtin96(filterValue, companyPrefix, itemReference, serial, output, includeEPC);
					break;
					
				// variable company prefix
				case 1 :
					for (long cp = companyPrefix; cp <= range[1].longValue(); cp++)
						encodeAppendSgtin96(filterValue, cp, itemReference, serial, output, includeEPC);
					break;
					
				// variable item reference
				case 2 :
					for (int ir = itemReference; ir <= range[1].intValue(); ir++)
						encodeAppendSgtin96(filterValue, companyPrefix, ir, serial, output, includeEPC);
					break;
					
				// variable serial number
				case 3 :
					for (long sr = serial; sr <= range[1].longValue(); sr++)
						encodeAppendSgtin96(filterValue, companyPrefix, itemReference, sr, output, includeEPC);
					break;
			}
			includeEPC = false;
		}
		output.write("\n");
	}
	
	/**
	 * 
	 * @param tagUri
	 * @param output
	 * @param tokens
	 * @throws Exception 
	 */
	private static void genSgln96(String tagUri, int times, Writer output, String [] tokens) throws Exception {		
		byte filterValue;
		long companyPrefix;
		int itemReference;
		long serial;
		Long[] range = null, test = null;
		int idx = 0 ;		
		
		/* decode pattern */
		chop(tagUri, '.', tokens);
		
		// handle filter value
		filterValue = Byte.parseByte(tokens[0]);
		
		// handle Company Prefix 
		if ((test = isRange(tokens[1])) == null)
			companyPrefix = Long.parseLong(tokens[1]);
		else {
			idx = 1;
			range = test;
			companyPrefix = range[0].longValue(); // min
		}
		
		// handle Item Reference 
		if ((test = isRange(tokens[2])) == null)
			itemReference = Integer.parseInt(tokens[2]);
		else if (idx != 0)
			throw new IllegalArgumentException("Error on item reference, only one range by pattern is allowed");
		else {
			idx = 2;
			range = test;
			itemReference = range[0].intValue(); // min
		}
		
		// handle Serial Number
		if ((test = isRange(tokens[3])) == null)
			serial = Long.parseLong(tokens[3]);
		else if (idx != 0)
			throw new IllegalArgumentException("Error on Serial Number, only one range by pattern is allowed");
		else {
			idx = 3;
			range = test;
			serial = range[0].longValue(); // min
		}
		
		/* Generate tag ids */
		boolean includeEPC = true;
		for (int i = 0 ; i< times ; i++) {
			switch(idx) {
				// fixed sgtin
				case 0 :
					encodeAppendSgln96(filterValue, companyPrefix, itemReference, serial, output, includeEPC);
					break;
					
				// variable company prefix
				case 1 :
					for (long cp = companyPrefix; cp <= range[1].longValue(); cp++)
						encodeAppendSgln96(filterValue, cp, itemReference, serial, output, includeEPC);
					break;
					
				// variable item reference
				case 2 :
					for (int ir = itemReference; ir <= range[1].intValue(); ir++)
						encodeAppendSgln96(filterValue, companyPrefix, ir, serial, output, includeEPC);
					break;
					
				// variable serial number
				case 3 :
					for (long sr = serial; sr <= range[1].longValue(); sr++)
						encodeAppendSgln96(filterValue, companyPrefix, itemReference, sr, output, includeEPC);
					break;
			}
			includeEPC = false;
		}
		output.write("\n");		

	}
	
	private static void encodeAppendSgtin96(byte filterValue, long companyPrefix, int itemReference, long serial, 
									 Writer output, boolean includeEPC) throws Exception 
	{
		byte [] id;
		
		if (includeEPC) {
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("#tag:sgtin-96:").append(filterValue).append('.').append(companyPrefix).append('.').append(itemReference).append('.').append(serial);
			output.write(sBuffer.toString());
			output.write("\n");
		}
		
		// build id in Big endian
		id = Sgtin96Codec.buildTagData(EpcCodec.MSB_FIRST, filterValue, companyPrefix, itemReference, serial);
		
		output.write("sgtin-96:");
		byteArrayTohex(output, id);
		output.write("\n");		
	}
	
	private static void encodeAppendSgln96(byte filterValue, long companyPrefix, int itemReference, long serial, 
									 Writer output, boolean includeEPC) throws Exception 
	{
		byte [] id;
		
		if (includeEPC) {
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("#tag:sgln96:").append(filterValue).append('.').append(companyPrefix).append('.').append(itemReference).append('.').append(serial);
			output.write(sBuffer.toString());
			output.write("\n");
		}
		
		// build id in Big endian
		id = Sgln96Codec.buildTagData(EpcCodec.MSB_FIRST, filterValue, companyPrefix, itemReference, serial);
		
		output.write("sgln-96:");
		byteArrayTohex(output, id);
		output.write("\n");	
	}
	
	/**
	 * Tests if afield is a range in the form [Lo-Hi]
	 * @param field to test
	 * @return null if the field is not a range,
	 *  or an array </pre> Long [2] = {Lo,Hi} </pre> otherwise
	 */
	private static Long[] isRange(String field)
	{
		Long[] ret = null ;
		StringTokenizer tokenizer ;
		
		if (field.startsWith("[") && field.endsWith("]")) {
			// build tokenizer from the pattern but without the '[' and ']' chars
			try {
				tokenizer = new StringTokenizer(field.substring(1,field.length()-1), "-");
				if (tokenizer.countTokens() != 0) {
					ret = new Long[2];
					// get Low value
					ret[0] = new Long(tokenizer.nextToken());				
					// get high value
					ret[1] = new Long(tokenizer.nextToken());		
				}
			} catch (Exception e) {
				// No action : return false 
			}
		}		
		return ret;
	}
	
	/**
	 * Chopper function.
	 * @param pattern 
	 * @param separator
	 * @return a list of patterns.
	 */
	private static void chop(String uri, char separator, String[] tokens) {
		char current;
		int stop = uri.length() - 1;
		int i, j, k;
		
		for (i = 0, j = 0, k = 0 ; i<=stop ; i++) {
			current = uri.charAt(i);
			if (current == separator) {
				tokens[j] = uri.substring(k, i);
				k = i+1; 
				j++;
			} else if (i == stop){
				tokens[j] = uri.substring(k, stop+1);
			}
		}
		
	}
	

	private static String byteTohex(byte n) {
		String hex;
		hex = Integer.toHexString(n & 0xFF);
		
		if (hex.length()<2) 
			hex = '0' + hex;
		
		return hex.toUpperCase();
	}
	
	private static void byteArrayTohex(Writer output, byte[] array) throws Exception {
		for (int i = 0 ; i < array.length; i++)
			output.write(byteTohex(array[i]));
		
	}
	
	public static String decomposeBinary(String binary) {
		StringBuffer sBuffer = new StringBuffer();
		int size = binary.length() ;
		int remainder = size%4 ;
		
		for (int i =  size-1; i >=remainder ; i-=4) {
			sBuffer.append(binary.charAt(i));
			sBuffer.append(binary.charAt(i-1));
			sBuffer.append(binary.charAt(i-2));
			sBuffer.append(binary.charAt(i-3));
			if (i >= (remainder+4))
				sBuffer.append('.');
		}
		
		// add remaining and trailing zeros if necessary
		if (remainder != 0) {
			sBuffer.append('.');
			for (int i =  0; i < remainder ; i++)
				sBuffer.append(binary.charAt(i));
			for (int i =  remainder; i < 4 ; i++)
				sBuffer.append('0');
		}
		sBuffer.reverse();
		
		// reverse		
		return sBuffer.toString();
	}
	
	/*
	 * Test Case.
	 */
	public static void main(String[] args) throws Exception{
		
		Parser aParser = new Parser(System.in, System.out);
		aParser.generatePatterns();
		
		//"sgtin-96:1.456782.0123456.[274877900000-274877900099]/1
		//sgtin-96:1.456782.0123456.274877900000/1
		//"sgtin-96:3039BE13807890FF0003E4E0"
		
//		Sgtin96Codec aCodec = new Sgtin96Codec();
//		byte[] temp = Sgtin96Codec.stringToArrayOfBytes("3039BE138078903FFFFFE4E0");
//		aCodec.encodeTagData((byte)1, 456782, 123456, 274877900000L);
//		System.out.println("epc-tag: " + aCodec.getTagUri());
//		System.out.println("epc-raw: " + aCodec.getRawHexUri());
//		System.out.println("supp epc-raw: 3039BE138078903FFFFFE4E0");
		
		
	}
		
}
