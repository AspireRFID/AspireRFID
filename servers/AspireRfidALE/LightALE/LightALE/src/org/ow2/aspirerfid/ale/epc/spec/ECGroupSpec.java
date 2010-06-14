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

/**
 * <code>ECGroupSpec</code> defines how filtered EPCs are grouped together for reporting.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECGroupSpec {

	/**
	 * (Optional) Specifies which field of the Tag is used for grouping, the datatype of 
	 * the field contents, and the format for grouping patterns that appear in 
	 * <code>patternList</code>. <br>
	 * If this parameter is omitted, the ALE implementation SHALL behave as though the 
	 * <code>fieldspec</code> parameter were set to an <code>ECFieldSpec</code> instance 
	 * whose <code>fieldname</code> parameter is set to <code>epc</code> and whose 
	 * <code>datatype</code> and <code>format</> parameters are omitted.
	 */
	private ECFieldSpec fieldspec  ;

	/**
	 * An unordered list that specifies the grouping patterns used to generate a group name
	 *  from the value of the specified Tag field. Each member of this list is a grouping 
	 *  pattern value conforming to the format implied by <code>fieldspec</code>.
	 */
	private String [] patternList ;

	//
	// <<extension point>>
	//	---	

	/**
	 * Constructor
	 * @param fieldspec
	 * @param patternList
	 */
	public ECGroupSpec(ECFieldSpec fieldspec, String[] patternList) {
		this.fieldspec = fieldspec;
		this.patternList = patternList;
	}

	/**
	 * Constructor
	 */
	public ECGroupSpec() {
	}

	/**
	 * Getter for fieldspec
	 * @return the fieldspec
	 */
	public ECFieldSpec getFieldspec() {
		return fieldspec;
	}

	/**
	 * Getter for patternList
	 * @return the patternList
	 */
	public String[] getPatternList() {
		return patternList;
	}

	/**
	 * Setter for fieldspec
	 * @param fieldspec the value to set
	 */
	public void setFieldspec(ECFieldSpec fieldspec) {
		this.fieldspec = fieldspec;
	}

	/**
	 * Setter for patternList
	 * @param patternList the value to set
	 */
	public void setPatternList(String[] patternList) {
		this.patternList = patternList;
	}
	
	
}
