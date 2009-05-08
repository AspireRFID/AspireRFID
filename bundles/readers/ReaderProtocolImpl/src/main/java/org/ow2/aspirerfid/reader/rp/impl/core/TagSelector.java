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

import java.math.BigInteger;
import java.util.Enumeration;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;

/**
 * A TagSelector is a non-mutable object that can exist as a member of a Source
 * object (i.e., TagSelector objects are added and removed from a Source
 * object). A Source object can contain zero or more TagSelector objects. It
 * contains all information used for tag filtering.
 * @author Markus Vitalini
 */
public final class TagSelector {

   /**
    * The tag field.
    */
   private TagField tagField;

   /**
    * The filter mask.
    */
   private String filterMask;

   /**
    * The filter value.
    */
   private String filterValue;

   /**
    * The inclusive flag.
    */
   private boolean inclusive;

   /**
    * The name of the tag selector.
    */
   private String name;

   /**
    * The instance of the reader device this tag selector belongs to.
    */
   private ReaderDevice readerDevice;

   /**
    * A static method to create a tag selector object with the given name. If a
    * tag selector object with the same name exists, an exception
    * ("ERROR_OBJECT_EXISTS") is thrown.
    * @param name
    *           The name of the tagselector
    * @param tagField
    *           The tag field of the dataselector
    * @param filterValue
    *           The filter value
    * @param filterMask
    *           The filter mask
    * @param inclusive
    *           The inclusive flag
    * @param readerDevice
    *           The instance of the reader device this tagselector belongs to
    * @return The instance of the created tagselector
    * @throws ReaderProtocolException
    *            "ERROR_OBJECT_EXISTS"
    */
   public static TagSelector create(final String name,
         final TagField tagField, final String filterValue,
         final String filterMask, final boolean inclusive,
         final ReaderDevice readerDevice) throws ReaderProtocolException {

      // check if TagSelector with the same name exists
      try {
         readerDevice.getTagSelector(name);
      } catch (ReaderProtocolException e) {
         // create new TagSelector
         TagSelector newTagSelector = new TagSelector(name, tagField,
               filterValue, filterMask, inclusive, readerDevice);
         readerDevice.getTagSelectors().put(name, newTagSelector);
         return newTagSelector;
      }

      throw new ReaderProtocolException("ERROR_OBJECT_EXISTS",
            MessagingConstants.ERROR_OBJECT_EXISTS);

   }

   /**
    * The private constructor of the tagselector.
    * @param name
    *           The name of the tagselector
    * @param tagField
    *           The tag field of the dataselector
    * @param filterValue
    *           The filter value
    * @param filterMask
    *           The filter mask
    * @param inclusive
    *           The inclusive flag
    * @param readerDevice
    *           The instance of the reader device this tagselector belongs to
    */
   private TagSelector(final String name, final TagField tagField,
         final String filterValue, final String filterMask,
         final boolean inclusive, final ReaderDevice readerDevice) {

      this.tagField = tagField;
      this.filterMask = filterMask;
      this.filterValue = filterValue;
      this.inclusive = inclusive;
      this.name = name;
      this.readerDevice = readerDevice;

   }

   /**
    * Returns the maximum number of tagselectors the reader supports.
    * @return Ehe maximum number of tagselectors the reader supports
    */
   public int getMaxNumberSupported() {
      return readerDevice.getMaxTagSelectorNumber();
   }

   /**
    * Returns the name of the given tagselector object.
    * @return Rhe name of the given tagselector object.
    */
   public String getName() {
      return name;
   }

   /**
    * Returns the tagfield attribute of the tagselector.
    * @return The tagfield attribute of the tagselector
    */
   public TagField getTagField() {
      return tagField;
   }

   /**
    * Returns the value attribute of this tagselector object.
    * @return The value attribute of this tagselector object
    */
   public String getValue() {
      return filterValue;
   }

   /**
    * Returns the mask attribute of this tagselector object.
    * @return The mask attribute of this tagselector object
    */
   public String getMask() {
      return filterMask;
   }

   /**
    * Returns the inclusive flag attribute of this tagselector object.
    * @return The inclusive flag attribute of this tagselector object
    */
   public boolean getInclusiveFlag() {
      return inclusive;
   }

   /**
    * Removes the associations of this tagselector.
    */
   protected void removeAssociations() {

      // remove associations with sources
      Enumeration iterator = readerDevice.getSources().elements();
      Source cur;

      while (iterator.hasMoreElements()) {
         cur = (Source) iterator.nextElement();

         TagSelector[] tagSelectorToRemove = new TagSelector[] {this};
         cur.removeTagSelectors(tagSelectorToRemove);

      }

   }

   /**
    * Validate a string according to the filter value and filter mask.
    * @param value
    *           The value to validate
    * @return 'true' if the value is valid, 'false' otherwise
    */
   protected boolean validate(final String value) {

      // (filterValue & filterMask) == (value & filterMask)

      final int hex = 16;
      BigInteger idInt = new BigInteger(value, hex);
      BigInteger valueInt = new BigInteger(filterValue, hex);
      BigInteger maskInt = new BigInteger(filterMask, hex);

      BigInteger valueAndMask = valueInt.and(maskInt);
      BigInteger idAndMask = idInt.and(maskInt);

      //System.out.println("id "+idInt.toString(16));
      //System.out.println("value "+valueInt.toString(16));
      //System.out.println("mask "+maskInt.toString(16));
      //System.out.println("value & mask "+valueAndMask.toString(2));
      //System.out.println("id & mask "+idAndMask.toString(2));
      //System.out.println(valueAndMask.equals(idAndMask));

      return valueAndMask.equals(idAndMask);

   }

}
