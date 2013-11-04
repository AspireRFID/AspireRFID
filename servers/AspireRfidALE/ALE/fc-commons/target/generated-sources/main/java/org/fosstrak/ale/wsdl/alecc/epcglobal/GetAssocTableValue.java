
package org.fosstrak.ale.wsdl.alecc.epcglobal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAssocTableValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetAssocTableValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tableName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="epc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAssocTableValue", propOrder = {
    "tableName",
    "epc"
})
public class GetAssocTableValue {

    @XmlElement(required = true)
    protected String tableName;
    @XmlElement(required = true)
    protected String epc;

    /**
     * Gets the value of the tableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the value of the tableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableName(String value) {
        this.tableName = value;
    }

    /**
     * Gets the value of the epc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEpc() {
        return epc;
    }

    /**
     * Sets the value of the epc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEpc(String value) {
        this.epc = value;
    }

}
