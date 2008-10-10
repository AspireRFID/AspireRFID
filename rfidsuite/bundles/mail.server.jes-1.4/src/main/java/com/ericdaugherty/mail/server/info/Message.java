/******************************************************************************
 * $Workfile: Message.java $
 * $Revision: 1.2 $
 * $Author: edaugherty $
 * $Date: 2003/10/01 19:30:24 $
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

package com.ericdaugherty.mail.server.info;

//Java imports
import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Simple bean class that represents a POP3 Message used in the User class
 * and POP3 Service.
 * 
 * @author Eric Daugherty
 */
public class Message {

    //***************************************************************
    // Variables
    //***************************************************************

    /** Logger Category for this class. */
    private static Log log = LogFactory.getLog( Message.class );

    private File messageLocation;
    private boolean deleted = false;

    //***************************************************************
    // Public Interface
    //***************************************************************
    
    //***************************************************************
    //JavaBean Methods

    public File getMessageLocation(){ return messageLocation; }

    public void setMessageLocation(File messageLocation){ this.messageLocation = messageLocation; }
    
    public long getMessageSize(){ return messageLocation.length(); }

    public boolean isDeleted(){ return deleted; }

    public void setDeleted(boolean deleted){ log.debug( "Setting is deleted to: " + deleted ); this.deleted = deleted; }
	
	public String getUniqueId() { 
		String location = messageLocation.getAbsolutePath();
		
		int begin = location.lastIndexOf( "pop" ) + 3;
		int end = location.lastIndexOf( ".jmsg" );
		
		return location.substring( begin, end );
	}

}
//EOF