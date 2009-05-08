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

import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.mib.EpcglobalReaderMib;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp.table.SnmpTable.TableTypeEnum;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.Alarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.AlarmProcessor;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.FailedEraseAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.FailedKillAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.FailedLockAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.FailedMemReadAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.FailedWriteAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.FreeMemoryAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.IOPortOperStatusAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.NotificationChannelOperStatusAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.ReadPointOperStatusAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.ReaderDeviceOperStatusAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.alarm.SourceOperStatusAlarm;
import org.ow2.aspirerfid.reader.rp.impl.core.mgmt.util.SnmpUtil;
import org.snmp4j.agent.NotificationOriginator;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.smi.Gauge32;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;

/**
 * This class can be used to process <code>Alarm</code> objects using SNMP.
 */
public class SnmpAlarmProcessor implements AlarmProcessor {
	
	/**
	 * The SNMP agent.
	 */
	private SnmpAgent snmpAgent;
	
	/**
	 * The <code>EpcglobalReaderMib</code>.
	 */
	private EpcglobalReaderMib epcglobalReaderMib;
	
	/**
	 * Constructor.
	 * 
	 * @param snmpAgent
	 *            The SNMP agent
	 */
	public SnmpAlarmProcessor(SnmpAgent snmpAgent) {
		this.snmpAgent = snmpAgent;
		epcglobalReaderMib = EpcglobalReaderMib.getInstance();
	}
	
	/**
	 * Processes an <code>Alarm</code> object.
	 * 
	 * @param alarm
	 *            The <code>Alarm</code> object
	 */
	public void process(Alarm alarm) {
		NotificationOriginator notifOrig = snmpAgent.getNotificationOriginator();
		
		if (notifOrig != null) {
		
			if (alarm instanceof ReaderDeviceOperStatusAlarm) {
				ReaderDeviceOperStatusAlarm readerDeviceOperStatusAlarm = (ReaderDeviceOperStatusAlarm) alarm;
				MOScalar operStatePrior = SnmpUtil
						.findMOScalar(EpcglobalReaderMib.oidTrapVarEpcgRdrDevOperStatusPrior);
				operStatePrior.setValue(new Integer32(
						readerDeviceOperStatusAlarm.getFromState().toInt()));
				epcglobalReaderMib
						.epcgReaderDeviceOperationState(
								notifOrig,
								new OctetString(),
								new VariableBinding[] {
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarSysName,
												new OctetString(
														readerDeviceOperStatusAlarm
																.getReaderDeviceName())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevTimeUtc,
												SnmpUtil
														.dateToOctetString(readerDeviceOperStatusAlarm
																.getTimeUTC())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevOperNotifStateLevel,
												new Integer32(
														readerDeviceOperStatusAlarm
																.getAlarmLevel()
																.toInt())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevOperStatusPrior,
												operStatePrior.getValue()),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevOperStatus,
												new Integer32(
														readerDeviceOperStatusAlarm
																.getToState()
																.toInt())) });
			} else if (alarm instanceof FreeMemoryAlarm) {
				FreeMemoryAlarm freeMemoryAlarm = (FreeMemoryAlarm) alarm;
				epcglobalReaderMib
						.epcgRdrDevMemoryState(
								notifOrig,
								new OctetString(),
								new VariableBinding[] {
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarSysName,
												new OctetString(freeMemoryAlarm
														.getReaderDeviceName())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevTimeUtc,
												SnmpUtil
														.dateToOctetString(freeMemoryAlarm
																.getTimeUTC())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevFreeMemoryNotifLevel,
												new Integer32(freeMemoryAlarm
														.getAlarmLevel()
														.toInt())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevFreeMemory,
												new Gauge32(freeMemoryAlarm
														.getFreeMemory())) });
			} else if (alarm instanceof ReadPointOperStatusAlarm) {
				ReadPointOperStatusAlarm readPointOperStatusAlarm = (ReadPointOperStatusAlarm) alarm;
				OID index = ((SnmpTable) SnmpUtil
						.getSnmpTable(TableTypeEnum.EPCG_READ_POINT_TABLE))
						.getTableRowIndexByValue(new OctetString(
								readPointOperStatusAlarm.getReadPointName()),
								EpcglobalReaderMib.idxEpcgReadPointName);
				Integer32 operStatePrior = new Integer32(readPointOperStatusAlarm.getFromState().toInt());
				epcglobalReaderMib
						.epcgReadPointOperationState(
								notifOrig,
								new OctetString(),
								new VariableBinding[] {
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarSysName,
												new OctetString(
														readPointOperStatusAlarm
																.getReaderDeviceName())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevTimeUtc,
												SnmpUtil
														.dateToOctetString(readPointOperStatusAlarm
																.getTimeUTC())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgReadPointOperNotifyStateLevel
																+ "." + index),
												new Integer32(
														readPointOperStatusAlarm
																.getAlarmLevel()
																.toInt())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgReadPointName
																+ "." + index),
												new OctetString(
														readPointOperStatusAlarm
																.getReadPointName())),
										new VariableBinding(
												new OID(EpcglobalReaderMib.oidTrapVarEpcgReadPointPriorOperStatus + "." + index),
												operStatePrior),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgReadPointOperStatus
																+ "." + index),
												new Integer32(
														readPointOperStatusAlarm
																.getToState()
																.toInt())) });
			} else if (alarm instanceof FailedMemReadAlarm) {
				FailedMemReadAlarm failedMemReadAlarm = (FailedMemReadAlarm) alarm;
				OID index = ((SnmpTable) SnmpUtil
						.getSnmpTable(TableTypeEnum.EPCG_READ_POINT_TABLE))
						.getTableRowIndexByValue(new OctetString(
								failedMemReadAlarm.getReadPointName()),
								EpcglobalReaderMib.idxEpcgReadPointName);
				epcglobalReaderMib
						.epcgReaderAntennaReadFailure(
								notifOrig,
								new OctetString(),
								new VariableBinding[] {
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarSysName,
												new OctetString(
														failedMemReadAlarm
																.getReaderDeviceName())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevTimeUtc,
												SnmpUtil
														.dateToOctetString(failedMemReadAlarm
																.getTimeUTC())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntReadFailureNotifLevel
																+ "." + index),
												new Integer32(
														failedMemReadAlarm
																.getAlarmLevel()
																.toInt())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgReadPointName
																+ "." + index),
												new OctetString(
														failedMemReadAlarm
																.getReadPointName())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntMemoryReadFailures
																+ "." + index),
												new Gauge32(
														failedMemReadAlarm
																.getFailedMemReadCount())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntNoiseLevel
																+ "." + index),
												new Integer32(
														failedMemReadAlarm
																.getNoiseLevel())) });
			} else if (alarm instanceof FailedWriteAlarm) {
				FailedWriteAlarm failedWriteAlarm = (FailedWriteAlarm) alarm;
				OID index = ((SnmpTable) SnmpUtil
						.getSnmpTable(TableTypeEnum.EPCG_READ_POINT_TABLE))
						.getTableRowIndexByValue(new OctetString(
								failedWriteAlarm.getReadPointName()),
								EpcglobalReaderMib.idxEpcgReadPointName);
				epcglobalReaderMib
						.epcgReaderAntennaWriteFailure(
								notifOrig,
								new OctetString(),
								new VariableBinding[] {
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarSysName,
												new OctetString(
														failedWriteAlarm
																.getReaderDeviceName())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevTimeUtc,
												SnmpUtil
														.dateToOctetString(failedWriteAlarm
																.getTimeUTC())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntWriteFailuresNotifLevel
																+ "." + index),
												new Integer32(failedWriteAlarm
														.getAlarmLevel()
														.toInt())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgReadPointName
																+ "." + index),
												new OctetString(
														failedWriteAlarm
																.getReadPointName())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntWriteFailures
																+ "." + index),
												new Gauge32(failedWriteAlarm
														.getFailedWriteCount())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntNoiseLevel
																+ "." + index),
												new Integer32(failedWriteAlarm
														.getNoiseLevel())) });
			} else if (alarm instanceof FailedKillAlarm) {
				FailedKillAlarm failedKillAlarm = (FailedKillAlarm) alarm;
				OID index = ((SnmpTable) SnmpUtil
						.getSnmpTable(TableTypeEnum.EPCG_READ_POINT_TABLE))
						.getTableRowIndexByValue(new OctetString(
								failedKillAlarm.getReadPointName()),
								EpcglobalReaderMib.idxEpcgReadPointName);
				epcglobalReaderMib
						.epcgReaderAntennaKillFailure(
								notifOrig,
								new OctetString(),
								new VariableBinding[] {
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarSysName,
												new OctetString(failedKillAlarm
														.getReaderDeviceName())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevTimeUtc,
												SnmpUtil
														.dateToOctetString(failedKillAlarm
																.getTimeUTC())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntKillFailuresNotifLevel
																+ "." + index),
												new Integer32(failedKillAlarm
														.getAlarmLevel()
														.toInt())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgReadPointName
																+ "." + index),
												new OctetString(failedKillAlarm
														.getReadPointName())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntKillFailures
																+ "." + index),
												new Gauge32(failedKillAlarm
														.getFailedKillCount())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntNoiseLevel
																+ "." + index),
												new Integer32(failedKillAlarm
														.getNoiseLevel())) });
			} else if (alarm instanceof FailedEraseAlarm) {
				FailedEraseAlarm failedEraseAlarm = (FailedEraseAlarm) alarm;
				OID index = ((SnmpTable) SnmpUtil
						.getSnmpTable(TableTypeEnum.EPCG_READ_POINT_TABLE))
						.getTableRowIndexByValue(new OctetString(
								failedEraseAlarm.getReadPointName()),
								EpcglobalReaderMib.idxEpcgReadPointName);
				epcglobalReaderMib
						.epcgReaderAntennaEraseFailure(
								notifOrig,
								new OctetString(),
								new VariableBinding[] {
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarSysName,
												new OctetString(
														failedEraseAlarm
																.getReaderDeviceName())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevTimeUtc,
												SnmpUtil
														.dateToOctetString(failedEraseAlarm
																.getTimeUTC())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntEraseFailuresNotifLevel
																+ "." + index),
												new Integer32(failedEraseAlarm
														.getAlarmLevel()
														.toInt())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgReadPointName
																+ "." + index),
												new OctetString(
														failedEraseAlarm
																.getReadPointName())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntEraseFailures
																+ "." + index),
												new Gauge32(failedEraseAlarm
														.getFailedEraseCount())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntNoiseLevel
																+ "." + index),
												new Integer32(failedEraseAlarm
														.getNoiseLevel())) });
			} else if (alarm instanceof FailedLockAlarm) {
				FailedLockAlarm failedLockAlarm = (FailedLockAlarm) alarm;
				OID index = ((SnmpTable) SnmpUtil
						.getSnmpTable(TableTypeEnum.EPCG_READ_POINT_TABLE))
						.getTableRowIndexByValue(new OctetString(
								failedLockAlarm.getReadPointName()),
								EpcglobalReaderMib.idxEpcgReadPointName);
				epcglobalReaderMib
						.epcgReaderAntennaLockFailure(
								notifOrig,
								new OctetString(),
								new VariableBinding[] {
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarSysName,
												new OctetString(failedLockAlarm
														.getReaderDeviceName())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevTimeUtc,
												SnmpUtil
														.dateToOctetString(failedLockAlarm
																.getTimeUTC())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntLockFailuresNotifLevel
																+ "." + index),
												new Integer32(failedLockAlarm
														.getAlarmLevel()
														.toInt())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgReadPointName
																+ "." + index),
												new OctetString(failedLockAlarm
														.getReadPointName())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntLockFailures
																+ "." + index),
												new Gauge32(failedLockAlarm
														.getFailedLockCount())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgAntRdPntNoiseLevel
																+ "." + index),
												new Integer32(failedLockAlarm
														.getNoiseLevel())) });
			} else if (alarm instanceof IOPortOperStatusAlarm) {
				IOPortOperStatusAlarm ioPortOperStatusAlarm = (IOPortOperStatusAlarm) alarm;
				OID index = ((SnmpTable) SnmpUtil
						.getSnmpTable(TableTypeEnum.EPCG_IO_PORT_TABLE))
						.getTableRowIndexByValue(new OctetString(
								ioPortOperStatusAlarm.getIOPortName()),
								EpcglobalReaderMib.idxEpcgIoPortName);
				Integer32 operStatePrior = new Integer32(ioPortOperStatusAlarm.getFromState().toInt());
				epcglobalReaderMib
						.epcgReaderIoPortOperationState(
								notifOrig,
								new OctetString(),
								new VariableBinding[] {
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarSysName,
												new OctetString(
														ioPortOperStatusAlarm
																.getReaderDeviceName())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevTimeUtc,
												SnmpUtil
														.dateToOctetString(ioPortOperStatusAlarm
																.getTimeUTC())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgIoPortOperStatusNotifLevel
																+ "." + index),
												new Integer32(
														ioPortOperStatusAlarm
																.getAlarmLevel()
																.toInt())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgIoPortName
																+ "." + index),
												new OctetString(
														ioPortOperStatusAlarm
																.getIOPortName())),
										new VariableBinding(
												new OID(EpcglobalReaderMib.oidTrapVarEpcgIoPortOperStatusPrior + "." + index),
												operStatePrior),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgIoPortOperStatus
																+ "." + index),
												new Integer32(
														ioPortOperStatusAlarm
																.getToState()
																.toInt())) });
			} else if (alarm instanceof SourceOperStatusAlarm) {
				SourceOperStatusAlarm sourceOperStatusAlarm = (SourceOperStatusAlarm) alarm;
				OID index = ((SnmpTable) SnmpUtil
						.getSnmpTable(TableTypeEnum.EPCG_SOURCE_TABLE))
						.getTableRowIndexByValue(new OctetString(
								sourceOperStatusAlarm.getSourceName()),
								EpcglobalReaderMib.idxEpcgSrcName);
				Integer32 operStatePrior = new Integer32(sourceOperStatusAlarm.getFromState().toInt());
				epcglobalReaderMib
						.epcgReaderSourceOperationState(
								notifOrig,
								new OctetString(),
								new VariableBinding[] {
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarSysName,
												new OctetString(
														sourceOperStatusAlarm
																.getReaderDeviceName())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevTimeUtc,
												SnmpUtil
														.dateToOctetString(sourceOperStatusAlarm
																.getTimeUTC())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgSrcOperStatusNotifyLevel
																+ "." + index),
												new Integer32(
														sourceOperStatusAlarm
																.getAlarmLevel()
																.toInt())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgSrcName
																+ "." + index),
												new OctetString(
														sourceOperStatusAlarm
																.getSourceName())),
										new VariableBinding(
												new OID(EpcglobalReaderMib.oidTrapVarEpcgSrcOperStatusPrior + "." + index),
												operStatePrior),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgSrcOperStatus
																+ "." + index),
												new Integer32(
														sourceOperStatusAlarm
																.getToState()
																.toInt())) });
			} else if (alarm instanceof NotificationChannelOperStatusAlarm) {
				NotificationChannelOperStatusAlarm notificationChannelOperStatusAlarm = (NotificationChannelOperStatusAlarm) alarm;
				OID index = ((SnmpTable) SnmpUtil
						.getSnmpTable(TableTypeEnum.EPCG_NOTIFICATION_CHANNEL_TABLE))
						.getTableRowIndexByValue(new OctetString(
								notificationChannelOperStatusAlarm
										.getNotificationChannelName()),
								EpcglobalReaderMib.idxEpcgNotifChanName);
				Integer32 operStatePrior = new Integer32(notificationChannelOperStatusAlarm.getFromState().toInt());
				epcglobalReaderMib
						.epcgReaderNotificationChanOperState(
								notifOrig,
								new OctetString(),
								new VariableBinding[] {
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarSysName,
												new OctetString(
														notificationChannelOperStatusAlarm
																.getReaderDeviceName())),
										new VariableBinding(
												EpcglobalReaderMib.oidTrapVarEpcgRdrDevTimeUtc,
												SnmpUtil
														.dateToOctetString(notificationChannelOperStatusAlarm
																.getTimeUTC())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgNotifChanOperNotifLevel
																+ "." + index),
												new Integer32(
														notificationChannelOperStatusAlarm
																.getAlarmLevel()
																.toInt())),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgNotifChanName
																+ "." + index),
												new OctetString(
														notificationChannelOperStatusAlarm
																.getNotificationChannelName())),
										new VariableBinding(
												new OID(EpcglobalReaderMib.oidTrapVarEpcgNotifChanOperStatusPrior + "." + index),
												operStatePrior),
										new VariableBinding(
												new OID(
														EpcglobalReaderMib.oidTrapVarEpcgNotifChanOperStatus
																+ "." + index),
												new Integer32(
														notificationChannelOperStatusAlarm
																.getToState()
																.toInt())) });
			}
			
		}
		
	}

}
