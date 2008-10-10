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
package org.ow2.aspirerfid.nfc.midlet.reader.rfid.thread;

import java.io.IOException;

import javax.microedition.contactless.ContactlessException;
import javax.microedition.contactless.TargetProperties;
import javax.microedition.contactless.TargetType;
import javax.microedition.contactless.ndef.NDEFMessage;
import javax.microedition.contactless.ndef.NDEFRecord;
import javax.microedition.contactless.ndef.NDEFRecordType;
import javax.microedition.contactless.ndef.NDEFTagConnection;
import javax.microedition.io.Connector;

import org.ow2.aspirerfid.nfc.midlet.generic.ReaderThread;
import org.ow2.aspirerfid.nfc.midlet.generic.RequestMessage;
import org.ow2.aspirerfid.nfc.midlet.message.send.CompleteRequestMessage;
import org.ow2.aspirerfid.nfc.midlet.reader.TagDetector;

/**
 * Process the information tag and creates a message with all the information.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 * @version 1.0.0 31/07/2008
 */
public class RFIDInformationReaderThread extends ReaderThread {

	/**
	 * Instance the class.
	 * 
	 * @param properties
	 *            Set of properties of the tag.
	 * @param midlet
	 *            Midlet that calls the RFID Detector.
	 */
	public RFIDInformationReaderThread(TargetProperties[] properties,
			TagDetector midlet) {
		super(properties, midlet);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.ReaderThread#buildMessage(javax.microedition.contactless.TargetProperties)
	 */
	protected RequestMessage buildMessage(TargetProperties properties) {
		CompleteRequestMessage message = new CompleteRequestMessage(
				this.m_midlet);

		// UID
		String uid = properties.getUid();
		message.setTagUID(uid);

		String messageContent = "";

		if (properties.hasTargetType(TargetType.ISO14443_CARD)) {
			// Tag type.
			message.setType("ISO 14443 Tag");

			// URL.
			try {
				message
						.setURL(properties
								.getUrl(Class
										.forName("javax.microedition.contactless.sc.ISO14443Connection")));
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Tag class not found: "
						+ e.getMessage());
			}
		} else if (properties.hasTargetType(TargetType.RFID_TAG)) {
			// Tag type.
			message.setType("RFID Tag");

			// URL.
			message.setURL(properties.getUrl());

		} else if (properties.hasTargetType(TargetType.NDEF_TAG)) {
			// Tag type.
			message.setType("NDEF Tag");

			// Get URL to open the connection.
			String url = properties.getUrl();

			try {
				// Open NDEFTagConnection to the target.
				NDEFTagConnection connection = (NDEFTagConnection) Connector
						.open(url);
				// Read data from the target
				NDEFMessage ndefMessage = connection.readNDEF();
				int qty = ndefMessage.getNumberOfRecords();
				NDEFRecord[] records = ndefMessage.getRecords();

				messageContent += "\nrec qty: " + qty;

				for (int j = 0; j < records.length; j++) {
					NDEFRecord record = records[j];

					byte[] id = record.getId();
					byte[] payload = record.getPayload();
					long length = record.getPayloadLength();
					NDEFRecordType type = record.getRecordType();
					int format = type.getFormat();
					String typeName = type.getName();

					messageContent += "\nid: " + id + "\npayload: " + payload
							+ "\nlength: " + length + "\nformatType: " + format
							+ "\nformatName" + typeName;

				}
			} catch (IOException e) {
				messageContent += "Error connecting or reading the tag";
			} catch (ContactlessException e) {
				messageContent += "Error reading the tag";
			}
		} else if (properties.hasTargetType(TargetType.VISUAL_TAG)) {
			// Not defined in the Nokia 6131 NFC.
		} else {
			Class[] connNames = properties.getConnectionNames();
			String url = properties.getUrl(connNames[0]);
			try {
				if (connNames[0]
						.equals(Class
								.forName("javax.microedition.contactless.rf.PlainPlainTagConnection"))) {
					properties.getMapping();
					Connector.open(url);
					// Construct input vector based on the mapping
					// and begin communication
				}
			} catch (ClassNotFoundException e) {
				messageContent += "Error searching the tag class";
			} catch (IOException e) {
				messageContent += "Error connecting the tag";
			}
		}

		Class[] classes = properties.getConnectionNames();
		for (int j = 0; j < classes.length; j++) {
			messageContent += ("\n" + ".2." + (j + 1) + " Name =\n" + classes[j]
					.getName());
			messageContent += ("\n" + ".2." + (j + 1) + " URL =\n" + properties
					.getUrl(classes[j]));
		}

		TargetType[] types = properties.getTargetTypes();

		for (int k = 0; k < types.length; k++) {
			messageContent += ("\n- " + ".3." + (k + 1) + " Type = " + types[k]
					.toString());
		}

		message.setContent(messageContent);
		return message;
	}
}
