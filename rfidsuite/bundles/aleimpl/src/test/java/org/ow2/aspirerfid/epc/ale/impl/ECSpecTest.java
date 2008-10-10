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
package org.ow2.aspirerfid.epc.ale.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;

import org.ow2.aspirerfid.epc.ale.api.ECReportSpec;
import org.ow2.aspirerfid.epc.ale.api.ECSpec;
import org.ow2.aspirerfid.epc.ale.impl.ECReportSpecImpl;
import org.ow2.aspirerfid.epc.ale.impl.ECSpecImpl;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel
 * @versino 2007
 */
public class ECSpecTest extends TestCase {

	private ECSpec ecSpec;

	protected void setUp() throws Exception {
		super.setUp();
		ecSpec = new ECSpecImpl();
	}

	/**
	 * Tests a valid ECSpec
	 */
	public void test_01() {
		ecSpec.addLogicalReaderName("readerName");
		ecSpec.setGatewayName("gateway name");

		ECReportSpec ecReportSpec = new ECReportSpecImpl();
		ecReportSpec.setIncludeEPC(true);

		ecSpec.addReportSpec(ecReportSpec);
		ecSpec.setEventTopic("DETECTION");
		try {
			ecSpec.setStopTrigger(new URI("uri://sdf"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		System.err.println(ecSpec.getValidationMessage());
		assertTrue(ecSpec.getValidationMessage() == null);
	}

	/**
	 * Tests an invalid ECSpec : no ECReportSpec defined
	 */
	public void test_02() {
		ecSpec.addLogicalReaderName("readerName");
		ecSpec.setGatewayName("gateway name");

		ecSpec.addReportSpec(null);
		ecSpec.setEventTopic("DETECTION");
		ecSpec.setDuration(10000);
		ecSpec.setStableSetInterval(2000);
		try {
			ecSpec.setStopTrigger(new URI("uri://sdf"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		System.err.println(ecSpec.getValidationMessage());
		assertTrue(ecSpec.getValidationMessage() != null);
	}

	/**
	 * Tests an invalid ECSpec : no ECReportSpec defined
	 */
	public void test_03() {
		ecSpec.addLogicalReaderName("readerName");
		ecSpec.setGatewayName("gateway name");

		Set set = new TreeSet();
		set.add(null);

		ecSpec.addReportSpecs(set);
		ecSpec.setEventTopic("DETECTION");
		ecSpec.setDuration(10000);
		ecSpec.setStableSetInterval(2000);
		try {
			ecSpec.setStopTrigger(new URI("uri://sdf"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		System.err.println(ecSpec.getValidationMessage());
		assertTrue(ecSpec.getValidationMessage() != null);
	}

	/**
	 * Tests valid ECSpec with a list of ECReportSpec Defined
	 */
	public void test_04() {
		ecSpec.addLogicalReaderName("readerName");
		ecSpec.setGatewayName("gateway name");

		ECReportSpec ecReportSpec = new ECReportSpecImpl();
		ecReportSpec.setIncludeEPC(true);
		Set set = new HashSet();
		set.add(null);
		set.add(ecReportSpec);
		set.add(null);
		set.add(ecReportSpec);

		ecSpec.addReportSpecs(set);
		assertTrue(ecSpec.getReportSpecs().size() == 1);

		ECReportSpec ecReportSpec2 = new ECReportSpecImpl();
		ecReportSpec2.setIncludeEPC(true);
		ecReportSpec2.setName("2");
		ecSpec.addReportSpec(ecReportSpec2);
		assertTrue(ecSpec.getReportSpecs().size() == 2);

		ecSpec.setEventTopic("DETECTION");
		ecSpec.setDuration(10000);
		ecSpec.setStableSetInterval(2000);
		try {
			ecSpec.setStopTrigger(new URI("uri://sdf"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		System.err.println(ecSpec.getValidationMessage());
		assertTrue(ecSpec.getValidationMessage() == null);
	}

	/**
	 * Tests an invalid ECSpec : the ECReportSpec defined doesn't include
	 * anything it would create empty reports.
	 */
	public void test_05() {
		ecSpec.addLogicalReaderName("readerName");
		ecSpec.setGatewayName("gateway name");

		ECReportSpec ecReportSpec = new ECReportSpecImpl();
		ecReportSpec.setIncludeEPC(false);
		ecReportSpec.setIncludeCount(false);
		ecReportSpec.setIncludeRawDecimal(false);
		ecReportSpec.setIncludeRawHex(false);
		ecReportSpec.setIncludeTag(false);

		// Even if these data are present, they can't be associated to a tag
		ecReportSpec.setIncludeGPS(true);
		ecReportSpec.setIncludeReaderName(true);
		ecReportSpec.setIncludeMeasurement(true);

		ecSpec.addReportSpec(ecReportSpec);
		ecSpec.setEventTopic("DETECTION");
		ecSpec.setDuration(1000);

		System.err.println(ecSpec.getValidationMessage());
		assertTrue(ecSpec.getValidationMessage() != null);
	}

	/**
	 * Tests an invalid ECSpec : no EC ending defined
	 */
	public void test_06() {
		ecSpec.addLogicalReaderName("readerName");
		ecSpec.setGatewayName("gateway name");

		ECReportSpec ecReportSpec = new ECReportSpecImpl();
		ecReportSpec.setIncludeEPC(true);

		ecSpec.addReportSpec(ecReportSpec);
		ecSpec.setEventTopic("DETECTION");

		System.err.println(ecSpec.getValidationMessage());
		assertTrue(ecSpec.getValidationMessage() != null);
	}

	/**
	 * Tests an invalid ECSpec : no event topic defined
	 */
	public void test_07() {
		ecSpec.addLogicalReaderName("readerName");
		ecSpec.setGatewayName("gateway name");

		ECReportSpec ecReportSpec = new ECReportSpecImpl();
		ecReportSpec.setIncludeEPC(true);

		ecSpec.setStableSetInterval(200);
		ecSpec.addReportSpec(ecReportSpec);

		System.err.println(ecSpec.getValidationMessage());
		assertTrue(ecSpec.getValidationMessage() != null);
	}

	/**
	 * Tests an invalid ECSpec : StartTrigger can't be defined while
	 * repeatPeriod is non-zero
	 */
	public void test_08() {
		ecSpec.addLogicalReaderName("readerName");
		ecSpec.setGatewayName("gateway name");

		ECReportSpec ecReportSpec = new ECReportSpecImpl();
		ecReportSpec.setIncludeEPC(true);

		ecSpec.setStableSetInterval(200);
		ecSpec.setRepeatPeriod(4000);
		try {
			ecSpec.setStartTrigger(new URI("uri://sdf"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		ecSpec.addReportSpec(ecReportSpec);

		System.err.println(ecSpec.getValidationMessage());
		assertTrue(ecSpec.getValidationMessage() != null);
	}

	/**
	 * Tests an invalid ECSpec : no gateway name defined
	 */
	public void test_09() {
		ecSpec.addLogicalReaderName("readerName");

		ECReportSpec ecReportSpec = new ECReportSpecImpl();
		ecReportSpec.setIncludeEPC(true);

		ecSpec.setEventTopic("DETECTION");
		ecSpec.setStableSetInterval(200);
		ecSpec.addReportSpec(ecReportSpec);

		System.err.println(ecSpec.getValidationMessage());
		assertTrue(ecSpec.getValidationMessage() != null);
	}

	/**
	 * Tests an invalid ECSpec : no logical readers defined
	 */
	public void test_10() {
		ecSpec.setGatewayName("gateway name");

		ECReportSpec ecReportSpec = new ECReportSpecImpl();
		ecReportSpec.setIncludeEPC(true);

		ecSpec.setEventTopic("DETECTION");
		ecSpec.setStableSetInterval(200);
		ecSpec.addReportSpec(ecReportSpec);

		System.err.println(ecSpec.getValidationMessage());
		assertTrue(ecSpec.getValidationMessage() != null);
	}

	/**
	 * Tests a valid ECSpec with an exclude pattern
	 */
	public void test_11() {
		ecSpec.addLogicalReaderName(null);
		ecSpec.addLogicalReaderName("name");
		ecSpec.setGatewayName("gateway name");

		ECReportSpec ecReportSpec = new ECReportSpecImpl();
		ecReportSpec.setIncludeEPC(true);
		ecReportSpec.addExcludePattern("urn:epc:pat:gid-96:*.*.*");

		ecSpec.setEventTopic("DETECTION");
		ecSpec.setStableSetInterval(200);
		ecSpec.addReportSpec(ecReportSpec);

		System.err.println(ecSpec.getValidationMessage());
		assertTrue(ecSpec.getValidationMessage() == null);
	}

	/**
	 * Tests an invalid ECSpec with grouping patterns overlapping
	 */
	public void test_12() {
		ecSpec.addLogicalReaderName(null);
		ecSpec.addLogicalReaderName("name");
		ecSpec.setGatewayName("gateway name");

		ECReportSpec ecReportSpec = new ECReportSpecImpl();
		ecReportSpec.setIncludeEPC(true);
		ecReportSpec.addGroupSpec("urn:epc:pat:gid-96:*.111.*");
		ecReportSpec.addGroupSpec("urn:epc:pat:gid-96:123.111.123");

		ecSpec.setEventTopic("DETECTION");
		ecSpec.setStableSetInterval(200);
		ecSpec.addReportSpec(ecReportSpec);

		System.err.println(ecSpec.getValidationMessage());
		assertTrue(ecSpec.getValidationMessage() != null);
	}

}
