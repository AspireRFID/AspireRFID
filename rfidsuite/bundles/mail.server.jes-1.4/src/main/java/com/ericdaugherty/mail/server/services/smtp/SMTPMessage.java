/******************************************************************************
 * $Workfile: SMTPMessage.java $
 * $Revision: 1.4 $
 * $Author: edaugherty $
 * $Date: 2003/12/24 02:26:35 $
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

package com.ericdaugherty.mail.server.services.smtp;

//Java imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ericdaugherty.mail.server.configuration.ConfigurationManager;
import com.ericdaugherty.mail.server.errors.InvalidAddressException;
import com.ericdaugherty.mail.server.info.EmailAddress;

/**
 * Bean class used to store incoming SMTP message on disk (via Java Serialization)
 * for delivery by the SMTPSender thread.
 *
 * @author Eric Daugherty
 */
public class SMTPMessage implements Serializable {

    //***************************************************************
    // Constants
    //***************************************************************

    private static final String DELIMITER = "\r\n";

    private static final String FILE_VERSION = "1.0";

    //***************************************************************
    // Variables
    //***************************************************************

    /** Logger */
    private static Log log = LogFactory.getLog( SMTPMessage.class.getName() );

    /** The ConfigurationManager */
    private static ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    private Date timeReceived;
    private Date scheduledDelivery;
    private int deliveryAttempts;
    private EmailAddress fromAddress;
    private List toAddresses = new ArrayList();
    private List dataLines = new ArrayList();
    private File messageLocation = null;
    private long size = 0;

    //***************************************************************
    // Constructor
    //***************************************************************

    /**
     * Initializes a new message with the current time.
     */
    public SMTPMessage() {
        Date now = new Date();
        timeReceived = now;
        scheduledDelivery = now;
        deliveryAttempts = 0;
    }

    //***************************************************************
    // Public Interface
    //***************************************************************

    public Date getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(Date timeReceived) {
        this.timeReceived = timeReceived;
    }

    public Date getScheduledDelivery() {
        return scheduledDelivery;
    }

    public void setScheduledDelivery(Date scheduledDelivery) {
        this.scheduledDelivery = scheduledDelivery;
    }

    public int getDeliveryAttempts() {
        return deliveryAttempts;
    }

    public void setDeliveryAttempts(int deliveryAttempts) {
        this.deliveryAttempts = deliveryAttempts;
    }

    public EmailAddress getFromAddress(){ return fromAddress; }

    public void setFromAddress(EmailAddress fromAddress){ this.fromAddress = fromAddress; }

    public List getToAddresses() { return toAddresses; }

    public void setToAddresses( List toAddresses ) { this.toAddresses = toAddresses; }

    public void addToAddress( EmailAddress toAddress ) { toAddresses.add( toAddress ); }

    public List getDataLines() { return dataLines; }

    public void addDataLine( String line ) {
        size += line.length();
        dataLines.add( line );
    }

    public File getMessageLocation(){ return messageLocation; }

    public void setMessageLocation(File messageLocation){ this.messageLocation = messageLocation; }

    public long getSize() {
        if( size == 0 )
        {
            Iterator i = dataLines.iterator();
            while( i.hasNext() )
            {
                size += ( (String)i.next() ).length();
            }
        }

        return size;
    }

    /**
     * Moves the message to the 'failed' Directory.
     */
    public void moveToFailedFolder() throws Exception {
        File failedDir = new File( configurationManager.getMailDirectory() + File.separator + "failed" );

        // If the directory does not exist, create it.
        if( !failedDir.exists() ) {
            log.info( "failed directory does not exist.  Creating: " + failedDir.getAbsolutePath() );
            if( !failedDir.mkdirs() )
            {
                log.error( "Error creating failed directory: " + failedDir.getAbsolutePath() + ".  No incoming mail will be accepted!" );
                throw new Exception( "Unable to create failed Directory." );
            }
        }

       File messageLocation = getMessageLocation();
       String newLocation= configurationManager.getMailDirectory() + File.separator + "failed" + File.separator + messageLocation.getName();
       if( !messageLocation.renameTo( new File(newLocation ) ) )
       {
           log.error( "moveToFailedFolder failed.  Message was not renamed." );
           throw new Exception( "moveToFailedFolder failed.  Message was not renamed." );
       }
    }

    /**
     * Saves the message to the Mail Spool Directory.
     */
    public void save() throws Exception {

        File smtpDirectory = new File( configurationManager.getMailDirectory() + File.separator + "smtp" );

        // If the directory does not exist, create it.
        if( !smtpDirectory.exists() ) {
            log.info( "SMTP Mail directory does not exist.  Creating: " + smtpDirectory.getAbsolutePath() );
            if( !smtpDirectory.mkdirs() )
            {
                log.error( "Error creating SMTP Mail directory: " + smtpDirectory.getAbsolutePath() + ".  No incoming mail will be accepted!" );
                throw new Exception( "Unable to create SMTP Mail Directory." );
            }
        }

        File messageFile = getMessageLocation();

        if( messageFile == null ) {
            messageFile = File.createTempFile( "smtp", ".ser", smtpDirectory );
            setMessageLocation( messageFile );
        }

        FileWriter writer = new FileWriter( messageFile );
        try
        {
            writer.write( FILE_VERSION );
            writer.write( DELIMITER );
            writer.write( getFromAddress().toString() );
            writer.write( DELIMITER );
            writer.write( flattenAddresses( getToAddresses() ) );
            writer.write( DELIMITER );
            writer.write( String.valueOf( getTimeReceived().getTime() ) );
            writer.write( DELIMITER );
            writer.write( String.valueOf( getScheduledDelivery().getTime() ) );
            writer.write( DELIMITER );
            writer.write( String.valueOf( getDeliveryAttempts() ) );
            writer.write( DELIMITER );
            List dataLines = getDataLines();
            for( int index = 0; index < dataLines.size(); index++ )
            {
                writer.write( (String) dataLines.get( index ) );
                writer.write( DELIMITER );
            }
        }
        finally
        {
            try
            {
                if( writer != null )
                {
                    writer.close();
                }
            }
            catch( IOException e )
            {
                log.warn( "Unable to close spool file for SMTPMessage " + messageLocation.getAbsolutePath() );
            }
        }
    }

    /**
     * Loads an individual message from disk.
     *
     * @param filename the filename of the message.
     * @throws IOException thrown if there is any IO error while reading the message.
     */
    public static SMTPMessage load( String filename ) throws Exception {

        File messageFile = new File( filename );
        FileReader fileReader = new FileReader( messageFile );
        BufferedReader reader = new BufferedReader( fileReader );

        try
        {
            String version = reader.readLine();
            if( log.isDebugEnabled() ) log.debug( "Loading SMTP Message " + messageFile.getName() + " version " + version );
            if( !FILE_VERSION.equals( version ) )
            {
                log.error( "Error loading SMTP Message.  Can not handle file version: " + version );
                throw new IOException( "Invalid file version: " + version );
            }
            // Initialize a new message with the right file location
            SMTPMessage message = new SMTPMessage();
            message.setMessageLocation( messageFile );

            // Load each variable
            message.setFromAddress( new EmailAddress( reader.readLine() ) );
            message.setToAddresses( inflateAddresses( reader.readLine() ) );
            message.setTimeReceived( new Date( Long.parseLong( reader.readLine() ) ) );
            message.setScheduledDelivery( new Date( Long.parseLong( reader.readLine() ) ) );
            message.setDeliveryAttempts( Integer.parseInt( reader.readLine() ) );

            String inputLine = reader.readLine();
            while( inputLine != null )
            {
                message.addDataLine( inputLine );
                inputLine = reader.readLine();
            }

            return message;
        }
        catch( InvalidAddressException invalidAddressException )
        {
            throw new IOException( "Unable to parse the address from the stored file." );
        }
        catch( NumberFormatException numberFormatException )
        {
            throw new IOException( "Unable to parse the data from the stored file into a number.  " + numberFormatException.toString() );
        }
        finally
        {
            if( reader != null )
            {
                reader.close();
            }
        }
    }

    /**
     * Converts a <code>List</code> of <code>EmailAddress</code>
     * instances into a comma delimited string.
     *
     * @param addresses Collection of Address instances.
     * @return Comma delimited String of the addresses.
     */
    private static String flattenAddresses( Collection addresses )
    {
        StringBuffer toAddresses = new StringBuffer();
        EmailAddress address;
        Iterator addressIterator = addresses.iterator();
        while( addressIterator.hasNext() )
        {
            address = (EmailAddress) addressIterator.next();
            toAddresses.append( address.toString() );
            toAddresses.append( "," );
        }

        // Remove the last comma.
        toAddresses.deleteCharAt( toAddresses.length() - 1 );

        return toAddresses.toString();
    }

    /**
     * Converts a comma delimited string of addresses into a
     * <code>List</code> of <code>EmailAddress</code> instances.
     *
     * @param addresses Comma delimited String of addresses.
     * @return List of Address instances.
     */
    private static List inflateAddresses( String addresses )
    {
        StringTokenizer addressTokenizer = new StringTokenizer( addresses, "," );
        List addressList = new ArrayList();
        EmailAddress address;

        try
        {
            while( addressTokenizer.hasMoreTokens() )
            {
                address = new EmailAddress( addressTokenizer.nextToken() );
                addressList.add( address );
            }

            return addressList;
        }
        catch( InvalidAddressException invalidAddressException )
        {
            log.error( "Unable to parse to address read from database.  Full String is: " + addresses, invalidAddressException );
            throw new RuntimeException( "Error parsing address.  Message Delivery Failed." );
        }
    }
}
//EOF