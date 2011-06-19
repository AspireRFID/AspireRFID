/*
 * Copyright © 2008-2010, Aspire
 *
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.management;

import com.sun.tools.visualvm.application.Application;
import com.sun.tools.visualvm.core.ui.DataSourceView;
import com.sun.tools.visualvm.core.ui.components.DataViewComponent;
import com.sun.tools.visualvm.tools.jmx.JmxModel;
import com.sun.tools.visualvm.tools.jmx.JmxModelFactory;
import javax.management.MBeanServerConnection;
import javax.swing.ImageIcon;
import org.openide.util.Exceptions;
import org.openide.util.Utilities;
import org.ow2.aspirerfid.management.model.PluginModel;
import org.ow2.aspirerfid.management.views.ALEGeneralInfoPanel;
import org.ow2.aspirerfid.management.views.ECSpecPanel;
import org.ow2.aspirerfid.management.views.LRSpecPanel;
import org.ow2.aspirerfid.management.views.SensorsPanel;

/**
 *
 * @author Kiev
 */
public class AspirePluginView extends DataSourceView {
    private PluginModel model;
    private DataViewComponent dvc;
    private MBeanServerConnection mbs;
    //Make sure there is an image at this location in your project:
    private static final String IMAGE_PATH = "resources/aspireicon.png"; // NOI18N

    public AspirePluginView(Application application) {
        super(application,"Aspire ALEServer", new ImageIcon(Utilities.loadImage(IMAGE_PATH, true)).getImage(), 60, false);
        this.mbs = getConnection(application);
        this.model = new PluginModel(mbs);
    }


    private MBeanServerConnection getConnection(Application application) {
        MBeanServerConnection mbs = null;

        try {
            JmxModel jmxmodel = JmxModelFactory.getJmxModelFor(application);
            if (jmxmodel != null) {
                mbs = jmxmodel.getMBeanServerConnection();
            }
        } catch (NullPointerException ex) {
            Exceptions.printStackTrace(ex);
        }
        return mbs;

    }

    protected DataViewComponent createComponent() {

        //Master view:
        DataViewComponent.MasterView masterView = new DataViewComponent.MasterView("ALE Server Overview", null, new ALEGeneralInfoPanel(model));

        //Configuration of master view:
        DataViewComponent.MasterViewConfiguration masterConfiguration =
                new DataViewComponent.MasterViewConfiguration(false);

        //Add the master view and configuration view to the component:
        dvc = new DataViewComponent(masterView, masterConfiguration);

        //Add detail views to the component:
        dvc.addDetailsView(new DataViewComponent.DetailsView(
                "LRSpecs", null, 10, new LRSpecPanel(model), null), DataViewComponent.TOP_RIGHT);

        dvc.addDetailsView(new DataViewComponent.DetailsView(
                "ECSpecs", null, 20, new ECSpecPanel(model), null), DataViewComponent.TOP_RIGHT);
        
        if (model.isSensorDataAvailable()) {
            dvc.addDetailsView(new DataViewComponent.DetailsView(
                    "Sensors", null, 30, new SensorsPanel(model), null), DataViewComponent.TOP_RIGHT);
        }
        return dvc;

    }
}