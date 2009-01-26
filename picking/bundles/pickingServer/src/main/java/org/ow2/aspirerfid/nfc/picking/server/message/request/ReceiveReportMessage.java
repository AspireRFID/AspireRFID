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

import java.util.List;
import java.util.Vector;

import org.ow2.aspirerfid.nfc.picking.server.MessageProcessorCallback;
import org.ow2.aspirerfid.nfc.picking.util.Range;
import org.ow2.aspirerfid.nfc.picking.util.UidRanges;
import org.ow2.aspirerfid.nfc.server.message.request.AbstractRequestMessage;
import org.ow2.aspirerfid.nfc.server.message.response.AbstractResponseMessage;
import org.ow2.aspirerfid.nfc.server.message.response.OKMessage;

/**
 * 
 * @author Maroula Perisanidi
 * @version 1.0 01/05/2008
 */
public class ReceiveReportMessage extends AbstractRequestMessage {
	// runnable object that is called back
	private MessageProcessorCallback callback;

	public ReceiveReportMessage(MessageProcessorCallback callback) {
		this.callback = callback;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.nfc.server.message.request.AbstractRequestMessage#getResponseMessage(java.lang.String)
	 */
	public AbstractResponseMessage getResponseMessage(String receivedMess) {
		try {
			System.out.println("--> " + receivedMess);
			PickingListParser parser = new PickingListParser(receivedMess);
			List<UidRanges> rangeList = parser.getPickingList();
			printMissingItems(rangeList);
			printPickedItems(parser.getPickedUIDs());
			callback.sendResult(parser.getPickedUIDs());
		} catch (Exception ex) {
			System.out.println("Error parsing received message "
					+ ex.getMessage());
		}

		// Node root = new Xparse().parse(receivedMess);
		// processRanges(root);
		// extractReadIds(root);
		return new OKMessage();
	}

	private void printPickedItems(List<String> pickedUIDs) {
		for (String uid : pickedUIDs) {
			System.out.println(" Picked: " + uid);
		}
	}

	private void printMissingItems(List<UidRanges> rangeList) {
		System.out.println("Missing items: ");
		for (UidRanges ranges : rangeList) {
			System.out.println("<Name>" + ranges.getName());
			System.out.println("<Quantity>" + ranges.getQuantity());
			Vector rangeVector = ranges.getVector();
			for (int i = 0; i < rangeVector.size(); i++) {
				Range range = (Range) rangeVector.get(i);
				System.out.println("<init>" + range.getMin());
				System.out.println("<final>" + range.getMax());
			}
		}
	}

}
