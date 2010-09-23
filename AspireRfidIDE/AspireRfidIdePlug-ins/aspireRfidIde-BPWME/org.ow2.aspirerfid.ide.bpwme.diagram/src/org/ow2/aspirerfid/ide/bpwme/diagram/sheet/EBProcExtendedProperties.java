package org.ow2.aspirerfid.ide.bpwme.diagram.sheet;

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

public class EBProcExtendedProperties extends PropertySource {

	
	public EBProcExtendedProperties(Object element) {
		super(element);
	}
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor[] propertyDescriptors = new PropertyDescriptor[PreferenceConstants.P_EXTEND.keySet().size()];

		Iterator<String> iter = PreferenceConstants.P_EXTEND.keySet().iterator();
		int i = 0;
		while(iter.hasNext()) {
			String key = iter.next();
			propertyDescriptors[i] = new ComboBoxPropertyDescriptor(key, key, PreferenceUtil.getAttributes(key));
			propertyDescriptors[i].setCategory("Extended Data");
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
		}
		return -1;
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
			mc.saveObject();
		}
	}

}
