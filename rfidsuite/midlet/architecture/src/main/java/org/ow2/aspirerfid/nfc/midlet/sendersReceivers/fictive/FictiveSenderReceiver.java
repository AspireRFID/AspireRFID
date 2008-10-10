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
package org.ow2.aspirerfid.nfc.midlet.sendersReceivers.fictive;

import org.ow2.aspirerfid.nfc.midlet.generic.ResponseMessage;
import org.ow2.aspirerfid.nfc.midlet.generic.SenderReceiver;
import org.ow2.aspirerfid.nfc.midlet.message.receive.SimpleResponseMessage;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.BusinessToCommInterface;

/**
 * Implements a fictive sender receiver reader that receives a simple message
 * that only has the UID and the received message has too just the UID. The
 * implementation of run method (in the inner class that inherits from Thread)
 * has a sleep that simulates the latency of receiving a message.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class FictiveSenderReceiver extends SenderReceiver {

	/**
	 * Thread that waits for the message and put it in the Midlet when it
	 * receives it. It's just a simulation, because there is no communication
	 * outside the cell phone.
	 * 
	 * @author Andres Gomez
	 */
	private class Receiver extends Thread {
		/**
		 * Default constructor.
		 */
		protected Receiver() {
			// Default constructor. This was done to improve the performance.
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			ResponseMessage message = new SimpleResponseMessage();
			String uid = (String) FictiveSenderReceiver.this.m_taglist
					.elementAt(0);
			FictiveSenderReceiver.this.m_taglist.removeElementAt(0);
			message.setTagUID(uid);
			// TODO
			// FictiveSenderReceiver.this.getCallerClass().putMessageReceived(
			// message);
		}
	}

	/**
	 * Default constructor.
	 * 
	 * @param caller
	 *            Caller class.
	 */
	public FictiveSenderReceiver(BusinessToCommInterface caller) {
		super(caller);
	}

	public String receiveMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.SenderReceiver#sendMessage(java.lang.String)
	 */
	public void sendMessage(String message) {
		this.m_taglist.addElement(message);
		new Receiver().start();
	}

	public void startSender() {
		// TODO Auto-generated method stub

	}

	public void stopSender() {
		// TODO Auto-generated method stub

	}
}
