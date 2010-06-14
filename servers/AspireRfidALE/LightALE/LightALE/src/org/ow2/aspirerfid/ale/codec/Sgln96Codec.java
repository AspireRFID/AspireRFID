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


public class Sgln96Codec extends EpcCodec{
	/**
	 * The codec's partition table
	 */
	private PartitionTable partitionTable;	
	
	byte[] tagData;
	private byte partition;
	private byte filterValue;
	private long companyPrefix;
	private int locationReference;
	private long extComponent;
	
	/**
	 * default.
	 * Constructor
	 */
	public Sgln96Codec() {
		this.partitionTable = PartitionTables.getPartitionTable(PartitionTables.SGLN_TABLE);
	}

	/**
	 * Constructor
	 * @param partitionTable
	 */
	public Sgln96Codec(PartitionTable partitionTable) {
		this.partitionTable = partitionTable;
	}
	
	public void decodeTagData(byte[] tagData) {
		byte M, L, N;
		
		// wrap data
		this.tagData = tagData;
		
		// decode partition
		this.partition = getPartition(tagData);
		M = partitionTable.getBits(partition, PartitionTables.SGLN_COMPANY_PREFIX);
		L = partitionTable.getDigits(partition, PartitionTables.SGLN_COMPANY_PREFIX);
		N = partitionTable.getBits(partition, PartitionTables.SGLN_LOCATION_REFERENCE);
		
		// decode Filter Value
		this.filterValue = getFilterValue(tagData);
		
		// decode Company Prefix
		this.companyPrefix = getCompanyPrefix(tagData, M, L);
		
		// decode Location Reference
		this.locationReference = getItemReference(tagData, M, N);
				
		// decode Extension Component
		this.extComponent = getSerialNumber(tagData, M, N);
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
	
	public int getLocationReference () {
		return this.locationReference;
	}
	
	public long getExtComponent() {
		return this.extComponent;
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
		uri.append("id:sgln:");
		
		/* build payload */
		// Company prefix
		L = partitionTable.getDigits(this.partition, PartitionTables.SGLN_COMPANY_PREFIX);
		appendUri(uri, this.companyPrefix, L);
		uri.append(".");
		// Location Reference
		L = partitionTable.getDigits(this.partition, PartitionTables.SGLN_LOCATION_REFERENCE);
		appendUri(uri, this.locationReference, L);
		uri.append(".");
		// Extension Component
		uri.append(this.extComponent);	
		
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
		uri.append("tag:sgln-96:");
		
		/* build payload */
		// Filter Value
		uri.append(this.filterValue);
		uri.append(".");
		
		// Company prefix
		L = partitionTable.getDigits(this.partition, PartitionTables.SGLN_COMPANY_PREFIX);
		appendUri(uri, this.companyPrefix, L);
		uri.append(".");
		// Item Reference
		L = partitionTable.getDigits(this.partition, PartitionTables.SGLN_LOCATION_REFERENCE);
		appendUri(uri, this.locationReference, L);
		uri.append(".");
		// Extension Component
		uri.append(this.extComponent);	
		
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
		Sgln96Codec anSglnCodec = new Sgln96Codec();
		
		byte[] Tagdata = {(byte) 0xCD, (byte) 0xAB, 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xB6, 0x29, (byte) 0x82, 0x58, (byte) 0xCE, 0x38, 0x32};
		
		//byte[] Tagdata = {0x32, 0x54, 0x00, (byte) 0xC0, (byte) 0xE4, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00};
		//byte[] Tagdata = {0x00, 0x00, 0x00, 0x00, 0x00, 0x04, 0x00, (byte) 0xE4, (byte) 0xC0, 0x00, 0x54, 0x32};
				
		System.out.println("-------------------------------------");
		System.out.println("----------- Instance ----------------");
		System.out.println("-------------------------------------");
		
		//anSgtinCodec = new SgtinCodec(PartitionTables.getPartitionTable(PartitionTables.SGTIN_TABLE));
		System.out.println("header = " + EpcCodec.decodeHeader(Tagdata));
		
		anSglnCodec.decodeTagData(Tagdata);
		System.out.println("Partition =  " + anSglnCodec.getPartition()) ;
		System.out.println("Filter Value =  " + anSglnCodec.getFilterValue()) ;
		
		System.out.println("Company Prefix (dec) : " + anSglnCodec.getCompanyPrefix());
		System.out.println("Company Prefix (hex) : 0x" + Long.toHexString(anSglnCodec.getCompanyPrefix()));
		
		System.out.println("LocationReference (dec) : " + anSglnCodec.getLocationReference());
		System.out.println("LocationReference (hex) : 0x" + Integer.toHexString(anSglnCodec.getLocationReference()));
		
		System.out.println("ExtComponent (dec) : " + anSglnCodec.getExtComponent());
		System.out.println("ExtComponent (hex) : 0x" + Long.toHexString(anSglnCodec.getExtComponent()));
		
		System.out.println("uri-pure : '" + anSglnCodec.getPureUri() +"'");
		System.out.println("uri-tag : '" + anSglnCodec.getTagUri() +"'");		
		System.out.println("uri-rawDec : '" + convertToRawDecUri(Tagdata) + "'");
		System.out.println("uri-rawHex : '" + convertToRawHexUri(Tagdata) + "'");
	}
	
}
