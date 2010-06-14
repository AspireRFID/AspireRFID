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

package org.ow2.aspirerfid.ale.epc.spec;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.ow2.aspirerfid.ale.epc.exceptions.ECSpecValidationException;



/**
 * An ECFieldSpec encodes a fieldspec.
 * Reminder of the TAG memory map : 
 * <br>
 * <H1 ALIGN=center><img src="doc-files/LogicalMemoryMap.png"> </H1>
 * <br>
 * 
 * Note : The ECFieldSpec type is used in many places within the ALE Reading API and
 * ALE Writing API.
 *
 * @author pops
 * @author R.DAGHER
 */
public class ECFieldSpec {
	/**
	 * Specifies the fieldname; that is, which field of the Tag to operate upon.
	 * When used in an ECReportOutputFieldSpec, may be a &quot;pattern&quot; fieldname that 
	 * specifies zero or more fields matching the pattern.<br>
	 * Possible values :
	 * <ul>
	 * <li>Built-in fieldnames:</li>
	 *   <ul>
	 *     <li>epcBank, tidBank, userBank</li>
	 *     <li>accessPwd, killPwd</li>
	 *     <li>afi, nsi</li>
	 *     <li>epc</li>
	 *   </ul>
	 * <li> Absolute Address fieldnames:
	 *   <ul>
	 *     <li> @bank.length[.offset]</li>
	 *     <li> E.g: @1.8.16 – 8-bit field starting at bit 10h in Bank 1</li>
	 *     <li> ISO 15962 dataset fieldnames:</li>
	 *     <li> @bank.oid</li>
	 *     <li> E.g. @3.urn:oid:1.0.15961.12.11</li>
	 *   </ul>
	 * <li> Symbolic names </li>
	 *   <ul>
	 *     <li> Defined by client using the Tag Memory API </li>
	 *     <li> Alias for Absolute Address or ISO 15962 dataset fieldname </li>
	 *   </ul>
	 * </ul>
	 */
	private String fieldname ;
	
	/**
	 * (Optional) Specifies what kind of data values the field holds, and how they are 
	 * encoded into Tag memory. <br>
	 * If omitted, the ALE implementation SHALL behave as though the default datatype 
	 * associated with fieldname were specified instead.<br>
	 * Built-in datatypes:
	 * <ul>
	 *   <li>"epc"</li>
	 *   <li>"unsigned integer"</li>
	 *   <li>"iso 15962-encoded string"</li>
	 * </ul>
	 */
	private String datatype ;
	
	/**
	 * (Optional) Specifies the syntax used to present field values through the ALE 
	 * interface.<br>
	 * If omitted, the ALE implementation SHALL behave as though the default format 
	 * associated with fieldname were specified instead.<br>
	 * Built-in formats:
	 * <ul>
	 *   <li>For the "epc" type: epc, epc-tag, epc-hex, epc-decimal</li>
	 *   <li>For the "unsigned integer" type: hex</li>
	 *   <li>For the "iso 15962-encoded string" type: string</li>
	 * </ul> 
	 */
	private String format ;

	//
	// <<extension point>>
	//	---	
	/**
	 * Constructor
	 * @param fieldname
	 * @param datatype
	 * @param format
	 * @throws ECSpecValidationException if invalid or unsupported parameters
	 */
	public ECFieldSpec(String fieldname, String datatype, String format) throws ECSpecValidationException {
		// Set fields 
		this.fieldname = fieldname;
		this.datatype = datatype;
		this.format = format;
		
		// Validate Spec
		validateSpec(this);
	}
	
	/**
	 * Validate spec fields according to implementation
	 * @param fieldSpec ECFieldSpec object to verify
	 * @throws ECSpecValidationException : if Invalid or Unsupported 
	 */
	public static void validateSpec(ECFieldSpec fieldSpec) throws ECSpecValidationException 
	{
		if (!isAbsoluteAddress (fieldSpec.fieldname)
				&& ((fieldSpec.datatype != null) && !fieldSpec.datatype.equals("epc"))
						|| !(fieldSpec.format.equals("epc-pure") 
								|| fieldSpec.format.equals("epc-tag"))) {
			throw new ECSpecValidationException("\n\t[ECFieldSpec]Invalid or Unsupported fieldSpec");
		}
		
	}


	/**
	 * Test if the given String is conform to the following syntax :
	 * '@bank.length[.offset]'
	 * @param fieldname the field name string
	 * @return true if the syntax is respected, false otherwise
	 */
	public static boolean isAbsoluteAddress(String fieldname) {
		boolean ret = false ;
		StringTokenizer tokenizer ;
		
		// test first character
		if (fieldname.startsWith("@")) {
			// split into '.' separated tokens and skip the '@'
			tokenizer = new StringTokenizer(fieldname.substring(1),".");
			if ( validateToken(tokenizer, false)   /* first token : bank (mandatory) */
				&& validateToken(tokenizer, false) /* Second token : length (mandatory) */
				&& validateToken(tokenizer, true) /* Third token : offset (optional) */)
				ret = true ;
		}
		
		return ret;
	}
	
	/**
	 *  Generic method for validating a field of an absolute address '@bank.length[.offset]'
	 * @param tokenizer tokenizer initialized with fieldname String and delimiter
	 * @param optional flag indicating if field presence is optional (true) 
	 * 		   or mandatory (false)
	 * @return true if valid, false otherwise
	 */
	private static boolean validateToken(StringTokenizer tokenizer, boolean optional) {
		boolean ret = false ;
		
		try {
			// get token and parse it to integer
			Integer.parseInt(tokenizer.nextToken());
			ret = true;
		}
		catch (NoSuchElementException e) {
			// field is absent :  it is optional
			ret = optional;
		}
		catch (NumberFormatException e) {
			// field is present but not numerical
			ret = false;
		}
		
		return ret;
	}
	
	/**
	 * 
	 * @param fieldSpec
	 * @return
	 */
	public static int decodeBank(ECFieldSpec fieldSpec) {
		int idx ;
		
		idx = fieldSpec.fieldname.indexOf("@")+1;
		return Integer.parseInt(fieldSpec.fieldname.substring(idx,idx+1));
	}
	
	/**
	 * 
	 * @param fieldSpec
	 * @return
	 */
	public static int decodeLength(ECFieldSpec fieldSpec) {
		int start, end ;
		
		start = fieldSpec.fieldname.indexOf(".")+1;
		end = fieldSpec.fieldname.indexOf(".", start);
		// special treatment because offset is optional 
		if (end < 0) 
			end =  fieldSpec.fieldname.length();
		// parse
		return Integer.parseInt(fieldSpec.fieldname.substring(start,end));
	}
	
	/**
	 * 
	 * @param fieldSpec
	 * @return
	 */
	public static int decodeOffset(ECFieldSpec fieldSpec) {
		int start, temp, val ;
		
		// find first '.'
		start = fieldSpec.fieldname.indexOf(".");
		// find next '.'
		temp = fieldSpec.fieldname.indexOf(".", start+1);
		// special treatment because offset is optional 
		if (temp > 0) {
			// compute offset 
			start += (temp-1);
			val= Integer.parseInt(fieldSpec.fieldname.substring(start));
		} else {
			// offset defaults to 0 if unspecified [ALE]§6.1.9.1, line 1567
			val = 0;
		}
		return val;
	}
	
	
	/**
	 * Getter for fieldname
	 * @return the fieldname
	 */
	public String getFieldname() {
		return fieldname;
	}

	/**
	 * Getter for datatype
	 * @return the datatype
	 */
	public String getDatatype() {
		return datatype;
	}

	/**
	 * Getter for format
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/* 
	 * Test case
	 */
	public static void main(String[] args) throws Exception {
		ECFieldSpec fieldSpec;
		
		// True matches
		System.out.println(isAbsoluteAddress("@3.10.100"));
		System.out.println(isAbsoluteAddress("@3.10"));
		// False matches
		System.out.println(isAbsoluteAddress("@3."));
		System.out.println(isAbsoluteAddress("hey"));
		
		// Instantiation
		fieldSpec = new ECFieldSpec("@3.10.100", "epc", "epc-pure");
		fieldSpec = new ECFieldSpec("@1.1", "epc", "epc-tag");
		//fieldSpec = new ECFieldSpec("@1.1", "epc", "epc-tag");
		
		//Decoding
		System.out.println("bank = " + decodeBank(fieldSpec));
		System.out.println("length = " + decodeLength (fieldSpec));
		System.out.println("offset = " + decodeOffset(fieldSpec));
	}

}
