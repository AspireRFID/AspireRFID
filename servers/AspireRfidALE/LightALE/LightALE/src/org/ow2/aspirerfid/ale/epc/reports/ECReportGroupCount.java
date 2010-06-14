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

/**
 * An <code>ECReportGroupCount</code> is included in an <code>ECReportGroup</code> when the 
 * <code>includeCount</code> field of the corresponding <code>ECReportOutputSpec</code> is 
 * true.
 * 
 * @author pops
 * @author R.DAGHER
 */
public class ECReportGroupCount {
	
	private int count;

	//
	// <<extension point>>
	//	---

	/**
	 * Constructor
	 * @param count
	 */
	public ECReportGroupCount(int count) {
		this.count = count;
	}
	
	/**
	 * 
	 * Constructor
	 */
	public ECReportGroupCount() {
		this.count = 0;
	}

	/**
	 * Getter for count
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Setter for count
	 * @param count the value to set
	 */
	public void setCount(int count) {
		this.count = count;
	}	
	
}
