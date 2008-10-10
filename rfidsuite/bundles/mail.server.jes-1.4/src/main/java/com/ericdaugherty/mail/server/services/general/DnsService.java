/******************************************************************************
 * $Workfile: DnsService.java $
 * $Revision: 1.2 $
 * $Author: edaugherty $
 * $Date: 2003/10/01 19:30:24 $
 *
 ******************************************************************************
 * This program is a 100% Java Email Server.
 ******************************************************************************
 * Copyright (C) 2001, Eric Daugherty
 * All rights reserved.
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 ******************************************************************************
 * For current versions and more information, please visit:
 * http://www.ericdaugherty.com/java/mail
 * 
 * or contact the author at:
 * java@ericdaugherty.com
 * 
 ******************************************************************************
 * This program is based on the CSRMail project written by Calvin Smith.
 * http://crsemail.sourceforge.net/
 *****************************************************************************/

package com.ericdaugherty.mail.server.services.general;

//DNSLib imports
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;
import org.xbill.DNS.dns;

/**
 * This class provides an interface to DNS Lookup implementations.
 * <p>
 * The current version of this class depends on the org.xbill.DNS libraries
 * to perform DNS Lookups.
 * 
 * @author Eric Daugherty
 */
public class DnsService {

    //***************************************************************
    // Public Interface
    //***************************************************************

    /**
     * This method returns an array of addresses that represent MX Entries
     * for the specified domain.  If no MX entries were found for the
     * specified domain, and empty array is returned.
     * 
     * @param domain the domain perform the MX Lookup on.
     */
    public static String[] getMXEntries( String domain ) {
        
        Record[] records = dns.getRecords( domain, Type.MX );

        //Just return an empty array if no domains were found.
        if( records == null ) {
            return new String[0];
        }

        int numEntries = records.length;
        
        String[] addresses = new String[numEntries];
        
        for( int index = 0; index < numEntries; index++ ) {
         
            addresses[index] = ( (MXRecord)records[index] ).getTarget().toString();
        }
        
        return addresses;
        
    }
}
//EOF