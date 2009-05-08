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

import java.math.BigInteger;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.Vector;

import org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction;
import org.ow2.aspirerfid.reader.rp.impl.hal.UnsignedByteArray;
import org.ow2.aspirerfid.reader.rp.impl.hal.HardwareException;
import org.ow2.aspirerfid.reader.rp.impl.hal.Observation;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.AdministrativeStatus;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.OperationalStatus;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmLevel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.SourceOperStatusAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.TTOperationalStatusAlarmControl;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.util.HexUtil;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.ReadReport;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.ReaderInfoType;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.SourceInfoType;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.SourceReport;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagFieldValueParamType;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagType;
import org.ow2.aspirerfid.reader.rp.impl.core.triggers.ContinuousReadThread;
import org.ow2.aspirerfid.reader.rp.impl.core.triggers.IOEdgeTriggerPortManager;
import org.ow2.aspirerfid.reader.rp.impl.core.triggers.IOValueTriggerPortManager;
import org.ow2.aspirerfid.reader.rp.impl.core.triggers.TimerReadThread;
import org.ow2.aspirerfid.reader.rp.impl.core.util.ResourceLocator;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;
import org.osgi.framework.ServiceReference;

import org.ow2.aspirerfid.reader.rp.impl.Activator;
import org.ow2.aspirerfid.common.tdt.TDT;

/**
 * This class represents the Source of the object model.
 * @author Markus Vitalini, Nektarios Leontiadis (nele@ait.edu.gr)
 */
public final class Source {

	/**
	 * The logger.
	 */
	public static final Logger LOG = Logger.getLogger(Source.class);
   /**
    * This class contains a reader (HardwareAbstraction) and all of its read
    * points (ReadPoint) It is used to simplify read and write calls.
    * @author Markus Vitalini
    */
   public class ReaderAndReadPoints {

	   
      /**
       * The instance of the reader.
       */
      private HardwareAbstraction reader;

      /**
       * The readpoint instances of the reader. The elements are of type String
       * (the readpoint id).
       */
      private Hashtable readPoints;

      /**
       * Constructor.
       */
      public ReaderAndReadPoints() {
         this.reader = null;
         this.readPoints = new Hashtable();
      }

      /**
       * Get the reader instance.
       * @return Reader instance of type HardwareAbstraction
       */
      public final HardwareAbstraction getReader() {
         return reader;
      }

      /**
       * Set the reader instance.
       * @param reader
       *           The reader to set
       */
      public final void setReader(final HardwareAbstraction reader) {
         this.reader = reader;
      }

      /**
       * Get all readpoints.
       * @return Returns a Vector of readpoints. The elements are of type String
       *         and contain the id of the readpoint.
       */
      public final Hashtable getAllReadPoints() {
         return readPoints;
      }

      /**
       * Get all readpoints as array of String.
       * @return Returns a String array with the ids of the readpoints
       */
      public final String[] getAllReadPointsAsArray() {

         String[] tempStrArray = new String[getAllReadPoints().size()];

         Enumeration iterator = getAllReadPoints().elements();
         String curReadPoint;
         int i = 0;
         while (iterator.hasMoreElements()) {
            curReadPoint = (String) iterator.nextElement();

            tempStrArray[i] = curReadPoint;
            i++;

         }

         return tempStrArray;

      }

      /**
       * Check if the readpoint is contained in the list of readpoints.
       * @param id
       *           Id of the readpoint
       * @return 'true' if the readpoint is in this class, otherwise 'false'
       */
      public final boolean containsReadPoint(final String id) {
         return readPoints.contains(id);
      }

      /**
       * Add a readpoint to the list of readpoints.
       * @param id
       *           Id of the readpoint
       */
      public final void addReadPoint(final String id) {
         readPoints.put(id, id);
      }

      /**
       * Remove a readpoint from list.
       * @param id
       *           Id of the readpoint to remove
       */
      public final void removeReadPoint(final String id) {
         readPoints.remove(id);
      }

   }

   /** The logger. */
	private static Logger log = Logger.getLogger(Source.class);



   /**
    * List of read triggers.
    * @link aggregation <{Trigger}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates Trigger
    */
   private Hashtable readTriggers;

   /**
    * List of tag selectors.
    * @link aggregation <{TagSelector}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates TagSelector
    */
   private Hashtable tagSelectors;

   /**
    * List of read points.
    * @link aggregation <{ReadPoint}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates ReadPoint
    */
   private Hashtable readPoints;

   /**
    * The glimpsed Timeout in ms.
    */
   private int glimpsedTimeout;

   /**
    * Flag, if the source is fixed.
    */
   private boolean isFixed;

   /**
    * The lost timeout in ms.
    */
   private int lostTimeout;

   /**
    * The maximal read duty cycles.
    */
   private int maxReadDutyCycles;

   /**
    * The name of the source.
    */
   private String name;

   /**
    * The observed Threshold in ms.
    */
   private int observedThreshold;

   /**
    * The observed timeout in ms.
    */
   private int observedTimeout;

   /**
    * The number of read cycles per trigger.
    */
   private int readCyclesPerTrigger;

   /**
    * The read timeout in ms.
    */
   private int readTimeout;

   /**
    * The session.
    */
   private int session;

   /**
    * The reader device this source belongs to.
    */
   private ReaderDevice readerDevice;

   /**
    * The current state.
    * @link aggregation <{TagState}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates TagState
    */
   private Hashtable currentState;

   /**
    * Contains the read report. This report is available for all notification.
    * channels
    */
   private SourceReport sourceReport;

   /**
    * The thread for continuous triggers.
    */
   private ContinuousReadThread continuousThread;

   /**
    * The threads for timer triggers (of type Timer).
    */
   private Hashtable timerThreads;

   /**
    * The notificationChannels to report events.
    */
   private Hashtable notificationChannels;

   /**
    * The set of tags ever detected at this source
    */
   private Set tagsEverDetected;

   /**
    * The path of the property file.
    */
   private static final String sourcePropFile = ReaderDevice.PROPERTIES_FILE;
   private static final String sourceDefaultPropFile = ReaderDevice.DEFAULT_PROPERTIES_FILE;

   /**
    * Key for the isFixed property.
    */
   private static final String IS_FIXED = "isFixed";

   /**
    * Default value for isFixed.
    */
   public static final String IS_FIXED_DEFAULT = "false";

   /**
    * Key for the glimpsedTimeout property.
    */
   private static final String GLIMPSED_TIMEOUT = "glimpsedTimeout";

   /**
    * Default value for glimpsedTimeout.
    */
   public static final String GLIMPSED_TIMEOUT_DEFAULT = "2000";

   /**
    * Key for the observedThreshold property.
    */
   private static final String OBSERVED_THRESHOLD = "observedThreshold";

   /**
    * Default value for observedThreshold.
    */
   public static final String OBSERVED_THRESHOLD_DEFAULT = "0";

   /**
    * Key for the observedTimeout property.
    */
   private static final String OBSERVED_TIMEOUT = "observedTimeout";

   /**
    * Default value for observedTimeout.
    */
   public static final String OBSERVED_TIMEOUT_DEFAULT = "1000";

   /**
    * Key for the lostTimeout property.
    */
   private static final String LOST_TIMEOUT = "lostTimeout";

   /**
    * Default value for lostTimeout.
    */
   public static final String LOST_TIMEOUT_DEFAULT = "0";

   /**
    * Key for the readCyclesPerTrigger property.
    */
   private static final String READ_CYCLES_PER_TRIGGER = "readCyclesPerTrigger";

   /**
    * Default value for readCyclesPerTrigger.
    */
   public static final String READ_CYCLES_PER_TRIGGER_DEFAULT = "1";

   /**
    * Key for the maxReadDutyCycles property.
    */
   private static final String MAX_READ_DUTY_CYCLES = "maxReadDutyCycles";

   /**
    * Default value for maxReadDutyCycles.
    */
   public static final String MAX_READ_DUTY_CYCLES_DEFAULT = "100";

   /**
    * Key for the readTimeout property.
    */
   private static final String READ_TIMEOUT = "readTimeout";

   /**
    * Default value for readTimeout.
    */
   public static final String READ_TIMEOUT_DEFAULT = "0";

   /**
    * The properties. 
    */
   private static XMLConfiguration configuration;


   // ====================================================================
   // ----- Fields added for the reader management implementation ------//
   // ====================================================================

   /**
    * The number of times a transition from state Unknown to state Glimpsed has
    * been detected for this particular source.
    */
   private int unknownToGlimpsedCount;

   /**
    * The number of times a transition from state Glimpsed to state Unwnown has
    * been detected for this particular source.
    */
   private int glimpsedToUnknownCount;

   /**
    * The number of times a transition from state Glimpsed to state Observed
    * has been detected for this particular source.
    */
   private int glimpsedToObservedCount;

   /**
    * The number of times a transition from state Observed to state Lost has
    * been detected for this particular source.
    */
   private int observedToLostCount;

   /**
    * The number of times a transition from state Lost to state Glimpsed has
    * been detected for this particular source.
    */
   private int lostToGlimpsedCount;

   /**
    * The number of times a transition from state Lost to state Unknown has
    * been detected for this particular source.
    */
   private int lostToUnknownCount;

   /**
    * The administrative status of a particular <code>Source</code>. This
    * represents the host's desired status for this <code>Source</code>.
    */
   private AdministrativeStatus adminStatus;

   /**
    * The operational status of this particular <code>Source</code>.
    */
   private OperationalStatus operStatus;

   /**
    * Controls the conditions for generating alarms alerting a manager of
    * changes in a <code>Source</code>'s operational status.
    */
   private TTOperationalStatusAlarmControl operStatusAlarmControl;

   /**
    * The number of source operational state change notifications that have
    * been suppressed for this <code>Source</code> object.
    */
   private int operStateSuppressions = 0;

   /**
    * The accumulated memory read counter of all <code>AntennaReadPoint</code>s.
    */
   private int antReadPointMemReadCount;

   /**
    * The accumulated write counter of all <code>AntennaReadPoint</code>s.
    */
   private int antReadPointWriteCount;

   /**
    * The accumulated kill counter of all <code>AntennaReadPoint</code>s.
    */
   private int antReadPointKillCount;


   /**
    * Create a source object with a given name. If a source object with the same
    * name exists, an error is returned ("ERROR_OBJECT_EXISTS"). This is a
    * static method. The source shall implicitly be added to the list of all
    * sources kept by the ReaderDevice object.
    * @param name
    *           The name of the source
    * @param readerDevice
    *           The reader device it belongs to
    * @return The instance of the new source
    * @throws ReaderProtocolException
    *            "ERROR_UNKNOWN"
    */
   public static Source create(final String name,
         final ReaderDevice readerDevice) throws ReaderProtocolException {

      if (readerDevice.getMaxSourceNumber() <= readerDevice.getSources()
            .size()) {
         throw new ReaderProtocolException("ERROR_UNKNOWN",
               MessagingConstants.ERROR_UNKNOWN);
      }

      // check if DataSelector with the same name exists
      try {
         readerDevice.getSource(name);
      } catch (ReaderProtocolException e) {
         // create new Source
         Source newSource = new Source(name, readerDevice);
         readerDevice.getSources().put(name, newSource);
         return newSource;
      }

      throw new ReaderProtocolException("ERROR_OBJECT_EXISTS",
            MessagingConstants.ERROR_OBJECT_EXISTS);

   }

   /**
    * The private constructor of the source.
    * @param name
    *           The name of the source
    * @param readerDevice
    *           The ReaderDevice it belongs to
    */
   private Source(final String name, final ReaderDevice readerDevice) {

      this.name = name;
      this.readerDevice = readerDevice;

      // initial values
      configuration = getProperties();
      String isFixed_str = configuration.getString(IS_FIXED);
      if (isFixed_str != null && isFixed_str.equalsIgnoreCase("true")) {
    	  this.isFixed = true;
      } else {
    	  this.isFixed = false;
      }
      this.glimpsedTimeout = Integer.parseInt(configuration.getString(
            GLIMPSED_TIMEOUT,
            GLIMPSED_TIMEOUT_DEFAULT));
      this.observedThreshold = Integer.parseInt(configuration.getString(
              OBSERVED_THRESHOLD,
              OBSERVED_THRESHOLD_DEFAULT));
      this.observedTimeout = Integer.parseInt(configuration.getString(
              OBSERVED_TIMEOUT,
              OBSERVED_TIMEOUT_DEFAULT));
      this.lostTimeout = Integer.parseInt(configuration.getString(
              LOST_TIMEOUT,
              LOST_TIMEOUT_DEFAULT));
      this.readCyclesPerTrigger = Integer.parseInt(configuration.getString(
              READ_CYCLES_PER_TRIGGER,
              READ_CYCLES_PER_TRIGGER_DEFAULT));
      this.maxReadDutyCycles = Integer.parseInt(configuration.getString(
              MAX_READ_DUTY_CYCLES,
              MAX_READ_DUTY_CYCLES_DEFAULT));
      this.readTimeout = Integer.parseInt(configuration.getString(
              READ_TIMEOUT,
              READ_TIMEOUT_DEFAULT));
      this.session = 0;
      this.readPoints = new Hashtable();
      this.readTriggers = new Hashtable();
      this.tagSelectors = new Hashtable();
      this.currentState = new Hashtable();
      this.continuousThread = null;
      this.timerThreads = new Hashtable();
      this.notificationChannels = new Hashtable();
      this.tagsEverDetected = new HashSet();

      resetCounters();
      this.adminStatus = AdministrativeStatus.UP;
      this.operStatus = OperationalStatus.UNKNOWN;

      operStatusAlarmControl = new TTOperationalStatusAlarmControl(name
              + "_OperStatusAlarmControl", false, AlarmLevel.ERROR, 0,
              OperationalStatus.ANY, OperationalStatus.ANY);

   }

   /**
    * Singleton implementation of properties file accessor.
    * 
    * @return properties instance
    */
   private static XMLConfiguration getProperties() {
      return getProperties(sourcePropFile, sourceDefaultPropFile);
   }

   /**
    * Singleton implementation of properties file accessor.
    * 
    * @return properties instance
    */
   private static XMLConfiguration getProperties(final String propFile,
         final String defaultPropFile) {
      if (configuration == null) {
         // properties
         configuration = new XMLConfiguration();
         try {
            // load resource from where this class is located
            URL fileurl = ResourceLocator.getURL(propFile, defaultPropFile);
            configuration.load(fileurl);
         } catch (ConfigurationException e) {
            log.error("Could not find properties file: " + propFile);
         }
      }
      return configuration;
   }

   /**
    * Returns the name of the source.
    * @return The name of the source
    */
   public String getName() {
      return name;
   }

   /**
    * Check if the source is fixed.
    * @return 'true' if the source is fixed, otherwise false
    */
   public boolean isFixed() {
      return isFixed;
   }

   /**
    * Sets the fixed flag.
    * @param isFixed
    *           Indicate if the source is fixed
    */
   public void setFixed(final boolean isFixed) {
      this.isFixed = isFixed;
   }

   /**
    * Adds the specified ReadPoints to the list of readpoints currently
    * associated with this source. If some of the ReadPoints to be added are
    * already associated with this source, only the not yet associated
    * ReadPoints will be added.
    * @param readPointList
    *           The list of readpoints
    */
   public void addReadPoints(final ReadPoint[] readPointList) {

      Vector readPoints = readerDevice.getVector(readPointList);

      Enumeration iterator = readPoints.elements();
      ReadPoint cur;

      while (iterator.hasMoreElements()) {
         cur = (ReadPoint) iterator.nextElement();
         if (!this.readPoints.containsKey(cur.getName())) {
            this.readPoints.put(cur.getName(), cur);
         }
      }

   }

   /**
    * Removes the specified ReadPoints from the list of ReadPoints currently
    * associated with this source.
    * @param readPointList
    *           The list of readpoints
    */
   public void removeReadPoints(final ReadPoint[] readPointList) {

      Vector readPoints = readerDevice.getVector(readPointList);

      Enumeration iterator = readPoints.elements();
      ReadPoint cur;

      while (iterator.hasMoreElements()) {
         cur = (ReadPoint) iterator.nextElement();
         this.readPoints.remove(cur.getName());
      }
   }

   /**
    * Remove all readpoints from source.
    */
   public void removeAllReadPoints() {
      removeReadPoints((ReadPoint[]) readerDevice
            .readPointsToArray(readPoints));
   }

   /**
    * Get a readpoint by name.
    * @param name
    *           The name of the readpoint
    * @return The instance of the readpoint
    * @throws ReaderProtocolException
    *            "ERROR_READPOINT_NOT_FOUND"
    */
   public ReadPoint getReadPoint(final String name)
         throws ReaderProtocolException {

      if (!readPoints.containsKey(name)) {
         throw new ReaderProtocolException("ERROR_READPOINT_NOT_FOUND",
               MessagingConstants.ERROR_READPOINT_NOT_FOUND);
      } else {
         return (ReadPoint) readPoints.get(name);
      }

   }

   /**
    * Get all readpoits of the source.
    * @return The list of readpoints
    */
   public ReadPoint[] getAllReadPoints() {
      return (ReadPoint[]) readerDevice.readPointsToArray(readPoints);
   }

   /**
    * Add a list of read triggers to this source.
    * @param triggerList
    *           The list of read triggers
    * @throws ReaderProtocolException
    *            "ERROR_TOO_MANY_TRIGGERS"
    */
   public void addReadTriggers(final Trigger[] triggerList)
         throws ReaderProtocolException {

      Vector triggers = readerDevice.getVector(triggerList);

      if (readerDevice.getMaxTriggerNumber() <= triggers.size()) {
         throw new ReaderProtocolException("ERROR_TOO_MANY_TRIGGERS",
               MessagingConstants.ERROR_TOO_MANY_TRIGGERS);
      }

      Enumeration iterator = triggers.elements();
      Trigger cur;

      while (iterator.hasMoreElements()) {
         cur = (Trigger) iterator.nextElement();
         if (!this.readTriggers.containsKey(cur.getName())) {
            this.readTriggers.put(cur.getName(), cur);
            if (cur.getType().equals(TriggerType.CONTINUOUS)) {
               // conitnuous trigger
               if (continuousThread == null && timerThreads.size() == 0) {
                  continuousThread = new ContinuousReadThread(this, cur);
                  continuousThread.start();
               }
            } else if (cur.getType().equals(TriggerType.TIMER)) {
               // timer trigger
               if (continuousThread == null) {
                  Timer timerThread = new Timer();
                  timerThreads.put(cur.getName(), timerThread);
                  final int num = 3;
                  timerThread.schedule(new TimerReadThread(this, cur), 0,
                        Integer.parseInt(cur.getValue().substring(num)));
               }
            } else if (cur.getType().equals(TriggerType.IO_EDGE)) {
               // io edge trigger
               if (continuousThread == null) {
                  // get port
                  final int num = 6;
                  String port = cur.getValue().substring(
                        cur.getValue().indexOf(';') + num,
                        cur.getValue().lastIndexOf(';'));
                  if (readerDevice.getEdgeTriggers().containsKey(port)) {
                     IOEdgeTriggerPortManager manager =
                           (IOEdgeTriggerPortManager) readerDevice
                           .getEdgeTriggers().get(port);
                     manager.addListener(cur, this.getName());
                     if (manager.getNumberOfTriggers() == 1) {
                        manager.start();
                     }
                  } else {
                     throw new ReaderProtocolException(
                           "no trigger manager available",
                           MessagingConstants.ERROR_UNKNOWN);
                  }
               }
            } else if (cur.getType().equals(TriggerType.IO_VALUE)) {
               // io value trigger
               if (continuousThread == null) {
                  // get port
                  final int num = 5;
                  String port = cur.getValue().substring(num,
                        cur.getValue().indexOf(';'));
                  if (readerDevice.getValueTriggers().containsKey(port)) {
                     IOValueTriggerPortManager manager =
                           (IOValueTriggerPortManager) readerDevice
                           .getValueTriggers().get(port);
                     manager.addListener(cur, this.getName());
                     if (manager.getNumberOfTriggers() == 1) {
                        manager.start();
                     }
                  } else {
                     throw new ReaderProtocolException(
                           "no trigger manager available",
                           MessagingConstants.ERROR_UNKNOWN);
                  }
               }
            }

         }
      }

   }

   /**
    * Remove a list of read triggers.
    * @param triggerList
    *           The list of read triggers
    */
   public void removeReadTriggers(final Trigger[] triggerList) {

      Vector triggers = readerDevice.getVector(triggerList);

      Enumeration iterator = triggers.elements();
      Trigger cur;

      while (iterator.hasMoreElements()) {
         cur = (Trigger) iterator.nextElement();

         if (readTriggers.containsKey(cur.getName())) {
            if (cur.getType().equals(TriggerType.CONTINUOUS)) {
               // continuous trigger
               continuousThread.stop();
               continuousThread = null;
            } else if (cur.getType().equals(TriggerType.TIMER)) {
               // timer trigger
               if (timerThreads.containsKey(cur.getName())) {
                  Timer t = (Timer) timerThreads.get(cur.getName());
                  t.cancel();
                  timerThreads.remove(cur.getName());
               }
            } else if (cur.getType().equals(TriggerType.IO_EDGE)) {
               // io edge trigger
               // get port
               final int num = 6;
               String port = cur.getValue().substring(
                     cur.getValue().indexOf(';') + num,
                     cur.getValue().lastIndexOf(';'));
               if (readerDevice.getEdgeTriggers().containsKey(port)) {
                  IOEdgeTriggerPortManager manager =
                        (IOEdgeTriggerPortManager) readerDevice
                        .getEdgeTriggers().get(port);
                  manager.removeListener(cur, this.getName());
                  if (manager.getNumberOfTriggers() <= 0) {
                     manager.stop();
                  }
               }
            } else if (cur.getType().equals(TriggerType.IO_VALUE)) {
               // io value trigger
               // get port
               final int num = 5;
               String port = cur.getValue().substring(num,
                     cur.getValue().indexOf(';'));
               if (readerDevice.getValueTriggers().containsKey(port)) {
                  IOValueTriggerPortManager manager =
                        (IOValueTriggerPortManager) readerDevice
                        .getValueTriggers().get(port);
                  manager.removeListener(cur, this.getName());
                  if (manager.getNumberOfTriggers() <= 0) {
                     manager.stop();
                  }
               }
            }

            this.readTriggers.remove(cur.getName());
         }
      }

   }

   /**
    * Remove all read triggers from source.
    */
   public void removeAllReadTriggers() {
      removeReadTriggers((Trigger[]) readerDevice
            .triggersToArray(readTriggers));
   }

   /**
    * Get a read trigger by name.
    * @param name
    *           The name of the read trigger
    * @return The instance of the read trigger
    * @throws ReaderProtocolException
    *            "ERROR_TRIGGER_NOT_FOUND"
    */
   public Trigger getReadTrigger(final String name)
         throws ReaderProtocolException {

      if (!readTriggers.containsKey(name)) {
         throw new ReaderProtocolException("ERROR_TRIGGER_NOT_FOUND",
               MessagingConstants.ERROR_TRIGGER_NOT_FOUND);
      } else {
         return (Trigger) readTriggers.get(name);
      }

   }

   /**
    * Get all read triggers.
    * @return The list of read triggers
    */
   public Trigger[] getAllReadTriggers() {
      return (Trigger[]) readerDevice.triggersToArray(readTriggers);
   }

   /**
    * Add a list of tag selectors.
    * @param selectorList
    *           The list of tag selectors
    * @throws ReaderProtocolException
    *            "ERROR_TOO_MANY_TAGSELECTORS"
    */
   public void addTagSelectors(final TagSelector[] selectorList)
         throws ReaderProtocolException {

      Vector selectors = readerDevice.getVector(selectorList);

      if (readerDevice.getMaxTagSelectorNumber() <= tagSelectors.size()) {
         throw new ReaderProtocolException("ERROR_TOO_MANY_TAGSELECTORS",
               MessagingConstants.ERROR_TOO_MANY_TAGSELECTORS);
      }

      Enumeration iterator = selectors.elements();
      TagSelector cur;

      while (iterator.hasMoreElements()) {
         cur = (TagSelector) iterator.nextElement();
         if (!this.tagSelectors.containsKey(cur.getName())) {
            this.tagSelectors.put(cur.getName(), cur);
         }
      }

   }

   /**
    * Remove a list of tag selectors.
    * @param tagSelectorList
    *           The list of tag selectors
    */
   public void removeTagSelectors(final TagSelector[] tagSelectorList) {

      Vector tagSelectors = readerDevice.getVector(tagSelectorList);

      Enumeration iterator = tagSelectors.elements();
      TagSelector cur;

      while (iterator.hasMoreElements()) {
         cur = (TagSelector) iterator.nextElement();
         this.tagSelectors.remove(cur.getName());
      }
   }

   /**
    * Remove all tag selectors.
    */
   public void removeAllTagSelectors() {
      removeTagSelectors((TagSelector[]) readerDevice
            .tagSelectorsToArray(tagSelectors));
   }

   /**
    * Get a tag selector by name.
    * @param name
    *           The name of the tag selector
    * @return The instance of the tag selector
    * @throws ReaderProtocolException
    *            "ERROR_TAGSELECTOR_NOT_FOUND"
    */
   public TagSelector getTagSelector(final String name)
         throws ReaderProtocolException {

      if (!tagSelectors.containsKey(name)) {
         throw new ReaderProtocolException("ERROR_TAGSELECTOR_NOT_FOUND",
               MessagingConstants.ERROR_TAGSELECTOR_NOT_FOUND);
      } else {
         return (TagSelector) tagSelectors.get(name);
      }

   }

   /**
    * Get all tag selectors.
    * @return The list of tag selectors
    */
   public TagSelector[] getAllTagSelectors() {
      return (TagSelector[]) readerDevice.tagSelectorsToArray(tagSelectors);
   }

   /**
    * Get the glimpsed timeout value.
    * @return The glimpsed timeout in ms
    */
   public int getGlimpsedTimeout() {
      return glimpsedTimeout;
   }

   /**
    * Set the glimpse timeout value (0..infinit ms).
    * @param timeout
    *           The glimpsed timeout in ms
    * @throws ReaderProtocolException
    *            "ERROR_PARAMETER_OUT_OF_RANGE"
    */
   public void setGlimpsedTimeout(final int timeout)
         throws ReaderProtocolException {
      if (timeout < 0) {
         throw new ReaderProtocolException("ERROR_PARAMETER_OUT_OF_RANGE",
               MessagingConstants.ERROR_PARAMETER_OUT_OF_RANGE);
      }
      this.glimpsedTimeout = timeout;
   }

   /**
    * Get the observed threshold value.
    * @return The observed threshold in ms
    */
   public int getObservedThreshold() {
      return observedThreshold;
   }

   /**
    * Set the observed threshold value (0..infinit).
    * @param threshold
    *           The observed threshold in ms
    * @throws ReaderProtocolException
    *            "ERROR_PARAMETER_OUT_OF_RANGE"
    */
   public void setObservedThreshold(final int threshold)
         throws ReaderProtocolException {
      if (threshold < 0) {
         throw new ReaderProtocolException("ERROR_PARAMETER_OUT_OF_RANGE",
               MessagingConstants.ERROR_PARAMETER_OUT_OF_RANGE);
      }
      this.observedThreshold = threshold;
   }

   /**
    * Get the observed timeout value.
    * @return The observed timeout in ms
    */
   public int getObservedTimeout() {
      return observedTimeout;
   }

   /**
    * Set the observed timeout (0..infinit ms).
    * @param timeout
    *           The observed timeout value in ms
    * @throws ReaderProtocolException
    *            "ERROR_PARAMETER_OUT_OF_RANGE"
    */
   public void setObservedTimeout(final int timeout)
         throws ReaderProtocolException {
      if (timeout < 0) {
         throw new ReaderProtocolException("ERROR_PARAMETER_OUT_OF_RANGE",
               MessagingConstants.ERROR_PARAMETER_OUT_OF_RANGE);
      }
      this.observedTimeout = timeout;
   }

   /**
    * Get the lost timeout value.
    * @return The lost timeout in ms
    */
   public int getLostTimeout() {
      return lostTimeout;
   }

   /**
    * Set the lost timeout value (0..infinit ms).
    * @param timeout
    *           The lost timeout in ms
    * @throws ReaderProtocolException
    *            "ERROR_PARAMETER_OUT_OF_RANGE"
    */
   public void setLostTimeout(final int timeout)
         throws ReaderProtocolException {
      if (timeout < 0) {
         throw new ReaderProtocolException("ERROR_PARAMETER_OUT_OF_RANGE",
               MessagingConstants.ERROR_PARAMETER_OUT_OF_RANGE);
      }
      this.lostTimeout = timeout;
   }

   /**
    * Performs a single read cycle without using the corresponding Source
    * objects list of TagSelectors. The resulting ReadReport shall be formatted
    * according to the given DataSelector.
    * @param dataselector
    *           The data selector to use
    * @return The read report
    */
   public ReadReport rawReadIDs(final DataSelector dataselector) {

      // if dataSelector is null, use the default dataSselector
      DataSelector dataSelector;
      if (dataselector == null) {
         dataSelector = readerDevice.getCurrentDataSelector();
      } else {
         dataSelector = dataselector;
      }

      SourceReport sourceReport = new SourceReport();

      // get all relevant readers with their readpoints
      Vector closure = getReaderAndReadPoints();

      // collection of all observations
      Observation[] tempObservations;
      Vector allObservations = new Vector();

      // closure
      Enumeration closureIterator = closure.elements();
      ReaderAndReadPoints curClosure;
      while (closureIterator.hasMoreElements()) {
         curClosure = (ReaderAndReadPoints) closureIterator.nextElement();

         try {
        	 tempObservations = curClosure.getReader().identify(curClosure.getAllReadPointsAsArray());
        	 for (int i = 0; i < tempObservations.length; i++) {
        		 Observation observation = tempObservations[i];
        		 if (observation.successful) {
        			 allObservations.add(tempObservations[i]);
        		 } else {
        			 ReadPoint readPoint = (ReadPoint) readPoints.get(observation.getReadPointName());
        			 if (readPoint instanceof AntennaReadPoint) {
        				 ((AntennaReadPoint) readPoint).increaseFailedIdentificationCount();
        			 }
        		 }
        	 }
         } catch (HardwareException he) {
        	 log.error(he.getMessage());
         }

      }

      // add tags to read report
      Enumeration observationIterator = allObservations.elements();
      Observation curObservation;
      while (observationIterator.hasMoreElements()) {
         curObservation = (Observation) observationIterator.nextElement();

         for (int i = 0; i < curObservation.getIds().length; i++) {
            try {
               if (!sourceReport.containsTag(curObservation.getIds()[i])) {
                  addTagToReport(curObservation.getIds()[i], sourceReport,
                        dataSelector, closure, null);
               }
            } catch (Exception e) {
            	// TODO: catch the concrete exception and not Exception
               System.out.println(e.getMessage());
            }
         }

      }

      ReadReport readReport = new ReadReport();

      // Complete the report
      addReaderInfo(readReport, dataSelector);
      addSourceInfo(sourceReport, dataSelector);

      readReport.addSourceReport(sourceReport);

      // update read point counter
      updateAntennaReadPointIdentificationCount(allObservations);

      return readReport;

   }

   /**
    * Performs multiple read cycles using all TagSelectors currently associated
    * with this Source. The number of read cycles performed shall be determined
    * by the value of the Source attribute ReadCyclesPerTrigger.The resulting
    * ReadReport is formatted according to the given DataSelector. If a tag is
    * seen in several read cycles, it shall only be reported once. Note that
    * this command does not use event generation.
    * @param dataselector
    *           The data selector to use
    * @return The read report
    */
   public ReadReport readIDs(final DataSelector dataselector) {

      DataSelector dataSelector;
      if (dataselector == null) {
         dataSelector = readerDevice.getCurrentDataSelector();
      } else {
         dataSelector = dataselector;
      }

      SourceReport sourceReport = new SourceReport();

      // get all relevant readers with their readpoints
      Vector closure = getReaderAndReadPoints();

      // temp variables
      Observation[] tempObservations;
      Vector allObservations = new Vector();

      // for time check
      Date date = new Date();
      long timeStart = date.getTime();

      // read cycles
      for (int readCycle = 0; readCycle < getReadCyclesPerTrigger(); readCycle++) {

         // closure
         Enumeration closureIterator = closure.elements();
         ReaderAndReadPoints curClosure;
         while (closureIterator.hasMoreElements()) {
            curClosure = (ReaderAndReadPoints) closureIterator.nextElement();


            try {
           	 tempObservations = curClosure.getReader().identify(curClosure.getAllReadPointsAsArray());
           	 for (int i = 0; i < tempObservations.length; i++) {
           		 Observation observation = tempObservations[i];
           		 if (observation.successful) {
           			 allObservations.add(tempObservations[i]);
           		 } else {
           			 ReadPoint readPoint = (ReadPoint) readPoints.get(observation.getReadPointName());
           			 if (readPoint instanceof AntennaReadPoint) {
           				 ((AntennaReadPoint) readPoint).increaseFailedIdentificationCount();
           			 }
           		 }
           	 }
            } catch (HardwareException he) {
           	 log.error(he.getMessage());
            }

         }

         date = new Date();
         if (date.getTime() - timeStart > getReadTimeout()) {
            break;
         }

      }

      // add tags to read report
      Enumeration observationIterator = allObservations.elements();
      Observation curObservation;
      while (observationIterator.hasMoreElements()) {
         curObservation = (Observation) observationIterator.nextElement();

         // tags
         for (int i = 0; i < curObservation.getIds().length; i++) {
            try {
               if (!sourceReport.containsTag(curObservation.getIds()[i])) {
                  if (isRelevantTag(curObservation.getIds()[i],
                        tagSelectors, closure)) {
                     addTagToReport(curObservation.getIds()[i],
                           sourceReport, dataSelector, closure, null);
                  }
               }
            } catch (Exception e) {
            	// TODO: catch the concrete exception and not Exception
               LOG.error(e.getMessage());
            }
         }

      }

      ReadReport readReport = new ReadReport();

      // Complete the report
      addReaderInfo(readReport, dataSelector);
      addSourceInfo(sourceReport, dataSelector);

      readReport.addSourceReport(sourceReport);

      // update read point counter
      updateAntennaReadPointIdentificationCount(allObservations);

      return readReport;

   }

   /**
    * Performs multiple read cycles on the selected group of tags. The number of
    * read cycles performed shall be determined by the value of the Source
    * attribute ReadCyclesPerTrigger. The resulting ReadReport is formatted
    * according to the given DataSelector. If a tag is seen in several read
    * cycles, it shall only be reported once. Note that this command does not
    * apply tag smoothing
    * @param dataSelector
    *           The data selector to use
    * @param passwords
    *           Not yet supported
    * @return The read report
    */
   public ReadReport read(final DataSelector dataSelector,
         final Hashtable passwords) {

	   return readIDs(dataSelector);

   }

   /**
    * Performs a single read cycle (with event generation).
    * @param dataSelector
    *           The dataselector for report generation
    * @param trigger
    *           The trigger that caused the call of this method
    */
   public void readWithEventGeneration(final DataSelector dataSelector,
         final Trigger trigger) {

      // get TagFields !

      Hashtable tagFieldNames = new Hashtable();

      // get all associated NotificationChannels
      Enumeration notificationChannelIterator = getAllNotificationChannels()
            .elements();
      NotificationChannel curNotificationChannel;
      while (notificationChannelIterator.hasMoreElements()) {
         curNotificationChannel = (NotificationChannel)
               notificationChannelIterator
               .nextElement();

         Enumeration tagFieldNameIterator;
         try {
            tagFieldNameIterator = curNotificationChannel.getDataSelector()
                  .getTagFieldNames().elements();
         } catch (ReaderProtocolException e) {
            // no dataSelector associated to curNotificationChannel
            break;
         }
         String curTagFieldName;
         while (tagFieldNameIterator.hasMoreElements()) {
            curTagFieldName = (String) tagFieldNameIterator.nextElement();
            tagFieldNames.put(curTagFieldName, curTagFieldName);
         }

      }
      dataSelector.addTagFieldNames((String[]) readerDevice
            .stringsToArray(tagFieldNames));

      // actual time
      Date now = new Date();

      // read part !

      // get all relevant readers with their readpoints
      Vector closure = getReaderAndReadPoints();

      // collection of all observations
      Observation[] tempObservations;
      Vector allObservations = new Vector();

      // closure
      Enumeration closureIterator = closure.elements();
      ReaderAndReadPoints curClosure;
      // TODO: anders strukturieren: erst checken welcher service
      // vorhanden dann fuer alle HALs die Abfragen machen
      while (closureIterator.hasMoreElements()) {
         curClosure = (ReaderAndReadPoints) closureIterator.nextElement();

         try {
            String[] arpaa = curClosure.getAllReadPointsAsArray();
            HardwareAbstraction rd = curClosure.getReader();
            tempObservations = rd.identify(arpaa);
            for (int i = 0; i < tempObservations.length; i++) {
               Observation observation = tempObservations[i];
               if (observation.successful) {
                  allObservations.add(tempObservations[i]);
               } else {
                  ReadPoint readPoint = (ReadPoint) readPoints.get(observation.getReadPointName());
                  if (readPoint instanceof AntennaReadPoint) {
                     ((AntennaReadPoint) readPoint).increaseFailedIdentificationCount();
                  }
               }
            }
         } catch (HardwareException he) {
            log.error(he.getMessage());
         }

      }

      // event part !

      // observed tags
      Enumeration observationIterator = allObservations.elements();
      Observation curObservation;
      while (observationIterator.hasMoreElements()) {
         curObservation = (Observation) observationIterator.nextElement();

         // tags
         for (int i = 0; i < curObservation.getIds().length; i++) {
            // update last read timestamp
        	tagsEverDetected.add(curObservation.getIds()[i]);
            updateLastReadTimestamp(curObservation.getIds()[i],
                  (new Date()).getTime());

         }

      }
      log.info("[Source: " + this.getName() + "] Tags ever detected: " + tagsEverDetected);

      // generate new report (events)
      setSourceReport(updateCurrentState(now, dataSelector, closure, trigger));

      /*
      if (getSourceReport().getAllTags().size() > 0) {
         // Complete the report
         addSourceInfo(getSourceReport(), dataSelector);

      }
      */

      //    Complete the report
      addSourceInfo(getSourceReport(), dataSelector);

      log.debug("[Source: " + this.getName() + "] Tags reported: " + getSourceReport().getAllTags().keySet());

      /* commented out by CF on 20/9/2006
      if (log.isDebugEnabled()) {
       	  if (getSourceReport().getSourceInfo().getSourceName() == null) {
       		  log.debug("Source name is not set in source report");
       	  }
       	  else {
       		  log.debug("Source name is set to " + getSourceReport().getSourceInfo().getSourceName() + " in source report");
       	  }
         }
      */

      // send reports to notification channels
      Enumeration ncIterator = getAllNotificationChannels().elements();
      NotificationChannel curNc;
      log.debug("Distributing source report to appropriate notification channels...");
      log.debug("Registered notification channels " + getAllNotificationChannels().keySet().toString());
      while (ncIterator.hasMoreElements()) {
         curNc = (NotificationChannel) ncIterator.nextElement();
         curNc.addSourceReport(getSourceReport());
         log.debug("SourceReport featuring tags " + getSourceReport().getAllTags().keySet()
        		 + " detected at source " + getSourceReport().getSourceInfo().getSourceName()
        		 + " added to notification channel " + curNc.getName());
      }

      // update read point counter
      updateAntennaReadPointIdentificationCount(allObservations);


         if (log.isDebugEnabled()) {
         Collection tags = getSourceReport().getAllTags().values();
         Iterator it = tags.iterator();
         while (it.hasNext()) {
        	 TagType tag = (TagType) it.next();
        	 log.debug("Tag: " + tag.getId() + " Event contained: " + tag.containsEventInfo());
         }

         //log.debug("Tag events are at least partially included: " + (TagType)(.));
         }
      }




   /**
    * Programs a tag with the given ID and the optionally specified passwords.
    * @param newID
    *           The new id
    * @param passwords
    *           An optional list of one or more passwords (or lock code). The
	 *          use of passwords is dependent upon the tag's RF protocol
    * @param tagSelectorList
    *           Not in use // TODO: Remove this parameter.
    * @throws ReaderProtocolException
    *            "ERROR_MULTIPLE_TAGS"
    */
   public void writeID(final String newID, final String[] passwords,
         final TagSelector[] tagSelectorList) throws ReaderProtocolException {

	   // get all relevant readers with their readpoints
	   Vector closure = getReaderAndReadPoints();

	   // closure
	   Enumeration closureIterator = closure.elements();
	   ReaderAndReadPoints curClosure;
	   while (closureIterator.hasMoreElements()) {
		   curClosure = (ReaderAndReadPoints) closureIterator.nextElement();

		   try {
			   HardwareAbstraction reader = curClosure.getReader();

			   // write ID
			   String[] readPoints = curClosure.getAllReadPointsAsArray();
			   HardwareException ex;
			   int failcount = 0;
			   for (int i = 0; i < readPoints.length; i++) {
				   try {
					   reader.writeId(readPoints[i], newID, passwords);
				   } catch (HardwareException he) {
					   failcount++;
					   ex = he;
				   }
			   }
			   if (failcount == readPoints.length) {
				   throw new ReaderProtocolException("ERROR_UNKNOWN", MessagingConstants.ERROR_UNKNOWN);
			   }

			   // Where is the tag?
			   Observation[] observations = reader.identify(curClosure.getAllReadPointsAsArray());
			   String readPointName = null;
			   for (int i = 0; i < observations.length; i++) {
				   if (observations[i].containsId(newID)) readPointName = observations[i].getReadPointName();
				   break;
			   }

			   // Increase the counter
			   if (readPointName != null) {
				   increaseAntennaReadPointWriteCount(readPointName);
			   }
		   } catch (HardwareException he) {
            ReadPoint readPoint = (ReadPoint) readPoints.get(he.getMessage());
            if ((readPoint != null) && (readPoint instanceof AntennaReadPoint)) {
               ((AntennaReadPoint) readPoint).writeFailureOccurred();
            }
			   /*
			   ReadPoint readPoint = (ReadPoint) readPoints.get(he.getReadPointName());
			   if (readPoint instanceof AntennaReadPoint) {
				   ((AntennaReadPoint) readPoint).writeFailureOccurred();
			   }
			   int errorCode = he.getReaderProtocolErrorCode();
			   switch(errorCode) {
			   	case MessagingConstants.ERROR_MULTIPLE_TAGS:
			   		throw new ReaderProtocolException("ERROR_MULTIPLE_TAGS", errorCode);
			   	case MessagingConstants.ERROR_NO_TAG:
			   		throw new ReaderProtocolException("ERROR_NO_TAG", errorCode);
			   }
			   */
			   throw new ReaderProtocolException("ERROR_UNKNOWN", MessagingConstants.ERROR_UNKNOWN);
		   }
	   }
   }

   /**
    * Writes the specified TagFieldValues to one or more tags. A list of
    * TagSelector objects can be used to select a set of tags, in the readers
    * field of view, for writing.
    * @param tagFieldValueList
    *           The date to write on the tag
    * @param passwords
    *           An optional list of one or more passwords (or lock code). The
	 *          use of passwords is dependent upon the tag's RF protocol
    * @param tagSelectorList
    *           The tag selectors
    * @throws ReaderProtocolException
    *            "ERROR_UNKNOWN"
    */
   public void write(TagFieldValue[] tagFieldValueList,
         final String[] passwords, final TagSelector[] tagSelectorList)
         throws ReaderProtocolException {

      Hashtable tagSelectors;
      if (tagSelectorList == null) {
         tagSelectors = this.tagSelectors;
      } else {
         tagSelectors = new Hashtable();
         for (int i = 0; i < tagSelectorList.length; i++) {
            tagSelectors.put(tagSelectorList[i].getName(), tagSelectorList[i]);
         }
      }

      Hashtable tagFieldValues = new Hashtable();
      for (int i = 0; i < tagFieldValueList.length; i++) {
         tagFieldValues.put(tagFieldValueList[i].getTagField().getName(),
               tagFieldValueList[i]);
      }

      // get all tags in range
      ReadReport report = rawReadIDs(null);

      // possible exceptions : TagMemoryServiceException, HardwareException
      try {

         // get relevant readers and their readpoints
         Vector closure = getReaderAndReadPoints();

         // get relevant tags out of the report
         Hashtable tags = getRelevantTags(report, tagSelectors, closure);

         // tags
         Enumeration tagIterator = tags.elements();
         TagType curTag;
         while (tagIterator.hasMoreElements()) {
            curTag = (TagType) tagIterator.nextElement();

            // closure
            Enumeration closureIterator = closure.elements();
            ReaderAndReadPoints curClosure;
            while (closureIterator.hasMoreElements()) {
               curClosure = (ReaderAndReadPoints) closureIterator
                     .nextElement();

               HardwareAbstraction reader = curClosure.getReader();
               String[] readPointNames = curClosure.getAllReadPointsAsArray();
               for (int i = 0; i < readPointNames.length; i++) {
            	   Enumeration tagFieldIterator = tagFieldValues.elements();
            	   TagFieldValue curTagFieldValue;
            	   while (tagFieldIterator.hasMoreElements()) {
            		   curTagFieldValue = (TagFieldValue) tagFieldIterator.nextElement();
            		   try {
                        // assemble byte array to write
                        String readPointName = readPointNames[i];
                        String id = curTag.getId();
                        int memoryBank = curTagFieldValue.getTagField().getMemoryBank();
                        int offset = curTagFieldValue.getTagField().getOffset();
                        int length = curTagFieldValue.getTagField().getLength();
                        byte[] data = HexUtil.hexToByteArray(curTagFieldValue.
                              getValue());
                        int byteoffset = offset / 8;
                        int bytelength = ((offset % 8) + length + 7) / 8;
                        int shift = (8 - ((offset + length) % 8)) % 8;
                        byte first;
                        if ((offset % 8) == 0) {
                           first = 0x00;
                        } else {
                           first = reader.readBytes(readPointName, id,
                                 memoryBank, byteoffset, 1, passwords)
                                 .toByteArray()[0];
                        }
                        byte last;
                        if (shift == 0) {
                           last = 0x00;
                        } else if (bytelength == 1) {
                           last = first;
                        } else {
                           last = reader.readBytes(readPointName, id,
                                 memoryBank, (byteoffset + bytelength - 1), 1,
                                 passwords).toByteArray()[0];
                        }
                        byte[] bytes = HexUtil.bitarrayShiftAndFill(data,
                              length, shift, first, last);
            			   reader.writeBytes(readPointName, id, memoryBank,
                              byteoffset, new UnsignedByteArray(bytes), passwords);
            			   increaseAntennaReadPointWriteCount(readPointNames[i]);
            		   } catch (HardwareException he) {
            			   ReadPoint readPoint = (ReadPoint) readPoints.get(readPointNames[i]);
            			   if (readPoint instanceof AntennaReadPoint) {
            				   ((AntennaReadPoint) readPoint).writeFailureOccurred();
            			   }
            		   }
            	   }
               }

            }

         }

      } catch (Exception e) {
         throw new ReaderProtocolException("ERROR_UNKNOWN",
               MessagingConstants.ERROR_UNKNOWN);
      }

   }

   /**
    * Kills the specified tag or group of tags. An list of TagSelector objects
    * can be specified with this command.
    * @param passwords
    *           Not yet supported
    * @param tagSelectorList
    *           The tag selectors
    * @throws ReaderProtocolException
    *            "ERROR_MULTIPLE_TAGS", "ERROR_NO_TAG", "ERROR_UNKNOWN"
    */
   public void kill(final String[] passwords,
         final TagSelector[] tagSelectorList) throws ReaderProtocolException {

      // passwords not supported in HardwareAbstraction

      Hashtable tagSelectors;
      if (tagSelectorList == null) {
         tagSelectors = this.tagSelectors;
      } else {
         tagSelectors = new Hashtable();
         for (int i = 0; i < tagSelectorList.length; i++) {
            tagSelectors
                  .put(tagSelectorList[i].getName(), tagSelectorList[i]);
         }
      }

      // get all tags in range
      ReadReport report = rawReadIDs(null);

      if (report.getAllTags().size() >= 1) {
         throw new ReaderProtocolException("ERROR_MULTIPLE_TAGS",
               MessagingConstants.ERROR_MULTIPLE_TAGS);
      }

      if (report.getAllTags().size() < 1) {
         throw new ReaderProtocolException("ERROR_NO_TAG",
               MessagingConstants.ERROR_NO_TAG);
      }

      // possible exceptions : TagMemoryServiceException, HardwareException
      try {

         // get relevant readers
         Vector closure = getReaderAndReadPoints();

         // get relevant tags out of the report
         Hashtable tags = getRelevantTags(report, tagSelectors, closure);

         // tags
         Enumeration tagIterator = tags.elements();
         TagType curTag;
         while (tagIterator.hasMoreElements()) {
            curTag = (TagType) tagIterator.nextElement();

            // readers
            Enumeration readerIterator = closure.elements();
            HardwareAbstraction curHardwareAbstraction;
            ReaderAndReadPoints curClosure;
            while (readerIterator.hasMoreElements()) {
               curClosure = (ReaderAndReadPoints) readerIterator.nextElement();
               curHardwareAbstraction = curClosure.getReader();

               try {
            	   // Where is the tag?
            	   Observation[] observations = curHardwareAbstraction.identify(curClosure.getAllReadPointsAsArray());
            	   String readPointName = null;
            	   for (int i = 0; i < observations.length; i++) {
            	      if (observations[i].containsId(curTag.getId())) readPointName = observations[i].getReadPointName();
            	      break;
            	   }

            	   // Kill
            	   curHardwareAbstraction.kill(readPointName, curTag.getId(), passwords);

            	   // Increase the counter
            	   if (readPointName != null) {
            		   increaseAntennaReadPointKillCount(readPointName);
            	   }
               } catch (HardwareException he) {
                  ReadPoint readPoint = (ReadPoint) readPoints.get(he.getMessage());
                  if ((readPoint != null) && (readPoint instanceof AntennaReadPoint)) {
                     ((AntennaReadPoint) readPoint).killFailureOccurred();
                  }
                  /*
                  ReadPoint readPoint = (ReadPoint) readPoints.get(he.getReadPointName());
                  if (readPoint instanceof AntennaReadPoint) {
                     ((AntennaReadPoint) readPoint).killFailureOccurred();
                  }
                  int errorCode = he.getReaderProtocolErrorCode();
                  switch(errorCode) {
    			   	   case MessagingConstants.ERROR_MULTIPLE_TAGS:
    			   	      throw new ReaderProtocolException("ERROR_MULTIPLE_TAGS", errorCode);
    			   	   case MessagingConstants.ERROR_NO_TAG:
    			   	      throw new ReaderProtocolException("ERROR_NO_TAG", errorCode);
                  }*/
                  throw new ReaderProtocolException("ERROR_UNKNOWN", MessagingConstants.ERROR_UNKNOWN);
               }

            }

         }

      } catch (Exception e) {
         throw new ReaderProtocolException("ERROR_UNKNOWN",
               MessagingConstants.ERROR_UNKNOWN);
      }

   }

   /**
    * Get the read cycle per trigger value.
    * @return The read cycle per trigger
    */
   public int getReadCyclesPerTrigger() {
      return readCyclesPerTrigger;
   }

   /**
    * Set the read cycle per trigger value.
    * @param cycles
    *           The read cycle per trigger value
    */
   public void setReadCyclesPerTrigger(final int cycles) {
      this.readCyclesPerTrigger = cycles;
   }

   /**
    * Get the maximal read duty cycle value.
    * @return The maximal read duty cycle value
    */
   public int getMaxReadDutyCycles() {
      return maxReadDutyCycles;
   }

   /**
    * Set the maximal read duty cycle value.
    * @param cycles
    *           The maximal read duty cycle value
    */
   public void setMaxReadDutyCycles(final int cycles) {
      this.maxReadDutyCycles = cycles;
   }

   /**
    * Get the read timeout value (msec).
    * @return The read timeout
    */
   public int getReadTimeout() {
      return readTimeout;
   }

   /**
    * Set the read timeout value (msec).
    * @param timeout
    *           The read timeout
    */
   public void setReadTimeout(final int timeout) {
      this.readTimeout = timeout;
   }

   /**
    * Get the session.
    * @return The session
    */
   public int getSession() {
      return session;
   }

   /**
    * Set the session.
    * @param session
    *           The session
    */
   public void setSession(final int session) {
      this.session = session;
   }

   /**
    * Remove all associations of this source.
    */
   public void removeAssociations() {

      // remove associations with triggers
      removeAllReadTriggers();

      // remove associations with read points
      removeAllReadPoints();

      // remove associations with tag selectors
      removeAllTagSelectors();

      // remove associations with notification channels
      Enumeration e = notificationChannels.elements();
      NotificationChannel nc;
      while (e.hasMoreElements()) {
         nc = (NotificationChannel) e.nextElement();
         Source[] s = new Source[] {this};
         nc.removeSources(s);
      }

      // remove associations with current source of the reader device
      if (readerDevice.getCurrentSource() != null
            && readerDevice.getCurrentSource().equals(this)) {
         readerDevice.setCurrentSource(null);
      }

   }

   /**
    * Adds information about the reader to a repot.
    * @param report
    *           The report to modify
    * @param dataSelector
    *           The dataSelector to use
    */
   protected void addReaderInfo(final ReadReport report,
         final DataSelector dataSelector) {

      Hashtable fieldNames = dataSelector.getFieldNames();

      if (!report.containsReaderInfo()) {
         ReaderInfoType newReaderInfo = new ReaderInfoType();
         report.setReaderInfo(newReaderInfo);
      }

      // READER_EPC
      if (fieldNames.containsKey(FieldName.READER_EPC)
            || fieldNames.containsKey(FieldName.ALL_READER)
            || fieldNames.containsKey(FieldName.ALL)) {
         report.getReaderInfo().setEpc(this.readerDevice.getEPC());
      } else {
         report.getReaderInfo().setEpc(null);
      }

      // READER_HANDLE
      if (fieldNames.containsKey(FieldName.READER_HANDLE)
            || fieldNames.containsKey(FieldName.ALL_READER)
            || fieldNames.containsKey(FieldName.ALL)) {
         report.getReaderInfo().setHandle(this.readerDevice.getHandle());
      } else {
         report.getReaderInfo().setHandle(-1);
      }

      // READER_NAME
      if (fieldNames.containsKey(FieldName.READER_NAME)
            || fieldNames.containsKey(FieldName.ALL_READER)
            || fieldNames.containsKey(FieldName.ALL)) {
         report.getReaderInfo().setName(this.readerDevice.getName());
      } else {
         report.getReaderInfo().setName(null);
      }

      // READER_ROLE
      if (fieldNames.containsKey(FieldName.READER_ROLE)
            || fieldNames.containsKey(FieldName.ALL_READER)
            || fieldNames.containsKey(FieldName.ALL)) {
         report.getReaderInfo().setRole(this.readerDevice.getRole());
      } else {
         report.getReaderInfo().setRole(null);
      }

      // READER_NOW_TICK
      if (fieldNames.containsKey(FieldName.READER_NOW_TICK)
            || fieldNames.containsKey(FieldName.ALL_READER)
            || fieldNames.containsKey(FieldName.ALL)) {
         report.getReaderInfo().setNowTick(this.readerDevice.getTimeTicks());
      } else {
         report.getReaderInfo().setNowTick(0);
      }

      // READER_NOW_UTC
      if (fieldNames.containsKey(FieldName.READER_NOW_UTC)
            || fieldNames.containsKey(FieldName.ALL_READER)
            || fieldNames.containsKey(FieldName.ALL)) {
         report.getReaderInfo().setNowUTC(this.readerDevice.getTimeUTC());
      } else {
         report.getReaderInfo().setNowUTC(null);
      }

   }

   /**
    * Adds information about the source to the report.
    * @param report
    *           The report to modify
    * @param dataSelector
    *           The dataSelector to use
    */
   protected void addSourceInfo(final SourceReport report,
         final DataSelector dataSelector) {

      Hashtable fieldNames = dataSelector.getFieldNames();

      if (!report.containsSourceInfo()) {
         SourceInfoType newSourceInfo = new SourceInfoType();
         report.setSourceInfo(newSourceInfo);
      }

      // SOURCE_NAME
      report.getSourceInfo().setSourceName(this.getName());
      log.debug("Source name in source report set to: " + report.getSourceInfo().getSourceName());


      // SOURCE_FREQUENCY
      if (fieldNames.containsKey(FieldName.SOURCE_FREQUENCY)
            || fieldNames.containsKey(FieldName.ALL_SOURCE)
            || fieldNames.containsKey(FieldName.ALL)) {
         report.getSourceInfo().setSourceFrequency(0);
      } else {
         report.getSourceInfo().setSourceFrequency(-1);
      }

      // SOURCE_PROTOCOL
      if (fieldNames.containsKey(FieldName.SOURCE_PROTOCOL)
            || fieldNames.containsKey(FieldName.ALL_SOURCE)
            || fieldNames.containsKey(FieldName.ALL)) {
         // Not supported in HardwareAbstraction
         report.getSourceInfo().setSourceProtocol("not supported");
      } else {
         report.getSourceInfo().setSourceProtocol(null);
      }

   }

   /**
    * Adds a tag with its fields to the report.
    * @param tagId
    *           The tag to add
    * @param report
    *           The report to modify
    * @param dataSelector
    *           The data to report
    * @param closure
    *           The closure
    * @param trigger
    *           The trigger that caused the event
    * @throws TagMemoryServiceException
    *            Problems with the tag memory
    * @throws HardwareException
    *            Problems with the HAL
    */
   protected void addTagToReport(final String tagId,
	         final SourceReport report, final DataSelector dataSelector,
	         final Vector closure, final Trigger trigger)
	         throws HardwareException {

	      TagType curTag;

	      if (!report.containsTag(tagId)) {
	         curTag = new TagType();
	         //TAG_ID_AS_HEX_FORMAT
	         curTag.setId(tagId);
//	         //TAG_ID_AS_RAW_BINARY_FORMAT
//	         curTag.setIdAsPureBinary(getBinaryTagId(tagId, 96));
	         report.addTag(curTag);
	      } else {
	         curTag = report.getTag(tagId);
	      }

	      Hashtable fieldNames = dataSelector.getFieldNames();

	      // TAG_TYPE
	      if (fieldNames.containsKey(FieldName.TAG_TYPE)
	            || fieldNames.containsKey(FieldName.ALL_TAG)
	            || fieldNames.containsKey(FieldName.ALL)) {
	         // Not supported in HardwareAbstraction
	         curTag.setTagType("not supported");
	      } else {
	         curTag.setTagType(null);
	      }
	      // TAG_ID_AS_PURE_URI
	      if (fieldNames.containsKey(FieldName.TAG_ID_AS_PURE_URI)
	            || fieldNames.containsKey(FieldName.ALL_TAG)
	            || fieldNames.containsKey(FieldName.ALL)) {
	         // Only for non-EPC tags
//	         final int num = 4;
//	         int numOfBits = num * curTag.getId().length();
//	         curTag.setIdAsPureURI("urn:epc:raw:" + numOfBits + ".x"
//	               + curTag.getId());
	          //TODO: set the correct parameters For: curTag.setIdAsPureURI(getTagId(tagId, 0, 64, 8, LevelTypeList.PURE_IDENTITY));
	          //These parameters are for Intermec.
	    	  //BUGFIX By nkef@ait.edu.gr use curTag.setIdAsPureURI than curTag.setIdAsTagURI that was before
	          curTag.setIdAsPureURI(getTagId(tagId, 1, 96, 7, "PURE_IDENTITY"));
	      } else {
	         curTag.setIdAsPureURI(null);
	      }

	      // TAG_ID_AS_TAG_URI
	      if (fieldNames.containsKey(FieldName.TAG_ID_AS_TAG_URI)
	            || fieldNames.containsKey(FieldName.ALL_TAG)
	            || fieldNames.containsKey(FieldName.ALL)) {
	         // Only for non-EPC tags
	         final int num = 4;
	         int numOfBits = num * curTag.getId().length();
//	         curTag.setIdAsTagURI("urn:epc:raw:" + numOfBits + ".x"
//	               + curTag.getId());
	         //TODO: set the corect parameters For: curTag.setIdAsTagURI(getTagId(tagId, 0, 64, 8, LevelTypeList.TAG_ENCODING));
	         //These parameters are for Intermec.
	         curTag.setIdAsTagURI(getTagId(tagId, 1, 96, 7, "TAG_ENCODING"));
	      } else {
	         curTag.setIdAsTagURI(null);
	      }

	      // OTHER_FIELDS

	      // tag field names
	      Enumeration tagFieldNameIterator = dataSelector.getTagFieldNames().elements();
	      String curTagFieldName;
	      String curTagFieldValue;

	      while (tagFieldNameIterator.hasMoreElements()) {
	         curTagFieldName = (String) tagFieldNameIterator.nextElement();

	         // check if already in report
	         if (!curTag.getAllTagFields().containsKey(curTagFieldName)) {

	            TagField tf;
	            try {
	               tf = readerDevice.getTagField(curTagFieldName);
	               curTagFieldValue = readTagField(curTag.getId(), closure, tf);

	               // add to report
	               TagFieldValueParamType tfvp = new TagFieldValueParamType();
	               tfvp.setTagFieldName(curTagFieldName);
	               tfvp.setTagFieldValue(curTagFieldValue);
	               curTag.addTagField(tfvp);
	            } catch (ReaderProtocolException e) {}
	         }
	      }

	      // events
	      Hashtable tagEvents = ((TagState) currentState.get(curTag.getId()))
	            .getTagEvents();
	      curTag.setTagEvents(tagEvents);

	   }
   
   /*
   protected void addTagToReport(final String tagId,
         final SourceReport report, final DataSelector dataSelector,
         final Vector closure, final Trigger trigger)
         throws HardwareException {

      TagType curTag;

      if (!report.containsTag(tagId)) {
         curTag = new TagType();
         curTag.setId(tagId);
         report.addTag(curTag);
      } else {
         curTag = report.getTag(tagId);
      }

      Hashtable fieldNames = dataSelector.getFieldNames();

      // TAG_TYPE
      if (fieldNames.containsKey(FieldName.TAG_TYPE)
            || fieldNames.containsKey(FieldName.ALL_TAG)
            || fieldNames.containsKey(FieldName.ALL)) {
         // Not supported in HardwareAbstraction
         curTag.setTagType("not supported");
      } else {
         curTag.setTagType(null);
      }

      // TAG_ID_AS_PURE_URI
      if (fieldNames.containsKey(FieldName.TAG_ID_AS_PURE_URI)
            || fieldNames.containsKey(FieldName.ALL_TAG)
            || fieldNames.containsKey(FieldName.ALL)) {
         // Only for non-EPC tags
//         final int num = 4;
//         int numOfBits = num * curTag.getId().length();
//         curTag.setIdAsPureURI("urn:epc:raw:" + numOfBits + ".x"
//               + curTag.getId());
          curTag.setIdAsPureURI(getTagId(tagId, 1, 96, 7, "PURE_IDENTITY"));
      } else {
         curTag.setIdAsPureURI(null);
      }

      // TAG_ID_AS_TAG_URI
      if (fieldNames.containsKey(FieldName.TAG_ID_AS_TAG_URI)
            || fieldNames.containsKey(FieldName.ALL_TAG)
            || fieldNames.containsKey(FieldName.ALL)) {
         // Only for non-EPC tags
         final int num = 4;
         int numOfBits = num * curTag.getId().length();
//         curTag.setIdAsTagURI("urn:epc:raw:" + numOfBits + ".x"
//               + curTag.getId());
         curTag.setIdAsTagURI(getTagId(tagId, 1, 96, 7, "TAG_ENCODING"));
      } else {
         curTag.setIdAsTagURI(null);
      }

      // OTHER_FIELDS

      // tag field names
      Enumeration tagFieldNameIterator = dataSelector.getTagFieldNames().elements();
      String curTagFieldName;
      String curTagFieldValue;

      while (tagFieldNameIterator.hasMoreElements()) {
         curTagFieldName = (String) tagFieldNameIterator.nextElement();

         // check if already in report
         if (!curTag.getAllTagFields().containsKey(curTagFieldName)) {

            TagField tf;
            try {
               tf = readerDevice.getTagField(curTagFieldName);
               curTagFieldValue = readTagField(curTag.getId(), closure, tf);

               // add to report
               TagFieldValueParamType tfvp = new TagFieldValueParamType();
               tfvp.setTagFieldName(curTagFieldName);
               tfvp.setTagFieldValue(curTagFieldValue);
               curTag.addTagField(tfvp);
            } catch (ReaderProtocolException e) {}
         }
      }

      // events
      Hashtable tagEvents = ((TagState) currentState.get(curTag.getId()))
            .getTagEvents();
      curTag.setTagEvents(tagEvents);

   }
*/
   private String getTagId(String tagIdHex, int filter, int taglength, int companyprefixlength, String outputLevel) {

	   BigInteger big = new BigInteger(tagIdHex, 16);
	   String binaryTagId = big.toString(2);
	   while (binaryTagId.length() < taglength) {
		   binaryTagId = "0" + binaryTagId;
	   }
	   Map<String, String> params = new HashMap<String, String>();
	   params.put("filter", new Integer(filter).toString());
	   params.put("taglength", new Integer(taglength).toString());
	   params.put("companyprefixlength", new Integer(companyprefixlength).toString());

	   LOG.debug("Convert TagId '" + binaryTagId + "' to " + outputLevel);

	   String result;
	   try {
		   TDT tdt = Activator.getTDTService();
		   if(tdt == null)
		   {
			   log.error("No TDT engine available");
			   return "";
		   }
		   else
			   result = tdt.convert(binaryTagId, params, outputLevel);
	   } catch(Exception e) {
		   LOG.debug("Error at getTagId(): "+e.getMessage());
		   result = binaryTagId;
	   }

	   LOG.debug(outputLevel + ": " + result);

	   return result;

   }

   /**
    * Updates the timestamp (last read timestamp) of the source's currentState.
    * @param tagId
    *           The tag to update
    * @param timestamp
    *           The timestamp
    */
   protected void updateLastReadTimestamp(final String tagId,
         final long timestamp) {

      // check if tag exists in current state
      TagState currentTag;
      if (!currentState.containsKey(tagId)) {
         currentTag = new TagState();
         currentTag.setId(tagId);
         currentTag.setFirst(timestamp);
         currentTag.setLast(timestamp);
         currentTag.setState(EventType.ST_IS_UNKNOWN);
         currentState.put(currentTag.getId(), currentTag);
      } else {
         currentTag = (TagState) currentState.get(tagId);
         if (currentTag.getLast() < timestamp) {
            currentTag.setLast(timestamp);
         }
      }

   }

   /**
    * Updates the currentState of the source and generates the events/report.
    * @param date
    *           The current date
    * @param dataSelector
    *           The date to report
    * @param closure
    *           The relevant reader and its readpoints
    * @param trigger
    *           The trigger that caused the event
    * @return Returns a report
    */
   protected SourceReport updateCurrentState(final Date date,
         final DataSelector dataSelector, final Vector closure,
         final Trigger trigger) {

      long now = date.getTime();

      SourceReport report = new SourceReport();

      // tags
      Enumeration tagIterator = currentState.elements();
      TagState currentTag;
      while (tagIterator.hasMoreElements()) {
         currentTag = (TagState) tagIterator.nextElement();

         // statemachine
         if (currentTag.getState().equals(EventType.ST_IS_UNKNOWN)) {
            // events : evGlimpsed, evNew
            // System.out.println("evGlimpsed, evNew");
            currentTag.setFirst(now);
            currentTag.setLast(now);
            currentTag.setState(EventType.ST_IS_GLIMPSED);
            unknownToGlimpsedCount++;
            currentTag.updateTagEvent(EventType.EV_NEW, trigger, date
                  .getTime(), date);
            // currentTag.updateTagEvent(EventType.EV_GLIMPSED, trigger,
            // date.getTime(), date);
            try {
               addTagToReport(currentTag.getId(), report, dataSelector,
                     closure, trigger);
            } catch (Exception e) {
            	// TODO: catch the concrete exception and not Exception
               System.out.println(e.getMessage());
            }
         } else if (currentTag.getState().equals(EventType.ST_IS_GLIMPSED)) {
            if (now - currentTag.getLast() > getGlimpsedTimeout()) {
               // events : evUnknown
               //System.out.println("evUnknown");
               glimpsedToUnknownCount++;
               currentTag.updateTagEvent(EventType.EV_UNKNOWN, trigger, date
                     .getTime(), date);
               try {
                  addTagToReport(currentTag.getId(), report, dataSelector,
                        closure, trigger);
               } catch (Exception e) {
               	// TODO: catch the concrete exception and not Exception
                  System.out.println(e.getMessage());
               }
               currentState.remove(currentTag.getId());
            } else if (now - currentTag.getFirst() > getObservedThreshold()) {
               // events : evObserved
               // System.out.println("evObserved");
               currentTag.setState(EventType.ST_IS_OBSERVED);
               glimpsedToObservedCount++;
               currentTag.updateTagEvent(EventType.EV_OBSERVED, trigger, date
                     .getTime(), date);
               try {
                  addTagToReport(currentTag.getId(), report, dataSelector,
                        closure, trigger);
               } catch (Exception e) {
                  System.out.println(e.getMessage());
               }
            }
         } else if (currentTag.getState().equals(EventType.ST_IS_OBSERVED)) {
            if (now - currentTag.getLast() > getObservedTimeout()) {
               // events : evLost
               // System.out.println("evLost");
               currentTag.setState(EventType.ST_IS_LOST);
               observedToLostCount++;
               currentTag.updateTagEvent(EventType.EV_LOST, trigger, date
                     .getTime(), date);
               try {
                  addTagToReport(currentTag.getId(), report, dataSelector,
                        closure, trigger);
               } catch (Exception e) {
                  System.out.println(e.getMessage());
               }
            }
         } else if (currentTag.getState().equals(EventType.ST_IS_LOST)) {
            if (now - currentTag.getLast() > getLostTimeout()
                  + getObservedTimeout()) {
               // events : evPurged
               // System.out.println("evPurged");
               //currentTag.setState("isUnknown");
               lostToUnknownCount++;
               currentTag.updateTagEvent(EventType.EV_PURGED, trigger, date
                     .getTime(), date);
               try {
                  addTagToReport(currentTag.getId(), report, dataSelector,
                        closure, trigger);
               } catch (Exception e) {
                  System.out.println(e.getMessage());
               }
               currentState.remove(currentTag.getId());
            } else {
               // events : evGlimpsed
               // System.out.println("evGlimpsed");
               currentTag.setFirst(now);
               currentTag.setLast(now);
               currentTag.setState(EventType.ST_IS_GLIMPSED);
               lostToGlimpsedCount++;
               currentTag.updateTagEvent(EventType.EV_GLIMPSED, trigger, date
                     .getTime(), date);
               try {
                  addTagToReport(currentTag.getId(), report, dataSelector,
                        closure, trigger);
               } catch (Exception e) {
                  System.out.println(e.getMessage());
               }
            }
         }

      }

      return report;

   }

   /**
    * Checks if a tag is relevant (dependant of the TagSelectors).
    * @param tagId
    *           The tag to check
    * @param allTagSelectors
    *           The TagSelectors
    * @param closure
    *           The reader and its readpoints
    * @return Returns 'true' if the tag is relevant, 'false' otherwise
    * @throws ReaderProtocolException
    *            "ERROR_UNKNOWN"
    */
   protected boolean isRelevantTag(final String tagId,
         final Hashtable allTagSelectors, final Vector closure)
         throws ReaderProtocolException {

      Hashtable tagSelectors = new Hashtable();

      if (allTagSelectors.size() == 0) {
         try {
            tagSelectors.put(readerDevice
                  .getTagSelector("defaultTagSelector").getName(),
                  readerDevice.getTagSelector("defaultTagSelector"));
         } catch (ReaderProtocolException e) {
            throw new ReaderProtocolException("ERROR_UNKNOWN",
                  MessagingConstants.ERROR_UNKNOWN);
         }
      } else {
         tagSelectors = allTagSelectors;
      }

      // indicates if tag is relevant
      boolean relevantInc = false;
      boolean relevantExc = false;

      // special case, if zero inclusive/exclusive patterns are defined
      boolean moreThanZeroIncs = false;
      boolean moreThanZeroExcs = false;

      // tag selectors
      Enumeration tagSelectorIterator = tagSelectors.elements();
      TagSelector curTagSelector;
      String tagValue = "";
      while (tagSelectorIterator.hasMoreElements()) {
         curTagSelector = (TagSelector) tagSelectorIterator.nextElement();

         if (curTagSelector.getTagField().getMemoryBank() == 2) {
            // id
            tagValue = tagId;
         } else {
            // tagField
            tagValue = readTagField(tagId, closure, curTagSelector
                  .getTagField());

         }

         // check value
         if (curTagSelector.validate(tagValue)
               && curTagSelector.getInclusiveFlag()) { // inlusive
            // relevant, the tag should be reported
            relevantInc = true;
         } else if (!curTagSelector.validate(tagValue)
               && !curTagSelector.getInclusiveFlag()) { // exclusive
            // relevant, the tag should be reported
            relevantExc = true;
         }
         if (curTagSelector.getInclusiveFlag()) {
            moreThanZeroIncs = true;
         }
         if (!curTagSelector.getInclusiveFlag()) {
            moreThanZeroExcs = true;
         }

      }

      // return true if tag is relevant
      if (relevantInc && relevantExc) {
         return true;
      } else if (!moreThanZeroIncs && relevantExc) {
         return true;
      } else if (!moreThanZeroExcs && relevantInc) {
         return true;
      }

      return false;

   }

   /**
    * Takes the current state and retuns a filtered list (given a tag selector).
    * @param currentReport
    *           The current state
    * @param tagSelectors
    *           The tag selectors
    * @param closure
    *           The closure
    * @return The list of relevant tags
    * @throws ReaderProtocolException
    *            "ERROR_UNKNOWN"
    */
   private Hashtable getRelevantTags(final ReadReport currentReport,
         final Hashtable tagSelectors, final Vector closure)
         throws ReaderProtocolException {

      // possible exceptions : TagMemoryServiceException, HardwareException
      try {
         // temp list of TagStates
         Hashtable filteredTags = new Hashtable();

         // tags
         Enumeration tagIterator = currentReport.getAllTags().elements();
         TagType curTag;
         while (tagIterator.hasMoreElements()) {
            curTag = (TagType) tagIterator.nextElement();

            if (isRelevantTag(curTag.getId(), tagSelectors, closure)) {
               filteredTags.put(curTag.getId(), curTag);
            }

         }

         return filteredTags;

      } catch (Exception e) {
         throw new ReaderProtocolException("ERROR_UNKNOWN",
               MessagingConstants.ERROR_UNKNOWN);
      }

   }

   /**
    * Computes the ReaderAndReadPoint elements of this source. A
    * ReaderAndReadPoints element contains one reader instance
    * (HardwareAbstraction) and all of ist read points associated with this
    * source.
    * @return Vector of ReaderAndReadPoints elements
    */
   protected Vector getReaderAndReadPoints() {

      // contains elements of type ReaderAndReadPoints
      Vector closure = new Vector();

      // read points
      Enumeration readPointIterator = readPoints.elements();
      ReadPoint curReadPoint;
      while (readPointIterator.hasMoreElements()) {
         curReadPoint = (ReadPoint) readPointIterator.nextElement();

         // flag if curReadPoint.getReader() is in closure
         boolean exists = false;

         // closure
         Enumeration closureIterator = closure.elements();
         ReaderAndReadPoints curClosure;
         while (closureIterator.hasMoreElements()) {
            curClosure = (ReaderAndReadPoints) closureIterator.nextElement();

            if (curClosure.getReader().equals(curReadPoint.getReader())) {
               exists = true;
               curClosure.addReadPoint(curReadPoint.getName());
               break;
            }

         }

         // add new ReaderAndReadPoint
         if (!exists) {
            ReaderAndReadPoints rarp = new ReaderAndReadPoints();
            rarp.setReader(curReadPoint.getReader());
            rarp.addReadPoint(curReadPoint.getName());
            closure.add(rarp);
         }

      }

      return closure;

   }

   /**
    * Reads a tag field.
    * @param tagId
    *           The id of the tag
    * @param closure
    *           The readers an its readpoint instances
    * @param tagField
    *           The tag field
    * @return The read value. If no value is found this method returns 'null'
    */
   private String readTagField(final String tagId, final Vector closure,
         final TagField tagField) {

      // closure
      Enumeration closureIterator = closure.elements();
      ReaderAndReadPoints curClosure;
      while (closureIterator.hasMoreElements()) {

         curClosure = (ReaderAndReadPoints) closureIterator.nextElement();

         HardwareAbstraction reader = curClosure.getReader();
         String[] readPointNames = curClosure.getAllReadPointsAsArray();
         for (int i = 0; i < readPointNames.length; i++) {
        	 try {
        		 byte[] memory;
             // calculate byte borders
             int offset = tagField.getOffset();
             int length = tagField.getLength();
             int byteoffset = offset / 8;
             int bytelength = ((offset % 8) + length + 7) / 8;
             // read
             memory = reader.readBytes(
        				 readPointNames[i],
        				 tagId,
        				 tagField.getMemoryBank(),
        				 byteoffset,
        				 bytelength,
        				 new String[] { }).toByteArray();
        		 increaseAntennaReadPointMemReadCount(readPointNames[i]);
        		 // extract requested bits
             int shift = (offset + length) % 8;
             byte[] cutmemory;
             if (shift != 0) {
                int shiftlength = bytelength * 8 - (offset % 8);
                byte[] shiftedmemory = HexUtil.bitarrayShiftAndFill(memory,
                      shiftlength, shift, (byte) 0x00, (byte) 0x00);
                cutmemory = new byte[shiftedmemory.length - 1];
                System.arraycopy(shiftedmemory, 0, cutmemory, 0, cutmemory.length);
             } else {
                cutmemory = memory;
                int leftmask = 0xFF >> ((8 - (length % 8)) % 8);
                cutmemory[0] &= leftmask; 
             }
             // create string representation
             return HexUtil.byteArrayToHexString(cutmemory);
        	 } catch (HardwareException he) {
        		 ReadPoint readPoint = (ReadPoint) readPoints.get(readPointNames[i]);
        		 if (readPoint instanceof AntennaReadPoint) {
        			 ((AntennaReadPoint) readPoint).memReadFailureOccurred();
        		 }
        	 }
         }
      }

		/* This is code from the 0.2 branch that Remo edited
		// I was not 100% convinced that we might not need, but it does not take the changes
		// in the HAL interface into account

         curClosure = (ReaderAndReadPoints) closureIterator.nextElement();

         // services
         try {
            String[] services = curClosure.getReader().getServices();
            for (int j = 0; j < services.length; j++) {
               if (services[j].equals("readBytes")) {
                  byte[] temp;
                  temp = curClosure.getReader().readBytes( tagId,
                        tagField.getOffset(), tagField.getLength());
                  return HexUtil.byteArrayToHexString(temp);
               }
            }
         } catch (Exception e) {
            // tag not in range of this reader
            System.out.println(e.getMessage());
         }

      }

		*/
      return "not found";
   }

   /**
    * Returns the source report. This report contains all information about
    * tags, reader and source needed to generate the final reports of the
    * notification channels.
    * @return Returns the sourceReport
    */
   public SourceReport getSourceReport() {
      return sourceReport;
   }

   /**
    * Sets the source report. This report contains all information about tags,
    * reader and source needed to generate the final reports of the notification
    * channels.
    * @param sourceReport
    *           The sourceReport to set
    */
   public void setSourceReport(final SourceReport sourceReport) {
      this.sourceReport = sourceReport;
   }

   /**
    * Adds a notification channel. This notification channels should be informed
    * about new events (NotificationChannel.addSourceReport())
    * @param notificationChannel
    *           The channels
    */
   public void addNotificationChannel(
         final NotificationChannel notificationChannel) {
      this.notificationChannels.put(notificationChannel.getName(),
            notificationChannel);
   }

   /**
    * Removes a notification channel This notification channels should be
    * informed about new events (NotificationChannel.addSourceReport()).
    * @param notificationChannel
    *           The channels to remove
    */
   public void removeNotificationChannel(
         final NotificationChannel notificationChannel) {
      this.notificationChannels.remove(notificationChannel.getName());
   }

   /**
    * Returns all notification channels This notification channels should be
    * informed about new events (NotificationChannel.addSourceReport()).
    * @return All notification channels
    */
   public Hashtable getAllNotificationChannels() {
      return notificationChannels;
   }

   /**
    * Returns the reader device instance.
    * @return The reader device
    */
   public ReaderDevice getReaderDevice() {
      return readerDevice;
   }

   /**
    * Returns the read triggers.
    * @return The read triggers
    */
   public Hashtable getReadTriggers() {
      return readTriggers;
   }


   // ====================================================================
   // ----- Methods added for the reader management implementation -----//
   // ====================================================================

   /**
    * Returns the number of times a transition from state Unknown to state
    * Glimpsed has been detected for this particular source.
    *
    * @return The number times the particular transition has been detected
    */
   public int getUnknownToGlimpsedCount() {
	   return unknownToGlimpsedCount;
   }

   /**
    * Returns the number of times a transition from state Glimpsed to state
    * Unknown has been detected for this particular source.
    *
    * @return The number times the particular transition has been detected
    */
   public int getGlimpsedToUnknownCount() {
	   return glimpsedToUnknownCount;
   }

   /**
    * Returns the number of times a transition from state Glimpsed to state
    * Observed has been detected for this particular source.
    *
    * @return The number times the particular transition has been detected
    */
   public int getGlimpsedToObservedCount() {
	   return glimpsedToObservedCount;
   }

   /**
    * Returns the number of times a transition from state Observed to state
    * Lost has been detected for this particular source.
    *
    * @return The number times the particular transition has been detected
    */
   public int getObservedToLostCount() {
	   return observedToLostCount;
   }

   /**
    * Returns the number of times a transition from state Lost to state
    * Glimpsed has been detected for this particular source.
    *
    * @return The number times the particular transition has been detected
    */
   public int getLostToGlimpsedCount() {
	   return lostToGlimpsedCount;
   }

   /**
    * Returns the number of times a transition from state Lost to state Unknown
    * has been detected for this particular source.
    *
    * @return The number times the particular transition has been detected
    */
   public int getLostToUnknownCount() {
	   return lostToUnknownCount;
   }

   /**
    * Returns the operational status of this particular <code>Source</code>.
    *
    * @return The operational status of this particular <code>Source</code>
    */
   public OperationalStatus getOperStatus() {
	   int up = 0;
	   int down = 0;
	   int other = 0;
	   Enumeration iter = readPoints.elements();
	   while (iter.hasMoreElements()) {
		   ReadPoint curReadPoint = (ReadPoint)iter.nextElement();
		   switch (curReadPoint.getOperStatus()) {
		   	case UP:
		   		up++;
		   		break;
		   	case DOWN:
		   		down++;
		   		break;
		   	case OTHER:
		   		other++;
		   		break;
		   }
	   }
	   if ((other > 0) || ((up > 0) && (down > 0)))
		   setOperStatus(OperationalStatus.OTHER);
	   else if ((up > 0) && (down == 0))
		   setOperStatus(OperationalStatus.UP);
	   else if ((up == 0) && (down > 0))
		   setOperStatus(OperationalStatus.DOWN);
	   else
		   setOperStatus(OperationalStatus.UNKNOWN);
	   return operStatus;
   }

   /**
    * Returns the administrative status of a particular <code>Source</code>.
    * This represents the host's desired status for this <code>Source</code>.
    *
    * @return The current administrative status of this <code>Source</code>
    */
   public AdministrativeStatus getAdminStatus() {
	   return adminStatus;
   }

   /**
    * Sets the administrative status of a particular <code>Source</code>.
    * This represents the host's desired status for this <code>Source</code>.
    *
    * @param administrativeStatus
    *            The new administrative status to be set
    */
   public void setAdminStatus(final AdministrativeStatus administrativeStatus) {
	   Enumeration iter = readPoints.elements();
	   while (iter.hasMoreElements()) {
		   ReadPoint curReadPoint = (ReadPoint)iter.nextElement();
		   curReadPoint.setAdminStatus(administrativeStatus);
	   }
	   adminStatus = administrativeStatus;
   }

   /**
    * Returns the <code>operStatusAlarmControl</code> attribute of this
    * source. This attribute is the object that controls the conditions for
    * generating alarms alerting a manager of changes in a <code>Source</code>'s
    * operational status.
    *
    * @return The alarm control for monitoring the operational status of the
    *         <code>Source</code>
    */
   public TTOperationalStatusAlarmControl getOperStatusAlarmControl() {
	   return operStatusAlarmControl;
   }

   /**
    * Returns <code>true</code> iff this source supports write operations,
    * <code>false</code> otherwise.
    *
    * @return <code>true</code> iff this source supports write operations,
    *         <code>false</code> otherwise
    * @throws ReaderProtocolException
    */
   public boolean supportsWriteOperations() throws ReaderProtocolException {
	   Vector closure = getReaderAndReadPoints();
	   Enumeration readerIterator = closure.elements();
	   HardwareAbstraction curHardwareAbstraction;
	   while (readerIterator.hasMoreElements()) {
		   curHardwareAbstraction = ((ReaderAndReadPoints) readerIterator.nextElement()).getReader();
		   if (curHardwareAbstraction.supportsWriteBytes() || curHardwareAbstraction.supportsWriteId()) {
			   return true;
		   }
	   }
	   return false;
   }

   /**
    * Resets all counters.
    */
   public void resetCounters() {
	   unknownToGlimpsedCount = 0;
	   glimpsedToUnknownCount = 0;
	   glimpsedToObservedCount = 0;
	   observedToLostCount = 0;
	   lostToGlimpsedCount = 0;
	   lostToUnknownCount = 0;

	   antReadPointMemReadCount = 0;
	   antReadPointWriteCount = 0;
	   antReadPointKillCount = 0;
   }

   /**
    * Increases the number of source operational state change notifications
    * that have been suppressed for this <code>Source</code> object by
    * <code>1</code>.
    */
   public void increaseOperStateSuppressions() {
	   operStateSuppressions++;
   }

   /**
    * Returns the number of source operational state change notifications that
    * have been suppressed for this <code>Source</code> object.
    *
    * @return The number of source operational state change notifications that
    *         have been suppressed for this <code>Source</code> object
    */
   public int getOperStateSuppressions() {
	   return operStateSuppressions;
   }

   /**
    * Resets the number of source operational state change notifications that
    * have been suppressed for this <code>Source</code> object to
    * <code>0</code>.
    */
   public void resetOperStateSuppressions() {
	   operStateSuppressions = 0;
   }

   /**
    * Sets the operational status.
    *
    * @param operStatus
    *            The new operational status
    */
   private void setOperStatus(OperationalStatus operStatus) {
	   if (operStatus != this.operStatus) {
		   readerDevice.getAlarmManager().fireAlarm(
					new SourceOperStatusAlarm("SourceOperStatusAlarm",
							operStatusAlarmControl.getLevel(), readerDevice,
							this.operStatus, operStatus, name),
					operStatusAlarmControl);
			this.operStatus = operStatus;
	   }
   }

   /**
    * Updates the <code>identificationCount</code> attribute of the
    * <code>AntennaReadPoint</code> objects according to the given
    * observations.
    *
    * @param observations
    *            The observations
    */
   private void updateAntennaReadPointIdentificationCount(Vector<Observation> observations) {
	   Enumeration observationIter = observations.elements();
       while (observationIter.hasMoreElements()) {
      	 Observation curObservation = (Observation)observationIter.nextElement();
      	 ReadPoint curReadPoint = (ReadPoint)readPoints.get(curObservation.getReadPointName());
      	 String[] ids = curObservation.getIds();
      	 if (curReadPoint instanceof AntennaReadPoint) {
      		 AntennaReadPoint curAntReadPoint = (AntennaReadPoint)curReadPoint;
      		 for (int tagIndex = 0; tagIndex < ids.length; tagIndex++) {
      			 curAntReadPoint.increaseIdentificationCount();
      		 }
      	 }
       }
   }

   /**
    * Increases the memory read counter of an <code>AntennaReadPoint</code>.
    *
    * @param readPointName
    *            The name of the <code>AntennaReadPoint</code>
    */
   private void increaseAntennaReadPointMemReadCount(String readPointName) {
	   if (readPointName != null) {
		   ReadPoint readPoint = (ReadPoint)readPoints.get(readPointName);
		   if (readPoint instanceof AntennaReadPoint) {
			   ((AntennaReadPoint)readPoint).increaseMemReadCount();
		   }
	   }
	   antReadPointMemReadCount++;
   }

   /**
    * Increases the write counter of an <code>AntennaReadPoint</code>.
    *
    * @param readPointName
    *            The name of the <code>AntennaReadPoint</code>
    */
   private void increaseAntennaReadPointWriteCount(String readPointName) {
	   if (readPointName != null) {
		   ReadPoint readPoint = (ReadPoint)readPoints.get(readPointName);
		   if (readPoint instanceof AntennaReadPoint) {
			   ((AntennaReadPoint)readPoint).increaseWriteCount();
		   }
	   }
	   antReadPointWriteCount++;
   }

   /**
    * Increases the kill counter of an <code>AntennaReadPoint</code>.
    *
    * @param readPointName
    *            The name of the <code>AntennaReadPoint</code>
    */
   private void increaseAntennaReadPointKillCount(String readPointName) {
	   if (readPointName != null) {
		   ReadPoint readPoint = (ReadPoint)readPoints.get(readPointName);
		   if (readPoint instanceof AntennaReadPoint) {
			   ((AntennaReadPoint)readPoint).increaseKillCount();
		   }
	   }
	   antReadPointKillCount++;
   }

   /**
    * Returns the accumulated memory read counter of all
    * <code>AntennaReadPoint</code>s.
    *
    * @return The accumulated memory read counter
    */
   public int getAntennaReadPointMemReadCount() {
	   return antReadPointMemReadCount;
   }

   /**
    * Returns the accumulated write counter of all
    * <code>AntennaReadPoint</code>s.
    *
    * @return The accumulated write counter
    */
   public int getAntennaReadPointWriteCount() {
	   return antReadPointWriteCount;
   }

   /**
    * Returns the accumulated kill counter of all
    * <code>AntennaReadPoint</code>s.
    *
    * @return The accumulated kill counter
    */
   public int getAntennaReadPointKillCount() {
	   return antReadPointKillCount;
   }

}
