
package org.fosstrak.ale.wsdl.alecc.epcglobal;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.1
 * 2013-10-03T11:31:18.854+02:00
 * Generated source version: 2.6.1
 */

@WebFault(name = "CCSpecValidationException", targetNamespace = "urn:epcglobal:alecc:wsdl:1")
public class CCSpecValidationExceptionResponse extends Exception {
    
    private org.fosstrak.ale.wsdl.alecc.epcglobal.CCSpecValidationException ccSpecValidationException;

    public CCSpecValidationExceptionResponse() {
        super();
    }
    
    public CCSpecValidationExceptionResponse(String message) {
        super(message);
    }
    
    public CCSpecValidationExceptionResponse(String message, Throwable cause) {
        super(message, cause);
    }

    public CCSpecValidationExceptionResponse(String message, org.fosstrak.ale.wsdl.alecc.epcglobal.CCSpecValidationException ccSpecValidationException) {
        super(message);
        this.ccSpecValidationException = ccSpecValidationException;
    }

    public CCSpecValidationExceptionResponse(String message, org.fosstrak.ale.wsdl.alecc.epcglobal.CCSpecValidationException ccSpecValidationException, Throwable cause) {
        super(message, cause);
        this.ccSpecValidationException = ccSpecValidationException;
    }

    public org.fosstrak.ale.wsdl.alecc.epcglobal.CCSpecValidationException getFaultInfo() {
        return this.ccSpecValidationException;
    }
}