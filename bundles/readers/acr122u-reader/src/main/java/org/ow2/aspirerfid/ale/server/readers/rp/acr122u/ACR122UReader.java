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
package org.ow2.aspirerfid.ale.server.readers.rp.acr122u;

import java.util.Map;

import org.ow2.aspirerfid.ale.server.Tag;
import org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractAdaptor;
import org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader;
import org.ow2.aspirerfid.reader.acr122u.impl.SimpleReaderMonitorImpl;

import com.tikitag.client.tagservice.ReaderMonitor;
import com.tikitag.client.tagservice.TagMonitor;
import com.tikitag.client.tagservice.TagService;
import com.tikitag.client.tagservice.TagServiceConfiguration;
import com.tikitag.client.tagservice.impl.TagServiceImpl;
import com.tikitag.ons.model.util.TagEvent;

/**
 * @author Gabriel Pedraza Ferreira
 * 
 */
public class ACR122UReader extends AbstractReader {

	private static final long DEFAULT_POLL_INTERVAL = 100;

	private static final long DEFAULT_PUT_THRESHOLD_TIME = 1000;

	private TagService tagService;

	private TagServiceConfiguration tagServiceConfiguration;

	private TagMonitor tagMonitor;

	private ReaderMonitor readerMonitor;

	/**
	 * Default constructor
	 * 
	 * @param adaptor
	 * @param properties
	 */
	public ACR122UReader(AbstractAdaptor adaptor, Map<String, String> properties) {
		super(adaptor, properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader#closeReader()
	 */
	@Override
	protected void closeReader() {
		tagService.removeReaderMonitor(readerMonitor);
		tagService.removeTagMonitor(tagMonitor);
		setState(STATE_NO_READER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader#openReader()
	 */
	@Override
	protected void openReader() {
		tagService = new TagServiceImpl(tagServiceConfiguration);
		setState(STATE_READY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader#read()
	 */
	@Override
	protected void read() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader#setSettings
	 * (java.util.Map)
	 */
	@Override
	protected void setSettings(Map<String, String> properties) {
		long temp = 0;

		tagServiceConfiguration = new TagServiceConfiguration();

		try {
			temp = Integer.parseInt(properties.get("PollInterval"));
			tagServiceConfiguration.setPollInterval(temp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			tagServiceConfiguration.setPollInterval(DEFAULT_POLL_INTERVAL);
		}

		try {
			temp = Integer.parseInt(properties.get("PutThresholdTime"));
			tagServiceConfiguration.setPutThresholdTime(temp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			tagServiceConfiguration.setPutThresholdTime(DEFAULT_PUT_THRESHOLD_TIME);
		}

	}

	@Override
	public void run() {
		/*
		 * A thread it's not necessary. A listener service is used instead. The
		 * tikitag library has a listener interaction model.
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.ow2.aspirerfid.ale.server.readers.rp.base.AbstractReader#
	 * startPollingThread()
	 */
	@Override
	protected void startPollingThread() {
		readerMonitor = new SimpleReaderMonitorImpl();

		tagMonitor = new TagMonitor() {

			public void onTagEvent(TagEvent tagEvent) {

				String readerId = tagEvent.getReaderId().toString();

				String strData = tagEvent.getActionTag().getTagId().getIdentifier();
				byte[] data = strData.getBytes();

				Tag tag = new Tag(readerId, data, strData, System.currentTimeMillis());
				synchronized (readingBuffer) {
					readingBuffer.add(tag);
				}

			}
		};

		tagService.addReaderMonitor(readerMonitor);
		tagService.addTagMonitor(tagMonitor);
		tagService.start();

		setState(STATE_PROCESSING);
	}

}
