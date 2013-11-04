//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.3-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.26 at 03:47:52 �� EET 
//


package org.ow2.aspirerfid.commons.xpdl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.wfmc.org/2002/XPDL1.0}XPDLVersion"/>
 *         &lt;element ref="{http://www.wfmc.org/2002/XPDL1.0}Vendor"/>
 *         &lt;element ref="{http://www.wfmc.org/2002/XPDL1.0}Created"/>
 *         &lt;element ref="{http://www.wfmc.org/2002/XPDL1.0}Description" minOccurs="0"/>
 *         &lt;element ref="{http://www.wfmc.org/2002/XPDL1.0}Documentation" minOccurs="0"/>
 *         &lt;element ref="{http://www.wfmc.org/2002/XPDL1.0}PriorityUnit" minOccurs="0"/>
 *         &lt;element ref="{http://www.wfmc.org/2002/XPDL1.0}CostUnit" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xpdlVersion",
    "vendor",
    "created",
    "description",
    "documentation",
    "priorityUnit",
    "costUnit"
})
@XmlRootElement(name = "PackageHeader")
public class PackageHeader {

    @XmlElement(name = "XPDLVersion", required = true)
    protected String xpdlVersion;
    @XmlElement(name = "Vendor", required = true)
    protected String vendor;
    @XmlElement(name = "Created", required = true)
    protected String created;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Documentation")
    protected String documentation;
    @XmlElement(name = "PriorityUnit")
    protected String priorityUnit;
    @XmlElement(name = "CostUnit")
    protected String costUnit;

    /**
     * Gets the value of the xpdlVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXPDLVersion() {
        return xpdlVersion;
    }

    /**
     * Sets the value of the xpdlVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXPDLVersion(String value) {
        this.xpdlVersion = value;
    }

    /**
     * Gets the value of the vendor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Sets the value of the vendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendor(String value) {
        this.vendor = value;
    }

    /**
     * Gets the value of the created property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreated() {
        return created;
    }

    /**
     * Sets the value of the created property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreated(String value) {
        this.created = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the documentation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentation() {
        return documentation;
    }

    /**
     * Sets the value of the documentation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentation(String value) {
        this.documentation = value;
    }

    /**
     * Gets the value of the priorityUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriorityUnit() {
        return priorityUnit;
    }

    /**
     * Sets the value of the priorityUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriorityUnit(String value) {
        this.priorityUnit = value;
    }

    /**
     * Gets the value of the costUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostUnit() {
        return costUnit;
    }

    /**
     * Sets the value of the costUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostUnit(String value) {
        this.costUnit = value;
    }

}