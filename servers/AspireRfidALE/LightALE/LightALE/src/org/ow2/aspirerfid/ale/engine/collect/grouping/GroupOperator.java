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


import java.util.ArrayList;


/**
 * This class represents a Group Operator implementation as defined in the ALE 
 * specification.<br>
 * A group operator object wraps a list of {@link GroupPattern#areDisjoint(String, String) disjoint} grouping patterns 
 * (see {@link #addPattern}). <br>
 * The aim of this class is to provide a facility for generating a group name corresponding to 
 * an EPC (see {@link #group(Epc)}).
 * @see GroupPattern
 * @see Epc
 * 
 */
public class GroupOperator {
	/**
	 * List of Group patterns belonging to group
	 */
	private ArrayList patternList;
	
	/**
	 * Wrappers objects for manipulating a group pattern String
	 */
	private GroupPattern grpPatt_1,  grpPatt_2;
	
	/**
	 * Decoded pattern's representation
	 */
	private UriRepresentation uriRepresentation = UriRepresentation.EPC_PAT_TAG;
	
	/**
	 * Constructor
	 */
	public GroupOperator() {
		patternList = new ArrayList();
		grpPatt_1 = new GroupPattern();
		grpPatt_2 = new GroupPattern();
	}

	/**
	 * Setter for uriRepresentation
	 * @param uriRepresentation the value to set
	 */
	public void setUriRepresentation(UriRepresentation uriRepresentation) {
		this.uriRepresentation = uriRepresentation;
	}


	/**
	 * Adds a grouping pattern to the group list.
	 * 
	 * @param patternGroup The group pattern String to add.
	 * @throws IllegalArgumentException if the given patternGroup is disjoint with the pattern group list
	 */
	public void addPattern (String patternUri) throws IllegalArgumentException {
		// Set up wrapper
		grpPatt_1.setGroupUri(patternUri);
		
		// Check that group pattern is consistent with the group's representation
		if (!grpPatt_1.getUriRepresentation().equals(this.uriRepresentation))
			throw new IllegalArgumentException("pattern is not conform to the syntax rules for grouping : " + this.uriRepresentation);
		
		// Check that the group is disjoint with others before adding it to list
		for (int i = 0 ; i< this.patternList.size(); i++ ) {
			//grpPatt_1.setGroupUri((String)iter.next());
			grpPatt_2.setGroupUri((String)this.patternList.get(i));
			if (!GroupPattern.areDisjoint(grpPatt_1, grpPatt_2)) {
				throw new IllegalArgumentException("pattern not disjoint with group members");
			}		
		}
		
		// group pattern is disjoint with group members, add it 
		patternList.add(patternUri);	
		}

	/**
	 * Gives the first group name for a given epc code.
	 * @param epc code to match against a group pattern
	 * @return the group name or "default" if the epc code does not match any group pattern 
	 * from the list.
	 */
	public String group (Epc epc) {
		String groupName = "default";
		
		for (int i = 0 ; i< this.patternList.size(); i++ ) {
			// wrap it
			grpPatt_1.setGroupUri((String)this.patternList.get(i));
			if (GroupPattern.matches(epc, grpPatt_1)) {
				groupName = buildGroup(epc,grpPatt_1);
				break;
			}		
		}
		
		return groupName;
	}
	
	/**
	 * cleanup function that erases all patterns from group. This permits to recycle objects.
	 */
	public void cleanUp () {
		patternList.clear();
	}
	
	
	/**
	 * Build a group name from an epc that matches a group pattern
	 * @param epc Uri representing the epc code
	 * @param groupPattern the group pattern that epc matches
	 * @return The group name
	 */
	private static String buildGroup(Epc epc, GroupPattern groupPattern) {
		StringBuffer groupName = new StringBuffer () ;
		String [] groupFields ;
		String groupField;
		
		// Build group name
		// Header and type
		groupName.append(groupPattern.getUriRepresentation()).append(':').append(epc.getType()).append(':');
		groupFields = groupPattern.getFields();
		// Fields
		for (int i = 0 ; i < groupFields.length ; i++) {
			groupField = groupFields[i];
			if (groupField.equals("X")) {
				// get it from epc
				groupName.append(epc.getField(i));
			} else {
				// get it from groupPattern
				groupName.append(groupField);
			}
			if ( i < (groupFields.length-1))
				groupName.append('.');
		}
		
		return groupName.toString();
	}
	
	/* 
	 * Test Case
	 */
	
	private static byte[] stringToArrayOfBytes (String data) {
		byte[] bytes;
		
		// add a leading zero if necessary
		if ((data.length()%2)!=0)
			data = "0"+data;
		
		bytes = new byte[data.length()/2];
		for (int i =  bytes.length - 1, j = 0; i >= 0; i--, j+=2) {
			bytes[i] = Integer.decode("0x"+data.substring(j,j+2)).byteValue();
		}
		
		return bytes;
	}
	
	public static void main(String[] args) throws Exception {
		Epc epc1, epc2, epc2_1, epc2_2, epc3, epc4;
		GroupPattern gPatt1, gPatt2;//, gPatt3;
		GroupOperator gOp;
		
		/* Init */
		epc1 = new Sgtin96();
		epc2 = new Sgtin96();
		epc2_1 = new Sgtin96();
		epc2_2 = new Sgtin96();
		epc3 = new Sgtin96();
		epc4 = new Sgln96();
		gPatt1 = new GroupPattern(); 
		gPatt2 = new GroupPattern();
		//gPatt3 = new GroupPattern(); //Exception test
		gOp = new GroupOperator();
		
		/* define a set of patterns */
		// epc1 = "urn:epc:tag:sgtin-96:3.0360000.123456.400"
		epc1.setTagData(stringToArrayOfBytes("30795F900078900000000190"));
		// epc2 = "urn:epc:tag:sgtin-96:3.0370000.123456.500"
		epc2 .setTagData(stringToArrayOfBytes("3079695400789000000001F4"));
		// epc2_1 = "urn:epc:tag:sgtin-96:4.0360000.123457.600"
		epc2_1 .setTagData(stringToArrayOfBytes("30995F900078904000000258")) ;
		// epc2_2 = "urn:epc:tag:sgtin-96:4.0360000.123457.700"
		epc2_2.setTagData(stringToArrayOfBytes("30995F9000789040000002BC"));
		// epc3 = "urn:epc:tag:sgtin-96:3.0290000.111111.100"
		epc3.setTagData(stringToArrayOfBytes("30791B34006C81C000000064"));
		// epc4 = "urn:epc:tag:sgln-96:2.0123456.000002"
		epc4.setTagData(stringToArrayOfBytes("325878900000040000000000"));
		
		/* define a group pattern object */
		gPatt1.setGroupUri("urn:epc:pat:sgtin-96:3.*.X.*");
		gPatt2.setGroupUri("urn:epc:pat:sgtin-96:4.X.*.[500-700]");
		//gPatt3.setGroupUri("urn:epc:idpat:sgtin:*.X.[500-700]");
		//gPatt3.setGroupUri("urn:epc:pat:sgtin-96:4.X.*.[400-600]");
		
		/* define a Group operator and add the pattern into it */	
		gOp.addPattern(gPatt1.pattern);
		gOp.addPattern(gPatt2.pattern);
		//gOp.addPattern(gPatt3.pattern);
		
		/* Compute first Groups and dump them up */
		System.out.println(gOp.group(epc1));
		System.out.println(gOp.group(epc2));
		System.out.println(gOp.group(epc2_1));
		System.out.println(gOp.group(epc2_2));
		System.out.println(gOp.group(epc3));
		System.out.println(gOp.group(epc4));
		
	}
	
}


