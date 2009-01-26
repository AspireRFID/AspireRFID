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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

import org.ow2.aspirerfid.app.epcis.client.IndexModule;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class TemperatureChart extends Image {
    
    /**
     * TODO Javadoc
     */
    public TemperatureChart() {
        super();
    }
    
    /**
     * TODO Javadoc
     * 
     * @param tag
     * @param unit
     */
    public TemperatureChart(String tag, String unit) {
        super();
        setTag(tag, unit);
    }
    
    /**
     * TODO Javadoc
     * 
     * @param tag
     * @param unit
     */
    public void setTag(String tag, String unit) {
        AsyncCallback callback = new AsyncCallback() {
            
            public void onSuccess(Object s) {
                String chartName = (String) s;
                if (chartName.compareTo("") == 0) {
                    setUrl("img/delete.gif");
                } else {
                    String imageUrl = "displayChart?filename=" + chartName;
                    setUrl(imageUrl);
                }
            }
            
            public void onFailure(Throwable ex) {
                setUrl("img/delete.gif");
            }
        };
        
        setStyleName("widget-TempChart");
        int w = RootPanel.get().getOffsetWidth() - 200 - 30;
        IndexModule.getSession().getTempatureChart(tag, unit, w, 350, callback);
    }
}
