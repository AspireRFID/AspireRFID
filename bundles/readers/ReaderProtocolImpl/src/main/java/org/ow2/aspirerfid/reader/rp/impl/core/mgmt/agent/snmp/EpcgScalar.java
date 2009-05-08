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

import java.util.Date;

import org.ow2.aspirerfid.reader.rp.impl.core.ReaderDevice;
import org.ow2.aspirerfid.reader.rp.impl.core.ReaderProtocolException;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmLevel;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util.SnmpUtil;
import org.apache.log4j.Logger;
import org.snmp4j.agent.MOAccess;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.Gauge32;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TimeTicks;
import org.snmp4j.smi.UnsignedInteger32;
import org.snmp4j.smi.Variable;

public class EpcgScalar extends MOScalar {
	
	/**
	 * Default value of the refresh time.
	 */
	private long refreshTimeInMs = 100;
	
	/**
	 * The logger.
	 */
	private static Logger log = Logger.getLogger(EpcgScalar.class);
	
	/**
	 * The time stamp of the last refresh.
	 */
	private long lastRefreshTime;
	
	/**
	 * The scalar type.
	 */
	private EpcgScalarType type;
	
	/**
	 * The objects needed by this scalar.
	 */
	private Object[] objects;

	/**
	 * Constructor.
	 * 
	 * @param type
	 *            The scalar type
	 * @param access
	 *            The maximum access level supported by this instance
	 * @param value
	 *            The initial value of the scalar instance. If the initial value
	 *            is null or a Counter syntax, the scalar is created as a
	 *            volatile (non-persistent) instance by default
	 * @param object
	 *            The object needed by this scalar
	 */
	public EpcgScalar(EpcgScalarType type, MOAccess access, Variable value, Object object) {
		super(EpcgScalar.computeOID(type), access, value);
		objects = new Object[] { object };
		this.type = type;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param type
	 *            The scalar type
	 * @param access
	 *            The maximum access level supported by this instance
	 * @param value
	 *            The initial value of the scalar instance. If the initial value
	 *            is null or a Counter syntax, the scalar is created as a
	 *            volatile (non-persistent) instance by default
	 * @param objects
	 *            The objects needed by this scalar
	 */
	public EpcgScalar(EpcgScalarType type, MOAccess access, Variable value, Object[] objects) {
		super(EpcgScalar.computeOID(type), access, value);
		this.objects = objects;
		this.type = type;
	}
	
	/**
	 * Returns the <code>OID</code> for a <code>EpcgScalarType</code>.
	 * 
	 * @param type
	 *            The <code>EpcgScalarType</code>
	 * @return The <code>OID</code> for a <code>EpcgScalarType</code>
	 */
	private static OID computeOID(EpcgScalarType type) {
		switch (type) {
			case EPCG_RDR_DEV_DESCRIPTION:
				return EpcglobalReaderMib.oidEpcgRdrDevDescription;
			case EPCG_RDR_DEV_ROLE:
				return EpcglobalReaderMib.oidEpcgRdrDevRole;
			case EPCG_RDR_DEV_EPC:
				return EpcglobalReaderMib.oidEpcgRdrDevEpc;
			case EPCG_RDR_DEV_SERIAL_NUMBER:
				return EpcglobalReaderMib.oidEpcgRdrDevSerialNumber;
			case EPCG_RDR_DEV_TIME_UTC:
				return EpcglobalReaderMib.oidEpcgRdrDevTimeUtc;
			case EPCG_RDR_DEV_CURRENT_SOURCE:
				return EpcglobalReaderMib.oidEpcgRdrDevCurrentSource;
			case EPCG_RDR_DEV_REBOOT:
				return EpcglobalReaderMib.oidEpcgRdrDevReboot;
			case EPCG_RDR_DEV_RESET_STATISTICS:
				return EpcglobalReaderMib.oidEpcgRdrDevResetStatistics;
			case EPCG_RDR_DEV_RESET_TIMESTAMP:
				return EpcglobalReaderMib.oidEpcgRdrDevResetTimestamp;
			case EPCG_RDR_DEV_NORMALIZE_POWER_LEVEL:
				return EpcglobalReaderMib.oidEpcgRdrDevNormalizePowerLevel;
			case EPCG_RDR_DEV_NORMALIZE_NOISE_LEVEL:
				return EpcglobalReaderMib.oidEpcgRdrDevNormalizeNoiseLevel;
			case EPCG_RDR_DEV_OPER_STATUS:
				return EpcglobalReaderMib.oidEpcgRdrDevOperStatus;
			case EPCG_RDR_DEV_OPER_STATUS_PRIOR:
				return EpcglobalReaderMib.oidTrapVarEpcgRdrDevOperStatusPrior;
			case EPCG_RDR_DEV_OPER_STATE_ENABLE:
				return EpcglobalReaderMib.oidEpcgRdrDevOperStateEnable;
			case EPCG_RDR_DEV_OPER_NOTIF_FROM_STATE:
				return EpcglobalReaderMib.oidEpcgRdrDevOperNotifFromState;
			case EPCG_RDR_DEV_OPER_NOTIF_TO_STATE:
				return EpcglobalReaderMib.oidEpcgRdrDevOperNotifToState;
			case EPCG_RDR_DEV_OPER_NOTIF_STATE_LEVEL:
				return EpcglobalReaderMib.oidEpcgRdrDevOperNotifStateLevel;
			case EPCG_RDR_DEV_OPER_STATE_SUPPRESS_INTERVAL:
				return EpcglobalReaderMib.oidEpcgRdrDevOperStateSuppressInterval;
			case EPCG_RDR_DEV_OPER_STATE_SUPPRESSIONS:
				return EpcglobalReaderMib.oidEpcgRdrDevOperStateSuppressions;
			case EPCG_RDR_DEV_FREE_MEMORY:
				return EpcglobalReaderMib.oidEpcgRdrDevFreeMemory;
			case EPCG_RDR_DEV_FREE_MEMORY_NOTIF_ENABLE:
				return EpcglobalReaderMib.oidEpcgRdrDevFreeMemoryNotifEnable;
			case EPCG_RDR_DEV_FREE_MEMORY_NOTIF_LEVEL:
				return EpcglobalReaderMib.oidEpcgRdrDevFreeMemoryNotifLevel;
			case EPCG_RDR_DEV_FREE_MEMORY_ONSET_THRESHOLD:
				return EpcglobalReaderMib.oidEpcgRdrDevFreeMemoryOnsetThreshold;
			case EPCG_RDR_DEV_FREE_MEMORY_ABATE_THRESHOLD:
				return EpcglobalReaderMib.oidEpcgRdrDevFreeMemoryAbateThreshold;
			case EPCG_RDR_DEV_FREE_MEMORY_STATUS:
				return EpcglobalReaderMib.oidEpcgRdrDevFreeMemoryStatus;
			case EPCG_RDR_DEV_MEM_STATE_SUPPRESS_INTERVAL:
				return EpcglobalReaderMib.oidEpcgRdrDevMemStateSuppressInterval;
			case EPCG_RDR_DEV_MEM_STATE_SUPPRESSIONS:
				return EpcglobalReaderMib.oidEpcgRdrDevMemStateSuppressions;
//			case EPCG_READ_POINT_PRIOR_OPER_STATUS:
//				return EpcglobalReaderMib.oidTrapVarEpcgReadPointPriorOperStatus;
//			case EPCG_READ_POINT_OPER_STATE_SUPPRESS_INTERVAL:
//				return EpcglobalReaderMib.oidEpcgReadPointOperStateSuppressInterval;
//			case EPCG_READ_POINT_OPER_STATE_SUPPRESSIONS:
//				return EpcglobalReaderMib.oidEpcgReadPointOperStateSuppressions;
//			case EPCG_ANT_RD_PNT_SUPPRESS_INTERVAL:
//				return EpcglobalReaderMib.oidEpcgAntRdPntSuppressInterval;
//			case EPCG_IO_PORT_OPER_STATUS_PRIOR:
//				return EpcglobalReaderMib.oidTrapVarEpcgIoPortOperStatusPrior;
//			case EPCG_IO_PORT_OPER_STATE_SUPPRESS_INTERVAL:
//				return EpcglobalReaderMib.oidEpcgIoPortOperStateSuppressInterval;
//			case EPCG_IO_PORT_OPER_STATE_SUPPRESSIONS:
//				return EpcglobalReaderMib.oidEpcgIoPortOperStateSuppressions;
//			case EPCG_SRC_OPER_PRIOR_STATUS:
//				return EpcglobalReaderMib.oidTrapVarEpcgSrcOperStatusPrior;
//			case EPCG_SRC_OPER_STATE_SUPPRESS_INTERVAL:
//				return EpcglobalReaderMib.oidEpcgSrcOperStateSuppressInterval;
//			case EPCG_SRC_OPER_STATE_SUPPRESSIONS:
//				return EpcglobalReaderMib.oidEpcgSrcOperStateSuppressions;
//			case EPCG_NOTIF_CHAN_OPER_STATUS_PRIOR:
//				return EpcglobalReaderMib.oidTrapVarEpcgNotifChanOperStatusPrior;
//			case EPCG_NOTIF_CHAN_OPER_STATE_SUPPRESS_INTERVAL:
//				return EpcglobalReaderMib.oidEpcgNotifChanOperStateSuppressInterval;
//			case EPCG_NOTIF_CHAN_OPER_STATE_SUPPRESSIONS:
//				return EpcglobalReaderMib.oidEpcgNotifChanOperStateSuppressions;
			case SYS_DESCR:
				return new OID(new int[] { 1,3,6,1,2,1,1,1,0 });
			case SYS_LOCATION:
				return new OID(new int[] { 1,3,6,1,2,1,1,6,0 });
			case SYS_CONTACT:
				return new OID(new int[] { 1,3,6,1,2,1,1,4,0 });
			case SYS_UP_TIME:
				return new OID(new int[] { 1,3,6,1,2,1,1,3,0 });
			case SYS_NAME:
				return new OID(new int[] { 1,3,6,1,2,1,1,5,0 });
		}
		return null;
	}

//	/**
//	 * Commits a previously prepared <code>SET</code> (sub)request. This is
//	 * the second phase of a two phase commit. The change is committed but the
//	 * resources locked during prepare not freed yet.
//	 * 
//	 * @param request
//	 *            The <code>SubRequest</code> to process
//	 */
//	@Override
//	public void commit(SubRequest request) {
//		setValue(request.getVariableBinding().getVariable());
//		super.commit(request);
//	}

	/**
	 * Returns the actual value of this scalar managed object.
	 * 
	 * @return A non <code>null</code> <code>Variable</code> with the same
	 *         syntax defined for this scalar object
	 */
	@Override
	public Variable getValue() {
		
		boolean refresh = ((System.currentTimeMillis() - lastRefreshTime) > refreshTimeInMs);
		if (refresh) {
			log.debug("refreshing " + type);
			lastRefreshTime = System.currentTimeMillis();
		} else {
			log.debug("no need to refresh " + type);
		}
		
		if (refresh) {
			
			ReaderDevice readerDevice = null;
			
			switch (type) {
				case EPCG_RDR_DEV_DESCRIPTION:
					setValue(new OctetString(((ReaderDevice)(objects[0])).getDescription()));
					break;
				case EPCG_RDR_DEV_ROLE:
					setValue(new OctetString(((ReaderDevice)(objects[0])).getRole()));
					break;
				case EPCG_RDR_DEV_EPC:
					setValue(new OctetString(((ReaderDevice)(objects[0])).getEPC()));
					break;
				case EPCG_RDR_DEV_SERIAL_NUMBER:
					setValue(new OctetString(((ReaderDevice)(objects[0])).getSerialNumber()));
					break;
				case EPCG_RDR_DEV_TIME_UTC:
					setValue(SnmpUtil.dateToOctetString(((ReaderDevice)(objects[0])).getTimeUTC()));
					break;
				case EPCG_RDR_DEV_CURRENT_SOURCE:
					SnmpTable sourceTable = (SnmpTable)SnmpUtil.getSnmpTable(TableTypeEnum.EPCG_SOURCE_TABLE);
					String sourceName = ((ReaderDevice)objects[0]).getCurrentSource().getName();
					OID index = sourceTable.getTableRowIndexByValue(new OctetString(sourceName), EpcglobalReaderMib.idxEpcgSrcName);
					OID oid = new OID(EpcglobalReaderMib.oidEpcgSourceEntry);
					oid.append(EpcglobalReaderMib.colEpcgSrcName);
					oid.append(index);
					setValue(oid);
					break;
				case EPCG_RDR_DEV_REBOOT:
					setValue(new Integer32(2));
					break;
				case EPCG_RDR_DEV_RESET_STATISTICS:
					setValue(new Integer32(2));
					break;
				case EPCG_RDR_DEV_RESET_TIMESTAMP:
					// do nothing
					break;
				case EPCG_RDR_DEV_NORMALIZE_POWER_LEVEL:
					setValue(new Integer32(1));
					break;
				case EPCG_RDR_DEV_NORMALIZE_NOISE_LEVEL:
					setValue(new Integer32(1));
					break;
				case EPCG_RDR_DEV_OPER_STATUS:
					setValue(new Integer32(((ReaderDevice)objects[0]).getOperStatus().toInt()));
					break;
				case EPCG_RDR_DEV_OPER_STATUS_PRIOR:
					// do nothing
					break;
				case EPCG_RDR_DEV_OPER_STATE_ENABLE:
					setValue(new Integer32(((ReaderDevice)objects[0]).getOperStatusAlarmControl().getEnabled() ? 1 : 2));
					break;
				case EPCG_RDR_DEV_OPER_NOTIF_FROM_STATE:
					setValue(SnmpUtil.operStateToBITS(((ReaderDevice)objects[0]).getOperStatusAlarmControl().getTriggerFromState()));
					break;
				case EPCG_RDR_DEV_OPER_NOTIF_TO_STATE:
					setValue(SnmpUtil.operStateToBITS(((ReaderDevice)objects[0]).getOperStatusAlarmControl().getTriggerToState()));
					break;
				case EPCG_RDR_DEV_OPER_NOTIF_STATE_LEVEL:
					setValue(new Integer32(((ReaderDevice)objects[0]).getOperStatusAlarmControl().getLevel().toInt()));
					break;
				case EPCG_RDR_DEV_OPER_STATE_SUPPRESS_INTERVAL:
					setValue(new UnsignedInteger32(((ReaderDevice)objects[0]).getOperStatusAlarmControl().getSuppressInterval()));
					break;
				case EPCG_RDR_DEV_OPER_STATE_SUPPRESSIONS:
					setValue(new Counter32(ReaderDevice.getOperStateSuppressions()));
					break;
				case EPCG_RDR_DEV_FREE_MEMORY:
					setValue(new Gauge32(((ReaderDevice)objects[0]).getFreeMemory()));
					break;
				case EPCG_RDR_DEV_FREE_MEMORY_NOTIF_ENABLE:
					setValue(new Integer32(((ReaderDevice)objects[0]).getFreeMemoryAlarmControl().getEnabled() ? 1 : 2));
					break;
				case EPCG_RDR_DEV_FREE_MEMORY_NOTIF_LEVEL:
					setValue(new Integer32(((ReaderDevice)objects[0]).getFreeMemoryAlarmControl().getLevel().toInt()));
					break;
				case EPCG_RDR_DEV_FREE_MEMORY_ONSET_THRESHOLD:
					setValue(new UnsignedInteger32(((ReaderDevice)objects[0]).getFreeMemoryAlarmControl().getAlarmThreshold()));
					break;
				case EPCG_RDR_DEV_FREE_MEMORY_ABATE_THRESHOLD:
					setValue(new UnsignedInteger32(((ReaderDevice)objects[0]).getFreeMemoryAlarmControl().getRearmThreshold()));
					break;
				case EPCG_RDR_DEV_FREE_MEMORY_STATUS:
					readerDevice = ((ReaderDevice)objects[0]);
					setValue(new Integer32(readerDevice.getFreeMemory() < readerDevice.getFreeMemoryAlarmControl().getAlarmThreshold() ? 1 : 2));
					break;
				case EPCG_RDR_DEV_MEM_STATE_SUPPRESS_INTERVAL:
					setValue(new UnsignedInteger32(((ReaderDevice)objects[0]).getFreeMemoryAlarmControl().getSuppressInterval()));
					break;
				case EPCG_RDR_DEV_MEM_STATE_SUPPRESSIONS:
					setValue(new Counter32(ReaderDevice.getMemStateSuppressions()));
					break;
//				case EPCG_READ_POINT_PRIOR_OPER_STATUS:
//					// do nothing
//					break;
//				case EPCG_READ_POINT_OPER_STATE_SUPPRESS_INTERVAL:
//					// ...
//					break;
//				case EPCG_READ_POINT_OPER_STATE_SUPPRESSIONS:
//					setValue(new Counter32(ReadPoint.getOperStateSuppressions()));
//					break;
//				case EPCG_ANT_RD_PNT_SUPPRESS_INTERVAL:
//					// ...
//					break;
//				case EPCG_IO_PORT_OPER_STATUS_PRIOR:
//					// do nothing
//					break;
//				case EPCG_IO_PORT_OPER_STATE_SUPPRESS_INTERVAL:
//					// ...
//					break;
//				case EPCG_IO_PORT_OPER_STATE_SUPPRESSIONS:
//					setValue(new Counter32(IOPort.getOperStateSuppressions()));
//					break;
//				case EPCG_SRC_OPER_PRIOR_STATUS:
//					// do nothing
//					break;
//				case EPCG_SRC_OPER_STATE_SUPPRESS_INTERVAL:
//					// ...
//					break;
//				case EPCG_SRC_OPER_STATE_SUPPRESSIONS:
//					setValue(new Counter32(Source.getOperStateSuppressions()));
//					break;
//				case EPCG_NOTIF_CHAN_OPER_STATUS_PRIOR:
//					// do nothing
//					break;
//				case EPCG_NOTIF_CHAN_OPER_STATE_SUPPRESS_INTERVAL:
//					// ...
//					break;
//				case EPCG_NOTIF_CHAN_OPER_STATE_SUPPRESSIONS:
//					setValue(new Counter32(NotificationChannel.getOperStateSuppressions()));
//					break;
				case SYS_DESCR:
					readerDevice = (ReaderDevice)objects[0];
					String descr =
						"Manufacturer: "
						+ readerDevice.getManufacturer()
						+ ", Model: "
						+ readerDevice.getModel()
						+ ", Manufacturer Description: "
						+ readerDevice.getManufacturerDescription();
					setValue(new OctetString(descr));
					break;
				case SYS_LOCATION:
					setValue(new OctetString(((ReaderDevice)objects[0]).getLocationDescription()));
					break;
				case SYS_CONTACT:
					setValue(new OctetString(((ReaderDevice)objects[0]).getContact()));
					break;
				case SYS_UP_TIME:
					setValue(new TimeTicks(((ReaderDevice)objects[0]).getTimeTicks()));
					break;
				case SYS_NAME:
					setValue(new OctetString(((ReaderDevice)objects[0]).getName()));
					break;
			}
		}
		return super.getValue();
	}

	/**
	 * Sets the value of this scalar managed object without checking it for the
	 * correct syntax.
	 * 
	 * @param value
	 *            A <code>Variable</code> with the same syntax defined for
	 *            this scalar object (not checked)
	 * @return A SNMP error code (zero indicating success by default)
	 */
	@Override
	public int setValue(Variable value) {
		switch (type) {
			case EPCG_RDR_DEV_REBOOT:
				boolean reboot = ((Integer32)value).toInt() == 1;
				if (reboot) {
					try {
						((ReaderDevice)(objects[0])).reboot();
					} catch (ReaderProtocolException rpe) {
						log.error(rpe.getMessage());
					}
				}
				break;
			case EPCG_RDR_DEV_RESET_STATISTICS:
				boolean resetStatistics = ((Integer32) value).toInt() == 1;
				if (resetStatistics) {
					((ReaderDevice) (objects[0])).resetStatistics();
					MOScalar resetTimestamp = SnmpUtil.findMOScalar(EpcglobalReaderMib.oidEpcgRdrDevResetTimestamp);
					if (resetTimestamp != null) {
						resetTimestamp.setValue(SnmpUtil.dateToOctetString(new Date()));
					}
				}
				break;
			case EPCG_RDR_DEV_OPER_STATE_ENABLE:
				((ReaderDevice) (objects[0])).getOperStatusAlarmControl().setEnabled(((Integer32)value).toInt() == 1);
				break;
			case EPCG_RDR_DEV_OPER_NOTIF_FROM_STATE:
				((ReaderDevice) (objects[0])).getOperStatusAlarmControl().setTriggerFromState(SnmpUtil.bitsToOperState((OctetString)value));
				break;
			case EPCG_RDR_DEV_OPER_NOTIF_TO_STATE:
				((ReaderDevice) (objects[0])).getOperStatusAlarmControl().setTriggerToState(SnmpUtil.bitsToOperState((OctetString)value));
				break;
			case EPCG_RDR_DEV_OPER_NOTIF_STATE_LEVEL:
				((ReaderDevice) (objects[0])).getOperStatusAlarmControl().setLevel(AlarmLevel.intToEnum(((Integer32)value).toInt()));
				break;
			case EPCG_RDR_DEV_OPER_STATE_SUPPRESS_INTERVAL:
				((ReaderDevice) (objects[0])).getOperStatusAlarmControl().setSuppressInterval(((UnsignedInteger32)value).toInt());
				break;
			case EPCG_RDR_DEV_FREE_MEMORY_NOTIF_ENABLE:
				((ReaderDevice) (objects[0])).getFreeMemoryAlarmControl().setEnabled(((Integer32)value).toInt() == 1);
				break;
			case EPCG_RDR_DEV_FREE_MEMORY_NOTIF_LEVEL:
				((ReaderDevice) (objects[0])).getFreeMemoryAlarmControl().setLevel(AlarmLevel.intToEnum(((Integer32)value).toInt()));
				break;
			case EPCG_RDR_DEV_FREE_MEMORY_ONSET_THRESHOLD:
				((ReaderDevice) (objects[0])).getFreeMemoryAlarmControl().setAlarmThreshold(((UnsignedInteger32)value).toInt());
				break;
			case EPCG_RDR_DEV_FREE_MEMORY_ABATE_THRESHOLD:
				((ReaderDevice) (objects[0])).getFreeMemoryAlarmControl().setRearmThreshold(((UnsignedInteger32)value).toInt());
				break;
			case EPCG_RDR_DEV_MEM_STATE_SUPPRESS_INTERVAL:
				((ReaderDevice) (objects[0])).getFreeMemoryAlarmControl().setSuppressInterval(((UnsignedInteger32)value).toInt());
				break;
			case SYS_CONTACT:
				((ReaderDevice) (objects[0])).setContact(((OctetString)value).toString());
				break;
			case SYS_LOCATION:
				((ReaderDevice) (objects[0])).setLocationDescription(((OctetString)value).toString());
				break;
			case SYS_NAME:
				((ReaderDevice) (objects[0])).setName(((OctetString)value).toString());
				break;
				
		}
		return super.setValue(value);
	}
	
	/**
	 * Forces a refresh.
	 */
	public void forceRefresh() {
		lastRefreshTime = 0;
	}
	
	/**
	 * Sets the refresh time in ms.
	 * 
	 * @param refreshTimeInMs
	 *            Refresh time in ms
	 */
	public void setRefreshTime(long refreshTimeInMs) {
		this.refreshTimeInMs = refreshTimeInMs;
	}

}
