
package org.ow2.aspirerfid.ale.core.helloworld;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sample package. 
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

    private final static QName _SayHelloArg0_QNAME = new QName("", "arg0");
    private final static QName _SaluerResponseReturn_QNAME = new QName("", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sample
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SaluerResponse }
     * 
     */
    public SaluerResponse createSaluerResponse() {
        return new SaluerResponse();
    }

    /**
     * Create an instance of {@link Saluer }
     * 
     */
    public Saluer createSaluer() {
        return new Saluer();
    }

    /**
     * Create an instance of {@link SayHello }
     * 
     */
    public SayHello createSayHello() {
        return new SayHello();
    }

    /**
     * Create an instance of {@link SayHelloResponse }
     * 
     */
    public SayHelloResponse createSayHelloResponse() {
        return new SayHelloResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg0", scope = SayHello.class)
    public JAXBElement<String> createSayHelloArg0(String value) {
        return new JAXBElement<String>(_SayHelloArg0_QNAME, String.class, SayHello.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg0", scope = Saluer.class)
    public JAXBElement<String> createSaluerArg0(String value) {
        return new JAXBElement<String>(_SayHelloArg0_QNAME, String.class, Saluer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = SaluerResponse.class)
    public JAXBElement<String> createSaluerResponseReturn(String value) {
        return new JAXBElement<String>(_SaluerResponseReturn_QNAME, String.class, SaluerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = SayHelloResponse.class)
    public JAXBElement<String> createSayHelloResponseReturn(String value) {
        return new JAXBElement<String>(_SaluerResponseReturn_QNAME, String.class, SayHelloResponse.class, value);
    }

}
