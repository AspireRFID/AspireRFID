
package org.fosstrak.ale.xsd.ale.epcglobal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * <p>Java class for CCCmdSpec complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CCCmdSpec">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filterSpec" type="{urn:epcglobal:ale:xsd:1}CCFilterSpec" minOccurs="0"/>
 *         &lt;element name="opSpecs" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="opSpec" type="{urn:epcglobal:ale:xsd:1}CCOpSpec" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="statProfileNames" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="statProfileName" type="{urn:epcglobal:ale:xsd:1}CCStatProfileName" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="extension" type="{urn:epcglobal:ale:xsd:1}CCCmdSpecExtension" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="reportIfEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CCCmdSpec", propOrder = {
    "filterSpec",
    "opSpecs",
    "statProfileNames",
    "extension",
    "any"
})
public class CCCmdSpec {

    protected CCFilterSpec filterSpec;
    protected CCCmdSpec.OpSpecs opSpecs;
    protected CCCmdSpec.StatProfileNames statProfileNames;
    protected CCCmdSpecExtension extension;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "reportIfEmpty")
    protected Boolean reportIfEmpty;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the filterSpec property.
     * 
     * @return
     *     possible object is
     *     {@link CCFilterSpec }
     *     
     */
    public CCFilterSpec getFilterSpec() {
        return filterSpec;
    }

    /**
     * Sets the value of the filterSpec property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCFilterSpec }
     *     
     */
    public void setFilterSpec(CCFilterSpec value) {
        this.filterSpec = value;
    }

    /**
     * Gets the value of the opSpecs property.
     * 
     * @return
     *     possible object is
     *     {@link CCCmdSpec.OpSpecs }
     *     
     */
    public CCCmdSpec.OpSpecs getOpSpecs() {
        return opSpecs;
    }

    /**
     * Sets the value of the opSpecs property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCCmdSpec.OpSpecs }
     *     
     */
    public void setOpSpecs(CCCmdSpec.OpSpecs value) {
        this.opSpecs = value;
    }

    /**
     * Gets the value of the statProfileNames property.
     * 
     * @return
     *     possible object is
     *     {@link CCCmdSpec.StatProfileNames }
     *     
     */
    public CCCmdSpec.StatProfileNames getStatProfileNames() {
        return statProfileNames;
    }

    /**
     * Sets the value of the statProfileNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCCmdSpec.StatProfileNames }
     *     
     */
    public void setStatProfileNames(CCCmdSpec.StatProfileNames value) {
        this.statProfileNames = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link CCCmdSpecExtension }
     *     
     */
    public CCCmdSpecExtension getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCCmdSpecExtension }
     *     
     */
    public void setExtension(CCCmdSpecExtension value) {
        this.extension = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the reportIfEmpty property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isReportIfEmpty() {
        if (reportIfEmpty == null) {
            return false;
        } else {
            return reportIfEmpty;
        }
    }

    /**
     * Sets the value of the reportIfEmpty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReportIfEmpty(Boolean value) {
        this.reportIfEmpty = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }


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
     *         &lt;element name="opSpec" type="{urn:epcglobal:ale:xsd:1}CCOpSpec" maxOccurs="unbounded" minOccurs="0"/>
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
        "opSpec"
    })
    public static class OpSpecs {

        protected List<CCOpSpec> opSpec;

        /**
         * Gets the value of the opSpec property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the opSpec property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOpSpec().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CCOpSpec }
         * 
         * 
         */
        public List<CCOpSpec> getOpSpec() {
            if (opSpec == null) {
                opSpec = new ArrayList<CCOpSpec>();
            }
            return this.opSpec;
        }

    }


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
     *         &lt;element name="statProfileName" type="{urn:epcglobal:ale:xsd:1}CCStatProfileName" maxOccurs="unbounded" minOccurs="0"/>
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
        "statProfileName"
    })
    public static class StatProfileNames {

        protected List<String> statProfileName;

        /**
         * Gets the value of the statProfileName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the statProfileName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStatProfileName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getStatProfileName() {
            if (statProfileName == null) {
                statProfileName = new ArrayList<String>();
            }
            return this.statProfileName;
        }

    }

}
