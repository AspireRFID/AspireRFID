
package org.fosstrak.ale.xsd.ale.epcglobal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * <p>Java class for CCOpSpec complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CCOpSpec">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="opType" type="{urn:epcglobal:ale:xsd:1}CCOpType"/>
 *         &lt;element name="fieldspec" type="{urn:epcglobal:ale:xsd:1}ECFieldSpec" minOccurs="0"/>
 *         &lt;element name="dataSpec" type="{urn:epcglobal:ale:xsd:1}CCOpDataSpec" minOccurs="0"/>
 *         &lt;element name="extension" type="{urn:epcglobal:ale:xsd:1}CCOpSpecExtension" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CCOpSpec", propOrder = {
    "opType",
    "fieldspec",
    "dataSpec",
    "extension",
    "any"
})
public class CCOpSpec {

    @XmlElement(required = true)
    protected String opType;
    protected ECFieldSpec fieldspec;
    protected CCOpDataSpec dataSpec;
    protected CCOpSpecExtension extension;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the opType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpType() {
        return opType;
    }

    /**
     * Sets the value of the opType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpType(String value) {
        this.opType = value;
    }

    /**
     * Gets the value of the fieldspec property.
     * 
     * @return
     *     possible object is
     *     {@link ECFieldSpec }
     *     
     */
    public ECFieldSpec getFieldspec() {
        return fieldspec;
    }

    /**
     * Sets the value of the fieldspec property.
     * 
     * @param value
     *     allowed object is
     *     {@link ECFieldSpec }
     *     
     */
    public void setFieldspec(ECFieldSpec value) {
        this.fieldspec = value;
    }

    /**
     * Gets the value of the dataSpec property.
     * 
     * @return
     *     possible object is
     *     {@link CCOpDataSpec }
     *     
     */
    public CCOpDataSpec getDataSpec() {
        return dataSpec;
    }

    /**
     * Sets the value of the dataSpec property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCOpDataSpec }
     *     
     */
    public void setDataSpec(CCOpDataSpec value) {
        this.dataSpec = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link CCOpSpecExtension }
     *     
     */
    public CCOpSpecExtension getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCOpSpecExtension }
     *     
     */
    public void setExtension(CCOpSpecExtension value) {
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

}
