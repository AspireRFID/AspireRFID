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

package org.ow2.aspirerfid.ide.bpwme.ecspec.model;

/**
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class ExtraProperty {
	public String name;
	int type;
	public static final int LLRP_TYPE = 0;
	public static final int RP_TYPE = 1;
	public static final int HAL_TYPE = 2;
	private static final int TYPE_MIN = LLRP_TYPE;
	private static final int TYPE_MAX = HAL_TYPE;
	
	public ExtraProperty(String name, int type) {
		this.name = name;
		if((type < TYPE_MIN) | (type > TYPE_MAX)) {
			System.out.println("Type Def Out of Range");
			return;
		}
		this.type = type;
	}
	
	public String toString() {
		return name;
	}
	
}
