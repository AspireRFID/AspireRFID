/*
 * Copyright 2005-2008, Aspire
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the
 * License, or (at your option) any later version. If you do not alter this
 * notice, a recipient may use your version of this file under either the
 * LGPL version 2.1, or (at his option) any later version.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public
 * License for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.sensor.temperproducer;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.util.measurement.Measurement;
import org.osgi.util.measurement.Unit;
import org.ow2.aspirerfid.sensor.producer.MeasurementProducer;

/**
 * The class provides a temperature producer for a TEMPer USB thermometer (http://www.dealextreme.com/details.dx/sku.7003)
 * TODO ManagedService (using iPOJO) to configure port name and poll delay
 * TODO Add a MBean (using iPOJO) to configure port name and poll delay
 * TODO Migrate to iPOJO
 * TODO should unregister Producer service when temper is null
 */
public class TEMPerProducer extends MeasurementProducer implements ManagedService {

    private static final int POLLDELAY = 2000; // millisec

    private String portName = "/dev/ttyUSB0"; // "COM14";

    private TEMPerDevice temper;

    protected Measurement getMeasurement() {
        if(temper==null) return null;
        double temperature=temper.getTemperature();
        if(temperature==Double.NaN) {
            return null;
        } else {
            // Remark: TEMPer accuracy is ± 0.5°C @ 25°C according to http://www.dealextreme.com/details.dx/sku.7003
            // and the temperature testing is between -40°C and +120°C
            return new Measurement(temperature,0.5,Unit.K,System.currentTimeMillis());
        }
    }

    protected Hashtable getRegistrationProperties() {
        Hashtable registrationProperties = super.getRegistrationProperties();
        registrationProperties.put( Unit.class.getName(), "K");
        registrationProperties.put( "application", "temperature"); // could be outdoor temperature, indoor temperature, ...
        registrationProperties.put(
                org.osgi.framework.Constants.SERVICE_DESCRIPTION,
                "a Producer that poll Measurement objects containing temperatures measuring on a TEMPer USB thermometer");
        return registrationProperties;
    }

    ServiceRegistration managedServiceRegistration;

    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext bundleContext) throws Exception {
//      SerialDevice.printSerialPortNames(System.out);
        this.pollDelay=POLLDELAY;
        if(portName!=null) {
            temper=new TEMPerDevice(portName);
        }
//      Hashtable registrationProperties = new Hashtable();
//      registrationProperties.put(
//              org.osgi.framework.Constants.SERVICE_PID,
//              this.getClass().getPackage().getName());
//      managedServiceRegistration=bundleContext.registerService(ManagedService.class.getName(), this, properties)
        super.start(bundleContext);
    }

    /**
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bundleContext) throws Exception {
        super.stop(bundleContext);
    }

    /**
     * TODO replace it by the iPOJO CM handler
     */
    public void updated(Dictionary properties) throws ConfigurationException {
        String _portName=(String) properties.get("serialdevice.portname");
        if(_portName!=null && !_portName.equals(portName)) {
            portName=_portName;
            if(portName!=null){
                temper=new TEMPerDevice(portName);
            }
        }
        // TODO pollDelay
    }
}
