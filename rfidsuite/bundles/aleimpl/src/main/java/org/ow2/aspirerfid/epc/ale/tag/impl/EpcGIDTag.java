/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.epc.ale.tag.impl;

import java.util.BitSet;
import java.util.Vector;

import org.ow2.aspirerfid.epc.ale.tag.api.EPCTag;

/**
 * Class to represent a GID format of tag
 * 
 * @author Anne Robert
 * @version 2006
 */
public class EpcGIDTag extends EPCTagImpl {
    
    private final BitSet serialNumber;
    private final BitSet objectClass;
    private final BitSet generalManagerNumber;
    
    /**
     * Constructor: parse the input guid to build the GID fields
     * 
     * @param binaryGuid
     *            the input guid in BitSet format
     * @throws EPCParseError
     *             if format is not correct
     */
    public EpcGIDTag(final BitSet binaryGuid) throws EPCParseError {
        // the General Identifier is composed of 3 fields:
        // General Manager Number 28 bits
        // Object Class 24 bits
        // Serial Number 36 bits
        this.binaryGuid = binaryGuid;
        this.binaryGuidLength = 96;
        
        // if (binaryGuid.length()!=96) {
        // // incorrect length for this format :
        // // should be 96 bits => 24 hexadecimal digit
        // isTagUri = false ;
        // EPCParseError e = new EPCParseError() ;
        // throw e ;
        // }
        // Get 28 bits after header for General Manager Number
        this.generalManagerNumber = binaryGuid.get(8, 36);
        // Object class : 24 bits
        this.objectClass = binaryGuid.get(36, 60);
        // Serial number: 36 nex bits.
        this.serialNumber = binaryGuid.get(60, 96);
        
        final String strgeneralManagerNumber = EPCTagImpl.bitSet2String(
                this.generalManagerNumber, 28);
        final String strobjectClass = EPCTagImpl.bitSet2String(
                this.objectClass, 24);
        final String strserialNumber = EPCTagImpl.bitSet2String(
                this.serialNumber, 36);
        
        super.tagUri = EPCTagImpl.PURE_IDENT_PREFIX + "gid-96:"
                + strgeneralManagerNumber + "." + strobjectClass + "."
                + strserialNumber;
        super.epcUri = EPCTagImpl.PURE_IDENT_PREFIX + "gid:"
                + strgeneralManagerNumber + "." + strobjectClass + "."
                + strserialNumber;
        
        this.tagFormat = EPCTag.GENERAL_IDENT_FORMAT;
        this.epcFormat = "gid";
        this.epcFields = new Vector();
        this.epcFields.add(new Long(EPCTagImpl.bitSet2long(
                this.generalManagerNumber, 28)));
        this.epcFields.add(new Long(EPCTagImpl
                .bitSet2long(this.objectClass, 24)));
        this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.serialNumber,
                36)));
    }
}
