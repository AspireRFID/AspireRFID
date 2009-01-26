/******************************************************************************
 * $Workfile: PasswordManager.java $
 * $Revision: 1.1 $
 * $Author: edaugherty $
 * $Date: 2003/10/01 19:30:25 $
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

package com.ericdaugherty.mail.server.configuration;

//Java imports
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Creates encrypted passwords and validating passwords.
 * 
 * @author Eric Daugherty
 */
public class PasswordManager {
    
    //***************************************************************
    // Public Interface
    //***************************************************************

    /**
     * Creates a one-way has of the specified password.  This allows passwords to be
     * safely stored in the database without any way to retrieve the original value.
     * 
     * @param password the string to encrypt.
     * 
     * @return the encrypted password, or null if encryption failed.
     */
    public static String encryptPassword( String password ) {
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
        
            //Create the encrypted Byte[]
            md.update( password.getBytes() );
            byte[] hash = md.digest();
            
            //Convert the byte array into a String
            
            StringBuffer hashStringBuf = new StringBuffer();
            String byteString;
            int byteLength;
            
            for( int index = 0; index < hash.length; index++ ) {
                
                byteString = String.valueOf( hash[index ] + 128 );
                
                //Pad string to 3.  Otherwise hash may not be unique.
                byteLength = byteString.length();
                switch( byteLength ) {
                case 1:
                    byteString = "00" + byteString;
                    break;
                case 2:
                    byteString = "0" + byteString;
                    break;
                }
                hashStringBuf.append( byteString );
            }
            
            return hashStringBuf.toString();
        }
        catch( NoSuchAlgorithmException nsae ) {
            System.out.println( "Error getting password hash - " + nsae.getMessage() );
            return null;
        }
    }
}
//EOF