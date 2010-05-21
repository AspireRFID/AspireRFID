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
package org.ow2.aspirerfid.ale.server.readers.rp.base;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.ow2.aspirerfid.ale.server.Tag;

/**
 * 
 * @author Gabriel Pedraza Ferreira
 *
 */
public abstract class AbstractReader implements Runnable {

	/** logger */
	private static final Logger LOG = Logger.getLogger(AbstractReader.class);

	/** Timer for polling reader's buffer */
	private Thread tReadTimer;

	/** reading frequency */
	private static final int DEFAULT_POLL_FREQUENCY = 500;

	/** Falg to the polling thread */
	private boolean isReadTimerEnded;

	/** waiting state */
	public static final int STATE_READY = 0; // Reader device is detected and
	// ready

	/** execution state */
	public static final int STATE_PROCESSING = -1; // Reader is detected and
	// executing commands

	/** not reader state */
	public static final int STATE_NO_READER = -2; // No reader has not been
	// detected

	/** reader state */
	private int iState;

	/** Reader logical name */
	private String logicalName;

	/** Human reader ID */
	private String readerGUId;

	/** Simple reader id */
	private static int readerId = 1;

	/** the reader adaptor */
	protected AbstractAdaptor adaptor;

	/** the buffer of read tags */
	protected List<Tag> readingBuffer = new LinkedList<Tag>();

	public AbstractReader(AbstractAdaptor adaptor, Map<String, String> properties) {
		readerGUId = "SimpleAdaptor " + readerId++;

		this.adaptor = adaptor;
		setState(STATE_NO_READER);

		setSettings(properties);

	}

	/**
	 * Sets the current reader state
	 * 
	 * @param iNewState
	 */
	protected void setState(int iNewState) {
		iState = iNewState;
	}

	/**
	 * Retrieves current reader state
	 */
	protected int getState() {
		return iState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (!isReadTimerEnded) {
			read();
			try {
				Thread.sleep(DEFAULT_POLL_FREQUENCY); // TODO: Configuration parameter
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * It is used by the adaptor to get the read tags
	 */
	public void poll() {
		LOG.debug("POLLING. Read " + readingBuffer.size());
		synchronized (readingBuffer) {
			adaptor.addTags(new LinkedList<Tag>(readingBuffer));
			readingBuffer.clear();
		}
	}

	/**
	 * It is used to start the reader
	 */
	public void startReader() {
		if (getState() == STATE_NO_READER)
			openReader();

		if (getState() == STATE_READY)
			startPollingThread();

	}

	/**
	 * It stops the physical reader
	 */
	public void stopReader() {
		if (!(getState() == STATE_NO_READER)) {
			stopPollingThread();
			closeReader();
		}
	}

	/**
	 * Starts the thread that is polling the physical reader
	 */
	protected void startPollingThread() {
		setState(STATE_PROCESSING);
		tReadTimer = new Thread(this, "physical reader thread");
		isReadTimerEnded = false;
		tReadTimer.start();
	}

	/**
	 * Stops the thread that is polling the physical reader
	 */
	protected void stopPollingThread() {
		isReadTimerEnded = true;
		setState(STATE_READY);
	}

	public String getLogicalName() {
		return logicalName;
	}

	public String getReaderGUId() {
		return readerGUId;
	}

	public void setLogicalName(String logName) {
		this.logicalName = logName;
	}

	/**
	 * Translates a tag string representation in hexa radix to a byte[]
	 * 
	 * @param tagId
	 * @return
	 */
	protected byte[] tagIDToByte(String tagId) {
		int length = tagId.length();
		if (length % 2 == 0) {
			byte[] tempArray = new byte[length / 2];
			String tempSt = tagId.substring(0, 2);
			int beginIndex = 0;
			int endIndex = 2;

			for (int i = 0; i < 8; i++) {
				tempSt = tagId.substring(beginIndex, endIndex);
				beginIndex += 2;
				endIndex += 2;
				tempArray[i] = (byte) Short.parseShort(tempSt, 16);
			}
			return tempArray;
		}
		return new byte[] {};
	}

	/**
	 * Temporal method. Instead TDT component must be used.
	 * 
	 * @param tagId
	 * @return
	 */
	protected String convertISO15693toPureURI(String tagId) {
		if (tagId.length() != 16)
			return tagId;
		try {
			int mfgCode = Integer.parseInt(tagId.substring(2, 4), 16);
			BigInteger isoSerial = new BigInteger(tagId.substring(4), 16);
			return "urn:iso:id:15693:" + mfgCode + "." + isoSerial.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tagId;
	}

	protected String convertISO14443toPureURI(String tagId) {
		String iso = "";
		if (tagId.length() == 8)
			iso = "single";
		else if (tagId.length() == 14)
			iso = "double";
		else if (tagId.length() == 20)
			iso = "triple";
		else
			return tagId;
      int uid0 = Integer.parseInt(tagId.substring(0,2),16);
      BigInteger isoSerial=new BigInteger(tagId.substring(2),16);
      return "urn:iso:id:14443"+ iso+":"+ uid0 + "." + isoSerial.toString();
	}

	/**
	 * Initializes the reader device
	 */
	protected abstract void openReader();

	/**
	 * Closes the reader device
	 */
	protected abstract void closeReader();

	/**
	 * Poll the reader device
	 */
	protected abstract void read();

	/**
	 * Sets the reader settings
	 * 
	 * @param properties
	 *           a Map with the new settings
	 */
	protected abstract void setSettings(Map<String, String> properties);

}
