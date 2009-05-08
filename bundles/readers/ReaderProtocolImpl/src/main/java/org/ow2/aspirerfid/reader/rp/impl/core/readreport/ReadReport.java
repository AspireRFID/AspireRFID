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

/**
 * The ReadReport. It contains information about the reader device, sources and
 * triggers.
 * @author Markus Vitalini
 */
public class ReadReport {

   /**
    * Information about the reader.
    */
   private ReaderInfoType readerInfo;

   /**
    * Information about the notification channel.
    */
   private NotificationInfoType notificationInfo;

   /**
    * The list of source reports (name of the source, sourceReport).
    */
   private Hashtable sourceReports;

   /**
    * The construcor of the ReadReport.
    */
   public ReadReport() {
      readerInfo = null;
      notificationInfo = null;
      sourceReports = new Hashtable();
   }

   /**
    * Checks if the report contains information about the reader.
    * @return 'true' if the report contains information about the reader,
    *         'false' otherwise
    */
   public final boolean containsReaderInfo() {
      if (readerInfo != null) {
         return true;
      }
      return false;

   }

   /**
    * Returns the information of the reader.
    * @return Returns the information of the reader
    */
   public final ReaderInfoType getReaderInfo() {
      return readerInfo;
   }

   /**
    * Sets the information about the reader.
    * @param readerInfo
    *           The information of the reader to set
    */
   public final void setReaderInfo(final ReaderInfoType readerInfo) {
      this.readerInfo = readerInfo;
   }

   /**
    * Checks if the report contains information about a notification channel.
    * @return 'true' if the report contains information about the notification
    *         channel, 'false' otherwise
    */
   public final boolean containsNotificationInfo() {
      if (notificationInfo != null) {
         return true;
      }
      return false;

   }

   /**
    * Returns information about the notification channel.
    * @return Returns information about the notification channel
    */
   public final NotificationInfoType getNotificationInfo() {
      return notificationInfo;
   }

   /**
    * Sets information about the notification channel.
    * @param notificationInfo
    *           The information of the notification channel to set
    */
   public final void setNotificationInfo(
         final NotificationInfoType notificationInfo) {
      this.notificationInfo = notificationInfo;
   }

   /**
    * Adds a source report to the list of source reports.
    * @param sourceReport
    *           The source report to add
    */
   public final void addSourceReport(final SourceReport sourceReport) {
      sourceReports.put(sourceReport.getSourceInfo().getSourceName(),
            sourceReport);
   }

   /**
    * Sets the sourceReports.
    * @param sourceReports
    *           The source reports to set
    */
   public final void setSourceReports(final Hashtable sourceReports) {
      this.sourceReports = sourceReports;
   }

   /**
    * Gets all sourceReports.
    * @return The source reports
    */
   public final Hashtable getSourceReports() {
      return sourceReports;
   }

   /**
    * Returns all tags in the report.
    * @return A list of tags
    */
   public final Hashtable getAllTags() {

      Hashtable tags = new Hashtable();

      Enumeration iterator = sourceReports.elements();
      SourceReport report;

      while (iterator.hasMoreElements()) {
         report = (SourceReport) iterator.nextElement();

         tags.putAll(report.getAllTags());

      }

      return tags;

   }

}
