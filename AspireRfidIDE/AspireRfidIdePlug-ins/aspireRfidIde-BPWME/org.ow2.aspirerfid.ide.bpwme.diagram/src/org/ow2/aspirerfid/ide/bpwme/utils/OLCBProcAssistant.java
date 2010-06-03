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

package org.ow2.aspirerfid.ide.bpwme.utils;

import org.ow2.aspirerfid.commons.apdl.model.CLCBProc;
import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.commons.apdl.model.OLCBProc;


/**
 * A class for retrieving olcb, clcb and ebproc.
 * Model from main memory by id.
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class OLCBProcAssistant {
	private OLCBProc olcb;
	
	/**
	 * Constructor, bind the assistant with an existing olcb
	 * @param olcb - the root OLCB Process
	 */
	public OLCBProcAssistant(OLCBProc olcb) {
		this.olcb = olcb;
	}
	
	/**
	 * Get the specific CLCB Proc by id
	 * id should be unique in system
	 * @param id
	 * @return CLCB Proc with the specific id
	 */
	public CLCBProc getCLCB(String id) {
		for (CLCBProc clcb : olcb.getCLCBProc()) {
			if(clcb.getId().equals(id)) {
				return clcb;
			}
		}
		return null;
	}
	
	/**
	 * Get the specific EBProc by id and its parent CLCBProc
	 * id should be unique in system
	 * the parent CLCBProc is used to accelerate search
	 * @param clcb
	 * @param id
	 * @return
	 */
	public EBProc getEBProc(CLCBProc clcb, String id) {
		for (EBProc ebp : clcb.getEBProc()) {
			if(ebp.getId().equals(id)) {
				return ebp;
			}
		}
		return null;
	}
}
