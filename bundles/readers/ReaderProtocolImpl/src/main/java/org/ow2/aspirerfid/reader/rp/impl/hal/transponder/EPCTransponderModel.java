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

import org.ow2.aspirerfid.reader.rp.impl.hal.util.ByteBlock;
import org.ow2.aspirerfid.reader.rp.impl.core.util.ResourceLocator;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;


public class EPCTransponderModel {

	// default configuration file
	private static String defaultConfigFile = "/props/EPCTransponderModels_default.xml";

   // the configuration
   private static XMLConfiguration config = null;
   
   // TID to tag key (in config)
   private static HashMap<String, String> tid2key;

   // the values of this model
   private String tid;
   private TransponderType type;
   private String manufacturer;
   private String modelName;
   private int reservedSize;
   private boolean reservedRead;
   private boolean reservedWrite;
   private int epcSize;
   private boolean epcRead;
   private boolean epcWrite;
   private int tidSize;
   private boolean tidRead;
   private boolean tidWrite;
   private int userSize;
   private boolean userRead;
   private boolean userWrite;

	private EPCTransponderModel(TransponderType type, String manufacturer,
         String modelName, int reservedSize, boolean reservedRead,
         boolean reservedWrite, int epcSize, boolean epcRead,
         boolean epcWrite, int tidSize, boolean tidRead, boolean tidWrite,
         int userSize, boolean userRead, boolean userWrite) {
		this.type = type;
		this.manufacturer = manufacturer;
      this.modelName = modelName;
      this.reservedSize = reservedSize;
      this.reservedRead = reservedRead;
      this.reservedWrite = reservedWrite;
      this.epcSize = epcSize;
      this.epcRead = epcRead;
      this.epcWrite = epcWrite;
      this.tidSize = tidSize;
      this.tidRead = tidRead;
      this.tidWrite = tidWrite;
      this.userSize = userSize;
      this.userRead = userRead;
      this.userWrite = userWrite;
	}
   
   private void setTID(String tid) {
      this.tid = tid;
   }
   
   public String getTID() {
      return tid;
   }

   public TransponderType getType() {
      return type;
   }
   
   public String getManufacturer() {
      return manufacturer;
   }
   
   public String getModelName() {
      return modelName;
   }
   
	public int getReservedSize() {
		return reservedSize;
	}

   public boolean getReservedReadable() {
      return reservedRead;
   }

   public boolean getReservedWriteable() {
      return reservedWrite;
   }

   public int getEpcSize() {
      return epcSize;
   }

   public boolean getEpcReadable() {
      return epcRead;
   }

   public boolean getEpcWriteable() {
      return epcWrite;
   }

   public int getTidSize() {
      return tidSize;
   }

   public boolean getTidReadable() {
      return tidRead;
   }

   public boolean getTidWriteable() {
      return tidWrite;
   }

   public int getUserSize() {
      return userSize;
   }

   public boolean getUserReadable() {
      return userRead;
   }

   public boolean getUserWriteable() {
      return userWrite;
   }

   public static EPCTransponderModel getEpcTrasponderModel(byte[] tid,
         String configFile) {

      if (config == null) {
         if (!initialize(configFile)) {
            config = null;
            return new EPCTransponderModel(TransponderType.EPCclass1Gen2,
                  "Unknown", "Unknown", 8, false, false, 16, true, false,
                  4, true, false, 0, false, false);
         }
      }

      String hextid = ByteBlock.byteArrayToHexString(tid);
      if (!tid2key.containsKey(hextid)) {
         // set TID of UNKNOWN
         hextid = "E2000000";
      }

      String key = tid2key.get(hextid);

      String typename = config.getString(key + "type");
      TransponderType type = TransponderType.getType(typename);
      String manufacturer = config.getString(key + "manufacturer");
      String modelName = config.getString(key + "modelName");
      int reservedSize = config.getInt(key + "reservedSize");
      boolean reservedRead = config.getBoolean(key + "reservedRead");
      boolean reservedWrite = config.getBoolean(key + "reservedWrite");
      int epcSize = config.getInt(key + "epcSize");
      boolean epcRead = config.getBoolean(key + "epcRead");
      boolean epcWrite = config.getBoolean(key + "epcWrite");
      int tidSize = config.getInt(key + "tidSize");
      boolean tidRead = config.getBoolean(key + "tidRead");
      boolean tidWrite = config.getBoolean(key + "tidWrite");
      int userSize = config.getInt(key + "userSize");
      boolean userRead = config.getBoolean(key + "userRead");
      boolean userWrite = config.getBoolean(key + "userWrite");

      EPCTransponderModel model = new EPCTransponderModel(type, manufacturer,
            modelName, reservedSize, reservedRead, reservedWrite, epcSize,
            epcRead, epcWrite, tidSize, tidRead, tidWrite, userSize, userRead,
            userWrite);
      model.setTID(hextid);
      return model;
   }

   private static boolean initialize(String configFile) {
      // read parameters from configuration file
      try {
         config = new XMLConfiguration();
         tid2key = new HashMap<String, String>();
         Exception ex = new Exception();
         StackTraceElement[] sTrace = ex.getStackTrace();
         String className = sTrace[0].getClassName();
         URL fileurl = ResourceLocator.getURL(configFile, defaultConfigFile);
         config.load(fileurl);
         // create index to map names to class- and mask designer identifier
         HashMap<String, String> name2CiMdi = new HashMap<String, String>();
         int manufacturers = config.getMaxIndex("manufacturer") + 1;
         String mankey, manname, manci, manmdi, mancimdi;
         for (int i = 0; i < manufacturers; i++) {
            mankey = "manufacturer(" + i + ").";
            manname = config.getString(mankey + "name");
            manci = config.getString(mankey + "classIdentifier");
            manmdi = config.getString(mankey + "maskDesignerIdentifier");
            mancimdi = manci.substring(2) + manmdi.substring(2);
            name2CiMdi.put(manname, mancimdi);
         }
         // create index to map TID to tag key
         int tags = config.getMaxIndex("tag") + 1;
         String tagkey, tagmanufacturer, cimdi, tagmodelid, tagtid;
         int tagmodels;
         for (int i = 0; i < tags; i++) {
            tagkey = "tag(" + i + ").";
            tagmanufacturer = config.getString(tagkey + "manufacturer");
            cimdi = name2CiMdi.get(tagmanufacturer);
            tagmodels = config.getMaxIndex(tagkey + "modelID") + 1;
            for (int j = 0; j < tagmodels; j++) {
               tagmodelid = config.getString(tagkey + "modelID(" + j + ")").
                  substring(2);
               tagtid = cimdi + tagmodelid;
               tid2key.put(tagtid, tagkey);
            }
         }
      } catch (ConfigurationException ce) {
         return false;
      } 
      return true;
   }
}
