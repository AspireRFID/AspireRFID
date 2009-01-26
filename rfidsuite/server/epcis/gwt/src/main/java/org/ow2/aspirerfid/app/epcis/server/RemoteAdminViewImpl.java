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
package org.ow2.aspirerfid.app.epcis.server;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapContext;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.ow2.aspirerfid.app.epcis.client.RemoteAdminView;
import org.ow2.aspirerfid.app.epcis.client.widget.roles.RoleGWT;
import org.ow2.aspirerfid.app.epcis.client.widget.table.RGLMComparator;
import org.ow2.aspirerfid.app.epcis.client.widget.table.ReportGroupListMemberTable;
import org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT;
import org.ow2.aspirerfid.app.epcis.client.widget.tags.TagGeneralInformation;
import org.ow2.aspirerfid.app.epcis.client.widget.topology.ArchiElement;
import org.ow2.aspirerfid.app.epcis.client.widget.users.UserGWT;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.WarningGWT;
import org.ow2.aspirerfid.epcis.server.PrivilegeBean;
import org.ow2.aspirerfid.epcis.server.RFIDDataAccessBean;
import org.ow2.aspirerfid.epcis.server.RFIDDataAccessRemote;
import org.ow2.aspirerfid.epcis.server.ReportGroupListMemberBean;
import org.ow2.aspirerfid.epcis.server.RoleBean;
import org.ow2.aspirerfid.epcis.server.TagBean;
import org.ow2.aspirerfid.epcis.server.TagInformationBean;
import org.ow2.aspirerfid.epcis.server.TagInformationRemote;
import org.ow2.aspirerfid.epcis.server.TagRequesterBean;
import org.ow2.aspirerfid.epcis.server.TagRequesterRemote;
import org.ow2.aspirerfid.epcis.server.UserBean;
import org.ow2.aspirerfid.epcis.server.UserManagementBean;
import org.ow2.aspirerfid.epcis.server.UserManagementRemote;
import org.ow2.aspirerfid.epcis.server.WarningLostBean;
import org.ow2.aspirerfid.epcis.server.WarningTempExceedingBean;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Vaudaux-Ruth, Guillaume Surrel
 * @version 2007
 */
public class RemoteAdminViewImpl extends RemoteServiceServlet implements
        RemoteAdminView {
    
    private static final long serialVersionUID = -6560968294128863265L;
    
    private static final int JONAS = 1;
    
    private static final int JBOSS = 2;
    
    private int applicationServer = JONAS;
    
    private String ip;
    
    private String port;
    
    private TagRequesterRemote tagRequester = null;
    
    private UserManagementRemote userManagement = null;
    
    private TagInformationRemote tagInformation = null;
    
    private RFIDDataAccessRemote dataAccess = null;
    
    private String onsURL;
    
    /**
     * Constructor
     */
    public RemoteAdminViewImpl() {
        Properties props = new Properties();
        
        try {
            props.load(RemoteAdminViewImpl.class
                    .getResourceAsStream("/server.properties"));
            applicationServer = Integer.parseInt(props
                    .getProperty("server.type"));
            ip = props.getProperty("server.jndi.ip");
            port = props.getProperty("server.jndi.port");
            onsURL = props.getProperty("ons.service.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        initRemoteSessions();
    }
    
    private void initRemoteSessions() {
        String lookupNameUser = "";
        String lookupNameTagRequester = "";
        String lookupNameTagInformation = "";
        String lookupNameData = "";
        
        InitialContext initialContext = null;
        Properties env = new Properties();
        
        switch (applicationServer) {
            case JONAS:
                env
                        .put(Context.INITIAL_CONTEXT_FACTORY,
                                "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory");
                env.put(Context.PROVIDER_URL, "rmi://" + ip + ":" + port);
                
                lookupNameUser = UserManagementBean.class.getName() + "_"
                        + UserManagementRemote.class.getName() + "@Remote";
                lookupNameTagRequester = TagRequesterBean.class.getName() + "_"
                        + TagRequesterRemote.class.getName() + "@Remote";
                lookupNameTagInformation = TagInformationBean.class.getName()
                        + "_" + TagInformationRemote.class.getName()
                        + "@Remote";
                lookupNameData = RFIDDataAccessBean.class.getName() + "_"
                        + RFIDDataAccessRemote.class.getName() + "@Remote";
                break;
            
            case JBOSS:
                env.put(Context.INITIAL_CONTEXT_FACTORY,
                        "org.jnp.interfaces.NamingContextFactory");
                env.put(Context.PROVIDER_URL, "jnp://" + ip + ":" + port);
                
                lookupNameUser = "epcis/"
                        + UserManagementBean.class.getSimpleName() + "/remote";
                lookupNameTagRequester = "epcis/"
                        + TagRequesterBean.class.getSimpleName() + "/remote";
                lookupNameTagInformation = "epcis/"
                        + TagInformationBean.class.getSimpleName() + "/remote";
                lookupNameData = "epcis/"
                        + RFIDDataAccessBean.class.getSimpleName() + "/remote";
                break;
            
            default:
                System.out.println("Application server unsupported");
                break;
        }
        
        try {
            initialContext = new InitialContext(env);
            this.userManagement = (UserManagementRemote) initialContext
                    .lookup(lookupNameUser);
            this.tagRequester = (TagRequesterRemote) initialContext
                    .lookup(lookupNameTagRequester);
            this.tagInformation = (TagInformationRemote) initialContext
                    .lookup(lookupNameTagInformation);
            this.dataAccess = (RFIDDataAccessRemote) initialContext
                    .lookup(lookupNameData);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#authentication(java.lang.String,
     *      java.lang.String)
     */
    public Boolean authentication(String login, String pass) {
        return this.userManagement.authentication(login, pass);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getPrivileges()
     */
    public List<String> getPrivileges() {
        return userManagement.getPrivileges();
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getRoles()
     */
    public List<RoleGWT> getRoles() {
        List<RoleBean> listRB = userManagement.getAllRoles();
        List<RoleGWT> listR = new ArrayList<RoleGWT>();
        
        for (Iterator<RoleBean> iter = listRB.iterator(); iter.hasNext();) {
            RoleBean rb = iter.next();
            listR.add(Convertor.RoleBean2RoleGWT(rb));
        }
        
        return listR;
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#initDB()
     */
    public void initDB() {
        userManagement.initDB();
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#removeRole(int)
     */
    public void removeRole(int roleId) {
        userManagement.removeRole(roleId);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#addRole(org.ow2.aspirerfid.app.epcis.client.widget.roles.RoleGWT)
     */
    public void addRole(RoleGWT r) {
        Set<String> p = new HashSet<String>(r.privileges);
        userManagement.addRole(r.name, p);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#modifyRole(int, java.lang.String, java.util.List)
     */
    public void modifyRole(int roleId, String newName, List newPrivilegeNames) {
        Set<String> p = new HashSet<String>(newPrivilegeNames);
        userManagement.modifyRole(roleId, newName, p);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getUsers()
     */
    public List<UserGWT> getUsers() {
        List<UserBean> usersBean = userManagement.getUsers();
        List<UserGWT> users = new ArrayList<UserGWT>();
        for (Iterator<UserBean> iter = usersBean.iterator(); iter.hasNext();) {
            UserBean ub = iter.next();
            users.add(Convertor.UserBean2UserGWT(ub));
        }
        return users;
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#removeUser(java.lang.String)
     */
    public void removeUser(String login) {
        userManagement.removeUser(login);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#addUser(java.lang.String, java.lang.String, java.lang.String, int)
     */
    public void addUser(String login, String password, String mail, int roleId) {
        userManagement.addUser(login, password, mail, roleId);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#modifyUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)
     */
    public void modifyUser(String login, String newLogin, String newPassword,
            String newMail, int newRoleId) {
        userManagement.modifyUser(login, newLogin, newPassword, newMail,
                newRoleId);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getEditorConf()
     */
    public Map<String, String> getEditorConf() {
        Properties props = new Properties();
        try {
            props.load(RemoteAdminViewImpl.class
                    .getResourceAsStream("/editor.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap(props);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getEPCHistory(java.lang.String)
     */
    public List<ReportGroupListMemberGWT> getEPCHistory(String tag) {
        List<ReportGroupListMemberBean> result = dataAccess.getEPCHistory(tag);
        return Convertor.RGLMBList2RGLMBGWTList(result);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getTempatureChart(java.lang.String, java.lang.String, int, int)
     */
    public String getTempatureChart(String tag, String unit, int w, int h) {
        List<ReportGroupListMemberBean> tagHistory = dataAccess
                .getTemperatureEPCHistory(tag);
        boolean convertToCelcius = (unit.equals("c"));
        
        if (tagHistory.size() == 0)
            return "";
        
        String chartName = "";
        
        TimeSeries series1 = new TimeSeries("Temperature evolution",
                FixedMillisecond.class);
        double pos = 0;
        double tempMin = tagHistory.get(0).getMeasurement("temperature")
                .getValue();
        if (convertToCelcius) {
            tempMin -= 273.15;
        }
        double tempMax = tempMin;
        for (ReportGroupListMemberBean r : tagHistory) {
            double temp = r.getMeasurement("temperature").getValue();
            if (convertToCelcius) {
                temp -= 273.15;
            }
            series1
                    .add(new FixedMillisecond(r.getMemberDate().getTime()),
                            temp);
            pos++;
            if (temp > tempMax) {
                tempMax = temp;
            }
            
            if (temp < tempMin) {
                tempMin = temp;
            }
            
        }
        
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        
        String yAxisLabel;
        if (convertToCelcius) {
            yAxisLabel = "Temperature in Celcius";
        } else {
            yAxisLabel = "Temperature in Kelvin";
        }
        
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Temperature of "
                + tag, // title
                "Date", // x-axis label
                yAxisLabel, // y-axis label
                dataset, // data
                true, // create legend?
                true, // generate tooltips?
                false // generate URLs?
                );
        
        // // get a reference to the plot for further customisation...
        XYPlot plot = chart.getXYPlot();
        // plot.setBackgroundPaint(Color.lightGray);
        // plot.setDomainGridlinePaint(Color.white);
        // plot.setRangeGridlinePaint(Color.white);
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(1, true);
        plot.setRenderer(renderer);
        
        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(tempMin - 1, tempMax + 1);
        
        try {
            HttpSession session = getThreadLocalRequest().getSession();
            chartName = ServletUtilities.saveChartAsPNG(chart, w, h, session);
        } catch (Exception e) {
            // handle exception
            e.printStackTrace();
        }
        
        return chartName;
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getUserPrivilege()
     */
    public List<String> getUserPrivilege() {
        RoleBean role = userManagement.getUserRole();
        ArrayList<String> result = new ArrayList<String>();
        if (role != null) {
            for (PrivilegeBean pb : role.getPrivileges()) {
                result.add(pb.getName());
            }
            return result;
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getFiltrateDate(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public List<ReportGroupListMemberGWT> getFiltrateDate(String beginDate,
            String beginHours, String endDate, String endHours) {
        List<ReportGroupListMemberBean> rBean = dataAccess.getFiltrateDate(
                Convertor.String2Date(beginDate, beginHours), Convertor
                        .String2Date(endDate, endHours));
        return Convertor.RGLMBList2RGLMBGWTList(rBean);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getLostTags(java.lang.String, long)
     */
    public List<ReportGroupListMemberGWT> getLostTags(String reader,
            long duration) {
        List<ReportGroupListMemberBean> rBean = dataAccess.getLostTags(reader,
                duration);
        return Convertor.RGLMBList2RGLMBGWTList(rBean);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#searchGateway(java.lang.String)
     */
    public List<String> searchGateway(String gatewayPattern) {
        return dataAccess.searchGateway(gatewayPattern);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#searchTag(java.lang.String)
     */
    public List<String> searchTag(String tagPattern) {
        return dataAccess.searchTag(tagPattern);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#searchReader(java.lang.String)
     */
    public List<String> searchReader(String readerPattern) {
        return dataAccess.searchReader(readerPattern);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getAllGateways()
     */
    public List<String> getAllGateways() {
        return dataAccess.getAllGateways();
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getAllReaders()
     */
    public List<String> getAllReaders() {
        return dataAccess.getAllReaders();
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#addWarningLost(java.lang.String, long, java.lang.String)
     */
    public void addWarningLost(String reader, long duration, String warningTitle) {
        userManagement.addWarningLost(reader, duration, warningTitle);
        System.out.println("warning saved");
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getEPCTemperatureAlert(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, java.lang.String)
     */
    public List<ReportGroupListMemberGWT> getEPCTemperatureAlert(
            String beginDate, String beginHours, String endDate,
            String endHours, String operator, double value, String readerName) {
        Date dateBegin = Convertor.String2Date(beginDate, beginHours);
        Date dateEnd = Convertor.String2Date(endDate, endHours);
        
        List<ReportGroupListMemberBean> rBean = dataAccess
                .getEPCTemperatureAlert(dateBegin, dateEnd, operator, value,
                        readerName);
        return Convertor.RGLMBList2RGLMBGWTList(rBean);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getArchitectureStruct()
     */
    public List<ArchiElement> getArchitectureStruct() {
        List<ArchiElement> list = new ArrayList<ArchiElement>();
        
        DirContext dirContex = null;
        
        try {
            dirContex = LDAPHelper.getInitialDirContext();
            
            if (dirContex == null) {
                return null;
            }
            
            NamingEnumeration<Binding> rootEnum;
            rootEnum = dirContex.listBindings("ou=rfid");
            
            while (rootEnum.hasMoreElements()) {
                Binding binding = rootEnum.nextElement();
                LdapContext rootContext = (LdapContext) binding.getObject();
                LDAPHelper.readBranch(list, rootContext, null);
            }
            
            // Close the context
            dirContex.close();
        } catch (NamingException e) {
            System.out.println("LDAP : error !");
            e.printStackTrace();
        }
        
        return list;
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#addWarningTemp(java.lang.String, java.lang.String, java.lang.String, double, java.lang.String, java.lang.String)
     */
    public void addWarningTemp(String beginDate, String beginHours,
            String operator, double value, String readerName,
            String warningTitle) {
        Date dateBegin = Convertor.String2Date(beginDate, beginHours);
        userManagement.addWarningTemp(dateBegin, operator, value, readerName,
                warningTitle);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#removeWarningLost(int)
     */
    public void removeWarningLost(int id) {
        userManagement.removeWarningLost(id);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#removeWarningTemp(int)
     */
    public void removeWarningTemp(int id) {
        userManagement.removeWarningTemp(id);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getWarnings()
     */
    public List<WarningGWT> getWarnings() {
        Set<WarningLostBean> warningLostList = userManagement
                .getUserWarningLost();
        Set<WarningTempExceedingBean> warningTempList = userManagement
                .getUserWarningTemp();
        
        List<WarningGWT> resultList = new ArrayList<WarningGWT>();
        for (WarningLostBean warningLostBean : warningLostList) {
            resultList.add(Convertor
                    .WarningLost2WarningLostGWT(warningLostBean));
        }
        for (WarningTempExceedingBean warningTempExceedingBean : warningTempList) {
            resultList.add(Convertor
                    .WarningTemp2WarningTempGWT(warningTempExceedingBean));
        }
        return resultList;
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getGeneralTagInformation(java.lang.String)
     */
    public TagGeneralInformation getGeneralTagInformation(String tag) {
        TagGeneralInformation tagGI = null;
        
        TagBean tagBean = dataAccess.generalTagInformation(tag);
        if (tagBean != null) {
            tagGI = new TagGeneralInformation(tagBean.getTag(), tagBean
                    .getCreationDate().toLocaleString(), tagBean
                    .getDescription(), "Local");
        } else {
            String epcisServiceURL = tagRequester.getEPCISServiceURL(onsURL,
                    tag);
            if (epcisServiceURL != null) {
                Map<String, String> infos = tagRequester.generalTagInformation(
                        epcisServiceURL, tag);
                if (infos.size() != 0) {
                    tagGI = new TagGeneralInformation(infos.get("tag"), infos
                            .get("creationDate"), infos.get("description"),
                            tagRequester.getEPCISDescription(onsURL,
                                    epcisServiceURL));
                } else {
                    tagGI = new TagGeneralInformation(tag, null, null,
                            tagRequester.getEPCISDescription(onsURL,
                                    epcisServiceURL));
                }
            } else {
                System.out.println("Tag information not accessible.");
                return null;
            }
        }
        
        return tagGI;
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getFullHistories(java.lang.String)
     */
    public List<ReportGroupListMemberGWT> getFullHistories(String tag) {
        LinkedList<ReportGroupListMemberGWT> result = new LinkedList<ReportGroupListMemberGWT>();
        int numPartner = 0;
        
        // Get the list of partners
        String[] tagServiceURLs = tagRequester.getEPCISServicesHistory(onsURL,
                tag);
        for (String tagServiceURL : tagServiceURLs) {
            // Get tag history for a partner
            List<Map<String, String>> tagHistory = tagRequester.tagHistory(
                    tagServiceURL, tag);
            if (tagHistory != null) {
                for (Map<String, String> m : tagHistory) {
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
                                .parse(m.get("date"));
                    } catch (ParseException e) {
                        date = new Date();
                        System.out.println("Date parsing error");
                    }
                    
                    result.add(new ReportGroupListMemberGWT(m.get("tag"),
                            "unknow", m.get("gatewayName"), "unknow", date, m
                                    .get("gpsCoordinates"), Double
                                    .parseDouble(m.get("temperature")),
                            numPartner));
                    
                }
                numPartner++;
            }
        }
        
        // sort by date
        Date minDate;
        ReportGroupListMemberGWT minRGLM;
        LinkedList<ReportGroupListMemberGWT> resultSorted = new LinkedList<ReportGroupListMemberGWT>();
        while (result.size() != 0) {
            minRGLM = result.get(0);
            minDate = minRGLM.getMemberDate();
            for (ReportGroupListMemberGWT r : result) {
                if (r.getMemberDate().before(minDate)) {
                    minDate = r.getMemberDate();
                    minRGLM = r;
                }
            }
            
            result.remove(minRGLM);
            resultSorted.addFirst(minRGLM);
        }
        
        return new ArrayList(resultSorted);
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getSavedWarningsLostResult()
     */
    public List<ReportGroupListMemberGWT> getSavedWarningsLostResult() {
        Set<WarningLostBean> warningLostList = userManagement
                .getUserWarningLost();
        
        List<ReportGroupListMemberGWT> resultList = new ArrayList<ReportGroupListMemberGWT>();
        for (WarningLostBean warningLostBean : warningLostList) {
            List<ReportGroupListMemberBean> l = dataAccess.getLostTags(
                    warningLostBean.getReader(), warningLostBean.getDuration());
            resultList.addAll(Convertor.RGLMBList2RGLMBGWTList(l,
                    warningLostBean.getName()));
        }
        Collections.sort(resultList, new RGLMComparator(
                ReportGroupListMemberTable.FIELD_DATE));
        Collections.reverse(resultList);
        if (resultList.size() > 50) {
            resultList.subList(50, resultList.size()).clear();
        }
        return resultList;
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getSavedWarningsTemperatureResult()
     */
    public List<ReportGroupListMemberGWT> getSavedWarningsTemperatureResult() {
        Set<WarningTempExceedingBean> warningTempList = userManagement
                .getUserWarningTemp();
        
        List<ReportGroupListMemberGWT> resultList = new ArrayList<ReportGroupListMemberGWT>();
        for (WarningTempExceedingBean warningTempBean : warningTempList) {
            List<ReportGroupListMemberBean> l = dataAccess
                    .getEPCTemperatureAlert(warningTempBean.getStartTime(),
                            new Date(), warningTempBean.getOperator(),
                            warningTempBean.getValue(), warningTempBean
                                    .getReaderName());
            resultList.addAll(Convertor.RGLMBList2RGLMBGWTList(l,
                    warningTempBean.getName()));
        }
        Collections.sort(resultList, new RGLMComparator(
                ReportGroupListMemberTable.FIELD_DATE));
        Collections.reverse(resultList);
        if (resultList.size() > 50) {
            resultList.subList(50, resultList.size()).clear();
        }
        return resultList;
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getPartOfTagEntry(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, int, boolean)
     */
    public List getPartOfTagEntry(String beginDate, String beginHours,
            String endDate, String endHours, int offsetBegin, int maxResult,
            int sortBy, boolean desc) {
        List<ReportGroupListMemberGWT> result = new ArrayList<ReportGroupListMemberGWT>();
        List<ReportGroupListMemberBean> resultBean = this.dataAccess
                .getPartOfTagEntry(
                        Convertor.String2Date(beginDate, beginHours), Convertor
                                .String2Date(endDate, endHours), offsetBegin,
                        maxResult, sortBy, desc);
        
        int size = resultBean.size();
        for (int i = 0; i < size; i++) {
            result
                    .add(
                            i,
                            Convertor
                                    .ReportGroupListMemberBean2ReportGroupListMemberGWT(resultBean
                                            .get(i)));
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see org.ow2.aspirerfid.app.epcis.client.RemoteAdminView#getCountTagEntry(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public Long getCountTagEntry(String beginDate, String beginHours,
            String endDate, String endHours) {
        return this.dataAccess.getCountTagEntry(Convertor.String2Date(
                beginDate, beginHours), Convertor
                .String2Date(endDate, endHours));
    }
    
}
