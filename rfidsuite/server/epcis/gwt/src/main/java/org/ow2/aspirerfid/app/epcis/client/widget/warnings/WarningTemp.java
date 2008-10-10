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

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.widget.IntervalSelector;
import org.ow2.aspirerfid.app.epcis.client.widget.LiveResultListener;
import org.ow2.aspirerfid.app.epcis.client.widget.table.ReportGroupListMemberTable;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.callback.GetReadersCallback;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.callback.GetTagsCallback;
import org.ow2.aspirerfid.app.epcis.client.widget.warnings.callback.SaveCallback;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
public class WarningTemp extends Composite implements LiveResultListener {
    
    private final TextBox valueTextBox;
    
    private final IntervalSelector intervalSelector;
    
    private final ListBox operatorListBox;
    
    private final ReportGroupListMemberTable reportGroupListMemberTable;
    
    private boolean isSaveEnabled;
    
    private final SuggestBox readerNameSuggestBox;
    
    private final MultiWordSuggestOracle oracle;
    
    /**
     * TODO Javadoc
     * 
     * @param reportGroupListMemberTable
     * @param intervalSelector
     * @param enableSave
     */
    public WarningTemp(
            final ReportGroupListMemberTable reportGroupListMemberTable,
            final IntervalSelector intervalSelector, boolean enableSave) {
        
        this.oracle = new MultiWordSuggestOracle();
        this.isSaveEnabled = enableSave;
        this.intervalSelector = intervalSelector;
        this.reportGroupListMemberTable = reportGroupListMemberTable;
        
        final Grid grid = new Grid();
        if (isSaveEnabled) {
            grid.resize(7, 2);
        } else {
            grid.resize(5, 2);
        }
        
        grid.setText(0, 0, "Variable :");
        grid.getCellFormatter().setHorizontalAlignment(0, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);
        grid.setText(1, 0, "Operator :");
        grid.getCellFormatter().setHorizontalAlignment(1, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);
        grid.setText(2, 0, "Value :");
        grid.getCellFormatter().setHorizontalAlignment(2, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);
        grid.setText(3, 0, "Select reader to monitor :");
        grid.getCellFormatter().setHorizontalAlignment(3, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);
        
        final ListBox variableListBox = new ListBox();
        grid.setWidget(0, 1, variableListBox);
        variableListBox.addItem("Temperature (K)");
        
        operatorListBox = new ListBox();
        grid.setWidget(1, 1, operatorListBox);
        operatorListBox.addItem("=");
        operatorListBox.addItem("!=");
        operatorListBox.addItem("<");
        operatorListBox.addItem(">");
        operatorListBox.addItem("<=");
        operatorListBox.addItem(">=");
        
        valueTextBox = new TextBox();
        grid.setWidget(2, 1, valueTextBox);
        valueTextBox.setWidth("100%");
        valueTextBox.setText("0");
        
        readerNameSuggestBox = new SuggestBox(oracle);
        readerNameSuggestBox.setText("ReaderName");
        grid.setWidget(3, 1, readerNameSuggestBox);
        readerNameSuggestBox.setWidth("100%");
        
        final Button runRequestButton = new Button();
        
        if (isSaveEnabled) {
            grid.setWidget(6, 0, runRequestButton);
            grid.getCellFormatter().setHorizontalAlignment(6, 0,
                    HasHorizontalAlignment.ALIGN_RIGHT);
            runRequestButton.setText("Test request");
        } else {
            grid.setWidget(4, 1, runRequestButton);
            runRequestButton.setText("Run request");
        }
        runRequestButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                doReact();
            }
        });
        
        if (isSaveEnabled) {
            
            grid.setText(5, 0, "Warning saving name :");
            grid.getCellFormatter().setHorizontalAlignment(5, 0,
                    HasHorizontalAlignment.ALIGN_RIGHT);
            
            final HTML html = new HTML("<hr>");
            grid.setWidget(4, 1, html);
            
            final TextBox textBox = new TextBox();
            grid.setWidget(5, 1, textBox);
            textBox.setWidth("100%");
            
            final Button saveRequestButton = new Button();
            grid.setWidget(6, 1, saveRequestButton);
            saveRequestButton.setText("Save warning");
            saveRequestButton.addClickListener(new ClickListener() {
                public void onClick(Widget sender) {
                    if (textBox.getText().equals("")) {
                        Window.alert("You must define the warning name");
                    } else {
                        try {
                            double value = new Double(valueTextBox.getText())
                                    .doubleValue();
                            IndexModule.getSession().addWarningTemp(
                                    intervalSelector.getBeginDate(),
                                    intervalSelector.getBeginHours(),
                                    operatorListBox.getItemText(operatorListBox
                                            .getSelectedIndex()), value,
                                    readerNameSuggestBox.getText(),
                                    textBox.getText(), new SaveCallback());
                        } catch (NumberFormatException nfe) {
                            Window.alert("Unrecognized value "
                                    + valueTextBox.getText());
                        }
                    }
                }
            });
        }
        initWidget(grid);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.app.epcis.client.widget.LiveResultListener#doReact()
     */
    public void doReact() {
        try {
            double value = new Double(valueTextBox.getText()).doubleValue();
            IndexModule.getSession().getEPCTemperatureAlert(
                    intervalSelector.getBeginDate(),
                    intervalSelector.getBeginHours(),
                    intervalSelector.getEndDate(),
                    intervalSelector.getEndHours(),
                    operatorListBox.getItemText(operatorListBox
                            .getSelectedIndex()), value,
                    readerNameSuggestBox.getText(),
                    new GetTagsCallback(reportGroupListMemberTable));
        } catch (NumberFormatException nfe) {
            Window.alert("Unrecognized value " + valueTextBox.getText());
        }
    }
    
    /**
     * 
     */
    public void update() {
        IndexModule.getSession().getAllReaders(new GetReadersCallback(oracle));
    }
}
