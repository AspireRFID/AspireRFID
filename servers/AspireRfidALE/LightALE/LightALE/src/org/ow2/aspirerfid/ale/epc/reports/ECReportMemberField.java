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

package org.ow2.aspirerfid.ale.epc.reports;

import org.ow2.aspirerfid.ale.epc.spec.ECFieldSpec;
import org.ow2.aspirerfid.ale.epc.spec.ECReportOutputSpec;

/**
 * Each <code>ECReportMemberField</code> within the fieldList of an 
 * <code>ECReportGroupListMember</code> gives the value read from a single field of a 
 * single Tag.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECReportMemberField {
	
	/**
	 * The name specified in the corresponding <code>ECReportOutputFieldSpec</code> that 
	 * generated this <code>ECReportMemberField</code> instance in this report, either 
	 * explicitly or defaulted to the <code>fieldname</code> as specified in 
	 * {@link ECReportOutputSpec}. If the name is defaulted to the <code>fieldname</code>, 
	 * and the <code>fieldname</code> specified in the <code>ECReportOutputFieldSpec</code> 
	 * was a pattern <code>fieldname</code>, then the value of the "name" parameter SHALL
	 * be the name of the specific field that matched the pattern.
	 */
	private String name ;
	
	/**
	 * The value read from the field of the Tag. This value SHALL conform to the syntax 
	 * implied by the format parameter of <code>fieldspec</code>.
	 * If the attempt to read the field value of the Tag caused a "field not found" or 
	 * "operation not possible" condition, the value parameter SHALL be omitted.
	 */
	private String value ; // optional
	
	/**
	 * If the <code>includeFieldSpecInReport</code> parameter of the corresponding 
	 * <code>ECReportOutputFieldSpec</code> that generated this <code>ECReportMemberField</code> 
	 * instance in this report was set to true, this <code>fieldspec</code> parameter 
	 * SHALL contain a copy of the corresponding <code>ECFieldSpec</code> instance in the 
	 * <code>ECReportOutputFieldSpec</code>. <br>
	 * If the <code>datatype</code> or <code>format</code> parameters were omitted in the 
	 * original <code>ECFieldSpec</code>, in this copy those fields SHALL contain the 
	 * default datatype or format that were used.<br>
	 * Omitted if the <code>includeFieldSpecInReport</code> parameter of the corresponding 
	 * <code>ECReportOutputFieldSpec</code> that generated this <code>ECReportMemberField</code> 
	 * instance in this report was omitted or set to false.
	 */
	private ECFieldSpec fieldspec ; // optional

	//
	// <<extension point>>
	//	---


	/**
	 * Constructor
	 * @param name
	 * @param value
	 * @param fieldspec
	 */
	public ECReportMemberField(String name, String value, ECFieldSpec fieldspec) {
		this.name = name;
		this.value = value;
		this.fieldspec = fieldspec;
	}
	
	/**
	 * 
	 * Constructor
	 */
	public ECReportMemberField() {		
	
	}
	
	/**
	 * Getter for name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for value
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Getter for fieldspec
	 * @return the fieldspec
	 */
	public ECFieldSpec getFieldspec() {
		return fieldspec;
	}

	/**
	 * Setter for name
	 * @param name the value to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for value
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Setter for fieldspec
	 * @param fieldspec the value to set
	 */
	public void setFieldspec(ECFieldSpec fieldspec) {
		this.fieldspec = fieldspec;
	}
	
}
