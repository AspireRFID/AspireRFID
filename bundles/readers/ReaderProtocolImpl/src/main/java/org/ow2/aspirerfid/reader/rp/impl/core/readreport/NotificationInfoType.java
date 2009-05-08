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

package org.ow2.aspirerfid.reader.rp.impl.core.readreport;

import java.util.Hashtable;

/**
 * The NotificationInfoType of the Reader Protocol. It is a part of the
 * ReadReport
 * @author Markus Vitalini
 */
public class NotificationInfoType {

   /**
    * The notification channel name.
    */
   private String channelName;

   /**
    * The triggers.
    */
   private Hashtable channelTriggers;

   /**
    * The constructor of the NotificationInfoType.
    */
   public NotificationInfoType() {
      channelName = null;
      channelTriggers = new Hashtable();
   }

   /**
    * Returns the channelName.
    * @return Returns the channelName.
    */
   public final String getChannelName() {
      return channelName;
   }

   /**
    * Sets the channel name.
    * @param channelName
    *           The channelName to set.
    */
   public final void setChannelName(final String channelName) {
      this.channelName = channelName;
   }

   /**
    * Gets the channel triggers.
    * @return Returns the channelTriggers.
    */
   public final Hashtable getChannelTriggers() {
      return channelTriggers;
   }

   /**
    * Sets the channel triggers.
    * @param channelTriggers
    *           The channelTriggers to set.
    */
   public final void setChannelTriggers(final Hashtable channelTriggers) {
      this.channelTriggers = channelTriggers;
   }

   /**
    * Adds a trigger to the list of triggers.
    * @param trigger
    *           The trigger to add
    */
   public final void addChannelTrigger(final String trigger) {
      channelTriggers.put(trigger, trigger);
   }

}
