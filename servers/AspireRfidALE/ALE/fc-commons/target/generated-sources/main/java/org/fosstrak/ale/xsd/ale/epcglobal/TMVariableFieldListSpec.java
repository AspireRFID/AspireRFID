
package org.fosstrak.ale.xsd.ale.epcglobal;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java class for TMVariableFieldListSpec complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TMVariableFieldListSpec">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:epcglobal:ale:xsd:1}TMSpec">
 *       &lt;sequence>
 *         &lt;element name="variableFields" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="variableField" type="{urn:epcglobal:ale:xsd:1}TMVariableFieldSpec" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="extension" type="{urn:epcglobal:ale:xsd:1}TMVariableFieldListSpecExtension" minOccurs="0"/>
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
@XmlType(name = "TMVariableFieldListSpec", propOrder = {
    "variableFields",
    "extension",
    "any"
})
public class TMVariableFieldListSpec
    extends TMSpec
{

    protected TMVariableFieldListSpec.VariableFields variableFields;
    protected TMVariableFieldListSpecExtension extension;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the variableFields property.
     * 
     * @return
     *     possible object is
     *     {@link TMVariableFieldListSpec.VariableFields }
     *     
     */
    public TMVariableFieldListSpec.VariableFields getVariableFields() {
        return variableFields;
    }

    /**
     * Sets the value of the variableFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link TMVariableFieldListSpec.VariableFields }
     *     
     */
    public void setVariableFields(TMVariableFieldListSpec.VariableFields value) {
        this.variableFields = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link TMVariableFieldListSpecExtension }
     *     
     */
    public TMVariableFieldListSpecExtension getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link TMVariableFieldListSpecExtension }
     *     
     */
    public void setExtension(TMVariableFieldListSpecExtension value) {
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
     *         &lt;element name="variableField" type="{urn:epcglobal:ale:xsd:1}TMVariableFieldSpec" maxOccurs="unbounded" minOccurs="0"/>
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
        "variableField"
    })
    public static class VariableFields {

        protected List<TMVariableFieldSpec> variableField;

        /**
         * Gets the value of the variableField property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the variableField property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVariableField().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TMVariableFieldSpec }
         * 
         * 
         */
        public List<TMVariableFieldSpec> getVariableField() {
            if (variableField == null) {
                variableField = new ArrayList<TMVariableFieldSpec>();
            }
            return this.variableField;
        }

    }

}
