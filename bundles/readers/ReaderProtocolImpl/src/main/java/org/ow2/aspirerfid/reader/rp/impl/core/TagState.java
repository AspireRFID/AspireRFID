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

import java.util.Date;
import java.util.Hashtable;

import org.ow2.aspirerfid.reader.rp.impl.core.readreport.TagEventType;

/**
 * This class is used to represent the state of a single tag.
 * @author Markus Vitalini
 */
public class TagState {

   /**
    * The tag id.
    */
   private String id;

   /**
    * The state of the tag. Possible states are (isUnknown, isGlimpsed,
    * isObserved, isLost).
    */
   private String state;

   /**
    * Timestamp at first tag detecition.
    */
   private long first;

   /**
    * Timestamp at last decection.
    */
   private long last;

   /**
    * The events occured Elements are of type TagEventType.
    */
   private Hashtable tagEvents;

   /**
    * The constructor of TagState.
    */
   public TagState() {
      this.tagEvents = new Hashtable();
   }

   /**
    * Returns the first detection timestamp.
    * @return Returns the first detection timestamp
    */
   protected final long getFirst() {
      return first;
   }

   /**
    * Sets the timestamp at first tag detection.
    * @param first
    *           The first detection timestamp
    */
   protected final void setFirst(final long first) {
      this.first = first;
   }

   /**
    * Returns the tag id.
    * @return Returns the tag id
    */
   protected final String getId() {
      return id;
   }

   /**
    * Sets the tag id.
    * @param id
    *           The tag id to set
    */
   protected final void setId(final String id) {
      this.id = id;
   }

   /**
    * Returns the last detection timestamp.
    * @return Returns the last detection timestamp
    */
   protected final long getLast() {
      return last;
   }

   /**
    * Sets the last detection timestamp.
    * @param last
    *           The last detection timestamp
    */
   protected final void setLast(final long last) {
      this.last = last;
   }

   /**
    * Returns the state.
    * @return Returns the state
    */
   protected final String getState() {
      return state;
   }

   /**
    * Sets the state.
    * @param state
    *           The state to set
    */
   protected final void setState(final String state) {
      this.state = state;
   }

   /**
    * Returns the tagevents.
    * @return The tag events
    */
   protected final Hashtable getTagEvents() {
      return tagEvents;
   }

   /**
    * Sets the tag events.
    * @param tagEvents
    *           The tag events to set
    */
   protected final void setTagEvents(final Hashtable tagEvents) {
      this.tagEvents = tagEvents;
   }

   /**
    * A Method to add a single tag event.
    * @param eventType
    *           The event type
    * @param trigger
    *           The trigger that lead to this event
    * @param timeTick
    *           The time tick
    * @param timeUTC
    *           The time UTC
    */
   protected final void updateTagEvent(final String eventType,
         final Trigger trigger, final long timeTick, final Date timeUTC) {

      TagEventType tempEventType;

      tagEvents = new Hashtable();
      tempEventType = new TagEventType();
      tempEventType.setEventType(eventType);
      tempEventType.setTimeTick(timeTick);
      tempEventType.setTimeUTC(timeUTC);
      tagEvents.put(EventType.EV_NEW, tempEventType);

      // in case of ev_new, ev_glimpsed ist alsow thrown
      if (eventType.equals(EventType.EV_NEW)) {
         tempEventType = new TagEventType();
         tempEventType.setEventType(EventType.EV_GLIMPSED);
         tempEventType.setTimeTick(timeTick);
         tempEventType.setTimeUTC(timeUTC);
         tagEvents.put(EventType.EV_GLIMPSED, tempEventType);
      }

      if (trigger != null) {
         tempEventType.getEventTriggers().put(trigger.getName(), trigger);
      }

   }

}
