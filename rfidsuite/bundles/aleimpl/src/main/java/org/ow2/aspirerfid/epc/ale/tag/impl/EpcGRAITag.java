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
 * Class to represent a GRAI format of tag
 * 
 * @author Anne Robert
 * @version 2006
 */
public class EpcGRAITag extends EPCTagImpl {
    
    // filter field not used to identify, but to help filters
    private BitSet filterValue;
    int lfv; // and its length
    
    // Field to represent the product company
    private BitSet companyPrefix;
    int lcp; // and its length
    
    // Field to represent the asset Type
    private BitSet assetType;
    int lat; // and its length
    
    // Field to represent the serial number
    private BitSet serialNumber;
    int lsn; // and its length
    
    /**
     * Constructor: parse the input guid to build the GRAI fields
     * 
     * @param binaryGuid
     *            the input guid in BitSet format
     * @param size
     *            physical format size
     * @throws EPCParseError
     *             if format is not correct
     */
    public EpcGRAITag(final BitSet binaryGuid, final int size)
            throws EPCParseError {
        // 5 fields :
        // 3 bits Filters
        // 3 bits partition
        // 20-40 bits Company Prefix
        // 24-4 bits Item Reference
        // 38/140 Serial Number
        this.binaryGuid = binaryGuid;
        this.binaryGuidLength = size;
        
        // first decode common fields (same partition for both format)
        this.decodeGlobalReturnableFileds();
        if (size == 96) {
            this.tagFormat = EPCTag.GLOBAL_RETURNABLE_96_FORMAT;
            // 14 bits for company prefix
            this.serialNumber = binaryGuid.get(58, 96);
        } else if (size == 170) {
            this.tagFormat = EPCTag.GLOBAL_RETURNABLE_170_FORMAT;
            this.serialNumber = binaryGuid.get(58, 170);
        } else {
            this.isTagUri = false;
            final EPCParseError e = new EPCParseError();
            throw e;
        }
        
        this.tagUri = EPCTagImpl.PURE_IDENT_PREFIX + "grai:"
                + EPCTagImpl.bitSet2String(this.companyPrefix, this.lcp) + "."
                + EPCTagImpl.bitSet2String(this.assetType, this.lat) + "."
                + EPCTagImpl.bitSet2String(this.serialNumber, this.lsn);
        this.epcUri = EPCTagImpl.TAG_URI_PREFIX + this.tagFormat
                + EPCTagImpl.bitSet2String(this.companyPrefix, this.lcp) + "."
                + EPCTagImpl.bitSet2String(this.assetType, this.lat) + "."
                + EPCTagImpl.bitSet2String(this.serialNumber, this.lsn);
        
        this.epcFormat = "grai";
        this.epcFields = new Vector();
        this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.filterValue,
                this.lfv)));
        this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.companyPrefix,
                this.lcp)));
        this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.assetType,
                this.lat)));
        this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.serialNumber,
                this.lsn)));
        
    }
    
    /**
     * get the fields common on both global returnable asset identification
     * format and variable with partition value (for length 96 and 170)
     * 
     * @throws EPCParseError
     *             if the format is not correct
     */
    private void decodeGlobalReturnableFileds() throws EPCParseError {
        // GRAI Partition (bit number) :
        // Part Company Prefix Location reference
        // 0 40 4
        // 1 37 7
        // 2 34 10
        // 3 30 14
        // 4 27 17
        // 5 24 20
        // 6 20 24
        final int partition = EPCTagImpl.bitSet2int(
                this.binaryGuid.get(11, 14), 3);
        switch (partition) {
            case 0: // 40 bits for Company Prefix, 4 for asset type
                this.lcp = 40;
                this.lat = 4;
                break;
            case 1: // 37 bits for Company Prefix, 7 for asset type
                this.lcp = 37;
                this.lat = 7;
                break;
            case 2: // 34 bits for Company Prefix, 10 for asset type
                this.lcp = 34;
                this.lat = 10;
                break;
            case 3: // 30 bits for Company Prefix, 14 for asset type
                this.lcp = 30;
                this.lat = 14;
                break;
            case 4: // 27 bits for Company Prefix, 17 for asset type
                this.lcp = 27;
                this.lat = 17;
                break;
            case 5: // 24 bits for Company Prefix, 20 for asset type
                this.lcp = 24;
                this.lat = 20;
                break;
            case 6: // 20 bits for Company Prefix, 24 for asset type
                this.lcp = 24;
                this.lat = 20;
                break;
            
            default:
                this.isTagUri = false;
                final EPCParseError e = new EPCParseError();
                throw e;
        }
        this.companyPrefix = this.binaryGuid.get(14, 14 + this.lcp);
        this.assetType = this.binaryGuid.get(14 + this.lcp, 14 + this.lcp
                + this.lat);
        
    }
    
}
