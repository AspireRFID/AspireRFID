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
package org.ow2.aspirerfid.epcis.server.jboss;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import org.jboss.ws.annotation.WebContext;

import org.ow2.aspirerfid.epcis.server.TagInformationRemote;

/**
 * Provides Web Services to allow EPC IS to request many Tag information.
 * 
 * @author François Fornaciari
 * @version 2007
 */
@Stateless
@WebService(name = "TagService", serviceName = "TagService", targetNamespace = "urn:TagService")
@WebContext(contextRoot = "epcisWS", urlPattern = "/TagService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TagServiceBean {
    @EJB
    TagInformationRemote tagInformationRemote = null;
    
    /**
     * Method called by an external EPC IS to retrieve tag information
     * 
     * @param tag
     *            The specific tag
     * @return Tag information
     */
    public String tagHistory(String tag) {
        return tagInformationRemote.tagHistory(tag);
    }
    
    /**
     * Method called by an external EPC IS to retrieve general tag information
     * 
     * @param tag
     *            The specific tag
     * @return Tag information
     */
    public String generalTagInformation(String tag) {
        return tagInformationRemote.generalTagInformation(tag);
    }
}
