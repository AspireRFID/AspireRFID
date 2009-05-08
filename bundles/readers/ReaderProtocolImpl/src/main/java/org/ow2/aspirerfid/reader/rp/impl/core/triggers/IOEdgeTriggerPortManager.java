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

import java.util.Enumeration;

import org.ow2.aspirerfid.reader.rp.impl.core.Trigger;

/**
 * This class represents the io edge trigger manager and implements some
 * important methods to notify the triggers.
 * @author Markus Vitalini
 */
public abstract class IOEdgeTriggerPortManager extends IOTriggerPortManager {

   /**
    * Notifies the triggers that an event was received. This method checks the
    * event and if necessary the corresponding trigger is fired.
    * @param recPin
    *           The received pin
    * @param recRising
    *           The flag if the signal is falling or rising
    */
   final void checkAndNotifyTriggers(final String recPin, 
         final boolean recRising) {

      Enumeration iterator = super.getTriggers().elements();
      Trigger cur;

      while (iterator.hasMoreElements()) {
         cur = (Trigger) iterator.nextElement();

         // type=<rising|falling>;port=<n>;pin=<n>

         String pin;
         boolean rising;

         if (cur.getValue().indexOf("rising") < 0) {
            rising = false;
         } else {
            rising = true;
         }
         int second = cur.getValue().lastIndexOf(';');
         final int offset = 5;
         pin = cur.getValue().substring(second + offset);

         if (validate(recPin, recRising, pin, rising)) {
            cur.fire();
            System.out.println("fire " + cur.getName());
         }

      }

   }

   /**
    * Validate the values of the edge trigger.
    * @param recPin
    *           The received pin
    * @param recRising
    *           The receive rising flag
    * @param pin
    *           The pin of the trigger
    * @param rising
    *           The rising flag of the trigger
    * @return 'ture' if the values matches, 'false' otherwise
    */
   final boolean validate(final String recPin, final boolean recRising,
         final String pin, final boolean rising) {

      return (recPin.equals(pin)) && (recRising == rising);

   }

}
