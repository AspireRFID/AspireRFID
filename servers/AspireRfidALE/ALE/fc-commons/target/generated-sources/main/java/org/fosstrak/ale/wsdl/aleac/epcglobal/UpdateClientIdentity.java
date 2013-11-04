
package org.fosstrak.ale.wsdl.aleac.epcglobal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.fosstrak.ale.xsd.ale.epcglobal.ACClientIdentity;


/**
 * <p>Java class for UpdateClientIdentity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateClientIdentity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="id" type="{urn:epcglobal:ale:xsd:1}ACClientIdentity"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateClientIdentity", propOrder = {
    "identityName",
    "id"
})
public class UpdateClientIdentity {

    @XmlElement(required = true)
    protected String identityName;
    @XmlElement(required = true)
    protected ACClientIdentity id;

    /**
     * Gets the value of the identityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityName() {
        return identityName;
    }

    /**
     * Sets the value of the identityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityName(String value) {
        this.identityName = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link ACClientIdentity }
     *     
     */
    public ACClientIdentity getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACClientIdentity }
     *     
     */
    public void setId(ACClientIdentity value) {
        this.id = value;
    }

}
