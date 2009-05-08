/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp;

import java.io.File;
import java.io.IOException;

import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.OperationalStatus;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.MgmtAgent;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.IfMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.IpMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.EpcgNotifChanSrcTableRowStatusListener;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.EpcgNotifTrigTableRowStatusListener;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.EpcgRdPntSrcTableRowStatusListener;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.EpcgReadTrigTableRowStatusListener;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.EpcgReaderServerTableRowStatusListener;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTargetAddrRowStatusListener;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.TableCreator;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmChannel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util.SnmpUtil;
import org.ow2.aspirerfid.reader.rp.impl.core.msg.Address;
import org.apache.log4j.Logger;
import org.snmp4j.TransportMapping;
import org.snmp4j.agent.BaseAgent;
import org.snmp4j.agent.CommandProcessor;
import org.snmp4j.agent.DuplicateRegistrationException;
import org.snmp4j.agent.mo.MOAccessImpl;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.agent.mo.snmp.RowStatus;
import org.snmp4j.agent.mo.snmp.SnmpCommunityMIB;
import org.snmp4j.agent.mo.snmp.SnmpNotificationMIB;
import org.snmp4j.agent.mo.snmp.SnmpTargetMIB;
import org.snmp4j.agent.mo.snmp.StorageType;
import org.snmp4j.agent.mo.snmp.TransportDomains;
import org.snmp4j.agent.mo.snmp.VacmMIB;
import org.snmp4j.log.Log4jLogFactory;
import org.snmp4j.log.LogFactory;
import org.snmp4j.mp.MessageProcessingModel;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModel;
import org.snmp4j.security.USM;
import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.UnsignedInteger32;
import org.snmp4j.smi.Variable;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.ThreadPool;


/**
 * The SNMP agent's core used in the reader management implementation.
 */
public class SnmpAgent extends BaseAgent implements MgmtAgent {
	
	// initialize Log4J logging for the SNMP4J libraries
	static {
		LogFactory.setLogFactory(new Log4jLogFactory());
	}
	
	/**
	 * The engine ID.
	 */
	public static final String ENGINE_ID = "80:00:13:70:01:c0:a8:01:21";
	
	/**
	 * The logger.
	 */
	private static Logger log = Logger.getLogger(SnmpAgent.class);
	
	/**
	 * The SNMP agent's address.
	 */
	private String address;

	
	/**********************************************
	 *                  The MIBs                  *
	 **********************************************/
	
	/**
	 * The EpcglobalReaderMib.
	 */
	private EpcglobalReaderMib epcglobalReaderMib;
	
	/**
	 * The IfMib.
	 */
	private IfMib ifMib;
	
	/**
	 * The IpMib.
	 */
	private IpMib ipMib;
	
//	/**
//	 * The SnmpTargetMib.
//	 */
//	private SnmpTargetMib snmpTargetMib;
	
//	/**
//	 * The SNMPv2Mib.
//	 */
//	private SNMPv2Mib snmpv2Mib;
	
	
	/**
	 * The reader device.
	 */
	private ReaderDevice readerDevice;
	
	/**
	 * The table creator.
	 */
	private TableCreator tableCreator;
	
	/**
	 * <code>true</code> if this agent has been initialized already,
	 * <code>false</code> otherwise.
	 */
	private boolean initialized;
	
	/**
	 * The singleton instance of the <code>SnmpAgent</code>.
	 */
	private static SnmpAgent instance = null;
	
	
	/**
	 * Creates and returns the singleton instance of the <code>SnmpAgent</code>.
	 * If there already exists an instance it returns that instance.
	 * 
	 * @param bootCounterFile
	 *            The boot counter file
	 * @param configFile
	 *            The config file
	 * @param address
	 *            The SNMP agent's address
	 * @return The singleton instance of the <code>SnmpAgent</code>
	 */
	public static SnmpAgent create(File bootCounterFile, File configFile,
			String address) {
		if (SnmpAgent.instance == null) {
			try {
				SnmpAgent.instance = new SnmpAgent(bootCounterFile, configFile,
						address);
			} catch (IOException ioe) {
				log.error("Unable to create SnmpAgent.");
			}
		} else {
			log
					.warn("SnmpAgent already exists: only one SnmpAgent object can be created.");
		}
		return SnmpAgent.instance;
	}
	
	/**
	 * Returns the singleton instance of the <code>SnmpAgent</code>.
	 * 
	 * @return The singleton instance of the <code>SnmpAgent</code>
	 */
	public static SnmpAgent getInstance() {
		if (SnmpAgent.instance == null) log.warn("Call create() method first -> null returned");
		return SnmpAgent.instance;
	}
	
	/**
	 * The private constructor.
	 * 
	 * @param bootCounterFile
	 *            The boot counter file
	 * @param configFile
	 *            The config file
	 * @param address
	 *            The SNMP agent's address
	 * @throws IOException
	 */
	private SnmpAgent(File bootCounterFile, File configFile, String address)
			throws IOException {
		super(bootCounterFile, configFile, new CommandProcessor(
//		new OctetString(MPv3.createLocalEngineID())));
				OctetString.fromHexString(SnmpAgent.ENGINE_ID, ':')));
		this.address = address;
		initialized = false;
	}
	
	/**
	 * Initialization.
	 * 
	 * @throws IOException
	 */
	@Override
	public void init() throws IOException {
		agent.setThreadPool(ThreadPool.create("RequestPool", 4));
		addShutdownHook();
		
		try {
			readerDevice = ReaderDevice.getInstance();
		} catch (ReaderProtocolException rpe) {
			rpe.printStackTrace();
		}
		
		tableCreator = new TableCreator(server,readerDevice);
		super.init();
		initialized = true;
	}
	
	public void stop()
	{
		try {
		transportMappings[0].close();
		}catch(IOException e)
		{
			log.warn(e);
		}
	}

	/**
	 * Registers managed objects at the agent's server.
	 */
	protected void registerManagedObjects() {
		try {
//			createEpcgReaderDeviceInformation();
			createEpcGlobalTables();
			createEpcGlobalScalars();
			
			RowStatus epcgReaderServerRowStatus = (RowStatus)epcglobalReaderMib.getEpcgReaderServerEntry().getColumn(EpcglobalReaderMib.idxEpcgReaderServerRowStatus);
			epcgReaderServerRowStatus.addRowStatusListener(new EpcgReaderServerTableRowStatusListener(readerDevice));
			
			RowStatus epcgNotifTrigRowStatus = (RowStatus)epcglobalReaderMib.getEpcgNotifTrigEntry().getColumn(EpcglobalReaderMib.idxEpcgNotifTrigRowStatus);
			epcgNotifTrigRowStatus.addRowStatusListener(new EpcgNotifTrigTableRowStatusListener());
			
			RowStatus epcgReadTrigRowStatus = (RowStatus)epcglobalReaderMib.getEpcgReadTrigEntry().getColumn(EpcglobalReaderMib.idxEpcgReadTrigRowStatus);
			epcgReadTrigRowStatus.addRowStatusListener(new EpcgReadTrigTableRowStatusListener());
			
			RowStatus epcgRdPntSrcRowStatus = (RowStatus)epcglobalReaderMib.getEpcgRdPntSrcEntry().getColumn(EpcglobalReaderMib.idxEpcgRdPntSrcRowStatus);
			epcgRdPntSrcRowStatus.addRowStatusListener(new EpcgRdPntSrcTableRowStatusListener());
			
			RowStatus epcgNotifChanSrcRowStatus = (RowStatus)epcglobalReaderMib.getEpcgNotifChanSrcEntry().getColumn(EpcglobalReaderMib.idxEpcgNotifChanSrcRowStatus);
			epcgNotifChanSrcRowStatus.addRowStatusListener(new EpcgNotifChanSrcTableRowStatusListener());
			
			RowStatus snmpTargetAddrRowStatus = (RowStatus)snmpTargetMIB.getSnmpTargetAddrEntry().getColumn(7);
			snmpTargetAddrRowStatus.addRowStatusListener(new SnmpTargetAddrRowStatusListener(readerDevice));
			
			epcglobalReaderMib.registerMOs(server, null);
			ifMib.registerMOs(server, null);
			ipMib.registerMOs(server, null);
//			snmpTargetMib.registerMOs(server, null);
//			snmpv2Mib.registerMOs(server, null);
		} catch (DuplicateRegistrationException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Adds initial notification targets and filters.
	 * 
	 * @param targetMIB
	 *            The <code>SnmpTargetMIB</code> holding the target
	 *            configuration
	 * @param notificationMIB
	 *            The <code>SnmpNotificationMIB</code> holding the
	 *            notification (filter) configuration
	 */
	protected void addNotificationTargets(SnmpTargetMIB targetMIB,
			SnmpNotificationMIB notificationMIB) {
		targetMIB.addDefaultTDomains();

//		targetMIB.addTargetAddress(new OctetString("notification"),
//				TransportDomains.transportDomainUdpIpv4, new OctetString(
//						new UdpAddress("127.0.0.1/162").getValue()), 200, 1,
//				new OctetString("notify"), new OctetString("v2c"),
//				StorageType.permanent);
		targetMIB.addTargetParams(new OctetString("v2c"),
				MessageProcessingModel.MPv2c,
				SecurityModel.SECURITY_MODEL_SNMPv2c,
				new OctetString("public"), SecurityLevel.NOAUTH_NOPRIV,
				StorageType.permanent);
		notificationMIB.addNotifyEntry(new OctetString("default"),
				new OctetString("notify"),
				SnmpNotificationMIB.SnmpNotifyTypeEnum.trap,
				StorageType.permanent);
	}

	/**
	 * Adds initial VACM configuration.
	 * 
	 * @param vacm
	 *            The <code>VacmMIB</code> holding the agent's view
	 *            configuration
	 */
	protected void addViews(VacmMIB vacm) {
		vacm.addGroup(SecurityModel.SECURITY_MODEL_SNMPv1, new OctetString(
				"public"), new OctetString("v1v2group"),
				StorageType.nonVolatile);
		vacm.addGroup(SecurityModel.SECURITY_MODEL_SNMPv2c, new OctetString(
				"public"), new OctetString("v1v2group"),
				StorageType.nonVolatile);
		vacm.addAccess(new OctetString("v1v2group"), new OctetString(),
				SecurityModel.SECURITY_MODEL_ANY, SecurityLevel.NOAUTH_NOPRIV,
				VacmMIB.vacmExactMatch, new OctetString("fullReadView"),
				new OctetString("fullWriteView"), new OctetString(
						"fullNotifyView"), StorageType.nonVolatile);
		vacm.addViewTreeFamily(new OctetString("fullReadView"), new OID("1.3"),
				new OctetString(), VacmMIB.vacmViewIncluded,
				StorageType.nonVolatile);
		vacm.addViewTreeFamily(new OctetString("fullWriteView"),
				new OID("1.3"), new OctetString(), VacmMIB.vacmViewIncluded,
				StorageType.nonVolatile);
		vacm.addViewTreeFamily(new OctetString("fullNotifyView"),
				new OID("1.3"), new OctetString(), VacmMIB.vacmViewIncluded,
				StorageType.nonVolatile);
	}

	/**
	 * Adds all the necessary initial users to the <code>USM</code>.
	 * 
	 * @param usm
	 *            The <code>USM</code> instance used by this agent
	 */
	protected void addUsmUser(USM usm) {
		// Not used.
	}

	/**
	 * Initializes the transport mappings (ports) to be used by the agent.
	 * 
	 * @throws IOException
	 */
	protected void initTransportMappings() throws IOException {
		transportMappings = new TransportMapping[1];
		transportMappings[0] = new DefaultUdpTransportMapping(new UdpAddress(
				address));
	}

	/**
	 * Unregister managed objects from the agent's server.
	 */
	protected void unregisterManagedObjects() {
		ifMib.unregisterMOs(server, null);
		ipMib.unregisterMOs(server, null);
//		snmpTargetMib.unregisterMOs(server, null);
//		snmpv2Mib.unregisterMOs(server, null);
	}

	/**
	 * Adds community to security name mappings needed for SNMPv1 and SNMPv2c.
	 * 
	 * @param communityMIB
	 *            The <code>SnmpCommunityMIB</code> holding coexistence
	 *            configuration for community based security models
	 */
	protected void addCommunities(SnmpCommunityMIB communityMIB) {
		Variable[] com2sec = new Variable[] { new OctetString("public"), // community name
				new OctetString("public"), // security name
				getAgent().getContextEngineID(), // local engine ID
				new OctetString(), // default context name
				new OctetString(), // transport tag
				new Integer32(StorageType.nonVolatile), // storage type
				new Integer32(RowStatus.active) // row status
		};
		MOTableRow row = communityMIB.getSnmpCommunityEntry().createRow(
				new OctetString("public2public").toSubIndex(true), com2sec);
		communityMIB.getSnmpCommunityEntry().addRow(row);
	}

	/**
	 * Register the basic MIB modules at the agent's <code>MOServer</code>.
	 */
	protected void registerSnmpMIBs() {
		epcglobalReaderMib = EpcglobalReaderMib.getInstance();
		ifMib = IfMib.getInstance();
		ipMib = IpMib.getInstance();
//		snmpTargetMib = SnmpTargetMib.getInstance();
//		snmpv2Mib = SNMPv2Mib.getInstance();
		super.registerSnmpMIBs();
	}
	
	/**
	 * Creates the tables for the Epcglobal reader management implementation.
	 */
	private void createEpcGlobalTables() {
		tableCreator.createTable(TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE);
		tableCreator.createTable(TableTypeEnum.EPCG_READER_SERVER_TABLE);
		tableCreator.createTable(TableTypeEnum.EPCG_READ_POINT_TABLE);
		tableCreator.createTable(TableTypeEnum.EPCG_ANT_READ_POINT_TABLE);
		tableCreator.createTable(TableTypeEnum.EPCG_IO_PORT_TABLE);
		tableCreator.createTable(TableTypeEnum.EPCG_SOURCE_TABLE);
		tableCreator.createTable(TableTypeEnum.EPCG_NOTIFICATION_CHANNEL_TABLE);
		tableCreator.createTable(TableTypeEnum.EPCG_TRIGGER_TABLE);
		tableCreator.createTable(TableTypeEnum.EPCG_NOTIF_TRIG_TABLE);
		tableCreator.createTable(TableTypeEnum.EPCG_READ_TRIG_TABLE);
		tableCreator.createTable(TableTypeEnum.EPCG_RD_PNT_SRC_TABLE);
		tableCreator.createTable(TableTypeEnum.EPCG_NOTIF_CHAN_SRC_TABLE);
	}
	
	private void createEpcGlobalScalars() {
		
		/*******************************
		 * epcgReaderDeviceInformation *
		 *******************************/
		
		EpcgScalar epcgRdrDevDescription = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_DESCRIPTION, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			server.register(epcgRdrDevDescription, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevRole = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_ROLE, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			server.register(epcgRdrDevRole, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevEpc = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_EPC, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			server.register(epcgRdrDevEpc, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevSerialNumber = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_SERIAL_NUMBER, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			server.register(epcgRdrDevSerialNumber, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevTimeUtc = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_TIME_UTC, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			server.register(epcgRdrDevTimeUtc, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevCurrentSource = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_CURRENT_SOURCE, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			server.register(epcgRdrDevCurrentSource, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevReboot = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_REBOOT, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			server.register(epcgRdrDevReboot, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevResetStatistics = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_RESET_STATISTICS, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			server.register(epcgRdrDevResetStatistics, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevResetTimestamp = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_RESET_TIMESTAMP, MOAccessImpl.ACCESS_READ_ONLY, new OctetString(""), readerDevice);
		try {
			server.register(epcgRdrDevResetTimestamp, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevNormalizePowerLevel = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_NORMALIZE_POWER_LEVEL, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			server.register(epcgRdrDevNormalizePowerLevel, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevNormalizeNoiseLevel = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_NORMALIZE_NOISE_LEVEL, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			server.register(epcgRdrDevNormalizeNoiseLevel, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		
		/*****************************
		 * epcgReaderDeviceOperation *
		 *****************************/
		
		EpcgScalar epcgRdrDevOperStatus = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_OPER_STATUS, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			server.register(epcgRdrDevOperStatus, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevOperStatusPrior = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_OPER_STATUS_PRIOR, MOAccessImpl.ACCESS_FOR_NOTIFY, new Integer32(OperationalStatus.UNKNOWN.toInt()), readerDevice);
		try {
			server.register(epcgRdrDevOperStatusPrior, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevOperStateEnable = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_OPER_STATE_ENABLE, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			server.register(epcgRdrDevOperStateEnable, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevOperNotifFromState = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_OPER_NOTIF_FROM_STATE, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			server.register(epcgRdrDevOperNotifFromState, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevOperNotifToState = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_OPER_NOTIF_TO_STATE, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			server.register(epcgRdrDevOperNotifToState, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevOperNotifStateLevel = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_OPER_NOTIF_STATE_LEVEL, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			server.register(epcgRdrDevOperNotifStateLevel, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevOperStateSuppressInterval = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_OPER_STATE_SUPPRESS_INTERVAL, MOAccessImpl.ACCESS_READ_WRITE, new UnsignedInteger32(0), readerDevice);
		try {
			server.register(epcgRdrDevOperStateSuppressInterval, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevOperStateSuppressions = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_OPER_STATE_SUPPRESSIONS, MOAccessImpl.ACCESS_READ_ONLY, new Counter32(0), readerDevice);
		try {
			server.register(epcgRdrDevOperStateSuppressions, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		
		/**************************
		 * epcgReaderDeviceMemory *
		 **************************/
		
		EpcgScalar epcgRdrDevFreeMemory = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_FREE_MEMORY, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			server.register(epcgRdrDevFreeMemory, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevFreeMemoryNotifEnable = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_FREE_MEMORY_NOTIF_ENABLE, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			server.register(epcgRdrDevFreeMemoryNotifEnable, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevFreeMemoryNotifLevel = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_FREE_MEMORY_NOTIF_LEVEL, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			server.register(epcgRdrDevFreeMemoryNotifLevel, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevFreeMemoryOnsetThreshold = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_FREE_MEMORY_ONSET_THRESHOLD, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			server.register(epcgRdrDevFreeMemoryOnsetThreshold, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevFreeMemoryAbateThreshold = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_FREE_MEMORY_ABATE_THRESHOLD, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			server.register(epcgRdrDevFreeMemoryAbateThreshold, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevFreeMemoryStatus = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_FREE_MEMORY_STATUS, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			server.register(epcgRdrDevFreeMemoryStatus, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevMemStateSuppressInterval = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_MEM_STATE_SUPPRESS_INTERVAL, MOAccessImpl.ACCESS_READ_WRITE, new UnsignedInteger32(0), readerDevice);
		try {
			server.register(epcgRdrDevMemStateSuppressInterval, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar epcgRdrDevMemStateSuppressions = new EpcgScalar(EpcgScalarType.EPCG_RDR_DEV_MEM_STATE_SUPPRESSIONS, MOAccessImpl.ACCESS_READ_ONLY, new Counter32(0), readerDevice);
		try {
			server.register(epcgRdrDevMemStateSuppressions, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		
		/******************
		 * epcgReadPoints *
		 ******************/
		
//		EpcgScalar epcgReadPointPriorOperStatus = new EpcgScalar(EpcgScalarType.EPCG_READ_POINT_PRIOR_OPER_STATUS, MOAccessImpl.ACCESS_FOR_NOTIFY, new Integer32(OperationalStatus.UNKNOWN.toInt()), readerDevice);
//		try {
//			server.register(epcgReadPointPriorOperStatus, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
//		EpcgScalar epcgReadPointOperStateSuppressInterval = new EpcgScalar(EpcgScalarType.EPCG_READ_POINT_OPER_STATE_SUPPRESS_INTERVAL, MOAccessImpl.ACCESS_READ_WRITE, new UnsignedInteger32(0), readerDevice);
//		try {
//			server.register(epcgReadPointOperStateSuppressInterval, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
//		EpcgScalar epcgReadPointOperStateSuppressions = new EpcgScalar(EpcgScalarType.EPCG_READ_POINT_OPER_STATE_SUPPRESSIONS, MOAccessImpl.ACCESS_READ_ONLY, new Counter32(0), readerDevice);
//		try {
//			server.register(epcgReadPointOperStateSuppressions, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
		
		/*************************
		 * epcgAntennaReadPoints *
		 *************************/
		
//		EpcgScalar epcgAntRdPntSuppressInterval = new EpcgScalar(EpcgScalarType.EPCG_ANT_RD_PNT_SUPPRESS_INTERVAL, MOAccessImpl.ACCESS_READ_WRITE, new UnsignedInteger32(0), readerDevice);
//		try {
//			server.register(epcgAntRdPntSuppressInterval, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
		
		/***************
		 * epcgIoPorts *
		 ***************/
		
//		EpcgScalar epcgIoPortOperStatusPrior = new EpcgScalar(EpcgScalarType.EPCG_IO_PORT_OPER_STATUS_PRIOR, MOAccessImpl.ACCESS_FOR_NOTIFY, new Integer32(OperationalStatus.UNKNOWN.toInt()), readerDevice);
//		try {
//			server.register(epcgIoPortOperStatusPrior, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
//		EpcgScalar epcgIoPortOperStateSuppressInterval = new EpcgScalar(EpcgScalarType.EPCG_IO_PORT_OPER_STATE_SUPPRESS_INTERVAL, MOAccessImpl.ACCESS_READ_WRITE, new UnsignedInteger32(0), readerDevice);
//		try {
//			server.register(epcgIoPortOperStateSuppressInterval, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
//		EpcgScalar epcgIoPortOperStateSuppressions = new EpcgScalar(EpcgScalarType.EPCG_IO_PORT_OPER_STATE_SUPPRESSIONS, MOAccessImpl.ACCESS_READ_ONLY, new Counter32(0), readerDevice);
//		try {
//			server.register(epcgIoPortOperStateSuppressions, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
		
		/***************
		 * epcgSources *
		 ***************/
		
//		EpcgScalar epcgSrcOperPriorStatus = new EpcgScalar(EpcgScalarType.EPCG_SRC_OPER_PRIOR_STATUS, MOAccessImpl.ACCESS_FOR_NOTIFY, new Integer32(OperationalStatus.UNKNOWN.toInt()), readerDevice);
//		try {
//			server.register(epcgSrcOperPriorStatus, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
//		EpcgScalar epcgSrcOperStateSuppressInterval = new EpcgScalar(EpcgScalarType.EPCG_SRC_OPER_STATE_SUPPRESS_INTERVAL, MOAccessImpl.ACCESS_READ_WRITE, new UnsignedInteger32(0), readerDevice);
//		try {
//			server.register(epcgSrcOperStateSuppressInterval, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
//		EpcgScalar epcgSrcOperStateSuppressions = new EpcgScalar(EpcgScalarType.EPCG_SRC_OPER_STATE_SUPPRESSIONS, MOAccessImpl.ACCESS_READ_ONLY, new Counter32(0), readerDevice);
//		try {
//			server.register(epcgSrcOperStateSuppressions, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
		
		/****************************
		 * epcgNotificationChannels *
		 ****************************/
		
//		EpcgScalar epcgNotifChanOperStatusPrior = new EpcgScalar(EpcgScalarType.EPCG_NOTIF_CHAN_OPER_STATUS_PRIOR, MOAccessImpl.ACCESS_FOR_NOTIFY, new Integer32(OperationalStatus.UNKNOWN.toInt()), readerDevice);
//		try {
//			server.register(epcgNotifChanOperStatusPrior, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
//		EpcgScalar epcgNotifChanOperStateSuppressInterval = new EpcgScalar(EpcgScalarType.EPCG_NOTIF_CHAN_OPER_STATE_SUPPRESS_INTERVAL, MOAccessImpl.ACCESS_READ_WRITE, new UnsignedInteger32(0), readerDevice);
//		try {
//			server.register(epcgNotifChanOperStateSuppressInterval, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
//		EpcgScalar epcgNotifChanOperStateSuppressions = new EpcgScalar(EpcgScalarType.EPCG_NOTIF_CHAN_OPER_STATE_SUPPRESSIONS, MOAccessImpl.ACCESS_READ_ONLY, new Counter32(0), readerDevice);
//		try {
//			server.register(epcgNotifChanOperStateSuppressions, null);
//		} catch (DuplicateRegistrationException dre) {
//			log.debug(dre.getMessage());
//		}
		
		/********
		 * IETF *
		 ********/
		
		EpcgScalar sysDescr = new EpcgScalar(EpcgScalarType.SYS_DESCR, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			MOScalar moScalar = SnmpUtil.findMOScalar(new OID(new int[] { 1,3,6,1,2,1,1,1,0 }));
			server.unregister(moScalar, null);
			server.register(sysDescr, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar sysLocation = new EpcgScalar(EpcgScalarType.SYS_LOCATION, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			MOScalar moScalar = SnmpUtil.findMOScalar(new OID(new int[] { 1,3,6,1,2,1,1,6,0 }));
			server.unregister(moScalar, null);
			server.register(sysLocation, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar sysContact = new EpcgScalar(EpcgScalarType.SYS_CONTACT, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			MOScalar moScalar = SnmpUtil.findMOScalar(new OID(new int[] { 1,3,6,1,2,1,1,4,0 }));
			server.unregister(moScalar, null);
			server.register(sysContact, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar sysUpTime = new EpcgScalar(EpcgScalarType.SYS_UP_TIME, MOAccessImpl.ACCESS_READ_ONLY, null, readerDevice);
		try {
			MOScalar moScalar = SnmpUtil.findMOScalar(new OID(new int[] { 1,3,6,1,2,1,1,3,0 }));
			server.unregister(moScalar, null);
			server.register(sysUpTime, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
		EpcgScalar sysName = new EpcgScalar(EpcgScalarType.SYS_NAME, MOAccessImpl.ACCESS_READ_WRITE, null, readerDevice);
		try {
			MOScalar moScalar = SnmpUtil.findMOScalar(new OID(new int[] { 1,3,6,1,2,1,1,5,0 }));
			server.unregister(moScalar, null);
			server.register(sysName, null);
		} catch (DuplicateRegistrationException dre) {
			log.debug(dre.getMessage());
		}
		
	}
	
	/**
	 * Adds the given <code>AlarmChannel</code>s.
	 * 
	 * @param alarmChannels
	 *            The <code>AlarmChannel</code>s to add
	 */
	public void addAlarmChannels(AlarmChannel[] alarmChannels) {
		for (int i = 0; i < alarmChannels.length; i++) {
			AlarmChannel curChan = alarmChannels[i];
			Address curAddr = curChan.getAddress();
			
			if (snmpTargetMIB != null) {
				snmpTargetMIB.addTargetAddress(new OctetString(curChan
						.getName()), TransportDomains.transportDomainUdpIpv4,
						new OctetString(new UdpAddress(curAddr.getHost() + "/"
								+ curAddr.getPort()).getValue()), 200, 1,
						new OctetString("notify"), new OctetString("v2c"),
						StorageType.permanent);
			} else {
				log.error("Run the init() method first.");
				return;
			}
		}
	}
	
	/**
	 * Removes an <code>AlarmChannel</code>.
	 * 
	 * @param alarmChannel
	 *            The <code>AlarmChannel</code> to remove
	 */
	public void removeAlarmChannel(AlarmChannel alarmChannel) {
		if (snmpTargetMIB != null) {
			snmpTargetMIB.removeTargetAddress(new OctetString(alarmChannel.getName()));
		} else {
			log.error("Run the init() method first.");
		}
	}
	
	/**
	 * Checks whether this agent has been initialized already.
	 * 
	 * @return <code>true</code> if this agent has been initialized already,
	 *         <code>false</code> otherwise
	 */
	public boolean isInitialized() {
		return initialized;
	}
	
}
