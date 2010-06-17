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
import com.sun.tools.visualvm.core.ui.DataSourceViewProvider;
import com.sun.tools.visualvm.core.ui.DataSourceViewsManager;
import com.sun.tools.visualvm.tools.jmx.JmxModelFactory;
import com.sun.tools.visualvm.tools.jmx.JmxModel;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import org.openide.util.Exceptions;

/**
 *
 * @author Kiev
 */
public class ViewProvider extends DataSourceViewProvider<Application> {
    private static DataSourceViewProvider<Application> instance =  new ViewProvider();

    public boolean supportsViewFor(Application application) {
        boolean osgiMBeanAvailable = false;
        try {
            ObjectName name = new ObjectName("org.ow2.aspirerfid:type=aleserver,name=server");
            JmxModel model = JmxModelFactory.getJmxModelFor(application);
            if (model != null) {
                MBeanServerConnection mbs = model.getMBeanServerConnection();

                osgiMBeanAvailable = (mbs != null && mbs.getMBeanInfo(name) != null);
            }
        } catch (InstanceNotFoundException ex) {
            osgiMBeanAvailable = false;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return osgiMBeanAvailable;
    }

    @Override
    public synchronized DataSourceView createView(final Application application) {
        return new EPCISPluginView(application);
    }

    static void initialize() {
        DataSourceViewsManager.sharedInstance().addViewProvider(instance, Application.class);
    }

    static void unregister() {
        DataSourceViewsManager.sharedInstance().removeViewProvider(instance);
    }
}