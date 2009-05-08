package org.ow2.aspirerfid.reader.rp.impl.hal.transponder;


public enum TransponderModel {
	
	EM4135 (TransponderType.ISO15693, TransponderManufacturer.EMMICRO, 48, 8),
	MB89R116 (TransponderType.ISO15693, TransponderManufacturer.FUJITSU, 256, 8),
	MB89R118 (TransponderType.ISO15693, TransponderManufacturer.FUJITSU, 256, 8),
	INFMYD10P (TransponderType.ISO15693, TransponderManufacturer.INFINEON, 128, 8),
	INFMYD02P (TransponderType.ISO15693, TransponderManufacturer.INFINEON, 32, 8),
	INFISO10P (TransponderType.ISO15693, TransponderManufacturer.INFINEON, 256, 4),
	INFISO02P (TransponderType.ISO15693, TransponderManufacturer.INFINEON, 64, 4),
	KSW (TransponderType.ISO15693, TransponderManufacturer.KSW, 72, 4),
	ICODESLI (TransponderType.ISO15693, TransponderManufacturer.PHILIPS, 32, 4),
	LRI512 (TransponderType.ISO15693, TransponderManufacturer.STMICRO, 16, 4),
	LRI64 (TransponderType.ISO15693, TransponderManufacturer.STMICRO, 5, 1),
	TAGITHFIPLUS (TransponderType.ISO15693, TransponderManufacturer.TI, 65, 4),
	TAGITHFI (TransponderType.ISO15693, TransponderManufacturer.TI, 11, 4),
	
	ICODE1 (TransponderType.ICode1, TransponderManufacturer.PHILIPS, 16, 4),
	ICODEEPC (TransponderType.ISO15693, TransponderManufacturer.PHILIPS, 17, 1),
	ICODEUID (TransponderType.ICodeUID, TransponderManufacturer.PHILIPS, 12, 1),
	
	UNKNOWN (TransponderType.UNKNOWN, TransponderManufacturer.UNKNOWN, 0, 0);
	
	private final TransponderType type;
	private final TransponderManufacturer manufacturer;
	private final int blocks;
	private final int blocksize;
	
	TransponderModel(TransponderType type, TransponderManufacturer manufacturer, int blocks, int blocksize) {
		this.type = type;
		this.manufacturer = manufacturer;
		this.blocks = blocks;
		this.blocksize = blocksize;
	}

	public int getBlocks() {
		return blocks;
	}

	public int getBlocksize() {
		return blocksize;
	}

	public TransponderManufacturer getManufacturer() {
		return manufacturer;
	}

	public TransponderType getType() {
		return type;
	}
	
	public static TransponderModel getTransponderSpec(String transponderName) {
		for(TransponderModel t : TransponderModel.values()) {
			if (t.getClass().getName().equalsIgnoreCase(transponderName)) {
				return t;
			}
		}
		return TransponderModel.UNKNOWN;
	}

}
