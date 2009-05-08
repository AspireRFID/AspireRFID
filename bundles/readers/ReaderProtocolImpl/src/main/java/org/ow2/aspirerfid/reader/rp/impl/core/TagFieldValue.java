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

/**
 * This object is not created on the reader. It is only passed as a parameter to
 * some Source commands. This object encapsulates the information for a named
 * tag data field and its associated data value.
 * @author Markus Vitalini
 */
public class TagFieldValue {

   /**
    * The tag field.
    */
   private TagField tagField;

   /**
    * The data (as String).
    */
   private String value;

   /**
    * The constructor of the tag field value.
    * @param tagField
    *           The tag field
    * @param value
    *           The data
    */
   public TagFieldValue(final TagField tagField, final String value) {
      this.tagField = tagField;
      this.value = value;
   }

   /**
    * Returns the tagField.
    * @return Returns the tagField
    */
   public final TagField getTagField() {
      return tagField;
   }

   /**
    * Sets the tag field.
    * @param tagField
    *           The tagField to set
    */
   public final void setTagField(final TagField tagField) {
      this.tagField = tagField;
   }

   /**
    * Returns the data.
    * @return Returns the data
    */
   public final String getValue() {
      return value;
   }

   /**
    * Sets the data.
    * @param value
    *           The data to set
    */
   public final void setValue(final String value) {
      this.value = value;
   }

}
