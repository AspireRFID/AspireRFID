
package org.fosstrak.ale.wsdl.alecc.epcglobal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.fosstrak.ale.xsd.ale.epcglobal.AssocTableEntryList;


/**
 * <p>Java class for PutAssocTableEntries complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PutAssocTableEntries">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tableName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="entries" type="{urn:epcglobal:ale:xsd:1}AssocTableEntryList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutAssocTableEntries", propOrder = {
    "tableName",
    "entries"
})
public class PutAssocTableEntries {

    @XmlElement(required = true)
    protected String tableName;
    @XmlElement(required = true)
    protected AssocTableEntryList entries;

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
     * Gets the value of the entries property.
     * 
     * @return
     *     possible object is
     *     {@link AssocTableEntryList }
     *     
     */
    public AssocTableEntryList getEntries() {
        return entries;
    }

    /**
     * Sets the value of the entries property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssocTableEntryList }
     *     
     */
    public void setEntries(AssocTableEntryList value) {
        this.entries = value;
    }

}
