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

package org.ow2.aspirerfid.ale.core.tools;


import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.epcglobalinc.tdt.LevelTypeList;
import org.fosstrak.tdt.TDTEngine;


/**
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class EpcUriFactory {

	// private String TDT_RESOURCE =
	// this.getClass().getResource("/props").getPath().replace("%20", " ");

	public String rawDecimal(byte[] tag) {
		BigInteger bi = new BigInteger(tag);
		// Format to binary
		String tagInBinary = bi.toString(2);
		Integer tagLength = (tag.length) * 8;

		if (tagInBinary.length() < tagLength) {
			// zero padding until filling the length
			int zeros = tagLength - tagInBinary.length();
			for (int i = 0; i < zeros; i++) {
				tagInBinary = "0".concat(tagInBinary);
			}
		}

		String rawDecimal = null;
		TDTEngine tdtEngine = null;
		// String tagInHex = HexUtil.byteArrayToHexString(tag);
		rawDecimal = "urn:epc:raw:";

		// System.out.println("TDT_RESOURCE file URI is at:"+TDT_RESOURCE);
		try {
			tdtEngine = new TDTEngine();

			HashMap<String, String> param = new HashMap<String, String>();
			LevelTypeList levelType = LevelTypeList.BINARY;
			param.put("taglength", tagLength.toString());
			param.put("filter", "1");
			param.put("companyprefixlength", "7");
			rawDecimal = rawDecimal + tagLength.toString() + ".";
			rawDecimal = rawDecimal
					+ new BigInteger(new BigInteger(tdtEngine.convert(
							tagInBinary, param, levelType), 2).toByteArray())
							.toString(10).toUpperCase();
			// String[] uriArray =
			// tdtEngine.convert(tagInBinary,param,levelType).split(":");
			// rawDecimal = rawDecimal + uriArray[4];

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rawDecimal;
	}

	public String rawHex(byte[] tag) {
		String rawHex = null;
		// String tagInHex = HexUtil.byteArrayToHexString(tag);
		TDTEngine tdtEngine = null;

		// Create a BigInteger using the byte array
		BigInteger bi = new BigInteger(tag);

		// Format to binary
		String tagInBinary = bi.toString(2);
		Integer tagLength = (tag.length) * 8;
		if (tagInBinary.length() < tagLength) {
			// zero padding until 4
			int zeros = tagLength - tagInBinary.length();
			for (int i = 0; i < zeros; i++) {
				tagInBinary = "0".concat(tagInBinary);
			}
		}

		rawHex = "urn:epc:raw:";

		try {
			tdtEngine = new TDTEngine();

			HashMap<String, String> param = new HashMap<String, String>();
			param.put("taglength", tagLength.toString());
			param.put("filter", "1");
			param.put("companyprefixlength", "7");
			rawHex = rawHex + tagLength.toString() + ".x";
			rawHex = rawHex
					+ new BigInteger(
							new BigInteger(tdtEngine.convert(tagInBinary,
									param, LevelTypeList.BINARY), 2)
									.toByteArray()).toString(16).toUpperCase();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rawHex;
	}

	public String getTagIDAsTagURI(byte[] tag) {
		TDTEngine engine = null;
		HashMap<String, String> extraparams = null;
		Integer tagLength = (tag.length) * 8;

		// Create a BigInteger using the byte array
		BigInteger bi = new BigInteger(tag);

		// Format to binary
		String tagInBinary = bi.toString(2);

		if (tagInBinary.length() < tagLength) {
			// zero padding until 4
			int zeros = tagLength - tagInBinary.length();
			for (int i = 0; i < zeros; i++) {
				tagInBinary = "0".concat(tagInBinary);
			}
		}

		try {
			engine = new TDTEngine();

			extraparams = new HashMap<String, String>();
			extraparams.put("taglength", tagLength.toString());
			extraparams.put("filter", "1");
			extraparams.put("companyprefixlength", "7");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return engine.convert(tagInBinary, extraparams,
				LevelTypeList.TAG_ENCODING);
	}

	public String getTagIDAsPureURI(byte[] tag) {
		TDTEngine engine = null;
		HashMap<String, String> extraparams = null;
		Integer tagLength = (tag.length) * 8;

		// Create a BigInteger using the byte array
		BigInteger bi = new BigInteger(tag);

		// Format to binary
		String tagInBinary = bi.toString(2);

		if (tagInBinary.length() < tagLength) {
			// zero padding until 4
			int zeros = tagLength - tagInBinary.length();
			for (int i = 0; i < zeros; i++) {
				tagInBinary = "0".concat(tagInBinary);
			}
		}

		try {
			engine = new TDTEngine();

			extraparams = new HashMap<String, String>();
			extraparams.put("taglength", tagLength.toString());
			extraparams.put("filter", "1");
			extraparams.put("companyprefixlength", "7");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return engine.convert(tagInBinary, extraparams,
				LevelTypeList.PURE_IDENTITY);
	}

}
