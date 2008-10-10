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

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
@Entity(name = "WARNING_LOST")
public class WarningLostBean implements Serializable {
    
    private static final long serialVersionUID = 6119729727330389027L;
    
    private int id;
    
    private String reader;
    
    private long duration;
    
    private String name;
    
    /**
     * TODO Javadoc
     */
    public WarningLostBean() {
    }
    
    /**
     * TODO Javadoc
     * 
     * @param reader
     * @param duration
     * @param name
     */
    public WarningLostBean(String reader, long duration, String name) {
        setReader(reader);
        setDuration(duration);
        setName(name);
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    @Id
    @GeneratedValue(strategy = AUTO)
    public int getId() {
        return id;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getReader() {
        return reader;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param reader
     */
    public void setReader(String reader) {
        this.reader = reader;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public long getDuration() {
        return duration;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param duration
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WarningLostBean) {
            WarningLostBean warn = (WarningLostBean) obj;
            if (warn.getId() == this.getId()) {
                return true;
            }
        }
        return false;
    }
    
}
