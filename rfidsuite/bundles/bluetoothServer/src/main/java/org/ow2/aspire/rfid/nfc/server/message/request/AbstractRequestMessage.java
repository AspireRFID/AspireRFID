package org.ow2.aspire.rfid.nfc.server.message.request;

import org.ow2.aspire.rfid.nfc.server.message.response.AbstractResponseMessage;

/**
 * Message received from the telephone.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 */
public abstract class AbstractRequestMessage {

	/**
	 * Creates the message to return to the telephone.
	 * 
	 * @param receivedMess
	 *            Received message.
	 * @return Specific response message.
	 */
	public abstract AbstractResponseMessage getResponseMessage(
			String receivedMess);

}
