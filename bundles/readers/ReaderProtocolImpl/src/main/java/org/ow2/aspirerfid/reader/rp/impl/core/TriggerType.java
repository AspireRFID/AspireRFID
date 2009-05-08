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
 * This class contains all types of triggers supported in the reader protocol.
 * @author Markus Vitalini
 */
public final class TriggerType {

   /**
    * The private constructor of the class. It is private because it is a
    * utility class.
    */
   private TriggerType() {

   }

   // trigger types
   /**
    * CONTINUOUS.
    */
   public static final String CONTINUOUS = "CONTINUOUS";

   /**
    * TIMER.
    */
   public static final String TIMER = "TIMER";

   /**
    * IOEDGE.
    */
   public static final String IO_EDGE = "IOEDGE";

   /**
    * IOVALUE.
    */
   public static final String IO_VALUE = "IOVALUE";
   
   /**
    * NONE.
    */
   public static final String NONE = "NONE";
   
   /**
    * VENDOR_EXTENSION
    */
   public static final String VENDOR_EXTENSION = "VENDOR_EXTENSION";

   /**
    * Returns the supported trigger types.
    * @return A list of trigger types
    */
   public static Vector getSupportedTypes() {

      Vector supportedTriggerTypes = new Vector();

      Class c = TriggerType.class;
      Field[] fields = c.getFields();

      String fieldName;
      Field tempField;
      String tempString;

      for (int i = 0; i < fields.length; i++) {

         try {
            fieldName = fields[i].getName();
            tempField = c.getField(fieldName);
            tempString = (String) tempField.get(c);
            supportedTriggerTypes.add(tempString);
         } catch (Exception e) {
            supportedTriggerTypes = new Vector();
            return supportedTriggerTypes;
         }

      }

      return supportedTriggerTypes;

   }

   /**
    * Check if a trigger type is available.
    * @param triggerType
    *           The type to check
    * @return Returns 'true' if the type is available, 'false' otherwise
    */
   public static boolean contains(final String triggerType) {
      String type = triggerType.toUpperCase();
      Class c = TriggerType.class;
      Field[] fields = c.getFields();

      String fieldName;
      Field tempField;
      String tempString;

      for (int i = 0; i < fields.length; i++) {

         try {
            fieldName = fields[i].getName();
            tempField = c.getField(fieldName);
            tempString = (String) tempField.get(c);
            if (tempString.equals(type)) {
               return true;
            }
         } catch (Exception e) {
            return false;
         }

      }

      return false;

   }

}
