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
 * This interface should be implemented in order to be able to receive tag
 * identification observations asynchronously.
 * 
 * @author Matthias Lampe, lampe@acm.org
 * @author Christian Floerkemeier, floerkem@mit.edu
 */
public interface AsynchronousIdentifyListener {
	
	/**
	 * This callback method is called whenever an asynchronous identify has been performed.
	 * 
	 * @param observation Contains the information about the tag identification.
	 */
	void asynchronousIdentifyPerformed(Observation observation);

}
