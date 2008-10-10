package org.ow2.aspire.rfid.nfc.server.message.response;

/**
 * This is the receipt message that indicates to the telephone that the message
 * was well received.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 */
public class OKMessage extends AbstractResponseMessage {

	/*
	 * (non-Javadoc)
	 * 
	 * @see bluetooth.messages.response.ResponseMessage#getMessage()
	 */
	public final String getMessage() {
		return "<ok/>";
	}
}
