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

import org.ow2.aspirerfid.app.epcis.client.IndexModule;
import org.ow2.aspirerfid.app.epcis.client.widget.IntervalSelector;
import org.ow2.aspirerfid.app.epcis.client.widget.tags.ReportGroupListMemberGWT;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO Javadoc
 * 
 * @author mcy
 * @version 2007
 */
public class RGLMSequentialTable extends Table {
    
    public static final int FIELD_ID = 0;
    
    public static final int FIELD_EPC = 1;
    
    public static final int FIELD_GATEWAYNAME = 2;
    
    public static final int FIELD_READERNAME = 3;
    
    public static final int FIELD_DATE = 4;
    
    public static final int FIELD_GPS = 5;
    
    public static final int FIELD_TEMPERATURE = 6;
    
    public static final int FIELD_LIST_POSITION = 7;
    
    public static final int FIELD_TAG = 8;
    
    public static final int FIELD_INFO = 9;
    
    private static final int MAX_RESULT_PER_PAGE = 10;
    
    private boolean sortable;
    
    private int currentSort;
    
    private DockPanel navPanel;
    
    private boolean sortDesc;
    
    private List/* <ReportGroupListMember> */content = null;
    
    private FlexTable table;
    
    private List /* <FieldAndName> */columns = null;
    
    private IntervalSelector intervalSelector;
    
    /**
     * TODO Javadoc
     * 
     * @param intervalSelector
     */
    public RGLMSequentialTable(IntervalSelector intervalSelector) {
        super();
        this.intervalSelector = intervalSelector;
        table = new FlexTable();
        table.setWidth("100%");
        table.setCellPadding(1);
        table.setCellSpacing(0);
        navPanel = new DockPanel();
        navPanel.setWidth("100%");
        navPanel.setStyleName("table-NavBar");
        columns = new ArrayList();
        sortable = true;
        currentSort = FIELD_DATE;
        sortDesc = false;
        
        mainPanel.add(table);
        mainPanel.add(navPanel);
        mainPanel.setWidth("100%");
        nbResult = 0L;
        maxResultPerPage = MAX_RESULT_PER_PAGE;
    }
    
    /**
     * @param content
     *            the content to set
     * @uml.property name="content"
     */
    public void setContent(List content) {
        table.clear();
        navPanel.clear();
        
        this.content = content;
        if (nbResult > 0) {
            displayHeader();
            displayContent();
            displayNavBar();
        } else {
            table.setWidget(1, 1, new Label("No result for this request"));
        }
    }
    
    /**
     * call after a page change
     */
    public void update() {
        String bDate = intervalSelector.getBeginDate();
        String eDate = intervalSelector.getEndDate();
        String bHours = intervalSelector.getBeginHours();
        String eHours = intervalSelector.getEndHours();
        IndexModule.getSession().getCountTagEntry(bDate, bHours, eDate, eHours,
                new NbResultCallback(currentSort));
    }
    
    /**
     * TODO Javadoc
     * 
     * @param field
     * @param name
     */
    public void addField(int field, String name) {
        columns.add(new FieldAndName(field, name));
    }
    
    //
    // PRIVATE METHODS
    //
    private void displayHeader() {
        int columnsSize = columns.size();
        for (int i = 0; i < columnsSize; i++) {
            Label label;
            label = new Label();
            label.setStyleName("table-Header");
            label.setWidth("100%");
            Panel p = new HorizontalPanel();
            p.setStyleName("table-Header");
            p.setWidth("100%");
            FieldAndName fan = (FieldAndName) columns.get(i);
            label.setText(fan.name);
            p.add(label);
            
            if (sortable) {
                Panel p2 = new HorizontalPanel();
                Image imgDown = new Image(IMG_DOWN);
                imgDown
                        .addClickListener(new SortClickListener(fan.field,
                                false));
                Image imgUp = new Image(IMG_UP);
                imgUp.addClickListener(new SortClickListener(fan.field, true));
                p2.add(imgDown);
                p2.add(imgUp);
                p.add(p2);
            }
            
            table.setWidget(1, i, p);
        }
    }
    
    private void clearContent() {
        for (int i = 0; i < maxResultPerPage; i++) {
            int columnsSize = columns.size();
            for (int col = 0; col < columnsSize; col++) {
                table.setText(i + 2, col, "");
            }
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
        int columnsSize = columns.size();
        boolean lineOne = true;
        
        for (int i = 0; i < maxResultPerPage; i++) {
            if (i + decal < nbResult) {
                ReportGroupListMemberGWT r = (ReportGroupListMemberGWT) content
                        .get(i);
                for (int j = 0; j < columnsSize; j++) {
                    FieldAndName fan = (FieldAndName) columns.get(j);
                    String text = "";
                    switch (fan.field) {
                        case FIELD_DATE:
                            text = r.getMemberDate().toLocaleString();
                            break;
                        case FIELD_EPC:
                            text = r.getEpc();
                            break;
                        case FIELD_GATEWAYNAME:
                            text = r.getGatewayName();
                            break;
                        case FIELD_GPS:
                            text = r.getGpsCoordinate();
                            break;
                        case FIELD_ID:
                            text = Integer.toString(r.getId());
                            break;
                        case FIELD_READERNAME:
                            text = r.getReaderName();
                            break;
                        case FIELD_TEMPERATURE:
                            text = Double.toString(r.getTemperature());
                            break;
                        case FIELD_LIST_POSITION:
                            text = Integer.toString(i + decal + 1);
                            break;
                        case FIELD_TAG:
                            text = r.getTag();
                            break;
                        case FIELD_INFO:
                            text = r.getAdditionalInfo();
                            break;
                        
                        default:
                            text = "null";
                            break;
                    }
                    Label label = new Label();
                    label.setText(text);
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
    
    public void setNbResult(long nbResult) {
        super.nbResult = nbResult;
    }
    
    //
    // PRIVATE CLASS
    //
    private class NextClickListener implements ClickListener {
        public void onClick(Widget sender) {
            setCurrentPage(getCurrentPage() + 1);
        }
    }
    
    private class PrevClickListener implements ClickListener {
        public void onClick(Widget sender) {
            setCurrentPage(getCurrentPage() - 1);
        }
    }
    
    private class NbResultCallback implements AsyncCallback {
        
        public NbResultCallback(int sortByField) {
            currentSort = sortByField;
        }
        
        public void onFailure(Throwable arg0) {
            
        }
        
        public void onSuccess(Object arg0) {
            nbResult = ((Long) arg0).longValue();
            
            int nbPage = getNbPage();
            int currentPage = getCurrentPage();
            if (nbPage < currentPage) {
                setCurrentPage(nbPage);
            }
            
            if (currentPage < 0) {
                setCurrentPage(0);
            }
            
            String bDate = intervalSelector.getBeginDate();
            String eDate = intervalSelector.getEndDate();
            String bHours = intervalSelector.getBeginHours();
            String eHours = intervalSelector.getEndHours();
            
            IndexModule.getSession().getPartOfTagEntry(bDate, bHours, eDate,
                    eHours, currentPage * maxResultPerPage,
                    ((currentPage + 1) * maxResultPerPage), currentSort,
                    sortDesc, new GetTagEntryCallback());
        }
        
    }
    
    private class GetTagEntryCallback implements AsyncCallback {
        public void onFailure(Throwable arg0) {
        }
        
        public void onSuccess(Object arg0) {
            setContent((List) arg0);
        }
    }
    
    private class FieldAndName {
        public int field;
        
        public String name;
        
        public FieldAndName(int field, String name) {
            this.field = field;
            this.name = name;
        }
    }
    
    /**
     * @return the sortable
     */
    public boolean isSortable() {
        return sortable;
    }
    
    /**
     * @param sortable
     *            the sortable to set
     */
    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }
    
    private class SortClickListener implements ClickListener {
        private int field;
        private boolean desc;
        
        public SortClickListener(int field, boolean minFirst) {
            this.field = field;
            desc = !minFirst;
        }
        
        public void onClick(Widget sender) {
            sortDesc = desc;
            String bDate = intervalSelector.getBeginDate();
            String eDate = intervalSelector.getEndDate();
            String bHours = intervalSelector.getBeginHours();
            String eHours = intervalSelector.getEndHours();
            IndexModule.getSession().getCountTagEntry(bDate, bHours, eDate,
                    eHours, new NbResultCallback(field));
        }
    }
    
    /**
     * @return the currentSort
     */
    public int getCurrentSort() {
        return currentSort;
    }
    
    /**
     * @return the sortDesc
     */
    public boolean isSortDesc() {
        return sortDesc;
    }
    
}
