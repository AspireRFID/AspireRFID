
package org.fosstrak.ale.xsd.ale.epcglobal;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java class for TMFixedFieldListSpec complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TMFixedFieldListSpec">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:epcglobal:ale:xsd:1}TMSpec">
 *       &lt;sequence>
 *         &lt;element name="fixedFields" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="fixedField" type="{urn:epcglobal:ale:xsd:1}TMFixedFieldSpec" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="extension" type="{urn:epcglobal:ale:xsd:1}TMFixedFieldListSpecExtension" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TMFixedFieldListSpec", propOrder = {
    "fixedFields",
    "extension",
    "any"
})
public class TMFixedFieldListSpec
    extends TMSpec
{

    protected TMFixedFieldListSpec.FixedFields fixedFields;
    protected TMFixedFieldListSpecExtension extension;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the fixedFields property.
     * 
     * @return
     *     possible object is
     *     {@link TMFixedFieldListSpec.FixedFields }
     *     
     */
    public TMFixedFieldListSpec.FixedFields getFixedFields() {
        return fixedFields;
    }

    /**
     * Sets the value of the fixedFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link TMFixedFieldListSpec.FixedFields }
     *     
     */
    public void setFixedFields(TMFixedFieldListSpec.FixedFields value) {
        this.fixedFields = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link TMFixedFieldListSpecExtension }
     *     
     */
    public TMFixedFieldListSpecExtension getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link TMFixedFieldListSpecExtension }
     *     
     */
    public void setExtension(TMFixedFieldListSpecExtension value) {
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="fixedField" type="{urn:epcglobal:ale:xsd:1}TMFixedFieldSpec" maxOccurs="unbounded" minOccurs="0"/>
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
        "fixedField"
    })
    public static class FixedFields {

        protected List<TMFixedFieldSpec> fixedField;

        /**
         * Gets the value of the fixedField property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the fixedField property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFixedField().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TMFixedFieldSpec }
         * 
         * 
         */
        public List<TMFixedFieldSpec> getFixedField() {
            if (fixedField == null) {
                fixedField = new ArrayList<TMFixedFieldSpec>();
            }
            return this.fixedField;
        }

    }

}
