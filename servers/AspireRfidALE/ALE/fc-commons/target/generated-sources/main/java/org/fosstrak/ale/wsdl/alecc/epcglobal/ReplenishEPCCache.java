
package org.fosstrak.ale.wsdl.alecc.epcglobal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.fosstrak.ale.xsd.ale.epcglobal.EPCPatternList;


/**
 * <p>Java class for ReplenishEPCCache complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReplenishEPCCache">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cacheName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="replenishment" type="{urn:epcglobal:ale:xsd:1}EPCPatternList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReplenishEPCCache", propOrder = {
    "cacheName",
    "replenishment"
})
public class ReplenishEPCCache {

    @XmlElement(required = true)
    protected String cacheName;
    @XmlElement(required = true)
    protected EPCPatternList replenishment;

    /**
     * Gets the value of the cacheName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCacheName() {
        return cacheName;
    }

    /**
     * Sets the value of the cacheName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCacheName(String value) {
        this.cacheName = value;
    }

    /**
     * Gets the value of the replenishment property.
     * 
     * @return
     *     possible object is
     *     {@link EPCPatternList }
     *     
     */
    public EPCPatternList getReplenishment() {
        return replenishment;
    }

    /**
     * Sets the value of the replenishment property.
     * 
     * @param value
     *     allowed object is
     *     {@link EPCPatternList }
     *     
     */
    public void setReplenishment(EPCPatternList value) {
        this.replenishment = value;
    }

}
