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
 * 
 * @author rdagher
 *
 */
public class FilterOperator {
	/**
	 * List of Filtering patterns for inclusion tests.
	 */
	private ArrayList includeMemberList;
	
	/**
	 * List of Filtering patterns for exclusion tests.
	 */
	private ArrayList excludeMemberList;
	
	/**
	 * Wrapper object for manipulating a filtering pattern String.
	 */
	private GroupPattern filtPatt;
	
	/**
	 * Constructor
	 */
	public FilterOperator() {
		includeMemberList = new ArrayList();
		excludeMemberList = new ArrayList();
		filtPatt = new GroupPattern();
	}
	
	/**
	 * Adds a filtering pattern to the filtering list.
	 * 
	 * @param patterns The filtering pattern Strings to add.
	 * @param include include if true, exclude if false.
	 * @throws IllegalArgumentException if the given patternGroup is disjoint with the pattern group list
	 */
	public void addPatterns (String[] patterns, boolean include) throws IllegalArgumentException {
		// TODO add parameters validation
		
		// add to relevant list.
		if (include) {
			includeMemberList.add(patterns);
		}else {
			excludeMemberList.add(patterns);
		}
	}

	/**
	 * Decides whether to keep a tag or not depending on the filtering patterns.
	 * @param epc code to match against filtering patterns.
	 * @return true if the given tag is to be kept, false otherwise.
	 */
	public boolean filter (Epc epc) {
		boolean keep, match;
		String[] pattList;
		
		// Inclusion Test : for each member, match at least one pattern
		keep = true;
		for (int i = 0; (i<includeMemberList.size()) && keep; i++) {
			// get member's list of include patterns
			pattList = (String[])includeMemberList.get(i);
			// match it against all patterns : the Tag passes the test if it matches at least one pattern.
			match = false;
			for (int j = 0 ; (j < pattList.length) && !match; j++) {
				// wrap it
				filtPatt.setGroupUri(pattList[j]);
				// match it
				match = GroupPattern.matches(epc, filtPatt);
			}
			keep = match; // we keep the tag if it matches at least one pattern of the member.
		}
		
		// if inclusion test has passed, check exclusion test
		if (keep) {
			// Exclusion Test : for each member, tag shall not match any pattern
			for (int i = 0; (i<excludeMemberList.size()) && keep; i++) {
				// get member's list of exclude patterns
				pattList = (String[])excludeMemberList.get(i);
				// match it against all patterns : the Tag passes the test if it matches none of the patterns.
				match = false;
				for (int j = 0 ; (j < pattList.length) && !match; j++) {
					// wrap it
					filtPatt.setGroupUri(pattList[j]);
					// match it
					match = GroupPattern.matches(epc, filtPatt);
				}
				keep = !match; // we keep the tag if it matches none of the patterns of the member.
			}
		}
		
		return keep;
	}
	
	/**
	 * cleanup function that erases all patterns from lists. 
	 * This permits to recycle objects.
	 */
	public void cleanUp () {
		includeMemberList.clear();
		excludeMemberList.clear();
	}
	
}
