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
package org.ow2.aspirerfid.nfc.picking.midlet;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.contactless.TargetProperties;
import javax.microedition.midlet.MIDletStateChangeException;

import org.ow2.aspirerfid.nfc.midlet.generic.GenericMidlet;
import org.ow2.aspirerfid.nfc.midlet.generic.NFCMidletException;
import org.ow2.aspirerfid.nfc.midlet.generic.ReaderThread;
import org.ow2.aspirerfid.nfc.midlet.generic.RequestMessage;
import org.ow2.aspirerfid.nfc.midlet.generic.ui.AlertScreen;
import org.ow2.aspirerfid.nfc.midlet.reader.TagDetector;
import org.ow2.aspirerfid.nfc.midlet.reader.rfid.RFIDDetector;
import org.ow2.aspirerfid.nfc.midlet.reader.rfid.thread.RFIDSimpleReaderThread;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothControlerUser;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.BluetoothController;
import org.ow2.aspirerfid.nfc.midlet.sendersReceivers.bluetooth.ui.UIToBluetoothInterface;
import org.ow2.aspirerfid.nfc.picking.midlet.ui.MenuScreen;
import org.ow2.aspirerfid.nfc.picking.midlet.ui.PresentationScreen;
import org.ow2.aspirerfid.nfc.picking.midlet.ui.SendReportScreen;
import org.ow2.aspirerfid.nfc.picking.midlet.ui.ShowListScreen;
import org.ow2.aspirerfid.nfc.picking.util.Range;
import org.ow2.aspirerfid.nfc.picking.util.UidRanges;
import org.ow2.aspirerfid.nfc.picking.util.XmlTags;

import com.exploringxml.xml.Node;
import com.exploringxml.xml.Xparse;

/**
 * This midlet is the controller of the Picking application. It uses the
 * BluetoothController to manage the communication. It receives a list of items
 * in xml format. Also, it uses the RFID reader to detect and read tags. It
 * checks if the read tags match the patterns of the list he received. If they
 * do, he removes them and considers the items collected. At the end it sends
 * back a report with the results in xml format.
 * 
 * @author Perisanidi Maroula
 */
public class PickingMidlet extends GenericMidlet implements
		BluetoothControlerUser, TagDetector {

	/**
	 * Vector containing the product name and quantity.
	 */
	private Vector patterns;

	/**
	 * Hashtable containing the name and the uid of the collected item.
	 */
	private final Hashtable collectedTag = new Hashtable();

	/**
	 * Bluetooth controller.
	 */
	private BluetoothController m_bluecontroller = null;

	/**
	 * Vector containing the UidRanges objects of the items in the list.
	 */
	private final Vector uidRangesVector = new Vector();

	/**
	 * It parses the message and creates a vector of UidRanges objects.
	 * 
	 * @param respMessage
	 *            the message in xml format received.
	 */
	private void analyzeMessage(String respMessage) {
		// every time I receive a list, I remove its previous contents
		this.uidRangesVector.removeAllElements();
		Node root = new Xparse().parse(respMessage);
		int occur[] = { 1, 1, 1, 1, 1 };
		Node name, quantity, init, fin = null;
		int k = 1, j;

		boolean continue1 = true;
		while (continue1) {
			occur[1] = k;
			k++;
			if (root.find(XmlTags.PROD_LIST + "/" + XmlTags.PROD,
					occur) != null) {
				Vector rangeLocal = new Vector();
				name = root.find(XmlTags.PROD_LIST + "/"
						+ XmlTags.PROD + "/" + XmlTags.PROD_NAME,
						occur);
				quantity = root.find(XmlTags.PROD_LIST + "/"
						+ XmlTags.PROD + "/" + XmlTags.QUANTITY,
						occur);
				j = 1;
				boolean continue2 = true;
				while (continue2) {
					occur[3] = j;
					j++;
					if (root.find(XmlTags.PROD_LIST + "/"
							+ XmlTags.PROD + "/" + XmlTags.RANGES
							+ "/" + XmlTags.RANGE, occur) != null) {
						init = root.find(XmlTags.PROD_LIST + "/"
								+ XmlTags.PROD + "/"
								+ XmlTags.RANGES + "/"
								+ XmlTags.RANGE + "/"
								+ XmlTags.INIT, occur);
						fin = root.find(XmlTags.PROD_LIST + "/"
								+ XmlTags.PROD + "/"
								+ XmlTags.RANGES + "/"
								+ XmlTags.RANGE + "/"
								+ XmlTags.FINAL, occur);
						Range range = new Range(init.getCharacters(), fin
								.getCharacters());
						rangeLocal.addElement(range);
					} else {
						continue2 = false;
					}
				}
				UidRanges uidRanges = new UidRanges(name.getCharacters(),
						rangeLocal, quantity.getCharacters());
				this.uidRangesVector.addElement(uidRanges);
			} else {
				continue1 = false;
			}
		}
	}

	/**
	 * @return A vector of Strings with the uids pattern and quantity.
	 */
	private Vector createNameVector() {
		Vector patterns = new Vector();
		for (int i = 0; i < this.uidRangesVector.size(); i++) {
			UidRanges uidRanges = (UidRanges) this.uidRangesVector.elementAt(i);
			patterns.addElement(uidRanges.getName() + " ("
					+ uidRanges.getQuantity() + ")");
		}
		return patterns;
	}

	/**
	 * Connects via bluetooth to the pc and sends a report of the items that
	 * have not been collected. The same list is displayed on the mobile phone.
	 */
	public void createReport() {
		// The xml String contains the the items that have not been collected.
		String xml = "<" + XmlTags.PROD_LIST + ">";
		for (int i = 0; i < this.uidRangesVector.size(); i++) {
			UidRanges uidRanges = (UidRanges) this.uidRangesVector.elementAt(i);
			xml = xml + "<" + XmlTags.PROD + "><"
					+ XmlTags.QUANTITY + ">" + uidRanges.getQuantity()
					+ "</" + XmlTags.QUANTITY + ">" + "<"
					+ XmlTags.PROD_NAME + ">" + uidRanges.getName()
					+ "</" + XmlTags.PROD_NAME + ">" + "<"
					+ XmlTags.RANGES + ">";
			for (int j = 0; j < uidRanges.getVector().size(); j++) {
				Range range = (Range) uidRanges.getVector().elementAt(j);
				xml = xml + "<" + XmlTags.RANGE + "><"
						+ XmlTags.INIT + ">" + range.getMin() + "</"
						+ XmlTags.INIT + ">" + "<" + XmlTags.FINAL
						+ ">" + range.getMax() + "</" + XmlTags.FINAL
						+ "></" + XmlTags.RANGE + ">";
			}
			xml = xml + "</" + XmlTags.RANGES + "></"
					+ XmlTags.PROD + ">";
		}
		//Adding read tag id info
		xml += getReadTagIDs();
		//end adding tags
		//TODO Thorough overhaul of XML generation!
		xml = xml + "</" + XmlTags.PROD_LIST + ">";
		Vector uidNames = this.createNameVector();
		if (!this.m_bluecontroller.isBluetoothConnected()) {
			this.m_bluecontroller.connectBluetooth(new MenuScreen(this),
					new SendReportScreen(this, uidNames, this.collectedTag));
			this.m_bluecontroller.sendMessage(xml);
		} else {
			// Already Connected
			this.m_bluecontroller.sendMessage(xml);
			this.setActiveScreen(new SendReportScreen(this, uidNames,
					this.collectedTag));
		}
	}
	
	private String getReadTagIDs() {
		Enumeration e = collectedTag.keys();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<readTags>");
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			buffer.append("<tag id=\"").append(key).append("\"/>");
		}
		buffer.append("</readTags>");
		return buffer.toString();
	}

	/**
	 * Returns the bluetooth controller.
	 * 
	 * @return Bluetooth controller.
	 */
	public UIToBluetoothInterface getBluetoothController() {
		return this.m_bluecontroller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appl.museum.BluetoothControlerUser#informConnected()
	 */
	public synchronized void informConnected() {
		this.m_bluecontroller.sendMessage("<" + XmlTags.SEND_LIST + ">");
		String respMessage = this.m_bluecontroller.receiveMessage();
		try {
			this.analyzeMessage(respMessage);
			try {
				// Starts the detector.
				this.startDetector(new RFIDDetector(this));

			} catch (NFCMidletException e) {
				this.setActiveScreen(new AlertScreen(this,
						"There was a problem establishing the tag listener.",
						true));
			}
			this.showList();
		} catch (RuntimeException e) {
			this.setActiveScreen(new AlertScreen(this, "Malformed Xml!",
					new MenuScreen(this)));
		}

	}

	/**
	 * Connects to the bluetooth controller and receives the message in xml
	 * format. Calls the method that will do the parsing.
	 */
	public synchronized void receiveList() {
		this.m_bluecontroller.connectBluetooth(new MenuScreen(this),
				new MenuScreen(this));
	}

	/**
	 * If the list has been downloaded, it is displayed.
	 */
	public void showList() {
		this.patterns = this.createNameVector();
		if (this.uidRangesVector.isEmpty()) {
			this.setActiveScreen(new AlertScreen(this,
					"The list is empty. Click Receive List", new MenuScreen(
							this)));
		} else {
			this.setActiveScreen(new ShowListScreen(this, this.patterns));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.midlet.MIDlet#startApp()
	 */
	protected void startApp() throws MIDletStateChangeException {
		this.setActiveScreen(new PresentationScreen(this));
		this.m_bluecontroller = new BluetoothController(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see reader.TagDetector#startReaderThread(javax.microedition.contactless.TargetProperties[])
	 */
	public ReaderThread startReaderThread(TargetProperties[] properties) {
		ReaderThread readerThread = new RFIDSimpleReaderThread(properties, this);
		return readerThread;
	}

	/*
	 * (non-Javadoc) Compare the uid read with the ones in the list. If it is
	 * the same remove from list and recreate the list.
	 * 
	 * @see reader.TagDetector#tagRead(core.RequestMessage)
	 */
	public synchronized void tagRead(RequestMessage message) {
		// System.out.println("Tag read");
		String tagUid = message.getTagUID();
		String sub = "";
		boolean flag = true;
		int tagUidPart, quaint;
		int rangeSize, last;
		for (int i = 0; i < this.uidRangesVector.size(); i++) {
			UidRanges uidRanges = (UidRanges) this.uidRangesVector.elementAt(i);
			// // if the size is correct
			last = 0;
			if (uidRanges.getPatternLength() == message.getTagUID().length()) {
				// get the ranges for this uid
				for (int j = 0; j < uidRanges.getVector().size(); j++) {
					Range range = (Range) uidRanges.getVector().elementAt(j);
					rangeSize = range.getRangeLength();
					sub = tagUid.substring(last, last + rangeSize);
					last = last + rangeSize;
					tagUidPart = Integer.parseInt(sub, 16);
					flag = range.isInRange(tagUidPart);
				}
				if (flag) {
					if (!this.collectedTag.containsKey(tagUid)) {
						// the uid was in Range, pick the item
						quaint = uidRanges.getQuantity();
						// check the quantity
						if (quaint > 1) {
							uidRanges.setQuantity(quaint - 1);
						} else {
							// if there is only one, remove it from the list
							this.uidRangesVector.removeElement(uidRanges);
						}
						this.collectedTag.put(tagUid, uidRanges.getName());
						this.showList();
						this.setActiveScreen(new AlertScreen(this,
								"Item considered picked.", new ShowListScreen(
										this, this.patterns)));
					} else {
						this.setActiveScreen(new AlertScreen(this,
								"Item already picked.", new ShowListScreen(
										this, this.patterns)));
					}
				} else {
					this.setActiveScreen(new AlertScreen(this, "Unknown item.",
							new ShowListScreen(this, this.patterns)));
				}
			} else {
				this.setActiveScreen(new AlertScreen(this, "Unknown item.",
						new ShowListScreen(this, this.patterns)));
			}
		}
	}

	public void informDisonnected() {
		// TODO Auto-generated method stub

	}
}
