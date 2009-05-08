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

import java.util.Date;
import java.util.Hashtable;

/**
 * The TagEventType of the Reader Protocol. It is a part of the ReadReport
 * @author Markus Vitalini
 */
public class TagEventType {

   /**
    * The eventtype.
    */
   private String eventType;

   /**
    * The triggers.
    * @associates <{Trigger}>
    */
   private Hashtable eventTriggers;

   /**
    * The timeTick.
    */
   private long timeTick;

   /**
    * The UTC time.
    */
   private Date timeUTC;

   /**
    * The constructor of the TagEventType.
    */
   public TagEventType() {
      this.eventType = "";
      this.eventTriggers = new Hashtable();
      this.timeTick = 0;
      this.timeUTC = new Date();
   }

   /**
    * The constructor of the TagEventType.
    * @param eventType
    *           The eventType
    * @param eventTriggers
    *           The triggers
    * @param timeTick
    *           The time tick
    * @param timeUTC
    *           The time UTC
    */
   public TagEventType(final String eventType, final Hashtable eventTriggers,
         final long timeTick, final Date timeUTC) {
      this.eventType = eventType;
      this.eventTriggers = eventTriggers;
      this.timeTick = timeTick;
      this.timeUTC = timeUTC;
   }

   /**
    * Returns the eventTriggers.
    * @return Returns the eventTriggers.
    */
   public final Hashtable getEventTriggers() {
      return eventTriggers;
   }

   /**
    * Sets the eventTriggers.
    * @param eventTriggers
    *           The eventTriggers to set.
    */
   public final void setEventTriggers(final Hashtable eventTriggers) {
      this.eventTriggers = eventTriggers;
   }

   /**
    * Returns the eventType.
    * @return Returns the eventType.
    */
   public final String getEventType() {
      return eventType;
   }

   /**
    * Sets the eventType.
    * @param eventType
    *           The eventType to set.
    */
   public final void setEventType(final String eventType) {
      this.eventType = eventType;
   }

   /**
    * Returns the timeTick.
    * @return Returns the timeTick.
    */
   public final long getTimeTick() {
      return timeTick;
   }

   /**
    * Sets the timeTick.
    * @param timeTick
    *           The timeTick to set.
    */
   public final void setTimeTick(final long timeTick) {
      this.timeTick = timeTick;
   }

   /**
    * Returns the timeUTC.
    * @return Returns the timeUTC.
    */
   public final Date getTimeUTC() {
      return timeUTC;
   }

   /**
    * Sets the timeUTC.
    * @param timeUTC
    *           The timeUTC to set.
    */
   public final void setTimeUTC(final Date timeUTC) {
      this.timeUTC = timeUTC;
   }

}
