package org.ow2.aspirerfid.ale.core.helloworld;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.5.1
 * 2012-01-11T17:32:18.363+01:00
 * Generated source version: 2.5.1
 * 
 */
@WebService(targetNamespace = "http://sample/", name = "Helloworld")
@XmlSeeAlso({ObjectFactory.class})
public interface Helloworld {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://sample/", className = "sample.SayHello")
    @WebMethod
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://sample/", className = "sample.SayHelloResponse")
    public java.lang.String sayHello(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "saluer", targetNamespace = "http://sample/", className = "sample.Saluer")
    @WebMethod
    @ResponseWrapper(localName = "saluerResponse", targetNamespace = "http://sample/", className = "sample.SaluerResponse")
    public java.lang.String saluer(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
