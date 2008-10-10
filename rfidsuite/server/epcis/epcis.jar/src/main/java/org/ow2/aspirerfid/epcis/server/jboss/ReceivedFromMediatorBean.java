/*
 * Copyright 2005-2008, Aspire This library is free software; you can
 * redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation (the "LGPL");
 * either version 2.1 of the License, or (at your option) any later version. If
 * you do not alter this notice, a recipient may use your version of this file
 * under either the LGPL version 2.1, or (at his option) any later version. You
 * should have received a copy of the GNU Lesser General Public License along
 * with this library; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA. This software is
 * distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express
 * or implied. See the GNU Lesser General Public License for the specific
 * language governing rights and limitations.
 */
package org.ow2.aspirerfid.epcis.server.jboss;

import java.util.Hashtable;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import org.jboss.annotation.ejb.ResourceAdapter;

import org.ow2.aspirerfid.epcis.server.ReportsAggregatorRemote;

/**
 * An enterprise bean that allows J2EE applications to process messages
 * asynchronously
 * 
 * @author François Fornaciari
 * @version 0.1.0
 * @version 2007
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "messagingType", propertyValue = "javax.jms.MessageListener"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "rfidservertopic")
})
@ResourceAdapter("joram.rar")
public class ReceivedFromMediatorBean implements MessageListener {
    
    @EJB
    ReportsAggregatorRemote reportsAggregator = null;
    
    /**
     * To receive messages asynchronously
     */
    public void onMessage(Message msg) {
        try {
            if (msg == null) {
                System.out.println("Message: message null ");
            } else {
                if (msg instanceof ObjectMessage) {
                    // receive a hashtable from the sender remotely
                    Hashtable<String, String> report = (Hashtable<String, String>) ((ObjectMessage) msg)
                            .getObject();
                    if (report == null) {
                        System.out.println("Hashtable is empty ");
                    }
                    reportsAggregator.onReport(report);
                    
                } else if (msg instanceof TextMessage) {
                    System.out.println("Message: message is text ");
                }
            }
        } catch (Exception exc) {
            System.out.println("Exception caught :" + exc);
            exc.printStackTrace();
        }
    }
    
}
