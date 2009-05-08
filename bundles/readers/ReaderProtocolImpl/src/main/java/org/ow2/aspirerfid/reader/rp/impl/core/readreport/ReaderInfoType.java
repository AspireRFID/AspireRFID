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

/**
 * The ReaderInfoType of the Reader Protocol. It is a part of the ReadReport
 * @author Markus Vitalini
 */
public class ReaderInfoType {

   /**
    * The EPC of the reader.
    */
   private String epc;

   /**
    * The handle of the reader.
    */
   private int handle;

   /**
    * The role of the reader.
    */
   private String role;

   /**
    * The name of the reader.
    */
   private String name;

   /**
    * The timeTick of the reader.
    */
   private long nowTick;

   /**
    * The UTC time of the reader.
    */
   private Date nowUTC;

   /**
    * The constructor of the ReaderInfoType.
    */
   public ReaderInfoType() {
      this.epc = "";
      this.handle = 0;
      this.name = "";
      this.nowTick = 0;
      this.nowUTC = null;
   }

   /**
    * Returns the EPC of the reader.
    * @return Returns the epc
    */
   public final String getEpc() {
      return epc;
   }

   /**
    * Sets the EPC of the reader.
    * @param epc
    *           The epc to set
    */
   public final void setEpc(final String epc) {
      this.epc = epc;
   }

   /**
    * Returns the handle of the reader.
    * @return Returns the handle
    */
   public final int getHandle() {
      return handle;
   }

   /**
    * Sets the handle of the reader.
    * @param handle
    *           The handle to set
    */
   public final void setHandle(final int handle) {
      this.handle = handle;
   }

   /**
    * Returns the name of the reader.
    * @return Returns the name
    */
   public final String getName() {
      return name;
   }

   /**
    * Sets the name of the reader.
    * @param name
    *           The name to set
    */
   public final void setName(final String name) {
      this.name = name;
   }

   /**
    * Returns the timeTick of the reader.
    * @return Returns the nowTick
    */
   public final long getNowTick() {
      return nowTick;
   }

   /**
    * Sets the timeTick of the reader.
    * @param nowTick
    *           The nowTick to set
    */
   public final void setNowTick(final long nowTick) {
      this.nowTick = nowTick;
   }

   /**
    * Returns the UTC of the reader.
    * @return Returns the nowUTC
    */
   public final Date getNowUTC() {
      return nowUTC;
   }

   /**
    * Sets the UTC of the reader.
    * @param nowUTC
    *           The nowUTC to set
    */
   public final void setNowUTC(final Date nowUTC) {
      this.nowUTC = nowUTC;
   }

   /**
    * Returns the role of the reader.
    * @return Returns the role
    */
   public final String getRole() {
      return role;
   }

   /**
    * Sets the role of the reader.
    * @param role
    *           The role to set
    */
   public final void setRole(final String role) {
      this.role = role;
   }

}
