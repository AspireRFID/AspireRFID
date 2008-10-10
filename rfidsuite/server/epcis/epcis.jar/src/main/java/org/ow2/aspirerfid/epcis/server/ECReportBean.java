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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * The ECReport Bean.
 * 
 * @author François Fornaciari
 * @version 0.1.0 2007
 */
@Entity
@Table(name = "ECREPORTS")
@NamedQuery(name = "allECReports", query = "select o from ECReportBean o")
public class ECReportBean implements Serializable {
    
    private static final long serialVersionUID = 3123357846100126375L;
    
    /**
     * The ECReport id
     */
    private int id;
    
    /**
     * The ECReport ale id
     */
    private String aleId = null;
    
    /**
     * The ECReport spec name
     */
    private String specName = null;
    
    /**
     * The ECReport gateway name
     */
    private String gatewayName = null;
    
    /**
     * The ECReport date
     */
    private Date date = null;
    
    /**
     * The ECReport total milliseconds
     */
    private long totalMilliseconds;
    
    /**
     * The ECReport termination condition
     */
    private String terminationCondition = null;
    
    /**
     * The reports associated with this ECReport
     */
    private Set<ReportBean> reports = null;
    
    /**
     * Constructor by default
     */
    public ECReportBean() {
    }
    
    /**
     * Create a ECReport
     * 
     * @param aleId
     *            The ECReport ale id
     * @param specName
     *            The ECReport spec name
     * @param gatewayName
     *            The ECReport gateway name
     * @param date
     *            The ECReport date
     * @param totalMilliseconds
     *            The ECReport termination condition
     * @param terminationCondition
     *            The ECReport termination condition
     */
    public ECReportBean(String aleId, String specName, String gatewayName,
            Date date, long totalMilliseconds, String terminationCondition) {
        this.aleId = aleId;
        this.specName = specName;
        this.gatewayName = gatewayName;
        this.date = date;
        this.totalMilliseconds = totalMilliseconds;
        this.terminationCondition = terminationCondition;
        this.reports = new HashSet<ReportBean>();
    }
    
    /**
     * Get the ECReport ale id
     * 
     * @return The ECReport ale id
     */
    public String getAleId() {
        return aleId;
    }
    
    /**
     * Set the ECReport ale id
     * 
     * @param aleId
     *            The ECReport ale id
     */
    public void setAleId(String aleId) {
        this.aleId = aleId;
    }
    
    /**
     * Get the ECReport date
     * 
     * @return the ECReport date
     */
    @Temporal(TIMESTAMP)
    public Date getDate() {
        return date;
    }
    
    /**
     * Set the ECReport date
     * 
     * @param date
     *            The ECReport date
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * Get the ECReport gateway name
     * 
     * @return The ECReport gateway name
     */
    public String getGatewayName() {
        return gatewayName;
    }
    
    /**
     * Set the ECReport gateway name
     * 
     * @param gatewayName
     */
    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
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
     * Get the reports associated with this ECReport
     * 
     * @return The reports associated with this ECReport
     */
    @OneToMany(mappedBy = "ECReport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<ReportBean> getReports() {
        return reports;
    }
    
    /**
     * Set the reports associated with this ECReport
     * 
     * @param reports
     *            The reports associated with this ECReport
     */
    public void setReports(Set<ReportBean> reports) {
        this.reports = reports;
    }
    
    /**
     * Get the ECReport spec name
     * 
     * @return The ECReport spec name
     */
    public String getSpecName() {
        return specName;
    }
    
    /**
     * Set the ECReport spec name
     * 
     * @param specName
     *            The ECReport spec name
     */
    public void setSpecName(String specName) {
        this.specName = specName;
    }
    
    /**
     * Get the ECReport termination condition
     * 
     * @return The ECReport termination condition
     */
    public String getTerminationCondition() {
        return terminationCondition;
    }
    
    /**
     * Set the ECReport termination condition
     * 
     * @param terminationCondition
     */
    public void setTerminationCondition(String terminationCondition) {
        this.terminationCondition = terminationCondition;
    }
    
    /**
     * Get the ECReport total milliseconds
     * 
     * @return The ECReport total milliseconds
     */
    public long getTotalMilliseconds() {
        return totalMilliseconds;
    }
    
    /**
     * Set the ECReport total milliseconds
     * 
     * @param totalMilliseconds
     */
    public void setTotalMilliseconds(long totalMilliseconds) {
        this.totalMilliseconds = totalMilliseconds;
    }
    
    /**
     * Add a report
     * 
     * @param report
     */
    public void addReport(ReportBean report) {
        this.reports.add(report);
    }
    
    /**
     * Remove a report
     * 
     * @param report
     */
    public void removeReport(ReportBean report) {
        this.reports.remove(report);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ECReportBean)) {
            return false;
        }
        
        ECReportBean ECReport = (ECReportBean) o;
        return getId() == ECReport.getId();
    }
}
