
package org.fosstrak.ale.xsd.ale.epcglobal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * <p>Java class for CCBoundarySpec complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CCBoundarySpec">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="startTriggerList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="startTrigger" type="{urn:epcglobal:ale:xsd:1}ECTrigger" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="repeatPeriod" type="{urn:epcglobal:ale:xsd:1}ECTime" minOccurs="0"/>
 *         &lt;element name="stopTriggerList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="stopTrigger" type="{urn:epcglobal:ale:xsd:1}ECTrigger" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="duration" type="{urn:epcglobal:ale:xsd:1}ECTime" minOccurs="0"/>
 *         &lt;element name="noNewTagsInterval" type="{urn:epcglobal:ale:xsd:1}ECTime" minOccurs="0"/>
 *         &lt;element name="tagsProcessedCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="afterError" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="extension" type="{urn:epcglobal:ale:xsd:1}CCBoundarySpecExtension" minOccurs="0"/>
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
@XmlType(name = "CCBoundarySpec", propOrder = {
    "startTriggerList",
    "repeatPeriod",
    "stopTriggerList",
    "duration",
    "noNewTagsInterval",
    "tagsProcessedCount",
    "afterError",
    "extension",
    "any"
})
public class CCBoundarySpec {

    protected CCBoundarySpec.StartTriggerList startTriggerList;
    protected ECTime repeatPeriod;
    protected CCBoundarySpec.StopTriggerList stopTriggerList;
    protected ECTime duration;
    protected ECTime noNewTagsInterval;
    protected Integer tagsProcessedCount;
    protected Boolean afterError;
    protected CCBoundarySpecExtension extension;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the startTriggerList property.
     * 
     * @return
     *     possible object is
     *     {@link CCBoundarySpec.StartTriggerList }
     *     
     */
    public CCBoundarySpec.StartTriggerList getStartTriggerList() {
        return startTriggerList;
    }

    /**
     * Sets the value of the startTriggerList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCBoundarySpec.StartTriggerList }
     *     
     */
    public void setStartTriggerList(CCBoundarySpec.StartTriggerList value) {
        this.startTriggerList = value;
    }

    /**
     * Gets the value of the repeatPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link ECTime }
     *     
     */
    public ECTime getRepeatPeriod() {
        return repeatPeriod;
    }

    /**
     * Sets the value of the repeatPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ECTime }
     *     
     */
    public void setRepeatPeriod(ECTime value) {
        this.repeatPeriod = value;
    }

    /**
     * Gets the value of the stopTriggerList property.
     * 
     * @return
     *     possible object is
     *     {@link CCBoundarySpec.StopTriggerList }
     *     
     */
    public CCBoundarySpec.StopTriggerList getStopTriggerList() {
        return stopTriggerList;
    }

    /**
     * Sets the value of the stopTriggerList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCBoundarySpec.StopTriggerList }
     *     
     */
    public void setStopTriggerList(CCBoundarySpec.StopTriggerList value) {
        this.stopTriggerList = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link ECTime }
     *     
     */
    public ECTime getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link ECTime }
     *     
     */
    public void setDuration(ECTime value) {
        this.duration = value;
    }

    /**
     * Gets the value of the noNewTagsInterval property.
     * 
     * @return
     *     possible object is
     *     {@link ECTime }
     *     
     */
    public ECTime getNoNewTagsInterval() {
        return noNewTagsInterval;
    }

    /**
     * Sets the value of the noNewTagsInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link ECTime }
     *     
     */
    public void setNoNewTagsInterval(ECTime value) {
        this.noNewTagsInterval = value;
    }

    /**
     * Gets the value of the tagsProcessedCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTagsProcessedCount() {
        return tagsProcessedCount;
    }

    /**
     * Sets the value of the tagsProcessedCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTagsProcessedCount(Integer value) {
        this.tagsProcessedCount = value;
    }

    /**
     * Gets the value of the afterError property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAfterError() {
        return afterError;
    }

    /**
     * Sets the value of the afterError property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAfterError(Boolean value) {
        this.afterError = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link CCBoundarySpecExtension }
     *     
     */
    public CCBoundarySpecExtension getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCBoundarySpecExtension }
     *     
     */
    public void setExtension(CCBoundarySpecExtension value) {
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
     *         &lt;element name="startTrigger" type="{urn:epcglobal:ale:xsd:1}ECTrigger" maxOccurs="unbounded" minOccurs="0"/>
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
        "startTrigger"
    })
    public static class StartTriggerList {

        protected List<String> startTrigger;

        /**
         * Gets the value of the startTrigger property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the startTrigger property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStartTrigger().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getStartTrigger() {
            if (startTrigger == null) {
                startTrigger = new ArrayList<String>();
            }
            return this.startTrigger;
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
     *         &lt;element name="stopTrigger" type="{urn:epcglobal:ale:xsd:1}ECTrigger" maxOccurs="unbounded" minOccurs="0"/>
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
        "stopTrigger"
    })
    public static class StopTriggerList {

        protected List<String> stopTrigger;

        /**
         * Gets the value of the stopTrigger property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the stopTrigger property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStopTrigger().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getStopTrigger() {
            if (stopTrigger == null) {
                stopTrigger = new ArrayList<String>();
            }
            return this.stopTrigger;
        }

    }

}
