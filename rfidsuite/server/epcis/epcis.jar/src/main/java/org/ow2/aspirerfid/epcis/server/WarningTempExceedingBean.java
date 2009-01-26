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
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
@Entity(name = "WARNING_TEMP")
public class WarningTempExceedingBean implements Serializable {
    
    private static final long serialVersionUID = 8337199604654748739L;
    
    private int id;
    
    private Date startTime;
    
    private String operator;
    
    private double value;
    
    private String readerName;
    
    private String name;
    
    /**
     * TODO Javadoc
     */
    public WarningTempExceedingBean() {
    }
    
    /**
     * TODO Javadoc
     * 
     * @param dateBegin
     * @param operator
     * @param value
     * @param readerName
     * @param warningTitle
     */
    public WarningTempExceedingBean(Date dateBegin, String operator,
            double value, String readerName, String warningTitle) {
        setStartTime(dateBegin);
        setOperator(operator);
        setValue(value);
        setReaderName(readerName);
        setName(warningTitle);
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
    @Temporal(TIMESTAMP)
    public Date getStartTime() {
        return startTime;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getOperator() {
        return operator;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public double getValue() {
        return value;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
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
        if (obj instanceof WarningTempExceedingBean) {
            WarningTempExceedingBean warn = (WarningTempExceedingBean) obj;
            if (warn.getId() == this.getId()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public String getReaderName() {
        return readerName;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param readerName
     */
    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }
    
}
