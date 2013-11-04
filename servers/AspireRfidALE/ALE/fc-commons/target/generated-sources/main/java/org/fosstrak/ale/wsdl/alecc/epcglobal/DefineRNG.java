
package org.fosstrak.ale.wsdl.alecc.epcglobal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.fosstrak.ale.xsd.ale.epcglobal.RNGSpec;


/**
 * <p>Java class for DefineRNG complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DefineRNG">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rngName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rngSpec" type="{urn:epcglobal:ale:xsd:1}RNGSpec"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DefineRNG", propOrder = {
    "rngName",
    "rngSpec"
})
public class DefineRNG {

    @XmlElement(required = true)
    protected String rngName;
    @XmlElement(required = true)
    protected RNGSpec rngSpec;

    /**
     * Gets the value of the rngName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRngName() {
        return rngName;
    }

    /**
     * Sets the value of the rngName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRngName(String value) {
        this.rngName = value;
    }

    /**
     * Gets the value of the rngSpec property.
     * 
     * @return
     *     possible object is
     *     {@link RNGSpec }
     *     
     */
    public RNGSpec getRngSpec() {
        return rngSpec;
    }

    /**
     * Sets the value of the rngSpec property.
     * 
     * @param value
     *     allowed object is
     *     {@link RNGSpec }
     *     
     */
    public void setRngSpec(RNGSpec value) {
        this.rngSpec = value;
    }

}
