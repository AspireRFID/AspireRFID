/******************************************************************************
 * $Workfile: Pop3Processor.java $
 * $Revision: 1.4 $
 * $Author: edaugherty $
 * $Date: 2003/11/15 21:40:30 $
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

package com.ericdaugherty.mail.server.services.pop3;

//Java imports
import java.net.*;
import java.io.*;

//Log imports
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

//Local imports
import com.ericdaugherty.mail.server.info.*;
import com.ericdaugherty.mail.server.services.general.DeliveryService;
import com.ericdaugherty.mail.server.services.general.ConnectionProcessor;
import com.ericdaugherty.mail.server.configuration.ConfigurationManager;

/**
 * Handles an incoming Pop3 connection.  See rfc 1939 for details.
 *
 * @author Eric Daugherty
 */
public class Pop3Processor extends Thread implements ConnectionProcessor {

    //***************************************************************
    // Variables
    //***************************************************************

    /** Logger Category for this class. */
    private static Log log = LogFactory.getLog( Pop3Processor.class.getName() );

    /** The ConfigurationManager */
    private static ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    /** Indicates if this thread should continue to run or shut down */
    private boolean running = true;

    /** The server socket used to listen for incoming connections */
    private ServerSocket serverSocket;

    /** Socket connection to the client */
    private Socket socket;

    /** The IP address of the client */
    private String clientIp;

    /** The user currently logged in */
    private User user = null;

    /** Writer to sent data to the client */
    private PrintWriter out;
    /** Reader to read data from the client */
    private BufferedReader in;

    //***************************************************************
    // Public Interface
    //***************************************************************

    /**
     * Sets the socket used to communicate with the client.
     */
    public void setSocket( ServerSocket serverSocket ) {

        this.serverSocket = serverSocket;
    }

    /**
     * Entrypoint for the Thread, this method handles the interaction with
     * the client socket.
     */
    public void run() {

        try {
            //Set the socket to timeout every 10 seconds so it does not
            //just block forever.
            serverSocket.setSoTimeout( 1000 );
        }
        catch( SocketException se ) {
            log.fatal( "Error initializing Socket Timeout in Pop3Processor" );
        }

        while( running ) {
            try {
                socket = serverSocket.accept();

                //Prepare the input and output streams.
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader( socket.getInputStream() ));

                InetAddress remoteAddress = socket.getInetAddress();
                clientIp = remoteAddress.getHostAddress();
                if( log.isInfoEnabled() ) { log.info( remoteAddress.getHostName() + "(" + clientIp + ") socket connected via POP3." ); }

                //Output the welcome message.
                write( WELCOME_MESSAGE );

                //Forces the client to property authenticate.
                user = authenticate();
                user.reset();

                //Parses the input for commands and delegates to the appropriate methods.
                handleCommands();
            }
            catch( InterruptedIOException iioe ) {
                //This is fine, it should time out every 10 seconds if
                //a connection is not made.
            }
            //If any exception gets to here uncaught, it means we should just disconnect.
            catch( Throwable e ) {
                log.debug( "Disconnecting Exception:", e );
                log.info( "Disconnecting" );

                //Unlock the user's mailbox
                if( user != null ) {
                    EmailAddress userAddress = new EmailAddress( user.getUsername(), user.getDomain() );
                    DeliveryService.getDeliveryService().unlockMailbox( userAddress );
                }

                try {
                    write( MESSAGE_DISCONNECT );
                }
                catch( Exception e1 ) {
                    log.debug( "Error sending disconnect message.", e1 );
                    //Nothing to do.
                }
                try {
                    if( socket != null ) {
                        socket.close();
                    }
                }
                catch( IOException ioe ) {
                    log.debug( "Error disconnecting.", ioe );
                    //Nothing to do.
                }
            }
        }
        log.warn( "Pop3Processor shut down gracefully" );
    }

    /**
     * Notifies this thread to stop processing and exit.
     */
    public void shutdown() {
        log.warn( "Shutting down Pop3Processor." );
        running = false;
    }

    //***************************************************************
    // Private Interface
    //***************************************************************

    /**
     * Checks to verify that the command is not a quit command.  If it is,
     * the current state is finalized (all messaged marked as deleted are
     * actually deleted) and closes the connection.
     */
    private void checkQuit( String command ) {

        if( command.equals( COMMAND_QUIT ) ) {
            log.debug( "User has QUIT the session." );

            //Delete the messages marked as deleted from disk
            if( user != null ) {
                Message[] messages = user.getMessages();
                int numMessage = messages.length;
                Message currentMessage = null;

                for( int index = 0; index < numMessage; index++ ) {
                    currentMessage = messages[index];
                    if( currentMessage.isDeleted() ) {
                        messages[index].getMessageLocation().delete();
                    }
                }
            }

            // TODO Find a better way to handle user logoffs.
            throw new RuntimeException();
        }

    }

    /**
     * The user must authenticate before moving on to enter
     * more commands.  This method will listen to incoming
     * commands until the user either successfully autheticates
     * or quits.
     */
    private User authenticate() {

        //Reusable Variables.
        String inputString;
        String command;
        String argument;

        String username = "";
        String domain = "";
        String password = "";
        EmailAddress address = null;
        DeliveryService deliveryService = DeliveryService.getDeliveryService();

        //Get the username from the client.
        boolean userAccepted = false;

        while( !userAccepted ) {
            inputString = read();

            command = parseCommand( inputString );
            argument = parseArgument( inputString );

            //Check to see if they sent the user command.
            if( command.equals( COMMAND_USER ) ) {

                //Make sure they sent a username
                if( argument.equals( "" ) ) {
                    write( MESSAGE_TOO_FEW_ARGUMENTS );
                }
                else {
                    int atIndex = argument.indexOf( "@" );

                    //Verify that the username contains the domain.
                    if( atIndex == -1 ) {
                        write( MESSAGE_NEED_USER_DOMAIN );
                    }
                    else {
                        //Accept the user, and proceed to get the password.
                        username = argument.substring( 0, atIndex );
                        domain = argument.substring( atIndex + 1 );

                        address = new EmailAddress( username, domain );

                        //Check to see if the user's mailbox is locked
                        if( deliveryService.isMailboxLocked( address ) ) {
                            write( MESSAGE_USER_MAILBOX_LOCKED );
                        }
                        else {
                            write( MESSAGE_USER_ACCEPTED + argument );
                            userAccepted = true;
                        }
                    }
                }
            }
            else {
                write( MESSAGE_INVALID_COMMAND + command );
            }
        }

        //The user has been accepted, now get the password.
        boolean passwordAccepted = false;

        while( !passwordAccepted ) {
            inputString = read();

            command = parseCommand( inputString );
            argument = parseArgument( inputString );

            //Check to see if they sent the user command.
            if( command.equals( COMMAND_PASS ) ) {

                //Make sure they sent a password
                if( argument.equals( "" ) ) {
                    write( MESSAGE_TOO_FEW_ARGUMENTS );
                }
                else {
                    password = argument;
                    passwordAccepted = true;
                }
            }
            else {
                write( MESSAGE_INVALID_COMMAND + command );
            }
        }

        User user = configurationManager.getUser( address );
        if( user != null && user.isPasswordValid( password ) )
        {
            deliveryService.ipAuthenticated( clientIp );
            deliveryService.lockMailbox( address );
            write( MESSAGE_LOGIN_SUCCESSFUL );
            if( log.isInfoEnabled() ) log.info( "User: " + address.getAddress() + " logged in successfully.");
            return user;
        }
        else
        {
            //The login failed, display a message to the user and disconnect.
            write( MESSAGE_INVALID_LOGIN + username );
            log.info( "Login failed for user: " + username + "@" + domain );
            throw new RuntimeException();
        }
    }

    /**
     * Handles all the commands related the the retrieval of mail.
     */
    private void handleCommands() {

        //Reusable Variables.
        String inputString;
        String command;
        String argument;

        //This just runs until a SystemException is thrown, which
        //signals us to disconnect.
        while( true ) {

            inputString = read();

            command = parseCommand( inputString );
            argument = parseArgument( inputString );

            //Identify the command and call the appropriate helper method.
            if( command.equals( COMMAND_STAT ) ) {
                handleStat();
            }
            else if( command.equals( COMMAND_LIST ) ) {
                handleList( argument );
            }
            else if( command.equals( COMMAND_RETR ) ) {
                handleRetr( argument );
            }
            else if( command.equals( COMMAND_DELE ) ) {
                handleDele( argument );
            }
            else if( command.equals( COMMAND_NOOP ) ) {
                write( "+OK" );
            }
            else if( command.equals( COMMAND_RSET ) ) {
                handleRset();
            }
			else if( command.equals( COMMAND_TOP ) ) {
				handleTop( argument );
			}
			else if( command.equals( COMMAND_UIDL ) ) {
				handleUidl( argument );
			}
            else {
                write( MESSAGE_INVALID_COMMAND + command );
            }
        }
    }

    /**
     * Handles the 'stat' command, which returns the total number of message
     * and the total size of those message.
     */
    private void handleStat() {

        write( "+OK " + user.getNumberOfMessage() + " " + user.getSizeOfAllMessage() );
    }

    /**
     * Handles the 'list' command, which returns the total number of messages and
     * size along with a list of the individual message sizes.
     */
    private void handleList( String argument ) {

        if( argument.equals( "" ) ) {
            long numMessages = user.getNumberOfMessage();
            long sizeMessage = user.getSizeOfAllMessage();

            write( "+OK " + numMessages + " messages (" + sizeMessage + " octets)" );

            for( int index = 0; index < numMessages; index++ ) {
                write( (index + 1) + " " + user.getMessage( index + 1 ).getMessageLocation().length() );
            }
            write( "." );
        }
        else {
            int messageNumber = 0;

            try {
                messageNumber = Integer.parseInt( argument );
            }
            catch( NumberFormatException nfe ) {
                write( MESSAGE_NOT_A_NUMBER );
                return;
            }

            long numMessages = user.getNumberOfMessage();

            if( messageNumber > numMessages || user.getMessage( messageNumber ).isDeleted() ) {
                write( MESSAGE_NO_SUCH_MESSAGE );
                return;
            }

            write( "+OK " + messageNumber + " " + user.getMessage( messageNumber ).getMessageLocation().length() );
        }
    }

    /**
     * Sends the specified email message to the client.
     */
    private void handleRetr( String argument ) {

        int messageNumber = 0;

        try {
            messageNumber = Integer.parseInt( argument );
        }
        catch( NumberFormatException nfe ) {
            write( MESSAGE_NOT_A_NUMBER );
            return;
        }

        long numMessages = user.getNumberOfMessage();

        if( log.isDebugEnabled() ) {
            log.debug( "Is Msg Deleted: " + user.getMessage( messageNumber ).isDeleted() );
            log.debug( "Message: " + messageNumber + " of " + numMessages );
        }
        if( messageNumber > numMessages || user.getMessage( messageNumber ).isDeleted() ) {
            write( MESSAGE_NO_SUCH_MESSAGE );
            return;
        }

        write( MESSAGE_OK );

        BufferedReader fileIn = null;
        try {
            //Open an reader to read the file.
            fileIn = new BufferedReader( new FileReader( user.getMessage( messageNumber ).getMessageLocation() ) );

            //Write the file to the client.
            String currentLine = fileIn.readLine();
            while (currentLine != null) {
                write( currentLine );
                currentLine = fileIn.readLine();
            }
            write( "." );
        }
        catch( FileNotFoundException fnfe ) {
            log.error( "Requested message for user " + user.getFullUsername() + " could not be found on disk.", fnfe );
        }
        catch( IOException ioe ) {
            log.error( "Error retrieving message.", ioe );
            write( "-ERR Error retrieving message" );
        }
        finally {
            //Make sure the input stream gets closed.
            try {
                if( fileIn != null ) {
                    fileIn.close();
                }
            }
            catch( IOException ioe ) {
                //Nothing to do...
            }
        }
    }

    /**
     * Marks the specified message for deletion.  The message will only
     * be deleted if the user later enters the QUIT command, as per the
     * spec.
     */
    private void handleDele( String argument ) {

        int messageNumber = 0;

        try {
            messageNumber = Integer.parseInt( argument );
        }
        catch( NumberFormatException nfe ) {
            write( MESSAGE_NOT_A_NUMBER );
            return;
        }

        long numMessages = user.getNumberOfMessage();

        if( messageNumber > numMessages ) {
            write( MESSAGE_NO_SUCH_MESSAGE );
        }
        else if(  user.getMessage( messageNumber ).isDeleted() ) {
            write( MESSAGE_ALREADY_DELETED );
        } else {
            user.getMessage( messageNumber ).setDeleted( true );
            write( MESSAGE_OK );
        }
    }

    /**
     * Unmarks all deleted messages.
     */
    private void handleRset() {

        Message[] messages = user.getMessages();
        int numMessage = messages.length;

        for( int index = 0; index < numMessage; index++ ) {
            messages[index].setDeleted( false );
        }

        write( MESSAGE_OK );
    }

	/**
	 * Returns the header and first x lines for the
	 * specified message.
	 */
	private void handleTop( String argument ) {

		log.debug( "In Top" );

		int messageNumber = 0;
		int numLines = 0;

		int spaceIndex = argument.indexOf( " " );
		if( spaceIndex == -1 ) {
			write( MESSAGE_TOO_FEW_ARGUMENTS );
			return;
		}
		String arg1 = argument.substring( 0, spaceIndex ).trim();
		String arg2 = argument.substring( spaceIndex + 1 ).trim();

        try {
            messageNumber = Integer.parseInt( arg1 );
			numLines = Integer.parseInt( arg2 );
        }
        catch( NumberFormatException nfe ) {
            write( MESSAGE_NOT_A_NUMBER );
            return;
        }

        long numMessages = user.getNumberOfMessage();

        if( log.isDebugEnabled() ) {
            log.debug( "Is Msg Deleted: " + user.getMessage( messageNumber ).isDeleted() );
            log.debug( "Message: " + messageNumber + " of " + numMessages );
        }
        if( messageNumber > numMessages || user.getMessage( messageNumber ).isDeleted() ) {
            write( MESSAGE_NO_SUCH_MESSAGE );
            return;
        }

        write( MESSAGE_OK );

        BufferedReader fileIn = null;
        try {
            //Open an reader to read the file.
            fileIn = new BufferedReader( new FileReader( user.getMessage( messageNumber ).getMessageLocation() ) );

            //Write the Message Header.
            String currentLine = fileIn.readLine();
            while (currentLine != null && !currentLine.equals( "" ) ) {
				write( currentLine );
                currentLine = fileIn.readLine();
            }

			//Write an empty line to seperate header from body.
			write( currentLine );
            currentLine = fileIn.readLine();

			//Write the requested number of lines from the body of the
			//message, or until the entire message has been written.
			int index = 0;
			while( index < numLines && currentLine != null ) {
				write( currentLine );
				currentLine = fileIn.readLine();
				index++;
			}

            write( "." );
        }
        catch( FileNotFoundException fnfe ) {
            log.error( "Requested message for user " + user.getFullUsername() + " could not be found on disk.", fnfe );
        }
        catch( IOException ioe ) {
            log.error( "Error retrieving message.", ioe );
            write( "-ERR Error retrieving message" );
        }
        finally {
            //Make sure the input stream gets closed.
            try {
                if( fileIn != null ) {
                    fileIn.close();
                }
            }
            catch( IOException ioe ) {
                //Nothing to do...
            }
        }
	}

	/**
	 * Returns the unique id of the specified message, or all the unique
	 * ids of the non-deleted messages.
	 */
	private void handleUidl( String argument ) {

		//Return all messages unique ids
		if( argument == null || argument.length() == 0 ) {

			long numMessages = user.getNumberOfMessage();
			Message message;

			write( MESSAGE_OK );

			//Write out each non-deleted message id.
			for( int index = 0; index < numMessages; index++ ) {
				message = user.getMessage( index + 1 );
				if( !message.isDeleted() ) {
					write( (index + 1) + " " + message.getUniqueId() );
				}
			}

			write( "." );
		}
		//Ouput a single messages unique id.
		else {

			int messageNumber = 0;

			try {
				messageNumber = Integer.parseInt( argument );
			}
			catch( NumberFormatException nfe ) {
				write( MESSAGE_NOT_A_NUMBER );
				return;
			}

			long numMessages = user.getNumberOfMessage();

			if( messageNumber > numMessages || user.getMessage( messageNumber ).isDeleted() ) {
				write( MESSAGE_NO_SUCH_MESSAGE );
				return;
			}

			write( MESSAGE_OK + " " + messageNumber + " " + user.getMessage( messageNumber ).getUniqueId() );
		}
	}

    /**
     * Reads a line from the input stream and returns it.
     */
    private String read() {
        try {
            String inputLine = in.readLine().trim();
            //Log the input, unless it is a password.
            if( log.isDebugEnabled() && !inputLine.startsWith( "PASS" ) ) {
                log.debug( "Read Input: " + inputLine );
            }
            return inputLine;
        }
        catch( IOException ioe ) {
            log.error( "Error reading from socket.", ioe );
            throw new RuntimeException();
        }
    }

    /**
     * Writes the specified output message to the client.
     */
    private void write( String message ) {
        if( log.isDebugEnabled() ) { log.debug( "Writing Output: " + message ); }
        out.print( message + "\r\n" );
        out.flush();
    }

    /**
     * Parses the input stream for the command.  The command is the
     * begining of the input stream to the first space.  If there is
     * space found, the entire input string is returned.
     * <p>
     * This method converts the returned command to uppercase to allow
     * for easier comparison.
     * <p>
     * Additinally, this method checks to verify that the quit command
     * was not issued.  If it was, a SystemException is thrown to terminate
     * the connection.
     */
    private String parseCommand( String inputString ) {

        int index = inputString.indexOf( " " );

        if( index == -1 ) {
            String command = inputString.toUpperCase();
            checkQuit( command );
            return command;
        }
        else {
            String command = inputString.substring( 0, index ).toUpperCase();
            checkQuit( command );
            return command;
        }
    }

    /**
     * Parses the input stream for the argument.  The argument is the
     * text starting afer the first space until the end of the inputstring.
     * If there is no space found, an empty string is returned.
     * <p>
     * This method does not convert the case of the argument.
     */
    private String parseArgument( String inputString ) {

        int index = inputString.indexOf( " " );

        if( index == -1 ) {
            return "";
        }
        else {
            return inputString.substring( index + 1 ).trim();
        }
    }

    //***************************************************************
    // Constants
    //***************************************************************

    //Message Constants
    //General Message
    private static final String WELCOME_MESSAGE = "+OK EricDaugherty's Java Pop Server Ready";
    private static final String MESSAGE_DISCONNECT = "+OK Pop server signing off.";
    private static final String MESSAGE_OK = "+OK";
    private static final String MESSAGE_INVALID_COMMAND = "-ERR Unknown command: ";
    private static final String MESSAGE_TOO_FEW_ARGUMENTS = "-ERR Too few arguments for this command.";

    //Authentication Messages
    private static final String MESSAGE_NEED_USER_DOMAIN = "-ERR User names must contain the username and domain.  ex: \"root@mydomain.com\"";
    private static final String MESSAGE_USER_ACCEPTED = "+OK Password required for ";
    private static final String MESSAGE_LOGIN_SUCCESSFUL = "+OK Login successful";
    private static final String MESSAGE_USER_MAILBOX_LOCKED = "-ERR User's Mailbox is locked";
    private static final String MESSAGE_INVALID_LOGIN = "-ERR Password supplied is incorrect for user: ";

    //Other Messages
    private static final String MESSAGE_NOT_A_NUMBER = "-ERR Command requires a valid number as an argument.";
    private static final String MESSAGE_NO_SUCH_MESSAGE = "-ERR No such message.";
    private static final String MESSAGE_ALREADY_DELETED = "-ERR Message already deleted.";

    //Command Constants
    private static final String COMMAND_QUIT = "QUIT";
    private static final String COMMAND_USER = "USER";
    private static final String COMMAND_PASS = "PASS";
    private static final String COMMAND_STAT = "STAT";
    private static final String COMMAND_LIST = "LIST";
    private static final String COMMAND_RETR = "RETR";
    private static final String COMMAND_DELE = "DELE";
    private static final String COMMAND_NOOP = "NOOP";
    private static final String COMMAND_RSET = "REST";
	private static final String COMMAND_TOP = "TOP";
	private static final String COMMAND_UIDL = "UIDL";

}

