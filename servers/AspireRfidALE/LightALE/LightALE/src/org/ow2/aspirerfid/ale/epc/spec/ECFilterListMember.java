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

import java.util.Properties;

/**
 * An ECFilterListMember specifies filtering by comparing a single field of a Tag to a 
 * set of patterns. This type is used in both the Reading API and the Writing API.
 * <br> <br>
 *
 * Each ECFilterListMember specifies either an inclusive or an exclusive test based 
 * on the value of one field of a Tag. If the includeExclude parameter of an
 * ECFilterListMember is INCLUDE, then the Tag passes the test if and only if
 * accessing the field does not cause a &quot;field not found&quot; or &quot;operation not possible&quot; 
 * condition and the value of the field matches at least one pattern specified in the
 * ECFilterListMember instance. If the includeExclude parameter of an 
 * ECFilterListMember is EXCLUDE, then the Tag passes the test if and only if accessing
 * the field causes a &quot;field not found&quot; or &quot;operation not possible&quot; condition or the 
 * value of the field does not match any pattern specified in the ECFilterListMember 
 * instance.
 * <br><br>
 * This can be expressed using the following notation, where R is the set of Tags to be
 * reported from a given event cycle, prior to filtering:
 * 
 * <H1 ALIGN=center><img src="doc-files/Filtering.png"> </H1>
 *  
 *  Where Ii,j denotes the set of Tags matched by the jth pattern in the patList of the
 *  ith member of filterList whose includeExclude flag is set to INCLUDE, and Ei,
 *  j denotes the set of Tags matched by the jth pattern in the patList of the ith 
 *  member of filterList whose includeExclude flag is set to EXCLUDE.
 *  
 * @author pops
 * @author R.DAGHER
 */

public class ECFilterListMember extends Spec{	
	/**
	 * Specifies whether this ECFilterListMember is inclusive or exclusive. If this 
	 * parameter is INCLUDE, a Tag is considered to pass the filter if the value in 
	 * the specified field matches any of the patterns in patList. If this parameter is
	 * EXCLUDE, a Tag is considered to pass the filter it the value in the specified 
	 * field does not match any of the patterns in patList.<br>
	 * (INCLUDE = true, EXCLUDE = false).
	 */
	private boolean includeExclude ; 
	
	
	/**
	 * Specifies which field of the Tag is considered in evaluating this filter, the 
	 * datatype of the field contents, and the format for patterns that appear in 
	 * patList.
	 */
	private ECFieldSpec fieldspec ;
	
	/**
	 * An unordered list that specifies the patterns against which the value of the 
	 * specified Tag field is to be compared. Each member of this list is a pattern 
	 * value conforming to the format implied by fieldspec.
	 */
	private String[] patList;
	
	//
	// <<extension point>>
	//	---	
	
	
	/**
	 * Constructor
	 * @param includeExclude
	 * @param fieldspec
	 * @param patList
	 */
	public ECFilterListMember(boolean includeExclude, ECFieldSpec fieldspec,
			String[] patList) {
		this.includeExclude = includeExclude;
		this.fieldspec = fieldspec;
		this.patList = patList;
	}
	
	/**
	 * Set up fields from a Java property object
	 * @param spec_property Java property object containing specification data
	 */
	public void loadFromProp (Properties spec_property) {
		// TODO
		
	}
	
	/**
	 * 
	 * Constructor from a Java property file 
	 * @param spec_property Java property object containing specification data
	 */
	public ECFilterListMember(Properties spec_property) {
		this.loadFromProp(spec_property);
	}

	/**
	 * Getter for includeExclude
	 * @return the includeExclude
	 */
	public boolean isIncludeExclude() {
		return includeExclude;
	}

	/**
	 * Getter for fieldspec
	 * @return the fieldspec
	 */
	public ECFieldSpec getFieldspec() {
		return fieldspec;
	}

	/**
	 * Getter for patList
	 * @return the patList
	 */
	public String[] getPatList() {
		return patList;
	}
	
}
