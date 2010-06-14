/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package org.ow2.aspirerfid.ale.engine.lr;

/**
 * This interface is meant to notify a reading layer of any update of its LRSpec.
 * 
 * @author rdagher
 *
 */
public interface IcomLRPropSetter {
	
	/**
	 * Power index minimum value.
	 */
	public final static int POWER_MIN_VALUE = 1 ;
	
	/**
	 * Power index maximum value.
	 */
	public final static int POWER_MAX_VALUE = 5 ;
	
	/**
	 * Session parameter minimum value.
	 */
	public final static int SESSION_MIN_VALUE = 0 ;
	
	/**
	 * Session parameter maximum value.
	 */
	public final static int SESSION_MAX_VALUE = 3 ;
	
	/**
	 * InitialQ parameter minimum value.
	 */
	public final static int INITIALQ_MIN_VALUE = 0 ;
	
	/**
	 * InitialQ parameter maximum value.
	 */
	public final static int INITIALQ_MAX_VALUE = 15 ;
	
	/**
	 * Set the Reader's RF power.
	 * @param power from 1 to 5.
	 * @param mapping the power mapping (an array of 5 elements) containing the values to set on the reader.
	 * 		  The values are reader dependent.
	 * 
	 * @see 
	 */
	public void setPower(int power, int[] mapping);
	
	/**
	 * Set the session.
	 * @param session 0 or 1.
	 */
	public void setSession(int session);
	
	/**
	 * Initial Q setter.
	 * @param initialQ used to lead the tree of reading of tags. 0 to 15.
	 */
	public void setInitialQ(int initialQ);
}
