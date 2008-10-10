/******************************************************************************
 * $Workfile: EmailAddress.java $
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
import java.io.Serializable;

//Local imports
import com.ericdaugherty.mail.server.errors.InvalidAddressException;

/**
 * Represents a full email address, including username and domain.  This
 * class performs conversions between a full email address, and a username
 * and domain.
 */
public class EmailAddress implements Serializable {

    //***************************************************************
    // Public Interface
    //***************************************************************

    //***************************************************************
    // Constructor(s)

    /**
     * Creates an empty email address.  This is possible form
     * SMTP messages that have no MAIL FROM address.
     */
    public EmailAddress() {

       _isEmpty = true;
    }

    /**
     * Creates a new instance of this class using a single string
     * that contains the full email address (joe@mydomain.com).
     */
    public EmailAddress( String fullAddress ) throws InvalidAddressException {

       setFullAddress( fullAddress );
    }

    /**
     * Creates a new instance of this class using a username string
     * and an address string.
     */
    public EmailAddress( String username, String domain ) {
        setUsername( username );
        setDomain( domain );
    }

    /**
     * Override tostring to return the full address
     */
    public String toString() {
        return getAddress();
    }

    //***************************************************************
    //JavaBean Methods

    public String getUsername(){
        if( _isEmpty ) {
            return "";
        }
        else {
            return _username;
        }
    }

    public void setUsername(String username) {
        _isEmpty = false;
        _username = username.trim().toLowerCase();
    }

    public String getDomain(){
        if( _isEmpty ) {
            return "";
        }
        else {
            return _domain;
        }
    }

    public void setDomain(String domain){
        _isEmpty = false;
        _domain = domain.trim().toLowerCase();
    }

    public String getAddress() {
        return getFullAddress( getUsername(), getDomain() );
    }

    public void setAddress( String fullAddress ) throws InvalidAddressException {
        setFullAddress( fullAddress );
    }

    //***************************************************************
    // Private Interface
    //***************************************************************

    /**
     * Combines a username and domain into a single email address.
     */
    private String getFullAddress( String username, String domain ) {

        if( _isEmpty ) {
            return "";
        }
        else {
            StringBuffer fullAddress = new StringBuffer( username );
            fullAddress.append( "@" );
            fullAddress.append( domain );

            return fullAddress.toString();
        }
    }

    /**
     * Parses a full address into a username and password for storage.
     */
    private void setFullAddress( String fullAddress ) throws InvalidAddressException {

        //Parse toAddress into username and domain.
        int index = fullAddress.indexOf( "@" );
        if( index == -1 ) {
            throw new InvalidAddressException();
        }

        setUsername( fullAddress.substring( 0, index ) );
        setDomain( fullAddress.substring( index + 1 ) );

        _isEmpty = false;
    }

    //***************************************************************
    // Variables
    //***************************************************************

    private String _username = "";
    private String _domain = "";
    private boolean _isEmpty = false;
}
//EOF