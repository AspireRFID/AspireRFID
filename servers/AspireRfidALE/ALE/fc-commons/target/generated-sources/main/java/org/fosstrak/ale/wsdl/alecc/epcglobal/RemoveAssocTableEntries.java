
package org.fosstrak.ale.wsdl.alecc.epcglobal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.fosstrak.ale.xsd.ale.epcglobal.EPCPatternList;


/**
 * <p>Java class for RemoveAssocTableEntries complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoveAssocTableEntries">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tableName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="patList" type="{urn:epcglobal:ale:xsd:1}EPCPatternList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoveAssocTableEntries", propOrder = {
    "tableName",
    "patList"
})
public class RemoveAssocTableEntries {

    @XmlElement(required = true)
    protected String tableName;
    @XmlElement(required = true)
    protected EPCPatternList patList;

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
     * Gets the value of the patList property.
     * 
     * @return
     *     possible object is
     *     {@link EPCPatternList }
     *     
     */
    public EPCPatternList getPatList() {
        return patList;
    }

    /**
     * Sets the value of the patList property.
     * 
     * @param value
     *     allowed object is
     *     {@link EPCPatternList }
     *     
     */
    public void setPatList(EPCPatternList value) {
        this.patList = value;
    }

}
