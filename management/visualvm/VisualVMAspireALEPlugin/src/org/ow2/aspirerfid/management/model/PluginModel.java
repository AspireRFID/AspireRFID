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
package org.ow2.aspirerfid.management.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import org.openide.util.Exceptions;

/**
 * Model that abstracts the view from all the JMX code for retrieving data
 *
 * @author Kiev
 */
public class PluginModel {

    private MBeanServerConnection mbs;
    private ObjectName aleServer;
    private ObjectName sensor;
    private ObjectName logicalReader;
    private ObjectName ecspec;
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport((this));
    private String[] sensorList;
    private ArrayList ecSpecs;
    private String[] lrSpecs;
    private long uptime;
    private Map properties;
    private Map<String, List<SensorCacheData>> sensorsCache = new HashMap<String, List<SensorCacheData>>();
    private boolean sensorDataAvailable = true;
    public static final String SENSOR_PROPERTY = "sensorList";
    public static final String SUBSCRIBERS_PROPERTY = "subscribers";
    public static final String LRSPECS_PROPERTY = "lrSpecs";
    public static final String ECSPECS_PROPERTY = "ecSpecs";
    public static final String UPTIME_PROPERTY = "uptime";
    public static final String PROPERTIES_PROPERTY = "properties";
    private PollingThread pollingThread = new PollingThread();
    private static final int MAX_CACHE = 10;
    
    public PluginModel(MBeanServerConnection mbs) {
        this.mbs = mbs;
        this.init();
        this.pollingThread.start();
    }

    private void init() {
        try {
            aleServer = new ObjectName("org.ow2.aspirerfid:type=aleserver,name=server");
            sensor = new ObjectName("org.ow2.aspirerfid:type=aleserver,name=sensor");
            logicalReader = new ObjectName("org.ow2.aspirerfid:type=aleserver,name=lrSpec");
            ecspec = new ObjectName("org.ow2.aspirerfid:type=aleserver,name=ecspec");

            setProperties((Map) mbs.getAttribute(aleServer, "Properties"));
            setUptime((Long) mbs.getAttribute(aleServer, "Uptime"));
            setLrSpecs((String[]) mbs.getAttribute(logicalReader, "LogicalReaderNames"));
            setEcSpecs((String[]) mbs.getAttribute(ecspec, "ECSpecNames"));
            try {
                setSensorList((String[]) mbs.getAttribute(sensor, "SensorNames"));
                pollSensorData();
            } catch (Throwable t) {
                sensorDataAvailable = false;
            }
        } catch (MBeanException ex) {
            Exceptions.printStackTrace(ex);
        } catch (AttributeNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (InstanceNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ReflectionException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (MalformedObjectNameException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public boolean isSensorDataAvailable() {
        return sensorDataAvailable;
    }

    //TODO change boolean return values for thrown exceptions
    public boolean createECSpec(String name, String filePath) {
        boolean result = false;
        //read file
        try {
            BufferedReader file = new BufferedReader(new FileReader(filePath));
            String s = null;
            StringBuffer sb = new StringBuffer();
            while (null != (s = file.readLine())) {
                sb.append(s);
            }
            mbs.invoke(ecspec, "createECSpec", new Object[]{name, sb.toString()}, new String[]{"java.lang.String", "java.lang.String"});
            result = true;
            setEcSpecs((String[]) mbs.getAttribute(ecspec, "ECSpecNames"));
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return result;
    }

    public boolean addLogicalReader(String name, String filePath) {
        boolean result = false;
        //read file
        try {
            BufferedReader file = new BufferedReader(new FileReader(filePath));
            String s = null;
            StringBuffer sb = new StringBuffer();
            while (null != (s = file.readLine())) {
                sb.append(s);
            }
            mbs.invoke(logicalReader, "addLogicalReader", new Object[]{name, sb.toString()}, new String[]{"java.lang.String", "java.lang.String"});
            result = true;
            setLrSpecs((String[]) mbs.getAttribute(logicalReader, "LogicalReaderNames"));
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return result;
    }

    public String[] getSubscribers(String ecSpecName) {
        String[] result = null;
        try {
            result = (String[]) mbs.invoke(ecspec, "getSubscribers", new Object[]{ecSpecName}, new String[]{"java.lang.String"});
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return result;
    }

    public boolean deleteECSpec(String ecSpecName) {
        boolean result = false;
        try {
            mbs.invoke(ecspec, "removeECSpec", new Object[]{ecSpecName}, new String[]{"java.lang.String"});
            result = true;
            setEcSpecs((String[]) mbs.getAttribute(ecspec, "ECSpecNames"));
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return result;
    }

    public boolean deleteLogicalReader(String ecSpecName) {
        boolean result = false;
        try {
            mbs.invoke(logicalReader, "removeLogicalReader", new Object[]{ecSpecName}, new String[]{"java.lang.String"});
            result = true;
            setLrSpecs((String[]) mbs.getAttribute(logicalReader, "LogicalReaderNames"));
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return result;
    }

    public boolean subscribeECSpec(String ecSpecName, String url) {
        boolean result = false;
        try {
            mbs.invoke(ecspec, "subscribe", new Object[]{ecSpecName, url}, new String[]{"java.lang.String", "java.lang.String"});
            result = true;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return result;
    }

    public boolean unsubscribeECSpec(String ecSpecName, String url) {
        boolean result = false;
        try {
            mbs.invoke(ecspec, "unsubscribe", new Object[]{ecSpecName, url}, new String[]{"java.lang.String", "java.lang.String"});
            result = true;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        return result;
    }

    public void setUptime(long uptime) {
        Object oldValue = this.uptime;
        this.uptime = uptime;
        changeSupport.firePropertyChange(UPTIME_PROPERTY, oldValue, uptime);
    }

    public long getUptime() {
        return uptime;
    }

    public Map<String, String> getLRSpecProperties(String lrSpec) {
        Map<String, String> result = Collections.EMPTY_MAP;
        try {
            result = (Map<String, String>) mbs.invoke(logicalReader, "getProperties", new Object[]{lrSpec}, new String[]{"java.lang.String"});
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return result;
    }

    public void setProperties(Map properties) {
        Object oldValue = this.properties;
        this.properties = properties;
        changeSupport.firePropertyChange(ECSPECS_PROPERTY, oldValue, properties);
    }

    public Map getProperties() {
        return properties;
    }

    public void setEcSpecs(String[] ecSpecs) {
        Object oldValue = this.ecSpecs;
        this.ecSpecs = new ArrayList(Arrays.asList(ecSpecs));
        changeSupport.firePropertyChange(ECSPECS_PROPERTY, oldValue, ecSpecs);
    }

    public String[] getEcSpecs() {
        return (String[]) ecSpecs.toArray(new String[ecSpecs.size()]);
    }

    public void setLrSpecs(String[] lrSpecs) {
        Object oldValue = this.lrSpecs;
        this.lrSpecs = lrSpecs;
        changeSupport.firePropertyChange(LRSPECS_PROPERTY, oldValue, lrSpecs);
    }

    public String[] getLrSpecs() {
        return lrSpecs;
    }

    public String[] getLrSpecs(boolean forceRefresh) {
        if (forceRefresh) {
            try {
                setLrSpecs((String[]) mbs.getAttribute(logicalReader, "LogicalReaderNames"));
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return lrSpecs;
    }

    public void setSensorList(String[] list) {
        Object oldValue = sensorList;
        sensorList = list;
        changeSupport.firePropertyChange(SENSOR_PROPERTY, oldValue, list);
    }

    public String[] getSensorList() {
        return sensorList;
    }

    public List<SensorCacheData> getSensorData(String sensorName) {
        return sensorsCache.get(sensorName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    private void pollUptime() {
        try {
            setUptime((Long) mbs.getAttribute(aleServer, "Uptime"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void pollSensorData() {
        try {
            Map<String, Map<String, String>> sensorData = (Map<String, Map<String, String>>) mbs.getAttribute(sensor, "SensorValues");
            ArrayList list = new ArrayList(new TreeSet(sensorData.keySet()));
            //TODO check removed sensors!
            for (int i = 0; i < list.size(); i++) {
                String sensorName = list.get(i).toString();
                List<SensorCacheData> cache = sensorsCache.get(sensorName);
                SensorCacheData currentData = SensorCacheData.fromMap(sensorData.get(sensorName));

                if (cache == null) {
                    cache = new ArrayList<SensorCacheData>();
                    cache.add(currentData);
                    sensorsCache.put(sensorName, cache);
                } else {
                    //compare polled value diffs from last value
                    if (cache.size() > 0 && currentData.getTimestamp() > cache.get(cache.size() - 1).getTimestamp()) {
                       cache.add(currentData);
                    }
                    if (cache.size() > MAX_CACHE) {
                        cache.remove(0);
                    }
                }
            }
            setSensorList((String[]) list.toArray(new String[list.size()]));
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    class PollingThread extends Thread {

        public PollingThread() {
            super("PollingThread");
        }

        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);
                    if (isSensorDataAvailable()) {
                        pollSensorData();
                    }
                    pollUptime();
                }
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
