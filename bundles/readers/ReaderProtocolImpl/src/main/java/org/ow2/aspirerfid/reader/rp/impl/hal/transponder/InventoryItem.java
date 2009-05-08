package org.ow2.aspirerfid.reader.rp.impl.hal.transponder;

import org.ow2.aspirerfid.reader.rp.impl.hal.transponder.EPCTransponderModel;


public class InventoryItem {
	/**
	 * 
	 */
	public TransponderType transponderType;
	
	/**
	 * 
	 */
	public RFTechnology rfTechnology;
	
	/**
	 * Data storage familiy identifier.
	 * This is only valid for ISO15693 tags.
	 */
	public byte dsfid = (byte) 0x00;
	
   /**
    * System information for ISO15693 transponders.
    */
   public TransponderSystemInformationISO systemInformation = new TransponderSystemInformationISO();
   
   /**
    * EPC TID of the tag
    */
   public byte[] tid;
   
   /**
    * EPC transponder model.
    * This is only valid for epc tags.
    */
   public EPCTransponderModel epcTransponderModel;
   
	/**
	 * Hexadecimal representation of the tag ID (UID,EPC,IDD)
	 */
	public String id = "";
	
	/**
	 * Readpoint the tag was read.
	 */
	public String readPoint = "";
	
}
