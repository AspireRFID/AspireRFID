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

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.views.properties.IPropertySource;
import org.ow2.aspirerfid.commons.epcis.model.AttributeType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.SpecProperties;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataUtil;

/**
 * Item is to be shown in master data editor "list"
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class DispositionItem implements IWorkbenchAdapter, IAdaptable{

	private VocabularyElementType vocabularyElement;
	
	public DispositionItem(VocabularyElementType element) {
		vocabularyElement = element;
		String name = getAttribute("urn:epcglobal:epcis:mda:Name");
		if(name == null) {
			addAttribute("urn:epcglobal:epcis:mda:Name", "testValue");
		}
	}
	
	public String getLabel() {
		return vocabularyElement.getId();
	}
	
	public void setName(String name) {
		setAttribute("urn:epcglobal:epcis:mda:Name", name);
		//this.name = name;
	}
	
	public String getName() {
		return getAttribute("urn:epcglobal:epcis:mda:Name");
		//return name;
	}
	
	public void setID(String id) {
		vocabularyElement.setId(id);
	}
	
	
	public String getID() {
		return vocabularyElement.getId();
	}
	
	public DispositionItem getClone() {
		VocabularyElementType newVocabulary = new VocabularyElementType();
		
		newVocabulary.setId(vocabularyElement.getId());
		for(AttributeType attr : vocabularyElement.getAttribute()) {
			AttributeType newAttr = new AttributeType();
			newAttr.setId(attr.getId());
			newAttr.getOtherAttributes().put(new QName("value"), 
					attr.getOtherAttributes().get(new QName("value")));
		}
		//newVocabulary.get
		DispositionItem newDi =  new DispositionItem(newVocabulary);
//		newDi.setName("Copy of " + getName());
//		newDi.setID(getID());
//		return newDi;
		return newDi;
	}

	@Override
	public Object[] getChildren(Object o) {
		return null;
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object object) {
		return null;
	}

	@Override
	public String getLabel(Object o) {
		return null;
	}

	@Override
	public Object getParent(Object o) {
		return null;
	}

	@Override
	public Object getAdapter(Class adapter) {
        if (adapter == IWorkbenchAdapter.class)
            return this;
        if (adapter == IPropertySource.class)
            return new DispositionItemProperties(this);
        return null;
	}
	
	public void addAttribute(String name, String value) {
		MasterDataUtil.setVocabularyElementAttribute(vocabularyElement, name, value);
	}
	
	public void removeAttribute(String name) {
		MasterDataUtil.removeVocabularyElementAttribute(vocabularyElement, name);
	}
	
	public ArrayList<String> getAttributeList() {
		return MasterDataUtil.getAttributeNameList(vocabularyElement);
	}

	public String getAttribute(String name) {
		AttributeType attr =  MasterDataUtil.getVocabularyElementAttribute(vocabularyElement, name);
		if(attr == null) {
			return null;
		}
		String value = attr.getOtherAttributes().get(new QName("value"));
		return value;
	}
	
	public void setAttribute(String name, String value) {
		AttributeType attr =  MasterDataUtil.getVocabularyElementAttribute(vocabularyElement, name);
		if(attr == null) {
			return;
		}else {
			attr.getOtherAttributes().put(new QName("value"), value);
		}
	}
	
	public VocabularyElementType getVocabularyElement() {
		return vocabularyElement;
	}
	
}
