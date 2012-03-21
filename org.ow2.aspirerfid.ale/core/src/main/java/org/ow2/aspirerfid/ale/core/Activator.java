package org.ow2.aspirerfid.ale.core;


//import java.util.Dictionary;
//import java.util.Hashtable;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
//import org.osgi.framework.ServiceEvent;
//import org.osgi.framework.ServiceListener;
//import org.osgi.framework.ServiceRegistration;



import org.ow2.aspirerfid.commons.ale.wsdl.alelr.ALELRServicePortType;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.ALEServicePortType;
import org.ow2.aspirerfid.ale.core.impl.ALELRServicePortTypeImpl;
import org.ow2.aspirerfid.ale.core.impl.ALEServicePortTypeImpl;


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

//	private ServiceRegistration aleServiceRegistration;
//	private ServiceRegistration aleLrServiceRegistration;

	
    final private static String PROPERTY_BASE_NAME = "org.apache.cxf.ws";
	

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

		    logger.debug("Hello world by Logger. From : {}",Activator.class.getName());

		// ===================Initialize ALE Service using DOSGI CXF JAX-WS frontend ===========
//		Dictionary aleDictionaryProps = new Hashtable();
		String aleServiceURL = bundleContext.getProperty("org.ow2.aspirerfid.ale.core.aleServiceURL");
//
//		
//		aleDictionaryProps.put("service.exported.interfaces", "org.ow2.aspirerfid.commons.ale.wsdl.ale.ALEServicePortType");
//		aleDictionaryProps.put("service.exported.configs", "org.apache.cxf.ws");
//		aleDictionaryProps.put("org.apache.cxf.ws.address", aleServiceURL);
//		
//		aleDictionaryProps.put(PROPERTY_BASE_NAME + ".frontend", "jaxws");
//		aleDictionaryProps.put("org.apache.cxf.ws.databinding","jaxb");
////		aleDictionaryProps.put(PROPERTY_BASE_NAME + ".service.ns", "http://ale.wsdl.ale.commons.aspirerfid.ow2.org"); // service namespace 
//		aleDictionaryProps.put(PROPERTY_BASE_NAME + ".service.name", "ALEService"); // service name
//		aleDictionaryProps.put(PROPERTY_BASE_NAME + ".port.name", "ALEServicePort"); // port/endpoint name 
//		aleDictionaryProps.put(PROPERTY_BASE_NAME + ".location", "epcglobal/ale/EPCglobal-ale-1_1-ale.wsdl");

//		aleServiceRegistration = bundleContext.registerService(ALEServicePortType.class.getName(),
//				new ALEServicePortTypeImpl(), aleDictionaryProps);
		
		System.out.println("#########Service listens to : "+aleServiceURL+"  #########");

		
		
		
//		===================================Embedded CXF  JAX-WS Frontend==========================
		
		
		Object aleServicePortTypeImplimentor = new ALEServicePortTypeImpl();
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(ALEServicePortType.class);
		svrFactory.setAddress(aleServiceURL);
		svrFactory.setServiceBean(aleServicePortTypeImplimentor);
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		svrFactory.create();

		
		
		
		
		
		
		// ===================Initialize ALELR Service (using CXF 'simple' frontend) ===========

//		Dictionary aleLrDictionaryProps = new Hashtable();
//		String aleLrServiceURL = bundleContext.getProperty("org.ow2.aspirerfid.ale.core.aleLrServiceURL");
//
//		aleLrDictionaryProps.put("service.exported.interfaces", "*");
//		aleLrDictionaryProps.put("service.exported.configs", "org.apache.cxf.ws");
//		aleLrDictionaryProps.put("org.apache.cxf.ws.address", aleLrServiceURL);
//		
//		aleLrServiceRegistration = bundleContext.registerService(ALELRServicePortType.class.getName(),
//				new ALELRServicePortTypeImpl(), aleLrDictionaryProps);
//		
//		System.out.println("#########Service listens to : "+aleLrServiceURL+"  #########");

		
		
		
		
	    

	}

	public void stop(BundleContext arg0) throws Exception {
		System.out.println("Goodbye World");
//		aleServiceRegistration.unregister();
//		aleLrServiceRegistration.unregister();


	}

}
