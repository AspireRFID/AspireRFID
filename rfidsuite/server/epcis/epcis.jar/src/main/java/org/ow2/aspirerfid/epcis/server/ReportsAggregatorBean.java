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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.ow2.aspirerfid.epcis.server.report.ReportId;

/**
 * Implementation of the reports aggregator Bean.
 * 
 * @author François Fornaciari
 * @version 2007
 */
@Stateless
@Remote(ReportsAggregatorRemote.class)
public class ReportsAggregatorBean implements ReportsAggregatorRemote {
    /**
     * Bean to save reports
     */
    @EJB
    private SaveReportsLocal saveReportsLocal = null;
    
    @EJB
    private ONSUpdaterLocal onsUpdaterLocal = null;
    
    /**
     * The timer service
     */
    @Resource
    private TimerService timerService;
    
    /**
     * This list saves received reports to avoid saving redundant reports.
     */
    private static List<ReportId> receivedReports = new LinkedList<ReportId>();
    
    /**
     * The current timer
     */
    private static Timer timer;
    
    /**
     * Timer cycle duration : every 90 seconds
     */
    private static final int DURATION = 90000;
    
    /**
     * Deletes all persistent timers and starts a new one.
     */
    @PostConstruct
    public void init() {
        if (timer == null) {
            // Cancels previous timers
            for (Object timer : timerService.getTimers()) {
                ((Timer) timer).cancel();
            }
            
            // Starts the timer
            timer = timerService.createTimer(DURATION, DURATION, null);
            onsUpdaterLocal.updateONS(null);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.ReportsAggregatorRemote#onReport(java.util.Map)
     */
    public synchronized boolean onReport(Map<String, String> report) {
        String message = report.get("XMLReport");
        if (message == null || message.equals("")) {
            System.out.println("The XML Report is empty");
        } else {
            String gatewayName = report.get("gateway.name");
            String messageId = report.get("message.id");
            ReportId reportId = new ReportId(gatewayName, messageId, System
                    .currentTimeMillis());
            
            // Save the received report if it hasn't been already saved
            if (!receivedReports.contains(reportId)) {
                boolean reportIsSaved = saveReportsLocal.saveData(message);
                if (reportIsSaved) {
                    // System.out.println(message);
                    receivedReports.add(reportId);
                    System.out.println("Report saved "
                            + reportId.getGatewayName() + " : "
                            + reportId.getMessageId());
                }
                return reportIsSaved;
            }
        }
        return false;
    }
    
    /**
     * This method is called periodically. It deletes old reports from the
     * received report list.
     */
    @Timeout
    public synchronized void refreshReceivedReports(Timer timer) {
        List<ReportId> reportIdList = new LinkedList<ReportId>();
        
        for (int i = 0; i < receivedReports.size(); i++) {
            ReportId reportId = receivedReports.get(i);
            if (reportId.getDate() + DURATION > System.currentTimeMillis()) {
                reportIdList.add(reportId);
            }
        }
        
        receivedReports = reportIdList;
        System.out.println("refreshed list : " + reportIdList);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.ReportsAggregatorRemote#initializeReportList()
     */
    public void initializeReportList() {
        receivedReports = new LinkedList<ReportId>();
    }
}
