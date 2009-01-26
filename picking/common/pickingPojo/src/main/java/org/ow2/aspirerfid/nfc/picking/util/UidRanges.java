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

import java.util.Vector;

/**
 * Class that holds the uid characteristics.
 * 
 * @author Perisanidi Maroula
 */
public class UidRanges {

	/**
	 * The name of the item.
	 */
	private final String name;

	/**
	 * The quantity of items with the same uid pattern.
	 */
	private int quantity;

	/**
	 * The vector containing the ranges of this uid.
	 */
	private final Vector vector;

	/**
	 * @param name
	 *            The name of the item.
	 * @param ranges
	 *            The vector containing the ranges of this item's uid.
	 * @param quantity
	 *            The quantity of items with the same uid pattern.
	 */
	public UidRanges(String name, Vector ranges, String quantity) {
		this.quantity = Integer.parseInt(quantity);
		this.vector = ranges;
		this.name = name;
	}

	/**
	 * @return The name of the item.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return The length of the tag's uid in its hexadecimal form.
	 */
	public int getPatternLength() {
		int size = 0;
		for (int i = 0; i < this.vector.size(); i++) {
			Range range = (Range) this.vector.elementAt(i);
			size = size + range.getRangeLength();
		}
		return size;
	}

	/**
	 * @return The quantity of items with the same uid pattern.
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * @return The vector containing the ranges of this uid.
	 */
	public Vector getVector() {
		return this.vector;
	}

	/**
	 * @param quantity
	 *            The new quantinty of items with the same uid pattern.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
