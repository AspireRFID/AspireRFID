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

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * The ReportGroupListMember Bean.
 * 
 * @author François Fornaciari
 * @version 0.1.0 2007
 */
@Entity
@Table(name = "REPORT_GROUP_LIST_MEMBERS")
@NamedQuery(name = "allReportGroupListMembers", query = "select o from ReportGroupListMemberBean o order by o.memberDate desc")
public class ReportGroupListMemberBean implements Serializable {
    
    private static final long serialVersionUID = -1733142420598199406L;
    
    public final static int TO_RECORD_ON_ONS = 0;
    
    public final static int NOT_TO_RECORD_ON_ONS = 1;
    
    /**
     * The report group list member epc
     */
    private String epc;
    
    /**
     * The report group list member tag
     */
    private String tag;
    
    /**
     * The report group list member gateway name
     */
    private String gatewayName;
    
    /**
     * The report group list member reader name
     */
    private String readerName;
    
    /**
     * The report group list member date
     */
    private Date memberDate;
    
    /**
     * The report group list member gps coordinates
     */
    private String gpsCoordinates;
    
    /**
     * The measurements when the epc was read
     */
    private Set<MeasurementBean> measurements;
    
    /**
     * The report group list member id
     */
    private int id;
    
    /**
     * The associated report group for this report group list member
     */
    private ReportGroupBean reportGroup;
    
    private int onsRecordState;
    
    /**
     * Constructor by default
     */
    public ReportGroupListMemberBean() {
    }
    
    /**
     * Construct a ReportGroupListMember
     * 
     * @param epc
     *            The report group list member epc
     * @param tag
     *            The report group list member tag
     * @param gatewayName
     *            The report group list member gateway name
     * @param readerName
     *            The report group list member reader name
     * @param memberDate
     *            The report group list member date
     * @param gpsCoordinate
     *            The report group list member gps coordinate
     */
    public ReportGroupListMemberBean(String epc, String tag,
            String gatewayName, String readerName, Date memberDate,
            String gpsCoordinate, ReportGroupBean reportGroup,
            int onsRecordState) {
        this.epc = epc;
        this.tag = tag;
        this.gatewayName = gatewayName;
        this.readerName = readerName;
        this.memberDate = memberDate;
        this.gpsCoordinates = gpsCoordinate;
        this.reportGroup = reportGroup;
        this.measurements = new HashSet<MeasurementBean>();
        this.onsRecordState = onsRecordState;
    }
    
    /**
     * Get the report group list member epc
     * 
     * @return The report group list member epc
     */
    public String getEpc() {
        return epc;
    }
    
    /**
     * Set the report group list member epc
     * 
     * @param epc
     *            The report group list member epc
     */
    public void setEpc(String epc) {
        this.epc = epc;
    }
    
    /**
     * Get the report group list member gateway name
     * 
     * @return The report group list member gateway name
     */
    public String getGatewayName() {
        return gatewayName;
    }
    
    /**
     * Set the report group list member gateway name
     * 
     * @param gatewayName
     *            The report group list member gateway name
     */
    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }
    
    /**
     * Get the report group list member gps coordinate
     * 
     * @return The report group list member gps coordinate
     */
    public String getGpsCoordinates() {
        return gpsCoordinates;
    }
    
    /**
     * Set the report group list member gps coordinate
     * 
     * @param gpsCoordinate
     *            The report group list member gps coordinate
     */
    public void setGpsCoordinates(String gpsCoordinate) {
        this.gpsCoordinates = gpsCoordinate;
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
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Get the report group list member date
     * 
     * @return The report group list member date
     */
    @Temporal(TIMESTAMP)
    public Date getMemberDate() {
        return memberDate;
    }
    
    /**
     * Set the report group list member date
     * 
     * @param memberDate
     *            The report group list member date
     */
    public void setMemberDate(Date memberDate) {
        this.memberDate = memberDate;
    }
    
    /**
     * Get the report group list member reader name
     * 
     * @return The report group list member reader name
     */
    public String getReaderName() {
        return readerName;
    }
    
    /**
     * Set the report group list member reader name
     * 
     * @param readerName
     *            The report group list member reader name
     */
    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }
    
    /**
     * Get the associated report group for this report group list member
     * 
     * @return The associated report group for this report group list member
     */
    @ManyToOne
    @JoinColumn(name = "REPORT_GROUPS_ID")
    public ReportGroupBean getReportGroup() {
        return reportGroup;
    }
    
    /**
     * Set the associated report group for this report group list member
     * 
     * @param reportGroup
     *            The associated report group for this report group list member
     */
    public void setReportGroup(ReportGroupBean reportGroup) {
        this.reportGroup = reportGroup;
    }
    
    /**
     * Get the report group list member tag
     * 
     * @return The report group list member tag
     */
    public String getTag() {
        return tag;
    }
    
    /**
     * Set the report group list member tag
     * 
     * @param tag
     *            The report group list member tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReportGroupListMemberBean)) {
            return false;
        }
        
        ReportGroupListMemberBean reportGroupListMember = (ReportGroupListMemberBean) o;
        return getId() == reportGroupListMember.getId();
    }
    
    /**
     * Returns the report list member measurements
     * 
     * @return The report list member measurements
     */
    @OneToMany(mappedBy = "reportGroupListMember", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<MeasurementBean> getMeasurements() {
        return this.measurements;
    }
    
    /**
     * Sets the report list member measurements
     * 
     * @param measurements
     *            The report list member measurements
     */
    public void setMeasurements(Set<MeasurementBean> measurements) {
        this.measurements = measurements;
    }
    
    public void addMeasurement(MeasurementBean measurement) {
        this.measurements.add(measurement);
    }
    
    /**
     * Returns the report list member ONS record state
     * 
     * @return Sets the report list member ONS record state
     */
    public int getOnsRecordState() {
        return onsRecordState;
    }
    
    /**
     * Sets the report list member ONS record state
     * 
     * @param onsRecordState
     *            The report list member ONS record state
     */
    public void setOnsRecordState(int onsRecordState) {
        this.onsRecordState = onsRecordState;
    }
    
    /**
     * Retrieve a specific measurement
     * 
     * @param name
     *            the unique application name
     * @return A specific measurement
     */
    public MeasurementBean getMeasurement(String name) {
        for (MeasurementBean measurement : measurements) {
            if (measurement.getApplicationName().equals(name)) {
                return measurement;
            }
        }
        return null;
    }
}
