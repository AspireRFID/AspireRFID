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

import org.ow2.aspirerfid.app.epcis.client.util.Util;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.ListWarnings;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * List role widget callback.
 * 
 * @author Guillaume Vaudaux-Ruth
 * @version
 */
public class ListWarningsCallbackGetList implements AsyncCallback {
    
    private ListWarnings listWarnings;
    
    /**
     * TODO Javadoc
     * 
     * @param listWarnings
     */
    public ListWarningsCallbackGetList(ListWarnings listWarnings) {
        this.listWarnings = listWarnings;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
     */
    public void onFailure(Throwable caught) {
        Util.defaultOnFailure(caught);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
     */
    public void onSuccess(Object result) {
        listWarnings.display((List) result);
    }
}
