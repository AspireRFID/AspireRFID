/*
 * Copyright (C) 2008-2010, Aspire
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


package org.ow2.aspirerfid.programmableengine.aleclient;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.List;

import org.ow2.aspirerfid.programmableengine.ale.utils.SerializerUtil;
import org.ow2.aspirerfid.programmableengine.ale.wsdl.ale.ALEServicePortType;
import org.ow2.aspirerfid.programmableengine.ale.wsdl.ale.ArrayOfString;
import org.ow2.aspirerfid.programmableengine.ale.wsdl.ale.Define;
import org.ow2.aspirerfid.programmableengine.ale.wsdl.ale.DuplicateNameExceptionResponse;
import org.ow2.aspirerfid.programmableengine.ale.wsdl.ale.DuplicateSubscriptionExceptionResponse;
import org.ow2.aspirerfid.programmableengine.ale.wsdl.ale.ECSpecValidationExceptionResponse;
import org.ow2.aspirerfid.programmableengine.ale.wsdl.ale.ImplementationExceptionResponse;
import org.ow2.aspirerfid.programmableengine.ale.wsdl.ale.InvalidURIExceptionResponse;
import org.ow2.aspirerfid.programmableengine.ale.wsdl.ale.NoSuchNameExceptionResponse;
import org.ow2.aspirerfid.programmableengine.ale.wsdl.ale.SecurityExceptionResponse;
import org.ow2.aspirerfid.programmableengine.ale.wsdl.ale.Subscribe;
import org.ow2.aspirerfid.programmableengine.model.ECReports;
import org.ow2.aspirerfid.programmableengine.model.ECSpec;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;

/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class AleClientUtil {

	/**
	 * ECSpec Configurator Result
	 */
	Object ecSpecConfiguratorResult = null;

	/** logger. */
	public static final Logger LOG = Logger.getLogger(AleClientUtil.class);

	/** ale proxy */
	private ALEServicePortType aleProxy = null;

	/**
	 * Initialize the ALE Server Proxy
	 * 
	 * @param aleClientEndPoint
	 */
	public void initializeAleProxy(String aleClientEndPoint) {
		if (aleClientEndPoint.equals(null) || aleClientEndPoint.equals("")) {
			LOG.error("No end point has been defined!");

			return;
		}
		JaxWsProxyFactoryBean aleProxyfactoryBean = new JaxWsProxyFactoryBean();
		aleProxyfactoryBean.setServiceClass(ALEServicePortType.class);
		aleProxyfactoryBean.setAddress(aleClientEndPoint);
		aleProxy = (ALEServicePortType) aleProxyfactoryBean.create();
	}

	/**
	 * @param ecSpecName
	 * @param ecSpec
	 */
	public void defineECSpec(String ecSpecName, ECSpec ecSpec) {
		if (ecSpecName.equals("") || ecSpecName.equals(null)) {
			LOG.debug("Error! Please spesify an ECSpec Name!");
			return;
		}
		if (ecSpec == null) {
			LOG.error("Please give an ECSpec Object !");
			return;
		}

		// TODO If the ECSpec is already been defined Update it
		
		//Check if the ECSpec Name have not been priorly been used
		List<String> ecSpecNames = getDefinedECSpecNames();
		if (ecSpecNames != null) {
			for (String ecspecName : ecSpecNames) {
				if (ecSpecName.equals(ecspecName)) {
					LOG.warn("The " +ecSpecName+" already exists as LRSpec name ! \n Please choose a diferent one!");
					return;
				}
			}
		}
		
		Define defineParms = new Define();
		defineParms.setSpecName(ecSpecName);
		defineParms.setSpec(ecSpec);

		try {
			ecSpecConfiguratorResult = aleProxy.define(defineParms);
			LOG.info("[Define " +ecSpecName+" method]: The ECSpec was successfully defined!");
		}
		catch (ImplementationExceptionResponse e1) {
			LOG.error("[Define " +ecSpecName+" method]: Implementation Exception was thrown." + e1.getMessage());
			e1.printStackTrace();
			return;
		}
		catch (SecurityExceptionResponse e1) {
			LOG.error("[Define " +ecSpecName+" method]: Security Exception was thrown." + e1.getMessage());
			e1.printStackTrace();
			return;
		}
		catch (ECSpecValidationExceptionResponse e1) {
			LOG.error("[Define " +ecSpecName+" method]: ECSpec Validation Exception was thrown." + e1.getMessage());
			e1.printStackTrace();
			return;
		}
		catch (DuplicateNameExceptionResponse e1) {
			LOG.error("[Define " +ecSpecName+" method]: Duplicate Name Exception was thrown." + e1.getMessage());
			e1.printStackTrace();
			return;
		}

		showECSpecConfiguratorResult(ecSpecConfiguratorResult);

	}

	/**
	 * @param ecSpecName
	 * @param notificationURI
	 */
	public void subscribeECSpec(String ecSpecName, String notificationURI) {

		if (ecSpecName == null || ecSpecName.equals("")) {
			LOG.error("Spec name field should be specified!");
			return;
		}

		if (notificationURI == null || notificationURI.equals("")) {
			LOG.error("Notification URI  field should be specified!");
			return;
		}

		Subscribe subscribeParms = new Subscribe();
		subscribeParms.setSpecName(ecSpecName);
		subscribeParms.setNotificationURI(notificationURI);

		try {

			ecSpecConfiguratorResult = aleProxy.subscribe(subscribeParms);

			LOG.info("[Subscribe " +ecSpecName+" method]: The ECSpec was successfully subscribed!");

		}
		catch (DuplicateSubscriptionExceptionResponse e1) {
			// TODO Auto-generated catch block
			LOG.error("[Subscribe " +ecSpecName+" method]: Duplicate Subscription Exception was thrown." + e1.getMessage());
			e1.printStackTrace();
		}
		catch (InvalidURIExceptionResponse e1) {
			// TODO Auto-generated catch block
			LOG.error("[Subscribe " +ecSpecName+" method]: Invalid URI Exception was thrown." + e1.getMessage());
			e1.printStackTrace();
		}
		catch (NoSuchNameExceptionResponse e1) {
			// TODO Auto-generated catch block
			LOG.error("[Subscribe " +ecSpecName+" method]: No Such Name Exception was thrown." + e1.getMessage());
			e1.printStackTrace();
		}
		catch (ImplementationExceptionResponse e1) {
			// TODO Auto-generated catch block
			LOG.error("[Subscribe " +ecSpecName+" method]: Implementation Exception was thrown." + e1.getMessage());
			e1.printStackTrace();
		}
		catch (SecurityExceptionResponse e1) {
			// TODO Auto-generated catch block
			LOG.error("[Subscribe " +ecSpecName+" method]: Security Exception was thrown." + e1.getMessage());
			e1.printStackTrace();
		}

	}
	
	
	private List<String> getDefinedECSpecNames() {

		List<String> ecSpecNames = null;

		try {
			ecSpecNames = aleProxy.getECSpecNames(new org.ow2.aspirerfid.programmableengine.ale.wsdl.ale.EmptyParms()).getString();
		}
		catch (Exception e) {
			LOG.error( e.getMessage());
		}
		if (ecSpecNames != null && ecSpecNames.size() > 0) {
			return ecSpecNames;
		}
		else {
			return null;

		}
	}
	
	

	/**
	 * This method displays the result in the result text area.
	 * 
	 * @param result
	 *            to display
	 */
	private void showECSpecConfiguratorResult(Object result) {

		if (result instanceof String) {
			LOG.info((String) result);
		}
		else if (result instanceof ArrayOfString) {
			ArrayOfString resultStringArray = (ArrayOfString) result;
			if (resultStringArray.getString().size() == 0) {
				LOG.info("No data found!");
			}
			else {
				for (String s : resultStringArray.getString()) {
					LOG.info(s);
				}
			}
		}
		else if (result instanceof ECSpec) {
			CharArrayWriter writer = new CharArrayWriter();
			try {
				SerializerUtil.serializeECSpec((ECSpec) result, writer);
			}
			catch (IOException e) {
				LOG.error("SerializationException was thrown!" + e.getMessage());
			}
			LOG.info(writer.toString());

		}
		else if (result instanceof ECReports) {
			CharArrayWriter writer = new CharArrayWriter();
			try {
				SerializerUtil.serializeECReports((ECReports) result, writer);
			}
			catch (IOException e) {
				LOG.error("SerializationException was thrown!" + e.getMessage());
			}
			LOG.info(writer.toString());

		}
	}

}
