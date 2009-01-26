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
package org.ow2.aspirerfid.reader.util.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * configuration utility
 * 
 * @author Didier Donsez
 * @created 3 avril 2003
 */
public class Configuration {
	static String DEFAULT_PROPERTIES_FILE = "config.properties";

	/**
	 * loads properties from a file
	 * 
	 * @param filename
	 *            the properties file name
	 * @return the properties
	 */
	public static Properties loadProperties(String filename) {
		if (filename == null) {
			filename = System.getProperty(
					"org.ow2.aspirerfid.bundle.util.configuration.file",
					DEFAULT_PROPERTIES_FILE);
		}
		try {
			return loadProperties(new FileInputStream(filename));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * loads properties from a input stream
	 * 
	 * @param in
	 *            the input stream
	 * @return the properties
	 */
	public static Properties loadProperties(InputStream in) {
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return prop;
	}

	/**
	 * builds a map from properties. the key list is in the property
	 * keyListPropertyName <br/>Example getMap(props,"keylist") returns a
	 * hashtable with 3 key-value entry
	 * 
	 * <pre>
	 * keylist=bar;bubba;foo
	 * bar=BAR
	 * foo=1.5;float
	 * bubba=100;int
	 * </pre>
	 * 
	 * @param props
	 *            the properties
	 * @param keyListPropertyName
	 *            the property name of the key list (separated by ;)
	 * @return The map
	 */
	public static Map getMap(Properties props, String keyListPropertyName) {
		return getHashtable(props, keyListPropertyName);
	}

	/**
	 * builds a hashtable from properties. the key list is in the property
	 * keyListPropertyName <br">Example getHashtable(props,"keylist") returns a
	 * hashtable with 3 key-value entry
	 * 
	 * <pre>
	 * keylist=bar;bubba;foo
	 * bar=BAR
	 * foo=1.5;float
	 * bubba=100;int
	 * </pre>
	 * 
	 * @param props
	 *            the properties
	 * @param keyListPropertyName
	 *            the property name of the key list (separated by ;)
	 * @return The hashtable
	 */
	public static Hashtable getHashtable(Properties props,
			String keyListPropertyName) {

		StringTokenizer st = new StringTokenizer(props
				.getProperty(keyListPropertyName), ";");
		Hashtable map = new Hashtable();

		while (st.hasMoreTokens()) {
			String key = st.nextToken();
			StringTokenizer stvaluetype = new StringTokenizer(props
					.getProperty(key), ";");
			Object obj = null;
			if (stvaluetype.hasMoreTokens()) {
				String value = stvaluetype.nextToken();
				if (stvaluetype.hasMoreTokens()) {
					String type = stvaluetype.nextToken();
					if (type.equals("int")) {
						obj = new Integer(value);
					} else if (type.equals("long")) {
						obj = new Long(value);
					} else if (type.equals("float")) {
						obj = new Float(value);
					} else if (type.equals("double")) {
						obj = new Double(value);
					} else if (type.equals("string")) {
						obj = value;
					} else {
						// default : string
						obj = value;
					}
				} else {
					// no type means String
					obj = value;
				}

			}
			map.put(key, obj);
		}
		return map;
	}

	/**
	 * builds a (ordered) vector from properties. the index list is in the
	 * property keyListPropertyName <br/>Example getVector(props,"tab") returns
	 * a 4 elements vector (tab.5 is ignored since tab.4 does not exist)
	 * 
	 * <pre>
	 * tab.0=bar
	 * tab.1=foo
	 * tab.2=1.5;float
	 * tab.3=100;int
	 * tab.5=10000;long
	 * </pre>
	 * 
	 * @param props
	 *            the properties
	 * @param vectorPropertyName
	 *            the property name of the key list (separated by ;)
	 * @return The vector
	 */
	public static Vector getVector(Properties props, String vectorPropertyName) {

		Vector vect = new Vector();

		for (int i = 0; true; i++) {
			String valuetype = props.getProperty(vectorPropertyName + "." + i);
			if (valuetype == null)
				break;
			StringTokenizer stvaluetype = new StringTokenizer(valuetype, ";");
			Object obj = null;
			if (stvaluetype.hasMoreTokens()) {
				String value = stvaluetype.nextToken();
				if (stvaluetype.hasMoreTokens()) {
					String type = stvaluetype.nextToken();
					if (type.equals("int")) {
						obj = new Integer(value);
					} else if (type.equals("long")) {
						obj = new Long(value);
					} else if (type.equals("float")) {
						obj = new Float(value);
					} else if (type.equals("double")) {
						obj = new Double(value);
					} else if (type.equals("string")) {
						obj = value;
					} else {
						// default : string
						obj = value;
					}
				} else {
					// no type means String
					obj = value;
				}

			}
			vect.addElement(obj);
		}
		return vect;
	}
}
