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

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;

import org.json.JSONObject;
import org.ow2.aspirerfid.epcis.server.util.JSONParser;
import org.ow2.aspirerfid.epcis.server.util.ServerTypeResolver;

/**
 * Implementation of the TagRequester Remote Bean.
 * 
 * @author François Fornaciari
 * @version 2007
 */
@Stateless
@Remote(TagRequesterRemote.class)
public class TagRequesterBean implements TagRequesterRemote {
    
    private static String TAG_SERVICE_NAME = "TagService";
    
    private static String URL_SERVICE_NAME = "URLService";
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.TagRequesterRemote#tagHistories(java.lang.String,
     *      java.lang.String)
     */
    public List<Map<String, String>> tagHistories(String url, String tag) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        
        String[] tagServiceURLs = getEPCISServicesHistory(url, tag);
        for (String tagServiceURL : tagServiceURLs) {
            // Get tag history
            List<Map<String, String>> list = tagHistory(tagServiceURL, tag);
            if (list != null) {
                for (Map<String, String> infos : list) {
                    result.add(infos);
                }
            }
        }
        
        return result;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.TagRequesterRemote#tagHistory(java.lang.String,
     *      java.lang.String)
     */
    public List<Map<String, String>> tagHistory(String url, String tag) {
        try {
            Object o = remoteCall(url, TAG_SERVICE_NAME, "tagHistory",
                    new Object[] {
                        tag
                    });
            if (o != null) {
                String result = (String) o;
                return JSONParser.jsonToList((new JSONObject((String) result)));
            }
        } catch (Exception e) {
            System.out.println("EPC IS Service not reachable.");
        }
        return null;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.TagRequesterRemote#generalTagInformation(java.lang.String,
     *      java.lang.String)
     */
    public Map<String, String> generalTagInformation(String url, String tag) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            Object o = remoteCall(url, TAG_SERVICE_NAME,
                    "generalTagInformation", new Object[] {
                        tag
                    });
            if (o != null) {
                String generalTagInformation = (String) o;
                if (generalTagInformation != null) {
                    return JSONParser.jsonToList(
                            (new JSONObject((String) generalTagInformation)))
                            .get(0);
                }
            }
        } catch (Exception e) {
            System.out.println("EPC IS Service not reachable.");
        }
        return result;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.TagRequesterRemote#getEPCISServiceURL(java.lang.String,
     *      java.lang.String)
     */
    public String getEPCISServiceURL(String url, String tag) {
        String result = null;
        try {
            Object o = remoteCall(url, URL_SERVICE_NAME, "getEPCISServiceURL",
                    new Object[] {
                        tag
                    });
            if (o != null) {
                result = (String) o;
            }
        } catch (Exception e) {
            System.out.println("ONS Service not reachable.");
        }
        return result;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.TagRequesterRemote#getEPCISServicesHistory(java.lang.String,
     *      java.lang.String)
     */
    public String[] getEPCISServicesHistory(String url, String tag) {
        String[] result = new String[0];
        try {
            Object o = remoteCall(url, URL_SERVICE_NAME,
                    "getEPCISServicesHistory", new Object[] {
                        tag
                    });
            if (o != null) {
                String urls = (String) o;
                if (!urls.equals("")) {
                    result = urls.split("\\,");
                }
            }
        } catch (Exception e) {
            System.out.println("ONS Service not reachable.");
        }
        return result;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.TagRequesterRemote#registerEPCISServiceHistory(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public boolean registerEPCISServiceHistory(String url, String tags,
            String serviceURL) {
        try {
            Object o = remoteCall(url, URL_SERVICE_NAME,
                    "registerEPCISServiceHistory", new Object[] {
                            tags, serviceURL
                    });
            if (o != null) {
                return ((Boolean) o).booleanValue();
            }
        } catch (Exception e) {
            // System.out.println("ONS Service not reachable.");
        }
        return false;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.epcis.server.TagRequesterRemote#getEPCISDescription(java.lang.String,
     *      java.lang.String)
     */
    public String getEPCISDescription(String url, String serviceURL) {
        try {
            return (String) remoteCall(url, URL_SERVICE_NAME,
                    "getEPCISDescription", new Object[] {
                        serviceURL
                    });
        } catch (Exception e) {
            System.out.println("ONS Service not reachable.");
        }
        return null;
    }
    
    /**
     * Call a remote method through a web service.
     * 
     * @param url
     *            The WSDL location
     * @param name
     *            The service name
     * @param method
     *            The method name
     * @param params
     *            The method params
     * @return The result of the remote call
     * @throws MalformedURLException
     *             Thrown if the Url is malformed
     * @throws ServiceException
     *             Thrown if the remote call fails
     * @throws RemoteException
     *             Thrown if the remote call fails
     */
    private Object remoteCall(String url, String name, String method,
            Object[] params) throws MalformedURLException, ServiceException,
            RemoteException {
        String ns = "urn:" + name;
        QName qname = new QName(ns, name);
        QName port = new QName(ns, name + "Port");
        QName operation = new QName(ns, method);
        
        ServiceFactory factory = ServiceFactory.newInstance();
        Service service = null;
        Call call = null;
        try {
            if (ServerTypeResolver.serverType() == ServerTypeResolver.JBOSS) {
                service = factory.createService(new URL(url), qname);
                call = service.createCall(port, operation);
            } else if (ServerTypeResolver.serverType() == ServerTypeResolver.JONAS) {
                service = factory.createService(port);
                call = service.createCall(port, operation);
                call.setTargetEndpointAddress(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return call.invoke(params);
    }
    
}
