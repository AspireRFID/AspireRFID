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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Stateful Bean for accessing EPC data from database.
 * 
 * @author François Fornaciari, Guillaume Surrel
 * @version 0.1.0
 */
@Stateless
@Remote(RFIDDataAccessRemote.class)
@SuppressWarnings("unchecked")
public class RFIDDataAccessBean implements RFIDDataAccessRemote {
    
    /**
     * Entity manager used by this bean.
     */
    @PersistenceContext
    private EntityManager entityManager = null;
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getAllAdditions()
     */
    public List<ReportGroupListMemberBean> getAllAdditions() {
        return getAllByType("ADDITIONS");
    }
    
    /**
     * TODO Javadoc
     * 
     * @param type
     * @return
     */
    private List<ReportGroupListMemberBean> getAllByType(String type) {
        Query q = entityManager
                .createQuery("select distinct RGLM from ReportBean R join R.reportGroups RG join RG.reportGroupListMembers RGLM"
                        + " where R.reportSet=:type order by RGLM.memberDate desc");
        q.setParameter("type", type);
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getAllCurrent()
     */
    public List<ReportGroupListMemberBean> getAllCurrent() {
        return getAllByType("CURRENT");
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getAllDeletions()
     */
    public List<ReportGroupListMemberBean> getAllDeletions() {
        return getAllByType("DELETIONS");
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getAllEPC()
     */
    public List<ReportGroupListMemberBean> getAllEPC() {
        return entityManager.createNamedQuery("allReportGroupListMembers")
                .getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getECReports()
     */
    public List<ECReportBean> getECReports() {
        Query q = entityManager.createQuery("select ECR from ECReportBean ECR"
                + " order by ECR.date desc");
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getLastECReports(int)
     */
    public List<ECReportBean> getLastECReports(int maxResult) {
        Query q = entityManager.createQuery("select ECR from ECReportBean ECR"
                + " order by ECR.date desc");
        q.setMaxResults(maxResult);
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getEPCByReaderName(java.lang.String)
     */
    public List<ReportGroupListMemberBean> getEPCByReaderName(String readerName) {
        Query q = entityManager
                .createQuery("select r from ReportGroupListMemberBean r where r.readerName=:readerName order by r.memberDate desc");
        q.setParameter("readerName", readerName);
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getEPCByReportName(java.lang.String)
     */
    public List<ReportGroupListMemberBean> getEPCByReportName(String reportName) {
        Query q = entityManager
                .createQuery("select distinct RGLM from ReportBean R join R.reportGroups RG join RG.reportGroupListMembers RGLM"
                        + " where R.reportName=:name order by RGLM.memberDate desc");
        q.setParameter("name", reportName);
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getEPCHistory(java.lang.String)
     */
    public List<ReportGroupListMemberBean> getEPCHistory(String tag) {
        Query q = entityManager
                .createQuery("select r from ReportGroupListMemberBean r "
                        + "where r.tag like :tag "
                        + "order by r.memberDate desc");
        q.setParameter("tag", tag);
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getCurrentEPCHistory(java.lang.String)
     */
    public List<ReportGroupListMemberBean> getCurrentEPCHistory(String tag) {
        Query q = entityManager
                .createQuery("select r from ReportGroupListMemberBean r "
                        + "where r.tag like :tag and r.reportGroup.report.reportSet = 'CURRENT' order by r.memberDate desc");
        q.setParameter("tag", tag);
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getTemperatureEPCHistory(java.lang.String)
     */
    public List<ReportGroupListMemberBean> getTemperatureEPCHistory(String tag) {
        try {
            Query q = entityManager
                    .createQuery("select r from ReportGroupListMemberBean r join r.measurements m "
                            + "where r.tag like :tag and r.reportGroup.report.reportSet = 'CURRENT' and m.applicationName = 'temperature' and m.value > -1 order by r.memberDate desc");
            
            q.setParameter("tag", tag);
            return q.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<ReportGroupListMemberBean>();
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getEPCTemperatureAlert(java.util.Date,
     *      java.util.Date, java.lang.String, double, java.lang.String)
     */
    public List<ReportGroupListMemberBean> getEPCTemperatureAlert(
            Date dateBegin, Date dateEnd, String operator, double value,
            String readerName) {
        try {
            Query q = entityManager
                    .createQuery("select r from ReportGroupListMemberBean r join r.measurements m "
                            + "where (r.memberDate between :timeBegin and :timeEnd) "
                            + "and m.applicationName = 'temperature' and m.value  "
                            + operator
                            + " :value and r.readerName like :readerName and r.reportGroup.report.reportSet = 'CURRENT'"
                            + "order by r.memberDate desc");
            q.setParameter("timeBegin", dateBegin);
            q.setParameter("timeEnd", dateEnd);
            q.setParameter("value", value);
            q.setParameter("readerName", readerName);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Returns the information of all the data by reader name, a tag type from
     * database
     */
    public List<ReportGroupListMemberBean> getFiltrate(String readerName,
            String gatewayName, String tag) {
        Query q = entityManager
                .createQuery("select r from ReportGroupListMemberBean r "
                        + "where r.readerName like :readerName "
                        + "and r.gatewayName like :gatewayName "
                        + "and r.tag like :tag " + "order by r.memberDate desc");
        q.setParameter("readerName", readerName);
        q.setParameter("tag", tag);
        q.setParameter("gatewayName", gatewayName);
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getFiltrateDate(java.util.Date,
     *      java.util.Date)
     */
    public List<ReportGroupListMemberBean> getFiltrateDate(
            Date memberDateBegin, Date memberDateEnd) {
        Query q = entityManager
                .createQuery("select r from ReportGroupListMemberBean r "
                        + "where (r.memberDate between :timeBegin and :timeEnd) "
                        + "order by r.memberDate desc");
        q.setParameter("timeBegin", memberDateBegin);
        q.setParameter("timeEnd", memberDateEnd);
        return q.getResultList();
    }
    
    /**
     * Returns the information of tags by the time and reader name, a tag type
     */
    public List<ReportGroupListMemberBean> getFiltrateDate(String readerName,
            String gatewayName, String tag, Date memberDateBegin,
            Date memberDateEnd) {
        
        Query q = entityManager
                .createQuery("select r from ReportGroupListMemberBean r "
                        + "where r.readerName like :readerName "
                        + "and r.gatewayName like :gatewayName "
                        + "and r.tag like :tag "
                        + "and (r.memberDate between :timeBegin and :timeEnd) "
                        + "order by r.memberDate desc");
        q.setParameter("readerName", readerName);
        q.setParameter("tag", tag);
        q.setParameter("gatewayName", gatewayName);
        q.setParameter("timeBegin", memberDateBegin);
        q.setParameter("timeEnd", memberDateEnd);
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getLostTags(java.lang.String,
     *      long)
     */
    public List<ReportGroupListMemberBean> getLostTags(String reader1,
            long duration) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis() - duration);
        Query q = entityManager
                .createNativeQuery(
                        "SELECT r.id, r.epc, r.gatewayname, r.gpscoordinates, r.memberdate, r.readername, r.tag, r.report_groups_id, r.onsrecordstate"
                                + " FROM REPORT_GROUP_LIST_MEMBERS r,"
                                + " (SELECT max(memberdate) as maxdate, tag"
                                + " FROM REPORT_GROUP_LIST_MEMBERS"
                                + " GROUP BY tag) maxresults"
                                + " WHERE r.tag = maxresults.tag"
                                + " AND r.memberdate = maxresults.maxdate"
                                + " AND r.readerName = :reader AND r.memberdate < :time",
                        ReportGroupListMemberBean.class);
        q.setParameter("reader", reader1);
        q.setParameter("time", cal.getTime());
        return q.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#generalTagInformation(java.lang.String)
     */
    public TagBean generalTagInformation(String tag) {
        try {
            Query q = entityManager.createQuery("select t from TagBean t "
                    + "where t.tag = :tag");
            q.setParameter("tag", tag);
            return (TagBean) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#removeAllECReports()
     */
    public void removeAllECReports() {
        Query q = entityManager.createQuery("select e from ECReportBean e");
        List<ECReportBean> ecReports = q.getResultList();
        for (ECReportBean ecReport : ecReports) {
            removeAllReports(ecReport);
            entityManager.remove(ecReport);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#removeAllReports(org.ow2.aspirerfid.epcis.server.ECReportBean)
     */
    public void removeAllReports(ECReportBean ecReportBean) {
        Set<ReportBean> reports = ecReportBean.getReports();
        Iterator it = reports.iterator();
        while (it.hasNext()) {
            ReportBean reportBean = (ReportBean) it.next();
            removeAllReportGroups(reportBean);
            entityManager.remove(reportBean);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#removeAllReportGroups(org.ow2.aspirerfid.epcis.server.ReportBean)
     */
    public void removeAllReportGroups(ReportBean reportBean) {
        Set<ReportGroupBean> reportGroups = reportBean.getReportGroups();
        Iterator it = reportGroups.iterator();
        while (it.hasNext()) {
            ReportGroupBean reportGroupBean = (ReportGroupBean) it.next();
            removeAllReportGroupListMembers(reportGroupBean);
            entityManager.remove(reportGroupBean);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#removeAllReportGroupListMembers(org.ow2.aspirerfid.epcis.server.ReportGroupBean)
     */
    public void removeAllReportGroupListMembers(ReportGroupBean reportGroupBean) {
        Set<ReportGroupListMemberBean> reportGroupListMembers = reportGroupBean
                .getReportGroupListMembers();
        Iterator it = reportGroupListMembers.iterator();
        while (it.hasNext()) {
            ReportGroupListMemberBean reportGroupListMemberBean = (ReportGroupListMemberBean) it
                    .next();
            entityManager.remove(reportGroupListMemberBean);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getAllReaders()
     */
    public List<String> getAllReaders() {
        try {
            Query query = entityManager
                    .createQuery("select distinct r.readerName from ReportGroupListMemberBean r");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getAllGateways()
     */
    public List<String> getAllGateways() {
        Query query = entityManager
                .createQuery("select distinct r.gatewayName from ReportGroupListMemberBean r");
        return query.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#searchTag(java.lang.String)
     */
    public List<String> searchTag(String tag) {
        Query query = entityManager
                .createQuery("select distinct r.tag from ReportGroupListMemberBean r where r.tag like :tag ");
        query.setParameter("tag", tag.replace('*', '%'));
        return query.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#searchReader(java.lang.String)
     */
    public List<String> searchReader(String reader) {
        Query query = entityManager
                .createQuery("select distinct r.readerName from ReportGroupListMemberBean r where r.readerName like :reader");
        query.setParameter("reader", reader.replace('*', '%'));
        return query.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#searchGateway(java.lang.String)
     */
    public List<String> searchGateway(String gateway) {
        Query query = entityManager
                .createQuery("select distinct r.gatewayName from ReportGroupListMemberBean r where r.gatewayName like :gateway");
        query.setParameter("gateway", gateway.replace('*', '%'));
        return query.getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getCountTagEntry(java.util.Date,
     *      java.util.Date)
     */
    public Long getCountTagEntry(Date memberDateBegin, Date memberDateEnd) {
        Long result;
        try {
            Query q = entityManager
                    .createQuery("select count(r) from ReportGroupListMemberBean r "
                            + "where (r.memberDate between :timeBegin and :timeEnd)");
            q.setParameter("timeBegin", memberDateBegin);
            q.setParameter("timeEnd", memberDateEnd);
            
            result = (Long) q.getSingleResult();
            
        } catch (Exception e) {
            e.printStackTrace();
            return new Long(0);
        }
        
        return result;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote#getPartOfTagEntry(java.util.Date,
     *      java.util.Date, int, int, int, boolean)
     */
    public List<ReportGroupListMemberBean> getPartOfTagEntry(
            Date memberDateBegin, Date memberDateEnd, int offsetBegin,
            int maxResult, int sortBy, boolean desc) {
        
        final int FIELD_ID = 0;
        final int FIELD_EPC = 1;
        final int FIELD_GATEWAYNAME = 2;
        final int FIELD_READERNAME = 3;
        final int FIELD_DATE = 4;
        final int FIELD_GPS = 5;
        final int FIELD_TEMPERATURE = 6;
        final int FIELD_LIST_POSITION = 7;
        final int FIELD_TAG = 8;
        final int FIELD_INFO = 9;
        
        String orderBy;
        switch (sortBy) {
            case FIELD_ID:
                orderBy = "id";
                break;
            
            case FIELD_EPC:
                orderBy = "epc";
                break;
            
            case FIELD_GATEWAYNAME:
                orderBy = "gatewayName";
                break;
            
            case FIELD_READERNAME:
                orderBy = "readerName";
                break;
            
            case FIELD_DATE:
                orderBy = "memberDate";
                break;
            
            case FIELD_GPS:
                orderBy = "gpsCoordinates";
                break;
            
            case FIELD_TAG:
                orderBy = "tag";
                break;
            
            case FIELD_INFO:
            case FIELD_TEMPERATURE:
            case FIELD_LIST_POSITION:
            default:
                orderBy = "id";
                break;
            
        }
        
        String strDesc = "ASC";
        if (desc) {
            strDesc = "DESC";
        }
        
        Query q = entityManager
                .createQuery("SELECT r FROM ReportGroupListMemberBean r "
                        + "WHERE (r.memberDate BETWEEN :timeBegin AND :timeEnd) "
                        + "ORDER BY r." + orderBy + " " + strDesc);
        q.setParameter("timeBegin", memberDateBegin);
        q.setParameter("timeEnd", memberDateEnd);
        q.setFirstResult(offsetBegin);
        q.setMaxResults(maxResult);
        
        return q.getResultList();
    }
    
}
