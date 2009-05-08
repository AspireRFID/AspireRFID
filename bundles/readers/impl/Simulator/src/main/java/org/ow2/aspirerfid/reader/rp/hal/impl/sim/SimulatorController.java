/*
 * Copyright (c) 2008-2010, Aspire 
 * 
 * This file contains the source code of the Accada library by ETH Zurich (www.accada.org), 
 * licensed under the terms of the GNU Lesser General Public License version 2.1 in 2007 and 
 * modified for the needs of the Aspire project.
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
 *  
 */

package org.ow2.aspirerfid.reader.rp.hal.impl.sim;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.ow2.aspirerfid.reader.rp.hal.impl.sim.util.ResourceLocator;
import org.ow2.aspirerfid.reader.rp.impl.hal.AsynchronousIdentifyListener;
import org.ow2.aspirerfid.reader.rp.impl.hal.ControllerProperties;
import org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction;
import org.ow2.aspirerfid.reader.rp.impl.hal.HardwareException;
import org.ow2.aspirerfid.reader.rp.impl.hal.MemoryBankDescriptor;
import org.ow2.aspirerfid.reader.rp.impl.hal.MemoryDescriptor;
import org.ow2.aspirerfid.reader.rp.impl.hal.Observation;
import org.ow2.aspirerfid.reader.rp.impl.hal.ReadPointNotFoundException;
import org.ow2.aspirerfid.reader.rp.impl.hal.TagDescriptor;
import org.ow2.aspirerfid.reader.rp.impl.hal.Trigger;
import org.ow2.aspirerfid.reader.rp.impl.hal.UnsignedByteArray;
import org.ow2.aspirerfid.reader.rp.impl.hal.UnsupportedOperationException;
import org.ow2.aspirerfid.reader.rp.impl.hal.transponder.EPCTransponderModel;
import org.ow2.aspirerfid.reader.rp.impl.hal.transponder.IDType;
import org.ow2.aspirerfid.reader.rp.impl.hal.util.ByteBlock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;



/**
 * The SimulatorController is a HAL controller that enables the implementation
 * of different kinds of RFID reader simulators/emulators.
 * 
 * From the HAL point of view the SimulatorController supports 
 * the same functionality as hardware controllers. Additionally it 
 * provides methods that allow a Simulator (i.e. SimulatorEngine) to 
 * (virtually) add and remove RFID tags. 
 * 
 * @author Roland Schneider
 */

public class SimulatorController implements HardwareAbstraction {

	/**
	 * The logger.
	 */
	private static Log log = LogFactory.getLog(SimulatorController.class); 
	
	//------ Controller fields --------
	
   /**
    * The name of the HAL.
    */
   private String halName; 
   
   /**
    * The properties file.
    */
   private String defaultPropFile = "/props/SimulatorController_default.xml";
   private String propFile;
   
	/**
	 * The names of all available read points connected to this reader.
	 */
	private String[] readPointNames;
	
	private int nOfReadPoints = 0;
	

	//----- Properties ----------------
	
	/**
	 * props instance which encapulates all the methods responsible for parameter updates.
	 * 
	 * @uml.property name="props"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private ControllerProperties props = null;
	
	
	//----------- Simulator fields -----------------------
	
	/**
	 * Java type of simulator to be loaded.
	 */
	private String simType;
	
	/**
	 * Properties file for simulator to be loaded and transponder models.
	 */
	private String simTypePropFile;
   private String epcTransponderModelsConfig;

	/**
	 * The Simulator.
	 * 
	 * @uml.property name="simulator"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	private SimulatorEngine simulator;

	
	/**
	 * This <code>HashMap</code> has an entry for each read point (antenna).
	 * Entry: Key: readPoint (String)  Value: readpoint/antenna content (HashSet)
	 */
	private HashMap reader;
	
	/**
	 * Contains the names of all read points that are ready.
	 */
	private Vector<String> readyReadPoints;
	
	/**
	 * Contains the names of all read points whose next identify-operation will
	 * fail.
	 */
	public Vector<String> identifyError = new Vector<String>();
	
	/**
	 * Contains the names of all read points whose next read-operation will
	 * fail.
	 */
	public Vector<String> readError = new Vector<String>();
	
	/**
	 * Contains the names of all read points whose next write-operation will
	 * fail.
	 */
	public Vector<String> writeError = new Vector<String>();
	
	/**
	 * Contains the names of all read points whose next kill-operation will
	 * fail.
	 */
	public Vector<String> killError = new Vector<String>();
	
	/**
	 * Contains the names of all read points whose identify-operations will
	 * always fail.
	 */
	public Vector<String> continuousIdentifyErrors = new Vector<String>();
	
	/**
	 * Contains the names of all read points whose read-operations will always
	 * fail.
	 */
	public Vector<String> continuousReadErrors = new Vector<String>();
	
	/**
	 * Contains the names of all read points whose write-operations will always
	 * fail.
	 */
	public Vector<String> continuousWriteErrors = new Vector<String>();
	
	/**
	 * Contains the names of all read points whose kill-operations will always
	 * fail.
	 */
	public Vector<String> continuousKillErrors = new Vector<String>();
	
	
	public SimulatorController(String halName, String propFile) {
		this.halName = halName;
		this.propFile = propFile;
		this.initialize();
		
		log.debug("Simulator: " + simType);
		reader = new HashMap();
		
		for (int i = 0; i < nOfReadPoints; i++){
			reader.put( readPointNames[i] , new HashSet() );
		}
		
		
		try{
			//Dynamic class loading
			//1.) The full package path 
			Class clazz = org.ow2.aspirerfid.reader.rp.hal.impl.sim.util.Activator.bundle.getClass().forName(simType);
			//2.) State the types of the constructors arguments. here: only one argument, of type String
			java.lang.reflect.Constructor co = clazz.getConstructor(new Class[] {});
			//3.) Create new instance with the argument's values
			simulator = (SimulatorEngine) co.newInstance(null);
			simulator.initialize(this, simTypePropFile);	
		}
		catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
			System.exit(1);
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}		
	}
	
	/**
	 * Initialize a reader.
	 */
	public void initialize(){
		
		this.props = new ControllerProperties(ResourceLocator.getURL(propFile, defaultPropFile));
		
		//sets the parameters according to the properties file
		try{
			simType = props.getParameter("simType");
			simTypePropFile = props.getParameter("simTypePropFile");
         epcTransponderModelsConfig = props.getParameter(
         "epcTransponderModelsConfig");
			
			nOfReadPoints = Integer.parseInt(props.getParameter("numberOfReadPoints"));
			readPointNames = new String[nOfReadPoints];
			readyReadPoints = new Vector<String>();
			for (int i = 0; i < nOfReadPoints; i++){
				readPointNames[i] = this.props.getParameter("readPoint_" + (i + 1));
				readyReadPoints.add(readPointNames[i]);
			}
		}	
		catch (Exception e){
			String message="Couldn't initialize the reader:" + e.getMessage();
			log.debug(message);
			e.printStackTrace();
		}
	}
	
	public void disconnect()
	{
		if (simulator instanceof GraphicSimulator)
			((GraphicSimulator)simulator).setVisible(false);
		for(int i=0; i<readPointNames.length; i++)
		{
		    shutDownReadPoint(readPointNames[i]);
		}
	}
	
	//-------------------- SimulatorEngine access methods ----------------------------
	/**
	 * 
	 * To move a certain tag to a certain antenna.
	 * 
	 * @param readPointName The name of the read point to which the tag should be added.
	 * @param tag The tag object to be added to the antenna / read point.
	 * 
	 * @return Returns true if tag has been added. False if tag with same TagID already is in this antenna.
	 */
	public boolean add(String readPointName, Tag tag){
		log.info("'" + tag.getTagID() + "' ENTER the range of antenna '" + readPointName + "'");
		return ((Set)reader.get(readPointName)).add(tag);
	}
	
	/**
	 * To move a certain tag to a certain antenna.
	 * 
	 * @param readPointName The name of the read point to which the tag should be added.
	 * @param tagId The ID of a tag to be added to the antenna / read point. A new (empty) tag object with ID tagId will be generated.
	 * 
	 * @return Returns true if tag has been added. False if tag with same TagID already is in this antenna.
	 */
	public boolean add(String readPointName, String tagId){
		Tag t = new Tag(tagId);
		return add(readPointName, t);
	}
	
	/**
	 * To remove a certain tag from a certain antenna.
	 * 
	 * @param readPointName The name of the read point from which the tag should be removed.
	 * @param tag The tag object to be removed from the antenna / read point.
	 * 
	 * @return Returns true if tag has been removed. False if no such tag existed in this antenna.
	 */
	public boolean remove(String readPointName, Tag tag){
		log.info("'" + tag.getTagID() + "' EXIT the range of antenna '" + readPointName + "'");
		return ((Set)reader.get(readPointName)).remove(tag);
	}
	
	/**
	 * To remove a certain tag from a certain antenna.
	 * 
	 * @param readPointName The name of the read point from which the tag should be removed.
	 * @param tagId The ID of a tag to be removed from the antenna / read point.
	 * 
	 * @return Returns true if tag has been removed. False if no such tag existed in this antenna.
	 */
	public boolean remove(String readPointName, String tagId){
		Tag t = new Tag(tagId);
		return remove(readPointName, t);		
	}
	

	/**
	 * 
	 * To find out whether a certain tag is situated on a certain antenna.
	 * 
	 * @param readPointName The name of the read point
	 * @param tag The tag.
	 * 
	 * @return Returns true if such a tag is on this antenna. False if no such tag existed in this antenna.
	 */
	public boolean contains(String readPointName, Tag tag){
		return ((Set)reader.get(readPointName)).contains(tag);
	}
	
	/**
	 * To find out whether a certain tag is situated on a certain antenna.
	 * 
	 * @param readPointName The name of the read point
	 * @param tagId The ID of a tag.
	 * 
	 * @return Returns true if a tag with ID tagId is on this antenna. False if no such tag existed in this antenna.
	 */
	public boolean contains(String readPointName, String tagId){
		Tag t = new Tag(tagId);
		return contains(readPointName, t);		
	}
	/**
	 * To get a set of all tags situated on a certain antenna.
	 * 
	 * @param readPointName The name of the read point
	 * @return Returns a Set containing a copy of all tags currently on this read point.
	 */
	public Set getTagsFromReadPoint(String readPointName){
		Iterator it = ((Set)reader.get(readPointName)).iterator();
		Set setCopy = new HashSet();
		
		while (it.hasNext()){
			setCopy.add(it.next());
		}		
		return setCopy;
	}
	
	//-------------------- HAL interface methods  ------------------------------------
	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#getHalName()
	 */
	public String getHALName() {
		return halName;
	}
	
	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#identify(java.lang.String[])
	 */
	public Observation[] identify(String[] readPointNames)
         throws HardwareException {
		// Each read point gets its own Observation
		Observation[] observations = new Observation[readPointNames.length];
		
		for (int i = 0; i < readPointNames.length; i++){
			
			observations[i] = new Observation();
			observations[i].setHalName(this.halName);
			observations[i].setReadPointName(readPointNames[i]);
			
			//Get the tag IDs on this antenna
			Vector<String> v = new Vector<String>();
			Iterator it = ((HashSet)reader.get(readPointNames[i])).iterator();
			while (it.hasNext()){
				Tag t = (Tag) it.next();
				v.add(t.getTagID());
			}
			
         int len = v.size();
         String v_arr[] = new String[len];
         v_arr = v.toArray(v_arr);
         TagDescriptor[] td_arr = new TagDescriptor[v_arr.length];
         String idTConf;
			try {
				idTConf = props.getParameter("idTypesConfig");
			} catch (Exception e) {
				idTConf = null;
			}
         for (int j = 0; j < td_arr.length; j++) {
				IDType idType = IDType.getIdType("EPC", idTConf);
            byte[] accada_sim_tid_bytes = ByteBlock.hexStringToByteArray("E2FFF000");
            EPCTransponderModel tagModel = EPCTransponderModel.getEpcTrasponderModel(
                  accada_sim_tid_bytes, epcTransponderModelsConfig);
            MemoryBankDescriptor[] memoryBankDescriptors =
               new MemoryBankDescriptor[4];
            memoryBankDescriptors[0] = new MemoryBankDescriptor(
               tagModel.getReservedSize(), tagModel.getReservedReadable(),
               tagModel.getReservedWriteable());
            memoryBankDescriptors[1] = new MemoryBankDescriptor(
                  tagModel.getEpcSize(), tagModel.getEpcReadable(),
                  tagModel.getEpcWriteable());
            memoryBankDescriptors[2] = new MemoryBankDescriptor(
                  tagModel.getTidSize(), tagModel.getTidReadable(),
                  tagModel.getTidWriteable());
            memoryBankDescriptors[3] = new MemoryBankDescriptor(
                  tagModel.getUserSize(), tagModel.getUserReadable(),
                  tagModel.getUserWriteable());
            MemoryDescriptor memoryDescriptor = new MemoryDescriptor(
                  memoryBankDescriptors); 
            td_arr[j] = new TagDescriptor(idType, memoryDescriptor);
         }
         observations[i].setTagDescriptors(td_arr);
			observations[i].setIds(v_arr);
			observations[i].setTimestamp(System.currentTimeMillis());
			
			if (!readyReadPoints.contains(readPointNames[i])) {
				log.error("Read point \"" + readPointNames[i] + "\" is not ready.");
				observations[i].setIds(new String[0]);
            observations[i].setTagDescriptors(new TagDescriptor[0]);
				observations[i].successful = false;
			} else if (continuousIdentifyErrors.contains(readPointNames[i])) {
				observations[i].successful = false;
			} else if (identifyError.contains(readPointNames[i])) {
				observations[i].successful = false;
				identifyError.remove(readPointNames[i]);
			} else {
				observations[i].successful = true;
			}
			
			log.debug(observations[i].toString());
		}
		return observations;
	}
		
	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#startAsynchronousIdentify(java.lang.String[], org.ow2.aspirerfid.reader.rp.impl.hal.AsynchronousIdentifyListener, java.lang.String, java.lang.String)
	 */
	public void startAsynchronousIdentify(String[] readPointNames,
			Trigger trigger) throws HardwareException,
			UnsupportedOperationException {
		throw new HardwareException("HAL not ready.");
	}
	
	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#stopAsynchronousIdentify(org.ow2.aspirerfid.reader.rp.impl.hal.AsynchronousIdentifyListener)
	 */
	public void stopAsynchronousIdentify()
			throws HardwareException, UnsupportedOperationException {
		throw new HardwareException("HAL not ready.");
	}
	
	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#isAsynchronousIdentifyRunning(org.ow2.aspirerfid.reader.rp.impl.hal.AsynchronousIdentifyListener)
	 */
	public boolean isAsynchronousIdentifyRunning() throws HardwareException,
			UnsupportedOperationException {
		throw new HardwareException("HAL not ready.");
	}


   /* (non-Javadoc)
    * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#addAsynchronousIdentifyListener(org.ow2.aspirerfid.reader.rp.impl.hal.AsynchronousIdentifyListener)
    */
	public void addAsynchronousIdentifyListener(AsynchronousIdentifyListener listener)
         throws HardwareException, UnsupportedOperationException {
	   throw new HardwareException("HAL not ready.");
	}


   /* (non-Javadoc)
    * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#removeAsynchronousIdentifyListener(org.ow2.aspirerfid.reader.rp.impl.hal.AsynchronousIdentifyListener)
    */
	public void removeAsynchronousIdentifyListener(AsynchronousIdentifyListener listener)
         throws HardwareException, UnsupportedOperationException {
	   throw new HardwareException("HAL not ready.");
	}


   /* (non-Javadoc)
    * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsAsynchronousIdentify()
    */
	public boolean supportsAsynchronousIdentify() {
	   return false;
	}


	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#readBytes(java.lang.String, java.lang.String, int, int, int, java.lang.String[])
	 */
	public UnsignedByteArray readBytes(String readPointName, String id, int memoryBank,
			int offset, int length, String[] passwords)
			throws HardwareException, UnsupportedOperationException {
		if (readyReadPoints.contains(readPointName)) {
			
			if (continuousReadErrors.contains(readPointName)) {
				throw new HardwareException();
			} else if (readError.contains(readPointName)) {
				readError.remove(readPointName);
				throw new HardwareException();
			}
			
			UnsignedByteArray byteData;
			Tag tag = null;
			if (contains(readPointName, id)) {
				Iterator it = ((HashSet)reader.get(readPointName)).iterator();
				while (it.hasNext()){
					Tag curTag = (Tag)it.next();
					if (curTag.getTagID().equalsIgnoreCase(id)) {
						tag = curTag;
						break;
					}
				}
			}
			
			if(tag != null){
				byteData = new UnsignedByteArray(tag.readData(memoryBank, offset, length));
			}
			else{
				String message="Specified tag not in range";
				throw new HardwareException(message);	
			}
			
			return byteData;
		} else {
			throw new HardwareException("Read point is not ready.");
		}
	}
	
	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#writeBytes(java.lang.String, java.lang.String, int, int, byte[], java.lang.String[])
	 */
	public void writeBytes(String readPointName, String id, int memoryBank,
			int offset, UnsignedByteArray data, String[] passwords)
			throws HardwareException, UnsupportedOperationException {
		if (readyReadPoints.contains(readPointName)) {
			
			if (continuousWriteErrors.contains(readPointName)) {
				throw new HardwareException();
			} else if (writeError.contains(readPointName)) {
				writeError.remove(readPointName);
				throw new HardwareException();
			}
			
			Tag tag = null;
			if (contains(readPointName, id)) {
				Iterator it = ((HashSet)reader.get(readPointName)).iterator();
				while (it.hasNext()){
					Tag curTag = (Tag)it.next();
					if (curTag.getTagID().equalsIgnoreCase(id)) {
						tag = curTag;
						break;
					}
				}
			}
			
			if(tag != null){
				tag.writeData(memoryBank, offset, data.toByteArray());
			}
			else{
				String message="Specified tag not in range";
				throw new HardwareException(message);	
			}
		} else {
			throw new HardwareException("Read point is not ready.");
		}
	}

	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#writeId(java.lang.String, java.lang.String, java.lang.String[])
	 */
	public void writeId(String readPointName, String id, String[] passwords)
			throws ReadPointNotFoundException, HardwareException,
			UnsupportedOperationException {
		throw new HardwareException("HAL not ready.");
		// TODO: implement
	}

	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsWriteId()
	 */
	public boolean supportsWriteId() {
		return false;
	}

	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#getReadPointNames()
	 */
	public String[] getReadPointNames() {
		return readPointNames;
	}
	
	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#setParameter(java.lang.String, java.lang.String)
	 */
	public void setParameter(String param, String value)
			throws HardwareException, UnsupportedOperationException {
		// TODO: implement
	}
	
	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#getAllParameterNames()
	 */
	public String[] getAllParameterNames() throws HardwareException,
			UnsupportedOperationException {
		try{
			return this.props.getParameterNames();
		}
		catch (Exception e){
			throw new HardwareException(e.getMessage());
		}
	}
	
	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#getParameter(java.lang.String)
	 */
	public String getParameter(String param) throws HardwareException,
			UnsupportedOperationException {
		try{
			return this.props.getParameter(param);
		}
		catch (Exception e){
			e.printStackTrace();
			throw new HardwareException(e.getMessage());
		}
	}
	
	
   /* (non-Javadoc)
    * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsParameters()
    */
   public boolean supportsParameters() {
      return true;
   }


	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#programId(java.lang.String, java.lang.String[])
	 */
	public void programId(String id, String[] passwords)
			throws HardwareException, UnsupportedOperationException {
	   throw new HardwareException("HAL not ready.");
		// TODO: implement
	}
	
	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#reset()
	 */
	public void reset() throws HardwareException {
		// TODO: implement
	}
	
	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#kill(java.lang.String, java.lang.String[])
	 */
	public void kill(String readPointName, String id, String[] passwords) throws HardwareException,
			UnsupportedOperationException {
	   throw new HardwareException("HAL not ready.");
		// TODO: implement
	}
	
	
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsReadBytes()
	 */
	public boolean supportsReadBytes() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsWriteBytes()
	 */
	public boolean supportsWriteBytes() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsProgramId()
	 */
	public boolean supportsProgramId() {
		return false;
	}
	
	
   /* (non-Javadoc)
    * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsKill()
    */
   public boolean supportsKill() {
      return false;
   }
   
   
   /* (non-Javadoc)
    * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsReset()
    */
   public boolean supportsReset() {
      return false;
   }
   
   
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#getReadPointPowerLevel(java.lang.String, boolean)
	 */
	public int getReadPointPowerLevel(String readPointName, boolean normalize) {
		// TODO: implement
		return 0;
	}
	
	
   /* (non-Javadoc)
    * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsGetReadPointPowerLevel()
    */
   public boolean supportsGetReadPointPowerLevel() {
      return false;
   }
   
   
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#getReadPointNoiseLevel(java.lang.String, boolean)
	 */
	public int getReadPointNoiseLevel(String readPointName, boolean normalize) {
		// TODO: implement
		return 0;
	}
	
	
   /* (non-Javadoc)
    * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsGetReadPointNoiseLevel()
    */
   public boolean supportsGetReadPointNoiseLevel() {
      return false;
   }
   
   
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#startUpReadPoint(java.lang.String)
	 */
	public void startUpReadPoint(String readPointName) {
		if (!readyReadPoints.contains(readPointName)) {
			readyReadPoints.add(readPointName);
		}
	}
	
	
   /* (non-Javadoc)
    * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsStartUpReadPoint()
    */
   public boolean supportsStartUpReadPoint() {
      return true;
   }
   
   
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#shutDownReadPoint(java.lang.String)
	 */
	public void shutDownReadPoint(String readPointName) {
		if (readyReadPoints.contains(readPointName)) {
			readyReadPoints.remove(readPointName);
		}
	}
	
	
   /* (non-Javadoc)
    * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsShutDownReadPoint()
    */
   public boolean supportsShutDownReadPoint() {
      return true;
   }
   
   
	/* (non-Javadoc)
	 * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#isReadPointReady(java.lang.String)
	 */
	public boolean isReadPointReady(String readPointName) {
		return readyReadPoints.contains(readPointName);
	}
	
	
   /* (non-Javadoc)
    * @see org.ow2.aspirerfid.reader.rp.impl.hal.HardwareAbstraction#supportsIsReadPointReady()
    */
   public boolean supportsIsReadPointReady() {
      return true;
   }
   
   
	/**
	 * For debugging.
	 * 
	 * @param args
	 *            No arguments
	 */
	public static void main (String[] args) {
		DOMConfigurator.configure("./props/log4j.xml");
		new SimulatorController("SimulatorController", "/props/SimulatorController.xml");
	}
}
