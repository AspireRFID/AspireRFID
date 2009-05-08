/**
 * Copyright (C) 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */

package org.ow2.aspirerfid.ale.server.readers.llrp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.IOException;
import java.util.Arrays;

import org.ow2.aspirerfid.ale.server.EpcUriFactory;
import org.ow2.aspirerfid.ale.server.Tag;
import org.accada.ale.util.HexUtil;
import org.accada.ale.wsdl.ale.epcglobal.ImplementationException;
import org.accada.ale.wsdl.ale.epcglobal.ImplementationExceptionResponse;
import org.accada.reader.rprm.core.EventType;
import org.accada.reader.rprm.core.FieldName;
import org.accada.reader.rprm.core.msg.notification.Notification;
import org.accada.reader.rprm.core.msg.reply.ReadReportType;
import org.accada.reader.rprm.core.msg.reply.TagEventType;
import org.accada.reader.rprm.core.msg.reply.TagType;
import org.accada.reader.rprm.core.msg.reply.ReadReportType.SourceReport;
import org.accada.reader.rp.proxy.DataSelector;
import org.accada.reader.rp.proxy.NotificationChannel;
import org.accada.reader.rp.proxy.NotificationChannelEndPoint;
import org.accada.reader.rp.proxy.NotificationChannelListener;
import org.accada.reader.rp.proxy.RPProxyException;
import org.accada.reader.rp.proxy.ReadReport;
import org.accada.reader.rp.proxy.ReaderDevice;
import org.accada.reader.rp.proxy.Source;
import org.accada.reader.rp.proxy.Trigger;
import org.accada.reader.rp.proxy.factories.DataSelectorFactory;
import org.accada.reader.rp.proxy.factories.NotificationChannelFactory;
import org.accada.reader.rp.proxy.factories.ReaderDeviceFactory;
import org.accada.reader.rp.proxy.factories.TriggerFactory;
import org.accada.reader.rp.proxy.msg.Handshake;
import org.apache.log4j.Logger;
import org.llrp.ltk.exceptions.InvalidLLRPMessageException;
import org.llrp.ltk.generated.enumerations.AISpecStopTriggerType;
import org.llrp.ltk.generated.enumerations.AccessReportTriggerType;
import org.llrp.ltk.generated.enumerations.AirProtocols;
import org.llrp.ltk.generated.enumerations.GetReaderCapabilitiesRequestedData;
import org.llrp.ltk.generated.enumerations.KeepaliveTriggerType;
import org.llrp.ltk.generated.enumerations.NotificationEventType;
import org.llrp.ltk.generated.enumerations.ROReportTriggerType;
import org.llrp.ltk.generated.enumerations.ROSpecStartTriggerType;
import org.llrp.ltk.generated.enumerations.ROSpecState;
import org.llrp.ltk.generated.enumerations.ROSpecStopTriggerType;
import org.llrp.ltk.generated.messages.ADD_ROSPEC;
import org.llrp.ltk.generated.messages.CLOSE_CONNECTION;
import org.llrp.ltk.generated.messages.DELETE_ROSPEC;
import org.llrp.ltk.generated.messages.DISABLE_ROSPEC;
import org.llrp.ltk.generated.messages.ENABLE_ROSPEC;
import org.llrp.ltk.generated.messages.GET_READER_CAPABILITIES;
import org.llrp.ltk.generated.messages.LLRPMessageFactory;
import org.llrp.ltk.generated.messages.SET_READER_CONFIG;
import org.llrp.ltk.generated.messages.START_ROSPEC;
import org.llrp.ltk.generated.parameters.AISpec;
import org.llrp.ltk.generated.parameters.AISpecStopTrigger;
import org.llrp.ltk.generated.parameters.AccessReportSpec;
import org.llrp.ltk.generated.parameters.C1G2EPCMemorySelector;
import org.llrp.ltk.generated.parameters.EventNotificationState;
import org.llrp.ltk.generated.parameters.EventsAndReports;
import org.llrp.ltk.generated.parameters.InventoryParameterSpec;
import org.llrp.ltk.generated.parameters.KeepaliveSpec;
import org.llrp.ltk.generated.parameters.ROBoundarySpec;
import org.llrp.ltk.generated.parameters.ROReportSpec;
import org.llrp.ltk.generated.parameters.ROSpec;
import org.llrp.ltk.generated.parameters.ROSpecStartTrigger;
import org.llrp.ltk.generated.parameters.ROSpecStopTrigger;
import org.llrp.ltk.generated.parameters.ReaderEventNotificationSpec;
import org.llrp.ltk.generated.parameters.TagReportContentSelector;
import org.llrp.ltk.types.Bit;
import org.llrp.ltk.types.LLRPInteger;
import org.llrp.ltk.types.LLRPMessage;
import org.llrp.ltk.types.UnsignedInteger;
import org.llrp.ltk.types.UnsignedShort;
import org.llrp.ltk.types.UnsignedShortArray;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.filter.*;
import org.jdom.Text;
import org.jdom.filter.ContentFilter;

/**
 * This class is the connector between the reader protocol and the ALE. It
 * creates the necessary objects on the reader device to get all required data.
 * 
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 * 
 */
public class LLRPInputGenerator implements NotificationChannelListener {

	/** logger. */
	private static final Logger LOG = Logger.getLogger(LLRPInputGenerator.class);

	/** indicates if this input generator is ready or not. */
	private boolean isReady = false;
	/**
	 * indicates if the initialization of this input generator is failed or not.
	 */
	private boolean isFailed = false;

	/** adaptor to which the input generator belongs. */
	private LLRPAdaptor adaptor = null;

	// ================================================

	/**
	 * The socket for the connection to the LLRP Reader
	 */
	private Socket connection;

	/**
	 * The output stream to the reader
	 */
	private DataOutputStream out;

	/**
	 * The ID of the ROSpec that is used
	 */
	private int ROSPEC_ID = 1;

	/**
	 * The incoming data stream from the LLRP reader connection
	 */
	private DataInputStream inStream = null;

	/**
	 * A queue to store incoming LLRP Messages
	 */
	private LinkedBlockingQueue<LLRPMessage> queue = null;

	/**
	 * Filter out everything by default
	 */
	private ContentFilter filter = new ContentFilter(false);

	/**
	 * where to temporarly store the hexadecimal EPC Tag code
	 */
	String hexEPC = "";

	/**
	 * where to store the Reader Sources from where we will read the tags
	 */
	short[] readerSource = null;

	/**
	 * The Duration that the RO Spec will be active and then send its report
	 */
	int roSpecDuration = -1;// default value

	// /**
	// * where to temporarly store the Antenna ID
	// */
	// String antennaID = "";

	// ================================================

	/**
	 * Constructor sets parameter and starts initializer.
	 * 
	 * @param adaptor
	 *            the adaptor holding this inputGenerator
	 * @throws ImplementationException
	 *             if an implementation exception occurs
	 */
	public LLRPInputGenerator(LLRPAdaptor adaptor) throws ImplementationExceptionResponse {
		this.adaptor = adaptor;

		// start Initializer Thread
		try {
			new Initializer(this).start();
		}
		catch (Exception e) {
			throw new ImplementationExceptionResponse(e.getMessage());
		}

	}

	/**
	 * This method is invoked if a notification is arrived at the notification
	 * channel end-point.
	 * 
	 * @param notification
	 *            from the reader device
	 */
	public void dataReceived(Notification notification) {
		LOG.debug("Notification received but not processed - polling mode is used");
	}

	/**
	 * This method indicates if the input generator is ready or not.
	 * 
	 * @return true if the input generator is ready and false otherwise
	 */
	public boolean isReady() {

		return isReady;

	}

	/**
	 * This method indicates if the initialization of the input generator is
	 * failed or not.
	 * 
	 * @return true if the initialization is failed and false otherwise
	 */
	public boolean isFailed() {

		return isFailed;

	}

	/**
	 * This method removes all objects on the reader device which are created by
	 * this input generator.
	 */
	public void remove() {

		LOG.debug("Try to remove InputGenerator.");

		// Create a DISABLE_ROSPEC message and send it to the reader
		DISABLE_ROSPEC disableROSpec = new DISABLE_ROSPEC();
		disableROSpec.setROSpecID(new UnsignedInteger(ROSPEC_ID));
		write(disableROSpec, "DISABLE_ROSPEC");
		pause(250);

		// Create a DELTE_ROSPEC message and send it to the reader
		DELETE_ROSPEC deleteROSpec = new DELETE_ROSPEC();
		deleteROSpec.setROSpecID(new UnsignedInteger(ROSPEC_ID));
		write(deleteROSpec, "DELETE_ROSPEC");
		pause(150);

		// wait for one second before closing the connection
		pause(250);

		// Create a CLOSE_CONNECTION message and send it to the reader
		CLOSE_CONNECTION cc = new CLOSE_CONNECTION();
		write(cc, "CloseConnection");

		LOG.debug("InputGenerator removed");

	}

	public void process(Element element) {
		List children = element.getContent(filter);
		Iterator iterator = children.iterator();
		while (iterator.hasNext()) {
			Object o = iterator.next();
			if (o instanceof Element) {
				Element child = (Element) o;
				process(child);
			}
			else { // Due to filter, the only other possibility is Text
				Text text = (Text) o;

				if (element.getParentElement().getName().equals("EPC_96")) {
					hexEPC = text.getText();

				}
			}
		}
	}

	/**
	 * Send a llrp message to the reader
	 * 
	 * @param msg
	 *            Message to be send
	 * @param message
	 *            Description for output purposes
	 */
	private void write(LLRPMessage msg, String message) {
		try {
			LOG.info(" Sending message: \n" + msg.toXMLString());
			out.write(msg.encodeBinary());
		}
		catch (IOException e) {
			LOG.error("Couldn't send Command ", e);
		}
		catch (InvalidLLRPMessageException e) {
			LOG.error("Couldn't send Command", e);
		}
	}

	/**
	 * This method causes the calling thread to sleep for a specified number of
	 * milliseconds
	 * 
	 * @param ms
	 */
	private void pause(long ms) {
		try {
			Thread.sleep(ms);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void startRoSpec() {
		// Create a START_ROSPEC message and send it to the reader
		START_ROSPEC startROSpec = new START_ROSPEC();
		startROSpec.setROSpecID(new UnsignedInteger(ROSPEC_ID));
		write(startROSpec, "START_ROSPEC");
	}

	/**
	 * polls the rp-proxy for all tags available.
	 * 
	 * @throws RPProxyException
	 *             when the polling failed
	 */
	public void poll() throws RPProxyException {

		startRoSpec();
		// wait till the reader collects the data + 90 and then continue to read
		// them
		// pause(roSpecDuration + 150);

		if (readerSource.length != 0) {

			List<Tag> tagsToNotify = new LinkedList<Tag>();

			// ========================================
			if (connection.isConnected()) {
				LLRPMessage message = null;
				try {
					message = read();
					while (!message.getName().equals("RO_ACCESS_REPORT")) {
						LOG.info("Read the message again not a \"RO_ACCESS_REPORT\" ");
						message = read();
					}
					if (message != null) {
						queue.put(message);
						if (message.getName().equals("RO_ACCESS_REPORT")) {
							LOG.info("Received Message: \n" + message.toXMLString());

							// Allow elements through the filter
							filter.setElementVisible(true);
							// Allow text nodes through the filter
							filter.setTextVisible(true);

							Element element = (Element) message.encodeXML().getContent(filter).get(0);
							List children = element.getContent(filter);
							Iterator iterator = children.iterator();
							while (iterator.hasNext()) {
								Object o = iterator.next();
								if (o instanceof Element) {
									Element child = (Element) o;
									process(child);
									tagsToNotify.add(new Tag(adaptor.getName(), HexUtil.hexStringToByteArray(hexEPC), hexEPC, System
											.currentTimeMillis()));
									hexEPC = "";
									// antennaID = "";
								}
							}
						}
					}
					else {
						LOG.info("closing socket");
						connection.close();
					}

				}
				catch (IOException e) {
					LOG.error("Error while reading message", e);
				}
				catch (InvalidLLRPMessageException e) {
					LOG.error("Error while reading message", e);
				}
				catch (InterruptedException e) {
					LOG.error("Error while reading message", e);
				}

			}
			// =========================================

			if (tagsToNotify.size() > 0) {
				adaptor.addTags(tagsToNotify);
			}
		}
		else {
			LOG.debug("sources null");
		}
	}

	/**
	 * Read everything from the stream until the socket is closed
	 * 
	 * @throws InvalidLLRPMessageException
	 */
	public LLRPMessage read() throws IOException, InvalidLLRPMessageException {
		LLRPMessage m = null;
		// The message header
		byte[] first = new byte[6];

		// the complete message
		byte[] msg;

		// Read in the message header. If -1 is read, there is no more
		// data available, so close the socket
		if (inStream.read(first, 0, 6) == -1) {
			return null;
		}
		int msgLength = 0;

		try {
			// calculate message length
			msgLength = calculateLLRPMessageLength(first);
		}
		catch (IllegalArgumentException e) {
			throw new IOException("Incorrect Message Length");
		}

		/*
		 * the rest of bytes of the message will be stored in here before they
		 * are put in the accumulator. If the message is short, all
		 * messageLength-6 bytes will be read in here at once. If it is long,
		 * the data might not be available on the socket all at once, so it make
		 * take a couple of iterations to read in all the bytes
		 */
		byte[] temp = new byte[msgLength - 6];

		// all the rest of the bytes will be put into the accumulator
		ArrayList<Byte> accumulator = new ArrayList<Byte>();

		// add the first six bytes to the accumulator so that it will
		// contain all the bytes at the end
		for (byte b : first) {
			accumulator.add(b);
		}

		// the number of bytes read on the last call to read()
		int numBytesRead = 0;

		// read from the input stream and put bytes into the accumulator
		// while there are still bytes left to read on the socket and
		// the entire message has not been read
		while (((msgLength - accumulator.size()) != 0) && numBytesRead != -1) {

			numBytesRead = inStream.read(temp, 0, msgLength - accumulator.size());

			for (int i = 0; i < numBytesRead; i++) {
				accumulator.add(temp[i]);
			}
		}

		if ((msgLength - accumulator.size()) != 0) {
			throw new IOException("Error: Discrepency between message size" + " in header and actual number of bytes read");
		}

		msg = new byte[msgLength];

		// copy all bytes in the accumulator to the msg byte array
		for (int i = 0; i < accumulator.size(); i++) {
			msg[i] = accumulator.get(i);
		}

		// turn the byte array into an LLRP Message Object
		m = LLRPMessageFactory.createLLRPMessage(msg);

		return m;

	}

	/**
	 * Send in the first 6 bytes of an LLRP Message
	 * 
	 * @param bytes
	 * @return
	 */
	private int calculateLLRPMessageLength(byte[] bytes) throws IllegalArgumentException {
		long msgLength = 0;
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		int num4 = 0;

		num1 = ((unsignedByteToInt(bytes[2])));
		num1 = num1 << 32;
		if (num1 > 127) {
			throw new RuntimeException("Cannot construct a message greater than " + "2147483647 bytes (2^31 - 1), due to the fact that there are "
					+ "no unsigned ints in java");
		}

		num2 = ((unsignedByteToInt(bytes[3])));
		num2 = num2 << 16;

		num3 = ((unsignedByteToInt(bytes[4])));
		num3 = num3 << 8;

		num4 = (unsignedByteToInt(bytes[5]));

		msgLength = num1 + num2 + num3 + num4;

		if (msgLength < 0) {
			throw new IllegalArgumentException("LLRP message length is less than 0");
		}
		else {
			return (int) msgLength;
		}
	}

	/**
	 * From http://www.rgagnon.com/javadetails/java-0026.html
	 * 
	 * @param b
	 * @return
	 */
	private int unsignedByteToInt(byte b) {
		return (int) b & 0xFF;
	}

	/**
	 * Receive the next Message
	 * 
	 * @return returns the Message form the Queue and removes it. It blocks if
	 *         there is no Message.
	 */
	public LLRPMessage getNextMessage() {
		LLRPMessage m = null;
		try {
			m = queue.take();
		}
		catch (InterruptedException e) {
			// nothing
		}
		return m;
	}

	public void clearPreviusRoSpecs() {
		LOG.debug("Try to clearPreviusRoSpec with ROSPEC_ID: " + ROSPEC_ID);

		// Create a DISABLE_ROSPEC message and send it to the reader
		DISABLE_ROSPEC disableROSpec = new DISABLE_ROSPEC();
		disableROSpec.setROSpecID(new UnsignedInteger(ROSPEC_ID));
		write(disableROSpec, "DISABLE_ROSPEC");
		pause(250);

		// Create a DELTE_ROSPEC message and send it to the reader
		DELETE_ROSPEC deleteROSpec = new DELETE_ROSPEC();
		deleteROSpec.setROSpecID(new UnsignedInteger(ROSPEC_ID));
		write(deleteROSpec, "DELETE_ROSPEC");
		// pause(550);
	}

	/**
	 * This class initializes the input generator
	 * 
	 * @author nkef@ait.edu.gr
	 */
	private class Initializer extends Thread {

		/** this input generator. */
		private final LLRPInputGenerator generator;

		/**
		 * Constructor sets the input generator.
		 * 
		 * @param generator
		 *            the inputGenerator for this reader.
		 */
		public Initializer(LLRPInputGenerator generator) {

			this.generator = generator;

		}

		/**
		 * This method contains the main loop.
		 */
		public void run() {

			try {

				initialize();

			}
			catch (IOException e) {
				isFailed(e);
			}

		}

		/**
		 * This method creates objects on the reader device using the proxies.
		 * 
		 * @throws IOException
		 *             if a proxy operation fails
		 */
		private void initialize() throws IOException {

			LOG.debug("-----------------------------------------------------------");
			LOG.debug("Start initializing InputGenerator...");
			LOG.info("Try connecting to reader devices...");

			// roSpecDuration = 500;// default value

			try {
				ROSPEC_ID = adaptor.getRoSpecID();

				readerSource = null;
				readerSource = new short[adaptor.getSources().toArray().length];
				for (int k = 0; k < adaptor.getSources().toArray().length; k++) {
					readerSource[k] = new Integer((String) adaptor.getSources().toArray()[k]).shortValue();

				}

				// Try to establish a connection to the reader
				connection = new Socket(adaptor.getConnectionChannelAddress(), adaptor.getConnectionChannelPort());
				out = new DataOutputStream(connection.getOutputStream());

				roSpecDuration = adaptor.getReadTimeInterval();

			}
			catch (IOException e) {
				LOG.error("Error while Initializing LLRPInput Generator", e);
			}

			queue = new LinkedBlockingQueue<LLRPMessage>();
			try {
				inStream = new DataInputStream(connection.getInputStream());
			}
			catch (IOException e) {
				LOG.error("Cannot get input stream", e);
			}
			
			clearPreviusRoSpecs();
			generator.pause(800);

			// Create a SET_READER_CONFIG Message and send it to the reader
			SET_READER_CONFIG setReaderConfig = createSetReaderConfig();
			generator.write(setReaderConfig, "SET_READER_CONFIG");
			generator.pause(150);

			// CREATE an ADD_ROSPEC Message and send it to the reader
			ADD_ROSPEC addROSpec = new ADD_ROSPEC();
			addROSpec.setROSpec(createROSpec(roSpecDuration, readerSource));
			generator.write(addROSpec, "ADD_ROSPEC");
			generator.pause(150);

			// Create an ENABLE_ROSPEC message and send it to the reader
			ENABLE_ROSPEC enableROSpec = new ENABLE_ROSPEC();
			enableROSpec.setROSpecID(new UnsignedInteger(ROSPEC_ID));
			generator.write(enableROSpec, "ENABLE_ROSPEC");
			generator.pause(150);

			// set isReady
			isReady = true;

			// notify all when InputGenerator is ready
			synchronized (generator) {
				generator.notifyAll();
			}

			LOG.debug("InputGenerator initialized.");
			LOG.info("Connection to reader devices established.");
			LOG.debug("-----------------------------------------------------------");
		}

		/**
		 * This method is invoked if the initialization is failed. An error
		 * message will be displayed in log.
		 * 
		 * @param exception
		 *            exception thrown
		 */
		private void isFailed(Exception exception) {

			isFailed = true;
			LOG.error("InputGenerator '" + adaptor.getConnectionChannelAddress() + " at port " + adaptor.getConnectionChannelPort()
					+ "' initialization failed. (" + exception.getMessage() + ")");

			// notify all when InputGenerator is failed
			synchronized (generator) {
				generator.notifyAll();
			}

		}

		/**
		 * This method creates a ROSpec with null start and stop triggers
		 * 
		 * @return
		 */
		private ROSpec createROSpec(int roSpecDuration, short[] readerSource) {
			// create a new rospec
			ROSpec roSpec = new ROSpec();
			roSpec.setPriority(new LLRPInteger(0));
			roSpec.setCurrentState(new ROSpecState(ROSpecState.Disabled));
			roSpec.setROSpecID(new UnsignedInteger(ROSPEC_ID));

			// set up ROBoundary (start and stop triggers)
			ROBoundarySpec roBoundarySpec = new ROBoundarySpec();
			ROSpecStartTrigger startTrig = new ROSpecStartTrigger();
			startTrig.setROSpecStartTriggerType(new ROSpecStartTriggerType(ROSpecStartTriggerType.Null));
			roBoundarySpec.setROSpecStartTrigger(startTrig);
			ROSpecStopTrigger stopTrig = new ROSpecStopTrigger();
			stopTrig.setDurationTriggerValue(new UnsignedInteger(roSpecDuration));
			ROSpecStopTriggerType roSpecStopTriggerType = new ROSpecStopTriggerType(ROSpecStopTriggerType.Duration);
			stopTrig.setROSpecStopTriggerType(roSpecStopTriggerType);
			roBoundarySpec.setROSpecStopTrigger(stopTrig);
			roSpec.setROBoundarySpec(roBoundarySpec);

			// Add an AISpec
			AISpec aispec = new AISpec();

			// set AI Stop trigger to null
			AISpecStopTrigger aiStopTrigger = new AISpecStopTrigger();
			aiStopTrigger.setAISpecStopTriggerType(new AISpecStopTriggerType(AISpecStopTriggerType.Null));
			aiStopTrigger.setDurationTrigger(new UnsignedInteger(0));
			aispec.setAISpecStopTrigger(aiStopTrigger);
			UnsignedShortArray antennaIDs = new UnsignedShortArray();
			for (int m = 0; m < readerSource.length; m++) {
				antennaIDs.add(new UnsignedShort(readerSource[m]));
			}
			aispec.setAntennaIDs(antennaIDs);
			InventoryParameterSpec inventoryParam = new InventoryParameterSpec();
			inventoryParam.setProtocolID(new AirProtocols(AirProtocols.EPCGlobalClass1Gen2));
			inventoryParam.setInventoryParameterSpecID(new UnsignedShort(1));
			aispec.addToInventoryParameterSpecList(inventoryParam);
			roSpec.addToSpecParameterList(aispec);

			return roSpec;
		}

		/**
		 * This method creates a SET_READER_CONFIG method
		 * 
		 * @return
		 */
		private SET_READER_CONFIG createSetReaderConfig() {
			SET_READER_CONFIG setReaderConfig = new SET_READER_CONFIG();

			// Create a default RoReportSpec so that reports are sent at the end
			// of ROSpecs
			ROReportSpec roReportSpec = new ROReportSpec();
			roReportSpec.setN(new UnsignedShort(0));
			roReportSpec.setROReportTrigger(new ROReportTriggerType(ROReportTriggerType.Upon_N_Tags_Or_End_Of_ROSpec));
			TagReportContentSelector tagReportContentSelector = new TagReportContentSelector();
			tagReportContentSelector.setEnableAccessSpecID(new Bit(0));
			tagReportContentSelector.setEnableAntennaID(new Bit(0));
			tagReportContentSelector.setEnableChannelIndex(new Bit(0));
			tagReportContentSelector.setEnableFirstSeenTimestamp(new Bit(0));
			tagReportContentSelector.setEnableInventoryParameterSpecID(new Bit(0));
			tagReportContentSelector.setEnableLastSeenTimestamp(new Bit(0));
			tagReportContentSelector.setEnablePeakRSSI(new Bit(0));
			tagReportContentSelector.setEnableROSpecID(new Bit(0));
			tagReportContentSelector.setEnableSpecIndex(new Bit(0));
			tagReportContentSelector.setEnableTagSeenCount(new Bit(0));
			C1G2EPCMemorySelector epcMemSel = new C1G2EPCMemorySelector();
			epcMemSel.setEnableCRC(new Bit(0));
			epcMemSel.setEnablePCBits(new Bit(0));
			tagReportContentSelector.addToAirProtocolEPCMemorySelectorList(epcMemSel);
			roReportSpec.setTagReportContentSelector(tagReportContentSelector);
			setReaderConfig.setROReportSpec(roReportSpec);

			// Set default AccessReportSpec
			AccessReportSpec accessReportSpec = new AccessReportSpec();
			accessReportSpec.setAccessReportTrigger(new AccessReportTriggerType(AccessReportTriggerType.Whenever_ROReport_Is_Generated));
			setReaderConfig.setAccessReportSpec(accessReportSpec);

			// Set up reporting for AISpec events, ROSpec events, and GPI Events
			ReaderEventNotificationSpec eventNoteSpec = new ReaderEventNotificationSpec();
			EventNotificationState noteState = new EventNotificationState();
			noteState.setEventType(new NotificationEventType(NotificationEventType.AISpec_Event));
			noteState.setNotificationState(new Bit(1));
			eventNoteSpec.addToEventNotificationStateList(noteState);
			noteState = new EventNotificationState();
			noteState.setEventType(new NotificationEventType(NotificationEventType.ROSpec_Event));
			noteState.setNotificationState(new Bit(1));
			eventNoteSpec.addToEventNotificationStateList(noteState);
			noteState = new EventNotificationState();
			noteState.setEventType(new NotificationEventType(NotificationEventType.GPI_Event));
			noteState.setNotificationState(new Bit(1));
			eventNoteSpec.addToEventNotificationStateList(noteState);
			setReaderConfig.setReaderEventNotificationSpec(eventNoteSpec);
			setReaderConfig.setResetToFactoryDefault(new Bit(0));

			// keepaliveSpec
			KeepaliveSpec keepaliveSpec = new KeepaliveSpec();
			keepaliveSpec.setKeepaliveTriggerType(new KeepaliveTriggerType(KeepaliveTriggerType.Null));
			keepaliveSpec.setPeriodicTriggerValue(new UnsignedInteger(1000));
			setReaderConfig.setKeepaliveSpec(keepaliveSpec);

			// eventsAndReports
			EventsAndReports eventsAndReports = new EventsAndReports();
			eventsAndReports.setHoldEventsAndReportsUponReconnect(new Bit(0));
			setReaderConfig.setEventsAndReports(eventsAndReports);

			return setReaderConfig;

		}
	}
}
