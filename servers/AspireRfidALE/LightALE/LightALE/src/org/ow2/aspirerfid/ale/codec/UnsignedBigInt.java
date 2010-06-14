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

import java.math.BigInteger;


/**
 * This class is a recyclable, unsigned and finite java's {@link BigInteger} like, but 
 * simpler.
 * This class wraps an array of bytes of maximum {@link #MAX_BYTES}.
 * This size is fixed for recycling the object. <br>
 * This class is intended to be used for manipulating numbers on more than 64 bits.
 * However, for better efficiency numbers that hold on maximum 7 bytes, are treated as native long.
 * Finally, this class has the same semantics as the following union in C : 
 * <pre>
 * typedef union {
 *   long nativeData;
 *   unsigned char data [MAX_BYTES];
 * } UnsignedBigInt;
 * </pre>
 * In this case, a C compiler allocates MAX_BYTES bytes of memory and the access to this
 * memory is determined upon compilation.<br>
 * In our case, this is determined manually by the data setters.<br>
 * <br>
 * Finally, this class still depends on java's {@link BigInteger} for string conversion 
 * of data on more than 7 bytes. 
 * @TODO build a specific toString converter for data on more than 7 bytes. 
 * 
 * @author pops
 * @author R.DAGHER
 *
 */
public class UnsignedBigInt {	
    /**
     * This mask is used to obtain the value of a byte as if it were unsigned.
     */
    private final static int INT_MASK = 0xFF;
    
    /**
     * The maximum bytes that can be hold inside this number.
     */
    private final static byte MAX_BYTES = 21;
    
    /**
	 * wrapped data Array : LSB is considered first
	 */
	private byte[] data;
	
	/**
	 * temporary buffer for converting to string using {@link BigInteger} algorithm
	 */
	private byte[] temp;
	
	/**
	 * Current size of array.
	 */
	private int size;	
	
	/**
	 * Alternative native representation of data.
	 */
	private long nativeData;
	/**
	 * Boolean indicating that current data representation is native instead of
	 * Array of bytes.
	 */
	private boolean isNative;
	
	/**
	 * Cached String representation of data
	 */
	private String stringVal;
	
	/**
	 * 
	 * Constructor
	 */
	public UnsignedBigInt() {		
		data = new byte[MAX_BYTES];
		temp = new byte[MAX_BYTES + 1];
		razData();
	}
	
	/**
	 * sets all bytes to zero
	 */
	public void razData() {
		if (size != 0) {
			for (int i = 0 ; i < size; i++)
				data[i] = 0;
		}
		size = 0 ;
		stringVal = null;
		isNative = false;
		nativeData = -1;
	}
	
	/**
	 * Copies the given array for processing. LSB is considered first.
	 * @param data array of bytes
	 */
	public void setData(UnsignedBigInt val) {
		int i ;
		
		for (i = 0 ; i < val.size; i++)
			data[i] = val.data[i];
		
		for (; i < MAX_BYTES; i++)
			data[i] = 0;
	}
	
	/**
	 * Copies the given array for processing. LSB is considered first.
	 * @param data array of bytes
	 */
	public void setData(byte[] data) {
		setData(data, 0, data.length);
	}
	
	/**
	 * Copies the given array for processing. LSB is considered first.
	 * @param data array of bytes
	 * @param length the amount of bytes to copy
	 */
	public void setData(byte[] data, int start, int length) {
		
		if ((start+length) > data.length)
			throw new IllegalArgumentException("not enough bytes to copy");
		
		if (length < 7) {
			// Native representation is better
			setData(arrayOfBytesToLong(data, start, length));
		}
		else {				
			// reset
			razData();
			
			this.size = Math.min(length,this.size) ;
			
			for ( int i = start; i< length; i++)
				this.data[i] = data[i];
		}
	
	}
	
	/**
	 * Copies the given array for processing. LSB is considered first.
	 * @param data array of bytes
	 */
	public void setData(byte[] data, int start, int startMask, int startShift,
						int length, int stopMask, int stopShift) 
	{		
		// buffer it all
		setData(data, start, length) ;
		
		// apply mask on first
		if (startShift >= 0) {	
			data[0] = (byte) ((data[0] & startMask) >>> startShift);
		}
		else {
			startShift = -startShift;
			data[0] = (byte) ((data[0] & startMask) << startShift);
		}
		
		
		// apply mask on last
		if (stopShift >= 0) {	
			data[0] = (byte) ((data[data.length-1] & stopMask) >>> stopShift);
		}
		else {
			stopShift = -stopShift;
			data[0] = (byte) ((data[data.length-1] & stopMask) << stopShift);
		}
	}
		
	/**
	 * Wraps the given long for processing. LSB is considered first.
	 * @param data in long : may be also a byte, short or int promoted to long.
	 */
	public void setNonNativeData(long data) {
		// reset
		razData();

		if (data != 0) {
			this.size = getWords(data);
			
			// compute bytes
			for (int i = 0, shift = 0; i < 8 ; i++, shift = 8*i)
				this.data[i] = (byte) ((data & (0xFFL << shift)) >>> shift) ;
		}
		
	}
		
	private static int getWords(long data) {
		int numBits, numDigits = 0 ;
		
		// compute number of digits
		
		while (data != 0) {
			data = data/10;
			numDigits++;
		}
		// compute number of bits
        numBits = (int)(((numDigits * 3402) >>> 10) + 1);
        
        // compute number of words		
		return (numBits + 7)/8;		
	}
	
	public void setData(long data) {
		
		// we don't raz bytes for performance
		//razData();
		this.stringVal = null;
		this.isNative = true;
		this.nativeData = data;
	}
	
	/**
	 * Wraps the given decimal String for processing. LSB is considered first.
	 * @param data in long
	 */
	public void setData(String data) {
		
		int cursor = 0, numDigits;
		int len = data.length();
		
		// Skip leading zeros and compute number of digits in magnitude
		while (cursor < len && Character.digit(data.charAt(cursor), 10) == 0)
		    cursor++;
		
		razData();
		
		if (cursor == len) {
			// all zero, return
			this.stringVal = "0";
		} else {
		    numDigits = len - cursor;
	        // Pre-allocate array of expected size
	        int numWords;
	        if (len < 2) {
	            numWords = 1;
	        } else {    
	            int numBits = (int)(((numDigits * 3402) >>> 10) + 1);
	            numWords = (numBits + 7) /8;
	        }
	        
	        if (numWords < 7) {
				// Native representation is better
	        	setData(Long.parseLong(data));	        	
	        }
	        else {
	        	this.size = numWords;
				// Process first (potentially short) digit group
				int firstGroupLen = numDigits % 2;
				if (firstGroupLen == 0)
				    firstGroupLen = 2;
				this.data[0] = parseByte(data, cursor,  cursor += firstGroupLen);
				// Process remaining digit groups
				while (cursor < len) {
				    byte groupVal = parseByte(data, cursor, cursor += 2);
			        destructiveMulAdd(this.data, this.size, (byte) 100, groupVal);
				}
	        }
			// Ok, cache the string representation without leading zeros
			this.stringVal = data.substring(len-numDigits);
		}
	}
	
	/**
	 * sizeof(dest) = sizeof(src) + 1
	 * @param dest
	 * @param src
	 */
	private void makePositiveLittleEndian(byte[] dest, byte[] src) {
		int i, size = src.length;
		
		for (i = 0 ; i < dest.length; i++)
			dest[i] = 0;

		for (i = 0 ; i < src.length; i++)
			dest[i+1] = src[size-1-i];


		return;
	}

	/**
	 * Create a byte with the digits between the two indexes     
	 * Assumes start < end. The result may be negative, but it       
	 * is to be treated as an unsigned value.
	 */
    private static byte parseByte(String source, int start, int end) {
        byte result;
        
        result = (byte) Character.digit(source.charAt(start++), 10);
        if (result == -1)
            throw new NumberFormatException(new String(source));

        for (int index = start; index<end; index++) {
            byte nextVal = (byte) Character.digit(source.charAt(index), 10);
            if (nextVal == -1)
                throw new NumberFormatException(new String(source));
            result = (byte) (10*result + nextVal);
        }

        return result;
    }    
    
    private static void destructiveMulAdd(byte[] x, int len, byte y, byte z) {
        
    	// Perform the multiplication word by word
        int yint = y & INT_MASK;
        int zint = z & INT_MASK;

        int product = 0;
        int carry = 0;
        for (int i = 0; i< len; i++) {
            product = (yint * (x[i] & 0x00FF) + carry);
            x[i] = (byte)product;
            carry = product >>> 8;
        }

        // Perform the addition
        int sum = (x[0] & INT_MASK) + zint;
        x[0] = (byte)sum;
        carry = sum >>> 8;      

        for (int i = 1; i < len; i++) {
            sum = (x[i] & INT_MASK) + carry;
            x[i] = (byte)sum;
            carry = sum >>> 8;
        }
    }
    
	/**
	 * Less Than.
	 * @param val value to compare with
	 * @return true if less than given value, false otherwise
	 */
	public boolean lessThan(UnsignedBigInt val) {
		return (-1 == compare(this, val));
	}
	
	/**
	 * Less or Equal Than.
	 * @param val value to compare with
	 * @return true if less or equal than given value, false otherwise
	 */
	public boolean lessEqThan(UnsignedBigInt val) {
		int cmp = compare(this, val);
		return ((cmp == 0) || (cmp == -1));
	}
	
	/**
	 * Greater Than.
	 * @param val value to compare with
	 * @return true if greater than given value
	 */
	public boolean greaterThan(UnsignedBigInt val) {
		return (1 == compare(this, val));
	}
	
	/**
	 * Greater or Equal Than.
	 * @param val value to compare with
	 * @return true if greater than given value
	 */
	public boolean greaterEqThan(UnsignedBigInt val) {
		int cmp = compare(this, val);
		return ((cmp == 0) || (cmp == 1));
	}
	
	/**
	 * Equal method
	 */
	public boolean equal(UnsignedBigInt val) {		
		return (0 == compare(this, val));
	}
	
    /**
     * Comparison of two numbers.
     * @return -1, 0 or +1 as big-endian unsigned int array arg1 is
     * less than, equal to, or greater than arg2.
     */
	public static int compare(UnsignedBigInt arg1, UnsignedBigInt arg2) {
		int ret = 0;
		int val1, val2, size1, size2;
		long cmp;
		
		if (arg1.isNative && arg2.isNative) {
			// both are native
			cmp = arg1.nativeData-arg2.nativeData;
			ret = (cmp < 0) ? -1 : ((cmp > 0) ? 1:0) ; 
		}else if (arg1.isNative) {
			// arg1 is native, and arg2 non native : i.e arg2 holds on more than 7 Bytes
			ret = -1;
		} else if (arg2.isNative) {
			ret = 1;
		}else {
			// Both are array of bytes	
			size1 = arg1.size;
			size2 = arg2.size;
			
			if (size1 < size2)
				ret = -1;
			else if (size1 > size2)
			    ret = 1;
			else {
				// Argument lengths are equal; compare the values by starting by the MSB
				for (int i=size1-1; i>=0; i--) {
					val1 = arg1.data[i] & INT_MASK;
					val2 = arg2.data[i] & INT_MASK;
				    if (val1 < val2) {
				    	ret = -1;
				    	break;
				    }
				    else if (val1 > val2) {
				    	ret = 1;
				    	break;
				    }
				}
			}
		}
		return ret;
	}
	
	private static long arrayOfBytesToLong(byte[] data, int start, int len) {
		long ret = 0, shift = 0  ;
		
		for (int i = start; i< len; i++, shift=8*i)
			ret |= ((data[i]&0x00FFL) << shift);
		
		return ret;		
	}
	
	/**
	 * @return string representation of the EPC object : the URI
	 */	
	public String toString() {
		String ret = null ;
		BigInteger bigInt;
		
		//this.stringVal = null;
		if (this.stringVal != null) 
			ret = this.stringVal;
		else {
			if (isNative) {
				ret = Long.toString(this.nativeData);
			}
			else if (this.size < 7)
				ret = Long.toString(arrayOfBytesToLong(this.data, 0, this.size));
			else {
				// Convert to String by using BigInteger Algorithm
				makePositiveLittleEndian(this.temp, this.data);
				bigInt = new BigInteger(this.temp);
				ret = bigInt.toString();
			}
			// cache converted value fo later use
			 this.stringVal = ret;
		}
		
		return ret;
	}
    
	/* 
	 * Test case
	 */
	public static void main(String[] args) throws Exception {		
		UnsignedBigInt val1 = new UnsignedBigInt();
		UnsignedBigInt val2 = new UnsignedBigInt();
		//byte[] data = {(byte)0xDE,0x03};
		//String val_1 = Long.toString(1000L*Integer.MAX_VALUE);	
		String val_2 = "9999999999999999999999999";	
		String val = val_2;	
		
		BigInteger bigInt = new BigInteger(val);

		bigInt = new BigInteger(val);
		val1.setData(0x1020L); //4128
		//System.out.println("data 990 as array of long: " + arrayOfBytesToLong(data));
		val2.setData(0x2010L); //8208
		val2.setData(val);
		System.out.println("val = " + val);
		System.out.println("val2 = " + val2);
		System.out.println("bigInt = " + bigInt);
		
		byte[] data = bigInt.toByteArray();
		System.out.print("bigInt:    [ ");
		for (int i = 0; i< data.length; i++)
			System.out.print( Integer.toHexString(data[i]&0xFF) + " ");
		System.out.println("]");
		
		System.out.print("val2_temp: [ ");
		for (int i = 0; i< val2.temp.length; i++)
			System.out.print( Integer.toHexString(val2.temp[i]&0xFF) + " ");
		System.out.println("]");
		
		System.out.print("val2_data: [ ");
		for (int i = 0; i< val2.data.length; i++)
			System.out.print( Integer.toHexString(val2.data[i]&0xFF) + " ");
		
		System.out.println("]");
		System.out.println(compare(val1, val2));
		System.out.println(compare(val2, val1));
		System.out.println(compare(val2, val2) +"\n");
		
		val1.setData(0x2010L); //8208
		val2.setData(0x1020L); //4128
		System.out.println(compare(val1, val2) +"\n");
		
		val1.setData(0x2000L); //8192
		val2.setData(0x10FFL); //4351
		System.out.println(compare(val1, val2) +"\n");
		
		val1.setData(0x2000L); //8192
		val2.setData(0x2000L); //8192
		System.out.println(compare(val1, val2));
		val1.setData(0x20000L); //8192
		val2.setData(0x2000); //4351
		System.out.println(compare(val1, val2) +"\n");
		
		val1.setData(0xFF00L); //65280
		val2.setData(0xFF); //255
		System.out.println(compare(val1, val2));
		System.out.println(compare(val2, val1) +"\n");
	}
	
	public static void bench() throws Exception {
		int bench_times = 10;
		double mean = 0;
		byte[] data = {(byte) 0xFF, 0x0F, (byte) 0xA5, (byte) 0xD4,  (byte) 0xE8 , 0x00};
		long [] time = new long[bench_times];
		
		for (int i = 0; i< bench_times; i++) {
			//time[i] = bench();
			time[i] = bench_toString(data);
		}
		
		for (int i = 0; i< bench_times; i++) {
			mean += time[i];
			System.out.println("bench " + i + " : " + time[i]);
		}
		mean = (mean/bench_times) ;
		System.out.println("mean Time : " + mean + "ms");
	}
	public static void prelude() throws InterruptedException {
		System.gc();
		Thread.sleep(1000);
	}
	public static void postlude() throws InterruptedException {
		System.gc();
		Thread.sleep(1000);
	}
	public static long benchSetData() throws Exception {
		long start, time;
		UnsignedBigInt val1;
		
		prelude();
		val1 = new UnsignedBigInt();
		
		start = System.currentTimeMillis();
		val1.setData("999999999999");
		time = System.currentTimeMillis() - start;		
		
		postlude();
		System.out.println(".");
		return time;
	}
	
	public static long bench_toString(byte[] data) throws Exception {
		long start, time;
		
		UnsignedBigInt val1;
		
		prelude();
		val1 = new UnsignedBigInt();
		
		start = System.currentTimeMillis();
		val1.setData(data);
		val1.toString();
		time = System.currentTimeMillis() - start;		
		
		postlude();
		System.out.println(".");
		return time;
	}
}
