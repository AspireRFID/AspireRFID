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
package org.ow2.aspirerfid.jonas.webservice;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.ow2.aspirerfid.epcis.server.TagInformationBean;
import org.ow2.aspirerfid.epcis.server.TagInformationRemote;

/**
 * Implementation of the Tag Service.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public class TagServiceImpl implements TagService {
    private TagInformationRemote tagInformationRemote;
    
    /**
     * TODO Javadoc
     */
    public TagServiceImpl() {
        try {
            Context ctx = new InitialContext();
            tagInformationRemote = (TagInformationRemote) ctx
                    .lookup(TagInformationBean.class.getName() + "_"
                            + TagInformationRemote.class.getName() + "@Remote");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.jonas.webservice.TagService#tagHistory(java.lang.String)
     */
    public String tagHistory(String tag) throws RemoteException {
        return tagInformationRemote.tagHistory(tag);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.jonas.webservice.TagService#generalTagInformation(java.lang.String)
     */
    public String generalTagInformation(String tag) throws RemoteException {
        return tagInformationRemote.generalTagInformation(tag);
    }
}
