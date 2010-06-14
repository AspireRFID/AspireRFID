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

package org.ow2.aspirerfid.ale.epc.reports;

/**
 * An <code>ECSightingStat</code> contains information about a single sighting of a Tag by 
 * a particular Reader. An ALE implementation MAY use a subclass of this type to provide 
 * information about a single sighting of a Tag. <br>
 * For example, a subclass of this type might provide the timestamp for this sighting, the 
 * received signal strength (for an RFID Tag), etc.
 * <br>
 * <br>
 * Note that ECSightingStat is used both by the Reading API and the Writing API. Unless otherwise noted, the interpretation of an ECSightingStat instance is the same in both APIs.
 * 
 * @author pops
 * @author R.DAGHER
 */
public abstract class ECSightingStat {
		
}
