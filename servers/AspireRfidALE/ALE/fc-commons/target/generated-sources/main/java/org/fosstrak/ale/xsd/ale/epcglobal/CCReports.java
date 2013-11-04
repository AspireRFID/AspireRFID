
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.fosstrak.ale.xsd.epcglobal.Document;
import org.w3c.dom.Element;


/**
 * <p>Java class for CCReports complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CCReports">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:epcglobal:xsd:1}Document">
 *       &lt;sequence>
 *         &lt;element name="CCSpec" type="{urn:epcglobal:ale:xsd:1}CCSpec" minOccurs="0"/>
 *         &lt;element name="cmdReports" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="cmdReport" type="{urn:epcglobal:ale:xsd:1}CCCmdReport" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="extension" type="{urn:epcglobal:ale:xsd:1}CCReportsExtension" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="specName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="date" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="ALEID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="totalMilliseconds" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="initiationCondition" use="required" type="{urn:epcglobal:ale:xsd:1}CCInitiationCondition" />
 *       &lt;attribute name="initiationTrigger" type="{urn:epcglobal:ale:xsd:1}ECTrigger" />
 *       &lt;attribute name="terminationCondition" use="required" type="{urn:epcglobal:ale:xsd:1}CCTerminationCondition" />
 *       &lt;attribute name="terminationTrigger" type="{urn:epcglobal:ale:xsd:1}ECTrigger" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CCReports", propOrder = {
    "ccSpec",
    "cmdReports",
    "extension",
    "any"
})
public class CCReports
    extends Document
{

    @XmlElement(name = "CCSpec")
    protected CCSpec ccSpec;
    protected CCReports.CmdReports cmdReports;
    protected CCReportsExtension extension;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "specName", required = true)
    protected String specName;
    @XmlAttribute(name = "date", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    @XmlAttribute(name = "ALEID", required = true)
    protected String aleid;
    @XmlAttribute(name = "totalMilliseconds", required = true)
    protected long totalMilliseconds;
    @XmlAttribute(name = "initiationCondition", required = true)
    protected String initiationCondition;
    @XmlAttribute(name = "initiationTrigger")
    protected String initiationTrigger;
    @XmlAttribute(name = "terminationCondition", required = true)
    protected String terminationCondition;
    @XmlAttribute(name = "terminationTrigger")
    protected String terminationTrigger;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the ccSpec property.
     * 
     * @return
     *     possible object is
     *     {@link CCSpec }
     *     
     */
    public CCSpec getCCSpec() {
        return ccSpec;
    }

    /**
     * Sets the value of the ccSpec property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCSpec }
     *     
     */
    public void setCCSpec(CCSpec value) {
        this.ccSpec = value;
    }

    /**
     * Gets the value of the cmdReports property.
     * 
     * @return
     *     possible object is
     *     {@link CCReports.CmdReports }
     *     
     */
    public CCReports.CmdReports getCmdReports() {
        return cmdReports;
    }

    /**
     * Sets the value of the cmdReports property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCReports.CmdReports }
     *     
     */
    public void setCmdReports(CCReports.CmdReports value) {
        this.cmdReports = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link CCReportsExtension }
     *     
     */
    public CCReportsExtension getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCReportsExtension }
     *     
     */
    public void setExtension(CCReportsExtension value) {
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
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the aleid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getALEID() {
        return aleid;
    }

    /**
     * Sets the value of the aleid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setALEID(String value) {
        this.aleid = value;
    }

    /**
     * Gets the value of the totalMilliseconds property.
     * 
     */
    public long getTotalMilliseconds() {
        return totalMilliseconds;
    }

    /**
     * Sets the value of the totalMilliseconds property.
     * 
     */
    public void setTotalMilliseconds(long value) {
        this.totalMilliseconds = value;
    }

    /**
     * Gets the value of the initiationCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitiationCondition() {
        return initiationCondition;
    }

    /**
     * Sets the value of the initiationCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitiationCondition(String value) {
        this.initiationCondition = value;
    }

    /**
     * Gets the value of the initiationTrigger property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitiationTrigger() {
        return initiationTrigger;
    }

    /**
     * Sets the value of the initiationTrigger property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitiationTrigger(String value) {
        this.initiationTrigger = value;
    }

    /**
     * Gets the value of the terminationCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminationCondition() {
        return terminationCondition;
    }

    /**
     * Sets the value of the terminationCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminationCondition(String value) {
        this.terminationCondition = value;
    }

    /**
     * Gets the value of the terminationTrigger property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminationTrigger() {
        return terminationTrigger;
    }

    /**
     * Sets the value of the terminationTrigger property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminationTrigger(String value) {
        this.terminationTrigger = value;
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
     *         &lt;element name="cmdReport" type="{urn:epcglobal:ale:xsd:1}CCCmdReport" maxOccurs="unbounded" minOccurs="0"/>
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
        "cmdReport"
    })
    public static class CmdReports {

        protected List<CCCmdReport> cmdReport;

        /**
         * Gets the value of the cmdReport property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cmdReport property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCmdReport().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CCCmdReport }
         * 
         * 
         */
        public List<CCCmdReport> getCmdReport() {
            if (cmdReport == null) {
                cmdReport = new ArrayList<CCCmdReport>();
            }
            return this.cmdReport;
        }

    }

}
