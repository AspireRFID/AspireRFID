/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.ons.webservice.beans;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import junit.framework.TestCase;

import org.ow2.aspirerfid.ons.webservice.util.JNDIReferenceResolver;

/**
 * TODO Javadoc
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class DatabaseTests extends TestCase {
	Context initialContext;

	protected void setUp() throws Exception {
		super.setUp();

		Properties env = new Properties();
		// For JOnAS
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
		// For JBoss
		// env.put(Context.INITIAL_CONTEXT_FACTORY,
		// "org.jnp.interfaces.NamingContextFactory");

		// initialize the context
		initialContext = new InitialContext(env);
	}

	/**
	 * Tests EPC IS service URL addition and deletion
	 */
	public void test_01() {
		try {
			ONSServiceRemote serviceRemote = (ONSServiceRemote) initialContext
					.lookup(JNDIReferenceResolver.getRemoteJNDIReference(ONSServiceRemote.class,
							ONSServiceBean.class));

			serviceRemote.removeAllEPCISServices();

			int epcManagerNumber = 6543210;
			String serviceUrl = "http://localhost:9000/epcis/TagService/TagServicePort?JWSDL";
			String description = "none";

			serviceRemote.addEPCISService(epcManagerNumber, serviceUrl, description);

			String epcisServiceURL = serviceRemote
					.getEPCISServiceURL("urn:epc:id:gid:6543210.123456.123456789");
			assertEquals(epcisServiceURL, serviceUrl);

			serviceRemote.removeEPCISService(epcManagerNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests EPC IS service History addition and deletion
	 */
	public void test_02() {
		try {
			ONSServiceRemote serviceRemote = (ONSServiceRemote) initialContext
					.lookup(JNDIReferenceResolver.getRemoteJNDIReference(ONSServiceRemote.class,
							ONSServiceBean.class));

			String tag1 = "urn:epc:id:gid:6543210.123456.123456789"; 
			String tag2 = "urn:epc:id:gid:6543210.123456.12345678a"; 
			String tags = tag1 + ","+ tag2;
			String serviceUrl = "http://127.0.0.1:9000/epcis/TagService/TagServicePort?JWSDL";

			serviceRemote.removeAllEPCISServicesHistories();

			serviceRemote.registerEPCISServiceHistory(tags, serviceUrl);
			
			String[] serviceUrls = ((String) serviceRemote.getEPCISServicesHistory(tag1)).split(",");
			assertEquals(serviceUrls[0], serviceUrl);
			assertEquals(1, serviceUrls.length);
			
			serviceUrl = "http://127.0.0.1:8000/epcis/TagService/TagServicePort?JWSDL";
			serviceRemote.registerEPCISServiceHistory(tags, serviceUrl);

			serviceUrls = ((String) serviceRemote.getEPCISServicesHistory(tag2)).split(",");
			assertEquals(serviceUrls[1], serviceUrl);
			assertEquals(2, serviceUrls.length);

			serviceRemote.removeAllEPCISServicesHistories();
			serviceUrls = ((String) serviceRemote.getEPCISServicesHistory(tag1)).split(",");
			assertEquals(1, serviceUrls.length);
			assertEquals("", serviceUrls[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests EPC IS service history duplicate addition
	 */
	public void test_03() {
		try {
			ONSServiceRemote serviceRemote = (ONSServiceRemote) initialContext
					.lookup(JNDIReferenceResolver.getRemoteJNDIReference(ONSServiceRemote.class,
							ONSServiceBean.class));

			String tag = "urn:epc:id:gid:6543210.123456.123456789";
			String serviceUrl = "http://127.0.0.1:9000/epcis/TagService/TagServicePort?JWSDL";

			serviceRemote.removeAllEPCISServicesHistories();

			serviceRemote.registerEPCISServiceHistory(tag, serviceUrl);
			serviceRemote.registerEPCISServiceHistory(tag, serviceUrl);

			String[] serviceUrls = ((String) serviceRemote.getEPCISServicesHistory(tag)).split(",");
			assertEquals(serviceUrls.length, 1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests EPC IS service URL getting with empty result
	 */
	public void test_04() {
		try {
			ONSServiceRemote serviceRemote = (ONSServiceRemote) initialContext
					.lookup(JNDIReferenceResolver.getRemoteJNDIReference(ONSServiceRemote.class,
							ONSServiceBean.class));

			serviceRemote.removeAllEPCISServices();

			String epcisServiceURL = serviceRemote
					.getEPCISServiceURL("urn:epc:id:gid:9543210.123456.123456789");
			assertEquals(epcisServiceURL, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests EPC IS service history getting with empty result
	 */
	public void test_05() {
		try {
			ONSServiceRemote serviceRemote = (ONSServiceRemote) initialContext
			 		.lookup(JNDIReferenceResolver.getRemoteJNDIReference(ONSServiceRemote.class,
							ONSServiceBean.class));

			String tag = "urn:epc:id:gid:6543210.123456.123456789";

			serviceRemote.removeAllEPCISServicesHistories();

			String[] serviceUrls = ((String) serviceRemote.getEPCISServicesHistory(tag)).split(",");
			assertEquals(serviceUrls.length, 1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
