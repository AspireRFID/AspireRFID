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

import org.apache.log4j.Logger;


/**
 * This exception is the root exception for all exeption that
 * are thrown in the hardware abstraction layer.
 * 
 * This exception is thrown by HAL controller methods of a objects within the hardware abstraction layer if the
 * attempt to execute a command failed. The idea is to propagate just a single
 * kind of error to the layers above. This simplifies the error handling for
 * other layers.
 * 
 * The error itself can be specified in more detail by using the following codes:
 * 
 * <code>serviceCode</code>: Indicates the kind of service, which caused the exception

 * <code>readerProtocolErrorCode</code> The RP conform error code..
 * 
 * @author Matthias Lampe, lampe@acm.org
 */

public class HardwareAbstractionException extends Exception {
	
	//----------------------constants--------------------------------
	
	private static final long serialVersionUID = 1L;
	
	//----------------------fields-----------------------------------
	
	/**
	 * The logger.
	 */
	protected static Logger log = Logger.getLogger(HardwareAbstractionException.class); 	
	
	//----------------------constructors----------------------------
	
	/**
	 * Constructor.
	 */
	public HardwareAbstractionException() {
		super();
	}

	/**
	 * Constructor specifying a message.
	 * 
	 * @param message
	 *            The message
	 */
	public HardwareAbstractionException(String message) {
		super(message);
	}

   /**
    * Constructor using a cause.
    * 
    * @param cause
    *            The cause
    */
   public HardwareAbstractionException(Throwable cause) {
      super();
   }
   
	/**
	 * Constructor specifying a message and a cause.
	 * 
	 * @param message
	 *            The message
	 * @param cause
	 *            The cause
	 */
	public HardwareAbstractionException(String message, Throwable cause) {
		super(message);
	}

}
