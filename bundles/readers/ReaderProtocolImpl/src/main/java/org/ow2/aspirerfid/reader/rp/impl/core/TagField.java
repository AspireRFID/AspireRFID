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

import java.util.Enumeration;
import java.util.Hashtable;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.MessagingConstants;

/**
 * Tagfield objects are used to define individual tag data fields. A tag field
 * specifies where the data field is located in tag memory. Further each tag
 * field has a name that makes it possible to associate data fields with the
 * same or similar data types. For example, two different tag types, using
 * different tag RF protocols, may need to store the same type of application
 * data. However, these associated data fields may exist at different locations
 * on the two different tag types.
 * @author Markus Vitalini
 */
public final class TagField {

   /**
    * The length of the data.
    */
   private int length;

   /**
    * The memory bank of the data. Current EPC standard: Bank 0: passwords Bank
    * 1: EPC Bank 2: Tag ID Bank 3: user memory (optional) Memory banks are not
    * known in the HardwareAbstraction. Only bank 2 (id) and bank 3 (user
    * memory) can be handled at the moment
    */
   private int memoryBank;

   /**
    * The name of the tagfield.
    */
   private String name;

   /**
    * The offset of the data.
    */
   private int offset;

   /**
    * The tagfieldname.
    */
   private String tagFieldName;

   /**
    * The instance of the reader device this tag field belongs to.
    */
   private ReaderDevice readerDevice;

   /**
    * Create a TagField object with a given name. If a TagField object with the
    * same name exists, an exception ("ERROR_OBJECT_EXISTS") is thrown.
    * @param name
    *           The name of the tagfield
    * @param readerDevice
    *           The instance of the reader device this tagfield belongs to
    * @return The instance of the created tagfield
    * @throws ReaderProtocolException
    *            "ERROR_OBJECT_EXISTS"
    */
   public static TagField create(final String name,
         final ReaderDevice readerDevice) throws ReaderProtocolException {

      // check if TagField with the same name exists
      try {
         readerDevice.getTagField(name);
      } catch (ReaderProtocolException e) {
         // create new TagField
         TagField newTagField = new TagField(name, readerDevice);
         readerDevice.getTagFields().put(name, newTagField);
         return newTagField;
      }

      throw new ReaderProtocolException("ERROR_OBJECT_EXISTS",
            MessagingConstants.ERROR_OBJECT_EXISTS);

   }

   /**
    * The private constructor of the TagField.
    * @param name
    *           The name of the TagField
    * @param readerDevice
    *           The instance of the reader device this TagField belongs to
    */
   private TagField(final String name, final ReaderDevice readerDevice) {

      this.name = name;
      this.length = 0;
      this.memoryBank = 0;
      this.offset = 0;
      this.tagFieldName = name;
      this.readerDevice = readerDevice;

   }

   /**
    * Returns the name of the given tag field object. This name should not be
    * confused with the TagFieldName attribute of the object. A TagField
    * objects name shall be unique whereas a TagField objects TagFieldName
    * attribute can be shared
    * @return The name of the TagField.
    */
   public String getName() {
      return name;
   }

   /**
    * Returns the tag field name of this TagField object. This TagFieldName
    * attribute is not the same entity as the TagField objects name. Two or
    * more TagField objects can share the same TagFieldName attributes. This may
    * be necessary to associate tag data fields that carry the same type of
    * application data but exist in different locations on different tag types
    * @return The TagFieldName of the TagField
    */
   public String getTagFieldName() {
      return tagFieldName;
   }

   /**
    * Sets the TagFieldName attribute for this TagField object.
    * @param tagFieldName
    *           The TagFieldName to set
    */
   public void setTagFieldName(final String tagFieldName) {
      this.tagFieldName = tagFieldName;
   }

   /**
    * Returns the value corresponding to the TagField objects memory bank
    * attribute. The application of this attribute is dependent upon a tags RF
    * protocol.
    * @return The memory bank
    */
   public int getMemoryBank() {
      return memoryBank;
   }

   /**
    * Sets the memory bank.
    * @param memoryBank
    *           The memory bank to set
    */
   public void setMemoryBank(final int memoryBank) {
      this.memoryBank = memoryBank;
   }

   /**
    * Returns the offset of the data.
    * @return The offset of the data
    */
   public int getOffset() {
      return offset;
   }

   /**
    * Sets the offset of the data.
    * @param offset
    *           The offset of the data to set
    */
   public void setOffset(final int offset) {
      this.offset = offset;
   }

   /**
    * Returns the length of the data.
    * @return The lenght of the data
    */
   public int getLength() {
      return length;
   }

   /**
    * Sets the length of the data.
    * @param length
    *           The lenght of the data
    */
   public void setLength(final int length) {
      this.length = length;
   }

   /**
    * Removes all associations of this TagSelectors.
    */
   protected void removeAssociations() {

      // get all tag selectors which contain this tag field
      Enumeration iterator = readerDevice.getTagSelectors().elements();
      TagSelector cur;
      Hashtable tagSelectorsToRemove = new Hashtable();
      while (iterator.hasMoreElements()) {
         cur = (TagSelector) iterator.nextElement();
         if (cur.getTagField().equals(this)) {

            tagSelectorsToRemove.put(cur.getName(), cur);
            cur.removeAssociations();

         }
      }
      // remove associated tag selectors
      readerDevice.removeTagSelectors((TagSelector[]) readerDevice
            .tagSelectorsToArray(tagSelectorsToRemove));

   }

}
