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
 * This exception signals a reader hardware problem and is
 * thrown by several of the HAL controller methods.
 * 
 * @author Matthias Lampe, lampe@acm.org
 */
public class HardwareException extends HardwareAbstractionException {
	
	private static final long serialVersionUID = 1L;


	/**
	 * Constructor.
	 */
	public HardwareException() {
		super();
	}

	/**
	 * Constructor specifying a message.
	 * 
	 * @param message
	 *            The message
	 */
	public HardwareException(String message) {
		super(message);
	}

   /**
    * Constructor using a cause.
    * 
    * @param cause
    *            The cause
    */
   public HardwareException(Throwable cause) {
      super(cause);
   }

	/**
	 * Constructor specifying a message and a cause.
	 * 
	 * @param message
	 *            The message
	 * @param cause
	 *            The cause
	 */
	public HardwareException(String message, Throwable cause) {
		super(message, cause);
	}

}
