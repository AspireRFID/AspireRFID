/******************************************************************************
 * $Workfile: User.java $
 * $Revision: 1.5 $
 * $Author: edaugherty $
 * $Date: 2003/11/24 21:09:26 $
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

//Log imports
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

//Local imports
import com.ericdaugherty.mail.server.configuration.ConfigurationManager;
import com.ericdaugherty.mail.server.configuration.PasswordManager;

/**
 * Represents a user object.  This class is responsible for providing all
 * information about a specific user and their mailbox.
 * 
 * @author Eric Daugherty
 */
public class User {

    //***************************************************************
    // Variables
    //***************************************************************

    private String username;
    private String domain;
    private String password;
    private EmailAddress[] forwardAddresses;

    private Message[] messages = null;

    private ConfigurationManager configurationManager = null;

    /** Logger */
    private Log log = LogFactory.getLog( this.getClass() );

    //***************************************************************
    // Constructor
    //***************************************************************

    /**
     * Creates a new user with the full username (user and domain).
     *
     * @param address User's full email address
     * @param configurationManager Reference to the ConfigurationManager
     */
    public User( EmailAddress address, ConfigurationManager configurationManager )
    {
        username = address.getUsername().trim().toLowerCase();
        domain = address.getDomain().trim().toLowerCase();

        this.configurationManager = configurationManager;
    }

    /**
     * Creates a new user with the full username (user and domain).
     *
     * @param address User's full email address
     */
    public User( EmailAddress address )
    {
        this( address, ConfigurationManager.getInstance() );
    }

    //***************************************************************
    // Public Interface
    //***************************************************************

    /**
     * Returns true if and only if the specified plain text password's hash
     * value matches the hashed password for this user.
     *
     * @param plainTextPassword the password to validate.
     * @return true if it matches.
     */
    public boolean isPasswordValid( String plainTextPassword )
    {
        if( log.isDebugEnabled() ) log.debug( "Authenticating User: " + getFullUsername() );
        boolean result = getPassword().equals( PasswordManager.encryptPassword( plainTextPassword ) );
        if( log.isDebugEnabled() && !result ) log.debug( "Authentication Failed for User: " + getFullUsername() );

        return result;
    }

    //***************************************************************
    //JavaBean Methods
    
    public String getUsername(){ return username; }

    public void setUsername(String username){ this.username = username; }
    
    public String getFullUsername() { return getFullUsername( username, domain ); }

    public String getDomain(){ return domain; }

    public void setDomain(String domain){ this.domain = domain; }

    public String getPassword(){ return password; }

    public void setPassword(String password){ this.password = password; }

    public EmailAddress[] getForwardAddresses(){ return forwardAddresses; }

    public void setForwardAddresses(EmailAddress[] forwardAddresses){ this.forwardAddresses = forwardAddresses; }

    /**
     * Returns an array of Strings that represent email addresses to deliver
     * email to this user to.  If the forwardAddresses is not null or empty,
     * this will return the forwardAddresses array.  Otherwise, this will return
     * the user's email address.
     *
     * @return array of strings that represent email addresses.
     */
    public EmailAddress[] getDeliveryAddresses() {

        if( forwardAddresses != null && forwardAddresses.length > 0 ) {
            return forwardAddresses;
        }
        else {
            return new EmailAddress[] { new EmailAddress( getUsername(),  getDomain() ) };
        }
    }

    /**
     * Returns an array of Message objects that represents all messaged
     * stored for this user.
     */
    public Message[] getMessages() {
        
        if( messages == null ) {
            
            File directory = getUserDirectory();
            
            String[] fileNames = directory.list();
            
            int numMessage = fileNames.length;
            
            messages = new Message[numMessage];
            Message currentMessage;
            
            for( int index = 0; index < numMessage; index++ ) {
                currentMessage = new Message();
                currentMessage.setMessageLocation( new File( directory, fileNames[index] ) );
                messages[index ] = currentMessage;
            }
        }
        return messages;
    }
    
    /**
     * Gets the specified message.  Message numbers are 1 based.  
     * This method counts on the calling method to verify that the
     * messageNumber actually exists.
     */
    public Message getMessage( int messageNumber ) {
        
        return getMessages()[messageNumber - 1];
    }
         
    /**
     * Gets the total number of messages currently stored for this user.
     */
    public long getNumberOfMessage() {
        
        return getMessages().length;
    }
    
    /**
     * Gets the total size of the messages currently stored for this user.
     */
    public long getSizeOfAllMessage() {
        
        Message[] message = getMessages();
        
        long totalSize = 0;
        
        for ( int index = 0; index < message.length; index++) {
            totalSize += message[index].getMessageLocation().length();
        }
        
        return totalSize;
    }
    
    /**
     * Gets the user's directory as a file.  This method also verifies
     * that that directory exists.
     */
    public File getUserDirectory() {

        String mailDirectory = configurationManager.getMailDirectory();
        File directory = new File( mailDirectory + File.separator + "users" + File.separator + getFullUsername() );

        if ( !directory.exists() ) { 
            if( log.isInfoEnabled() ) log.info( "Directory for user: " + getFullUsername() + "does not exist, creating..." );
            directory.mkdirs();
        }

        if( !directory.isDirectory() ) {
            log.error( "User Directory: " + directory.getAbsolutePath() + " for user: " + getFullUsername() + " does not exist." );
            throw new RuntimeException( "User's Directory path: " + directory.getAbsolutePath() + " is not a directory!" );
        }
        
        return directory;
    }
    
    /**
     * This method removes any cached message information this user may have stored
     */
    public void reset() {

        messages = null;
    }
    
    //***************************************************************
    // Private Interface
    //***************************************************************
        
    /**
     * Converts a username and domaing to the combined username.
     */
    private static String getFullUsername( String username, String domain ) {

        return username + "@" + domain;
    }

    


}
