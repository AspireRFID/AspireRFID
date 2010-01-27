
/*
 * 
 */

package org.ow2.aspirerfid.programmableengine.ale.wsdl.ale;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.4
 * Wed Nov 04 12:55:11 EET 2009
 * Generated source version: 2.2.4
 * 
 */


@WebServiceClient(name = "ALEService", 
                  wsdlLocation = "file:EPCglobal-ale-1_1-ale.wsdl",
                  targetNamespace = "urn:epcglobal:ale:wsdl:1") 
public class ALEService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("urn:epcglobal:ale:wsdl:1", "ALEService");
    public final static QName ALEServicePort = new QName("urn:epcglobal:ale:wsdl:1", "ALEServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:EPCglobal-ale-1_1-ale.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:EPCglobal-ale-1_1-ale.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public ALEService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ALEService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ALEService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns ALEServicePortType
     */
    @WebEndpoint(name = "ALEServicePort")
    public ALEServicePortType getALEServicePort() {
        return super.getPort(ALEServicePort, ALEServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ALEServicePortType
     */
    @WebEndpoint(name = "ALEServicePort")
    public ALEServicePortType getALEServicePort(WebServiceFeature... features) {
        return super.getPort(ALEServicePort, ALEServicePortType.class, features);
    }

}