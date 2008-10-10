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
package org.ow2.aspirerfid.epcis.server;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The Measurement Bean.
 * 
 * @author François Fornaciari
 * @version 0.1.0 2007
 */
@Entity
@Table(name = "MEASUREMENTS")
@NamedQuery(name = "allMeasurements", query = "select o from MeasurementBean o")
public class MeasurementBean implements Serializable {
    private static final long serialVersionUID = 8475092828170850277L;
    
    private int id;
    
    private String applicationName;
    
    private double error;
    
    private String sensorId;
    
    private long timestamp;
    
    private String unit;
    
    private double value;
    
    private ReportGroupListMemberBean reportGroupListMember;
    
    /**
     * TODO Javadoc
     */
    public MeasurementBean() {
    }
    
    /**
     * @param applicationName
     * @param error
     * @param sensorId
     * @param timestamp
     * @param unit
     * @param value
     */
    public MeasurementBean(String applicationName, double error,
            String sensorId, long timestamp, String unit, double value,
            ReportGroupListMemberBean reportGroupListMember) {
        this.applicationName = applicationName;
        this.error = error;
        this.sensorId = sensorId;
        this.timestamp = timestamp;
        this.unit = unit;
        this.value = value;
        this.reportGroupListMember = reportGroupListMember;
    }
    
    /**
     * Get the primary key
     * 
     * @return the primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    
    /**
     * Set the primary key
     * 
     * @param id
     *            The primary key
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getApplicationName() {
        return applicationName;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param applicationName
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public double getError() {
        return error;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param error
     */
    public void setError(double error) {
        this.error = error;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getSensorId() {
        return sensorId;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param sensorId
     */
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public long getTimestamp() {
        return timestamp;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getUnit() {
        return unit;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public double getValue() {
        return value;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "REPORT_GROUP_LIST_MEMBERS_ID")
    public ReportGroupListMemberBean getReportGroupListMember() {
        return reportGroupListMember;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param reportGroupListMember
     */
    public void setReportGroupListMember(
            ReportGroupListMemberBean reportGroupListMember) {
        this.reportGroupListMember = reportGroupListMember;
    }
    
}
