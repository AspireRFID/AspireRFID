/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.epc.ale.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.ow2.aspirerfid.ale.ECMeasurementList;
import org.ow2.aspirerfid.ale.ECReportGroupCount;
import org.ow2.aspirerfid.ale.ECReportGroupList;
import org.ow2.aspirerfid.ale.ECReportGroupListMemberExtension;
import org.ow2.aspirerfid.ale.ECReportList;
import org.ow2.aspirerfid.ale.ECReportsExtension;
import org.ow2.aspirerfid.epcglobal.EPC;
import org.ow2.aspirerfid.epc.ale.api.ECReportSetSpec;
import org.ow2.aspirerfid.epc.ale.api.ECReportSpec;
import org.ow2.aspirerfid.epc.ale.api.ECSpec;
import org.ow2.aspirerfid.epc.ale.pattern.impl.EPCFilteringPatternImpl;
import org.ow2.aspirerfid.epc.ale.pattern.impl.EPCGroupingPatternImpl;
import org.ow2.aspirerfid.epc.ale.report.impl.ECReportGroupImpl;
import org.ow2.aspirerfid.epc.ale.report.impl.ECReportGroupListMemberImpl;
import org.ow2.aspirerfid.epc.ale.report.impl.ECReportImpl;
import org.ow2.aspirerfid.epc.ale.report.impl.ECReportsImpl;
import org.ow2.aspirerfid.epc.ale.pattern.api.BadPatternSyntaxException;
import org.ow2.aspirerfid.epc.ale.pattern.api.EPCFilteringPattern;
import org.ow2.aspirerfid.epc.ale.pattern.api.EPCGroupingPattern;
import org.ow2.aspirerfid.epc.ale.report.api.ECMeasurement;
import org.ow2.aspirerfid.epc.ale.report.api.ECReport;
import org.ow2.aspirerfid.epc.ale.report.api.ECReportGroup;
import org.ow2.aspirerfid.epc.ale.report.api.ECReportGroupListMember;
import org.ow2.aspirerfid.epc.ale.report.api.ECReports;
import org.ow2.aspirerfid.epc.ale.report.api.ECTerminationCondition;
import org.ow2.aspirerfid.epc.ale.tag.api.EPCTag;
import org.ow2.aspirerfid.util.Logger;

/**
 * TODO Javadoc
 * @author François Fornaciari
 * @author Guillaume Surrel
 * @version 2007
 */
public class EPCBuffer {
    private final ECSpec ecSpec;
    
    private final Logger logger;
    
    private ECReports reports;
    
    private final String specName;
    
    private long startTime;
    
    private List lastECReads;
    
    private List thisECReads;
    
    /**
     * Builds EPC buffer. TODO Javadoc
     * @param ecSpec 
     * @param specName 
     * @param logger 
     */
    public EPCBuffer(final ECSpec ecSpec, final String specName,
            final Logger logger) {
        this.ecSpec = ecSpec;
        this.specName = specName;
        this.logger = logger;
        
        this.lastECReads = new ArrayList();
        this.thisECReads = new ArrayList();
        
        this.initECReports();
    }
    
    private void addEPCInReportGroup(final ALEEvent aleEvent,
            final ECReportSpec reportSpec) {
        
        final ECReport report = this.reports.getReport(reportSpec.getName());
        
        // Check if the epc matches a group
        String groupPattern = null;
        EPCGroupingPattern epcGroupingPattern = null;
        boolean isMatched = false;
        final Iterator groupsIterator = reportSpec.getGroupSpec().iterator();
        while (groupsIterator.hasNext()) {
            groupPattern = (String) groupsIterator.next();
            try {
                epcGroupingPattern = new EPCGroupingPatternImpl(groupPattern);
            } catch (final BadPatternSyntaxException e) {
                // The ecSpec must have been validated, so can't reach here
                throw new IllegalStateException();
            }
            if (epcGroupingPattern.match(aleEvent.getEpc())) {
                isMatched = true;
                break;
            }
        }
        
        ECReportGroup reportGroup = null;
        // the epc is in a group
        if (isMatched) {
            
            groupPattern = epcGroupingPattern.getGroup(aleEvent.getEpc());
            if (groupPattern == null) {
                throw new IllegalStateException();
            }
            reportGroup = report.getGroup(groupPattern);
            if (reportGroup == null) {
                reportGroup = new ECReportGroupImpl(groupPattern);
                report.addGroup(reportGroup);
            }
            
        } else {
            // the epc is in a default group
            reportGroup = report.getGroup("default");
            if (reportGroup == null) {
                reportGroup = new ECReportGroupImpl("default");
                report.addGroup(reportGroup);
            }
        }
        
        final EPCTag epcTag = aleEvent.getEpc();
        
        final Calendar date = Calendar.getInstance();
        date.setTimeInMillis(new Long(aleEvent.getTimeStamp()).longValue());
        
        URI epc = null;
        URI tag = null;
        URI rawDecimal = null;
        URI rawHex = null;
        
        try {
            epc = new URI(epcTag.getEpcUri());
            tag = new URI(epcTag.getTagUri());
            rawDecimal = new URI(epcTag.getRawDecimalUri());
            rawHex = new URI(epcTag.getRawHexUri());
        } catch (final URISyntaxException e) {
            e.printStackTrace();
        }
        
        final String readerName = aleEvent.getLogicalReaderName();
        final String gpsCoordinates = aleEvent.getReaderCoordinates();
        final List measurements = aleEvent.getMeasurements();
        
        final ECReportGroupListMember groupListMember = new ECReportGroupListMemberImpl(
                date, epc, tag, rawDecimal, rawHex, readerName, gpsCoordinates,
                measurements);
        
        reportGroup.addGroupListMember(groupListMember);
        
        this.logger.log(Logger.DEBUG, "Member added to group : "
                + reportGroup.getGroupName());
    }
    
    private void addEPCListInReport(final List aleEvents,
            final ECReportSpec reportSpec) {
        
        // Applying excluding and including filters
        // Excluding filters
        if (!((reportSpec.getExcludePatterns() == null) || reportSpec
                .getExcludePatterns().isEmpty())) {
            this.logger.log(Logger.DEBUG, "Excluding filters defined");
            
            final Iterator filtersIterator = reportSpec.getIncludePatterns()
                    .iterator();
            while (filtersIterator.hasNext()) {
                final String filter = (String) filtersIterator.next();
                try {
                    // Remove from the list all excluded epcs
                    final EPCFilteringPattern pattern = new EPCFilteringPatternImpl(
                            filter);
                    final Iterator it = aleEvents.iterator();
                    while (it.hasNext()) {
                        final ALEEvent aleEvent = (ALEEvent) it.next();
                        if (pattern.match(aleEvent.getEpc())) {
                            aleEvents.remove(aleEvent);
                        }
                    }
                } catch (final BadPatternSyntaxException e) {
                    // The ecSpec must have been validated, so can't
                    // reach here
                    throw new IllegalStateException();
                }
            }
        }
        
        // Including filters
        if (!((reportSpec.getIncludePatterns() == null) || reportSpec
                .getIncludePatterns().isEmpty())) {
            this.logger.log(Logger.DEBUG, "Including filters defined");
            
            final Iterator filtersIterator = reportSpec.getIncludePatterns()
                    .iterator();
            while (filtersIterator.hasNext()) {
                final String filter = (String) filtersIterator.next();
                try {
                    // if the epc is included, add it to the report
                    // and go to next epc
                    final EPCFilteringPattern pattern = new EPCFilteringPatternImpl(
                            filter);
                    final Iterator it = aleEvents.iterator();
                    while (it.hasNext()) {
                        final ALEEvent aleEvent = (ALEEvent) it.next();
                        if (pattern.match(aleEvent.getEpc())) {
                            this.addEPCInReportGroup(aleEvent, reportSpec);
                        }
                    }
                } catch (final BadPatternSyntaxException e) {
                    // The ecSpec must have been validated, so can't
                    // reach here
                    throw new IllegalStateException();
                }
            }
        }

        // If no including filters are defined, all epc which
        // are not excluded are included
        else {
            this.logger.log(Logger.DEBUG, "No filter defined");
            final Iterator it = aleEvents.iterator();
            while (it.hasNext()) {
                final ALEEvent aleEvent = (ALEEvent) it.next();
                this.addEPCInReportGroup(aleEvent, reportSpec);
            }
        }
    }
    
    private void buildECReport(
            final org.ow2.aspirerfid.ale.ECReports sendedReports) {
        final Set reports = this.reports.getReports();
        final ECReportList ecReportList = sendedReports.addNewReports();
        final Iterator it = reports.iterator();
        while (it.hasNext()) {
            final ECReport report = (ECReport) it.next();
            
            final ECReportSpec reportSpec = this.ecSpec.getReportSpec(report
                    .getReportName());
            if (report.getGroups().size() == 0) {
                if (reportSpec.getReportIfEmpty()) {
                    report.addGroup(new ECReportGroupImpl("default"));
                    final org.ow2.aspirerfid.ale.ECReport sendedECReport = ecReportList
                            .addNewReport();
                    sendedECReport.setReportName(report.getReportName());
                    sendedECReport.setReportSet(report.getReportSet());
                    this.buildECReportGroups(report, sendedECReport);
                }
            } else {
                final org.ow2.aspirerfid.ale.ECReport sendedECReport = ecReportList
                        .addNewReport();
                sendedECReport.setReportName(report.getReportName());
                sendedECReport.setReportSet(report.getReportSet());
                this.buildECReportGroups(report, sendedECReport);
            }
        }
        sendedReports.setReports(ecReportList);
        
        final ECReportsExtension extension = sendedReports.addNewExtension();
        if (this.ecSpec.getGatewayName() != null) {
            extension.setGatewayName(this.ecSpec.getGatewayName());
        }
    }
    
    private void buildECReportGroups(final ECReport report,
            final org.ow2.aspirerfid.ale.ECReport sendedECReport) {
        final Set reportGroups = report.getGroups();
        final Iterator groupIt = reportGroups.iterator();
        
        final ECReportSpec reportSpec = this.ecSpec.getReportSpec(report
                .getReportName());
        
        while (groupIt.hasNext()) {
            final ECReportGroup reportGroup = (ECReportGroup) groupIt.next();
            final org.ow2.aspirerfid.ale.ECReportGroup sendedECReportGroup = sendedECReport
                    .addNewGroup();
            sendedECReportGroup.setGroupName(reportGroup.getGroupName());
            
            if (reportSpec.getIncludeCount()) {
                final ECReportGroupCount groupCount = sendedECReportGroup
                        .addNewGroupCount();
                groupCount.setCount(reportGroup.getGroupCount());
            }
            
            if (reportGroup.getGroupCount() == 0) {
                continue;
            }
            
            if (!reportSpec.getIncludeEPC() && !reportSpec.getIncludeGPS()
                    && !reportSpec.getIncludeRawDecimal()
                    && !reportSpec.getIncludeRawHex()
                    && !reportSpec.getIncludeReaderName()
                    && !reportSpec.getIncludeTag()
                    && !reportSpec.getIncludeMeasurement()) {
                continue;
            }
            
            final ECReportGroupList memberList = sendedECReportGroup
                    .addNewGroupList();
            final Iterator memberIt = reportGroup.getGroupList().iterator();
            
            while (memberIt.hasNext()) {
                final ECReportGroupListMember member = (ECReportGroupListMember) memberIt
                        .next();
                
                final org.ow2.aspirerfid.ale.ECReportGroupListMember sendedMember = memberList
                        .addNewMember();
                
                sendedMember.setDate(member.getDate());
                
                if (reportSpec.getIncludeEPC()) {
                    final EPC epc = sendedMember.addNewEpc();
                    epc.setStringValue(member.getEPC().toString());
                    sendedMember.setEpc(epc);
                }
                
                if (reportSpec.getIncludeTag()) {
                    final EPC epc = sendedMember.addNewTag();
                    epc.setStringValue(member.getTag().toString());
                    sendedMember.setTag(epc);
                }
                
                if (reportSpec.getIncludeRawDecimal()) {
                    final EPC epc = sendedMember.addNewRawDecimal();
                    epc.setStringValue(member.getRawDecimal().toString());
                    sendedMember.setRawDecimal(epc);
                }
                
                if (reportSpec.getIncludeRawHex()) {
                    final EPC epc = sendedMember.addNewRawHex();
                    epc.setStringValue(member.getRawHex().toString());
                    sendedMember.setRawHex(epc);
                }
                
                if (!reportSpec.getIncludeGPS()
                        && !reportSpec.getIncludeReaderName()
                        && !reportSpec.getIncludeMeasurement()) {
                    continue;
                }
                final ECReportGroupListMemberExtension extension = sendedMember
                        .addNewExtension();
                
                if ((member.getReaderName() != null)
                        && reportSpec.getIncludeReaderName()) {
                    extension.setReaderName(member.getReaderName());
                }
                
                if ((member.getGpsCoordinates() != null)
                        && reportSpec.getIncludeGPS()) {
                    extension.setGpsCoordinates(member.getGpsCoordinates());
                }
                
                if ((member.getMeasurements() != null)
                        && reportSpec.getIncludeMeasurement()) {
                    final ECMeasurementList measurementList = extension
                            .addNewMeasurementList();
                    
                    final List measurements = member.getMeasurements();
                    for (int i = 0; i < measurements.size(); i++) {
                        final org.ow2.aspirerfid.ale.ECMeasurement ecMeasurement = measurementList
                                .addNewMeasurement();
                        final ECMeasurement m = (ECMeasurement) measurements
                                .get(i);
                        ecMeasurement
                                .setApplicationName(m.getApplicationName());
                        ecMeasurement.setError(m.getMeasurement().getError());
                        ecMeasurement.setSensorId(m.getSensorId());
                        ecMeasurement
                                .setTimestamp(m.getMeasurement().getTime());
                        ecMeasurement.setUnit(m.getMeasurement().getUnit()
                                .toString());
                        ecMeasurement.setValue(m.getMeasurement().getValue());
                    }
                }
            }
        }
        
        report.removeGroups();
        
    }
    
    private void buildECReports() {
        
        final Set reportSpecs = this.ecSpec.getReportSpecs();
        final Iterator it = reportSpecs.iterator();
        while (it.hasNext()) {
            final ECReportSpec reportSpec = (ECReportSpec) it.next();
            
            // If we only want the new tags
            if (reportSpec.getReportSetSpec().equals(ECReportSetSpec.ADDITIONS)) {
                final List eventsList = (List) ((ArrayList) this.thisECReads)
                        .clone();
                eventsList.removeAll(this.lastECReads);
                this.addEPCListInReport(eventsList, reportSpec);
            }

            // If we want the deleted tags
            else if (reportSpec.getReportSetSpec().equals(
                    ECReportSetSpec.DELETIONS)) {
                final List eventsList = (List) ((ArrayList) this.lastECReads)
                        .clone();
                eventsList.removeAll(this.thisECReads);
                this.addEPCListInReport(eventsList, reportSpec);
            }

            // If we want all read tags
            else {
                this.addEPCListInReport(this.thisECReads, reportSpec);
            }
        }
    }
    
    protected org.ow2.aspirerfid.ale.ECReports buildXMLECReports() {
        this.logger.log(Logger.INFO, "Creating report with "
                + this.thisECReads.size() + " epcs.");
        
        this.buildECReports();
        
        // Creates an ECReports instance
        final org.ow2.aspirerfid.ale.ECReports sendedReports = org.ow2.aspirerfid.ale.ECReports.Factory
                .newInstance();
        
        // Sets ALEID
        sendedReports.setALEID(this.reports.getALEID());
        if (this.ecSpec.getIncludeSpecInReports()) {
            // Set ECSpec if specified
            final org.ow2.aspirerfid.ale.ECSpec sendedECSpec = org.ow2.aspirerfid.ale.ECSpec.Factory
                    .newInstance();
            sendedReports.setECSpec(sendedECSpec);
        }
        
        // Sets the date of event cycle ended
        this.reports.setDate(System.currentTimeMillis());
        final Calendar date = Calendar.getInstance();
        date.setTimeInMillis(this.reports.getDate());
        sendedReports.setDate(date);
        
        // Sets the specName
        sendedReports.setSpecName(this.specName);
        
        // Sets the termination condition
        this.reports.setTerminationCondition(this.getTerminationCondition());
        final org.ow2.aspirerfid.ale.ECTerminationCondition.Enum terminationConditionEnum = org.ow2.aspirerfid.ale.ECTerminationCondition.Enum
                .forString(this.reports.getTerminationCondition());
        sendedReports.setTerminationCondition(terminationConditionEnum);
        
        // Sets the total milliseconds duration of this report
        this.reports.setTotalMilliseconds(System.currentTimeMillis()
                - this.startTime);
        sendedReports.setTotalMilliseconds(this.reports.getTotalMilliseconds());
        
        // Add ECReport to ECReports
        this.buildECReport(sendedReports);
        
        // Initializes EPCs buffer - A new EC starts
        this.startTime = System.currentTimeMillis();
        
        this.lastECReads = this.thisECReads;
        this.thisECReads = new ArrayList();
        
        return sendedReports;
    }
    
    private String getTerminationCondition() {
        // For the moment, only duration is supported
        return ECTerminationCondition.DURATION;
    }
    
    protected void handleALEEvent(final ALEEvent aleEvent) {
        
        this.logger.log(Logger.DEBUG, "EPC received from "
                + aleEvent.getLogicalReaderName());
        
        if (System.currentTimeMillis() - this.startTime > this.ecSpec
                .getDuration()) {
            // If the repeat period is larger than the duration,
            // epc received after duration has elapsed are ignored
            this.logger.log(Logger.DEBUG,
                    "EPC ignored, the event cycle is finished");
            return;
        }
        
        // If ECSpec includes reads for this EPC logical reader
        if (this.isLogicalReaderIncluded(aleEvent.getLogicalReaderName())) {
            if (!this.isRedondant(aleEvent)) {
                this.thisECReads.add(aleEvent);
            } else {
                this.logger.log(Logger.DEBUG, "EPC already received");
            }
        }
    }
    
    /**
     * Builds the Event Cycle Report structure
     */
    private void initECReport() {
        // Init ECReport
        final Set reportsSpec = this.ecSpec.getReportSpecs();
        final Iterator it = reportsSpec.iterator();
        while (it.hasNext()) {
            final ECReportSpec reportSpec = (ECReportSpec) it.next();
            final ECReport report = new ECReportImpl(reportSpec.getName(),
                    reportSpec.getReportSetSpec());
            this.reports.addReport(report);
        }
    }
    
    /**
     * Builds the Event Cycle root structure
     */
    private void initECReports() {
        // Init ECReports
        if (this.ecSpec.getIncludeSpecInReports()) {
            this.reports = new ECReportsImpl(null, this.ecSpec, this.specName,
                    null);
        } else {
            this.reports = new ECReportsImpl(null, null, this.specName, null);
        }
        this.startTime = System.currentTimeMillis();
        
        this.initECReport();
    }
    
    private boolean isLogicalReaderIncluded(final String reader) {
        final Set logicalReaders = this.ecSpec.getLogicalReaderNames();
        final Iterator it = logicalReaders.iterator();
        while (it.hasNext()) {
            final String logicalReader = (String) it.next();
            if (logicalReader.compareTo(reader) == 0) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isRedondant(final ALEEvent event) {
        return this.thisECReads.contains(event);
    }
    
}
