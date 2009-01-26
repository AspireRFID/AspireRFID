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

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.xmlbeans.XmlException;

import org.ow2.aspirerfid.ale.ECMeasurementList;
import org.ow2.aspirerfid.ale.ECReport;
import org.ow2.aspirerfid.ale.ECReportGroup;
import org.ow2.aspirerfid.ale.ECReportGroupCount;
import org.ow2.aspirerfid.ale.ECReportGroupCountExtension;
import org.ow2.aspirerfid.ale.ECReportGroupList;
import org.ow2.aspirerfid.ale.ECReportGroupListMember;
import org.ow2.aspirerfid.ale.ECReportGroupListMemberExtension;
import org.ow2.aspirerfid.ale.ECReports;
import org.ow2.aspirerfid.ale.ECReportsDocument;
import org.ow2.aspirerfid.ale.ECReportsExtension;
import org.ow2.aspirerfid.ale.ECTerminationCondition.Enum;
import org.ow2.aspirerfid.epcglobal.EPC;

/**
 * Stateless Bean for communicating with other entity bean.
 * 
 * @author François Fornaciari
 * @version 2007
 */
@Stateless
@Local(SaveReportsLocal.class)
public class SaveReportsBean implements SaveReportsLocal {
    
    /**
     * Entity manager used by this bean.
     */
    @PersistenceContext
    private EntityManager entityManager = null;
    
    /**
     * To save the data into the database
     * 
     * @param data
     *            the string
     */
    public boolean saveData(String data) {
        // get ECReport
        ECReportsDocument reportDoc = null;
        
        try {
            // get reportDoc object from the xml string
            reportDoc = ECReportsDocument.Factory.parse(data);
        } catch (XmlException e) {
            System.err
                    .println("Exception during the parsing of the XML Report");
            return false;
        }
        // get the ECReports object from the ReportDoc
        ECReports report = reportDoc.getECReports();
        if (report == null) {
            System.out.println("No reports");
            return false;
        }
        // save the information of ECReport
        ECReportBean ecReport = saveECReport(report);
        // save the information of Report
        saveReport(report, ecReport);
        
        return true;
    }
    
    /**
     * To save the attributes of ECReports into the database
     * 
     * @param report
     *            the object of ECReports
     */
    private ECReportBean saveECReport(ECReports report) {
        String gatewayName = null;
        // get parameter of the ECReport
        String specName = report.getSpecName();
        // get the gateway name from the extension of the ECReports
        ECReportsExtension reportExtension = report.getExtension();
        if (reportExtension != null) {
            gatewayName = reportExtension.getGatewayName();
        } else {
            gatewayName = "";
        }
        
        String aleId = report.getALEID();
        
        // get the data of the ECReport
        Date creationDate = null;
        Calendar creatDate = report.getDate();
        if (creatDate != null) {
            creationDate = creatDate.getTime();
        }
        // get the totalMilliseconds from the informations of ECReport
        long totalMilliseconds = report.getTotalMilliseconds();
        // get the terminationCondition from the informations of ECReport
        Enum enu = report.getTerminationCondition();
        String terminationCondition = "";
        if (enu != null) {
            switch (enu.intValue()) {
                case 1:
                    terminationCondition = "TRIGGER";
                    break;
                case 2:
                    terminationCondition = "DURATION";
                    break;
                case 3:
                    terminationCondition = "STABLE_SET";
                    break;
                case 4:
                    terminationCondition = "UNREQUEST";
                    break;
            }
        }
        
        ECReportBean ecReport = null;
        
        try {
            // Save the ECReport into database
            ecReport = new ECReportBean(aleId, specName, gatewayName,
                    creationDate, totalMilliseconds, terminationCondition);
            entityManager.persist(ecReport);
            
        } catch (Exception e) {
            System.err.println("Cannot create ECReport");
        }
        return ecReport;
    }
    
    /**
     * To save the content of ECReports: report into the database
     * 
     * @param ecReports
     *            the object of ECReports
     */
    private void saveReport(ECReports ecReports, ECReportBean ecReport) {
        // get the reports infomations
        ECReport[] reports = (ECReport[]) ecReports.getReports()
                .getReportArray();
        // Put the reports informations into database
        for (int i = 0; i < reports.length; i++) {
            String reportName = ((ECReport) reports[i]).getReportName();
            String reportSet = ((ECReport) reports[i]).getReportSet();
            ReportBean report = new ReportBean(reportName, ecReport, reportSet);
            entityManager.persist(report);
            ecReport.addReport(report);
            
            saveReportGroup(reports[i], report, ecReport.getGatewayName());
        }
    }
    
    /**
     * To save the reportGroup into the database
     * 
     * @param report
     *            the object of ECReport
     */
    private void saveReportGroup(ECReport ecReport, ReportBean report,
            String gatewayName) {
        int groupCount = -1;
        String groupDate = "";
        // get the report group array
        ECReportGroup[] reportGroups = ecReport.getGroupArray();
        for (int i = 0; i < reportGroups.length; i++) {
            // get the group name
            String groupName = reportGroups[i].getGroupName();
            // if the ECReportGroup has group count, get the group count
            ECReportGroupCount reportGroupCount = reportGroups[i]
                    .getGroupCount();
            if (reportGroupCount != null) {
                groupCount = reportGroupCount.getCount();
                // if the groupcount has extension, get his extension
                ECReportGroupCountExtension countExtension = reportGroupCount
                        .getExtension();
                if (countExtension != null) {
                    // get the group date
                    groupDate = countExtension.getDate().getTime().toString();
                }
            }
            // Save the informations into database
            ReportGroupBean reportGroup = new ReportGroupBean(groupName,
                    groupDate, groupCount, report);
            entityManager.persist(reportGroup);
            report.addReportGroup(reportGroup);
            
            // get the list of the groupList
            ECReportGroupList groupList = reportGroups[i].getGroupList();
            if (groupList != null) {
                // if the groupList isn't null, call the method
                // saveReportGroupListMember
                saveReportGroupListMember(groupList, reportGroup, gatewayName);
            }
        }
    }
    
    /**
     * To save the ReportGroupListMember into the database
     * 
     * @param groupList
     *            the object of ECReportGroupList
     */
    private void saveReportGroupListMember(ECReportGroupList groupList,
            ReportGroupBean reportGroup, String gateWayName) {
        
        String tag = "";
        String epc = "";
        String readerName = "";
        Date memberDate = null;
        String coordinateGPS = "";
        double temperature = 0;
        
        // get the members array from groupList
        ECReportGroupListMember[] members = groupList.getMemberArray();
        for (int i = 0; i < members.length; i++) {
            // get the tags
            EPC objetTag = members[i].getTag();
            // get the epc
            EPC objetEpc = members[i].getEpc();
            if (objetTag != null) {
                tag = objetTag.getStringValue();
            }
            if (objetEpc != null) {
                epc = objetEpc.getStringValue();
            }
            
            // if ECReportGroupListMember has extension, get the extension list
            ECReportGroupListMemberExtension extensionList = members[i]
                    .getExtension();
            if (extensionList != null) {
                // and the reader name
                readerName = members[i].getExtension().getReaderName();
                coordinateGPS = members[i].getExtension().getGpsCoordinates();
                
                if (coordinateGPS != null) {
                    coordinateGPS = coordinateGPS.replaceAll(":", ",");
                }
            }
            
            // get the time of the members
            Calendar listDate = members[i].getDate();
            if (listDate != null) {
                memberDate = listDate.getTime();
            }
            
            // String epcManagerNumber = epc.substring(15).split("\\.")[0];
            int onsRecordState = ReportGroupListMemberBean.TO_RECORD_ON_ONS;
            // if
            // (epcManagerNumber.equals(EPCISProperties.getProperty("epc.manager.number")))
            // {
            // onsRecordState = ReportGroupListMemberBean.NOT_TO_RECORD_ON_ONS;
            // }
            
            // Save the informations into database
            ReportGroupListMemberBean reportGroupListMember = new ReportGroupListMemberBean(
                    tag, epc, gateWayName, readerName, memberDate,
                    coordinateGPS, reportGroup, onsRecordState);
            entityManager.persist(reportGroupListMember);
            reportGroup.addReportGroupListMember(reportGroupListMember);
            
            if (extensionList != null) {
                saveMeasurements(reportGroupListMember, members[i]);
            }
        }
    }
    
    private void saveMeasurements(
            ReportGroupListMemberBean reportGroupListMemberBean,
            ECReportGroupListMember reportGroupListMember) {
        ECMeasurementList measurementList = reportGroupListMember
                .getExtension().getMeasurementList();
        
        if (measurementList != null) {
            for (int j = 0; j < measurementList.sizeOfMeasurementArray(); j++) {
                
                String applicationName = measurementList.getMeasurementArray(j)
                        .getApplicationName();
                double error = measurementList.getMeasurementArray(j)
                        .getError();
                String sensorId = measurementList.getMeasurementArray(j)
                        .getSensorId();
                long timestamp = measurementList.getMeasurementArray(j)
                        .getTimestamp();
                String unit = measurementList.getMeasurementArray(j).getUnit();
                double value = measurementList.getMeasurementArray(j)
                        .getValue();
                
                MeasurementBean measurement = new MeasurementBean(
                        applicationName, error, sensorId, timestamp, unit,
                        value, reportGroupListMemberBean);
                entityManager.persist(measurement);
                reportGroupListMemberBean.addMeasurement(measurement);
            }
        }
    }
}
