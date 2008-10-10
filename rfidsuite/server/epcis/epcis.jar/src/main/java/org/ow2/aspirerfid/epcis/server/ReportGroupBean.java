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
 * The ReportGroup Bean.
 * 
 * @author François Fornaciari
 * @version 0.1.0 2007
 */
@Entity
@Table(name = "REPORT_GROUPS")
@NamedQuery(name = "allReportGroups", query = "select o from ReportGroupBean o")
public class ReportGroupBean implements Serializable {
    
    private static final long serialVersionUID = 4409665747307507117L;
    
    /**
     * The report group id
     */
    private int id;
    
    /**
     * The report group name
     */
    private String groupName = null;
    
    /**
     * The report group date
     */
    private String groupDate = null;
    
    /**
     * The report group count
     */
    private int groupCount;
    
    /**
     * The report associated to this report group
     */
    private ReportBean report = null;
    
    /**
     * The report group list members for this report group
     */
    private Set<ReportGroupListMemberBean> reportGroupListMembers = null;
    
    /**
     * Constructor by default
     */
    public ReportGroupBean() {
    }
    
    /**
     * Create a report group
     * 
     * @param groupName
     *            The report group name
     * @param groupDate
     *            The report group date
     * @param groupCount
     *            The report group count
     */
    public ReportGroupBean(String groupName, String groupDate, int groupCount,
            ReportBean report) {
        this.groupName = groupName;
        this.groupDate = groupDate;
        this.groupCount = groupCount;
        this.report = report;
        this.reportGroupListMembers = new HashSet<ReportGroupListMemberBean>();
    }
    
    /**
     * Get the report group count
     * 
     * @return The report group count
     */
    public int getGroupCount() {
        return groupCount;
    }
    
    /**
     * Set the report group count
     * 
     * @param groupCount
     *            The report group count
     */
    public void setGroupCount(int groupCount) {
        this.groupCount = groupCount;
    }
    
    /**
     * Get the report group date
     * 
     * @return The report group date
     */
    public String getGroupDate() {
        return groupDate;
    }
    
    /**
     * Set the report group date
     * 
     * @param groupDate
     *            The report group date
     */
    public void setGroupDate(String groupDate) {
        this.groupDate = groupDate;
    }
    
    /**
     * Get the report group name
     * 
     * @return The report group name
     */
    public String getGroupName() {
        return groupName;
    }
    
    /**
     * Set the report group name
     * 
     * @param groupName
     *            The report group name
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
     * Get the report associated to this report group
     * 
     * @return The report associated to this report group
     */
    @ManyToOne
    @JoinColumn(name = "REPORTS_ID")
    public ReportBean getReport() {
        return report;
    }
    
    /**
     * Set the report associated to this report group
     * 
     * @param report
     *            The report associated to this report group
     */
    public void setReport(ReportBean report) {
        this.report = report;
    }
    
    /**
     * Get the report group list members for this report group
     * 
     * @return The report group list members for this report group
     */
    @OneToMany(mappedBy = "reportGroup", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<ReportGroupListMemberBean> getReportGroupListMembers() {
        return reportGroupListMembers;
    }
    
    /**
     * Set the report group list members for this report group
     * 
     * @param reportGroupListMembers
     *            The report group list members for this report group
     */
    public void setReportGroupListMembers(
            Set<ReportGroupListMemberBean> reportGroupListMembers) {
        this.reportGroupListMembers = reportGroupListMembers;
    }
    
    /**
     * Add a report group list member
     * 
     * @param reportGroupListMember
     */
    public void addReportGroupListMember(
            ReportGroupListMemberBean reportGroupListMember) {
        this.reportGroupListMembers.add(reportGroupListMember);
    }
    
    /**
     * Remove a report group list member
     * 
     * @param reportGroupListMember
     */
    public void removeReportGroupListMember(
            ReportGroupListMemberBean reportGroupListMember) {
        this.reportGroupListMembers.remove(reportGroupListMember);
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReportGroupBean)) {
            return false;
        }
        
        ReportGroupBean reportGroup = (ReportGroupBean) o;
        return getId() == reportGroup.getId();
    }
}
