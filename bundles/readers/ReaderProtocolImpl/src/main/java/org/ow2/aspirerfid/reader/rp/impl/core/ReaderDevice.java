/*
 * Copyright (c) 2008-2010, Aspire 
 * 
 * This file contains the source code of the Accada library by ETH Zurich (www.accada.org), 
 * licensed under the terms of the GNU Lesser General Public License version 2.1 in 2007 and 
 * modified for the needs of the Aspire project.
 * 
 * Aspire is free software; you can redistribute it and/or 
 * modify it under  the terms of the GNU Lesser General Public 
 * License version 2.1 as published by the Free Software Foundation (the 
 * "LGPL"). 
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library in the file COPYING-LGPL-2.1; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA. 
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY 
 * OF ANY KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 *  
 */

package org.ow2.aspirerfid.reader.rp.impl.core;

import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.IOPort;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.OperationalStatus;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.MgmtAgent;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.SnmpAgent;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.SnmpAlarmProcessor;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmChannel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmLevel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmManager;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmProcessor;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.EdgeTriggeredAlarmControl;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.EdgeTriggeredAlarmDirection;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.ReaderDeviceOperStatusAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.TTOperationalStatusAlarmControl;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util.DHCPServerFinder;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util.NTPServerFinder;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.Address;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessageLayer;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;
import org.ow2.aspirerfid.reader.rp.impl.core.triggers.IOEdgeTriggerPortManager;
import org.ow2.aspirerfid.reader.rp.impl.core.triggers.IOValueTriggerPortManager;
import org.ow2.aspirerfid.reader.rp.impl.core.util.ResourceLocator;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;



/**
 * The ReaderDevice object represents the Reader. It is a singleton object that
 * shall exist by default. There are therefore no commands to create or delete
 * it. The ReaderDevice object keeps lists of all Sources, DataSelectors,
 * NotificationChannels, Triggers and TagSelectors known. The objects shall be
 * added to these lists implicitly when created.
 * @author Markus Vitalini
 */
public class ReaderDevice {

   /**
    * The logger.
    */
   private static Logger log = Logger.getLogger(ReaderDevice.class);

   /**
    * The path of the default configuration file.
    */
   public static final String DEFAULT_PROPERTIES_FILE = "/props/ReaderDevice_default.xml";
   public static String PROPERTIES_FILE = "ReaderDevice.xml";//public static String PROPERTIES_FILE = "/props/ReaderDevice.xml";

   /**
    * The current data selector.
    */
   private DataSelector currentDataSelector;

   /**
    * The current source.
    */
   private Source currentSource;

   /**
    * The list of triggers.
    * @link aggregation <{Trigger}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates Trigger
    */
   private Hashtable triggers;

   /**
    * The list of notification channels.
    * @link aggregation <{NotificationChannel}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates NotificationChannel
    */
   private Hashtable notificationChannels;

   /**
    * The list of tag selectors.
    * @link aggregation <{TagSelector}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates TagSelector
    */
   private Hashtable tagSelectors;

   /**
    * The list of tag fields.
    * @link aggregation <{TagField}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates TagField
    */
   private Hashtable tagFields;

   /**
    * The list of data selectors.
    * @link aggregation <{DataSelector}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates DataSelector
    */
   private Hashtable dataSelectors;

   /**
    * The list of readpoints.
    * @link aggregation <{ReadPoint}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates ReadPoint
    */
   private Hashtable readPoints;

   /**
    * The list of sources.
    * @link aggregation <{Source}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates Source
    */
   private Hashtable sources;

   /**
    * The maximal number of sources.
    */
   private int maxSourceNumber;

   /**
    * The maximal number of TagSelectors.
    */
   private int maxTagSelectorNumber;

   /**
    * The maximal number of Triggers.
    */
   private int maxTriggerNumber;

   /**
    * The EPC of the reader.
    */
   private String epc;

   /**
    * The handle of the reader.
    */
   private int handle;

   /**
    * The manufacturer of the reader.
    */
   private String manufacturer;

   /**
    * A description of the manufacturer.
    */
   private String manufacturerDescription;

   /**
    * The model of the reader.
    */
   private String model;

   /**
    * The name of the reader.
    */
   private String name;

   /**
    * The role of the reader.
    */
   private String role;

   /**
    * The date and time at initialisation.
    */
   private Date initialDate;

   /**
    * The HardwareAbstractions of this reader device.
    */
   private Hashtable<String, HardwareAbstraction> readers;

   /**
    * The edge triggers (port, instance of IOEdgeTriggerPortManager).
    */
   private Hashtable edgeTriggers;

   /**
    * The value triggers (port, instance of IOValueTriggerPortManager).
    */
   private Hashtable valueTriggers;


   // ====================================================================
   // ----- Fields added for the reader management implementation ------//
   // ====================================================================

   /**
    * The description of the general use of this particular reader.
    */
   private String description;

   /**
    * The description of the location of the reader device.
    */
   private String locationDescription;

   /**
    * The information which identifies the individual or organization
    * responsible for administering the reader.
    */
   private String contact;

   /**
    * The serial number of the reader device.
    */
   private String serialNumber;

   /**
    * The operational status of the reader device.
    */
   private OperationalStatus operStatus;

   /**
    * This object controls the conditions for generating alarms alerting a
    * manager of changes in a <coder>ReaderDevice</code>'s operational
    * status.
    */
   private TTOperationalStatusAlarmControl operStatusAlarmControl;

   /**
    * The amount of free memory available on the reader device. It represents
    * the number of kilobytes (KB) of Read/Write memory available to internal
    * or external resources for buffering or other processing of information.
    */
   private long freeMemory;

   /**
    * The object which controls the generation of alarms alerting a managing
    * system of a reduction of a reader's free memory resources below a
    * specified threshold.
    */
   private EdgeTriggeredAlarmControl freeMemoryAlarmControl;

   /**
    * A list of NTP servers used by the device to synchronize its current UTC
    * clock.
    */
   private String[] ntpServers;

   /**
    * The DHCP server currently used by the device for DHCP requests.
    */
   private String dhcpServer;

   /**
    * The list of alarm channels.
    * @link aggregation <{AlarmChannel}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates AlarmChannel
    */
   private Hashtable<String,AlarmChannel> alarmChannels;

   /**
    * The list of IO-ports.
    * @link aggregation <{IOPort}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates IOPort
    */
   private Hashtable<String, IOPort> ioPorts;

   /**
    * The alarm manager.
    */
   private AlarmManager alarmManager;

   /**
    * The management agent.
    */
   private MgmtAgent mgmtAgent;

   /**
    * The NTP server finder.
    */
   private NTPServerFinder ntpServerFinder;

   /**
    * The timestamp of the last refresh of the <code>ntpServers</code>
    * attribute.
    */
   private Date lastNTPServersRefreshTimestamp = new Date(0);

   /**
    * The DHCP server finder.
    */
   private DHCPServerFinder dhcpServerFinder;

   /**
    * The number of device operation state change notifications that have been
    * suppressed.
    */
   private static int operStateSuppressions = 0;

   /**
    * The number of device memory state change notifications that have been
    * suppressed.
    */
   private static int memStateSuppressions = 0;


   private static ReaderDevice instance;

   public synchronized static ReaderDevice getInstance() throws ReaderProtocolException {
	   if (instance == null) {
		   instance = new ReaderDevice();
	   }
	   else
	   {
		   instance.stopReaders();
	   }
	   instance.reboot();
	   return instance;
   }


   /**
    * The constructor of the ReaderDevice. This method takes
    * the value of PROPERTIES_FILE as the location of the property file.
    * @throws ReaderProtocolException
    *            "Reader not found", "Failed to read properties file",
    *            "wrong valueTrigger in property file", "wrong edgeTrigger in
    *            property file"
    */
   private ReaderDevice(String propetiesFile) throws ReaderProtocolException {
      switch (MessageLayer.mgmtAgentType) {
         case SNMP:
            mgmtAgent = SnmpAgent.getInstance();
            break;
         // case ...:
      }
      try {
    	  ntpServerFinder = new NTPServerFinder();
      } catch (SocketException se) {
    	  log.error(se.getMessage());
      }
      try {
    	  dhcpServerFinder = new DHCPServerFinder(null);
      } catch (SocketException se) {
    	  log.error(se.getMessage());
      }
      reboot(propetiesFile, DEFAULT_PROPERTIES_FILE);
   }
   
   private ReaderDevice() throws ReaderProtocolException {
	   
//	   this(PROPERTIES_FILE);
   }

   /**
    * The constructor of the ReaderDevice.
    * @param propFile
    *           The location of the property file
    * @throws ReaderProtocolException
    *            "Reader not found", "Failed to read properties file",
    *            "wrong valueTrigger in property file", "wrong edgeTrigger in
    *            property file"
    */
   private ReaderDevice(final String propFile, final String defaultPropFile)
         throws ReaderProtocolException {
	  switch (MessageLayer.mgmtAgentType) {
         case SNMP:
            mgmtAgent = SnmpAgent.getInstance();
            break;
         // case ...:
      }
	  try {
    	  ntpServerFinder = new NTPServerFinder();
      } catch (SocketException se) {
    	  log.error(se.getMessage());
      }
	  try {
    	  dhcpServerFinder = new DHCPServerFinder(null);
      } catch (SocketException se) {
    	  log.error(se.getMessage());
      }
      reboot(propFile, defaultPropFile);
   }
   
   public void stopReaders()
   {
	   for(HardwareAbstraction ha : readers.values())
	   {
		   ha.disconnect();
	   }
   }

   /**
    * Returns the EPC code of the reader.
    * @return The EPC of the reader
    */
   public final String getEPC() {
      return epc;
   }

   /**
    * Sets the EPC of the reader.
    * @param epc
    *           The new EPC of the reader
    */
   public final void setEPC(final String epc) {
      this.epc = epc;
   }

   /**
    * Returns the name of the reader manufacturer.
    * @return The name of the reader manufacturer
    */
   public final String getManufacturer() {
      return manufacturer;
   }

   /**
    * Sets the name of the reader manufacturer.
    * @param manufacturer
    *           The new neame of the reader manufacturer
    */
   public final void setManufacturer(final String manufacturer) {
      this.manufacturer = manufacturer;
   }

   /**
    * Returns the manufacturer-defined model description of the reader.
    * @return The manufacturer-defined model description of the reader
    */
   public final String getModel() {
      return model;
   }

   /**
    * Sets the manufacturer-defined model description of the reader.
    * @param model
    *           The new description of the manufacturer-defined model of the
    *           reader
    */
   public final void setModel(final String model) {
      this.model = model;
   }

   /**
    * Returns the reader handle. The reader handle is an arbitrary integer
    * number, settable by the Host, to distinguish between different readers.
    * The handle can be used as an alternative to the reader name, however, it
    * is more code-friendly than a name and therefore intended for use by
    * applications.
    * @return The handle of the reader
    */
   public final int getHandle() {
      return handle;
   }

   /**
    * Sets the handel of the reader.
    * @param handle
    *           The new handle of the reader
    */
   public final void setHandle(final int handle) {
      this.handle = handle;
   }

   /**
    * Returns the name of the reader. The reader name is an arbitrary string,
    * settable by the Host, to distinguish between different readers. The name
    * can be used as an alternative to the Reader handle, however, it is
    * intended to be easier understandable for the human user and therefore used
    * for display in user interfaces.
    * @return The name of the reader
    */
   public final String getName() {
      return name;
   }

   /**
    * Sets the name of the reader.
    * @param name
    *           The new name of the reader
    */
   public final void setName(final String name) {
      this.name = name;
   }

   /**
    * Returns the Readers role. The role is an arbitrary string, settable by
    * the Host, to identify the task or function of a reader, e.g. that this
    * reader is at a receiving door.
    * @return The role of the reader
    */
   public final String getRole() {
      return role;
   }

   /**
    * Sets the role of the reader.
    * @param role
    *           The new role of the reader
    */
   public final void setRole(final String role) {
      this.role = role;
   }

   /**
    * Returns the current uptime ticks of a reader. A compliant Reader shall
    * have an internal clock that is reset when booted. The uptime ticks are the
    * number of clock ticks since the reader was booted last.
    * @return The number of clock ticks since the reader was booted last
    */
   public final long getTimeTicks() {
      Date now = new Date();
      return now.getTime() - initialDate.getTime();
   }

   /**
    * Returns the current absolute (UTC) time of the reader.
    * @return The current UTC time of the reader
    */
   public final Date getTimeUTC() {
      return new Date();
   }

   /**
    * Sets the UTC time of the reader.
    * @param utc
    *           The new UTC of the reader
    */
   public final void setTimeUTC(final Date utc) {
      initialDate = utc;
   }

   /**
    * Returns a description of the reader provided by the manufacturer. This may
    * include information about the firmware version used and other information
    * deemed important by the manufacturer. The format is
    * implementation-dependent and shall be described in the reader
    * documentation.
    * @return The manufacturer description
    */
   public final String getManufacturerDescription() {
      return manufacturerDescription;
   }

   /**
    * Sets the manufacturer description.
    * @param manufacturerDescription
    *           The new manufacturer description
    */
   public final void setManufacturerDescription(
         final String manufacturerDescription) {
      this.manufacturerDescription = manufacturerDescription;
   }

   /**
    * Returns the maximal number of sources this reader supports.
    * @return Returns The maximal number of sources this reader supports
    */
   public final int getMaxSourceNumber() {
      return maxSourceNumber;
   }

   /**
    * Sets the maximal number of sources this reader supports.
    * @param maxSourceNumber
    *           The new maximal number of sources this reader supports
    */
   public final void setMaxSourceNumber(final int maxSourceNumber) {
      this.maxSourceNumber = maxSourceNumber;
   }

   /**
    * Returns the maximal number of TagSelectors this reader supports.
    * @return Returns The maximal number of TagSelectors this reader supports
    */
   public final int getMaxTagSelectorNumber() {
      return maxTagSelectorNumber;
   }

   /**
    * Sets the maximal number of TagSelectors this reader supports.
    * @param maxTagSelectorNumber
    *           The new maximal number of TagSelectors this reader supports
    */
   public final void setMaxTagSelectorNumber(final int maxTagSelectorNumber) {
      this.maxTagSelectorNumber = maxTagSelectorNumber;
   }

   /**
    * Returns the maximal number of triggers this reader supports.
    * @return Returns The maximal number of triggers this reader supports
    */
   public final int getMaxTriggerNumber() {
      return maxTriggerNumber;
   }

   /**
    * Sets the maximal number of triggers this reader supports.
    * @param maxTriggerNumber
    *           The new maximal number of triggers this reader supports
    */
   public final void setMaxTriggerNumber(final int maxTriggerNumber) {
      this.maxTriggerNumber = maxTriggerNumber;
   }

   /**
    * Returns the current source of the reader. If the Reader supports one or
    * more Sources then the Reader shall provide a current source. The current
    * source can be pre-configured by the reader manufacturer or the reader can
    * support commands that allow setting the current wource.
    * @return The current source
    */
   public final Source getCurrentSource() {
      return currentSource;
   }

   /**
    * Sets the current source of the reader.
    * @param currentSource
    *           The new current source
    */
   public final void setCurrentSource(final Source currentSource) {
      this.currentSource = currentSource;
   }

   /**
    * Returns the current DataSelector of the reader. Per default, this shall be
    * a DataSelector with only the FieldName AllSupportedFields added, i.e,
    * all supported fields and all supported event types will be reported.
    * @return The current DataSelector of the reader
    */
   public final DataSelector getCurrentDataSelector() {
      return currentDataSelector;
   }

   /**
    * Sets the current DataSelector of the reader.
    * @param currentDataSelector
    *           The new current DataSelector of the reader
    */
   public final void setCurrentDataSelector(
         final DataSelector currentDataSelector) {
      this.currentDataSelector = currentDataSelector;
   }

   /**
    * Removes the specified sources from the list of sources currently
    * associated with this reader. If one or more of the sources given are not
    * known, or if some of the sources to be removed are currently not
    * associated with this reader, these are ignored and all other sources shall
    * be removed and the command shall complete successfully. But note, in case
    * of a fixed source an exception ("ERROR_SOURCE_READ_ONLY") is thrown and
    * none of the sources will be removed
    * @param sourceList
    *           The list of sources to remove
    * @throws ReaderProtocolException
    *            "ERROR_SOURCE_READ_ONLY"
    */
   public final void removeSources(final Source[] sourceList)
         throws ReaderProtocolException {

      Vector sources = getVector(sourceList);

      Enumeration iterator = sources.elements();
      Source cur;
      while (iterator.hasMoreElements()) {
         cur = (Source) iterator.nextElement();
         if (cur.isFixed()) {
            throw new ReaderProtocolException("ERROR_SOURCE_READ_ONLY",
                  MessagingConstants.ERROR_SOURCE_READ_ONLY);
         }
      }

      iterator = sources.elements();
      while (iterator.hasMoreElements()) {
         cur = (Source) iterator.nextElement();
         cur.removeAssociations();
         this.sources.remove(cur.getName());
      }

   }

   /**
    * Removes all Sources currently associated with this reader, except the ones
    * marked as fixed.
    */
   public final void removeAllSources() {

      Enumeration iterator = sources.elements();
      Source cur;

      while (iterator.hasMoreElements()) {
         cur = (Source) iterator.nextElement();
         if (!cur.isFixed()) {
            cur.removeAssociations();
            this.sources.remove(cur.getName());
         }
      }

   }

   /**
    * Returns the source with the specified name. If no source object with the
    * given name exists, the error "ERROR_SOURCE_NOT_FOUND" is raised.
    * @param name
    *           The name of the source
    * @return The instance of the source
    * @throws ReaderProtocolException
    *            "ERROR_SOURCE_NOT_FOUND"
    */
   public final Source getSource(final String name)
         throws ReaderProtocolException {

      if (!sources.containsKey(name)) {
         throw new ReaderProtocolException("ERROR_SOURCE_NOT_FOUND",
               MessagingConstants.ERROR_SOURCE_NOT_FOUND);
      } else {
         return (Source) sources.get(name);
      }

   }

   /**
    * Returns all sources currently associated with this reader. If no sources
    * are currently associated with the Reader, the command shall complete
    * successfully and an empty list SHALL be returned.
    * @return The list of sources associated with this reader
    */
   public final Source[] getAllSources() {
      return (Source[]) sourcesToArray(sources);
   }

   /**
    * Removes the specified DataSelectors from the list of DataSelectors
    * currently associated with this reader. If one or more of the DataSelectors
    * given are not known, or if some of the DataSelectors to be removed are
    * currently not associated with this reader, these are ignored and all other
    * DataSelectors shall be removed and the command shall complete
    * successfully.
    * @param dataSelectorList
    *           The list of DataSelectors to remove
    */
   public final void removeDataSelectors(final DataSelector[]
           dataSelectorList) {

      Vector dataSelectors = getVector(dataSelectorList);

      Enumeration iterator = dataSelectors.elements();
      DataSelector cur;

      while (iterator.hasMoreElements()) {
         cur = (DataSelector) iterator.nextElement();
         cur.removeAssociations();
         this.dataSelectors.remove(cur.getName());
      }

   }

   /**
    * Removes all DataSelectors currently associated with this reader.
    */
   public final void removeAllDataSelectors() {
      removeDataSelectors((DataSelector[]) dataSelectorsToArray(dataSelectors));
   }

   /**
    * Returns the DataSelector with the specified name currently associated with
    * this reader. If no DataSelector object with the given name exists, the
    * error ERROR_DATASELECTOR_NOT_FOUND is raised.
    * @param name
    *           The name of the DataSelector
    * @return The instance of the DataSelector
    * @throws ReaderProtocolException
    *            "ERROR_DATASELECTOR_NOT_FOUND"
    */
   public final DataSelector getDataSelector(final String name)
         throws ReaderProtocolException {

      if (!dataSelectors.containsKey(name)) {
         throw new ReaderProtocolException("ERROR_DATASELECTOR_NOT_FOUND",
               MessagingConstants.ERROR_DATASELECTOR_NOT_FOUND);
      } else {
         return (DataSelector) dataSelectors.get(name);
      }

   }

   /**
    * Returns all DataSelectors currently associated with the Reader. If no
    * DataSelectors are currently associated with this object, the command shall
    * complete successfully and an empty list shall be returned.
    * @return The list of DataSelectors
    */
   public final DataSelector[] getAllDataSelectors() {
      return (DataSelector[]) dataSelectorsToArray(dataSelectors);
   }

   /**
    * Removes the specified NotificationChannels from the list of
    * NotificationChannels currently associated with the reader. If one or more
    * of the NotificationChannels given are not known, or if some of the
    * NotificationChannels to be removed are currently not associated with the
    * reader, these are ignored and all other NotificationChannels shall be
    * removed and the command shall complete successfully.
    * @param notificationChannelList
    *           The list of NotificationChannels to remove
    */
   public final void removeNotificationChannels(
         final NotificationChannel[] notificationChannelList) {

      Vector notificationChannels = getVector(notificationChannelList);

      Enumeration iterator = notificationChannels.elements();
      NotificationChannel cur;

      while (iterator.hasMoreElements()) {
         cur = (NotificationChannel) iterator.nextElement();
         cur.removeAssociations();
         this.notificationChannels.remove(cur.getName());
      }

   }

   /**
    * Removes all NotificationChannels currently associated with the reader.
    */
   public final void removeAllNotificationChannels() {
      removeNotificationChannels((NotificationChannel[])
            notificationChannelsToArray(notificationChannels));
   }

   /**
    * Returns the NotificationChannel with the specified name currently
    * associated with the reader. If no NotificationChannel object with the
    * given name exists, the error ERROR_CHANNEL_NOT_FOUND is raised.
    * @param name
    *           The name of the NotificationChannel
    * @return The instance of the NotificationChannel
    * @throws ReaderProtocolException
    *            "Reader not found", "Failed to read properties file",
    *            "wrong valueTrigger in property file", "wrong edgeTrigger in
    *            property file"
    */
   public final NotificationChannel getNotificationChannel(final String name)
         throws ReaderProtocolException {

      if (!notificationChannels.containsKey(name)) {
         throw new ReaderProtocolException("ERROR_CHANNEL_NOT_FOUND",
               MessagingConstants.ERROR_CHANNEL_NOT_FOUND);
      } else {
         return (NotificationChannel) notificationChannels.get(name);
      }

   }

   /**
    * Returns all NotificationChannels currently associated with the Reader. If
    * no NotificationChannels are currently associated with this object, the
    * command complete successfully and an empty list will be returned.
    * @return The list of NotificationChannels
    */
   public final NotificationChannel[] getAllNotificationChannels() {
      return (NotificationChannel[])
      notificationChannelsToArray(notificationChannels);
   }

   /**
    * Removes the specified Triggers from the list of Triggers currently
    * associated with the reader. If one or more of the Triggers given are not
    * known, or if some of the Triggers to be removed are currently not
    * associated with the reader, these are ignored and all other Triggers will
    * be removed and the command complete successfully.
    * @param triggerList
    *           The list of Triggers to remove
    */
   public final void removeTriggers(final Trigger[] triggerList) {

      Vector triggers = getVector(triggerList);

      Enumeration iterator = triggers.elements();
      Trigger cur;

      while (iterator.hasMoreElements()) {
         cur = (Trigger) iterator.nextElement();
         cur.removeAssociations();
         this.triggers.remove(cur.getName());
      }

   }

   /**
    * Removes all Triggers currently associated with the reader.
    */
   public final void removeAllTriggers() {
      removeTriggers((Trigger[]) triggersToArray(triggers));
   }

   /**
    * Returns the Trigger with the specified name currently associated with the
    * reader. If no Trigger object with the given name exists, the error
    * ERROR_TRIGGER_NOT_FOUND is raised.
    * @param name
    *           The name of the Trigger
    * @return The instance of the Trigger
    * @throws ReaderProtocolException
    *            "ERROR_TRIGGER_NOT_FOUND"
    */
   public final Trigger getTrigger(final String name)
         throws ReaderProtocolException {

      if (!triggers.containsKey(name)) {
         throw new ReaderProtocolException("ERROR_TRIGGER_NOT_FOUND",
               MessagingConstants.ERROR_TRIGGER_NOT_FOUND);
      } else {
         return (Trigger) triggers.get(name);
      }

   }

   /**
    * Returns all Triggers currently associated with the reader. If no Triggers
    * are currently associated with this object, the command complete
    * successfully and an empty list will be returned.
    * @return The list of Triggers
    */
   public final Trigger[] getAllTriggers() {
      return (Trigger[]) triggersToArray(triggers);
   }

   /**
    * Removes the specified TagSelectors from the list of TagSelectors currently
    * associated with the reader. If one or more of the TagSelectors given are
    * not known, or if some of the TagSelectors to be removed are currently not
    * associated with the reader, these are ignored and all other TagSelectors
    * will be removed and the command complete successfully.
    * @param tagSelectorList
    *           The list of TagSelectors to remove
    */
   public final void removeTagSelectors(final TagSelector[] tagSelectorList) {

      Vector tagSelectors = getVector(tagSelectorList);

      Enumeration iterator = tagSelectors.elements();
      TagSelector cur;

      while (iterator.hasMoreElements()) {
         cur = (TagSelector) iterator.nextElement();
         cur.removeAssociations();
         this.tagSelectors.remove(cur.getName());
      }

   }

   /**
    * Removes all TagSelectors currently associated with the reader.
    */
   public final void removeAllTagSelectors() {
      removeTagSelectors((TagSelector[]) tagSelectorsToArray(tagSelectors));
   }

   /**
    * Returns the TagSelector with the specified name currently associated with
    * the reader. If no TagSelector object with the given name exists, the error
    * ERROR_FILTER_NOT_FOUND is raised.
    * @param name
    *           The name of the TagSelector
    * @return The instance of the TagSelector
    * @throws ReaderProtocolException
    *            "ERROR_TAGSELECTOR_NOT_FOUND"
    */
   public final TagSelector getTagSelector(final String name)
         throws ReaderProtocolException {

      if (!tagSelectors.containsKey(name)) {
         throw new ReaderProtocolException("ERROR_TAGSELECTOR_NOT_FOUND",
               MessagingConstants.ERROR_TAGSELECTOR_NOT_FOUND);
      } else {
         return (TagSelector) tagSelectors.get(name);
      }

   }

   /**
    * Returns all TagSelectors currently associated with the reader. If no
    * TagSelectors are currently associated with this object, the command
    * complete successfully and an empty list will be returned.
    * @return The list of TagSelectors
    */
   public final TagSelector[] getAllTagSelectors() {
      return (TagSelector[]) tagSelectorsToArray(tagSelectors);
   }

   /**
    * Removes the specified TagFields from the list of TagFields currently
    * associated with the reader. If one or more of the TagFields given are not
    * known, or if some of the TagFields to be removed are currently not
    * associated with the reader, these are ignored and all other TagFields will
    * be removed and the command complete successfully.
    * @param tagFieldList
    *           The list of TagFields to remove
    */
   public final void removeTagFields(final TagField[] tagFieldList) {

      Vector tagFields = getVector(tagFieldList);

      Enumeration iterator = tagFields.elements();
      TagField cur;

      while (iterator.hasMoreElements()) {
         cur = (TagField) iterator.nextElement();
         cur.removeAssociations();
         this.tagFields.remove(cur.getName());
      }

   }

   /**
    * Removes all TagFields currently associated with the reader.
    */
   public final void removeAllTagFields() {
      removeTagFields((TagField[]) tagFieldsToArray(tagFields));
   }

   /**
    * Returns the TagField with the specified name currently associated with the
    * reader. If no TagField object with the given name exists, the error
    * ERROR_TAGFIELD_NOT_FOUND is raised.
    * @param name
    *           The name of the TagField
    * @return The instance of the TagField
    * @throws ReaderProtocolException
    *            "ERROR_TAGFIELD_NOT_FOUND"
    */
   public final TagField getTagField(final String name)
         throws ReaderProtocolException {

      if (!tagFields.containsKey(name)) {
         throw new ReaderProtocolException("ERROR_TAGFIELD_NOT_FOUND",
               MessagingConstants.ERROR_TAGFIELD_NOT_FOUND);
      } else {
         return (TagField) tagFields.get(name);
      }

   }

   /**
    * Returns all TagFields currently associated with the reader. If no
    * TagFields are currently associated with this object, the command complete
    * successfully and an empty list will be returned.
    * @return The list of TagFields
    */
   public final TagField[] getAllTagFields() {
      return (TagField[]) tagFieldsToArray(tagFields);
   }

   /**
    * Resets all internal state variables of the reader to a default
    * configuration. The documentation of the reader shall provide a definition
    * what the default settings are
    * @throws ReaderProtocolException
    *            "Reader not found", "Failed to read properties file",
    *            "wrong valueTrigger in property file", "wrong edgeTrigger in
    *            property file"
    */
   public final void resetToDefaultSettings() throws ReaderProtocolException {

      resetToDefaultSettings(PROPERTIES_FILE, DEFAULT_PROPERTIES_FILE);

   }

   /**
    * Resets all internal state variables of the reader to a default
    * configuration. The documentation of the reader shall provide a definition
    * what the default settings are
    * @param propFile
    *           The location of the property file
    * @throws ReaderProtocolException
    *            "Reader not found", "Failed to read properties file",
    *            "wrong valueTrigger in property file", "wrong edgeTrigger in
    *            property file"
    */
   public final void resetToDefaultSettings(final String propFile,
         final String defaultPropFile) throws ReaderProtocolException {
	   log.info("reseting");
      // operational status is DOWN before resetting
	   setOperStatus(OperationalStatus.DOWN);

      //init lists
      sources = new Hashtable();
      dataSelectors = new Hashtable();
      notificationChannels = new Hashtable();
      triggers = new Hashtable();
      tagSelectors = new Hashtable();
      tagFields = new Hashtable();
      readPoints = new Hashtable();
      readers = new Hashtable<String,HardwareAbstraction>();
      valueTriggers = new Hashtable();
      edgeTriggers = new Hashtable();
      currentSource = null;
      alarmChannels = new Hashtable<String,AlarmChannel>();
      ioPorts = new Hashtable<String, IOPort>();

      // properties
      XMLConfiguration conf = new XMLConfiguration();
      try {
         conf.load(ResourceLocator.getReader(propFile, defaultPropFile));
      } catch (ConfigurationException e) {
         System.out.println(e.getMessage());
         throw new ReaderProtocolException(
               "Failed to read properties file (" + propFile + ")",
               MessagingConstants.ERROR_UNKNOWN);
      }catch(Exception e)
      {
    	  e.printStackTrace();
      }
      
      // get properties
      setEPC(conf.getString("epc"));
      setName(conf.getString("name"));
      setManufacturer(conf.getString("manufacturer"));
      setManufacturerDescription(conf.getString("manufacturerDescription"));
      setModel(conf.getString("model"));
      setHandle(conf.getInt("handle"));
      setRole(conf.getString("role"));

      setMaxSourceNumber(conf.getInt("maxSourceNumber"));
      setMaxTagSelectorNumber(conf.getInt("maxTagSelectorNumber"));
      setMaxTriggerNumber(conf.getInt("maxTriggerNumber"));

      // get readers
      SubnodeConfiguration readerConf = conf.configurationAt("readers");
      for (int i = 0; i <= readerConf.getMaxIndex("reader"); i++) {
         // key to current reader
         String key = "reader(" + i + ")";

         // reader's name
         String readerName = readerConf.getString(key + ".name");

         // get reader
         if (!readers.containsKey(readerName)) {
            // reflection
            String rClass = readerConf.getString(key + ".class");
            String prop = readerConf.getString(key + ".properties");
            try {
               Class cls = org.ow2.aspirerfid.reader.rp.impl.Activator.bundle.getClass().forName(rClass);
               Class[] partypes = new Class[] {String.class, String.class};
               Constructor ct = cls.getConstructor(partypes);
               Object[] arglist = new Object[] {readerName, prop};
               HardwareAbstraction ha = (HardwareAbstraction) ct
                     .newInstance(arglist);
               readers.put(readerName, ha);

            } catch (Exception e) {
               log.error(e.getMessage() + "|" + e.getCause());
               throw new ReaderProtocolException("Reader not found",
                     MessagingConstants.ERROR_UNKNOWN);
            }
         }
      }

      // configure readpoints
      SubnodeConfiguration rpConf = conf.configurationAt("readers");
      for (int i = 0; i <= rpConf.getMaxIndex("reader"); i++) {
         // key to current reader
         String key = "reader(" + i + ")";
         // name of the reader
         String readerName = rpConf.getString(key + ".name");
         // get reader
         HardwareAbstraction tempHardwareAbstraction =
               (HardwareAbstraction) readers.get(readerName);
         // get readpoints
         for (int j = 0; j <= rpConf.getMaxIndex(key + ".readpoint"); j++) {
            String rp = rpConf.getString(key + ".readpoint(" + j + ")");
            // System.out.println(" "+rps[j]);
            AntennaReadPoint.create(rp, this, tempHardwareAbstraction);
         }
      }

      // configure sources
      SubnodeConfiguration sourceConf = conf.configurationAt("sources");
      for (int i = 0; i <= sourceConf.getMaxIndex("source"); i++) {
         String key = "source(" + i + ")";
         // name of the source
         String sourceName = sourceConf.getString(key + ".name");
         // get source
         Source tempSource;
         if (!sources.containsKey(sourceName)) {
            tempSource = Source.create(sourceName, this);
         } else {
            tempSource = getSource(sourceName);
         }
         // fixed
         if (sourceConf.getString(key + ".fixed").equals("true")) {
            tempSource.setFixed(true);
         } else {
            tempSource.setFixed(false);
         }
         // reader's readpoints
         for (int j = 0; j <= sourceConf.getMaxIndex(key + ".readpoint"); j++) {
            String rp = sourceConf.getString(key + ".readpoint(" + j + ")");
            // System.out.println(" "+rp);
            tempSource.addReadPoints(new ReadPoint[] {getReadPoint(rp)});
         }
      }

      // set current Source
      setCurrentSource(getSource(conf.getString("currentSource")));

      // set defaults
      setDefaults();

      // get io triggers
      getIoTriggers(conf);

      // information used for the reader management implementation
      setDescription(conf.getString("description"));
      setLocationDescription(conf.getString("locationDescription"));
      setContact(conf.getString("contact"));
      serialNumber = conf.getString("serialNumber");
//      dhcpServerFinder
//				.setMacAddress(DHCPServerFinder.macAddressStringToByteArray(
//						conf.getString("macAddress"), "-"));

//      operStatusAlarmControl = new TTOperationalStatusAlarmControl(
//				"OperStatusAlarmControl", false, AlarmLevel.ERROR, 0,
//				OperationalStatus.ANY, OperationalStatus.ANY);

//      freeMemoryAlarmControl = new EdgeTriggeredAlarmControl(
//				"FreeMemoryAlarmControl", false, AlarmLevel.CRITICAL, 0, 100,
//				1000, EdgeTriggeredAlarmDirection.FALLING);
//
//      if ((alarmManager == null) && (mgmtAgent != null)) {
//    	  AlarmProcessor alarmProcessor = null;
//    	  switch (MessageLayer.mgmtAgentType) {
//    	  	case SNMP:
//    	  		alarmProcessor = new SnmpAlarmProcessor((SnmpAgent) mgmtAgent);
//    	  		break;
//    	  	// case ...:
//    	  }
//    	  alarmManager = new AlarmManager(alarmProcessor, this);
//    	  alarmManager.start();
//      }

      // configure alarm channels
//      SubnodeConfiguration alarmChanConf = conf.configurationAt("alarmChannels");
//      String host = "";
//      int port = -1;
//      for (int i = 0; i <= alarmChanConf.getMaxIndex("alarmChannel"); i++) {
//         String key = "alarmChannel(" + i + ")";
//         // name of the alarm channel
//         String alarmChanName = alarmChanConf.getString(key + ".name");
//         // get alarm channel
//         try {
//            host = alarmChanConf.getString(key + ".host");
//            port = alarmChanConf.getInt(key + ".port");
//            try {
//               AlarmChannel.create(alarmChanName, new Address("udp://" + host + ":" + port), this);
//            } catch (MalformedURLException mfue) {
//               log.error(alarmChanName + ": invalid address");
//            }
//            host = "";
//            port = -1;
//         } catch (ReaderProtocolException rpe) {
//            // next
//         }
//      }

      // operational status is UP after resetting
	  setOperStatus(OperationalStatus.UP);

   }

   /**
    * Sets defaults (TagSelector, TagField, ...).
    */
   private void setDefaults() {
      try {
         // set default TagSelector
         TagField tf = TagField.create("defaultTagField", this);
         tf.setMemoryBank(2);
         tf.setLength(0);
         tf.setOffset(0);
         tf.setTagFieldName("defaultTagSelector");
         TagSelector ts = TagSelector.create("defaultTagSelector", tf, "0",
               "0", true, this);
         // set default DataSelector
         currentDataSelector = DataSelector.create("defaultDataSelector",
               this);
         // add FieldName to DataSelector
         String[] allFieldNames = new String[] {FieldName.ALL, FieldName.ALL};
         currentDataSelector.addFieldNames(allFieldNames);
         // add EventType to DataSelector
         String[] allEventTypes = new String[] {EventType.EV_GLIMPSED,
               EventType.EV_UNKNOWN, EventType.EV_LOST, EventType.EV_NEW,
               EventType.EV_OBSERVED, EventType.EV_PURGED};
         currentDataSelector.addEventFilters(allEventTypes);
         setCurrentDataSelector(currentDataSelector);
      } catch (ReaderProtocolException e) {
         System.out.println(e.getErrorName());
      }
   }

   /**
    * Reads the configuration of the io triggers from property file.
    * @param conf
    *           The configuration
    * @throws ReaderProtocolException
    *            "wrong edgeTrigger in property file"
    */
   private void getIoTriggers(final XMLConfiguration conf)
         throws ReaderProtocolException {
      // get io value triggers
      SubnodeConfiguration valueTriggerConf = conf
            .configurationAt("IOValueTriggerPortManager");
      String port;
      for (int i = 0; i <= valueTriggerConf.getMaxIndex("port"); i++) {
         port = valueTriggerConf.getString("port(" + i + ")");
         try {
            Class cls = Class.forName(valueTriggerConf.getString(port));
            Class[] partypes = new Class[] {};
            Constructor ct = cls.getConstructor(partypes);
            Class[] cl = ct.getParameterTypes();
            Object[] arglist = new Object[] {};
            IOValueTriggerPortManager manager = (IOValueTriggerPortManager) ct
                  .newInstance(arglist);
            final int num = 4;
            valueTriggers.put(port.substring(num), manager);
         } catch (Exception e) {
            throw new ReaderProtocolException(
                  "wrong valueTrigger in property file",
                  MessagingConstants.ERROR_UNKNOWN);
         }
      }

      // get io edge triggers
      SubnodeConfiguration edgeTriggerConf = conf
            .configurationAt("IOEdgeTriggerPortManager");
      for (int i = 0; i <= valueTriggerConf.getMaxIndex("port"); i++) {
         port = edgeTriggerConf.getString("port(" + i + ")");
         try {
            Class cls = Class.forName(edgeTriggerConf.getString(port));
            Class[] partypes = new Class[] {};
            Constructor ct = cls.getConstructor(partypes);
            Class[] cl = ct.getParameterTypes();
            Object[] arglist = new Object[] {};
            IOEdgeTriggerPortManager manager = (IOEdgeTriggerPortManager) ct
                  .newInstance(arglist);
            final int num = 4;
            edgeTriggers.put(port.substring(num), manager);
         } catch (Exception e) {
            throw new ReaderProtocolException(
                  "wrong edgeTrigger in property file",
                  MessagingConstants.ERROR_UNKNOWN);
         }
      }

   }

   /**
    * Reinitializee all variables to its power up state and execute any
    * initialization functions it normally performs on power up.
    * @param propFile
    *           The location of the property file
    * @throws ReaderProtocolException
    *            "Reader not found", "Failed to read properties file",
    *            "wrong valueTrigger in property file", "wrong edgeTrigger in
    *            property file"
    */
   public final void reboot(final String propFile, final String defaultPropFile)
         throws ReaderProtocolException {

      initialDate = new Date();
      resetToDefaultSettings(propFile, defaultPropFile);

   }

   /**
    * Reinitializee all variables to its power up state and execute any
    * initialization functions it normally performs on power up.
    * @throws ReaderProtocolException
    *            "Reader not found", "Failed to read properties file",
    *            "wrong valueTrigger in property file", "wrong edgeTrigger in
    *            property file"
    */
   public final void reboot() throws ReaderProtocolException {

      initialDate = new Date();
      resetToDefaultSettings(PROPERTIES_FILE, DEFAULT_PROPERTIES_FILE);

   }

   /**
    * This command is used to indicate a graceful disconnect distinguishing
    * proper system operation from a disgraceful disconnect due to loss of
    * network connectivity, unexpected shutdown of a Host or reader, or other
    * surprising conditions. Upon receiving the reply to this command, the
    * connection on the control channel will be terminated by the Host.
    */
   public final void goodbye() {

   }

   /**
    * Returns the ReadPoint with the specified name currently associated with
    * the reader. If no ReadPoint object with the given name exists, the error
    * ERROR_READPOINT_NOT_FOUND is raised.
    * @param name
    *           The name of the ReadPoint
    * @return The instance of the ReadPoint
    * @throws ReaderProtocolException
    *            "ERROR_READPOINT_NOT_FOUND"
    */
   public final ReadPoint getReadPoint(final String name)
         throws ReaderProtocolException {

      if (!readPoints.containsKey(name)) {
         throw new ReaderProtocolException("ERROR_READPOINT_NOT_FOUND",
               MessagingConstants.ERROR_READPOINT_NOT_FOUND);
      } else {
         return (ReadPoint) readPoints.get(name);
      }

   }

   /**
    * Returns all ReadPoints of the reader. This list is preconfigured by the
    * manufacturer
    * @return The list of ReadPoints
    */
   public final ReadPoint[] getAllReadPoints() {
      return (ReadPoint[]) readPointsToArray(readPoints);
   }

   /**
    * Conversion of array to Vector.
    * @param obj
    *           The object to convert
    * @return A vector
    */
   public final Vector getVector(final Object[] obj) {

      Vector v = new Vector();
      for (int i = 0; i < obj.length; i++) {
         v.add(obj[i]);
      }
      return v;

   }

   /**
    * Conversion of Hashtable of strings to a string array.
    * @param h
    *           The hashtable to convert
    * @return The array
    */
   public final String[] stringsToArray(final Hashtable h) {

      String[] array = new String[h.size()];
      Enumeration iterator = h.elements();
      int i = 0;
      while (iterator.hasMoreElements()) {
         array[i] = (String) iterator.nextElement();
         i++;
      }

      return array;

   }

   /**
    * Conversion of Hashtable of sources to a source array.
    * @param h
    *           The hashtable to convert
    * @return The array
    */
   public final Source[] sourcesToArray(final Hashtable h) {

      Source[] array = new Source[h.size()];
      Enumeration iterator = h.elements();
      int i = 0;
      while (iterator.hasMoreElements()) {
         array[i] = (Source) iterator.nextElement();
         i++;
      }

      return array;

   }

   /**
    * Conversion of Hashtable of read points to a read point array.
    * @param h
    *           The hashtable to convert
    * @return The array
    */
   public final ReadPoint[] readPointsToArray(final Hashtable h) {

      ReadPoint[] array = new ReadPoint[h.size()];
      Enumeration iterator = h.elements();
      int i = 0;
      while (iterator.hasMoreElements()) {
         array[i] = (ReadPoint) iterator.nextElement();
         i++;
      }

      return array;

   }

   /**
    * Conversion of Hashtable of triggers to a trigger array.
    * @param h
    *           The hashtable to convert
    * @return The array
    */
   public final Trigger[] triggersToArray(final Hashtable h) {

      Trigger[] array = new Trigger[h.size()];
      Enumeration iterator = h.elements();
      int i = 0;
      while (iterator.hasMoreElements()) {
         array[i] = (Trigger) iterator.nextElement();
         i++;
      }

      return array;

   }

   /**
    * Conversion of Hashtable of tag selectors to a tag selector array.
    * @param h
    *           The hashtable to convert
    * @return The array
    */
   public final TagSelector[] tagSelectorsToArray(final Hashtable h) {

      TagSelector[] array = new TagSelector[h.size()];
      Enumeration iterator = h.elements();
      int i = 0;
      while (iterator.hasMoreElements()) {
         array[i] = (TagSelector) iterator.nextElement();
         i++;
      }

      return array;

   }

   /**
    * Conversion of Hashtable of data selectors to a data selector array.
    * @param h
    *           The hashtable to convert
    * @return The array
    */
   public final DataSelector[] dataSelectorsToArray(final Hashtable h) {

      DataSelector[] array = new DataSelector[h.size()];
      Enumeration iterator = h.elements();
      int i = 0;
      while (iterator.hasMoreElements()) {
         array[i] = (DataSelector) iterator.nextElement();
         i++;
      }

      return array;

   }

   /**
    * Conversion of Hashtable of notification channels to a notification.
    * channels array
    * @param h
    *           The hashtable to convert
    * @return The array
    */
   public final NotificationChannel[] notificationChannelsToArray(
         final Hashtable h) {

      NotificationChannel[] array = new NotificationChannel[h.size()];
      Enumeration iterator = h.elements();
      int i = 0;
      while (iterator.hasMoreElements()) {
         array[i] = (NotificationChannel) iterator.nextElement();
         i++;
      }

      return array;

   }

   /**
    * Conversion of Hashtable of tag fields to a tag field array.
    * @param h
    *           The hashtable to convert
    * @return The array
    */
   public final TagField[] tagFieldsToArray(final Hashtable h) {

      TagField[] array = new TagField[h.size()];
      Enumeration iterator = h.elements();
      int i = 0;
      while (iterator.hasMoreElements()) {
         array[i] = (TagField) iterator.nextElement();
         i++;
      }

      return array;

   }

   /**
    * Returns al triggers.
    * @return The triggers
    */
   public final Hashtable getTriggers() {
      return triggers;
   }

   /**
    * Returns the notification channels.
    * @return The notification channels
    */
   public final Hashtable getNotificationChannels() {
      return notificationChannels;
   }

   /**
    * Returns the tag selectors.
    * @return The tag selectors
    */
   public final Hashtable getTagSelectors() {
      return tagSelectors;
   }

   /**
    * Returns the tag fields.
    * @return The tag fields
    */
   public final Hashtable getTagFields() {
      return tagFields;
   }

   /**
    * Returns the data selector.
    * @return The data selector
    */
   public final Hashtable getDataSelectors() {
      return dataSelectors;
   }

   /**
    * Returns the read points.
    * @return The read points
    */
   public final Hashtable getReadPoints() {
      return readPoints;
   }

   /**
    * Returns the sources.
    * @return The sources
    */
   public final Hashtable getSources() {
      return sources;
   }

   /**
    * Returns the edge triggers.
    * @return The edge triggers
    */
   public final Hashtable getEdgeTriggers() {
      return edgeTriggers;
   }

   /**
    * Returns the value triggers.
    * @return The value triggers
    */
   public final Hashtable getValueTriggers() {
      return valueTriggers;
   }


   // ====================================================================
   // ----- Methods added for the reader management implementation -----//
   // ====================================================================

   /**
    * Returns the user defined description of the general use of this
    * particular reader.
    * @return The user defined description of this particular reader
    */
   public final String getDescription() {
	   return description;
   }

   /**
    * Sets the description of the general use of this particular reader.
    * @param description
    *            The description of the general use of this particular reader.
    */
   public final void setDescription(String description) {
	   this.description = description;
   }

   /**
    * Returns the user defined location description of the reader device.
    * @return The user defined location description
    */
   public final String getLocationDescription() {
	   return locationDescription;
   }

   /**
    * Sets the description of the location of the reader device.
    * @param locationDescription
    *            The description of the location of the reader device
    */
   public final void setLocationDescription(String locationDescription) {
	   this.locationDescription = locationDescription;
   }

   /**
    * Returns the information which identifies the individual or
    * organizationvresponsible for administering the reader.
    * @return The contact information to the person reponsible for the device
    *         or someone acting for him/her
    */
   public final String getContact() {
	   return contact;
   }

   /**
    * Sets the information which identifies the individual or organization
    * responsible for administering the reader.
    * @param contact
    *            The contact information to the person responsible for the
    *            device or someone acting for him/her
    */
   public final void setContact(String contact) {
	   this.contact = contact;
   }

   /**
    * Returns the serial number of the reader device.
    * @return The serial number of the reader device
    */
   public final String getSerialNumber() {
	   return serialNumber;
   }

   /**
    * Returns the operational status of the reader device.
    * @return The <code>ReaderDevice</code> object's <code>operStatus</code>
    *         atribute.
    */
   public final OperationalStatus getOperStatus() {
	   return operStatus;
   }

   /**
    * Returns the operational status alarm control. This object controls the
    * conditions for generating alarms alerting a manager of changes in a
    * <coder>ReaderDevice</code>'s operational status.
    * @return The <code>ReaderDevice</code> object's <code>operStatusAlarmControl</code>
    *         attribute.
    */
   public final TTOperationalStatusAlarmControl getOperStatusAlarmControl() {
	   return operStatusAlarmControl;
   }

   /**
    * Returns the amount of free memory available on the reader device. This
    * value represents the number of kilobytes (KB) of Read/Write memory
    * available to internal or external resources for buffering or other
    * processing of information.
    * @return The available free memory specified in kilobytes (KB)
    */
   public final long getFreeMemory() {
	   freeMemory = (long)(Runtime.getRuntime().freeMemory() / 1024);
	   return freeMemory;
   }

   /**
    * Returns the object which controls the generation of alarms alerting a
    * managing system of a reduction of a reader's free memory resources below
    * a specified threshold.
    * @return The reader's <code>FreeMemoryAlarmControl</code> object of type <code>EdgeTriggeredAlarmControl</code>.
    */
   public final EdgeTriggeredAlarmControl getFreeMemoryAlarmControl() {
	   return freeMemoryAlarmControl;
   }

   /**
    * Returns a list of NTP servers used by the device to synchronize its
    * current UTC clock.
    * @return A list of NTP servers used by the device to synchronize its
    *         current UTC clock
    */
   public final String[] getNTPservers() {
	   int timeout = 1000;
	   int validMs = 1000; // The number of milliseconds after which the
                           // ntpServers attribute will be refreshed.
	   if (System.currentTimeMillis() - lastNTPServersRefreshTimestamp.getTime() > validMs) {
		   if (ntpServerFinder != null) {
			   Vector<InetAddress> servers = ntpServerFinder.findNTPServers(timeout);
			   ntpServers = new String[servers.size()];
			   for (int i = 0; i < servers.size(); i++) {
				   ntpServers[i] = servers.elementAt(i).getHostAddress() + "/123";
			   }
			   lastNTPServersRefreshTimestamp = new Date();
		   }
	   }
	   return ntpServers;
   }

   /**
    * Returns the DHCP server currently used by the device for DHCP requests.
    * @return The DHCP server currently used by the device for DHCP requests.
    */
   public final String getDHCPServer() {
	   if (dhcpServerFinder != null) {
		   InetAddress addr = dhcpServerFinder.findDHCPServer();
		   if (addr != null) {
			   dhcpServer = addr.getHostAddress() + "/67";
		   }
	   }
	   return dhcpServer;
   }

   /**
    * Returns the IO port with the specified name currently associated with
    * this reader. If no <code>IOPort</code> object with the given name
    * exists, the error <code>ERROR_IOPORT_NOT_FOUND</code> is raised.
    * @param name
    *            A name for the <code>IOPort</code> object which should be
    *            retrieved from the reader.
    * @return The named <code>IOPort</code> object
    */
   public final IOPort getIOPort(String name) throws ReaderProtocolException {
	   if (!ioPorts.containsKey(name)) {
		   throw new ReaderProtocolException("ERROR_IOPORT_NOT_FOUND",
				   MessagingConstants.ERROR_IOPORT_NOT_FOUND);
	   } else {
		   return ioPorts.get(name);
	   }
   }

   /**
	 * Returns all <code>IOPort</code> objects of the reader.
	 *
	 * @return All <code>IOPort</code> objects of the reader
	 */
   public final IOPort[] getAllIOPorts() {
	   IOPort[] ports = new IOPort[ioPorts.size()];
	   Enumeration<IOPort> iterator = ioPorts.elements();
	   int i = 0;
	   while (iterator.hasMoreElements()) {
		   ports[i++] = iterator.nextElement();
	   }
	   return ports;
   }

   /**
    * Instructs the reader to reset all statistical counters.
    */
   public final void resetStatistics() {
	   Enumeration iter;

	   // counters of the AntennaReadPoints
	   iter = readPoints.elements();
	   while (iter.hasMoreElements()) {
		   ReadPoint readPoint = (ReadPoint)iter.nextElement();
		   if (readPoint instanceof AntennaReadPoint) {
			   ((AntennaReadPoint)readPoint).resetCounters();
		   }
	   }

	   // counters of the sources
	   iter = sources.elements();
	   while (iter.hasMoreElements()) {
		   ((Source)iter.nextElement()).resetCounters();
	   }

	   // counters of the triggers
	   iter = triggers.elements();
	   while (iter.hasMoreElements()) {
		   ((Trigger)iter.nextElement()).resetFireCount();
	   }

	   // counters of the notification channels
	   iter = notificationChannels.elements();
	   while (iter.hasMoreElements()) {
		   ((NotificationChannel)iter.nextElement()).resetOperStateSuppressions();
	   }
   }

   /**
    * Removes the specified <code>AlarmChannels</code> from the list of
    * <code>AlarmChannels</code> currently associated with this reader. If one or more
    * of the <code>AlarmChannels</code> given are not known, or if some of the
    * <code>AlarmChannels</code> to be removed are currently not associated with this
    * reader, these are ignored and all other <code>AlarmChannels</code> shall be
    * removed and the command shall complete successfully.
    * @param channels
    *           The array with AlarmChannel objects to be removed from the reader
    */
   public final void removeAlarmChannels(AlarmChannel[] channels) {
	   for (int i = 0; i < channels.length; i++) {
		   alarmChannels.remove(channels[i].getName());
		   if (mgmtAgent != null) mgmtAgent.removeAlarmChannel(channels[i]);
	   }
   }

   /**
    * Removes all <code>AlarmChannels</code> currently associated with this reader.
    */
   public final void removeAllAlarmChannels() {
	   Enumeration<AlarmChannel> iterator = alarmChannels.elements();
	   while (iterator.hasMoreElements()) {
		   AlarmChannel curChan = iterator.nextElement();
		   if (mgmtAgent != null) mgmtAgent.removeAlarmChannel(curChan);
	   }
	   alarmChannels.clear();
   }

   /**
    * Returns the <code>AlarmChannel</code> with the specified name currently
    * associated with this reader. If no <code>AlarmChannel</code> object
    * with the given name exists, the error
    * <code>ERROR_CHANNEL_NOT_FOUND</code> is raised.
    *
    * @param name
    *            The name of the <code>AlarmChannel</code> object which
    *            should be retrieved from the reader
    * @return The named <code>AlarmChannel</code> object
    * @throws <code>AlarmChannel</code> not found
    */
   public final AlarmChannel getAlarmChannel(final String name)
			throws ReaderProtocolException {
		if (!alarmChannels.containsKey(name)) {
			throw new ReaderProtocolException("ERROR_CHANNEL_NOT_FOUND",
					MessagingConstants.ERROR_CHANNEL_NOT_FOUND);
		} else {
			return alarmChannels.get(name);
		}
	}

   /**
    * Returns all <code>AlarmChannels</code> currently associated with the
    * reader. If no <code>AlarmChannels</code> are currently associated with
    * this object, the command complete successfully and an empty list will be
    * returned.
    *
    * @return An array with all defined <code>AlarmChannel</code> objects for
    *         this reader
    */
   public final AlarmChannel[] getAllAlarmChannels() {
	   AlarmChannel[] alarmChans = new AlarmChannel[alarmChannels.size()];
	   Enumeration<AlarmChannel> iterator = alarmChannels.elements();
	   int i = 0;
	   while (iterator.hasMoreElements()) {
		   alarmChans[i++] = iterator.nextElement();
	   }
	   return alarmChans;
   }

   /**
    * Returns the alarm channels <code>Hashtable</code>.
    * @return The alarm channels <code>Hashtable</code>
    */
   public final Hashtable<String, AlarmChannel> getAlarmChannels() {
      return alarmChannels;
   }

   /**
    * Returns the alarm manager.
    * @return The alarm manager
    */
   public final AlarmManager getAlarmManager() {
	   return alarmManager;
   }

   /**
    * Returns the management agent.
    *
    * @return The management agent
    */
   public final MgmtAgent getManagementAgent() {
	   return mgmtAgent;
   }

   /**
    * Increases the number of device operation state change notifications that
    * have been suppressed by <code>1</code>.
    */
   public static final void increaseOperStateSuppressions() {
	   ReaderDevice.operStateSuppressions++;
   }

   /**
    * Returns the number of device operation state change notifications that
    * have been suppressed.
    *
    * @return The number of device operation state change notifications that
    *         have been suppressed
    */
   public static final int getOperStateSuppressions() {
	   return ReaderDevice.operStateSuppressions;
   }

   /**
    * Resets the number of device operation state change notifications that
    * have been suppressed to <code>0</code>.
    */
   public static final void resetOperStateSuppressions() {
	   ReaderDevice.operStateSuppressions = 0;
   }

   /**
    * Increases the number of device memory state change notifications that
    * have been suppressed by <code>1</code>.
    */
   public static final void increaseMemStateSuppressions() {
	   ReaderDevice.memStateSuppressions++;
   }

   /**
    * Returns the number of device memory state change notifications that have
    * been suppressed.
    *
    * @return The number of device memory state change notifications that have
    *         been suppressed
    */
   public static final int getMemStateSuppressions() {
	   return ReaderDevice.memStateSuppressions;
   }

   /**
    * Resets the number of device memory state change notifications that have
    * been suppressed to <code>0</code>.
    */
   public static final void resetMemStateSuppressions() {
	   ReaderDevice.memStateSuppressions = 0;
   }

   /**
    * Sets the operational status.
    *
    * @param operStatus
    *            The new operational status
    */
   private final void setOperStatus(OperationalStatus operStatus) {
	   if (operStatus != this.operStatus) {
		   if ((alarmManager != null) && (operStatusAlarmControl != null)) {
				alarmManager.fireAlarm(
						new ReaderDeviceOperStatusAlarm(
								"ReaderDeviceOperStatusAlarm",
								operStatusAlarmControl.getLevel(), this,
								this.operStatus, operStatus),
						operStatusAlarmControl);
				this.operStatus = operStatus;
			}
	   }
   }

	/**
	 * The main method.
	 *
	 * @param args
	 *            Not used
	 */
//	public static void main(String[] args) {
//		if (args.length == 1) {
//			PROPERTIES_FILE = args[0];
//		}
//		MessageLayer m;
//		m = new MessageLayer();
//	}
}
