/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt;

/**
 * The administrative status represents the host's desired state for an object.
 */
public enum AdministrativeStatus {
	
	/*
	 * The host desires that the operational status be "up"
	 */
	UP,
	
	/*
	 * The host desires that the operational status be "down"
	 */
	DOWN;
	
	
	/**
	 * Converts this <code>AdministrativeStatus</code> instance to an
	 * <code>int</code> value.
	 * 
	 * @return <code>int</code> representation of this
	 *         <code>AdministrativeStatus</code> instance
	 */
	public final int toInt() {
		AdministrativeStatus[] values = AdministrativeStatus.values();
		for (int index = 0; index < values.length; index++) {
			if (values[index] == this) return index + 1;
		}
		return -1;
	}
	
	/**
	 * Converts an <code>int</code> value to an
	 * <code>AdministrativeStatus instance</code>. If the <code>int</code>
	 * value is out of range it returns <code>null</code>.
	 * 
	 * @param i
	 *            The <code>int</code> value
	 * @return The <code>AdministrativeStatus</code> representation of the
	 *         <code>int</code> value
	 */
	public static final AdministrativeStatus intToEnum(final int i) {
		try {
			return AdministrativeStatus.values()[i - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
}
