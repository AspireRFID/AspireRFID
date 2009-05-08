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
import org.snmp4j.agent.mo.snmp.Enumerated;
import org.snmp4j.agent.mo.snmp.EnumeratedScalar;
import org.snmp4j.agent.mo.snmp.smi.Constraint;
import org.snmp4j.agent.mo.snmp.smi.ConstraintsImpl;
import org.snmp4j.agent.mo.snmp.smi.EnumerationConstraint;
import org.snmp4j.agent.mo.snmp.smi.ValueConstraint;
import org.snmp4j.agent.mo.snmp.smi.ValueConstraintValidator;
import org.snmp4j.agent.request.SubRequest;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.IpAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.SMIConstants;
import org.snmp4j.smi.Variable;

//--AgentGen BEGIN=_IMPORT
//--AgentGen END

public class IpMib 
//--AgentGen BEGIN=_EXTENDS
//--AgentGen END
implements MOGroup 
//--AgentGen BEGIN=_IMPLEMENTS
//--AgentGen END
{
  private static IpMib instance = null;
  
  public static IpMib getInstance() {
    if (instance == null) instance = new IpMib();
    return IpMib.instance;
  }

  private static final Logger log = Logger.getLogger(IpMib.class);

//--AgentGen BEGIN=_STATIC
//--AgentGen END

  // Factory
  private static MOFactory moFactory = DefaultMOFactory.getInstance();

  // Constants 
  public static final OID oidIpForwarding = 
    new OID(new int[] { 1,3,6,1,2,1,4,1,0 });
  public static final OID oidIpDefaultTTL = 
    new OID(new int[] { 1,3,6,1,2,1,4,2,0 });
  public static final OID oidIpInReceives = 
    new OID(new int[] { 1,3,6,1,2,1,4,3,0 });
  public static final OID oidIpInHdrErrors = 
    new OID(new int[] { 1,3,6,1,2,1,4,4,0 });
  public static final OID oidIpInAddrErrors = 
    new OID(new int[] { 1,3,6,1,2,1,4,5,0 });
  public static final OID oidIpForwDatagrams = 
    new OID(new int[] { 1,3,6,1,2,1,4,6,0 });
  public static final OID oidIpInUnknownProtos = 
    new OID(new int[] { 1,3,6,1,2,1,4,7,0 });
  public static final OID oidIpInDiscards = 
    new OID(new int[] { 1,3,6,1,2,1,4,8,0 });
  public static final OID oidIpInDelivers = 
    new OID(new int[] { 1,3,6,1,2,1,4,9,0 });
  public static final OID oidIpOutRequests = 
    new OID(new int[] { 1,3,6,1,2,1,4,10,0 });
  public static final OID oidIpOutDiscards = 
    new OID(new int[] { 1,3,6,1,2,1,4,11,0 });
  public static final OID oidIpOutNoRoutes = 
    new OID(new int[] { 1,3,6,1,2,1,4,12,0 });
  public static final OID oidIpReasmTimeout = 
    new OID(new int[] { 1,3,6,1,2,1,4,13,0 });
  public static final OID oidIpReasmReqds = 
    new OID(new int[] { 1,3,6,1,2,1,4,14,0 });
  public static final OID oidIpReasmOKs = 
    new OID(new int[] { 1,3,6,1,2,1,4,15,0 });
  public static final OID oidIpReasmFails = 
    new OID(new int[] { 1,3,6,1,2,1,4,16,0 });
  public static final OID oidIpFragOKs = 
    new OID(new int[] { 1,3,6,1,2,1,4,17,0 });
  public static final OID oidIpFragFails = 
    new OID(new int[] { 1,3,6,1,2,1,4,18,0 });
  public static final OID oidIpFragCreates = 
    new OID(new int[] { 1,3,6,1,2,1,4,19,0 });
  public static final OID oidIpRoutingDiscards = 
    new OID(new int[] { 1,3,6,1,2,1,4,23,0 });
  public static final OID oidIcmpInMsgs = 
    new OID(new int[] { 1,3,6,1,2,1,5,1,0 });
  public static final OID oidIcmpInErrors = 
    new OID(new int[] { 1,3,6,1,2,1,5,2,0 });
  public static final OID oidIcmpInDestUnreachs = 
    new OID(new int[] { 1,3,6,1,2,1,5,3,0 });
  public static final OID oidIcmpInTimeExcds = 
    new OID(new int[] { 1,3,6,1,2,1,5,4,0 });
  public static final OID oidIcmpInParmProbs = 
    new OID(new int[] { 1,3,6,1,2,1,5,5,0 });
  public static final OID oidIcmpInSrcQuenchs = 
    new OID(new int[] { 1,3,6,1,2,1,5,6,0 });
  public static final OID oidIcmpInRedirects = 
    new OID(new int[] { 1,3,6,1,2,1,5,7,0 });
  public static final OID oidIcmpInEchos = 
    new OID(new int[] { 1,3,6,1,2,1,5,8,0 });
  public static final OID oidIcmpInEchoReps = 
    new OID(new int[] { 1,3,6,1,2,1,5,9,0 });
  public static final OID oidIcmpInTimestamps = 
    new OID(new int[] { 1,3,6,1,2,1,5,10,0 });
  public static final OID oidIcmpInTimestampReps = 
    new OID(new int[] { 1,3,6,1,2,1,5,11,0 });
  public static final OID oidIcmpInAddrMasks = 
    new OID(new int[] { 1,3,6,1,2,1,5,12,0 });
  public static final OID oidIcmpInAddrMaskReps = 
    new OID(new int[] { 1,3,6,1,2,1,5,13,0 });
  public static final OID oidIcmpOutMsgs = 
    new OID(new int[] { 1,3,6,1,2,1,5,14,0 });
  public static final OID oidIcmpOutErrors = 
    new OID(new int[] { 1,3,6,1,2,1,5,15,0 });
  public static final OID oidIcmpOutDestUnreachs = 
    new OID(new int[] { 1,3,6,1,2,1,5,16,0 });
  public static final OID oidIcmpOutTimeExcds = 
    new OID(new int[] { 1,3,6,1,2,1,5,17,0 });
  public static final OID oidIcmpOutParmProbs = 
    new OID(new int[] { 1,3,6,1,2,1,5,18,0 });
  public static final OID oidIcmpOutSrcQuenchs = 
    new OID(new int[] { 1,3,6,1,2,1,5,19,0 });
  public static final OID oidIcmpOutRedirects = 
    new OID(new int[] { 1,3,6,1,2,1,5,20,0 });
  public static final OID oidIcmpOutEchos = 
    new OID(new int[] { 1,3,6,1,2,1,5,21,0 });
  public static final OID oidIcmpOutEchoReps = 
    new OID(new int[] { 1,3,6,1,2,1,5,22,0 });
  public static final OID oidIcmpOutTimestamps = 
    new OID(new int[] { 1,3,6,1,2,1,5,23,0 });
  public static final OID oidIcmpOutTimestampReps = 
    new OID(new int[] { 1,3,6,1,2,1,5,24,0 });
  public static final OID oidIcmpOutAddrMasks = 
    new OID(new int[] { 1,3,6,1,2,1,5,25,0 });
  public static final OID oidIcmpOutAddrMaskReps = 
    new OID(new int[] { 1,3,6,1,2,1,5,26,0 });

  // Enumerations
  public static final class IpForwardingEnum {
    /* -- acting as a router */
    public static final int forwarding = 1;
    /* -- NOT acting as a router */
    public static final int notForwarding = 2;
  }

  public static final class IpNetToMediaTypeEnum {
    /* -- none of the following */
    public static final int other = 1;
    /* -- an invalidated mapping */
    public static final int invalid = 2;
    public static final int dynamic = 3;
    public static final int static_ = 4;
  }

  // TextualConventions

  // Scalars
  private MOScalar ipForwarding;
  private MOScalar ipDefaultTTL;
  private MOScalar ipInReceives;
  private MOScalar ipInHdrErrors;
  private MOScalar ipInAddrErrors;
  private MOScalar ipForwDatagrams;
  private MOScalar ipInUnknownProtos;
  private MOScalar ipInDiscards;
  private MOScalar ipInDelivers;
  private MOScalar ipOutRequests;
  private MOScalar ipOutDiscards;
  private MOScalar ipOutNoRoutes;
  private MOScalar ipReasmTimeout;
  private MOScalar ipReasmReqds;
  private MOScalar ipReasmOKs;
  private MOScalar ipReasmFails;
  private MOScalar ipFragOKs;
  private MOScalar ipFragFails;
  private MOScalar ipFragCreates;
  private MOScalar ipRoutingDiscards;
  private MOScalar icmpInMsgs;
  private MOScalar icmpInErrors;
  private MOScalar icmpInDestUnreachs;
  private MOScalar icmpInTimeExcds;
  private MOScalar icmpInParmProbs;
  private MOScalar icmpInSrcQuenchs;
  private MOScalar icmpInRedirects;
  private MOScalar icmpInEchos;
  private MOScalar icmpInEchoReps;
  private MOScalar icmpInTimestamps;
  private MOScalar icmpInTimestampReps;
  private MOScalar icmpInAddrMasks;
  private MOScalar icmpInAddrMaskReps;
  private MOScalar icmpOutMsgs;
  private MOScalar icmpOutErrors;
  private MOScalar icmpOutDestUnreachs;
  private MOScalar icmpOutTimeExcds;
  private MOScalar icmpOutParmProbs;
  private MOScalar icmpOutSrcQuenchs;
  private MOScalar icmpOutRedirects;
  private MOScalar icmpOutEchos;
  private MOScalar icmpOutEchoReps;
  private MOScalar icmpOutTimestamps;
  private MOScalar icmpOutTimestampReps;
  private MOScalar icmpOutAddrMasks;
  private MOScalar icmpOutAddrMaskReps;

  // Tables
  public static final OID oidIpAddrEntry = 
    new OID(new int[] { 1,3,6,1,2,1,4,20,1 });
    
  // Column sub-identifier definitions for ipAddrEntry:
  public static final int colIpAdEntAddr = 1;
  public static final int colIpAdEntIfIndex = 2;
  public static final int colIpAdEntNetMask = 3;
  public static final int colIpAdEntBcastAddr = 4;
  public static final int colIpAdEntReasmMaxSize = 5;

  // Column index definitions for ipAddrEntry:
  public static final int idxIpAdEntAddr = 0;
  public static final int idxIpAdEntIfIndex = 1;
  public static final int idxIpAdEntNetMask = 2;
  public static final int idxIpAdEntBcastAddr = 3;
  public static final int idxIpAdEntReasmMaxSize = 4;

  private static final MOTableSubIndex[] ipAddrEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_IPADDRESS, 4, 4)  };

  private static final MOTableIndex ipAddrEntryIndex = 
      moFactory.createIndex(ipAddrEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=ipAddrEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             ipAddrEntry;
  private MOMutableTableModel ipAddrEntryModel;
  public static final OID oidIpNetToMediaEntry = 
    new OID(new int[] { 1,3,6,1,2,1,4,22,1 });
    
  // Column sub-identifier definitions for ipNetToMediaEntry:
  public static final int colIpNetToMediaIfIndex = 1;
  public static final int colIpNetToMediaPhysAddress = 2;
  public static final int colIpNetToMediaNetAddress = 3;
  public static final int colIpNetToMediaType = 4;

  // Column index definitions for ipNetToMediaEntry:
  public static final int idxIpNetToMediaIfIndex = 0;
  public static final int idxIpNetToMediaPhysAddress = 1;
  public static final int idxIpNetToMediaNetAddress = 2;
  public static final int idxIpNetToMediaType = 3;

  private static final MOTableSubIndex[] ipNetToMediaEntryIndexes = 
    new MOTableSubIndex[] {
        moFactory.createSubIndex(SMIConstants.SYNTAX_INTEGER, 1, 1),
        moFactory.createSubIndex(SMIConstants.SYNTAX_IPADDRESS, 4, 4)  };

  private static final MOTableIndex ipNetToMediaEntryIndex = 
      moFactory.createIndex(ipNetToMediaEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
    public boolean isValidIndex(OID index) {
      boolean isValidIndex = true;
     //--AgentGen BEGIN=ipNetToMediaEntry::isValidIndex
     //--AgentGen END
      return isValidIndex;
    }
  });

  
  private MOTable             ipNetToMediaEntry;
  private MOMutableTableModel ipNetToMediaEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  private IpMib() {
    ipForwarding = 
      new IpForwarding(oidIpForwarding, MOAccessImpl.ACCESS_READ_WRITE);
    ipForwarding.addMOValueValidationListener(new IpForwardingValidator());
    ipDefaultTTL = 
      new IpDefaultTTL(oidIpDefaultTTL, MOAccessImpl.ACCESS_READ_WRITE);
    ipDefaultTTL.addMOValueValidationListener(new IpDefaultTTLValidator());
    ipInReceives = 
      moFactory.createScalar(oidIpInReceives,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipInHdrErrors = 
      moFactory.createScalar(oidIpInHdrErrors,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipInAddrErrors = 
      moFactory.createScalar(oidIpInAddrErrors,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipForwDatagrams = 
      moFactory.createScalar(oidIpForwDatagrams,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipInUnknownProtos = 
      moFactory.createScalar(oidIpInUnknownProtos,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipInDiscards = 
      moFactory.createScalar(oidIpInDiscards,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipInDelivers = 
      moFactory.createScalar(oidIpInDelivers,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipOutRequests = 
      moFactory.createScalar(oidIpOutRequests,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipOutDiscards = 
      moFactory.createScalar(oidIpOutDiscards,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipOutNoRoutes = 
      moFactory.createScalar(oidIpOutNoRoutes,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipReasmTimeout = 
      moFactory.createScalar(oidIpReasmTimeout,
                             MOAccessImpl.ACCESS_READ_ONLY, new Integer32());
    ipReasmReqds = 
      moFactory.createScalar(oidIpReasmReqds,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipReasmOKs = 
      moFactory.createScalar(oidIpReasmOKs,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipReasmFails = 
      moFactory.createScalar(oidIpReasmFails,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipFragOKs = 
      moFactory.createScalar(oidIpFragOKs,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipFragFails = 
      moFactory.createScalar(oidIpFragFails,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipFragCreates = 
      moFactory.createScalar(oidIpFragCreates,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    ipRoutingDiscards = 
      moFactory.createScalar(oidIpRoutingDiscards,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInMsgs = 
      moFactory.createScalar(oidIcmpInMsgs,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInErrors = 
      moFactory.createScalar(oidIcmpInErrors,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInDestUnreachs = 
      moFactory.createScalar(oidIcmpInDestUnreachs,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInTimeExcds = 
      moFactory.createScalar(oidIcmpInTimeExcds,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInParmProbs = 
      moFactory.createScalar(oidIcmpInParmProbs,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInSrcQuenchs = 
      moFactory.createScalar(oidIcmpInSrcQuenchs,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInRedirects = 
      moFactory.createScalar(oidIcmpInRedirects,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInEchos = 
      moFactory.createScalar(oidIcmpInEchos,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInEchoReps = 
      moFactory.createScalar(oidIcmpInEchoReps,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInTimestamps = 
      moFactory.createScalar(oidIcmpInTimestamps,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInTimestampReps = 
      moFactory.createScalar(oidIcmpInTimestampReps,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInAddrMasks = 
      moFactory.createScalar(oidIcmpInAddrMasks,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpInAddrMaskReps = 
      moFactory.createScalar(oidIcmpInAddrMaskReps,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutMsgs = 
      moFactory.createScalar(oidIcmpOutMsgs,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutErrors = 
      moFactory.createScalar(oidIcmpOutErrors,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutDestUnreachs = 
      moFactory.createScalar(oidIcmpOutDestUnreachs,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutTimeExcds = 
      moFactory.createScalar(oidIcmpOutTimeExcds,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutParmProbs = 
      moFactory.createScalar(oidIcmpOutParmProbs,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutSrcQuenchs = 
      moFactory.createScalar(oidIcmpOutSrcQuenchs,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutRedirects = 
      moFactory.createScalar(oidIcmpOutRedirects,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutEchos = 
      moFactory.createScalar(oidIcmpOutEchos,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutEchoReps = 
      moFactory.createScalar(oidIcmpOutEchoReps,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutTimestamps = 
      moFactory.createScalar(oidIcmpOutTimestamps,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutTimestampReps = 
      moFactory.createScalar(oidIcmpOutTimestampReps,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutAddrMasks = 
      moFactory.createScalar(oidIcmpOutAddrMasks,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    icmpOutAddrMaskReps = 
      moFactory.createScalar(oidIcmpOutAddrMaskReps,
                             MOAccessImpl.ACCESS_READ_ONLY, new Counter32());
    createIpAddrEntry();
    createIpNetToMediaEntry();
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

//--AgentGen BEGIN=_CONSTRUCTORS
//--AgentGen END


  public MOTable getIpAddrEntry() {
    return ipAddrEntry;
  }


  private void createIpAddrEntry() {
    MOColumn[] ipAddrEntryColumns = new MOColumn[5];
    ipAddrEntryColumns[idxIpAdEntAddr] = 
      moFactory.createColumn(colIpAdEntAddr, 
                             SMIConstants.SYNTAX_IPADDRESS,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ipAddrEntryColumns[idxIpAdEntIfIndex] = 
      moFactory.createColumn(colIpAdEntIfIndex, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ipAddrEntryColumns[idxIpAdEntNetMask] = 
      moFactory.createColumn(colIpAdEntNetMask, 
                             SMIConstants.SYNTAX_IPADDRESS,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ipAddrEntryColumns[idxIpAdEntBcastAddr] = 
      moFactory.createColumn(colIpAdEntBcastAddr, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    ipAddrEntryColumns[idxIpAdEntReasmMaxSize] = 
      moFactory.createColumn(colIpAdEntReasmMaxSize, 
                             SMIConstants.SYNTAX_INTEGER,
                             MOAccessImpl.ACCESS_READ_ONLY);
    
    ipAddrEntryModel = new DefaultMOMutableTableModel();
//    ipAddrEntryModel.setRowFactory(new IpAddrEntryRowFactory());
    ipAddrEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.IP_ADDR_TABLE));
//    ipAddrEntry = 
//      moFactory.createTable(oidIpAddrEntry,
//                            ipAddrEntryIndex,
//                            ipAddrEntryColumns,
//                            ipAddrEntryModel);
    ipAddrEntry = 
      new SnmpTable(TableTypeEnum.IP_ADDR_TABLE,
                    oidIpAddrEntry,
                    ipAddrEntryIndex,
                    ipAddrEntryColumns,
                    ipAddrEntryModel);
  }

  public MOTable getIpNetToMediaEntry() {
    return ipNetToMediaEntry;
  }


  private void createIpNetToMediaEntry() {
    MOColumn[] ipNetToMediaEntryColumns = new MOColumn[4];
    ipNetToMediaEntryColumns[idxIpNetToMediaIfIndex] = 
      new MOMutableColumn(colIpNetToMediaIfIndex,
                          SMIConstants.SYNTAX_INTEGER,
                          MOAccessImpl.ACCESS_READ_CREATE,
                          null);
    ValueConstraint ipNetToMediaIfIndexVC = new ConstraintsImpl();
    ((ConstraintsImpl)ipNetToMediaIfIndexVC).add(new Constraint(1, 2147483647));
    ((MOMutableColumn)ipNetToMediaEntryColumns[idxIpNetToMediaIfIndex]).
      addMOValueValidationListener(new ValueConstraintValidator(ipNetToMediaIfIndexVC));                                  
    ((MOMutableColumn)ipNetToMediaEntryColumns[idxIpNetToMediaIfIndex]).
      addMOValueValidationListener(new IpNetToMediaIfIndexValidator());
    ipNetToMediaEntryColumns[idxIpNetToMediaPhysAddress] = 
      new MOMutableColumn(colIpNetToMediaPhysAddress,
                          SMIConstants.SYNTAX_OCTET_STRING,
                          MOAccessImpl.ACCESS_READ_CREATE,
                          null);
    ((MOMutableColumn)ipNetToMediaEntryColumns[idxIpNetToMediaPhysAddress]).
      addMOValueValidationListener(new IpNetToMediaPhysAddressValidator());
    ipNetToMediaEntryColumns[idxIpNetToMediaNetAddress] = 
      new MOMutableColumn(colIpNetToMediaNetAddress,
                          SMIConstants.SYNTAX_IPADDRESS,
                          MOAccessImpl.ACCESS_READ_CREATE,
                          null);
    ((MOMutableColumn)ipNetToMediaEntryColumns[idxIpNetToMediaNetAddress]).
      addMOValueValidationListener(new IpNetToMediaNetAddressValidator());
    ipNetToMediaEntryColumns[idxIpNetToMediaType] = 
      new Enumerated(colIpNetToMediaType,
                     MOAccessImpl.ACCESS_READ_CREATE,
                     (Integer32)null);
    ValueConstraint ipNetToMediaTypeVC = new EnumerationConstraint(
      new int[] { IpNetToMediaTypeEnum.other,
                  IpNetToMediaTypeEnum.invalid,
                  IpNetToMediaTypeEnum.dynamic,
                  IpNetToMediaTypeEnum.static_ });
    ((MOMutableColumn)ipNetToMediaEntryColumns[idxIpNetToMediaType]).
      addMOValueValidationListener(new ValueConstraintValidator(ipNetToMediaTypeVC));                                  
    ((MOMutableColumn)ipNetToMediaEntryColumns[idxIpNetToMediaType]).
      addMOValueValidationListener(new IpNetToMediaTypeValidator());
    
    ipNetToMediaEntryModel = new DefaultMOMutableTableModel();
//    ipNetToMediaEntryModel.setRowFactory(new IpNetToMediaEntryRowFactory());
    ipNetToMediaEntryModel.setRowFactory(new SnmpTableRowFactory(TableTypeEnum.IP_NET_TO_MEDIA_TABLE));
//    ipNetToMediaEntry = 
//      moFactory.createTable(oidIpNetToMediaEntry,
//                            ipNetToMediaEntryIndex,
//                            ipNetToMediaEntryColumns,
//                            ipNetToMediaEntryModel);
    ipNetToMediaEntry = 
      new SnmpTable(TableTypeEnum.IP_NET_TO_MEDIA_TABLE,
                    oidIpNetToMediaEntry,
                    ipNetToMediaEntryIndex,
                    ipNetToMediaEntryColumns,
                    ipNetToMediaEntryModel);
  }



  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.ipForwarding, context);
    server.register(this.ipDefaultTTL, context);
    server.register(this.ipInReceives, context);
    server.register(this.ipInHdrErrors, context);
    server.register(this.ipInAddrErrors, context);
    server.register(this.ipForwDatagrams, context);
    server.register(this.ipInUnknownProtos, context);
    server.register(this.ipInDiscards, context);
    server.register(this.ipInDelivers, context);
    server.register(this.ipOutRequests, context);
    server.register(this.ipOutDiscards, context);
    server.register(this.ipOutNoRoutes, context);
    server.register(this.ipReasmTimeout, context);
    server.register(this.ipReasmReqds, context);
    server.register(this.ipReasmOKs, context);
    server.register(this.ipReasmFails, context);
    server.register(this.ipFragOKs, context);
    server.register(this.ipFragFails, context);
    server.register(this.ipFragCreates, context);
    server.register(this.ipRoutingDiscards, context);
    server.register(this.icmpInMsgs, context);
    server.register(this.icmpInErrors, context);
    server.register(this.icmpInDestUnreachs, context);
    server.register(this.icmpInTimeExcds, context);
    server.register(this.icmpInParmProbs, context);
    server.register(this.icmpInSrcQuenchs, context);
    server.register(this.icmpInRedirects, context);
    server.register(this.icmpInEchos, context);
    server.register(this.icmpInEchoReps, context);
    server.register(this.icmpInTimestamps, context);
    server.register(this.icmpInTimestampReps, context);
    server.register(this.icmpInAddrMasks, context);
    server.register(this.icmpInAddrMaskReps, context);
    server.register(this.icmpOutMsgs, context);
    server.register(this.icmpOutErrors, context);
    server.register(this.icmpOutDestUnreachs, context);
    server.register(this.icmpOutTimeExcds, context);
    server.register(this.icmpOutParmProbs, context);
    server.register(this.icmpOutSrcQuenchs, context);
    server.register(this.icmpOutRedirects, context);
    server.register(this.icmpOutEchos, context);
    server.register(this.icmpOutEchoReps, context);
    server.register(this.icmpOutTimestamps, context);
    server.register(this.icmpOutTimestampReps, context);
    server.register(this.icmpOutAddrMasks, context);
    server.register(this.icmpOutAddrMaskReps, context);
    server.register(this.ipAddrEntry, context);
    server.register(this.ipNetToMediaEntry, context);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.ipForwarding, context);
    server.unregister(this.ipDefaultTTL, context);
    server.unregister(this.ipInReceives, context);
    server.unregister(this.ipInHdrErrors, context);
    server.unregister(this.ipInAddrErrors, context);
    server.unregister(this.ipForwDatagrams, context);
    server.unregister(this.ipInUnknownProtos, context);
    server.unregister(this.ipInDiscards, context);
    server.unregister(this.ipInDelivers, context);
    server.unregister(this.ipOutRequests, context);
    server.unregister(this.ipOutDiscards, context);
    server.unregister(this.ipOutNoRoutes, context);
    server.unregister(this.ipReasmTimeout, context);
    server.unregister(this.ipReasmReqds, context);
    server.unregister(this.ipReasmOKs, context);
    server.unregister(this.ipReasmFails, context);
    server.unregister(this.ipFragOKs, context);
    server.unregister(this.ipFragFails, context);
    server.unregister(this.ipFragCreates, context);
    server.unregister(this.ipRoutingDiscards, context);
    server.unregister(this.icmpInMsgs, context);
    server.unregister(this.icmpInErrors, context);
    server.unregister(this.icmpInDestUnreachs, context);
    server.unregister(this.icmpInTimeExcds, context);
    server.unregister(this.icmpInParmProbs, context);
    server.unregister(this.icmpInSrcQuenchs, context);
    server.unregister(this.icmpInRedirects, context);
    server.unregister(this.icmpInEchos, context);
    server.unregister(this.icmpInEchoReps, context);
    server.unregister(this.icmpInTimestamps, context);
    server.unregister(this.icmpInTimestampReps, context);
    server.unregister(this.icmpInAddrMasks, context);
    server.unregister(this.icmpInAddrMaskReps, context);
    server.unregister(this.icmpOutMsgs, context);
    server.unregister(this.icmpOutErrors, context);
    server.unregister(this.icmpOutDestUnreachs, context);
    server.unregister(this.icmpOutTimeExcds, context);
    server.unregister(this.icmpOutParmProbs, context);
    server.unregister(this.icmpOutSrcQuenchs, context);
    server.unregister(this.icmpOutRedirects, context);
    server.unregister(this.icmpOutEchos, context);
    server.unregister(this.icmpOutEchoReps, context);
    server.unregister(this.icmpOutTimestamps, context);
    server.unregister(this.icmpOutTimestampReps, context);
    server.unregister(this.icmpOutAddrMasks, context);
    server.unregister(this.icmpOutAddrMaskReps, context);
    server.unregister(this.ipAddrEntry, context);
    server.unregister(this.ipNetToMediaEntry, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  // Notifications

  // Scalars
  public class IpForwarding extends EnumeratedScalar {
    IpForwarding(OID oid, MOAccess access) {
      super(oid, access, new Integer32(),
            new int[] { IpForwardingEnum.forwarding,
                        IpForwardingEnum.notForwarding });
//--AgentGen BEGIN=ipForwarding
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
     //--AgentGen BEGIN=ipForwarding::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=ipForwarding::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=ipForwarding::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=ipForwarding::_METHODS
     //--AgentGen END

  }

  public class IpDefaultTTL extends MOScalar {
    IpDefaultTTL(OID oid, MOAccess access) {
      super(oid, access, new Integer32());
//--AgentGen BEGIN=ipDefaultTTL
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
      long v = ((Integer32)newValue).getValue();
      if (!(((v >= 1L) && (v <= 255L)))) {
        valueOK = SnmpConstants.SNMP_ERROR_WRONG_VALUE;
      }    
     //--AgentGen BEGIN=ipDefaultTTL::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public Variable getValue() {
     //--AgentGen BEGIN=ipDefaultTTL::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(Variable newValue) {
     //--AgentGen BEGIN=ipDefaultTTL::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=ipDefaultTTL::_METHODS
     //--AgentGen END

  }


  // Value Validators
  /**
   * The <code>IpForwardingValidator</code> implements the value
   * validation for <code>IpForwarding</code>.
   */
  static class IpForwardingValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=ipForwarding::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IpDefaultTTLValidator</code> implements the value
   * validation for <code>IpDefaultTTL</code>.
   */
  static class IpDefaultTTLValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((Integer32)newValue).getValue();
      if (!(((v >= 1L) && (v <= 255L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=ipDefaultTTL::validate
     //--AgentGen END
    }
  }

  /**
   * The <code>IpNetToMediaIfIndexValidator</code> implements the value
   * validation for <code>IpNetToMediaIfIndex</code>.
   */
  static class IpNetToMediaIfIndexValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      long v = ((Integer32)newValue).getValue();
      if (!(((v >= 1L) && (v <= 2147483647L)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_VALUE);
        return;
      }
     //--AgentGen BEGIN=ipNetToMediaIfIndex::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IpNetToMediaPhysAddressValidator</code> implements the value
   * validation for <code>IpNetToMediaPhysAddress</code>.
   */
  static class IpNetToMediaPhysAddressValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=ipNetToMediaPhysAddress::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IpNetToMediaNetAddressValidator</code> implements the value
   * validation for <code>IpNetToMediaNetAddress</code>.
   */
  static class IpNetToMediaNetAddressValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=ipNetToMediaNetAddress::validate
     //--AgentGen END
    }
  }
  /**
   * The <code>IpNetToMediaTypeValidator</code> implements the value
   * validation for <code>IpNetToMediaType</code>.
   */
  static class IpNetToMediaTypeValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=ipNetToMediaType::validate
     //--AgentGen END
    }
  }

  // Rows and Factories
  public class IpAddrEntryRow extends DefaultMOMutableRow2PC {
    public IpAddrEntryRow(OID index, Variable[] values) {
      super(index, values);
    }
    
    public IpAddress getIpAdEntAddr() {
      return (IpAddress) getValue(idxIpAdEntAddr);
    }  
    
    public void setIpAdEntAddr(IpAddress newValue) {
      setValue(idxIpAdEntAddr, newValue);
    }
    
    public Integer32 getIpAdEntIfIndex() {
      return (Integer32) getValue(idxIpAdEntIfIndex);
    }  
    
    public void setIpAdEntIfIndex(Integer32 newValue) {
      setValue(idxIpAdEntIfIndex, newValue);
    }
    
    public IpAddress getIpAdEntNetMask() {
      return (IpAddress) getValue(idxIpAdEntNetMask);
    }  
    
    public void setIpAdEntNetMask(IpAddress newValue) {
      setValue(idxIpAdEntNetMask, newValue);
    }
    
    public Integer32 getIpAdEntBcastAddr() {
      return (Integer32) getValue(idxIpAdEntBcastAddr);
    }  
    
    public void setIpAdEntBcastAddr(Integer32 newValue) {
      setValue(idxIpAdEntBcastAddr, newValue);
    }
    
    public Integer32 getIpAdEntReasmMaxSize() {
      return (Integer32) getValue(idxIpAdEntReasmMaxSize);
    }  
    
    public void setIpAdEntReasmMaxSize(Integer32 newValue) {
      setValue(idxIpAdEntReasmMaxSize, newValue);
    }
    

     //--AgentGen BEGIN=ipAddrEntry::Row
     //--AgentGen END
  }
  
  class IpAddrEntryRowFactory 
        extends DefaultMOMutableRow2PCFactory 
  {
    public synchronized MOTableRow createRow(OID index, Variable[] values) 
        throws UnsupportedOperationException 
    {
      IpAddrEntryRow row = new IpAddrEntryRow(index, values);
     //--AgentGen BEGIN=ipAddrEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(MOTableRow row) {
     //--AgentGen BEGIN=ipAddrEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=ipAddrEntry::RowFactory
     //--AgentGen END
  }
  public class IpNetToMediaEntryRow extends DefaultMOMutableRow2PC {
    public IpNetToMediaEntryRow(OID index, Variable[] values) {
      super(index, values);
    }
    
    public Integer32 getIpNetToMediaIfIndex() {
      return (Integer32) getValue(idxIpNetToMediaIfIndex);
    }  
    
    public void setIpNetToMediaIfIndex(Integer32 newValue) {
      setValue(idxIpNetToMediaIfIndex, newValue);
    }
    
    public OctetString getIpNetToMediaPhysAddress() {
      return (OctetString) getValue(idxIpNetToMediaPhysAddress);
    }  
    
    public void setIpNetToMediaPhysAddress(OctetString newValue) {
      setValue(idxIpNetToMediaPhysAddress, newValue);
    }
    
    public IpAddress getIpNetToMediaNetAddress() {
      return (IpAddress) getValue(idxIpNetToMediaNetAddress);
    }  
    
    public void setIpNetToMediaNetAddress(IpAddress newValue) {
      setValue(idxIpNetToMediaNetAddress, newValue);
    }
    
    public Integer32 getIpNetToMediaType() {
      return (Integer32) getValue(idxIpNetToMediaType);
    }  
    
    public void setIpNetToMediaType(Integer32 newValue) {
      setValue(idxIpNetToMediaType, newValue);
    }
    

     //--AgentGen BEGIN=ipNetToMediaEntry::Row
     //--AgentGen END
  }
  
  class IpNetToMediaEntryRowFactory 
        extends DefaultMOMutableRow2PCFactory 
  {
    public synchronized MOTableRow createRow(OID index, Variable[] values) 
        throws UnsupportedOperationException 
    {
      IpNetToMediaEntryRow row = new IpNetToMediaEntryRow(index, values);
     //--AgentGen BEGIN=ipNetToMediaEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(MOTableRow row) {
     //--AgentGen BEGIN=ipNetToMediaEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=ipNetToMediaEntry::RowFactory
     //--AgentGen END
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

//--AgentGen BEGIN=_CLASSES
//--AgentGen END

//--AgentGen BEGIN=_END
//--AgentGen END
}


