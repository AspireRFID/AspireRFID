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

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ow2.aspirerfid.epcis.server.util.EPCISProperties;

/**
 * Implementation of the ONS Updater Bean.
 * 
 * @author François Fornaciari
 * @version 2007
 */
@Stateless
@Local(ONSUpdaterLocal.class)
@Remote(ONSUpdaterRemote.class)
@SuppressWarnings("unchecked")
public class ONSUpdaterBean implements ONSUpdaterLocal {
    
    /**
     * Entity manager used by this bean.
     */
    @PersistenceContext
    private EntityManager entityManager = null;
    
    /**
     * The timer service
     */
    @Resource
    private TimerService timerService;
    
    /**
     * The current timer
     */
    private static Timer timer;
    
    /**
     * Timer cycle duration : every 90 seconds
     */
    private static final int DURATION = 90000;
    
    /**
     * The ONS service URL
     */
    private String onsServiceURL = null;
    
    @EJB
    private TagRequesterRemote tagRequesterRemote = null;
    
    /**
     * Deletes all persistent timers and starts a new one.
     */
    @PostConstruct
    public void init() {
        // Defines the ONS service URL
        onsServiceURL = EPCISProperties.getProperty("ons.service.url");
        
        if (timer == null) {
            // Cancels previous timers
            for (Object timer : timerService.getTimers()) {
                ((Timer) timer).cancel();
            }
            
            // Starts the timer
            timer = timerService.createTimer(DURATION, DURATION, null);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.ONSUpdaterLocal#updateONS(javax.ejb.Timer)
     */
    @Timeout
    public synchronized void updateONS(Timer timer) {
        System.out.println("Trying to update ONS...");
        
        Query q = entityManager
                .createQuery("SELECT r.tag, MAX(r.onsRecordState), MAX(r.id) "
                        + "FROM ReportGroupListMemberBean r GROUP BY r.tag");
        List<Object[]> result = q.getResultList();
        
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String serviceURL = "http://" + ip + ":"
                    + EPCISProperties.getProperty("tag.service.url");
            
            Map<Integer, String> tags = new HashMap<Integer, String>();
            
            for (Object[] objects : result) {
                String tag = (String) objects[0];
                Integer onsRecordState = (Integer) objects[1];
                Integer id = (Integer) objects[2];
                
                if (onsRecordState == ReportGroupListMemberBean.TO_RECORD_ON_ONS) {
                    tags.put(id, tag);
                }
            }
            
            if (tags.size() > 0) {
                boolean isONSUpdated = tagRequesterRemote
                        .registerEPCISServiceHistory(onsServiceURL, tags
                                .values().toString(), serviceURL);
                
                if (isONSUpdated) {
                    for (Integer id : tags.keySet()) {
                        ReportGroupListMemberBean member = entityManager.find(
                                ReportGroupListMemberBean.class, id);
                        member
                                .setOnsRecordState(ReportGroupListMemberBean.NOT_TO_RECORD_ON_ONS);
                    }
                    System.out.println("ONS updated");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
