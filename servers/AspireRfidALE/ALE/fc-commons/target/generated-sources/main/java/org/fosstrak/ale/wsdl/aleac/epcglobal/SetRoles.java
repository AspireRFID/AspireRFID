
package org.fosstrak.ale.wsdl.aleac.epcglobal;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SetRoles complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetRoles">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roleNames" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="roleName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "SetRoles", propOrder = {
    "identityName",
    "roleNames"
})
public class SetRoles {

    @XmlElement(required = true)
    protected String identityName;
    protected SetRoles.RoleNames roleNames;

    /**
     * Gets the value of the identityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityName() {
        return identityName;
    }

    /**
     * Sets the value of the identityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityName(String value) {
        this.identityName = value;
    }

    /**
     * Gets the value of the roleNames property.
     * 
     * @return
     *     possible object is
     *     {@link SetRoles.RoleNames }
     *     
     */
    public SetRoles.RoleNames getRoleNames() {
        return roleNames;
    }

    /**
     * Sets the value of the roleNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link SetRoles.RoleNames }
     *     
     */
    public void setRoleNames(SetRoles.RoleNames value) {
        this.roleNames = value;
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
     *         &lt;element name="roleName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "roleName"
    })
    public static class RoleNames {

        protected List<String> roleName;

        /**
         * Gets the value of the roleName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the roleName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRoleName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getRoleName() {
            if (roleName == null) {
                roleName = new ArrayList<String>();
            }
            return this.roleName;
        }

    }

}
