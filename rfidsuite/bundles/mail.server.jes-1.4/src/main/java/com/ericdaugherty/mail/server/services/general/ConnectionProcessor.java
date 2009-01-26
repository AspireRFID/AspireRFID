/******************************************************************************
 * $Workfile: ConnectionProcessor.java $
 * $Revision: 1.1 $
 * $Author: edaugherty $
 * $Date: 2003/01/23 22:41:28 $
 *
 ******************************************************************************
 * This program is a 100% Java Email Server.
 ******************************************************************************
 * Copyright (C) 2001, Eric Daugherty
 * All rights reserved.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 ******************************************************************************
 * For current versions and more information, please visit:
 * http://www.ericdaugherty.com/java/mail
 * 
 * or contact the author at:
 * java@ericdaugherty.com
 * 
 ******************************************************************************
 * This program is based on the CSRMail project written by Calvin Smith.
 * http://crsemail.sourceforge.net/
 *****************************************************************************/

package com.ericdaugherty.mail.server.services.general;

//Java imports
import java.net.ServerSocket;

/**
 * Defines the interface for all classes that will handle a connection.  This
 * interface is used by ServiceListener to interact with the Connection Processors.
 * 
 * @author Eric Daugherty
 */
public interface ConnectionProcessor extends Runnable {
    
    /**
     * Sets the socket used to communicate with the client.
     */
    public void setSocket( ServerSocket serverSocket );

    /**
     * Notifies the service to stop processing connections.
     */
    public void shutdown();
}
