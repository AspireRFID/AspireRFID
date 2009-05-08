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

import java.lang.reflect.Field;
import java.util.Vector;

/**
 * This class contains all types of fieldnames used in the Reader Protocol.
 * @author Markus Vitalini
 */
public final class FieldName {

   /**
    * The private consturctor. It is private because FieldName is a utility
    * class
    */
   private FieldName() {

   }

   /**
    * eventTriggers.
    */
   public static final String EVENT_TRIGGERS = "eventTriggers";

   /**
    * eventType.
    */
   public static final String EVENT_TYPE = "eventType";

   /**
    * eventTimeTick.
    */
   public static final String EVENT_TIME_TICK = "eventTimeTick";

   /**
    * eventTimeUTC.
    */
   public static final String EVENT_TIME_UTC = "eventTimeUTC";

   /**
    * readerEPC.
    */
   public static final String READER_EPC = "readerEPC";

   /**
    * readerHandle.
    */
   public static final String READER_HANDLE = "readerHandle";

   /**
    * readerName.
    */
   public static final String READER_NAME = "readerName";

   /**
    * readerRole.
    */
   public static final String READER_ROLE = "readerRole";

   /**
    * readerNowTick.
    */
   public static final String READER_NOW_TICK = "readerNowTick";

   /**
    * readerNowUTC.
    */
   public static final String READER_NOW_UTC = "readerNowUTC";

   /**
    * tagType.
    */
   public static final String TAG_TYPE = "tagType";

   /**
    * tagID.
    */
   public static final String TAG_ID = "tagID";

   /**
    * tagIDasPureURI.
    */
   public static final String TAG_ID_AS_PURE_URI = "tagIDasPureURI";

   /**
    * tagIDasTagUri.
    */
   public static final String TAG_ID_AS_TAG_URI = "tagIDasTagURI";

   /**
    * sourceName.
    */
   public static final String SOURCE_NAME = "sourceName";

   /**
    * sourceFrequency.
    */
   public static final String SOURCE_FREQUENCY = "sourceFrequency";

   /**
    * sourceProtocol.
    */
   public static final String SOURCE_PROTOCOL = "sourceProtocol";

   /**
    * notifyChannelName.
    */
   public static final String NOTIFY_CHANNEL_NAME = "notifyChannelName";

   /**
    * notifyTriggerName.
    */
   public static final String NOTIFY_TRIGGER_NAME = "notifyTriggerName";

   /**
    * allEvent.
    */
   public static final String ALL_EVENT = "allEvent";

   /**
    * allReader.
    */
   public static final String ALL_READER = "allReader";

   /**
    * allRag.
    */
   public static final String ALL_TAG = "allTag";

   /**
    * allSource.
    */
   public static final String ALL_SOURCE = "allSource";

   /**
    * allNotify.
    */
   public static final String ALL_NOTIFY = "allNotify";

   /**
    * all.
    */
   public static final String ALL = "all";

   /**
    * allSupported.
    */
   public static final String ALL_SUPPORTED = "allSupported";

   /**
    * Returns all supported fieldnames.
    * @return A Vector with all supported fieldnames
    */
   public static Vector getSupportedNames() {

      Vector supportedNames = new Vector();

      Class c = FieldName.class;
      Field[] fields = c.getFields();

      String fieldName;
      Field tempField;
      String tempString;

      for (int i = 0; i < fields.length; i++) {

         try {
            fieldName = fields[i].getName();
            tempField = c.getField(fieldName);
            tempString = (String) tempField.get(c);
            supportedNames.add(tempString);
         } catch (Exception e) {
            supportedNames = new Vector();
            return supportedNames;
         }

      }

      return supportedNames;

   }

   /**
    * Checks if a fieldname is contained in this class.
    * @param type
    *           The field name to check
    * @return 'ture' if the field name is in this class, otherwise 'false'
    */
   public static boolean contains(final String type) {
      
      Class c = FieldName.class;
      Field[] fields = c.getFields();

      String fieldName;
      Field tempField;
      String tempString;

      for (int i = 0; i < fields.length; i++) {

         try {
            fieldName = fields[i].getName();
            tempField = c.getField(fieldName);
            tempString = (String) tempField.get(c);
            if (tempString.toUpperCase().equals(type.toUpperCase())) {
               return true;
            }
         } catch (Exception e) {
            return false;
         }

      }

      return false;

   }
}
