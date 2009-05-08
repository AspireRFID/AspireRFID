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
 * This class contains all events that occur in the reader protocol.
 * @author Markus Vitalini
 */
public final class EventType {

   /**
    * The constructor of the EventType. It is private because EventType is a
    * utility class.
    */
   private EventType() {

   }

   // event types

   /**
    * evGlimpsed.
    */
   public static final String EV_GLIMPSED = "evGlimpsed";

   /**
    * evNew.
    */
   public static final String EV_NEW = "evNew";

   /**
    * evUnknown.
    */
   public static final String EV_UNKNOWN = "evUnknown";

   /**
    * evObserved.
    */
   public static final String EV_OBSERVED = "evObserved";

   /**
    * evLost.
    */
   public static final String EV_LOST = "evLost";

   /**
    * evPurged.
    */
   public static final String EV_PURGED = "evPurged";

   // states

   /**
    * isUnknown.
    */
   public static final String ST_IS_UNKNOWN = "isUnknown";

   /**
    * isObserved.
    */
   public static final String ST_IS_OBSERVED = "isObserved";

   /**
    * isLost.
    */
   public static final String ST_IS_LOST = "isLost";

   /**
    * isGlimpsed.
    */
   public static final String ST_IS_GLIMPSED = "isGlimpsed";

   /**
    * Returns all supported event types.
    * @return A Vector with all supported event types
    */
   public static Vector getSupportedTypes() {

      Vector supportedEventTypes = new Vector();

      Class c = EventType.class;
      Field[] fields = c.getFields();

      String fieldName;
      Field tempField;
      String tempString;

      for (int i = 0; i < fields.length; i++) {

         try {
            fieldName = fields[i].getName();
            tempField = c.getField(fieldName);
            tempString = (String) tempField.get(c);
            supportedEventTypes.add(tempString);
         } catch (Exception e) {
            supportedEventTypes = new Vector();
            return supportedEventTypes;
         }

      }

      return supportedEventTypes;

   }

   /**
    * Checks if a event type is contained in this class.
    * @param type
    *           The event type to check
    * @return 'ture' if the event type is in this class, otherwise 'false'
    */
   public static boolean contains(final String type) {
      
      Class c = EventType.class;
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
