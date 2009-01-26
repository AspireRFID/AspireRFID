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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
public class GetReadersCallback implements AsyncCallback {
    
    private MultiWordSuggestOracle oracle;
    
    /**
     * TODO Javadoc
     * 
     * @param oracle
     */
    public GetReadersCallback(MultiWordSuggestOracle oracle) {
        this.oracle = oracle;
    }
    
    /* (non-Javadoc)
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
     */
    public void onFailure(Throwable caught) {
        IndexModule.getKikooBox().showError("Retrieve readers list failed",
                2000);
    }
    
    /* (non-Javadoc)
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
     */
    public void onSuccess(Object result) {
        IndexModule.getKikooBox().showOk("Retrieve readers list sucess", 1000);
        oracle.addAll((List) result);
    }
    
}
