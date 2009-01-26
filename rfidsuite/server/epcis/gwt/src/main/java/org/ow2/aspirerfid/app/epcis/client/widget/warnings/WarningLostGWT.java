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
package org.ow2.aspirerfid.app.epcis.client.widget.warnings;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
public class WarningLostGWT implements WarningGWT {
    
    private static final long serialVersionUID = -74140019226491283L;
    
    private int id;
    
    private String reader;
    
    private long duration;
    
    private String name;
    
    /**
     * TODO Javadoc
     */
    public WarningLostGWT() {
    }
    
    /**
     * TODO Javadoc
     * 
     * @param reader
     * @param duration
     * @param name
     * @param id
     */
    public WarningLostGWT(String reader, long duration, String name, int id) {
        setReader(reader);
        setDuration(duration);
        setName(name);
        setId(id);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.app.epcis.client.widget.warnings.WarningGWT#getId()
     */
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
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.app.epcis.client.widget.warnings.WarningGWT#getName()
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
    
}
