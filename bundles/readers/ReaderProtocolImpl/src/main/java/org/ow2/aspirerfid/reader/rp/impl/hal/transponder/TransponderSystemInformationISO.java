package org.ow2.aspirerfid.reader.rp.impl.hal.transponder;

import org.ow2.aspirerfid.reader.rp.impl.hal.util.ByteBlock;

public class TransponderSystemInformationISO {
	
	/**
	 * Determines if transponder information is supported
	 */
	private boolean isSupported = false;
	
	/**
	 * Data storage format identifier
	 */
	private byte dsfid = 0x00;
	
	/**
	 * Transponders UID
	 */
	private String uid = "";
	
	/**
	 * Application familiy identifier
	 */
	private byte afi = 0x0;
	
	/**
	 * Memory size of transponder
	 */
	private int memSize = 0;
	
	/**
	 * IC reference (version) of the transponder
	 */
	private byte icReference = 0x0;
	
	public TransponderSystemInformationISO() {
		isSupported = false;
	}
	
	public TransponderSystemInformationISO(byte[] data) {
		if (data.length != 13)
			throw new IllegalArgumentException("Data has unexpected size");
		
		dsfid = data[0];
		uid = ByteBlock.byteArrayToHexString(copyOfRange(data, 1, 9));
		afi = data[9];
		memSize = ByteBlock.bytesToNumber(copyOfRange(data, 10, 12));
		icReference = data[12];
		isSupported = true;
	}
	
	private byte[] copyOfRange(byte[] data, int from, int to) {
		if (to < from) {
			return null;
		}
		byte[] res = new byte[to - from];
		for (int i = 0; i < res.length; i++) {
			if ((from + i) < data.length) {
				res[i] = data[from + i];
			} else {
				res[i] = (byte) 0x00;
			}
		}
		return res;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DSFID: 0x");
		sb.append(Integer.toHexString(dsfid));
		sb.append("/n");
		sb.append("UID: ");
		sb.append(uid);
		sb.append("/n");
		sb.append("AFI: ");
		sb.append(Integer.toHexString(dsfid));
		sb.append("/n");
		sb.append("Memory size: ");
		sb.append(String.valueOf(memSize));
		sb.append("/n");
		return sb.toString();
	}

	public byte getAFI() {
		return afi;
	}

	public byte getDSFID() {
		return dsfid;
	}

	public byte getICreference() {
		return icReference;
	}

	public boolean isSupported() {
		return isSupported;
	}

	public int getMemSize() {
		return memSize;
	}

	public String getUID() {
		return uid;
	}
}
