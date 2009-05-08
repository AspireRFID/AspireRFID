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
import java.util.Hashtable;
import java.util.Vector;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;

/**
 * DataSelector objects are used to define what data shall be reported in
 * notification messages or by commands that take a DataSelector object as
 * parameter. Only events and data fields that are added to the respective lists
 * shall be reported.
 * @author Markus Vitalini
 */
public class DataSelector {

   /**
    * The name of the dataselector.
    */
   private String name;

   /**
    * The field names to report.
    * @link aggregation <{FieldName}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates FieldName
    */
   private Hashtable fieldNames;

   /**
    * The events to report.
    * @link aggregation <{EventType}>
    * @directed directed
    * @supplierCardinality 0..*
    * @associates EventType
    */
   private Hashtable eventTypes;

   /**
    * The tagfields to report (as String).
    */
   private Hashtable tagFieldNames;

   /**
    * The reader device this dataselector belongs to.
    */
   private ReaderDevice readerDevice;

   /**
    * The static method to create an instance of a dateselector. If this method
    * terminates successfully, the data selector is added to the list of
    * dataselectors in the reader device. If a dataselector with the same name
    * exists, an exception ("ERROR_OBJECT_EXISTS") is thrown.
    * @param name
    *           The name of the dataselector
    * @param readerDevice
    *           The reader device the dataselector belongs to
    * @return The instance of the dataselector
    * @throws ReaderProtocolException
    *            "ERROR_OBJECT_EXISTS"
    */
   public static DataSelector create(final String name,
         final ReaderDevice readerDevice) throws ReaderProtocolException {

      // check if DataSelector with the same name exists
      try {
         readerDevice.getDataSelector(name);
      } catch (ReaderProtocolException e) {
         // create new DataSelector
         DataSelector newDataSelector = new DataSelector(name, readerDevice);
         readerDevice.getDataSelectors().put(name, newDataSelector);
         return newDataSelector;
      }

      throw new ReaderProtocolException("ERROR_OBJECT_EXISTS",
            MessagingConstants.ERROR_OBJECT_EXISTS);

   }

   /**
    * The constructor of the dataselector.
    * @param name
    *           The name of the data selector
    * @param readerDevice
    *           The reader device the data selector belongs to
    */
   public DataSelector(final String name, final ReaderDevice readerDevice) {

      this.name = name;
      this.fieldNames = new Hashtable();
      this.eventTypes = new Hashtable();
      this.tagFieldNames = new Hashtable();
      this.readerDevice = readerDevice;

   }

   /**
    * Returns the name of the given DataSelector object.
    * @return The name of the data selector
    */
   public final String getName() {
      return name;
   }

   /**
    * Adds the specified field names to the list of field names currently
    * associated with this dataselector.
    * @param fieldNameList
    *           A list of field names
    */
   public final void addFieldNames(final String[] fieldNameList) {

      Vector fieldNames = readerDevice.getVector(fieldNameList);

      Enumeration iterator = fieldNames.elements();
      String cur;

      while (iterator.hasMoreElements()) {
         cur = (String) iterator.nextElement();
         if (!this.fieldNames.containsKey(cur)) {
            this.fieldNames.put(cur, cur);
         }
      }

   }

   /**
    * Removes a fieldname of the list of fieldnames currently associated with
    * this dataselector.
    * @param fieldNameList
    *           The list of fieldnames to remove
    */
   public final void removeFieldNames(final String[] fieldNameList) {

      Vector v = readerDevice.getVector(fieldNameList);
      
      Enumeration iterator = fieldNames.elements();
      String cur;

      while (iterator.hasMoreElements()) {
         cur = (String) iterator.nextElement();
         this.fieldNames.remove(cur);
      }

   }

   /**
    * Removes all fieldnames of the list of fieldnames currently associated with
    * this dataselector.
    */
   public final void removeAllFieldNames() {
      removeFieldNames((String[]) readerDevice.stringsToArray(fieldNames));
   }

   /**
    * Returns a list with all fieldnames currentliy associated with this
    * dataselector.
    * @return A list with all fieldnames currentliy associated with this
    *         dataselector
    */
   public final String[] getAllFieldNames() {
      return (String[]) readerDevice.stringsToArray(fieldNames);
   }

   /**
    * Adds the specified eventtypes to the list of eventtypes currently
    * associated with this DataSelector. Only events of the types that have been
    * added shall be reported.
    * @param eventList
    *           A list of event types to add
    */
   public final void addEventFilters(final String[] eventList) {

      Vector events = readerDevice.getVector(eventList);

      Enumeration iterator = events.elements();
      String cur;

      while (iterator.hasMoreElements()) {
         cur = (String) iterator.nextElement();
         if (!this.eventTypes.containsKey(cur)) {
            this.eventTypes.put(cur, cur);
         }
      }

   }

   /**
    * Removes the specified eventtypes from the list of eventfilters currently
    * associated with this dataselector.
    * @param eventList
    *           A list of eventtypes to remove
    */
   public final void removeEventFilters(final String[] eventList) {

      Vector events = readerDevice.getVector(eventList);

      Enumeration iterator = events.elements();
      String cur;

      while (iterator.hasMoreElements()) {
         cur = (String) iterator.nextElement();
         this.eventTypes.remove(cur);
      }

   }

   /**
    * Removes all eventtypes currently associated with this dataselector.
    */
   public final void removeAllEventFilters() {
      removeEventFilters((String[]) readerDevice.stringsToArray(eventTypes));
   }

   /**
    * Returns tahe list of all eventtypes currently associated with this
    * dataselector.
    * @return Returns the list of event filters
    */
   public final String[] getAllEventFilters() {
      return (String[]) readerDevice.stringsToArray(eventTypes);
   }

   /**
    * Adds the specified tagfieldnames to the list of tagfieldnames currently
    * associated with this dataselector.
    * @param tagFieldNameList
    *           A list of tag field names to add (list of strings)
    */
   public final void addTagFieldNames(final String[] tagFieldNameList) {

      Vector tagFieldNames = readerDevice.getVector(tagFieldNameList);

      Enumeration iterator = tagFieldNames.elements();
      String cur;

      while (iterator.hasMoreElements()) {
         cur = (String) iterator.nextElement();
         if (!this.tagFieldNames.containsKey(cur)) {
            this.tagFieldNames.put(cur, cur);
         }
      }

   }

   /**
    * Removes certain tagfieldnames from the list of tagfieldnames currently
    * associated with this dataselector.
    * @param tagFieldNameList
    *           A list of tagfieldnames to remove
    */
   public final void removeTagFieldNames(final String[] tagFieldNameList) {

      Vector tagFieldNames = readerDevice.getVector(tagFieldNameList);

      Enumeration iterator = tagFieldNames.elements();
      String cur;

      while (iterator.hasMoreElements()) {
         cur = (String) iterator.nextElement();
         this.tagFieldNames.remove(cur);
      }

   }

   /**
    * Removes all tagfieldnames currently associated with this dataselector.
    */
   public final void removeAllTagFieldNames() {
      removeTagFieldNames((String[]) readerDevice
            .stringsToArray(tagFieldNames));
   }

   /**
    * Returns a list of all tagfieldnames currently associated with this
    * dataselector. Note, this is a list of Strings.
    * @return A list of all tagfieldnames
    */
   public final String[] getAllTagFieldNames() {
      return (String[]) readerDevice.stringsToArray(tagFieldNames);
   }

   /**
    * Removes all associations of this dataselector.
    */
   protected final void removeAssociations() {

      // remove associations with current data selector of the reader device
      if (readerDevice.getCurrentDataSelector() != null
            && readerDevice.getCurrentDataSelector().equals(this)) {
         readerDevice.setCurrentDataSelector(null); // or default
         // DataSelector
         // ?
      }

      // remove associations with notification channesl
      Enumeration nCIterator = readerDevice.getNotificationChannels()
            .elements();
      NotificationChannel curNc;
      while (nCIterator.hasMoreElements()) {
         curNc = (NotificationChannel) nCIterator.nextElement();
         try {
            if (curNc.getDataSelector() == this) {
               curNc.setDataSelector(null);
            }
         } catch (ReaderProtocolException e) {
            System.out.println(e.getErrorName());
         }
      }

   }

   /**
    * Returns the event type.
    * @return The event type
    */
   public final Hashtable getEventTypes() {
      return eventTypes;
   }

   /**
    * Returns the FieldNames.
    * @return The FieldNames
    */
   public final Hashtable getFieldNames() {
      return fieldNames;
   }

   /**
    * Returns the TagFieldNames.
    * @return The TagFieldName
    */
   public final Hashtable getTagFieldNames() {
      return tagFieldNames;
   }
}
