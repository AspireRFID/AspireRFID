
package org.fosstrak.ale.wsdl.aletm.epcglobal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.fosstrak.ale.xsd.ale.epcglobal.TMSpec;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.fosstrak.ale.wsdl.aletm.epcglobal package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetTMSpec_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "GetTMSpec");
    private final static QName _GetStandardVersion_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "GetStandardVersion");
    private final static QName _DefineTMSpec_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "DefineTMSpec");
    private final static QName _UndefineTMSpec_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "UndefineTMSpec");
    private final static QName _DuplicateNameException_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "DuplicateNameException");
    private final static QName _ImplementationException_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "ImplementationException");
    private final static QName _GetTMSpecNames_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "GetTMSpecNames");
    private final static QName _GetStandardVersionResult_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "GetStandardVersionResult");
    private final static QName _ALEException_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "ALEException");
    private final static QName _GetTMSpecNamesResult_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "GetTMSpecNamesResult");
    private final static QName _GetTMSpecResult_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "GetTMSpecResult");
    private final static QName _SecurityException_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "SecurityException");
    private final static QName _NoSuchNameException_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "NoSuchNameException");
    private final static QName _GetVendorVersion_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "GetVendorVersion");
    private final static QName _TMSpecValidationException_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "TMSpecValidationException");
    private final static QName _GetVendorVersionResult_QNAME = new QName("urn:epcglobal:aletm:wsdl:1", "GetVendorVersionResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.fosstrak.ale.wsdl.aletm.epcglobal
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UndefineTMSpec }
     * 
     */
    public UndefineTMSpec createUndefineTMSpec() {
        return new UndefineTMSpec();
    }

    /**
     * Create an instance of {@link ALEException }
     * 
     */
    public ALEException createALEException() {
        return new ALEException();
    }

    /**
     * Create an instance of {@link EmptyParms }
     * 
     */
    public EmptyParms createEmptyParms() {
        return new EmptyParms();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link DefineTMSpec }
     * 
     */
    public DefineTMSpec createDefineTMSpec() {
        return new DefineTMSpec();
    }

    /**
     * Create an instance of {@link TMSpecValidationException }
     * 
     */
    public TMSpecValidationException createTMSpecValidationException() {
        return new TMSpecValidationException();
    }

    /**
     * Create an instance of {@link SecurityException }
     * 
     */
    public SecurityException createSecurityException() {
        return new SecurityException();
    }

    /**
     * Create an instance of {@link NoSuchNameException }
     * 
     */
    public NoSuchNameException createNoSuchNameException() {
        return new NoSuchNameException();
    }

    /**
     * Create an instance of {@link DefineTMSpecResult }
     * 
     */
    public DefineTMSpecResult createDefineTMSpecResult() {
        return new DefineTMSpecResult();
    }

    /**
     * Create an instance of {@link GetTMSpec }
     * 
     */
    public GetTMSpec createGetTMSpec() {
        return new GetTMSpec();
    }

    /**
     * Create an instance of {@link DuplicateNameException }
     * 
     */
    public DuplicateNameException createDuplicateNameException() {
        return new DuplicateNameException();
    }

    /**
     * Create an instance of {@link ImplementationException }
     * 
     */
    public ImplementationException createImplementationException() {
        return new ImplementationException();
    }

    /**
     * Create an instance of {@link UndefineTMSpecResult }
     * 
     */
    public UndefineTMSpecResult createUndefineTMSpecResult() {
        return new UndefineTMSpecResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTMSpec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "GetTMSpec")
    public JAXBElement<GetTMSpec> createGetTMSpec(GetTMSpec value) {
        return new JAXBElement<GetTMSpec>(_GetTMSpec_QNAME, GetTMSpec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "GetStandardVersion")
    public JAXBElement<EmptyParms> createGetStandardVersion(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetStandardVersion_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefineTMSpec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "DefineTMSpec")
    public JAXBElement<DefineTMSpec> createDefineTMSpec(DefineTMSpec value) {
        return new JAXBElement<DefineTMSpec>(_DefineTMSpec_QNAME, DefineTMSpec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UndefineTMSpec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "UndefineTMSpec")
    public JAXBElement<UndefineTMSpec> createUndefineTMSpec(UndefineTMSpec value) {
        return new JAXBElement<UndefineTMSpec>(_UndefineTMSpec_QNAME, UndefineTMSpec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DuplicateNameException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "DuplicateNameException")
    public JAXBElement<DuplicateNameException> createDuplicateNameException(DuplicateNameException value) {
        return new JAXBElement<DuplicateNameException>(_DuplicateNameException_QNAME, DuplicateNameException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImplementationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "ImplementationException")
    public JAXBElement<ImplementationException> createImplementationException(ImplementationException value) {
        return new JAXBElement<ImplementationException>(_ImplementationException_QNAME, ImplementationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "GetTMSpecNames")
    public JAXBElement<EmptyParms> createGetTMSpecNames(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetTMSpecNames_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "GetStandardVersionResult")
    public JAXBElement<String> createGetStandardVersionResult(String value) {
        return new JAXBElement<String>(_GetStandardVersionResult_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ALEException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "ALEException")
    public JAXBElement<ALEException> createALEException(ALEException value) {
        return new JAXBElement<ALEException>(_ALEException_QNAME, ALEException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "GetTMSpecNamesResult")
    public JAXBElement<ArrayOfString> createGetTMSpecNamesResult(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_GetTMSpecNamesResult_QNAME, ArrayOfString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TMSpec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "GetTMSpecResult")
    public JAXBElement<TMSpec> createGetTMSpecResult(TMSpec value) {
        return new JAXBElement<TMSpec>(_GetTMSpecResult_QNAME, TMSpec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SecurityException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "SecurityException")
    public JAXBElement<SecurityException> createSecurityException(SecurityException value) {
        return new JAXBElement<SecurityException>(_SecurityException_QNAME, SecurityException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoSuchNameException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "NoSuchNameException")
    public JAXBElement<NoSuchNameException> createNoSuchNameException(NoSuchNameException value) {
        return new JAXBElement<NoSuchNameException>(_NoSuchNameException_QNAME, NoSuchNameException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "GetVendorVersion")
    public JAXBElement<EmptyParms> createGetVendorVersion(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetVendorVersion_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TMSpecValidationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "TMSpecValidationException")
    public JAXBElement<TMSpecValidationException> createTMSpecValidationException(TMSpecValidationException value) {
        return new JAXBElement<TMSpecValidationException>(_TMSpecValidationException_QNAME, TMSpecValidationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:aletm:wsdl:1", name = "GetVendorVersionResult")
    public JAXBElement<String> createGetVendorVersionResult(String value) {
        return new JAXBElement<String>(_GetVendorVersionResult_QNAME, String.class, null, value);
    }

}
