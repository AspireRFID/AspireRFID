
package org.fosstrak.ale.wsdl.ale.epcglobal;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.1
 * 2013-10-03T11:31:17.818+02:00
 * Generated source version: 2.6.1
 */

@WebFault(name = "NoSuchNameException", targetNamespace = "urn:epcglobal:ale:wsdl:1")
public class NoSuchNameExceptionResponse extends Exception {
    
    private org.fosstrak.ale.wsdl.ale.epcglobal.NoSuchNameException noSuchNameException;

    public NoSuchNameExceptionResponse() {
        super();
    }
    
    public NoSuchNameExceptionResponse(String message) {
        super(message);
    }
    
    public NoSuchNameExceptionResponse(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchNameExceptionResponse(String message, org.fosstrak.ale.wsdl.ale.epcglobal.NoSuchNameException noSuchNameException) {
        super(message);
        this.noSuchNameException = noSuchNameException;
    }

    public NoSuchNameExceptionResponse(String message, org.fosstrak.ale.wsdl.ale.epcglobal.NoSuchNameException noSuchNameException, Throwable cause) {
        super(message, cause);
        this.noSuchNameException = noSuchNameException;
    }

    public org.fosstrak.ale.wsdl.ale.epcglobal.NoSuchNameException getFaultInfo() {
        return this.noSuchNameException;
    }
}