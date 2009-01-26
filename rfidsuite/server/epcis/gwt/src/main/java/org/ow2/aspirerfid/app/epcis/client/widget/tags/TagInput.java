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
package org.ow2.aspirerfid.app.epcis.client.widget.tags;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.page.layout.TrackOnMapLayout;
import org.ow2.aspirerfid.app.epcis.client.widget.gmap.GMapHelper;
import org.ow2.aspirerfid.app.epcis.client.widget.gmap.GMapMenu;
import org.ow2.aspirerfid.app.epcis.client.widget.inputhelper.InputHelperButtonOpenClickListener;
import org.ow2.aspirerfid.app.epcis.client.widget.table.Table;

import java.util.List;

import asquare.gwt.tk.client.ui.ModalDialog;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.mapitz.gwt.googleMaps.client.GMap2;
import com.mapitz.gwt.googleMaps.client.GMap2Widget;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class TagInput extends Composite {
    
    private static TextBox tbEpc;
    
    private static String unit = "c";
    
    private Table listTagHistory;
    
    private GMap2Widget gmapWidget;
    
    private GMapMenu menu;
    
    /**
     * TODO Javadoc
     * 
     * @param listTagHistory
     * @param gmapWidget
     * @param menu
     */
    public TagInput(Table listTagHistory, GMap2Widget gmapWidget, GMapMenu menu) {
        this.menu = menu;
        this.gmapWidget = gmapWidget;
        this.listTagHistory = listTagHistory;
        Panel pTag = new HorizontalPanel();
        Label lTag = new Label("Tag : ");
        tbEpc = new TextBox();
        tbEpc.setText("urn:epc:id:gid:6543210.123456.12345678b");
        tbEpc.setWidth("350px");
        tbEpc.addKeyboardListener(new TextBoxKeyListener());
        // tbEpc.addChangeListener(new TextBoxChangeListener());
        pTag.add(lTag);
        pTag.add(tbEpc);
        
        Button btValid = new Button("Valid", new ValidClickListener());
        pTag.add(btValid);
        
        Button btInputHelper = new Button();
        btInputHelper.setText("Input helper");
        btInputHelper.addClickListener(new InputHelperButtonOpenClickListener(
                this));
        pTag.add(btInputHelper);
        
        Button btGeneralInformation = new Button("Get general information",
                new GeneralInformationClickListener());
        pTag.add(btGeneralInformation);
        
        initWidget(pTag);
        setStyleName("widget-TagInput");
    }
    
    /**
     * TODO Javadoc
     * 
     * @param tag
     */
    public static void setTag(String tag) {
        tbEpc.setText(tag);
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public static String getTag() {
        return tbEpc.getText();
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public static String getUnit() {
        return unit;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param _unit
     */
    public static void setUnit(String _unit) {
        unit = _unit;
    }
    
    /**
     * TODO Javadoc
     */
    public void updateDependencies() {
        tbEpc.setStyleName("widget-TagInput-valid");
        TrackOnMapLayout.chart.setTag(getTag(), unit);
        if (menu.PartnerIsSelected()) {
            IndexModule.getSession().getFullHistories(TagInput.getTag(),
                    new GetEPCHistoryCallback());
        } else {
            IndexModule.getSession().getEPCHistory(TagInput.getTag(),
                    new GetEPCHistoryCallback());
        }
    }
    
    // private class TextBoxChangeListener implements ChangeListener {
    // public void onChange(Widget sender) {
    // tbEpc.setStyleName("widget-TagInput-notvalid");
    // }
    // }
    
    private class GetEPCHistoryCallback implements AsyncCallback {
        public void onFailure(Throwable caught) {
            IndexModule.getKikooBox().showError("Get EPC History failed", 4000);
        }
        
        public void onSuccess(Object result) {
            GMap2 gmap = gmapWidget.getGmap();
            listTagHistory.setContent((List) result);
            gmap.clearOverlays();
            GMapHelper.addCustomOverlay(gmap, (List) result, menu
                    .PathIsSelected(), menu.MarkerIsSelected(), menu
                    .PartnerIsSelected());
            IndexModule.getKikooBox().showOk("Get EPC History success", 1000);
            
            // move the camera to see new marker
            GMapHelper.focusMarker(gmap, (List) result);
        }
    }
    
    private class TextBoxKeyListener implements KeyboardListener {
        public void onKeyDown(Widget sender, char keyCode, int modifiers) {
        }
        
        /*
         * (non-Javadoc)
         * 
         * @see com.google.gwt.user.client.ui.KeyboardListener#onKeyPress(com.google.gwt.user.client.ui.Widget,
         *      char, int)
         */
        public void onKeyPress(Widget sender, char keyCode, int modifiers) {
            
            if (keyCode == '\r') {
                updateDependencies();
            } else {
                tbEpc.setStyleName("widget-TagInput-notvalid");
            }
        }
        
        /*
         * (non-Javadoc)
         * 
         * @see com.google.gwt.user.client.ui.KeyboardListener#onKeyUp(com.google.gwt.user.client.ui.Widget,
         *      char, int)
         */
        public void onKeyUp(Widget sender, char keyCode, int modifiers) {
        }
    }
    
    private class GeneralInformationClickListener implements ClickListener {
        /*
         * (non-Javadoc)
         * 
         * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
         */
        public void onClick(Widget sender) {
            IndexModule.getSession().getGeneralTagInformation(tbEpc.getText(),
                    new GeneralInformationCallback());
        }
    }
    
    private class GeneralInformationCallback implements AsyncCallback {
        /*
         * (non-Javadoc)
         * 
         * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
         */
        public void onFailure(Throwable caught) {
            IndexModule.getKikooBox().showError(
                    "Get general information failed", 2000);
        }
        
        /*
         * (non-Javadoc)
         * 
         * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
         */
        public void onSuccess(Object result) {
            System.out.println("success");
            ModalDialog alert = new ModalDialog();
            alert.setCaption("General information", false);
            FlexTable flex = new FlexTable();
            alert.add(flex);
            
            if (result != null) {
                TagGeneralInformation tgi = (TagGeneralInformation) result;
                
                if (tgi.getCreationDate() != null) {
                    flex.setText(1, 1, "Tag : " + tgi.getTag());
                    flex.setText(2, 1, "Owner : " + tgi.getOwner());
                    flex.setText(3, 1, "Creation date : "
                            + tgi.getCreationDate());
                    flex.setText(4, 1, "Description : " + tgi.getDescription());
                } else {
                    flex.setText(1, 1, "Tag : " + tgi.getTag());
                    flex.setText(2, 1, "Owner : " + tgi.getOwner());
                    Label error = new Label("Tag unknow");
                    error.setStyleName("warningMessage");
                    flex.setWidget(3, 1, error);
                }
                
            } else {
                flex.setText(1, 1, "General information unreachable");
            }
            
            flex.setWidget(5, 1, new Button("Close", new CloseDialog(alert)));
            
            alert.show();
            alert.center();
        }
        
        private class CloseDialog implements ClickListener {
            
            private ModalDialog dialog;
            
            /**
             * TODO Javadoc
             * 
             * @param m
             */
            public CloseDialog(ModalDialog m) {
                this.dialog = m;
            }
            
            /*
             * (non-Javadoc)
             * 
             * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
             */
            public void onClick(Widget sender) {
                dialog.hide();
            }
        }
    }
    
    private class ValidClickListener implements ClickListener {
        /*
         * (non-Javadoc)
         * 
         * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
         */
        public void onClick(Widget arg0) {
            updateDependencies();
        }
    }
}
