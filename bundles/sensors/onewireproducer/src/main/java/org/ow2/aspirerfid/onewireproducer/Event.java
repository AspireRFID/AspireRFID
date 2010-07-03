/*
   Copyright 2005-2008, Aspire
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.
**/
package org.ow2.aspirerfid.onewireproducer;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a sensor event
 * <p>This class will change for JSR 256 (Mobile Sensor API) and JSR 257 (Contactless Communications API [RFID]) available on 2005Q1. 
 * @version 0.1.0 03 Dec. 2004
 * @author Didier Donsez (Didier.Donsez@ieee.org)
 * @link http://jcp.org/en/jsr/detail?id=256
 * @link http://jcp.org/en/jsr/detail?id=257
 * 
 */
public class Event implements Serializable {
    
    /**
     * the GUID (Global Unique IDentifier) of the reader that communicate with the tag 
     * <p>example: ow:090000019F69F709 for a one-wire adapter
     *
     * Should respect the chapter 23 "Name-space Specification Version 1.0" of the release 3
     */
    private String readerGuid;
    /**
     * the GUID (Global Unique IDentifier) of the tag 
     * <p>example: ow:5A34C0000219A121 for a one-wire iButton microcan 
     *
     * Should respect the chapter 23 "Name-space Specification Version 1.0" of the release 3
     */
    private String tagGuid;
    /**
     * the type of the event
     */
    private int type;
    
    /**
     * the timestamp when the event occurs 
     */
    private long timestamp;

    /**
     * represents sensor detection
     */
    public final static int DETECTION=1;
    /**
     * represents sensor activation and calibration
     */
    public final static int ACTIVATION=2;
    /**
     * represents sensor capture
     */
    public final static int CAPTURE=4;
    /**
     * represents sensor desactivation
     */
    public final static int DESACTIVATION=8;
    
    public Event(String readerGuid, String tagGuid, int type, long timestamp) {
        this.readerGuid=readerGuid;
        this.tagGuid=tagGuid;
        this.type=type;
        this.timestamp=timestamp;
    }
    
    /**
     * @return Returns the  reader GUID.
     */
    public String getReaderGuid() {
        return readerGuid;
    }
    /**
     * @param readerGuid The reader GUID to set.
     */
    public void setReaderGuid(String readerId) {
        this.readerGuid = readerId;
    }
    /**
     * @return Returns the tag GUID.
     */
    public String getTagGuid() {
        return tagGuid;
    }
    /**
     * @param tagGuid The tag GUID to set.
     */
    public void setTagGuid(String tagId) {
        this.tagGuid = tagId;
    }
    /**
     * @return Returns the event type.
     */
    public int getType() {
        return type;
    }
    /**
     * @param type The event type to set.
     */
    public void setType(int type) {
        this.type = type;
    }
    /**
     * @return Returns the timestamp when the event occured.
     */
    public long getTimestamp() {
        return timestamp;
    }
    /**
     * @param timestamp The timestamp to set.
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    public String toString(){
        StringBuffer sb=new StringBuffer();
        sb.append("[readerGuid=").append(readerGuid);
        sb.append(",tagGuid=").append(tagGuid);
        sb.append(",timestamp=").append((new Date(timestamp)).toString());
        sb.append(",type=");
        switch (type) {
        case DETECTION:
            sb.append("DETECTION");
            break;
        case ACTIVATION:
            sb.append("ACTIVATION");
            break;
        case CAPTURE:
            sb.append("CAPTURE");
            break;
        case DESACTIVATION:
            sb.append("DESACTIVATION");
            break;
        default:
            sb.append("UNKNOWN");
        }
        sb.append(']');
        return sb.toString();
    }
}
