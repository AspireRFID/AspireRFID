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
package org.ow2.aspirerfid.epc.ale.impl.pattern;

import junit.framework.TestCase;

import org.ow2.aspirerfid.epc.ale.pattern.api.BadPatternSyntaxException;
import org.ow2.aspirerfid.epc.ale.pattern.api.EPCGroupingPattern;
import org.ow2.aspirerfid.epc.ale.pattern.impl.EPCFilteringPatternImpl;
import org.ow2.aspirerfid.epc.ale.pattern.impl.EPCGroupingPatternImpl;
import org.ow2.aspirerfid.epc.ale.tag.api.EPCTag;
import org.ow2.aspirerfid.epc.ale.tag.impl.EPCTagFactory;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
public class EPCPatternTest extends TestCase {

	/**
	 * Tests some correct patterns
	 */
	public static void test_01() {
		try {
			new EPCGroupingPatternImpl(
					"urn:epc:pat:sgtin-64:3.X.12345.[313-2435]");
			new EPCFilteringPatternImpl(
					"urn:epc:pat:sgtin-64:*.*.[2434-2435].[313-2435]");
		} catch (BadPatternSyntaxException e) {
			assertTrue(false);
			return;
		}
		assertTrue(true);
	}

	/**
	 * Tests an incorrect pattern : bad prefix
	 */
	public static void test_02() {
		try {
			new EPCGroupingPatternImpl(
					"urn:epc:patate:sgtin-64:3.X.12345.[313-2435]");
		} catch (BadPatternSyntaxException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}

	/**
	 * Tests an incorrect pattern : incorrect range [2440-2435]
	 */
	public static void test_03() {
		try {
			new EPCFilteringPatternImpl(
					"urn:epc:pat:gid-96:*.*.[2440-2435].[313-2435]");
		} catch (BadPatternSyntaxException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}

	/**
	 * Tests getGroup function
	 */
	public static void test_04() {
		try {
			EPCGroupingPattern pat = new EPCGroupingPatternImpl(
					"urn:epc:pat:gid-96:X.X.*");
			EPCTag epc = EPCTagFactory.getInstance("357654321123456123456789");
			assertEquals(pat.getGroup(epc),
					"urn:epc:pat:gid-96:7654321.123456.*");
		} catch (BadPatternSyntaxException e) {
			assertTrue(false);
			return;
		}
	}

	/**
	 * Tests an incorrect pattern : filtering patterns can't contain crosses
	 */
	public static void test_05() {
		try {
			new EPCFilteringPatternImpl(
					"urn:epc:pat:sgtin-64:3.X.12345.[313-2435]");
		} catch (BadPatternSyntaxException e) {
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}

	/**
	 * Tests a correct pattern
	 */
	public static void test_06() {
		try {
			new EPCFilteringPatternImpl(
					"urn:epc:pat:sgtin-64:*.2.12345.[313-2435]");
		} catch (BadPatternSyntaxException e) {
			assertTrue(false);
			return;
		}
		assertTrue(true);
	}

	/**
	 * Tests match function : bad format sgtin-64 instead of gid-96
	 */
	public static void test_07() {
		try {
			EPCGroupingPattern pat = new EPCGroupingPatternImpl(
					"urn:epc:pat:sgtin-64:7654321.X.*");
			EPCTag epc = EPCTagFactory.getInstance("357654321123456123456789");

			assertFalse(pat.match(epc));
		} catch (BadPatternSyntaxException e) {
			assertTrue(false);
			return;
		}
		assertTrue(true);
	}

	/**
	 * Tests match function : good match
	 */
	public static void test_08() {
		try {
			EPCGroupingPattern pat = new EPCGroupingPatternImpl(
					"urn:epc:pat:gid-96:7654321.X.*");
			EPCTag epc = EPCTagFactory.getInstance("357654321123456123456789");

			assertTrue(pat.match(epc));
		} catch (BadPatternSyntaxException e) {
			assertTrue(false);
			return;
		}
		assertTrue(true);
	}

	/**
	 * Tests isDisjoint function with 2 disjoint groups
	 */
	public static void test_09() {
		try {
			EPCGroupingPattern pat = new EPCGroupingPatternImpl(
					"urn:epc:pat:sgtin-64:3.X.12345.[313-2435]");
			EPCGroupingPattern pat2 = new EPCGroupingPatternImpl(
					"urn:epc:pat:sgtin-64:3.*.12345.[2436-5363]");
			assertTrue(pat.isDisjoint(pat2));
		} catch (BadPatternSyntaxException e) {
			assertTrue(false);
		}
	}

	/**
	 * Tests isDisjoint function with 2 non-disjoint groups
	 */
	public static void test_10() {
		try {
			EPCGroupingPattern pat = new EPCGroupingPatternImpl(
					"urn:epc:pat:sgtin-64:3.X.12345.[313-2439]");
			EPCGroupingPattern pat2 = new EPCGroupingPatternImpl(
					"urn:epc:pat:sgtin-64:3.*.12345.[2436-5363]");
			assertFalse(pat.isDisjoint(pat2));
		} catch (BadPatternSyntaxException e) {
			assertTrue(false);
		}
	}

	/**
	 * Tests isDisjoint function with 2 non-disjoint groups
	 */
	public static void test_11() {
		try {
			EPCGroupingPattern pat = new EPCGroupingPatternImpl(
					"urn:epc:pat:sgtin-64:3.X.*.*");
			EPCGroupingPattern pat2 = new EPCGroupingPatternImpl(
					"urn:epc:pat:sgtin-64:3.*.12345.[2436-5363]");
			assertFalse(pat.isDisjoint(pat2));
		} catch (BadPatternSyntaxException e) {
			assertTrue(false);
		}
	}
}
