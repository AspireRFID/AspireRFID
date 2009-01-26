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
package org.ow2.aspirerfid.app.epcis.client.widget.users.callback;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.RemoteAdminViewAsync;
import org.ow2.aspirerfid.app.epcis.client.widget.users.EditUser;
import org.ow2.aspirerfid.app.epcis.client.widget.users.ListUsers;
import org.ow2.aspirerfid.app.epcis.client.widget.users.UserGWT;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class EditUserCallbackSaveButton implements ClickListener, AsyncCallback {
    private EditUser editUser;
    
    private RemoteAdminViewAsync remoteView;
    
    private boolean newUser;
    
    private ListUsers listUsers;
    
    /**
     * TODO Javadoc
     * 
     * @param editUser
     * @param remoteView
     * @param newUser
     * @param listUsers
     */
    public EditUserCallbackSaveButton(EditUser editUser,
            RemoteAdminViewAsync remoteView, boolean newUser,
            ListUsers listUsers) {
        this.editUser = editUser;
        this.remoteView = remoteView;
        this.newUser = newUser;
        this.listUsers = listUsers;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
     */
    public void onClick(Widget sender) {
        UserGWT user = editUser.getUser();
        
        // Add a new user ?
        if (newUser) {
            remoteView.addUser(user.getLogin(), user.getPass(), user.getMail(),
                    user.getRole().getId(), this);
            editUser.hide();
        }
        // Change a existent user
        else {
            remoteView.modifyUser(user.getOldLogin(), user.getLogin(), user
                    .getPass(), user.getMail(), user.getRole().getId(), this);
            editUser.hide();
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
     */
    public void onFailure(Throwable caught) {
        IndexModule.getKikooBox().showError("Save user failed", 2000);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
     */
    public void onSuccess(Object result) {
        IndexModule.getKikooBox().showOk("Save user success", 2000);
        listUsers.update();
    }
}
