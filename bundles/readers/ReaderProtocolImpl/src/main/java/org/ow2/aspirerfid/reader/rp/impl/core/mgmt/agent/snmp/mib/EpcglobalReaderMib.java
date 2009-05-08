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

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib;

//--AgentGen BEGIN=_BEGIN
//--AgentGen END

import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTableRowFactory;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.apache.log4j.Logger;
import org.snmp4j.agent.DuplicateRegistrationException;
import org.snmp4j.agent.MOAccess;
import org.snmp4j.agent.MOGroup;
import org.snmp4j.agent.MOServer;
import org.snmp4j.agent.NotificationOriginator;
import org.snmp4j.agent.mo.DefaultMOFactory;
import org.snmp4j.agent.mo.DefaultMOMutableTableModel;
import org.snmp4j.agent.mo.MOAccessImpl;
import org.snmp4j.agent.mo.MOColumn;
import org.snmp4j.agent.mo.MOFactory;
import org.snmp4j.agent.mo.MOMutableColumn;
import org.snmp4j.agent.mo.MOMutableTableModel;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.agent.mo.MOTableIndex;
import org.snmp4j.agent.mo.MOTableIndexValidator;
import org.snmp4j.agent.mo.MOTableSubIndex;
import org.snmp4j.agent.mo.MOValueValidationEvent;
import org.snmp4j.agent.mo.MOValueValidationListener;
import org.snmp4j.agent.mo.snmp.RowStatus;
import org.snmp4j.agent.mo.snmp.smi.Constraint;
import org.snmp4j.agent.mo.snmp.smi.ConstraintsImpl;
import org.snmp4j.agent.mo.snmp.smi.EnumerationConstraint;
import org.snmp4j.agent.mo.snmp.smi.ValueConstraint;
import org.snmp4j.agent.mo.snmp.smi.ValueConstraintValidator;
import org.snmp4j.agent.request.SubRequest;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.Gauge32;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.SMIConstants;
import org.snmp4j.smi.UnsignedInteger32;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

//--AgentGen BEGIN=_IMPORT
//--AgentGen END

public class EpcglobalReaderMib 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup 
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
{

  private static EpcglobalReaderMib instance = null;
  
  public static EpcglobalReaderMib getInstance() {
	if (instance == null) instance = new EpcglobalReaderMib();
	return EpcglobalReaderMib.instance;
  }
  
  private static final Logger log = Logger.getLogger(EpcglobalReaderMib.class);

//--AgentGen BEGIN=_STATIC
//--AgentGen END

  // Factory
  private static MOFactory moFactory = DefaultMOFactory.getInstance();

  // Constants 
  public static final OID oidEpcgRdrDevDescription = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,1,1,0 });
  public static final OID oidEpcgRdrDevRole = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,1,2,0 });
  public static final OID oidEpcgRdrDevEpc = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,1,3,0 });
  public static final OID oidEpcgRdrDevSerialNumber = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,1,4,0 });
  public static final OID oidEpcgRdrDevTimeUtc = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,1,5,0 });
  public static final OID oidEpcgRdrDevCurrentSource = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,1,6,0 });
  public static final OID oidEpcgRdrDevReboot = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,1,7,0 });
  public static final OID oidEpcgRdrDevResetStatistics = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,1,8,0 });
  public static final OID oidEpcgRdrDevResetTimestamp = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,1,9,0 });
  public static final OID oidEpcgRdrDevNormalizePowerLevel = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,1,10,0 });
  public static final OID oidEpcgRdrDevNormalizeNoiseLevel = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,1,11,0 });
  public static final OID oidEpcgRdrDevOperStatus = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,3,1,0 });
  public static final OID oidEpcgRdrDevOperStateEnable = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,3,3,0 });
  public static final OID oidEpcgRdrDevOperNotifFromState = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,3,4,0 });
  public static final OID oidEpcgRdrDevOperNotifToState = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,3,5,0 });
  public static final OID oidEpcgRdrDevOperNotifStateLevel = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,3,6,0 });
  public static final OID oidEpcgRdrDevOperStateSuppressInterval = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,3,7,0 });
  public static final OID oidEpcgRdrDevOperStateSuppressions = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,3,8,0 });
  public static final OID oidEpcgRdrDevFreeMemory = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,4,1,0 });
  public static final OID oidEpcgRdrDevFreeMemoryNotifEnable = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,4,2,0 });
  public static final OID oidEpcgRdrDevFreeMemoryNotifLevel = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,4,3,0 });
  public static final OID oidEpcgRdrDevFreeMemoryOnsetThreshold = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,4,4,0 });
  public static final OID oidEpcgRdrDevFreeMemoryAbateThreshold = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,4,5,0 });
  public static final OID oidEpcgRdrDevFreeMemoryStatus = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,4,6,0 });
  public static final OID oidEpcgRdrDevMemStateSuppressInterval = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,4,7,0 });
  public static final OID oidEpcgRdrDevMemStateSuppressions = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,4,8,0 });
  public static final OID oidEpcgReaderDeviceOperationState =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,0,1 });   
  public static final OID oidTrapVarSysName =
    new OID(new int[] { 1,3,6,1,2,1,1,5 });
//    new OID(new int[] { $oid.replace($DOT.charAt(0),$COMMA.charAt(0)) });
  public static final OID oidTrapVarEpcgRdrDevTimeUtc =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,1,5 });
  public static final OID oidTrapVarEpcgRdrDevOperNotifStateLevel =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,3,6 });
  public static final OID oidTrapVarEpcgRdrDevOperStatusPrior =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,3,2 });
  public static final OID oidTrapVarEpcgRdrDevOperStatus =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,3,1 });

  public static final OID oidEpcgRdrDevMemoryState =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,0,2 });   
  public static final OID oidTrapVarEpcgRdrDevFreeMemoryNotifLevel =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,4,3 });
  public static final OID oidTrapVarEpcgRdrDevFreeMemory =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,4,1 });

  public static final OID oidEpcgReadPointOperationState =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,0,3 });   
  public static final OID oidTrapVarEpcgReadPointOperNotifyStateLevel =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,2,1,1,9 });
  public static final OID oidTrapVarEpcgReadPointName =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,2,1,1,2 });
  public static final OID oidTrapVarEpcgReadPointPriorOperStatus =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,2,1,1,10 });
  public static final OID oidTrapVarEpcgReadPointOperStatus =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,2,1,1,5 });

  public static final OID oidEpcgReaderAntennaReadFailure =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,0,4 });   
  public static final OID oidTrapVarEpcgAntRdPntReadFailureNotifLevel =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,3,1,1,5 });
  public static final OID oidTrapVarEpcgAntRdPntMemoryReadFailures =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,3,1,1,3 });
  public static final OID oidTrapVarEpcgAntRdPntNoiseLevel =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,3,1,1,23 });

  public static final OID oidEpcgReaderAntennaWriteFailure =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,0,5 });   
  public static final OID oidTrapVarEpcgAntRdPntWriteFailuresNotifLevel =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,3,1,1,9 });
  public static final OID oidTrapVarEpcgAntRdPntWriteFailures =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,3,1,1,7 });

  public static final OID oidEpcgReaderAntennaKillFailure =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,0,6 });   
  public static final OID oidTrapVarEpcgAntRdPntKillFailuresNotifLevel =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,3,1,1,13 });
  public static final OID oidTrapVarEpcgAntRdPntKillFailures =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,3,1,1,11 });

  public static final OID oidEpcgReaderAntennaEraseFailure =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,0,7 });   
  public static final OID oidTrapVarEpcgAntRdPntEraseFailuresNotifLevel =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,3,1,1,17 });
  public static final OID oidTrapVarEpcgAntRdPntEraseFailures =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,3,1,1,15 });

  public static final OID oidEpcgReaderAntennaLockFailure =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,0,8 });   
  public static final OID oidTrapVarEpcgAntRdPntLockFailuresNotifLevel =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,3,1,1,21 });
  public static final OID oidTrapVarEpcgAntRdPntLockFailures =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,3,1,1,19 });

  public static final OID oidEpcgReaderIoPortOperationState =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,0,9 });   
  public static final OID oidTrapVarEpcgIoPortOperStatusNotifLevel =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,4,1,1,6 });
  public static final OID oidTrapVarEpcgIoPortName =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,4,1,1,2 });
  public static final OID oidTrapVarEpcgIoPortOperStatusPrior =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,4,1,1,10 });
  public static final OID oidTrapVarEpcgIoPortOperStatus =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,4,1,1,4 });

  public static final OID oidEpcgReaderSourceOperationState =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,0,10 });   
  public static final OID oidTrapVarEpcgSrcOperStatusNotifyLevel =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,5,1,1,21 });
  public static final OID oidTrapVarEpcgSrcName =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,5,1,1,2 });
  public static final OID oidTrapVarEpcgSrcOperStatusPrior =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,5,1,1,23 });
  public static final OID oidTrapVarEpcgSrcOperStatus =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,5,1,1,17 });

  public static final OID oidEpcgReaderNotificationChanOperState =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,0,11 });   
  public static final OID oidTrapVarEpcgNotifChanOperNotifLevel =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,6,1,1,10 });
  public static final OID oidTrapVarEpcgNotifChanName =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,6,1,1,2 });
  public static final OID oidTrapVarEpcgNotifChanOperStatusPrior =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,6,1,1,13 });
  public static final OID oidTrapVarEpcgNotifChanOperStatus =
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,6,1,1,8 });


  // Enumerations
  public static final class EpcgRdrDevRebootEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgRdrDevResetStatisticsEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgRdrDevNormalizePowerLevelEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgRdrDevNormalizeNoiseLevelEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgRdrDevOperStatusEnum {
    public static final int unknown = 1;
    public static final int other = 2;
    public static final int up = 3;
    public static final int down = 4;
  }
  public static final class EpcgRdrDevOperStateEnableEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgRdrDevOperNotifFromStateEnum {
    public static final int unknown = 0;
    public static final int other = 1;
    public static final int up = 2;
    public static final int down = 3;
  }
  public static final class EpcgRdrDevOperNotifToStateEnum {
    public static final int unknown = 0;
    public static final int other = 1;
    public static final int up = 2;
    public static final int down = 3;
  }
  public static final class EpcgRdrDevOperNotifStateLevelEnum {
    public static final int emergency = 0;
    public static final int alert = 1;
    public static final int critical = 2;
    public static final int error = 3;
    public static final int warning = 4;
    public static final int notice = 5;
    public static final int informational = 6;
    public static final int debug = 7;
  }
  public static final class EpcgRdrDevFreeMemoryNotifEnableEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgRdrDevFreeMemoryNotifLevelEnum {
    public static final int emergency = 0;
    public static final int alert = 1;
    public static final int critical = 2;
    public static final int error = 3;
    public static final int warning = 4;
    public static final int notice = 5;
    public static final int informational = 6;
    public static final int debug = 7;
  }
  public static final class EpcgRdrDevFreeMemoryStatusEnum {
    public static final int shortage = 1;
    public static final int normal = 2;
  }

  public static final class EpcgReaderServerAddressTypeEnum {
    public static final int unknown = 0;
    public static final int ipv4 = 1;
    public static final int ipv6 = 2;
    public static final int ipv4z = 3;
    public static final int ipv6z = 4;
    public static final int dns = 16;
  }
  public static final class EpcgReaderServerRowStatusEnum {
    public static final int active = 1;
    /* -- the following value is a state:
-- this value may be read, but not written */
    public static final int notInService = 2;
    /* -- the following three values are
-- actions: these values may be written,
--   but are never read */
    public static final int notReady = 3;
    public static final int createAndGo = 4;
    public static final int createAndWait = 5;
    public static final int destroy = 6;
  }
  public static final class EpcgReadPointAdminStatusEnum {
    public static final int up = 1;
    public static final int down = 2;
  }
  public static final class EpcgReadPointOperStateNotifyEnableEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgReadPointOperNotifyFromStateEnum {
    public static final int unknown = 0;
    public static final int other = 1;
    public static final int up = 2;
    public static final int down = 3;
  }
  public static final class EpcgReadPointOperNotifyToStateEnum {
    public static final int unknown = 0;
    public static final int other = 1;
    public static final int up = 2;
    public static final int down = 3;
  }
  public static final class EpcgReadPointOperNotifyStateLevelEnum {
    public static final int emergency = 0;
    public static final int alert = 1;
    public static final int critical = 2;
    public static final int error = 3;
    public static final int warning = 4;
    public static final int notice = 5;
    public static final int informational = 6;
    public static final int debug = 7;
  }
  public static final class EpcgAntRdPntReadFailureNotifEnableEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgAntRdPntReadFailureNotifLevelEnum {
    public static final int emergency = 0;
    public static final int alert = 1;
    public static final int critical = 2;
    public static final int error = 3;
    public static final int warning = 4;
    public static final int notice = 5;
    public static final int informational = 6;
    public static final int debug = 7;
  }
  public static final class EpcgAntRdPntWriteFailuresNotifEnableEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgAntRdPntWriteFailuresNotifLevelEnum {
    public static final int emergency = 0;
    public static final int alert = 1;
    public static final int critical = 2;
    public static final int error = 3;
    public static final int warning = 4;
    public static final int notice = 5;
    public static final int informational = 6;
    public static final int debug = 7;
  }
  public static final class EpcgAntRdPntKillFailuresNotifEnableEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgAntRdPntKillFailuresNotifLevelEnum {
    public static final int emergency = 0;
    public static final int alert = 1;
    public static final int critical = 2;
    public static final int error = 3;
    public static final int warning = 4;
    public static final int notice = 5;
    public static final int informational = 6;
    public static final int debug = 7;
  }
  public static final class EpcgAntRdPntEraseFailuresNotifEnableEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgAntRdPntEraseFailuresNotifLevelEnum {
    public static final int emergency = 0;
    public static final int alert = 1;
    public static final int critical = 2;
    public static final int error = 3;
    public static final int warning = 4;
    public static final int notice = 5;
    public static final int informational = 6;
    public static final int debug = 7;
  }
  public static final class EpcgAntRdPntLockFailuresNotifEnableEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgAntRdPntLockFailuresNotifLevelEnum {
    public static final int emergency = 0;
    public static final int alert = 1;
    public static final int critical = 2;
    public static final int error = 3;
    public static final int warning = 4;
    public static final int notice = 5;
    public static final int informational = 6;
    public static final int debug = 7;
  }
  public static final class EpcgIoPortAdminStatusEnum {
    public static final int up = 1;
    public static final int down = 2;
  }
  public static final class EpcgIoPortOperStatusNotifEnableEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgIoPortOperStatusNotifLevelEnum {
    public static final int emergency = 0;
    public static final int alert = 1;
    public static final int critical = 2;
    public static final int error = 3;
    public static final int warning = 4;
    public static final int notice = 5;
    public static final int informational = 6;
    public static final int debug = 7;
  }
  public static final class EpcgIoPortOperStatusNotifFromStateEnum {
    public static final int unknown = 0;
    public static final int other = 1;
    public static final int up = 2;
    public static final int down = 3;
  }
  public static final class EpcgIoPortOperStatusNotifToStateEnum {
    public static final int unknown = 0;
    public static final int other = 1;
    public static final int up = 2;
    public static final int down = 3;
  }
  public static final class EpcgSrcAdminStatusEnum {
    public static final int up = 1;
    public static final int down = 2;
  }
  public static final class EpcgSrcOperStatusNotifEnableEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgSrcOperStatusNotifFromStateEnum {
    public static final int unknown = 0;
    public static final int other = 1;
    public static final int up = 2;
    public static final int down = 3;
  }
  public static final class EpcgSrcOperStatusNotifToStateEnum {
    public static final int unknown = 0;
    public static final int other = 1;
    public static final int up = 2;
    public static final int down = 3;
  }
  public static final class EpcgSrcOperStatusNotifyLevelEnum {
    public static final int emergency = 0;
    public static final int alert = 1;
    public static final int critical = 2;
    public static final int error = 3;
    public static final int warning = 4;
    public static final int notice = 5;
    public static final int informational = 6;
    public static final int debug = 7;
  }
  public static final class EpcgRdPntSrcRowStatusEnum {
    public static final int active = 1;
    /* -- the following value is a state:
-- this value may be read, but not written */
    public static final int notInService = 2;
    /* -- the following three values are
-- actions: these values may be written,
--   but are never read */
    public static final int notReady = 3;
    public static final int createAndGo = 4;
    public static final int createAndWait = 5;
    public static final int destroy = 6;
  }
  public static final class EpcgNotifChanSrcRowStatusEnum {
    public static final int active = 1;
    /* -- the following value is a state:
-- this value may be read, but not written */
    public static final int notInService = 2;
    /* -- the following three values are
-- actions: these values may be written,
--   but are never read */
    public static final int notReady = 3;
    public static final int createAndGo = 4;
    public static final int createAndWait = 5;
    public static final int destroy = 6;
  }
  public static final class EpcgNotifChanAdminStatusEnum {
    public static final int up = 1;
    public static final int down = 2;
  }
  public static final class EpcgNotifChanOperNotifEnableEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class EpcgNotifChanOperNotifLevelEnum {
    public static final int emergency = 0;
    public static final int alert = 1;
    public static final int critical = 2;
    public static final int error = 3;
    public static final int warning = 4;
    public static final int notice = 5;
    public static final int informational = 6;
    public static final int debug = 7;
  }
  public static final class EpcgNotifChanOperNotifFromStateEnum {
    public static final int unknown = 0;
    public static final int other = 1;
    public static final int up = 2;
    public static final int down = 3;
  }
  public static final class EpcgNotifChanOperNotifToStateEnum {
    public static final int unknown = 0;
    public static final int other = 1;
    public static final int up = 2;
    public static final int down = 3;
  }
  public static final class EpcgNotifTrigRowStatusEnum {
    public static final int active = 1;
    /* -- the following value is a state:
-- this value may be read, but not written */
    public static final int notInService = 2;
    /* -- the following three values are
-- actions: these values may be written,
--   but are never read */
    public static final int notReady = 3;
    public static final int createAndGo = 4;
    public static final int createAndWait = 5;
    public static final int destroy = 6;
  }
  public static final class EpcgReadTrigRowStatusEnum {
    public static final int active = 1;
    /* -- the following value is a state:
-- this value may be read, but not written */
    public static final int notInService = 2;
    /* -- the following three values are
-- actions: these values may be written,
--   but are never read */
    public static final int notReady = 3;
    public static final int createAndGo = 4;
    public static final int createAndWait = 5;
    public static final int destroy = 6;
  }

  // TextualConventions
  private static final String TC_MODULE_EPCGLOBAL_READER_MIB = "EPCGLOBAL-READER-MIB";
  private static final String TC_MODULE_SNMP_FRAMEWORK_MIB = "SNMP-FRAMEWORK-MIB";
  private static final String TC_MODULE_EPCGLOBAL_SMI_MIB = "EPCGLOBAL-SMI-MIB";
  private static final String TC_EPCGTHRESHOLD = "EpcgThreshold";
  private static final String TC_EPCGOPERATIONALENABLE = "EpcgOperationalEnable";
  private static final String TC_EPCGNOTIFLEVEL = "EpcgNotifLevel";
  private static final String TC_DATEANDTIME = "DateAndTime";
  private static final String TC_ROWPOINTER = "RowPointer";
  private static final String TC_SNMPADMINSTRING = "SnmpAdminString";
  private static final String TC_TRUTHVALUE = "TruthValue";
  private static final String TC_EPCGOPERATIONALSTATUS = "EpcgOperationalStatus";

  // Scalars
  private MOScalar epcgRdrDevDescription;
  private MOScalar epcgRdrDevRole;
  private MOScalar epcgRdrDevEpc;
  private MOScalar epcgRdrDevSerialNumber;
  private MOScalar epcgRdrDevTimeUtc;
  private MOScalar epcgRdrDevCurrentSource;
  private MOScalar epcgRdrDevReboot;
  private MOScalar epcgRdrDevResetStatistics;
  private MOScalar epcgRdrDevResetTimestamp;
  private MOScalar epcgRdrDevNormalizePowerLevel;
  private MOScalar epcgRdrDevNormalizeNoiseLevel;
  private MOScalar epcgRdrDevOperStatus;
  private MOScalar epcgRdrDevOperStateEnable;
  private MOScalar epcgRdrDevOperNotifFromState;
  private MOScalar epcgRdrDevOperNotifToState;
  private MOScalar epcgRdrDevOperNotifStateLevel;
  private MOScalar epcgRdrDevOperStateSuppressInterval;
  private MOScalar epcgRdrDevOperStateSuppressions;
  private MOScalar epcgRdrDevFreeMemory;
  private MOScalar epcgRdrDevFreeMemoryNotifEnable;
  private MOScalar epcgRdrDevFreeMemoryNotifLevel;
  private MOScalar epcgRdrDevFreeMemoryOnsetThreshold;
  private MOScalar epcgRdrDevFreeMemoryAbateThreshold;
  private MOScalar epcgRdrDevFreeMemoryStatus;
  private MOScalar epcgRdrDevMemStateSuppressInterval;
  private MOScalar epcgRdrDevMemStateSuppressions;

  // Tables
  public static final OID oidEpcgGlobalCountersEntry = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,2,1 });
    
  // Column sub-identifier definitions for epcgGlobalCountersEntry:
  public static final int colEpcgGlobalCountersData = 2;

  // Column index definitions for epcgGlobalCountersEntry:
  public static final int idxEpcgGlobalCountersData = 0;

  private static final MOTableSubIndex[] epcgGlobalCountersEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex epcgGlobalCountersEntryIndex = 
      moFactory.createIndex(epcgGlobalCountersEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=epcgGlobalCountersEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             epcgGlobalCountersEntry;
  private MOMutableTableModel epcgGlobalCountersEntryModel;
  public static final OID oidEpcgReaderServerEntry = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,1,5,1 });
    
  // Column sub-identifier definitions for epcgReaderServerEntry:
  public static final int colEpcgReaderServerAddressType = 3;
  public static final int colEpcgReaderServerAddress = 4;
  public static final int colEpcgReaderServerRowStatus = 5;

  // Column index definitions for epcgReaderServerEntry:
  public static final int idxEpcgReaderServerAddressType = 0;
  public static final int idxEpcgReaderServerAddress = 1;
  public static final int idxEpcgReaderServerRowStatus = 2;

  private static final MOTableSubIndex[] epcgReaderServerEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1),
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex epcgReaderServerEntryIndex = 
      moFactory.createIndex(epcgReaderServerEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=epcgReaderServerEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             epcgReaderServerEntry;
  private MOMutableTableModel epcgReaderServerEntryModel;
  public static final OID oidEpcgReadPointEntry = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,2,1,1 });
    
  // Column sub-identifier definitions for epcgReadPointEntry:
  public static final int colEpcgReadPointName = 2;
  public static final int colEpcgReadPointDescription = 3;
  public static final int colEpcgReadPointAdminStatus = 4;
  public static final int colEpcgReadPointOperStatus = 5;
  public static final int colEpcgReadPointOperStateNotifyEnable = 6;
  public static final int colEpcgReadPointOperNotifyFromState = 7;
  public static final int colEpcgReadPointOperNotifyToState = 8;
  public static final int colEpcgReadPointOperNotifyStateLevel = 9;
  public static final int colEpcgReadPointPriorOperStatus = 10;
  public static final int colEpcgReadPointOperStateSuppressInterval = 11;
  public static final int colEpcgReadPointOperStateSuppressions = 12;

  // Column index definitions for epcgReadPointEntry:
  public static final int idxEpcgReadPointName = 0;
  public static final int idxEpcgReadPointDescription = 1;
  public static final int idxEpcgReadPointAdminStatus = 2;
  public static final int idxEpcgReadPointOperStatus = 3;
  public static final int idxEpcgReadPointOperStateNotifyEnable = 4;
  public static final int idxEpcgReadPointOperNotifyFromState = 5;
  public static final int idxEpcgReadPointOperNotifyToState = 6;
  public static final int idxEpcgReadPointOperNotifyStateLevel = 7;
  public static final int idxEpcgReadPointPriorOperStatus = 8;
  public static final int idxEpcgReadPointOperStateSuppressInterval = 9;
  public static final int idxEpcgReadPointOperStateSuppressions = 10;

  private static final MOTableSubIndex[] epcgReadPointEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex epcgReadPointEntryIndex = 
      moFactory.createIndex(epcgReadPointEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=epcgReadPointEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             epcgReadPointEntry;
  private MOMutableTableModel epcgReadPointEntryModel;
  public static final OID oidEpcgAntReadPointEntry = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,3,1,1 });
    
  // Column sub-identifier definitions for epcgAntReadPointEntry:
  public static final int colEpcgAntRdPntTagsIdentified = 1;
  public static final int colEpcgAntRdPntTagsNotIdentified = 2;
  public static final int colEpcgAntRdPntMemoryReadFailures = 3;
  public static final int colEpcgAntRdPntReadFailureNotifEnable = 4;
  public static final int colEpcgAntRdPntReadFailureNotifLevel = 5;
  public static final int colEpcgAntRdPntWriteOperations = 6;
  public static final int colEpcgAntRdPntWriteFailures = 7;
  public static final int colEpcgAntRdPntWriteFailuresNotifEnable = 8;
  public static final int colEpcgAntRdPntWriteFailuresNotifLevel = 9;
  public static final int colEpcgAntRdPntKillOperations = 10;
  public static final int colEpcgAntRdPntKillFailures = 11;
  public static final int colEpcgAntRdPntKillFailuresNotifEnable = 12;
  public static final int colEpcgAntRdPntKillFailuresNotifLevel = 13;
  public static final int colEpcgAntRdPntEraseOperations = 14;
  public static final int colEpcgAntRdPntEraseFailures = 15;
  public static final int colEpcgAntRdPntEraseFailuresNotifEnable = 16;
  public static final int colEpcgAntRdPntEraseFailuresNotifLevel = 17;
  public static final int colEpcgAntRdPntLockOperations = 18;
  public static final int colEpcgAntRdPntLockFailures = 19;
  public static final int colEpcgAntRdPntLockFailuresNotifEnable = 20;
  public static final int colEpcgAntRdPntLockFailuresNotifLevel = 21;
  public static final int colEpcgAntRdPntPowerLevel = 22;
  public static final int colEpcgAntRdPntNoiseLevel = 23;
  public static final int colEpcgAntRdPntTimeEnergized = 24;
  public static final int colEpcgAntRdPntMemoryReadOperations = 25;
  public static final int colEpcgAntRdPntReadFailureSuppressInterval = 26;
  public static final int colEpcgAntRdPntReadFailureSuppressions = 27;
  public static final int colEpcgAntRdPntWriteFailureSuppressInterval = 28;
  public static final int colEpcgAntRdPntWriteFailureSuppressions = 29;
  public static final int colEpcgAntRdPntKillFailureSuppressInterval = 30;
  public static final int colEpcgAntRdPntKillFailureSuppressions = 31;
  public static final int colEpcgAntRdPntEraseFailureSuppressInterval = 32;
  public static final int colEpcgAntRdPntEraseFailureSuppressions = 33;
  public static final int colEpcgAntRdPntLockFailureSuppressInterval = 34;
  public static final int colEpcgAntRdPntLockFailureSuppressions = 35;

  // Column index definitions for epcgAntReadPointEntry:
  public static final int idxEpcgAntRdPntTagsIdentified = 0;
  public static final int idxEpcgAntRdPntTagsNotIdentified = 1;
  public static final int idxEpcgAntRdPntMemoryReadFailures = 2;
  public static final int idxEpcgAntRdPntReadFailureNotifEnable = 3;
  public static final int idxEpcgAntRdPntReadFailureNotifLevel = 4;
  public static final int idxEpcgAntRdPntWriteOperations = 5;
  public static final int idxEpcgAntRdPntWriteFailures = 6;
  public static final int idxEpcgAntRdPntWriteFailuresNotifEnable = 7;
  public static final int idxEpcgAntRdPntWriteFailuresNotifLevel = 8;
  public static final int idxEpcgAntRdPntKillOperations = 9;
  public static final int idxEpcgAntRdPntKillFailures = 10;
  public static final int idxEpcgAntRdPntKillFailuresNotifEnable = 11;
  public static final int idxEpcgAntRdPntKillFailuresNotifLevel = 12;
  public static final int idxEpcgAntRdPntEraseOperations = 13;
  public static final int idxEpcgAntRdPntEraseFailures = 14;
  public static final int idxEpcgAntRdPntEraseFailuresNotifEnable = 15;
  public static final int idxEpcgAntRdPntEraseFailuresNotifLevel = 16;
  public static final int idxEpcgAntRdPntLockOperations = 17;
  public static final int idxEpcgAntRdPntLockFailures = 18;
  public static final int idxEpcgAntRdPntLockFailuresNotifEnable = 19;
  public static final int idxEpcgAntRdPntLockFailuresNotifLevel = 20;
  public static final int idxEpcgAntRdPntPowerLevel = 21;
  public static final int idxEpcgAntRdPntNoiseLevel = 22;
  public static final int idxEpcgAntRdPntTimeEnergized = 23;
  public static final int idxEpcgAntRdPntMemoryReadOperations = 24;
  public static final int idxEpcgAntRdPntReadFailureSuppressInterval = 25;
  public static final int idxEpcgAntRdPntReadFailureSuppressions = 26;
  public static final int idxEpcgAntRdPntWriteFailureSuppressInterval = 27;
  public static final int idxEpcgAntRdPntWriteFailureSuppressions = 28;
  public static final int idxEpcgAntRdPntKillFailureSuppressInterval = 29;
  public static final int idxEpcgAntRdPntKillFailureSuppressions = 30;
  public static final int idxEpcgAntRdPntEraseFailureSuppressInterval = 31;
  public static final int idxEpcgAntRdPntEraseFailureSuppressions = 32;
  public static final int idxEpcgAntRdPntLockFailureSuppressInterval = 33;
  public static final int idxEpcgAntRdPntLockFailureSuppressions = 34;

  private static final MOTableSubIndex[] epcgAntReadPointEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex epcgAntReadPointEntryIndex = 
      moFactory.createIndex(epcgAntReadPointEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=epcgAntReadPointEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             epcgAntReadPointEntry;
  private MOMutableTableModel epcgAntReadPointEntryModel;
  public static final OID oidEpcgIoPortEntry = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,4,1,1 });
    
  // Column sub-identifier definitions for epcgIoPortEntry:
  public static final int colEpcgIoPortName = 2;
  public static final int colEpcgIoPortAdminStatus = 3;
  public static final int colEpcgIoPortOperStatus = 4;
  public static final int colEpcgIoPortOperStatusNotifEnable = 5;
  public static final int colEpcgIoPortOperStatusNotifLevel = 6;
  public static final int colEpcgIoPortOperStatusNotifFromState = 7;
  public static final int colEpcgIoPortOperStatusNotifToState = 8;
  public static final int colEpcgIoPortDescription = 9;
  public static final int colEpcgIoPortOperStatusPrior = 10;
  public static final int colEpcgIoPortOperStateSuppressInterval = 11;
  public static final int colEpcgIoPortOperStateSuppressions = 12;

  // Column index definitions for epcgIoPortEntry:
  public static final int idxEpcgIoPortName = 0;
  public static final int idxEpcgIoPortAdminStatus = 1;
  public static final int idxEpcgIoPortOperStatus = 2;
  public static final int idxEpcgIoPortOperStatusNotifEnable = 3;
  public static final int idxEpcgIoPortOperStatusNotifLevel = 4;
  public static final int idxEpcgIoPortOperStatusNotifFromState = 5;
  public static final int idxEpcgIoPortOperStatusNotifToState = 6;
  public static final int idxEpcgIoPortDescription = 7;
  public static final int idxEpcgIoPortOperStatusPrior = 8;
  public static final int idxEpcgIoPortOperStateSuppressInterval = 9;
  public static final int idxEpcgIoPortOperStateSuppressions = 10;

  private static final MOTableSubIndex[] epcgIoPortEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex epcgIoPortEntryIndex = 
      moFactory.createIndex(epcgIoPortEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=epcgIoPortEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             epcgIoPortEntry;
  private MOMutableTableModel epcgIoPortEntryModel;
  public static final OID oidEpcgSourceEntry = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,5,1,1 });
    
  // Column sub-identifier definitions for epcgSourceEntry:
  public static final int colEpcgSrcName = 2;
  public static final int colEpcgSrcReadCyclesPerTrigger = 3;
  public static final int colEpcgSrcReadDutyCycle = 4;
  public static final int colEpcgSrcReadTimeout = 5;
  public static final int colEpcgSrcGlimpsedTimeout = 6;
  public static final int colEpcgSrcObservedThreshold = 7;
  public static final int colEpcgSrcObservedTimeout = 8;
  public static final int colEpcgSrcLostTimeout = 9;
  public static final int colEpcgSrcUnknownToGlimpsedTrans = 10;
  public static final int colEpcgSrcGlimpsedToUnknownTrans = 11;
  public static final int colEpcgSrcGlimpsedToObservedTrans = 12;
  public static final int colEpcgSrcObservedToLostTrans = 13;
  public static final int colEpcgSrcLostToGlimpsedTrans = 14;
  public static final int colEpcgSrcLostToUnknownTrans = 15;
  public static final int colEpcgSrcAdminStatus = 16;
  public static final int colEpcgSrcOperStatus = 17;
  public static final int colEpcgSrcOperStatusNotifEnable = 18;
  public static final int colEpcgSrcOperStatusNotifFromState = 19;
  public static final int colEpcgSrcOperStatusNotifToState = 20;
  public static final int colEpcgSrcOperStatusNotifyLevel = 21;
  public static final int colEpcgSrcSupportsWriteOperations = 22;
  public static final int colEpcgSrcOperStatusPrior = 23;
  public static final int colEpcgSrcOperStateSuppressInterval = 24;
  public static final int colEpcgSrcOperStateSuppressions = 25;

  // Column index definitions for epcgSourceEntry:
  public static final int idxEpcgSrcName = 0;
  public static final int idxEpcgSrcReadCyclesPerTrigger = 1;
  public static final int idxEpcgSrcReadDutyCycle = 2;
  public static final int idxEpcgSrcReadTimeout = 3;
  public static final int idxEpcgSrcGlimpsedTimeout = 4;
  public static final int idxEpcgSrcObservedThreshold = 5;
  public static final int idxEpcgSrcObservedTimeout = 6;
  public static final int idxEpcgSrcLostTimeout = 7;
  public static final int idxEpcgSrcUnknownToGlimpsedTrans = 8;
  public static final int idxEpcgSrcGlimpsedToUnknownTrans = 9;
  public static final int idxEpcgSrcGlimpsedToObservedTrans = 10;
  public static final int idxEpcgSrcObservedToLostTrans = 11;
  public static final int idxEpcgSrcLostToGlimpsedTrans = 12;
  public static final int idxEpcgSrcLostToUnknownTrans = 13;
  public static final int idxEpcgSrcAdminStatus = 14;
  public static final int idxEpcgSrcOperStatus = 15;
  public static final int idxEpcgSrcOperStatusNotifEnable = 16;
  public static final int idxEpcgSrcOperStatusNotifFromState = 17;
  public static final int idxEpcgSrcOperStatusNotifToState = 18;
  public static final int idxEpcgSrcOperStatusNotifyLevel = 19;
  public static final int idxEpcgSrcSupportsWriteOperations = 20;
  public static final int idxEpcgSrcOperStatusPrior = 21;
  public static final int idxEpcgSrcOperStateSuppressInterval = 22;
  public static final int idxEpcgSrcOperStateSuppressions = 23;

  private static final MOTableSubIndex[] epcgSourceEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex epcgSourceEntryIndex = 
      moFactory.createIndex(epcgSourceEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=epcgSourceEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             epcgSourceEntry;
  private MOMutableTableModel epcgSourceEntryModel;
  public static final OID oidEpcgRdPntSrcEntry = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,5,3,1 });
    
  // Column sub-identifier definitions for epcgRdPntSrcEntry:
  public static final int colEpcgRdPntSrcRowStatus = 1;

  // Column index definitions for epcgRdPntSrcEntry:
  public static final int idxEpcgRdPntSrcRowStatus = 0;

  private static final MOTableSubIndex[] epcgRdPntSrcEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1),
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex epcgRdPntSrcEntryIndex = 
      moFactory.createIndex(epcgRdPntSrcEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=epcgRdPntSrcEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             epcgRdPntSrcEntry;
  private MOMutableTableModel epcgRdPntSrcEntryModel;
  public static final OID oidEpcgNotifChanSrcEntry = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,5,4,1 });
    
  // Column sub-identifier definitions for epcgNotifChanSrcEntry:
  public static final int colEpcgNotifChanSrcRowStatus = 1;

  // Column index definitions for epcgNotifChanSrcEntry:
  public static final int idxEpcgNotifChanSrcRowStatus = 0;

  private static final MOTableSubIndex[] epcgNotifChanSrcEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1),
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex epcgNotifChanSrcEntryIndex = 
      moFactory.createIndex(epcgNotifChanSrcEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=epcgNotifChanSrcEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             epcgNotifChanSrcEntry;
  private MOMutableTableModel epcgNotifChanSrcEntryModel;
  public static final OID oidEpcgNotificationChannelEntry = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,6,1,1 });
    
  // Column sub-identifier definitions for epcgNotificationChannelEntry:
  public static final int colEpcgNotifChanName = 2;
  public static final int colEpcgNotifChanAddressType = 3;
  public static final int colEpcgNotifChanAddress = 4;
  public static final int colEpcgNotifChanLastAttempt = 5;
  public static final int colEpcgNotifChanLastSuccess = 6;
  public static final int colEpcgNotifChanAdminStatus = 7;
  public static final int colEpcgNotifChanOperStatus = 8;
  public static final int colEpcgNotifChanOperNotifEnable = 9;
  public static final int colEpcgNotifChanOperNotifLevel = 10;
  public static final int colEpcgNotifChanOperNotifFromState = 11;
  public static final int colEpcgNotifChanOperNotifToState = 12;
  public static final int colEpcgNotifChanOperStatusPrior = 13;
  public static final int colEpcgNotifChanOperStateSuppressInterval = 14;
  public static final int colEpcgNotifChanOperStateSuppressions = 15;

  // Column index definitions for epcgNotificationChannelEntry:
  public static final int idxEpcgNotifChanName = 0;
  public static final int idxEpcgNotifChanAddressType = 1;
  public static final int idxEpcgNotifChanAddress = 2;
  public static final int idxEpcgNotifChanLastAttempt = 3;
  public static final int idxEpcgNotifChanLastSuccess = 4;
  public static final int idxEpcgNotifChanAdminStatus = 5;
  public static final int idxEpcgNotifChanOperStatus = 6;
  public static final int idxEpcgNotifChanOperNotifEnable = 7;
  public static final int idxEpcgNotifChanOperNotifLevel = 8;
  public static final int idxEpcgNotifChanOperNotifFromState = 9;
  public static final int idxEpcgNotifChanOperNotifToState = 10;
  public static final int idxEpcgNotifChanOperStatusPrior = 11;
  public static final int idxEpcgNotifChanOperStateSuppressInterval = 12;
  public static final int idxEpcgNotifChanOperStateSuppressions = 13;

  private static final MOTableSubIndex[] epcgNotificationChannelEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex epcgNotificationChannelEntryIndex = 
      moFactory.createIndex(epcgNotificationChannelEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=epcgNotificationChannelEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             epcgNotificationChannelEntry;
  private MOMutableTableModel epcgNotificationChannelEntryModel;
  public static final OID oidEpcgTriggerEntry = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,7,1,1 });
    
  // Column sub-identifier definitions for epcgTriggerEntry:
  public static final int colEpcgTrigName = 2;
  public static final int colEpcgTrigType = 3;
  public static final int colEpcgTrigParameters = 4;
  public static final int colEpcgTriggerMatches = 5;
  public static final int colEpcgTrigIoPort = 6;

  // Column index definitions for epcgTriggerEntry:
  public static final int idxEpcgTrigName = 0;
  public static final int idxEpcgTrigType = 1;
  public static final int idxEpcgTrigParameters = 2;
  public static final int idxEpcgTriggerMatches = 3;
  public static final int idxEpcgTrigIoPort = 4;

  private static final MOTableSubIndex[] epcgTriggerEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex epcgTriggerEntryIndex = 
      moFactory.createIndex(epcgTriggerEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=epcgTriggerEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             epcgTriggerEntry;
  private MOMutableTableModel epcgTriggerEntryModel;
  public static final OID oidEpcgNotifTrigEntry = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,7,2,1 });
    
  // Column sub-identifier definitions for epcgNotifTrigEntry:
  public static final int colEpcgNotifTrigRowStatus = 1;

  // Column index definitions for epcgNotifTrigEntry:
  public static final int idxEpcgNotifTrigRowStatus = 0;

  private static final MOTableSubIndex[] epcgNotifTrigEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1),
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex epcgNotifTrigEntryIndex = 
      moFactory.createIndex(epcgNotifTrigEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=epcgNotifTrigEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             epcgNotifTrigEntry;
  private MOMutableTableModel epcgNotifTrigEntryModel;
  public static final OID oidEpcgReadTrigEntry = 
    new OID(new int[] { 1,3,6,1,4,1,22695,1,1,1,7,3,1 });
    
  // Column sub-identifier definitions for epcgReadTrigEntry:
  public static final int colEpcgReadTrigRowStatus = 1;

  // Column index definitions for epcgReadTrigEntry:
  public static final int idxEpcgReadTrigRowStatus = 0;

  private static final MOTableSubIndex[] epcgReadTrigEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1),
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex epcgReadTrigEntryIndex = 
      moFactory.createIndex(epcgReadTrigEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=epcgReadTrigEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             epcgReadTrigEntry;
  private MOMutableTableModel epcgReadTrigEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  private EpcglobalReaderMib() {
    epcgRdrDevDescription = 
      moFactory.createScalar(oidEpcgRdrDevDescription,
                             MOAccessImpl.ACCESS_READ_ONLY, new OctetString(),
                             TC_MODULE_SNMP_FRAMEWORK_MIB, TC_SNMPADMINSTRING);
    epcgRdrDevRole = 
      moFactory.createScalar(oidEpcgRdrDevRole,
                             MOAccessImpl.ACCESS_READ_ONLY, new OctetString(),
                             TC_MODULE_SNMP_FRAMEWORK_MIB, TC_SNMPADMINSTRING);
    epcgRdrDevEpc = 
      moFactory.createScalar(oidEpcgRdrDevEpc,
                             MOAccessImpl.ACCESS_READ_ONLY, new OctetString(),
                             TC_MODULE_SNMP_FRAMEWORK_MIB, TC_SNMPADMINSTRING);
    epcgRdrDevSerialNumber = 
      moFactory.createScalar(oidEpcgRdrDevSerialNumber,
                             MOAccessImpl.ACCESS_READ_ONLY, new OctetString(),
                             TC_MODULE_SNMP_FRAMEWORK_MIB, TC_SNMPADMINSTRING);
    epcgRdrDevTimeUtc = 
      moFactory.createScalar(oidEpcgRdrDevTimeUtc,
                             MOAccessImpl.ACCESS_READ_ONLY, new OctetString(),
                             TC_MODULE_EPCGLOBAL_SMI_MIB, TC_DATEANDTIME);
    epcgRdrDevCurrentSource = 
      moFactory.createScalar(oidEpcgRdrDevCurrentSource,
                             MOAccessImpl.ACCESS_READ_ONLY, new OID(),
                             TC_MODULE_EPCGLOBAL_SMI_MIB, TC_ROWPOINTER);
    epcgRdrDevReboot = 
      new EpcgRdrDevReboot(oidEpcgRdrDevReboot, MOAccessImpl.ACCESS_READ_WRITE);
    epcgRdrDevReboot.addMOValueValidationListener(new EpcgRdrDevRebootValidator());
    epcgRdrDevResetStatistics = 
      new EpcgRdrDevResetStatistics(oidEpcgRdrDevResetStatistics, MOAccessImpl.ACCESS_READ_WRITE);
    epcgRdrDevResetStatistics.addMOValueValidationListener(new EpcgRdrDevResetStatisticsValidator());
    epcgRdrDevResetTimestamp = 
      moFactory.createScalar(oidEpcgRdrDevResetTimestamp,
                             MOAccessImpl.ACCESS_READ_ONLY, new OctetString(),
                             TC_MODULE_EPCGLOBAL_SMI_MIB, TC_DATEANDTIME);
    epcgRdrDevNormalizePowerLevel = 
      moFactory.createScalar(oidEpcgRdrDevNormalizePowerLevel,
                             MOAccessImpl.ACCESS_READ_ONLY, new Integer32(2),
//                             MOAccessImpl.ACCESS_READ_ONLY, new Integer32(),
                             TC_MODULE_EPCGLOBAL_SMI_MIB, TC_TRUTHVALUE);
    epcgRdrDevNormalizeNoiseLevel = 
      moFactory.createScalar(oidEpcgRdrDevNormalizeNoiseLevel,
                             MOAccessImpl.ACCESS_READ_ONLY, new Integer32(2),
//                             MOAccessImpl.ACCESS_READ_ONLY, new Integer32(),
                             TC_MODULE_EPCGLOBAL_SMI_MIB, TC_TRUTHVALUE);
    epcgRdrDevOperStatus = 
      moFactory.createScalar(oidEpcgRdrDevOperStatus,
                             MOAccessImpl.ACCESS_READ_ONLY, new Integer32(),
                             TC_MODULE_EPCGLOBAL_READER_MIB, TC_EPCGOPERATIONALSTATUS);
    epcgRdrDevOperStateEnable = 
      new EpcgRdrDevOperStateEnable(oidEpcgRdrDevOperStateEnable, MOAccessImpl.ACCESS_READ_WRITE);
    epcgRdrDevOperStateEnable.addMOValueValidationListener(new EpcgRdrDevOperStateEnableValidator());
    epcgRdrDevOperNotifFromState = 
      new EpcgRdrDevOperNotifFromState(oidEpcgRdrDevOperNotifFromState, MOAccessImpl.ACCESS_READ_WRITE);
    epcgRdrDevOperNotifFromState.addMOValueValidationListener(new EpcgRdrDevOperNotifFromStateValidator());
    epcgRdrDevOperNotifToState = 
      new EpcgRdrDevOperNotifToState(oidEpcgRdrDevOperNotifToState, MOAccessImpl.ACCESS_READ_WRITE);
    epcgRdrDevOperNotifToState.addMOValueValidationListener(new EpcgRdrDevOperNotifToStateValidator());
    epcgRdrDevOperNotifStateLevel = 
      new EpcgRdrDevOperNotifStateLevel(oidEpcgRdrDevOperNotifStateLevel, MOAccessImpl.ACCESS_READ_WRITE);
    epcgRdrDevOperNotifStateLevel.addMOValueValidationListener(new EpcgRdrDevOperNotifStateLevelValidator());
    epcgRdrDevOperStateSuppressInterval = 
      new EpcgRdrDevOperStateSuppressInterval(oidEpcgRdrDevOperStateSuppressInterval, MOAccessImpl.ACCESS_READ_WRITE);
    epcgRdrDevOperStateSuppressInterval.addMOValueValidationListener(new EpcgRdrDevOperStateSuppressIntervalValidator());
    epcgRdrDevOperStateSuppressions = 
      moFactory.createScalar(oidEpcgRdrDevOperStateSuppressions,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    epcgRdrDevFreeMemory = 
      moFactory.createScalar(oidEpcgRdrDevFreeMemory,
                             MOAccessImpl.ACCESS_READ_ONLY, new Gauge32());
    epcgRdrDevFreeMemoryNotifEnable = 
      new EpcgRdrDevFreeMemoryNotifEnable(oidEpcgRdrDevFreeMemoryNotifEnable, MOAccessImpl.ACCESS_READ_WRITE);
    epcgRdrDevFreeMemoryNotifEnable.addMOValueValidationListener(new EpcgRdrDevFreeMemoryNotifEnableValidator());
    epcgRdrDevFreeMemoryNotifLevel = 
      new EpcgRdrDevFreeMemoryNotifLevel(oidEpcgRdrDevFreeMemoryNotifLevel, MOAccessImpl.ACCESS_READ_WRITE);
    epcgRdrDevFreeMemoryNotifLevel.addMOValueValidationListener(new EpcgRdrDevFreeMemoryNotifLevelValidator());
    epcgRdrDevFreeMemoryOnsetThreshold = 
      new EpcgRdrDevFreeMemoryOnsetThreshold(oidEpcgRdrDevFreeMemoryOnsetThreshold, MOAccessImpl.ACCESS_READ_WRITE);
    epcgRdrDevFreeMemoryOnsetThreshold.addMOValueValidationListener(new EpcgRdrDevFreeMemoryOnsetThresholdValidator());
    epcgRdrDevFreeMemoryAbateThreshold = 
      new EpcgRdrDevFreeMemoryAbateThreshold(oidEpcgRdrDevFreeMemoryAbateThreshold, MOAccessImpl.ACCESS_READ_WRITE);
    epcgRdrDevFreeMemoryAbateThreshold.addMOValueValidationListener(new EpcgRdrDevFreeMemoryAbateThresholdValidator());
    epcgRdrDevFreeMemoryStatus = 
      moFactory.createScalar(oidEpcgRdrDevFreeMemoryStatus,
                             MOAccessImpl.ACCESS_READ_ONLY, new Integer32());
    epcgRdrDevMemStateSuppressInterval = 
      new EpcgRdrDevMemStateSuppressInterval(oidEpcgRdrDevMemStateSuppressInterval, MOAccessImpl.ACCESS_READ_WRITE);
    epcgRdrDevMemStateSuppressInterval.addMOValueValidationListener(new EpcgRdrDevMemStateSuppressIntervalValidator());
    epcgRdrDevMemStateSuppressions = 
      moFactory.createScalar(oidEpcgRdrDevMemStateSuppressions,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    createEpcgGlobalCountersEntry();
    createEpcgReaderServerEntry();
    createEpcgReadPointEntry();
    createEpcgAntReadPointEntry();
    createEpcgIoPortEntry();
    createEpcgSourceEntry();
    createEpcgRdPntSrcEntry();
    createEpcgNotifChanSrcEntry();
    createEpcgNotificationChannelEntry();
    createEpcgTriggerEntry();
    createEpcgNotifTrigEntry();
    createEpcgReadTrigEntry();
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

//--AgentGen BEGIN=_CONSTRUCTORS
//--AgentGen END


  public MOTable getEpcgGlobalCountersEntry() {
    return epcgGlobalCountersEntry;
  }


  private void createEpcgGlobalCountersEntry() {
    MOColumn[] epcgGlobalCountersEntryColumns = new MOColumn[1];
    epcgGlobalCountersEntryColumns[idxEpcgGlobalCountersData] = 
      moFactory.createColumn(colEpcgGlobalCountersData, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    
    epcgGlobalCountersEntryModel = new DefaultMOMutableTableModel();
//    epcgGlobalCountersEntryModel.setRowFactory(new EpcgGlobalCountersEntryRowFactory());
    epcgGlobalCountersEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE));
//    epcgGlobalCountersEntry = 
//      moFactory.createTable(oidEpcgGlobalCountersEntry,
//                            epcgGlobalCountersEntryIndex,
//                            epcgGlobalCountersEntryColumns,
//                            epcgGlobalCountersEntryModel);
    epcgGlobalCountersEntry = 
      new SnmpTable(TableTypeEnum.EPCG_GLOBAL_COUNTERS_TABLE,
                    oidEpcgGlobalCountersEntry,
                    epcgGlobalCountersEntryIndex,
                    epcgGlobalCountersEntryColumns,
                    epcgGlobalCountersEntryModel);
  }

  public MOTable getEpcgReaderServerEntry() {
    return epcgReaderServerEntry;
  }


  private void createEpcgReaderServerEntry() {
    MOColumn[] epcgReaderServerEntryColumns = new MOColumn[3];
    epcgReaderServerEntryColumns[idxEpcgReaderServerAddressType] = 
      new MOMutableColumn(colEpcgReaderServerAddressType,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_CREATE,
                          null);
    ValueConstraint epcgReaderServerAddressTypeVC = new EnumerationConstraint(
      new int[] { EpcgReaderServerAddressTypeEnum.unknown,
                  EpcgReaderServerAddressTypeEnum.ipv4,
                  EpcgReaderServerAddressTypeEnum.ipv6,
                  EpcgReaderServerAddressTypeEnum.ipv4z,
                  EpcgReaderServerAddressTypeEnum.ipv6z,
                  EpcgReaderServerAddressTypeEnum.dns });
    ((MOMutableColumn)epcgReaderServerEntryColumns[idxEpcgReaderServerAddressType]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgReaderServerAddressTypeVC));                                  
    ((MOMutableColumn)epcgReaderServerEntryColumns[idxEpcgReaderServerAddressType]).
      addMOValueValidationListener(new EpcgReaderServerAddressTypeValidator());
    epcgReaderServerEntryColumns[idxEpcgReaderServerAddress] = 
      new MOMutableColumn(colEpcgReaderServerAddress,
                          SMIConstants.SYNTAX_OCTET_STRING,
                          MOAccessImpl.ACCESS_READ_CREATE,
                          null);
    ValueConstraint epcgReaderServerAddressVC = new ConstraintsImpl();
    ((ConstraintsImpl)epcgReaderServerAddressVC).add(new Constraint(0, 255));
    ((MOMutableColumn)epcgReaderServerEntryColumns[idxEpcgReaderServerAddress]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgReaderServerAddressVC));                                  
    ((MOMutableColumn)epcgReaderServerEntryColumns[idxEpcgReaderServerAddress]).
      addMOValueValidationListener(new EpcgReaderServerAddressValidator());
    epcgReaderServerEntryColumns[idxEpcgReaderServerRowStatus] = 
//      new RowStatus(colEpcgReaderServerRowStatus);
      new RowStatus(colEpcgReaderServerRowStatus, MOAccessImpl.ACCESS_READ_CREATE);
    ValueConstraint epcgReaderServerRowStatusVC = new EnumerationConstraint(
      new int[] { EpcgReaderServerRowStatusEnum.active,
                  EpcgReaderServerRowStatusEnum.notInService,
                  EpcgReaderServerRowStatusEnum.notReady,
                  EpcgReaderServerRowStatusEnum.createAndGo,
                  EpcgReaderServerRowStatusEnum.createAndWait,
                  EpcgReaderServerRowStatusEnum.destroy });
    ((MOMutableColumn)epcgReaderServerEntryColumns[idxEpcgReaderServerRowStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgReaderServerRowStatusVC));                                  
    ((MOMutableColumn)epcgReaderServerEntryColumns[idxEpcgReaderServerRowStatus]).
      addMOValueValidationListener(new EpcgReaderServerRowStatusValidator());
    
    epcgReaderServerEntryModel = new DefaultMOMutableTableModel();
//    epcgReaderServerEntryModel.setRowFactory(new EpcgReaderServerEntryRowFactory());
    epcgReaderServerEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.EPCG_READER_SERVER_TABLE));
//    epcgReaderServerEntry = 
//      moFactory.createTable(oidEpcgReaderServerEntry,
//                            epcgReaderServerEntryIndex,
//                            epcgReaderServerEntryColumns,
//                            epcgReaderServerEntryModel);
    epcgReaderServerEntry =
      new SnmpTable(TableTypeEnum.EPCG_READER_SERVER_TABLE,
                    oidEpcgReaderServerEntry,
                    epcgReaderServerEntryIndex,
                    epcgReaderServerEntryColumns,
                    epcgReaderServerEntryModel);
  }

  public MOTable getEpcgReadPointEntry() {
    return epcgReadPointEntry;
  }


  private void createEpcgReadPointEntry() {
    MOColumn[] epcgReadPointEntryColumns = new MOColumn[11];
    epcgReadPointEntryColumns[idxEpcgReadPointName] = 
      moFactory.createColumn(colEpcgReadPointName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgReadPointEntryColumns[idxEpcgReadPointDescription] = 
      moFactory.createColumn(colEpcgReadPointDescription, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgReadPointEntryColumns[idxEpcgReadPointAdminStatus] = 
      new MOMutableColumn(colEpcgReadPointAdminStatus,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgReadPointAdminStatusVC = new EnumerationConstraint(
      new int[] { EpcgReadPointAdminStatusEnum.up,
                  EpcgReadPointAdminStatusEnum.down });
    ((MOMutableColumn)epcgReadPointEntryColumns[idxEpcgReadPointAdminStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgReadPointAdminStatusVC));                                  
    ((MOMutableColumn)epcgReadPointEntryColumns[idxEpcgReadPointAdminStatus]).
      addMOValueValidationListener(new EpcgReadPointAdminStatusValidator());
    epcgReadPointEntryColumns[idxEpcgReadPointOperStatus] = 
      moFactory.createColumn(colEpcgReadPointOperStatus, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgReadPointEntryColumns[idxEpcgReadPointOperStateNotifyEnable] = 
      new MOMutableColumn(colEpcgReadPointOperStateNotifyEnable,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgReadPointOperStateNotifyEnableVC = new EnumerationConstraint(
      new int[] { EpcgReadPointOperStateNotifyEnableEnum._true,
                  EpcgReadPointOperStateNotifyEnableEnum._false });
    ((MOMutableColumn)epcgReadPointEntryColumns[idxEpcgReadPointOperStateNotifyEnable]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgReadPointOperStateNotifyEnableVC));                                  
    ((MOMutableColumn)epcgReadPointEntryColumns[idxEpcgReadPointOperStateNotifyEnable]).
      addMOValueValidationListener(new EpcgReadPointOperStateNotifyEnableValidator());
    epcgReadPointEntryColumns[idxEpcgReadPointOperNotifyFromState] = 
      new MOMutableColumn(colEpcgReadPointOperNotifyFromState,
                          SMIConstants.SYNTAX_OCTET_STRING,
//                          SMIConstants.SYNTAX_BITS,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new OctetString(new byte[] { (byte)-16 }));
//    ValueConstraint epcgReadPointOperNotifyFromStateVC = new EnumerationConstraint(
//      new int[] { EpcgReadPointOperNotifyFromStateEnum.unknown,
//                  EpcgReadPointOperNotifyFromStateEnum.other,
//                  EpcgReadPointOperNotifyFromStateEnum.up,
//                  EpcgReadPointOperNotifyFromStateEnum.down });
    ValueConstraint epcgReadPointOperNotifyFromStateVC = new BitsEnumerationConstraint();
    ((MOMutableColumn)epcgReadPointEntryColumns[idxEpcgReadPointOperNotifyFromState]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgReadPointOperNotifyFromStateVC));                                  
    ((MOMutableColumn)epcgReadPointEntryColumns[idxEpcgReadPointOperNotifyFromState]).
      addMOValueValidationListener(new EpcgReadPointOperNotifyFromStateValidator());
    epcgReadPointEntryColumns[idxEpcgReadPointOperNotifyToState] = 
      new MOMutableColumn(colEpcgReadPointOperNotifyToState,
                          SMIConstants.SYNTAX_OCTET_STRING,
//                          SMIConstants.SYNTAX_BITS,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new OctetString(new byte[] { (byte)-16 }));
//    ValueConstraint epcgReadPointOperNotifyToStateVC = new EnumerationConstraint(
//      new int[] { EpcgReadPointOperNotifyToStateEnum.unknown,
//                  EpcgReadPointOperNotifyToStateEnum.other,
//                  EpcgReadPointOperNotifyToStateEnum.up,
//                  EpcgReadPointOperNotifyToStateEnum.down });
    ValueConstraint epcgReadPointOperNotifyToStateVC = new BitsEnumerationConstraint();
    ((MOMutableColumn)epcgReadPointEntryColumns[idxEpcgReadPointOperNotifyToState]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgReadPointOperNotifyToStateVC));                                  
    ((MOMutableColumn)epcgReadPointEntryColumns[idxEpcgReadPointOperNotifyToState]).
      addMOValueValidationListener(new EpcgReadPointOperNotifyToStateValidator());
    epcgReadPointEntryColumns[idxEpcgReadPointOperNotifyStateLevel] = 
      new MOMutableColumn(colEpcgReadPointOperNotifyStateLevel,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new Integer32(3));
    ValueConstraint epcgReadPointOperNotifyStateLevelVC = new EnumerationConstraint(
      new int[] { EpcgReadPointOperNotifyStateLevelEnum.emergency,
                  EpcgReadPointOperNotifyStateLevelEnum.alert,
                  EpcgReadPointOperNotifyStateLevelEnum.critical,
                  EpcgReadPointOperNotifyStateLevelEnum.error,
                  EpcgReadPointOperNotifyStateLevelEnum.warning,
                  EpcgReadPointOperNotifyStateLevelEnum.notice,
                  EpcgReadPointOperNotifyStateLevelEnum.informational,
                  EpcgReadPointOperNotifyStateLevelEnum.debug });
    ((MOMutableColumn)epcgReadPointEntryColumns[idxEpcgReadPointOperNotifyStateLevel]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgReadPointOperNotifyStateLevelVC));                                  
    ((MOMutableColumn)epcgReadPointEntryColumns[idxEpcgReadPointOperNotifyStateLevel]).
      addMOValueValidationListener(new EpcgReadPointOperNotifyStateLevelValidator());
    epcgReadPointEntryColumns[idxEpcgReadPointPriorOperStatus] = 
      moFactory.createColumn(colEpcgReadPointPriorOperStatus, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_FOR_NOTIFY);
    epcgReadPointEntryColumns[idxEpcgReadPointOperStateSuppressInterval] = 
      new MOMutableColumn(colEpcgReadPointOperStateSuppressInterval,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new UnsignedInteger32(0));
    ValueConstraint epcgReadPointOperStateSuppressIntervalVC = new ConstraintsImpl();
    ((ConstraintsImpl)epcgReadPointOperStateSuppressIntervalVC).add(new Constraint(0, 0));
    ((ConstraintsImpl)epcgReadPointOperStateSuppressIntervalVC).add(new Constraint(1, 3600));
    ((MOMutableColumn)epcgReadPointEntryColumns[idxEpcgReadPointOperStateSuppressInterval]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgReadPointOperStateSuppressIntervalVC));                                  
    ((MOMutableColumn)epcgReadPointEntryColumns[idxEpcgReadPointOperStateSuppressInterval]).
      addMOValueValidationListener(new EpcgReadPointOperStateSuppressIntervalValidator());
    epcgReadPointEntryColumns[idxEpcgReadPointOperStateSuppressions] = 
      moFactory.createColumn(colEpcgReadPointOperStateSuppressions, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    
    epcgReadPointEntryModel = new DefaultMOMutableTableModel();
//    epcgReadPointEntryModel.setRowFactory(new EpcgReadPointEntryRowFactory());
    epcgReadPointEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.EPCG_READ_POINT_TABLE));
//    epcgReadPointEntry = 
//      moFactory.createTable(oidEpcgReadPointEntry,
//                            epcgReadPointEntryIndex,
//                            epcgReadPointEntryColumns,
//                            epcgReadPointEntryModel);
    epcgReadPointEntry = 
      new SnmpTable(TableTypeEnum.EPCG_READ_POINT_TABLE,
                    oidEpcgReadPointEntry,
                    epcgReadPointEntryIndex,
                    epcgReadPointEntryColumns,
                    epcgReadPointEntryModel);
  }

  public MOTable getEpcgAntReadPointEntry() {
    return epcgAntReadPointEntry;
  }


  private void createEpcgAntReadPointEntry() {
    MOColumn[] epcgAntReadPointEntryColumns = new MOColumn[35];
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntTagsIdentified] = 
      moFactory.createColumn(colEpcgAntRdPntTagsIdentified, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntTagsNotIdentified] = 
      moFactory.createColumn(colEpcgAntRdPntTagsNotIdentified, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntMemoryReadFailures] = 
      moFactory.createColumn(colEpcgAntRdPntMemoryReadFailures, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntReadFailureNotifEnable] = 
      new MOMutableColumn(colEpcgAntRdPntReadFailureNotifEnable,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgAntRdPntReadFailureNotifEnableVC = new EnumerationConstraint(
      new int[] { EpcgAntRdPntReadFailureNotifEnableEnum._true,
                  EpcgAntRdPntReadFailureNotifEnableEnum._false });
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntReadFailureNotifEnable]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntReadFailureNotifEnableVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntReadFailureNotifEnable]).
      addMOValueValidationListener(new EpcgAntRdPntReadFailureNotifEnableValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntReadFailureNotifLevel] = 
      new MOMutableColumn(colEpcgAntRdPntReadFailureNotifLevel,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new Integer32(3));
    ValueConstraint epcgAntRdPntReadFailureNotifLevelVC = new EnumerationConstraint(
      new int[] { EpcgAntRdPntReadFailureNotifLevelEnum.emergency,
                  EpcgAntRdPntReadFailureNotifLevelEnum.alert,
                  EpcgAntRdPntReadFailureNotifLevelEnum.critical,
                  EpcgAntRdPntReadFailureNotifLevelEnum.error,
                  EpcgAntRdPntReadFailureNotifLevelEnum.warning,
                  EpcgAntRdPntReadFailureNotifLevelEnum.notice,
                  EpcgAntRdPntReadFailureNotifLevelEnum.informational,
                  EpcgAntRdPntReadFailureNotifLevelEnum.debug });
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntReadFailureNotifLevel]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntReadFailureNotifLevelVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntReadFailureNotifLevel]).
      addMOValueValidationListener(new EpcgAntRdPntReadFailureNotifLevelValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntWriteOperations] = 
      moFactory.createColumn(colEpcgAntRdPntWriteOperations, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntWriteFailures] = 
      moFactory.createColumn(colEpcgAntRdPntWriteFailures, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntWriteFailuresNotifEnable] = 
      new MOMutableColumn(colEpcgAntRdPntWriteFailuresNotifEnable,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgAntRdPntWriteFailuresNotifEnableVC = new EnumerationConstraint(
      new int[] { EpcgAntRdPntWriteFailuresNotifEnableEnum._true,
                  EpcgAntRdPntWriteFailuresNotifEnableEnum._false });
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntWriteFailuresNotifEnable]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntWriteFailuresNotifEnableVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntWriteFailuresNotifEnable]).
      addMOValueValidationListener(new EpcgAntRdPntWriteFailuresNotifEnableValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntWriteFailuresNotifLevel] = 
      new MOMutableColumn(colEpcgAntRdPntWriteFailuresNotifLevel,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new Integer32(5));
    ValueConstraint epcgAntRdPntWriteFailuresNotifLevelVC = new EnumerationConstraint(
      new int[] { EpcgAntRdPntWriteFailuresNotifLevelEnum.emergency,
                  EpcgAntRdPntWriteFailuresNotifLevelEnum.alert,
                  EpcgAntRdPntWriteFailuresNotifLevelEnum.critical,
                  EpcgAntRdPntWriteFailuresNotifLevelEnum.error,
                  EpcgAntRdPntWriteFailuresNotifLevelEnum.warning,
                  EpcgAntRdPntWriteFailuresNotifLevelEnum.notice,
                  EpcgAntRdPntWriteFailuresNotifLevelEnum.informational,
                  EpcgAntRdPntWriteFailuresNotifLevelEnum.debug });
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntWriteFailuresNotifLevel]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntWriteFailuresNotifLevelVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntWriteFailuresNotifLevel]).
      addMOValueValidationListener(new EpcgAntRdPntWriteFailuresNotifLevelValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntKillOperations] = 
      moFactory.createColumn(colEpcgAntRdPntKillOperations, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntKillFailures] = 
      moFactory.createColumn(colEpcgAntRdPntKillFailures, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntKillFailuresNotifEnable] = 
      new MOMutableColumn(colEpcgAntRdPntKillFailuresNotifEnable,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgAntRdPntKillFailuresNotifEnableVC = new EnumerationConstraint(
      new int[] { EpcgAntRdPntKillFailuresNotifEnableEnum._true,
                  EpcgAntRdPntKillFailuresNotifEnableEnum._false });
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntKillFailuresNotifEnable]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntKillFailuresNotifEnableVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntKillFailuresNotifEnable]).
      addMOValueValidationListener(new EpcgAntRdPntKillFailuresNotifEnableValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntKillFailuresNotifLevel] = 
      new MOMutableColumn(colEpcgAntRdPntKillFailuresNotifLevel,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new Integer32(5));
    ValueConstraint epcgAntRdPntKillFailuresNotifLevelVC = new EnumerationConstraint(
      new int[] { EpcgAntRdPntKillFailuresNotifLevelEnum.emergency,
                  EpcgAntRdPntKillFailuresNotifLevelEnum.alert,
                  EpcgAntRdPntKillFailuresNotifLevelEnum.critical,
                  EpcgAntRdPntKillFailuresNotifLevelEnum.error,
                  EpcgAntRdPntKillFailuresNotifLevelEnum.warning,
                  EpcgAntRdPntKillFailuresNotifLevelEnum.notice,
                  EpcgAntRdPntKillFailuresNotifLevelEnum.informational,
                  EpcgAntRdPntKillFailuresNotifLevelEnum.debug });
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntKillFailuresNotifLevel]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntKillFailuresNotifLevelVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntKillFailuresNotifLevel]).
      addMOValueValidationListener(new EpcgAntRdPntKillFailuresNotifLevelValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntEraseOperations] = 
      moFactory.createColumn(colEpcgAntRdPntEraseOperations, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntEraseFailures] = 
      moFactory.createColumn(colEpcgAntRdPntEraseFailures, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntEraseFailuresNotifEnable] = 
      new MOMutableColumn(colEpcgAntRdPntEraseFailuresNotifEnable,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgAntRdPntEraseFailuresNotifEnableVC = new EnumerationConstraint(
      new int[] { EpcgAntRdPntEraseFailuresNotifEnableEnum._true,
                  EpcgAntRdPntEraseFailuresNotifEnableEnum._false });
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntEraseFailuresNotifEnable]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntEraseFailuresNotifEnableVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntEraseFailuresNotifEnable]).
      addMOValueValidationListener(new EpcgAntRdPntEraseFailuresNotifEnableValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntEraseFailuresNotifLevel] = 
      new MOMutableColumn(colEpcgAntRdPntEraseFailuresNotifLevel,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new Integer32(5));
    ValueConstraint epcgAntRdPntEraseFailuresNotifLevelVC = new EnumerationConstraint(
      new int[] { EpcgAntRdPntEraseFailuresNotifLevelEnum.emergency,
                  EpcgAntRdPntEraseFailuresNotifLevelEnum.alert,
                  EpcgAntRdPntEraseFailuresNotifLevelEnum.critical,
                  EpcgAntRdPntEraseFailuresNotifLevelEnum.error,
                  EpcgAntRdPntEraseFailuresNotifLevelEnum.warning,
                  EpcgAntRdPntEraseFailuresNotifLevelEnum.notice,
                  EpcgAntRdPntEraseFailuresNotifLevelEnum.informational,
                  EpcgAntRdPntEraseFailuresNotifLevelEnum.debug });
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntEraseFailuresNotifLevel]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntEraseFailuresNotifLevelVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntEraseFailuresNotifLevel]).
      addMOValueValidationListener(new EpcgAntRdPntEraseFailuresNotifLevelValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntLockOperations] = 
      moFactory.createColumn(colEpcgAntRdPntLockOperations, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntLockFailures] = 
      moFactory.createColumn(colEpcgAntRdPntLockFailures, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntLockFailuresNotifEnable] = 
      new MOMutableColumn(colEpcgAntRdPntLockFailuresNotifEnable,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgAntRdPntLockFailuresNotifEnableVC = new EnumerationConstraint(
      new int[] { EpcgAntRdPntLockFailuresNotifEnableEnum._true,
                  EpcgAntRdPntLockFailuresNotifEnableEnum._false });
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntLockFailuresNotifEnable]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntLockFailuresNotifEnableVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntLockFailuresNotifEnable]).
      addMOValueValidationListener(new EpcgAntRdPntLockFailuresNotifEnableValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntLockFailuresNotifLevel] = 
      new MOMutableColumn(colEpcgAntRdPntLockFailuresNotifLevel,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new Integer32(5));
    ValueConstraint epcgAntRdPntLockFailuresNotifLevelVC = new EnumerationConstraint(
      new int[] { EpcgAntRdPntLockFailuresNotifLevelEnum.emergency,
                  EpcgAntRdPntLockFailuresNotifLevelEnum.alert,
                  EpcgAntRdPntLockFailuresNotifLevelEnum.critical,
                  EpcgAntRdPntLockFailuresNotifLevelEnum.error,
                  EpcgAntRdPntLockFailuresNotifLevelEnum.warning,
                  EpcgAntRdPntLockFailuresNotifLevelEnum.notice,
                  EpcgAntRdPntLockFailuresNotifLevelEnum.informational,
                  EpcgAntRdPntLockFailuresNotifLevelEnum.debug });
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntLockFailuresNotifLevel]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntLockFailuresNotifLevelVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntLockFailuresNotifLevel]).
      addMOValueValidationListener(new EpcgAntRdPntLockFailuresNotifLevelValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntPowerLevel] = 
      moFactory.createColumn(colEpcgAntRdPntPowerLevel, 
                             SMIConstants.SYNTAX_INTEGER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntNoiseLevel] = 
      moFactory.createColumn(colEpcgAntRdPntNoiseLevel, 
                             SMIConstants.SYNTAX_INTEGER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntTimeEnergized] = 
      moFactory.createColumn(colEpcgAntRdPntTimeEnergized, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntMemoryReadOperations] = 
      moFactory.createColumn(colEpcgAntRdPntMemoryReadOperations, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntReadFailureSuppressInterval] = 
      new MOMutableColumn(colEpcgAntRdPntReadFailureSuppressInterval,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new UnsignedInteger32(0));
    ValueConstraint epcgAntRdPntReadFailureSuppressIntervalVC = new ConstraintsImpl();
    ((ConstraintsImpl)epcgAntRdPntReadFailureSuppressIntervalVC).add(new Constraint(0, 0));
    ((ConstraintsImpl)epcgAntRdPntReadFailureSuppressIntervalVC).add(new Constraint(1, 3600));
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntReadFailureSuppressInterval]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntReadFailureSuppressIntervalVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntReadFailureSuppressInterval]).
      addMOValueValidationListener(new EpcgAntRdPntReadFailureSuppressIntervalValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntReadFailureSuppressions] = 
      moFactory.createColumn(colEpcgAntRdPntReadFailureSuppressions, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntWriteFailureSuppressInterval] = 
      new MOMutableColumn(colEpcgAntRdPntWriteFailureSuppressInterval,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new UnsignedInteger32(0));
    ValueConstraint epcgAntRdPntWriteFailureSuppressIntervalVC = new ConstraintsImpl();
    ((ConstraintsImpl)epcgAntRdPntWriteFailureSuppressIntervalVC).add(new Constraint(0, 0));
    ((ConstraintsImpl)epcgAntRdPntWriteFailureSuppressIntervalVC).add(new Constraint(1, 3600));
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntWriteFailureSuppressInterval]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntWriteFailureSuppressIntervalVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntWriteFailureSuppressInterval]).
      addMOValueValidationListener(new EpcgAntRdPntWriteFailureSuppressIntervalValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntWriteFailureSuppressions] = 
      moFactory.createColumn(colEpcgAntRdPntWriteFailureSuppressions, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntKillFailureSuppressInterval] = 
      new MOMutableColumn(colEpcgAntRdPntKillFailureSuppressInterval,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new UnsignedInteger32(0));
    ValueConstraint epcgAntRdPntKillFailureSuppressIntervalVC = new ConstraintsImpl();
    ((ConstraintsImpl)epcgAntRdPntKillFailureSuppressIntervalVC).add(new Constraint(0, 0));
    ((ConstraintsImpl)epcgAntRdPntKillFailureSuppressIntervalVC).add(new Constraint(1, 3600));
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntKillFailureSuppressInterval]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntKillFailureSuppressIntervalVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntKillFailureSuppressInterval]).
      addMOValueValidationListener(new EpcgAntRdPntKillFailureSuppressIntervalValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntKillFailureSuppressions] = 
      moFactory.createColumn(colEpcgAntRdPntKillFailureSuppressions, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntEraseFailureSuppressInterval] = 
      new MOMutableColumn(colEpcgAntRdPntEraseFailureSuppressInterval,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new UnsignedInteger32(0));
    ValueConstraint epcgAntRdPntEraseFailureSuppressIntervalVC = new ConstraintsImpl();
    ((ConstraintsImpl)epcgAntRdPntEraseFailureSuppressIntervalVC).add(new Constraint(0, 0));
    ((ConstraintsImpl)epcgAntRdPntEraseFailureSuppressIntervalVC).add(new Constraint(1, 3600));
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntEraseFailureSuppressInterval]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntEraseFailureSuppressIntervalVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntEraseFailureSuppressInterval]).
      addMOValueValidationListener(new EpcgAntRdPntEraseFailureSuppressIntervalValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntEraseFailureSuppressions] = 
      moFactory.createColumn(colEpcgAntRdPntEraseFailureSuppressions, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntLockFailureSuppressInterval] = 
      new MOMutableColumn(colEpcgAntRdPntLockFailureSuppressInterval,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new UnsignedInteger32(0));
    ValueConstraint epcgAntRdPntLockFailureSuppressIntervalVC = new ConstraintsImpl();
    ((ConstraintsImpl)epcgAntRdPntLockFailureSuppressIntervalVC).add(new Constraint(0, 0));
    ((ConstraintsImpl)epcgAntRdPntLockFailureSuppressIntervalVC).add(new Constraint(1, 3600));
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntLockFailureSuppressInterval]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgAntRdPntLockFailureSuppressIntervalVC));                                  
    ((MOMutableColumn)epcgAntReadPointEntryColumns[idxEpcgAntRdPntLockFailureSuppressInterval]).
      addMOValueValidationListener(new EpcgAntRdPntLockFailureSuppressIntervalValidator());
    epcgAntReadPointEntryColumns[idxEpcgAntRdPntLockFailureSuppressions] = 
      moFactory.createColumn(colEpcgAntRdPntLockFailureSuppressions, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    
    epcgAntReadPointEntryModel = new DefaultMOMutableTableModel();
//    epcgAntReadPointEntryModel.setRowFactory(new EpcgAntReadPointEntryRowFactory());
    epcgAntReadPointEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.EPCG_ANT_READ_POINT_TABLE));
//    epcgAntReadPointEntry = 
//      moFactory.createTable(oidEpcgAntReadPointEntry,
//                            epcgAntReadPointEntryIndex,
//                            epcgAntReadPointEntryColumns,
//                            epcgAntReadPointEntryModel);
    epcgAntReadPointEntry = 
      new SnmpTable(TableTypeEnum.EPCG_ANT_READ_POINT_TABLE,
                    oidEpcgAntReadPointEntry,
                    epcgAntReadPointEntryIndex,
                    epcgAntReadPointEntryColumns,
                    epcgAntReadPointEntryModel);
  }

  public MOTable getEpcgIoPortEntry() {
    return epcgIoPortEntry;
  }


  private void createEpcgIoPortEntry() {
    MOColumn[] epcgIoPortEntryColumns = new MOColumn[11];
    epcgIoPortEntryColumns[idxEpcgIoPortName] = 
      moFactory.createColumn(colEpcgIoPortName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgIoPortEntryColumns[idxEpcgIoPortAdminStatus] = 
      new MOMutableColumn(colEpcgIoPortAdminStatus,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgIoPortAdminStatusVC = new EnumerationConstraint(
      new int[] { EpcgIoPortAdminStatusEnum.up,
                  EpcgIoPortAdminStatusEnum.down });
    ((MOMutableColumn)epcgIoPortEntryColumns[idxEpcgIoPortAdminStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgIoPortAdminStatusVC));                                  
    ((MOMutableColumn)epcgIoPortEntryColumns[idxEpcgIoPortAdminStatus]).
      addMOValueValidationListener(new EpcgIoPortAdminStatusValidator());
    epcgIoPortEntryColumns[idxEpcgIoPortOperStatus] = 
      moFactory.createColumn(colEpcgIoPortOperStatus, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgIoPortEntryColumns[idxEpcgIoPortOperStatusNotifEnable] = 
      new MOMutableColumn(colEpcgIoPortOperStatusNotifEnable,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgIoPortOperStatusNotifEnableVC = new EnumerationConstraint(
      new int[] { EpcgIoPortOperStatusNotifEnableEnum._true,
                  EpcgIoPortOperStatusNotifEnableEnum._false });
    ((MOMutableColumn)epcgIoPortEntryColumns[idxEpcgIoPortOperStatusNotifEnable]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgIoPortOperStatusNotifEnableVC));                                  
    ((MOMutableColumn)epcgIoPortEntryColumns[idxEpcgIoPortOperStatusNotifEnable]).
      addMOValueValidationListener(new EpcgIoPortOperStatusNotifEnableValidator());
    epcgIoPortEntryColumns[idxEpcgIoPortOperStatusNotifLevel] = 
      new MOMutableColumn(colEpcgIoPortOperStatusNotifLevel,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new Integer32(3));
    ValueConstraint epcgIoPortOperStatusNotifLevelVC = new EnumerationConstraint(
      new int[] { EpcgIoPortOperStatusNotifLevelEnum.emergency,
                  EpcgIoPortOperStatusNotifLevelEnum.alert,
                  EpcgIoPortOperStatusNotifLevelEnum.critical,
                  EpcgIoPortOperStatusNotifLevelEnum.error,
                  EpcgIoPortOperStatusNotifLevelEnum.warning,
                  EpcgIoPortOperStatusNotifLevelEnum.notice,
                  EpcgIoPortOperStatusNotifLevelEnum.informational,
                  EpcgIoPortOperStatusNotifLevelEnum.debug });
    ((MOMutableColumn)epcgIoPortEntryColumns[idxEpcgIoPortOperStatusNotifLevel]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgIoPortOperStatusNotifLevelVC));                                  
    ((MOMutableColumn)epcgIoPortEntryColumns[idxEpcgIoPortOperStatusNotifLevel]).
      addMOValueValidationListener(new EpcgIoPortOperStatusNotifLevelValidator());
    epcgIoPortEntryColumns[idxEpcgIoPortOperStatusNotifFromState] = 
      new MOMutableColumn(colEpcgIoPortOperStatusNotifFromState,
                          SMIConstants.SYNTAX_OCTET_STRING,
//                          SMIConstants.SYNTAX_BITS,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new OctetString(new byte[] { (byte)-16 }));
//    ValueConstraint epcgIoPortOperStatusNotifFromStateVC = new EnumerationConstraint(
//      new int[] { EpcgIoPortOperStatusNotifFromStateEnum.unknown,
//                  EpcgIoPortOperStatusNotifFromStateEnum.other,
//                  EpcgIoPortOperStatusNotifFromStateEnum.up,
//                  EpcgIoPortOperStatusNotifFromStateEnum.down });
    ValueConstraint epcgIoPortOperStatusNotifFromStateVC = new BitsEnumerationConstraint();
    ((MOMutableColumn)epcgIoPortEntryColumns[idxEpcgIoPortOperStatusNotifFromState]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgIoPortOperStatusNotifFromStateVC));                                  
    ((MOMutableColumn)epcgIoPortEntryColumns[idxEpcgIoPortOperStatusNotifFromState]).
      addMOValueValidationListener(new EpcgIoPortOperStatusNotifFromStateValidator());
    epcgIoPortEntryColumns[idxEpcgIoPortOperStatusNotifToState] = 
      new MOMutableColumn(colEpcgIoPortOperStatusNotifToState,
                          SMIConstants.SYNTAX_OCTET_STRING,
//                          SMIConstants.SYNTAX_BITS,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new OctetString(new byte[] { (byte)-16 }));
//    ValueConstraint epcgIoPortOperStatusNotifToStateVC = new EnumerationConstraint(
//      new int[] { EpcgIoPortOperStatusNotifToStateEnum.unknown,
//                  EpcgIoPortOperStatusNotifToStateEnum.other,
//                  EpcgIoPortOperStatusNotifToStateEnum.up,
//                  EpcgIoPortOperStatusNotifToStateEnum.down });
    ValueConstraint epcgIoPortOperStatusNotifToStateVC = new BitsEnumerationConstraint();
    ((MOMutableColumn)epcgIoPortEntryColumns[idxEpcgIoPortOperStatusNotifToState]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgIoPortOperStatusNotifToStateVC));                                  
    ((MOMutableColumn)epcgIoPortEntryColumns[idxEpcgIoPortOperStatusNotifToState]).
      addMOValueValidationListener(new EpcgIoPortOperStatusNotifToStateValidator());
    epcgIoPortEntryColumns[idxEpcgIoPortDescription] = 
      moFactory.createColumn(colEpcgIoPortDescription, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgIoPortEntryColumns[idxEpcgIoPortOperStatusPrior] = 
      moFactory.createColumn(colEpcgIoPortOperStatusPrior, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_FOR_NOTIFY);
    epcgIoPortEntryColumns[idxEpcgIoPortOperStateSuppressInterval] = 
      new MOMutableColumn(colEpcgIoPortOperStateSuppressInterval,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new UnsignedInteger32(0));
    ValueConstraint epcgIoPortOperStateSuppressIntervalVC = new ConstraintsImpl();
    ((ConstraintsImpl)epcgIoPortOperStateSuppressIntervalVC).add(new Constraint(0, 0));
    ((ConstraintsImpl)epcgIoPortOperStateSuppressIntervalVC).add(new Constraint(1, 3600));
    ((MOMutableColumn)epcgIoPortEntryColumns[idxEpcgIoPortOperStateSuppressInterval]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgIoPortOperStateSuppressIntervalVC));                                  
    ((MOMutableColumn)epcgIoPortEntryColumns[idxEpcgIoPortOperStateSuppressInterval]).
      addMOValueValidationListener(new EpcgIoPortOperStateSuppressIntervalValidator());
    epcgIoPortEntryColumns[idxEpcgIoPortOperStateSuppressions] = 
      moFactory.createColumn(colEpcgIoPortOperStateSuppressions, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    
    epcgIoPortEntryModel = new DefaultMOMutableTableModel();
//    epcgIoPortEntryModel.setRowFactory(new EpcgIoPortEntryRowFactory());
    epcgIoPortEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.EPCG_IO_PORT_TABLE));
//    epcgIoPortEntry = 
//      moFactory.createTable(oidEpcgIoPortEntry,
//                            epcgIoPortEntryIndex,
//                            epcgIoPortEntryColumns,
//                            epcgIoPortEntryModel);
    epcgIoPortEntry = 
      new SnmpTable(TableTypeEnum.EPCG_IO_PORT_TABLE,
                    oidEpcgIoPortEntry,
                    epcgIoPortEntryIndex,
                    epcgIoPortEntryColumns,
                    epcgIoPortEntryModel);
  }

  public MOTable getEpcgSourceEntry() {
    return epcgSourceEntry;
  }


  private void createEpcgSourceEntry() {
    MOColumn[] epcgSourceEntryColumns = new MOColumn[24];
    epcgSourceEntryColumns[idxEpcgSrcName] = 
      moFactory.createColumn(colEpcgSrcName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgSourceEntryColumns[idxEpcgSrcReadCyclesPerTrigger] = 
      new MOMutableColumn(colEpcgSrcReadCyclesPerTrigger,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcReadCyclesPerTrigger]).
      addMOValueValidationListener(new EpcgSrcReadCyclesPerTriggerValidator());
    epcgSourceEntryColumns[idxEpcgSrcReadDutyCycle] = 
      new MOMutableColumn(colEpcgSrcReadDutyCycle,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgSrcReadDutyCycleVC = new ConstraintsImpl();
    ((ConstraintsImpl)epcgSrcReadDutyCycleVC).add(new Constraint(0, 100));
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcReadDutyCycle]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgSrcReadDutyCycleVC));                                  
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcReadDutyCycle]).
      addMOValueValidationListener(new EpcgSrcReadDutyCycleValidator());
    epcgSourceEntryColumns[idxEpcgSrcReadTimeout] = 
      new MOMutableColumn(colEpcgSrcReadTimeout,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcReadTimeout]).
      addMOValueValidationListener(new EpcgSrcReadTimeoutValidator());
    epcgSourceEntryColumns[idxEpcgSrcGlimpsedTimeout] = 
      new MOMutableColumn(colEpcgSrcGlimpsedTimeout,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcGlimpsedTimeout]).
      addMOValueValidationListener(new EpcgSrcGlimpsedTimeoutValidator());
    epcgSourceEntryColumns[idxEpcgSrcObservedThreshold] = 
      new MOMutableColumn(colEpcgSrcObservedThreshold,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcObservedThreshold]).
      addMOValueValidationListener(new EpcgSrcObservedThresholdValidator());
    epcgSourceEntryColumns[idxEpcgSrcObservedTimeout] = 
      new MOMutableColumn(colEpcgSrcObservedTimeout,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcObservedTimeout]).
      addMOValueValidationListener(new EpcgSrcObservedTimeoutValidator());
    epcgSourceEntryColumns[idxEpcgSrcLostTimeout] = 
      new MOMutableColumn(colEpcgSrcLostTimeout,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcLostTimeout]).
      addMOValueValidationListener(new EpcgSrcLostTimeoutValidator());
    epcgSourceEntryColumns[idxEpcgSrcUnknownToGlimpsedTrans] = 
      moFactory.createColumn(colEpcgSrcUnknownToGlimpsedTrans, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgSourceEntryColumns[idxEpcgSrcGlimpsedToUnknownTrans] = 
      moFactory.createColumn(colEpcgSrcGlimpsedToUnknownTrans, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgSourceEntryColumns[idxEpcgSrcGlimpsedToObservedTrans] = 
      moFactory.createColumn(colEpcgSrcGlimpsedToObservedTrans, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgSourceEntryColumns[idxEpcgSrcObservedToLostTrans] = 
      moFactory.createColumn(colEpcgSrcObservedToLostTrans, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgSourceEntryColumns[idxEpcgSrcLostToGlimpsedTrans] = 
      moFactory.createColumn(colEpcgSrcLostToGlimpsedTrans, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgSourceEntryColumns[idxEpcgSrcLostToUnknownTrans] = 
      moFactory.createColumn(colEpcgSrcLostToUnknownTrans, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgSourceEntryColumns[idxEpcgSrcAdminStatus] = 
      new MOMutableColumn(colEpcgSrcAdminStatus,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgSrcAdminStatusVC = new EnumerationConstraint(
      new int[] { EpcgSrcAdminStatusEnum.up,
                  EpcgSrcAdminStatusEnum.down });
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcAdminStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgSrcAdminStatusVC));                                  
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcAdminStatus]).
      addMOValueValidationListener(new EpcgSrcAdminStatusValidator());
    epcgSourceEntryColumns[idxEpcgSrcOperStatus] = 
      moFactory.createColumn(colEpcgSrcOperStatus, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgSourceEntryColumns[idxEpcgSrcOperStatusNotifEnable] = 
      new MOMutableColumn(colEpcgSrcOperStatusNotifEnable,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgSrcOperStatusNotifEnableVC = new EnumerationConstraint(
      new int[] { EpcgSrcOperStatusNotifEnableEnum._true,
                  EpcgSrcOperStatusNotifEnableEnum._false });
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcOperStatusNotifEnable]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgSrcOperStatusNotifEnableVC));                                  
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcOperStatusNotifEnable]).
      addMOValueValidationListener(new EpcgSrcOperStatusNotifEnableValidator());
    epcgSourceEntryColumns[idxEpcgSrcOperStatusNotifFromState] = 
      new MOMutableColumn(colEpcgSrcOperStatusNotifFromState,
                          SMIConstants.SYNTAX_OCTET_STRING,
//                          SMIConstants.SYNTAX_BITS,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new OctetString(new byte[] { (byte)-16 }));
//    ValueConstraint epcgSrcOperStatusNotifFromStateVC = new EnumerationConstraint(
//      new int[] { EpcgSrcOperStatusNotifFromStateEnum.unknown,
//                  EpcgSrcOperStatusNotifFromStateEnum.other,
//                  EpcgSrcOperStatusNotifFromStateEnum.up,
//                  EpcgSrcOperStatusNotifFromStateEnum.down });
    ValueConstraint epcgSrcOperStatusNotifFromStateVC = new BitsEnumerationConstraint();
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcOperStatusNotifFromState]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgSrcOperStatusNotifFromStateVC));                                  
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcOperStatusNotifFromState]).
      addMOValueValidationListener(new EpcgSrcOperStatusNotifFromStateValidator());
    epcgSourceEntryColumns[idxEpcgSrcOperStatusNotifToState] = 
      new MOMutableColumn(colEpcgSrcOperStatusNotifToState,
                          SMIConstants.SYNTAX_OCTET_STRING,
//                          SMIConstants.SYNTAX_BITS,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new OctetString(new byte[] { (byte)-16 }));
//    ValueConstraint epcgSrcOperStatusNotifToStateVC = new EnumerationConstraint(
//      new int[] { EpcgSrcOperStatusNotifToStateEnum.unknown,
//                  EpcgSrcOperStatusNotifToStateEnum.other,
//                  EpcgSrcOperStatusNotifToStateEnum.up,
//                  EpcgSrcOperStatusNotifToStateEnum.down });
    ValueConstraint epcgSrcOperStatusNotifToStateVC = new BitsEnumerationConstraint();
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcOperStatusNotifToState]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgSrcOperStatusNotifToStateVC));                                  
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcOperStatusNotifToState]).
      addMOValueValidationListener(new EpcgSrcOperStatusNotifToStateValidator());
    epcgSourceEntryColumns[idxEpcgSrcOperStatusNotifyLevel] = 
      new MOMutableColumn(colEpcgSrcOperStatusNotifyLevel,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new Integer32(3));
    ValueConstraint epcgSrcOperStatusNotifyLevelVC = new EnumerationConstraint(
      new int[] { EpcgSrcOperStatusNotifyLevelEnum.emergency,
                  EpcgSrcOperStatusNotifyLevelEnum.alert,
                  EpcgSrcOperStatusNotifyLevelEnum.critical,
                  EpcgSrcOperStatusNotifyLevelEnum.error,
                  EpcgSrcOperStatusNotifyLevelEnum.warning,
                  EpcgSrcOperStatusNotifyLevelEnum.notice,
                  EpcgSrcOperStatusNotifyLevelEnum.informational,
                  EpcgSrcOperStatusNotifyLevelEnum.debug });
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcOperStatusNotifyLevel]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgSrcOperStatusNotifyLevelVC));                                  
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcOperStatusNotifyLevel]).
      addMOValueValidationListener(new EpcgSrcOperStatusNotifyLevelValidator());
    epcgSourceEntryColumns[idxEpcgSrcSupportsWriteOperations] = 
      moFactory.createColumn(colEpcgSrcSupportsWriteOperations, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgSourceEntryColumns[idxEpcgSrcOperStatusPrior] = 
      moFactory.createColumn(colEpcgSrcOperStatusPrior, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_FOR_NOTIFY);
    epcgSourceEntryColumns[idxEpcgSrcOperStateSuppressInterval] = 
      new MOMutableColumn(colEpcgSrcOperStateSuppressInterval,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new UnsignedInteger32(0));
    ValueConstraint epcgSrcOperStateSuppressIntervalVC = new ConstraintsImpl();
    ((ConstraintsImpl)epcgSrcOperStateSuppressIntervalVC).add(new Constraint(0, 0));
    ((ConstraintsImpl)epcgSrcOperStateSuppressIntervalVC).add(new Constraint(1, 3600));
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcOperStateSuppressInterval]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgSrcOperStateSuppressIntervalVC));                                  
    ((MOMutableColumn)epcgSourceEntryColumns[idxEpcgSrcOperStateSuppressInterval]).
      addMOValueValidationListener(new EpcgSrcOperStateSuppressIntervalValidator());
    epcgSourceEntryColumns[idxEpcgSrcOperStateSuppressions] = 
      moFactory.createColumn(colEpcgSrcOperStateSuppressions, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    
    epcgSourceEntryModel = new DefaultMOMutableTableModel();
//    epcgSourceEntryModel.setRowFactory(new EpcgSourceEntryRowFactory());
    epcgSourceEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.EPCG_SOURCE_TABLE));
//    epcgSourceEntry = 
//      moFactory.createTable(oidEpcgSourceEntry,
//                            epcgSourceEntryIndex,
//                            epcgSourceEntryColumns,
//                            epcgSourceEntryModel);
    epcgSourceEntry = 
      new SnmpTable(TableTypeEnum.EPCG_SOURCE_TABLE,
                    oidEpcgSourceEntry,
                    epcgSourceEntryIndex,
                    epcgSourceEntryColumns,
                    epcgSourceEntryModel);
  }

  public MOTable getEpcgRdPntSrcEntry() {
    return epcgRdPntSrcEntry;
  }


  private void createEpcgRdPntSrcEntry() {
    MOColumn[] epcgRdPntSrcEntryColumns = new MOColumn[1];
    epcgRdPntSrcEntryColumns[idxEpcgRdPntSrcRowStatus] = 
//      new RowStatus(colEpcgRdPntSrcRowStatus);
      new RowStatus(colEpcgRdPntSrcRowStatus, MOAccessImpl.ACCESS_READ_CREATE);
    ValueConstraint epcgRdPntSrcRowStatusVC = new EnumerationConstraint(
      new int[] { EpcgRdPntSrcRowStatusEnum.active,
                  EpcgRdPntSrcRowStatusEnum.notInService,
                  EpcgRdPntSrcRowStatusEnum.notReady,
                  EpcgRdPntSrcRowStatusEnum.createAndGo,
                  EpcgRdPntSrcRowStatusEnum.createAndWait,
                  EpcgRdPntSrcRowStatusEnum.destroy });
    ((MOMutableColumn)epcgRdPntSrcEntryColumns[idxEpcgRdPntSrcRowStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgRdPntSrcRowStatusVC));                                  
    ((MOMutableColumn)epcgRdPntSrcEntryColumns[idxEpcgRdPntSrcRowStatus]).
      addMOValueValidationListener(new EpcgRdPntSrcRowStatusValidator());
    
    epcgRdPntSrcEntryModel = new DefaultMOMutableTableModel();
//    epcgRdPntSrcEntryModel.setRowFactory(new EpcgRdPntSrcEntryRowFactory());
    epcgRdPntSrcEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.EPCG_RD_PNT_SRC_TABLE));
//    epcgRdPntSrcEntry = 
//      moFactory.createTable(oidEpcgRdPntSrcEntry,
//                            epcgRdPntSrcEntryIndex,
//                            epcgRdPntSrcEntryColumns,
//                            epcgRdPntSrcEntryModel);
    epcgRdPntSrcEntry = 
      new SnmpTable(TableTypeEnum.EPCG_RD_PNT_SRC_TABLE,
                    oidEpcgRdPntSrcEntry,
                    epcgRdPntSrcEntryIndex,
                    epcgRdPntSrcEntryColumns,
                    epcgRdPntSrcEntryModel);
  }

  public MOTable getEpcgNotifChanSrcEntry() {
    return epcgNotifChanSrcEntry;
  }


  private void createEpcgNotifChanSrcEntry() {
    MOColumn[] epcgNotifChanSrcEntryColumns = new MOColumn[1];
    epcgNotifChanSrcEntryColumns[idxEpcgNotifChanSrcRowStatus] = 
//      new RowStatus(colEpcgNotifChanSrcRowStatus);
      new RowStatus(colEpcgNotifChanSrcRowStatus, MOAccessImpl.ACCESS_READ_CREATE);
    ValueConstraint epcgNotifChanSrcRowStatusVC = new EnumerationConstraint(
      new int[] { EpcgNotifChanSrcRowStatusEnum.active,
                  EpcgNotifChanSrcRowStatusEnum.notInService,
                  EpcgNotifChanSrcRowStatusEnum.notReady,
                  EpcgNotifChanSrcRowStatusEnum.createAndGo,
                  EpcgNotifChanSrcRowStatusEnum.createAndWait,
                  EpcgNotifChanSrcRowStatusEnum.destroy });
    ((MOMutableColumn)epcgNotifChanSrcEntryColumns[idxEpcgNotifChanSrcRowStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgNotifChanSrcRowStatusVC));                                  
    ((MOMutableColumn)epcgNotifChanSrcEntryColumns[idxEpcgNotifChanSrcRowStatus]).
      addMOValueValidationListener(new EpcgNotifChanSrcRowStatusValidator());
    
    epcgNotifChanSrcEntryModel = new DefaultMOMutableTableModel();
//    epcgNotifChanSrcEntryModel.setRowFactory(new EpcgNotifChanSrcEntryRowFactory());
    epcgNotifChanSrcEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.EPCG_NOTIF_CHAN_SRC_TABLE));
//    epcgNotifChanSrcEntry = 
//      moFactory.createTable(oidEpcgNotifChanSrcEntry,
//                            epcgNotifChanSrcEntryIndex,
//                            epcgNotifChanSrcEntryColumns,
//                            epcgNotifChanSrcEntryModel);
    epcgNotifChanSrcEntry = 
      new SnmpTable(TableTypeEnum.EPCG_NOTIF_CHAN_SRC_TABLE,
                    oidEpcgNotifChanSrcEntry,
                    epcgNotifChanSrcEntryIndex,
                    epcgNotifChanSrcEntryColumns,
                    epcgNotifChanSrcEntryModel);
  }

  public MOTable getEpcgNotificationChannelEntry() {
    return epcgNotificationChannelEntry;
  }


  private void createEpcgNotificationChannelEntry() {
    MOColumn[] epcgNotificationChannelEntryColumns = new MOColumn[14];
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanName] = 
      moFactory.createColumn(colEpcgNotifChanName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanAddressType] = 
      moFactory.createColumn(colEpcgNotifChanAddressType, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanAddress] = 
      moFactory.createColumn(colEpcgNotifChanAddress, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanLastAttempt] = 
      moFactory.createColumn(colEpcgNotifChanLastAttempt, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanLastSuccess] = 
      moFactory.createColumn(colEpcgNotifChanLastSuccess, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanAdminStatus] = 
      new MOMutableColumn(colEpcgNotifChanAdminStatus,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgNotifChanAdminStatusVC = new EnumerationConstraint(
      new int[] { EpcgNotifChanAdminStatusEnum.up,
                  EpcgNotifChanAdminStatusEnum.down });
    ((MOMutableColumn)epcgNotificationChannelEntryColumns[idxEpcgNotifChanAdminStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgNotifChanAdminStatusVC));                                  
    ((MOMutableColumn)epcgNotificationChannelEntryColumns[idxEpcgNotifChanAdminStatus]).
      addMOValueValidationListener(new EpcgNotifChanAdminStatusValidator());
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperStatus] = 
      moFactory.createColumn(colEpcgNotifChanOperStatus, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperNotifEnable] = 
      new MOMutableColumn(colEpcgNotifChanOperNotifEnable,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint epcgNotifChanOperNotifEnableVC = new EnumerationConstraint(
      new int[] { EpcgNotifChanOperNotifEnableEnum._true,
                  EpcgNotifChanOperNotifEnableEnum._false });
    ((MOMutableColumn)epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperNotifEnable]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgNotifChanOperNotifEnableVC));                                  
    ((MOMutableColumn)epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperNotifEnable]).
      addMOValueValidationListener(new EpcgNotifChanOperNotifEnableValidator());
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperNotifLevel] = 
      new MOMutableColumn(colEpcgNotifChanOperNotifLevel,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new Integer32(3));
    ValueConstraint epcgNotifChanOperNotifLevelVC = new EnumerationConstraint(
      new int[] { EpcgNotifChanOperNotifLevelEnum.emergency,
                  EpcgNotifChanOperNotifLevelEnum.alert,
                  EpcgNotifChanOperNotifLevelEnum.critical,
                  EpcgNotifChanOperNotifLevelEnum.error,
                  EpcgNotifChanOperNotifLevelEnum.warning,
                  EpcgNotifChanOperNotifLevelEnum.notice,
                  EpcgNotifChanOperNotifLevelEnum.informational,
                  EpcgNotifChanOperNotifLevelEnum.debug });
    ((MOMutableColumn)epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperNotifLevel]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgNotifChanOperNotifLevelVC));                                  
    ((MOMutableColumn)epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperNotifLevel]).
      addMOValueValidationListener(new EpcgNotifChanOperNotifLevelValidator());
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperNotifFromState] = 
      new MOMutableColumn(colEpcgNotifChanOperNotifFromState,
                          SMIConstants.SYNTAX_OCTET_STRING,
//                          SMIConstants.SYNTAX_BITS,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new OctetString(new byte[] { (byte)-16 }));
//    ValueConstraint epcgNotifChanOperNotifFromStateVC = new EnumerationConstraint(
//      new int[] { EpcgNotifChanOperNotifFromStateEnum.unknown,
//                  EpcgNotifChanOperNotifFromStateEnum.other,
//                  EpcgNotifChanOperNotifFromStateEnum.up,
//                  EpcgNotifChanOperNotifFromStateEnum.down });
    ValueConstraint epcgNotifChanOperNotifFromStateVC = new BitsEnumerationConstraint();
    ((MOMutableColumn)epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperNotifFromState]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgNotifChanOperNotifFromStateVC));                                  
    ((MOMutableColumn)epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperNotifFromState]).
      addMOValueValidationListener(new EpcgNotifChanOperNotifFromStateValidator());
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperNotifToState] = 
      new MOMutableColumn(colEpcgNotifChanOperNotifToState,
                          SMIConstants.SYNTAX_OCTET_STRING,
//                          SMIConstants.SYNTAX_BITS,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new OctetString(new byte[] { (byte)-16 }));
//    ValueConstraint epcgNotifChanOperNotifToStateVC = new EnumerationConstraint(
//      new int[] { EpcgNotifChanOperNotifToStateEnum.unknown,
//                  EpcgNotifChanOperNotifToStateEnum.other,
//                  EpcgNotifChanOperNotifToStateEnum.up,
//                  EpcgNotifChanOperNotifToStateEnum.down });
    ValueConstraint epcgNotifChanOperNotifToStateVC = new BitsEnumerationConstraint();
    ((MOMutableColumn)epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperNotifToState]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgNotifChanOperNotifToStateVC));                                  
    ((MOMutableColumn)epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperNotifToState]).
      addMOValueValidationListener(new EpcgNotifChanOperNotifToStateValidator());
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperStatusPrior] = 
      moFactory.createColumn(colEpcgNotifChanOperStatusPrior, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_FOR_NOTIFY);
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperStateSuppressInterval] = 
      new MOMutableColumn(colEpcgNotifChanOperStateSuppressInterval,
                          SMIConstants.SYNTAX_GAUGE32,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          new UnsignedInteger32(0));
    ValueConstraint epcgNotifChanOperStateSuppressIntervalVC = new ConstraintsImpl();
    ((ConstraintsImpl)epcgNotifChanOperStateSuppressIntervalVC).add(new Constraint(0, 0));
    ((ConstraintsImpl)epcgNotifChanOperStateSuppressIntervalVC).add(new Constraint(1, 3600));
    ((MOMutableColumn)epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperStateSuppressInterval]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgNotifChanOperStateSuppressIntervalVC));                                  
    ((MOMutableColumn)epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperStateSuppressInterval]).
      addMOValueValidationListener(new EpcgNotifChanOperStateSuppressIntervalValidator());
    epcgNotificationChannelEntryColumns[idxEpcgNotifChanOperStateSuppressions] = 
      moFactory.createColumn(colEpcgNotifChanOperStateSuppressions, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    
    epcgNotificationChannelEntryModel = new DefaultMOMutableTableModel();
//    epcgNotificationChannelEntryModel.setRowFactory(new EpcgNotificationChannelEntryRowFactory());
    epcgNotificationChannelEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.EPCG_NOTIFICATION_CHANNEL_TABLE));
//    epcgNotificationChannelEntry = 
//      moFactory.createTable(oidEpcgNotificationChannelEntry,
//                            epcgNotificationChannelEntryIndex,
//                            epcgNotificationChannelEntryColumns,
//                            epcgNotificationChannelEntryModel);
    epcgNotificationChannelEntry = 
      new SnmpTable(TableTypeEnum.EPCG_NOTIFICATION_CHANNEL_TABLE,
                    oidEpcgNotificationChannelEntry,
                    epcgNotificationChannelEntryIndex,
                    epcgNotificationChannelEntryColumns,
                    epcgNotificationChannelEntryModel);
  }

  public MOTable getEpcgTriggerEntry() {
    return epcgTriggerEntry;
  }


  private void createEpcgTriggerEntry() {
    MOColumn[] epcgTriggerEntryColumns = new MOColumn[5];
    epcgTriggerEntryColumns[idxEpcgTrigName] = 
      moFactory.createColumn(colEpcgTrigName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgTriggerEntryColumns[idxEpcgTrigType] = 
      moFactory.createColumn(colEpcgTrigType, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgTriggerEntryColumns[idxEpcgTrigParameters] = 
      moFactory.createColumn(colEpcgTrigParameters, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgTriggerEntryColumns[idxEpcgTriggerMatches] = 
      moFactory.createColumn(colEpcgTriggerMatches, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    epcgTriggerEntryColumns[idxEpcgTrigIoPort] = 
      moFactory.createColumn(colEpcgTrigIoPort, 
                             SMIConstants.SYNTAX_OBJECT_IDENTIFIER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    
    epcgTriggerEntryModel = new DefaultMOMutableTableModel();
//    epcgTriggerEntryModel.setRowFactory(new EpcgTriggerEntryRowFactory());
    epcgTriggerEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.EPCG_TRIGGER_TABLE));
//    epcgTriggerEntry = 
//      moFactory.createTable(oidEpcgTriggerEntry,
//                            epcgTriggerEntryIndex,
//                            epcgTriggerEntryColumns,
//                            epcgTriggerEntryModel);
    epcgTriggerEntry = 
      new SnmpTable(TableTypeEnum.EPCG_TRIGGER_TABLE,
                    oidEpcgTriggerEntry,
                    epcgTriggerEntryIndex,
                    epcgTriggerEntryColumns,
                    epcgTriggerEntryModel);
  }

  public MOTable getEpcgNotifTrigEntry() {
    return epcgNotifTrigEntry;
  }


  private void createEpcgNotifTrigEntry() {
    MOColumn[] epcgNotifTrigEntryColumns = new MOColumn[1];
    epcgNotifTrigEntryColumns[idxEpcgNotifTrigRowStatus] = 
//      new RowStatus(colEpcgNotifTrigRowStatus);
      new RowStatus(colEpcgNotifTrigRowStatus, MOAccessImpl.ACCESS_READ_CREATE);
    ValueConstraint epcgNotifTrigRowStatusVC = new EnumerationConstraint(
      new int[] { EpcgNotifTrigRowStatusEnum.active,
                  EpcgNotifTrigRowStatusEnum.notInService,
                  EpcgNotifTrigRowStatusEnum.notReady,
                  EpcgNotifTrigRowStatusEnum.createAndGo,
                  EpcgNotifTrigRowStatusEnum.createAndWait,
                  EpcgNotifTrigRowStatusEnum.destroy });
    ((MOMutableColumn)epcgNotifTrigEntryColumns[idxEpcgNotifTrigRowStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgNotifTrigRowStatusVC));                                  
    ((MOMutableColumn)epcgNotifTrigEntryColumns[idxEpcgNotifTrigRowStatus]).
      addMOValueValidationListener(new EpcgNotifTrigRowStatusValidator());
    
    epcgNotifTrigEntryModel = new DefaultMOMutableTableModel();
//    epcgNotifTrigEntryModel.setRowFactory(new EpcgNotifTrigEntryRowFactory());
    epcgNotifTrigEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.EPCG_NOTIF_TRIG_TABLE));
//    epcgNotifTrigEntry = 
//      moFactory.createTable(oidEpcgNotifTrigEntry,
//                            epcgNotifTrigEntryIndex,
//                            epcgNotifTrigEntryColumns,
//                            epcgNotifTrigEntryModel);
    epcgNotifTrigEntry = 
      new SnmpTable(TableTypeEnum.EPCG_NOTIF_TRIG_TABLE,
                    oidEpcgNotifTrigEntry,
                    epcgNotifTrigEntryIndex,
                    epcgNotifTrigEntryColumns,
                    epcgNotifTrigEntryModel);
  }

  public MOTable getEpcgReadTrigEntry() {
    return epcgReadTrigEntry;
  }


  private void createEpcgReadTrigEntry() {
    MOColumn[] epcgReadTrigEntryColumns = new MOColumn[1];
    epcgReadTrigEntryColumns[idxEpcgReadTrigRowStatus] = 
//      new RowStatus(colEpcgReadTrigRowStatus);
      new RowStatus(colEpcgReadTrigRowStatus, MOAccessImpl.ACCESS_READ_CREATE);
    ValueConstraint epcgReadTrigRowStatusVC = new EnumerationConstraint(
      new int[] { EpcgReadTrigRowStatusEnum.active,
                  EpcgReadTrigRowStatusEnum.notInService,
                  EpcgReadTrigRowStatusEnum.notReady,
                  EpcgReadTrigRowStatusEnum.createAndGo,
                  EpcgReadTrigRowStatusEnum.createAndWait,
                  EpcgReadTrigRowStatusEnum.destroy });
    ((MOMutableColumn)epcgReadTrigEntryColumns[idxEpcgReadTrigRowStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(epcgReadTrigRowStatusVC));                                  
    ((MOMutableColumn)epcgReadTrigEntryColumns[idxEpcgReadTrigRowStatus]).
      addMOValueValidationListener(new EpcgReadTrigRowStatusValidator());
    
    epcgReadTrigEntryModel = new DefaultMOMutableTableModel();
//    epcgReadTrigEntryModel.setRowFactory(new EpcgReadTrigEntryRowFactory());
    epcgReadTrigEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.EPCG_READ_TRIG_TABLE));
//    epcgReadTrigEntry = 
//      moFactory.createTable(oidEpcgReadTrigEntry,
//                            epcgReadTrigEntryIndex,
//                            epcgReadTrigEntryColumns,
//                            epcgReadTrigEntryModel);
    epcgReadTrigEntry = 
        new SnmpTable(TableTypeEnum.EPCG_READ_TRIG_TABLE,
                      oidEpcgReadTrigEntry,
                      epcgReadTrigEntryIndex,
                      epcgReadTrigEntryColumns,
                      epcgReadTrigEntryModel);
  }



  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.epcgRdrDevDescription, context);
    server.register(this.epcgRdrDevRole, context);
    server.register(this.epcgRdrDevEpc, context);
    server.register(this.epcgRdrDevSerialNumber, context);
    server.register(this.epcgRdrDevTimeUtc, context);
    server.register(this.epcgRdrDevCurrentSource, context);
    server.register(this.epcgRdrDevReboot, context);
    server.register(this.epcgRdrDevResetStatistics, context);
    server.register(this.epcgRdrDevResetTimestamp, context);
    server.register(this.epcgRdrDevNormalizePowerLevel, context);
    server.register(this.epcgRdrDevNormalizeNoiseLevel, context);
    server.register(this.epcgRdrDevOperStatus, context);
    server.register(this.epcgRdrDevOperStateEnable, context);
    server.register(this.epcgRdrDevOperNotifFromState, context);
    server.register(this.epcgRdrDevOperNotifToState, context);
    server.register(this.epcgRdrDevOperNotifStateLevel, context);
    server.register(this.epcgRdrDevOperStateSuppressInterval, context);
    server.register(this.epcgRdrDevOperStateSuppressions, context);
    server.register(this.epcgRdrDevFreeMemory, context);
    server.register(this.epcgRdrDevFreeMemoryNotifEnable, context);
    server.register(this.epcgRdrDevFreeMemoryNotifLevel, context);
    server.register(this.epcgRdrDevFreeMemoryOnsetThreshold, context);
    server.register(this.epcgRdrDevFreeMemoryAbateThreshold, context);
    server.register(this.epcgRdrDevFreeMemoryStatus, context);
    server.register(this.epcgRdrDevMemStateSuppressInterval, context);
    server.register(this.epcgRdrDevMemStateSuppressions, context);
    server.register(this.epcgGlobalCountersEntry, context);
    server.register(this.epcgReaderServerEntry, context);
    server.register(this.epcgReadPointEntry, context);
    server.register(this.epcgAntReadPointEntry, context);
    server.register(this.epcgIoPortEntry, context);
    server.register(this.epcgSourceEntry, context);
    server.register(this.epcgRdPntSrcEntry, context);
    server.register(this.epcgNotifChanSrcEntry, context);
    server.register(this.epcgNotificationChannelEntry, context);
    server.register(this.epcgTriggerEntry, context);
    server.register(this.epcgNotifTrigEntry, context);
    server.register(this.epcgReadTrigEntry, context);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.epcgRdrDevDescription, context);
    server.unregister(this.epcgRdrDevRole, context);
    server.unregister(this.epcgRdrDevEpc, context);
    server.unregister(this.epcgRdrDevSerialNumber, context);
    server.unregister(this.epcgRdrDevTimeUtc, context);
    server.unregister(this.epcgRdrDevCurrentSource, context);
    server.unregister(this.epcgRdrDevReboot, context);
    server.unregister(this.epcgRdrDevResetStatistics, context);
    server.unregister(this.epcgRdrDevResetTimestamp, context);
    server.unregister(this.epcgRdrDevNormalizePowerLevel, context);
    server.unregister(this.epcgRdrDevNormalizeNoiseLevel, context);
    server.unregister(this.epcgRdrDevOperStatus, context);
    server.unregister(this.epcgRdrDevOperStateEnable, context);
    server.unregister(this.epcgRdrDevOperNotifFromState, context);
    server.unregister(this.epcgRdrDevOperNotifToState, context);
    server.unregister(this.epcgRdrDevOperNotifStateLevel, context);
    server.unregister(this.epcgRdrDevOperStateSuppressInterval, context);
    server.unregister(this.epcgRdrDevOperStateSuppressions, context);
    server.unregister(this.epcgRdrDevFreeMemory, context);
    server.unregister(this.epcgRdrDevFreeMemoryNotifEnable, context);
    server.unregister(this.epcgRdrDevFreeMemoryNotifLevel, context);
    server.unregister(this.epcgRdrDevFreeMemoryOnsetThreshold, context);
    server.unregister(this.epcgRdrDevFreeMemoryAbateThreshold, context);
    server.unregister(this.epcgRdrDevFreeMemoryStatus, context);
    server.unregister(this.epcgRdrDevMemStateSuppressInterval, context);
    server.unregister(this.epcgRdrDevMemStateSuppressions, context);
    server.unregister(this.epcgGlobalCountersEntry, context);
    server.unregister(this.epcgReaderServerEntry, context);
    server.unregister(this.epcgReadPointEntry, context);
    server.unregister(this.epcgAntReadPointEntry, context);
    server.unregister(this.epcgIoPortEntry, context);
    server.unregister(this.epcgSourceEntry, context);
    server.unregister(this.epcgRdPntSrcEntry, context);
    server.unregister(this.epcgNotifChanSrcEntry, context);
    server.unregister(this.epcgNotificationChannelEntry, context);
    server.unregister(this.epcgTriggerEntry, context);
    server.unregister(this.epcgNotifTrigEntry, context);
    server.unregister(this.epcgReadTrigEntry, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  // Notifications
  public void epcgReaderDeviceOperationState(NotificationOriginator notificationOriginator,
                        OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 5) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<5");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarSysName))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarSysName);
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarEpcgRdrDevTimeUtc))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevTimeUtc);
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarEpcgRdrDevOperNotifStateLevel))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevOperNotifStateLevel);
    }
    if (!(vbs[3].getOid().startsWith(oidTrapVarEpcgRdrDevOperStatusPrior))) {
      throw new IllegalArgumentException("Variable 3 has wrong OID: "+vbs[3].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevOperStatusPrior);
    }
    if (!(vbs[4].getOid().startsWith(oidTrapVarEpcgRdrDevOperStatus))) {
      throw new IllegalArgumentException("Variable 4 has wrong OID: "+vbs[4].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevOperStatus);
    }
    notificationOriginator.notify(context, oidEpcgReaderDeviceOperationState, vbs);
  }

  public void epcgRdrDevMemoryState(NotificationOriginator notificationOriginator,
                        OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 4) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<4");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarSysName))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarSysName);
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarEpcgRdrDevTimeUtc))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevTimeUtc);
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarEpcgRdrDevFreeMemoryNotifLevel))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevFreeMemoryNotifLevel);
    }
    if (!(vbs[3].getOid().startsWith(oidTrapVarEpcgRdrDevFreeMemory))) {
      throw new IllegalArgumentException("Variable 3 has wrong OID: "+vbs[3].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevFreeMemory);
    }
    notificationOriginator.notify(context, oidEpcgRdrDevMemoryState, vbs);
  }

  public void epcgReadPointOperationState(NotificationOriginator notificationOriginator,
                        OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 6) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<6");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarSysName))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarSysName);
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarEpcgRdrDevTimeUtc))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevTimeUtc);
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarEpcgReadPointOperNotifyStateLevel))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarEpcgReadPointOperNotifyStateLevel);
    }
    if (!epcgReadPointEntryIndex.isValidIndex(epcgReadPointEntry.getIndexPart(vbs[2].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 2 specified: "+
                                         epcgReadPointEntry.getIndexPart(vbs[2].getOid()));
    }
    if (!(vbs[3].getOid().startsWith(oidTrapVarEpcgReadPointName))) {
      throw new IllegalArgumentException("Variable 3 has wrong OID: "+vbs[3].getOid()+
                                         " does not start with "+oidTrapVarEpcgReadPointName);
    }
    if (!epcgReadPointEntryIndex.isValidIndex(epcgReadPointEntry.getIndexPart(vbs[3].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 3 specified: "+
                                         epcgReadPointEntry.getIndexPart(vbs[3].getOid()));
    }
    if (!(vbs[4].getOid().startsWith(oidTrapVarEpcgReadPointPriorOperStatus))) {
      throw new IllegalArgumentException("Variable 4 has wrong OID: "+vbs[4].getOid()+
                                         " does not start with "+oidTrapVarEpcgReadPointPriorOperStatus);
    }
    if (!epcgReadPointEntryIndex.isValidIndex(epcgReadPointEntry.getIndexPart(vbs[4].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 4 specified: "+
                                         epcgReadPointEntry.getIndexPart(vbs[4].getOid()));
    }
    if (!(vbs[5].getOid().startsWith(oidTrapVarEpcgReadPointOperStatus))) {
      throw new IllegalArgumentException("Variable 5 has wrong OID: "+vbs[5].getOid()+
                                         " does not start with "+oidTrapVarEpcgReadPointOperStatus);
    }
    if (!epcgReadPointEntryIndex.isValidIndex(epcgReadPointEntry.getIndexPart(vbs[5].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 5 specified: "+
                                         epcgReadPointEntry.getIndexPart(vbs[5].getOid()));
    }
    notificationOriginator.notify(context, oidEpcgReadPointOperationState, vbs);
  }

  public void epcgReaderAntennaReadFailure(NotificationOriginator notificationOriginator,
                        OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 6) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<6");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarSysName))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarSysName);
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarEpcgRdrDevTimeUtc))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevTimeUtc);
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarEpcgAntRdPntReadFailureNotifLevel))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntReadFailureNotifLevel);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[2].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 2 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[2].getOid()));
    }
    if (!(vbs[3].getOid().startsWith(oidTrapVarEpcgReadPointName))) {
      throw new IllegalArgumentException("Variable 3 has wrong OID: "+vbs[3].getOid()+
                                         " does not start with "+oidTrapVarEpcgReadPointName);
    }
    if (!epcgReadPointEntryIndex.isValidIndex(epcgReadPointEntry.getIndexPart(vbs[3].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 3 specified: "+
                                         epcgReadPointEntry.getIndexPart(vbs[3].getOid()));
    }
    if (!(vbs[4].getOid().startsWith(oidTrapVarEpcgAntRdPntMemoryReadFailures))) {
      throw new IllegalArgumentException("Variable 4 has wrong OID: "+vbs[4].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntMemoryReadFailures);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[4].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 4 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[4].getOid()));
    }
    if (!(vbs[5].getOid().startsWith(oidTrapVarEpcgAntRdPntNoiseLevel))) {
      throw new IllegalArgumentException("Variable 5 has wrong OID: "+vbs[5].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntNoiseLevel);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[5].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 5 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[5].getOid()));
    }
    notificationOriginator.notify(context, oidEpcgReaderAntennaReadFailure, vbs);
  }

  public void epcgReaderAntennaWriteFailure(NotificationOriginator notificationOriginator,
                        OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 6) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<6");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarSysName))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarSysName);
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarEpcgRdrDevTimeUtc))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevTimeUtc);
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarEpcgAntRdPntWriteFailuresNotifLevel))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntWriteFailuresNotifLevel);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[2].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 2 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[2].getOid()));
    }
    if (!(vbs[3].getOid().startsWith(oidTrapVarEpcgReadPointName))) {
      throw new IllegalArgumentException("Variable 3 has wrong OID: "+vbs[3].getOid()+
                                         " does not start with "+oidTrapVarEpcgReadPointName);
    }
    if (!epcgReadPointEntryIndex.isValidIndex(epcgReadPointEntry.getIndexPart(vbs[3].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 3 specified: "+
                                         epcgReadPointEntry.getIndexPart(vbs[3].getOid()));
    }
    if (!(vbs[4].getOid().startsWith(oidTrapVarEpcgAntRdPntWriteFailures))) {
      throw new IllegalArgumentException("Variable 4 has wrong OID: "+vbs[4].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntWriteFailures);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[4].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 4 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[4].getOid()));
    }
    if (!(vbs[5].getOid().startsWith(oidTrapVarEpcgAntRdPntNoiseLevel))) {
      throw new IllegalArgumentException("Variable 5 has wrong OID: "+vbs[5].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntNoiseLevel);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[5].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 5 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[5].getOid()));
    }
    notificationOriginator.notify(context, oidEpcgReaderAntennaWriteFailure, vbs);
  }

  public void epcgReaderAntennaKillFailure(NotificationOriginator notificationOriginator,
                        OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 6) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<6");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarSysName))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarSysName);
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarEpcgRdrDevTimeUtc))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevTimeUtc);
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarEpcgAntRdPntKillFailuresNotifLevel))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntKillFailuresNotifLevel);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[2].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 2 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[2].getOid()));
    }
    if (!(vbs[3].getOid().startsWith(oidTrapVarEpcgReadPointName))) {
      throw new IllegalArgumentException("Variable 3 has wrong OID: "+vbs[3].getOid()+
                                         " does not start with "+oidTrapVarEpcgReadPointName);
    }
    if (!epcgReadPointEntryIndex.isValidIndex(epcgReadPointEntry.getIndexPart(vbs[3].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 3 specified: "+
                                         epcgReadPointEntry.getIndexPart(vbs[3].getOid()));
    }
    if (!(vbs[4].getOid().startsWith(oidTrapVarEpcgAntRdPntKillFailures))) {
      throw new IllegalArgumentException("Variable 4 has wrong OID: "+vbs[4].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntKillFailures);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[4].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 4 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[4].getOid()));
    }
    if (!(vbs[5].getOid().startsWith(oidTrapVarEpcgAntRdPntNoiseLevel))) {
      throw new IllegalArgumentException("Variable 5 has wrong OID: "+vbs[5].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntNoiseLevel);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[5].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 5 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[5].getOid()));
    }
    notificationOriginator.notify(context, oidEpcgReaderAntennaKillFailure, vbs);
  }

  public void epcgReaderAntennaEraseFailure(NotificationOriginator notificationOriginator,
                        OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 6) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<6");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarSysName))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarSysName);
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarEpcgRdrDevTimeUtc))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevTimeUtc);
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarEpcgAntRdPntEraseFailuresNotifLevel))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntEraseFailuresNotifLevel);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[2].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 2 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[2].getOid()));
    }
    if (!(vbs[3].getOid().startsWith(oidTrapVarEpcgReadPointName))) {
      throw new IllegalArgumentException("Variable 3 has wrong OID: "+vbs[3].getOid()+
                                         " does not start with "+oidTrapVarEpcgReadPointName);
    }
    if (!epcgReadPointEntryIndex.isValidIndex(epcgReadPointEntry.getIndexPart(vbs[3].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 3 specified: "+
                                         epcgReadPointEntry.getIndexPart(vbs[3].getOid()));
    }
    if (!(vbs[4].getOid().startsWith(oidTrapVarEpcgAntRdPntEraseFailures))) {
      throw new IllegalArgumentException("Variable 4 has wrong OID: "+vbs[4].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntEraseFailures);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[4].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 4 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[4].getOid()));
    }
    if (!(vbs[5].getOid().startsWith(oidTrapVarEpcgAntRdPntNoiseLevel))) {
      throw new IllegalArgumentException("Variable 5 has wrong OID: "+vbs[5].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntNoiseLevel);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[5].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 5 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[5].getOid()));
    }
    notificationOriginator.notify(context, oidEpcgReaderAntennaEraseFailure, vbs);
  }

  public void epcgReaderAntennaLockFailure(NotificationOriginator notificationOriginator,
                        OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 6) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<6");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarSysName))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarSysName);
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarEpcgRdrDevTimeUtc))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevTimeUtc);
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarEpcgAntRdPntLockFailuresNotifLevel))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntLockFailuresNotifLevel);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[2].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 2 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[2].getOid()));
    }
    if (!(vbs[3].getOid().startsWith(oidTrapVarEpcgReadPointName))) {
      throw new IllegalArgumentException("Variable 3 has wrong OID: "+vbs[3].getOid()+
                                         " does not start with "+oidTrapVarEpcgReadPointName);
    }
    if (!epcgReadPointEntryIndex.isValidIndex(epcgReadPointEntry.getIndexPart(vbs[3].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 3 specified: "+
                                         epcgReadPointEntry.getIndexPart(vbs[3].getOid()));
    }
    if (!(vbs[4].getOid().startsWith(oidTrapVarEpcgAntRdPntLockFailures))) {
      throw new IllegalArgumentException("Variable 4 has wrong OID: "+vbs[4].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntLockFailures);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[4].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 4 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[4].getOid()));
    }
    if (!(vbs[5].getOid().startsWith(oidTrapVarEpcgAntRdPntNoiseLevel))) {
      throw new IllegalArgumentException("Variable 5 has wrong OID: "+vbs[5].getOid()+
                                         " does not start with "+oidTrapVarEpcgAntRdPntNoiseLevel);
    }
    if (!epcgAntReadPointEntryIndex.isValidIndex(epcgAntReadPointEntry.getIndexPart(vbs[5].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 5 specified: "+
                                         epcgAntReadPointEntry.getIndexPart(vbs[5].getOid()));
    }
    notificationOriginator.notify(context, oidEpcgReaderAntennaLockFailure, vbs);
  }

  public void epcgReaderIoPortOperationState(NotificationOriginator notificationOriginator,
                        OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 6) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<6");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarSysName))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarSysName);
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarEpcgRdrDevTimeUtc))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevTimeUtc);
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarEpcgIoPortOperStatusNotifLevel))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarEpcgIoPortOperStatusNotifLevel);
    }
    if (!epcgIoPortEntryIndex.isValidIndex(epcgIoPortEntry.getIndexPart(vbs[2].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 2 specified: "+
                                         epcgIoPortEntry.getIndexPart(vbs[2].getOid()));
    }
    if (!(vbs[3].getOid().startsWith(oidTrapVarEpcgIoPortName))) {
      throw new IllegalArgumentException("Variable 3 has wrong OID: "+vbs[3].getOid()+
                                         " does not start with "+oidTrapVarEpcgIoPortName);
    }
    if (!epcgIoPortEntryIndex.isValidIndex(epcgIoPortEntry.getIndexPart(vbs[3].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 3 specified: "+
                                         epcgIoPortEntry.getIndexPart(vbs[3].getOid()));
    }
    if (!(vbs[4].getOid().startsWith(oidTrapVarEpcgIoPortOperStatusPrior))) {
      throw new IllegalArgumentException("Variable 4 has wrong OID: "+vbs[4].getOid()+
                                         " does not start with "+oidTrapVarEpcgIoPortOperStatusPrior);
    }
    if (!epcgIoPortEntryIndex.isValidIndex(epcgIoPortEntry.getIndexPart(vbs[4].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 4 specified: "+
                                         epcgIoPortEntry.getIndexPart(vbs[4].getOid()));
    }
    if (!(vbs[5].getOid().startsWith(oidTrapVarEpcgIoPortOperStatus))) {
      throw new IllegalArgumentException("Variable 5 has wrong OID: "+vbs[5].getOid()+
                                         " does not start with "+oidTrapVarEpcgIoPortOperStatus);
    }
    if (!epcgIoPortEntryIndex.isValidIndex(epcgIoPortEntry.getIndexPart(vbs[5].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 5 specified: "+
                                         epcgIoPortEntry.getIndexPart(vbs[5].getOid()));
    }
    notificationOriginator.notify(context, oidEpcgReaderIoPortOperationState, vbs);
  }

  public void epcgReaderSourceOperationState(NotificationOriginator notificationOriginator,
                        OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 6) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<6");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarSysName))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarSysName);
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarEpcgRdrDevTimeUtc))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevTimeUtc);
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarEpcgSrcOperStatusNotifyLevel))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarEpcgSrcOperStatusNotifyLevel);
    }
    if (!epcgSourceEntryIndex.isValidIndex(epcgSourceEntry.getIndexPart(vbs[2].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 2 specified: "+
                                         epcgSourceEntry.getIndexPart(vbs[2].getOid()));
    }
    if (!(vbs[3].getOid().startsWith(oidTrapVarEpcgSrcName))) {
      throw new IllegalArgumentException("Variable 3 has wrong OID: "+vbs[3].getOid()+
                                         " does not start with "+oidTrapVarEpcgSrcName);
    }
    if (!epcgSourceEntryIndex.isValidIndex(epcgSourceEntry.getIndexPart(vbs[3].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 3 specified: "+
                                         epcgSourceEntry.getIndexPart(vbs[3].getOid()));
    }
    if (!(vbs[4].getOid().startsWith(oidTrapVarEpcgSrcOperStatusPrior))) {
      throw new IllegalArgumentException("Variable 4 has wrong OID: "+vbs[4].getOid()+
                                         " does not start with "+oidTrapVarEpcgSrcOperStatusPrior);
    }
    if (!epcgSourceEntryIndex.isValidIndex(epcgSourceEntry.getIndexPart(vbs[4].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 4 specified: "+
                                         epcgSourceEntry.getIndexPart(vbs[4].getOid()));
    }
    if (!(vbs[5].getOid().startsWith(oidTrapVarEpcgSrcOperStatus))) {
      throw new IllegalArgumentException("Variable 5 has wrong OID: "+vbs[5].getOid()+
                                         " does not start with "+oidTrapVarEpcgSrcOperStatus);
    }
    if (!epcgSourceEntryIndex.isValidIndex(epcgSourceEntry.getIndexPart(vbs[5].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 5 specified: "+
                                         epcgSourceEntry.getIndexPart(vbs[5].getOid()));
    }
    notificationOriginator.notify(context, oidEpcgReaderSourceOperationState, vbs);
  }

  public void epcgReaderNotificationChanOperState(NotificationOriginator notificationOriginator,
                        OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 6) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<6");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarSysName))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarSysName);
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarEpcgRdrDevTimeUtc))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarEpcgRdrDevTimeUtc);
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarEpcgNotifChanOperNotifLevel))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarEpcgNotifChanOperNotifLevel);
    }
    if (!epcgNotificationChannelEntryIndex.isValidIndex(epcgNotificationChannelEntry.getIndexPart(vbs[2].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 2 specified: "+
                                         epcgNotificationChannelEntry.getIndexPart(vbs[2].getOid()));
    }
    if (!(vbs[3].getOid().startsWith(oidTrapVarEpcgNotifChanName))) {
      throw new IllegalArgumentException("Variable 3 has wrong OID: "+vbs[3].getOid()+
                                         " does not start with "+oidTrapVarEpcgNotifChanName);
    }
    if (!epcgNotificationChannelEntryIndex.isValidIndex(epcgNotificationChannelEntry.getIndexPart(vbs[3].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 3 specified: "+
                                         epcgNotificationChannelEntry.getIndexPart(vbs[3].getOid()));
    }
    if (!(vbs[4].getOid().startsWith(oidTrapVarEpcgNotifChanOperStatusPrior))) {
      throw new IllegalArgumentException("Variable 4 has wrong OID: "+vbs[4].getOid()+
                                         " does not start with "+oidTrapVarEpcgNotifChanOperStatusPrior);
    }
    if (!epcgNotificationChannelEntryIndex.isValidIndex(epcgNotificationChannelEntry.getIndexPart(vbs[4].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 4 specified: "+
                                         epcgNotificationChannelEntry.getIndexPart(vbs[4].getOid()));
    }
    if (!(vbs[5].getOid().startsWith(oidTrapVarEpcgNotifChanOperStatus))) {
      throw new IllegalArgumentException("Variable 5 has wrong OID: "+vbs[5].getOid()+
                                         " does not start with "+oidTrapVarEpcgNotifChanOperStatus);
    }
    if (!epcgNotificationChannelEntryIndex.isValidIndex(epcgNotificationChannelEntry.getIndexPart(vbs[5].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 5 specified: "+
                                         epcgNotificationChannelEntry.getIndexPart(vbs[5].getOid()));
    }
    notificationOriginator.notify(context, oidEpcgReaderNotificationChanOperState, vbs);
  }


  // Scalars
  public class EpcgRdrDevReboot extends MOScalar {
    EpcgRdrDevReboot(OID oid, MOAccess access) {
      super(oid, access, new Integer32());
//--AgentGen BEGIN=epcgRdrDevReboot
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
     //--AgentGen BEGIN=epcgRdrDevReboot::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=epcgRdrDevReboot::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=epcgRdrDevReboot::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=epcgRdrDevReboot::_METHODS
     //--AgentGen END

  }

  public class EpcgRdrDevResetStatistics extends MOScalar {
    EpcgRdrDevResetStatistics(OID oid, MOAccess access) {
      super(oid, access, new Integer32());
//--AgentGen BEGIN=epcgRdrDevResetStatistics
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
     //--AgentGen BEGIN=epcgRdrDevResetStatistics::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=epcgRdrDevResetStatistics::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=epcgRdrDevResetStatistics::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=epcgRdrDevResetStatistics::_METHODS
     //--AgentGen END

  }

  public class EpcgRdrDevOperStateEnable extends MOScalar {
    EpcgRdrDevOperStateEnable(OID oid, MOAccess access) {
      super(oid, access, new Integer32());
//--AgentGen BEGIN=epcgRdrDevOperStateEnable
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
     //--AgentGen BEGIN=epcgRdrDevOperStateEnable::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=epcgRdrDevOperStateEnable::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=epcgRdrDevOperStateEnable::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=epcgRdrDevOperStateEnable::_METHODS
     //--AgentGen END

  }

  public class EpcgRdrDevOperNotifFromState extends MOScalar {
    EpcgRdrDevOperNotifFromState(OID oid, MOAccess access) {
      super(oid, access, new OctetString());
//--AgentGen BEGIN=epcgRdrDevOperNotifFromState
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
      OctetString os = (OctetString)newValue;
      if (os.length() != 1) {
        valueOK = SnmpConstants.SNMP_ERROR_WRONG_LENGTH;
      }
     //--AgentGen BEGIN=epcgRdrDevOperNotifFromState::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=epcgRdrDevOperNotifFromState::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=epcgRdrDevOperNotifFromState::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=epcgRdrDevOperNotifFromState::_METHODS
     //--AgentGen END

  }

  public class EpcgRdrDevOperNotifToState extends MOScalar {
    EpcgRdrDevOperNotifToState(OID oid, MOAccess access) {
      super(oid, access, new OctetString());
//--AgentGen BEGIN=epcgRdrDevOperNotifToState
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
      OctetString os = (OctetString)newValue;
      if (os.length() != 1) {
        valueOK = SnmpConstants.SNMP_ERROR_WRONG_LENGTH;
      }
     //--AgentGen BEGIN=epcgRdrDevOperNotifToState::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=epcgRdrDevOperNotifToState::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=epcgRdrDevOperNotifToState::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=epcgRdrDevOperNotifToState::_METHODS
     //--AgentGen END

  }

  public class EpcgRdrDevOperNotifStateLevel extends MOScalar {
    EpcgRdrDevOperNotifStateLevel(OID oid, MOAccess access) {
      super(oid, access, new Integer32());
//--AgentGen BEGIN=epcgRdrDevOperNotifStateLevel
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
     //--AgentGen BEGIN=epcgRdrDevOperNotifStateLevel::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=epcgRdrDevOperNotifStateLevel::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=epcgRdrDevOperNotifStateLevel::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=epcgRdrDevOperNotifStateLevel::_METHODS
     //--AgentGen END

  }

  public class EpcgRdrDevOperStateSuppressInterval extends MOScalar {
    EpcgRdrDevOperStateSuppressInterval(OID oid, MOAccess access) {
      super(oid, access, new UnsignedInteger32());
//--AgentGen BEGIN=epcgRdrDevOperStateSuppressInterval
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        valueOK = SnmpConstants.SNMP_ERROR_WRONG_VALUE;
      }    
     //--AgentGen BEGIN=epcgRdrDevOperStateSuppressInterval::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=epcgRdrDevOperStateSuppressInterval::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=epcgRdrDevOperStateSuppressInterval::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=epcgRdrDevOperStateSuppressInterval::_METHODS
     //--AgentGen END

  }

  public class EpcgRdrDevFreeMemoryNotifEnable extends MOScalar {
    EpcgRdrDevFreeMemoryNotifEnable(OID oid, MOAccess access) {
      super(oid, access, new Integer32());
//--AgentGen BEGIN=epcgRdrDevFreeMemoryNotifEnable
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryNotifEnable::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryNotifEnable::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryNotifEnable::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=epcgRdrDevFreeMemoryNotifEnable::_METHODS
     //--AgentGen END

  }

  public class EpcgRdrDevFreeMemoryNotifLevel extends MOScalar {
    EpcgRdrDevFreeMemoryNotifLevel(OID oid, MOAccess access) {
      super(oid, access, new Integer32());
//--AgentGen BEGIN=epcgRdrDevFreeMemoryNotifLevel
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryNotifLevel::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryNotifLevel::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryNotifLevel::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=epcgRdrDevFreeMemoryNotifLevel::_METHODS
     //--AgentGen END

  }

  public class EpcgRdrDevFreeMemoryOnsetThreshold extends MOScalar {
    EpcgRdrDevFreeMemoryOnsetThreshold(OID oid, MOAccess access) {
      super(oid, access, new UnsignedInteger32());
//--AgentGen BEGIN=epcgRdrDevFreeMemoryOnsetThreshold
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 4294967295L)))) {
        valueOK = SnmpConstants.SNMP_ERROR_WRONG_VALUE;
      }    
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryOnsetThreshold::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryOnsetThreshold::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryOnsetThreshold::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=epcgRdrDevFreeMemoryOnsetThreshold::_METHODS
     //--AgentGen END

  }

  public class EpcgRdrDevFreeMemoryAbateThreshold extends MOScalar {
    EpcgRdrDevFreeMemoryAbateThreshold(OID oid, MOAccess access) {
      super(oid, access, new UnsignedInteger32());
//--AgentGen BEGIN=epcgRdrDevFreeMemoryAbateThreshold
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 4294967295L)))) {
        valueOK = SnmpConstants.SNMP_ERROR_WRONG_VALUE;
      }    
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryAbateThreshold::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryAbateThreshold::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryAbateThreshold::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=epcgRdrDevFreeMemoryAbateThreshold::_METHODS
     //--AgentGen END

  }

  public class EpcgRdrDevMemStateSuppressInterval extends MOScalar {
    EpcgRdrDevMemStateSuppressInterval(OID oid, MOAccess access) {
      super(oid, access, new UnsignedInteger32());
//--AgentGen BEGIN=epcgRdrDevMemStateSuppressInterval
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        valueOK = SnmpConstants.SNMP_ERROR_WRONG_VALUE;
      }    
     //--AgentGen BEGIN=epcgRdrDevMemStateSuppressInterval::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=epcgRdrDevMemStateSuppressInterval::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=epcgRdrDevMemStateSuppressInterval::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=epcgRdrDevMemStateSuppressInterval::_METHODS
     //--AgentGen END

  }


  // Value Validators
  /**
   * The <code>EpcgRdrDevRebootValidator</code> implements the value
   * validation for <code>EpcgRdrDevReboot</code>.
   */
  static class EpcgRdrDevRebootValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgRdrDevReboot::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgRdrDevResetStatisticsValidator</code> implements the value
   * validation for <code>EpcgRdrDevResetStatistics</code>.
   */
  static class EpcgRdrDevResetStatisticsValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgRdrDevResetStatistics::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgRdrDevOperStateEnableValidator</code> implements the value
   * validation for <code>EpcgRdrDevOperStateEnable</code>.
   */
  static class EpcgRdrDevOperStateEnableValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgRdrDevOperStateEnable::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgRdrDevOperNotifFromStateValidator</code> implements the value
   * validation for <code>EpcgRdrDevOperNotifFromState</code>.
   */
  static class EpcgRdrDevOperNotifFromStateValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (os.length() != 1) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=epcgRdrDevOperNotifFromState::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgRdrDevOperNotifToStateValidator</code> implements the value
   * validation for <code>EpcgRdrDevOperNotifToState</code>.
   */
  static class EpcgRdrDevOperNotifToStateValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (os.length() != 1) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=epcgRdrDevOperNotifToState::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgRdrDevOperNotifStateLevelValidator</code> implements the value
   * validation for <code>EpcgRdrDevOperNotifStateLevel</code>.
   */
  static class EpcgRdrDevOperNotifStateLevelValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgRdrDevOperNotifStateLevel::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgRdrDevOperStateSuppressIntervalValidator</code> implements the value
   * validation for <code>EpcgRdrDevOperStateSuppressInterval</code>.
   */
  static class EpcgRdrDevOperStateSuppressIntervalValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgRdrDevOperStateSuppressInterval::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgRdrDevFreeMemoryNotifEnableValidator</code> implements the value
   * validation for <code>EpcgRdrDevFreeMemoryNotifEnable</code>.
   */
  static class EpcgRdrDevFreeMemoryNotifEnableValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryNotifEnable::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgRdrDevFreeMemoryNotifLevelValidator</code> implements the value
   * validation for <code>EpcgRdrDevFreeMemoryNotifLevel</code>.
   */
  static class EpcgRdrDevFreeMemoryNotifLevelValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryNotifLevel::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgRdrDevFreeMemoryOnsetThresholdValidator</code> implements the value
   * validation for <code>EpcgRdrDevFreeMemoryOnsetThreshold</code>.
   */
  static class EpcgRdrDevFreeMemoryOnsetThresholdValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 4294967295L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryOnsetThreshold::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgRdrDevFreeMemoryAbateThresholdValidator</code> implements the value
   * validation for <code>EpcgRdrDevFreeMemoryAbateThreshold</code>.
   */
  static class EpcgRdrDevFreeMemoryAbateThresholdValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 4294967295L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgRdrDevFreeMemoryAbateThreshold::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgRdrDevMemStateSuppressIntervalValidator</code> implements the value
   * validation for <code>EpcgRdrDevMemStateSuppressInterval</code>.
   */
  static class EpcgRdrDevMemStateSuppressIntervalValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgRdrDevMemStateSuppressInterval::validate
     //--AgentGen END
    }
  }

  /**
   * The <code>EpcgReaderServerAddressTypeValidator</code> implements the value
   * validation for <code>EpcgReaderServerAddressType</code>.
   */
  static class EpcgReaderServerAddressTypeValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgReaderServerAddressType::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgReaderServerAddressValidator</code> implements the value
   * validation for <code>EpcgReaderServerAddress</code>.
   */
  static class EpcgReaderServerAddressValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (!(((os.length() >= 0) && (os.length() <= 255)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=epcgReaderServerAddress::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgReaderServerRowStatusValidator</code> implements the value
   * validation for <code>EpcgReaderServerRowStatus</code>.
   */
  static class EpcgReaderServerRowStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgReaderServerRowStatus::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgReadPointAdminStatusValidator</code> implements the value
   * validation for <code>EpcgReadPointAdminStatus</code>.
   */
  static class EpcgReadPointAdminStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgReadPointAdminStatus::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgReadPointOperStateNotifyEnableValidator</code> implements the value
   * validation for <code>EpcgReadPointOperStateNotifyEnable</code>.
   */
  static class EpcgReadPointOperStateNotifyEnableValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgReadPointOperStateNotifyEnable::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgReadPointOperNotifyFromStateValidator</code> implements the value
   * validation for <code>EpcgReadPointOperNotifyFromState</code>.
   */
  static class EpcgReadPointOperNotifyFromStateValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (os.length() != 1) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=epcgReadPointOperNotifyFromState::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgReadPointOperNotifyToStateValidator</code> implements the value
   * validation for <code>EpcgReadPointOperNotifyToState</code>.
   */
  static class EpcgReadPointOperNotifyToStateValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (os.length() != 1) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=epcgReadPointOperNotifyToState::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgReadPointOperNotifyStateLevelValidator</code> implements the value
   * validation for <code>EpcgReadPointOperNotifyStateLevel</code>.
   */
  static class EpcgReadPointOperNotifyStateLevelValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgReadPointOperNotifyStateLevel::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgReadPointOperStateSuppressIntervalValidator</code> implements the value
   * validation for <code>EpcgReadPointOperStateSuppressInterval</code>.
   */
  static class EpcgReadPointOperStateSuppressIntervalValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgReadPointOperStateSuppressInterval::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntReadFailureNotifEnableValidator</code> implements the value
   * validation for <code>EpcgAntRdPntReadFailureNotifEnable</code>.
   */
  static class EpcgAntRdPntReadFailureNotifEnableValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgAntRdPntReadFailureNotifEnable::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntReadFailureNotifLevelValidator</code> implements the value
   * validation for <code>EpcgAntRdPntReadFailureNotifLevel</code>.
   */
  static class EpcgAntRdPntReadFailureNotifLevelValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgAntRdPntReadFailureNotifLevel::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntWriteFailuresNotifEnableValidator</code> implements the value
   * validation for <code>EpcgAntRdPntWriteFailuresNotifEnable</code>.
   */
  static class EpcgAntRdPntWriteFailuresNotifEnableValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgAntRdPntWriteFailuresNotifEnable::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntWriteFailuresNotifLevelValidator</code> implements the value
   * validation for <code>EpcgAntRdPntWriteFailuresNotifLevel</code>.
   */
  static class EpcgAntRdPntWriteFailuresNotifLevelValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgAntRdPntWriteFailuresNotifLevel::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntKillFailuresNotifEnableValidator</code> implements the value
   * validation for <code>EpcgAntRdPntKillFailuresNotifEnable</code>.
   */
  static class EpcgAntRdPntKillFailuresNotifEnableValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgAntRdPntKillFailuresNotifEnable::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntKillFailuresNotifLevelValidator</code> implements the value
   * validation for <code>EpcgAntRdPntKillFailuresNotifLevel</code>.
   */
  static class EpcgAntRdPntKillFailuresNotifLevelValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgAntRdPntKillFailuresNotifLevel::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntEraseFailuresNotifEnableValidator</code> implements the value
   * validation for <code>EpcgAntRdPntEraseFailuresNotifEnable</code>.
   */
  static class EpcgAntRdPntEraseFailuresNotifEnableValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgAntRdPntEraseFailuresNotifEnable::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntEraseFailuresNotifLevelValidator</code> implements the value
   * validation for <code>EpcgAntRdPntEraseFailuresNotifLevel</code>.
   */
  static class EpcgAntRdPntEraseFailuresNotifLevelValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgAntRdPntEraseFailuresNotifLevel::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntLockFailuresNotifEnableValidator</code> implements the value
   * validation for <code>EpcgAntRdPntLockFailuresNotifEnable</code>.
   */
  static class EpcgAntRdPntLockFailuresNotifEnableValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgAntRdPntLockFailuresNotifEnable::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntLockFailuresNotifLevelValidator</code> implements the value
   * validation for <code>EpcgAntRdPntLockFailuresNotifLevel</code>.
   */
  static class EpcgAntRdPntLockFailuresNotifLevelValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgAntRdPntLockFailuresNotifLevel::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntReadFailureSuppressIntervalValidator</code> implements the value
   * validation for <code>EpcgAntRdPntReadFailureSuppressInterval</code>.
   */
  static class EpcgAntRdPntReadFailureSuppressIntervalValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgAntRdPntReadFailureSuppressInterval::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntWriteFailureSuppressIntervalValidator</code> implements the value
   * validation for <code>EpcgAntRdPntWriteFailureSuppressInterval</code>.
   */
  static class EpcgAntRdPntWriteFailureSuppressIntervalValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgAntRdPntWriteFailureSuppressInterval::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntKillFailureSuppressIntervalValidator</code> implements the value
   * validation for <code>EpcgAntRdPntKillFailureSuppressInterval</code>.
   */
  static class EpcgAntRdPntKillFailureSuppressIntervalValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgAntRdPntKillFailureSuppressInterval::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntEraseFailureSuppressIntervalValidator</code> implements the value
   * validation for <code>EpcgAntRdPntEraseFailureSuppressInterval</code>.
   */
  static class EpcgAntRdPntEraseFailureSuppressIntervalValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgAntRdPntEraseFailureSuppressInterval::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgAntRdPntLockFailureSuppressIntervalValidator</code> implements the value
   * validation for <code>EpcgAntRdPntLockFailureSuppressInterval</code>.
   */
  static class EpcgAntRdPntLockFailureSuppressIntervalValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgAntRdPntLockFailureSuppressInterval::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgIoPortAdminStatusValidator</code> implements the value
   * validation for <code>EpcgIoPortAdminStatus</code>.
   */
  static class EpcgIoPortAdminStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgIoPortAdminStatus::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgIoPortOperStatusNotifEnableValidator</code> implements the value
   * validation for <code>EpcgIoPortOperStatusNotifEnable</code>.
   */
  static class EpcgIoPortOperStatusNotifEnableValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgIoPortOperStatusNotifEnable::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgIoPortOperStatusNotifLevelValidator</code> implements the value
   * validation for <code>EpcgIoPortOperStatusNotifLevel</code>.
   */
  static class EpcgIoPortOperStatusNotifLevelValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgIoPortOperStatusNotifLevel::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgIoPortOperStatusNotifFromStateValidator</code> implements the value
   * validation for <code>EpcgIoPortOperStatusNotifFromState</code>.
   */
  static class EpcgIoPortOperStatusNotifFromStateValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (os.length() != 1) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=epcgIoPortOperStatusNotifFromState::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgIoPortOperStatusNotifToStateValidator</code> implements the value
   * validation for <code>EpcgIoPortOperStatusNotifToState</code>.
   */
  static class EpcgIoPortOperStatusNotifToStateValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (os.length() != 1) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=epcgIoPortOperStatusNotifToState::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgIoPortOperStateSuppressIntervalValidator</code> implements the value
   * validation for <code>EpcgIoPortOperStateSuppressInterval</code>.
   */
  static class EpcgIoPortOperStateSuppressIntervalValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgIoPortOperStateSuppressInterval::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcReadCyclesPerTriggerValidator</code> implements the value
   * validation for <code>EpcgSrcReadCyclesPerTrigger</code>.
   */
  static class EpcgSrcReadCyclesPerTriggerValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgSrcReadCyclesPerTrigger::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcReadDutyCycleValidator</code> implements the value
   * validation for <code>EpcgSrcReadDutyCycle</code>.
   */
  static class EpcgSrcReadDutyCycleValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 100L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgSrcReadDutyCycle::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcReadTimeoutValidator</code> implements the value
   * validation for <code>EpcgSrcReadTimeout</code>.
   */
  static class EpcgSrcReadTimeoutValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgSrcReadTimeout::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcGlimpsedTimeoutValidator</code> implements the value
   * validation for <code>EpcgSrcGlimpsedTimeout</code>.
   */
  static class EpcgSrcGlimpsedTimeoutValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgSrcGlimpsedTimeout::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcObservedThresholdValidator</code> implements the value
   * validation for <code>EpcgSrcObservedThreshold</code>.
   */
  static class EpcgSrcObservedThresholdValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgSrcObservedThreshold::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcObservedTimeoutValidator</code> implements the value
   * validation for <code>EpcgSrcObservedTimeout</code>.
   */
  static class EpcgSrcObservedTimeoutValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgSrcObservedTimeout::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcLostTimeoutValidator</code> implements the value
   * validation for <code>EpcgSrcLostTimeout</code>.
   */
  static class EpcgSrcLostTimeoutValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgSrcLostTimeout::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcAdminStatusValidator</code> implements the value
   * validation for <code>EpcgSrcAdminStatus</code>.
   */
  static class EpcgSrcAdminStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgSrcAdminStatus::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcOperStatusNotifEnableValidator</code> implements the value
   * validation for <code>EpcgSrcOperStatusNotifEnable</code>.
   */
  static class EpcgSrcOperStatusNotifEnableValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgSrcOperStatusNotifEnable::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcOperStatusNotifFromStateValidator</code> implements the value
   * validation for <code>EpcgSrcOperStatusNotifFromState</code>.
   */
  static class EpcgSrcOperStatusNotifFromStateValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (os.length() != 1) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=epcgSrcOperStatusNotifFromState::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcOperStatusNotifToStateValidator</code> implements the value
   * validation for <code>EpcgSrcOperStatusNotifToState</code>.
   */
  static class EpcgSrcOperStatusNotifToStateValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (os.length() != 1) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=epcgSrcOperStatusNotifToState::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcOperStatusNotifyLevelValidator</code> implements the value
   * validation for <code>EpcgSrcOperStatusNotifyLevel</code>.
   */
  static class EpcgSrcOperStatusNotifyLevelValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgSrcOperStatusNotifyLevel::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgSrcOperStateSuppressIntervalValidator</code> implements the value
   * validation for <code>EpcgSrcOperStateSuppressInterval</code>.
   */
  static class EpcgSrcOperStateSuppressIntervalValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgSrcOperStateSuppressInterval::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgRdPntSrcRowStatusValidator</code> implements the value
   * validation for <code>EpcgRdPntSrcRowStatus</code>.
   */
  static class EpcgRdPntSrcRowStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgRdPntSrcRowStatus::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgNotifChanSrcRowStatusValidator</code> implements the value
   * validation for <code>EpcgNotifChanSrcRowStatus</code>.
   */
  static class EpcgNotifChanSrcRowStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgNotifChanSrcRowStatus::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgNotifChanAdminStatusValidator</code> implements the value
   * validation for <code>EpcgNotifChanAdminStatus</code>.
   */
  static class EpcgNotifChanAdminStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgNotifChanAdminStatus::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgNotifChanOperNotifEnableValidator</code> implements the value
   * validation for <code>EpcgNotifChanOperNotifEnable</code>.
   */
  static class EpcgNotifChanOperNotifEnableValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgNotifChanOperNotifEnable::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgNotifChanOperNotifLevelValidator</code> implements the value
   * validation for <code>EpcgNotifChanOperNotifLevel</code>.
   */
  static class EpcgNotifChanOperNotifLevelValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgNotifChanOperNotifLevel::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgNotifChanOperNotifFromStateValidator</code> implements the value
   * validation for <code>EpcgNotifChanOperNotifFromState</code>.
   */
  static class EpcgNotifChanOperNotifFromStateValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (os.length() != 1) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=epcgNotifChanOperNotifFromState::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgNotifChanOperNotifToStateValidator</code> implements the value
   * validation for <code>EpcgNotifChanOperNotifToState</code>.
   */
  static class EpcgNotifChanOperNotifToStateValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (os.length() != 1) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=epcgNotifChanOperNotifToState::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgNotifChanOperStateSuppressIntervalValidator</code> implements the value
   * validation for <code>EpcgNotifChanOperStateSuppressInterval</code>.
   */
  static class EpcgNotifChanOperStateSuppressIntervalValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((UnsignedInteger32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 0L)) ||
          ((v >= 1L) && (v <= 3600L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=epcgNotifChanOperStateSuppressInterval::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgNotifTrigRowStatusValidator</code> implements the value
   * validation for <code>EpcgNotifTrigRowStatus</code>.
   */
  static class EpcgNotifTrigRowStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgNotifTrigRowStatus::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>EpcgReadTrigRowStatusValidator</code> implements the value
   * validation for <code>EpcgReadTrigRowStatus</code>.
   */
  static class EpcgReadTrigRowStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=epcgReadTrigRowStatus::validate
     //--AgentGen END
    }
  }

  // Rows and Factories
//  public class EpcgGlobalCountersEntryRow extends DefaultMOMutableRow2PC {
//    public EpcgGlobalCountersEntryRow(OID index, Variable[] values) {
//      super(index, values);
//    }
//    
//    public Gauge32 getEpcgGlobalCountersData() {
//      return (Gauge32) getValue(idxEpcgGlobalCountersData);
//    }  
//    
//    public void setEpcgGlobalCountersData(Gauge32 newValue) {
//      setValue(idxEpcgGlobalCountersData, newValue);
//    }
//    
//
//     //--AgentGen BEGIN=epcgGlobalCountersEntry::Row
//     //--AgentGen END
//  }
//  
//  class EpcgGlobalCountersEntryRowFactory 
//        extends DefaultMOMutableRow2PCFactory 
//  {
//    public synchronized MOTableRow createRow(OID index, Variable[] values) 
//        throws UnsupportedOperationException 
//    {
//      EpcgGlobalCountersEntryRow row = new EpcgGlobalCountersEntryRow(index, values);
//     //--AgentGen BEGIN=epcgGlobalCountersEntry::createRow
//     //--AgentGen END
//      return row;
//    }
//    
//    public synchronized void freeRow(MOTableRow row) {
//     //--AgentGen BEGIN=epcgGlobalCountersEntry::freeRow
//     //--AgentGen END
//    }
//
//     //--AgentGen BEGIN=epcgGlobalCountersEntry::RowFactory
//     //--AgentGen END
//  }
//  public class EpcgReaderServerEntryRow extends DefaultMOMutableRow2PC {
//    public EpcgReaderServerEntryRow(OID index, Variable[] values) {
//      super(index, values);
//    }
//    
//    public Integer32 getEpcgReaderServerAddressType() {
//      return (Integer32) getValue(idxEpcgReaderServerAddressType);
//    }  
//    
//    public void setEpcgReaderServerAddressType(Integer32 newValue) {
//      setValue(idxEpcgReaderServerAddressType, newValue);
//    }
//    
//    public OctetString getEpcgReaderServerAddress() {
//      return (OctetString) getValue(idxEpcgReaderServerAddress);
//    }  
//    
//    public void setEpcgReaderServerAddress(OctetString newValue) {
//      setValue(idxEpcgReaderServerAddress, newValue);
//    }
//    
//    public Integer32 getEpcgReaderServerRowStatus() {
//      return (Integer32) getValue(idxEpcgReaderServerRowStatus);
//    }  
//    
//    public void setEpcgReaderServerRowStatus(Integer32 newValue) {
//      setValue(idxEpcgReaderServerRowStatus, newValue);
//    }
//    
//
//     //--AgentGen BEGIN=epcgReaderServerEntry::Row
//     //--AgentGen END
//  }
//  
//  class EpcgReaderServerEntryRowFactory 
//        extends DefaultMOMutableRow2PCFactory 
//  {
//    public synchronized MOTableRow createRow(OID index, Variable[] values) 
//        throws UnsupportedOperationException 
//    {
//      EpcgReaderServerEntryRow row = new EpcgReaderServerEntryRow(index, values);
//     //--AgentGen BEGIN=epcgReaderServerEntry::createRow
//     //--AgentGen END
//      return row;
//    }
//    
//    public synchronized void freeRow(MOTableRow row) {
//     //--AgentGen BEGIN=epcgReaderServerEntry::freeRow
//     //--AgentGen END
//    }
//
//     //--AgentGen BEGIN=epcgReaderServerEntry::RowFactory
//     //--AgentGen END
//  }
//  public class EpcgReadPointEntryRow extends DefaultMOMutableRow2PC {
//    public EpcgReadPointEntryRow(OID index, Variable[] values) {
//      super(index, values);
//    }
//    
//    public OctetString getEpcgReadPointName() {
//      return (OctetString) getValue(idxEpcgReadPointName);
//    }  
//    
//    public void setEpcgReadPointName(OctetString newValue) {
//      setValue(idxEpcgReadPointName, newValue);
//    }
//    
//    public OctetString getEpcgReadPointDescription() {
//      return (OctetString) getValue(idxEpcgReadPointDescription);
//    }  
//    
//    public void setEpcgReadPointDescription(OctetString newValue) {
//      setValue(idxEpcgReadPointDescription, newValue);
//    }
//    
//    public Integer32 getEpcgReadPointAdminStatus() {
//      return (Integer32) getValue(idxEpcgReadPointAdminStatus);
//    }  
//    
//    public void setEpcgReadPointAdminStatus(Integer32 newValue) {
//      setValue(idxEpcgReadPointAdminStatus, newValue);
//    }
//    
//    public Integer32 getEpcgReadPointOperStatus() {
//      return (Integer32) getValue(idxEpcgReadPointOperStatus);
//    }  
//    
//    public void setEpcgReadPointOperStatus(Integer32 newValue) {
//      setValue(idxEpcgReadPointOperStatus, newValue);
//    }
//    
//    public Integer32 getEpcgReadPointOperStateNotifyEnable() {
//      return (Integer32) getValue(idxEpcgReadPointOperStateNotifyEnable);
//    }  
//    
//    public void setEpcgReadPointOperStateNotifyEnable(Integer32 newValue) {
//      setValue(idxEpcgReadPointOperStateNotifyEnable, newValue);
//    }
//    
//    public OctetString getEpcgReadPointOperNotifyFromState() {
//      return (OctetString) getValue(idxEpcgReadPointOperNotifyFromState);
//    }  
//    
//    public void setEpcgReadPointOperNotifyFromState(OctetString newValue) {
//      setValue(idxEpcgReadPointOperNotifyFromState, newValue);
//    }
//    
//    public OctetString getEpcgReadPointOperNotifyToState() {
//      return (OctetString) getValue(idxEpcgReadPointOperNotifyToState);
//    }  
//    
//    public void setEpcgReadPointOperNotifyToState(OctetString newValue) {
//      setValue(idxEpcgReadPointOperNotifyToState, newValue);
//    }
//    
//    public Integer32 getEpcgReadPointOperNotifyStateLevel() {
//      return (Integer32) getValue(idxEpcgReadPointOperNotifyStateLevel);
//    }  
//    
//    public void setEpcgReadPointOperNotifyStateLevel(Integer32 newValue) {
//      setValue(idxEpcgReadPointOperNotifyStateLevel, newValue);
//    }
//    
//    public Integer32 getEpcgReadPointPriorOperStatus() {
//      return (Integer32) getValue(idxEpcgReadPointPriorOperStatus);
//    }  
//    
//    public void setEpcgReadPointPriorOperStatus(Integer32 newValue) {
//      setValue(idxEpcgReadPointPriorOperStatus, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgReadPointOperStateSuppressInterval() {
//      return (UnsignedInteger32) getValue(idxEpcgReadPointOperStateSuppressInterval);
//    }  
//    
//    public void setEpcgReadPointOperStateSuppressInterval(UnsignedInteger32 newValue) {
//      setValue(idxEpcgReadPointOperStateSuppressInterval, newValue);
//    }
//    
//    public Counter32 getEpcgReadPointOperStateSuppressions() {
//      return (Counter32) getValue(idxEpcgReadPointOperStateSuppressions);
//    }  
//    
//    public void setEpcgReadPointOperStateSuppressions(Counter32 newValue) {
//      setValue(idxEpcgReadPointOperStateSuppressions, newValue);
//    }
//    
//
//     //--AgentGen BEGIN=epcgReadPointEntry::Row
//     //--AgentGen END
//  }
//  
//  class EpcgReadPointEntryRowFactory 
//        extends DefaultMOMutableRow2PCFactory 
//  {
//    public synchronized MOTableRow createRow(OID index, Variable[] values) 
//        throws UnsupportedOperationException 
//    {
//      EpcgReadPointEntryRow row = new EpcgReadPointEntryRow(index, values);
//     //--AgentGen BEGIN=epcgReadPointEntry::createRow
//     //--AgentGen END
//      return row;
//    }
//    
//    public synchronized void freeRow(MOTableRow row) {
//     //--AgentGen BEGIN=epcgReadPointEntry::freeRow
//     //--AgentGen END
//    }
//
//     //--AgentGen BEGIN=epcgReadPointEntry::RowFactory
//     //--AgentGen END
//  }
//  public class EpcgAntReadPointEntryRow extends DefaultMOMutableRow2PC {
//    public EpcgAntReadPointEntryRow(OID index, Variable[] values) {
//      super(index, values);
//    }
//    
//    public Gauge32 getEpcgAntRdPntTagsIdentified() {
//      return (Gauge32) getValue(idxEpcgAntRdPntTagsIdentified);
//    }  
//    
//    public void setEpcgAntRdPntTagsIdentified(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntTagsIdentified, newValue);
//    }
//    
//    public Gauge32 getEpcgAntRdPntTagsNotIdentified() {
//      return (Gauge32) getValue(idxEpcgAntRdPntTagsNotIdentified);
//    }  
//    
//    public void setEpcgAntRdPntTagsNotIdentified(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntTagsNotIdentified, newValue);
//    }
//    
//    public Gauge32 getEpcgAntRdPntMemoryReadFailures() {
//      return (Gauge32) getValue(idxEpcgAntRdPntMemoryReadFailures);
//    }  
//    
//    public void setEpcgAntRdPntMemoryReadFailures(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntMemoryReadFailures, newValue);
//    }
//    
//    public Integer32 getEpcgAntRdPntReadFailureNotifEnable() {
//      return (Integer32) getValue(idxEpcgAntRdPntReadFailureNotifEnable);
//    }  
//    
//    public void setEpcgAntRdPntReadFailureNotifEnable(Integer32 newValue) {
//      setValue(idxEpcgAntRdPntReadFailureNotifEnable, newValue);
//    }
//    
//    public Integer32 getEpcgAntRdPntReadFailureNotifLevel() {
//      return (Integer32) getValue(idxEpcgAntRdPntReadFailureNotifLevel);
//    }  
//    
//    public void setEpcgAntRdPntReadFailureNotifLevel(Integer32 newValue) {
//      setValue(idxEpcgAntRdPntReadFailureNotifLevel, newValue);
//    }
//    
//    public Gauge32 getEpcgAntRdPntWriteOperations() {
//      return (Gauge32) getValue(idxEpcgAntRdPntWriteOperations);
//    }  
//    
//    public void setEpcgAntRdPntWriteOperations(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntWriteOperations, newValue);
//    }
//    
//    public Gauge32 getEpcgAntRdPntWriteFailures() {
//      return (Gauge32) getValue(idxEpcgAntRdPntWriteFailures);
//    }  
//    
//    public void setEpcgAntRdPntWriteFailures(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntWriteFailures, newValue);
//    }
//    
//    public Integer32 getEpcgAntRdPntWriteFailuresNotifEnable() {
//      return (Integer32) getValue(idxEpcgAntRdPntWriteFailuresNotifEnable);
//    }  
//    
//    public void setEpcgAntRdPntWriteFailuresNotifEnable(Integer32 newValue) {
//      setValue(idxEpcgAntRdPntWriteFailuresNotifEnable, newValue);
//    }
//    
//    public Integer32 getEpcgAntRdPntWriteFailuresNotifLevel() {
//      return (Integer32) getValue(idxEpcgAntRdPntWriteFailuresNotifLevel);
//    }  
//    
//    public void setEpcgAntRdPntWriteFailuresNotifLevel(Integer32 newValue) {
//      setValue(idxEpcgAntRdPntWriteFailuresNotifLevel, newValue);
//    }
//    
//    public Gauge32 getEpcgAntRdPntKillOperations() {
//      return (Gauge32) getValue(idxEpcgAntRdPntKillOperations);
//    }  
//    
//    public void setEpcgAntRdPntKillOperations(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntKillOperations, newValue);
//    }
//    
//    public Gauge32 getEpcgAntRdPntKillFailures() {
//      return (Gauge32) getValue(idxEpcgAntRdPntKillFailures);
//    }  
//    
//    public void setEpcgAntRdPntKillFailures(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntKillFailures, newValue);
//    }
//    
//    public Integer32 getEpcgAntRdPntKillFailuresNotifEnable() {
//      return (Integer32) getValue(idxEpcgAntRdPntKillFailuresNotifEnable);
//    }  
//    
//    public void setEpcgAntRdPntKillFailuresNotifEnable(Integer32 newValue) {
//      setValue(idxEpcgAntRdPntKillFailuresNotifEnable, newValue);
//    }
//    
//    public Integer32 getEpcgAntRdPntKillFailuresNotifLevel() {
//      return (Integer32) getValue(idxEpcgAntRdPntKillFailuresNotifLevel);
//    }  
//    
//    public void setEpcgAntRdPntKillFailuresNotifLevel(Integer32 newValue) {
//      setValue(idxEpcgAntRdPntKillFailuresNotifLevel, newValue);
//    }
//    
//    public Gauge32 getEpcgAntRdPntEraseOperations() {
//      return (Gauge32) getValue(idxEpcgAntRdPntEraseOperations);
//    }  
//    
//    public void setEpcgAntRdPntEraseOperations(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntEraseOperations, newValue);
//    }
//    
//    public Gauge32 getEpcgAntRdPntEraseFailures() {
//      return (Gauge32) getValue(idxEpcgAntRdPntEraseFailures);
//    }  
//    
//    public void setEpcgAntRdPntEraseFailures(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntEraseFailures, newValue);
//    }
//    
//    public Integer32 getEpcgAntRdPntEraseFailuresNotifEnable() {
//      return (Integer32) getValue(idxEpcgAntRdPntEraseFailuresNotifEnable);
//    }  
//    
//    public void setEpcgAntRdPntEraseFailuresNotifEnable(Integer32 newValue) {
//      setValue(idxEpcgAntRdPntEraseFailuresNotifEnable, newValue);
//    }
//    
//    public Integer32 getEpcgAntRdPntEraseFailuresNotifLevel() {
//      return (Integer32) getValue(idxEpcgAntRdPntEraseFailuresNotifLevel);
//    }  
//    
//    public void setEpcgAntRdPntEraseFailuresNotifLevel(Integer32 newValue) {
//      setValue(idxEpcgAntRdPntEraseFailuresNotifLevel, newValue);
//    }
//    
//    public Gauge32 getEpcgAntRdPntLockOperations() {
//      return (Gauge32) getValue(idxEpcgAntRdPntLockOperations);
//    }  
//    
//    public void setEpcgAntRdPntLockOperations(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntLockOperations, newValue);
//    }
//    
//    public Gauge32 getEpcgAntRdPntLockFailures() {
//      return (Gauge32) getValue(idxEpcgAntRdPntLockFailures);
//    }  
//    
//    public void setEpcgAntRdPntLockFailures(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntLockFailures, newValue);
//    }
//    
//    public Integer32 getEpcgAntRdPntLockFailuresNotifEnable() {
//      return (Integer32) getValue(idxEpcgAntRdPntLockFailuresNotifEnable);
//    }  
//    
//    public void setEpcgAntRdPntLockFailuresNotifEnable(Integer32 newValue) {
//      setValue(idxEpcgAntRdPntLockFailuresNotifEnable, newValue);
//    }
//    
//    public Integer32 getEpcgAntRdPntLockFailuresNotifLevel() {
//      return (Integer32) getValue(idxEpcgAntRdPntLockFailuresNotifLevel);
//    }  
//    
//    public void setEpcgAntRdPntLockFailuresNotifLevel(Integer32 newValue) {
//      setValue(idxEpcgAntRdPntLockFailuresNotifLevel, newValue);
//    }
//    
//    public Integer32 getEpcgAntRdPntPowerLevel() {
//      return (Integer32) getValue(idxEpcgAntRdPntPowerLevel);
//    }  
//    
//    public void setEpcgAntRdPntPowerLevel(Integer32 newValue) {
//      setValue(idxEpcgAntRdPntPowerLevel, newValue);
//    }
//    
//    public Integer32 getEpcgAntRdPntNoiseLevel() {
//      return (Integer32) getValue(idxEpcgAntRdPntNoiseLevel);
//    }  
//    
//    public void setEpcgAntRdPntNoiseLevel(Integer32 newValue) {
//      setValue(idxEpcgAntRdPntNoiseLevel, newValue);
//    }
//    
//    public Gauge32 getEpcgAntRdPntTimeEnergized() {
//      return (Gauge32) getValue(idxEpcgAntRdPntTimeEnergized);
//    }  
//    
//    public void setEpcgAntRdPntTimeEnergized(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntTimeEnergized, newValue);
//    }
//    
//    public Gauge32 getEpcgAntRdPntMemoryReadOperations() {
//      return (Gauge32) getValue(idxEpcgAntRdPntMemoryReadOperations);
//    }  
//    
//    public void setEpcgAntRdPntMemoryReadOperations(Gauge32 newValue) {
//      setValue(idxEpcgAntRdPntMemoryReadOperations, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgAntRdPntReadFailureSuppressInterval() {
//      return (UnsignedInteger32) getValue(idxEpcgAntRdPntReadFailureSuppressInterval);
//    }  
//    
//    public void setEpcgAntRdPntReadFailureSuppressInterval(UnsignedInteger32 newValue) {
//      setValue(idxEpcgAntRdPntReadFailureSuppressInterval, newValue);
//    }
//    
//    public Counter32 getEpcgAntRdPntReadFailureSuppressions() {
//      return (Counter32) getValue(idxEpcgAntRdPntReadFailureSuppressions);
//    }  
//    
//    public void setEpcgAntRdPntReadFailureSuppressions(Counter32 newValue) {
//      setValue(idxEpcgAntRdPntReadFailureSuppressions, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgAntRdPntWriteFailureSuppressInterval() {
//      return (UnsignedInteger32) getValue(idxEpcgAntRdPntWriteFailureSuppressInterval);
//    }  
//    
//    public void setEpcgAntRdPntWriteFailureSuppressInterval(UnsignedInteger32 newValue) {
//      setValue(idxEpcgAntRdPntWriteFailureSuppressInterval, newValue);
//    }
//    
//    public Counter32 getEpcgAntRdPntWriteFailureSuppressions() {
//      return (Counter32) getValue(idxEpcgAntRdPntWriteFailureSuppressions);
//    }  
//    
//    public void setEpcgAntRdPntWriteFailureSuppressions(Counter32 newValue) {
//      setValue(idxEpcgAntRdPntWriteFailureSuppressions, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgAntRdPntKillFailureSuppressInterval() {
//      return (UnsignedInteger32) getValue(idxEpcgAntRdPntKillFailureSuppressInterval);
//    }  
//    
//    public void setEpcgAntRdPntKillFailureSuppressInterval(UnsignedInteger32 newValue) {
//      setValue(idxEpcgAntRdPntKillFailureSuppressInterval, newValue);
//    }
//    
//    public Counter32 getEpcgAntRdPntKillFailureSuppressions() {
//      return (Counter32) getValue(idxEpcgAntRdPntKillFailureSuppressions);
//    }  
//    
//    public void setEpcgAntRdPntKillFailureSuppressions(Counter32 newValue) {
//      setValue(idxEpcgAntRdPntKillFailureSuppressions, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgAntRdPntEraseFailureSuppressInterval() {
//      return (UnsignedInteger32) getValue(idxEpcgAntRdPntEraseFailureSuppressInterval);
//    }  
//    
//    public void setEpcgAntRdPntEraseFailureSuppressInterval(UnsignedInteger32 newValue) {
//      setValue(idxEpcgAntRdPntEraseFailureSuppressInterval, newValue);
//    }
//    
//    public Counter32 getEpcgAntRdPntEraseFailureSuppressions() {
//      return (Counter32) getValue(idxEpcgAntRdPntEraseFailureSuppressions);
//    }  
//    
//    public void setEpcgAntRdPntEraseFailureSuppressions(Counter32 newValue) {
//      setValue(idxEpcgAntRdPntEraseFailureSuppressions, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgAntRdPntLockFailureSuppressInterval() {
//      return (UnsignedInteger32) getValue(idxEpcgAntRdPntLockFailureSuppressInterval);
//    }  
//    
//    public void setEpcgAntRdPntLockFailureSuppressInterval(UnsignedInteger32 newValue) {
//      setValue(idxEpcgAntRdPntLockFailureSuppressInterval, newValue);
//    }
//    
//    public Counter32 getEpcgAntRdPntLockFailureSuppressions() {
//      return (Counter32) getValue(idxEpcgAntRdPntLockFailureSuppressions);
//    }  
//    
//    public void setEpcgAntRdPntLockFailureSuppressions(Counter32 newValue) {
//      setValue(idxEpcgAntRdPntLockFailureSuppressions, newValue);
//    }
//    
//
//     //--AgentGen BEGIN=epcgAntReadPointEntry::Row
//     //--AgentGen END
//  }
//  
//  class EpcgAntReadPointEntryRowFactory 
//        extends DefaultMOMutableRow2PCFactory 
//  {
//    public synchronized MOTableRow createRow(OID index, Variable[] values) 
//        throws UnsupportedOperationException 
//    {
//      EpcgAntReadPointEntryRow row = new EpcgAntReadPointEntryRow(index, values);
//     //--AgentGen BEGIN=epcgAntReadPointEntry::createRow
//     //--AgentGen END
//      return row;
//    }
//    
//    public synchronized void freeRow(MOTableRow row) {
//     //--AgentGen BEGIN=epcgAntReadPointEntry::freeRow
//     //--AgentGen END
//    }
//
//     //--AgentGen BEGIN=epcgAntReadPointEntry::RowFactory
//     //--AgentGen END
//  }
//  public class EpcgIoPortEntryRow extends DefaultMOMutableRow2PC {
//    public EpcgIoPortEntryRow(OID index, Variable[] values) {
//      super(index, values);
//    }
//    
//    public OctetString getEpcgIoPortName() {
//      return (OctetString) getValue(idxEpcgIoPortName);
//    }  
//    
//    public void setEpcgIoPortName(OctetString newValue) {
//      setValue(idxEpcgIoPortName, newValue);
//    }
//    
//    public Integer32 getEpcgIoPortAdminStatus() {
//      return (Integer32) getValue(idxEpcgIoPortAdminStatus);
//    }  
//    
//    public void setEpcgIoPortAdminStatus(Integer32 newValue) {
//      setValue(idxEpcgIoPortAdminStatus, newValue);
//    }
//    
//    public Integer32 getEpcgIoPortOperStatus() {
//      return (Integer32) getValue(idxEpcgIoPortOperStatus);
//    }  
//    
//    public void setEpcgIoPortOperStatus(Integer32 newValue) {
//      setValue(idxEpcgIoPortOperStatus, newValue);
//    }
//    
//    public Integer32 getEpcgIoPortOperStatusNotifEnable() {
//      return (Integer32) getValue(idxEpcgIoPortOperStatusNotifEnable);
//    }  
//    
//    public void setEpcgIoPortOperStatusNotifEnable(Integer32 newValue) {
//      setValue(idxEpcgIoPortOperStatusNotifEnable, newValue);
//    }
//    
//    public Integer32 getEpcgIoPortOperStatusNotifLevel() {
//      return (Integer32) getValue(idxEpcgIoPortOperStatusNotifLevel);
//    }  
//    
//    public void setEpcgIoPortOperStatusNotifLevel(Integer32 newValue) {
//      setValue(idxEpcgIoPortOperStatusNotifLevel, newValue);
//    }
//    
//    public OctetString getEpcgIoPortOperStatusNotifFromState() {
//      return (OctetString) getValue(idxEpcgIoPortOperStatusNotifFromState);
//    }  
//    
//    public void setEpcgIoPortOperStatusNotifFromState(OctetString newValue) {
//      setValue(idxEpcgIoPortOperStatusNotifFromState, newValue);
//    }
//    
//    public OctetString getEpcgIoPortOperStatusNotifToState() {
//      return (OctetString) getValue(idxEpcgIoPortOperStatusNotifToState);
//    }  
//    
//    public void setEpcgIoPortOperStatusNotifToState(OctetString newValue) {
//      setValue(idxEpcgIoPortOperStatusNotifToState, newValue);
//    }
//    
//    public OctetString getEpcgIoPortDescription() {
//      return (OctetString) getValue(idxEpcgIoPortDescription);
//    }  
//    
//    public void setEpcgIoPortDescription(OctetString newValue) {
//      setValue(idxEpcgIoPortDescription, newValue);
//    }
//    
//    public Integer32 getEpcgIoPortOperStatusPrior() {
//      return (Integer32) getValue(idxEpcgIoPortOperStatusPrior);
//    }  
//    
//    public void setEpcgIoPortOperStatusPrior(Integer32 newValue) {
//      setValue(idxEpcgIoPortOperStatusPrior, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgIoPortOperStateSuppressInterval() {
//      return (UnsignedInteger32) getValue(idxEpcgIoPortOperStateSuppressInterval);
//    }  
//    
//    public void setEpcgIoPortOperStateSuppressInterval(UnsignedInteger32 newValue) {
//      setValue(idxEpcgIoPortOperStateSuppressInterval, newValue);
//    }
//    
//    public Counter32 getEpcgIoPortOperStateSuppressions() {
//      return (Counter32) getValue(idxEpcgIoPortOperStateSuppressions);
//    }  
//    
//    public void setEpcgIoPortOperStateSuppressions(Counter32 newValue) {
//      setValue(idxEpcgIoPortOperStateSuppressions, newValue);
//    }
//    
//
//     //--AgentGen BEGIN=epcgIoPortEntry::Row
//     //--AgentGen END
//  }
//  
//  class EpcgIoPortEntryRowFactory 
//        extends DefaultMOMutableRow2PCFactory 
//  {
//    public synchronized MOTableRow createRow(OID index, Variable[] values) 
//        throws UnsupportedOperationException 
//    {
//      EpcgIoPortEntryRow row = new EpcgIoPortEntryRow(index, values);
//     //--AgentGen BEGIN=epcgIoPortEntry::createRow
//     //--AgentGen END
//      return row;
//    }
//    
//    public synchronized void freeRow(MOTableRow row) {
//     //--AgentGen BEGIN=epcgIoPortEntry::freeRow
//     //--AgentGen END
//    }
//
//     //--AgentGen BEGIN=epcgIoPortEntry::RowFactory
//     //--AgentGen END
//  }
//  public class EpcgSourceEntryRow extends DefaultMOMutableRow2PC {
//    public EpcgSourceEntryRow(OID index, Variable[] values) {
//      super(index, values);
//    }
//    
//    public OctetString getEpcgSrcName() {
//      return (OctetString) getValue(idxEpcgSrcName);
//    }  
//    
//    public void setEpcgSrcName(OctetString newValue) {
//      setValue(idxEpcgSrcName, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgSrcReadCyclesPerTrigger() {
//      return (UnsignedInteger32) getValue(idxEpcgSrcReadCyclesPerTrigger);
//    }  
//    
//    public void setEpcgSrcReadCyclesPerTrigger(UnsignedInteger32 newValue) {
//      setValue(idxEpcgSrcReadCyclesPerTrigger, newValue);
//    }
//    
//    public Gauge32 getEpcgSrcReadDutyCycle() {
//      return (Gauge32) getValue(idxEpcgSrcReadDutyCycle);
//    }  
//    
//    public void setEpcgSrcReadDutyCycle(Gauge32 newValue) {
//      setValue(idxEpcgSrcReadDutyCycle, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgSrcReadTimeout() {
//      return (UnsignedInteger32) getValue(idxEpcgSrcReadTimeout);
//    }  
//    
//    public void setEpcgSrcReadTimeout(UnsignedInteger32 newValue) {
//      setValue(idxEpcgSrcReadTimeout, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgSrcGlimpsedTimeout() {
//      return (UnsignedInteger32) getValue(idxEpcgSrcGlimpsedTimeout);
//    }  
//    
//    public void setEpcgSrcGlimpsedTimeout(UnsignedInteger32 newValue) {
//      setValue(idxEpcgSrcGlimpsedTimeout, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgSrcObservedThreshold() {
//      return (UnsignedInteger32) getValue(idxEpcgSrcObservedThreshold);
//    }  
//    
//    public void setEpcgSrcObservedThreshold(UnsignedInteger32 newValue) {
//      setValue(idxEpcgSrcObservedThreshold, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgSrcObservedTimeout() {
//      return (UnsignedInteger32) getValue(idxEpcgSrcObservedTimeout);
//    }  
//    
//    public void setEpcgSrcObservedTimeout(UnsignedInteger32 newValue) {
//      setValue(idxEpcgSrcObservedTimeout, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgSrcLostTimeout() {
//      return (UnsignedInteger32) getValue(idxEpcgSrcLostTimeout);
//    }  
//    
//    public void setEpcgSrcLostTimeout(UnsignedInteger32 newValue) {
//      setValue(idxEpcgSrcLostTimeout, newValue);
//    }
//    
//    public Gauge32 getEpcgSrcUnknownToGlimpsedTrans() {
//      return (Gauge32) getValue(idxEpcgSrcUnknownToGlimpsedTrans);
//    }  
//    
//    public void setEpcgSrcUnknownToGlimpsedTrans(Gauge32 newValue) {
//      setValue(idxEpcgSrcUnknownToGlimpsedTrans, newValue);
//    }
//    
//    public Gauge32 getEpcgSrcGlimpsedToUnknownTrans() {
//      return (Gauge32) getValue(idxEpcgSrcGlimpsedToUnknownTrans);
//    }  
//    
//    public void setEpcgSrcGlimpsedToUnknownTrans(Gauge32 newValue) {
//      setValue(idxEpcgSrcGlimpsedToUnknownTrans, newValue);
//    }
//    
//    public Gauge32 getEpcgSrcGlimpsedToObservedTrans() {
//      return (Gauge32) getValue(idxEpcgSrcGlimpsedToObservedTrans);
//    }  
//    
//    public void setEpcgSrcGlimpsedToObservedTrans(Gauge32 newValue) {
//      setValue(idxEpcgSrcGlimpsedToObservedTrans, newValue);
//    }
//    
//    public Gauge32 getEpcgSrcObservedToLostTrans() {
//      return (Gauge32) getValue(idxEpcgSrcObservedToLostTrans);
//    }  
//    
//    public void setEpcgSrcObservedToLostTrans(Gauge32 newValue) {
//      setValue(idxEpcgSrcObservedToLostTrans, newValue);
//    }
//    
//    public Gauge32 getEpcgSrcLostToGlimpsedTrans() {
//      return (Gauge32) getValue(idxEpcgSrcLostToGlimpsedTrans);
//    }  
//    
//    public void setEpcgSrcLostToGlimpsedTrans(Gauge32 newValue) {
//      setValue(idxEpcgSrcLostToGlimpsedTrans, newValue);
//    }
//    
//    public Gauge32 getEpcgSrcLostToUnknownTrans() {
//      return (Gauge32) getValue(idxEpcgSrcLostToUnknownTrans);
//    }  
//    
//    public void setEpcgSrcLostToUnknownTrans(Gauge32 newValue) {
//      setValue(idxEpcgSrcLostToUnknownTrans, newValue);
//    }
//    
//    public Integer32 getEpcgSrcAdminStatus() {
//      return (Integer32) getValue(idxEpcgSrcAdminStatus);
//    }  
//    
//    public void setEpcgSrcAdminStatus(Integer32 newValue) {
//      setValue(idxEpcgSrcAdminStatus, newValue);
//    }
//    
//    public Integer32 getEpcgSrcOperStatus() {
//      return (Integer32) getValue(idxEpcgSrcOperStatus);
//    }  
//    
//    public void setEpcgSrcOperStatus(Integer32 newValue) {
//      setValue(idxEpcgSrcOperStatus, newValue);
//    }
//    
//    public Integer32 getEpcgSrcOperStatusNotifEnable() {
//      return (Integer32) getValue(idxEpcgSrcOperStatusNotifEnable);
//    }  
//    
//    public void setEpcgSrcOperStatusNotifEnable(Integer32 newValue) {
//      setValue(idxEpcgSrcOperStatusNotifEnable, newValue);
//    }
//    
//    public OctetString getEpcgSrcOperStatusNotifFromState() {
//      return (OctetString) getValue(idxEpcgSrcOperStatusNotifFromState);
//    }  
//    
//    public void setEpcgSrcOperStatusNotifFromState(OctetString newValue) {
//      setValue(idxEpcgSrcOperStatusNotifFromState, newValue);
//    }
//    
//    public OctetString getEpcgSrcOperStatusNotifToState() {
//      return (OctetString) getValue(idxEpcgSrcOperStatusNotifToState);
//    }  
//    
//    public void setEpcgSrcOperStatusNotifToState(OctetString newValue) {
//      setValue(idxEpcgSrcOperStatusNotifToState, newValue);
//    }
//    
//    public Integer32 getEpcgSrcOperStatusNotifyLevel() {
//      return (Integer32) getValue(idxEpcgSrcOperStatusNotifyLevel);
//    }  
//    
//    public void setEpcgSrcOperStatusNotifyLevel(Integer32 newValue) {
//      setValue(idxEpcgSrcOperStatusNotifyLevel, newValue);
//    }
//    
//    public Integer32 getEpcgSrcSupportsWriteOperations() {
//      return (Integer32) getValue(idxEpcgSrcSupportsWriteOperations);
//    }  
//    
//    public void setEpcgSrcSupportsWriteOperations(Integer32 newValue) {
//      setValue(idxEpcgSrcSupportsWriteOperations, newValue);
//    }
//    
//    public Integer32 getEpcgSrcOperStatusPrior() {
//      return (Integer32) getValue(idxEpcgSrcOperStatusPrior);
//    }  
//    
//    public void setEpcgSrcOperStatusPrior(Integer32 newValue) {
//      setValue(idxEpcgSrcOperStatusPrior, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgSrcOperStateSuppressInterval() {
//      return (UnsignedInteger32) getValue(idxEpcgSrcOperStateSuppressInterval);
//    }  
//    
//    public void setEpcgSrcOperStateSuppressInterval(UnsignedInteger32 newValue) {
//      setValue(idxEpcgSrcOperStateSuppressInterval, newValue);
//    }
//    
//    public Counter32 getEpcgSrcOperStateSuppressions() {
//      return (Counter32) getValue(idxEpcgSrcOperStateSuppressions);
//    }  
//    
//    public void setEpcgSrcOperStateSuppressions(Counter32 newValue) {
//      setValue(idxEpcgSrcOperStateSuppressions, newValue);
//    }
//    
//
//     //--AgentGen BEGIN=epcgSourceEntry::Row
//     //--AgentGen END
//  }
//  
//  class EpcgSourceEntryRowFactory 
//        extends DefaultMOMutableRow2PCFactory 
//  {
//    public synchronized MOTableRow createRow(OID index, Variable[] values) 
//        throws UnsupportedOperationException 
//    {
//      EpcgSourceEntryRow row = new EpcgSourceEntryRow(index, values);
//     //--AgentGen BEGIN=epcgSourceEntry::createRow
//     //--AgentGen END
//      return row;
//    }
//    
//    public synchronized void freeRow(MOTableRow row) {
//     //--AgentGen BEGIN=epcgSourceEntry::freeRow
//     //--AgentGen END
//    }
//
//     //--AgentGen BEGIN=epcgSourceEntry::RowFactory
//     //--AgentGen END
//  }
//  public class EpcgRdPntSrcEntryRow extends DefaultMOMutableRow2PC {
//    public EpcgRdPntSrcEntryRow(OID index, Variable[] values) {
//      super(index, values);
//    }
//    
//    public Integer32 getEpcgRdPntSrcRowStatus() {
//      return (Integer32) getValue(idxEpcgRdPntSrcRowStatus);
//    }  
//    
//    public void setEpcgRdPntSrcRowStatus(Integer32 newValue) {
//      setValue(idxEpcgRdPntSrcRowStatus, newValue);
//    }
//    
//
//     //--AgentGen BEGIN=epcgRdPntSrcEntry::Row
//     //--AgentGen END
//  }
//  
//  class EpcgRdPntSrcEntryRowFactory 
//        extends DefaultMOMutableRow2PCFactory 
//  {
//    public synchronized MOTableRow createRow(OID index, Variable[] values) 
//        throws UnsupportedOperationException 
//    {
//      EpcgRdPntSrcEntryRow row = new EpcgRdPntSrcEntryRow(index, values);
//     //--AgentGen BEGIN=epcgRdPntSrcEntry::createRow
//     //--AgentGen END
//      return row;
//    }
//    
//    public synchronized void freeRow(MOTableRow row) {
//     //--AgentGen BEGIN=epcgRdPntSrcEntry::freeRow
//     //--AgentGen END
//    }
//
//     //--AgentGen BEGIN=epcgRdPntSrcEntry::RowFactory
//     //--AgentGen END
//  }
//  public class EpcgNotifChanSrcEntryRow extends DefaultMOMutableRow2PC {
//    public EpcgNotifChanSrcEntryRow(OID index, Variable[] values) {
//      super(index, values);
//    }
//    
//    public Integer32 getEpcgNotifChanSrcRowStatus() {
//      return (Integer32) getValue(idxEpcgNotifChanSrcRowStatus);
//    }  
//    
//    public void setEpcgNotifChanSrcRowStatus(Integer32 newValue) {
//      setValue(idxEpcgNotifChanSrcRowStatus, newValue);
//    }
//    
//
//     //--AgentGen BEGIN=epcgNotifChanSrcEntry::Row
//     //--AgentGen END
//  }
//  
//  class EpcgNotifChanSrcEntryRowFactory 
//        extends DefaultMOMutableRow2PCFactory 
//  {
//    public synchronized MOTableRow createRow(OID index, Variable[] values) 
//        throws UnsupportedOperationException 
//    {
//      EpcgNotifChanSrcEntryRow row = new EpcgNotifChanSrcEntryRow(index, values);
//     //--AgentGen BEGIN=epcgNotifChanSrcEntry::createRow
//     //--AgentGen END
//      return row;
//    }
//    
//    public synchronized void freeRow(MOTableRow row) {
//     //--AgentGen BEGIN=epcgNotifChanSrcEntry::freeRow
//     //--AgentGen END
//    }
//
//     //--AgentGen BEGIN=epcgNotifChanSrcEntry::RowFactory
//     //--AgentGen END
//  }
//  public class EpcgNotificationChannelEntryRow extends DefaultMOMutableRow2PC {
//    public EpcgNotificationChannelEntryRow(OID index, Variable[] values) {
//      super(index, values);
//    }
//    
//    public OctetString getEpcgNotifChanName() {
//      return (OctetString) getValue(idxEpcgNotifChanName);
//    }  
//    
//    public void setEpcgNotifChanName(OctetString newValue) {
//      setValue(idxEpcgNotifChanName, newValue);
//    }
//    
//    public Integer32 getEpcgNotifChanAddressType() {
//      return (Integer32) getValue(idxEpcgNotifChanAddressType);
//    }  
//    
//    public void setEpcgNotifChanAddressType(Integer32 newValue) {
//      setValue(idxEpcgNotifChanAddressType, newValue);
//    }
//    
//    public OctetString getEpcgNotifChanAddress() {
//      return (OctetString) getValue(idxEpcgNotifChanAddress);
//    }  
//    
//    public void setEpcgNotifChanAddress(OctetString newValue) {
//      setValue(idxEpcgNotifChanAddress, newValue);
//    }
//    
//    public OctetString getEpcgNotifChanLastAttempt() {
//      return (OctetString) getValue(idxEpcgNotifChanLastAttempt);
//    }  
//    
//    public void setEpcgNotifChanLastAttempt(OctetString newValue) {
//      setValue(idxEpcgNotifChanLastAttempt, newValue);
//    }
//    
//    public OctetString getEpcgNotifChanLastSuccess() {
//      return (OctetString) getValue(idxEpcgNotifChanLastSuccess);
//    }  
//    
//    public void setEpcgNotifChanLastSuccess(OctetString newValue) {
//      setValue(idxEpcgNotifChanLastSuccess, newValue);
//    }
//    
//    public Integer32 getEpcgNotifChanAdminStatus() {
//      return (Integer32) getValue(idxEpcgNotifChanAdminStatus);
//    }  
//    
//    public void setEpcgNotifChanAdminStatus(Integer32 newValue) {
//      setValue(idxEpcgNotifChanAdminStatus, newValue);
//    }
//    
//    public Integer32 getEpcgNotifChanOperStatus() {
//      return (Integer32) getValue(idxEpcgNotifChanOperStatus);
//    }  
//    
//    public void setEpcgNotifChanOperStatus(Integer32 newValue) {
//      setValue(idxEpcgNotifChanOperStatus, newValue);
//    }
//    
//    public Integer32 getEpcgNotifChanOperNotifEnable() {
//      return (Integer32) getValue(idxEpcgNotifChanOperNotifEnable);
//    }  
//    
//    public void setEpcgNotifChanOperNotifEnable(Integer32 newValue) {
//      setValue(idxEpcgNotifChanOperNotifEnable, newValue);
//    }
//    
//    public Integer32 getEpcgNotifChanOperNotifLevel() {
//      return (Integer32) getValue(idxEpcgNotifChanOperNotifLevel);
//    }  
//    
//    public void setEpcgNotifChanOperNotifLevel(Integer32 newValue) {
//      setValue(idxEpcgNotifChanOperNotifLevel, newValue);
//    }
//    
//    public OctetString getEpcgNotifChanOperNotifFromState() {
//      return (OctetString) getValue(idxEpcgNotifChanOperNotifFromState);
//    }  
//    
//    public void setEpcgNotifChanOperNotifFromState(OctetString newValue) {
//      setValue(idxEpcgNotifChanOperNotifFromState, newValue);
//    }
//    
//    public OctetString getEpcgNotifChanOperNotifToState() {
//      return (OctetString) getValue(idxEpcgNotifChanOperNotifToState);
//    }  
//    
//    public void setEpcgNotifChanOperNotifToState(OctetString newValue) {
//      setValue(idxEpcgNotifChanOperNotifToState, newValue);
//    }
//    
//    public Integer32 getEpcgNotifChanOperStatusPrior() {
//      return (Integer32) getValue(idxEpcgNotifChanOperStatusPrior);
//    }  
//    
//    public void setEpcgNotifChanOperStatusPrior(Integer32 newValue) {
//      setValue(idxEpcgNotifChanOperStatusPrior, newValue);
//    }
//    
//    public UnsignedInteger32 getEpcgNotifChanOperStateSuppressInterval() {
//      return (UnsignedInteger32) getValue(idxEpcgNotifChanOperStateSuppressInterval);
//    }  
//    
//    public void setEpcgNotifChanOperStateSuppressInterval(UnsignedInteger32 newValue) {
//      setValue(idxEpcgNotifChanOperStateSuppressInterval, newValue);
//    }
//    
//    public Counter32 getEpcgNotifChanOperStateSuppressions() {
//      return (Counter32) getValue(idxEpcgNotifChanOperStateSuppressions);
//    }  
//    
//    public void setEpcgNotifChanOperStateSuppressions(Counter32 newValue) {
//      setValue(idxEpcgNotifChanOperStateSuppressions, newValue);
//    }
//    
//
//     //--AgentGen BEGIN=epcgNotificationChannelEntry::Row
//     //--AgentGen END
//  }
//  
//  class EpcgNotificationChannelEntryRowFactory 
//        extends DefaultMOMutableRow2PCFactory 
//  {
//    public synchronized MOTableRow createRow(OID index, Variable[] values) 
//        throws UnsupportedOperationException 
//    {
//      EpcgNotificationChannelEntryRow row = new EpcgNotificationChannelEntryRow(index, values);
//     //--AgentGen BEGIN=epcgNotificationChannelEntry::createRow
//     //--AgentGen END
//      return row;
//    }
//    
//    public synchronized void freeRow(MOTableRow row) {
//     //--AgentGen BEGIN=epcgNotificationChannelEntry::freeRow
//     //--AgentGen END
//    }
//
//     //--AgentGen BEGIN=epcgNotificationChannelEntry::RowFactory
//     //--AgentGen END
//  }
//  public class EpcgTriggerEntryRow extends DefaultMOMutableRow2PC {
//    public EpcgTriggerEntryRow(OID index, Variable[] values) {
//      super(index, values);
//    }
//    
//    public OctetString getEpcgTrigName() {
//      return (OctetString) getValue(idxEpcgTrigName);
//    }  
//    
//    public void setEpcgTrigName(OctetString newValue) {
//      setValue(idxEpcgTrigName, newValue);
//    }
//    
//    public Integer32 getEpcgTrigType() {
//      return (Integer32) getValue(idxEpcgTrigType);
//    }  
//    
//    public void setEpcgTrigType(Integer32 newValue) {
//      setValue(idxEpcgTrigType, newValue);
//    }
//    
//    public OctetString getEpcgTrigParameters() {
//      return (OctetString) getValue(idxEpcgTrigParameters);
//    }  
//    
//    public void setEpcgTrigParameters(OctetString newValue) {
//      setValue(idxEpcgTrigParameters, newValue);
//    }
//    
//    public Gauge32 getEpcgTriggerMatches() {
//      return (Gauge32) getValue(idxEpcgTriggerMatches);
//    }  
//    
//    public void setEpcgTriggerMatches(Gauge32 newValue) {
//      setValue(idxEpcgTriggerMatches, newValue);
//    }
//    
//    public OID getEpcgTrigIoPort() {
//      return (OID) getValue(idxEpcgTrigIoPort);
//    }  
//    
//    public void setEpcgTrigIoPort(OID newValue) {
//      setValue(idxEpcgTrigIoPort, newValue);
//    }
//    
//
//     //--AgentGen BEGIN=epcgTriggerEntry::Row
//     //--AgentGen END
//  }
//  
//  class EpcgTriggerEntryRowFactory 
//        extends DefaultMOMutableRow2PCFactory 
//  {
//    public synchronized MOTableRow createRow(OID index, Variable[] values) 
//        throws UnsupportedOperationException 
//    {
//      EpcgTriggerEntryRow row = new EpcgTriggerEntryRow(index, values);
//     //--AgentGen BEGIN=epcgTriggerEntry::createRow
//     //--AgentGen END
//      return row;
//    }
//    
//    public synchronized void freeRow(MOTableRow row) {
//     //--AgentGen BEGIN=epcgTriggerEntry::freeRow
//     //--AgentGen END
//    }
//
//     //--AgentGen BEGIN=epcgTriggerEntry::RowFactory
//     //--AgentGen END
//  }
//  public class EpcgNotifTrigEntryRow extends DefaultMOMutableRow2PC {
//    public EpcgNotifTrigEntryRow(OID index, Variable[] values) {
//      super(index, values);
//    }
//    
//    public Integer32 getEpcgNotifTrigRowStatus() {
//      return (Integer32) getValue(idxEpcgNotifTrigRowStatus);
//    }  
//    
//    public void setEpcgNotifTrigRowStatus(Integer32 newValue) {
//      setValue(idxEpcgNotifTrigRowStatus, newValue);
//    }
//    
//
//     //--AgentGen BEGIN=epcgNotifTrigEntry::Row
//     //--AgentGen END
//  }
//  
//  class EpcgNotifTrigEntryRowFactory 
//        extends DefaultMOMutableRow2PCFactory 
//  {
//    public synchronized MOTableRow createRow(OID index, Variable[] values) 
//        throws UnsupportedOperationException 
//    {
//      EpcgNotifTrigEntryRow row = new EpcgNotifTrigEntryRow(index, values);
//     //--AgentGen BEGIN=epcgNotifTrigEntry::createRow
//     //--AgentGen END
//      return row;
//    }
//    
//    public synchronized void freeRow(MOTableRow row) {
//     //--AgentGen BEGIN=epcgNotifTrigEntry::freeRow
//     //--AgentGen END
//    }
//
//     //--AgentGen BEGIN=epcgNotifTrigEntry::RowFactory
//     //--AgentGen END
//  }
//  public class EpcgReadTrigEntryRow extends DefaultMOMutableRow2PC {
//    public EpcgReadTrigEntryRow(OID index, Variable[] values) {
//      super(index, values);
//    }
//    
//    public Integer32 getEpcgReadTrigRowStatus() {
//      return (Integer32) getValue(idxEpcgReadTrigRowStatus);
//    }  
//    
//    public void setEpcgReadTrigRowStatus(Integer32 newValue) {
//      setValue(idxEpcgReadTrigRowStatus, newValue);
//    }
//    
//
//     //--AgentGen BEGIN=epcgReadTrigEntry::Row
//     //--AgentGen END
//  }
//  
//  class EpcgReadTrigEntryRowFactory 
//        extends DefaultMOMutableRow2PCFactory 
//  {
//    public synchronized MOTableRow createRow(OID index, Variable[] values) 
//        throws UnsupportedOperationException 
//    {
//      EpcgReadTrigEntryRow row = new EpcgReadTrigEntryRow(index, values);
//     //--AgentGen BEGIN=epcgReadTrigEntry::createRow
//     //--AgentGen END
//      return row;
//    }
//    
//    public synchronized void freeRow(MOTableRow row) {
//     //--AgentGen BEGIN=epcgReadTrigEntry::freeRow
//     //--AgentGen END
//    }
//
//     //--AgentGen BEGIN=epcgReadTrigEntry::RowFactory
//     //--AgentGen END
//  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

//--AgentGen BEGIN=_CLASSES
//--AgentGen END

//--AgentGen BEGIN=_END
//--AgentGen END
}


