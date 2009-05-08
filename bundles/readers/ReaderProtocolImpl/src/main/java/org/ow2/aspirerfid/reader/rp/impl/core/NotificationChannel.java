/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.impl.core;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Timer;
import java.util.Vector;

import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.AdministrativeStatus;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.OperationalStatus;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmLevel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.NotificationChannelOperStatusAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.TTOperationalStatusAlarmControl;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.NotificationInfoType;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.ReadReport;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.ReaderInfoType;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.SourceInfoType;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.SourceReport;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagEventType;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagType;
import org.ow2.aspirerfid.reader.rp.impl.core.triggers.ContinuousNotificationThread;
import org.ow2.aspirerfid.reader.rp.impl.core.triggers.IOEdgeTriggerPortManager;
import org.ow2.aspirerfid.reader.rp.impl.core.triggers.IOValueTriggerPortManager;
import org.ow2.aspirerfid.reader.rp.impl.core.triggers.TimerNotificationThread;
import org.apache.log4j.Logger;

/**
 * A NotificationChannel is used to report notifications to a single host. It
 * has a list of Sources associated with it. Only events coming from these
 * Sources shall be reported. It also has a DataSelector that determines what
 * data should be reported to the host. If no DataSelector is explicitly
 * associated, then the Readers current DataSelector shall be used.
 * Notification shall be sent whenever the associated Trigger fires.
 * Alternatively, a host can also query the NotificationChannel for its
 * contents, using the command NotificationChannel.readQueuedData().
 * @author Markus Vitalini
 */
public final class NotificationChannel {

	/** The logger. */
	private static Logger log = Logger.getLogger(NotificationChannel.class);
	
   /**
    * The triggers associated with this notification channel.
    * @link aggregation <{Trigger}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates Trigger
    */
   private Hashtable notificationTriggers;

   /**
    * The dataSelector to generate the report.
    */
   private DataSelector dataSelector;

   /**
    * The relevant souces to observe.
    * @link aggregation <{Source}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates Source
    */
   private Hashtable sources;

   /**
    * The URL of the host.
    */
   private String address;

   /**
    * The name of this notification channel.
    */
   private String name;

   /**
    * The read report (SourceReport.getSourceInfo().getSourceName(),
    * sourceReport).
    */
   private volatile Hashtable report;

   /**
    * The reader device this notification channel belongs to.
    */
   private ReaderDevice readerDevice;

   /**
    * The thread for continuous triggers.
    */
   private ContinuousNotificationThread continuousThread;

   /**
    * The threads for timer triggers (of type Timer).
    */
   private Hashtable timerThreads;

   /**
    * The threads for io port triggers (of type IOValueTrigger).
    */
   private Hashtable ioValueTriggers;

   /**
    * The threads for io port triggers (of type IOEdgeTrigger).
    */
   private Hashtable ioEdgeTriggers;
   
   
   // ====================================================================
   // ----- Fields added for the reader management implementation ------//
   // ====================================================================
   
   /**
    * The timestamp when the last attempt was made to send a notification to
    * the given address.
    */
   private Date lastNotificationAttempt;
   
   /**
    * The timestamp when the last successful notification was send to the given
    * address.
    */
   private Date lastSuccessfulNotification;
   
   /**
    * The operational status of this notification channel.
    */
   private OperationalStatus operStatus;
   
   /**
    * The object that controls the conditions for generating alarms alerting a
    * manager of changes in a <code>NotificationChannel</code>'s operational
    * status.
    */
   private TTOperationalStatusAlarmControl operStatusAlarmControl;
   
   /**
    * The administrative status of this notification channel.
    */
   private AdministrativeStatus adminStatus;
   
   /**
    * The number of channel operational state change notifications that have
    * been suppressed for this <code>NotificationChannel</code> object.
    */
   private int operStateSuppressions = 0;

   /**
    * Create a NotificationChannel object with a given name. If a
    * NotificationChannel object with the same name exists already, an error is
    * returned. This is a static method. The NotificationChannel will implicitly
    * be added to the list of all NotificationChannels kept by the ReaderDevice
    * object.
    * @param name
    *           The name of the notification channel
    * @param addr
    *           The host address
    * @param readerDevice
    *           The reader device this channel belongs to
    * @return The instance of the new NotificationChannel
    * @throws ReaderProtocolException
    *            The ReaderProtocolException "ERROR_OBJECT_EXISTS" is thrown
    */
   public static NotificationChannel create(final String name,
         final String addr, final ReaderDevice readerDevice)
         throws ReaderProtocolException {

      // check if NotificationChannel with the same name exists
      try {
         readerDevice.getNotificationChannel(name);
      } catch (ReaderProtocolException e) {
         // create new NotificationChannel
         NotificationChannel newNotificationChannel = new NotificationChannel(
               name, addr, readerDevice);
         readerDevice.getNotificationChannels().put(name,
               newNotificationChannel);
         return newNotificationChannel;
      }

      throw new ReaderProtocolException("ERROR_OBJECT_EXISTS",
            MessagingConstants.ERROR_OBJECT_EXISTS);
   }

   /**
    * The private constructor of the NotificationChannel.
    * @param name
    *           The name of the channel
    * @param addr
    *           The address of the host
    * @param readerDevice
    *           The reader device this channel belongs to
    */
   private NotificationChannel(final String name, final String addr,
         final ReaderDevice readerDevice) {
      this.name = name;
      this.address = addr;
      this.readerDevice = readerDevice;
      this.sources = new Hashtable();
      this.report = new Hashtable();
      this.dataSelector = readerDevice.getCurrentDataSelector();
      this.continuousThread = null;
      this.timerThreads = new Hashtable();
      this.notificationTriggers = new Hashtable();
      this.ioValueTriggers = new Hashtable();
      this.ioEdgeTriggers = new Hashtable();
      
      operStatusAlarmControl = new TTOperationalStatusAlarmControl(name
				+ "_OperStatusAlarmControl", false, AlarmLevel.ERROR, 0,
				OperationalStatus.ANY, OperationalStatus.ANY);
      
      adminStatus = AdministrativeStatus.UP;
   }

   /**
    * Returns the name of the given NotificationChannel object.
    * @return The name of the NotificationChannel
    */
   public String getName() {
      return name;
   }

   /**
    * Returns the address to which this NotificationChannel object sends its
    * notifications as specified by either commands NotificationChannel.create
    * or NotificationChannel.setAddress.
    * @return The address of the host
    */
   public String getAddress() {
      return address;
   }

   /**
    * Returns the effective address to which this NotificationChannel object
    * sends its notifications.
    * @return The effective address of the host
    */
   public String getEffectiveAddress() {
      return address;
   }

   /**
    * Sets the address to which this NotificationChannel object sends its
    * notifications.
    * @param addr
    *           The address of the host
    * @return If connect mode was specified, then the return value does not
    *         apply and the Reader returns zero. Otherwise, if listen mode was
    *         specified, then the return returns the port number assigned by the
    *         Reader to listen for host NotificationChannel connections.
    */
   public int setAddress(final String addr) {
      address = addr;
      return 0;
   }

   /**
    * Returns the DataSelector currently associated with this
    * NotificationChannel object. If there is no DataSelector object associated,
    * the error ERROR_DATASELECTOR_NOT_FOUND is raised.
    * @return The dataSelector associated to this notification channel
    * @throws ReaderProtocolException
    *            "ERROR_DATASELECTOR_NOT_FOUND"
    */
   public DataSelector getDataSelector() throws ReaderProtocolException {
      if (dataSelector == null) {
         throw new ReaderProtocolException("ERROR_DATASELECTOR_NOT_FOUND",
               MessagingConstants.ERROR_DATASELECTOR_NOT_FOUND);
      } else {
         return dataSelector;
      }
   }

   /**
    * Sets the DataSelector object to be used with this NotificationChannel.
    * @param dataSelector
    *           The dataSelector to use
    */
   public void setDataSelector(final DataSelector dataSelector) {
      this.dataSelector = dataSelector;
   }

   /**
    * Adds the specified Sources to the list of Sources currently associated
    * with this NotificationChannel. If some of the Sources to be added are
    * already associated with this NotificationChannel, only the not yet
    * associated Sources shall be added and the command completes successfully.
    * @param sourceList
    *           The sources to add
    */
   public void addSources(final Source[] sourceList) {

      Vector sources = readerDevice.getVector(sourceList);

      Enumeration iterator = sources.elements();
      Source cur;

      while (iterator.hasMoreElements()) {
         cur = (Source) iterator.nextElement();

         this.sources.put(cur.getName(), cur);
         cur.addNotificationChannel(this);

      }

   }

   /**
    * Removes the specified Sources from the list of Sources currently
    * associated with this NotificationChannel.
    * @param sourceList
    *           The sources to remove
    */
   public void removeSources(final Source[] sourceList) {

      Vector souces = readerDevice.getVector(sourceList);

      Enumeration iterator = sources.elements();
      Source cur;

      while (iterator.hasMoreElements()) {
         cur = (Source) iterator.nextElement();

         this.sources.remove(cur.getName());
         cur.removeNotificationChannel(this);

      }

   }

   /**
    * Removes all Sources currently associated with this NotificationChannel.
    */
   public void removeAllSources() {
      removeSources((Source[]) readerDevice.sourcesToArray(sources));
   }

   /**
    * Returns the Source with the specified name currently associated with this
    * NotificationChannel object. If no Source object with the given name
    * exists, the error ERROR_SOURCE_NOT_FOUND is raised.
    * @param name
    *           The name of the source
    * @return The instance of the source
    * @throws ReaderProtocolException
    *            "ERROR_SOURCE_NOT_FOUND"
    */
   public Source getSource(final String name) throws ReaderProtocolException {

      Source tempSource = (Source) sources.get(name);
      if (tempSource != null) {
         return tempSource;
      } else {
         throw new ReaderProtocolException("ERROR_SOURCE_NOT_FOUND",
               MessagingConstants.ERROR_SOURCE_NOT_FOUND);
      }

   }

   /**
    * Returns all Sources currently associated with this NotificationChannel
    * object. If no Sources are currently associated with this object, the
    * command completes successfully and an empty list will be returned.
    * @return A list of sources
    */
   public Source[] getAllSources() {
      return (Source[]) readerDevice.sourcesToArray(sources);
   }

   /**
    * Adds the specified Triggers to the list of Notification Triggers currently
    * associated with this NotificationChannel. If some of the Triggers to be
    * added are already associated with this NotificationChannel, only the not
    * yet associated Triggers shall be added and the command completes
    * successfully. Once a Trigger has been added, it is immediately activated.
    * @param triggerList
    *           The triggers to add
    * @throws ReaderProtocolException
    *            "ERROR_TOO_MANY_TRIGGERS"
    */
   public void addNotificationTriggers(final Trigger[] triggerList)
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
         if (!this.notificationTriggers.containsKey(cur.getName())) {
            this.notificationTriggers.put(cur.getName(), cur);
            if (cur.getType().equals(TriggerType.CONTINUOUS)) {
               // continuous trigger
               if (continuousThread == null && timerThreads.size() == 0) {
                  continuousThread = new ContinuousNotificationThread(this, cur);
                  continuousThread.start();
               }
            } else if (cur.getType().equals(TriggerType.TIMER)) {
               // timer trigger
               if (continuousThread == null) {
                  Timer timerThread = new Timer();
                  timerThreads.put(cur.getName(), timerThread);
                  final int num = 3;
                  timerThread.schedule(
                        new TimerNotificationThread(this, cur), 0, Integer
                              .parseInt(cur.getValue().substring(num)));
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
    * Removes the specified Triggers from the list of Notification Triggers
    * currently associated with this NotificationChannel. Once a Trigger isnt
    * associated to any object anymore, it is deactivated.
    * @param triggerList
    *           The triggers to remove
    */
   public void removeNotificationTriggers(final Trigger[] triggerList) {

      Vector triggers = readerDevice.getVector(triggerList);

      Enumeration iterator = triggers.elements();
      Trigger cur;

      while (iterator.hasMoreElements()) {
         cur = (Trigger) iterator.nextElement();

         if (notificationTriggers.containsKey(cur.getName())) {
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

            this.notificationTriggers.remove(cur.getName());
         }
      }

   }

   /**
    * Removes all Triggers currently associated with this NotificationChannel.
    */
   public void removeAllNotificationTriggers() {
      removeNotificationTriggers((Trigger[]) readerDevice
            .triggersToArray(notificationTriggers));
   }

   /**
    * Returns the Trigger with the specified name currently associated with this
    * NotificationChannel object. If no Trigger object with the given name
    * exists, the error ERROR_TRIGGER_NOT_FOUND is raised.
    * @param name
    *           The name of the notification trigger
    * @return The notification trigger
    * @throws ReaderProtocolException
    *            "ERROR_TRIGGER_NOT_FOUND"
    */
   public Trigger getNotificationTrigger(final String name)
         throws ReaderProtocolException {

      Trigger tempSource = (Trigger) notificationTriggers.get(name);
      if (tempSource != null) {
         return tempSource;
      } else {
         throw new ReaderProtocolException("ERROR_TRIGGER_NOT_FOUND",
               MessagingConstants.ERROR_TRIGGER_NOT_FOUND);
      }

   }

   /**
    * Returns all Triggers currently associated with this NotificationChannel
    * object. If no Triggers are currently associated with this object, the
    * command will complete successfully and an empty list will be returned.
    * @return A list of triggers
    */
   public Trigger[] getAllNotificationTriggers() {
      return (Trigger[]) readerDevice.triggersToArray(notificationTriggers);
   }

   /**
    * This command returns the data currently queued for delivery in the report
    * buffer. What data is reported depends on the DataSelector associated with
    * the NotificationChannel.
    * @param clearBuffer
    *           An optional flag to indicate if the report buffer should be
    *           cleared after the ReadReport is returned
    * @return The resulting ReadReport
    */
   public ReadReport readQueuedData(final boolean clearBuffer) {

      Hashtable tempReport;
      synchronized(this) {
         if (report.isEmpty()) {
            try {
               wait();
            } catch (InterruptedException e) {}
         }
         tempReport = report;
         if (clearBuffer) {
            report = new Hashtable();
         }
         notify();
      }
      return generateReadReport(tempReport, dataSelector, null);
   }

   /**
    * This command returns the data currently queued for delivery in the report
    * buffer. What data is reported depends on the DataSelector associated with
    * the NotificationChannel.
    * @param trigger
    *           The trigger that cause this readQueuedData request
    * @param clearBuffer
    *           An optional flag to indicate if the report buffer should be
    *           cleared after the ReadReport is returned
    * @return The resulting ReadReport
    */
   public ReadReport readQueuedData(final boolean clearBuffer,
         final Trigger trigger) {

      Hashtable tempReport;
      synchronized(this) {
         if (report.isEmpty()) {
            try {
               wait();
            } catch (InterruptedException e) {}
         }
         tempReport = report;
         if (clearBuffer) {
            report = new Hashtable();
         }
         notify();
      }
      return generateReadReport(tempReport, dataSelector, trigger);
   }

   /**
    * Add a report to the actual report of this source.
    * @param sourceReport
    *           The report to add
    */
   public void addSourceReport(final SourceReport sourceReport) {

      if ((sourceReport == null) || (sourceReport.getSourceInfo() == null)) {
    	  log.debug("Source Report or Source Info is null. " +
    	  		"Source Report is not added to notification channel");
         return;
      }
      
      synchronized(this) {
         SourceReport tempReport;
   
         if (!report.containsKey(sourceReport.getSourceInfo().getSourceName())) {
       	  // if there is no source report from this source 
       	  // currently stored for this notification channel, 
       	  // add an empty report with the source name only to the report hashmap
            tempReport = new SourceReport();
            SourceInfoType tempSourceInfo = new SourceInfoType();
            tempSourceInfo.setSourceName(sourceReport.getSourceInfo()
                  .getSourceName());
            tempReport.setSourceInfo(tempSourceInfo);
            report.put(sourceReport.getSourceInfo().getSourceName(), tempReport);
         } else {
       	  // there is already a source report from this source stored, 
       	  // get the corresponding source report 
            tempReport = (SourceReport) report.get(sourceReport.getSourceInfo()
                  .getSourceName());
         }
   
         Hashtable fieldNames = dataSelector.getFieldNames();
   
         // SOURCE_NAME
         if (fieldNames.containsKey(FieldName.SOURCE_NAME)
               || fieldNames.containsKey(FieldName.ALL_SOURCE)
               || fieldNames.containsKey(FieldName.ALL)) {
            tempReport.getSourceInfo().setSourceName(
                  sourceReport.getSourceInfo().getSourceName());
         } else {
            tempReport.getSourceInfo().setSourceName(null);
         }  
         log.debug("Setting source name in notification source report to " +
        		 tempReport.getSourceInfo().getSourceName());
         
         // SOURCE_FREQUENCY
         if (fieldNames.containsKey(FieldName.SOURCE_FREQUENCY)
               || fieldNames.containsKey(FieldName.ALL_SOURCE)
               || fieldNames.containsKey(FieldName.ALL)) {
            tempReport.getSourceInfo().setSourceFrequency(
                  sourceReport.getSourceInfo().getSourceFrequency());
         } else {
            tempReport.getSourceInfo().setSourceFrequency(-1);
         }   
         log.debug("Setting source frequency in notification source report to " +
        		 tempReport.getSourceInfo().getSourceFrequency());
         
         // SOURCE_PROTOCOL
         if (fieldNames.containsKey(FieldName.SOURCE_PROTOCOL)
               || fieldNames.containsKey(FieldName.ALL_SOURCE)
               || fieldNames.containsKey(FieldName.ALL)) {
            // Not supported in HardwareAbstraction
            tempReport.getSourceInfo().setSourceProtocol("not supported");
         } else {
            tempReport.getSourceInfo().setSourceProtocol(null);
         }
         log.debug("Setting source Protocol in notification source report to " +
         		 tempReport.getSourceInfo().getSourceProtocol());
         
         // tags
         Enumeration tagIterator = sourceReport.getAllTags().elements();
         TagType curTag;
         while (tagIterator.hasMoreElements()) {
            curTag = (TagType) tagIterator.nextElement();
            TagType tag;
            if (tempReport.getTag(curTag.getId()) == null) {
               tag = new TagType();
               tag.setId(curTag.getId());
               tempReport.addTag(tag);
            } else {
               tag = tempReport.getTag(curTag.getId());
            }
   
            // TAG_TYPE
            if (fieldNames.containsKey(FieldName.TAG_TYPE)
                  || fieldNames.containsKey(FieldName.ALL_TAG)
                  || fieldNames.containsKey(FieldName.ALL)) {
               // Not supported in HardwareAbstraction
               tag.setTagType("not supported");
            } else {
               tag.setTagType(null);
            }
            // TAG_ID_AS_PURE_URI
            if (fieldNames.containsKey(FieldName.TAG_ID_AS_PURE_URI)
                  || fieldNames.containsKey(FieldName.ALL_TAG)
                  || fieldNames.containsKey(FieldName.ALL)) {
               // Only for non-EPC tags
               final int num = 4;
               int numOfBits = num * tag.getId().length();
               tag.setIdAsPureURI("urn:epc:raw:" + numOfBits + ".x"
                     + tag.getId());
            } else {
               tag.setIdAsPureURI(null);
            }
            // TAG_ID_AS_TAG_URI
            if (fieldNames.containsKey(FieldName.TAG_ID_AS_TAG_URI)
                  || fieldNames.containsKey(FieldName.ALL_TAG)
                  || fieldNames.containsKey(FieldName.ALL)) {
               // Only for non-EPC tags
               final int num = 4;
               int numOfBits = num * tag.getId().length();
               tag.setIdAsTagURI("urn:epc:raw:" + numOfBits + ".x"
                           + tag.getId());
            } else {
               tag.setIdAsTagURI(null);
            }
            // tag fields
            Enumeration tfnIterator = dataSelector.getTagFieldNames().elements();
            String curTfn;
            while (tfnIterator.hasMoreElements()) {
               curTfn = (String) tfnIterator.nextElement();
               tag.addTagField(curTag.getTagField(curTfn));
            }
            // events
            Enumeration eventIterator = curTag.getAllTagEvents().elements();
            TagEventType curEvent;
            while (eventIterator.hasMoreElements()) {
               curEvent = (TagEventType) eventIterator.nextElement();
   
               // EVENTS
               if (fieldNames.containsKey(FieldName.EVENT_TIME_TICK)
                     || fieldNames.containsKey(FieldName.EVENT_TIME_UTC)
                     || fieldNames.containsKey(FieldName.EVENT_TRIGGERS)
                     || fieldNames.containsKey(FieldName.EVENT_TYPE)
                     || fieldNames.containsKey(FieldName.ALL_EVENT)
                     || fieldNames.containsKey(FieldName.ALL)) {
                  if (dataSelector.getEventTypes().size() <= 0) {
                     tempReport.removeTag(curTag.getId());
                  } else if (dataSelector.getEventTypes().containsKey(
                        curEvent.getEventType())) {
                     TagEventType tempEvent = new TagEventType();
                     tempEvent.setEventType(curEvent.getEventType());
                     if (fieldNames.containsKey(FieldName.EVENT_TIME_TICK)
                           || fieldNames.containsKey(FieldName.ALL_EVENT)
                           || fieldNames.containsKey(FieldName.ALL)) {
                        tempEvent.setTimeTick(curEvent.getTimeTick());
                     }
                     if (fieldNames.containsKey(FieldName.EVENT_TIME_UTC)
                           || fieldNames.containsKey(FieldName.ALL_EVENT)
                           || fieldNames.containsKey(FieldName.ALL)) {
                        tempEvent.setTimeUTC(curEvent.getTimeUTC());
                     }
                     if (fieldNames.containsKey(FieldName.EVENT_TRIGGERS)
                           || fieldNames.containsKey(FieldName.ALL_EVENT)
                           || fieldNames.containsKey(FieldName.ALL)) {
                        tempEvent.setEventTriggers(curEvent.getEventTriggers());
                     }
                     tag.addTagEvent(tempEvent);
                  }
               }
   
            }
   
         }
   
         if (tempReport.getAllTags().size() <= 0) {
            report.remove(sourceReport.getSourceInfo().getSourceName());
            log.debug("No tags in source report just put together - removing it.");
         }
         notify();
      } // end synchronized

   }

   /**
    * This method generates a read report.
    * @param sourceReport
    *           The source report
    * @param dataSelector
    *           The data selector
    * @param trigger
    *           The trigger
    * @return The report
    */
   public ReadReport generateReadReport(final Hashtable sourceReport,
         final DataSelector dataSelector, final Trigger trigger) {

      Hashtable fieldNames = dataSelector.getFieldNames();

      ReadReport tempReport = new ReadReport();
      tempReport.setSourceReports(sourceReport);

      // READER
      if (!tempReport.containsReaderInfo()) {
         ReaderInfoType newReaderInfo = new ReaderInfoType();
         tempReport.setReaderInfo(newReaderInfo);
      }

      // READER_EPC
      if (fieldNames.containsKey(FieldName.READER_EPC)
            || fieldNames.containsKey(FieldName.ALL_READER)
            || fieldNames.containsKey(FieldName.ALL)) {
         tempReport.getReaderInfo().setEpc(this.readerDevice.getEPC());
      } else {
         tempReport.getReaderInfo().setEpc(null);
      }

      // READER_HANDLE
      if (fieldNames.containsKey(FieldName.READER_HANDLE)
            || fieldNames.containsKey(FieldName.ALL_READER)
            || fieldNames.containsKey(FieldName.ALL)) {
         tempReport.getReaderInfo().setHandle(this.readerDevice.getHandle());
      } else {
         tempReport.getReaderInfo().setHandle(-1);
      }

      // READER_NAME
      if (fieldNames.containsKey(FieldName.READER_NAME)
            || fieldNames.containsKey(FieldName.ALL_READER)
            || fieldNames.containsKey(FieldName.ALL)) {
         tempReport.getReaderInfo().setName(this.readerDevice.getName());
      } else {
         tempReport.getReaderInfo().setName(null);
      }

      // READER_ROLE
      if (fieldNames.containsKey(FieldName.READER_ROLE)
            || fieldNames.containsKey(FieldName.ALL_READER)
            || fieldNames.containsKey(FieldName.ALL)) {
         tempReport.getReaderInfo().setRole(this.readerDevice.getRole());
      } else {
         tempReport.getReaderInfo().setRole(null);
      }

      // READER_NOW_TICK
      if (fieldNames.containsKey(FieldName.READER_NOW_TICK)
            || fieldNames.containsKey(FieldName.ALL_READER)
            || fieldNames.containsKey(FieldName.ALL)) {
         tempReport.getReaderInfo().setNowTick(
               this.readerDevice.getTimeTicks());
      } else {
         tempReport.getReaderInfo().setNowTick(-1);
      }

      // READER_NOW_UTC
      if (fieldNames.containsKey(FieldName.READER_NOW_UTC)
            || fieldNames.containsKey(FieldName.ALL_READER)
            || fieldNames.containsKey(FieldName.ALL)) {
         tempReport.getReaderInfo().setNowUTC(this.readerDevice.getTimeUTC());
      } else {
         tempReport.getReaderInfo().setNowUTC(null);
      }

      //  NOTIFY
      if (!tempReport.containsNotificationInfo()) {
         NotificationInfoType newNotificationInfo = new NotificationInfoType();
         tempReport.setNotificationInfo(newNotificationInfo);
      }

      // NOTIFY_CHANNEL_NAME
      if (fieldNames.containsKey(FieldName.NOTIFY_CHANNEL_NAME)
            || fieldNames.containsKey(FieldName.ALL_NOTIFY)
            || fieldNames.containsKey(FieldName.ALL)) {
         tempReport.getNotificationInfo().setChannelName(this.getName());
      } else {
         tempReport.getNotificationInfo().setChannelName(null);
      }

      // NOTIFY_TRIGGER_NAME
      if (fieldNames.containsKey(FieldName.NOTIFY_TRIGGER_NAME)
            || fieldNames.containsKey(FieldName.ALL_NOTIFY)
            || fieldNames.containsKey(FieldName.ALL)) {
         if (trigger != null) {
            tempReport.getNotificationInfo().addChannelTrigger(
                  trigger.getName());
         }
      }

      return tempReport;

   }

   /**
    * Remove all associations of this source.
    */
   public void removeAssociations() {
      // source
      removeAllSources();
      removeAllNotificationTriggers();

   }

   /**
    * Returns the notification triggers.
    * @return The notification triggers
    */
   public Hashtable getNotificationTriggers() {
      return notificationTriggers;
   }

   /**
    * Returns the sources.
    * @return The sources
    */
   public Hashtable getSources() {
      return sources;
   }
   
   
   // ====================================================================
   // ----- Methods added for the reader management implementation -----//
   // ====================================================================
   
   /**
    * Returns the timestamp when the last attempt was made to send a
    * notification to the given address.
    * 
    * @return The timestamp when the last attempt was made to send a
    *         notification to the given address
    */
   public Date getLastNotificationAttempt() {
	   return lastNotificationAttempt;
   }
   
   /**
    * Returns the timestamp when the last successful notification was send to
    * the given address.
    * 
    * @return The timestamp when the last successful notification was send to
    *         the given address
    */
   public Date getLastSuccessfulNotification() {
	   return lastSuccessfulNotification;
   }
   
   /**
    * Returns the value of the <code>operStatus</code> attribute.
    * 
    * @return The value of the <code>operStatus</code> attribute
    */
   public OperationalStatus getOperStatus() {
	   // try to reach the host
	   
	   int timeout = 200;
	   
	   URI addr = null;
	   try {
		   addr = new URI(getAddress());
	   } catch (URISyntaxException use) {
		   setOperStatus(OperationalStatus.UNKNOWN);
		   return operStatus;
	   }

	   try {
		   InetAddress host = InetAddress.getByName(addr.getHost());
		   if (host.isReachable(timeout))
			   setOperStatus(OperationalStatus.UP);
		   else
			   setOperStatus(OperationalStatus.DOWN);
	   } catch (Exception e) {
		   setOperStatus(OperationalStatus.UNKNOWN);
	   }
	   
	   return operStatus;
   }
   
   /**
    * Sets the <code>NotificationChannel</code>'s <code>AdminStatus</code>
    * object.
    * 
    * @param administrativeStatus
    *            The administrative state for the
    *            <code>NotificationChannel</code>
    */
   public void setAdminStatus(AdministrativeStatus administrativeStatus) {
	   adminStatus = administrativeStatus;
   }
   
   /**
    * Returns the <code>NotificationChannel</code>'s
    * <code>AdminStatus</code> object.
    * 
    * @return The <code>NotificationChannel</code>'s
    *         <code>AdminStatus</code> object
    */
   public AdministrativeStatus getAdminStatus() {
	   return adminStatus;
   }
   
   /**
    * Returns the object that controls the conditions for generating alarms
    * alerting a manager of changes in a <code>NotificationChannel</code>'s
    * operational status.
    * 
    * @return The <code>NotificationChannel</code>'s
    *         <code>operStatusAlarmControl</code> object
    */
   public TTOperationalStatusAlarmControl getOperStatusAlarmControl() {
	   return operStatusAlarmControl;
   }
   
   /**
    * Increases the number of channel operational state change notifications
    * that have been suppressed for this <code>NotificationChannel</code>
    * object by <code>1</code>.
    */
   public void increaseOperStateSuppressions() {
	   operStateSuppressions++;
   }
   
   /**
    * Returns the number of channel operational state change notifications that
    * have been suppressed for this <code>NotificationChannel</code> object.
    * 
    * @return The number of channel operational state change notifications that
    *         have been suppressed for this <code>NotificationChannel</code>
    *         object
    */
   public int getOperStateSuppressions() {
	   return operStateSuppressions;
   }
   
   /**
    * Resets the number of channel operational state change notifications that
    * have been suppressed for this <code>NotificationChannel</code> object
    * to <code>0</code>.
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
					new NotificationChannelOperStatusAlarm(
							"NotificationChannelOperStatusAlarm",
							operStatusAlarmControl.getLevel(), readerDevice,
							this.operStatus, operStatus, name),
					operStatusAlarmControl);
			this.operStatus = operStatus;
	   }
   }
   
   /**
    * Sets the timestamp when the last attempt was made to send a notification
    * to the given address.
    * 
    * @param lastNotificationAttempt
    *            The timestamp when the last attempt was made to send a
    *            notification to the given address
    */
   public void setLastNotificationAttempt(Date lastNotificationAttempt) {
	   this.lastNotificationAttempt = lastNotificationAttempt;
   }
   
   /**
    * Sets the timestamp when the last successful notification was send to the
    * given address.
    * 
    * @param lastSuccessfulNotification
    *            The timestamp when the last successful notification was send
    *            to the given address
    */
   public void setLastSuccessfulNotification(Date lastSuccessfulNotification) {
	   this.lastSuccessfulNotification = lastSuccessfulNotification;
   }
}
