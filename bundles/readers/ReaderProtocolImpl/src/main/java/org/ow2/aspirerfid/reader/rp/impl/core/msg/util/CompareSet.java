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

package org.ow2.aspirerfid.reader.rp.impl.core.msg.util;

import java.util.Collection;
import java.util.HashSet;

/**
 * This class implements a helper class to have a Set in which
 * all contained Strings are in upper case letters and can be
 * compared case-insensitive.
 * 
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 *
 */
public class CompareSet extends HashSet {

	
	/**
	 * the serialVersionUID
	 */
	private static final long serialVersionUID = 3772594236692502458L;

	/**
	 * Constructs a new, empty set; the backing HashMap instance has default
	 * initial capacity (16) and load factor (0.75).
	 */
	public CompareSet() {
		super();
	}

	/**
	 * Constructs a new set containing the elements in the specified collection.
	 * The HashMap is created with default load factor (0.75) and an initial
	 * capacity sufficient to contain the elements in the specified collection.
	 * 
	 * @param c
	 *            the collection whose elements are to be placed into this set.
	 * 
	 * @throws NullPointerException
	 *             if the specified collection is null.
	 */
	public CompareSet(Collection c) throws NullPointerException {
		super(c);
	}

	/**
	 * Constructs a new, empty set; the backing HashMap instance has the
	 * specified initial capacity and the specified load factor.
	 * 
	 * @param initialCapacity
	 *            the initial capacity of the hash map.
	 * @param loadFactor
	 *            the load factor of the hash map.
	 * @throws IllegalArgumentException
	 *             if the initial capacity is less than zero, or if the load
	 *             factor is nonpositive.
	 */
	public CompareSet(int initialCapacity, float loadFactor) throws IllegalArgumentException {
		super(initialCapacity, loadFactor);
	}

	/**
	 * Constructs a new, empty set; the backing HashMap instance has the
	 * specified initial capacity and default load factor, which is 0.75.
	 * 
	 * @param initialCapacity
	 *            the initial capacity of the hash table.
	 * @throws IllegalArgumentException
	 *             if the initial capacity is less than zero.
	 */
	public CompareSet(int initialCapacity) throws IllegalArgumentException {
		super(initialCapacity);
	}
	
	/**
	 * Adds the specified element to this set if it is not already present.<br />
	 * The specified String is transformed to upper case letters to allow
	 * case insensitive String comparisons.
	 * 
	 * @param s String to be added to this set. 
	 * @return <code>true</code> if this set did not already contain the specified element. 
	 */
	public boolean add(String s) {
		return super.add(s.toUpperCase());
	}

	/**
	 * Returns true if this set contains the specified element. The comparison
	 * is done case-insensitive by transforming all Strings to upper case letters.
	 * 
	 * @param s String whose presence in this set is to be tested.
	 * @return <code>true</code> if this set contains the specified element.
	 */
	public boolean contains(String s) {
		return super.contains(s.toUpperCase());
	}	
}
