package org.fosstrak.ale.server.readers.tagsys;



import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.fosstrak.ale.exception.ImplementationException;
import org.fosstrak.ale.server.Tag;
import org.fosstrak.ale.server.readers.BaseReader;
import org.fosstrak.ale.server.readers.IdentifyThread;
import org.fosstrak.ale.xsd.ale.epcglobal.LRSpec;
import org.fosstrak.hal.HardwareException;
import org.fosstrak.hal.Observation;

import com.tagsys.protocol.stxe.TSTXe;
import com.tagsys.protocol.stxe.TSTXeErrorException;
import com.tagsys.protocol.stxe.TSTXeSerialRXTX;
import com.tagsys.reader.mediop0xx.TMedioP0xx;
import com.tagsys.reader.mediop0xx.TMedioP0xxFeatures;
import com.tagsys.reader.mediop0xx.TMedioP0xxISO15693Slot;
import com.tagsys.reader.mediop0xx.TMedioP0xxVersion;


import java.lang.Object;

/**
 * The Tagsys Medio Reader TR-P101 is an industrial ISO 15693 RFID reader operating at 13.56MHz frequency.
 * 
 */

public class Trp101Adaptor extends BaseReader {
	
	public Trp101Adaptor()
	{
		super();
		readerName = "Tagsys Reader";
	}
	
	/**
	 * Reader device (TagsysMedio library)
	 */
	private TMedioP0xx readerDevice;

	/**
	 * Reader protocol (TagsysMedio library)
	 */
	private TSTXe readerProtocol;
	
	
	/** logger. */
	private static final Logger LOG = Logger.getLogger(Trp101Adaptor.class);
	
	/** to get all the tags we need a polling thread.  */
	private IdentifyThread identifyThread = null;
	
	
	/** the interval in which shall be read from the reader. */
	private int readTimeInterval = -1;
	
	/** the physical sources where tags are read in a String (eg shelf1, shelf2). */
	private String sourcesString = null;
	
	/** default readTimeInterval. */
	public static final int DEFAULT_READTIME_INTERVALL = 2000;
	
	private static final String PROPERTY_PREFIX = "tagsys.mediolx00.settings";

	public static final int MEDIA_SERIAL = 0;


	/** the physical sources. */
	private Set<String> sources = new HashSet<String>();
	
	
	int iTimer;
	
	int iMediaType;

	String strSerialName;

	int iSerialSpeed;

	String strIPAddress;

	int iTCPPort;

	int iPower;

	int iChannel;

	int iTagType;

	boolean boCont;

	boolean boSort;
	 
	
	Trp101Adaptor adaptor;

	private int iDefineTypeResult;

	private byte[] byPage;

	private byte[][] byPages;

	private byte[][] byChips;


	private TMedioP0xxFeatures Features;

	private TMedioP0xxVersion Version;

	private int iNbElements=100;

	private TMedioP0xxISO15693Slot[] mySlots = TMedioP0xxISO15693Slot.createArray(iNbElements);

	private int iMaxScans;

	private String readTag;

	private byte[] byID;

	private int chipType;

	
	public void initialize(String name, LRSpec spec) throws ImplementationException {
		try {
			super.initialize(name, spec);
		} catch (ImplementationException ie) {
			LOG.error("error in initialize of superclass");
			throw ie;
		}
		
		try {
			// extract from LRSpec how to connect to the reader
			extractConnectionSettings();
		} catch (ImplementationException ie) {
			LOG.error("could not extract connection settings from LRSpec", ie);
			throw ie;
		}
		
		// connect to the reader
		connectReader();
	}
	
	@Override
	public void addTag(Tag tag) {
		setChanged();
		tag.addTrace(getName());
		//LOG.debug("calling observers");
		notifyObservers(tag);
	}

	@Override
	public void addTags(List<Tag> tags) {
		setChanged();
		for (Tag tag : tags) {
			tag.addTrace(getName());
		}
		
		notifyObservers(tags);	
	}

	@Override
	public void start() {
		if (!isConnected()) {
			try {
				connectReader();
			} catch (ImplementationException e) {
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

	@Override
	public void stop() {
		LOG.debug("identifyThread suspend to identify");
		identifyThread.suspendIdentify();
		setStopped();
		
	}

	@Override
	public void connectReader() throws ImplementationException {
		if (isConnected()) {
			return;
		}
		
			adaptor = new Trp101Adaptor();
			try{
				connectProcess();
			}
			catch (Exception e) {
			setDisconnected();
			}
			
			try {
				chipType = readerDevice.detectChip(false, byID);
			} catch (TSTXeErrorException e1) {
				LOG.error("Error while detecting chip : ", e1);
			}
			
			
			LOG.debug("the detected chip :" + chipType);
			if (chipType != 0)
				readProcess();
		
			setConnected();
		
	}

	@Override
	public void disconnectReader() throws ImplementationException {
		
		try {
			if (readerDevice != null) {
			   readerDevice.setOutput(false);                //stop current reading process
				readerDevice.setRf(false);  //turning all reader channels off
			}
			if (readerProtocol != null)
				readerProtocol.close();
		} catch (TSTXeErrorException e) {
			e.printStackTrace();
		}
		
		
		if (identifyThread != null) {
			identifyThread.stopIdentify();
			identifyThread = null;
		}
		setDisconnected();
		setStopped();
		
	}

	@Override
	public void update(LRSpec spec) throws ImplementationException {
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

	@Override
	public Observation[] identify(String[] readPointNames)
			throws HardwareException {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void connectProcess () {
		
		// Create corresponding protocol object and attach it to reader object
		try {
			
			// Close previous media if existing
			if (readerProtocol != null){
				readerProtocol.close();
				LOG.debug("reader protocol is not null");
				}
			
			LOG.debug("serial port = " + this.strSerialName);
			readerProtocol = new TSTXeSerialRXTX(this.strSerialName, this.iSerialSpeed);
			

			readerProtocol.setMode(TSTXe.FAST_MODE);

			if (readerDevice == null) {
				readerDevice = new TMedioP0xx(readerProtocol);
			} else {
				readerDevice.changeProtocol(readerProtocol);
			}

			// Reset reader
			readerDevice.resetReader();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}

			readerDevice.setRf(true);  
			readerDevice.setBaudrate(this.iSerialSpeed);
			readerDevice.setDownlinkISO15693(TMedioP0xx.ISO15693_FAST);
			Features = readerDevice.getReaderFeatures();
			Version = readerDevice.getReaderVersion();
			LOG.debug("Reader Features : " + Features);
			LOG.debug("Reader version : " + Version);
			//readerDevice.buildRequestFlagsISO15693(true, false, false, false, false);								
												
			// Set ISO15693 uplink to ASK
			readerDevice.setUplinkISO15693(TMedioP0xx.ISO15693_ASK, TMedioP0xx.ISO15693_OOK);

			System.out.println("--->" + readerDevice.getReaderVersion().toString());
			

	       // Determines the tag type to be read
			switch (this.iTagType) {
			
				case TMedioP0xx.TAG_C210:
					readerDevice.readC210(byPage);
					break;
				case TMedioP0xx.TAG_C240:
					readerDevice.readPagesC240(false, false, 0, 0, 1, byPages[0]);
					break;
				case TMedioP0xx.TAG_C270:
					readerDevice.unselectedReadC270(false, TMedioP0xx.C270_HASH_0, TMedioP0xx.C270_DONT_USE_FAMILY_CODE, TMedioP0xx.C270_DONT_USE_APPLICATION_ID, TMedioP0xx.C270_1_SLOT, 0, 8, 2, 5, byChips);
					break;
				case TMedioP0xx.TAG_ISO15693:
					readerDevice.getDetectedChipType(iDefineTypeResult);
					break;
			}
			
		} catch (TSTXeErrorException e) {
			System.err.println("Error opening reader : ");
			LOG.error("Error opening reader : ", e);
			e.printStackTrace();
		}
	    
		
	}
		
		protected void readProcess() {
		LOG.debug("Reading Process...");
		boolean boContinue;
		try {
			iMaxScans = readerDevice.autoInventoryISO15693(true, TMedioP0xx.ISO15693_NO_AFI, 5, mySlots);
			} 
		catch (TSTXeErrorException e) {
			e.printStackTrace();
		}
		LOG.debug(" actual number of chips = " +iMaxScans);
		
		while (iMaxScans-- != 0) {
			try {
				readerDevice.autoInventoryISO15693(true, TMedioP0xx.ISO15693_NO_AFI, 2, mySlots);
				boContinue = true;
				
			} catch (TSTXeErrorException e) {
				System.err.println(e.getLocalizedMessage());
				return;
			}
		
			if (!boContinue) {
				break;
			}
		}
		
		
		int i=0;
		for (i=0;i<iNbElements;i++)
		{	
			LOG.debug("the read chip slot :" + mySlots[i].getDataAsString());
			if (mySlots[i].getDataAsString().equalsIgnoreCase("Not set"))
				LOG.debug("empty");
			else
			{
				readTag = mySlots[i].getDataAsString();
				LOG.debug("read chip:" +readTag);
			}	
		}
		
	
		LOG.debug("catched Tag : " +readTag);
		LOG.debug("setup identifyThread on RPAdaptor " + getName());
		// setup the polling thread
		identifyThread = new IdentifyThread(this);
		identifyThread.setPollingFrequency(readTimeInterval);
		identifyThread.start();
	
		// suspend the polling thread to the beginning
		identifyThread.suspendIdentify();		
	
		String tagIDasPureId = readTag;
		tagIDasPureId = convertISO15693toPureURI(readTag);
		
		
		Tag tag = new Tag(this.readerName, tagIDToByte(readTag), tagIDasPureId, System.currentTimeMillis());

		synchronized(adaptor)
		{
			adaptor.addTag(tag);
		}
		
	}
	

	private String convertISO15693toPureURI(String tagId) {
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

	private boolean extractConnectionSettings() throws ImplementationException {
		LOG.debug("extracting settings...");
		
		this.iTimer = Integer.parseInt(logicalReaderProperties.get(PROPERTY_PREFIX + ".iTimer"));
		
		String iMediaType_ = logicalReaderProperties.get(PROPERTY_PREFIX	+ ".iMediaType");
		String interval = logicalReaderProperties.get("ReadTimeInterval");
		
		setReadTimeInterval(-1);
		try {
			setReadTimeInterval(Integer.parseInt(interval));
		} catch (Exception ne) {
			LOG.error("could not extract readTimeIntervall from LRPropery");
			throw new ImplementationException("could not extract notificationPoint from LRPropery");
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
		
		if (iMediaType_ != null) {
			if (iMediaType_.equals("serial")) {
				this.iMediaType = MEDIA_SERIAL;
				this.strSerialName = logicalReaderProperties.get(PROPERTY_PREFIX	+ ".strSerialName");
				this.iSerialSpeed = Integer.parseInt(logicalReaderProperties.get(PROPERTY_PREFIX + ".iSerialSpeed"));
			} 
		else {
				disconnect = true;
				return disconnect;
			}

			this.iChannel = Integer.parseInt(logicalReaderProperties.get(PROPERTY_PREFIX + ".iChannel"));
			
			// multiple of 500 mW
			this.iPower = Integer.parseInt(logicalReaderProperties.get(PROPERTY_PREFIX + ".iPower"));
			
			
			// set the new sources
			sourcesString = nsources;
			setSourcesFromArray(sourcesString.split("[,]"));
			
			// Set tag type
			// values are TAG_C210, TAG_C240, TAG_C270,
			// TAG_ISO15693
			String strTagType = logicalReaderProperties.get(PROPERTY_PREFIX+ ".strTagType");
			LOG.debug("strTagType = " + strTagType);
			if(strTagType!=null){
				if (strTagType.equals("TAG_ISO15693")) {
					this.iTagType = TMedioP0xx.TAG_ISO15693;
				} else if (strTagType.equals("TAG_C210")) {
					this.iTagType = TMedioP0xx.TAG_C210;
				} else if (strTagType.equals("TAG_C240")) {
					this.iTagType = TMedioP0xx.TAG_C240;
				} else if (strTagType.equals("TAG_C270")) {
					this.iTagType = TMedioP0xx.TAG_C270;
				} else {
					// unknown tag type
					this.iTagType = -1;
					disconnect = true;
				}
			} else {
				// unknown tag type
				this.iTagType = -1;
				disconnect = true;
			}

			// Set continuous
			this.boCont = Boolean.parseBoolean(logicalReaderProperties.get(PROPERTY_PREFIX + ".boCont"));

			// Set sorting
			this.boSort = Boolean.parseBoolean(logicalReaderProperties.get(PROPERTY_PREFIX + ".boSort"));

		} else {
			disconnect = true;
			return disconnect;
		}
		
		return disconnect;
			
		
	}

	protected Object clone() {

		Trp101Adaptor clonedSettings = new Trp101Adaptor();
		clonedSettings.boCont = this.boCont;
		clonedSettings.boSort = this.boSort;
		clonedSettings.iChannel = this.iChannel;
		clonedSettings.iMediaType = this.iMediaType;
		clonedSettings.iPower = this.iPower;
		clonedSettings.iSerialSpeed = this.iSerialSpeed;
		clonedSettings.iTagType = this.iTagType;
		clonedSettings.iTCPPort = this.iTCPPort;
		clonedSettings.iTimer = this.iTimer;
		clonedSettings.strIPAddress = this.strIPAddress;
		clonedSettings.strSerialName = this.strSerialName;
		LOG.debug("Serial port this" + this.strSerialName);
		return clonedSettings;
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
	 * sets the readTimeInterval.
	 * @param readTimeInterval the readTimeInterval
	 */
	private void setReadTimeInterval(int readTimeInterval) {
		this.readTimeInterval = readTimeInterval;
	}

	
	private void setSourcesFromArray(String[] sources) {
		for (String source : sources) {
			this.sources.add(source);
		}
	}
	
}
