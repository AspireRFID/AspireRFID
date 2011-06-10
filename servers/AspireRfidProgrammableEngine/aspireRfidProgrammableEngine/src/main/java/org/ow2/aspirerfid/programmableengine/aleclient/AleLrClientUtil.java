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

import org.ow2.aspirerfid.commons.ale.utils.SerializerUtil;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.ALELRServicePortType;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.ValidationExceptionResponse;
import org.ow2.aspirerfid.commons.ale.model.alelr.LRSpec;
//import org.ow2.aspirerfid.commons.apdl.model.LRSpec;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;


/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class AleLrClientUtil {

	/** logger. */
	public static final Logger LOG = Logger.getLogger(AleLrClientUtil.class);
	
	/** ale proxy */
	private ALELRServicePortType alelrProxy = null;
	
	
	/**
	 * LRSpec Configurator Result
	 */
	Object lrSpecConfiguratorResult = null;
	
	
	
	
	
	
	
	public void initializeAleLrProxy(String aleLrClientEndPoint) {
		JaxWsProxyFactoryBean lrProxyfactoryBean = new JaxWsProxyFactoryBean();
		lrProxyfactoryBean.setServiceClass(ALELRServicePortType.class);
		if (aleLrClientEndPoint.equals(null) || aleLrClientEndPoint.equals("")) {
			LOG.error("No end point has been defined!");
			return;
		}
		lrProxyfactoryBean.setAddress(aleLrClientEndPoint);
		alelrProxy = (ALELRServicePortType) lrProxyfactoryBean.create();
	}
	
	
	
	public void defineLRSpec(String logicalReadeName, LRSpec lrSpec){
		
		List<String> lrSpecNames = getDefinedLRSpecNames();

		// TODO If the reader is already been defined Update it
		
		//Check if this Logical reader name is already defined
		if (lrSpecNames != null) {
			for (String lrspecName : lrSpecNames) {
				if (logicalReadeName.equals(lrspecName)) {
					LOG.warn("The " +logicalReadeName+" already exists as LRSpec name ! \n Please choose a diferent one!");
					return;
				}
			}
		}

		// ==================================

		if (logicalReadeName.equals("")) {
			LOG.error("Please spesify an LRSpec Name!");
			return;
		}

		//By default the readers Described in an APDL and defined at the PE are not Composite. 
		lrSpec.setIsComposite(false);
		
		org.ow2.aspirerfid.commons.ale.wsdl.alelr.Define defineParms = new org.ow2.aspirerfid.commons.ale.wsdl.alelr.Define();
		defineParms.setName(logicalReadeName);
		defineParms.setSpec(lrSpec);

		try {
			lrSpecConfiguratorResult = alelrProxy.define(defineParms);
			LOG.debug("[LRSpec Define method]: The "+logicalReadeName+" LRSpec was successfully defined!");
		} catch (org.ow2.aspirerfid.commons.ale.wsdl.alelr.SecurityExceptionResponse e1) {
			// TODO Auto-generated catch block
			LOG.error("[LRSpec Define method]: Security Exception was thrown.");
			e1.printStackTrace();
		} catch (org.ow2.aspirerfid.commons.ale.wsdl.alelr.ImplementationExceptionResponse e1) {
			// TODO Auto-generated catch block
			LOG.error("[LRSpec Define method]: Implementation Exception was thrown.");
			e1.printStackTrace();
		} catch (org.ow2.aspirerfid.commons.ale.wsdl.alelr.DuplicateNameExceptionResponse e1) {
			// TODO Auto-generated catch block
			LOG.error("[LRSpec Define method]: Duplicate Name Exception was thrown.");
			e1.printStackTrace();
		} catch (ValidationExceptionResponse e1) {
			// TODO Auto-generated catch block
			LOG.error("[LRSpec Define method]: Validation Exception was thrown.");
			e1.printStackTrace();
		}

		showLRSpecConfiguratorResult(lrSpecConfiguratorResult);
		
	}
	

	
	
	private List<String> getDefinedLRSpecNames() {

		List<String> lrSpecNames = null;

		try {
			lrSpecNames = alelrProxy.getLogicalReaderNames(new org.ow2.aspirerfid.commons.ale.wsdl.alelr.EmptyParms()).getString();
		}
		catch (Exception e) {
			LOG.error( e.getMessage());
		}
		if (lrSpecNames != null && lrSpecNames.size() > 0) {
			return lrSpecNames;
		}
		else {
			return null;

		}
	}
	
	private void showLRSpecConfiguratorResult(Object result) {

		if (result instanceof String) {
			LOG.info((String) result);

		}
		else if (result instanceof org.ow2.aspirerfid.commons.ale.wsdl.alelr.ArrayOfString) {
			org.ow2.aspirerfid.commons.ale.wsdl.alelr.ArrayOfString resultStringArray = (org.ow2.aspirerfid.commons.ale.wsdl.alelr.ArrayOfString) result;
			if (resultStringArray.getString().size() == 0) {
				LOG.info("No data found!");
			}
			else {
				for (String s : resultStringArray.getString()) {
					LOG.info(s);
				}
			}
		}
		else if (result instanceof LRSpec) {
			CharArrayWriter writer = new CharArrayWriter();
			try {
				SerializerUtil.serializeLRSpec((LRSpec) result, writer);
			}
			catch (IOException e) {
				LOG.error("SerializationException was thrown."+ e.getMessage());
			}
			LOG.info(writer.toString());

		}
	}
	
	
	
}
