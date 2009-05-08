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
 * The SourceInfoType of the Reader Protocol. It is a part of the ReadReport
 * @author Markus Vitalini
 */
public class SourceInfoType {

   /**
    * The name of the source.
    */
   private String sourceName;

   /**
    * The frequency of read requests.
    */
   private int sourceFrequency;

   /**
    * The protocol.
    */
   private String sourceProtocol;

   /**
    * The constructor of the SourceInfoType.
    */
   public SourceInfoType() {
      this.sourceName = "";
      this.sourceFrequency = -1;
      this.sourceProtocol = null; // not supported in HAL
   }

   /**
    * Returns the frequency of read requests.
    * @return Returns the sourceFrequency
    */
   public final int getSourceFrequency() {
      return sourceFrequency;
   }

   /**
    * Sets the frequency of the read requests.
    * @param sourceFrequency
    *           The sourceFrequency to set
    */
   public final void setSourceFrequency(final int sourceFrequency) {
      this.sourceFrequency = sourceFrequency;
   }

   /**
    * Returns the name of the source.
    * @return Returns the sourceName
    */
   public final String getSourceName() {
      return sourceName;
   }

   /**
    * Sets the name of the source.
    * @param sourceName
    *           The sourceName to set
    */
   public final void setSourceName(final String sourceName) {
      this.sourceName = sourceName;
   }

   /**
    * Returns the protocol.
    * @return Returns the sourceProtocol
    */
   public final String getSourceProtocol() {
      return sourceProtocol;
   }

   /**
    * Sets the protocol.
    * @param sourceProtocol
    *           The sourceProtocol to set
    */
   public final void setSourceProtocol(final String sourceProtocol) {
      this.sourceProtocol = sourceProtocol;
   }

}
