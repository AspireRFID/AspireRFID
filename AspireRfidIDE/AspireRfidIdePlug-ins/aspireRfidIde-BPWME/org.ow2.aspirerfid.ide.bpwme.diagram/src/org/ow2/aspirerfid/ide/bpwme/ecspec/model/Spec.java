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


import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.views.properties.IPropertySource;
import org.ow2.aspirerfid.commons.ale.model.alelr.LRProperty;
import org.ow2.aspirerfid.commons.ale.model.alelr.LRSpec;
import org.ow2.aspirerfid.commons.ale.model.alelr.LRSpec.Properties;
import org.ow2.aspirerfid.commons.apdl.model.ApdlDataField;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;

/**
 * 
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class Spec implements IWorkbenchAdapter, IAdaptable{
	public enum Type{LLRP, RP, HAL}
	protected ApdlDataField adf;
	protected Type type;
	
	public Spec() {
		this.adf = new ApdlDataField();
	}
	
	public Spec(String name) {
		this();
		adf.setName(name);
		adf.setType("LRSpec");
		adf.setLRSpec(new LRSpec());
		adf.getLRSpec().setProperties(new Properties());
	}
	
	public Spec(ApdlDataField adf) {
		this.adf = adf;
	}
	
	public void setName(String name) {
		adf.setName(name);
//		MainControl mc = MainControl.getMainControl();
//		mc.lrsb.notifyListeners();
	}
	
	public String getName() {
		return adf.getName();
	}
	
	public void addProperty(LRProperty property) {
		adf.getLRSpec().getProperties().getProperty().add(property);
	}
	
	public void removeProperty(String name) {
		Iterator<LRProperty> iter = adf.getLRSpec().getProperties().getProperty().iterator();
		while(iter.hasNext()) {
			LRProperty lrp = (LRProperty) iter.next();
			if(lrp.getName().equals(name)) {
				iter.remove();
			}
		}
	}
	
	public Spec getClone() {
		Spec newSpec = new Spec(adf.getName());
		for(LRProperty lrp:adf.getLRSpec().getProperties().getProperty()) {
			newSpec.adf.getLRSpec().getProperties().getProperty().add(lrp);
		}
		return newSpec;
	}
	
	protected LRProperty cloneLRProperty(LRProperty lrp) {
		LRProperty newLRP = new LRProperty();
		newLRP.setName(lrp.getName());
		newLRP.setValue(lrp.getValue());
		return newLRP;
	}
//	public static Spec createSpec(Type type) {
//		switch(type) {
//		case LLRP:
//			return new LLRPSpec();
//		case RP:
//			return new RPSpec();
//		case HAL:
//			return new HALSpec();
//		}
//		return null;
//	}
	
//	public static Spec createSpec(ApdlDataField adf) {
//		if(adf.getType().equals("LRSpec")) {
//			
//		}
//	}
	
//	public Spec(Spec item) {
//		this.propertyTable = (Hashtable<String, String>) item.propertyTable.clone();
//	}
	public String toString() {
		return type.toString()+":"+getName();
	}
	
	@Override
	public Object[] getChildren(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ImageDescriptor getImageDescriptor(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getLabel(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getParent(Object o) {
		return null;
	}
	
	public ApdlDataField getApdl() {
		return adf;
	}
	@Override
	public Object getAdapter(Class adapter) {
        if (adapter == IWorkbenchAdapter.class)
            return this;
        if (adapter == IPropertySource.class)
            return new SpecProperties(this);
        return null;
	}
	
	//factory pattern for creating specs	
	public static LLRPSpec createLLRP(ApdlDataField adf) {
		return new LLRPSpec(adf);
	}
	
	public static RPSpec createRP(ApdlDataField adf) {
		return new RPSpec(adf);
	}
	
	public static HALSpec createHAL(ApdlDataField adf) {
		return new HALSpec(adf);
	}
}
