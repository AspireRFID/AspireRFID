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
package org.ow2.aspirerfid.app.epcis.client.widget.warnings.callback;

import java.util.List;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.widget.table.ReportGroupListMemberTable;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
public class GetTagsCallback implements AsyncCallback {
    
    private ReportGroupListMemberTable reportGroupListMemberTable;
    
    /**
     * TODO Javadoc
     * 
     * @param reportGroupListMemberTable
     */
    public GetTagsCallback(ReportGroupListMemberTable reportGroupListMemberTable) {
        this.reportGroupListMemberTable = reportGroupListMemberTable;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
     */
    public void onFailure(Throwable caught) {
        IndexModule.getKikooBox().showError("Call on server failed", 2000);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
     */
    public void onSuccess(Object result) {
        reportGroupListMemberTable.setContent((List) result);
        IndexModule.getKikooBox().showOk("Call on server success", 1000);
    }
}
