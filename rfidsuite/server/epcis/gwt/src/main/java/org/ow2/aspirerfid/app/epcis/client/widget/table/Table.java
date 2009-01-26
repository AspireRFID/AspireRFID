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

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public abstract class Table extends Composite {
    
    private final static int DEFAULT_MAX_RESULT_PER_PAGE = 5;
    
    protected static final String IMG_NEXT = "img/next.png";
    
    protected static final String IMG_PREV = "img/prev.png";
    
    protected static final String IMG_NEXT_DISABLE = "img/next_disable.png";
    
    protected static final String IMG_PREV_DISABLE = "img/prev_disable.png";
    
    protected static final String IMG_UP = "img/triangle_up.gif";
    
    protected static final String IMG_DOWN = "img/triangle_down.gif";
    
    private int currentPage;
    
    protected int maxResultPerPage;
    
    protected long nbResult;
    
    protected Panel mainPanel;
    
    /**
     * TODO Javadoc
     */
    public Table() {
        mainPanel = new VerticalPanel();
        initWidget(mainPanel);
        maxResultPerPage = DEFAULT_MAX_RESULT_PER_PAGE;
        currentPage = 0;
        nbResult = 0;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public int getCurrentPage() {
        return currentPage;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        update();
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public int getMaxResultPerPage() {
        return maxResultPerPage;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public long getNbResult() {
        return nbResult;
    }
    
    /**
     * TODO Javadoc
     * 
     * @return
     */
    public int getNbPage() {
        double exactNbPage = ((double) nbResult) / ((double) maxResultPerPage);
        int nbPage = (int) Math.ceil(exactNbPage);
        
        return nbPage;
    }
    
    /**
     * TODO Javadoc
     * 
     * @param content
     */
    public abstract void setContent(List content);
    
    /**
     * TODO Javadoc
     */
    public abstract void update();
}
