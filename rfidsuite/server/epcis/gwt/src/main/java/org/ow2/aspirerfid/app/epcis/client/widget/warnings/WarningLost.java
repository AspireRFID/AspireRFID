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

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.widget.table.ReportGroupListMemberTable;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.callback.GetReadersCallback;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.callback.GetTagsCallback;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.callback.SaveCallback;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel version 2007
 */
public class WarningLost extends Composite {
    
    private final MultiWordSuggestOracle oracle;
    
    /**
     * TODO Javadoc
     * 
     * @param reportGroupListMemberTable
     */
    public WarningLost(
            final ReportGroupListMemberTable reportGroupListMemberTable) {
        
        this.oracle = new MultiWordSuggestOracle();
        
        final FlexTable flexTable = new FlexTable();
        
        initWidget(flexTable);
        
        flexTable.setText(0, 0, "Time elapsed before an object is lost :");
        flexTable.getCellFormatter().setHorizontalAlignment(0, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);
        
        final TextBox timeTextBox = new TextBox();
        timeTextBox.setText("0");
        flexTable.setWidget(0, 1, timeTextBox);
        
        final ListBox timeUnitListBox = new ListBox();
        flexTable.setWidget(0, 2, timeUnitListBox);
        timeUnitListBox.addItem("milliseconds");
        timeUnitListBox.addItem("seconds");
        timeUnitListBox.addItem("minutes");
        timeUnitListBox.addItem("hours");
        timeUnitListBox.addItem("days");
        timeUnitListBox.addItem("months");
        
        flexTable.setText(1, 0, "Select reader to monitor :");
        flexTable.getCellFormatter().setHorizontalAlignment(1, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);
        
        final SuggestBox suggestBox = new SuggestBox(oracle);
        suggestBox.setText("ReaderName");
        flexTable.setWidget(1, 1, suggestBox);
        
        flexTable.setWidget(3, 1, new HTML("<hr>"));
        
        flexTable.setText(4, 0, "Warning saving name :");
        flexTable.getCellFormatter().setHorizontalAlignment(4, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);
        
        final TextBox requestNameTextBox = new TextBox();
        flexTable.setWidget(4, 1, requestNameTextBox);
        
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        flexTable.setWidget(5, 0, horizontalPanel);
        flexTable.getFlexCellFormatter().setColSpan(5, 0, 3);
        flexTable.getCellFormatter().setHorizontalAlignment(5, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);
        horizontalPanel.setSpacing(3);
        
        final Button runRequestButton = new Button();
        horizontalPanel.add(runRequestButton);
        runRequestButton.setText("Test request");
        runRequestButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                long l = parseTimeTextBox(timeTextBox.getText(),
                        timeUnitListBox.getSelectedIndex());
                if (l != -1) {
                    IndexModule.getSession().getLostTags(suggestBox.getText(),
                            l, new GetTagsCallback(reportGroupListMemberTable));
                }
            }
        });
        
        final Button saveRequestButton = new Button();
        horizontalPanel.add(saveRequestButton);
        saveRequestButton.setText("Save warning");
        saveRequestButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                if (requestNameTextBox.getText().equals("")) {
                    Window.alert("You must define the warning name");
                } else {
                    long l = parseTimeTextBox(timeTextBox.getText(),
                            timeUnitListBox.getSelectedIndex());
                    IndexModule.getSession().addWarningLost(
                            suggestBox.getText(), l,
                            requestNameTextBox.getText(), new SaveCallback());
                }
            }
        });
    }
    
    /**
     * TODO Javadoc
     * 
     * @param text
     * @param unit
     * @return
     */
    public long parseTimeTextBox(String text, int unit) {
        long l;
        try {
            l = new Long(text).longValue();
        } catch (NumberFormatException nfe) {
            Window.alert("Unrecognized duration : " + text);
            l = -1;
        }
        switch (unit) {
            case 0:
                // ms
                break;
            case 1:
                // s
                l = l * 1000;
                break;
            case 2:
                // min
                l = l * 1000 * 60;
                break;
            case 3:
                // hours
                l = l * 1000 * 60 * 60;
                break;
            case 4:
                // days
                l = l * 1000 * 60 * 60 * 24;
                break;
            case 5:
                // month
                l = l * 1000 * 60 * 60 * 24 * 30;
                break;
        }
        return l;
    }
    
    /**
     * TODO Javadoc
     */
    public void update() {
        IndexModule.getSession().getAllReaders(new GetReadersCallback(oracle));
    }
    
}
