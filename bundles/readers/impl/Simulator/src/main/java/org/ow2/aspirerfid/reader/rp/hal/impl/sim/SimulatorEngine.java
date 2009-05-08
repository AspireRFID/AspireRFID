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

/*
 * Created on 2005
 */
package org.ow2.aspirerfid.reader.rp.hal.impl.sim;


/**
 * A simulator implementation within the Simulator Framework must implement this interface.
 * 
 * @author Roland Schneider
 * @author Anna Wojtas, Marcel Bihr, Lukas Blunschi
 *
 */
public interface SimulatorEngine {
	
	/**
	 * Initializes the Simulator.
	 * This method will be called by the SimulatorController after the instantiation of a SimulatorEngine.
	 * 
	 * @param controller The reference to the caller
	 */
	public void initialize(SimulatorController controller, String propFile) throws Exception;

}
