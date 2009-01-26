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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.ow2.aspirerfid.app.epcis.client.widget.roles.RoleGWT;
import org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT;
import org.ow2.aspirerfid.app.epcis.client.widget.users.UserGWT;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.WarningLostGWT;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.WarningTempGWT;
import org.ow2.aspirerfid.epcis.server.PrivilegeBean;
import org.ow2.aspirerfid.epcis.server.ReportGroupListMemberBean;
import org.ow2.aspirerfid.epcis.server.RoleBean;
import org.ow2.aspirerfid.epcis.server.UserBean;
import org.ow2.aspirerfid.epcis.server.WarningLostBean;
import org.ow2.aspirerfid.epcis.server.WarningTempExceedingBean;

/**
 * The client can't use class from server side. So a converter is required.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version 2007
 */
public class Convertor {
    
    /**
     * Convert a RoleBean to a Role
     * 
     * @param rb
     *            RoleBean to convert
     * @return RoleBean converted to Role
     */
    public static RoleGWT RoleBean2RoleGWT(RoleBean rb) {
        List<String> p = new ArrayList<String>();
        for (Iterator<PrivilegeBean> iter = rb.getPrivileges().iterator(); iter
                .hasNext();) {
            PrivilegeBean element = iter.next();
            p.add(element.getName());
        }
        
        return new RoleGWT(rb.getId(), rb.getName(), p, false);
    }
    
    /**
     * TODO Javadoc
     * 
     * @param ub
     * @return
     */
    public static UserGWT UserBean2UserGWT(UserBean ub) {
        return new UserGWT(ub.getLogin(), ub.getPass(), ub.getMail(),
                RoleBean2RoleGWT(ub.getRole()));
    }
    
    /**
     * TODO Javadoc
     * 
     * @param bean
     * @return
     */
    public static ReportGroupListMemberGWT ReportGroupListMemberBean2ReportGroupListMemberGWT(
            ReportGroupListMemberBean bean) {
        double temp = -1.0;
        if (bean.getMeasurement("temperature") != null) {
            temp = bean.getMeasurement("temperature").getValue();
        }
        
        Date utilDate = new Date(bean.getMemberDate().getTime());
        return new ReportGroupListMemberGWT(bean.getTag(), bean.getEpc(), bean
                .getGatewayName(), bean.getReaderName(), utilDate, bean
                .getGpsCoordinates(), temp, bean.getId());
    }
    
    /**
     * TODO Javadoc
     * 
     * @param beanList
     * @return
     */
    public static List<ReportGroupListMemberGWT> RGLMBList2RGLMBGWTList(
            List<ReportGroupListMemberBean> beanList) {
        List<ReportGroupListMemberGWT> resultList = new ArrayList<ReportGroupListMemberGWT>();
        if (beanList != null) {
            for (ReportGroupListMemberBean reportGroupListMemberBean : beanList) {
                resultList
                        .add(Convertor
                                .ReportGroupListMemberBean2ReportGroupListMemberGWT(reportGroupListMemberBean));
            }
        }
        return resultList;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param beanList
     * @param info
     * @return
     */
    public static List<ReportGroupListMemberGWT> RGLMBList2RGLMBGWTList(
            List<ReportGroupListMemberBean> beanList, String info) {
        List<ReportGroupListMemberGWT> resultList = new ArrayList<ReportGroupListMemberGWT>();
        for (ReportGroupListMemberBean reportGroupListMemberBean : beanList) {
            ReportGroupListMemberGWT rGWT = Convertor
                    .ReportGroupListMemberBean2ReportGroupListMemberGWT(reportGroupListMemberBean);
            rGWT.setAdditionalInfo(info);
            resultList.add(rGWT);
        }
        return resultList;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param date
     * @param time
     * @return
     */
    public static Date String2Date(String date, String time) {
        String splitted[] = date.split("/");
        int m = Integer.parseInt(splitted[0]) - 1; // Jan = 0
        int d = Integer.parseInt(splitted[1]);
        int y = Integer.parseInt(splitted[2]);
        
        String splitted2[] = time.split(":");
        int h = Integer.parseInt(splitted2[0]);
        int min = Integer.parseInt(splitted2[1]);
        int s = Integer.parseInt(splitted2[2]);
        
        Calendar c = new GregorianCalendar();
        c.set(y, m, d, h, min, s);
        return c.getTime();
    }
    
    /**
     * TODO Javadoc
     * 
     * @param date
     * @return
     */
    public static Date String2Date(String date) {
        String splitted[] = date.split("/");
        int m = Integer.parseInt(splitted[0]) - 1;
        int d = Integer.parseInt(splitted[1]);
        int y = Integer.parseInt(splitted[2]);
        
        Calendar c = new GregorianCalendar();
        c.set(y, m, d);
        return c.getTime();
    }
    
    /**
     * TODO Javadoc
     * 
     * @param warningLostBean
     * @return
     */
    public static WarningLostGWT WarningLost2WarningLostGWT(
            WarningLostBean warningLostBean) {
        return new WarningLostGWT(warningLostBean.getReader(), warningLostBean
                .getDuration(), warningLostBean.getName(), warningLostBean
                .getId());
    }
    
    /**
     * TODO Javadoc
     * 
     * @param warningTempBean
     * @return
     */
    public static WarningTempGWT WarningTemp2WarningTempGWT(
            WarningTempExceedingBean warningTempBean) {
        Date startTime = new Date(warningTempBean.getStartTime().getTime());
        return new WarningTempGWT(startTime, warningTempBean.getOperator(),
                warningTempBean.getValue(), warningTempBean.getName(),
                warningTempBean.getId());
    }
}
