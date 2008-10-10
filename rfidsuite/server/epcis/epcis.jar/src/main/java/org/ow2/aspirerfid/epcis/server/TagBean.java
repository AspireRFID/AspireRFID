/*
 * Copyright 2005-2008, Aspire This library is free software; you can
 * redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation (the "LGPL");
 * either version 2.1 of the License, or (at your option) any later version. If
 * you do not alter this notice, a recipient may use your version of this file
 * under either the LGPL version 2.1, or (at his option) any later version. You
 * should have received a copy of the GNU Lesser General Public License along
 * with this library; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA. This software is
 * distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express
 * or implied. See the GNU Lesser General Public License for the specific
 * language governing rights and limitations.
 */
package org.ow2.aspirerfid.epcis.server;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The Product Bean represents a tagged thing.
 * 
 * @author François Fornaciari
 * @version 2007
 */
@Entity
@Table(name = "TAGS")
@NamedQuery(name = "allProducts", query = "select o from TagBean o")
public class TagBean implements Serializable {
    
    private static final long serialVersionUID = -7086314164537081138L;
    
    /**
     * The product tag
     */
    private String tag;
    
    /**
     * The creation date
     */
    private Date creationDate;
    
    /**
     * The product description
     */
    private String description;
    
    /**
     * Constructor by default
     */
    public TagBean() {
    }
    
    /**
     * @param creationDate
     * @param description
     */
    public TagBean(String tag, Date creationDate, String description) {
        this.tag = tag;
        this.creationDate = creationDate;
        this.description = description;
    }
    
    /**
     * Gets the creation date
     * 
     * @return The creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }
    
    /**
     * Sets the creation date
     * 
     * @param creationDate
     *            The creation date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    /**
     * Gets the product description
     * 
     * @return The product description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the product description
     * 
     * @param description
     *            The product description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Gets the product tag
     * 
     * @return The product tag
     */
    @Id
    public String getTag() {
        return tag;
    }
    
    /**
     * Sets the product tag
     * 
     * @param tag
     *            The product tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
    
}
