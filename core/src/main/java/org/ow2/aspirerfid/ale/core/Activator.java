package org.ow2.aspirerfid.ale.core;


import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
//import org.osgi.framework.ServiceEvent;
//import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceRegistration;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.ALELRServicePortType;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.ALEServicePortType;
import org.ow2.aspirerfid.ale.core.impl.ALELRServiceImpl;
import org.ow2.aspirerfid.ale.core.impl.ALEServiceImpl;
//import org.slf4j.Marker;
//import org.slf4j.MarkerFactory;
//import org.slf4j.helpers.*;
//import org.slf4j.impl.*;
//import org.slf4j.spi.*;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import ch.qos.logback.classic.LoggerContext; 
import ch.qos.logback.core.util.StatusPrinter;


//import org.osgi.service.log.*;

import ch.qos.logback.classic.BasicConfigurator;


//import org.ow2.aspirerfid.commons.ale.model.ale.ECBoundarySpec;

//import org.ow2.aspirerfid.commons.ale.wsdl.alelr.ALELRService;
//import org.ow2.aspirerfid.commons.ale.wsdl.ale.ALEService;

public class Activator implements BundleActivator { // ServiceListener
	private ServiceRegistration helloWorldRegistration;
	private ServiceRegistration aleServiceRegistration;
	private ServiceRegistration aleLrServiceRegistration;
	
	

	// public void serviceChanged(ServiceEvent serviceEvent) {
	// // TODO Auto-generated method stub
	//
	// }


	
	
	public void start(BundleContext bundleContext) throws Exception {
		
		    //Initialize the Logger
		    Logger logger = LoggerFactory.getLogger(Activator.class.getName()); 
		    // print Logger's internal state 
		    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory(); 
		    StatusPrinter.print(lc);

		

		// ===================Initialize ALE and ALELR Services ===========
		Dictionary<String, String> aleDictionaryProps = new Hashtable<String, String>();
		String aleServiceURL = bundleContext.getProperty("org.ow2.aspirerfid.ale.core.aleServiceURL");

		aleDictionaryProps.put("service.exported.interfaces", "*");
		aleDictionaryProps.put("service.exported.configs", "org.apache.cxf.ws");
		aleDictionaryProps.put("org.apache.cxf.ws.address", aleServiceURL);
		

		aleServiceRegistration = bundleContext.registerService(ALEServicePortType.class.getName(),
				new ALEServiceImpl(), aleDictionaryProps);
		
		System.out.println("#########Service listens to : "+aleServiceURL+"  #########");

		// ===================Initialize ALE and ALELR Services ===========

		Dictionary<String, String> aleLrDictionaryProps = new Hashtable<String, String>();
		String aleLrServiceURL = bundleContext.getProperty("org.ow2.aspirerfid.ale.core.aleLrServiceURL");

		aleLrDictionaryProps.put("service.exported.interfaces", "*");
		aleLrDictionaryProps.put("service.exported.configs", "org.apache.cxf.ws");
		aleLrDictionaryProps.put("org.apache.cxf.ws.address", aleLrServiceURL);
		
		aleLrServiceRegistration = bundleContext.registerService(ALELRServicePortType.class.getName(),
				new ALELRServiceImpl(), aleLrDictionaryProps);
		
		System.out.println("#########Service listens to : "+aleLrServiceURL+"  #########");

		
		
		// ===================Initialize ALE and ALELR Services ===========

		Dictionary<String, String> helloWorldDictionaryProps = new Hashtable<String, String>();


		helloWorldDictionaryProps.put("service.exported.interfaces", "*");
		helloWorldDictionaryProps.put("service.exported.configs", "org.apache.cxf.ws");
		helloWorldDictionaryProps.put("org.apache.cxf.ws.address", "http://localhost:9090/HelloWorld");
		
		helloWorldRegistration = bundleContext.registerService(HelloWorldService.class.getName(),
				new HelloWorldServiceImpl(), helloWorldDictionaryProps);
		
		System.out.println("#########Service listens to : http://localhost:9090/HelloWorld  #########");
		
		
		
	    logger.debug("Hello world by Logger. From : {}",Activator.class.getName()); 

	}

	public void stop(BundleContext arg0) throws Exception {
		System.out.println("Goodbye World");
		aleServiceRegistration.unregister();
		aleLrServiceRegistration.unregister();
		helloWorldRegistration.unregister();

	}

}
