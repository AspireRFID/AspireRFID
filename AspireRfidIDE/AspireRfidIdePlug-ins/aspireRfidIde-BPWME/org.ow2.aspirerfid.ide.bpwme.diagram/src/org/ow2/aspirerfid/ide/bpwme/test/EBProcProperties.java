package org.ow2.aspirerfid.ide.bpwme.test;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.gmf.runtime.common.ui.services.properties.extended.PropertySource;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceUtil;

public class EBProcProperties extends PropertySource {
	
	public EBProcProperties(Object element) {
		super(element);
	}
	
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		
		IPropertyDescriptor[] propertyDescriptors = new IPropertyDescriptor[3 + PreferenceConstants.P_EXTEND.keySet().size()];
		propertyDescriptors[0] = new TextPropertyDescriptor("Id", "Id");
		propertyDescriptors[1] = new TextPropertyDescriptor("Name", "Name");
		propertyDescriptors[2] = new TextPropertyDescriptor("Description", "Description");
		
		Iterator<String> iter = PreferenceConstants.P_EXTEND.keySet().iterator();
		int i = 3;
		while(iter.hasNext()) {
			String key = iter.next();
			propertyDescriptors[i] = 
				new ComboBoxPropertyDescriptor(key, key, PreferenceUtil.getAttributes(key));
			i++;
		}
		
		
		
		return propertyDescriptors;
	}
	
	@Override
	public Object getPropertyValue(Object id) {
		//note that the combobox property has to return the index number
		if(PreferenceConstants.P_EXTEND.containsKey(id)) {
			return 0;
		}
		return "fake";
	}
	
	
	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		super.setPropertyValue(id, value);
	}
}
