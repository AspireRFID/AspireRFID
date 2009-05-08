package org.ow2.aspirerfid.reader.rp.impl.hal.transponder;

import org.ow2.aspirerfid.reader.rp.impl.hal.util.ByteBlock;

public enum TransponderType {
	// TODO Check data sizes for ICode tags
	ICode1 (0x00, 10),
	TagItHF (0x01, 10),
	ISO15693 (0x03, 10),
	ICodeEPC (0x06, 13),
	ICodeUID (0x07, 20),
   ISO18000_6A (0x80, 0), // IDD length sent with inventory response
   ISO18000_6B (0x81, 0),
   EM4222 (0x83, 0),
   EPCclass1Gen2 (0x84, 0),
   EPCclass0Gen1 (0x88, 0),
   EPCclass1Gen1 (0x89, 0),
	UNKNOWN (0xff, 10);
	
	private final int code;
	
	private final int dataSize;
	
	TransponderType(int code, int dataSize) {
		this.code = code;
		this.dataSize = dataSize;
	}
	
	public int code() { return code; }
	
	public int dataSize() { return dataSize; }
	
	public static TransponderType getType(byte trType) {
      int typeno = ByteBlock.byteToNumber(trType) & 0xff;
      for(TransponderType t : TransponderType.values()) {
			if (t.code == typeno)
				return t;
		}
		return TransponderType.UNKNOWN;
	}
	
   public static TransponderType getType(String trTypeName) {
      for(TransponderType t : TransponderType.values()) {
         if (t.getClass().getName().equalsIgnoreCase(trTypeName)) {
            return t;
         }
      }
      return TransponderType.UNKNOWN;
   }
   
}
	

	

