package org.ow2.aspire.rfid.nfc.server.message.request;

import org.ow2.aspire.rfid.nfc.server.message.response.AbstractResponseMessage;
import org.ow2.aspire.rfid.nfc.server.message.response.OKMessage;

/**
 * This is the default message when the incoming content was not recognized.
 * 
 * @author <a href="mailto:angoca@yahoo.com">Andres Gomez</a>
 */
public class DefaultMessage extends AbstractRequestMessage {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspire.rfid.nfc.server.message.request.RequestMessage#getResponseMessage(java.lang.String)
	 */
	@Override
	public final AbstractResponseMessage getResponseMessage(
			final String receivedMess) {
		AbstractResponseMessage response = new OKMessage();
		return response;
	}

}
