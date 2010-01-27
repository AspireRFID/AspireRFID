
package org.ow2.aspirerfid.programmableengine.ale.wsdl.alelr;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.2.4
 * Wed Nov 04 14:26:17 EET 2009
 * Generated source version: 2.2.4
 * 
 */

@WebFault(name = "ReaderLoopException", targetNamespace = "urn:epcglobal:alelr:wsdl:1")
public class ReaderLoopExceptionResponse extends Exception {
    public static final long serialVersionUID = 20091104142617L;
    
    private org.ow2.aspirerfid.programmableengine.ale.wsdl.alelr.ReaderLoopException readerLoopException;

    public ReaderLoopExceptionResponse() {
        super();
    }
    
    public ReaderLoopExceptionResponse(String message) {
        super(message);
    }
    
    public ReaderLoopExceptionResponse(String message, Throwable cause) {
        super(message, cause);
    }

    public ReaderLoopExceptionResponse(String message, org.ow2.aspirerfid.programmableengine.ale.wsdl.alelr.ReaderLoopException readerLoopException) {
        super(message);
        this.readerLoopException = readerLoopException;
    }

    public ReaderLoopExceptionResponse(String message, org.ow2.aspirerfid.programmableengine.ale.wsdl.alelr.ReaderLoopException readerLoopException, Throwable cause) {
        super(message, cause);
        this.readerLoopException = readerLoopException;
    }

    public org.ow2.aspirerfid.programmableengine.ale.wsdl.alelr.ReaderLoopException getFaultInfo() {
        return this.readerLoopException;
    }
}