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

import java.util.HashSet;
import java.util.Hashtable;

/**
 * Example of building groups from incoming epc tags.
 *
 */
public class Groups {
	/**
	 * 
	 */
	private Hashtable groups ; // key : GroupPattern, value a HashSet of Strings
	/**
	 * A group operator for computing the group name of an epc and for ensuring
	 * disjointness of group names
	 */
	private GroupOperator groupOperator;

	/**
	 * Constructor
	 */
	public Groups() {
		groups = new Hashtable();
		groups.put("default", new HashSet());
		groupOperator = new GroupOperator();
	}
	
	/**
	 * Constructor from an array of patterns
	 */
	public Groups(GroupPattern[] gPattern) throws IllegalArgumentException {
		
		// call default constructor
		this();
		
		// add group patterns
		for (int i = 0 ; i< gPattern.length; i++)
			addGroupPattern(gPattern[i]);
	}
	
	/**
	 * Clean up method for reusing object.
	 */
	public void cleanUp() {
		this.groupOperator.cleanUp();
		this.groups.clear();
	}
	
	/**
	 * 
	 * @param gPattern
	 * @throws IllegalArgumentException
	 */
	public void addGroupPattern (GroupPattern gPattern) throws IllegalArgumentException {	
		groupOperator.addPattern(gPattern.pattern);
	}
	
	/**
	 * 
	 * @param gPatternUri
	 * @throws IllegalArgumentException
	 */
	public void addGroupPattern (String gPatternUri) throws IllegalArgumentException {	
		groupOperator.addPattern(gPatternUri);
	}
	
	/**
	 * Adds a group key to the hash table
	 * @param groupName
	 * @return
	 * @throws IllegalArgumentException
	 */
	private boolean addGroup (String groupName) throws IllegalArgumentException {
		boolean ret = false;
		
		if (!groups.containsKey(groupName)) {
			groups.put(groupName, new HashSet());
			ret = true;
		} 
		return ret;		
	}
		
	/**
	 * Adds an epc string to one of the groups in the hash table
	 * @param epc
	 * @return
	 */
	public boolean addEpc(Epc epc) {
		boolean ret = false;
		String epcGroupName ;
		
		// Generate Group Name
		epcGroupName = this.groupOperator.group(epc);
		
		// Add it to hash table keys (no effect if exists)
		addGroup(epcGroupName);
		
		// Add epc to group list
		addEpc(epc, epcGroupName);
		
		return ret;
	}
	
	/**
	 * 
	 * @param epc
	 * @param gPatternUri
	 * @return
	 */
	private boolean addEpc(Epc epc, String gPatternUri) {
		//return addEpc(epc.getUri(), gPatternUri);
		boolean ret = false;
		HashSet values;
		
		// get group key : the same
		if (groups.containsKey(gPatternUri)) {
			ret = true;
			// get Array List of group items
			values = (HashSet) groups.get(gPatternUri);
			// Add the EPC to the list
			values.add(epc);
		}
		
		return ret;
	}
	
	/* 
	 * Test Case
	 */
	
	public static byte[] stringToArrayOfBytes (String data) {
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
		Epc epc1, epc2, epc3, epc4;
		// Group patterns defined by user in a spec for example
		GroupPattern gPatt1 = new GroupPattern();
		GroupPattern gPatt2 = new GroupPattern();
		GroupPattern[] gPattArray =  {gPatt1, gPatt2} ;

		// Groups of patterns and associated set of epc tags
		gPatt1.setGroupUri("urn:epc:pat:sgtin-96:*.0360000.*.*");
		gPatt2.setGroupUri("urn:epc:pat:sgtin-96:*.0290000.*.*");
		Groups agroups = new Groups(gPattArray);
		
		// epc tags captured
		epc1 = new Sgtin96();
		epc2 = new Sgtin96();
		epc3 = new Sgtin96();
		epc4 = new Sgln96();
		
		// epc1 = "urn:epc:tag:sgtin-96:3.0360000.123456.400"
		epc1.setTagData(stringToArrayOfBytes("30795F900078900000000190"));
		// epc2 = "urn:epc:tag:sgtin-96:3.0360000.123456.500"
		epc2 .setTagData(stringToArrayOfBytes("30795F9000789000000001F4"));
		// epc3 = "urn:epc:tag:sgtin-96:3.0290000.111111.100"
		epc3.setTagData(stringToArrayOfBytes("30791B34006C81C000000064"));
		// epc4 = "urn:epc:tag:sgln-96:2.0123456.000002"
		epc4.setTagData(stringToArrayOfBytes("325878900000040000000000"));
				
		// Add captured Epcs
		agroups.addEpc(epc1);
		agroups.addEpc(epc2);
		agroups.addEpc(epc3);
		agroups.addEpc(epc4);

		
		System.out.println(agroups.groups);
		
	}	
	
}
