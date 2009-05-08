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

package org.ow2.aspirerfid.reader.rp.impl.hal;

/**
 * The class is a data structure to store information that describes a memory bank
 * such as size and read/write access. 
 * 
 * @author 	Matthias Lampe, lampe@acm.org
 */
public class MemoryBankDescriptor {
	
	//---- Fields -----------------------------------------------------------

	/** Size of the memory bank in number of bytes */
	protected int size;

	/** Flag that indicates if read access is allowed */
	protected boolean readAccess;
	
	/** Flag that indicates if write access is allowed */
	protected boolean writeAccess;


	//---- Constructor(s) ---------------------------------------------------

	/**
	 * creates a memory bank descriptor with the given paramters.
	 *
	 * @param size the size of the memory bank in number of bytes
	 * @param readAccess flag that indicates if read access is allowed
	 * @param writeAccess flag that indicates if write access is allowed
	 * 
	 */
	public MemoryBankDescriptor(int size, boolean readAccess, boolean writeAccess) {
		this.size = size;
		this.readAccess = readAccess;
		this.writeAccess = writeAccess;
	}


	//---- Methods ----------------------------------------------------------

	/**
	 * gets the size of the memory bank in number of bytes.
	 * 
	 * @return the size of the memory bank in number of bytes
	 */
	public int getSize() {
		return size;
	}

	/**
	 * gets the flag that indicates if read access is allowed.
	 * 
	 * @return the flag that indicates if read access is allowed
	 */
	public boolean isReadAccess() {
		return readAccess;
	}


	/**
	 * gets the flag that indicates if write access is allowed.
	 * 
	 * @return the flag that indicates if write access is allowed
	 */
	public boolean isWriteAccess() {
		return writeAccess;
	}

}
