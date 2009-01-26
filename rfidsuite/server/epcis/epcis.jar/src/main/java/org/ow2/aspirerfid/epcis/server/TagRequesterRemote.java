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

import java.util.List;
import java.util.Map;

/**
 * This remote Bean allows the EPC IS to request Tag information through a web
 * service to another external EPC IS.
 * 
 * @author François Fornaciari
 * @version 2007
 */
public interface TagRequesterRemote {
    /**
     * Requests tag information from unique EPC IS about a specific tag. The
     * result is a list of tag information. The tag properties are stored into a
     * Map (the tag name, the gateway name, the GPS coordinates, the temperature
     * and the tag date).
     * 
     * @param url
     *            Endpoint URL
     * @param tag
     *            A specific tag
     * @return List of tag information
     */
    public List<Map<String, String>> tagHistory(String url, String tag);
    
    /**
     * Requests tag information from multiple EPS IS about a specific tag. The
     * result is a list of tag information. The tag properties are stored into a
     * Map (the tag name, the gateway name, the GPS coordinates, the temperature
     * and the tag date).
     * 
     * @param url
     *            Endpoint URL
     * @param tag
     *            A specific tag
     * @return List of tag information
     */
    public List<Map<String, String>> tagHistories(String url, String tag);
    
    /**
     * Requests tag information about a specific tag. The tag properties are
     * stored into a Map (the tag name, the description and the creation date).
     * 
     * @param url
     *            Endpoint URL
     * @param tag
     *            A specific tag
     * @return List of tag information
     */
    public Map<String, String> generalTagInformation(String url, String tag);
    
    /**
     * Method called by an EPC IS to retrieve the EPC IS provider service URL
     * and description for a specific tag. This method is an ONS functionality.
     * 
     * @param url
     *            Endpoint URL
     * @param tag
     *            The specific tag
     * @return The service URL
     */
    public String getEPCISServiceURL(String url, String tag);
    
    /**
     * Get the list of EPC IS services URLs for the specified tag. This method
     * is a Discovery Service functionality.
     * 
     * @param url
     *            Endpoint URL
     * @param tag
     *            The specified tag
     * @return The list of service URLs for the specified tag
     */
    public String[] getEPCISServicesHistory(String url, String tag);
    
    /**
     * Register a new EPC IS service in order to update the tag history. This
     * method is a Discovery Service functionality. Returns true if the update
     * has succeed.
     * 
     * @param url
     *            Endpoint URL
     * @param tags
     *            The specified tags
     * @param serviceURL
     *            Service URL of the EPC IS which declares the tag
     * @return True if the update has succeed.
     */
    public boolean registerEPCISServiceHistory(String url, String tags,
            String serviceURL);
    
    /**
     * Get the EPC IS description name depending on the EPC IS service URL.
     * 
     * @param url
     *            Endpoint URL
     * @param serviceURL
     *            The specified tag
     * @return The EPC IS description name
     */
    public String getEPCISDescription(String url, String serviceURL);
}
