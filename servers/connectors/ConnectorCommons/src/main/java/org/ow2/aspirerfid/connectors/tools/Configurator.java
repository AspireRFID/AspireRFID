/**
 * Copyright (c) 2008-2010, Aspire 
 * 
 * Aspire is free software; you can redistribute it and/or 
 * modify it under  the terms of the GNU Lesser General Public 
 * License version 2.1 as published by the Free Software Foundation (the 
 * "LGPL"). 
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library in the file COPYING-LGPL-2.1; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA. 
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY 
 * OF ANY KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations. 
 * 
 */

package org.ow2.aspirerfid.connectors.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * This class handles property loading operations
 * @author Nektarios Leontiadis (nele@ait.edu.gr)
 * 
 */
public class Configurator {

    private static Properties property;
    private static Configurator conf;
    private static Logger logger;

    static {
	logger = Logger.getLogger(Configurator.class.getName());
	property = null;
    }

    private Configurator() {
	conf = this;
    }

    /**
     * Load a property file into this object. If properties already exist, the new ones
     * will be appended. In this case, if there is a conflicting property key,
     * the new property value will be kept.
     * @param propertyFile The full or relative path to the property file
     * @throws IOException
     */
    public static void loadProperties(String propertyFile, Class clas) throws IOException {
	Properties property = new Properties();
	if(conf == null)
	    new Configurator();
	logger.info("Loading property file: "+propertyFile);
	try{
	    FileInputStream file = new FileInputStream(propertyFile);
		property.load(file);   
	}
	catch(FileNotFoundException e)
	{
	    URL fileUrl = clas.getClassLoader().getResource(propertyFile);
	    property.load(fileUrl.openStream());
	}

	if(Configurator.property == null)
	{
	    Configurator.property = property;
	}
	else
	    Configurator.property.putAll(property);
    }
    
    /**
     * Return the property with the specified key. If there is no such key or no property file has been loaded
     * null will be returned.
     * @param key The key whose value should be returned
     * @return The specified key's value or null
     */
    public static String getProperty(String key) {
	if(property != null)
	    return property.getProperty(key);
	else 
	    return null;
    }

    /**
     * Return the property with the specified key. If there is no such key or no property file has been loaded
     * the default value will be returned
     * @param key The key whose value should be returned
     * @param defaultValue 
     * @return The specified key's value or the default value
     */
    public static String getProperty(String key, String defaultValue) {
	String p;
	if(property == null || (p=property.getProperty(key)) == null)
	    return defaultValue;
	else 
	    return p;
    }
}
