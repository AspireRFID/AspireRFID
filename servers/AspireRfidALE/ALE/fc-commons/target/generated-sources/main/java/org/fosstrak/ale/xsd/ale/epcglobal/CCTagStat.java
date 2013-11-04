
package org.fosstrak.ale.xsd.ale.epcglobal;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CCTagStat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CCTagStat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="profile" type="{urn:epcglobal:ale:xsd:1}CCStatProfileName"/>
 *         &lt;element name="statBlocks" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="statBlock" type="{urn:epcglobal:ale:xsd:1}ECReaderStat" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CCTagStat", propOrder = {
    "profile",
    "statBlocks"
})
public class CCTagStat {

    @XmlElement(required = true)
    protected String profile;
    protected CCTagStat.StatBlocks statBlocks;

    /**
     * Gets the value of the profile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfile(String value) {
        this.profile = value;
    }

    /**
     * Gets the value of the statBlocks property.
     * 
     * @return
     *     possible object is
     *     {@link CCTagStat.StatBlocks }
     *     
     */
    public CCTagStat.StatBlocks getStatBlocks() {
        return statBlocks;
    }

    /**
     * Sets the value of the statBlocks property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCTagStat.StatBlocks }
     *     
     */
    public void setStatBlocks(CCTagStat.StatBlocks value) {
        this.statBlocks = value;
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
     *         &lt;element name="statBlock" type="{urn:epcglobal:ale:xsd:1}ECReaderStat" maxOccurs="unbounded" minOccurs="0"/>
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
        "statBlock"
    })
    public static class StatBlocks {

        protected List<ECReaderStat> statBlock;

        /**
         * Gets the value of the statBlock property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the statBlock property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStatBlock().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ECReaderStat }
         * 
         * 
         */
        public List<ECReaderStat> getStatBlock() {
            if (statBlock == null) {
                statBlock = new ArrayList<ECReaderStat>();
            }
            return this.statBlock;
        }

    }

}
