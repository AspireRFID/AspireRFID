
package org.fosstrak.ale.xsd.ale.epcglobal;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.fosstrak.ale.xsd.epcglobal.Document;


/**
 * <p>Java class for TMSpec complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TMSpec">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:epcglobal:xsd:1}Document">
 *       &lt;sequence>
 *         &lt;element name="baseExtension" type="{urn:epcglobal:ale:xsd:1}TMSpecExtension" minOccurs="0"/>
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
@XmlType(name = "TMSpec", propOrder = {
    "baseExtension"
})
@XmlSeeAlso({
    TMFixedFieldListSpec.class,
    TMVariableFieldListSpec.class
})
public abstract class TMSpec
    extends Document
{

    protected TMSpecExtension baseExtension;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the baseExtension property.
     * 
     * @return
     *     possible object is
     *     {@link TMSpecExtension }
     *     
     */
    public TMSpecExtension getBaseExtension() {
        return baseExtension;
    }

    /**
     * Sets the value of the baseExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link TMSpecExtension }
     *     
     */
    public void setBaseExtension(TMSpecExtension value) {
        this.baseExtension = value;
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
