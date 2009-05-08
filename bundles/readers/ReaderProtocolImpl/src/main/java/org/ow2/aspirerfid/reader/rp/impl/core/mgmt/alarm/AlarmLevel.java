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

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm;

/**
 * The level of a reported alarm. When the host requests an alarm it defines the
 * alarm level (see <code>AlarmControl.setLevel</code>). This additional
 * information is then returned by the reader along with each occurrence of that
 * alarm. It is expected that some management systems may choose to filter based
 * on the alarm level. The alarm levels are modeled after The syslog Protocol
 * IETF draft.
 */
public enum AlarmLevel {
	
	/*
	 * System is unusable
	 */
	EMERGENCY,
	
	/*
	 * Action must be taken immediately
	 */
	ALERT,
	
	/*
	 * Critical conditions
	 */
	CRITICAL,
	
	/*
	 * Error conditions
	 */
	ERROR,
	
	/*
	 * Warning conditions
	 */
	WARNING,
	
	/*
	 * Normal but significant conditions
	 */
	NOTICE,
	
	/*
	 * Informational messages
	 */
	INFORMATIONAL,
	
	/*
	 * Debug level messages
	 */
	DEBUG;
	
	
	/**
	 * Converts this <code>AlarmLevel</code> instance to an <code>int</code>
	 * value.
	 * 
	 * @return <code>int</code> representation of this <code>AlarmLevel</code>
	 *         instance
	 */
	public final int toInt() {
		AlarmLevel[] values = AlarmLevel.values();
		for (int index = 0; index < values.length; index++) {
			if (values[index] == this) return index;
		}
		return -1;
	}
	
	/**
	 * Converts an <code>int</code> value to an
	 * <code>AlarmLevel instance</code>. If the <code>int</code> value is
	 * out of range it returns <code>null</code>.
	 * 
	 * @param i
	 *            The <code>int</code> value
	 * @return The <code>AlarmLevel</code> representation of the
	 *         <code>int</code> value
	 */
	public static final AlarmLevel intToEnum(final int i) {
		try {
			return AlarmLevel.values()[i];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
}
