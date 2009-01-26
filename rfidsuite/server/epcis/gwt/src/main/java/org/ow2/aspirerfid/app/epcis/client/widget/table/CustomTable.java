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

import java.util.List;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class CustomTable extends Table {
    
    private static final int MAX_RESULT_PER_PAGE = 10;
    
    private DockPanel navPanel;
    
    private List/* <ColumnAndName> */content = null;
    
    private FlexTable table;
    
    /**
     * TODO Javadoc
     */
    public CustomTable() {
        super();
        table = new FlexTable();
        table.setWidth("100%");
        table.setCellPadding(1);
        table.setCellSpacing(0);
        navPanel = new DockPanel();
        navPanel.setWidth("100%");
        navPanel.setStyleName("table-NavBar");
        
        mainPanel.add(table);
        mainPanel.add(navPanel);
        mainPanel.setWidth("100%");
        maxResultPerPage = MAX_RESULT_PER_PAGE;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.app.epcis.client.widget.table.Table#setContent(java.util.List)
     */
    public void setContent(List/* <ListAndName> */content) {
        setCurrentPage(0);
        this.content = content;
        if (content.size() > 0) {
            ListAndName lan = (ListAndName) content.get(0);
            nbResult = lan.list.size();
        } else {
            nbResult = 0;
        }
        display();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.ow2.aspirerfid.app.epcis.client.widget.table.Table#update()
     */
    public void update() {
        if (content != null)
            display();
    }
    
    private void display() {
        table.clear();
        navPanel.clear();
        if (nbResult > 0) {
            displayHeader();
            displayContent();
            displayNavBar();
        } else {
            table.setWidget(1, 1, new Label("No result for this request"));
        }
    }
    
    //
    // PRIVATE METHODS
    //
    private void displayHeader() {
        int columnsSize = content.size();
        for (int i = 0; i < columnsSize; i++) {
            Label label = new Label();
            label.setStyleName("table-Header");
            ListAndName lan = (ListAndName) content.get(i);
            label.setText(lan.columnName);
            table.setWidget(1, i, label);
        }
    }
    
    private void displayNavBar() {
        int nbPage = getNbPage();
        if (nbPage > 1) {
            boolean firstPage = (getCurrentPage() == 0);
            boolean lastPage = (getCurrentPage() == (nbPage - 1));
            
            Image imgPrev;
            if (firstPage) {
                imgPrev = new Image(IMG_PREV_DISABLE);
            } else {
                imgPrev = new Image(IMG_PREV);
                imgPrev.addClickListener(new PrevClickListener());
            }
            
            Image imgNext;
            if (lastPage) {
                imgNext = new Image(IMG_NEXT_DISABLE);
            } else {
                imgNext = new Image(IMG_NEXT);
                imgNext.addClickListener(new NextClickListener());
            }
            
            Label label = new Label((getCurrentPage() + 1) + "/" + nbPage);
            
            HorizontalPanel hp = new HorizontalPanel();
            hp.add(imgPrev);
            hp.add(label);
            hp.add(imgNext);
            navPanel.add(hp, DockPanel.EAST);
            navPanel.setCellHorizontalAlignment(hp, DockPanel.ALIGN_CENTER);
            
        }
    }
    
    private void displayContent() {
        int decal = maxResultPerPage * getCurrentPage();
        int nbColumns = content.size();
        boolean lineOne = true;
        
        for (int j = 0; j < nbColumns; j++) {
            for (int i = 0; i < maxResultPerPage; i++) {
                if (i + decal < nbResult) {
                    ListAndName lan = (ListAndName) content.get(j);
                    List columnContent = lan.list;
                    Label label = new Label((String) columnContent.get(i
                            + decal));
                    if (lineOne) {
                        label.setStyleName("table-Element-LineOne");
                    } else {
                        label.setStyleName("table-Element-LineTwo");
                    }
                    table.setWidget(i + 2, j, label);
                }
            }
            lineOne = !lineOne;
        }
    }
    
    //
    // PUBLIC CLASS
    //
    /**
     * TODO Javadoc
     * 
     * @author Unknown
     */
    public class ListAndName {
        public List/* <String> */list;
        
        public String columnName;
        
        public ListAndName(List list, String name) {
            this.list = list;
            this.columnName = name;
        }
    }
    
    //
    // PRIVATE CLASS
    //
    private class NextClickListener implements ClickListener {
        public void onClick(Widget sender) {
            setCurrentPage(getCurrentPage() + 1);
            display();
        }
    }
    
    private class PrevClickListener implements ClickListener {
        public void onClick(Widget sender) {
            setCurrentPage(getCurrentPage() - 1);
            display();
        }
    }
}
