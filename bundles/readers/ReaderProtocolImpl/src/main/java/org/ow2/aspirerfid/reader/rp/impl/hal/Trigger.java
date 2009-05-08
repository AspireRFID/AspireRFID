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

package org.ow2.aspirerfid.reader.rp.impl.hal;


/**
 * A trigger in the HAL is used to trigger identify commands of the reader
 * hardware while a HAL controller instance is performing an asynchronous 
 * identify.
 * 
 * Different trigger types exists:
 * 		CONTINUOUS - continuous identify commands are performed 
 * 
 * 		TIMER - identify commands are performed at certain time intervals
 * 
 * @author Matthias Lampe, lampe@acm.org
 */
public final class Trigger {
	
	//---- Constants --------------------------------------------------------
	
	/** CONTINUOUS Trigger */
	public static final int CONTINUOUS = 1;
	
	/** TIMER Trigger */
	public static final int TIMER = 2;


	//---- Fields -----------------------------------------------------------

	/** The type of the trigger */
	private int type;

	/** The time interval for the TIMER trigger in milliseconds */
	private long interval;


	//---- Constructor ------------------------------------------------------
	
	/**
    * Creates a Triger object with all given parameters. The contructor is private
    * and the factory methods should be used to create triggers.
    *
    * @param type the type of the trigger
    * @param interval the time interval for the TIMER trigger in milliseconds
    */
   private Trigger(int type, long interval) {
	   this.type = type;
	   this.interval = interval;
   }


   //---- Factory Methods ---------------------------------------------------

   /**
    * creates a TIMER trigger.
    * 
    * @param interval the time interval for the TIMER trigger in milliseconds
    * 
    * @return the TIMER trigger.
    */
   public static Trigger createTimerTrigger(long interval) {
	   return new Trigger(Trigger.TIMER, interval);
   }
   

   /**
    * creates a TIMER trigger.
    * 
    * @return the TIMER trigger.
    */
   public static Trigger createContinuousTrigger() {
	   return new Trigger(Trigger.CONTINUOUS, 0);
   }
   
   
   //---- Getter Methods ----------------------------------------------------
   
   /**
    * Gets the type of the trigger.
    * 
    * @return The type of the trigger
    */
   public int getType() {
      return type;
   }

   
   /**
    * Returns the time interval.
    * 
    * @return The time interval of the trigger
    */
   public long getInteral() {
      return interval;
   }
}
