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
 * This exception is thrown if the called method is not supported by the current
 * hardware abstraction implementation.
 */
public class ReadPointNotFoundException extends HardwareException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public ReadPointNotFoundException() {
		super();
	}

	/**
	 * Constructor specifying a message.
	 * 
	 * @param message
	 *            The message
	 */
	public ReadPointNotFoundException(String message) {
		super(message);
	}

   /**
    * Constructor using a cause.
    * 
    * @param cause
    *            The cause
    */
   public ReadPointNotFoundException(Throwable cause) {
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
	public ReadPointNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
