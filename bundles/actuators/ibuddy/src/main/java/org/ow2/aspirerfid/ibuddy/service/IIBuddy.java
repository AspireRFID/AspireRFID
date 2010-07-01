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

package org.ow2.aspirerfid.ibuddy.service;

import java.io.PrintStream;
import java.util.Map;

/**
 * TODO
 * @author Daniel Lovera and Clément Deschamps and El Mehdi Damou
 */

public interface IIBuddy {

	public static enum HeadColor {NONE, RED, GREEN, YELLOW, BLUE, VIOLET, CYAN, WHITE}
	public static enum WingsState {UP, DOWN}
	public static enum Orientation {LEFT, RIGHT}
	public static enum HeartState {OFF, ON}
	
	public Map<Long,IBuddyDescriptor> getListIbuddy();
	
	public IBuddyDescriptor getIBuddyID(Long i);
	
	public void execute(String command, PrintStream out, PrintStream err, String errMsg);
}