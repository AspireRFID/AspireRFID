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

package org.ow2.aspirerfid.ide.bpwme.master.model;

import java.util.ArrayList;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataBuilder;

/**
 * Created to make item editable in the properties view
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class DispositionItemProperties implements IPropertySource{
	private DispositionItem dispositionItem;
	
	public DispositionItemProperties(DispositionItem dis) {
		this.dispositionItem = dis;
	}

	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		ArrayList<String> list = dispositionItem.getAttributeList();
		IPropertyDescriptor[] propertyDescriptors = new IPropertyDescriptor[list.size()];
		int i = 0;
		for(String name : list) {
			PropertyDescriptor descriptor = new TextPropertyDescriptor(name, name);
			propertyDescriptors[i] = descriptor;
			i ++;
		}
		return propertyDescriptors;
	}

	@Override
	public Object getPropertyValue(Object id) {
		return dispositionItem.getAttribute((String)id);
	}

	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {		
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		dispositionItem.setAttribute((String)id, (String)value);
		
		MasterDataBuilder mdb = MasterDataBuilder.getInstance();
		mdb.getMasterEditor().setDirty(true);
	}
}
