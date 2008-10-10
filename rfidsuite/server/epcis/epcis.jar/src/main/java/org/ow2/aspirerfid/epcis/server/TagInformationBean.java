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

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Implementation of the TagInformation Remote Bean.
 * 
 * @author François Fornaciari
 * @version 2007
 */
@Stateless
@Remote(TagInformationRemote.class)
public class TagInformationBean implements TagInformationRemote {
    /**
     * Entity manager used by this bean.
     */
    @EJB
    RFIDDataAccessRemote dataAccessRemote = null;
    
    @PersistenceContext
    private EntityManager entityManager = null;
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.TagInformationRemote#tagHistory(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public String tagHistory(String tag) {
        JSONObject jsonTag = new JSONObject();
        
        // Get the history of the specified tag
        List<ReportGroupListMemberBean> tagHistory = dataAccessRemote
                .getEPCHistory(tag);
        for (ReportGroupListMemberBean member : tagHistory) {
            try {
                JSONObject jsonMember = new JSONObject();
                jsonMember.append("tag", member.getTag());
                jsonMember.append("gatewayName", member.getGatewayName());
                jsonMember.append("gpsCoordinates", member.getGpsCoordinates());
                jsonMember.append("date", member.getMemberDate());
                jsonMember.append("temperature", member.getMeasurement(
                        "temperature").getValue());
                jsonTag.append("tags", jsonMember);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        
        return jsonTag.toString();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.TagInformationRemote#generalTagInformation(java.lang.String)
     */
    public String generalTagInformation(String tag) {
        JSONObject jsonTag = new JSONObject();
        TagBean tagBean = dataAccessRemote.generalTagInformation(tag);
        if (tagBean != null) {
            try {
                JSONObject jsonMember = new JSONObject();
                jsonMember.append("tag", tagBean.getTag());
                jsonMember.append("creationDate", tagBean.getCreationDate());
                jsonMember.append("description", tagBean.getDescription());
                jsonTag.append("tags", jsonMember);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonTag.toString();
        } else {
            return null;
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.TagInformationRemote#addTagInformation(java.lang.String,
     *      java.util.Date, java.lang.String)
     */
    public void addTagInformation(String tag, Date creationDate,
            String description) {
        TagBean tagBean = entityManager.find(TagBean.class, tag);
        if (tagBean == null) {
            tagBean = new TagBean(tag, creationDate, description);
            entityManager.persist(tagBean);
        } else {
            tagBean.setDescription(description);
            tagBean.setCreationDate(creationDate);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.TagInformationRemote#removeTagInformation(java.lang.String)
     */
    public void removeTagInformation(String tag) {
        TagBean tagBean = entityManager.find(TagBean.class, tag);
        if (tagBean != null) {
            entityManager.remove(tagBean);
        }
    }
}
