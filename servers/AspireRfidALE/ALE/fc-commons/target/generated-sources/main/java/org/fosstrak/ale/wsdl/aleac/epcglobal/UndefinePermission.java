
package org.fosstrak.ale.wsdl.aleac.epcglobal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UndefinePermission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UndefinePermission">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="permName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UndefinePermission", propOrder = {
    "permName"
})
public class UndefinePermission {

    @XmlElement(required = true)
    protected String permName;

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

}
