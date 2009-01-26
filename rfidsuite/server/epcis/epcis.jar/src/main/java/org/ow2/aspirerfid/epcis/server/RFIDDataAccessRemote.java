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

import java.util.Date;
import java.util.List;

/**
 * Remote interface for the session bean RFIDDataAccessBean.
 * 
 * @author François Fornaciari, Guillaume Surrel
 * @version 0.1.0 2007
 */
public interface RFIDDataAccessRemote {
    
    /**
     * Gets all data from ADDITIONS reports
     */
    public List<ReportGroupListMemberBean> getAllAdditions();
    
    /**
     * Gets all data from CURRENT reports
     */
    public List<ReportGroupListMemberBean> getAllCurrent();
    
    /**
     * Gets all data from DELETIONS reports
     */
    public List<ReportGroupListMemberBean> getAllDeletions();
    
    /**
     * Gets all data from database
     */
    public List<ReportGroupListMemberBean> getAllEPC();
    
    /**
     * Gets all reports
     * 
     * @return all reports stored in the database
     */
    public List<ECReportBean> getECReports();
    
    /**
     * Gets a limited number of ECReports
     * 
     * @param maxResult
     *            the maximum number of results to retrieve
     */
    public List<ECReportBean> getLastECReports(int maxResult);
    
    /**
     * Gets data by given reader name from database
     * 
     * @param readerName
     */
    public List<ReportGroupListMemberBean> getEPCByReaderName(String readerName);
    
    /**
     * Gets all epcs from reports of the specified name
     * 
     * @param reportName
     *            the name of the report
     */
    public List<ReportGroupListMemberBean> getEPCByReportName(String reportName);
    
    /**
     * @param tag
     * @return all instances of the specified tag stored in the database
     */
    public List<ReportGroupListMemberBean> getEPCHistory(String tag);
    
    /**
     * @param tag
     * @return current instances of the specified tag stored in the database
     */
    public List<ReportGroupListMemberBean> getTemperatureEPCHistory(String tag);
    
    /**
     * @param tag
     * @return current instances of the specified tag stored in the database
     */
    public List<ReportGroupListMemberBean> getCurrentEPCHistory(String tag);
    
    /**
     * @param dateBegin
     *            the beginning date
     * @param dateEnd
     *            the stopping date
     * @param operator
     *            the operator
     * @param value
     *            the boundary value
     * @param readerName
     *            the reader logical name
     * @return the list of epc exceeding the boundary value respecting the given
     *         operator
     */
    public List<ReportGroupListMemberBean> getEPCTemperatureAlert(
            Date dateBegin, Date dateEnd, String operator, double value,
            String readerName);
    
    /**
     * Gets data by given attributes from database
     * 
     * @param readerName
     * @param gatewayName
     * @param tag
     */
    public List<ReportGroupListMemberBean> getFiltrate(String readerName,
            String gatewayName, String tag);
    
    /**
     * @param dateBegin
     * @param dateEnd
     */
    public List<ReportGroupListMemberBean> getFiltrateDate(Date dateBegin,
            Date dateEnd);
    
    /**
     * Gets data by given attributes from database
     * 
     * @param readerName
     * @param gatewayName
     * @param tag
     * @param dateBegin
     * @param dateEnd
     */
    public List<ReportGroupListMemberBean> getFiltrateDate(String readerName,
            String gatewayName, String tag, Date dateBegin, Date dateEnd);
    
    /**
     * Lost objects are objects which have last been seen by one reader and
     * never seen by another when at least duration has elapsed
     * 
     * @param reader
     *            the reader logical name
     * @param duration
     *            the duration before objects can be called "lost" in ms
     * @return The list of lost objects.
     */
    public List<ReportGroupListMemberBean> getLostTags(String reader,
            long duration);
    
    /**
     * Returns information about the requested tag.
     * 
     * @param tag
     *            The requested tag
     * @return Information about the requested tag
     */
    public TagBean generalTagInformation(String tag);
    
    /**
     * Remove all event cycle reports
     */
    public void removeAllECReports();
    
    /**
     * Remove all reports contained in an event cycle report
     * 
     * @param ecReportBean
     */
    public void removeAllReports(ECReportBean ecReportBean);
    
    /**
     * Remove all report groups contained in a report
     * 
     * @param reportBean
     */
    public void removeAllReportGroups(ReportBean reportBean);
    
    /**
     * Remove all report group list members contained in a report group
     * 
     * @param reportGroupBean
     */
    public void removeAllReportGroupListMembers(ReportGroupBean reportGroupBean);
    
    /**
     * Get all readers which are sent a ReportGroupListMember to the EPC IS
     * 
     * @return all readers see by the server
     */
    public List<String> getAllReaders();
    
    /**
     * Get all edge and premise see by the server
     * 
     * @return all edge and premise name
     */
    public List<String> getAllGateways();
    
    /**
     * Search tag, wildcard '*' and '%' can be used
     * 
     * @param tag
     *            tag pattern
     * @return list of tag matching with the tag pattern
     */
    public List<String> searchTag(String tag);
    
    /**
     * Search reader, wildcard '*' and '%' can be used
     * 
     * @param reader
     *            reader pattern
     * @return list of reader matching with the pattern
     */
    public List<String> searchReader(String reader);
    
    /**
     * Search gateway (premise+edge), wildcard '*' and '%' can be used
     * 
     * @param gateway
     *            gateway pattern
     * @return list of gateway matching with the pattern
     */
    public List<String> searchGateway(String gateway);
    
    /**
     * Return the number of tag entry in data base
     * 
     * @return number of tag in data base
     */
    public Long getCountTagEntry(Date memberDateBegin, Date memberDateEnd);
    
    /**
     * Get a sub part of tag entry
     * 
     * @param offsetBegin
     *            beginning offset
     * @param maxResult
     *            max result
     * @param sortBy
     *            the list is sort by field
     * @return
     */
    public List<ReportGroupListMemberBean> getPartOfTagEntry(
            Date memberDateBegin, Date memberDateEnd, int offsetBegin,
            int maxResult, int sortBy, boolean desc);
    
}
