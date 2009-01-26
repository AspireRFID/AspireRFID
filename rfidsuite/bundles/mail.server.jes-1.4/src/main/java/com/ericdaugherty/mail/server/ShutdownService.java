/******************************************************************************
 * $Workfile: Mail.java $
 * $Revision: 1.3 $
 * $Author: edaugherty $
 * $Date: 2003/10/15 19:01:01 $
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

package com.ericdaugherty.mail.server;

//Log imports
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * This thread runs when the JVM is shutdown.
 *
 * @author Eric Daugherty
 */
public class ShutdownService implements Runnable {

    /** Logger */
    private Log log = LogFactory.getLog( this.getClass() );

    /**
     * Runs when the JVM is shutdown.  Stops all threads gracefully.
     */
    public void run() {

        log.warn( "Server is shutting down." );

        try {
            Mail.shutdown();

            log.warn( "Server shutdown complete." );
        }
        catch (Exception e) {
            log.error("Failed to terminate properly", e);
        }
    }
}
