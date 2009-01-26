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
package org.ow2.aspirerfid.app.epcis.client.widget.topology;

import java.io.Serializable;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class ArchiElement implements Serializable {
    
    private static final long serialVersionUID = 7417785703913127746L;
    
    public static final int TYPE_READER = 1;
    
    public static final int TYPE_EDGE = 2;
    
    public static final int TYPE_PREMISE = 3;
    
    public static final int TYPE_SERVER = 4;
    
    /**
     * TODO Javadoc
     */
    public String name;
    
    /**
     * TODO Javadoc
     */
    public ArchiElement parent;
    
    /**
     * TODO Javadoc
     */
    public int type;
    
    /**
     * TODO Javadoc
     */
    public ArchiElement() {
        // for serialization
    }
    
    /**
     * TODO Javadoc
     * 
     * @param parent
     * @param name
     * @param type
     */
    public ArchiElement(ArchiElement parent, String name, int type) {
        this.parent = parent;
        this.name = name;
        this.type = type;
    }
}
