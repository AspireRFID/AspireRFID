
package org.fosstrak.ale.wsdl.alecc.epcglobal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UndefineRNG complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UndefineRNG">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rngName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UndefineRNG", propOrder = {
    "rngName"
})
public class UndefineRNG {

    @XmlElement(required = true)
    protected String rngName;

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

}
