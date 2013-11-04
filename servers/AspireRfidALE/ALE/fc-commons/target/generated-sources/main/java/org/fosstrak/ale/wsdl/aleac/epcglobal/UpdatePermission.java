
package org.fosstrak.ale.wsdl.aleac.epcglobal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.fosstrak.ale.xsd.ale.epcglobal.ACPermission;


/**
 * <p>Java class for UpdatePermission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdatePermission">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="permName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="perm" type="{urn:epcglobal:ale:xsd:1}ACPermission"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdatePermission", propOrder = {
    "permName",
    "perm"
})
public class UpdatePermission {

    @XmlElement(required = true)
    protected String permName;
    @XmlElement(required = true)
    protected ACPermission perm;

    /**
     * Gets the value of the permName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermName() {
        return permName;
    }

    /**
     * Sets the value of the permName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermName(String value) {
        this.permName = value;
    }

    /**
     * Gets the value of the perm property.
     * 
     * @return
     *     possible object is
     *     {@link ACPermission }
     *     
     */
    public ACPermission getPerm() {
        return perm;
    }

    /**
     * Sets the value of the perm property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACPermission }
     *     
     */
    public void setPerm(ACPermission value) {
        this.perm = value;
    }

}
