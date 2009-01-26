/*
 *  Copyright (C) Aspire
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.ow2.aspirerfid.nfc.picking.server;

import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.util.tracker.ServiceTracker;

/**
 * This class is based on the ServerActivator of Museum. TODO Javadoc
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0 14/08/2008
 */
public class ServerActivator implements BundleActivator {
	private PickingServer server;
	private ServiceTracker eadminTracker;

	/**
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext ctx) throws Exception {
		System.out.println("Registering!!!!!");
		
		this.server = new PickingServer(new MessageProcessorCallbackImpl());
		this.server.start();
		this.eadminTracker = new ServiceTracker(ctx,EventAdmin.class.getName(),null);
		this.eadminTracker.open();
		
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext ctx) throws Exception {
		this.server.stop();
		this.eadminTracker.close();
	}
	
	private class MessageProcessorCallbackImpl implements MessageProcessorCallback {

		public void sendResult(List<String> readTags) {
			EventAdmin ea = (EventAdmin)eadminTracker.getService();
			if (ea != null) {
				Event event = new PickingEventAdapter(readTags).getEvent();
				ea.postEvent(event);
				System.out.println("sending event...");
			}
		}
	}
}
