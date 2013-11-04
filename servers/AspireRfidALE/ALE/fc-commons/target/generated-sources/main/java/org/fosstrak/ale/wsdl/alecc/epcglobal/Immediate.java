
package org.fosstrak.ale.wsdl.alecc.epcglobal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.fosstrak.ale.xsd.ale.epcglobal.CCSpec;


/**
 * <p>Java class for Immediate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Immediate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="spec" type="{urn:epcglobal:ale:xsd:1}CCSpec"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Immediate", propOrder = {
    "spec"
})
public class Immediate {

    @XmlElement(required = true)
    protected CCSpec spec;

    /**
     * Gets the value of the spec property.
     * 
     * @return
     *     possible object is
     *     {@link CCSpec }
     *     
     */
    public CCSpec getSpec() {
        return spec;
    }

    /**
     * Sets the value of the spec property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCSpec }
     *     
     */
    public void setSpec(CCSpec value) {
        this.spec = value;
    }

}
