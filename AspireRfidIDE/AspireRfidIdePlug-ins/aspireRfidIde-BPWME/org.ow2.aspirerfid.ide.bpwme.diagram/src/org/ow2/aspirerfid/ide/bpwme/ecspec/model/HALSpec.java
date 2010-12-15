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
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.Spec.Type;

/**
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class HALSpec extends Spec{
	public HALSpec(String name) {
		super(name);
		type = Type.HAL;
		iniHAL();
	}
	
	public HALSpec(ApdlDataField adf) {
		super(adf);
		type = Type.HAL;
	}
	
	private void iniHAL() {
		LRProperty lrp = new LRProperty();
		lrp.setName("Description");
		lrp.setValue("A HAL Description");
		adf.getLRSpec().getProperties().getProperty().add(lrp);
		
		lrp = new LRProperty();
		lrp.setName("ConnectionPointPort");
		lrp.setValue("5084");
		adf.getLRSpec().getProperties().getProperty().add(lrp);
		
		lrp = new LRProperty();
		lrp.setName("PhysicalReaderSource");
		lrp.setValue("1,2,3");
		adf.getLRSpec().getProperties().getProperty().add(lrp);
		
		lrp = new LRProperty();
		lrp.setName("RoSpecID");
		lrp.setValue("1");
		adf.getLRSpec().getProperties().getProperty().add(lrp);

		lrp = new LRProperty();
		lrp.setName("ConnectionPointAddress");
		lrp.setValue("1.1.1.1");
		adf.getLRSpec().getProperties().getProperty().add(lrp);

	}
	
	public HALSpec getClone() {
		HALSpec newSpec = new HALSpec(this.getName());
		newSpec.adf.getLRSpec().getProperties().getProperty().clear();
		for(LRProperty lrp:adf.getLRSpec().getProperties().getProperty()) {
			newSpec.adf.getLRSpec().getProperties().getProperty().add(cloneLRProperty(lrp));
		}
		return newSpec;
	}

}
