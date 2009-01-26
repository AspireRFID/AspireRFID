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
package org.ow2.aspirerfid.nfc.picking.server.message.request;

import org.ow2.aspirerfid.nfc.picking.server.message.response.ListMessage;
import org.ow2.aspirerfid.nfc.server.message.request.AbstractRequestMessage;
import org.ow2.aspirerfid.nfc.server.message.response.AbstractResponseMessage;

/**
 * TODO Javadoc
 * 
 * @author Maroula Perisanidi
 * @version 1.0 01/05/2008
 */
public class ReceiveListMessage extends AbstractRequestMessage {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.nfc.server.message.request.AbstractRequestMessage#getResponseMessage(java.lang.String)
	 */
	@Override
	public AbstractResponseMessage getResponseMessage(String receivedMess) {
		return new ListMessage();
	}
}
