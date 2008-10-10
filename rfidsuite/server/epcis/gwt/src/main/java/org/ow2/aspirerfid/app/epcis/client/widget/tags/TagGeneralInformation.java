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
package org.ow2.aspirerfid.app.epcis.client.widget.tags;

import java.io.Serializable;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class TagGeneralInformation implements Serializable {
    
    private static final long serialVersionUID = 8447172749619240808L;
    
    /**
     * The product tag
     */
    private String tag;
    
    /**
     * The creation date
     */
    private String creationDate;
    
    /**
     * The product description
     */
    private String description;
    
    private String owner;
    
    /**
     * TODO Javadoc
     */
    public TagGeneralInformation() {
        
    }
    
    /**
     * Constructor
     * 
     */
    public TagGeneralInformation(String tag, String date, String description,
            String owner) {
        this.tag = tag;
        this.creationDate = date;
        this.description = description;
        this.owner = owner;
    }
    
    /**
     * @return the creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }
    
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }
    
    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }
}
