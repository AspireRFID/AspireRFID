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

import org.ow2.aspirerfid.app.epcis.client.RemoteAdminViewAsync;
import org.ow2.aspirerfid.app.epcis.client.util.Util;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.callback.ListWarningsCallbackDelete;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.callback.ListWarningsCallbackGetList;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 * 
 */
public class ListWarnings extends Composite {
    //
    // Constantes
    //
    private static final String TABLE_WIDTH = "270px";
    private static final String ROLE_CELL_WIDTH = "200px";
    private static final String DELETE_CELL_WIDTH = "35px";
    
    //
    // Privates variables
    //
    private Image imgDel;
    
    private HorizontalPanel hp;
    
    private VerticalPanel vp;
    
    private RemoteAdminViewAsync remoteSession;
    
    /**
     * Constructor
     * 
     * @param r
     *            remote session
     */
    public ListWarnings(RemoteAdminViewAsync r) {
        remoteSession = r;
        vp = new VerticalPanel();
        initWidget(vp);
        vp.setWidth(TABLE_WIDTH);
    }
    
    /**
     * Add legend table
     * 
     * @param p
     *            destination panel
     */
    private void addLegend(Panel p) {
        hp = new HorizontalPanel();
        hp.setStyleName("list-title");
        hp.setWidth(TABLE_WIDTH);
        
        // Role
        Label l = new Label("Warning name");
        hp.add(l);
        hp.setCellWidth(l, ROLE_CELL_WIDTH);
        
        l = new Label();
        l.setWidth(DELETE_CELL_WIDTH);
        hp.add(l);
        p.add(hp);
    }
    
    /**
     * Display the widget
     * 
     * @param warnings
     *            List of warnings
     */
    public void display(List warnings) {
        vp.clear();
        
        if (warnings.size() == 0) {
            vp.add(new Label("No saved warning"));
            return;
        }
        addLegend(vp);
        
        boolean lineOne = true;
        for (int i = 0; i < warnings.size(); i++) {
            WarningGWT warning = (WarningGWT) warnings.get(i);
            
            hp = new HorizontalPanel();
            hp.setWidth(TABLE_WIDTH);
            
            if (lineOne) {
                hp.setStyleName("list-rowOne");
            } else {
                hp.setStyleName("list-rowTwo");
            }
            
            lineOne = !lineOne;
            // name
            Label lr;
            if (warning instanceof WarningLostGWT) {
                lr = new Label("LOST_" + warning.getName());
            } else {
                lr = new Label("TEMP_" + warning.getName());
            }
            hp.add(lr);
            hp.setCellWidth(lr, ROLE_CELL_WIDTH);
            
            // delete
            imgDel = new Image();
            imgDel.setTitle("Delete role");
            imgDel.setUrl(Util.DELETE_IMG_URL);
            imgDel.addClickListener(new ListWarningsCallbackDelete(warning,
                    this));
            hp.add(imgDel);
            hp.setCellWidth(imgDel, DELETE_CELL_WIDTH);
            
            vp.add(hp);
        }
    }
    
    /**
     * Update the widget
     */
    public void update() {
        vp.clear();
        loadingMessage();
        remoteSession.getWarnings(new ListWarningsCallbackGetList(this));
    }
    
    /**
     * Define loading message
     */
    private void loadingMessage() {
        vp.add(Util.defaultLoadingMessage());
    }
}
