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

import java.util.StringTokenizer;

import org.ow2.aspirerfid.epc.ale.pattern.api.BadPatternSyntaxException;

/**
 * class to compare pattern field.
 * 
 * @author Anne Robert
 * @author Guillaume Surrel
 * @version 2007
 */
public class PatternField {

	private final static int NUMERIC_VALUE = 1;

	private final static int CROSS = 2;

	private final static int STAR = 3;

	private final static int RANGE_SPECIFICATION = 4;

	private int patternType;

	private long leftValue = 0;

	private long rightValue = 0;

	/**
	 * constructor of the pattern
	 * 
	 * @param fieldSpec
	 *            pattern specification
	 * @param isFilteringPattern
	 *            true if the pattern is a filtering pattern, false if it is a
	 *            grouping pattern
	 * @throws BadPatternSyntaxException
	 *             raise an error if the pattern specification is badly formed
	 */
	public PatternField(final String fieldSpec, final boolean isFilteringPattern)
			throws BadPatternSyntaxException {
		// possible syntax for a pattern:
		// - numeric value
		// - star
		// - range : [numeric-numeric]
		final StringTokenizer st = new StringTokenizer(fieldSpec, "[]-", true);
		final int count = st.countTokens();
		if (count == 1) {
			// numeric value or star
			final String token = st.nextToken();
			if (token.equals("*")) {
				this.patternType = PatternField.STAR;
			} else if (token.equals("X")) {
				if (isFilteringPattern) {
					throw new BadPatternSyntaxException();
				}
				this.patternType = PatternField.CROSS;
			} else {
				this.patternType = PatternField.NUMERIC_VALUE;
				try {
					this.leftValue = Long.parseLong(token, 16);
				} catch (final NumberFormatException e) {
					throw new BadPatternSyntaxException();
				}
			}
		} else if (count == 5) {
			// should be [numeric-numeric]
			String tok = st.nextToken();
			if (!tok.equals("[")) {
				throw new BadPatternSyntaxException();
			}
			tok = st.nextToken();
			try {
				this.leftValue = Long.parseLong(tok, 16);
			} catch (final NumberFormatException e) {
				throw new BadPatternSyntaxException();
			}
			tok = st.nextToken();
			if (!tok.equals("-")) {
				throw new BadPatternSyntaxException();
			}
			tok = st.nextToken();
			try {
				this.rightValue = Long.parseLong(tok, 16);
			} catch (final NumberFormatException e) {
				throw new BadPatternSyntaxException();
			}
			if (this.leftValue > this.rightValue) {
				throw new BadPatternSyntaxException();
			}
			tok = st.nextToken();
			if (!tok.equals("]")) {
				throw new BadPatternSyntaxException();
			}
			this.patternType = PatternField.RANGE_SPECIFICATION;
		} else {
			throw new BadPatternSyntaxException();
		}
	}

	protected PatternField getPattern(final Long l) {
		try {
			switch (this.patternType) {
			case STAR:
				return new PatternField("*", false);
			case NUMERIC_VALUE:
				return new PatternField(Long.toHexString(this.leftValue) + "",
						false);
			case RANGE_SPECIFICATION:
				return new PatternField("[" + Long.toHexString(this.leftValue)
						+ "-" + Long.toHexString(this.rightValue) + "]", false);
			case CROSS:
				return new PatternField(Long.toHexString(l.longValue()), false);
			default:
				return null;
			}
		} catch (final BadPatternSyntaxException e) {
			throw new IllegalStateException();
		}
	}

	protected PatternField getPattern(final String s) {
		try {
			switch (this.patternType) {
			case STAR:
				return new PatternField("*", false);
			case NUMERIC_VALUE:
				return new PatternField(Long.toHexString(this.leftValue) + "",
						false);
			case RANGE_SPECIFICATION:
				return new PatternField("[" + Long.toHexString(this.leftValue)
						+ "-" + Long.toHexString(this.rightValue) + "]", false);
			case CROSS:
				return new PatternField(s, false);
			default:
				return null;
			}
		} catch (final BadPatternSyntaxException e) {
			throw new IllegalStateException();
		}
	}

	protected boolean isDisjoint(final PatternField patternField2) {
		switch (this.patternType) {
		case STAR:
			return false;
		case CROSS:
			return false;
		case NUMERIC_VALUE:
			switch (patternField2.patternType) {
			case STAR:
				return false;
			case NUMERIC_VALUE:
				return this.leftValue != patternField2.leftValue;
			case RANGE_SPECIFICATION:
				return (this.leftValue < patternField2.leftValue)
						|| (this.leftValue > patternField2.rightValue);
			case CROSS:
				return false;
			default:
				return false;
			}
		case RANGE_SPECIFICATION:
			switch (patternField2.patternType) {
			case STAR:
				return false;
			case NUMERIC_VALUE:
				return (patternField2.leftValue < this.leftValue)
						|| (patternField2.leftValue > this.rightValue);
			case RANGE_SPECIFICATION:
				if (patternField2.rightValue < this.leftValue) {
					return true;
				}
				if (patternField2.leftValue > this.rightValue) {
					return true;
				}
				return false;
			case CROSS:
				return false;
			default:
				return false;
			}
		default:
			return false;
		}
	}

	/**
	 * Tests if a value matches the field
	 * 
	 * @param l
	 *            the value to compare
	 * @return true if the long matches the pattern field
	 */
	public boolean match(final Long l) {
		switch (this.patternType) {
		case STAR:
			return true;
		case CROSS:
			return true;
		case NUMERIC_VALUE:
			return (l.longValue() == this.leftValue);
		case RANGE_SPECIFICATION:
			return ((l.longValue() >= this.leftValue) && (l.longValue() <= this.rightValue));
		default:
			return false;
		}
	}

	/**
	 * Tests if a string matches the field
	 * 
	 * @param s
	 *            the value to compare
	 * @return true if the string matches the pattern field
	 */
	public boolean match(final String s) {
		switch (this.patternType) {
		case STAR:
			return true;
		case CROSS:
			return true;
		case NUMERIC_VALUE:
			if ((new Long(this.leftValue)).toString().equals(s)) {
				return true;
			}
		case RANGE_SPECIFICATION:
			final long val = (new Long(s).longValue());
			return ((val >= this.leftValue) && (val <= this.rightValue));
		default:
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		switch (this.patternType) {
		case STAR:
			return "*";
		case NUMERIC_VALUE:
			return Long.toHexString(this.leftValue) + "";
		case RANGE_SPECIFICATION:
			return "[" + Long.toHexString(this.leftValue) + "-"
					+ Long.toHexString(this.rightValue) + "]";
		case CROSS:
			return "X";
		default:
			return null;
		}
	}

}
