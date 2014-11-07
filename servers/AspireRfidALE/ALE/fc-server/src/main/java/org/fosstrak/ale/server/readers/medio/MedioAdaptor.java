package org.fosstrak.ale.server.readers.medio;

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
import com.tagsys.reader.mediolx00.TMedioLx00;
import com.tagsys.reader.mediolx00.TMedioLx00BufferEntry;
import com.tagsys.reader.mediolx00.TMedioLx00Features;
import com.tagsys.reader.mediolx00.TMedioLx00Version;

/**
 * 
 * This is an adaptor for Medio L100/L200 rfid readers
 *         
 *
 */
public class MedioAdaptor extends BaseReader {

	public MedioAdaptor()
	{
		super();
		readerName = "Medio Reader";
	}
	
	/**
	 * Reader device (TagsysMedio library)
	 */
	private TMedioLx00 readerDevice;
	
	/**
	 * Reader protocol (TagsysMedio library)
	 */
	private TSTXe readerProtocol;
	
	
	/** logger. */
	private static final Logger LOG = Logger.getLogger(MedioAdaptor.class);
	
	/** to get all the tags we need a polling thread.  */
	private IdentifyThread identifyThread = null;
	
	
	/** the interval in which shall be read from the reader. */
	private int readTimeInterval = -1;
	
	/** the physical sources where tags are read in a String (eg shelf1, shelf2). */
	private String sourcesString = null;
	
	/** default readTimeInterval. */
	public static final int DEFAULT_READTIME_INTERVALL = 500;
	
	public static final int DEFAULT_READTIME_INTERVALL1 = 250;
	
	private static final String PROPERTY_PREFIX = "tagsys.mediolx00.settings";

	public static final int MEDIA_SERIAL = 0;
	
	/** the physical sources. */
	private Set<String> sources = new HashSet<String>();

	private MedioAdaptor adaptor;
	
	
	String strSerialName;
	
	int iSerialSpeed;

	int iChannel;
	
	int iTagType;

	private TMedioLx00Features Features;

	private TMedioLx00Version Version;

	private int iHashvalue;

	private int iWakeUpPeriod;

	private int READ_PARAM_NULL;
	
	int iTimer;
	
	int iMediaType;
	
	int iPower;
	
	boolean boCont;
	
	boolean boSort;

	private int iNbElements=100;

	private byte[] byUID;

	private TMedioLx00BufferEntry myEntry;

	private int ISO15693_NONE;

	private String readTag;

	private int iMaskLength;

	private byte[] byMask;

	TMedioLx00BufferEntry[] myEntries = TMedioLx00BufferEntry.createArray(iNbElements);

	private int iMaxScans;
	
	int RF_CH1;
	
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
		
			adaptor = new MedioAdaptor();
			try{
				connectProcess();
			}
			catch (Exception e) {
			setDisconnected();
			}
			
			try {
				readerDevice.inventoryISO15693(false, false, READ_PARAM_NULL, true, iMaskLength, byMask, myEntries);
			} catch (TSTXeErrorException e1) {
				LOG.error("Cannot retrieve information about tag capabilities ", e1);
			}
			
			
				if (myEntries[0].getDataAsString().equalsIgnoreCase("N"))   // N="Not set" what Buffer gets when no tags are present
						LOG.debug("No tags to read!");
				else
					readProcess();
			
							
			
			setConnected();
		
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
			LOG.debug("serial connection established ");


			if (readerDevice == null) {
				readerDevice = new TMedioLx00(readerProtocol);
			} else {
				readerDevice.changeProtocol(readerProtocol);
			}
			
			switch (this.iChannel) {
			
			case 1:
				readerDevice.setRf(TMedioLx00.RF_CH1);
			break;
			case 2:
				readerDevice.setRf(TMedioLx00.RF_CH2);
			break;
			case 3:
				readerDevice.setRf(TMedioLx00.RF_CH3);
			break;
			case 4:
				readerDevice.setRf(TMedioLx00.RF_CH4);
			break;
			default:
				System.err.println("Invalid Channel number!");
			break;
			}
			
			
			LOG.debug("selected Channel : " + iChannel);	
			
			int iGranularity=TMedioLx00.GRANULARITY_250MW;
			
			readerDevice.setPower(iGranularity, 1);
			
			Features = readerDevice.getReaderFeatures();
			Version = readerDevice.getReaderVersion();
			LOG.debug("Reader Features : " + Features);
			LOG.debug("Reader version : " + Version);							
												
			
			System.out.println("--->" + readerDevice.getReaderVersion().toString());
			

	       // Determines the tag type to be read
			switch (this.iTagType) {
			
				case TMedioLx00BufferEntry.TAG_C210:
					readerDevice.startReadC210(DEFAULT_READTIME_INTERVALL);
					break;
				case TMedioLx00BufferEntry.TAG_C220:
					readerDevice.startReadC220(DEFAULT_READTIME_INTERVALL1);
					break;
				case TMedioLx00BufferEntry.TAG_C240:
					readerDevice.startReadC240(DEFAULT_READTIME_INTERVALL);
					break;
				case TMedioLx00BufferEntry.TAG_C270:
					readerDevice.startSelectionC270(iHashvalue, true);
					break;
				case TMedioLx00BufferEntry.TAG_C320:
					readerDevice.startReadNSNTC320(false, true, iWakeUpPeriod);
					break;
				case TMedioLx00BufferEntry.TAG_EPC:
					readerDevice.startReadEPC(false, true, iHashvalue);
					break;
				case TMedioLx00BufferEntry.TAG_ISO15693:
					readerDevice.inventoryISO15693(false, false, READ_PARAM_NULL, true, iMaskLength, byMask, myEntries);
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
			iMaxScans = readerDevice.inventoryISO15693(false, false, READ_PARAM_NULL, true, iMaskLength, byMask, myEntries);

			} 
		catch (TSTXeErrorException e) {
			e.printStackTrace();
		}
		
		LOG.debug(" actual number of tags = " +iMaxScans);
		
		int j=iMaxScans;
		for (j=iMaxScans;j<0;j--)
		{
		//while (iMaxScans-- != 0) {
			try {
				readerDevice.startReadISO15693(false, READ_PARAM_NULL);
				readerDevice.getSystemInfoISO15693(false, ISO15693_NONE, byUID, myEntry);
				boContinue = true;
				
			} catch (TSTXeErrorException e) {
				System.err.println(e.getLocalizedMessage());
				return;
			}
		
			if (!boContinue) {
			}
		}
		
	
			int i=0;
			for (i=0;i<iNbElements;i++)
			{	
					readTag = myEntries[i].getDataAsString();
					LOG.debug("read chip:" +readTag);
			}	
				
		
		
	
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
	
	@Override
	public void disconnectReader() throws ImplementationException {
		try {
			if (readerDevice != null) {
			   readerDevice.stopRead();              //stop current reading process
				readerDevice.setPower(0, 0); 		 //turning all reader channels off
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
					this.iTagType = TMedioLx00BufferEntry.TAG_ISO15693;
				} else if (strTagType.equals("TAG_C210")) {
					this.iTagType = TMedioLx00BufferEntry.TAG_C210;
				} else if (strTagType.equals("TAG_C220")) {
					this.iTagType = TMedioLx00BufferEntry.TAG_C220;
				} else if (strTagType.equals("TAG_C240")) {
					this.iTagType = TMedioLx00BufferEntry.TAG_C240;
				} else if (strTagType.equals("TAG_C270")) {
					this.iTagType = TMedioLx00BufferEntry.TAG_C270;
				} else if (strTagType.equals("TAG_C320")) {
					this.iTagType = TMedioLx00BufferEntry.TAG_C320;
				} else if (strTagType.equals("TAG_EPC")) {
					this.iTagType = TMedioLx00BufferEntry.TAG_EPC;
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
	private void setReadTimeInterval(int readTimeInterval) {
		this.readTimeInterval = readTimeInterval;
	}
	
	private void setSourcesFromArray(String[] sources) {
		for (String source : sources) {
			this.sources.add(source);
		}
	}
}

