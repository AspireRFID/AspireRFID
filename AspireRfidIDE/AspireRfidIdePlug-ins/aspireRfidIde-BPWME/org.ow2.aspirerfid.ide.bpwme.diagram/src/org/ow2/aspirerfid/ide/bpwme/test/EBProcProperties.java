package org.ow2.aspirerfid.ide.bpwme.test;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gmf.runtime.common.ui.services.properties.extended.PropertySource;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.ow2.aspirerfid.commons.epcis.model.EPCISMasterDataDocumentType;
import org.ow2.aspirerfid.commons.epcis.model.VocabularyElementType;
import org.ow2.aspirerfid.ide.bpwme.BpwmePackage;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceUtil;
import org.ow2.aspirerfid.ide.bpwme.impl.EBProcImpl;
import org.ow2.aspirerfid.ide.bpwme.master.utils.MasterDataUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.EBProcUtil;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;

public class EBProcProperties extends PropertySource {
	
	public EBProcProperties(Object element) {
		super(element);
	}
	
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		
		PropertyDescriptor[] propertyDescriptors = new PropertyDescriptor[3 + PreferenceConstants.P_EXTEND.keySet().size()];
		propertyDescriptors[0] = new TextPropertyDescriptor("Id", "Id");
		propertyDescriptors[1] = new TextPropertyDescriptor("Name", "Name");
		propertyDescriptors[2] = new TextPropertyDescriptor("Description", "Description");
		
		for (int i = 0; i < 3; i ++) {
			propertyDescriptors[i].setCategory("General");
		}
		
		Iterator<String> iter = PreferenceConstants.P_EXTEND.keySet().iterator();
		int i = 3;
		while(iter.hasNext()) {
			String key = iter.next();
			propertyDescriptors[i] = new ComboBoxPropertyDescriptor(key, key, PreferenceUtil.getAttributes(key));
			propertyDescriptors[i].setCategory("ExtendedAttributes");
			i++;
		}
		
		return propertyDescriptors;
	}
	
	@Override
	public Object getPropertyValue(Object id) {
		//note that the combobox property has to return the index number
		if(PreferenceConstants.P_EXTEND.containsKey(id)) {
			EBProcImpl epi = (EBProcImpl)getElement();
			MainControl mc = MainControl.getMainControl();
			org.ow2.aspirerfid.commons.apdl.model.EBProc ebproc = 
				(org.ow2.aspirerfid.commons.apdl.model.EBProc) mc.getMapObject(epi.hashCode());
			String value = EBProcUtil.getExtendedAttributeValue(ebproc, (String)id);
			//System.out.println(ebproc);
			if(value != null) {
				return EBProcUtil.getAttributeIndex(value, PreferenceUtil.getAttributes((String)id));
			}else {
				return -1;
			}
		}else {
			EBProcImpl epi = (EBProcImpl)getElement();
			if(id.equals("Id")) {
				return epi.getId();
			}else if(id.equals("Name")) {
				return epi.getName();
			}else if(id.equals("Description")) {
				return epi.getDescription();
			}else {
				System.out.println("Wrong id in get " + id);
				return "fake";
			}
		}
	}
	
	
	@Override
	public void setPropertyValue(Object id, Object value) {
		EBProcImpl epi = (EBProcImpl)getElement();
		MainControl mc = MainControl.getMainControl();
		org.ow2.aspirerfid.commons.apdl.model.EBProc ebproc = 
			(org.ow2.aspirerfid.commons.apdl.model.EBProc) mc.getMapObject(epi.hashCode());
		EPCISMasterDataDocumentType doc = MasterDataUtil.getEPCISMasterDataDocument(ebproc);
		VocabularyElementType vocabularyElement = MasterDataUtil.getEBProcVocabularyElement(doc);

		if(PreferenceConstants.P_EXTEND.containsKey(id)) {//extended attribute
			
			EBProcUtil.setExtendedAttribute(ebproc, (String)id, 
					PreferenceUtil.getAttributes((String)id)[(Integer)value]);
			epi.setFake("fake");
			//epi.setDirty();
		}else {//normal attribute
			if(id.equals("Id")) {
				epi.setId((String)value);
				ebproc.setId((String)value);
				MasterDataUtil.setVocabularyElementID(vocabularyElement, (String)value);
			}else if(id.equals("Name")) {
				epi.setName((String)value);
				ebproc.setName((String)value);
				MasterDataUtil.setVocabularyElementAttribute(vocabularyElement, 
						"urn:epcglobal:epcis:mda:event_name", 
						(String)value);
			}else if(id.equals("Description")) {
				epi.setDescription((String)value);
				ebproc.setDescription((String)value);
			}else {
				System.out.println("Wrong id in set" + id);
			}
		}
	}
	
	@Override
	public boolean isPropertySet(Object id) {
		return true;
	}
	
}
