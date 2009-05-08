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
import java.util.Hashtable;

import org.ow2.aspirerfid.reader.rp.impl.core.Trigger;

/**
 * The abstract class of the io triggers. Every io trigger should inherit from
 * this class because it contains the logic to add and remove a io trigger from
 * sources and notification channels.
 * @author Markus Vitalini
 */
public abstract class IOTriggerPortManager {

   /**
    * The triggers that listen on this manager.
    */
   private Hashtable triggers = new Hashtable();

   /**
    * Adds a trigger to the list of triggers that wait to be informed by the
    * port manager.
    * @param trigger
    *           The trigger to add
    * @param assoc
    *           The name of the associates instance (removeListener(trigger,
    *           this.getName))
    */
   public final void addListener(final Trigger trigger, final String assoc) {
      triggers.put(trigger.getName() + assoc, trigger);
   }

   /**
    * Removes a trigger.
    * @param trigger
    *           The trigger to remove
    * @param assoc
    *           The name of the associates instance (removeListener(trigger,
    *           this.getName))
    */
   public final void removeListener(final Trigger trigger, final String assoc) {
      triggers.remove(trigger.getName() + assoc);
   }

   /**
    * This method is called if the first trigger is added to the list of
    * triggers that want to be informed by this port manager.
    */
   public abstract void start();

   /**
    * This method is called if the last trigger was removed from list of
    * triggers that want to be informed by the port manager.
    */
   public abstract void stop();

   /**
    * Notifies all associated triggers.
    */
   protected final void notifyAllTriggers() {

      Enumeration iterator = triggers.elements();
      Trigger cur;

      while (iterator.hasMoreElements()) {
         cur = (Trigger) iterator.nextElement();

         cur.fire();

      }

   }

   /**
    * Returns the number of associated triggers.
    * @return The number of associated triggers
    */
   public final int getNumberOfTriggers() {
      return triggers.size();
   }

   /**
    * Returns the triggers.
    * @return The triggers
    */
   public final Hashtable getTriggers() {
      return triggers;
   }
}
