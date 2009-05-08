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

import org.ow2.aspirerfid.reader.rp.impl.hal.transponder.IDType;

/**
 * The class is a data structure to store information that describes a tag
 * such as tag id type and memory structure.
 * 
 * @author 	Matthias Lampe, lampe@acm.org
 */
public class TagDescriptor {
	
	//---- Fields -----------------------------------------------------------

	/** The descriptions of the memory */
	protected MemoryDescriptor memoryDescriptor;

	/** The type of the ID of the tag */
	private IDType idType = null;
	

	//---- Constructor(s) ---------------------------------------------------

	/**
	 * creates a tag descriptor with the given paramters.
	 *
	 * @param idType the type of the ID of the tag
	 * @param memoryDescriptor the description of the memory 
	 */
	public TagDescriptor(IDType idType, MemoryDescriptor memoryDescriptor) {
		this.idType = idType;
		this.memoryDescriptor = memoryDescriptor;
	}


	//---- Methods ----------------------------------------------------------

	/**
	 * gets the descriptions of the memory.
	 * 
	 * @return the memory descriptor
	 */
	public MemoryDescriptor getMemoryDescriptor() {
		return memoryDescriptor;
	}

	/**
	 * Gets the type of the ID of the tag.
	 * 
	 * @return The type of the ID of the tag
	 */
	IDType getIdType() {
		return idType;
	}

}
