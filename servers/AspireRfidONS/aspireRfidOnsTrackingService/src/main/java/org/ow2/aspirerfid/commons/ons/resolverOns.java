/*
 * Copyright (C) 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.commons.ons;

import org.xbill.DNS.*;

import java.net.UnknownHostException;
import java.util.StringTokenizer;

/**
 * @author Kostas Mourtzoukos (komo) e-mail: komo@ait.edu.gr
 *
 */

public class resolverOns {
//    private String onsServerIp;
    private Resolver onsResolver;

    public resolverOns(String onsServerIp) throws UnknownHostException {
        this.onsResolver = new SimpleResolver(onsServerIp);
    }

    public Record[] getAnyResultEPC(String epcid) throws TextParseException {
        Record[] records = new Record[0];
        Lookup lookup = new Lookup(epcToDnsRequest(epcid), Type.ANY);
        lookup.setResolver(onsResolver);
        lookup.setCache(null);
        records = lookup.run();
        if (lookup.getResult() == Lookup.SUCCESSFUL) {
            return records;
        } else {
            return null;
        }
    }

    public Record[] getNaptrResultEPC(String epcid) throws TextParseException {
        Record[] records = new Record[0];
        Lookup lookup = new Lookup(epcToDnsRequest(epcid), Type.NAPTR);
        lookup.setResolver(onsResolver);
        lookup.setCache(null);
        records = lookup.run();
        if (lookup.getResult() == Lookup.SUCCESSFUL) {
            return records;
        } else {
            return null;
        }
    }

    public Record[] getNSResultEPC(String epcid) throws TextParseException {
        Record[] records = new Record[0];
        Lookup lookup = new Lookup(epcToDnsRequest(epcid), Type.NS);
        lookup.setResolver(onsResolver);
        lookup.setCache(null);
        records = lookup.run();
        if (lookup.getResult() == Lookup.SUCCESSFUL) {
            return records;
        } else {
            return null;
        }
    }

    public Record[] getAnyResult(String dnsrec, int resType) throws TextParseException {
        Record[] records = new Record[0];
        Lookup lookup = new Lookup(dnsrec, resType);
        lookup.setResolver(onsResolver);
        lookup.setCache(null);
        records = lookup.run();
        if (lookup.getResult() == Lookup.SUCCESSFUL) {
            return records;
        } else {
            return null;
        }
    }
    
    private String epcToDnsRequest(String epc){
    	String token;
    	String result;
    	StringTokenizer st = new StringTokenizer(epc, ":");
    	token = st.nextToken(); // urn
    	token = st.nextToken(); // epc
    	token = st.nextToken(); // id
    	result = "." + st.nextToken() + ".id.onsepc.com"; // epc type
    	token = st.nextToken(); // last part
    	
    	st = new StringTokenizer(token,".");
    	result = "." + st.nextToken() + result; // company prefix
    	result = st.nextToken() + result; // epc class
    	return result;
    }
}
