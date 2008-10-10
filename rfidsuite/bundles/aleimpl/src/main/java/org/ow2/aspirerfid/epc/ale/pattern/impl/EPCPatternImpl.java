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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.ow2.aspirerfid.epc.ale.pattern.api.BadPatternSyntaxException;
import org.ow2.aspirerfid.epc.ale.pattern.api.EPCFilteringPattern;
import org.ow2.aspirerfid.epc.ale.pattern.api.EPCPattern;
import org.ow2.aspirerfid.epc.ale.tag.api.EPCTag;

/**
 * implementation of EPCPattern interface. Able to parse, store a pattern, and
 * to determine if an epc matches the pattern
 * 
 * @author Anne Robert
 * @author Guillaume Surrel
 * @version 2007
 */
public abstract class EPCPatternImpl implements EPCPattern {

	protected static String PATTERN_PREFIX = "urn:epc:pat:";

	/**
	 * Vector of specified fields
	 */
	protected List patternFields = null;

	/**
	 * Pattern format
	 */
	protected String format;

	/**
	 * The pattern compare tag or epc format
	 */
	protected boolean isTag;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.pattern.api.EPCPattern#match(org.ow2.aspirerfid.epc.ale.tag.api.EPCTag)
	 */
	public boolean match(final EPCTag epc) {

		if (this.isTag) {
			if (!epc.getTagFormat().equals(this.format)) {
				return false;
			}
		} else {
			if (!epc.getEpcFormat().equals(this.format)) {
				return false;
			}
		}

		final Iterator iterEpc = epc.getFieldIter();
		final Iterator iterPattern = this.patternFields.iterator();
		while (iterEpc.hasNext() && iterPattern.hasNext()) {
			final PatternField f = (PatternField) iterPattern.next();
			final Object o = iterEpc.next();
			if (o.getClass() == String.class) {
				if (!f.match((String) o)) {
					return false;
				}
			} else if (o.getClass() == Long.class) {
				if (!f.match((Long) o)) {
					return false;
				}
			} else {
				// Should never happen
				return false;
			}

		}
		return (!iterEpc.hasNext() && !iterPattern.hasNext());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.pattern.api.EPCPattern#setPattern(java.lang.String)
	 */
	public void setPattern(final String pattern)
			throws BadPatternSyntaxException {
		this.patternFields = new ArrayList();

		// First control the prefix of the pattern URI
		if (pattern.startsWith(EPCPatternImpl.PATTERN_PREFIX)) {
			// Separate the pattern URI in fields
			String suffix = pattern.substring(EPCPatternImpl.PATTERN_PREFIX
					.length(), pattern.length());

			final int index = suffix.indexOf(':');
			if (index == -1) {
				throw new BadPatternSyntaxException();
			} else {
				this.format = suffix.substring(0, index);
			}
			suffix = suffix
					.substring(this.format.length() + 1, suffix.length());

			final StringTokenizer st = new StringTokenizer(suffix, ".");
			// First field is the format
			// if this format include a physical size information,
			// it is a tag format. This size information is separated by
			// the '-' character.
			this.isTag = (this.format.indexOf("-") != -1);
			if ((st.countTokens() == 3) || (st.countTokens() == 4)) {
				while (st.hasMoreTokens()) {
					this.patternFields.add(new PatternField(st.nextToken(),
							this instanceof EPCFilteringPattern));
				}
			} else {
				throw new BadPatternSyntaxException();
			}
		} else {
			throw new BadPatternSyntaxException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		final StringBuilder sb = new StringBuilder(
				EPCPatternImpl.PATTERN_PREFIX + this.format + ":");
		final Iterator it = this.patternFields.iterator();
		if (it.hasNext()) {
			sb.append(it.next());
		}
		while (it.hasNext()) {
			sb.append('.');
			sb.append(it.next());
		}
		return sb.toString();
	}

}
