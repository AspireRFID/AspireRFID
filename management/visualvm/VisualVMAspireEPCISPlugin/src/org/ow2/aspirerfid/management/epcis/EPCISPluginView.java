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
package org.ow2.aspirerfid.management.epcis;

import com.sun.tools.visualvm.application.Application;
import com.sun.tools.visualvm.core.ui.DataSourceView;
import com.sun.tools.visualvm.core.ui.components.DataViewComponent;
import com.sun.tools.visualvm.tools.jmx.JmxModel;
import com.sun.tools.visualvm.tools.jmx.JmxModelFactory;
import javax.management.MBeanServerConnection;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.openide.util.Exceptions;
import org.openide.util.Utilities;
/**
 *
 * @author Kiev
 */
public class EPCISPluginView extends DataSourceView {
    private DataViewComponent dvc;
    private MBeanServerConnection mbs;
    //Make sure there is an image at this location in your project:
    private static final String IMAGE_PATH = "resources/aspireicon.png"; // NOI18N

    public EPCISPluginView(Application application) {
        super(application,"Aspire EPCIS", new ImageIcon(Utilities.loadImage(IMAGE_PATH, true)).getImage(), 60, false);
        this.mbs = getConnection(application);
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
        DataViewComponent.MasterView masterView = new DataViewComponent.MasterView("EPC Information Services", null, new JLabel("EPCIS Details"));

        //Configuration of master view:
        DataViewComponent.MasterViewConfiguration masterConfiguration =
                new DataViewComponent.MasterViewConfiguration(false);

        //Add the master view and configuration view to the component:
        dvc = new DataViewComponent(masterView, masterConfiguration);

         return dvc;

    }

}