/*
 * Copyright © 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.ide.bpwme.util;

/**
 * Used to get unique id for every diagram model.
 * Now the implementation is simply counter.
 * TODO reopen the diagram may cause the counter not accurate
 * @author Yongming Luo
 *
 */
public class BpwmeAssistant {
	private static int OLCBCounter = 0;
	private static int CLCBCounter = 0;
	private static int EBCounter = 0;
	
	public static String getUniqueOLCBID() {
		return "olcbproc:" + (OLCBCounter ++);
	}
	
	public static String getUniqueCLCBID() {
		return "clcbproc:" + (CLCBCounter ++);
	}
	
	public static String getUniqueEBID() {
		return "ebproc:" + (EBCounter ++);
	}
}
