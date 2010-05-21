/*
   Copyright 2005-2010, OW2 Aspire RFID project 
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.

   Contact: OW2 Aspire RFID project <X AT Y DOT org> (with X=aspirerfid and Y=ow2)

   LGPL version 2.1 full text http://www.gnu.org/licenses/lgpl-2.1.txt    
 */
package org.ow2.aspirerfid.reader.acr122u.impl;

import java.util.prefs.Preferences;

import org.apache.log4j.Logger;

import com.tikitag.client.tagservice.ReaderEvent;
import com.tikitag.client.tagservice.ReaderMonitor;
import com.tikitag.client.tagservice.impl.Acr122TagReader;

/**
 * 
 * @author Gabriel Pedraza Ferreira
 * 
 */
public class SimpleReaderMonitorImpl implements ReaderMonitor {
	private static final Logger log = Logger.getLogger(Acr122TagReader.class.getName());
	private final Preferences prefs = Preferences.userNodeForPackage(SimpleReaderMonitorImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tikitag.client.tagservice.ReaderMonitor#onReaderEvent(com.tikitag.
	 * client.tagservice.ReaderEvent)
	 */
	public void onReaderEvent(ReaderEvent readerEvent) {
		if (readerEvent.getEventType().equals(com.tikitag.client.tagservice.ReaderEvent.ReaderEventType.READER_ADDED)) {
			String readerSn = readerEvent.getReaderId().getSerialNr();
			boolean readerFound = false;
			for (int i = 1; i <= prefs.getInt("readers.index", 0); i++)
				if (readerSn.equals(prefs.get((new StringBuilder()).append("readers.list.snreader.").append(i).toString(),
				      null)))
					readerFound = true;

			if (!readerFound) {
				int newReaderIndex = prefs.getInt("readers.index", 0) + 1;
				prefs.putInt("readers.index", newReaderIndex);
				prefs.put((new StringBuilder()).append("readers.list.snreader.").append(newReaderIndex).toString(),
				      readerSn);
				// NewReaderEvent newReaderEvent = new
				// NewReaderEvent(readerEvent.getReaderId());
			} else {
				log.debug("New reader detected, but already discovered, no further action.");
			}
		} else if (readerEvent.getEventType().equals(
		      com.tikitag.client.tagservice.ReaderEvent.ReaderEventType.READER_REMOVED)) {
			log.debug("A reader was removed " + readerEvent.getReaderId() + " serial number"
			      + readerEvent.getReaderId().getSerialNr());
		} else if (readerEvent.getEventType().equals(
		      com.tikitag.client.tagservice.ReaderEvent.ReaderEventType.READER_FAILURE)) {
			log.debug("A reader has failed " + readerEvent.getReaderId() + " serial number"
			      + readerEvent.getReaderId().getSerialNr());
		}
	}

}
