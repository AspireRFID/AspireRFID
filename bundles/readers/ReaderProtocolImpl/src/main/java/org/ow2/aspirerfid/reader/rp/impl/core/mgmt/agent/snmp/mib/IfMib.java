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
import org.snmp4j.agent.MOGroup;
import org.snmp4j.agent.MOServer;
import org.snmp4j.agent.NotificationOriginator;
import org.snmp4j.agent.mo.DefaultMOFactory;
import org.snmp4j.agent.mo.DefaultMOMutableRow2PC;
import org.snmp4j.agent.mo.DefaultMOMutableRow2PCFactory;
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
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.agent.mo.MOTableSubIndex;
import org.snmp4j.agent.mo.MOValueValidationEvent;
import org.snmp4j.agent.mo.MOValueValidationListener;
import org.snmp4j.agent.mo.snmp.DisplayString;
import org.snmp4j.agent.mo.snmp.Enumerated;
import org.snmp4j.agent.mo.snmp.RowStatus;
import org.snmp4j.agent.mo.snmp.smi.Constraint;
import org.snmp4j.agent.mo.snmp.smi.ConstraintsImpl;
import org.snmp4j.agent.mo.snmp.smi.EnumerationConstraint;
import org.snmp4j.agent.mo.snmp.smi.ValueConstraint;
import org.snmp4j.agent.mo.snmp.smi.ValueConstraintValidator;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.Counter64;
import org.snmp4j.smi.Gauge32;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.SMIConstants;
import org.snmp4j.smi.TimeTicks;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

//--AgentGen BEGIN=_IMPORT
//--AgentGen END

public class IfMib 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup 
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
{
  private static IfMib instance = null;
  
  public static IfMib getInstance() {
    if (instance == null) instance = new IfMib();
    return IfMib.instance;
  }

  private static final Logger log = Logger.getLogger(IfMib.class);

//--AgentGen BEGIN=_STATIC
//--AgentGen END

  // Factory
  private static MOFactory moFactory = DefaultMOFactory.getInstance();

  // Constants 
  public static final OID oidIfNumber = 
    new OID(new int[] { 1,3,6,1,2,1,2,1,0 });
  public static final OID oidIfTableLastChange = 
    new OID(new int[] { 1,3,6,1,2,1,31,1,5,0 });
  public static final OID oidIfStackLastChange = 
    new OID(new int[] { 1,3,6,1,2,1,31,1,6,0 });
  public static final OID oidLinkDown =
    new OID(new int[] { 1,3,6,1,6,3,1,1,5,3 });   
  public static final OID oidTrapVarIfIndex =
    new OID(new int[] { 1,3,6,1,2,1,2,2,1,1 });
  public static final OID oidTrapVarIfAdminStatus =
    new OID(new int[] { 1,3,6,1,2,1,2,2,1,7 });
  public static final OID oidTrapVarIfOperStatus =
    new OID(new int[] { 1,3,6,1,2,1,2,2,1,8 });

  public static final OID oidLinkUp =
    new OID(new int[] { 1,3,6,1,6,3,1,1,5,4 });   


  // Enumerations

  public static final class IfAdminStatusEnum {
    /* -- ready to pass packets */
    public static final int up = 1;
    public static final int down = 2;
    /* -- in some test mode */
    public static final int testing = 3;
  }
  public static final class IfLinkUpDownTrapEnableEnum {
    public static final int enabled = 1;
    public static final int disabled = 2;
  }
  public static final class IfPromiscuousModeEnum {
    public static final int _true = 1;
    public static final int _false = 2;
  }
  public static final class IfStackStatusEnum {
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
  public static final class IfTestStatusEnum {
    public static final int notInUse = 1;
    public static final int inUse = 2;
  }
  public static final class IfRcvAddressStatusEnum {
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
  public static final class IfRcvAddressTypeEnum {
    public static final int other = 1;
    public static final int _volatile = 2;
    public static final int nonVolatile = 3;
  }

  // TextualConventions

  // Scalars
  private MOScalar ifNumber;
  private MOScalar ifTableLastChange;
  private MOScalar ifStackLastChange;

  // Tables
  public static final OID oidIfEntry = 
    new OID(new int[] { 1,3,6,1,2,1,2,2,1 });
    
  // Column sub-identifier definitions for ifEntry:
  public static final int colIfIndex = 1;
  public static final int colIfDescr = 2;
  public static final int colIfType = 3;
  public static final int colIfMtu = 4;
  public static final int colIfSpeed = 5;
  public static final int colIfPhysAddress = 6;
  public static final int colIfAdminStatus = 7;
  public static final int colIfOperStatus = 8;
  public static final int colIfLastChange = 9;
  public static final int colIfInOctets = 10;
  public static final int colIfInUcastPkts = 11;
  public static final int colIfInNUcastPkts = 12;
  public static final int colIfInDiscards = 13;
  public static final int colIfInErrors = 14;
  public static final int colIfInUnknownProtos = 15;
  public static final int colIfOutOctets = 16;
  public static final int colIfOutUcastPkts = 17;
  public static final int colIfOutNUcastPkts = 18;
  public static final int colIfOutDiscards = 19;
  public static final int colIfOutErrors = 20;
  public static final int colIfOutQLen = 21;
  public static final int colIfSpecific = 22;

  // Column index definitions for ifEntry:
  public static final int idxIfIndex = 0;
  public static final int idxIfDescr = 1;
  public static final int idxIfType = 2;
  public static final int idxIfMtu = 3;
  public static final int idxIfSpeed = 4;
  public static final int idxIfPhysAddress = 5;
  public static final int idxIfAdminStatus = 6;
  public static final int idxIfOperStatus = 7;
  public static final int idxIfLastChange = 8;
  public static final int idxIfInOctets = 9;
  public static final int idxIfInUcastPkts = 10;
  public static final int idxIfInNUcastPkts = 11;
  public static final int idxIfInDiscards = 12;
  public static final int idxIfInErrors = 13;
  public static final int idxIfInUnknownProtos = 14;
  public static final int idxIfOutOctets = 15;
  public static final int idxIfOutUcastPkts = 16;
  public static final int idxIfOutNUcastPkts = 17;
  public static final int idxIfOutDiscards = 18;
  public static final int idxIfOutErrors = 19;
  public static final int idxIfOutQLen = 20;
  public static final int idxIfSpecific = 21;

  private static final MOTableSubIndex[] ifEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex ifEntryIndex = 
      moFactory.createIndex(ifEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=ifEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             ifEntry;
  private MOMutableTableModel ifEntryModel;
  public static final OID oidIfXEntry = 
    new OID(new int[] { 1,3,6,1,2,1,31,1,1,1 });
    
  // Column sub-identifier definitions for ifXEntry:
  public static final int colIfName = 1;
  public static final int colIfInMulticastPkts = 2;
  public static final int colIfInBroadcastPkts = 3;
  public static final int colIfOutMulticastPkts = 4;
  public static final int colIfOutBroadcastPkts = 5;
  public static final int colIfHCInOctets = 6;
  public static final int colIfHCInUcastPkts = 7;
  public static final int colIfHCInMulticastPkts = 8;
  public static final int colIfHCInBroadcastPkts = 9;
  public static final int colIfHCOutOctets = 10;
  public static final int colIfHCOutUcastPkts = 11;
  public static final int colIfHCOutMulticastPkts = 12;
  public static final int colIfHCOutBroadcastPkts = 13;
  public static final int colIfLinkUpDownTrapEnable = 14;
  public static final int colIfHighSpeed = 15;
  public static final int colIfPromiscuousMode = 16;
  public static final int colIfConnectorPresent = 17;
  public static final int colIfAlias = 18;
  public static final int colIfCounterDiscontinuityTime = 19;

  // Column index definitions for ifXEntry:
  public static final int idxIfName = 0;
  public static final int idxIfInMulticastPkts = 1;
  public static final int idxIfInBroadcastPkts = 2;
  public static final int idxIfOutMulticastPkts = 3;
  public static final int idxIfOutBroadcastPkts = 4;
  public static final int idxIfHCInOctets = 5;
  public static final int idxIfHCInUcastPkts = 6;
  public static final int idxIfHCInMulticastPkts = 7;
  public static final int idxIfHCInBroadcastPkts = 8;
  public static final int idxIfHCOutOctets = 9;
  public static final int idxIfHCOutUcastPkts = 10;
  public static final int idxIfHCOutMulticastPkts = 11;
  public static final int idxIfHCOutBroadcastPkts = 12;
  public static final int idxIfLinkUpDownTrapEnable = 13;
  public static final int idxIfHighSpeed = 14;
  public static final int idxIfPromiscuousMode = 15;
  public static final int idxIfConnectorPresent = 16;
  public static final int idxIfAlias = 17;
  public static final int idxIfCounterDiscontinuityTime = 18;

  private static final MOTableSubIndex[] ifXEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex ifXEntryIndex = 
      moFactory.createIndex(ifXEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=ifXEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             ifXEntry;
  private MOMutableTableModel ifXEntryModel;
  public static final OID oidIfStackEntry = 
    new OID(new int[] { 1,3,6,1,2,1,31,1,2,1 });
    
  // Column sub-identifier definitions for ifStackEntry:
  public static final int colIfStackStatus = 3;

  // Column index definitions for ifStackEntry:
  public static final int idxIfStackStatus = 0;

  private static final MOTableSubIndex[] ifStackEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1),
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex ifStackEntryIndex = 
      moFactory.createIndex(ifStackEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=ifStackEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             ifStackEntry;
  private MOMutableTableModel ifStackEntryModel;
  public static final OID oidIfTestEntry = 
    new OID(new int[] { 1,3,6,1,2,1,31,1,3,1 });
    
  // Column sub-identifier definitions for ifTestEntry:
  public static final int colIfTestId = 1;
  public static final int colIfTestStatus = 2;
  public static final int colIfTestType = 3;
  public static final int colIfTestResult = 4;
  public static final int colIfTestCode = 5;
  public static final int colIfTestOwner = 6;

  // Column index definitions for ifTestEntry:
  public static final int idxIfTestId = 0;
  public static final int idxIfTestStatus = 1;
  public static final int idxIfTestType = 2;
  public static final int idxIfTestResult = 3;
  public static final int idxIfTestCode = 4;
  public static final int idxIfTestOwner = 5;

  private static final MOTableSubIndex[] ifTestEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1)  };

  private static final MOTableIndex ifTestEntryIndex = 
      moFactory.createIndex(ifTestEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=ifTestEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             ifTestEntry;
  private MOMutableTableModel ifTestEntryModel;
  public static final OID oidIfRcvAddressEntry = 
    new OID(new int[] { 1,3,6,1,2,1,31,1,4,1 });
    
  // Column sub-identifier definitions for ifRcvAddressEntry:
  public static final int colIfRcvAddressStatus = 2;
  public static final int colIfRcvAddressType = 3;

  // Column index definitions for ifRcvAddressEntry:
  public static final int idxIfRcvAddressStatus = 0;
  public static final int idxIfRcvAddressType = 1;

  private static final MOTableSubIndex[] ifRcvAddressEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1),
        moFactory.createSubIndex(SMIConstants.SYNTAX_OCTET_STRING, 0, 128)  };

  private static final MOTableIndex ifRcvAddressEntryIndex = 
      moFactory.createIndex(ifRcvAddressEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=ifRcvAddressEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             ifRcvAddressEntry;
  private MOMutableTableModel ifRcvAddressEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  private IfMib() {
    ifNumber = 
      moFactory.createScalar(oidIfNumber,
                             MOAccessImpl.ACCESS_READ_ONLY, new Integer32());
    ifTableLastChange = 
      moFactory.createScalar(oidIfTableLastChange,
                             MOAccessImpl.ACCESS_READ_ONLY, new TimeTicks());
    ifStackLastChange = 
      moFactory.createScalar(oidIfStackLastChange,
                             MOAccessImpl.ACCESS_READ_ONLY, new TimeTicks());
    createIfEntry();
    createIfXEntry();
    createIfStackEntry();
    createIfTestEntry();
    createIfRcvAddressEntry();
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

//--AgentGen BEGIN=_CONSTRUCTORS
//--AgentGen END


  public MOTable getIfEntry() {
    return ifEntry;
  }


  private void createIfEntry() {
    MOColumn[] ifEntryColumns = new MOColumn[22];
    ifEntryColumns[idxIfIndex] = 
      moFactory.createColumn(colIfIndex, 
                             SMIConstants.SYNTAX_INTEGER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfDescr] = 
      moFactory.createColumn(colIfDescr, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfType] = 
      moFactory.createColumn(colIfType, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfMtu] = 
      moFactory.createColumn(colIfMtu, 
                             SMIConstants.SYNTAX_INTEGER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfSpeed] = 
      moFactory.createColumn(colIfSpeed, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfPhysAddress] = 
      moFactory.createColumn(colIfPhysAddress, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfAdminStatus] = 
      new Enumerated(colIfAdminStatus,
                     MOAccessImpl.ACCESS_READ_WRITE,
                     (Integer32)null);
    ValueConstraint ifAdminStatusVC = new EnumerationConstraint(
      new int[] { IfAdminStatusEnum.up,
                  IfAdminStatusEnum.down,
                  IfAdminStatusEnum.testing });
    ((MOMutableColumn)ifEntryColumns[idxIfAdminStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(ifAdminStatusVC));                                  
    ((MOMutableColumn)ifEntryColumns[idxIfAdminStatus]).
      addMOValueValidationListener(new IfAdminStatusValidator());
    ifEntryColumns[idxIfOperStatus] = 
      moFactory.createColumn(colIfOperStatus, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfLastChange] = 
      moFactory.createColumn(colIfLastChange, 
                             SMIConstants.SYNTAX_TIMETICKS,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfInOctets] = 
      moFactory.createColumn(colIfInOctets, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfInUcastPkts] = 
      moFactory.createColumn(colIfInUcastPkts, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfInNUcastPkts] = 
      moFactory.createColumn(colIfInNUcastPkts, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfInDiscards] = 
      moFactory.createColumn(colIfInDiscards, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfInErrors] = 
      moFactory.createColumn(colIfInErrors, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfInUnknownProtos] = 
      moFactory.createColumn(colIfInUnknownProtos, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfOutOctets] = 
      moFactory.createColumn(colIfOutOctets, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfOutUcastPkts] = 
      moFactory.createColumn(colIfOutUcastPkts, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfOutNUcastPkts] = 
      moFactory.createColumn(colIfOutNUcastPkts, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfOutDiscards] = 
      moFactory.createColumn(colIfOutDiscards, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfOutErrors] = 
      moFactory.createColumn(colIfOutErrors, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfOutQLen] = 
      moFactory.createColumn(colIfOutQLen, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifEntryColumns[idxIfSpecific] = 
      moFactory.createColumn(colIfSpecific, 
                             SMIConstants.SYNTAX_OBJECT_IDENTIFIER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    
    ifEntryModel = new DefaultMOMutableTableModel();
//    ifEntryModel.setRowFactory(new IfEntryRowFactory());
    ifEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.IF_TABLE));
//    ifEntry = 
//      moFactory.createTable(oidIfEntry,
//                            ifEntryIndex,
//                            ifEntryColumns,
//                            ifEntryModel);
    ifEntry = 
      new SnmpTable(TableTypeEnum.IF_TABLE,
                    oidIfEntry,
                    ifEntryIndex,
                    ifEntryColumns,
                    ifEntryModel);
  }

  public MOTable getIfXEntry() {
    return ifXEntry;
  }


  private void createIfXEntry() {
    MOColumn[] ifXEntryColumns = new MOColumn[19];
    ifXEntryColumns[idxIfName] = 
      moFactory.createColumn(colIfName, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfInMulticastPkts] = 
      moFactory.createColumn(colIfInMulticastPkts, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfInBroadcastPkts] = 
      moFactory.createColumn(colIfInBroadcastPkts, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfOutMulticastPkts] = 
      moFactory.createColumn(colIfOutMulticastPkts, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfOutBroadcastPkts] = 
      moFactory.createColumn(colIfOutBroadcastPkts, 
                             SMIConstants.SYNTAX_COUNTER32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfHCInOctets] = 
      moFactory.createColumn(colIfHCInOctets, 
                             SMIConstants.SYNTAX_COUNTER64,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfHCInUcastPkts] = 
      moFactory.createColumn(colIfHCInUcastPkts, 
                             SMIConstants.SYNTAX_COUNTER64,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfHCInMulticastPkts] = 
      moFactory.createColumn(colIfHCInMulticastPkts, 
                             SMIConstants.SYNTAX_COUNTER64,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfHCInBroadcastPkts] = 
      moFactory.createColumn(colIfHCInBroadcastPkts, 
                             SMIConstants.SYNTAX_COUNTER64,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfHCOutOctets] = 
      moFactory.createColumn(colIfHCOutOctets, 
                             SMIConstants.SYNTAX_COUNTER64,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfHCOutUcastPkts] = 
      moFactory.createColumn(colIfHCOutUcastPkts, 
                             SMIConstants.SYNTAX_COUNTER64,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfHCOutMulticastPkts] = 
      moFactory.createColumn(colIfHCOutMulticastPkts, 
                             SMIConstants.SYNTAX_COUNTER64,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfHCOutBroadcastPkts] = 
      moFactory.createColumn(colIfHCOutBroadcastPkts, 
                             SMIConstants.SYNTAX_COUNTER64,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfLinkUpDownTrapEnable] = 
      new Enumerated(colIfLinkUpDownTrapEnable,
                     MOAccessImpl.ACCESS_READ_WRITE,
                     (Integer32)null);
    ValueConstraint ifLinkUpDownTrapEnableVC = new EnumerationConstraint(
      new int[] { IfLinkUpDownTrapEnableEnum.enabled,
                  IfLinkUpDownTrapEnableEnum.disabled });
    ((MOMutableColumn)ifXEntryColumns[idxIfLinkUpDownTrapEnable]).
      addMOValueValidationListener(new ValueConstraintValidator(ifLinkUpDownTrapEnableVC));                                  
    ((MOMutableColumn)ifXEntryColumns[idxIfLinkUpDownTrapEnable]).
      addMOValueValidationListener(new IfLinkUpDownTrapEnableValidator());
    ifXEntryColumns[idxIfHighSpeed] = 
      moFactory.createColumn(colIfHighSpeed, 
                             SMIConstants.SYNTAX_GAUGE32,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfPromiscuousMode] = 
      new MOMutableColumn(colIfPromiscuousMode,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint ifPromiscuousModeVC = new EnumerationConstraint(
      new int[] { IfPromiscuousModeEnum._true,
                  IfPromiscuousModeEnum._false });
    ((MOMutableColumn)ifXEntryColumns[idxIfPromiscuousMode]).
      addMOValueValidationListener(new ValueConstraintValidator(ifPromiscuousModeVC));                                  
    ((MOMutableColumn)ifXEntryColumns[idxIfPromiscuousMode]).
      addMOValueValidationListener(new IfPromiscuousModeValidator());
    ifXEntryColumns[idxIfConnectorPresent] = 
      moFactory.createColumn(colIfConnectorPresent, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifXEntryColumns[idxIfAlias] = 
      new DisplayString(colIfAlias,
                        MOAccessImpl.ACCESS_READ_WRITE,
                        null);
    ValueConstraint ifAliasVC = new ConstraintsImpl();
    ((ConstraintsImpl)ifAliasVC).add(new Constraint(0, 64));
    ((MOMutableColumn)ifXEntryColumns[idxIfAlias]).
      addMOValueValidationListener(new ValueConstraintValidator(ifAliasVC));                                  
    ((MOMutableColumn)ifXEntryColumns[idxIfAlias]).
      addMOValueValidationListener(new IfAliasValidator());
    ifXEntryColumns[idxIfCounterDiscontinuityTime] = 
      moFactory.createColumn(colIfCounterDiscontinuityTime, 
                             SMIConstants.SYNTAX_TIMETICKS,
                             MOAccessImpl.ACCESS_READ_ONLY);
    
    ifXEntryModel = new DefaultMOMutableTableModel();
    ifXEntryModel.setRowFactory(new IfXEntryRowFactory());
    ifXEntry = 
      moFactory.createTable(oidIfXEntry,
                            ifXEntryIndex,
                            ifXEntryColumns,
                            ifXEntryModel);
  }

  public MOTable getIfStackEntry() {
    return ifStackEntry;
  }


  private void createIfStackEntry() {
    MOColumn[] ifStackEntryColumns = new MOColumn[1];
    ifStackEntryColumns[idxIfStackStatus] = 
      new RowStatus(colIfStackStatus);
    ValueConstraint ifStackStatusVC = new EnumerationConstraint(
      new int[] { IfStackStatusEnum.active,
                  IfStackStatusEnum.notInService,
                  IfStackStatusEnum.notReady,
                  IfStackStatusEnum.createAndGo,
                  IfStackStatusEnum.createAndWait,
                  IfStackStatusEnum.destroy });
    ((MOMutableColumn)ifStackEntryColumns[idxIfStackStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(ifStackStatusVC));                                  
    ((MOMutableColumn)ifStackEntryColumns[idxIfStackStatus]).
      addMOValueValidationListener(new IfStackStatusValidator());
    
    ifStackEntryModel = new DefaultMOMutableTableModel();
    ifStackEntryModel.setRowFactory(new IfStackEntryRowFactory());
    ifStackEntry = 
      moFactory.createTable(oidIfStackEntry,
                            ifStackEntryIndex,
                            ifStackEntryColumns,
                            ifStackEntryModel);
  }

  public MOTable getIfTestEntry() {
    return ifTestEntry;
  }


  private void createIfTestEntry() {
    MOColumn[] ifTestEntryColumns = new MOColumn[6];
    ifTestEntryColumns[idxIfTestId] = 
      new MOMutableColumn(colIfTestId,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint ifTestIdVC = new ConstraintsImpl();
    ((ConstraintsImpl)ifTestIdVC).add(new Constraint(0, 2147483647));
    ((MOMutableColumn)ifTestEntryColumns[idxIfTestId]).
      addMOValueValidationListener(new ValueConstraintValidator(ifTestIdVC));                                  
    ((MOMutableColumn)ifTestEntryColumns[idxIfTestId]).
      addMOValueValidationListener(new IfTestIdValidator());
    ifTestEntryColumns[idxIfTestStatus] = 
      new Enumerated(colIfTestStatus,
                     MOAccessImpl.ACCESS_READ_WRITE,
                     (Integer32)null);
    ValueConstraint ifTestStatusVC = new EnumerationConstraint(
      new int[] { IfTestStatusEnum.notInUse,
                  IfTestStatusEnum.inUse });
    ((MOMutableColumn)ifTestEntryColumns[idxIfTestStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(ifTestStatusVC));                                  
    ((MOMutableColumn)ifTestEntryColumns[idxIfTestStatus]).
      addMOValueValidationListener(new IfTestStatusValidator());
    ifTestEntryColumns[idxIfTestType] = 
      new MOMutableColumn(colIfTestType,
                          SMIConstants.SYNTAX_OBJECT_IDENTIFIER,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ((MOMutableColumn)ifTestEntryColumns[idxIfTestType]).
      addMOValueValidationListener(new IfTestTypeValidator());
    ifTestEntryColumns[idxIfTestResult] = 
      moFactory.createColumn(colIfTestResult, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifTestEntryColumns[idxIfTestCode] = 
      moFactory.createColumn(colIfTestCode, 
                             SMIConstants.SYNTAX_OBJECT_IDENTIFIER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ifTestEntryColumns[idxIfTestOwner] = 
      new MOMutableColumn(colIfTestOwner,
                          SMIConstants.SYNTAX_OCTET_STRING,
                          MOAccessImpl.ACCESS_READ_WRITE,
                          null);
    ValueConstraint ifTestOwnerVC = new ConstraintsImpl();
    ((ConstraintsImpl)ifTestOwnerVC).add(new Constraint(0, 255));
    ((MOMutableColumn)ifTestEntryColumns[idxIfTestOwner]).
      addMOValueValidationListener(new ValueConstraintValidator(ifTestOwnerVC));                                  
    ((MOMutableColumn)ifTestEntryColumns[idxIfTestOwner]).
      addMOValueValidationListener(new IfTestOwnerValidator());
    
    ifTestEntryModel = new DefaultMOMutableTableModel();
    ifTestEntryModel.setRowFactory(new IfTestEntryRowFactory());
    ifTestEntry = 
      moFactory.createTable(oidIfTestEntry,
                            ifTestEntryIndex,
                            ifTestEntryColumns,
                            ifTestEntryModel);
  }

  public MOTable getIfRcvAddressEntry() {
    return ifRcvAddressEntry;
  }


  private void createIfRcvAddressEntry() {
    MOColumn[] ifRcvAddressEntryColumns = new MOColumn[2];
    ifRcvAddressEntryColumns[idxIfRcvAddressStatus] = 
      new RowStatus(colIfRcvAddressStatus);
    ValueConstraint ifRcvAddressStatusVC = new EnumerationConstraint(
      new int[] { IfRcvAddressStatusEnum.active,
                  IfRcvAddressStatusEnum.notInService,
                  IfRcvAddressStatusEnum.notReady,
                  IfRcvAddressStatusEnum.createAndGo,
                  IfRcvAddressStatusEnum.createAndWait,
                  IfRcvAddressStatusEnum.destroy });
    ((MOMutableColumn)ifRcvAddressEntryColumns[idxIfRcvAddressStatus]).
      addMOValueValidationListener(new ValueConstraintValidator(ifRcvAddressStatusVC));                                  
    ((MOMutableColumn)ifRcvAddressEntryColumns[idxIfRcvAddressStatus]).
      addMOValueValidationListener(new IfRcvAddressStatusValidator());
    ifRcvAddressEntryColumns[idxIfRcvAddressType] = 
      new Enumerated(colIfRcvAddressType,
                     MOAccessImpl.ACCESS_READ_CREATE,
                     new Integer32(2));
    ValueConstraint ifRcvAddressTypeVC = new EnumerationConstraint(
      new int[] { IfRcvAddressTypeEnum.other,
                  IfRcvAddressTypeEnum._volatile,
                  IfRcvAddressTypeEnum.nonVolatile });
    ((MOMutableColumn)ifRcvAddressEntryColumns[idxIfRcvAddressType]).
      addMOValueValidationListener(new ValueConstraintValidator(ifRcvAddressTypeVC));                                  
    ((MOMutableColumn)ifRcvAddressEntryColumns[idxIfRcvAddressType]).
      addMOValueValidationListener(new IfRcvAddressTypeValidator());
    
    ifRcvAddressEntryModel = new DefaultMOMutableTableModel();
    ifRcvAddressEntryModel.setRowFactory(new IfRcvAddressEntryRowFactory());
    ifRcvAddressEntry = 
      moFactory.createTable(oidIfRcvAddressEntry,
                            ifRcvAddressEntryIndex,
                            ifRcvAddressEntryColumns,
                            ifRcvAddressEntryModel);
  }



  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.ifNumber, context);
    server.register(this.ifTableLastChange, context);
    server.register(this.ifStackLastChange, context);
    server.register(this.ifEntry, context);
    server.register(this.ifXEntry, context);
    server.register(this.ifStackEntry, context);
    server.register(this.ifTestEntry, context);
    server.register(this.ifRcvAddressEntry, context);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.ifNumber, context);
    server.unregister(this.ifTableLastChange, context);
    server.unregister(this.ifStackLastChange, context);
    server.unregister(this.ifEntry, context);
    server.unregister(this.ifXEntry, context);
    server.unregister(this.ifStackEntry, context);
    server.unregister(this.ifTestEntry, context);
    server.unregister(this.ifRcvAddressEntry, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  // Notifications
  public void linkDown(NotificationOriginator notificationOriginator,
                         OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 3) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<3");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarIfIndex))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarIfIndex);
    }
    if (!ifEntryIndex.isValidIndex(ifEntry.getIndexPart(vbs[0].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 0 specified: "+
                                         ifEntry.getIndexPart(vbs[0].getOid()));
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarIfAdminStatus))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarIfAdminStatus);
    }
    if (!ifEntryIndex.isValidIndex(ifEntry.getIndexPart(vbs[1].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 1 specified: "+
                                         ifEntry.getIndexPart(vbs[1].getOid()));
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarIfOperStatus))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarIfOperStatus);
    }
    if (!ifEntryIndex.isValidIndex(ifEntry.getIndexPart(vbs[2].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 2 specified: "+
                                         ifEntry.getIndexPart(vbs[2].getOid()));
    }
    notificationOriginator.notify(context, oidLinkDown, vbs);
  }

  public void linkUp(NotificationOriginator notificationOriginator,
                         OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 3) {
      throw new IllegalArgumentException("Too few notification objects: "+
                                         vbs.length+"<3");
    }
    if (!(vbs[0].getOid().startsWith(oidTrapVarIfIndex))) {
      throw new IllegalArgumentException("Variable 0 has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarIfIndex);
    }
    if (!ifEntryIndex.isValidIndex(ifEntry.getIndexPart(vbs[0].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 0 specified: "+
                                         ifEntry.getIndexPart(vbs[0].getOid()));
    }
    if (!(vbs[1].getOid().startsWith(oidTrapVarIfAdminStatus))) {
      throw new IllegalArgumentException("Variable 1 has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarIfAdminStatus);
    }
    if (!ifEntryIndex.isValidIndex(ifEntry.getIndexPart(vbs[1].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 1 specified: "+
                                         ifEntry.getIndexPart(vbs[1].getOid()));
    }
    if (!(vbs[2].getOid().startsWith(oidTrapVarIfOperStatus))) {
      throw new IllegalArgumentException("Variable 2 has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarIfOperStatus);
    }
    if (!ifEntryIndex.isValidIndex(ifEntry.getIndexPart(vbs[2].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 2 specified: "+
                                         ifEntry.getIndexPart(vbs[2].getOid()));
    }
    notificationOriginator.notify(context, oidLinkUp, vbs);
  }


  // Scalars

  // Value Validators

  /**
   * The <code>IfAdminStatusValidator</code> implements the value
   * validation for <code>IfAdminStatus</code>.
   */
  static class IfAdminStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=ifAdminStatus::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IfLinkUpDownTrapEnableValidator</code> implements the value
   * validation for <code>IfLinkUpDownTrapEnable</code>.
   */
  static class IfLinkUpDownTrapEnableValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=ifLinkUpDownTrapEnable::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IfPromiscuousModeValidator</code> implements the value
   * validation for <code>IfPromiscuousMode</code>.
   */
  static class IfPromiscuousModeValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=ifPromiscuousMode::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IfAliasValidator</code> implements the value
   * validation for <code>IfAlias</code>.
   */
  static class IfAliasValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (!(((os.length() >= 0) && (os.length() <= 64)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=ifAlias::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IfStackStatusValidator</code> implements the value
   * validation for <code>IfStackStatus</code>.
   */
  static class IfStackStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=ifStackStatus::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IfTestIdValidator</code> implements the value
   * validation for <code>IfTestId</code>.
   */
  static class IfTestIdValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((Integer32)newValue).getValue();
      if (!(((v >= 0L) && (v <= 2147483647L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=ifTestId::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IfTestStatusValidator</code> implements the value
   * validation for <code>IfTestStatus</code>.
   */
  static class IfTestStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=ifTestStatus::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IfTestTypeValidator</code> implements the value
   * validation for <code>IfTestType</code>.
   */
  static class IfTestTypeValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=ifTestType::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IfTestOwnerValidator</code> implements the value
   * validation for <code>IfTestOwner</code>.
   */
  static class IfTestOwnerValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (!(((os.length() >= 0) && (os.length() <= 255)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=ifTestOwner::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IfRcvAddressStatusValidator</code> implements the value
   * validation for <code>IfRcvAddressStatus</code>.
   */
  static class IfRcvAddressStatusValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=ifRcvAddressStatus::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IfRcvAddressTypeValidator</code> implements the value
   * validation for <code>IfRcvAddressType</code>.
   */
  static class IfRcvAddressTypeValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=ifRcvAddressType::validate
     //--AgentGen END
    }
  }

  // Rows and Factories
  public class IfEntryRow extends DefaultMOMutableRow2PC {
    public IfEntryRow(OID index, Variable[] values) {
      super(index, values);
    }
    
    public Integer32 getIfIndex() {
      return (Integer32) getValue(idxIfIndex);
    }  
    
    public void setIfIndex(Integer32 newValue) {
      setValue(idxIfIndex, newValue);
    }
    
    public OctetString getIfDescr() {
      return (OctetString) getValue(idxIfDescr);
    }  
    
    public void setIfDescr(OctetString newValue) {
      setValue(idxIfDescr, newValue);
    }
    
    public Integer32 getIfType() {
      return (Integer32) getValue(idxIfType);
    }  
    
    public void setIfType(Integer32 newValue) {
      setValue(idxIfType, newValue);
    }
    
    public Integer32 getIfMtu() {
      return (Integer32) getValue(idxIfMtu);
    }  
    
    public void setIfMtu(Integer32 newValue) {
      setValue(idxIfMtu, newValue);
    }
    
    public Gauge32 getIfSpeed() {
      return (Gauge32) getValue(idxIfSpeed);
    }  
    
    public void setIfSpeed(Gauge32 newValue) {
      setValue(idxIfSpeed, newValue);
    }
    
    public OctetString getIfPhysAddress() {
      return (OctetString) getValue(idxIfPhysAddress);
    }  
    
    public void setIfPhysAddress(OctetString newValue) {
      setValue(idxIfPhysAddress, newValue);
    }
    
    public Integer32 getIfAdminStatus() {
      return (Integer32) getValue(idxIfAdminStatus);
    }  
    
    public void setIfAdminStatus(Integer32 newValue) {
      setValue(idxIfAdminStatus, newValue);
    }
    
    public Integer32 getIfOperStatus() {
      return (Integer32) getValue(idxIfOperStatus);
    }  
    
    public void setIfOperStatus(Integer32 newValue) {
      setValue(idxIfOperStatus, newValue);
    }
    
    public TimeTicks getIfLastChange() {
      return (TimeTicks) getValue(idxIfLastChange);
    }  
    
    public void setIfLastChange(TimeTicks newValue) {
      setValue(idxIfLastChange, newValue);
    }
    
    public Counter32 getIfInOctets() {
      return (Counter32) getValue(idxIfInOctets);
    }  
    
    public void setIfInOctets(Counter32 newValue) {
      setValue(idxIfInOctets, newValue);
    }
    
    public Counter32 getIfInUcastPkts() {
      return (Counter32) getValue(idxIfInUcastPkts);
    }  
    
    public void setIfInUcastPkts(Counter32 newValue) {
      setValue(idxIfInUcastPkts, newValue);
    }
    
    public Counter32 getIfInNUcastPkts() {
      return (Counter32) getValue(idxIfInNUcastPkts);
    }  
    
    public void setIfInNUcastPkts(Counter32 newValue) {
      setValue(idxIfInNUcastPkts, newValue);
    }
    
    public Counter32 getIfInDiscards() {
      return (Counter32) getValue(idxIfInDiscards);
    }  
    
    public void setIfInDiscards(Counter32 newValue) {
      setValue(idxIfInDiscards, newValue);
    }
    
    public Counter32 getIfInErrors() {
      return (Counter32) getValue(idxIfInErrors);
    }  
    
    public void setIfInErrors(Counter32 newValue) {
      setValue(idxIfInErrors, newValue);
    }
    
    public Counter32 getIfInUnknownProtos() {
      return (Counter32) getValue(idxIfInUnknownProtos);
    }  
    
    public void setIfInUnknownProtos(Counter32 newValue) {
      setValue(idxIfInUnknownProtos, newValue);
    }
    
    public Counter32 getIfOutOctets() {
      return (Counter32) getValue(idxIfOutOctets);
    }  
    
    public void setIfOutOctets(Counter32 newValue) {
      setValue(idxIfOutOctets, newValue);
    }
    
    public Counter32 getIfOutUcastPkts() {
      return (Counter32) getValue(idxIfOutUcastPkts);
    }  
    
    public void setIfOutUcastPkts(Counter32 newValue) {
      setValue(idxIfOutUcastPkts, newValue);
    }
    
    public Counter32 getIfOutNUcastPkts() {
      return (Counter32) getValue(idxIfOutNUcastPkts);
    }  
    
    public void setIfOutNUcastPkts(Counter32 newValue) {
      setValue(idxIfOutNUcastPkts, newValue);
    }
    
    public Counter32 getIfOutDiscards() {
      return (Counter32) getValue(idxIfOutDiscards);
    }  
    
    public void setIfOutDiscards(Counter32 newValue) {
      setValue(idxIfOutDiscards, newValue);
    }
    
    public Counter32 getIfOutErrors() {
      return (Counter32) getValue(idxIfOutErrors);
    }  
    
    public void setIfOutErrors(Counter32 newValue) {
      setValue(idxIfOutErrors, newValue);
    }
    
    public Gauge32 getIfOutQLen() {
      return (Gauge32) getValue(idxIfOutQLen);
    }  
    
    public void setIfOutQLen(Gauge32 newValue) {
      setValue(idxIfOutQLen, newValue);
    }
    
    public OID getIfSpecific() {
      return (OID) getValue(idxIfSpecific);
    }  
    
    public void setIfSpecific(OID newValue) {
      setValue(idxIfSpecific, newValue);
    }
    

     //--AgentGen BEGIN=ifEntry::Row
     //--AgentGen END
  }
  
  class IfEntryRowFactory 
        extends DefaultMOMutableRow2PCFactory 
  {
    public synchronized MOTableRow createRow(OID index, Variable[] values) 
        throws UnsupportedOperationException 
    {
      IfEntryRow row = new IfEntryRow(index, values);
     //--AgentGen BEGIN=ifEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(MOTableRow row) {
     //--AgentGen BEGIN=ifEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=ifEntry::RowFactory
     //--AgentGen END
  }
  public class IfXEntryRow extends DefaultMOMutableRow2PC {
    public IfXEntryRow(OID index, Variable[] values) {
      super(index, values);
    }
    
    public OctetString getIfName() {
      return (OctetString) getValue(idxIfName);
    }  
    
    public void setIfName(OctetString newValue) {
      setValue(idxIfName, newValue);
    }
    
    public Counter32 getIfInMulticastPkts() {
      return (Counter32) getValue(idxIfInMulticastPkts);
    }  
    
    public void setIfInMulticastPkts(Counter32 newValue) {
      setValue(idxIfInMulticastPkts, newValue);
    }
    
    public Counter32 getIfInBroadcastPkts() {
      return (Counter32) getValue(idxIfInBroadcastPkts);
    }  
    
    public void setIfInBroadcastPkts(Counter32 newValue) {
      setValue(idxIfInBroadcastPkts, newValue);
    }
    
    public Counter32 getIfOutMulticastPkts() {
      return (Counter32) getValue(idxIfOutMulticastPkts);
    }  
    
    public void setIfOutMulticastPkts(Counter32 newValue) {
      setValue(idxIfOutMulticastPkts, newValue);
    }
    
    public Counter32 getIfOutBroadcastPkts() {
      return (Counter32) getValue(idxIfOutBroadcastPkts);
    }  
    
    public void setIfOutBroadcastPkts(Counter32 newValue) {
      setValue(idxIfOutBroadcastPkts, newValue);
    }
    
    public Counter64 getIfHCInOctets() {
      return (Counter64) getValue(idxIfHCInOctets);
    }  
    
    public void setIfHCInOctets(Counter64 newValue) {
      setValue(idxIfHCInOctets, newValue);
    }
    
    public Counter64 getIfHCInUcastPkts() {
      return (Counter64) getValue(idxIfHCInUcastPkts);
    }  
    
    public void setIfHCInUcastPkts(Counter64 newValue) {
      setValue(idxIfHCInUcastPkts, newValue);
    }
    
    public Counter64 getIfHCInMulticastPkts() {
      return (Counter64) getValue(idxIfHCInMulticastPkts);
    }  
    
    public void setIfHCInMulticastPkts(Counter64 newValue) {
      setValue(idxIfHCInMulticastPkts, newValue);
    }
    
    public Counter64 getIfHCInBroadcastPkts() {
      return (Counter64) getValue(idxIfHCInBroadcastPkts);
    }  
    
    public void setIfHCInBroadcastPkts(Counter64 newValue) {
      setValue(idxIfHCInBroadcastPkts, newValue);
    }
    
    public Counter64 getIfHCOutOctets() {
      return (Counter64) getValue(idxIfHCOutOctets);
    }  
    
    public void setIfHCOutOctets(Counter64 newValue) {
      setValue(idxIfHCOutOctets, newValue);
    }
    
    public Counter64 getIfHCOutUcastPkts() {
      return (Counter64) getValue(idxIfHCOutUcastPkts);
    }  
    
    public void setIfHCOutUcastPkts(Counter64 newValue) {
      setValue(idxIfHCOutUcastPkts, newValue);
    }
    
    public Counter64 getIfHCOutMulticastPkts() {
      return (Counter64) getValue(idxIfHCOutMulticastPkts);
    }  
    
    public void setIfHCOutMulticastPkts(Counter64 newValue) {
      setValue(idxIfHCOutMulticastPkts, newValue);
    }
    
    public Counter64 getIfHCOutBroadcastPkts() {
      return (Counter64) getValue(idxIfHCOutBroadcastPkts);
    }  
    
    public void setIfHCOutBroadcastPkts(Counter64 newValue) {
      setValue(idxIfHCOutBroadcastPkts, newValue);
    }
    
    public Integer32 getIfLinkUpDownTrapEnable() {
      return (Integer32) getValue(idxIfLinkUpDownTrapEnable);
    }  
    
    public void setIfLinkUpDownTrapEnable(Integer32 newValue) {
      setValue(idxIfLinkUpDownTrapEnable, newValue);
    }
    
    public Gauge32 getIfHighSpeed() {
      return (Gauge32) getValue(idxIfHighSpeed);
    }  
    
    public void setIfHighSpeed(Gauge32 newValue) {
      setValue(idxIfHighSpeed, newValue);
    }
    
    public Integer32 getIfPromiscuousMode() {
      return (Integer32) getValue(idxIfPromiscuousMode);
    }  
    
    public void setIfPromiscuousMode(Integer32 newValue) {
      setValue(idxIfPromiscuousMode, newValue);
    }
    
    public Integer32 getIfConnectorPresent() {
      return (Integer32) getValue(idxIfConnectorPresent);
    }  
    
    public void setIfConnectorPresent(Integer32 newValue) {
      setValue(idxIfConnectorPresent, newValue);
    }
    
    public OctetString getIfAlias() {
      return (OctetString) getValue(idxIfAlias);
    }  
    
    public void setIfAlias(OctetString newValue) {
      setValue(idxIfAlias, newValue);
    }
    
    public TimeTicks getIfCounterDiscontinuityTime() {
      return (TimeTicks) getValue(idxIfCounterDiscontinuityTime);
    }  
    
    public void setIfCounterDiscontinuityTime(TimeTicks newValue) {
      setValue(idxIfCounterDiscontinuityTime, newValue);
    }
    

     //--AgentGen BEGIN=ifXEntry::Row
     //--AgentGen END
  }
  
  class IfXEntryRowFactory 
        extends DefaultMOMutableRow2PCFactory 
  {
    public synchronized MOTableRow createRow(OID index, Variable[] values) 
        throws UnsupportedOperationException 
    {
      IfXEntryRow row = new IfXEntryRow(index, values);
     //--AgentGen BEGIN=ifXEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(MOTableRow row) {
     //--AgentGen BEGIN=ifXEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=ifXEntry::RowFactory
     //--AgentGen END
  }
  public class IfStackEntryRow extends DefaultMOMutableRow2PC {
    public IfStackEntryRow(OID index, Variable[] values) {
      super(index, values);
    }
    
    public Integer32 getIfStackStatus() {
      return (Integer32) getValue(idxIfStackStatus);
    }  
    
    public void setIfStackStatus(Integer32 newValue) {
      setValue(idxIfStackStatus, newValue);
    }
    

     //--AgentGen BEGIN=ifStackEntry::Row
     //--AgentGen END
  }
  
  class IfStackEntryRowFactory 
        extends DefaultMOMutableRow2PCFactory 
  {
    public synchronized MOTableRow createRow(OID index, Variable[] values) 
        throws UnsupportedOperationException 
    {
      IfStackEntryRow row = new IfStackEntryRow(index, values);
     //--AgentGen BEGIN=ifStackEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(MOTableRow row) {
     //--AgentGen BEGIN=ifStackEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=ifStackEntry::RowFactory
     //--AgentGen END
  }
  public class IfTestEntryRow extends DefaultMOMutableRow2PC {
    public IfTestEntryRow(OID index, Variable[] values) {
      super(index, values);
    }
    
    public Integer32 getIfTestId() {
      return (Integer32) getValue(idxIfTestId);
    }  
    
    public void setIfTestId(Integer32 newValue) {
      setValue(idxIfTestId, newValue);
    }
    
    public Integer32 getIfTestStatus() {
      return (Integer32) getValue(idxIfTestStatus);
    }  
    
    public void setIfTestStatus(Integer32 newValue) {
      setValue(idxIfTestStatus, newValue);
    }
    
    public OID getIfTestType() {
      return (OID) getValue(idxIfTestType);
    }  
    
    public void setIfTestType(OID newValue) {
      setValue(idxIfTestType, newValue);
    }
    
    public Integer32 getIfTestResult() {
      return (Integer32) getValue(idxIfTestResult);
    }  
    
    public void setIfTestResult(Integer32 newValue) {
      setValue(idxIfTestResult, newValue);
    }
    
    public OID getIfTestCode() {
      return (OID) getValue(idxIfTestCode);
    }  
    
    public void setIfTestCode(OID newValue) {
      setValue(idxIfTestCode, newValue);
    }
    
    public OctetString getIfTestOwner() {
      return (OctetString) getValue(idxIfTestOwner);
    }  
    
    public void setIfTestOwner(OctetString newValue) {
      setValue(idxIfTestOwner, newValue);
    }
    

     //--AgentGen BEGIN=ifTestEntry::Row
     //--AgentGen END
  }
  
  class IfTestEntryRowFactory 
        extends DefaultMOMutableRow2PCFactory 
  {
    public synchronized MOTableRow createRow(OID index, Variable[] values) 
        throws UnsupportedOperationException 
    {
      IfTestEntryRow row = new IfTestEntryRow(index, values);
     //--AgentGen BEGIN=ifTestEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(MOTableRow row) {
     //--AgentGen BEGIN=ifTestEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=ifTestEntry::RowFactory
     //--AgentGen END
  }
  public class IfRcvAddressEntryRow extends DefaultMOMutableRow2PC {
    public IfRcvAddressEntryRow(OID index, Variable[] values) {
      super(index, values);
    }
    
    public Integer32 getIfRcvAddressStatus() {
      return (Integer32) getValue(idxIfRcvAddressStatus);
    }  
    
    public void setIfRcvAddressStatus(Integer32 newValue) {
      setValue(idxIfRcvAddressStatus, newValue);
    }
    
    public Integer32 getIfRcvAddressType() {
      return (Integer32) getValue(idxIfRcvAddressType);
    }  
    
    public void setIfRcvAddressType(Integer32 newValue) {
      setValue(idxIfRcvAddressType, newValue);
    }
    

     //--AgentGen BEGIN=ifRcvAddressEntry::Row
     //--AgentGen END
  }
  
  class IfRcvAddressEntryRowFactory 
        extends DefaultMOMutableRow2PCFactory 
  {
    public synchronized MOTableRow createRow(OID index, Variable[] values) 
        throws UnsupportedOperationException 
    {
      IfRcvAddressEntryRow row = new IfRcvAddressEntryRow(index, values);
     //--AgentGen BEGIN=ifRcvAddressEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(MOTableRow row) {
     //--AgentGen BEGIN=ifRcvAddressEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=ifRcvAddressEntry::RowFactory
     //--AgentGen END
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

//--AgentGen BEGIN=_CLASSES
//--AgentGen END

//--AgentGen BEGIN=_END
//--AgentGen END
}


