package org.ow2.aspirerfid.nfc.picking.server;

import java.util.List;

/**
 * Workaround for intercepting the processed messages.
 * TODO Move/integrate this callback into the BluetoothServer
 * 
 * @author kiev
 *
 */
public interface MessageProcessorCallback {
	//The list of tags that have been read
	void sendResult(List<String> readTags);
}
