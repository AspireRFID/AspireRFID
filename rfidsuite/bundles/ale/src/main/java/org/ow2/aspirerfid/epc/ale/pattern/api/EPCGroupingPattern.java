/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.epc.ale.pattern.api;

import java.util.List;

import org.ow2.aspirerfid.epc.ale.tag.api.EPCTag;

/**
 * A grouping pattern is a pattern defined by EPCGlobal to manage epc grouping.
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
public interface EPCGroupingPattern extends EPCPattern {

	/**
	 * @param pattern
	 *            the pattern to compare to
	 * @return true if the two patterns are disjoint
	 */
	public boolean isDisjoint(EPCGroupingPattern pattern);

	/**
	 * @param epc
	 *            the epc
	 * @return the group the epc belongs to (replaces the cross in the pattern)
	 */
	public String getGroup(EPCTag epc);

	/**
	 * @return the fields of the epc pattern
	 */
	public List getPatternFields();

	/**
	 * @return the format of the pattern
	 */
	public String getFormat();

}
