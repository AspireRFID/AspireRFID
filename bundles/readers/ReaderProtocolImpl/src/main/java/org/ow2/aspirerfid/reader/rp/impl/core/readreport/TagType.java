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

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * This class is part of the ReadReport in the ReaderProtocol.
 * @author Markus Vitalini
 */
public class TagType {

   /**
    * The id of the tag.
    */
   private String id;

   /**
    * The id of the tag as pure URI.
    */
   private String idAsPureURI;

   /**
    * The id of the tag as tag URI.
    */
   private String idAsTagUri;

   /**
    * The type of the tag.
    */
   private String tagType;

   /**
    * The events that caused the report of this tag.
    * @associates <{TagEventType}>
    */
   private Hashtable tagEvents;

   /**
    * The tagfield of the tag.
    * @associates <{TagFieldValueParamType}>
    */
   private Hashtable tagFields;

   /**
    * The constructor of TagType.
    */
   public TagType() {
      this.id = "";
      this.idAsPureURI = null;
      this.idAsTagUri = null;
      this.tagType = null; // not supported in HAL
      this.tagEvents = new Hashtable();
      this.tagFields = new Hashtable();
   }

   /**
    * Returns the id of the tag.
    * @return Returns the id
    */
   public final String getId() {
      return id;
   }

   /**
    * Sets the id of the tag.
    * @param id
    *           The id to set
    */
   public final void setId(final String id) {
      this.id = id;
   }

   /**
    * Returns the id of the tag as pure URI.
    * @return Returns the idAsPureURI
    */
   public final String getIdAsPureURI() {
      return idAsPureURI;
   }

   /**
    * Sets the pure URI id of the tag.
    * @param idAsPureURI
    *           The idAsPureURI to set
    */
   public final void setIdAsPureURI(final String idAsPureURI) {
      this.idAsPureURI = idAsPureURI;
   }

   /**
    * Returns the tag URI of the tag.
    * @return Returns the idAsTagUri
    */
   public final String getIdAsTagURI() {
      return idAsTagUri;
   }

   /**
    * Sets the tag URI of the tag.
    * @param idAsTagUri
    *           The idAsTagUri to set
    */
   public final void setIdAsTagURI(final String idAsTagUri) {
      this.idAsTagUri = idAsTagUri;
   }

   /**
    * Returns all events.
    * @return Returns the tagEvent
    */
   public final Hashtable getAllTagEvents() {
      return tagEvents;
   }

   /**
    * Adds a event.
    * @param event
    *           The event to add
    */
   public final void addTagEvent(final TagEventType event) {
      tagEvents.put(String.valueOf(tagEvents.size()), event);
   }

   /**
    * Adds events to the tagEvent list.
    * @param tagEvents
    *           The events to add
    */
   public final void addTagEvents(final Hashtable tagEvents) {

      Enumeration iterator = tagEvents.elements();
      TagEventType cur;
      while (iterator.hasMoreElements()) {
         cur = (TagEventType) iterator.nextElement();
         this.tagEvents.put(cur.getEventType(), cur);
      }

   }

   /**
    * Removes a single event from tagEvent list.
    * @param tagEvent
    *           The tagEvent to delete
    */
   public final void removeTagEvent(final String tagEvent) {

      this.tagEvents.remove(tagEvent);

   }

   /**
    * Sets all events.
    * @param tagEvents
    *           The events to set
    */
   public final void setTagEvents(final Hashtable tagEvents) {
      this.tagEvents = tagEvents;
   }

   /**
    * Returns a TagFieldValueParam.
    * @param name
    *           The name of the tag field
    * @return The TagFieldValueParam
    */
   public final TagFieldValueParamType getTagField(final String name) {
      return (TagFieldValueParamType) tagFields.get(name);
   }

   /**
    * Returns all tagfields.
    * @return Returns the tagFields
    */
   public final Hashtable getAllTagFields() {
      return tagFields;
   }

   /**
    * Checks if a tag field is in the tagfield list of this tag.
    * @param name
    *           The name of the tagfield to check
    * @return Returns 'true' if a TagField is available, 'false' otherwise
    */
   public final boolean containsTagField(final String name) {

      Enumeration iterator = tagFields.elements();
      TagFieldValueParamType curTagField;
      while (iterator.hasMoreElements()) {
         curTagField = (TagFieldValueParamType) iterator.nextElement();
         if (curTagField.getTagFieldName().equals(name)) {
            return true;
         }
      }
      return false;

   }

   /**
    * Adds a single tagfield to the tagField list.
    * @param tfvp
    *           The TagFieldValueParamType instance
    */
   public final void addTagField(final TagFieldValueParamType tfvp) {
      tagFields.put(tfvp.getTagFieldName(), tfvp);
   }

   /**
    * Adds a list of tagfields to the tagField list.
    * @param tagFields
    *           The tagFields to set.
    */
   public final void addTagFields(final Hashtable tagFields) {

      Enumeration iterator = tagFields.elements();
      TagFieldValueParamType cur;
      while (iterator.hasMoreElements()) {
         cur = (TagFieldValueParamType) iterator.nextElement();
         this.tagFields.put(cur.getTagFieldName(), cur);
      }

   }

   /**
    * Removes a single tagfield from tagField list.
    * @param tagFieldName
    *           The tagfield to remove
    */
   public final void removeTagField(final String tagFieldName) {
      this.tagFields.remove(tagFieldName);
   }

   /**
    * Returns the tag type.
    * @return Returns the tagType
    */
   public final String getTagType() {
      return tagType;
   }

   /**
    * Sets the tag type.
    * @param tagType
    *           The tagType to set
    */
   public final void setTagType(final String tagType) {
      this.tagType = tagType;
   }

   /**
    * Checks if the tag contains information about events.
    * @return 'true' if information about events is available, 'false' otherwise
    */
   public final boolean containsEventInfo() {
      if (tagEvents.size() > 0) {
         return true;
      }
      return false;

   }

}
