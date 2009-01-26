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
package org.ow2.aspirerfid.nfc.midlet.reader.rfid;

import javax.microedition.contactless.ContactlessException;
import javax.microedition.contactless.DiscoveryManager;
import javax.microedition.contactless.TargetListener;
import javax.microedition.contactless.TargetProperties;
import javax.microedition.contactless.TargetType;

import org.ow2.aspirerfid.nfc.midlet.generic.NFCMidletException;
import org.ow2.aspirerfid.nfc.midlet.generic.ReaderThread;
import org.ow2.aspirerfid.nfc.midlet.reader.Reader;
import org.ow2.aspirerfid.nfc.midlet.reader.TagDetector;

/**
 * This is the RFID Tag detector that implements the interface that receive the
 * message when a tag is read.<br/> There are several types of cards and this
 * class adds listener for each selected type.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public final class RFIDDetector extends Reader implements TargetListener {

	/**
	 * Midlet that call the detector.
	 */
	private TagDetector m_controllerMidlet = null;

	/**
	 * Initialize the necessary objects to detect the tags.
	 * 
	 * @param controllerMidlet
	 *            Caller midlet that starts the rfid detector.
	 * 
	 * @throws NFCMidletException
	 *             If there is a problem initializing the detector.
	 */
	public RFIDDetector(TagDetector controllerMidlet) throws NFCMidletException {
		this.m_controllerMidlet = controllerMidlet;
		this.addListeners();
	}

	/**
	 * Registers the card types in the Discovery Manager to receive
	 * notifications when a tag is detected.
	 * 
	 * @throws NFCMidletException
	 *             When there is a problem adding a listener: Already defined or
	 *             not supported.
	 */
	public void addListeners() throws NFCMidletException {
		// Add the listener to all the types of supported tags.
		try {
			DiscoveryManager.getInstance().addTargetListener(this,
					TargetType.ISO14443_CARD);
			DiscoveryManager.getInstance().addTargetListener(this,
					TargetType.NDEF_TAG);
			DiscoveryManager.getInstance().addTargetListener(this,
					TargetType.RFID_TAG);

		} catch (IllegalStateException e) {
			throw new RFIDReaderException(
					"The listener or the tag type has been already registred.",
					e);
		} catch (ContactlessException e) {
			throw new RFIDReaderException("The current tag is not supported:",
					e);
		}
	}

	/**
	 * Removes the listeners from the Discovery Manager.
	 */
	public void removeListeners() {
		// Unregistries all the established listeners.
		DiscoveryManager.getInstance().removeTargetListener(this,
				TargetType.ISO14443_CARD);
		DiscoveryManager.getInstance().removeTargetListener(this,
				TargetType.NDEF_TAG);
		DiscoveryManager.getInstance().removeTargetListener(this,
				TargetType.RFID_TAG);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.contactless.TargetListener#targetDetected(javax.microedition.contactless.TargetProperties[])
	 */
	public void targetDetected(TargetProperties[] properties) {
		if (properties != null) {
			// Process the tag in a separate thread.
			ReaderThread readerThread = this.m_controllerMidlet
					.startReaderThread(properties);
			if (readerThread != null) {
				readerThread.start();
			} else {
				throw new RuntimeException("Not tag reader thread defined.");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspire.rfid.nfc.midlet.reader.Reader#stop()
	 */
	public void stop() {
		removeListeners();
	}
}
