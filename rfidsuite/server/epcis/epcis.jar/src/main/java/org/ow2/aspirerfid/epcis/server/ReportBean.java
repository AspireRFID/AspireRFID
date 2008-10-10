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

/**
 * The Report Bean.
 * 
 * @author François Fornaciari
 * @version 0.1.0 2007
 */
@Entity
@Table(name = "REPORTS")
@NamedQuery(name = "allReports", query = "select o from ReportBean o")
public class ReportBean implements Serializable {
    
    private static final long serialVersionUID = -743339515934696162L;
    
    /**
     * The report id
     */
    private int id;
    
    /**
     * The report name
     */
    private String reportName = null;
    
    /**
     * The ecReport of this report
     */
    private ECReportBean ecReport = null;
    
    /**
     * The report groups associated with this report
     */
    private Set<ReportGroupBean> reportGroups = null;
    
    /**
     * The report set (CURRENT, ADDITIONS, DELETIONS)
     */
    private String reportSet = null;
    
    /**
     * Constructor by default
     */
    public ReportBean() {
    }
    
    /**
     * Create a report
     * 
     * @param reportName
     *            The report name
     */
    public ReportBean(String reportName, ECReportBean ecReport, String reportSet) {
        this.reportName = reportName;
        this.ecReport = ecReport;
        this.reportGroups = new HashSet<ReportGroupBean>();
        this.reportSet = reportSet;
    }
    
    /**
     * Get the primary key
     * 
     * @return The primary key
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
     * Get the report groups associated with this report
     * 
     * @return The report groups associated with this report
     */
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<ReportGroupBean> getReportGroups() {
        return reportGroups;
    }
    
    /**
     * Set the report groups associated with this report
     * 
     * @param reportGroups
     *            The report groups associated with this report
     */
    public void setReportGroups(Set<ReportGroupBean> reportGroups) {
        this.reportGroups = reportGroups;
    }
    
    /**
     * Get the report name
     * 
     * @return The report name
     */
    public String getReportName() {
        return reportName;
    }
    
    /**
     * Set the report name
     * 
     * @param reportName
     *            The report name
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    
    /**
     * Add a report group
     * 
     * @param reportGroup
     */
    public void addReportGroup(ReportGroupBean reportGroup) {
        this.reportGroups.add(reportGroup);
    }
    
    /**
     * Remove a report group
     * 
     * @param reportGroup
     */
    public void removeReportGroup(ReportGroupBean reportGroup) {
        this.reportGroups.remove(reportGroup);
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReportBean)) {
            return false;
        }
        
        ReportBean report = (ReportBean) o;
        return getId() == report.getId();
    }
    
    /**
     * Get the ECreport of this report
     * 
     * @return The ECreport of this report
     */
    @ManyToOne
    @JoinColumn(name = "ECREPORTS_ID")
    public ECReportBean getECReport() {
        return ecReport;
    }
    
    /**
     * Set the ECreport of this report
     * 
     * @param ecReport
     *            The ECreport of this report
     */
    public void setECReport(ECReportBean ecReport) {
        this.ecReport = ecReport;
    }
    
    /**
     * Get the report set
     * 
     * @return The report set
     */
    public String getReportSet() {
        return reportSet;
    }
    
    /**
     * Set the report set
     * 
     * @param reportSet
     *            The report set
     */
    public void setReportSet(String reportSet) {
        this.reportSet = reportSet;
    }
}
