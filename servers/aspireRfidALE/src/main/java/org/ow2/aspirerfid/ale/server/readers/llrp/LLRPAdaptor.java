/**
 * Copyright (C) 2008-2010, Aspire 
 *
 * Aspire is free software; you can redistribute it and/or 
 * modify it under  the terms of the GNU Lesser General Public 
 * License version 2.1 as published by the Free Software Foundation (the 
 * "LGPL"). 
 *
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library in the file COPYING-LGPL-2.1; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA. 
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY 
 * OF ANY KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations. 
 */

package org.ow2.aspirerfid.ale.server.readers.llrp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import org.ow2.aspirerfid.ale.server.Tag;
import org.ow2.aspirerfid.ale.server.readers.BaseReader;
import org.ow2.aspirerfid.ale.server.readers.IdentifyThread;
import org.accada.ale.wsdl.ale.epcglobal.ImplementationException;
import org.accada.ale.wsdl.ale.epcglobal.ImplementationExceptionResponse;
import org.accada.ale.xsd.ale.epcglobal.LRSpec;
import org.accada.hal.HardwareException;
import org.accada.hal.Observation;
import org.accada.reader.rp.proxy.RPProxyException;

/**
 * represents an adaptor to the LLRP reader.
 * 
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class LLRPAdaptor extends BaseReader {

	/** logger. */
	private static final Logger LOG = Logger.getLogger(LLRPAdaptor.class);

	/** default readTimeInterval. */
	public static final int DEFAULT_READTIME_INTERVALL = 2000;

	/**
	 * input generator for the LLRP that establishes connection and receives
	 * tags.
	 */
	private LLRPInputGenerator llrpInputGenerator = null;

	/** the host where Unencrypted connection is established. */
	private String connectionChannelAddress = null;

	/** the port where Unencrypted connection is established. */
	private int connectionChannelPort = -1;

	/** the host where encrypted TLS connection is established. */
	private String encryptedConnectionChannelAddress = null;

	/** the port where encrypted TLS connection is established. */
	private int encryptedConnectionChannelPort = -1;

	/** the interval in which shall be read from the reader. */
	private int readTimeInterval = -1;

	/** the physical sources where tags are read in a String (eg shelf1, shelf2). */
	private String sourcesString = null;

	/** the physical sources. */
	private Set<String> sources = new HashSet<String>();

	/** the RO Spec ID. */
	private int roSpecID = 1;

	/** to get all the tags we need a polling thread. */
	private IdentifyThread identifyThread = null;

	/**
	 * constructor for the LLRP adaptor.
	 */
	public LLRPAdaptor() {
		super();
	}

	/**
	 * initializes a LLRPAdaptor. this method must be called befor the Adaptor
	 * can be used.
	 * 
	 * @param name
	 *            the name for the reader encapsulated by this adaptor.
	 * @param spec
	 *            the specification that describes the current reader.
	 * @throws ImplementationException
	 *             whenever an internal error occurs.
	 * 
	 */
	public void initialize(String name, LRSpec spec) throws ImplementationExceptionResponse {
		try {
			super.initialize(name, spec);
		} catch (ImplementationExceptionResponse ie) {
			LOG.error("error in initialize of superclass");
			throw ie;
		}

		try {
			// extract from LRSpec how to connect to the reader
			extractConnectionSettings();
		} catch (ImplementationExceptionResponse ie) {
			ie.printStackTrace();
			LOG.error("could not extract connection settings from LRSpec");
			throw new ImplementationExceptionResponse();
		}

		// connect to the reader
		connectReader();
	}

	/**
	 * this method extracts the connection settings from the LRProperty. when
	 * there is the need to disconnect the reader (when something in the
	 * connection to the reader has been changed in the LRSpec), true will be
	 * returned false otherwise. if necessary the reader will be restarted
	 * 
	 * @throws ImplementationException
	 *             whenever an error occurs
	 * @return returns true if the connection to the reader needs to be
	 *         reconnected
	 */
	private boolean extractConnectionSettings() throws ImplementationExceptionResponse {

		String newConnectionChannelAddress = logicalReaderProperties.get("ConnectionPointAddress");
		int newConnectionChannelPort = Integer.parseInt(logicalReaderProperties.get("ConnectionPointPort"));

		roSpecID = Integer.parseInt(logicalReaderProperties.get("RoSpecID"));

		String interval = logicalReaderProperties.get("ReadTimeInterval");

		setReadTimeInterval(-1);
		try {
			setReadTimeInterval(Integer.parseInt(interval));
		} catch (Exception ne) {
			LOG.error("could not extract readTimeIntervall from LRPropery");
			throw new ImplementationExceptionResponse("could not extract notificationPoint from LRPropery");
		}

		// assert that the readTimeInterval is not -1
		if (readTimeInterval == -1) {
			LOG.error("ReadTimeInterval not set - assuming 2000ms");
			setReadTimeInterval(DEFAULT_READTIME_INTERVALL);
		}

		boolean disconnect = false;

		// compare the new sources string to the old sources string
		String nsources = logicalReaderProperties.get("PhysicalReaderSource");
		if (!nsources.equalsIgnoreCase(sourcesString)) {
			disconnect = true;
		}

		// disconnect if connection channel address has changed
		if (!newConnectionChannelAddress.equalsIgnoreCase(connectionChannelAddress)) {
			disconnect = true;
		}

		// disconnect when the port has changed
		if (newConnectionChannelPort != connectionChannelPort) {
			disconnect = true;
		}

		// disconnect when the reader was not connected before
		if (llrpInputGenerator != null && !llrpInputGenerator.isReady()) {
			disconnect = true;
		}

		LOG.debug("readTimeInterval " + readTimeInterval);

		LOG.debug(String.format("connectionChannelAddress %s on port %d", newConnectionChannelAddress, newConnectionChannelPort));

		// set the new connection Channel Settings
		setConnectionChannelAddress(newConnectionChannelAddress);
		setConnectionChannelPort(newConnectionChannelPort);

		// set the new sources
		sourcesString = nsources;
		setSourcesFromArray(sourcesString.split("[,]"));

		return disconnect;
	}

	/**
	 * sets up a reader. if the adaptor can be connected to the llrp-proxy the
	 * adaptor will be set to connected.
	 * 
	 * @throws ImplementationException
	 *             whenever an internal error occured
	 */
	@Override
	public void connectReader() throws ImplementationExceptionResponse {
		if (isConnected()) {
			return;
		}

		try {
			llrpInputGenerator = new LLRPInputGenerator(this);

		} catch (ImplementationExceptionResponse e) {
			setDisconnected();
			throw e;
		}

		LOG.debug("setup identifyThread on RPAdaptor " + getName());
		// setup the polling thread
		identifyThread = new IdentifyThread(this);
		identifyThread.setPollingFrequency(10);
		identifyThread.setSourceIds(sourcesString.split("[,]"));
		identifyThread.start();

		// suspend the polling thread to the beginning
		identifyThread.suspendIdentify();

		setConnected();
	}

	/**
	 * destroys a reader.
	 * 
	 * @throws ImplementationException
	 *             whenever an internal error occured
	 */
	@Override
	public void disconnectReader() throws ImplementationExceptionResponse {

		setConnectionChannelAddress(null);
		setEncryptedConnectionChannelAddress(null);
		setConnectionChannelPort(-1);
		setEncryptedConnectionChannelPort(-1);
		setReadTimeInterval(-1);

		if (llrpInputGenerator != null) {
			llrpInputGenerator.remove();

			llrpInputGenerator = null;
		}

		if (identifyThread != null) {
			identifyThread.stopIdentify();
			identifyThread = null;
		}
		setDisconnected();
		setStopped();
	}

	/**
	 * whenever a new Tag is read a notification is sent to the observers.
	 * 
	 * @param tag
	 *            a tag read on the reader
	 */
	@Override
	public void addTag(Tag tag) {
		setChanged();
		tag.addTrace(getName());
		// LOG.debug("calling observers");
		notifyObservers(tag);
	}

	/**
	 * whenever new Tags are read a notification is sent to the observers.
	 * 
	 * @param tags
	 *            a list of tags to be added to the reader
	 */
	@Override
	public void addTags(List<Tag> tags) {
		setChanged();
		for (Tag tag : tags) {
			tag.addTrace(getName());
		}
		// adds the readed tags for proccesing
		notifyObservers(tags);
	}

	/**
	 * starts a base reader to read tags. if the reader is not yet connected
	 * this command will connect the reader immediately and then start it. If
	 * the reader cannot be connected then the reader is not started as well.
	 * 
	 */
	@Override
	public synchronized void start() {
		if (!isConnected()) {
			try {
				connectReader();
			} catch (ImplementationExceptionResponse e) {
				e.printStackTrace();

				setDisconnected();
			}
		}

		// reader is not connected so it is not started as well
		if (!isConnected()) {
			setStopped();
		} else {
			LOG.debug("identifyThread starting to identify");
			identifyThread.resumeIdentify();
			setStarted();
		}
	}

	/**
	 * stops a reader from reading tags.
	 * 
	 */
	@Override
	public synchronized void stop() {
		LOG.debug("identifyThread suspend to identify");
		identifyThread.suspendIdentify();
		setStopped();
	}

	/**
	 * updates a reader according the specified LRSpec.
	 * 
	 * @param spec
	 *            LRSpec for the reader
	 * @throws ImplementationException
	 *             whenever an internal error occurs
	 */
	@Override
	public synchronized void update(LRSpec spec) throws ImplementationExceptionResponse {
		stop();

		setLRSpec(spec);
		if (extractConnectionSettings()) {
			LOG.debug("restarting reader " + getName());
			disconnectReader();
			// set the connection settings again and then start the reader
			extractConnectionSettings();
			connectReader();
		}
		start();
	}

	/**
	 * Triggers the identification of all tags that are currently available on
	 * the reader. this method is used when the IdentifyThread is used to poll
	 * the adaptor.
	 * 
	 * @param readPointNames
	 *            the readers/sources that have to be polled
	 * @return a set of Observations
	 * @throws HardwareException
	 *             whenever an internal hardware error occurs (as reader not
	 *             available...)
	 */
	@Override
	public Observation[] identify(String[] readPointNames) throws HardwareException {
		// TODO: Observe tags only from the spesific read Point Names
		// LOG.debug("identify called an RPAdaptor " + getName());

		if ((llrpInputGenerator != null) && (countObservers() > 0)) {

			if (llrpInputGenerator.isReady()) {

				try {
					llrpInputGenerator.poll();

				} catch (RPProxyException e) {
					e.printStackTrace();
					throw new HardwareException("Could not poll the adaptor " + getName());
				}
			} else {
				LOG.debug("rp-proxy not ready (yet)");
			}
		}

		return null;
	}

	/**
	 * @return the connectionChannelAddress
	 */
	public String getConnectionChannelAddress() {
		return connectionChannelAddress;
	}

	/**
	 * @param connectionChannelAddress
	 *            the connectionChannelAddress to set
	 */
	public void setConnectionChannelAddress(String connectionChannelAddress) {
		this.connectionChannelAddress = connectionChannelAddress;
	}

	/**
	 * @return the connectionChannelPort
	 */
	public int getConnectionChannelPort() {
		return connectionChannelPort;
	}

	/**
	 * @param connectionChannelPort
	 *            the connectionChannelPort to set
	 */
	public void setConnectionChannelPort(int connectionChannelPort) {
		this.connectionChannelPort = connectionChannelPort;
	}

	/**
	 * @return the encryptedConnectionChannelAddress
	 */
	public String getEncryptedConnectionChannelAddress() {
		return encryptedConnectionChannelAddress;
	}

	/**
	 * @param encryptedConnectionChannelAddress
	 *            the encryptedConnectionChannelAddress to set
	 */
	public void setEncryptedConnectionChannelAddress(String encryptedConnectionChannelAddress) {
		this.encryptedConnectionChannelAddress = encryptedConnectionChannelAddress;
	}

	/**
	 * @return the encryptedConnectionChannelPort
	 */
	public int getEncryptedConnectionChannelPort() {
		return encryptedConnectionChannelPort;
	}

	/**
	 * @param encryptedConnectionChannelPort
	 *            the encryptedConnectionChannelPort to set
	 */
	public void setEncryptedConnectionChannelPort(int encryptedConnectionChannelPort) {
		this.encryptedConnectionChannelPort = encryptedConnectionChannelPort;
	}

	/**
	 * returns the readTimeInterval.
	 * 
	 * @return the readTimeInterval
	 */
	public int getReadTimeInterval() {
		return readTimeInterval;
	}

	/**
	 * sets the readTimeInterval.
	 * 
	 * @param readTimeInterval
	 *            the readTimeInterval
	 */
	private void setReadTimeInterval(int readTimeInterval) {
		this.readTimeInterval = readTimeInterval;
	}

	/**
	 * returns the sources from where the reader reads tags.
	 * 
	 * @return the sources from where the reader reads tags.
	 */
	public Set<String> getSources() {
		return sources;
	}

	/**
	 * sets the sources from where the reader reads tags. the sources are copied
	 * into the HashSet sources
	 * 
	 * @param sources
	 *            an array of strings containing the sources.
	 */
	private void setSourcesFromArray(String[] sources) {
		for (String source : sources) {
			this.sources.add(source);
		}
	}

	/**
	 * @return the roSpecID
	 */
	public int getRoSpecID() {
		return roSpecID;
	}

	/**
	 * @param roSpecID
	 *            the roSpecID to set
	 */
	public void setRoSpecID(int roSpecID) {
		this.roSpecID = roSpecID;
	}
}
