/******************************************************************************
 * $Source: /cvsroot/javaemailserver/src/com/ericdaugherty/mail/server/configuration/ConfigurationManager.java,v $
 * $Revision: 1.10 $
 * $Author: edaugherty $
 * $Date: 2003/12/24 02:26:34 $
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

import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import com.ericdaugherty.mail.server.info.User;
import com.ericdaugherty.mail.server.info.EmailAddress;
import com.ericdaugherty.mail.server.errors.InvalidAddressException;

/**
 * Provides a centralized repository for all configuration
 * information.
 * <p>
 * All configuration information should be retrieved here for
 * every use.  The ConfigurationManager will reload
 * configuration changes dynamically.
 * <p>
 * Classes may cache the reference to the ConfigurationManager instance,
 * as only one will ever be created.
 *
 * @author Eric Daugherty
 */
public class ConfigurationManager implements ConfigurationParameterContants {

    //***************************************************************
    // Variables
    //***************************************************************

    private static ConfigurationManager instance;

    /** The file reference to the mail.conf configuration file */
    private File generalConfigurationFile;

    /** The timestamp for the mail.conf file when it was last loaded */
    private long generalConfigurationFileTimestamp;

    /** The file reference to the user.conf configuration file */
    private File userConfigurationFile;

    /** The timestamp for the user.conf file when it was last loaded */
    private long userConfigurationFileTimestamp;

    /** Tracks whether the user configuration properties were changed during loading */
    private boolean userConfModified = false;

    /** Logger */
    private Log log = LogFactory.getLog( this.getClass() );

    //
    // Configuration Properties
    //

    /** The root directory used to store the incoming and outgoing messages. */
    private String mailDirectory;

    /** Array of domains that the SMTP server should accept mail for local delivery */
    private String[] localDomains;

    /** The number of threads to use for each listener */
    private int executeThreadCount;

    /** The local IP address to lisen on.  Null for all addresses */
    private InetAddress listenAddress;

    /** The port the SMTP server listens on. */
    private int smtpPort;

    /** The port the POP3 server listens on */
    private int pop3Port;

    /** The timeout length for authenticated ip addresses */
    private long authenticationTimeoutMilliseconds;

    /** True if POP Before SMTP is enabled */
    private boolean enablePOPBeforeSMTP;

    /** IP Addresses that are allowed to relay mail. */
    private String[] relayApprovedIpAddresses;

    /** Email Addresses that are allowed to relay mail. */
    private String[] relayApprovedEmailAddresses;

    /** True if all outgoing mail should go though the default server */
    private boolean defaultSmtpServerEnabled;

    /** The servers to send all outgoing mail through */
    private String[] defaultSmtpServers;

    /**
     * True if email to the local domain for a non-existent
     * user should be delivered to the default user.
     */
    private boolean defaultUserEnabled;

    /** The user to delivery default email to */
    private EmailAddress defaultUser;

    /** The number of seconds to wait between delivery attempts */
    private long deliveryIntervalSeconds;

    /**
     * The max number of delivery attempts before message is considered
     * 'undeliverable' and moved to 'failed' folder
     */
    private int deliveryAttemptThreshold;

    /** The maximum size (in megabytes) allowed for email attachments. */
    private int maximumMessageSize;

    /** A Map of Users keyed by their full username */
    private Map users;

    //***************************************************************
    // Constructor
    //***************************************************************

    /**
     * Initialize the file path.  Enforces the Singleton pattern.
     *
     * @param generalConfigurationFile the file to load the general configuration from.
     * @param userConfigurationFile the file to load the user configuration from.
     */
    private ConfigurationManager( File generalConfigurationFile, File userConfigurationFile )
    {
        this.generalConfigurationFile = generalConfigurationFile;
        this.userConfigurationFile = userConfigurationFile;
    }

    //***************************************************************
    // Static Methods
    //***************************************************************

    /**
     * Initializes the ConfigurationManager to use the specified
     * directory.  This method should only be called once during
     * startup, and then never again.  The file path can not
     * be re-initialized!
     *
     * @param configurationDirectory the directory that contains mail.conf and user.conf
     * @return returns the singleton instance of the ConfigurationManager.
     * @throws RuntimeException thrown if called more than once, the file does not exist,
     * or there is an error loading the file.
     */
    public static synchronized ConfigurationManager initialize( Properties prop) throws RuntimeException
    {
        String generalConfigFilename = (String)prop.getProperty("mail.conf");
        String userConfigFilename = (String)prop.getProperty("user.conf");

        // Make sure we are not already configured.
        if( instance != null )
        {
        	// FIXME
        	return instance;	// allowed multiple call of initialize for debug ... 
            //throw new RuntimeException( "Configurationmanager:initialize() called more than once!" );
        }

        // Verify the General config file exists.
        File generalConfigFile = new File(generalConfigFilename );
        if( !generalConfigFile.exists() || !generalConfigFile.isFile() )
        {
            throw new RuntimeException( "Invalid mail.conf ConfigurationFile! " + generalConfigFile.getAbsolutePath() );
        }

        // Verify the User config file exists.
        File userConfigFile = new File( userConfigFilename );
        if( !userConfigFile.exists() || !userConfigFile.isFile() )
        {
            throw new RuntimeException( "Invalid user.conf ConfigurationFile! " + userConfigFile.getAbsolutePath() );
        }

        // Go ahead and create the singleton instance.
        instance = new ConfigurationManager( generalConfigFile, userConfigFile );

        instance.setMailDirectory( (String)prop.getProperty("mail.directory"));

        // Load the properties from disk.
        instance.loadProperties();

        // Start the Watchdog Thread
        instance.new ConfigurationFileWatcher().start();

        return instance;
    }

    /**
     * Provides access to the singleton instance.
     *
     * @return the singleton instance.
     */
    public static synchronized ConfigurationManager getInstance()
    {
        if( instance == null )
        {
            throw new RuntimeException( "ConfigurationManager can not be accessed before it is initialized!" );
        }
        return instance;
    }

    //***************************************************************
    // Public Methods
    //***************************************************************

    public void loadProperties()
    {
        loadGeneralProperties();
        loadUserProperties();
    }

    //***************************************************************
    // Parameter Access Methods
    //***************************************************************

    /**
     * The root directory used to store the incoming and outgoing messages.
     *
     * @return String
     */
    public String getMailDirectory() {
        return mailDirectory;
    }

    /**
     * Get the max number of delivvery attempts before message is considered
     * 'undeliverable' and moved to 'failed' folder
     * @return int
     */
    public int getDeliveryAttemptThreshold() {
        return deliveryAttemptThreshold;
    }

    /** The maximum size (in megabytes) allowed for email attachments. */
    public int getMaximumMessageSize() {
        return maximumMessageSize;
    }

    /**
     * The root directory used to store the incoming and outgoing messages.
     *
     * @param mailDirectory String
     */
    public void setMailDirectory(String mailDirectory) {
        this.mailDirectory = mailDirectory;
    }

    /**
     * Array of domains that the SMTP server should accept mail for local delivery
     *
     * @return String array
     */
    public String[] getLocalDomains() {
        return localDomains;
    }

    /**
     * Checks the local domains to see if the specified
     * parameter matches.
     *
     * @param domain a domain to check.
     * @return true if and only if it matches exactly an existing domain.
     */
    public boolean isLocalDomain( String domain )
    {
        domain = domain.toLowerCase();
        if( localDomains != null && localDomains.length > 0 )
        {
            int numDomains = localDomains.length;
            for( int index = 0; index < numDomains; index++ )
            {
                if( localDomains[index].equals( domain ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Array of domains that the SMTP server should accept mail for local delivery
     *
     * @param localDomains String array
     */
    public void setLocalDomains(String[] localDomains) {
        this.localDomains = localDomains;
    }

    /**
     * The number of threads to use for each listener.
     *
     * @return int
     */
    public int getExecuteThreadCount() {
        return executeThreadCount;
    }

    /**
     * The number of threads to use for each listener.
     *
     * @param executeThreadCount int
     */
    public void setExecuteThreadCount(int executeThreadCount) {
        this.executeThreadCount = executeThreadCount;
    }

    /**
     * The local IP address to lisen on.  Null for all addresses
     *
     * @return null for all addresses.
     */
    public InetAddress getListenAddress() {
        return listenAddress;
    }

    /**
     * The port the SMTP server listens on.
     *
     * @return port number
     */
    public int getSmtpPort() {
        return smtpPort;
    }

    /**
     * The port the SMTP server listens on.
     *
     * @param smtpPort port number
     */
    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    /**
     * The port the POP3 server listens on.
     *
     * @return port number
     */
    public int getPop3Port() {
        return pop3Port;
    }

    /**
     * The port the POP3 server listens on.
     *
     * @param pop3Port port number
     */
    public void setPop3Port(int pop3Port) {
        this.pop3Port = pop3Port;
    }

    /**
     * Returns the specified user, or null if the user
     * does not exist.
     *
     * @param address the user's full email address.
     * @return null if the user does not exist.
     */
    public User getUser( EmailAddress address )
    {
        User user = (User) users.get( address.getAddress() );
        if( log.isInfoEnabled() && user == null ) log.info( "Tried to load non-existent user: " +  address.getAddress() );

        return user;
    }

    /** The timeout length for authenticated ip addresses */
    public long getAuthenticationTimeoutMilliseconds() {
        return authenticationTimeoutMilliseconds;
    }

    /** The timeout length for authenticated ip addresses */
    public void setAuthenticationTimeoutMinutes(long minutes) {
        this.authenticationTimeoutMilliseconds = minutes * 60 * 1000;
    }

    /** True if POP Before SMTP is a valid relay option */
    public boolean isEnablePOPBeforeSMTP() {
        return enablePOPBeforeSMTP;
    }

    /** True if POP Before SMTP is a valid relay option */
    public void setEnablePOPBeforeSMTP(boolean enablePOPBeforeSMTP) {
        this.enablePOPBeforeSMTP = enablePOPBeforeSMTP;
    }

    /** IP Addresses that are allowed to relay mail. */
    public String[] getRelayApprovedIpAddresses() {
        return relayApprovedIpAddresses;
    }

    /** IP Addresses that are allowed to relay mail. */
    public void setRelayApprovedIpAddresses(String[] relayApprovedIpAddresses) {
        this.relayApprovedIpAddresses = relayApprovedIpAddresses;
    }

    /** Email Addresses that are allowed to relay mail. */
    public String[] getRelayApprovedEmailAddresses() {
        return relayApprovedEmailAddresses;
    }

    /** Emails Addresses that are allowed to relay mail. */
    public void setRelayApprovedEmailAddresses(String[] relayApprovedEmailAddresses) {
        this.relayApprovedEmailAddresses = relayApprovedEmailAddresses;
    }

    /** True if all outgoing mail should go though the default server */
    public boolean isDefaultSmtpServerEnabled() {
        return defaultSmtpServerEnabled;
    }

    /** True if all outgoing mail should go though the default server */
    public void setDefaultSmtpServerEnabled(boolean defaultSmtpServerEnabled) {
        this.defaultSmtpServerEnabled = defaultSmtpServerEnabled;
    }

    /** The servers to send all outoing mail through */
    public String[] getDefaultSmtpServers() {
        return defaultSmtpServers;
    }

    /** The server to send all outoing mail through */
    public void setDefaultSmtpServers(String[] defaultSmtpServers) {
        this.defaultSmtpServers = defaultSmtpServers;
    }

    public boolean isDefaultUserEnabled() {
        return defaultUserEnabled;
    }

    public void setDefaultUserEnabled(boolean defaultUserEnabled) {
        this.defaultUserEnabled = defaultUserEnabled;
    }

    public EmailAddress getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(EmailAddress defaultUser) {
        this.defaultUser = defaultUser;
    }

    /** The number of seconds to wait between delivery attempts */
    public long getDeliveryIntervalSeconds() {
        return deliveryIntervalSeconds;
    }

    /** The number of milliseconds to wait between delivery attempts */
    public long getDeliveryIntervealMilliseconds()
    {
        return deliveryIntervalSeconds * 1000;
    }

    /** The number of seconds to wait between delivery attempts */
    public void setDeliveryIntervalSeconds(long deliveryIntervalSeconds) {
        this.deliveryIntervalSeconds = deliveryIntervalSeconds;
    }
    //***************************************************************
    // Private Methods
    //***************************************************************

    /**
     * Loads the properties file into the local variables for quick
     * access.
     */
    private void loadGeneralProperties()
    {
        Properties properties = new Properties();

        try {
            FileInputStream inputStream = new FileInputStream( generalConfigurationFile );
            properties.load( inputStream );
        }
        catch (IOException e) {
            // All checks should be done before we get here, so there better
            // not be any errors.  If so, throw a RuntimeException.
            throw new RuntimeException( "Error Loading Properties File!  Unable to continue Operation." );
        }

        //
        // Load the local domains
        //

        String domains = properties.getProperty( DOMAINS, "" );
        localDomains = tokenize( domains.trim().toLowerCase() );
        log.info( "Loaded " + localDomains.length + " local domains." );
        if( domains.length() == 0 )
        {
            throw new RuntimeException( "No Local Domains defined!  Can not run without local domains defined." );
        }

        //
        // Load the number of Execute Threads for each listener
        //

        //Load the number of execute threads to use for each ServiceListener.
        String threadsString = properties.getProperty( EXECUTE_THREADS, "5" );
        try {
            executeThreadCount = Integer.parseInt( threadsString );
        }
        catch( NumberFormatException nfe ) {
            log.warn( "Invalid value for property: " + EXECUTE_THREADS + ".  Using default value of 5.");
            executeThreadCount = 5;
        }

        //
        // Load the address port numbers
        //

        String listenAddressString = properties.getProperty( LISTEN_ADDRESS, "" );
        listenAddressString.trim();
        // If not address is specified, default to null.  ServiceListener can handle
        // a null listenAddress.
        if( listenAddressString.length() > 0 )
        {
            try {
                listenAddress = InetAddress.getByName( listenAddressString );
            }
            catch ( UnknownHostException unknownHostException ) {
                throw new RuntimeException( "Invalid value for property: " + LISTEN_ADDRESS + ".  Server will listen on all addresses.  " + unknownHostException );
            }
        }
        else
        {
            listenAddress = null;
        }



        String smtpPortString = properties.getProperty( SMTPPORT );
        String pop3PortString = properties.getProperty( POP3PORT );
        smtpPort = parsePort( smtpPortString, 25 );
        pop3Port = parsePort( pop3PortString, 110 );

        //
        // Load the SMTP Delivery Parameters

        enablePOPBeforeSMTP = Boolean.valueOf( properties.getProperty( RELAY_POP_BEFORE_SMTP, "false" ) ).booleanValue();
        // Initialize the timeout Minutes parameter
        String timoutString = properties.getProperty( RELAY_POP_BEFORE_SMTP_TIMEOUT, "10" );
        try {
            setAuthenticationTimeoutMinutes( Long.parseLong( timoutString ) );
        }
        catch( NumberFormatException nfe ) {
            log.warn( "Invalid value for property: " + RELAY_POP_BEFORE_SMTP_TIMEOUT + ". Defaulting to 10." );
            //Set the default to 10 minutes.
            setAuthenticationTimeoutMinutes( 10 );
        }

        // Relay approved IP Addresses
        String ipAddresses = properties.getProperty( RELAY_ADDRESSLIST, "" );
        setRelayApprovedIpAddresses( tokenize( ipAddresses ) );

        // Relay approved email Addresses
        String emailAddresses = properties.getProperty( RELAY_EMAILSLIST, "" );
        setRelayApprovedEmailAddresses( tokenize( emailAddresses ) );

        // Load default Server info.

        String smtpServers = properties.getProperty( DEFAULT_SMTP_SERVERS, "" ).trim();
        if( smtpServers.length() > 0 ) {
            defaultSmtpServerEnabled = true;
            defaultSmtpServers = tokenize( smtpServers );
        }
        else {
            defaultSmtpServerEnabled = false;
            defaultSmtpServers = new String[0];
        }

        // Load default user info
        String defaultUserString = properties.getProperty( DEFAULT_USER, "" ).trim();;
        if( defaultUserString.length() > 0 )
        {
            try {
                defaultUser = new EmailAddress( defaultUserString );
                defaultUserEnabled = true;
            }
            catch (InvalidAddressException e) {
                throw new RuntimeException( "Invalid address for default user: " + defaultUserString );
            }
        }
        else
        {
            defaultUser = null;
            defaultUserEnabled = false;
        }

        String deliveryIntervalString = properties.getProperty( SMTP_DELIVERY_INTERVAL, "10" );
        try {
            //Convert to number and then convert to ms.
            setDeliveryIntervalSeconds( Long.parseLong( deliveryIntervalString ) );
        }
        catch( NumberFormatException nfe ) {
            setDeliveryIntervalSeconds( 10 );
        }

        // Set the Delivery Attempt Threshold.
        try
        {
            deliveryAttemptThreshold = Integer.parseInt(properties.getProperty( SMTP_DELIVERY_THRESHOLD, "10" ) );
        }
        catch( NumberFormatException numberFormatException )
        {
            log.warn( "Invalid value for property: " + SMTP_DELIVERY_THRESHOLD + ". Defaulting to 10." );
            deliveryAttemptThreshold = 10;
        }

        // Set the Maximum message Size
        try
        {
            maximumMessageSize = Integer.parseInt( properties.getProperty( SMTP_MAX_MESSAGE_SIZE, "5" ) );
        }
        catch( NumberFormatException numberFormatException )
        {
            log.warn( "Invalid value for property: " + SMTP_MAX_MESSAGE_SIZE + ". Defaulting to 5." );
            deliveryAttemptThreshold = 5;
        }

        // Update the 'last loaded' timestamp.
        generalConfigurationFileTimestamp = generalConfigurationFile.lastModified();
    }

    private void loadUserProperties() {

        // Load the properties
        Properties properties = new Properties();

        try {
            FileInputStream inputStream = new FileInputStream( userConfigurationFile );
            properties.load( inputStream );
        }
        catch (IOException e) {
            // All checks should be done before we get here, so there better
            // not be any errors.  If so, throw a RuntimeException.
            throw new RuntimeException( "Error Loading Properties File!  Unable to continue Operation." );
        }

        // Clear the modified flag so we know if we have to save the file
        // after loading.
        userConfModified = false;

        //
        // Load the users
        //

        Map users = new HashMap();
        Enumeration propertyKeys = properties.keys();
        String key;
        String fullUsername;
        String correctedUsername;
        
        while( propertyKeys.hasMoreElements() )
        {
            key = (String) propertyKeys.nextElement();
            if( key.startsWith( USER_DEF_PREFIX ) )
            {
                fullUsername = key.substring( USER_DEF_PREFIX.length() );
                correctedUsername = fullUsername.toLowerCase();
                System.out.println("Add user : " + correctedUsername);
                try {
                    users.put( correctedUsername, loadUser( fullUsername, properties ) );
                }
                catch (InvalidAddressException e) {
                    log.warn( "Skipping user: " + fullUsername + ".  Address is invalid." );
                }
            }
        }
        this.users = users;

        if( log.isInfoEnabled() ) log.info( "Loaded " + users.size() + " users from user.conf" );

        // Save the user configuration if they changed.
        if( userConfModified ) {
            try {
                FileOutputStream out = new FileOutputStream( userConfigurationFile );
                //properties.store( out, "Java Email Server (JES) User Configuration");
                properties.store( out, USER_PROPERTIES_HEADER );
                log.info( "Changes to user.conf persisted to disk." );
            }
            catch (IOException e) {
                log.error( "Unable to store changes to user.conf!  Plain text passwords were not hashed!" );
            }
        }
        System.out.println("Users list : " + this.users);
        // Update the 'last loaded' timestamp.
        userConfigurationFileTimestamp = userConfigurationFile.lastModified();
    }

    /**
     * Loads the values of the specified key from the configuration file.
     * This method parses the value into a String array
     * using the comma (,) as a delimiter.  This method returns an
     * array of size 0 if the the value string was null or empty.
     *
     * @param value the string to tokenize into an array.
     * @return a String[] of the values, or an empty array if the key could not be found.
     */
    public static String[] tokenize( String value ) {

        if( value == null || value.trim().equals( "" ) ) {
            return new String[0];
        }
        else {
            StringTokenizer stringTokenizer = new StringTokenizer( value, "," );
            Vector tokenVector = new Vector();
            while( stringTokenizer.hasMoreTokens() ) {
                tokenVector.addElement( stringTokenizer.nextToken().trim() );
            }

            String[] values = new String[ tokenVector.size() ];
            return (String[]) tokenVector.toArray( values );
        }
    }

    /**
     * Converts the string into a valid port number.
     *
     * @param stringValue the string value to parse
     * @param defaultValue the default value to return if parsing fails.
     * @return a valid int.
     */
    private int parsePort( String stringValue, int defaultValue )
    {
        int value = defaultValue;
        if( stringValue != null && stringValue.length() > 0 )
        {
            try {
                value = Integer.parseInt( stringValue );
            }
            catch (NumberFormatException e) {
                log.warn( "Error parsing port string: " + stringValue + " using default value: " + defaultValue );
            }
        }
        return value;
    }

    /**
     * Creates a new User instance for the specified username
     * using the specified properties.
     *
     * @param fullAddress full username (me@mydomain.com)
     * @param properties the properties that contain the user parameters.
     * @return a new User instance.
     */
    private User loadUser( String fullAddress, Properties properties ) throws InvalidAddressException
    {
        EmailAddress address = new EmailAddress( fullAddress );
        User user = new User( address );

        // Load the password
        String password = properties.getProperty( USER_DEF_PREFIX + fullAddress );
        // If the password is not hashed, hash it now.
        if( password.length() != 60 ) {
            password = PasswordManager.encryptPassword( password );
            properties.setProperty( USER_DEF_PREFIX + fullAddress, password );
            if( password == null ) {
                log.error( "Error encrypting plaintext password from user.conf for user " + fullAddress );
                throw new RuntimeException( "Error encrypting password for user: " + fullAddress );
            }
            userConfModified = true;
        }
        user.setPassword( password );

        // Load the 'forward' addresses.
        String forwardAddressesString = properties.getProperty( USER_PROPERTY_PREFIX + fullAddress + USER_FILE_FORWARDS );
        String[] forwardAddresses = new String[0];
        if( forwardAddressesString != null && forwardAddressesString.trim().length() >= 0 )
        {
            forwardAddresses = tokenize( forwardAddressesString );
        }
        ArrayList addressList = new ArrayList( forwardAddresses.length );
        for( int index = 0; index < forwardAddresses.length; index++ ) {
            try {
                addressList.add( new EmailAddress( forwardAddresses[index] ) );
            }
            catch (InvalidAddressException e) {
                log.warn( "Forward address: " + forwardAddresses[index] + " for user " + user.getFullUsername() + " is invalid and will be ignored." );
            }
        }

        EmailAddress[] emailAddresses = new EmailAddress[ addressList.size() ];
        emailAddresses = (EmailAddress[]) addressList.toArray( emailAddresses );

        if( log.isDebugEnabled() ) log.debug( emailAddresses.length + " forward addresses load for user: " + user.getFullUsername() );
        user.setForwardAddresses( emailAddresses );

        return user;
    }

    //***************************************************************
    // Watchdog Inner Class
    //***************************************************************

    /**
     * Checks the user configuration file and reloads it if it is new.
     */
    class ConfigurationFileWatcher extends Thread {

        /**
         * Initialize the thread.
         */
        public ConfigurationFileWatcher() {
            super( "User Config Watchdog" );
            setDaemon( true );
        }

        /**
         * Check the timestamp on the file to see
         * if it has been updated.
         */
        public void run() {
            long sleepTime = 10 * 1000;
            while( true )
            {
                try {
                    Thread.sleep( sleepTime );
                    if( generalConfigurationFile.lastModified() > generalConfigurationFileTimestamp ) {
                        log.info( "General Configuration File Changed, reloading..." );
                        loadGeneralProperties();
                    }
                    if( userConfigurationFile.lastModified() > userConfigurationFileTimestamp ) {
                        log.info( "User Configuration File Changed, reloading..." );
                        loadUserProperties();
                    }
                }
                catch( Throwable throwable ) {
                    log.error( "Error in ConfigurationWatcher thread.  Thread will continue to execute. " + throwable, throwable );
                }
            }
        }
    }

    private static final String LF = "\r\n";

    private static final String USER_PROPERTIES_HEADER =
        "# Java Email Server (JES) User Configuration" + LF +
        "#" + LF +
        "# All users are defined in this file.  To add a user, follow" + LF +
        "# the following pattern:" + LF +
        "# user.<username@domain>=<plain text password>" + LF +
        "#" + LF +
        "# The plain text password will be converted to a hash when the file" + LF +
        "# is first loaded by the server." + LF +
        "#" + LF +
        "# Additional configuration such as forward addresses can be specified as:" + LF +
        "# userprop.<username@domain>.forwardAddresses=<Comma list of forward addresses>" + LF +
        "#" + LF +
        "# When a message is received for a local user, the user's address will be replaced" + LF +
        "# with the addresses in the forwardAddresses property.  If you also wish to have" + LF +
        "# a copy delivered to the local user, you may add the user's local address to" + LF +
        "# the forwardAddresses property" + LF +
        "";
}
