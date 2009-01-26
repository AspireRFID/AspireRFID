/*
 *  Copyright (C) Aspire
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.ow2.aspirerfid.nfc.picking.util;

/**
 * Class that holds the ranges of a uid pattern.
 * 
 * @author Perisanidi Maroula
 * 
 */
public class Range {

	/**
	 * Integer containing the maximum decimal range.
	 */
	private final int max;

	/**
	 * String containing the maximum range in hexa.
	 */
	private final String maximum;

	/**
	 * Integer containing the minimum decimal range.
	 */
	private final int min;

	/**
	 * @param minimum
	 *            String containing the minimum range in hexa.
	 * @param maximum
	 *            String containing the maximum range in hexa.
	 */
	public Range(String minimum, String maximum) {
		this.min = Integer.parseInt(minimum, 16);
		this.maximum = maximum;
		this.max = Integer.parseInt(maximum, 16);
	}

	/**
	 * @return An integer containing the maximum decimal range.
	 */
	public int getMax() {
		return this.max;
	}

	/**
	 * @return An integer containing the minimum decimal range.
	 */
	public int getMin() {
		return this.min;
	}

	/**
	 * @return The length of the String containing the maximum range in hexa.
	 */
	public int getRangeLength() {
		int size = 0;
		size = this.maximum.length();
		return size;
	}

	/**
	 * @param tagUidPart
	 *            Part of the uid of the tag that has the same length (in its
	 *            hexadecimal form) as the ranges against which it is compared.
	 * @return A boolean confirming if the given part of the uid is within
	 *         range.
	 */
	public boolean isInRange(int tagUidPart) {
		if ((tagUidPart <= this.max) && (tagUidPart >= this.min)) {
			return true;
		}
		return false;
	}
}
