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
package org.ow2.aspirerfid.wires;

import java.util.Hashtable;

/**
 * This is the POJO used to send the tag information through the wire.
 * 
 * @author <a href="maroulix@gmail.com">Perisanidi Maroula</a>
 * @version 08/07/2008 1.0.0
 */
public class RFIDTagRead {
	/**
	 * Hashtable containing the tag information.
	 */
	private Hashtable props = null;

	/**
	 * Constructor.
	 */
	public RFIDTagRead() {
		props = new Hashtable();
	}

	/**
	 * @param key
	 *            The name of one of the tag's characteristics. Usually a
	 *            constant.
	 * @param value
	 *            The value associated with the preceding characteristic.
	 */
	public void put(Object key, Object value) {
		props.put(key, value);
	}

	/**
	 * @param key
	 *            The name of one of the tag's characteristics. Usually a
	 *            constant.
	 * @return The value associated with the tag characteristic given as input.
	 */
	public Object get(Object key) {
		return props.get(key);
	}
}
