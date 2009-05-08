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
 * The operational status represents the actual state for an object.
 */
public enum OperationalStatus {
	
	/*
	 * The reader lacks the ability to determine the operational status of the
	 * object. The real status may be "up" ,"down", or "other".
	 */
	UNKNOWN,
	
	/*
	 * The object is neither fully up, nor fully down. For aggregate objects
	 * (i.e. objects that encompass other objects, as a source may encompass
	 * multiple read points) this includes the case where all sub-objects are
	 * administratively down and the aggregate object is administratively up.
	 */
    OTHER,
	
	/*
	 * The object is operational. For aggregate objects (i.e. objects that
	 * encompass other objects, as a source may encompass multiple read points)
	 * this means that all objects that are administratively up are either
	 * operationally up or operationally unknown.
	 */
    UP,
    
    /*
	 * The object is not operational. For aggregate objects (i.e. objects that
	 * encompass other objects, as a source may encompass multiple read points)
	 * this means that all objects that are administratively up are either
	 * operationally down or operationally unknown.
	 */
    DOWN,
    
    /*
	 * A wildcard operator. The comparison "ANY"==S is true for all status
	 * enumerations S. This is never returned by the reader, but is useful to
	 * hosts describing events. For example a host may desire an alert when the
	 * reader state changed from "UP" to "ANY".
	 */
    ANY;
	
	
	/**
	 * Converts this <code>OperationalStatus</code> instance to an
	 * <code>int</code> value.
	 * 
	 * @return <code>int</code> representation of this
	 *         <code>OperationalStatus</code> instance
	 */
	public final int toInt() {
		OperationalStatus[] values = OperationalStatus.values();
		for (int index = 0; index < values.length; index++) {
			if (values[index] == this) return index + 1;
		}
		return -1;
	}
	
	/**
	 * Converts an <code>int</code> value to an
	 * <code>OperationalStatus instance</code>. If the <code>int</code>
	 * value is out of range it returns <code>null</code>.
	 * 
	 * @param i
	 *            The <code>int</code> value
	 * @return The <code>OperationalStatus</code> representation of the
	 *         <code>int</code> value
	 */
	public static final OperationalStatus intToEnum(final int i) {
		try {
			return OperationalStatus.values()[i - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
    
}
