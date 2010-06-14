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

/**
 * Singleton containing a list of partition tables.
 * @author rdagher
 *
 */
public class PartitionTables {
	/**
	 * Number of partitions
	 */
	public static final byte PARTITIONS_NUMBER = 2;

	/**
	 * SGTIN_96 Table definition
	 */
	public static final byte SGTIN_TABLE = 0;
	/**
	 * SGLN_96 Table definition
	 */
	public static final byte SGLN_TABLE = 1;
	

	/**
	 * SGTIN_96 entryId definitions
	 */
	public static final byte SGTIN_COMPANY_PREFIX = 0;
	public static final byte SGTIN_INDIC_DIGIT_ITEM_REFERENCE = 1;
	/**
	 * SGLN_96 entryId definitions
	 */
	public static final byte SGLN_COMPANY_PREFIX = 0;
	public static final byte SGLN_LOCATION_REFERENCE = 1;
	
	/**
	 * a unique partition table
	 */
	private static aPartitionTable[] partitionTables = null;;
	
	protected PartitionTables() {
		
	}
	
	/**
	 * Gets the partition table of a header.
	 * @param header SGTIN_96_TABLE or SGLN_96_TABLE
	 * @return a partition table singleton for the given header type
	 */
	public static PartitionTable getPartitionTable(byte header) {
		if (partitionTables == null)
			initTables();
		
		if (header >= PARTITIONS_NUMBER)
			throw new IllegalArgumentException("header out of range : " + header );
		
		return partitionTables[header];	
	}
	
	/**
	 * Set up tables pool.
	 */
	private static void initTables() {
		if (partitionTables == null) {
			// init array
			partitionTables = new aPartitionTable[PARTITIONS_NUMBER];
			
			// init SGTIN_96 table
			partitionTables[SGTIN_TABLE] = new aPartitionTable( new Infos[][]
				{
					{new Infos((byte)40,(byte)12), new Infos((byte)4,(byte)1)}, /* Partition 0 */
					{new Infos((byte)37,(byte)11), new Infos((byte)7,(byte)2)}, /* Partition 1 */
					{new Infos((byte)34,(byte)10), new Infos((byte)10,(byte)3)},/* Partition 2 */
					{new Infos((byte)30,(byte)9), new Infos((byte)14,(byte)4)}, /* Partition 3 */
					{new Infos((byte)27,(byte)8), new Infos((byte)17,(byte)5)}, /* Partition 4 */
					{new Infos((byte)24,(byte)7), new Infos((byte)20,(byte)6)}, /* Partition 5 */
					{new Infos((byte)20,(byte)6), new Infos((byte)24,(byte)7)} 	/* Partition 6 */
				});
			
			// init SGLN_96 table
			partitionTables[SGLN_TABLE] = new aPartitionTable( new Infos[][]
				{
					{new Infos((byte)40,(byte)12), new Infos((byte)1,(byte)0)}, /* Partition 0 */
					{new Infos((byte)37,(byte)11), new Infos((byte)4,(byte)1)}, /* Partition 1 */
					{new Infos((byte)34,(byte)10), new Infos((byte)7,(byte)2)},	/* Partition 2 */
					{new Infos((byte)30,(byte)9), new Infos((byte)11,(byte)3)}, /* Partition 3 */
					{new Infos((byte)27,(byte)8), new Infos((byte)14,(byte)4)}, /* Partition 4 */
					{new Infos((byte)24,(byte)7), new Infos((byte)17,(byte)5)}, /* Partition 5 */
					{new Infos((byte)20,(byte)6), new Infos((byte)21,(byte)6)} 	/* Partition 6 */
				});
		}		
	}
}

/**
 * Implementation of a PartitionTable.
 * @author rdagher
 *
 */
class aPartitionTable implements PartitionTable {
	private Infos[][] table;
	
	/**
	 * Forbidden
	 * Constructor
	 */
	protected aPartitionTable() {
		
	}
	
	/**
	 * Constructor
	 * @param table
	 */
	public aPartitionTable(Infos[][] table) {
		this.table = table;
	}
	
	public byte getBits(byte partitionNumber, byte entryId) {
		return table[partitionNumber][entryId].Bits;
	}

	public byte getDigits(byte partitionNumber, byte entryId) {
		return table[partitionNumber][entryId].Digits;
	}	
}

/**
 * Information container in a paritionTable element.
 * @author rdagher
 *
 */
class Infos {
	protected byte Bits ;
	protected byte Digits ;
	
	/**
	 * Constructor
	 * @param bits
	 * @param digits
	 */
	public Infos(byte bits, byte digits) {
		Bits = bits;
		Digits = digits;
	}
}
