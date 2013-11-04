
package org.fosstrak.ale.wsdl.alecc.epcglobal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ALEException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ALEException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ALEException", propOrder = {
    "reason"
})
@XmlSeeAlso({
    SecurityException.class,
    RNGValidationException.class,
    InvalidPatternException.class,
    EPCCacheSpecValidationException.class,
    ParameterForbiddenException.class,
    CCSpecValidationException.class,
    InvalidEPCException.class,
    DuplicateSubscriptionException.class,
    InUseException.class,
    ParameterException.class,
    NoSuchNameException.class,
    InvalidURIException.class,
    InvalidAssocTableEntryException.class,
    NoSuchSubscriberException.class,
    AssocTableValidationException.class,
    ImplementationException.class,
    DuplicateNameException.class
})
public class ALEException {

    @XmlElement(required = true)
    protected String reason;

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

}
