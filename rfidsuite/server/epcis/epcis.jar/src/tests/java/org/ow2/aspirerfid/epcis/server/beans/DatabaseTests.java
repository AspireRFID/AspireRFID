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
package org.ow2.aspirerfid.epcis.server.beans;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.ow2.aspirerfid.epcis.server.ONSUpdaterBean;
import org.ow2.aspirerfid.epcis.server.ONSUpdaterRemote;
import org.ow2.aspirerfid.epcis.server.RFIDDataAccessBean;
import org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote;
import org.ow2.aspirerfid.epcis.server.ReportsAggregatorBean;
import org.ow2.aspirerfid.epcis.server.ReportsAggregatorRemote;
import org.ow2.aspirerfid.epcis.server.RoleBean;
import org.ow2.aspirerfid.epcis.server.TagInformationBean;
import org.ow2.aspirerfid.epcis.server.TagInformationRemote;
import org.ow2.aspirerfid.epcis.server.TagRequesterBean;
import org.ow2.aspirerfid.epcis.server.TagRequesterRemote;
import org.ow2.aspirerfid.epcis.server.UserBean;
import org.ow2.aspirerfid.epcis.server.UserManagementBean;
import org.ow2.aspirerfid.epcis.server.UserManagementRemote;
import org.ow2.aspirerfid.epcis.server.util.EPCISProperties;
import org.ow2.aspirerfid.epcis.server.util.JNDIReferenceResolver;

/**
 * TODO Javadoc
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class DatabaseTests extends TestCase {
    Context initialContext;
    
    private String onsServiceURL;
    
    protected void setUp() throws Exception {
        super.setUp();
        
        Properties env = new Properties();
        // For JOnAS
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
        // For JBoss
        // env.put(Context.INITIAL_CONTEXT_FACTORY,
        // "org.jnp.interfaces.NamingContextFactory");
        
        // initialize the context
        initialContext = new InitialContext(env);
        
        onsServiceURL = EPCISProperties.getProperty("ons.service.url");
    }
    
    /**
     * Tests user's creation, user's authentication and user's deletion
     */
    public void test_01() {
        try {
            UserManagementRemote userManagementRemote = (UserManagementRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            UserManagementRemote.class,
                            UserManagementBean.class));
            RoleBean role = userManagementRemote.addRole("role",
                    new HashSet<String>());
            UserBean user = userManagementRemote.addUser("login", "pass",
                    "email@rfid.fr", role.getId());
            
            assertTrue(userManagementRemote.authentication("login", "pass"));
            
            userManagementRemote.removeUser(user.getLogin());
            userManagementRemote.removeRole(role.getId());
            
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests role and user's update and deletion
     */
    public void test_02() {
        try {
            UserManagementRemote userManagementRemote = (UserManagementRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            UserManagementRemote.class,
                            UserManagementBean.class));
            RoleBean role = userManagementRemote.addRole("role",
                    new HashSet<String>());
            role = userManagementRemote.modifyRole(role.getId(), "role2",
                    new HashSet<String>());
            
            UserBean user = userManagementRemote.addUser("login", "pass",
                    "email@rfid.fr", role.getId());
            user = userManagementRemote.modifyUser(user.getLogin(), "login2",
                    "pass2", "email@rfid.fr", role.getId());
            
            userManagementRemote.removeUser(user.getLogin());
            userManagementRemote.removeRole(role.getId());
            
            assertEquals("login2", user.getLogin());
            assertEquals("role2", role.getName());
            
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests user's role update
     */
    public void test_03() {
        try {
            UserManagementRemote userManagementRemote = (UserManagementRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            UserManagementRemote.class,
                            UserManagementBean.class));
            RoleBean role = userManagementRemote.addRole("role",
                    new HashSet<String>());
            UserBean user = userManagementRemote.addUser("login", "pass",
                    "email@rfid.fr", role.getId());
            role = userManagementRemote.modifyRole(role.getId(), "role2",
                    new HashSet<String>());
            
            userManagementRemote.removeUser(user.getLogin());
            userManagementRemote.removeRole(role.getId());
            
            assertEquals("login", user.getLogin());
            assertEquals("role2", role.getName());
            
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests tag information addition and remote call through web service. Need
     * to have the EPC Manager 6543210 stored into the ONS
     */
    public void test_04() {
        try {
            TagRequesterRemote tagRequesterRemote = (TagRequesterRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            TagRequesterRemote.class, TagRequesterBean.class));
            
            TagInformationRemote tagInformationRemote = (TagInformationRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            TagInformationRemote.class,
                            TagInformationBean.class));
            
            String tag = "urn:epc:id:gid:6543210.123456.123456789";
            String description = "none";
            
            tagInformationRemote.addTagInformation(tag, Calendar.getInstance()
                    .getTime(), description);
            
            String epcisServiceURL = tagRequesterRemote.getEPCISServiceURL(
                    onsServiceURL, tag);
            Map<String, String> infos = tagRequesterRemote
                    .generalTagInformation(epcisServiceURL, tag);
            
            tagInformationRemote.removeTagInformation(tag);
            
            assertEquals(infos.get("tag"), tag);
            assertEquals(infos.get("description"), description);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests empty XML Report addition
     */
    public void test_05() {
        try {
            RFIDDataAccessRemote rfiDataAccessRemote = (RFIDDataAccessRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            RFIDDataAccessRemote.class,
                            RFIDDataAccessBean.class));
            
            rfiDataAccessRemote.removeAllECReports();
            
            ReportsAggregatorRemote aggregatorRemote = (ReportsAggregatorRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            ReportsAggregatorRemote.class,
                            ReportsAggregatorBean.class));
            
            aggregatorRemote.initializeReportList();
            
            Map<String, String> report = new Hashtable<String, String>();
            report.put("gateway.name", "gateway");
            report.put("message.id", "1");
            report.put("XMLReport", "");
            boolean stored = aggregatorRemote.onReport(report);
            
            assertEquals(false, stored);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests Report addition with not valid XML syntax
     */
    public void test_06() {
        try {
            RFIDDataAccessRemote rfiDataAccessRemote = (RFIDDataAccessRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            RFIDDataAccessRemote.class,
                            RFIDDataAccessBean.class));
            
            rfiDataAccessRemote.removeAllECReports();
            
            ReportsAggregatorRemote aggregatorRemote = (ReportsAggregatorRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            ReportsAggregatorRemote.class,
                            ReportsAggregatorBean.class));
            
            aggregatorRemote.initializeReportList();
            
            Map<String, String> report = new Hashtable<String, String>();
            report.put("gateway.name", "gateway");
            report.put("message.id", "1");
            
            String xmlReport = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
                    + "<ECReports ALEID=\"\" date=\"2007-08-20T10:59:19.093+02:00\" specName=\"String\" terminationCondition=\"DURATION\" totalMilliseconds=\"9968\" xmlns=\"http://www.ow2.org/aspirerfid/ale\">"
                    + "<reports xmlns=\"\">"
                    + "<report reportName=\"String\" reportSet=\"CURRENT\">"
                    + " <group groupName=\"default\">" + "<groupList>"
                    + " <member date=\"2007-08-20T10:59:11.765+02:00\">"
                    + " <epc>urn:epc:id:gid:7654321.123456.12345678a</epc>"
                    + " <tag>urn:epc:id:gid-96:7654321.123456.12345678a</tag>"
                    + "<extension>" + "  <readerName>fictive</readerName>"
                    + " <gpsCoordinates>55:44</gpsCoordinates>"
                    + " <measurementList>" + " <measurement>"
                    + "  <value>271.5</value>" + " <error>0.0</error>"
                    + " <unit>K</unit>"
                    + " <timestamp>1187600351765</timestamp>"
                    + " <applicationName>temperature</applicationName>"
                    + " <sensorId>fictiveTemperature</sensorId>"
                    + "</measurement>" + " </measurementList>" + "</extension>"
                    + " </member>" + " </groupList>" + "<groupCount>"
                    + "  <count>2</count>" + "</groupCount>" + "</group>"
                    + "</report>"
                    + "</reports>"
                    +
                    // A fault here
                    " <<extension xmlns=\"\">"
                    + "  <gatewayName>edge1</gatewayName>" + " </extension>"
                    + "</ECReports>";
            
            report.put("XMLReport", xmlReport);
            boolean stored = aggregatorRemote.onReport(report);
            
            assertEquals(false, stored);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests correct XML Report addition
     */
    public void test_07() {
        try {
            RFIDDataAccessRemote rfiDataAccessRemote = (RFIDDataAccessRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            RFIDDataAccessRemote.class,
                            RFIDDataAccessBean.class));
            
            rfiDataAccessRemote.removeAllECReports();
            
            ReportsAggregatorRemote aggregatorRemote = (ReportsAggregatorRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            ReportsAggregatorRemote.class,
                            ReportsAggregatorBean.class));
            
            aggregatorRemote.initializeReportList();
            
            Map<String, String> report = new Hashtable<String, String>();
            report.put("gateway.name", "gateway");
            report.put("message.id", "1");
            
            String xmlReport = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
                    + "<ECReports ALEID=\"\" date=\"2007-08-20T10:59:19.093+02:00\" specName=\"String\" terminationCondition=\"DURATION\" totalMilliseconds=\"9968\" xmlns=\"http://www.ow2.org/aspirerfid/ale\">"
                    + "<reports xmlns=\"\">"
                    + "<report reportName=\"String\" reportSet=\"CURRENT\">"
                    + " <group groupName=\"default\">" + "<groupList>"
                    + " <member date=\"2007-08-20T10:59:11.765+02:00\">"
                    + " <epc>urn:epc:id:gid:7654321.123456.12345678a</epc>"
                    + " <tag>urn:epc:id:gid-96:7654321.123456.12345678a</tag>"
                    + "<extension>" + "  <readerName>fictive</readerName>"
                    + " <gpsCoordinates>55:44</gpsCoordinates>"
                    + " <measurementList>" + " <measurement>"
                    + "  <value>271.5</value>" + " <error>0.0</error>"
                    + " <unit>K</unit>"
                    + " <timestamp>1187600351765</timestamp>"
                    + " <applicationName>temperature</applicationName>"
                    + " <sensorId>fictiveTemperature</sensorId>"
                    + "</measurement>" + " </measurementList>" + "</extension>"
                    + " </member>" + " </groupList>" + "<groupCount>"
                    + "  <count>2</count>" + "</groupCount>" + "</group>"
                    + "</report>" + "</reports>" + " <extension xmlns=\"\">"
                    + "  <gatewayName>edge1</gatewayName>" + " </extension>"
                    + "</ECReports>";
            
            report.put("XMLReport", xmlReport);
            boolean stored = aggregatorRemote.onReport(report);
            
            assertEquals(true, stored);
            
            stored = aggregatorRemote.onReport(report);
            assertEquals(false, stored);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests redundant XML Report addition
     */
    public void test_08() {
        try {
            RFIDDataAccessRemote rfiDataAccessRemote = (RFIDDataAccessRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            RFIDDataAccessRemote.class,
                            RFIDDataAccessBean.class));
            
            rfiDataAccessRemote.removeAllECReports();
            
            ReportsAggregatorRemote aggregatorRemote = (ReportsAggregatorRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            ReportsAggregatorRemote.class,
                            ReportsAggregatorBean.class));
            
            aggregatorRemote.initializeReportList();
            
            Map<String, String> report = new Hashtable<String, String>();
            report.put("gateway.name", "gateway");
            report.put("message.id", "1");
            
            String xmlReport = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
                    + "<ECReports ALEID=\"\" date=\"2007-08-20T10:59:19.093+02:00\" specName=\"String\" terminationCondition=\"DURATION\" totalMilliseconds=\"9968\" xmlns=\"www.ow2.org/aspirerfid/ale\">"
                    + "<reports xmlns=\"\">"
                    + "<report reportName=\"String\" reportSet=\"CURRENT\">"
                    + " <group groupName=\"default\">" + "<groupList>"
                    + " <member date=\"2007-08-20T10:59:11.765+02:00\">"
                    + " <epc>urn:epc:id:gid:7654321.123456.12345678a</epc>"
                    + " <tag>urn:epc:id:gid-96:7654321.123456.12345678a</tag>"
                    + "<extension>" + "  <readerName>fictive</readerName>"
                    + " <gpsCoordinates>55:44</gpsCoordinates>"
                    + " <measurementList>" + " <measurement>"
                    + "  <value>271.5</value>" + " <error>0.0</error>"
                    + " <unit>K</unit>"
                    + " <timestamp>1187600351765</timestamp>"
                    + " <applicationName>temperature</applicationName>"
                    + " <sensorId>fictiveTemperature</sensorId>"
                    + "</measurement>" + " </measurementList>" + "</extension>"
                    + " </member>" + " </groupList>" + "<groupCount>"
                    + "  <count>1</count>" + "</groupCount>" + "</group>"
                    + "</report>" + "</reports>" + " <extension xmlns=\"\">"
                    + "  <gatewayName>edge1</gatewayName>" + " </extension>"
                    + "</ECReports>";
            
            report.put("XMLReport", xmlReport);
            boolean stored = aggregatorRemote.onReport(report);
            
            assertEquals(true, stored);
            
            stored = aggregatorRemote.onReport(report);
            assertEquals(false, stored);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests XML Report insertion and consultation
     */
    public void test_09() {
        try {
            RFIDDataAccessRemote rfiDataAccessRemote = (RFIDDataAccessRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            RFIDDataAccessRemote.class,
                            RFIDDataAccessBean.class));
            
            rfiDataAccessRemote.removeAllECReports();
            
            ReportsAggregatorRemote aggregatorRemote = (ReportsAggregatorRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            ReportsAggregatorRemote.class,
                            ReportsAggregatorBean.class));
            
            aggregatorRemote.initializeReportList();
            
            String xmlReport = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
                    + "<ECReports ALEID=\"\" date=\"2007-08-20T10:59:19.093+02:00\" specName=\"String\" terminationCondition=\"DURATION\" totalMilliseconds=\"9968\" xmlns=\"http://www.ow2.org/aspirerfid/ale\">"
                    + "<reports xmlns=\"\">"
                    + "<report reportName=\"String\" reportSet=\"CURRENT\">"
                    + " <group groupName=\"default\">" + "<groupList>"
                    + " <member date=\"2007-08-20T10:59:11.765+02:00\">"
                    + " <epc>urn:epc:id:gid:7654321.123456.12345678a</epc>"
                    + " <tag>urn:epc:id:gid-96:7654321.123456.12345678a</tag>"
                    + "<extension>" + "  <readerName>fictive</readerName>"
                    + " <gpsCoordinates>55:44</gpsCoordinates>"
                    + " <measurementList>" + " <measurement>"
                    + "  <value>271.5</value>" + " <error>0.0</error>"
                    + " <unit>K</unit>"
                    + " <timestamp>1187600351765</timestamp>"
                    + " <applicationName>temperature</applicationName>"
                    + " <sensorId>fictiveTemperature</sensorId>"
                    + "</measurement>" + " </measurementList>" + "</extension>"
                    + " </member>" + " </groupList>" + "<groupCount>"
                    + "  <count>1</count>" + "</groupCount>" + "</group>"
                    + "</report>" + "</reports>" + " <extension xmlns=\"\">"
                    + "  <gatewayName>edge1</gatewayName>" + " </extension>"
                    + "</ECReports>";
            
            for (int i = 1; i <= 5; i++) {
                Map<String, String> report = new Hashtable<String, String>();
                report.put("gateway.name", "gateway");
                report.put("message.id", String.valueOf(i));
                report.put("XMLReport", xmlReport);
                aggregatorRemote.onReport(report);
            }
            
            xmlReport = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
                    + "<ECReports ALEID=\"\" date=\"2007-08-20T10:59:19.093+02:00\" specName=\"String\" terminationCondition=\"DURATION\" totalMilliseconds=\"9968\" xmlns=\"www.ow2.org/aspirerfid/ale\">"
                    + "<reports xmlns=\"\">"
                    + "<report reportName=\"String\" reportSet=\"ADDITIONS\">"
                    + " <group groupName=\"default\">" + "<groupList>"
                    + " <member date=\"2007-08-20T10:59:11.765+02:00\">"
                    + " <epc>urn:epc:id:gid:7654321.123456.12345678a</epc>"
                    + " <tag>urn:epc:id:gid-96:7654321.123456.12345678a</tag>"
                    + "<extension>" + "  <readerName>fictive</readerName>"
                    + " <gpsCoordinates>55:44</gpsCoordinates>"
                    + " <measurementList>" + " <measurement>"
                    + "  <value>271.5</value>" + " <error>0.0</error>"
                    + " <unit>K</unit>"
                    + " <timestamp>1187600351765</timestamp>"
                    + " <applicationName>temperature</applicationName>"
                    + " <sensorId>fictiveTemperature</sensorId>"
                    + "</measurement>" + " </measurementList>" + "</extension>"
                    + " </member>" + " </groupList>" + "<groupCount>"
                    + "  <count>1</count>" + "</groupCount>" + "</group>"
                    + "</report>" + "</reports>" + " <extension xmlns=\"\">"
                    + "  <gatewayName>edge1</gatewayName>" + " </extension>"
                    + "</ECReports>";
            
            for (int i = 6; i <= 9; i++) {
                Map<String, String> report = new Hashtable<String, String>();
                report.put("gateway.name", "gateway");
                report.put("message.id", String.valueOf(i));
                report.put("XMLReport", xmlReport);
                aggregatorRemote.onReport(report);
            }
            
            assertEquals(rfiDataAccessRemote.getAllEPC().size(), 9);
            assertEquals(rfiDataAccessRemote.getAllCurrent().size(), 5);
            assertEquals(rfiDataAccessRemote.getAllAdditions().size(), 4);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Tests launching ONS Updater timer
     */
    public void test_10() {
        try {
            ONSUpdaterRemote onsUpdaterRemote = (ONSUpdaterRemote) initialContext
                    .lookup(JNDIReferenceResolver.getRemoteJNDIReference(
                            ONSUpdaterRemote.class, ONSUpdaterBean.class));
            
            onsUpdaterRemote.updateONS(null);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        
    }
}
