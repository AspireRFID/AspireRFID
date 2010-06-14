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

package org.ow2.aspirerfid.ale.engine.collect.grouping;

import java.util.Arrays;
import java.util.StringTokenizer;

import org.ow2.aspirerfid.ale.codec.EncScheme;
import org.ow2.aspirerfid.ale.codec.UnsignedBigInt;



public class GroupPattern {
	/**
	 * Wrappers for manipulating string data as numbers.
	 * These wrappers are completely recyclable.
	 */
	private final static UnsignedBigInt wrap1 = new UnsignedBigInt(),
	                              wrap2 = new UnsignedBigInt();
	
	/**
	 * Wrapped pattern String. By reference.
	 */
	protected String pattern;
	
	/**
	 * Decoded pattern's representation
	 */
	private UriRepresentation uriRepresentation;
	
	/**
	 * Decoded pattern's encoding scheme
	 */
	private EncScheme type;
	
	/**
	 * Decoded pattern's fields as strings
	 */
	private String[] fields ;
	
	
	public GroupPattern() {
	}
	
	/**
	 * @return string representation of the object : the URI
	 */
	public String toString() {
		return this.pattern;
	}
	
	public EncScheme getType() {
		return this.type;
	}
	
	public int getNumFields() {
		return type.getNumFields();
	}
	
	public String[] getFields() {
		return this.fields;
	}
		
	/**
	 * Getter for uriRepresentation
	 * @return the uriRepresentation
	 */
	public UriRepresentation getUriRepresentation() {
		return this.uriRepresentation;
	}


	private StringBuffer headerBuffer = new StringBuffer(3);	// temporary buffer
	
	public void setGroupUri(String groupUri) {
		StringTokenizer tokenizer;	// for cutting
		String type;
		int i ;
		
		/* set URI */
		this.pattern = groupUri;
		
		/* construct fields */
		tokenizer = new StringTokenizer(groupUri,":");
		
		// decode header
		for (i = 0 ; i < 3 ; i++)
			headerBuffer.append(tokenizer.nextToken()).append(':');
		this.uriRepresentation = UriRepresentation.getUriRepresentation(headerBuffer.substring(0, headerBuffer.length()-1), true);
		headerBuffer.setLength(0);
		
		// decode type
		type = tokenizer.nextToken();
		this.type = EncScheme.getScheme(type);		
		
		// decode fields
		tokenizer = new StringTokenizer(tokenizer.nextToken(),".");
		this.fields = new String[tokenizer.countTokens()];
		i = 0;
		while (tokenizer.hasMoreTokens()) {
			this.fields[i] = tokenizer.nextToken();
			i++;
		}
	}
	
	/**
	 * Indicates if a field at a given index is the 'X' character
	 * @param index 
	 * @return 
	 */
	private boolean isX(int index) {
		return fields[index].equals("X");
	}
	
	/**
	 * Indicates if a field at a given index is the'*' character
	 * @param index
	 * @return
	 */
	private  boolean isStar(int index){
		return fields[index].equals("*");
	}
		
	public static boolean matches(Epc epc, GroupPattern patternGroup) {
		boolean match = false;
		String[] fields ;
		UriRepresentation pattUri = patternGroup.uriRepresentation;
		UnsignedBigInt epcField;
		
		if (pattUri == UriRepresentation.EPC_PAT_PURE)
			epc.setRepresentation(UriRepresentation.EPC_PURE);
		else if (pattUri == UriRepresentation.EPC_PAT_TAG)
			epc.setRepresentation(UriRepresentation.EPC_TAG);
		else
			throw new IllegalArgumentException("Invalid Group Uri representation : " + pattUri);
			
		if (epc.getType().equals((patternGroup.type), true)) {
			// Get pattern group fields
			fields = patternGroup.fields;
			for ( int i = 0; i < patternGroup.fields.length; i++ ) {
				// if pattern field is 'X' or '*' take it
				if (patternGroup.isX(i) || patternGroup.isStar(i))
				{
					// This match, but keep looking to match other fields
					match = true;
				}
				else {
					// check for range 
					// wrap epc field
					epcField = epc.getField(i);
					if (isRange(fields[i], wrap1, wrap2)) {
						// Check if epc field is in range of the pattern field
						if ( inRange(epcField, fields[i])) {
							// This match, but keep looking to match other fields
							match = true;
						}
						else {
							// No match : break up
							match = false;
							break;
						}
					}
					else {
						// wrap group pattern field
						wrap2.setData(fields[i]);
						
						if (epcField.equal(wrap2))
						{
							// This match, but keep looking to match other fields
							match = true;
						} else {
							// No match : break up
							match = false;
							break;
						}
					}
				}
			}
		}
		
		return match;
	}
	
	/**
	 * Tests if a field of an EPC pattern is in the range of a group pattern field.
	 * 
	 * @param wrap12 a field from an EPC Uri
	 * @param gPattField A field from a group pattern
	 * @return true if in range, false otherwise
	 */
	private static boolean inRange(UnsignedBigInt epcField, String gPattField) {
		boolean ret = false;
		
		// test if pattern field is a range in the form [Lo-Hi]
		if (isRange(gPattField, wrap1, wrap2)) {
			ret = (epcField.greaterEqThan(wrap1) && epcField.lessEqThan(wrap2));
		}
		
		return ret;
	}
	
	/**
	 * Tests if a group pattern field is a range in the form [Lo-Hi]
	 * @param gPattField to test
	 * @return null if the field is not a range,
	 *  or an array </pre> BigInteger [2] = {Lo,Hi} </pre> otherwise
	 */
	private static boolean isRange(String gPattField, 
								   UnsignedBigInt Lo, UnsignedBigInt Hi) 
	{
		boolean ret = false ;
		StringTokenizer tokenizer ;
		
		if (gPattField.startsWith("[") && gPattField.endsWith("]")) {
			// build tokenizer from the pattern but without the '[' and ']' chars
			try {
				tokenizer = new StringTokenizer(gPattField.substring(1,gPattField.length()-1), "-");
				if (tokenizer.countTokens() != 0) {
					ret = true;
					// get Low value
					Lo.setData(tokenizer.nextToken());				
					// get high value
					Hi.setData(tokenizer.nextToken());
				}
			} catch (Exception e) {
				// No action : return false 
			}
		}		
		return ret;
	}
	
	/**
	 * Disjointness of two patterns test. It is defined as follows : <br>
	 * Let Pat_i and Pat_j be two pattern URIs, written as a series of fields as follows:<br>
	 * <pre> Pat_i = urn:epc:pat:type_i:field_i_1.field_i_2.field_i_3...</pre>
	 * <pre> Pat_j = urn:epc:pat:type_j:field_j_1.field_j_2.field_j_3... </pre>
	 * Then Pat_i and Pat_j are disjoint if:
	 * <ul>
	 * <li> type_i is not equal to type_j </li>
	 * <li> type_i = type_j but there is at least one field k for which 
	 *      field_i_k and field_j_k are disjoint (cf. {@link #areDisjoint(String,String) areDisjoint}) </li>
	 * </ul>
	 * @param gPattA Group pattern left
	 * @param gPattB Group pattern right
	 * @return true if disjoint, false otherwise
	 */
	public static boolean areDisjoint(GroupPattern gPattA, GroupPattern gPattB) {
		boolean disjoint = false;	// return value
		
		if (gPattA.uriRepresentation.equals(gPattB.uriRepresentation) &&
			gPattA.type.equals(gPattB.type)) 
		{
			// Fields have the same type, i.e the same number of fields
			/*
			 * type_A = type_B but there is at least one field k for which field_i_k and 
			 * field_j_k are disjoint
			 */
			// Regexps forbidden : grrr
			for (int i = 0 ; i < gPattA.fields.length; i++) {
				if (areDisjoint(gPattA.fields[i], gPattB.fields[i])) {
				//if (areDisjoint(gPattA.fields[i], gPattA.fields[i])) {
					// at least one of the fields are disjoint
					disjoint = true;
					break;
				}
			}		
		} else {
			// types are different : groups are disjoint
			disjoint = true;
		}
		
		
		return disjoint;	
	}
	
	/**
	 * Disjointness test for fields. The tests is done according to the following criteria:
	 * <table border="1">
	 * <tr>
	 * <td> </td>
	 * <td> X </td>
	 * <td> * </td>
	 * <td> Number </td>
	 * <td> [Lo-Hi] </td>
	 * </tr>
	 * <tr>
	 * <td> X </td>
	 * <td> Not disjoint </td>
	 * <td> Not disjoint </td>
	 * <td> Not disjoint </td>
	 * <td> Not disjoint </td>
	 * </tr>
	 * <tr>
	 * <td> * </td>
	 * <td> Not disjoint </td>
	 * <td> Not disjoint </td>
	 * <td> Not disjoint </td>
	 * <td> Not disjoint </td>
	 * </tr>
	 * <tr>
	 * <td> Number </td>
	 * <td> Not disjoint </td>
	 * <td> Not disjoint </td>
	 * <td> Disjoint if the numbers are different </td>
	 * <td> Disjoint if the number is not included in the range </td>
	 * </tr>
	 * <tr>
	 * <td> [Lo-Hi] </td>
	 * <td> Not disjoint </td>
	 * <td> Not disjoint </td>
	 * <td> Disjoint if the number is not included in the range </td>
	 * <td> Disjoint if the numbers are different </td>
	 * </tr>
	 * </table>
	 * 
	 * @param fieldA left field
	 * @param fieldB right field
	 * @return true if disjoint, false otherwise
	 */
	private static boolean areDisjoint(String fieldA, String fieldB) {
		boolean test ;
		
		// test on '*' or 'X'
		test = !(fieldA.equals("X") || fieldA.equals("*") ||fieldB.equals("X") || fieldB.equals("*")) ;
		
		// Test on numbers if previous test fails
		if (test) {
			try {
				wrap1.setData(fieldA);
				wrap2.setData(fieldB);
				test = !wrap1.equal(wrap2) ;
			} catch (Exception e) {
				 test = false;
			}
		}

		return test;
	}
		
	/* 
	 * Test case
	 */
	public static void dump(GroupPattern gPatt) {
		System.out.println( "epc : '" + gPatt.pattern + "'" +
				"\n -> header : '" + gPatt.uriRepresentation + "'" +
				"\n -> type :'" + gPatt.type + "'" +
				"\n -> fields :" + (gPatt.fields != null ? Arrays.asList(gPatt.fields) : null));
	}
	public static void main(String[] args) throws Exception {
		GroupPattern gPatt = new GroupPattern();
		
		gPatt.setGroupUri("urn:epc:pat:sgtin-96:*.123456.400");
		
		System.out.println(gPatt);
		dump(gPatt);		
	}
}
