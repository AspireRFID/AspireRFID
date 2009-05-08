/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

/*
 * Created on 2005
 */
package org.ow2.aspirerfid.reader.rp.hal.impl.intermecif5;

import java.util.Random;

import org.ow2.aspirerfid.reader.rp.impl.hal.HardwareException;
import org.ow2.aspirerfid.reader.rp.impl.hal.util.ByteBlock;


/**
 * The class represents a simple tag that can be used a virtual tag within the Simulator Framework.
 * 
 * 
 * 
 * @author Roland Schneider
 *
 */
public class Tag implements Cloneable {
   
   /**
    * The TID (without S/N) of simulated Tags.
    * This number consists of 8-bit ISO/IEC 15963 allocation class identifier,
    * 12-bit Tag mask-designer identifier and 12-bit Tag model number.
    * 'E2' for EPCglobal, 'FFF' for (hopefully) unused Tag mask-designer and
    * '000' for Tag model number.
    */
   private static final String TID = "E2FFF000";
   
   /**
    * Readable status of the memory banks.
    */
   private static final boolean[] READ = new boolean[] { false, true, true,
      true };
   
   /**
    * Writeable status of the memory banks.
    */
   private static final boolean[] WRITE = new boolean[] { false, true, false,
      true };
   
   /**
    * EPC memory size in bytes.
    */
   private static final int EPC_MEMORY_SIZE = 32;
   
   /**
    * User memory size in bytes.
    */
   private static final int USER_MEMORY_SIZE = 32;
	
	/**
	 * The ID.
	 */
	private String snr;
	
	/**
	 * The memory banks.
	 */
	private ByteBlock[] memory;

	/**
	 * Constructor without user memory.
	 * 
	 * @param epc
	 *            The ID (EPC)
	 */
	public Tag(String epc){
		memory = new ByteBlock[4];
		
      // Reserved (passwords)
		memory[0] = new ByteBlock(new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00 });
      
      // EPC
      memory[1] = new ByteBlock(new byte[EPC_MEMORY_SIZE]);
		setTagID(epc);
      
      // TID
		setTID();
      
      // User
		memory[3] = new ByteBlock(new byte[USER_MEMORY_SIZE]);
	}
	
	/**
	 * Constructor with user memory
	 * 
	 * @param epc
	 *            The ID (EPC)
	 * @param userMemory
	 *            The user memory
	 */
	public Tag(String epc, byte[] userMemory){
      memory = new ByteBlock[4];
      
      // Reserved (passwords)
      memory[0] = new ByteBlock(new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00 });
      
      // EPC
      memory[1] = new ByteBlock(new byte[EPC_MEMORY_SIZE]);
      setTagID(epc);
      
      // TID
      setTID();
      
      // User
      memory[3] = new ByteBlock(new byte[USER_MEMORY_SIZE]);
      if (userMemory.length > USER_MEMORY_SIZE) {
         byte[] user = new byte[USER_MEMORY_SIZE];
         System.arraycopy(userMemory, 0, user, 0, user.length);
         userMemory = user;
      }
      try {
         writeData(3, 0, userMemory);
      } catch (HardwareException e) {}
	}
	
	/**
	 * Returns the ID of this tag.
	 * 
	 * @return The ID of this tag
	 */
	public String getTagID(){
		return snr;
	}
	
   /**
    * Sets the tag's ID (snr and EPC memory bank).
    * 
    * @param aID
    *            The ID to be set
    */
   public void setTagID(String aID){
      if (aID == null) {
         aID = "";
      }
      aID = ((aID.length() % 2 != 0) ? (aID + "0") : aID);
      aID = ((aID.length() % 4 != 0) ? (aID + "00") : aID);
      if (aID.length() > 124) {
         // maximum length of PC+EPC is 32 words
         aID = aID.substring(0, 124);
      }
      if (aID.length() > ((EPC_MEMORY_SIZE - 4) * 2)) {
         // fit into EPC memory block
         aID = aID.substring(0, ((EPC_MEMORY_SIZE - 4) * 2));
      }
      this.snr = aID;
      byte[] epcbytes = ByteBlock.hexStringToByteArray(aID);
      byte[] pcepcbytes = new byte[epcbytes.length + 2];
      int lenbits = (epcbytes.length / 2) << 11;
      pcepcbytes[0] = (byte) ((lenbits >> 8) & 0xFF);
      pcepcbytes[1] = (byte) (lenbits & 0xFF);
      System.arraycopy(epcbytes, 0, pcepcbytes, 2, epcbytes.length);
      int crcint = crc16(pcepcbytes);
      byte[] epcmemory = new byte[pcepcbytes.length + 2];
      epcmemory[0] = (byte) ((crcint >> 8) & 0xFF);
      epcmemory[1] = (byte) (crcint & 0xFF);
      System.arraycopy(pcepcbytes, 0, epcmemory, 2, pcepcbytes.length);
      try {
         writeData(1, 0, epcmemory);
      } catch (HardwareException e) {}
   }
   
   /**
    * Computes the CRC-16 precurser and ones-complement it.
    * 
    * @param data
    *          the data to compute the CRC16 from
    * @return
    *          the CRC16 of the data
    */
   private int crc16 (byte[] data) {
      int crc = 0xFFFF;
      int polynomial = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12) 

      for (byte b : data) {
          for (int i = 0; i < 8; i++) {
              boolean bit = ((b   >> (7-i) & 1) == 1);
              boolean c15 = ((crc >> 15    & 1) == 1);
              crc <<= 1;
              if (c15 ^ bit) crc ^= polynomial;
           }
      }
      
      crc ^= 0xFFFF; // ones-complement
      crc &= 0xFFFF; // mask lower two bytes
      
      return crc;
   }
   
	/**
	 * Sets the tag's TID memory bank.
	 */
	public void setTID(){
      // random 32-bit S/N
      byte[] snbytes = new byte[4];
      Random rnd = new Random();
      rnd.nextBytes(snbytes);
      String tid = TID + ByteBlock.byteArrayToHexString(snbytes);
      memory[2] = new ByteBlock(tid);
	}
	
	/**
	 * Reads some data.
	 * 
    * @param memoryBank
    *          number of the memory bank (0-3)
    * @param offset
    *          beginning of data to read in memory bank
    * @param length
    *          length of data to read, read to end of memory if 0
	 * @return the data
    * @throws HardwareException
    *          If memory bank shorter than offset + length or memoryBank
    *          not readable
	 */
	public byte[] readData(int memoryBank, int offset, int length)
         throws HardwareException {
      if (!READ[memoryBank]) {
         throw new HardwareException("Memory bank not readable.");
      }
      if (length == 0) {
         length = memory[memoryBank].getInternalByteArray().length - offset;
      }
      if (memory[memoryBank].getInternalByteArray().length < (offset + length)) {
         throw new HardwareException("Can not read, memory bank shorter than "
               + "offset + length.");
      }
		return ByteBlock.getRegion(memory[memoryBank].toByteArray(), offset, length);
	}
	
	/**
	 * Writes some data.
	 * 
    * @param memoryBank
    *          number of the memory bank (0-3)
    * @param offset
    *          beginning of data to read in memory bank
	 * @param data
	 *          The data
    * @throws HardwareException
    *          If memory bank shorter than offset + length or memoryBank not
    *          writeable
	 */
	public void writeData(int memoryBank, int offset, byte[] data)
         throws HardwareException {
      if (!WRITE[memoryBank]) {
         throw new HardwareException("Memory bank not writeable.");
      }
      if (memory[memoryBank].getInternalByteArray().length < (offset + data.length)) {
         throw new HardwareException("Can not write, memory bank shorter than "
               + "offset + length.");
      }
		ByteBlock.replaceRegion(memory[memoryBank].getInternalByteArray(), offset, data);
	}
	
   /**
    * Writes some data.
    * 
    * @param data
    *            The data
    */
   public void setData(byte[] data, int memoryBank) {
      memory[memoryBank] = new ByteBlock(data);
   }
   
	/**
	 * Clones the tag.
	 *
	 * @return The deep copy of the RFID tag
	 *
	 */
	 public Object clone() {
	 	Tag tagCopy = new Tag( this.snr );
	 	for (int i = 0; i < memory.length; i++) {
	 		tagCopy.setData(memory[i].toByteArray(), i);
	 	}
	    return tagCopy;
	  }
	
	
	/**
	 * Compares SNR (the ID) of this tag and the argument.
	 * 
	 * @param snr
	 *            The ID
	 * @return <code>true</code> it the ID is the same, <code>false</code>
	 *         otherwise
	 */ 
	 private boolean compareSNR(String snr){
	 	return this.snr.equalsIgnoreCase(snr);
	 }
	 
	  /**
	   * Checks tags for equality.
	   * 
	   * @param tag
	   *            The tag to compare
	   * 
	   * @return If both IDs are equal <code>true</code> else
	   *         <code>false</code>
	   */
	  public boolean equals( Object tag ) {
	    if ( tag instanceof Tag ) {
	      return compareSNR(((Tag)tag).getTagID());
	    }
	    else {
	      return false;
	    }
	  }
	
	  /**
	   * Returns the hash code of the ID.
	   * 
	   * @return The hash code of the ID
	   */
	  public int hashCode() {	   
	    return snr.hashCode();
	  }
}
