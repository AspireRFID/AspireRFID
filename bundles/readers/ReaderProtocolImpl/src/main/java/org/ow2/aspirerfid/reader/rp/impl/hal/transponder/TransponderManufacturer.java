package org.ow2.aspirerfid.reader.rp.impl.hal.transponder;

public enum TransponderManufacturer {

	STMICRO (0x02),
	PHILIPS (0x04),
	INFINEON (0x05),
	TI (0x07),
	FUJITSU (0x08),
	EMMICRO (0x16),
	KSW (0x17),

   UNKNOWN (0xff);
   
	private final int code;
	
	private TransponderManufacturer(int code) {
		this.code = code;
	}
	
	public int getCode() { return code; }
	
	public static TransponderManufacturer getTransponderManufacturer(int code) {
		for(TransponderManufacturer t : TransponderManufacturer.values()) {
			if (t.code == code)
				return t;
		}
		return TransponderManufacturer.UNKNOWN;
	}
}
