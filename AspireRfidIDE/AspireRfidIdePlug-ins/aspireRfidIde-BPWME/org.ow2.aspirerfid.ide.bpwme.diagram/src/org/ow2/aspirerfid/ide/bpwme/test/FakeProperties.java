package org.ow2.aspirerfid.ide.bpwme.test;

import org.eclipse.gmf.runtime.common.ui.services.properties.extended.PropertySource;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class FakeProperties extends PropertySource {
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		IPropertyDescriptor[] propertyDescriptors = new IPropertyDescriptor[1];
		propertyDescriptors[0] = new TextPropertyDescriptor("fake", "fake");
		return propertyDescriptors;
	}
	
	@Override
	public Object getPropertyValue(Object id) {
		return "fake";
	}
}
