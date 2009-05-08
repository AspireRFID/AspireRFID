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

import org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction;
import org.ow2.aspirerfid.reader.rp.impl.hal.HardwareException;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.AdministrativeStatus;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.OperationalStatus;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmLevel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.ReadPointOperStatusAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.TTOperationalStatusAlarmControl;

/**
 * This class represents a readpoint. This means a single antenna, a barcode
 * reader, ...
 * @author Markus Vitalini
 */

public class ReadPoint {

   /**
    * The name of the readpoint.
    */
   protected String name;

   /**
    * The reader device this readpoint belongs to.
    */
   protected ReaderDevice readerDevice;

   /**
    * The instance of the HardwareAbstraction which contains the read point.
    */
   protected HardwareAbstraction reader;
   
   
   // ====================================================================
   // ----- Fields added for the reader management implementation ------//
   // ====================================================================
   
   /**
    * A description of this <code>ReadPoint</code>.
    */
   protected String description;
   
   /**
    * The administrative status of this <code>ReadPoint</code>. This
    * represents the host's desired status for this <code>ReadPoint</code>.
    */
   protected AdministrativeStatus adminStatus;
   
   /**
    * The actual status of the <code>ReadPoint</code>.
    */
   protected OperationalStatus operStatus;
   
   /**
    * Controls the conditions for generating alarms alerting a manager of
    * changes in a <code>ReadPoint</code>'s operational status.
    */
   protected TTOperationalStatusAlarmControl operStatusAlarmControl;
   
   /**
    * The number of read point operational state change notifications that have
    * been suppressed for this <code>ReadPoint</code> object.
    */
   protected int operStateSuppressions = 0;
   

   /**
    * The protected constructor of a ReadPoint.
    * @param name
    *           The name of the readpoint
    * @param readerDevice
    *           The reader device the source belongs to
    * @param reader
    *           The HardwareAbstraction which contains the read point
    */
   protected ReadPoint(final String name, final ReaderDevice readerDevice,
         final HardwareAbstraction reader) {

      this.name = name;
      this.readerDevice = readerDevice;
      this.reader = reader;
      
      // initial values
      description = "";
      adminStatus = AdministrativeStatus.UP;
      operStatus = OperationalStatus.UNKNOWN;
      
      operStatusAlarmControl = new TTOperationalStatusAlarmControl(name
				+ "_OperStatusAlarmControl", false, AlarmLevel.ERROR, 0,
				OperationalStatus.ANY, OperationalStatus.ANY);
   }

   /**
    * Returns the name of the ReadPoint object.
    * @return The name of the readpoint
    */
   public String getName() {
      return name;
   }

   /**
    * Returns the instance of the HardwareAbstraction this readpoint belongs to.
    * @return The instance of the HardwareAbstraction this readpoint belongs to
    */
   public HardwareAbstraction getReader() {
      return reader;
   }

   /**
    * Sets the instance of the HardwareAbstraction this readpoint belongs to.
    * @param reader
    *           The instance of the HardwareAbstraction this readpoint belongs
    *           to
    */
   public void setReader(final HardwareAbstraction reader) {
      this.reader = reader;
   }
   
   
   // ====================================================================
   // ----- Methods added for the reader management implementation -----//
   // ====================================================================
   
   /**
    * Returns the class name of the <code>ReadPoint</code> object.
    * 
    * @return The class name of the <code>ReadPoint</code>
    */
   public String getClassName() {
	   return getClass().getName();
   }
   
   /**
    * Returns a description of this <code>ReadPoint</code>. If there has
    * been no previous call to <code>setDescription</code> then the returned
    * string will have 0 length.
    * 
    * @return The readpoint descriptive name
    */
   public String getDescription() {
	   return description;
   }
   
   /**
    * Sets the current <code>ReadPoint</code> description.
    * 
    * @param description
    *            A description for this <code>ReadPoint</code>
    */
   public void setDescription(String description) {
	   this.description = description;
   }
   
   /**
    * Returns the administrative status of this <code>ReadPoint</code>. This
    * represents the host's desired status for this <code>ReadPoint</code>.
    * 
    * @return The administrative status of this <code>ReadPoint</code>
    */
   public AdministrativeStatus getAdminStatus() {
	   return adminStatus;
   }
   
   /**
    * Sets the administrative status of this <code>ReadPoint</code>. This
    * represents the host's desired status for this <code>ReadPoint</code>.
    * 
    * @param administrativeStatus
    *            The desired administrative status of the </code>ReadPoint</code>
    */
   public void setAdminStatus(AdministrativeStatus administrativeStatus) {
	   adminStatus = administrativeStatus;
	   switch (administrativeStatus) {
  		case UP:
  			try {
				reader.startUpReadPoint(name);
			} catch (HardwareException e) {
				// TODO Auto-generated catch block
			}
  			break;
  		case DOWN:
  			try {
				reader.shutDownReadPoint(name);
			} catch (HardwareException e) {
				// TODO Auto-generated catch block
			}
  			break;
	   }
   }
   
   /**
    * Returns the actual status of the <code>ReadPoint</code>.
    * 
    * @return The operational status of the <code>ReadPoint</code>
    */
   public OperationalStatus getOperStatus() {
	   boolean ready = false;
	   try {
		   ready = reader.isReadPointReady(name);
	   } catch (HardwareException e) {
		   // TODO Auto-generated catch block
	   }
	   if (ready) {
		   setOperStatus(OperationalStatus.UP);
		   return operStatus;
	   } else {
		   setOperStatus(OperationalStatus.DOWN);
		   return operStatus;
	   }
   }
   
   /**
    * Returns the alarm control object which controls the conditions for
    * generating alarms alerting a manager of changes in a
    * <code>ReadPoint</code>'s operational status.
    * 
    * @return An alarm control for monitoring the operational status of the
    *         <code>ReadPoint</code>
    */
   public TTOperationalStatusAlarmControl getOperStatusAlarmControl() {
	   return operStatusAlarmControl;
   }
   
   /**
    * Increases the number of read point operational state change notifications
    * that have been suppressed for this <code>ReadPoint</code> object by
    * <code>1</code>.
    */
   public void increaseOperStateSuppressions() {
	   operStateSuppressions++;
   }
   
   /**
    * Returns the number of read point operational state change notifications
    * that have been suppressed for this <code>ReadPoint</code> object.
    * 
    * @return The number of read point operational state change notifications
    *         that have been suppressed for this <code>ReadPoint</code>
    *         object
    */
   public int getOperStateSuppressions() {
	   return operStateSuppressions;
   }
   
   /**
    * Resets the number of read point operational state change notifications
    * that have been suppressed for this <code>ReadPoint</code> object to
    * <code>0</code>.
    */
   public void resetOperStateSuppressions() {
	   operStateSuppressions = 0;
   }
   
   /**
    * Sets the operational status.
    * 
    * @param operStatus
    *            The new operational status
    */
   public void setOperStatus(OperationalStatus operStatus) {
	   if (operStatus != this.operStatus) {
		   readerDevice.getAlarmManager().fireAlarm(
					new ReadPointOperStatusAlarm("ReadPointOperStatusAlarm",
							operStatusAlarmControl.getLevel(), readerDevice,
							this.operStatus, operStatus, name),
					operStatusAlarmControl);
		   this.operStatus = operStatus;
	   }
   }

}
