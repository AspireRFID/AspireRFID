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
 * Class to represent a SSCC format of tag
 * 
 * @author Anne Robert
 * @version 2006
 */
public class EpcSSCCTag extends EPCTagImpl {
    
    // filter field not used to identify, but to help filters
    private BitSet filterValue;
    int lfv; // and its length
    
    // Field to represent the product company
    private BitSet companyPrefix;
    int lcp; // and its length
    
    // serial number of the product
    private BitSet serialReference;
    int lsr; // and its length
    
    /**
     * Constructor: parse the input guid to build the SSCC fields
     * 
     * @param binaryGuid
     *            the input guid in BitSet format
     * @param size
     *            physical format size
     * @throws EPCParseError
     *             if format is not correct
     */
    public EpcSSCCTag(final BitSet binaryGuid, final int size)
            throws EPCParseError {
        // 5 fields :
        // 3 bits Filters
        // 3 bits partition
        // 20-40 bits Company Prefix
        // 24-4 bits Item Reference
        // 38/140 Serial Number
        this.binaryGuid = binaryGuid;
        this.binaryGuidLength = size;
        
        if (size == 64) {
            this.tagFormat = "sscc-64";
            // 14 bits for company prefix
            this.companyPrefix = binaryGuid.get(11, 25);
            // 39 bits for serial reference
            this.serialReference = binaryGuid.get(25, 64);
        } else if (size == 96) {
            this.tagFormat = EPCTag.SERIAL_SHIPPING_FORMAT;
            this.serialShipping96Decode();
        } else {
            this.isTagUri = false;
            final EPCParseError e = new EPCParseError();
            throw e;
        }
        
        this.tagUri = EPCTagImpl.PURE_IDENT_PREFIX + "sscc:"
                + EPCTagImpl.bitSet2String(this.companyPrefix, this.lcp) + "."
                + EPCTagImpl.bitSet2String(this.serialReference, this.lsr);
        
        this.epcFormat = "sscc";
        this.epcFields = new Vector();
        this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.filterValue,
                this.lfv)));
        this.epcFields.add(new Long(EPCTagImpl.bitSet2long(this.companyPrefix,
                this.lcp)));
        this.epcFields.add(new Long(EPCTagImpl.bitSet2long(
                this.serialReference, this.lsr)));
        
    }
    
    /**
     * get the fields common on both serialized global trade item format and
     * variable with partition value (for length 96 and 198)
     * 
     * @throws EPCParseError
     *             is the format does not correspond to SGTIN format
     */
    private void serialShipping96Decode() throws EPCParseError {
        // 5 fields :
        // 3 bits Filters
        // 3 bits partition
        // 20-40 bits Company Prefix
        // 38-18 bits Serial Reference
        if (this.binaryGuid.length() != 96) {
            // incorrect length for this format :
            // should be 96 bits => 24 hexadecimal digit
            this.isTagUri = false;
            final EPCParseError e = new EPCParseError();
            throw e;
        }
        this.filterValue = this.binaryGuid.get(8, 11);
        this.lfv = 3;
        final int partition = EPCTagImpl.bitSet2int(
                this.binaryGuid.get(11, 14), 3);
        // SSCC Partition (bit number) :
        // Part Company Prefix serial reference
        // 0 40 17
        // 1 37 20
        // 2 34 24
        // 3 30 27
        // 4 27 30
        // 5 24 34
        // 6 20 37
        switch (partition) {
            case 0: // 40 bits for Company Prefix, 17 for serial reference
                this.companyPrefix = this.binaryGuid.get(14, 54);
                this.serialReference = this.binaryGuid.get(54, 71);
                break;
            case 1: // 37 bits for Company Prefix, 20 for serial reference
                this.companyPrefix = this.binaryGuid.get(14, 51);
                this.serialReference = this.binaryGuid.get(51, 71);
                break;
            case 2: // 34 bits for Company Prefix, 24 for serial reference
                this.companyPrefix = this.binaryGuid.get(14, 48);
                this.serialReference = this.binaryGuid.get(48, 72);
                break;
            case 3: // 30 bits for Company Prefix, 27 for serial reference
                this.companyPrefix = this.binaryGuid.get(14, 44);
                this.serialReference = this.binaryGuid.get(44, 71);
                break;
            case 4: // 27 bits for Company Prefix, 30 for serial reference
                this.companyPrefix = this.binaryGuid.get(14, 41);
                this.serialReference = this.binaryGuid.get(41, 71);
                break;
            case 5: // 24 bits for Company Prefix, 34 for serial reference
                this.companyPrefix = this.binaryGuid.get(14, 38);
                this.serialReference = this.binaryGuid.get(38, 72);
                break;
            case 6: // 20 bits for Company Prefix, 37 for serial reference
                this.companyPrefix = this.binaryGuid.get(14, 34);
                this.serialReference = this.binaryGuid.get(34, 71);
                break;
            
            default:
                this.isTagUri = false;
                final EPCParseError e = new EPCParseError();
                throw e;
        }
        
    }
    
}
