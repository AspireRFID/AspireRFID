/*
   Copyright 2005-2010, OW2 Aspire RFID project 
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.

   Contact: OW2 Aspire RFID project <X AT Y DOT org> (with X=aspirerfid and Y=ow2)

   LGPL version 2.1 full text http://www.gnu.org/licenses/lgpl-2.1.txt    
 */
package org.ow2.aspirerfid.ale.server.readers.rp.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.accada.ale.wsdl.ale.epcglobal.ImplementationException;
import org.accada.ale.wsdl.ale.epcglobal.ImplementationExceptionResponse;
import org.accada.ale.xsd.ale.epcglobal.LRSpec;
import org.accada.hal.HardwareException;
import org.accada.hal.Observation;
import org.apache.log4j.Logger;
import org.ow2.aspirerfid.ale.server.Tag;
import org.ow2.aspirerfid.ale.server.readers.BaseReader;
import org.ow2.aspirerfid.ale.server.readers.IdentifyThread;

/**
 * 
 * @author Gabriel Pedraza Ferreira
 * 
 */
public abstract class AbstractAdaptor extends BaseReader {

   /** default readTimeInterval. */
   public static final int DEFAULT_READTIME_INTERVAL = 2000;

   /** logger. */
   private static final Logger LOG = Logger.getLogger(AbstractAdaptor.class);

   /** to get all the tags we need a polling thread. */
   private IdentifyThread identifyThread = null;

   /** the interval in which shall be read from the reader. */
   private int readTimeInterval = -1;

   /** the reader */
   private AbstractReader reader;

   public AbstractAdaptor() {
      super();
      LOG.info("Simple adaptor instantiated!!!");
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.ow2.aspirerfid.ale.server.readers.BaseReader#initialize(java.lang.String,
    * org.accada.ale.xsd.ale.epcglobal.LRSpec)
    */
   @Override
   public void initialize(String name, LRSpec spec) throws ImplementationExceptionResponse {
      super.initialize(name, spec);
      try {
         super.initialize(name, spec);
      } catch (ImplementationExceptionResponse ie) {
         LOG.error("error in initialize of superclass");
         throw ie;
      }

      try {
         // extract from LRSpec how to connect to the reader
         extractConnectionSettings();

         reader = getReader();
      } catch (ImplementationExceptionResponse ie) {
         ie.printStackTrace();
         LOG.error("could not extract connection settings from LRSpec");
         throw new ImplementationExceptionResponse();
      }

      // connect to the reader
      connectReader();
   }

   /**
    * Gets a reference for the driver of actual reader
    * 
    * @return reader reference
    */
   protected abstract AbstractReader getReader();

   /*
    * (non-Javadoc)
    * 
    * @see org.ow2.aspirerfid.ale.server.readers.BaseReader#addTag(org.ow2.aspirerfid .ale.server.Tag)
    */
   @Override
   public void addTag(Tag tag) {
      setChanged();
      tag.addTrace(getName());
      notifyObservers(tag);
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.ow2.aspirerfid.ale.server.readers.BaseReader#addTags(java.util.List)
    */
   @Override
   public void addTags(List<Tag> tags) {
      setChanged();
      for (Tag tag : tags) {
         tag.addTrace(getName());
      }
      notifyObservers(tags);
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.ow2.aspirerfid.ale.server.readers.BaseReader#connectReader()
    */
   @Override
   public void connectReader() throws ImplementationExceptionResponse {
      if (isConnected()) {
         return;
      }

      try {
         startReader();
      } catch (Exception e) {
         setDisconnected();
         throw new ImplementationExceptionResponse(e.getMessage());
      }

      LOG.debug("setup identifyThread on RPAdaptor " + getName());
      // setup the polling thread
      identifyThread = new IdentifyThread(this);
      identifyThread.setPollingFrequency(readTimeInterval);
      identifyThread.start();

      // suspend the polling thread to the beginning
      identifyThread.suspendIdentify();

      setConnected();

   }

   /*
    * (non-Javadoc)
    * 
    * @see org.ow2.aspirerfid.ale.server.readers.BaseReader#disconnectReader()
    */
   @Override
   public void disconnectReader() throws ImplementationExceptionResponse {
      System.out.println("////////////// **************** The reader has been desactived //////////////// **********");
      stopReader();
      setReadTimeInterval(-1);

      if (identifyThread != null) {
         identifyThread.stopIdentify();
         identifyThread = null;
      }
      setDisconnected();
      setStopped();

   }

   /*
    * (non-Javadoc)
    * 
    * @see org.ow2.aspirerfid.ale.server.readers.BaseReader#identify(java.lang.String [])
    */
   @Override
   public Observation[] identify(String[] readPointNames) throws HardwareException {
      LOG.debug("identify called an RPAdaptor " + getName());

      if ((countObservers() > 0)) {
         reader.poll();
         LOG.debug("Polling the reader");
      } else {
         LOG.debug("rp-proxy not ready (yet)");
      }

      return null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.ow2.aspirerfid.ale.server.readers.BaseReader#start()
    */
   @Override
   public void start() {
      if (!isConnected()) {
         try {
            connectReader();
         } catch (ImplementationExceptionResponse e) {
            e.printStackTrace();
            setDisconnected();
         }
      }

      // reader is not connected so it is not started as well
      if (!isConnected()) {
         setStopped();
      } else {
         LOG.debug("identifyThread starting to identify");
         identifyThread.resumeIdentify();
         setStarted();
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.ow2.aspirerfid.ale.server.readers.BaseReader#stop()
    */
   @Override
   public void stop() {
      stopReader();
      LOG.debug("identifyThread suspend to identify");
      identifyThread.suspendIdentify();
      setStopped();
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.ow2.aspirerfid.ale.server.readers.BaseReader#update(org.accada.ale .xsd.ale.epcglobal.LRSpec)
    */
   @Override
   public void update(LRSpec spec) throws ImplementationExceptionResponse {
      stop();
      setLRSpec(spec);
      if (extractConnectionSettings()) {
         LOG.debug("restarting reader " + getName());
         disconnectReader();
         // set the connection settings again and then start the reader
         extractConnectionSettings();
         connectReader();
      }
      start();
   }

   private void startReader() {
      if (reader != null)
         reader.startReader();

   }

   private void stopReader() {
      if (reader != null)
         reader.stopReader();
   }

   public int getReadTimeInterval() {
      return readTimeInterval;
   }

   public void setReadTimeInterval(int readTimeInterval) {
      this.readTimeInterval = readTimeInterval;
   }

   /**
    * this method extracts the connection settings from the LRProperty. when there is the need to disconnect the reader
    * (when something in the connection to the reader has been changed in the LRSpec), true will be returned false
    * otherwise. if necessary the reader will be restarted
    * 
    * @throws ImplementationException whenever an error occurs
    * @return returns true if the connection to the reader needs to be reconnected
    */
   private boolean extractConnectionSettings() throws ImplementationExceptionResponse {

      // URL connectionPoint = toURL(logicalReaderProperties.get("ConnectionPoint"), "ConnectionPoint");
      // URL notificationPoint = toURL(logicalReaderProperties.get("NotificationPoint"), "NotificationPoint");
      String interval = logicalReaderProperties.get("ReadTimeInterval");

      setReadTimeInterval(-1);

      try {
         setReadTimeInterval(Integer.parseInt(interval));
      } catch (Exception ne) {
         LOG.error("could not extract readTimeIntervall from LRPropery");
         throw new ImplementationExceptionResponse("Could not extract ReadTimeInterval from LRPropery");
      }

      // assert that the readTimeInterval is not -1
      if (readTimeInterval == -1) {
         LOG.error("ReadTimeInterval not set - assuming 2000ms");
         setReadTimeInterval(DEFAULT_READTIME_INTERVAL);
      }

      return false;
   }

   /**
    * this method calls the URL constructor with a string.
    * 
    * @param urlString the URL to be converted into a java.net.URL
    * @param comment "NotificationPoint" or " ConnectionPoint"
    * @return a URL created from urlString
    * @throws ImplementationException when a MalformedURLException is thrown
    */
   private URL toURL(String urlString, String comment) throws ImplementationExceptionResponse {
      URL url = null;
      try {
         url = new URL(urlString);
      } catch (MalformedURLException e) {
         e.printStackTrace();
         throw new ImplementationExceptionResponse("Could not extract " + comment + " from LRProperty");
      }
      return url;
   }

}
