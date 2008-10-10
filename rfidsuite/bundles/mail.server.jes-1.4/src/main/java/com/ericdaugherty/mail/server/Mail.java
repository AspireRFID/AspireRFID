/******************************************************************************
 * $Workfile: Mail.java $
 * $Revision: 1.4 $
 * $Author: edaugherty $
 * $Date: 2003/10/15 19:06:23 $
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

//Java imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ericdaugherty.mail.server.configuration.ConfigurationManager;
import com.ericdaugherty.mail.server.services.general.ServiceListener;
import com.ericdaugherty.mail.server.services.pop3.Pop3Processor;
import com.ericdaugherty.mail.server.services.smtp.SMTPProcessor;
import com.ericdaugherty.mail.server.services.smtp.SMTPSender;

/**
 * This class is the entrypoint for the Mail Server application.  It creates
 * threads to listen for SMTP and POP3 connections.  It also handles the
 * configuration information and initialization of the User subsystem.
 *
 * @author Eric Daugherty
 */
public class Mail implements BundleActivator {

    //***************************************************************
    // Variables
    //***************************************************************

    //Threads

    private static ServiceListener popListener;
    private static ServiceListener smtpListener;
    private static SMTPSender smtpSender;
    private static ShutdownService shutdownService;

    /** The SMTP sender thread */
    private static Thread smtpSenderThread;

    /** The ShutdownService Thread.  Started when the JVM is shutdown. */
    private static Thread shutdownServiceThread;

    /** Logger Category for this class.  This variable is initialized once
     * the main logging system has been initialized */
    private static Log log = null;
    
	public void start(BundleContext bundleContext) {
        // Perform the basic application startup.  If anything goes wrong here,
        // we need to abort the application.
        try {

        	// load the configuration file.	
            URL confUrl = bundleContext.getBundle().getResource("conf.properties");
            System.out.println("URL de conf.properties : "+confUrl);
            InputStream is = null;
            try {
				is = confUrl.openStream();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            Properties prop = new Properties();
            try {
				prop.load(is);
			} catch (FileNotFoundException e) {
				System.err.println("There is no conf.properties file in your bundle.");
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            // Initialize the logging mechanism.  We want to do this before we do
            // anything else.
            initializeLogging((String)prop.getProperty("log.conf"));

            // Initialize the Configuration Manager.
            ConfigurationManager configurationManager = ConfigurationManager.initialize( prop );

            //Start the threads.
            int port;
            int executeThreads = configurationManager.getExecuteThreadCount();

            //Start the Pop3 Thread.
            port = configurationManager.getPop3Port();
            if( log.isDebugEnabled() ) log.debug( "Starting POP3 Service on port: " + port );
            popListener = new ServiceListener( port, Pop3Processor.class, executeThreads );
            new Thread( popListener, "POP3" ).start();

            //Start SMTP Threads.
            port = configurationManager.getSmtpPort();
            if( log.isDebugEnabled() ) log.debug( "Starting SMTP Service on port: " + port );
            smtpListener = new ServiceListener( port, SMTPProcessor.class, executeThreads );
            new Thread( smtpListener, "SMTP" ).start();

            //Start the SMTPSender thread (This thread actually delivers the mail recieved
            //by the SMTP threads.
            smtpSender = new SMTPSender();
            smtpSenderThread = new Thread( smtpSender, "SMTPSender" );
            smtpSenderThread.start();

            //Initialize ShutdownService
            shutdownService = new ShutdownService();
            shutdownServiceThread = new Thread(shutdownService);
            Runtime.getRuntime().addShutdownHook( shutdownServiceThread );
        }
        catch( RuntimeException runtimeException ) {
            System.err.println( "The application failed to initialize." );
            System.err.println( runtimeException.getMessage() );
            runtimeException.printStackTrace();
            //System.exit( 0 );
        }
	}

	public void stop(BundleContext BundleContext) {
		shutdown();
	}

    //***************************************************************
    // Public Interface
    //***************************************************************

    /**
     * Provides a 'safe' way for the application to shut down.  It will attempt
     * to stop the running threads.  If the threads to not stop within 60 seconds,
     * the application will force the threads to stop by calling System.exit();
     */
    public static void shutdown() {

        log.warn( "Shutting down Mail Server.  Server will shut down in 60 seconds." );

        popListener.shutdown();
        smtpListener.shutdown();
        smtpSender.shutdown();

        try{
            smtpSenderThread.join(10000);
        }
        catch (InterruptedException ie)
        {
            log.error("Was interrupted while waiting for thread to die");
        }

        log.info("Thread gracefully terminated");
        smtpSenderThread = null;
    }
 
    
    //***************************************************************
    // Private Interface
    //***************************************************************
    
    
    /**
     * This method is responsible for initialzing the logging mechanism used 
     * by this application.  All required information is loaded from the
     * configuration file.
     */
    private void initializeLogging(String logConfigurationPath) {
   
        File logConfigurationFile = new File( logConfigurationPath );

        if( !logConfigurationFile.exists() ) {
            System.err.println("No log configuration file");
        }
        else
        {
        	PropertyConfigurator.configureAndWatch(logConfigurationFile.getAbsolutePath());
            log = LogFactory.getLog( Mail.class );
            log.info( "Logger using log4j.");
        }
    }
}
