/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package org.ow2.aspirerfid.ale.codec;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class Sgtin96Codec extends EpcCodec{
	byte[] tagData;
	private byte partition;
	private byte filterValue;
	private long companyPrefix;
	private int itemReference;
	private long serialNumber;
	
	/**
	 * The codec's partition table
	 */
	private PartitionTable partitionTable;	
	
	/**
	 * default.
	 * Constructor
	 */
	public Sgtin96Codec() {
		this.partitionTable = PartitionTables.getPartitionTable(PartitionTables.SGTIN_TABLE);
	}

	/**
	 * Constructor
	 * @param partitionTable
	 */
	public Sgtin96Codec(PartitionTable partitionTable) {
		this.partitionTable = partitionTable;
	}
		
	public void decodeTagData(byte[] tagData) {
		byte M, L, N;
		
		// wrap data
		this.tagData = tagData;
		
		// decode partition
		this.partition = getPartition(tagData);
		M = partitionTable.getBits(partition, PartitionTables.SGTIN_COMPANY_PREFIX);
		L = partitionTable.getDigits(partition, PartitionTables.SGTIN_COMPANY_PREFIX);
		N = partitionTable.getBits(partition, PartitionTables.SGTIN_INDIC_DIGIT_ITEM_REFERENCE);
		
		// decode Filter Value
		this.filterValue = getFilterValue(tagData);
		
		// decode Company Prefix
		this.companyPrefix = getCompanyPrefix(tagData, M, L);
		
		// decode Item Reference
		this.itemReference = getItemReference(tagData, M, N);
				
		// decode Serial lNumber
		this.serialNumber = getSerialNumber(tagData, M, N);
	}
	
	public final byte getPartition () {
		return this.partition;
	}
	
	public final byte getFilterValue () {
		return this.filterValue;
	}
	
	public long getCompanyPrefix () {
		return this.companyPrefix;
	}
	
	public int getItemReference () {
		return this.itemReference;
	}
	
	public long getSerialNumber() {
		return this.serialNumber;
	}
	
	/**
	 * Converter to build an epc pure URI representation.
	 * @return epc pure URI representation.
	 */
	public String getPureUri() 
	{
		StringBuffer uri ;
		byte L;
		
		/* build header */
		uri = new StringBuffer(COMMON_HEADER);
		uri.append("id:sgtin:");
		
		/* build payload */
		// Company prefix
		L = partitionTable.getDigits(this.partition, PartitionTables.SGTIN_COMPANY_PREFIX);
		appendUri(uri, this.companyPrefix, L);
		uri.append(".");
		// Item Reference
		L = partitionTable.getDigits(this.partition, PartitionTables.SGTIN_INDIC_DIGIT_ITEM_REFERENCE);
		appendUri(uri, this.itemReference, L);
		uri.append(".");
		// Serial Number
		uri.append(this.serialNumber);		
		
		return uri.toString();
	}
	
	/**
	 * Converter to build an epc tag URI representation.
	 * @return epc tag URI representation.
	 */
	public String getTagUri()  
	{
		StringBuffer uri ;
		byte L;
		
		/* build header */
		uri = new StringBuffer(COMMON_HEADER);
		uri.append("tag:sgtin-96:");
		
		/* build payload */
		// Filter Value
		uri.append(this.filterValue);
		uri.append(".");
		
		// Company prefix
		L = partitionTable.getDigits(this.partition, PartitionTables.SGTIN_COMPANY_PREFIX);
		appendUri(uri, this.companyPrefix, L);
		uri.append(".");
		// Item Reference
		L = partitionTable.getDigits(this.partition, PartitionTables.SGTIN_INDIC_DIGIT_ITEM_REFERENCE);
		appendUri(uri, this.itemReference, L);
		uri.append(".");
		// Serial Number
		uri.append(this.serialNumber);		
		
		return uri.toString();
	}
	
	public String getRawDecUri() {
		return convertToRawDecUri(this.tagData);
	}
	
	public String getRawHexUri() {
		return convertToRawHexUri(this.tagData);
	}	
		
	/*
	 * Test case
	 */
	public static void main(String[] args) {
		Sgtin96Codec anSgtinCodec = new Sgtin96Codec();
		//byte[] Tagdata = {0x30, 0x50, 0x00, 0x18, 0x1C, (byte) 0xB5, 0x0C, (byte) 0x80, 0x00 , 0x00, 0x10, 0x70};
		byte[] Tagdata = {0x70, 0x10, 0x00, 0x00, (byte) 0x80, 0x0C, (byte) 0xB5, 0x1C, 0x18, 0x00, 0x50, 0x30};
		
		
		System.out.println("-------------------------------------");
		System.out.println("----------- Instance ----------------");
		System.out.println("-------------------------------------");
		
		//anSgtinCodec = new SgtinCodec(PartitionTables.getPartitionTable(PartitionTables.SGTIN_TABLE));
		anSgtinCodec.decodeTagData(Tagdata);
		System.out.println("Partition =  " + anSgtinCodec.getPartition()) ;
		
		System.out.println("Company Prefix (dec) : " + anSgtinCodec.getCompanyPrefix());
		System.out.println("Company Prefix (hex) : 0x" + Long.toHexString(anSgtinCodec.getCompanyPrefix()));
		
		System.out.println("ItemReference (dec) : " + anSgtinCodec.getItemReference());
		System.out.println("ItemReference (hex) : 0x" + Integer.toHexString(anSgtinCodec.getItemReference()));
		
		System.out.println("SerialNumber (dec) : " + anSgtinCodec.getSerialNumber());
		System.out.println("SerialNumber (hex) : 0x" + Long.toHexString(anSgtinCodec.getSerialNumber()));
		
		System.out.println("-------------------------------------");
		System.out.println("----------- Test Case ---------------");
		System.out.println("-------------------------------------");		
		//testUnit ("30395DFA80E587C000000001");
		testCase();
		
	}

	public static byte[] stringToArrayOfBytes (String data) {
		byte[] bytes;
		
		// add a leading zero if necessary
		if ((data.length()%2)!=0)
			data = "0"+data;
		
		bytes = new byte[data.length()/2];
		for (int i =  bytes.length - 1, j = 0; i >= 0; i--, j+=2) {
			bytes[i] = Integer.decode("0x"+data.substring(j,j+2)).byteValue();
		}
		
		return bytes;
	}
	
	public static void dumpByteArray(byte[] bytes) {
		
		System.out.print("[");
		for (int i = 0; i < bytes.length; i++) {
			System.out.print("0x"+Integer.toHexString(bytes[i]&0x00FF) + ((i < (bytes.length-1)) ? "," : "]\n"));
		}
	}
	
	
	public static void testCase() {
		HashMap translation = new HashMap();       
        initTags(translation, "tags.txt");
        
		for (Iterator iter = translation.keySet().iterator(); iter.hasNext();) {
        	String key = (String)iter.next();
        	System.out.println("item : " + key);
        	testUnit ((String) translation.get(key));
        	System.out.println("-------------------------------------");
		}
	}

	public static void testUnit(String bytes) {
		byte [] tagData = stringToArrayOfBytes(bytes);
		Sgtin96Codec anSgtinCodec = new Sgtin96Codec();
		
		anSgtinCodec.decodeTagData(tagData);
//		System.out.println("Partition =  " + anSgtinCodec.getPartition()) ;
//		System.out.println("Filter Value (dec) : " + anSgtinCodec.getFilterValue());
//		
//		System.out.println("Company Prefix (dec) : " + anSgtinCodec.getCompanyPrefix());
//		System.out.println("Company Prefix (hex) : 0x" + Long.toHexString(anSgtinCodec.getCompanyPrefix()));
//		
//		System.out.println("ItemReference (dec) : " + anSgtinCodec.getItemReference());
//		System.out.println("ItemReference (hex) : 0x" + Integer.toHexString(anSgtinCodec.getItemReference()));
//		
//		System.out.println("SerialNumber (dec) : " + anSgtinCodec.getSerialNumber());
//		System.out.println("SerialNumber (hex) : 0x" + Long.toHexString(anSgtinCodec.getSerialNumber()));
//		
//		System.out.println("uri-pure : '" + anSgtinCodec.getPureUri());
		
		System.out.println("uri-tag : '" + anSgtinCodec.getTagUri());
		
		//System.out.println("uri-rawDec : '" + anSgtinCodec.getRawDecUri() + "'");
		System.out.println("uri-rawHex : '" + anSgtinCodec.getRawHexUri() + "'");

	}
	
	/**
	 * Init a Hash Map with tags.
	 * @param tags
	 * @param fileName
	 */
	private static void initTags(HashMap tags, String fileName) {
		BufferedReader input = null;
		
        // try reading simu tags from file
        try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		} catch (Exception e) {
			input = null;
		}
		
		if (input == null) {
	        tags.put("FITNESS POCKET",  "30395DFA80E587C000000001");
	        tags.put("BOIS EXIA GRAPH", "30395DFA80BD284000000003");
	        tags.put("FER UNITE EXIA",  "30395DFA80BD1F8000000001");
	        tags.put("TS EQUAREA",      "30395DFA80B3894000000001");
	        tags.put("ARTENGO 710 P",   "30395DFA80A49E8000000001");
	        tags.put("GEL SHAMPOING/D", "30395DFA8080828000000001");
	        tags.put("TS FORCLAZ 50 R", "30395DFA80E1060000000001");
	        tags.put("POL FORCLAZ 500", "30395DFA80E2BEC000000001");
	        tags.put("ARTENGO 100W PO", "30395DFA80D6698000000001");
	        tags.put("POL FORCLAZ 50",  "30395DFA80CBCE8000000001");
	        tags.put("ARTENGO 920 X 4", "30395DFA80CD1B0000000001");
	        tags.put("R300 LOW NOIR V", "30395DFA8096E8C000000001");
	        tags.put("ARTENGO  451 LA", "30395DFA80C23E0000000001");
	        tags.put("6 BARRES CEREAL", "30395DFA8067B50000000001");
	        tags.put("ARC STARTECH AD", "30395DFA808AF98000000001");
	        tags.put("ARTENGO 100W SH", "30395DFA808AF98000000001");
	        tags.put("FLECHE CARBONE",  "30395DFA806C90C000000001");
	        tags.put("SYNKRON NOIRE",   "30395DFA80A69DC000000001");
	        tags.put("BALLES EXIA",     "30395DFA809B0EC000000001");
	        tags.put("LOT 3 ANTIVOLS",  "30395DFA8037C84000000001");
	        tags.put("BALLON  R300",    "30395DFA804042C000000001");
	        tags.put("Mini Spray IP30", "30395DFA80B389400000000B");
	       
	        //tags.put("Invalide", "30FF5DFA80B389400000000B");
		} else {
			String line;
			int i = 0;
			
			// init map accordingly
			try {
				while ((line = input.readLine()) != null) {
					// ignore comments
					if (line.startsWith("#")) continue;
					// ignore empty lines
					if (line.length() == 0) continue;
					// ignore non sgtin lines
					if (!line.startsWith("sgtin-96")) continue;
					
					tags.put(Integer.toString(i++), line.substring(line.indexOf(':')+1));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}

