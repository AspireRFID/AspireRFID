/******************************************************************************
 * $Workfile: ConfigurationParameterContants.java $
 * $Revision: 1.3 $
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

/**
 * This interface defines the names for all configuration properties
 * loaded from the configuration file.  This interface also serves as
 * documentation for the possible paramters and their expected values.
 * <p>
 * Descriptions of the paratmers are also available in the ConfigurationTool
 * java application included with this distribution.
 *
 * @author Eric Daugherty
 */
public interface ConfigurationParameterContants {

    //***************************************************************
    // General Paramters
    //***************************************************************

    /**
     * The local IP address that the server will listen on.  If this
     * property is not set, or is invalid, the server will listen on all
     * addresses.
     */
    public static final String LISTEN_ADDRESS ="listen.address";

    /**
     * The pop3port parameter defines the port to listen to incoming
     * Pop3 connection on.  By default, this value should be 110.
     */
    public static final String POP3PORT = "pop3port";

    /**
     * The smtpport parameter defines the port to listen to incoming
     * SMTP connection on.  By default, this value should be 25.
     */
    public static final String SMTPPORT = "smtpport";

    /**
     * The domains parameter defines the domain names that this server
     * will accept mail for.  All domains not listed here will will either
     * be relayed or ignored.  To configure mulitiple domains, just seperate
     * each domain with a comma.
     */
    public static final String DOMAINS = "domains";

    /**
     * The number of threads that will be allocated to each connection listener
     * for each port.
     */
    public static final String EXECUTE_THREADS = "threads";

    //***************************************************************
    // Mail Delivery Paramters
    //***************************************************************

    /**
     * The default user to deliver mail addressed to local unknown users.
     */
    public static final String DEFAULT_USER = "defaultuser";

    /**
     * The default server to deliver mail addressed to remote users.
     */
    public static final String DEFAULT_SMTP_SERVERS = "defaultsmtpservers";

    /**
     * Enables the POP3 login as a valid address for SMTP Relaying.
     */
    public static final String RELAY_POP_BEFORE_SMTP = "relay.popbeforesmtp";

    /**
     * This setting only applies when RELAY_POP_BEFORE_SMTP is set to true.
     * This setting defines the timeout period (in minutes) between when a
     * user (ip address) last authenticated with the POP3 server and when they will
     * no longer be able to send SMTP mail to remote domains. This option defaults
     * to 10 minutes.
     */
    public static final String RELAY_POP_BEFORE_SMTP_TIMEOUT = "relay.popbeforesmtp.timeout";

    /**
     * This is the label for the UI
     */
    public static final String RELAY_ADDRESSLIST = "relay.ipaddresses";

    /**
     * Enables some email addresses for SMTP Relaying.
     */
    public static final String RELAY_EMAILSLIST = "relay.emailaddresses";

    /**
     * The server stores incoming SMTP messages on disk before attempting to deliver them.  This
     * setting determines how often (in seconds) the server checks the disk for new messages to deliver.  The
     * smaller the number, the faster message will be processed.  However, a smaller number will cause
     * the server to use more of your system's resources.
     */
    public static final String SMTP_DELIVERY_INTERVAL = "smtpdelivery.interval";

    /**
     * The server picks the messages from the disk in order to deliver them.  If some message
     * cannot be delivered to remote SMTP server at that moment, because of some error, then the message
     * will be kept on the disk for later delivery attempt. However server can't retry delivery
     * indefinitely, therefore following config entry will set maximum number of retries before the server
     * gives up on the message and moves it from smtp spool directory to failed directory.
     */
    public static final String SMTP_DELIVERY_THRESHOLD = "smtpdelivery.threshold";

    /**
     * This setting limits the size of incoming SMTP messages.  This setting
     * (in megabytes) will cause emails over the max size to be rejected.
     */
    public static final String SMTP_MAX_MESSAGE_SIZE = "smtp.messagesize";

    //***************************************************************
    // User Parameters
    //***************************************************************

    /**
     * Defines the prefix to usernames stored in the properties file.
     * A username should be stored as:
     * USER_DEF_PREFIX&lt;user@domain.com&gt;=&lt;password&gt;
     */
    public static final String USER_DEF_PREFIX = "user.";

    /**
     * Defines the prefix for user properties usernames stored in the
     * properties file.
     * A user property should be stored as:
     * USER_PROPERTY_PREFIX&lt;user@domain.com&gt;.&lt;property name&gt;=&lt;value&gt;
     */
    public static final String USER_PROPERTY_PREFIX = "userprop.";

   /**
    * The USER_PROPERTY_PREFIX&lt;user@domain.com&gt;.&lt;forwardAddresses&gt;=&lt;value&gt;
    * property defines a comma separated
    * list of addresses that mail to this user will be forwarded to.
    */
    public static final String USER_FILE_FORWARDS = ".forwardAddresses";

    //***************************************************************
    // Logging Paramters
    //***************************************************************

    /**
     * Defines the default log threshold to use if log4j is not enabled.
     */
    public static final String LOGGING_DEFAULT_THRESHOLD = "defaultthreshold";

}
//EOF