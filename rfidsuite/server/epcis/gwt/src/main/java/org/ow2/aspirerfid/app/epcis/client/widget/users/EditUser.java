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
package org.ow2.aspirerfid.app.epcis.client.widget.users;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.widget.roles.RoleGWT;
import org.ow2.aspirerfid.app.epcis.client.widget.users.callback.EditUserCallbackGetListRole;
import org.ow2.aspirerfid.app.epcis.client.widget.users.callback.EditUserCallbackSaveButton;

import java.util.Iterator;
import java.util.List;

import asquare.gwt.tk.client.ui.ModalDialog;
import asquare.gwt.tk.client.ui.behavior.FocusModel;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Edit user widget. This widget can 'show' and 'edit' user information.
 * 
 * @author Guillaume Vaudaux-Ruth, Guillaume Surrel
 * @version 2007
 */
public class EditUser extends ModalDialog {
    
    private UserGWT user;
    
    private TextBox tbLogin;
    
    private TextBox tbPass;
    
    private TextBox tbMail;
    
    private ListBox lbRoles;
    
    private Button btSave;
    
    private Button btCancel;
    
    private List roles;
    
    private FlexTable flexTable;
    
    private boolean newUser;
    
    private ListUsers listUsers;
    
    /**
     * Constructor
     * 
     * @param addUser
     *            Client add a new user or modify an existent ?
     */
    public EditUser(boolean addUser, ListUsers list) {
        super();
        this.newUser = addUser;
        this.listUsers = list;
        init();
        setUser(null);
        IndexModule.getSession()
                .getRoles(new EditUserCallbackGetListRole(this));
    }
    
    /**
     * Clear add user form
     * 
     */
    public void clear() {
        tbLogin.setText("");
        tbPass.setText("");
        tbMail.setText("");
        lbRoles.setItemSelected(0, true);
    }
    
    /**
     * Sets role list
     * 
     * @param roles
     *            list of roles - List<Role>
     */
    public void setRoleListBox(List roles) {
        this.roles = roles;
        lbRoles.clear();
        int i = 0;
        for (Iterator iter = roles.iterator(); iter.hasNext();) {
            RoleGWT r = (RoleGWT) iter.next();
            lbRoles.addItem(r.name);
            if (user.getRole() != null && r.name.equals(user.getRole().name)) {
                lbRoles.setSelectedIndex(i);
            }
            i++;
        }
    }
    
    /**
     * Sets user to edit
     * 
     * @param u
     *            user to edit
     */
    public void setUser(UserGWT u) {
        user = u;
        if (user != null) {
            tbLogin.setText(user.getLogin());
            tbPass.setText(user.getPass());
            tbMail.setText(user.getMail());
        } else {
            user = new UserGWT();
        }
    }
    
    /**
     * Gets current user information
     * 
     * @return current user information
     */
    public UserGWT getUser() {
        user.setLogin(tbLogin.getText());
        user.setMail(tbMail.getText());
        user.setPass(tbPass.getText());
        user.setRole(findRole(lbRoles.getItemText(lbRoles.getSelectedIndex())));
        return user;
    }
    
    private void init() {
        tbLogin = new TextBox();
        tbPass = new TextBox();
        tbMail = new TextBox();
        lbRoles = new ListBox();
        btCancel = new Button("Cancel");
        
        FocusModel fModel = this.getFocusModel();
        
        flexTable = new FlexTable();
        flexTable.setText(0, 0, "Login :");
        flexTable.getCellFormatter().setHorizontalAlignment(0, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);
        flexTable.setWidget(0, 1, tbLogin);
        tbLogin.setEnabled(newUser);
        fModel.add(tbLogin);
        
        flexTable.setText(1, 0, "Pass :");
        flexTable.getCellFormatter().setHorizontalAlignment(1, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);
        flexTable.setWidget(1, 1, tbPass);
        fModel.add(tbPass);
        
        flexTable.setText(2, 0, "Mail :");
        flexTable.getCellFormatter().setHorizontalAlignment(2, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);
        flexTable.setWidget(2, 1, tbMail);
        fModel.add(tbMail);
        
        flexTable.setText(3, 0, "Roles :");
        flexTable.getCellFormatter().setHorizontalAlignment(3, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);
        flexTable.setWidget(3, 1, lbRoles);
        fModel.add(lbRoles);
        
        btSave = new Button();
        if (newUser) {
            btSave.setText("Add");
            this.setCaption("Add a user", false);
        } else {
            btSave.setText("Modify");
            this.setCaption("Modify a user", false);
        }
        btSave.addClickListener(new EditUserCallbackSaveButton(this,
                IndexModule.getSession(), newUser, listUsers));
        fModel.add(btSave);
        flexTable.setWidget(4, 0, btSave);
        
        flexTable.setWidget(4, 1, btCancel);
        flexTable.getCellFormatter().setHorizontalAlignment(4, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        btCancel.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                hide();
            }
        });
        fModel.add(btCancel);
        
        if (lbRoles.getItemCount() == 0) {
            lbRoles.addItem("loading...");
        }
        this.add(flexTable);
    }
    
    private RoleGWT findRole(String roleName) {
        for (int i = 0; i < roles.size(); i++) {
            RoleGWT r = (RoleGWT) roles.get(i);
            if (r.name.equals(roleName)) {
                return r;
            }
        }
        return null;
    }
}
