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

package org.ow2.aspirerfid.reader.rp.impl.core.triggers;

import java.math.BigInteger;
import java.util.Enumeration;

import org.ow2.aspirerfid.reader.rp.impl.core.Trigger;

/**
 * This class represents the io value triggers manager and implements some
 * important methods to notify the triggers.
 * @author Markus Vitalini
 */
public abstract class IOValueTriggerPortManager extends IOTriggerPortManager {

   /**
    * Notifies the triggers that a value was received. This method checks the
    * value and if necessary the corresponding trigger is fired.
    * @param recValue
    *           The received value
    */
   protected final void checkAndNotifyTriggers(final String recValue) {

      Enumeration iterator = super.getTriggers().elements();
      Trigger cur;

      while (iterator.hasMoreElements()) {
         cur = (Trigger) iterator.nextElement();

         // port=<n>;value=<hex>[;mask=<hex>]
         String value;
         String mask;

         int first = cur.getValue().indexOf(';');
         int second = cur.getValue().lastIndexOf(';');
         final int seven = 7;
         final int six = 6;
         if (second != first) {
            value = cur.getValue().substring(first + seven, second);
            mask = cur.getValue().substring(second + six);
         } else {
            value = cur.getValue().substring(first + seven);
            mask = null;
         }

         if (validate(recValue, value, mask)) {
            cur.fire();
         }

      }

   }

   /**
    * Validates a string according to the filterValue and filterMask.
    * @param value
    *           The value to validate
    * @param filterValue
    *           The filterValue
    * @param filterMask
    *           The filterMask
    * @return 'true' if the value is valid, 'false' otherwise
    */
   final boolean validate(final String value, final String filterValue,
         final String filterMask) {

      // (filterValue & filterMask) == (value & filterMask)

      if (filterMask == null) {
         return filterValue == value;
      }

      final int hex = 16;

      BigInteger idInt = new BigInteger(value, hex);
      BigInteger valueInt = new BigInteger(filterValue, hex);
      BigInteger maskInt = new BigInteger(filterMask, hex);

      BigInteger valueAndMask = valueInt.and(maskInt);
      BigInteger idAndMask = idInt.and(maskInt);

      return valueAndMask.equals(idAndMask);

   }

}
