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
package org.ow2.aspirerfid.epc.ale.impl;

import java.util.List;

import org.ow2.aspirerfid.epc.ale.tag.api.EPCTag;

/**
 * Class to internally represent an ALE event
 * 
 * @author Anne Robert
 * @version 2006
 */
public class ALEEvent {
    
    private EPCTag epc;
    
    private String readerGuid;
    
    private String timeStamp;
    
    private String logicalReaderName;
    
    private String readerCoordinates;
    
    private List measurements;
    
    /**
     * constructor of ALE event
     * 
     * @param epc :
     *            the naming representation of the tag identifier
     * @param readerGuid :
     *            reader identifier
     * @param timeStamp :
     *            event time
     * @param readerLogicalName
     *            the logical name of the reader
     * @param readerCoordinates
     *            the reader GPS coordinates
     * @param measurements
     *            list of measures
     */
    public ALEEvent(final EPCTag epc, final String readerGuid,
            final String timeStamp, final String readerLogicalName,
            final String readerCoordinates, final List measurements) {
        this.epc = epc;
        this.readerGuid = readerGuid;
        this.timeStamp = timeStamp;
        this.logicalReaderName = readerLogicalName;
        this.readerCoordinates = readerCoordinates;
        this.measurements = measurements;
    }
    
    /**
     * Two AleEvents are equals if the couple EPC/Reader name already exists
     * @param obj 
     * @return TODO Javadoc
     */
    public boolean equals(final Object obj) {
        if (obj instanceof ALEEvent) {
            final ALEEvent aleEvent = (ALEEvent) obj;
            return aleEvent.getEpc().equals(this.getEpc())
                    && aleEvent.getReaderGuid().equals(this.readerGuid);
        }
        return false;
    }
    
    /**
     * get epc element of ALE event
     * 
     * @return the epc of the event
     */
    public EPCTag getEpc() {
        return this.epc;
    }
    
    /**
     * get logical reader name of ALE event
     * 
     * @return the logical reader name of the event
     */
    public String getLogicalReaderName() {
        return this.logicalReaderName;
    }
    
    /**
     * @return the measurements
     */
    public List getMeasurements() {
        return this.measurements;
    }
    
    /**
     * get Coordinates reader of ALE event
     * 
     * @return the logical name of reader source of the event
     */
    public String getReaderCoordinates() {
        return this.readerCoordinates;
    }
    
    /**
     * get reader element of ALE event
     * 
     * @return the reader source of the event
     */
    public String getReaderGuid() {
        return this.readerGuid;
    }
    
    /**
     * get the time element of ALE event
     * 
     * @return the time stamp of the event
     */
    public String getTimeStamp() {
        return this.timeStamp;
    }
    
    /**
     * set epc element of ALE event
     * 
     * @param epc
     *            tag identifier
     */
    public void setEpc(final EPCTag epc) {
        this.epc = epc;
    }
    
    /**
     * set logical reader name of ALE event
     * 
     * @param logicalReaderName
     *            the logical name of source reader
     */
    public void setLogicalReaderName(final String logicalReaderName) {
        this.logicalReaderName = logicalReaderName;
    }
    
    /**
     * @param measurements
     *            the measurements to set
     */
    public void setMeasurements(final List measurements) {
        this.measurements = measurements;
    }
    
    /**
     * set Coordinates reader of ALE event
     * 
     * @param coordinates
     *            the Coordinates of source reader
     */
    public void setReaderCoordinates(final String coordinates) {
        this.readerCoordinates = coordinates;
    }
    
    /**
     * set source reader element of ALE event
     * 
     * @param readerGuid
     *            the source reader identifier
     */
    public void setReaderGuid(final String readerGuid) {
        this.readerGuid = readerGuid;
    }
    
    /**
     * get time element of ALE event
     * 
     * @param timeStamp
     *            the event time
     */
    public void setTimeStamp(final String timeStamp) {
        this.timeStamp = timeStamp;
    }
    
}
