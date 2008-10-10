/******************************************************************************
 * $Workfile: ConfigurationProcessor.java $
 * $Revision: 1.6 $
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

package com.ericdaugherty.mail.server.configuration.console;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.IOException;

import com.ericdaugherty.mail.server.services.general.ConnectionProcessor;
import com.ericdaugherty.mail.server.configuration.ConfigurationManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class provides a console based interface to the configuration
 * information.
 *
 * @author Eric Daugherty
 */
public class ConfigurationProcessor implements ConnectionProcessor {

    //***************************************************************
    // Variables
    //***************************************************************

    /** Logger Category for this class. */
    private static Log log = LogFactory.getLog( ConfigurationProcessor.class );

    /** The ConfigurationManager */
    private static ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    /** Indicates if this thread should continue to run or shut down */
    private boolean running = true;

    /** The server socket used to listen for incoming connections */
    private ServerSocket serverSocket;

    /** Socket connection to the client */
    private Socket socket;

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
    public void setSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Entry point for this thread to handle a connection.
     */
    public void run() {
        try {
            //Set the socket to timeout every second so it does not
            //just block forever.
            serverSocket.setSoTimeout( 1000 );
        }
        catch( SocketException se ) {
            log.fatal( "Error initializing Socket Timeout in SMTPProcessor" );
        }

        while( running ) {
            try {
                socket = serverSocket.accept();

                //Prepare the input and output streams.
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader( socket.getInputStream() ));

                handleCommands();
            }
            catch( InterruptedIOException iioe ) {
                //This is fine, it should time out every second if
                //a connection is not made.
            }
            //If any exception gets to here uncaught, it means we should just disconnect.
            catch( Throwable e ) {
                log.debug( "Disconnecting Exception:", e );
                log.info( "Disconnecting" );
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
    }

    /**
     * Notifies the service to stop processing connections.
     */
    public void shutdown() {
        running = false;
    }

    //***************************************************************
    // Public Interface
    //***************************************************************

    private void handleCommands() {
        out.write( "Welcome to the JES Configuration Console." );
    }
}