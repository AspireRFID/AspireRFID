
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
 * <p>Java class for TMFixedFieldSpec complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TMFixedFieldSpec">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fieldname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bank" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="length" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="offset" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="defaultDatatype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="defaultFormat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extension" type="{urn:epcglobal:ale:xsd:1}TMFixedFieldSpecExtension" minOccurs="0"/>
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
@XmlType(name = "TMFixedFieldSpec", propOrder = {
    "fieldname",
    "bank",
    "length",
    "offset",
    "defaultDatatype",
    "defaultFormat",
    "extension",
    "any"
})
public class TMFixedFieldSpec {

    @XmlElement(required = true)
    protected String fieldname;
    protected int bank;
    protected int length;
    protected int offset;
    @XmlElement(required = true)
    protected String defaultDatatype;
    @XmlElement(required = true)
    protected String defaultFormat;
    protected TMFixedFieldSpecExtension extension;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the fieldname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldname() {
        return fieldname;
    }

    /**
     * Sets the value of the fieldname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldname(String value) {
        this.fieldname = value;
    }

    /**
     * Gets the value of the bank property.
     * 
     */
    public int getBank() {
        return bank;
    }

    /**
     * Sets the value of the bank property.
     * 
     */
    public void setBank(int value) {
        this.bank = value;
    }

    /**
     * Gets the value of the length property.
     * 
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     */
    public void setLength(int value) {
        this.length = value;
    }

    /**
     * Gets the value of the offset property.
     * 
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Sets the value of the offset property.
     * 
     */
    public void setOffset(int value) {
        this.offset = value;
    }

    /**
     * Gets the value of the defaultDatatype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultDatatype() {
        return defaultDatatype;
    }

    /**
     * Sets the value of the defaultDatatype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultDatatype(String value) {
        this.defaultDatatype = value;
    }

    /**
     * Gets the value of the defaultFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultFormat() {
        return defaultFormat;
    }

    /**
     * Sets the value of the defaultFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultFormat(String value) {
        this.defaultFormat = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link TMFixedFieldSpecExtension }
     *     
     */
    public TMFixedFieldSpecExtension getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link TMFixedFieldSpecExtension }
     *     
     */
    public void setExtension(TMFixedFieldSpecExtension value) {
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
