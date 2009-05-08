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

/**
 * The TagFieldValueParamType of the Reader Protocol. It is a part of the
 * ReadReport.
 * @author Markus Vitalini
 */
public class TagFieldValueParamType {

   /**
    * The tagFieldName.
    */
   private String tagFieldName;

   /**
    * The value.
    */
   private String tagFieldValue;

   /**
    * The constructor of the TagFieldValueParameterType.
    */
   public TagFieldValueParamType() {
      this.tagFieldName = "";
      this.tagFieldValue = "";
   }

   /**
    * Returns the tagFieldName.
    * @return Returns the tagFieldName
    */
   public final String getTagFieldName() {
      return tagFieldName;
   }

   /**
    * Sets the tagFieldName.
    * @param tagFieldName
    *           The tagFieldName to set
    */
   public final void setTagFieldName(final String tagFieldName) {
      this.tagFieldName = tagFieldName;
   }

   /**
    * Returns the tagFieldValue.
    * @return Returns the tagFieldValue
    */
   public final String getTagFieldValue() {
      return tagFieldValue;
   }

   /**
    * Sets the tagFieldValue.
    * @param tagFieldValue
    *           The tagFieldValue to set
    */
   public final void setTagFieldValue(final String tagFieldValue) {
      this.tagFieldValue = tagFieldValue;
   }

}
