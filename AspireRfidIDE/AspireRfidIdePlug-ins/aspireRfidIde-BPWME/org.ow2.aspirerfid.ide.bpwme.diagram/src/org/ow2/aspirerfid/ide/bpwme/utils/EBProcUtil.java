package org.ow2.aspirerfid.ide.bpwme.utils;

import java.util.List;

import org.ow2.aspirerfid.commons.xpdl.model.*;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceUtil;

public class EBProcUtil {
	public static void setExtendedAttribute(org.ow2.aspirerfid.commons.apdl.model.EBProc ebProc, 
			String name, String value) {
		ExtendedAttributes eas = ebProc.getExtendedAttributes();
		
		if(eas == null) {
			eas = new ExtendedAttributes();
			ebProc.setExtendedAttributes(eas);
			
		}
		List<ExtendedAttribute> eaList = eas.getExtendedAttribute();
		for (ExtendedAttribute ea : eaList) {
			if(ea.getName().equals(name)) {
				ea.setValue(value);
				MainControl mc = MainControl.getMainControl();
				mc.saveObject();
				return;
			}	
		}
		ExtendedAttribute ea = new ExtendedAttribute();
		ea.setName(name);
		ea.setValue(value);
		eaList.add(ea);
		//write to xml file
		MainControl mc = MainControl.getMainControl();
		mc.saveObject();
	}
	
//	public static int getExtendedAttribute(org.ow2.aspirerfid.commons.apdl.model.EBProc ebProc,
//			String name) {
//		ExtendedAttributes eas = ebProc.getExtendedAttributes();
//		if(eas == null) {
//			eas = new ExtendedAttributes();
//			ebProc.setExtendedAttributes(eas);
//		}
//		
//		List<ExtendedAttribute> eaList = eas.getExtendedAttribute();
//		for (ExtendedAttribute ea : eaList) {
//			if(ea.getName().equals(name)) {
//				String value = ea.getValue();
//				System.out.println(value);
//				return getIndex(value, PreferenceUtil.getAttributes(name));
//			}
//		}
//		
//		return -1;
//	}
	
	
	//return the value of this attribute
	public static String getExtendedAttributeValue(org.ow2.aspirerfid.commons.apdl.model.EBProc ebProc,
			String name) {
		ExtendedAttributes eas = ebProc.getExtendedAttributes();
		if(eas == null) {
			eas = new ExtendedAttributes();
			ebProc.setExtendedAttributes(eas);
		}
		
		List<ExtendedAttribute> eaList = eas.getExtendedAttribute();
		for (ExtendedAttribute ea : eaList) {
			if(ea.getName().equals(name)) {
				return ea.getValue();
			}
		}		
		return null;		
	}
	
	//return the index of the given value, -1 if there is no
	public static int getExtendedAttributeIndex(String value, String[] options) {
		if(options == null) {
			return -1;
		}
		
		for(int i = 0; i < options.length; i++) {
			if(options[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}
	
}
