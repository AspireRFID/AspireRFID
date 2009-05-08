/*
 * Copyright (c) 2008-2010, Aspire 
 * 
 * This file contains the source code of the Accada library by ETH Zurich (www.accada.org), 
 * licensed under the terms of the GNU Lesser General Public License version 2.1 in 2007 and 
 * modified for the needs of the Aspire project.
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

package org.ow2.aspirerfid.reader.rp.hal.impl.sim.util;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.apache.log4j.Logger;
import org.osgi.framework.Bundle;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.util.Activator;
/**
 * This class locates resources (configuration files, images) for the accada
 * reader core. It searches for user resources in the user directory (current
 * application directory) and for default resources in the library if no user
 * resources found.
 * 
 * author hallerj, Nektarios Leontiadis (nele@ait.edu.gr)
 *
 */
public final class ResourceLocator {

   /**
    * The logger.
    */
   private static Logger log = Logger.getLogger(ResourceLocator.class);

   /**
    * Suffix for default resource file name if no name given.
    */
   private static final String DEFAULT_SUFFIX = "_default";
   
   public static URL getURL(String resourceFileName, String defaultResourceFileName, Object obj) {
	   return getURL(resourceFileName, defaultResourceFileName);
   }
   /**
    * Get the URL of the resourceFileName.
    * 
    * @param resourceFileName
    *          path and name of the resource file (e.g. '/path/file.ext')
    * @param defaultResourceFileName
    *          path and name of the default resource file (e.g. '/path/file_default.ext')
    * @param caller
    *          the caller class
    * @return URL of the file or null if not found
    */
   public static URL getURL(String resourceFileName, String defaultResourceFileName) {
	   Bundle bundle = Activator.bundle;
      // check arguments
   	if ((resourceFileName == null) && (defaultResourceFileName != null)) {
   		resourceFileName = defaultResourceFileName;
   	}
      if (!resourceFileName.startsWith("/")) {
         resourceFileName = "/" + resourceFileName;
      }
      if (defaultResourceFileName == null) {
         defaultResourceFileName = resourceFileName.substring(0,
            resourceFileName.lastIndexOf(".")) + DEFAULT_SUFFIX
            + resourceFileName.substring(resourceFileName.lastIndexOf("."));
      }

      URL url = null;
//      log.info(url);
      log.debug("Bundle location: "+bundle.getLocation());
      url = bundle.getEntry(resourceFileName);
      if(url ==null)
    	  url = bundle.getEntry(defaultResourceFileName);

      if (url != null) {
         log.debug("Resource URL is: " + url.toString());
      } else {
         log.debug("Resource '" + resourceFileName + "' and '"
            + defaultResourceFileName + "' not found.");
      }

      return url;
   }
   
   public static Reader getReader(String resourceFileName, String defaultResourceFileName) {
	   InputStreamReader reader=null;
      try {
    	  URL url = getURL(resourceFileName, defaultResourceFileName);
    	  reader = new InputStreamReader(url.openStream());
      }catch(Exception e)
      {
    	  e.printStackTrace();
      }
	   return reader;
   }

}
