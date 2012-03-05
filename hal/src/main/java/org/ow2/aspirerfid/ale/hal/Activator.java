package org.ow2.aspirerfid.ale.hal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

public class Activator implements BundleActivator, ServiceListener  {

	public void serviceChanged(ServiceEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void start(BundleContext arg0) throws Exception {
		System.out.println("Hello World");
		
	}

	public void stop(BundleContext arg0) throws Exception {
		System.out.println("Goodbye World");
		
	}

}
