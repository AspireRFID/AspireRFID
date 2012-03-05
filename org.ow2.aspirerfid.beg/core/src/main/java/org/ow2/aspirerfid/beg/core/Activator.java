package org.ow2.aspirerfid.beg.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.ow2.aspirerfid.commons.ale.model.ale.ECBoundarySpec;

public class Activator implements BundleActivator, ServiceListener  {

	public void serviceChanged(ServiceEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void start(BundleContext arg0) throws Exception {
		ECBoundarySpec eCBoundarySpec = new ECBoundarySpec();
		eCBoundarySpec.setStartTrigger("Triger");
		System.out.println("Hello World"+eCBoundarySpec.getStartTrigger());
		
	}

	public void stop(BundleContext arg0) throws Exception {
		System.out.println("Goodbye World");
		
	}

}
