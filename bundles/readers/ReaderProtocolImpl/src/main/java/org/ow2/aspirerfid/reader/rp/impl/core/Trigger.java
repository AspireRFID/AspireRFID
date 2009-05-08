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

import java.util.Enumeration;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.ReadReportNotificationListener;
import org.ow2.aspirerfid.reader.rp.impl.core.readreport.ReadReport;

/**
 * A Trigger is a non-mutable object that can exist as a composite member of
 * either a Source or a NotificationChannel object, i.e., Trigger objects are
 * added and removed from Source and NotificationChannel objects. There is one
 * pool of Trigger objects. Source and NotificationChannel objects can both
 * reference the same Trigger object. Source and NotificationChannel objects can
 * contain zero or up to a predefined maximum number of total Trigger member
 * objects. A Trigger shall be activated as soon as it is added to another
 * object. It shall be deactivated once it has been removed from all objects it
 * had been added to, i.e. when it is not associated with any other object.
 * @author Markus Vitalini
 */
public final class Trigger {

   /**
    * The name of the trigger.
    */
   private String name;

   /**
    * A value dependant of the trigger type.
    */
   private String value;

   /**
    * The type of the trigger (Continuous, Timer, IOEdge, IOValue).
    */
   private String type;

   /**
    * The reader device this trigger belongs to.
    */
   private ReaderDevice readerDevice;
   
   
   // ====================================================================
   // ----- Fields added for the reader management implementation ------//
   // ====================================================================
   
   /**
    * The number of times this trigger has fired.
    */
   private int fireCount;

   /**
    * Create a Triger object with a given name. If a Trigger object with the
    * same name exists, an error is returned. This is a static method. The
    * Trigger objects type and value establish the conditions whereby the
    * trigger shall fire. If the Reader doesnt support the requested
    * TriggerType, the error condition ERROR_PARAMETER_NOT_SUPPORTED shall be
    * raised. The Trigger shall implicitly be added to the list of all Triggers
    * kept by the ReaderDevice object. Possible values for triggers are:
    * Continuous: no value <br>
    * Timer : "ms=5000"
    * @param name
    *           The name of the trigger
    * @param type
    *           The type of the trigger ((Continuous, Timer, IOEdge, IOValue))
    * @param value
    *           The value of the trigger
    * @param readerDevice
    *           The reader device this trigger belongs to
    * @return The new instance of the trigger
    * @throws ReaderProtocolException
    *            "ERROR_TOO_MANY_TRIGGERS", "ERROR_PARAMETER_NOT_SUPPORTED",
    *            "ERROR_PARAMETER_MISSING"
    */
   public static Trigger create(final String name, final String type,
         final String value, final ReaderDevice readerDevice)
         throws ReaderProtocolException {

      // check number of triggers
      if (readerDevice.getMaxTriggerNumber() <= readerDevice.getTriggers()
            .size()) {
         throw new ReaderProtocolException("ERROR_TOO_MANY_TRIGGERS",
               MessagingConstants.ERROR_TOO_MANY_TRIGGERS);
      }

      // check type of trigger
      if (!TriggerType.contains(type)) {
         throw new ReaderProtocolException("ERROR_PARAMETER_NOT_SUPPORTED",
               MessagingConstants.ERROR_PARAMETER_NOT_SUPPORTED);
      }

      // check value
      if (type.toUpperCase().equals(TriggerType.IO_EDGE)
            || type.toUpperCase().equals(TriggerType.IO_VALUE)
            || type.toUpperCase().equals(TriggerType.TIMER)) {
         if (value == null) {
            throw new ReaderProtocolException("ERROR_PARAMETER_MISSING",
                  MessagingConstants.ERROR_PARAMETER_MISSING);
         }
         // check if 'ms=' not forgotten for timer trigger value
         if (type.toUpperCase().equals(TriggerType.TIMER)) {
            if (!value.substring(0, 3).equals("ms=")) {
               throw new ReaderProtocolException("ERROR_PARAMETER_INVALID_FORMAT",
                     MessagingConstants.ERROR_PARAMETER_INVALID_FORMAT);
            }
         }
      }

      // check if Trigger with the same name exists
      try {
         readerDevice.getTrigger(name);
      } catch (ReaderProtocolException e) {
         // create new Trigger
         Trigger newTrigger = new Trigger(name, value, type, readerDevice);
         readerDevice.getTriggers().put(name, newTrigger);
         return newTrigger;
      }

      throw new ReaderProtocolException("ERROR_OBJECT_EXISTS",
            MessagingConstants.ERROR_OBJECT_EXISTS);

   }

   /**
    * The private constructor of the trigger.
    * @param name
    *           The name of the trigger
    * @param value
    *           The value of the trigger
    * @param type
    *           The type of the trigger ((Continuous, Timer, IOEdge, IOValue))
    * @param readerDevice
    *           The reader device this trigger belongs to
    */
   private Trigger(final String name, final String value, final String type,
         final ReaderDevice readerDevice) {

      this.name = name;
      this.value = value;
      this.type = type.toUpperCase();
      this.readerDevice = readerDevice;
      fireCount = 0;

   }

   /**
    * Returns the maximum number of Triggers this Reader supports.
    * @return The maximum number of Triggers this Reader supports
    */
   public int getMaxNumberSupported() {
      return readerDevice.getMaxTriggerNumber();
   }

   /**
    * Returns the name of the given Trigger object.
    * @return The name of the trigger
    */
   public String getName() {
      return name;
   }

   /**
    * Returns the TriggerType of this Trigger.
    * @return The TriggerType
    */
   public String getType() {
      return type;
   }

   /**
    * Returns the triggervalue of this Trigger object.
    * @return The value of the trigger
    */
   public String getValue() {
      return value;
   }

   /**
    * This command causes this Trigger object to fire (i.e., activate the
    * trigger) immediately, regardless of the value settings of the Trigger.
    */
   public void fire() {

      // do nothing if type.equals(TriggerType.CONTINUOUS)
      
      if (type.equals(TriggerType.TIMER)
            || type.equals(TriggerType.IO_EDGE)
            || type.equals(TriggerType.IO_VALUE)) {
         
         // increase the fire counter
         fireCount++;
         
         // sources
         Enumeration sourceIterator = readerDevice.getSources().elements();
         Source curSource;
         while (sourceIterator.hasMoreElements()) {
            curSource = (Source) sourceIterator.nextElement();
            if (curSource.getReadTriggers().containsKey(this.getName())) {
               // new DataSelector
               String name = "triggerDataSelecotorOf" + this.getName();
               DataSelector dataSelector = new DataSelector(name,
                     readerDevice);
               // set field names
               String[] allFieldNames = new String[] {FieldName.ALL,
                     FieldName.ALL};
               dataSelector.addFieldNames(allFieldNames);
               curSource.readWithEventGeneration(dataSelector, this);
            }
         }

         // notification channels
         Enumeration ncIterator = readerDevice.getNotificationChannels()
               .elements();
         NotificationChannel curNc;
         while (ncIterator.hasMoreElements()) {
            curNc = (NotificationChannel) ncIterator.nextElement();
            try {
               ReadReport report = curNc.readQueuedData(true, this);
               if (report.getAllTags().size() > 0) {
                  ReadReportNotificationListener.getInstance().notifyHost(
                        report, curNc.getName(), curNc.getDataSelector());
               }
               // ReaderTester.printReadReport(curReport);
            } catch (ReaderProtocolException e) {
               System.out.println(e.getErrorName());
            }
         }
      }

   }

   /**
    * Removes associations with other instances.
    */
   public void removeAssociations() {

      // sources
      Trigger[] tempTrigger = new Trigger[] {this};

      Enumeration sourceIterator = readerDevice.getSources().elements();
      Source curSource;
      while (sourceIterator.hasMoreElements()) {
         curSource = (Source) sourceIterator.nextElement();

         curSource.removeReadTriggers(tempTrigger);

      }

   }

   /**
    * Gets the reader Device.
    * @return The reader device
    */
   public ReaderDevice getReaderDevice() {
      return readerDevice;
   }
   
   
   // ====================================================================
   // ----- Methods added for the reader management implementation -----//
   // ====================================================================
   
   /**
    * Returns the number of times this trigger has fired.
    * 
    * @return The number of times this trigger has fired
    */
   public int getFireCount() {
	   return fireCount;
   }
   
   /**
    * Resets the <code>fireCount</code> attribute to <code>0</code>.
    */
   public void resetFireCount() {
	   fireCount = 0;
   }
   
}
