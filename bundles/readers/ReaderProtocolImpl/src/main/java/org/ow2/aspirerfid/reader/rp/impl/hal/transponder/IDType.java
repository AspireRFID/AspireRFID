/*
 * Copyright (c) 2008-2010, Aspire 
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
package org.ow2.aspirerfid.reader.rp.impl.hal.transponder;

import java.net.URL;
import java.util.HashMap;

import org.ow2.aspirerfid.reader.rp.impl.core.util.ResourceLocator;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;


public class IDType {

	// default configuration file
	private static String defaultConfigFile = "/props/IDTypes_default.xml";

   // the configuration
   private static XMLConfiguration config = null;
   
   // type to key (in config)
   private static HashMap<String, String> type2key;

   // the values of this model
   private String idType;
   private String description;

	private IDType(String idType, String description) {
		this.idType = idType;
		this.description = description;
	}
   
   public String getIdType() {
      return idType;
   }

   public String getDescription() {
      return description;
   }
   
   public static IDType getIdType(String type, String configFile) {

   	if (configFile == null) {
   		configFile = defaultConfigFile;
   	}
      if (config == null) {
         if (!initialize(configFile)) {
            config = null;
            return new IDType("Unknown", "Unknown");
         }
      }

      if (!type2key.containsKey(type.toLowerCase())) {
         // set type to Unknown
         type = "Unknown";
      }

      String key = type2key.get(type.toLowerCase());

      String idType = config.getString(key + "idType");
      String description = config.getString(key + "description");

      IDType t = new IDType(idType, description);
      return t;
   }

   private static boolean initialize(String configFile) {
      // read parameters from configuration file
      try {
         config = new XMLConfiguration();
         type2key = new HashMap<String, String>();
         Exception ex = new Exception();
         StackTraceElement[] sTrace = ex.getStackTrace();
         String className = sTrace[0].getClassName();
         Class c = Class.forName(className);
         URL fileurl = ResourceLocator.getURL(configFile, defaultConfigFile);
         config.load(fileurl);
         // create index to map idType to key
         int types = config.getMaxIndex("type") + 1;
         String typekey, idType;
         for (int i = 0; i < types; i++) {
            typekey = "type(" + i + ").";
            idType = config.getString(typekey + "idType");
            type2key.put(idType.toLowerCase(), typekey);
         }
      } catch (ConfigurationException ce) {
         return false;
      } catch (ClassNotFoundException cnfe) {
         return false;
      }
      return true;
   }
}
