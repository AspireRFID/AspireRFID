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

import org.ow2.aspirerfid.commons.ale.model.alelr.LRProperty;
import org.ow2.aspirerfid.commons.apdl.model.ApdlDataField;
/**
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class RPSpec extends Spec{
	
	public RPSpec(String name) {
		super(name);
		type = Type.RP;
		iniRP();
	}
	
	public RPSpec(ApdlDataField adf) {
		super(adf);
		type = Type.RP;
	}
	
	
	private void iniRP() {
		LRProperty lrp = new LRProperty();
		lrp.setName("Description");
		lrp.setValue("This Logical Reader consists of shelf 1,2,3,4 of the physical reader named AccadaRPSimulator");
		adf.getLRSpec().getProperties().getProperty().add(lrp);
		lrp = new LRProperty();
		lrp.setName("ReaderType");
		lrp.setValue("org.ow2.aspirerfid.ale.server.readers.rp.RPAdaptor");
		adf.getLRSpec().getProperties().getProperty().add(lrp);
	}

	public RPSpec getClone() {
		RPSpec newSpec = new RPSpec(this.getName());
		newSpec.adf.getLRSpec().getProperties().getProperty().clear();
		for(LRProperty lrp:adf.getLRSpec().getProperties().getProperty()) {
			newSpec.adf.getLRSpec().getProperties().getProperty().add(cloneLRProperty(lrp));
		}
		return newSpec;
	}

}
