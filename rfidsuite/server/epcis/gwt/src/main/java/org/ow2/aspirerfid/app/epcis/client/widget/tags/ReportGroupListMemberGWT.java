/*
 * Copyright 2005-2008, Aspire This library is free software; you can
 * redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation (the "LGPL");
 * either version 2.1 of the License, or (at your option) any later version. If
 * you do not alter this notice, a recipient may use your version of this file
 * under either the LGPL version 2.1, or (at his option) any later version. You
 * should have received a copy of the GNU Lesser General Public License along
 * with this library; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA. This software is
 * distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express
 * or implied. See the GNU Lesser General Public License for the specific
 * language governing rights and limitations.
 */
package org.ow2.aspirerfid.app.epcis.client.widget.tags;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class ReportGroupListMemberGWT implements Serializable {
    
    private static final long serialVersionUID = 4762081927359559963L;
    
    private String tag;
    
    private String epc;
    
    private String gatewayName;
    
    private String readerName;
    
    private Date memberDate;
    
    private String gpsCoordinate;
    
    private double temperature;
    
    private String additionalInfo;
    
    private int id;
    
    /**
     * TODO Javadoc
     */
    public ReportGroupListMemberGWT() {
        
    }
    
    /**
     * TODO Javadoc
     * 
     * @param tag
     * @param epc
     * @param gatewayName
     * @param readerName
     * @param memberDate
     * @param gpsCoordinate
     * @param temperature
     * @param id
     */
    public ReportGroupListMemberGWT(String tag, String epc, String gatewayName,
            String readerName, Date memberDate, String gpsCoordinate,
            double temperature, int id) {
        this.epc = epc;
        this.tag = tag;
        this.gatewayName = gatewayName;
        this.readerName = readerName;
        this.memberDate = memberDate;
        this.gpsCoordinate = gpsCoordinate;
        this.temperature = temperature;
        this.id = id;
        this.additionalInfo = "";
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getEpc() {
        return epc;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getGatewayName() {
        return gatewayName;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getGpsCoordinate() {
        return gpsCoordinate;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public int getId() {
        return id;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public Date getMemberDate() {
        return memberDate;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getReaderName() {
        return readerName;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public double getTemperature() {
        return temperature;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getTag() {
        return tag;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getAdditionalInfo() {
        return additionalInfo;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param additionalInfo
     */
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    
}
