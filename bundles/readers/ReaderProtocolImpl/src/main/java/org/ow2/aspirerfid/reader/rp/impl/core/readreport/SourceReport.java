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
 * @author Markus Vitalini
 */
public class SourceReport {

   /**
    * All tags of the report.
    * @associates <{TagType}>
    */
   private Hashtable tags;

   /**
    * Information about the source.
    */
   private SourceInfoType sourceInfo;

   /**
    * The constructor of the ReadReport.
    */
   public SourceReport() {
      tags = new Hashtable();
      sourceInfo = null;
   }

   /**
    * Returns a list with all tags.
    * @return Returns the tags
    */
   public final Hashtable getAllTags() {
      return tags;
   }

   /**
    * Returns a tag with the specific id.
    * @param id
    *           The id of the tag
    * @return The tag with id 'id'
    */
   public final TagType getTag(final String id) {

      if (tags.containsKey(id)) {
         return (TagType) tags.get(id);
      } else {
         return null;
      }

   }

   /**
    * Adds a tag to the list of tags.
    * @param tag
    *           The tag to add
    */
   public final void addTag(final TagType tag) {

      tags.put(tag.getId(), tag);

   }

   /**
    * Adds a list of tags.
    * @param tags
    *           The tags to add
    */
   public final void addTags(final Hashtable tags) {

      Enumeration iterator = tags.elements();
      TagType cur;
      while (iterator.hasMoreElements()) {
         cur = (TagType) iterator.nextElement();
         if (!this.tags.containsKey(cur.getId())) {
            this.tags.put(cur.getId(), cur);
         }
      }

   }

   /**
    * Removes the tag with id 'id'.
    * @param id
    *           The id of the tag to remove
    */
   public final void removeTag(final String id) {
      tags.remove(id);
   }

   /**
    * Checks if the tag with a specific id is in the report.
    * @param id
    *           The id of the tag
    * @return 'true' if the tag is in the report, otherwise 'false'
    */
   public final boolean containsTag(final String id) {

      if (getTag(id) != null) {
         return true;
      }
      return false;

   }

   /**
    * Checks if the report contains information about a specific source.
    * @return 'true' if the report contains information about the source,
    *         'false' otherwise
    */
   public final boolean containsSourceInfo() {
      if (sourceInfo != null) {
         return true;
      }
      return false;

   }

   /**
    * Returns information about the source.
    * @return Returns information about the source
    */
   public final SourceInfoType getSourceInfo() {
      return sourceInfo;
   }

   /**
    * Sets information about the source.
    * @param sourceInfo
    *           The information of the source to set
    */
   public final void setSourceInfo(final SourceInfoType sourceInfo) {
      this.sourceInfo = sourceInfo;
   }

   /**
    * Converts SourceReport to a String.
    * @returns SourceReport as a String
    */
   /* added by CF but not implemented in other classes
   public final String toString() {
	   
	  StringBuffer buffer = new StringBuffer(); 
	   
	  Iterator it = tags.values().iterator();
	  while (it.hasNext()) {
		  buffer.append(((TagType)it.next()).toString() + "\n");
	  }
	  buffer.append(sourceInfo.toString()); 
	  
	  return buffer.toString();
   }
   */
}
