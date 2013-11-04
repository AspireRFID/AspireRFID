
package org.fosstrak.ale.wsdl.alecc.epcglobal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.fosstrak.ale.xsd.ale.epcglobal.CCParameterList;


/**
 * <p>Java class for Poll complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Poll">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="specName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="params" type="{urn:epcglobal:ale:xsd:1}CCParameterList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Poll", propOrder = {
    "specName",
    "params"
})
public class Poll {

    @XmlElement(required = true)
    protected String specName;
    @XmlElement(required = true)
    protected CCParameterList params;

    /**
     * Gets the value of the specName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecName() {
        return specName;
    }

    /**
     * Sets the value of the specName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecName(String value) {
        this.specName = value;
    }

    /**
     * Gets the value of the params property.
     * 
     * @return
     *     possible object is
     *     {@link CCParameterList }
     *     
     */
    public CCParameterList getParams() {
        return params;
    }

    /**
     * Sets the value of the params property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCParameterList }
     *     
     */
    public void setParams(CCParameterList value) {
        this.params = value;
    }

}
