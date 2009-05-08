/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.impl.core.msg;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import org.ow2.aspirerfid.reader.rp.impl.core.msg.transport.HttpConnection;

/**
 * <code>OutgoingMessageDispatcher</code> sends a message to a given client
 * (called "host" in the EPC Reader Protocol). <code>MessageDispatcher</code>
 * or <code>NotificationListener</code> call the <code>sendMessage()</code>
 * method of the <code>OutgoingMessageDispatcher</code> to send a message. It
 * receives a clientID to which the message has to be sent. Using
 * <code>Clients</code> it finds an <code>OutgoingMessageBuffer</code> that
 * belongs to the client with given client id and puts the message into the
 * buffer.
 *
 *
 * @author Dijana Micijevic, ETH Zurich Switzerland, Winter 2003/04
 * @author Andreas F�rer, ETH Zurich Switzerland, Winter 2005/06
 *
 */
public class OutgoingMessageDispatcher {

   // ============================== fields =======================================
   private Clients clients_;

   /**
    * Instance of <code>OutgoingMessageDispatcher</code>.
    */
   private static OutgoingMessageDispatcher outDispatcher_ = null;

   // ============================== constructor =======================================
   private OutgoingMessageDispatcher() {
   }

   /** Gets instance of <code>OutgoingMessageDispatcher</code> */
   public static OutgoingMessageDispatcher getInstance() {
      if (outDispatcher_ == null) {
         outDispatcher_ = new OutgoingMessageDispatcher();
      }
      return outDispatcher_;
   }

   /**
    * Initializes the <code>OutgoingMessageDispatcher</code>.
    *
    * @param clients
    *            The clients
    */
   public void initialize(Clients clients) {
      clients_ = clients;
   }

   /**
    * Puts a message into a corresponding buffer. It gets a corresponding
    * buffer from <code>Clients</code> since it gets a <code>ClientID</code>
    * as a parameter (i.e. a list of client ids) Method
    * <code>sendMessage</code>
    *
    * @param clients
    *            a list of client ids to whome the message has to be sent
    * @param msg
    *            message to send
    */
   public synchronized void sendMessage(List clients, String msg) {
      for (Iterator iter = clients.iterator(); iter.hasNext();) {
         String clientID = (String) iter.next();
         sendMessage(clientID, msg);
      }
   }

   /**
    * Puts a message into a corresponding buffer. It gets a corresponding
    * buffer from <code>Clients</code> since it gets a <code>ClientID</code>
    * as a parameter.
    *
    * @param clientID
    *            the client id to whom the message has to be sent.
    * @param msg
    *            the message to send
    */
   public synchronized void sendMessage(String clientID, String msg) {
      OutgoingMessageBuffer outgoingMessageBuffer = clients_
            .getOutMsgBuffer(clientID);
      if (outgoingMessageBuffer != null) {
         outgoingMessageBuffer.put(msg);
      }
   }

   /**
    * Puts a request message into a corresponding buffer like the method
    * sendMessage above but filters out the http messages for special
    * handling (see ReadReportNotificationListener method notifyHost).
    *
    * @author hallerj
    * @param clientID
    *            the client id to whom the message has to be sent.
    * @param msg
    *            the message to send.
    */
   public synchronized void sendRequest(String clientID, String msg) {
      if (clients_.getConnection(clientID) instanceof HttpConnection) {
         sendHttpRequest(clientID, msg);
      } else {
         OutgoingMessageBuffer outgoingMessageBuffer = clients_
               .getOutMsgBuffer(clientID);
         if (outgoingMessageBuffer != null) {
            outgoingMessageBuffer.put(msg);
         }
      }
   }

   /**
    * Sends a HTTP request. Provisional solution without message buffers.
    * Current thread has to send request and wait for response.
    *
    * @author hallerj
    * @param clientID
    *            the client id to whom the message has to be sent.
    * @param msg
    *            the message to send.
    */
   private void sendHttpRequest(String clientID, String msg) {
      try {
         Socket s = ((HttpConnection) clients_.getConnection(clientID)).getSocket();
         URL url = new URL ("http", s.getInetAddress().getHostAddress(), s.getPort(), "");
         URLConnection urlConn = url.openConnection();

         urlConn.setDoInput (true);
         urlConn.setDoOutput (true);
         urlConn.setUseCaches (false);
         urlConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");

         // Send POST output.
         DataOutputStream printout = new DataOutputStream (urlConn.getOutputStream ());
         printout.writeBytes (msg);
         printout.flush ();
         printout.close ();

         // Get response data.
         BufferedReader input = new BufferedReader(new InputStreamReader(
               urlConn.getInputStream ()));
         String str;
         while (null != (str = input.readLine())) {
            // do nothing, only read whole input.
            // System.out.println(str);
         }
          input.close ();
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}