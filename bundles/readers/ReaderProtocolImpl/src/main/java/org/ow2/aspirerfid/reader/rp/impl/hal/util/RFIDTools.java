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

package org.ow2.aspirerfid.reader.rp.impl.hal.util;
/**
 * This class is an abstraction for the RFID tags.
 * A tag has a serial number and depending on the application data memory.
 *
 * <p>Copyright: Copyright (c) 2002</p>
 * @author Matthias Lampe, lampe@acm.org
 * @author Harald Vogt, vogt@inf.ethz.ch
 * @author Philip Pfister, philip.pfister@hsr.ch
 * @author Stefan Kuenzle, stefan.kuenzle@hsr.ch
 * @version 1.0
 */

public class RFIDTools implements Cloneable {
  //-------- Constants ---------------------------------------------------------
  /** size of serial number */
  public static final int SERIAL_NR_SIZE = 8;
  /** Tag-it HF */
  public static final int TAG_IT_HF = 1;
  /** Tag-it HF-i or other ISO 15693 compliant transponders */
  public static final int ISO_15693 = 3;

  byte[] mySerialNr;
  int transponterType=0;
  int dataSotageFamilyID=0;

  static public String hexByte( int b ) {
    String h = "0123456789ABCDEF";
    return "" + h.charAt( ( b & 0xF0 ) >> 4 ) + h.charAt( b & 0x0F );
  }

  static public String getData( byte[] data ) {
    StringBuffer sb = new StringBuffer();
    for( int i = 0; i < data.length; i++ ) {
      sb.append( hexByte( data[i] ) );
    }
    return sb.toString();
  }

  static public String getDataWithSpace( byte[] data ) {
    StringBuffer sb = new StringBuffer();
    for( int i = 0; i < data.length; i++ ) {
      sb.append( hexByte( data[i] ) + " " );
    }
    return sb.toString().trim();
  }

  static public String getDataWithSpaceLength( byte[] data, int l ) {
    StringBuffer sb = new StringBuffer();
    for( int i = 0; (i < l)&&(i<data.length); i++ ) {
      sb.append( hexByte( data[i] ) + " " );
    }
    return sb.toString().trim();
  }

  static public String getChar( byte[] data ) {
    StringBuffer sb = new StringBuffer();
    for( int i = 0; i < data.length; i++ ) {
      sb.append( ( char )data[i] );
    }
    return sb.toString();
  }
  
  static public String getSerialFromByteArray( byte[] snr, int offset ){
  	byte[] mySerialNr = new byte[SERIAL_NR_SIZE];
    for( int i = 0; i < SERIAL_NR_SIZE; i++ ) {
      mySerialNr[i] = snr[offset + i];
    }
    StringBuffer sb = new StringBuffer();
    for( int i = 0; i < mySerialNr.length; i++ ) {
      sb.append( hexByte( mySerialNr[i] ) );
    }
    return sb.toString().trim();
    
  }


  //-------- Constructor(s) ----------------------------------------------------
  /**
   * creates a RFIDTag with the gifen parameter
   *
   * @param snr serial number of the transponder
   * @param offset offset of the serial number
   * @param type transponder type
   * @param dsfID data storage family identifier
   */
  public RFIDTools( byte[] snr, int offset, int type, int dsfID ) {
    mySerialNr = new byte[SERIAL_NR_SIZE];
    for( int i = 0; i < SERIAL_NR_SIZE; i++ ) {
      mySerialNr[i] = snr[offset + i];
    }
    transponterType = type;
    dataSotageFamilyID = dsfID;
  }


  //-------- Public Methods ----------------------------------------------------
  /**
   * gets the serial number
   *
   * @return the serial number of the tag as a array of byte.
   */
  public byte[] getSNR() {
    return mySerialNr;
  }

  /**
   * gets the serial number of the tag as a String where each byte
   * is in hex format and separated by a space.
   *
   * @return the serial number of the tag as a String.
   */
  public String getSerialWithSpace() {
    StringBuffer sb = new StringBuffer();
    for( int i = 0; i < mySerialNr.length; i++ ) {
      sb.append( hexByte( mySerialNr[i] ) + " " );
    }
    return sb.toString().trim();
  }

  /**
   * gets the serial number of the tag as a String in hex format.
   *
   * @return the serial number of the tag as a String.
   */
  public String getSerial() {
    StringBuffer sb = new StringBuffer();
    for( int i = 0; i < mySerialNr.length; i++ ) {
      sb.append( hexByte( mySerialNr[i] ) );
    }
    return sb.toString().trim();
  }


  /**
   * checks the serial number
   *
   * @param data to compared serial number
   *
   * @return if boath serial numbers are equal TRUE else FALSE
   */
  public boolean hasSNR( byte[] data ) {
    for( int i = 0; i < data.length; i++ ) {
      if( mySerialNr[i] != data[i] ) {
        return false;
      }
    }
    return true;
  }

  /**
   * checks the serial number
   *
   * @param data to compared serial number
   * @param offset start position
   * @param length lebgth of serial number
   *
   * @return if boath serial numbers are equal TRUE else FALSE
   */
  public boolean hasSNR( byte[] data, int offset, int length ) {
    for( int i = 0; i < length; i++ ) {
      if( mySerialNr[i] != data[offset + i] ) {
        return false;
      }
    }
    return true;
  }

  /**
   * clones the RFID tag.
   *
   * @return the deep copy of the RFID tag.
   *
   */
  public Object clone() {
    RFIDTools tagCopy = new RFIDTools( ( byte[] )this.mySerialNr.clone(), 0,
        this.transponterType, this.dataSotageFamilyID );
    return tagCopy;
  }

  /**
   *
   *
   * @return String
   */
  public String toString() {
    return "[" + getSerial() + "]";
  }

  /**
   * checks tags
   *
   * @param tag tag to compare
   *
   * @return if boath serial numbers are equal TRUE else FALSE
   */
  public boolean equals( Object tag ) {
    if( tag instanceof RFIDTools ) {
      return ( ( RFIDTools )tag ).hasSNR( mySerialNr );
    }
    else {
      return false;
    }
  }

  /**
   * gets the hash code of the serial number
   *
   * @return hash code
   */
  public int hashCode() {
    long v = 0;
    for ( int i = mySerialNr.length-1; i >= 0; i-- ) {
      v = 256 * v + ( ( int )mySerialNr[i] & 0xFF );
    }
    return ( int )v;
  }
}