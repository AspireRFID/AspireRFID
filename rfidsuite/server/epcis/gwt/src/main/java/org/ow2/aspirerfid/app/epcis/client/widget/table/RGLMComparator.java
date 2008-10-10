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
package org.ow2.aspirerfid.app.epcis.client.widget.table;

import java.util.Comparator;

import org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class RGLMComparator implements Comparator {
    
    private int field;
    
    /**
     * TODO Javadoc
     * 
     * @param field
     */
    public RGLMComparator(int field) {
        this.field = field;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2) {
        ReportGroupListMemberGWT r1 = (ReportGroupListMemberGWT) o1;
        ReportGroupListMemberGWT r2 = (ReportGroupListMemberGWT) o2;
        
        switch (field) {
            case ReportGroupListMemberTable.FIELD_DATE:
                return r1.getMemberDate().compareTo(r2.getMemberDate());
            case ReportGroupListMemberTable.FIELD_EPC:
                return r1.getEpc().compareTo(r2.getEpc());
            case ReportGroupListMemberTable.FIELD_GATEWAYNAME:
                return r1.getGatewayName().compareTo(r2.getGatewayName());
            case ReportGroupListMemberTable.FIELD_GPS:
                return r1.getGpsCoordinate().compareTo(r2.getGpsCoordinate());
            case ReportGroupListMemberTable.FIELD_ID:
                if (r1.getId() < r2.getId()) {
                    return -1;
                } else if (r1.getId() == r2.getId()) {
                    return 0;
                } else {
                    return 1;
                }
            case ReportGroupListMemberTable.FIELD_READERNAME:
                return r1.getReaderName().compareTo(r2.getReaderName());
            case ReportGroupListMemberTable.FIELD_TEMPERATURE:
                if (r1.getTemperature() < r2.getTemperature()) {
                    return -1;
                } else if (r1.getTemperature() == r2.getTemperature()) {
                    return 0;
                } else {
                    return 1;
                }
            case ReportGroupListMemberTable.FIELD_LIST_POSITION:
                return -1;
            case ReportGroupListMemberTable.FIELD_TAG:
                return r1.getTag().compareTo(r2.getTag());
            case ReportGroupListMemberTable.FIELD_INFO:
                return r1.getAdditionalInfo().compareTo(r2.getAdditionalInfo());
            default:
                return 0;
        }
    }
}
