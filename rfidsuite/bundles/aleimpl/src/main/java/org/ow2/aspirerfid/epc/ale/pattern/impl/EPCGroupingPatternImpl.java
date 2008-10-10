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
package org.ow2.aspirerfid.epc.ale.pattern.impl;

import java.util.Iterator;
import java.util.List;

import org.ow2.aspirerfid.epc.ale.pattern.api.BadPatternSyntaxException;
import org.ow2.aspirerfid.epc.ale.pattern.api.EPCGroupingPattern;
import org.ow2.aspirerfid.epc.ale.tag.api.EPCTag;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 * @version 2007
 */
public class EPCGroupingPatternImpl extends EPCPatternImpl implements
		EPCGroupingPattern {

	/**
	 * @param pattern
	 *            TODO Javadoc
	 * @throws BadPatternSyntaxException
	 */
	public EPCGroupingPatternImpl(final String pattern)
			throws BadPatternSyntaxException {
		this.setPattern(pattern);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.pattern.api.EPCGroupingPattern#getFormat()
	 */
	public String getFormat() {
		return this.format;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.pattern.api.EPCGroupingPattern#getGroup(org.ow2.aspirerfid.epc.ale.tag.api.EPCTag)
	 */
	public String getGroup(final EPCTag epc) {

		final StringBuilder sb = new StringBuilder(
				EPCPatternImpl.PATTERN_PREFIX);
		sb.append(this.format);
		sb.append(':');

		if (this.isTag) {
			if (!epc.getTagFormat().equals(this.format)) {
				return null;
			}
		} else {
			if (!epc.getEpcFormat().equals(this.format)) {
				return null;
			}
		}

		final Iterator iterEpc = epc.getFieldIter();
		final Iterator iterPattern = this.patternFields.iterator();
		while (iterEpc.hasNext() && iterPattern.hasNext()) {
			final PatternField f = (PatternField) iterPattern.next();
			final Object o = iterEpc.next();
			if (o.getClass() == String.class) {
				sb.append(f.getPattern((String) o));
			} else if (o.getClass() == Long.class) {
				sb.append(f.getPattern((Long) o));
			} else {
				// Should never happen
				return null;
			}
			if (iterEpc.hasNext() && iterPattern.hasNext()) {
				sb.append('.');
			}
		}
		if (!iterEpc.hasNext() && !iterPattern.hasNext()) {
			return sb.toString();
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.pattern.api.EPCGroupingPattern#getPatternFields()
	 */
	public List getPatternFields() {
		return this.patternFields;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.pattern.api.EPCGroupingPattern#isDisjoint(org.ow2.aspirerfid.epc.ale.pattern.api.EPCGroupingPattern)
	 */
	public boolean isDisjoint(final EPCGroupingPattern pattern) {

		if (!this.format.equals(pattern.getFormat())) {
			return false;
		}

		final Iterator iter = this.patternFields.iterator();
		final Iterator iter2 = pattern.getPatternFields().iterator();

		boolean res = false;
		while (iter.hasNext() && iter2.hasNext()) {
			final PatternField patternField = (PatternField) iter.next();
			final PatternField patternField2 = (PatternField) iter2.next();
			res = res || patternField.isDisjoint(patternField2);
		}
		if (iter.hasNext() || iter2.hasNext()) {
			return false;
		}
		return res;
	}

}
